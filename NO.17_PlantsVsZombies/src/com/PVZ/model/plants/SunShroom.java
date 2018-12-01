package com.PVZ.model.plants;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.api.common.Controller;
import com.PVZ.api.interfaces.plants.FunctionalPlants;
import com.PVZ.controller.adventure.action.AdventureController;
import com.PVZ.model.others.Sun;

/**
 * 第三关阳光菇
 * @author tyz[U201717008]
 * @project NO.17_PlantsVsZombies
 * @date 2018年11月30日下午5:05:21
 */
public class SunShroom extends Plant implements FunctionalPlants{
    private static Image descirptionImage;
    protected static Image cardImage;

    private int timeInterval;//阳光产生间隔
    private long lastProducedTime;//上次生产阳光的时间
    private boolean isGrownUp;//是否已成长?
    private long plantedTime;//种植时间

    static {
        descirptionImage = new ImageIcon(SystemConstant.PLANTS_DESCRIPTION_PATH+"SunShroomAbout.png").getImage();
        cardImage = new ImageIcon(SystemConstant.PLANTS_CARD_PATH+"SunShroomCard .png").getImage();
    }

    public SunShroom() {
        this.activeImage = this.getImageByName("normalImage1");
        this.sunPointCost = 25;
        this.cd = 7;
    }

    public <T extends Controller> SunShroom(T pc, int row, int column){
        super(pc, row, column);
        this.current_health = 300;
        this.timeInterval = 24000;//24s阳光产生间隔
        this.lastProducedTime = System.currentTimeMillis();//初始化上次生产阳光的时间
        this.activeImage = this.getImageByName("normalImage1");
        this.isGrownUp = false;
        this.plantedTime = System.currentTimeMillis();

        this.posX += 10;
        this.posY += 29;
        this.width = 59;
        this.height = 61;
        this.col_posX = posX;
        this.col_posY = posY;
        this.col_width = width;
        this.col_height = height;

        this.sunPointCost = 25;
        this.cd = 7;
    }

    public SunShroom(AdventureController pc, int row, int column){
        super(pc, row, column);
        this.current_health = 300;
        this.timeInterval = 24000;//24s阳光产生间隔
        this.lastProducedTime = System.currentTimeMillis();//初始化上次生产阳光的时间
        this.activeImage = this.getImageByName("normalImage1");
        this.isGrownUp = false;
        this.plantedTime = System.currentTimeMillis();
        
        this.posX += 10;
        this.posY += 29;
        this.width = 59;
        this.height = 61;
        this.col_posX = posX;
        this.col_posY = posY;
        this.col_width = width;
        this.col_height = height;

        this.sunPointCost = 25;
        this.cd = 7;
    }

    @Override
    public void act() {
        // TODO Auto-generated method stub
        if(this.current_health <= 0) {
            this.parentController.removeActor(this);
            return;
        }
        if(!isGrownUp) {
            long curTime = System.currentTimeMillis();
            if(curTime >= this.plantedTime + 45000) {
                this.isGrownUp = true;
                this.activeImage = this.getImageByName("normalImage2");
                this.posX = 125 + (this.column-1)*80;
                this.posY = 85 + (this.row-1)*90;
                this.posX += 10;
                this.posY += 29;
                col_posX = posX;
                col_posY = posY;
                col_width = width;
                col_height = height;
            }
        }
        functionalAct();
    }

    @Override
    public Image getImageByName(String imageName) {
        if(imageName.equals("normalImage1"))
            return Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH + "SunShroom/SunShroom2.gif");
        else if(imageName.equals("normalImage2"))
            return Toolkit.getDefaultToolkit().createImage(SystemConstant.PLANTS_PATH + "SunShroom/SunShroom.gif");
        return null;
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
    public void functionalAct() {
        //阳光菇:间隔时间产生阳光
        long currentTime = System.currentTimeMillis();
        if(currentTime - this.lastProducedTime >= timeInterval) {
            Sun sun = new Sun(this.parentController, false, this.posX+50, this.posY+30);
            this.parentController.addActor(sun);
            this.lastProducedTime = currentTime;
        }
    }
}