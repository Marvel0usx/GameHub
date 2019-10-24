package com.example.userinterface.TowerDefense;

import android.graphics.Canvas;

abstract class Enemy {
    int health;
    int speed;
    int reward;
    int x;
    int y;

    public abstract void move();

    public abstract void draw(Canvas canvas);

    public abstract int getHealth();

    public abstract void hit(int num);

    public abstract int getY();
}
