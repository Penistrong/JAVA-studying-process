package com.PVZ.view.game_end_panel;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.controller.Main;
import com.PVZ.view.game_panel.AdventureModePanel;
import com.PVZ.view.game_panel.BackgroundPanel;
import com.PVZ.view.welcome_panel.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.PVZ.api.base.constant.AdventureConstant.MISSIONS_PARAMS;
import static com.PVZ.api.base.constant.AdventureConstant.PATH_DAYMODE_BG;

/**
 * @ClassName
 * @Description 游戏结束界面，游戏胜利，提示用户结束游戏还是继续下一关
 * @Author chenqi
 * @Date 2018/11/10 11:52nn
 * @Version 1.0
 **/
public class ContinuePanel extends JPanel {

    private Image bgImage=new ImageIcon(SystemConstant.INTERFACE_PATH+"game_interface/pass.png").getImage();
    private Exit_button Exit_button;
    private ContinueButton Continue_button;
    private Restart_button Restart_button;
    private AdventureModePanel adventureModePanel;
    public ContinuePanel(StartPanel startPanel,AdventureModePanel adventureModePanel){
        this.adventureModePanel=adventureModePanel;
        Exit_button = new Exit_button(startPanel);
        Restart_button = new Restart_button(startPanel,this.adventureModePanel);
        Continue_button = new ContinueButton(startPanel,this.adventureModePanel);
        this.setSize(900,600);
        this.setLayout(null);
        Continue_button.setBounds(117, 480, 216, 38);
        Restart_button.setBounds(342,400, 216, 38);
        Exit_button.setBounds(567, 480, 216, 38);
        this.add(Exit_button);
        this.add(Restart_button);
        this.add(Continue_button);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0,0, SystemConstant.WIDTH_S,SystemConstant.HEIGHT_S,null );
    }
}

class Restart_button extends JLabel implements MouseListener {
    private static ImageIcon normalButton = new ImageIcon(SystemConstant.INTERFACE_PATH+"/pause_panel/restart-1.png");
    private static ImageIcon clickButton = new ImageIcon(SystemConstant.INTERFACE_PATH+"/pause_panel/restart-2.png");
    private StartPanel startPanel;
    private AdventureModePanel adventureModePanel;


    public Restart_button(StartPanel startPanel,AdventureModePanel adventureModePanel) {
        this.startPanel = startPanel;
        this.adventureModePanel=adventureModePanel;
        normalButton.setImage(normalButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));
        clickButton.setImage(clickButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));
        this.setIcon(normalButton);
        this.setBounds(117, 280, 216, 38);
        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Main.tst.setContentPane(new BackgroundPanel(MISSIONS_PARAMS.get(adventureModePanel.getCurMissonIndex()).get("BG_PATH"),new AdventureModePanel(startPanel,adventureModePanel.getAdventrueMissionList(),adventureModePanel.getCurMissonIndex())));
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

class ContinueButton extends JLabel implements MouseListener {
    private static ImageIcon normalButton = new ImageIcon(SystemConstant.INTERFACE_PATH+"/pause_panel/continue-1.png");
    private static ImageIcon clickButton = new ImageIcon(SystemConstant.INTERFACE_PATH+"/pause_panel/continue-2.png");
    private StartPanel startPanel;
    private AdventureModePanel adventureModePanel;


    public ContinueButton(StartPanel startPanel,AdventureModePanel adventureModePanel) {
        this.adventureModePanel=adventureModePanel;
        this.startPanel = startPanel;
        normalButton.setImage(normalButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));
        clickButton.setImage(clickButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));

        this.setIcon(normalButton);
        this.setBounds(117, 280, 216, 38);
        this.addMouseListener(this);

    }




    @Override
    public void mouseClicked(MouseEvent e) {
        Main.tst.setContentPane(new BackgroundPanel(MISSIONS_PARAMS.get(adventureModePanel.getNextMissionIndex()).get("BG_PATH"),new AdventureModePanel(startPanel,adventureModePanel.getAdventrueMissionList(),adventureModePanel.getNextMissionIndex())));

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