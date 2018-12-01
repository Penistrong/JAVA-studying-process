package com.PVZ.model.zombies;

import com.PVZ.api.common.Controller;
import com.PVZ.api.interfaces.zombies.WalkingDead;
import com.PVZ.controller.adventure.action.AdventureController;
import com.PVZ.model.plants.Plant;

import javax.swing.*;

import static com.PVZ.api.base.constant.SystemConstant.ZOMBIES_PATH;
import static com.PVZ.api.base.constant.SystemConstant.ZOMBIE_CARD_PATH;
import static com.PVZ.api.base.constant.SystemConstant.ZOMBIE_DESCRIPTION_PATH;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import com.PVZ.api.base.constant.SystemConstant;

/**
 * 普通僵尸
 *
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies
 * @date 2018年11月8日上午11:25:19
 */
public class NormalZombie extends Zombie implements WalkingDead {

	private static Map<String, Image> images;//键值对保存各种图片
	protected static Image cardImage;
	protected static Image descirptionImage;

	static {
		cardImage=new ImageIcon(ZOMBIE_CARD_PATH+"NormolZombiesCard.png").getImage();

		descirptionImage=new ImageIcon(ZOMBIE_DESCRIPTION_PATH+"ZombieAbout.png").getImage();
		images = new HashMap<>();
		images.put("lostHeadImage", new ImageIcon(ZOMBIES_PATH+"Zombie/ZombieLostHead.gif").getImage());
		images.put("lostHeadEatingImage", new ImageIcon(ZOMBIES_PATH+"Zombie/ZombieLostHeadAttack.gif").getImage());
		images.put("eatingImage", new ImageIcon(ZOMBIES_PATH+"Zombie/ZombieAttack.gif").getImage());
	}


	public NormalZombie(){
		this.activeImage = new ImageIcon(ZOMBIES_PATH+"Zombie/Zombie"+1+".gif").getImage();
	}

    public <T extends Controller> NormalZombie(int movingRow, T pc) {
        super(movingRow, pc);
        this.col_posX = posX + 70;
        this.col_posY = posY + 50;
        this.col_width = 40;
        this.col_height = 80;
        this.current_health = 270;//200 + 70临界点
        changeSpeed(4700);//4.7s一格
        this.damage = 100/pc.getFPS();//每秒100点伤害除以每秒帧数,每帧伤害
        
        this.activeImage = this.getImageByName("movingImage");
        this.status = SystemConstant.ZOMBIE_MOVING;
    }
    
    public NormalZombie(int movingRow, AdventureController pc) {
        super(movingRow, pc);
        this.col_posX = posX + 70;
        this.col_posY = posY + 50;
        this.col_width = 40;
        this.col_height = 80;
        this.current_health = 270;//200 + 70临界点
        changeSpeed(4700);//4.7s一格
        this.damage = 100/pc.getFPS();//每秒100点伤害除以每秒帧数,每帧伤害
        
        this.activeImage = this.getImageByName("movingImage");
        this.status = SystemConstant.ZOMBIE_MOVING;
    }

    @Override
    public void act() {
    	if(this.isZombieAteYourBrain())
			return;
    	this.status = criticalPoint();
    	if(this.status == SystemConstant.ZOMBIE_LOST_HEAD || this.status == SystemConstant.ZOMBIE_LOST_HEAD_EATING)
    		if(current_health % 2 == 0)
    			current_health --;
    	if(isZombieDying()) {
    		//僵尸正处在死亡阶段,接下来的操作都不执行,只是持续性掉血
    		this.current_health -= 100/this.parentController.getFPS();//每秒掉100滴血,判定值为-200,也就是0.200s播放死亡动画时间
    		if(this.current_health <= -200)
    			this.parentController.removeActor(this);
    		return;
    	}
    	
    	Image current_Image = this.activeImage;
    	int current_Speed = this.speed;
    	
    	Rectangle zRect = new Rectangle(col_posX, col_posY, col_width, col_height);
        for(Plant plant:parentController.getPlantsInGrid().values()) {
        	if(plant.getRow() == this.movingRow) {
        		Rectangle pRect = new Rectangle(plant.col_posX, plant.col_posY, plant.col_width - 20, plant.col_height);
        		if(zRect.intersects(pRect)) {
        			switch(this.status) {
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
        			return;
        		}
        	}
        }
        if(status!=SystemConstant.ZOMBIE_EATING && status != SystemConstant.ZOMBIE_LOST_HEAD_EATING) {
        	//不在吃且没有死,判断速度是否为0,为0说明刚刚在吃,现在要恢复原速度;为别的数值(可能为减速时数值),就采用上次的数值
        	changeSpeed(current_Speed == 0?4700:current_Speed);
        	//判断动画是否是eating动画,是的话改成moving动画;不是的话,仍然采取原动画
        	this.activeImage = current_Image == images.get("eatingImage")?this.getImageByName("movingImage"):current_Image;
        }
        
        this.posX -= this.step;
        this.col_posX -= this.step;
    }

	@Override
	public int criticalPoint() {
		//正常270 临界点 70/0,掉头/死亡
		if(this.current_health <= 270 && this.current_health >70) {
			if(this.status != SystemConstant.ZOMBIE_MOVING)
				this.activeImage = this.getImageByName("movingImage");
			return SystemConstant.ZOMBIE_MOVING;
		}else if(this.current_health<=70 && this.current_health>0) {
			if(this.status != SystemConstant.ZOMBIE_LOST_HEAD) {
				this.activeImage = images.get("lostHeadImage");
				this.damage = 0;//头掉了无伤害
				if(!isLostHead) {
					ZombieHead head = new ZombieHead(this.movingRow, this.col_posX, posY, this.parentController, this.getImageByName("headImage"));
					this.parentController.addActor(head);
					isLostHead = true;
				}
			}
			return SystemConstant.ZOMBIE_LOST_HEAD;
		}else if(this.current_health <= 0){
			if(this.status != SystemConstant.ZOMBIE_DYING) {
				this.activeImage = this.getImageByName("dyingImage");
				this.col_width = 0;
				this.col_height = 0;
				changeSpeed(0);
			}
			return SystemConstant.ZOMBIE_DYING;
		}else
			return SystemConstant.ZOMBIE_ERROR;
	}

	@Override
	public Image getImageByName(String image_name) {
		if(image_name.equals("boomDieImage"))
			return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH+"Zombie/BoomDie.gif");
		else if(image_name.equals("dyingImage"))
			return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH+"Zombie/ZombieDie.gif");
		else if(image_name.equals("movingImage")) {
			int random = (int)(1 + Math.random()*3);
			return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH+"Zombie/Zombie"+random+".gif");
		}else if(image_name.equals("headImage"))
			return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH+"Zombie/ZombieHead.gif");
		return images.get(image_name);
	}

	@Override
	public Image getCardImage() {
		return cardImage;
	}

	@Override
	public  Image getDescirptionImage() {
		return descirptionImage;
	}

}
