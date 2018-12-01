package com.PVZ.model.others;

import com.PVZ.api.common.Actor;
import com.PVZ.api.common.Controller;
import com.PVZ.controller.Main;
import com.PVZ.controller.adventure.action.AdventureController;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.PVZ.api.base.constant.SystemConstant;
/**
 * 太阳粒
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月8日上午11:17:18
 */
public class Sun extends Actor  implements MouseListener{
	private Image activeImage;
	private int count;	//计时器，到达一定时间太阳消失
	private int value;	//阳光点数
	private boolean enableMoving;//是否可移动,区分场景产生的太阳和生产者产生的太阳
	private boolean isClickable;//是否可点击
	private boolean isLabelAdded;//label是否已被add到父组件里
	private int step;//移动的阳光的步数
	public int step_x;
	public int step_y;

	private JLabel sunLabel;

	public <T extends Controller>Sun(T pc,boolean enableMoving, int posX, int posY) {
		this.parentController = pc;
		this.enableMoving = enableMoving;
		this.isClickable = true;
		this.isLabelAdded = false;
		this.step = enableMoving == false ? 0 : 3;//场景产生的太阳每帧移动 2 pixel
		this.step_x = 0;
		this.step_y = 0;
		this.posX = posX;
		this.posY = posY;
		this.width = 48;
		this.height = 48;
		this.value = 25;
		this.activeImage = Toolkit.getDefaultToolkit().createImage(SystemConstant.INTERFACE_PATH+"Sun.gif");
		
		this.sunLabel=new JLabel();
		this.sunLabel.setBounds(posX, posY, width, height);
		this.sunLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isClickable) {
					super.mouseClicked(e);
					System.out.println("[LOG]Sun is clicked @["+posX+", "+posY+"]");
					step_x = (posX - 30)/parentController.getFPS();
					step_y = (posY - 10)/parentController.getFPS();
					isClickable = false;
					sunLabel.setVisible(false);
				}
				Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));


			}

			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		//this.sunLabel.setBounds(posX, posY, activeImage.getWidth(null), activeImage.getHeight(null));
		//this.sunLabel.setVisible(false);
	}
	
	public boolean isLabelAdded() {
		return isLabelAdded;
	}

	public void setLabelAdded(boolean isLabelAdded) {
		this.isLabelAdded = isLabelAdded;
	}
	
	public void resetLabelLocation() {
		this.sunLabel.setBounds(posX, posY, width, height);
	}

	@Override
	public void act() {
		
		if(!isClickable) {
			this.posX -= step_x;
			this.posY -= step_y;
			if(posX <= 30 || posY<= 10) {
				AdventureController ac = (AdventureController)parentController;
				int sun_points = ac.getSun_points() + this.value;
				ac.setSun_points(sun_points);
				parentController.removeActor(this);
				return;
			}
			return;
		}
		
		if(enableMoving) {
			if(this.posX < 0 || this.posX >900)
				parentController.removeActor(this);
			else if(this.posY > 600)
				parentController.removeActor(this);
			int random = (int)(Math.random()*3);//0或1或2
			switch(random) {
			case 0:
				//向下移动
				this.posY += step;
				break;
			case 1:
				//向左移动
				//this.posX -= step - 1;
				break;
			case 2:
				//向右移动
				//this.posX += step;
				break;
			}
		}
			
	}

	public JLabel getSunLabel() {
		return sunLabel;
	}

	public Image getActiveImage() {
		return this.activeImage;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("我被点击了");

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	
	public boolean isClickable() {
		return isClickable;
	}
	
}
