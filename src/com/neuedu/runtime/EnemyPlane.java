package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {
    private Image image;

    private Image image1;

    private  int speed = FrameConstant.GAME_SPEED * 4;

    Random random = new Random();

    //敌机类型
    private int type;
    private int index;
    private int hp;

   // final long start = System.currentTimeMillis();

    public EnemyPlane() {

        this(0,0,1);
    }

    public EnemyPlane(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image = ImageMap.get("ep01");
        this.image1 = ImageMap.get("ep02");
       init();
    }

    void init(){
        if (type == 1){
            hp = 5;
        }else if (type == 2){
            hp = 10;
        }
    }
    
    public int getType() {
        return type;
    }


    @Override
    public void draw(Graphics g) {
            if (type==1){
                g.drawImage(image,getX(),getY(),image.getWidth(null)/2,image.getHeight(null)/2,null);
                move();
                fire();
            }
           if (type==2){
               g.drawImage(image1,getX(),getY(),image1.getWidth(null)/4*3,image1.getHeight(null)/4*3,null);
               move();
               fire();
           }


    }

    public void fire(){
        GameFrame gameFrame = DateStore.get("gameFrame");
        //子弹的频率
        if (random.nextInt(1000) > 985) {
            gameFrame.enemyBulletList.add(new EnemyBullet(
                    getX() + (image.getWidth(null) / 2) - ImageMap.get("epb01").getWidth(null) / 2,
                    getY() + image.getHeight(null),
                    ImageMap.get("epb01")));
            /*if (random.nextInt(1000) > 950) {
                gameFrame.enemyBulletList.add(new EnemyBullet(
                        getX() + (image1.getWidth(null) / 2) - ImageMap.get("epb01").getWidth(null) / 2,
                        getY() + image1.getHeight(null),
                        ImageMap.get("epb01")));
            }*/
        }
    }

    private boolean right = true;


    @Override
    public void move() {
        if (type == 1){
            setY(getY() + speed);
        } else if (type == 2){
            if (right){
                setX(getX() + speed);
                setY(getY() + speed);
            }else {
                setX(getX() - speed);
            }
        }
        borderTesting();
    }

    public void borderTesting(){
        if (type == 1) {
            if (getY() > FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = DateStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);
            }
        }else if (type == 2){
            if (getX() + image1.getWidth(null) >= FrameConstant.FRAME_WIDTH){
                right = false;
            }else if (getX() < 0){
                right = true;
            }

        }


    }
    public static void createEnemyPlanePlus(){
        Random random = new Random();
        GameFrame gameFrame =DateStore.get("gameFrame");
        if (random.nextInt(1000) > 985){
            gameFrame.enemyPlaneList.add(new EnemyPlane(
                    random.nextInt(300),
                    50,
                    1));
        }else if (random.nextInt(1000) > 985){
                gameFrame.enemyPlaneList.add(new EnemyPlane(
                        random.nextInt(300),
                        50,
                        2));
        }

    }

    @Override
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
}
