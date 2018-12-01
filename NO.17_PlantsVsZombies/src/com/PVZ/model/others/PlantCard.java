package com.PVZ.model.others;

import com.PVZ.model.plants.Plant;
import com.PVZ.model.plants.SunFlower;
import com.PVZ.view.game_panel.AdventureModePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.PVZ.api.base.constant.SystemConstant.*;

/**
 * @ClassName PlantCard
 * @Description 植物卡片，放植物卡，响应事件
 * @Author chenqi
 * @Date 2018/11/14 11:11
 * @Version 1.0
 **/
public class PlantCard extends JPanel implements Runnable, MouseListener, MouseMotionListener {

    private Image plantImage;
    private Plant plant;
    private Point point;
    private JLabel plantLabel;
    private ImageIcon planticon;
    private int posX;
    private int posY;
    private int cd;
    private float grayRY;
    private boolean isFrozen;
    private int costSunPoint;
    private AdventureModePanel gamePanel;
    private boolean clickable;
    private int iconHeight;
    private int iconWeight;
    private Thread cardThread;
    private int status;

    public PlantCard(Plant aPlant, AdventureModePanel gamePanel) {
        this.plant = aPlant;
        this.grayRY = (aPlant instanceof SunFlower) ? 70 : 0;
        this.isFrozen = (aPlant instanceof SunFlower) ? false : true;
        this.clickable = (aPlant instanceof SunFlower) ? true : false;
        this.cd = aPlant.getCd();
        this.costSunPoint = plant.getSunPointCost();
        this.gamePanel = gamePanel;
        this.setSize(PLANT_CARD_WIDTH, PLANT_CARD_HEIGHT);
        this.setOpaque(false);
        this.plant = aPlant;
        this.plantImage = plant.getCardImage();
        this.planticon = new ImageIcon(plant.getActiveImage());
        this.iconHeight = planticon.getIconHeight();
        this.iconWeight = planticon.getIconWidth();
        this.plantLabel = new JLabel();

        this.plantLabel.setIcon(planticon);
        this.plantLabel.setBounds(this.getBounds().x + iconWeight / 3, -iconHeight * 2, iconWeight, iconHeight);
        gamePanel.add(this.plantLabel);
        this.plantLabel.setVisible(false);

        this.status = RUNNING;

        //注册事件源
        this.addMouseListener(this);
        this.addMouseMotionListener(this);


        cardThread = new Thread(this);
        cardThread.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(plantImage, 0, 0, this.getWidth(), this.getHeight(), null);
        g.setColor(new Color(50, 50, 50, 125));
        g.fillRect(0, (int) grayRY, this.getWidth(), this.getHeight());
    }




    public Thread getCardThread() {
        return cardThread;
    }

    @Override
    public void run() {
        int count = 0;
        while (status != STOP) {
            //判断线程是否挂起
            if (status != SUSPEND) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }

                if (isFrozen && grayRY < PLANT_CARD_HEIGHT) {
                    grayRY += 70 * (float) (0.05 / cd);
                } else if (gamePanel.getSunPoint() >= costSunPoint) {
                    isFrozen = false;
                    grayRY = PLANT_CARD_HEIGHT;
                    this.clickable = true;
                } else {
                    isFrozen = false;
                    grayRY = 0;
                    this.clickable = false;
                }
                repaint();
            } else {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException event) {
                    }
                }
            }
        }


    }



    public synchronized void cardResume() {
        status = RUNNING;
        notifyAll();
    }


    public void cardStop() {
        status = STOP;
    }



    public void cardSuspend() {
        status = SUSPEND;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!isFrozen && clickable) {
            this.plantLabel.setVisible(false);
            System.out.println("[DEBUG]Released Position@[ posX_Range:" + (e.getX() + "~" + this.getLocation().x) + "  posY_Range:" + ((e.getX() + this.getLocation().x) + 75) + "~" + (e.getY() + 5)+" ]");
            int row = (e.getY() + 5 - 85) / 90 + 1;
            int column = (e.getX() + this.getLocation().x + 75 - 125) / 80 + 1;
            String type = this.plant.getClass().getName();
            System.out.println("[DEBUG]Realeased Postion@[ ROW: " + row + " COLUMN:" + column+" ]");

            if (gamePanel.getAdventureController().generatePlantByName(type, row, column)) {
                isFrozen = true;
                this.grayRY = 0;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!isFrozen && clickable) {
            System.out.println("[DEBUG]Drag Position@[ posX:" + e.getXOnScreen() + " posY:" + e.getYOnScreen()+" ]");
            plantLabel.setVisible(true);
            plantLabel.setLocation(e.getX() + this.getLocation().x + iconWeight * 1 / 3, e.getY() - iconHeight / 2);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isFrozen && clickable) {
            plantLabel.setVisible(true);
            plantLabel.setLocation(e.getX() + this.getLocation().x + iconWeight * 1 / 3, e.getY() - iconHeight / 2);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
