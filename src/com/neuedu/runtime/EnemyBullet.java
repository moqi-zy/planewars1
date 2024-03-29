package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import java.awt.*;

public class EnemyBullet extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 5;
    private EnemyPlane enemyPlane;
    public EnemyBullet() {
    }
    public EnemyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX()-38,getY(),image.getWidth(null)*2/3,image.getHeight(null)*2/3,null);
    }

    @Override
    public void move() {
        setY(getY() + speed);
        borderTesting();
    }
    public  void borderTesting(){
        if (getY() > FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.enemyBulletList.remove(this);

        }
    }
    @Override
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())){
          gameFrame.enemyBulletList.remove(this);
        //  gameFrame.life += enemyPlane.getType() * 2;
            gameFrame.life--;
            if (gameFrame.life==0){gameFrame.gameOver = true;}
        }

    }
}
