package com.PVZ.api.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.PVZ.model.bullets.Bullet;
import com.PVZ.model.others.Lawnmower;
import com.PVZ.model.plants.Plant;
import com.PVZ.model.zombies.Zombie;

public abstract class Controller {
	protected int FPS;//每秒帧数
	protected String name;
	protected ConcurrentHashMap<Integer,CopyOnWriteArrayList<Zombie>> zombiesInLines;//每行存在的僵尸实例
	protected ConcurrentHashMap<Integer,CopyOnWriteArrayList<Bullet>> bulletsInLines;//每行的发射物
	protected ConcurrentHashMap<Map<Integer,Integer>,Plant> plantsInGrid;//5x9网格中存在的植物实例,Entry<Integer,Integer> key为行,value为列
	protected CopyOnWriteArrayList<Lawnmower> lawnmowers;
	
	public Controller(String name, int FPS) {
		this.name = name;
		this.FPS = FPS;
	}
	
	public abstract void advance();
	
	public abstract <T extends Actor> void addActor(T actor);
	public abstract <T extends Actor> void removeActor(T actor);

	public int getFPS() {
		return FPS;
	}

	public void setFPS(int fPS) {
		FPS = fPS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ConcurrentHashMap<Integer, CopyOnWriteArrayList<Zombie>> getZombiesInLines() {
		return zombiesInLines;
	}

	public void setZombiesInLines(ConcurrentHashMap<Integer, CopyOnWriteArrayList<Zombie>> zombiesInLines) {
		this.zombiesInLines = zombiesInLines;
	}

	public ConcurrentHashMap<Integer, CopyOnWriteArrayList<Bullet>> getBulletsInLines() {
		return bulletsInLines;
	}

	public void setBulletsInLines(ConcurrentHashMap<Integer, CopyOnWriteArrayList<Bullet>> bulletsInLines) {
		this.bulletsInLines = bulletsInLines;
	}

	public ConcurrentHashMap<Map<Integer, Integer>, Plant> getPlantsInGrid() {
		return plantsInGrid;
	}

	public void setPlantsInGrid(ConcurrentHashMap<Map<Integer, Integer>, Plant> plantsInGrid) {
		this.plantsInGrid = plantsInGrid;
	}

	public CopyOnWriteArrayList<Lawnmower> getLawnmowers() {
		return lawnmowers;
	}

	public void setLawnmowers(CopyOnWriteArrayList<Lawnmower> lawnmowers) {
		this.lawnmowers = lawnmowers;
	}
	
	
}
