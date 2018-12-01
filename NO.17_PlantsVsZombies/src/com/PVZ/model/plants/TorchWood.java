package com.PVZ.model.plants;

import static com.PVZ.api.base.constant.SystemConstant.PLANTS_DESCRIPTION_PATH;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.api.common.Controller;
import com.PVZ.api.interfaces.plants.FunctionalPlants;
import com.PVZ.controller.adventure.action.AdventureController;
import com.PVZ.model.bullets.Bullet;

public class TorchWood extends Plant implements FunctionalPlants{
	
	private static Image descirptionImage;
	protected static Image cardImage;
	
	static {
		cardImage=new ImageIcon(SystemConstant.PLANTS_CARD_PATH+"TorchWoodCard.png").getImage();
		//descirptionImage=new ImageIcon(PLANTS_DESCRIPTION_PATH+"TorchWoodDescription.png").getImage();
		//descirptionImage=new ImageIcon(PLANTS_DESCRIPTION_PATH+"TorchWoodAbout.png").getImage();
		descirptionImage=new ImageIcon(PLANTS_DESCRIPTION_PATH+"TorchWoodAbout.png").getImage();
	}
	
	public TorchWood() {
		this.activeImage = this.getImageByName("normalImage");
		this.sunPointCost = 175;
		this.cd = 8;
	}
	
	public <T extends Controller> TorchWood(T pc, int row, int column){
		super(pc, row, column);
		this.current_health = 300;
		this.posX += 3;
		this.posY += 3;
		this.width = 73;
		this.height = 87;
		this.col_posX = posX;
		this.col_posY = posY;
		this.col_width = width;
		this.col_height = height;
		
		this.activeImage = this.getImageByName("normalImage");
		this.sunPointCost = 175;
		this.cd = 8;
	}
	
	public TorchWood(AdventureController pc, int row, int column) {
		super(pc, row, column);
		this.current_health = 300;
		this.posX += 3;
		this.posY += 3;
		this.width = 73;
		this.height = 87;
		this.col_posX = posX;
		this.col_posY = posY;
		this.col_width = width;
		this.col_height = height;
		
		this.activeImage = this.getImageByName("normalImage");
		this.sunPointCost = 175;
		this.cd = 8;
	}
	
	@Override
	public void act() {
		if(this.current_health <= 0) {
			this.parentController.removeActor(this);
			return;
		}
		functionalAct();
	}
	
	@Override
	public void functionalAct() {
		Rectangle tRect = new Rectangle(posX, posY, width, height);
		CopyOnWriteArrayList<Bullet> bulletsInLine = this.parentController.getBulletsInLines().get(this.row);
		for(Bullet bullet:bulletsInLine) {
			if(!bullet.isTorched()) {
				if(bullet.posX >= this.posX && bullet.posX <= this.posX + this.width) {
					bullet.setDamage(40);
					bullet.setTorched(true);
					bullet.setActiveImage(this.getImageByName("torchedPea"));
				}
			}
		}
		
	}
	
	@Override
	public Image getImageByName(String imageName) {
		if(imageName.equals("normalImage"))
			return Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH+"TorchWood/TorchWood.gif");
		else if(imageName.equals("torchedPea"))
			return Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH+"PB10.gif");
		return null;
	}

	@Override
	public Image getCardImage() {
		return cardImage;
	}

	@Override
	public Image getDescirptionImage() {
		// TODO Auto-generated method stub
		return descirptionImage;
	}


}
