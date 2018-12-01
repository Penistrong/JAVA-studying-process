package com.PVZ.model.others;


import com.PVZ.model.zombies.Zombie;
import com.PVZ.view.handbook_panel.ShowPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.PVZ.api.base.constant.SystemConstant.PLANT_CARD_HEIGHT;
import static com.PVZ.api.base.constant.SystemConstant.PLANT_CARD_WIDTH;

/**
 * @ClassName ZombieShowInHandbook
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/26 19:09
 * @Version 1.0
 **/
public class ZombieShowInHandbook extends JPanel implements MouseListener {
    private Image zombieImage;
    private Zombie zombie;
    private Point point;
    private JLabel zombieDescriptionLabel;
    private ImageIcon zombieicon;
    private int posX;
    private int posY;
    private ShowPanel showPanel;
    private ImageIcon descriptionImage;

    public ZombieShowInHandbook(Zombie aZombie, ShowPanel showPanel) {
        this.zombie = aZombie;
        this.showPanel=showPanel;
        this.setSize(PLANT_CARD_WIDTH, PLANT_CARD_HEIGHT);
        this.setOpaque(false);
        this.zombieImage = zombie.getCardImage();
        this.zombieicon = new ImageIcon(zombie.getActiveImage());
        this.zombieDescriptionLabel = new JLabel();
        this.descriptionImage=new ImageIcon(zombie.getDescirptionImage());
        this.zombieDescriptionLabel.setIcon(descriptionImage);

        this.addMouseListener(this);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(zombieImage, 0, 0, this.getWidth(), this.getHeight(), null);
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
        //zombieDescriptionLabel.setVisible(true);
        //showPanel.addDescription(zombieDescriptionLabel);
        showPanel.paintDesShow(zombie.getDescirptionImage(), zombie.getActiveImage());
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
