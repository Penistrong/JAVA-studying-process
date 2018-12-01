package com.PVZ.model.bullets;

import javax.swing.ImageIcon;

import static com.PVZ.api.base.constant.SystemConstant.PLANTS_PATH;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.PVZ.api.common.Controller;
import com.PVZ.model.zombies.Zombie;

/**
 * 绿色豌豆
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月9日上午9:08:23
 */
public class Pea extends Bullet{
	private static Map<String, Image> images;
	public int col_width = 24;
	public int col_height = 24;
	
	static {
		images = new HashMap<>();
		images.put("bulletImage", new ImageIcon(PLANTS_PATH+"PB01.gif").getImage());
		images.put("bulletHit", new ImageIcon(PLANTS_PATH+"PeaBulletHit.gif").getImage());
	}
	
	public <T extends Controller> Pea(int movingRow, int startX,T pc, int speed) {
		super(movingRow, startX, pc, speed);
		
		this.activeImage = images.get("bulletImage");
		this.damage = 20;//每发豌豆20伤害
	}
	
	@Override
	public void act() {
		if(!isHit) {
			Rectangle pRect = new Rectangle(posX, posY, col_width, col_height);//豌豆碰撞体积28X28
			List<Zombie> zombiesInLine = parentController.getZombiesInLines().get(movingRow);
			for(Zombie zombie:zombiesInLine) {
				Rectangle zRect = new Rectangle(zombie.col_posX, zombie.col_posY, zombie.col_width, zombie.col_height);
				if(pRect.intersects(zRect)) {
					this.activeImage = images.get("bulletHit");
					zombie.current_health -= this.damage;
					this.isHit = true;
					return;
				}
			}
		}else {
			long curTime = System.currentTimeMillis();
			if(curTime > this.hitTime + 1500) {
				this.parentController.removeActor(this);
				return;
			}
		}
		if(posX>900) {
			this.parentController.removeActor(this);
			return;
		}
		posX += step;
	}
	
}
