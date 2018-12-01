package com.PVZ.model.plants;

import static com.PVZ.api.base.constant.SystemConstant.PLANTS_DESCRIPTION_PATH;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.api.common.Controller;
import com.PVZ.api.interfaces.plants.DurablePlants;
import com.PVZ.controller.adventure.action.AdventureController;
import com.PVZ.model.bullets.Pea;
import com.PVZ.model.zombies.Zombie;

/**
 * 豌豆机关枪
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月30日下午9:21:42
 */
public class GatlingPeaShooter extends Plant implements DurablePlants{
	
	protected static Image cardImage;
	protected static Image descirptionImage;
	
	private int pea_speed;//豌豆速度
	private int pea_damage;//每颗豌豆伤害
	private int timeInterval;//发射豌豆间隔
	private long last_shoot_time;
	private long last_pea_time;
	private boolean isShooting;
	
	static {
		cardImage=new ImageIcon(SystemConstant.PLANTS_CARD_PATH+"GatlingPeaShooterCard.png").getImage();
		descirptionImage=new ImageIcon(PLANTS_DESCRIPTION_PATH+"GatlingPeaShooterAbout.png").getImage();
	}
	
	public GatlingPeaShooter() {
		this.activeImage = this.getImageByName("normalImage");
		this.sunPointCost = 250;
		this.cd = 50;
	}
	
	public <T extends Controller>GatlingPeaShooter(T pc, int row, int column){
		super(pc, row, column);
		this.current_health = 300;
		this.pea_damage = 20;
		this.pea_speed = 800;
		this.timeInterval = 1400;//每1.4s四颗豌豆
		this.last_shoot_time = System.currentTimeMillis();//初始化时间
		this.activeImage = this.getImageByName("normalImage");
		this.posX += 1;
		this.posY += 6;
		this.width = 88;
		this.height = 84;
		this.col_posX = posX;
		this.col_posY = posY;
		this.col_width = width;
		this.col_height = height;
		this.isShooting = false;
		
		this.sunPointCost = 250;
		this.cd = 50;
	}
	
	public GatlingPeaShooter(AdventureController pc, int row, int column) {
		super(pc, row, column);
		this.current_health = 300;
		this.pea_damage = 20;
		this.pea_speed = 12;
		this.timeInterval = 1400;//每1.4s四颗豌豆
		this.last_shoot_time = System.currentTimeMillis();//初始化时间
		this.activeImage = this.getImageByName("normalImage");
		this.posX += 1;
		this.posY += 6;
		this.width = 88;
		this.height = 84;
		this.col_posX = posX;
		this.col_posY = posY;
		this.col_width = width;
		this.col_height = height;
		this.isShooting = false;
		
		this.sunPointCost = 250;
		this.cd = 50;
	}
	
	@Override
	public void act() {
		ShootFourPeas();
		if(this.current_health <= 0) {
			this.parentController.removeActor(this);
			return;
		}
		durableAct();
	}
	
	@Override
	public void durableAct() {
		//豌豆机关枪:每当其所在行出现僵尸就会连续发射四颗豌豆(1s内四颗)
		CopyOnWriteArrayList<Zombie> zombiesInLine = parentController.getZombiesInLines().get(this.row);
		if(!zombiesInLine.isEmpty()) {
			for(Zombie zombie:zombiesInLine) {
				if(zombie.col_posX <= 850)
					break;
				else
					return;
			}
			long curTime = System.currentTimeMillis();
			if(curTime >= last_shoot_time + this.timeInterval) {
				this.isShooting = true;
				last_shoot_time = curTime;//发射豌豆后，更新上次发射时间
				last_pea_time = curTime - 250;
			}
		}
		
	}

	private void ShootFourPeas() {
		if(isShooting) {
			long curTime = System.currentTimeMillis();
			if(curTime >= last_pea_time + 250) {
				Pea pea = new Pea(this.row, this.posX + 80, this.parentController, this.pea_speed);
				this.parentController.addActor(pea);
				this.last_pea_time = curTime;
			}
			if(curTime >= last_shoot_time + 1000) {
				this.isShooting = false;
			}
		}
	}
	
	@Override
	public Image getImageByName(String imageName) {
		if(imageName.equals("normalImage"))
			return Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH+"GatlingPea/GatlingPea.gif");
		return null;
	}

	@Override
	public Image getCardImage() {
		return cardImage;
	}

	@Override
	public Image getDescirptionImage() {
		return descirptionImage;
	}


}
