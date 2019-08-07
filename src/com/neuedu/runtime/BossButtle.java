package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class BossButtle extends BaseSprite implements Moveable, Drawable{
    private Image image;
    private int ButtleType;
    private final int speed = FrameConstant.GAME_SPEED * 6;

    public BossButtle() {
        this(0,0,ImageMap.get("boss01"));
    }

    public BossButtle(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();

    }

    @Override
    public void move() {
        setY(getY() + speed);
        borderTesting();
    }
    public void borderTesting(){
        if (getY() > FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bossButtles.remove(this);
        }
    }
    @Override
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())){
            gameFrame.bossButtles.remove(this);
            //  gameFrame.life += enemyPlane.getType() * 2;
            gameFrame.life--;
            if (gameFrame.life==0){gameFrame.gameOver = true;}
        }

    }
}
