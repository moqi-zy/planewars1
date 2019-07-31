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
    private int speed = FrameConstant.GAME_SPEED * 1;

    public Bullet() {
        this(0,0, ImageMap.get("myb01"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
       // borderTesting();
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
            }

        }
    }


}
