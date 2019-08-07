package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.ImageMap;
import jdk.internal.dynalink.support.Guards;

import java.awt.*;
import java.awt.event.*;
import java.security.Guard;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameFrame extends Frame {

    //创建背景对象
    private Background background = new Background();
    //创建飞机对象
    private Plane plane = new Plane();
    //创建Boss
    private Boss boss = new Boss();
    private StartButton startButton = new StartButton();
    private PauseButton pauseButton = new PauseButton();
    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();
    //创建敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();
    //创建敌方飞机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();
    //创建道具集合
    public final List<Prop> propList = new CopyOnWriteArrayList<>();
    //创建按钮
    public final List<StartButton> start = new CopyOnWriteArrayList<>();
    public final List<PauseButton> pause = new CopyOnWriteArrayList<>();
    public final List<BossButtle> bossButtles = new CopyOnWriteArrayList<>();

    //游戏不结束
    public boolean gameOver = false;
    //游戏开始
    public boolean startA = false;
    //游戏暂停
    public boolean pauseB = false;
    //得分
    public int score = 0;
    //定时器
    private int timer = 0;
    //生命值
    public int life = 100;
    //boss生命值
    public int bosslife = 1000;
    //经验值
    public int empiricValue = 0;
    int sleep=20;
    //是否遭到攻击
    public volatile boolean attack = true;
    public boolean drawBoss = true;

    @Override
    public void paint(Graphics g) {
        background.draw(g);
        for (StartButton startButton : start) {
            startButton.draw(g);
        }

        if (!gameOver && startA) {
            plane.draw(g);
            if (timer >= 40 * 50) {
                boss.draw(g);
                for (Bullet bullet : bulletList) {
                    bullet.collisionTesting(boss);
                }
            }
            for (PauseButton pauseButton : pause) {
                pauseButton.draw(g);
            }
            for (StartButton startButton : start) {
                start.remove(startButton);
            }

            for (Prop prop : propList) {
                prop.draw(g);
            }
            EnemyPlane.createEnemyPlanePlus();
            Prop.createPropPlus();
            for (BossButtle bossButtle : bossButtles) {
                bossButtle.draw(g);
            }
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
            }

            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);

            }
            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlaneList);
            }

            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.collisionTesting(plane);
            }
            for (BossButtle bossButtle : bossButtles) {
                bossButtle.collisionTesting(plane);
            }

            for (Prop prop : propList) {
                prop.collisionTesting(plane);
            }
            if (drawBoss){
                g.setFont(new Font("楷体", Font.BOLD, 25));
                g.setColor(new Color(142, 119, 255));
                g.drawRect(580, 96, 80, 17);
                g.fillRect(580, 96,bosslife/10, 17);
                g.drawString("boss生命:" + bosslife, 580, 80);

            }else if(bosslife <= 0){
                gameOver = true;
                drawBoss = false;
            }

            g.setFont(new Font("楷体", Font.BOLD, 25));
            g.setColor(new Color(142, 119, 255));
            g.drawString("得分:" + score, 100, 80);
            g.drawString("生命值:" + life, 240, 80);
            g.drawString("经验值:" + empiricValue, 420, 80);
            g.setFont(new Font("楷体", Font.BOLD, 20));
          /*  g.setColor(Color.MAGENTA);
            g.drawString("boss生命值:" + boss.count, 600, 150);
            g.drawRect(650, 110, 80, 15);
            g.fillRect(650, 110, boss.count, 15);*/
        }
        if (life < 0 || gameOver == true) {
            g.setColor(Color.red);
            g.setFont(new Font("宋体", Font.BOLD, 45));
            g.drawString("GAME  OVER!!!", 240, 400);
            gameOver = true;
        }
        //测试
       /* g.setColor(Color.red);
        g.drawString("" + bulletList.size(),100,100);*/
    }

    /**
     * 使用这个方法初始化窗口
     */

    public void init() {
        //设置尺寸
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        //设置居中
        setLocationRelativeTo(null);
        //
        enableInputMethods(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        start.add(new StartButton());
        pause.add(new PauseButton());

        /**
         * 鼠标点击判断事件
         */
/*

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX());
                System.out.println(e.getY());
            }
        });
*/
        //鼠标监听（关闭窗口监听）
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (StartButton startButton : start) {
                    if (e.getX() > startButton.getX() &&
                            e.getX() < startButton.getX() + ImageMap.get("s01").getWidth(null) &&
                            e.getY() < ImageMap.get("s01").getHeight(null) + startButton.getY() &&
                            e.getY() > startButton.getY()) {
                        startA = true;

                    }
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (PauseButton pauseButton : pause) {
                    if (e.getX() > pauseButton.getX() &&
                            e.getX() < pauseButton.getX() + ImageMap.get("pause1").getWidth(null) &&
                            e.getY() < ImageMap.get("pause1").getHeight(null) + pauseButton.getY() &&
                            e.getY() > pauseButton.getY()) {
                        if (!pauseB)
                        { pauseB = true;
                        sleep=Integer.MAX_VALUE;
                        }
                        else
                        { pauseB=false;
                        sleep=20;
                            new Thread() {
                                @Override
                                public void run() {

                                    while (true) {
                                        repaint();
                                        try {
                                            Thread.sleep(sleep);
                                            timer++;
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            }.start();

                        }
                    }
                }
            }
        });

        //添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });


        new Thread() {
            @Override
            public void run() {

                while (true) {
                    repaint();
                    try {
                        Thread.sleep(sleep);
                        timer++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();


        //游戏初始时添加一些敌方飞机
/*
        enemyPlaneList.add(new EnemyPlane(300,30, 1));
        enemyPlaneList.add(new EnemyPlane(30,60, 1));
        enemyPlaneList.add(new EnemyPlane(550,45, 1));
        enemyPlaneList.add(new EnemyPlane(210,85, 1));
        enemyPlaneList.add(new EnemyPlane(480,20, 2));*/

        setVisible(true);

    }

    private Image offScreenImage = null;

    //闪屏
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics goff = offScreenImage.getGraphics();
        paint(goff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
