package com.PVZ.model.others;

import com.PVZ.model.plants.Plant;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @ClassName Grid
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/10 15:48
 * @Version 1.0
 **/
public class Grid extends JPanel implements MouseListener {
    private int posX;
    private int posY;
    private int width=85;
    private int height=90;

    private Plant assignedPlant;

    public Grid(int posX,int poxY,int width,int height){
        this.posX=posX;
        this.posY=poxY;
        this.width=width;
        this.height=height;
        addMouseListener(this);
        setSize(this.width,this.height);


    }


    public void setAssignedPlant(Plant assignedPlant) {
        this.assignedPlant = assignedPlant;
    }



    public void removePlant(){

    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
