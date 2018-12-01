package com.PVZ.view.handbook_panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @ClassName AdventureModePanel
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/22 8:50
 * @Version 1.0
 **/

public class ShowPanel extends JLayeredPane {

    protected JPanel listPanel;
    protected JPanel showPanel;
    protected JPanel describePanel;
    protected Image desImage;
    protected JLabel showLabel;
    protected ArrayList<JPanel> showInHandBookList;


    public ShowPanel() {
        this.setSize(900, 600);
        this.setLayout(null);

        this.setOpaque(false);
        this.showLabel = new JLabel();
        this.showLabel.setBounds(630, 110, 175, 135);

        this.describePanel = new JPanel();
        //test
        this.describePanel.setOpaque(false);
        this.describePanel.setLayout(null);
        this.describePanel.setBounds(540, 300, 310, 400);

        this.listPanel = new JPanel();
        this.listPanel.setLayout(null);
        this.listPanel.setOpaque(false);
        this.listPanel.setBounds(30, 95, 470, 500);

        this.showPanel = new JPanel(new BorderLayout());
        //test
        this.showPanel.setOpaque(true);
        this.showPanel.setOpaque(false);
        this.showPanel.setBounds(575, 110, 225, 135);

        //初始化
        this.showInHandBookList = new ArrayList<>();

        this.add(listPanel);
        this.add(describePanel);
        this.add(showLabel);

    }



    public void addToList(JPanel show, int index, int line) {
        show.setBounds(66 * index, 76 * line, 66, 76);
        this.listPanel.add(show);
        this.showInHandBookList.add(show);

    }

    public void addPlane(JPanel Img, int index, int line) {
        Img.setBounds(80 * index, 97 * line, 60, 70);
        this.showPanel.add(Img);
    }

    public void addDescription(JLabel desc) {
        desc.setBounds(0, 0, 310, 440);
        desc.setVisible(true);
        this.describePanel.add(desc);
    }

    public void paintDesShow(Image desImage, Image showImg) {
        this.desImage = desImage;
        this.showLabel.setIcon(new ImageIcon(showImg));
        repaint();
    }


}
