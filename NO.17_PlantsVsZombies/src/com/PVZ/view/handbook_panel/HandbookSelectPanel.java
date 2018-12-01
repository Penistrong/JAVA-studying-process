package com.PVZ.view.handbook_panel;

import com.PVZ.controller.Main;
import com.PVZ.view.welcome_panel.StartPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.PVZ.api.base.constant.SystemConstant.*;

/**
 * @ClassName HandbookSelectPanel
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/26 18:26
 * @Version 1.0
 **/
public class HandbookSelectPanel extends JPanel  implements MouseListener {
    private  StartPanel startPanel;
    private HandbookPanel plantHandbookPanel;
    private HandbookPanel zombieHandbookPanel;

    private JLabel plantHandbookButton;
    private JLabel zombieHandbookButton;
    private JLabel homeButton;


    private ImageIcon homeImage=new ImageIcon(INTERFACE_PATH+"illustrated_handbook/Almanac_IndexButton.png");
    private ImageIcon homeImageHighlight=new ImageIcon(INTERFACE_PATH+"illustrated_handbook/Almanac_IndexButton_Hightlight.png");

    private ImageIcon plantImage=new ImageIcon(INTERFACE_PATH+"illustrated_handbook/PlantsButton.png");
    private ImageIcon zombieImage=new ImageIcon(INTERFACE_PATH+"illustrated_handbook/ZombiesButton.png");

    private Image bg=new ImageIcon(INTERFACE_PATH+"illustrated_handbook/Almanac_IndexBack.png").getImage();

    public HandbookSelectPanel(StartPanel startPanel){
        this.startPanel=startPanel;

        this.setLayout(null);
        this.setSize(900,600);

        this.plantHandbookPanel=new HandbookPanel(PLANT_HANDBOOK_DAYMODE_BG,DES_PLANT_CARD,new PlantShowPanel(),this);
        this.zombieHandbookPanel=new HandbookPanel(ZOMBIE_HANDBOOK_DAYMODE_BG,DES_ZOMBIE_CARD,new ZombieShowPanel(),this);

        this.plantHandbookButton=new JLabel();
        this.plantHandbookButton.setOpaque(false);
        this.plantHandbookButton.setIcon(plantImage);
        this.plantHandbookButton.setBounds(40, 150 , 400, 270);

        this.zombieHandbookButton=new JLabel();
        this.zombieHandbookButton.setOpaque(false);
        this.zombieHandbookButton.setIcon(zombieImage);
        this.zombieHandbookButton.setBounds(460, 150 , 520, 270);



        this.homeButton=new JLabel();
        this.homeButton.setIcon(homeImage);
        this.homeButton.setBounds(30,520,homeImage.getIconWidth(),25);



        this.setBounds(0,0,900,600);
        this.add(homeButton);
        this.add(zombieHandbookButton);
        this.add(plantHandbookButton);


        homeButton.addMouseListener(this);
        plantHandbookButton.addMouseListener(this);
        zombieHandbookButton.addMouseListener(this);

    }


    public StartPanel getStartPanel() {
        return startPanel;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, 890, 565, null);


    }

    public static void enterResetIcon(JLabel aLabel,ImageIcon aImageIcon){
        aLabel.setIcon(aImageIcon);
        aLabel.setBounds(aLabel.getX(), aLabel.getY(), aLabel.getWidth()+1, aLabel.getHeight()+1);
    }

    public static void exitResetIcon(JLabel aLabel,ImageIcon aImageIcon){
        aLabel.setIcon(aImageIcon);
        aLabel.setBounds(aLabel.getX(), aLabel.getY(), aLabel.getWidth()-1, aLabel.getHeight()-1);
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==plantHandbookButton){
            exitResetIcon(plantHandbookButton, plantImage);
        }
        if(e.getSource()==zombieHandbookButton){
            exitResetIcon(zombieHandbookButton, zombieImage);
        }
        if(e.getSource()==homeButton){
            exitResetIcon(homeButton, homeImage);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==plantHandbookButton){
            enterResetIcon(plantHandbookButton,plantImage );
        }
        if(e.getSource()==zombieHandbookButton){
            enterResetIcon(zombieHandbookButton ,zombieImage );
        }
        if (e.getSource()==homeButton){
            enterResetIcon(homeButton, homeImageHighlight);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==plantHandbookButton){
            Main.tst.setContentPane(plantHandbookPanel);
        }
        if(e.getSource()==zombieHandbookButton){
            Main.tst.setContentPane(zombieHandbookPanel);
        }

        if(e.getSource()==homeButton){
            Main.tst.setContentPane(startPanel);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
