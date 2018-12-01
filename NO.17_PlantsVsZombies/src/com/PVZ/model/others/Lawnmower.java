package com.PVZ.model.others;

import javax.swing.ImageIcon;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.api.common.Actor;
import com.PVZ.api.common.Controller;
import com.PVZ.model.zombies.*;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 铲车(不然吃掉你的脑子)
 * @author tyz
 * @project NO.17_PlantsVsZombies	
 * @date 2018/11/25 0:48
 */
public class Lawnmower extends Actor{



   //GreenfootSound sound = new GreenfootSound("lamborghini.wav");
    private int speed;
	public int status;//保存当前状态
	public int step;
	public int col_posX;//实际碰撞箱左上角X
	public int col_posY;//实际碰撞箱左上角Y
	public int col_height;//碰撞箱高度
	public int col_width;//碰撞箱宽度
	protected int row;//所在线路
	protected Controller parentController;
	protected Image activeImage = new ImageIcon(SystemConstant.INTERFACE_PATH+"LawnCleaner.png").getImage();//当前活跃的图片 

	public <T extends Controller> Lawnmower(int row, T pc) {
		this.status = SystemConstant.ZOMBIE_MOVING;
		this.row = row;
		this.parentController = pc;
		this.posX = 60;
		this.posY = (row-1) * 90 + 85 + 23;
		this.width = 70;
		this.height = 57;
		changeSpeed(4700);//4.7s一格

		this.status = 0;//0表示小车静止
	}
	

/**
 * @description: eliminate every zombie in row
 * @author: tyz
 * @param: void
 * @return: void
 * @date: 2018/11/29 23:59
 * */
   public void act(){
		Rectangle cRect = new Rectangle(posX, posY, width, height);
	    CopyOnWriteArrayList<Zombie> zombiesInLine = parentController.getZombiesInLines().get(this.row);

	   if(!zombiesInLine.isEmpty()) {
		   for (Zombie zombie : zombiesInLine) {
			   if (zombie.getMovingRow() == this.row) {
				   Rectangle zRect = new Rectangle(zombie.col_posX, zombie.col_posY, zombie.col_width, zombie.col_height);
				   if (cRect.intersects(zRect)) {
					   this.status = 1;//可移动状态
					   zombie.current_health = 0;
					   zombie.setActiveImage("dyingImage");
					   zombie.status = SystemConstant.ZOMBIE_DYING;
					   zombie.changeSpeed(0);
					   zombie.col_width = 0;
					   zombie.col_height = 0;
				   }
			   }
		   }
	   }

	   if(this.col_posX>900){//小车到达地图右侧移除
		   parentController.removeActor(this);
		   return;
	   }
	   
	   //可移动状态才改变坐标
	   if(status == 1)
		   this.posX += this.step;
   }


   public void changeSpeed(int speed) {
		this.speed = speed;
		this.step = speed != 0?15:0;
	}
   
   public Image getActiveImage() {
	   return this.activeImage;
   }
}
