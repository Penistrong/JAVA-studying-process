package com.PVZ.model.plants;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.api.common.Controller;
import com.PVZ.api.interfaces.plants.ConsumablePlants;
import com.PVZ.controller.adventure.action.AdventureController;
import com.PVZ.model.zombies.Zombie;

/**
 * 樱桃炸弹
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月8日上午11:13:35
 */
public class CherryBomb extends Plant implements ConsumablePlants{

	private static Image descirptionImage;
	private static Image cardImage;
	private static Map<String, Image> images;
	
	private long bomb_planted_time;
	private int damage;//爆炸伤害


	static {
		images = new HashMap<>();
		images.put("normalImage", new ImageIcon(SystemConstant.PLANTS_PATH+"CherryBomb/CherryBomb.gif").getImage());
		cardImage = new ImageIcon(SystemConstant.PLANTS_CARD_PATH+"card_cherrybomb.png").getImage();
		descirptionImage=new ImageIcon(SystemConstant.PLANTS_DESCRIPTION_PATH+"CherryBoomAbout.png").getImage();

	}
	
	public CherryBomb(){
		this.cd = 30;
		this.sunPointCost = 150;
		this.activeImage=images.get("normalImage");
	}
	
	public	<T extends Controller> CherryBomb(T pc, int row, int column) {
		super(pc,row,column);
		this.bomb_planted_time = System.currentTimeMillis();
		this.current_health = 300;
		this.damage = 1800;
		this.activeImage = images.get("normalImage");
		this.width = 90;
		this.col_width = width;
		this.height = 90;
		this.col_height = height;
		this.posX += (80 - width)/2;
		this.posY += (90 - height)/2;
		this.sunPointCost = 150;
		this.cd = 30;
	}
	
	public CherryBomb(AdventureController pc, int row, int column) {
		super(pc,row,column);
		this.bomb_planted_time = System.currentTimeMillis();
		this.current_health = 300;
		this.damage = 1800;
		this.activeImage = images.get("normalImage");
		this.width = 112;
		this.height = 81;
		this.posX -= 16;
		this.posY += 9;
		this.sunPointCost = 150;
		this.cd = 30;
	}
	
	/**
	 * 手动设置爆炸伤害
	 * @param damage
	 */
	public CherryBomb(Controller pc, int row, int column,int damage) {
		super(pc,row,column);
		this.current_health = 300;
		this.damage = damage;
		this.activeImage = images.get("normalImage");
		this.width = 112;
		this.height = 81;
		this.posX -= 16;
		this.posY += 9;
		this.sunPointCost = 150;
		this.cd = 30;
	}


	
	@Override
	public void act() {
		if(this.current_health > 0) {
			consumableAct();
		}else{
			if(this.current_health <= -120) {
				//爆炸动画播放时间0.6s,以生命值标定
				this.parentController.removeActor(this);
				return;
			}else{
				this.current_health -= 100/parentController.getFPS();//每帧掉血,持续1.2s
			}
		}
	}

	@Override
	public void consumableAct() {
		long curTime = System.currentTimeMillis();
		//种下后0.6s内爆炸
		if(curTime - bomb_planted_time >= 600) {
			//this.activeImage = images.get("boomImage");
			this.activeImage = Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH+"CherryBomb/Boom.gif");
			//设置爆炸动画精确坐标和宽高
			this.posX = this.posX + 16 - 80 + 13;
			this.posY = this.posY - 9 - 90 + 37;
			this.width = 213;
			this.height = 196;
			this.current_health = 0;
			//九格内全部造成巨大伤害

			Rectangle cRect = new Rectangle(this.posX - 40, this.posY - 20, 160, 180);
			for(List<Zombie> zombies:parentController.getZombiesInLines().values()) {
				for(Zombie zombie:zombies) {
					Rectangle zRect = new Rectangle(zombie.posX, zombie.posY, zombie.width, zombie.height);
					if(zRect.intersects(cRect)) {
						zombie.setActiveImage("boomDieImage");
						zombie.current_health = 0;
						zombie.changeSpeed(0);
						zombie.status = SystemConstant.ZOMBIE_DYING;
						zombie.col_width = 0;
						zombie.col_height = 0;
					}
				}
			}
		}
	}
	
	@Override
	public Image getCardImage() {
		return cardImage;
	}

	@Override
	public  Image getDescirptionImage() {
		return descirptionImage;
	}

	@Override
	public Image getImageByName(String imageName) {
		// TODO Auto-generated method stub
		return null;
	}
}
