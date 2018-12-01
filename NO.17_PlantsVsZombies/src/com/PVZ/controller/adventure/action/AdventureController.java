package com.PVZ.controller.adventure.action;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.PVZ.api.base.constant.AdventureConstant;
import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.api.common.Actor;
import com.PVZ.api.common.CollisionHandler;
import com.PVZ.api.common.Controller;
import com.PVZ.model.bullets.Bullet;
import com.PVZ.model.others.Lawnmower;
import com.PVZ.model.others.MsgLabel;
import com.PVZ.model.others.Sun;
import com.PVZ.model.plants.Plant;
import com.PVZ.model.zombies.Zombie;

/**
 * 冒险模式Controller
 *
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies
 * @date 2018年11月9日下午6:38:15
 */
public class AdventureController extends Controller{
	private CopyOnWriteArrayList<Sun> activeSunInPanel;//待收集的活跃阳光
	private CollisionHandler col_handler;//碰撞机
	private long bgSunProTime;//背景阳光产生时间
	private int sun_points;//太阳数
	private long modeBeginTime;
	private long lastZombieTime;
	private boolean isMissionPassed;
	private boolean isZombieProduceEnd;
	private boolean isZombieAteYourBrain;
	private MsgLabel msgLabel;
	
	public AdventureController(String name, int FPS) {
		super(name, FPS);
		initializeController();
	}
	
	public void AdventureAction() {
		//定时刷新
		Timer timer = new Timer();
		int timeInterval = 20;//ms为单位,50fps，一秒50帧,帧距20ms
		//定时器循环执行
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				actorsAction();
				
			}
			
		}, timeInterval, timeInterval);
	}
	
	
	/**
	 * @Title:initializeController
	 * @Description:TODO 
	 * @return void
	 * @throws
	 */
	private void initializeController() {
		
		this.zombiesInLines = new ConcurrentHashMap<>();
		for(int i = 1;i <= AdventureConstant.ROWS;i++) {
			CopyOnWriteArrayList<Zombie> zombiesInLine = new CopyOnWriteArrayList<>();
			this.zombiesInLines.put(i, zombiesInLine);
		}
		this.bulletsInLines = new ConcurrentHashMap<>();
		for(int i = 1;i <= AdventureConstant.ROWS;i++) {
			CopyOnWriteArrayList<Bullet> bulletsInLine = new CopyOnWriteArrayList<>();
			this.bulletsInLines.put(i, bulletsInLine);
		}
		this.plantsInGrid = new ConcurrentHashMap<>();
		this.activeSunInPanel = new CopyOnWriteArrayList<>();
		this.lawnmowers = new CopyOnWriteArrayList<>();
		this.initializeLawnmowers();
		this.msgLabel = null;
		
		this.col_handler = new CollisionHandler();
		switch(this.name){
			case SystemConstant.ADVENTURE_MISSION_1:
				this.sun_points = 150;//初始150
				break;
			case SystemConstant.ADVENTURE_MISSION_2:
				this.sun_points = 300;
				break;
			case SystemConstant.ADVENTURE_MISSION_3:
				this.sun_points = 200;
				break;
		}

		this.modeBeginTime = System.currentTimeMillis();
		this.lastZombieTime = System.currentTimeMillis();
		
		this.isZombieProduceEnd = false;
		this.isMissionPassed = false;
		this.isZombieAteYourBrain = false;
	}
	
	public boolean isZombieAteYourBrain() {
		return isZombieAteYourBrain;
	}

	public boolean isMissionPassed() {
		return isMissionPassed;
	}

	public void setZombieAteYourBrain(boolean isZombieAteYourBrain) {
		this.isZombieAteYourBrain = isZombieAteYourBrain;
	}

	public CopyOnWriteArrayList<Sun> getActiveSunInPanel(){
		return this.activeSunInPanel;
	}
	
	/** 
	 * @Title:addZombie
	 * @Description:TODO
	 * @param lineOfzombie
	 * @param zombie 
	 * @return void
	 * @throws
	 */
	public <T extends Zombie> void addZombie(int lineOfzombie, T zombie) {
		zombiesInLines.get(lineOfzombie).add(zombie);
	}
	
	/**
	 * @Title:addBullet
	 * @Description:TODO
	 * @param lineOfbullet
	 * @param bullet 
	 * @return void
	 * @throws
	 */
	public <T extends Bullet > void addBullet(int lineOfbullet, T bullet) {
		bulletsInLines.get(lineOfbullet).add(bullet);
	}
	
	/**
	 * @Title:addPlant
	 * @Description:TODO
	 * @param row
	 * @param column
	 * @param plant 
	 * @return void
	 * @throws
	 */
	public <T extends Plant> void addPlant(int row, int column, T plant) {
		Map<Integer, Integer> pos = new HashMap<>();
		pos.put(row, column);
		plantsInGrid.put(pos, plant);
	}
	
	/**
	 * @Title:addSun
	 * @Description:TODO
	 * @param sun 
	 * @return void
	 * @throws
	 */
	public void addSun(Sun sun) {
		activeSunInPanel.add(sun);
	}
	
	public void addLawnmower(Lawnmower lawnmower) {
		lawnmowers.add(lawnmower);
	}
	
	public void addMsgLabel(MsgLabel msglabel) {
		this.msgLabel = msglabel;
	}
	
	/**
	 * @Title:removeActor
	 * @Description:TODO
	 * @param actor 
	 * @return void
	 * @throws
	 */
	@Override
	public <T extends Actor> void removeActor(T actor) {
		if(actor instanceof Plant) {
			for(Plant plant:plantsInGrid.values()) {
				if(plant.equals(actor)) {
					List<Plant> removedActor = new ArrayList<>();
					removedActor.add(plant);
					plantsInGrid.values().removeAll(removedActor);
					System.out.println("[LOG]移除"+actor.getClass().getSimpleName());
					break;
				}
			}
		}else if(actor instanceof Zombie) {
			LoopSearch:for(CopyOnWriteArrayList<Zombie> zombies:zombiesInLines.values()) {
				for(Zombie zombie:zombies) {
					if(zombie.equals(actor)) {
						zombies.remove(zombie);
						System.out.println("[LOG]移除"+actor.getClass().getSimpleName());
						break LoopSearch;
					}
				}
			}
				
		}else if(actor instanceof Sun) {
			for(Sun sun:activeSunInPanel) {
				if(sun.equals(actor)) {
					activeSunInPanel.remove(sun);
					System.out.println("[LOG]移除"+actor.getClass().getSimpleName());
					break;
				}
			}
		}else if(actor instanceof Bullet) {
			LoopSearch:for(List<Bullet> bullets:bulletsInLines.values()) {
				for(Bullet bullet:bullets) {
					if(bullet.equals(actor)) {
						bullets.remove(bullet);
						break LoopSearch;
					}
				}
			}
		}else if(actor instanceof Lawnmower) {
			for(Lawnmower lawnmower:lawnmowers) {
				if(lawnmower.equals(actor)) {
					lawnmowers.remove(lawnmower);
					System.out.println("[LOG]移除"+actor.getClass().getSimpleName());
					break;
				}
			}
		}else if(actor instanceof MsgLabel) {
			System.out.println("置空过");
			this.msgLabel = null;
		}
	}
	/**
	 * @Title:addActor
	 * @Description:TODO
	 * @param actor 
	 * @return void
	 * @throws
	 */
	@Override
	public <T extends Actor> void addActor(T actor) {
		if(actor instanceof Plant) {
			this.addPlant(((Plant) actor).getRow(), ((Plant) actor).getColumn(), (Plant)actor);
		}else if(actor instanceof Bullet) {
			this.addBullet(((Bullet) actor).getMovingRow(), (Bullet)actor);
		}else if(actor instanceof Sun) {
			this.addSun((Sun)actor);
		}else if(actor instanceof Zombie) {
			this.addZombie(((Zombie) actor).getMovingRow(), (Zombie)actor);
			//this.debugZombies();
		}else if(actor instanceof Lawnmower) {
			this.addLawnmower((Lawnmower)actor);
		}else if(actor instanceof MsgLabel) {
			this.addMsgLabel((MsgLabel)actor);
		}
		
	}
	
	/**
	 * @Title:actorsAction
	 * @Description:逻辑步进 
	 * @return void
	 * @throws
	 */
	public void actorsAction() {
		/*for(List<Zombie> zombies:this.zombiesInLines.values()) {
			Iterator<Zombie> iter_z = zombies.iterator();
			while(iter_z.hasNext())
				iter_z.next().act();
		}*/
		for(List<Zombie> zombies:this.zombiesInLines.values())
			for(Zombie zombie:zombies)
				zombie.act();
		
		/*for(List<Bullet> bullets:this.bulletsInLines.values()) {
			Iterator<Bullet> iter = bullets.iterator();
			while(iter.hasNext())
				iter.next().act();
		}*/
		for(CopyOnWriteArrayList<Bullet> bullets:this.bulletsInLines.values())
			for(Bullet bullet:bullets)
				bullet.act();
		
		for(Plant plant:this.plantsInGrid.values())
			plant.act();
		/*Iterator<Plant> iter_p = this.plantsInGrid.values().iterator();
		while(iter_p.hasNext())
			iter_p.next().act();*/
		
		for(Sun sun:this.activeSunInPanel) {
			sun.act();
		}
		
		for(Lawnmower lawnmower:this.lawnmowers)
			lawnmower.act();
		
		if(msgLabel != null)
			msgLabel.act();
	}
	
	public boolean shovePlantByRC(int row, int column) {
		for(Plant plant:plantsInGrid.values()) {
			if(plant.getRow() == row && plant.getColumn() == column) {
				System.out.println("[LOG]铲去"+plant.getClass().getSimpleName()+"@["+row+", "+column+"]");
				this.removeActor(plant);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @Title:generateZombiesByType
	 * @Description:传入僵尸类型和数量批量产生僵尸
	 * @param sum
	 * @param type 
	 * @return void
	 * @throws
	 */
	public void generateZombiesByType(int sum, String type) {
		try {
			Class<?> cls = Class.forName("com.PVZ.model.zombies."+type);
			Constructor<?> cons = cls.getConstructor(int.class, AdventureController.class);
			for(int i = 0;i < sum;i++) {
				int movingRow =(int)(1 + Math.random()*5);
				Zombie zombie = (Zombie) cons.newInstance(movingRow, this);//使用反射新建实例后向下转型
				this.addActor(zombie);
				System.out.println("[LOG]生成1个"+type+"@ROW:"+zombie.getMovingRow());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title:generatePlantByName
	 * @Description:生成对应植物实例并添加
	 * @param type
	 * @param pos
	 * @param posY 
	 * @return void
	 * @throws
	 */
	public boolean generatePlantByName(String type, int row, int column) {
		if(!((row>=1 && row<=5)&&(column>=1 && column <= 9))) {
			System.out.println("[LOG]种植区域不对不能种植!");
			return false;
		}
		
		for(Plant plant:this.plantsInGrid.values()) {
			if((plant.getRow()==row) && (plant.getColumn()==column)) {
				System.out.println("[LOG]该格子已有植物不能种植!");
				return false;
			}
		}
		
		try {
			Class<?> cls = Class.forName(type);
			Constructor<?> cons = cls.getConstructor(AdventureController.class, int.class, int.class);
			Plant plant = (Plant)cons.newInstance(this, row, column);
			if(this.sun_points < plant.getSunPointCost()) {
				System.out.println("[LOG]费用不足无法种植");
				return false;
			}
			this.sun_points -= plant.getSunPointCost();
			this.addActor(plant);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Title:generateBgSun
	 * @Description:场地阳光, 5s一个
	 * @return void
	 * @throws
	 */
	public void generateBgSun() {
		if(this.name.equals(SystemConstant.ADVENTURE_MISSION_3))
			return;
		long curTime = System.currentTimeMillis();
		if(curTime - bgSunProTime >= 5000) {
			int random_x = (int)(205+Math.random()*520);
			Sun sun = new Sun(this, true, random_x, 90);
			this.bgSunProTime = curTime;
			this.addActor(sun);
		}
	}
	
	public void initializeLawnmowers() {
		for(int i = 1;i<=5;i++) {
			Lawnmower lw = new Lawnmower(i, this);
			this.addActor(lw);
		}
	}
	
	/**
	 * @Title:generateZombieItself
	 * @Description:生成僵尸 
	 * @return void
	 * @throws
	 */
	public void generateZombieItself() {
		long curTime = System.currentTimeMillis();
		int total_seconds = (int)(curTime - modeBeginTime)/1000;
		int temp_seconds = (int)(curTime - lastZombieTime)/1000;
		switch(this.name) {
		case SystemConstant.ADVENTURE_MISSION_1:
			if(total_seconds < 90 && total_seconds >= 10 && temp_seconds >= 20) {
				int sum = (int)(1 + Math.random()*2);
				this.generateZombiesByType(sum, "NormalZombie");
				this.lastZombieTime = curTime;
			}else if(total_seconds >= 90 && total_seconds < 180) {
				//第一大波僵尸
				if(temp_seconds >= 20 && total_seconds < 110) {
					this.addActor(new MsgLabel(this, "LargeWave"));
					this.generateZombiesByType(1, "FlagZombie");
					this.generateZombiesByType(3, "NormalZombie");
					this.generateZombiesByType(2, "ConeHeadZombie");
					this.lastZombieTime = curTime;
				}else if(temp_seconds >= 30 && total_seconds < 140) {
					this.generateZombiesByType(6, "NormalZombie");
					this.generateZombiesByType(3, "ConeHeadZombie");
					this.lastZombieTime = curTime;
				}	
			}else if(total_seconds >= 180 && total_seconds < 240 && temp_seconds >= 20) {
				int sum = (int)(2+Math.random()*2);//2~3
				int czb_sum = (int)(1+Math.random()*sum);//1~sum-1
				this.generateZombiesByType(czb_sum, "ConeHeadZombie");
				this.generateZombiesByType(sum - czb_sum, "NormalZombie");
				this.lastZombieTime = curTime;
			}else if(total_seconds >= 240 ) {
				//第二大波僵尸
				if(temp_seconds >= 20 && total_seconds < 270) {
					this.addActor(new MsgLabel(this, "FinalWave"));
					this.generateZombiesByType(2, "FlagZombie");
					this.generateZombiesByType(5, "NormalZombie");
					this.generateZombiesByType(3, "NewspaperZombie");
					this.lastZombieTime = curTime;
				}else if(temp_seconds >= 30 && total_seconds < 300) {
					this.generateZombiesByType(6, "NormalZombie");
					this.generateZombiesByType(4, "ConeHeadZombie");
					this.generateZombiesByType(2, "BucketHeadZombie");
					this.lastZombieTime = 900000000000000L;//本关结束
					this.isZombieProduceEnd = true;
				}else if(total_seconds > 360) {
					for(List<Zombie> zombiesInLine:this.zombiesInLines.values()) {
						if(zombiesInLine.isEmpty())
							continue;
						else
							return;
					}
					this.isMissionPassed = true;
				}
			}
			break;
		case SystemConstant.ADVENTURE_MISSION_2:
			if(total_seconds < 90 && total_seconds >= 10 && temp_seconds >= 20) {
				int sum = (int)(1 + Math.random()*2);
				this.generateZombiesByType(sum, "NormalZombie");
				this.lastZombieTime = curTime;
			}else if(total_seconds >= 90 && total_seconds < 180) {
				//第一大波僵尸
				if(temp_seconds >= 20 && total_seconds < 110) {
					this.addActor(new MsgLabel(this, "LargeWave"));
					this.generateZombiesByType(1, "FlagZombie");
					this.generateZombiesByType(3, "NormalZombie");
					this.generateZombiesByType(1, "BucketHeadZombie");
					this.generateZombiesByType(2, "NewspaperZombie");
					this.lastZombieTime = curTime;
				}else if(temp_seconds >= 30 && total_seconds < 140) {
					this.generateZombiesByType(1, "FlagZombie");
					this.generateZombiesByType(4, "NormalZombie");
					this.generateZombiesByType(4, "ConeHeadZombie");
					this.lastZombieTime = curTime;
				}	
			}else if(total_seconds >= 180 && total_seconds < 240 && temp_seconds >= 20) {
				int sum = (int)(2+Math.random()*2);//2~3
				int czb_sum = (int)(1+Math.random()*sum);//1~sum-1
				this.generateZombiesByType(czb_sum, "ConeHeadZombie");
				this.generateZombiesByType(sum - czb_sum, "NormalZombie");
				int nor_sum = (int)(Math.random()*2);//0~1
				this.generateZombiesByType(nor_sum, "NewspaperZombie");
				this.lastZombieTime = curTime;
			}else if(total_seconds >= 240 ) {
				//第二大波僵尸
				if(temp_seconds >= 20 && total_seconds < 270) {
					this.addActor(new MsgLabel(this, "FinalWave"));
					this.generateZombiesByType(2, "FlagZombie");
					this.generateZombiesByType(5, "NormalZombie");
					this.generateZombiesByType(2, "NewspaperZombie");
					this.generateZombiesByType(3, "ConeHeadZombie");
					this.lastZombieTime = curTime;
				}else if(temp_seconds >= 30 && total_seconds < 300) {
					this.generateZombiesByType(2, "FlagZombie");
					this.generateZombiesByType(6, "NormalZombie");
					this.generateZombiesByType(3, "NewspaperZombie");
					this.generateZombiesByType(4, "BucketHeadZombie");
					this.lastZombieTime = 900000000000000L;//本关结束
					this.isZombieProduceEnd = true;
				}else if(total_seconds > 340) {
					for(List<Zombie> zombiesInLine:this.zombiesInLines.values()) {
						if(zombiesInLine.isEmpty())
							continue;
						else
							return;
					}
					this.isMissionPassed = true;
				}
			}
			break;
		case SystemConstant.ADVENTURE_MISSION_3:
			if(total_seconds < 90 && total_seconds >= 10 && temp_seconds >= 20) {
				int sum = (int)(1 + Math.random()*2);
				this.generateZombiesByType(sum, "NormalZombie");
				this.lastZombieTime = curTime;
			}else if(total_seconds >= 90 && total_seconds < 180) {
				//第一大波僵尸
				if(temp_seconds >= 20 && total_seconds < 110) {
					this.addActor(new MsgLabel(this, "LargeWave"));
					this.generateZombiesByType(1, "FlagZombie");
					this.generateZombiesByType(3, "NormalZombie");
					this.generateZombiesByType(1, "BucketHeadZombie");
					this.generateZombiesByType(2, "NewspaperZombie");
					this.lastZombieTime = curTime;
				}else if(temp_seconds >= 30 && total_seconds < 140) {
					this.generateZombiesByType(2, "FlagZombie");
					this.generateZombiesByType(5, "NormalZombie");
					this.generateZombiesByType(4, "ConeHeadZombie");
					this.generateZombiesByType(2, "BucketHeadZombie");
					this.lastZombieTime = curTime;
				}	
			}else if(total_seconds >= 180 && total_seconds < 240 && temp_seconds >= 20) {
				int sum = (int)(2+Math.random()*3);//2~4
				int czb_sum = (int)(1+Math.random()*sum);//1~sum-1
				this.generateZombiesByType(czb_sum, "BucketHeadZombie");
				this.generateZombiesByType(sum - czb_sum, "ConeHeadZombie");
				int nor_sum = (int)(1+Math.random()*2);//1~2
				this.generateZombiesByType(nor_sum, "NewspaperZombie");
				this.lastZombieTime = curTime;
			}else if(total_seconds >= 240 ) {
				//第二大波僵尸
				if(temp_seconds >= 20 && total_seconds < 270) {
					this.generateZombiesByType(2, "FlagZombie");
					this.generateZombiesByType(5, "ConeHeadZombie");
					this.generateZombiesByType(3, "NewspaperZombie");
					this.generateZombiesByType(4, "BucketHeadZombie");
					this.lastZombieTime = curTime;
				}else if(temp_seconds >= 30 && total_seconds < 300) {
					this.generateZombiesByType(2, "FlagZombie");
					this.generateZombiesByType(6, "NormalZombie");
					this.generateZombiesByType(5, "NewspaperZombie");
					this.generateZombiesByType(8, "BucketHeadZombie");
				}else if(total_seconds > 340) {
					if(temp_seconds >= 15) {
						int sum = (int)(2+Math.random()*2);//2~3
						this.generateZombiesByType(sum, "ConeHeadZombie");
						int bz_sum = (int)(1+Math.random()*2);//1~2
						this.generateZombiesByType(bz_sum, "BucketHeadZombie");
						this.generateZombiesByType(bz_sum, "NewspaperZombie");
						this.lastZombieTime = curTime;
					}
				}
			}
			break;
		}
	}
	
	public MsgLabel getMsgLabel() {
		return msgLabel;
	}

	public void setMsgLabel(MsgLabel msgLabel) {
		this.msgLabel = msgLabel;
	}

	@Override
	public void advance() {
		generateBgSun();
		actorsAction();
		generateZombieItself();
	}

	public int getSun_points() {
		return sun_points;
	}

	public void setSun_points(int sun_points) {
		this.sun_points = sun_points;
	}
	
}

   
