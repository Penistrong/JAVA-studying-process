package com.PVZ.controller;

import com.PVZ.view.handbook_panel.ShowPanel;
import com.PVZ.view.welcome_panel.StartPanel;

import javax.swing.*;

import static com.PVZ.api.base.constant.SystemConstant.*;


/**
 * @ClassName Main
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/8 18:29
 * @Version 1.0
 **///,new AdventureModePanel())
public class Main {
	public static JFrame tst=new JFrame();

    public static void main(String[]args){

        tst.setSize(WIDTH_S,HEIGHT_S);
        tst.setResizable(false);
        StartPanel startPanel=new StartPanel();
        //Main.tst.setContentPane(new HandbookSelectPanel(startPanel));
        tst.setContentPane(startPanel);
        ShowPanel adp=new ShowPanel();

        //tst.setContentPane(new HandbookPanel(PLANT_HANDBOOK_DAYMODE_BG,PLAND_GROUND_DAY,DES_PLANT_CARD,new PlantShowPanel(),new HandbookSelectPanel(new StartPanel())));

        tst.setVisible(true);
        tst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	
    	/*//Test for reflection
    	 * try {
			Class<?> cls = Class.forName("com.PVZ.model.zombies.ConeHeadZombie");
			Constructor<?> cons = cls.getConstructor(int.class);
			int movingRow = (int)(1 + Math.random()*5);
			Zombie zombie = (Zombie)cons.newInstance(movingRow);
			System.out.println(zombie.getClass().getName());
			if(zombie instanceof ConeHeadZombie) {
				System.out.println("路障僵尸实例");
				System.out.println(zombie.getMovingRow()+"|"+zombie.getCurrent_health());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }




}
