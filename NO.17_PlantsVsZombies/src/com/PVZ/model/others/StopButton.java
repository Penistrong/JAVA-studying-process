package com.PVZ.model.others;

import com.PVZ.view.game_panel.AdventureModePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.PVZ.api.base.constant.SystemConstant.INTERFACE_PATH;

/**
 * @ClassName StopButton
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/10 16:56
 * @Version 1.0
 **/
public class StopButton extends JLabel implements MouseListener {

    private static ImageIcon normalButton = new ImageIcon(INTERFACE_PATH + "/game_interface/stop.png");
    private static ImageIcon clickButton = new ImageIcon(INTERFACE_PATH + "/game_interface/stop_highlight.png");

    private AdventureModePanel gamePanel;

    public StopButton(AdventureModePanel gamePanel) {
        normalButton.setImage(normalButton.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        clickButton.setImage(normalButton.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        this.gamePanel = gamePanel;
        this.setIcon(normalButton);
        this.setBounds(800, 10, 40, 40);
        this.gamePanel.add(this);
        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

            this.setIcon(clickButton);
            gamePanel.gamePanelSuspend();
            for (PlantCard pc : gamePanel.getPlantCardList()) {
                pc.cardSuspend();
            }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
