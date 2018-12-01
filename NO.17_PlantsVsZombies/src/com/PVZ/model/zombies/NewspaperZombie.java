package com.PVZ.model.zombies;

import static com.PVZ.api.base.constant.SystemConstant.ZOMBIES_PATH;
import static com.PVZ.api.base.constant.SystemConstant.ZOMBIE_DESCRIPTION_PATH;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.api.common.Controller;
import com.PVZ.api.interfaces.zombies.WalkingDead;
import com.PVZ.controller.adventure.action.AdventureController;
import com.PVZ.model.plants.Plant;

/**
 * 读报僵尸(报纸破碎红眼冲锋)
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月8日上午11:23:28
 */
public class NewspaperZombie extends Zombie implements WalkingDead{

	protected static Image cardImage;
	protected static Image descirptionImage;
	private static Map<String, Image> images;//键值对保存各种图片
	private boolean isLosingOrnament;
	private long losingO_Time;
	
	static {
		cardImage=new ImageIcon(SystemConstant.ZOMBIE_CARD_PATH+"NewspaperZombieCard.png").getImage();
		descirptionImage=new ImageIcon(ZOMBIE_DESCRIPTION_PATH+"NewspaperZombieAbout.png").getImage();
		images = new HashMap<>();
		images.put("eatingImage", new ImageIcon(ZOMBIES_PATH+"NewspaperZombie/HeadAttack1.gif").getImage());
		images.put("lostHeadImage", new ImageIcon(ZOMBIES_PATH+"NewspaperZombie/LostHeadWalk0.gif").getImage());
		images.put("lostHeadEatingImage", new ImageIcon(ZOMBIES_PATH+"NewspaperZombie/LostHeadAttack0.gif").getImage());
		images.put("losingOrnament", new ImageIcon(ZOMBIES_PATH+"NewspaperZombie/LostNewspaper.gif").getImage());
		images.put("lostOrnamentEating", new ImageIcon(ZOMBIES_PATH+"NewspaperZombie/HeadAttack0.gif").getImage());
	}

	public NewspaperZombie(){
		this.activeImage=this.getImageByName("movingImage");
	}

	public <T extends Controller>NewspaperZombie(int movingRow, T pc){
		super(movingRow, pc);
	}

	public NewspaperZombie(int movingRow, AdventureController pc) {
		super(movingRow, pc);
		this.width = 216;
		this.height = 164;
		this.col_posX = posX+10;
		this.col_posY = posY+30; 
		this.posX -= 48;
		this.posY -= 14;
		this.col_width = 40;
		this.col_height = 60;
		this.current_health = 420;
		changeSpeed(4700);
		this.damage = 100/parentController.getFPS();
		this.activeImage = this.getImageByName("movingImage");
		this.status = SystemConstant.ZOMBIE_MOVING;
		this.losingO_Time = 0;
	}
	
	@Override
	public void act() {
		if(this.isZombieAteYourBrain())
			return;
		if(!isLosingOrnament && this.losingO_Time == 0)
			if(this.current_health <= 270 && this.current_health >70) {
				this.isLosingOrnament = true;
				this.activeImage = images.get("losingOrnament");
				changeSpeed(0);
				this.losingO_Time = System.currentTimeMillis();
				return;
			}
		if(isLosingOrnament)
			if(System.currentTimeMillis() - this.losingO_Time >= 1000) {
				this.isLosingOrnament = false;
				changeSpeed(4700);
				this.step = 2;
			}else
				return;
		this.status = criticalPoint();
		if(this.status == SystemConstant.ZOMBIE_LOST_HEAD || this.status == SystemConstant.ZOMBIE_LOST_HEAD_EATING)
			if(current_health % 2 == 0)
    			current_health --;
		if(isZombieDying()) {
    		//僵尸正处在死亡阶段,接下来的操作都不执行,只是持续性掉血
    		this.current_health -= 100/this.parentController.getFPS();
    		if(this.current_health <= -200)
    			this.parentController.removeActor(this);
    		return;
    	}
		Image current_Image = this.activeImage;
		int current_Step = this.step;
		
		Rectangle zRect = new Rectangle(col_posX, col_posY, col_width, col_height);
        for(Plant plant:parentController.getPlantsInGrid().values()) {
        	if(plant.getRow() == this.movingRow) {
        		Rectangle pRect = new Rectangle(plant.col_posX, plant.col_posY, plant.col_width - 20, plant.col_height);
        		if(zRect.intersects(pRect)) {
        			switch(this.status) {
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
        			break;
        		}
        	}
        }
        if(status != SystemConstant.ZOMBIE_EATING && status != SystemConstant.ZOMBIE_LOST_HEAD_EATING && status != SystemConstant.ZOMBIE_LOST_ORNAMENT_EATING) {
        	//不在吃且没有死,判断速度是否为0,为0说明刚刚在吃,现在要恢复原速度;为别的数值(可能为减速时数值),就采用上次的数值
        	if(losingO_Time != 0)
        		this.step = 2;
        	else
        		changeSpeed(4700);
        	//判断动画是否是eating动画,是的话改成moving动画;不是的话,仍然采取原动画
        	this.activeImage = current_Image == images.get("eatingImage")?this.getImageByName("movingImage"):current_Image;
        }
        this.posX -= this.step;
        this.col_posX -= this.step;
	}

	@Override
	public int criticalPoint() {
		// TODO Auto-generated method stub
		if(this.current_health <= 420 && this.current_health > 270) {
			if(this.status != SystemConstant.ZOMBIE_MOVING)
				this.activeImage = this.getImageByName("movingImage");
			return SystemConstant.ZOMBIE_MOVING;
		}else if(this.current_health<=270 && this.current_health>70) {
			if(this.status != SystemConstant.ZOMBIE_LOST_ORNAMENT)
				this.activeImage = this.getImageByName("lostOrnament");
			return SystemConstant.ZOMBIE_LOST_ORNAMENT;
		}else if(this.current_health<=70 && this.current_health>0) {
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
		if(image_name.equals("boomDieImage")) {
			return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH+"Zombie/BoomDie.gif");
		}else if(image_name.equals("dyingImage")) {
			return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH+"NewspaperZombie/Die.gif");
		}else if(image_name.equals("headImage")) {
			return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH+"NewspaperZombie/Head.gif");
		}else if(image_name.equals("movingImage")) {
			return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH+"NewspaperZombie/HeadWalk1.gif");
		}else if(image_name.equals("lostOrnament")) {
			return Toolkit.getDefaultToolkit().createImage(ZOMBIES_PATH+"NewspaperZombie/HeadWalk0.gif");
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
