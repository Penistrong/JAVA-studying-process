package com.PVZ.view.handbook_panel;

import com.PVZ.model.others.ZombieShowInHandbook;
import com.PVZ.model.zombies.*;

import javax.swing.*;

import java.awt.*;

/**
 * @ClassName ZombieShowPanel
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/26 19:01
 * @Version 1.0
 **/
public class ZombieShowPanel extends ShowPanel {

    public ZombieShowPanel() {

        super();

        this.showLabel.setLocation(600, 110);

        this.addToList(new ZombieShowInHandbook(new NormalZombie(), this), 0, 0);

        this.addToList(new ZombieShowInHandbook(new NormalZombie(), this), 0, 0);
        this.addToList(new ZombieShowInHandbook(new FlagZombie(), this), 1, 0);
        this.addToList(new ZombieShowInHandbook(new BucketHeadZombie(), this), 2, 0);
        this.addToList(new ZombieShowInHandbook(new NewspaperZombie(), this), 3, 0);
        this.addToList(new ZombieShowInHandbook(new ConeHeadZombie(), this), 4, 0);


        NormalZombie p = new NormalZombie();
        paintDesShow(p.getDescirptionImage(), p.getActiveImage());
        repaint();

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(desImage, 540, 300, 310, 300, null);
    }

    @Override
    public void addToList(JPanel show, int index, int line) {
        show.setBounds(66 * index + 5, 76 * line + 13, 66, 76);
        this.listPanel.add(show);
        this.showInHandBookList.add(show);

    }
}
