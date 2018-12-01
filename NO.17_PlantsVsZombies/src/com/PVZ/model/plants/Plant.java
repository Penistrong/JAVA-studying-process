package com.PVZ.model.plants;

import java.awt.Image;

import com.PVZ.api.common.Actor;
import com.PVZ.api.common.Controller;

/**
 * 植物种的父类
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月8日上午10:50:32
 */
public abstract class Plant extends Actor {

	public int current_health;
	public int col_posX;
	public int col_posY;
	public int col_width;
	public int col_height;
	protected int cd;
	protected int row;//所在行数
	protected int column;//所在列
	protected Image activeImage;
	protected Controller parentController;
	protected int sunPointCost;
	//private static Image descirptionImage;

	public <T extends Controller> Plant(T pc, int row, int column) {
		this.parentController = pc;
		this.row = row;
		this.posY = 85 + (row-1)*90;
		this.column = column;
		this.posX = 125 + (column-1)*80;
	}


	public Plant(){

	}
	
	public abstract Image getImageByName(String imageName);
	
	public int getCd() {
		return cd;
	}

	public int getCurrent_health() {
		return current_health;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Controller getParentController() {
		return parentController;
	}

	public void setParentController(Controller parentController) {
		this.parentController = parentController;
	}

	public void setCd(int cd) {
		this.cd = cd;
	}

	public int getRow() {
		return row;
	}

	public void setActiveImage(Image activeImage) {
		this.activeImage = activeImage;
	}

	public void setCurrent_health(int current_health) {
		this.current_health = current_health;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Image getActiveImage() {
		return activeImage;
	}

	public int getSunPointCost() {
		return sunPointCost;
	}
	
	public abstract Image getCardImage();

	public  abstract Image getDescirptionImage() ;
}
