package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends BaseSprite implements Moveable, Drawable {

    public static int type;
    private Image image;
    private boolean up, right, down,left;
    private boolean fire;

    private int speed = FrameConstant.GAME_SPEED * 3;
    private final int WIDTHPLUS = ImageMap.get("ep02").getWidth(null);

    public Plane() {
        this((FrameConstant.FRAME_WIDTH - ImageMap.get("my01").getWidth(null))/2,
                FrameConstant.FRAME_HEIGHT - ImageMap.get("my01").getHeight(null),
                ImageMap.get("my01"));
    }

    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }


    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null)/4*3,image.getHeight(null)/4*3,null);
       /* System.out.println("1111111");
        fire();*/
       /* fire();
        if (fire){
            index++;
            if (index >= 10){
                index = 0;

            }
        }
*/
    }
   private int index ;

    /**
     * 开火方法
     * 判断开关是打开
     * 创建一个子弹对象放入到gameFrame里的子弹集合中
     */

    public void fire(){
        if (fire) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bulletList.add(new Bullet(
                    getX() + (image.getWidth(null)/2) - (ImageMap.get("myb01").getWidth(null)/2),
                    getY() - ImageMap.get("myb01").getHeight(null)/2,
                    ImageMap.get("myb01")
            ) );
     /*   }else if (type == 2){
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bulletList.add(new Bullet(
                    getX() + (image.getWidth(null)/2) - (ImageMap.get("myb02").getWidth(null)/2),
                    getY() - ImageMap.get("myb02").getHeight(null)/2,
                    ImageMap.get("myb02")
            ) );
*/
        }
    }
    /**
     * 产生敌机
     * 只要我方飞机活着  重绘的时候就调用此方法来生成敌机
     */
    @Override
    public void move() {
        if (up){
            setY(getY() - speed);
        }
        if (right){
            setX(getX() + speed);
        }
        if (down){
            setY(getY() + speed);
        }
        if (left){
            setX(getX() - speed);
        }
        borderTesting();
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            fire = true;
           // fire();
        }


    }
    public void borderTesting(){
        if (getX() < 0){
            setX(0);
        }
        if (getX() > FrameConstant.FRAME_WIDTH - image.getWidth(null)){
            setX(FrameConstant.FRAME_WIDTH - image.getWidth(null));
        }
        if (getY() < 30){
            setY(30);
        }
        if (getY() > FrameConstant.FRAME_HEIGHT - image.getHeight(null)){
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null));
        }


    }

    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            fire();
            fire = false;
        }
    }
    @Override
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

     //声明生命值
    public boolean life() {
        return life();
    }
}
