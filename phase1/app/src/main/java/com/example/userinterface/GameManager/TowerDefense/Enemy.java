package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;

abstract class Enemy {
    int health;
    int speed;
    int reward;
    int x;
    int y;

    public abstract void move();

    public abstract void draw(Canvas canvas);

    public int getHealth(){ return health; }

    public abstract void hit(int num);

    public int getY(){return y;}

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
}
