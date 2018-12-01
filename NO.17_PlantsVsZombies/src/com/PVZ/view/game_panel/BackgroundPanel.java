package com.PVZ.view.game_panel;

import javax.swing.*;
import java.awt.*;

import com.PVZ.api.base.constant.SystemConstant;

/**
 * @ClassName HandbookPanel
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/10 12:24
 * @Version 1.0
 * daymode: start_x :125,start_y :85 gird_w:80,grid_h:90
 **/
public class BackgroundPanel extends JPanel  {
    private  Image bgImage;

    private Image cardBoard=new ImageIcon(SystemConstant.INTERFACE_PATH+"game_interface/adventure_mode/cardboard.png").getImage();
    private Image shovelBoard =new ImageIcon(SystemConstant.INTERFACE_PATH+"game_interface/adventure_mode/ShovelBack.png").getImage();
    //private Image stopImage=new ImageIcon().getImage();



    public BackgroundPanel(String pathToBgImage,JLayeredPane gamePanel){
        this.bgImage= new ImageIcon(pathToBgImage).getImage();
        this.setSize(900,600);
        this.setLayout(null);
        gamePanel.setBounds(0, 0,900, 600);
        this.add(gamePanel);
    }

    public BackgroundPanel(String pathToBgImage){
        this.bgImage= new ImageIcon(pathToBgImage).getImage();
        this.setSize(900,600);
        this.setLayout(null);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0,0, SystemConstant.WIDTH_S,SystemConstant.HEIGHT_S,null );
        g.drawImage(cardBoard, 0, 0,500,80, null);
        g.drawImage(shovelBoard,500 , 0,60 ,60,null);
        //g.drawImage(stopImage, , , )
    }
}
