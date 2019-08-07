package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class PauseButton extends BaseSprite implements Drawable {
    private Image image;

    public PauseButton() {
        this(26,660, ImageMap.get("pause1"));
    }

    public PauseButton(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null)/12,image.getHeight(null)/12,null);

    }
}
