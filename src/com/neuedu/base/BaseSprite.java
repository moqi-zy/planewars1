package com.neuedu.base;

import java.awt.*;

public abstract class BaseSprite {
    private int x;
    private int y;
/*

    //总血量
    public int total;
    //血
    public int hp;
    //攻击
    public int attack;
*/

    public BaseSprite() {
    }

    public BaseSprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRectangle(){
        return null;
    }
}
