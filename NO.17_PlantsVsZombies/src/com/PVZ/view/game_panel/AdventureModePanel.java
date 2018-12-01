package com.PVZ.view.game_panel;

import com.PVZ.api.base.constant.SystemConstant;
import com.PVZ.controller.Main;
import com.PVZ.controller.adventure.action.AdventureController;
import com.PVZ.model.bullets.Bullet;
import com.PVZ.model.others.*;
import com.PVZ.model.plants.*;
import com.PVZ.model.zombies.Zombie;
import com.PVZ.model.zombies.ZombieHead;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
import javax.swing.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.*;

import com.PVZ.view.game_end_panel.ContinuePanel;
import com.PVZ.view.game_end_panel.RestartPanel;
import com.PVZ.view.welcome_panel.StartPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;

import static com.PVZ.api.base.constant.AdventureConstant.MISSIONS_PARAMS;
import static com.PVZ.api.base.constant.AdventureConstant.PATH_DAYMODE_BG;
import static com.PVZ.api.base.constant.SystemConstant.*;

/**
 * @ClassName AdventureModePanel
 * @Description TODO
 * @Author chenqi
 * @Date 2018/11/13 18:50
 * @Version 1.0
 **/
public class AdventureModePanel extends JLayeredPane implements MouseListener, MouseMotionListener, Runnable {

    private Graphics g;
    private boolean stopFlag = false;
    private JPanel GamePanel;
    private StopPanel StopPanel = new StopPanel();
    private JPanel cardBoardPanel;
    private JLabel sunPointLabel;
    private JLabel shovelLabel;
    private Thread gameThread;
    private AdventureController adventureController;
    private List<PlantCard> plantCardList;
    private int status;
    private int sunPoint;
    private Image shadow;
    private JLabel sunLabel;
    private ContinuePanel continuePanel;
    private RestartPanel restartPanel;
    private List<String> adventrueMissionList;
    private int curMissonIndex;
    private int nextMissionIndex;
    private Player player;
    
    private StartPanel startPanel;


    public AdventureModePanel(StartPanel startPanel, List<String> adventrueMissionList, int curMissionIndex) {

        this.adventrueMissionList = adventrueMissionList;
        this.curMissonIndex = curMissionIndex;
        this.nextMissionIndex = (curMissionIndex + 1) < adventrueMissionList.size() ? (curMissionIndex + 1) : 0;
        System.out.println("[WARNING]You are in Mode Adventure MISSION_"+(this.curMissonIndex+1));
        this.adventureController = new AdventureController(this.adventrueMissionList.get(this.curMissonIndex), 20);
        this.setSize(900, 600);
        this.setLayout(null);
        this.startPanel = startPanel;
        this.setOpaque(false);
        //植物插槽
        this.cardBoardPanel = new JPanel();
        this.GamePanel = new JPanel();
        this.cardBoardPanel.setLayout(null);
        this.cardBoardPanel.setOpaque(false);
        this.StopPanel.setBounds(0, 0, 900, 600);
        this.StopPanel.setOpaque(false);
        this.StopPanel.setLayout(null);
        this.GamePanel.setBounds(140, 95, 805, 470);
        this.cardBoardPanel.setBounds(75, 5, 420, 70);
        this.add(cardBoardPanel);

        this.plantCardList = new ArrayList<>();

        //植物卡片
        this.addPlantCard(new PlantCard(new PeaShooter(), this), 0);
        this.addPlantCard(new PlantCard(new WallNut(), this), 1);
        this.addPlantCard(new PlantCard(new CherryBomb(), this), 2);
        if(adventureController.getName().equals(SystemConstant.ADVENTURE_MISSION_3))
        	this.addPlantCard(new PlantCard(new SunShroom(), this), 3);
        else
        	this.addPlantCard(new PlantCard(new SunFlower(), this), 3);
        this.addPlantCard(new PlantCard(new Chomper(), this), 4);
        this.addPlantCard(new PlantCard(new TorchWood(), this), 5);
        if(!adventureController.getName().equals(SystemConstant.ADVENTURE_MISSION_1))
        	this.addPlantCard(new PlantCard(new GatlingPeaShooter(), this), 6);
        //铲子
        this.add(new Shovel(this));
        //暂停按钮
        new StopButton(this);

        //显示阳光值
        this.sunPointLabel = new JLabel(String.valueOf(adventureController.getSun_points()), JLabel.CENTER);
        this.sunPointLabel.setBounds(15, 60, 45, 15);
        this.add(sunPointLabel);


        //植物阴影
        this.shadow = new ImageIcon(INTERFACE_PATH + "plantshadow32.png").getImage();

        //通过成功界面
        this.continuePanel = new ContinuePanel(this.startPanel, this);
        //通关失败界面
        this.restartPanel = new RestartPanel(this.startPanel, this);
        

        try {
        	File file=new File("resources/audio/backmusic.wav");

        	player = Manager.createPlayer(file.toURL());
        	player.start();
        	player.addControllerListener(new ControllerListener() {
				
				@Override
				public void controllerUpdate(ControllerEvent ce) {
					if(ce instanceof EndOfMediaEvent) {
						player.setMediaTime(new Time(0));
						player.start();
						return;
					}
					
				}
			});
        }catch (MalformedURLException E){

        }catch (IOException E){

        }catch (NoPlayerException E){

        }

        this.gameThread = new Thread(this);

        //
        //设置线程状态
        this.status = RUNNING;
        gameThread.start();
    }




    public void setStatus(int status) {
        this.status = status;
    }

    public StartPanel getStartPanel() {
        return startPanel;
    }

    public int getCurMissonIndex() {
        return curMissonIndex;
    }

    public int getNextMissionIndex() {
        return nextMissionIndex;
    }

    public List<String> getAdventrueMissionList() {
        return adventrueMissionList;
    }

    public void addPlantCard(PlantCard plantCard, int index) {
        plantCard.setBounds(60 * index, 0, 60, 70);
        this.cardBoardPanel.add(plantCard);
        this.plantCardList.add(plantCard);
    }



    public void addSun(JPanel sun, int x, int y) {
        sun.setBounds(x, y, 50, 50);
        this.GamePanel.add(sun);
    }

    public void addZombie(JPanel zombieImg, int x, int line) {
        zombieImg.setBounds(x, 97 * line, 60, 70);
        this.GamePanel.add(zombieImg);
    }

    public void addPlane(JPanel planeImg, int index, int line) {
        planeImg.setBounds(80 * index, 97 * line, 60, 70);
        this.GamePanel.add(planeImg);
    }

    public int getSunPoint() {
        return adventureController.getSun_points();
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public List<PlantCard> getPlantCardList() {
        return plantCardList;
    }

    //画子弹
    public void paintBullets(Graphics g) {
        if (!adventureController.getBulletsInLines().isEmpty()) {
            for (CopyOnWriteArrayList<Bullet> bulletsInLine : adventureController.getBulletsInLines().values()) {
                for (Bullet bl : bulletsInLine) {
                    g.drawImage(bl.getActiveImage(), bl.posX, bl.posY, bl.width, bl.height, null);
                }
            }
        }
    }

    //画僵尸
    public void paintZombies(Graphics g) {
        if (!adventureController.getZombiesInLines().isEmpty()) {
            for (CopyOnWriteArrayList<Zombie> zbList : adventureController.getZombiesInLines().values()) {
                {
                    for (Zombie zb : zbList) {
                    	if(!(zb instanceof ZombieHead))
                    		g.drawImage(shadow, zb.col_posX, zb.col_posY + 65, shadow.getWidth(null) + 5, shadow.getHeight(null), null);
                        g.drawImage(zb.getActiveImage(), zb.posX, zb.posY, zb.width, zb.height, null);
                    }
                }
            }
        }

    }


    public void paintPlant(Graphics g) {
        if (!adventureController.getPlantsInGrid().isEmpty()) {
            for (Plant p : adventureController.getPlantsInGrid().values()) {
                {
                    g.drawImage(shadow, p.getColumn() * 80 + 40, p.getRow() * 90 + 60, shadow.getWidth(null), shadow.getHeight(null), null);
                    g.drawImage(p.getActiveImage(), p.posX, p.posY, p.width, p.height, null);
                }
            }
        }
    }


    public void paintSun(Graphics g) {
        if (!adventureController.getActiveSunInPanel().isEmpty()) {
            for (Sun sun : adventureController.getActiveSunInPanel()) {
                g.drawImage(sun.getActiveImage(), sun.posX, sun.posY, sun.width, sun.height, null);
                if (!sun.isLabelAdded()) {
                    this.add(sun.getSunLabel(), 2);
                    sun.setLabelAdded(true);
                } else {
                    sun.resetLabelLocation();
                }
            }
        }
    }

    public void paintLawnmowers(Graphics g) {
        for (Lawnmower lw : this.adventureController.getLawnmowers()) {
            g.drawImage(shadow, lw.posX, lw.posY + 30, shadow.getWidth(null), shadow.getHeight(null), null);
            g.drawImage(lw.getActiveImage(), lw.posX, lw.posY, lw.width, lw.height, null);
        }

    }

    public void paintMsgLabel(Graphics g) {
    	MsgLabel msgLabel = this.adventureController.getMsgLabel();
    	if(msgLabel!=null)
    		g.drawImage(msgLabel.getActiveImage(), msgLabel.posX, msgLabel.posY, msgLabel.width, msgLabel.height, null);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.paintPlant(g);
        this.paintZombies(g);
        this.paintLawnmowers(g);
        this.paintBullets(g);
        this.paintSun(g);
        this.paintMsgLabel(g);
        this.sunPointLabel.setText(String.valueOf(this.adventureController.getSun_points()));
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public Player getPlayer() {
    	return this.player;
    }

    @Override
    public void run() {
        while (status != STOP) {

            if (status != SUSPEND) {

                try {
                    Thread.sleep(1000 / this.adventureController.getFPS());
                } catch (InterruptedException e) {
                }
                //TODO
                adventureController.advance();
                //System.out.println("更新一次");
                if (adventureController.isZombieAteYourBrain()) {
                	player.stop();
                    restartGame();
                }
                if (adventureController.isMissionPassed()) {
                	player.stop();
                    continueGame();
                }
                repaint();
            } else {
                System.out.println("it is waiting");
                synchronized (this) {
                    gamePanelSuspend();
                    ExitB Exit_continua = new ExitB(StopPanel, this);
                    ContinueB Button_continua = new ContinueB(StopPanel, this);
                    RestartB Restart_button = new RestartB(StopPanel, this, startPanel);
                    this.add(StopPanel, new Integer(Integer.MAX_VALUE));
                    try {
                        this.wait();
                    } catch (InterruptedException event) {
                    }
                }
            }
        }

    }

    public synchronized void gamePanelResume() {
        this.remove(StopPanel);
        status = RUNNING;
        notifyAll();
    }


    public void gamePanelStop() {
        status = STOP;
    }


    public void gamePanelSuspend() {
        status = SUSPEND;

    }

    //继续游戏
    public synchronized void continueGame() {
        System.out.println("继续游戏");
        status = STOP;
        notifyAll();

        Main.tst.setContentPane(this.continuePanel);
        gameThread.interrupt();
        //Main.tst.setContentPane(new ContinuePanel(startPanel) );
    }


    //重新开始
    public synchronized void restartGame() {
        System.out.println("停止");
        status = STOP;
        notifyAll();
        Main.tst.setContentPane(this.restartPanel);
        gameThread.interrupt();
    }




    public AdventureController getAdventureController() {
        return adventureController;
    }




    public void setAdventureController(AdventureController adventureController) {
        this.adventureController = adventureController;
    }


    public JPanel getCardBoardPanel() {
        return cardBoardPanel;
    }
}

class StopPanel extends JPanel {

    Image stopImage = new ImageIcon(SystemConstant.INTERFACE_PATH + "/pause_panel/pause_empty.png").getImage();

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(stopImage, 293, 143, 315, 314, null);
    }
}

class ContinueB extends JLabel implements MouseListener {

    private static ImageIcon normalButton = new ImageIcon(SystemConstant.INTERFACE_PATH + "/pause_panel/continue-1.png");
    private static ImageIcon clickButton = new ImageIcon(SystemConstant.INTERFACE_PATH + "/pause_panel/continue-2.png");
    private StopPanel StopPanel;
    private AdventureModePanel gamePanel;

    public ContinueB(StopPanel StopPanel, AdventureModePanel gamePanel) {
        this.gamePanel = gamePanel;
        normalButton.setImage(normalButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));
        clickButton.setImage(clickButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));
        this.StopPanel = StopPanel;
        this.setIcon(normalButton);
        this.setBounds(342, 292, 216, 38);
        this.StopPanel.add(this);
        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setIcon(clickButton);
        gamePanel.gamePanelResume();
        for (PlantCard pc : gamePanel.getPlantCardList()) {
            pc.cardResume();
        }
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

class ExitB extends JLabel implements MouseListener {

    private static ImageIcon normalButton = new ImageIcon(SystemConstant.INTERFACE_PATH + "/pause_panel/exit-1.png");
    private static ImageIcon clickButton = new ImageIcon(SystemConstant.INTERFACE_PATH + "/pause_panel/exit-2.png");
    private StopPanel StopPanel;
    private AdventureModePanel gamePanel;

    public ExitB(StopPanel StopPanel, AdventureModePanel gamePanel) {
        this.gamePanel = gamePanel;
        normalButton.setImage(normalButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));
        clickButton.setImage(clickButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));
        this.StopPanel = StopPanel;
        this.setIcon(normalButton);
        this.setBounds(342, 360, 216, 38);
        this.StopPanel.add(this);
        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setIcon(clickButton);
        gamePanel.getPlayer().stop();

        Main.tst.setContentPane(gamePanel.getStartPanel());

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

class RestartB extends JLabel implements MouseListener {

    private static ImageIcon normalButton = new ImageIcon(SystemConstant.INTERFACE_PATH + "/pause_panel/restart-1.png");
    private static ImageIcon clickButton = new ImageIcon(SystemConstant.INTERFACE_PATH + "/pause_panel/restart-2.png");
    private StopPanel StopPanel;
    private AdventureModePanel gamePanel;
    private StartPanel startPanel;


    public RestartB(StopPanel StopPanel, AdventureModePanel gamePanel, StartPanel startPanel) {
        this.gamePanel = gamePanel;
        this.startPanel = startPanel;
        normalButton.setImage(normalButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));
        clickButton.setImage(clickButton.getImage().getScaledInstance(216, 38, Image.SCALE_DEFAULT));
        this.StopPanel = StopPanel;
        this.setIcon(normalButton);
        this.setBounds(342, 227, 216, 38);
        this.StopPanel.add(this);
        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setIcon(clickButton);
        gamePanel.continueGame();
        gamePanel.gamePanelResume();
        gamePanel.getPlayer().stop();
        Main.tst.setContentPane(new BackgroundPanel(MISSIONS_PARAMS.get(gamePanel.getCurMissonIndex()).get("BG_PATH"), new AdventureModePanel(startPanel, gamePanel.getAdventrueMissionList(), gamePanel.getCurMissonIndex())));

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