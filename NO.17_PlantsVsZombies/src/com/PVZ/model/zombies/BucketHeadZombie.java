package com.PVZ.model.zombies;

import com.PVZ.api.base.constant.SystemConstant;

import static com.PVZ.api.base.constant.SystemConstant.ZOMBIES_PATH;
import static com.PVZ.api.base.constant.SystemConstant.ZOMBIE_DESCRIPTION_PATH;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.PVZ.api.common.Controller;
import com.PVZ.api.interfaces.zombies.WalkingDead;
import com.PVZ.controller.adventure.action.AdventureController;
import com.PVZ.model.plants.Plant;

/**
 * 铁桶僵尸
 *
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies
 * @date 2018年11月8日上午11:21:20
 */
public class BucketHeadZombie extends Zombie implements WalkingDead {

    protected static Image cardImage;
    protected static Image descirptionImage;
    private static Map<String, Image> images;//键值对保存各种图片

    static {
        cardImage = new ImageIcon(SystemConstant.ZOMBIE_CARD_PATH + "BucketZombieCard.png").getImage();
        descirptionImage = new ImageIcon(ZOMBIE_DESCRIPTION_PATH + "BucketZombieAbout.png").getImage();
        images = new HashMap<>();
        images.put("lostHeadImage", new ImageIcon(ZOMBIES_PATH + "Zombie/ZombieLostHead.gif").getImage());
        images.put("lostHeadEatingImage", new ImageIcon(ZOMBIES_PATH + "Zombie/ZombieLostHeadAttack.gif").getImage());
        images.put("eatingImage", new ImageIcon(ZOMBIES_PATH + "BucketheadZombie/BucketheadZombieAttack.gif").getImage());
        images.put("lostOrnamentEating", new ImageIcon(ZOMBIES_PATH + "Zombie/ZombieAttack.gif").getImage());
    }

    public BucketHeadZombie() {
        this.activeImage = new ImageIcon(ZOMBIES_PATH + "BucketheadZombie/BucketheadZombie.gif").getImage();
    }

    public <T extends Controller> BucketHeadZombie(int movingRow, T pc) {
        super(movingRow, pc);
        this.col_posX = posX + 70;
        this.col_posY = posY + 50;
        this.col_width = 40;
        this.col_height = 80;
        this.current_health = 1370;//铁桶1100+本体200+临界点70
        changeSpeed(4700);
        this.damage = 100/pc.getFPS();
        
        int random = (int) (1 + Math.random() * 3);
        images.put("lostOrnament", new ImageIcon(SystemConstant.ZOMBIES_PATH + "Zombie/Zombie" + random + ".gif").getImage());

        this.activeImage = this.getImageByName("movingImage");
        this.status = SystemConstant.ZOMBIE_MOVING;
    }

    public BucketHeadZombie(int movingRow, AdventureController pc) {
        super(movingRow, pc);
        this.col_posX = posX + 70;
        this.col_posY = posY + 50;
        this.col_width = 40;
        this.col_height = 80;
        this.current_health = 1370;//铁桶1100+本体200+临界点70
        changeSpeed(4700);
        this.damage = 100/pc.getFPS();
        
        int random = (int) (1 + Math.random() * 3);
        images.put("lostOrnament", new ImageIcon(SystemConstant.ZOMBIES_PATH + "Zombie/Zombie" + random + ".gif").getImage());

        this.activeImage = this.getImageByName("movingImage");
        this.status = SystemConstant.ZOMBIE_MOVING;
    }

    @Override
    public void act() {
        if (this.isZombieAteYourBrain())
            return;
        this.status = criticalPoint();
        if (this.status == SystemConstant.ZOMBIE_LOST_HEAD || this.status == SystemConstant.ZOMBIE_LOST_HEAD_EATING)
            if (current_health % 2 == 0)
                current_health--;
        if (isZombieDying()) {
            //僵尸正处在死亡阶段,接下来的操作都不执行,只是持续性掉血
            this.current_health -= 100 / this.parentController.getFPS();
            if (this.current_health <= -200)
                this.parentController.removeActor(this);
            return;
        }

        Image current_Image = this.activeImage;
        int current_Speed = this.speed;
        boolean isEating = false;

        Rectangle zRect = new Rectangle(col_posX, col_posY, col_width, col_height);
        for (Plant plant : parentController.getPlantsInGrid().values()) {
            if (plant.getRow() == this.movingRow) {
                Rectangle pRect = new Rectangle(plant.col_posX, plant.col_posY, plant.col_width - 20, plant.col_height);
                if (zRect.intersects(pRect)) {
                    switch (this.status) {
                        case SystemConstant.ZOMBIE_LOST_ORNAMENT:
                            this.activeImage = images.get("lostOrnamentEating");
                            this.status = SystemConstant.ZOMBIE_LOST_ORNAMENT_EATING;
                            break;
                        case SystemConstant.ZOMBIE_LOST_HEAD:
                            this.activeImage = images.get("lostHeadEatingImage");
                            this.status = SystemConstant.ZOMBIE_LOST_HEAD_EATING;
                            break;
                        default:
                            this.activeImage = images.get("eatingImage");
                            this.status = SystemConstant.ZOMBIE_EATING;
                            break;
                    }

                    changeSpeed(0);
                    plant.current_health -= this.damage;
                    isEating = true;
                    break;
                }
            }
        }
        if (status != SystemConstant.ZOMBIE_EATING && status != SystemConstant.ZOMBIE_LOST_HEAD_EATING && status != SystemConstant.ZOMBIE_LOST_ORNAMENT_EATING) {
            //不在吃且没有死,判断速度是否为0,为0说明刚刚在吃,现在要恢复原速度;为别的数值(可能为减速时数值),就采用上次的数值
            changeSpeed(current_Speed == 0 ? 4700 : current_Speed);
            //判断动画是否是eating动画,是的话改成moving动画;不是的话,仍然采取原动画
            this.activeImage = current_Image == images.get("eatingImage") ? this.getImageByName("movingImage") : current_Image;
        }
        this.posX -= this.step;
        this.col_posX -= this.step;

    }

    @Override
    public int criticalPoint() {
        //正常 1370 临界点: 270/70/0 掉铁桶/掉头/死亡
        if (this.current_health <= 1370 && this.current_health > 270) {
            if (this.status != SystemConstant.ZOMBIE_MOVING)
                this.activeImage = this.getImageByName("movingImage");
            return SystemConstant.ZOMBIE_MOVING;
        } else if (this.current_health <= 270 && this.current_health >= 70) {
            if (this.status != SystemConstant.ZOMBIE_LOST_ORNAMENT)
                this.activeImage = images.get("lostOrnament");
            return SystemConstant.ZOMBIE_LOST_ORNAMENT;
        } else if (this.current_health < 70 && this.current_health > 0) {
        	if(this.status != SystemConstant.ZOMBIE_LOST_HEAD) {
				this.activeImage = images.get("lostHeadImage");
				this.damage = 0;//头掉了攻击无伤害
				if(!isLostHead) {
					ZombieHead head = new ZombieHead(this.movingRow, this.col_posX, posY, this.parentController, this.getImageByName("headImage"));
					this.parentController.addActor(head);
					isLostHead = true;
				}
			}
            return SystemConstant.ZOMBIE_LOST_HEAD;
        } else if (this.current_health <= 0) {
            if (this.status != SystemConstant.ZOMBIE_DYING) {
                this.activeImage = this.getImageByName("dyingImage");
                this.col_width = 0;
                this.col_height = 0;
                changeSpeed(0);
            }
            return SystemConstant.ZOMBIE_DYING;
        } else
            return SystemConstant.ZOMBIE_ERROR;
    }

    @Override
    public Image getImageByName(String image_name) {
        if (image_name.equals("boomDieImage"))
            return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH + "Zombie/BoomDie.gif");
        else if (image_name.equals("dyingImage"))
            return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH + "Zombie/ZombieDie.gif");
        else if (image_name.equals("movingImage"))
            return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH + "BucketheadZombie/BucketheadZombie.gif");
        else if(image_name.equals("headImage"))
			return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH+"Zombie/ZombieHead.gif");
        return images.get(image_name);
    }

    @Override
    public Image getCardImage() {
        return cardImage;
    }

    @Override
    public Image getDescirptionImage() {
        return descirptionImage;
    }

}
