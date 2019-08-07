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

public class Prop extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private Image image1;

    private int speed = FrameConstant.GAME_SPEED * 2;
    private int type1;

    private boolean right = true;
    public Prop() {
        this(0,0, 1);
    }


    public Prop(int x, int y, int type1) {
        super(x, y);
        this.type1 = type1;
        this.image = ImageMap.get("prop01");
        this.image1 = ImageMap.get("prop02");
    }

    @Override
    public void draw(Graphics g) {
        if (type1 == 1){
            g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
            move();
        }
        if (type1 == 2){
            g.drawImage(image1,getX(),getY(),image1.getWidth(null),image1.getHeight(null),null);
            move();
        }
    }

    @Override
    public void move() {
        setY(getY() + speed);
        borderTesting();
    }
    public void borderTesting(){
        if (type1 == 1) {
            if (getY() > FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = DateStore.get("gameFrame");
                gameFrame.propList.remove(this);
            }
        }
        if (type1 == 2){
            if (getX() + image1.getWidth(null) >= FrameConstant.FRAME_WIDTH){
                right = false;
            }else if (getX() < 0){
                right = true;
            }
        }
    }
   @Override
   public Rectangle getRectangle(){

       return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
   }
    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.propList.remove(this);
            if (this.type1 == 1 && gameFrame.life <80){
                gameFrame.life++;
            }
            if (type1 == 2 ){
                gameFrame.empiricValue += 5;
            }
        }
    }
    public static void createPropPlus(){
        Random random = new Random();
        GameFrame gameFrame =DateStore.get("gameFrame");
        if (random.nextInt(1000) >997){
            gameFrame.propList.add(new Prop(
                    random.nextInt(500),
                    100,
                    1));
        }
        if (random.nextInt(1000) > 997 ){
            gameFrame.propList.add(new Prop(
                    random.nextInt(500),
                    100,
                    2));
        }

    }
}
