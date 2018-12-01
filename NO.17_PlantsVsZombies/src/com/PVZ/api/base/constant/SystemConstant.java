package com.PVZ.api.base.constant;

import java.util.ArrayList;

/**
 * 系统持久变量集合
 *
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies
 * @date 2018年11月9日上午9:31:16
 */
public final class SystemConstant {

    public static final String PLANTS_PATH = "resources/images/Plants/";
    public static final String ZOMBIES_PATH = "resources/images/Zombies/";
    public static final String INTERFACE_PATH = "resources/images/interface/";
    public static final String PLANTS_CARD_PATH = "resources/images/Card/Plants/";
    public static final String ZOMBIE_CARD_PATH = "resources/images/Card/Zombies/";
    public static final String PLANTS_DESCRIPTION_PATH = "resources/images/Card/PlantDescription/";
    public static final String ZOMBIE_DESCRIPTION_PATH = "resources/images/Card/zombieDescription/";

    public static final String ZOMBIES_CARD_PATH = "resources/images/Card/Zombies/";
    public static final String AUDIO_PATH = "resources/audio/";

    public static final String ROOT_PATH = "resources/images/";
    public static final String INDEX_PATH = "resources/images/index";

    //植物图鉴
    //背景
    public static final String PLANT_HANDBOOK_DAYMODE_BG=SystemConstant.INTERFACE_PATH+"illustrated_handbook/Almanac_PlantBack.png";
    public static final String ZOMBIE_HANDBOOK_DAYMODE_BG=SystemConstant.INTERFACE_PATH+"illustrated_handbook/Almanac_ZombieBack.png";
    //描述框
    public static final String DES_PLANT_CARD=SystemConstant.INTERFACE_PATH+"illustrated_handbook/Almanac_PlantCard.png";
    public static final String DES_ZOMBIE_CARD=SystemConstant.INTERFACE_PATH+"illustrated_handbook/Almanac_ZombieCard.png";
    //描述框背景
    public static final String PLAND_GROUND_DAY=SystemConstant.INTERFACE_PATH+"illustrated_handbook/Almanac_GroundDay.jpg";
    public static final String ZOMBIE_GROUND_DAY=SystemConstant.INTERFACE_PATH+"illustrated_handbook/Almanac_GroundRoof.png";

    //游戏窗口尺寸---运行时
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 620;


    //游戏窗口尺寸---开始时
    public static final int WIDTH_S = 900;
    public static final int HEIGHT_S = 600;

    public static final int GRID_WIDTH = 80;
    public static final int GRID_HEIGHT = 90;



    //植物卡片长度 宽带
    public static final int PLANT_CARD_WIDTH=60;
    public static final int PLANT_CARD_HEIGHT=70;

    
    //状态码
    public static final int ZOMBIE_ERROR = 0;
    public static final int ZOMBIE_MOVING = 1;
    public static final int ZOMBIE_EATING = 2;
    public static final int ZOMBIE_LOST_ORNAMENT = 3;
    public static final int ZOMBIE_LOST_ORNAMENT_EATING = 4;
    public static final int ZOMBIE_LOST_HEAD = 5;
    public static final int ZOMBIE_LOST_HEAD_EATING = 6;
    public static final int ZOMBIE_DYING = 7;

    //线程状态码
    public static final int STOP=-1;
    public static final int SUSPEND=0;
    public static  final int RUNNING=1;

    //关卡代码
    public static final String ADVENTURE_MISSION_1 = "ad_mission_1";
    public static final String ADVENTURE_MISSION_2 = "ad_mission_2";
    public static final String ADVENTURE_MISSION_3 = "ad_mission_3";

    public static  ArrayList<String> MISSIONSLIST;

    static {
        MISSIONSLIST = new ArrayList<>();
        MISSIONSLIST.add(ADVENTURE_MISSION_1);
        MISSIONSLIST.add(ADVENTURE_MISSION_2);
        MISSIONSLIST.add(ADVENTURE_MISSION_3);
    }

    public static  ArrayList<String> ADVENTURE_MODE=new ArrayList<>();



}
