package com.PVZ.model.plants;

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

import static com.PVZ.api.base.constant.SystemConstant.PLANTS_DESCRIPTION_PATH;

/**
 * 豌豆射手
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月8日上午11:09:31
 */
public class PeaShooter extends Plant implements DurablePlants{

	protected static Image cardImage;
	protected static Image descirptionImage;

	private int pea_speed;//豌豆速度
	private int pea_damage;//每颗豌豆伤害
	private int timeInterval;//发射豌豆间隔
	private long last_shoot_time;


	static {
		cardImage=new ImageIcon(SystemConstant.PLANTS_CARD_PATH+"card_peashooter.png").getImage();
		descirptionImage=new ImageIcon(PLANTS_DESCRIPTION_PATH+"PeaShooterDescription.png").getImage();
	}
	
	public PeaShooter(){
		this.activeImage = this.getImageByName("normalImage");
		this.sunPointCost = 100;
		this.cd=8;
	}
	
	public <T extends Controller>PeaShooter(T pc, int row, int column) {
		super(pc, row, column);
		this.current_health = 300;
		this.pea_speed = 12;//0.8s一格
		this.pea_damage = 20;
		this.timeInterval = 1400;//1.4秒一颗豌豆
		this.last_shoot_time = System.currentTimeMillis();//初始化时间
		this.activeImage = this.getImageByName("normalImage");
		this.width = 106;
		this.height = 107;
		this.col_posX = posX + 5;
		this.posX -= 13;
		this.posY += 4;
		this.col_posY = posY + 16;
		this.col_width = 70;
		this.col_height = 70;
		this.sunPointCost = 100;
		this.cd=8;
	}

	public PeaShooter(AdventureController pc, int row, int column) {
		super(pc, row, column);
		this.current_health = 300;
		this.pea_speed = 12;
		this.pea_damage = 20;
		this.timeInterval = 1400;//1.4秒一颗豌豆
		this.last_shoot_time = System.currentTimeMillis();//初始化时间
		this.activeImage = this.getImageByName("normalImage");
		this.width = 106;
		this.height = 107;
		this.col_posX = posX + 5;
		this.posX -= 13;
		this.posY += 4;
		this.col_posY = posY + 16;
		this.col_width = 70;
		this.col_height = 70;
		this.sunPointCost = 100;
		this.cd=8;
	}


	@Override
	public void act() {
		if(this.current_health <= 0) {
			this.parentController.removeActor(this);
			return;
		}
		durableAct();
	}

	@Override
	public void durableAct() {
		//豌豆射手:每当其所在行出现僵尸就会发射豌豆
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
				Pea pea = new Pea(this.row, this.posX + 80, this.parentController, this.pea_speed);
				this.parentController.addActor(pea);
				last_shoot_time = curTime;//发射豌豆后，更新当前发射时间
			}
		}
	}
	
	@Override
	public Image getCardImage() {
		return cardImage;
	}

	@Override
	public  Image getDescirptionImage() {
		return descirptionImage;
	}

	@Override
	public Image getImageByName(String imageName) {
		if(imageName.equals("normalImage"))
			return Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH + "PeaShooter/PeaShooter.gif");
		return null;
	}
}
