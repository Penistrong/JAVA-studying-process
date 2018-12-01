package com.PVZ.api.common;

/**
 * 实体的超类
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月9日上午9:01:34
 */
public abstract class Actor {
	public int posX;
	public int posY;
	public Controller parentController;
	public int width;
	public int height;


	
	public void toggleImage() {
		//可被重写或重载
	}
	
	public abstract void act();



	
}
