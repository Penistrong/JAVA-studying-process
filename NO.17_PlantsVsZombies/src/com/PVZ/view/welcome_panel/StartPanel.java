package com.PVZ.view.welcome_panel;


import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.controller.Main;
import com.PVZ.view.game_panel.AdventureModePanel;
import com.PVZ.view.game_panel.BackgroundPanel;
import com.PVZ.view.handbook_panel.HandbookSelectPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.PVZ.api.base.constant.AdventureConstant.MISSIONS_PARAMS;
import static com.PVZ.api.base.constant.AdventureConstant.PATH_NIGHT_BG;
import static com.PVZ.api.base.constant.SystemConstant.MISSIONSLIST;

/**
 * @ClassName StartPanel
 * @Description 游戏选择界面
 * @Author chenqi
 * @Date 2018/11/8 17:40
 * @Version 1.0
 **/
public class StartPanel extends JPanel  implements MouseListener {
    public static ImageIcon background;
    public static ImageIcon selectorScreenWoodSignUsername;
    public static ImageIcon selectorScreenWoodSignHint;
    public static ImageIcon selectorScreenStartAdventureButton;
    public static ImageIcon selectorScreenStartAdventureButtonHighlight;
    public static ImageIcon selectorScreenVasebreakerButton;
    public static ImageIcon selectorScreenVasebreakerButtonHighlight;
    public static ImageIcon selectorScreenMiniGameButton;
    public static ImageIcon selectorScreenMiniGameButtonHighlight;
    public static ImageIcon selectorScreenOptionButton;
    public static ImageIcon selectorScreenHelpButton;
    public static ImageIcon selectorScreenExitButton;
    public static ImageIcon handbookButtom;
    public static ImageIcon handbookButtomHighlight;

    private HandbookSelectPanel handbookSelectPanel;
    private AdventureModePanel adventureModePanel;



    //按钮
    //冒险模式游戏按钮
    public JLabel startAdventure = new JLabel();
    //解密模式游戏按钮
    public JLabel vasebreaker= new JLabel();
    //迷你游戏按钮
    public JLabel minigame = new JLabel();
    //选项按钮
    public JLabel option=new JLabel();
    //帮助按钮
    public  JLabel help=new JLabel();
    //退出按钮
    public JLabel exit=new JLabel();
    //用户信息Label
    public JLabel userWood=new JLabel();
    //提示Buttom
    public JLabel userInfoHint=new JLabel();
    //图鉴Button
    public JLabel handbook=new JLabel();

    static {


            //加载背景图
            background = new ImageIcon(SystemConstant.INTERFACE_PATH + "select_interface/Background.png");
            //加载挂图
            selectorScreenWoodSignUsername = new ImageIcon(SystemConstant.INTERFACE_PATH + "select_interface/SelectorScreen_WoodSign_Username.png");
            selectorScreenWoodSignHint = new ImageIcon(SystemConstant.INTERFACE_PATH + "select_interface/SelectorScreen_WoodSign_Hint.png");

            //加载游戏模式图
            //---冒险模式
            selectorScreenStartAdventureButton = new ImageIcon(SystemConstant.INTERFACE_PATH + "select_interface/SelectorScreen_StartAdventure.png");
            selectorScreenStartAdventureButtonHighlight=new ImageIcon(SystemConstant.INTERFACE_PATH + "select_interface/SelectorScreen_StartAdventure_Highlight.png");
            //---解密模式
            selectorScreenVasebreakerButton = new ImageIcon(SystemConstant.INTERFACE_PATH + "select_interface/SelectorScreen_Vasebreaker_Button.png");
            selectorScreenVasebreakerButtonHighlight=new ImageIcon(SystemConstant.INTERFACE_PATH+"select_interface/SelectorScreen_Vasebreaker_Button_Highlight.png");
            //---小游戏模式
            selectorScreenMiniGameButton = new ImageIcon(SystemConstant.INTERFACE_PATH + "select_interface/SelectorScreen_MiniGame_Button.png");
            selectorScreenMiniGameButtonHighlight=new ImageIcon(SystemConstant.INTERFACE_PATH+"select_interface/SelectorScreen_Minigame_button_Highlight.png");
            //---选项按钮
            selectorScreenOptionButton=new ImageIcon(SystemConstant.INTERFACE_PATH+"select_interface/SelectorScreen_Options1.png");
            //---帮助按钮
            selectorScreenHelpButton=new ImageIcon(SystemConstant.INTERFACE_PATH+"select_interface/SelectorScreen_Help1.png");
            //---退出按钮
            selectorScreenExitButton=new ImageIcon(SystemConstant.INTERFACE_PATH+"select_interface/SelectorScreen_Quit1.png");
            //--图鉴按钮
            handbookButtom=new ImageIcon(SystemConstant.INTERFACE_PATH+"select_interface/SelectorScreen_Almanac.png");
            handbookButtomHighlight=new ImageIcon(SystemConstant.INTERFACE_PATH+"select_interface/SelectorScreen_AlmanacHighlight.png");


        }
    public StartPanel() {

        //把默认布局去除
        this.setLayout(null);
        //startAdventure=new IButton(SystemConstant.INTERFACE_PATH + "select_interface/selectorScreen_StartAdventure_Highlight.png",SystemConstant.INTERFACE_PATH + "select_interface/selectorScreen_StartAdventure.png" ,SystemConstant.INTERFACE_PATH + "select_interface/selectorScreen_StartAdventure_Highlight.png");

        //木框
        userWood.setIcon(selectorScreenWoodSignUsername);
        userWood.setBounds(0, 0,selectorScreenWoodSignUsername.getIconWidth(),selectorScreenWoodSignUsername.getIconHeight());
        userInfoHint.setIcon(selectorScreenWoodSignHint);
        userInfoHint.setBounds( 0, 140,selectorScreenWoodSignHint.getIconWidth(),selectorScreenWoodSignHint.getIconHeight());
        //游戏模式
        startAdventure.setIcon(selectorScreenStartAdventureButton);
        startAdventure.setBounds(483, 80,selectorScreenStartAdventureButton.getIconWidth(),selectorScreenStartAdventureButton.getIconHeight());
        vasebreaker.setIcon(selectorScreenVasebreakerButton);
        vasebreaker.setBounds( 470, 190,selectorScreenVasebreakerButton.getIconWidth(),selectorScreenVasebreakerButton.getIconHeight());
        minigame.setIcon(selectorScreenMiniGameButton);
        minigame.setBounds(453,288, selectorScreenMiniGameButton.getIconWidth(),selectorScreenMiniGameButton.getIconHeight()-10);


        //退出 帮助 选项 图鉴 按钮
        exit.setIcon(selectorScreenExitButton);
        exit.setBounds(813,510, selectorScreenExitButton.getIconWidth(), selectorScreenExitButton.getIconHeight());
        help.setIcon(selectorScreenHelpButton);
        help.setBounds(730,513,selectorScreenHelpButton.getIconWidth(),selectorScreenHelpButton.getIconHeight());
        option.setIcon(selectorScreenOptionButton);
        option.setBounds(650,495,selectorScreenOptionButton.getIconWidth(),selectorScreenOptionButton.getIconHeight());
        handbook.setIcon(handbookButtom);
        handbook.setBounds(500, 450, handbookButtom.getIconWidth(), handbookButtom.getIconHeight());

        //注册事件源
        userWood.addMouseListener(this);
        userInfoHint.addMouseListener(this);
        startAdventure.addMouseListener(this);
        vasebreaker.addMouseListener(this);
        minigame.addMouseListener(this);
        exit.addMouseListener(this);
        help.addMouseListener(this);
        option.addMouseListener(this);
        handbook.addMouseListener(this);

        //把组件添加到panle
        this.add(userWood);
        this.add(userInfoHint);
        this.add(startAdventure);
        this.add(vasebreaker);
        this.add(minigame);
        this.add(exit);
        this.add(help);
        this.add(option);
        this.add(handbook);


        //初始化各种panel
        this.handbookSelectPanel=new HandbookSelectPanel(this);

    }

    public static void enterResetIcon(JLabel aLabel,ImageIcon aImageIcon){
        aLabel.setIcon(aImageIcon);
        aLabel.setBounds(aLabel.getX(), aLabel.getY(), aLabel.getWidth()+1, aLabel.getHeight()+1);
    }

    public static void exitResetIcon(JLabel aLabel,ImageIcon aImageIcon){
        aLabel.setIcon(aImageIcon);
        aLabel.setBounds(aLabel.getX(), aLabel.getY(), aLabel.getWidth()-1, aLabel.getHeight()-1);
       // aLabel.setSize(aLabel.getWidth()+1,aLabel.getHeight()+1);
    }



    /**
     * 布置背景
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //背景图
        setLayout(null);
        g.drawImage(background.getImage(), 0, 0,null);
        this.repaint();
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==startAdventure){
            exitResetIcon(startAdventure,selectorScreenStartAdventureButton);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if(e.getSource()==vasebreaker){
            exitResetIcon(vasebreaker,selectorScreenVasebreakerButton);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if(e.getSource()==minigame){
            exitResetIcon(minigame,selectorScreenMiniGameButton);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if(e.getSource()==exit){
            exitResetIcon(exit,selectorScreenExitButton);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if(e.getSource()==option){
            exitResetIcon(option,selectorScreenOptionButton);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if(e.getSource()==help){
            exitResetIcon(help,selectorScreenHelpButton);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if(e.getSource()==handbook){
            exitResetIcon(handbook, handbookButtom);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==startAdventure){
            enterResetIcon(startAdventure,selectorScreenStartAdventureButtonHighlight);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==vasebreaker){
            enterResetIcon(vasebreaker,selectorScreenVasebreakerButtonHighlight);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==minigame){
            enterResetIcon(minigame,selectorScreenMiniGameButtonHighlight);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==exit){
            enterResetIcon(exit,selectorScreenExitButton);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        }
        if(e.getSource()==option){
            enterResetIcon(option,selectorScreenOptionButton);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==help){
            enterResetIcon(help,selectorScreenHelpButton);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==handbook){
            enterResetIcon(handbook,handbookButtomHighlight );
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==startAdventure){
            Main.tst.setContentPane(new BackgroundPanel(MISSIONS_PARAMS.get(0).get("BG_PATH"),new AdventureModePanel(this,MISSIONSLIST,0)));
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if(e.getSource()==handbook){
            Main.tst.setContentPane(this.handbookSelectPanel);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if(e.getSource()==exit){
            System.exit(0);
            Main.tst.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mousePressed(MouseEvent e){

    }


}
