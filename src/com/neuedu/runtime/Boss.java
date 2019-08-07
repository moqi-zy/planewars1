package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Boss extends BaseSprite implements Moveable, Drawable {

    private List<Image> imageList = new ArrayList<>();
    public int count;
    public int hp = 50;
    private int speed = FrameConstant.GAME_SPEED * 5;
    private boolean right;
    private Random random = new Random();
    /*  public int x,y;
    //Boss每帧的宽高
    public int frameW, frameH;

    //Boss运动的速度
    //Boss的运动轨迹
    //一定时间会向着屏幕下方运动，并且发射大范围子弹，（是否狂态）
    //正常状态下 ，子弹垂直朝下运动
    private boolean isCrazy;
    //进入疯狂状态的状态时间间隔
    private int crazyTime = 200;
    //Boss当前帧下标
    private int frameIndex;*/
    //计数器
    private int count1;

    public Boss() {
    /*    for (int i = 0; i <10 ; i++) {
            imageList.add(ImageMap.get("index"+ i));
        }

*/
        imageList.add(ImageMap.get("boss1"));
        imageList.add(ImageMap.get("boss2"));
        imageList.add(ImageMap.get("boss3"));
        imageList.add(ImageMap.get("boss4"));
        imageList.add(ImageMap.get("boss5"));
        imageList.add(ImageMap.get("boss6"));
        imageList.add(ImageMap.get("boss7"));
        imageList.add(ImageMap.get("boss8"));
        imageList.add(ImageMap.get("boss9"));
    }

    int index = 0;

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        fire();
        g.drawImage(imageList.get(index++/8),getX(),getY(),imageList.get(index++/8).getWidth(null),
                imageList.get(index++/8).getHeight(null),
                null);
        if (index >= 72){
            index = 0;
        }
    }
    @Override
    public void move() {
        if (right) {
            setX(getX() + speed);
        } else {
            setX(getX() - speed);
        }
    }
    public void fire(){
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (random.nextInt(1000) > 985){
            gameFrame.bossButtles.add(
                    new BossButtle(getX() + imageList.get(index / 8).getWidth(null) - ImageMap.get("boss01").getWidth(null),
                            getY() + imageList.get(index / 8).getHeight(null),
                            ImageMap.get("boss01"))
            );

        }
    }
    @Override
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),imageList.get(index / 8).getWidth(null),
                imageList.get(index/8).getHeight(null));
    }

    public void borderTesting(){
        if (getX() + imageList.get(index / 8).getWidth(null) > FrameConstant.FRAME_WIDTH){
            right = false;
        }else if (getX() < 0){
            right = true;

        }
    }


   //public void logic()
    /*{
        //不断循环播放帧形成动画
        frameIndex++;
        if (frameIndex >=10){
            frameIndex = 0;
        }
        //没有疯狂的状态
       if(isCrazy == false){
           x += speed;
           if (x + frameW > FrameConstant.FRAME_WIDTH){
               speed = -speed;
           }else if (x<0){
               speed = -speed;

           }
           count1++;
           if (count1%crazyTime == 0){
               isCrazy = true;
               speed = 8;
           }
           //疯狂状态
       }else {
           speed -= 1;
           //当Boss返回时创建大量子弹
           if (speed == 0){

           }
       }
   }*/
}
