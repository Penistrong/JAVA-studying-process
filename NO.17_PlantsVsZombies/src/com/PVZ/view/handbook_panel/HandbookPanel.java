package com.PVZ.view.handbook_panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.controller.Main;

/**
 * @ClassName HandbookPanel
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/25 8:24
 * @Version 1.0
 * daymode:
 **/
public class HandbookPanel extends JPanel implements MouseListener {
    private  Image bgImage;

    private Image cardBoard;
    private Image almanacGround =new ImageIcon(SystemConstant.INTERFACE_PATH+"illustrated_handbook/Almanac_GroundDay.jpg").getImage();


    private ImageIcon backButton =new ImageIcon(SystemConstant.INTERFACE_PATH+"illustrated_handbook/SeedChooser_Button.png");
    private ImageIcon backButtonHightlight =new ImageIcon(SystemConstant.INTERFACE_PATH+"illustrated_handbook/SeedChooser_Button_Hightlight.png");
    private JLabel back;

    private ImageIcon homeButtomHighlight =new ImageIcon(SystemConstant.INTERFACE_PATH+"illustrated_handbook/Almanac_CloseButtonHighlight.png");
    private ImageIcon homeButtom=new ImageIcon(SystemConstant.INTERFACE_PATH+"illustrated_handbook/Almanac_CloseButton.png");
    private JLabel home;

    private HandbookSelectPanel handbookSelectPanel;


    public HandbookPanel(String pathToBgImage,String cardBoard, JLayeredPane showPanel,HandbookSelectPanel handbookSelectPanel){
        this.handbookSelectPanel=handbookSelectPanel;
        this.cardBoard=new ImageIcon(cardBoard).getImage();
        this.bgImage= new ImageIcon(pathToBgImage).getImage();
        this.setSize(900,600);
        this.setLayout(null);
        showPanel.setBounds(0, 0,900, 590);

        this.home=new JLabel();
        this.home.setIcon(homeButtom);
        this.home.setBounds(10 , 10,200 ,50);
        this.back=new JLabel();
        this.back.setIcon(backButton);
        this.back.setBounds(760,10,200,50);
        this.add(home);
        this.add(back);
        this.add(showPanel);

        home.addMouseListener(this);
        back.addMouseListener(this);
    }


    public HandbookPanel(String pathToBgImage){
        this.bgImage= new ImageIcon(pathToBgImage).getImage();
        this.setSize(900,600);
        this.setLayout(null);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0,0, SystemConstant.WIDTH_S,SystemConstant.HEIGHT_S,null );
        g.drawImage(cardBoard, 520, 75,350,488, null);

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
        if(e.getSource()==home){
            exitResetIcon(home, homeButtom);
        }
        if(e.getSource()==back){
            exitResetIcon(back, backButton);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==home){
            enterResetIcon(home, homeButtomHighlight);
        }
        if(e.getSource()==back){
            enterResetIcon(back, backButtonHightlight);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==home){
            Main.tst.setContentPane(handbookSelectPanel.getStartPanel());
        }
        if(e.getSource()==back){
            Main.tst.setContentPane(handbookSelectPanel);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
