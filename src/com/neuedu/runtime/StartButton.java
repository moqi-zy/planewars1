package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.MouseEvent;

public class StartButton extends BaseSprite implements Drawable {

    private Image image;

    public StartButton() {
        this(280,400,ImageMap.get("s01"));

    }

    public StartButton(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null)/2,image.getHeight(null)/2,null);

    }

}
