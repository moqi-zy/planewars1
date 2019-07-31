package com.neuedu.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg1.png"));
        map.put("my01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my_1.png"));
        map.put("myb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_2.png"));
        map.put("ep01",ImageUtil.getImage("com\\neuedu\\imgs\\enemyplane\\ep_01.png"));
        map.put("epb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_01.png"));
    }

    public static Image get(String key){
        return map.get(key);
    }

}
