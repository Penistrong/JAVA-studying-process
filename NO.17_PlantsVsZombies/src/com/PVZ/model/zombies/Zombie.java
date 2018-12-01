package com.PVZ.model.zombies;

import com.PVZ.api.common.Actor;
import com.PVZ.api.common.Controller;
import com.PVZ.controller.adventure.action.AdventureController;

import com.PVZ.api.base.constant.SystemConstant;

import java.awt.*;

/**
 * 僵尸种的父类
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月8日上午10:51:04
 */
public abstract class Zombie extends Actor{
	public int status;//保存当前状态
	public int current_health;
	public int speed;
	public int step;
	public int damage;//每秒伤害
	public int col_posX;//实际碰撞箱左上角X
	public int col_posY;//实际碰撞箱左上角Y
	public int col_height;//碰撞箱高度
	public int col_width;//碰撞箱宽度
	protected int movingRow;//僵尸所在线路
	protected boolean isMoving;
	protected boolean isLostHead;//是否掉头(是否达到临界点生命)
	protected Controller parentController;
	protected Image activeImage;//当前活跃的图片

	
	public Zombie() {}
	
	public <T extends Controller>Zombie(int movingRow, T pc) {
		this.status = SystemConstant.ZOMBIE_MOVING;
		this.movingRow = movingRow;
		this.parentController = pc;
		int random_posX = (int)(900 +Math.random()*200);
		this.posX = random_posX;
		this.posY = (movingRow-1) * 90 + 85 - 54;
		this.width = 166;
		this.height = 144;
		this.isLostHead = false;
	}
	
	public abstract int criticalPoint();
	
	public boolean isZombieDying() {
		return (this.status == SystemConstant.ZOMBIE_DYING);
	}
	
	/**
	 * @Title:changeSpeed
	 * @Description:改变速度值,并计算每帧步长
	 * @param speed 
	 * @return void
	 * @throws
	 */
	public void changeSpeed(int speed) {
		this.speed = speed;
		this.step = speed != 0?1:0;
	}
	
    public int getPosX() {
        return posX;
    }
    
    public void setPosX(int posX) {
    	this.posX = posX;
    }


    public int getCurrent_health() {
        return current_health;
    }
    
    public void setCurrent_health(int current_health) {
    	this.current_health = current_health;
    }

    public int getMovingRow() {
        return movingRow;
    }
    
    public void setMovingRow(int movingRow) {
    	this.movingRow = movingRow;
    }

    public int getSpeed() {
        return speed;
    }

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public Image getActiveImage() {
		return activeImage;
	}
	
	public void setActiveImage(String image_name) {
		this.activeImage = this.getImageByName(image_name);
	}
	
	/**
	 * @Title:getImageByName
	 * @Description:供外部使用
	 * @param image_name
	 * @return Image
	 * @throws
	 */
	public abstract Image getImageByName(String image_name);

	public abstract Image getCardImage();

	public  abstract Image getDescirptionImage() ;
	
	public boolean isZombieAteYourBrain() {
		if(this.posX < -50){
			((AdventureController)parentController).setZombieAteYourBrain(true);
			return true;
		}else
			return false;
		
	}
}
