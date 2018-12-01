package com.PVZ.model.others;

import com.PVZ.view.game_panel.AdventureModePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static com.PVZ.api.base.constant.SystemConstant.INTERFACE_PATH;

/**
 * @ClassName Shovel
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/24 0:48
 * @Version 1.0
 **/
public class Shovel extends JPanel implements MouseMotionListener, MouseListener {
    private static ImageIcon shovelImage=new ImageIcon(INTERFACE_PATH+"Shovel.png");
    private AdventureModePanel gamePanel;
    private JLabel shovelLabel;
    public Shovel(AdventureModePanel gamePanel){
        this.shovelLabel=new JLabel();
        shovelImage.setImage(shovelImage.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.shovelLabel.setIcon(shovelImage);
        this.gamePanel=gamePanel;
        this.setOpaque(false);
        this.setBounds(505 , 5,50 ,50);
        shovelLabel.setBounds(505,5,50,50);
        gamePanel.add(this.shovelLabel);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        shovelLabel.setLocation(e.getX()+505, e.getY()+5);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        shovelLabel.setLocation(e.getX()+505, e.getY()+5);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int row = (e.getY() +5 - 85) /90 + 1;
        int column = (e.getX()+this.getLocation().x+75 - 125)/80;
        System.out.println(row+" | "+column);
        if(gamePanel.getAdventureController().shovePlantByRC(row, column)) {
        	
        }
        shovelLabel.setLocation(505,5);

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }


    @Override
    public void mouseExited(MouseEvent e) {

    }

}
