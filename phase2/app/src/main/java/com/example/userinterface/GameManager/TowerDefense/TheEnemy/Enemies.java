package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;

public interface Enemies {
    public abstract void move();

    public abstract void draw(Canvas canvas);

    public int getHealth();

    public void decreaseHealth(int damage);

    public int getY();

    public int getX();

    public int getMoneyGain();

    public int getScore();

    public int getDamage();

    public int getSpeed();

    public void setLocation(int x, int y);
}
