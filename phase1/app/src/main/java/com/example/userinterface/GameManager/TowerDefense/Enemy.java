package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;

abstract class Enemy { //common interface for all Enemies
    int health;
    int speed;

    int score;
    int x;
    int y;

    public abstract void move();

    public abstract void draw(Canvas canvas);

    int getHealth() {
        return health;
    }

    abstract void hit(int num);

    int getY() {
        return y;
    }

    int getScore() {
        return score;
    }

    void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
