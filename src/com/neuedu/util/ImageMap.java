package com.neuedu.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map = new HashMap<>();

    static {
        //背景图
        map.put("bg01",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg7.png"));
        //飞机
        map.put("my01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my_1.png"));

       //我方子弹
        map.put("myb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_1.png"));
        map.put("myb02",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_2.png"));
        //敌机
        map.put("ep01",ImageUtil.getImage("com\\neuedu\\imgs\\enemyplane\\ep_15.png"));
        map.put("ep02",ImageUtil.getImage("com\\neuedu\\imgs\\enemyplane\\ep_5.png"));
        //敌机子弹
        map.put("epb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_04.png"));
        map.put("epb02",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_02.png"));
        //开始图片
        map.put("s01",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\s1.png"));
        //血量条
        map.put("bar01",ImageUtil.getImage("com\\neuedu\\imgs\\blood\\bar.png"));
        map.put("blood01",ImageUtil.getImage("com\\neuedu\\imgs\\blood\\blood.png"));
        //道具
        map.put("prop01",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\prop3.png"));
        map.put("prop02",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\prop5.png"));
        //Boss
        map.put("boss1",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_01.png"));
        map.put("boss2",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_02.png"));
        map.put("boss3",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_03.png"));
        map.put("boss4",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_04.png"));
        map.put("boss5",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_05.png"));
        map.put("boss6",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_06.png"));
        map.put("boss7",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_07.png"));
        map.put("boss8",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_08.png"));
        map.put("boss9",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_09.png"));
        //boss子弹
        map.put("boss01",ImageUtil.getImage("com\\neuedu\\imgs\\bossbuttle\\boss01_03.png"));
        map.put("boss02",ImageUtil.getImage("com\\neuedu\\imgs\\bossbuttle\\boss01_04.png"));
        map.put("boss03",ImageUtil.getImage("com\\neuedu\\imgs\\bossbuttle\\boss01_05.png"));
        map.put("boss04",ImageUtil.getImage("com\\neuedu\\imgs\\bossbuttle\\boss01_06.png"));
        //暂停按钮
        map.put("pause1",ImageUtil.getImage("com\\neuedu\\imgs\\button\\pause.png"));


    }

    public static Image get(String key){
        return map.get(key);
    }


}
