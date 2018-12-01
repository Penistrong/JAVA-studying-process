package com.PVZ.model.plants;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.api.common.Controller;
import com.PVZ.api.interfaces.plants.DurablePlants;
import com.PVZ.controller.adventure.action.AdventureController;
import com.PVZ.model.zombies.Zombie;

public class Chomper extends Plant implements DurablePlants{
	protected static Image cardImage;
	private static Image descirptionImage;

	private boolean isDigesting;
	private boolean isAttacking;
	private Zombie attackedZombie;
	private int digestingTime;
	private long ateTime;
	
	static {
		cardImage = new ImageIcon(SystemConstant.PLANTS_CARD_PATH+"ChomperCard.png").getImage();
		descirptionImage=new ImageIcon(SystemConstant.PLANTS_DESCRIPTION_PATH+"ChomperAbout.png").getImage();
	}
	
	public Chomper() {
		this.activeImage = this.getImageByName("normalImage");
		this.sunPointCost = 300;
		this.cd = 8;
	}
	
	public <T extends Controller> Chomper(T pc, int row, int column){
		super(pc, row, column);
		this.current_health = 300;
		this.isDigesting = false;
		this.isAttacking = false;
		this.digestingTime = 42000;
		this.ateTime = 0;
		this.col_posX = posX + 10;
		this.col_posY = posY + 10;
		this.col_width = 60;
		this.col_height = 70;
		this.posX -= 9;
		this.posY -= 1;
		this.width = 98;
		this.height = 91;
		this.sunPointCost = 300;
		this.cd = 8;
		this.activeImage = this.getImageByName("normalImage");
	}
	
	public Chomper(AdventureController pc, int row, int column) {
		super(pc, row, column);
		this.current_health = 300;
		this.isDigesting = false;
		this.isAttacking = false;
		this.digestingTime = 42000;
		this.ateTime = 0;
		this.col_posX = posX + 10;
		this.col_posY = posY + 10;
		this.col_width = 60;
		this.col_height = 70;
		this.posX += 10;
		this.posY -= 24;
		this.width = 130;
		this.height = 114;
		this.sunPointCost = 300;
		this.cd = 8;
		this.activeImage = this.getImageByName("normalImage");
	}
	
	@Override
	public void act() {
		if(this.current_health <= 0) {
			parentController.removeActor(this);
			return;
		}
		if(isAttacking) {
			long curTime = System.currentTimeMillis();
			if(curTime >= ateTime + 800) {
				this.activeImage = this.getImageByName("digestImage");
				this.isAttacking = false;
				this.isDigesting = true;
				//this.parentController.removeActor(attackedZombie);
				this.parentController.removeActor(attackedZombie);
			}
		}
		durableAct();
		changeStatus();
	}
	
	@Override
	public void durableAct() {
		if(isDigesting || isAttacking)
			return;
		
		CopyOnWriteArrayList<Zombie> zombiesInLine = parentController.getZombiesInLines().get(this.row);
		if(!zombiesInLine.isEmpty()) {
			Rectangle pRect = new Rectangle(col_posX, col_posY, col_width+90, col_height);
			for(Zombie z:zombiesInLine) {
				Rectangle zRect = new Rectangle(z.col_posX, z.col_posY, z.col_width, z.col_height);
				if(pRect.intersects(zRect)) {
					this.isAttacking = true;
					this.ateTime = System.currentTimeMillis();
					this.activeImage = this.getImageByName("attackImage");
					this.attackedZombie = z;
					return;
				}
			}
		}
	}

	public void changeStatus() {
		if(isDigesting) {
			long curTime = System.currentTimeMillis();
			if(curTime >= ateTime + digestingTime) {
				this.isDigesting = false;
				this.activeImage = this.getImageByName("normalImage");
				this.ateTime = 0;
			}
		}
	}
	
	@Override
	public Image getCardImage() {
		// TODO Auto-generated method stub
		return cardImage;
	}

	@Override
	public Image getDescirptionImage() {
		// TODO Auto-generated method stub
		return descirptionImage;
	}

	@Override
	public Image getImageByName(String imageName) {
		if(imageName.equals("normalImage"))
			return Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH+"Chomper/Chomper.gif");
		else if(imageName.equals("attackImage"))
			return Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH+"Chomper/ChomperAttack.gif");
		else if(imageName.equals("digestImage"))
			return Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH+"Chomper/ChomperDigest.gif");
		
		return null;
	}


}
