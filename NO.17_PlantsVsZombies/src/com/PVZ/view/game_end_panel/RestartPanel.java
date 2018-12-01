package com.PVZ.view.game_end_panel;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.controller.Main;
import com.PVZ.view.game_panel.AdventureModePanel;
import com.PVZ.view.welcome_panel.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @ClassName 游戏结束界面，游戏失败，提示用户结束游戏还是重新开始
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/10 11:51
 * @Version 1.0
 **/
public class RestartPanel extends JPanel {
    private  Image bgImage = new ImageIcon(SystemConstant.INTERFACE_PATH+"game_interface/fail.png").getImage();
    private Exit_button Exit_button;
    private Restart_button Restart_button;
    AdventureModePanel adventureModePanel;

    public RestartPanel(StartPanel startPanel,AdventureModePanel adventureModePanel){
        this.adventureModePanel=adventureModePanel;
        Exit_button = new Exit_button(startPanel);
        Restart_button = new Restart_button(startPanel,this.adventureModePanel);
        this.setSize(900,600);
        this.setLayout(null);
        Restart_button.setBounds(117, 480, 216, 38);
        Exit_button.setBounds(567, 480, 216, 38);
        this.add(Exit_button);
        this.add(Restart_button);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0,0, SystemConstant.WIDTH_S,SystemConstant.HEIGHT_S,null );
        //g.drawImage(stopImage, , , )
    }
}

class Exit_button extends JLabel implements MouseListener {
    private static ImageIcon normalButton = new ImageIcon(SystemConstant.INTERFACE_PATH+"/pause_panel/exit-1.png");
    private static ImageIcon clickButton = new ImageIcon(SystemConstant.INTERFACE_PATH+"/pause_panel/exit-2.png");
    private StartPanel startPanel;

    public Exit_button(StartPanel startPanel) {
        this.startPanel = startPanel;
        normalButton.setImage(normalButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));
        clickButton.setImage(clickButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));

        this.setIcon(normalButton);
        this.setBounds(117, 280, 216, 38);
        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Main.tst.setContentPane(startPanel);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setIcon(clickButton);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setIcon(clickButton);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setIcon(normalButton);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setIcon(normalButton);
    }
}