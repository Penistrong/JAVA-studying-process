package com.PVZ.model.others;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.api.common.Actor;
import com.PVZ.controller.adventure.action.AdventureController;

public class MsgLabel extends Actor{
	
	public Image activeImage;
	private static Image normalWave;
	private static Image finalWave;
	private long showedTime;
	private int showingTime;
	
	static {
		normalWave = new ImageIcon(SystemConstant.INTERFACE_PATH+"LargeWave.gif").getImage();
		finalWave = new ImageIcon(SystemConstant.INTERFACE_PATH+"FinalWave.gif").getImage();
	}
	
	public MsgLabel(AdventureController pc, String name) {
		if(name.equals("LargeWave")) {
			this.parentController = pc;
			this.activeImage = normalWave;
			this.posX = 150;
			this.posY = 230;
			this.width = 286;
			this.height = 34;
			this.showedTime = System.currentTimeMillis();
			this.showingTime = 4000;
		}else {
			this.parentController = pc;
			this.activeImage = finalWave;
			this.posX = 325;
			this.posY = 230;
			this.width = 252;
			this.height = 71;
			this.showedTime = System.currentTimeMillis();
			this.showingTime = 5000;
		}
	}
	
	@Override
	public void act() {
		long curTime = System.currentTimeMillis();
		if(curTime >= this.showedTime + this.showingTime)
			this.parentController.removeActor(this);
	}

	public Image getActiveImage() {
		return this.activeImage;
	}
}
