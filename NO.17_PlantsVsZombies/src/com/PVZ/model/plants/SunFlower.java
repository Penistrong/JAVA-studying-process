package com.PVZ.model.plants;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.api.common.Controller;
import com.PVZ.api.interfaces.plants.FunctionalPlants;
import com.PVZ.controller.adventure.action.AdventureController;
import com.PVZ.model.others.Sun;

import static com.PVZ.api.base.constant.SystemConstant.PLANTS_DESCRIPTION_PATH;

/**
 * 向日葵
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月8日上午11:04:49
 */
public class SunFlower extends Plant implements FunctionalPlants{

	private static Image descirptionImage;
	protected static Image cardImage;
	
	private int timeInterval;//阳光产生间隔
	private long lastProducedTime;//上次生产阳光的时间

	static {
		cardImage=new ImageIcon(SystemConstant.PLANTS_CARD_PATH+"sunflower_on.jpg").getImage();
		descirptionImage=new ImageIcon(PLANTS_DESCRIPTION_PATH+"SunFlowerAbout.png").getImage();

	}
	
	public SunFlower(){
		this.activeImage = this.getImageByName("normalImage");
		this.sunPointCost = 50;
		this.cd = 8;
	}


	public <T extends Controller>SunFlower(T pc, int row, int column) {

		super(pc, row, column);
		this.current_health = 300;
		this.timeInterval = 24000;//24s阳光产生间隔
		this.lastProducedTime = System.currentTimeMillis();//初始化上次生产阳光的时间
		this.activeImage = this.getImageByName("normalImage");
		this.width = 73;
		this.height = 74;
		this.posX += 4;
		this.posY += 16;
		this.col_posX = posX + 6;
		this.col_posY = posY + 4;
		this.col_width = 60;
		this.col_height = 70;
		this.sunPointCost = 50;
		this.cd = 8;
	}

	public SunFlower(AdventureController pc, int row, int column) {

		super(pc, row, column);
		this.current_health = 300;
		this.timeInterval = 24000;//24s阳光产生间隔
		this.lastProducedTime = System.currentTimeMillis();//初始化上次生产阳光的时间
		this.activeImage = this.getImageByName("normalImage");
		this.width = 73;
		this.height = 74;
		this.posX += 4;
		this.posY += 16;
		this.col_posX = posX + 6;
		this.col_posY = posY + 4;
		this.col_width = 60;
		this.col_height = 70;
		this.sunPointCost = 50;
		this.cd = 8;
	}

	
	/**
	 * 自定义血量和生产力
	 * @param max_health
	 * @param timeInterval
	 */
	public <T extends Controller>SunFlower(T pc, int row, int column, int max_health, int timeInterval) {
		
		super(pc, row, column);
		this.current_health = max_health;
		this.timeInterval = timeInterval;
		this.lastProducedTime = System.currentTimeMillis();//初始化上次生产阳光的时间
		this.activeImage = this.getImageByName("normalImage");
		this.width = 73;
		this.height = 74;
		this.posX += 4;
		this.posY += 16;
		this.col_posX = posX + 6;
		this.col_posY = posY + 4;
		this.col_width = 60;
		this.col_height = 70;
		this.sunPointCost = 50;
		this.cd = 8;
	}
	
	@Override
	public void functionalAct() {
		//向日葵:间隔时间产生阳光
		long currentTime = System.currentTimeMillis();
		if(currentTime - this.lastProducedTime >= timeInterval) {
			Sun sun = new Sun(this.parentController, false, this.posX+50, this.posY+50);
			this.parentController.addActor(sun);
			this.lastProducedTime = currentTime;
		}
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		if(this.current_health <= 0) {
			this.parentController.removeActor(this);
			return;
		}
		functionalAct();
		
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
		if(imageName.equals("normalImage"))
			return Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH + "SunFlower/SunFlower1.gif");
		return null;
	}
}
