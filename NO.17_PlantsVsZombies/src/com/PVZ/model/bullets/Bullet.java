package com.PVZ.model.bullets;

import java.awt.Image;

import com.PVZ.api.common.Actor;
import com.PVZ.api.common.Controller;

/**
 * 投掷物,子弹的父类
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月9日上午9:04:43
 */
public class Bullet extends Actor{

	protected int movingRow;
	protected Image activeImage;
	protected int damage;
	protected int speed;
	protected int step;//根据FPS计算每帧步长
	protected boolean isHit;//是否击中
	protected boolean isTorched;//是否被点燃
	protected long hitTime;
	
	public <T extends Controller> Bullet(int movingRow, int startX, T pc, int speed) {
		this.movingRow = movingRow;
		this.posX = startX;
		this.posY = 85 + (movingRow-1)*90 + 20;
		this.width = 55;
		this.height = 34;
		this.speed = speed;
		this.parentController = pc;
		this.step = speed;
		this.isTorched = false;
		//this.step = GRID_WIDTH/(speed/1000)/pc.getFPS();//格子宽除以速度(每格x秒)除以帧数
	}
	
	public boolean isTorched() {
		return isTorched;
	}

	public void setTorched(boolean isTorched) {
		this.isTorched = isTorched;
	}

	public int getMovingRow() {
		return movingRow;
	}

	public void setMovingRow(int movingRow) {
		this.movingRow = movingRow;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	

	public Image getActiveImage() {
		return activeImage;
	}

	public void setActiveImage(Image activeImage) {
		this.activeImage = activeImage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleImage() {
		// TODO Auto-generated method stub
		
	}
	
}
