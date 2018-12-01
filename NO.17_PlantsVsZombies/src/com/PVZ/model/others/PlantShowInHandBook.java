package com.PVZ.model.others;

import com.PVZ.model.plants.Plant;
import com.PVZ.view.handbook_panel.ShowPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.PVZ.api.base.constant.SystemConstant.PLANT_CARD_HEIGHT;
import static com.PVZ.api.base.constant.SystemConstant.PLANT_CARD_WIDTH;

/**
 * @ClassName PlantCard
 * @Description 植物卡片，放植物卡，响应事件
 * @Author chenqi
 * @Date 2018/11/14 11:11
 * @Version 1.0
 **/

public class PlantShowInHandBook extends JPanel implements MouseListener {
    private Image plantImage;
    private Plant plant;
    private Point point;
    private JLabel plantDescriptionLabel;
    private ImageIcon planticon;
    private int posX;
    private int posY;
    private ShowPanel showPanel;
    private ImageIcon descriptionImage;

    public PlantShowInHandBook(Plant aPlant, ShowPanel showPanel) {
        this.plant = aPlant;
        this.showPanel=showPanel;
        this.setSize(PLANT_CARD_WIDTH, PLANT_CARD_HEIGHT);
        this.setOpaque(false);
        this.plantImage = plant.getCardImage();
        this.planticon = new ImageIcon(plant.getActiveImage());
        this.plantDescriptionLabel = new JLabel();
        this.descriptionImage=new ImageIcon(plant.getDescirptionImage());
        this.plantDescriptionLabel.setIcon(descriptionImage);

        this.addMouseListener(this);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(plantImage, 0, 0, this.getWidth(), this.getHeight(), null);
        g.setColor(new Color(50, 50, 50, 125));
    }



    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("i am here");
        //plantDescriptionLabel.setVisible(true);
        //showPanel.addDescription(plantDescriptionLabel);
        showPanel.paintDesShow(plant.getDescirptionImage(), plant.getActiveImage());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
