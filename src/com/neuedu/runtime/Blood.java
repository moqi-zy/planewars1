package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class Blood extends BaseSprite implements Drawable {
    private Image image;

    public Blood() {
        this(0,0, ImageMap.get("blood01"));
    }

    public Blood(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }



    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

    }
}
