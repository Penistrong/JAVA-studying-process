package com.PVZ.model.zombies;

import java.awt.Image;

import com.PVZ.api.common.Controller;
import com.PVZ.controller.adventure.action.AdventureController;

/**1`
 * 临时头部动画触发对象
 * @author tyz[U201717008]
 * @project NO.17_PlantsVsZombies
 * @date 2018年11月30日下午5:31:06
 */
public class ZombieHead extends Zombie{

    private long triggeredTime;

    public <T extends Controller>ZombieHead(int movingRow, int posX, int posY, T parentController, Image headImage) {
        this.parentController = parentController;
        this.posX = posX + 40;
        this.posY = posY - 20;
        changeSpeed(0);
        this.col_posX = 0;
        this.col_posY = 0;
        this.col_width = 0;
        this.col_height = 0;
        this.movingRow = movingRow;
        this.width = 150;
        this.height = 186;

        this.triggeredTime = System.currentTimeMillis();
        this.activeImage = headImage;
    }

    @Override
    public int criticalPoint() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Image getImageByName(String image_name) {
        return null;
    }

    @Override
    public Image getCardImage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Image getDescirptionImage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void act() {
        long cur_Time = System.currentTimeMillis();
        if(cur_Time > this.triggeredTime + 2500) {
            this.parentController.removeActor(this);
        }
    }

}