package com.PVZ.model.plants;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.PVZ.api.common.Controller;
import com.PVZ.api.interfaces.plants.FunctionalPlants;
import com.PVZ.controller.adventure.action.AdventureController;

import static com.PVZ.api.base.constant.SystemConstant.*;

/**
 * 坚果(小)
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月8日上午11:10:29
 */
public class WallNut extends Plant implements FunctionalPlants{

	private static Image descirptionImage;
	private static Image cardImage;
	private static Map<String, Image> images;
	
	//坚果有三种形态,对应:满血-损坏点1:三分之一毁损-损坏点2:三分之二毁损
	private int cracked1_health;
	private int cracked2_health;

	static {
		images = new HashMap<>();
		cardImage=new ImageIcon(PLANTS_CARD_PATH+"card_wallnut.png").getImage();
		images.put("normalImage", new ImageIcon(PLANTS_PATH + "WallNut/WallNut.gif").getImage());
		images.put("cracked1_gif", new ImageIcon(PLANTS_PATH + "WallNut/Wallnut_cracked1.gif").getImage());
		images.put("cracked2_gif", new ImageIcon(PLANTS_PATH + "WallNut/Wallnut_cracked2.gif").getImage());
		descirptionImage=new ImageIcon(PLANTS_DESCRIPTION_PATH+"WallNutAbout.png").getImage();

	}
	
	public WallNut(){
		this.sunPointCost=50;
		this.activeImage = images.get("normalImage");
		this.cd=30;
	}
	
	/**
	 * 普通构造函数,默认坚果最大血量为4000
	 */
	public <T extends Controller> WallNut(T pc, int row, int column) {
		super(pc, row, column);
		this.current_health = 4000;//耐久4000
		this.cracked1_health = 2 * this.current_health / 3;
		this.cracked2_health = this.current_health / 3;
		
		this.activeImage = this.images.get("normalImage");
		this.width = 65;
		this.height = 73;
		this.posX += 7;
		this.posY += 17;
		this.col_posX = posX;
		this.col_posY = posY;
		this.col_width = width;
		this.col_height = height;
		this.sunPointCost = 50;
		this.cd=30;
	}
	
	public WallNut(AdventureController pc, int row, int column) {
		super(pc, row, column);
		this.current_health = 4000;//耐久4000
		this.cracked1_health = 2 * this.current_health / 3;
		this.cracked2_health = this.current_health / 3;
		
		this.activeImage = this.images.get("normalImage");
		this.width = 65;
		this.height = 73;
		this.posX += 7;
		this.posY += 17;
		this.col_posX = posX;
		this.col_posY = posY;
		this.col_width = width;
		this.col_height = height;
		this.sunPointCost = 50;
		this.cd=30;
	}
	
	/**
	 * 重载构造函数,手动设置坚果最大血量,用以某些小游戏或特性
	 * @param max_health
	 */
	public WallNut(Controller pc, int row, int column, int max_health) {
		super(pc, row, column);
		this.current_health = max_health>0?max_health:4000;
		this.cracked1_health = 2 * this.current_health / 3;
		this.cracked2_health = this.current_health / 3;
		
		this.activeImage = this.images.get("normalImage");
		this.width = 60;
		this.col_width = width;
		this.height = 68;
		this.col_height = height;
		this.posX += (80-this.width)/2;
		this.posY += (90-this.height)/2;
		this.sunPointCost = 50;
		this.cd=20;
	}
	
	/**
	 * 判断血量并改变形态
	 * @Title:changeStatus
	 * @Description:TODO void
	 * @throws
	 */
	private void changeStatus() {
		if(current_health <= cracked1_health && current_health > cracked2_health) {
			//损失血量:33%~67%
			this.activeImage = images.get("cracked1_gif");
		}else if(current_health <= cracked2_health && current_health > 0) {
			//损失血量:67%~100%
			this.activeImage = images.get("cracked2_gif");
		}else if(current_health <= 0){
			//死亡:逻辑-消除本实例-待定
			this.parentController.removeActor(this);
		}
	}
	@Override
	public void act() {
		changeStatus();
		functionalAct();
	}

	@Override
	public void functionalAct() {
		//待定实现:坚果随时间缓慢回血
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
