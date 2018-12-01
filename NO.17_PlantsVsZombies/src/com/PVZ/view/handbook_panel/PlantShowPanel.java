package com.PVZ.view.handbook_panel;

import com.PVZ.model.others.PlantShowInHandBook;
import com.PVZ.model.plants.*;

import java.awt.*;

/**
 * @ClassName PlantShowPanel
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/26 19:00
 * @Version 1.0
 **/
public class PlantShowPanel extends ShowPanel{

    public PlantShowPanel(){
        super();
        this.addToList(new PlantShowInHandBook(new SunFlower(),this), 0,0);
        this.addToList(new PlantShowInHandBook(new PeaShooter(),this), 1,0);
        this.addToList(new PlantShowInHandBook(new WallNut(),this), 2,0);
        this.addToList(new PlantShowInHandBook(new CherryBomb(),this), 3,0);
        this.addToList(new PlantShowInHandBook(new Chomper(), this),4,0);
        this.addToList(new PlantShowInHandBook(new SunShroom(), this), 5,0 );
        this.addToList(new PlantShowInHandBook(new TorchWood(), this), 6,0 );
        this.addToList(new PlantShowInHandBook(new GatlingPeaShooter(), this), 0,1 );

        PeaShooter p=new PeaShooter();
        paintDesShow(p.getDescirptionImage(),p.getActiveImage() );
        repaint();
    }


    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(desImage, 540, 265, 310, 300, null);
    }
}
