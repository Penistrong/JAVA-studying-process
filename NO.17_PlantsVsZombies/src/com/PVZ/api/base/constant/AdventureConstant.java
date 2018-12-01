package com.PVZ.api.base.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 冒险模式常量
 * @author Penistrong[chenliwei]
 * @project NO.17_PlantsVsZombies	
 * @date 2018年11月9日下午6:58:15
 */
public final class AdventureConstant {
	public static final int ROWS = 5;
	public static final int COLUMNS = 9;

	public static final String PATH_DAYMODE_BG=SystemConstant.INTERFACE_PATH+"game_interface/adventure_mode/daymode_bg.jpg";
	public static final String PATH_NIGHT_BG=SystemConstant.INTERFACE_PATH+"game_interface/adventure_mode/nightmode_bg.png";
	public static final int[] ROW_Y={170,260,350,440,530,620};
	
	public static List<Map<String, String>> MISSIONS_PARAMS;
	
	static {
		MISSIONS_PARAMS = new ArrayList<>();
		Map<String, String> m1_params = new HashMap<>();
		m1_params.put("startSunPoints", "150");//转换阳光值记得String.valueOf
		m1_params.put("BG_PATH", PATH_DAYMODE_BG);
		MISSIONS_PARAMS.add(m1_params);
		Map<String, String> m2_params = new HashMap<>();
		m2_params.put("startSunPoints", "300");//转换阳光值记得String.valueOf
		m2_params.put("BG_PATH", PATH_DAYMODE_BG);
		MISSIONS_PARAMS.add(m2_params);
		Map<String, String> m3_params = new HashMap<>();
		m3_params.put("startSunPoints", "500");//转换阳光值记得String.valueOf
		m3_params.put("BG_PATH", PATH_NIGHT_BG);
		MISSIONS_PARAMS.add(m3_params);
	}
}
