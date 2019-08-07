package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;
import sun.awt.ModalExclude;

import java.awt.*;
import java.util.List;

public class Bullet extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private Image image1;
    private int speed = FrameConstant.GAME_SPEED * 4;


    public Bullet() {
        this(0,0, ImageMap.get("myb01"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
        this.image1 = ImageMap.get("myb02") ;
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (gameFrame.empiricValue < 60){
            g.drawImage(image,getX()-12,getY(),image.getWidth(null)/2,image.getHeight(null)/2,null);

        }else {
            g.drawImage(image1,getX()-12,getY(),image.getWidth(null)/2,image1.getHeight(null)/2,null) ;
        }
    }

    @Override
    public void move() {
        setY(getY() - speed);
    }

    public  void borderTesting(){
        if (getY() < 30 - image.getHeight(null)){
           GameFrame gameFrame = DateStore.get("gameFrame");
           gameFrame.bulletList.remove(this);

        }
    }
    @Override
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void collisionTesting(List<EnemyPlane> enemyPlaneList){
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            if (enemyPlane.getRectangle().intersects(this.getRectangle())){
                enemyPlaneList.remove(enemyPlane);
                gameFrame.bulletList.remove(this);
                gameFrame.score += enemyPlane.getType() * 1;
                //生命值
                gameFrame.empiricValue++;

            }

        }
    }
    //我方子弹对boss的伤害
    public void collisionTesting(Boss boss){
        GameFrame gameFrame =DateStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())){
            gameFrame.bulletList.remove(this);
            gameFrame.bosslife -= 5;

        }
    }
}
