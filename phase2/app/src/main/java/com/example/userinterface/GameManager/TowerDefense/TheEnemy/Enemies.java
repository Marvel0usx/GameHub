package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;

public interface Enemies {
    void move();

    void draw(Canvas canvas);

    int getHealth();

    void decreaseHealth(int damage);

    int getY();

    int getX();

    int getMoneyGain();

    int getScore();

    int getDamage();

    int getSpeed();

    void setLocation(int x, int y);
}
