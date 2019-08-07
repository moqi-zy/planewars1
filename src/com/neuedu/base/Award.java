package com.neuedu.base;

public interface Award {
    //双倍火力
    int DOUBLE_FIRE = 0;
    //生命一条
    int Life = 1;
    //获得奖励类型（0/1）
    int getType();
    void Award();
}
