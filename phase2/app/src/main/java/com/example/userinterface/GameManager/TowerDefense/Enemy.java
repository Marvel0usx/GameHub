package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;

public abstract class Enemy { //common interface for all Enemies
    int health;
    int speed;

    int score;
    int x;
    int y;
    String appearence;

    public abstract void move();

    public abstract void draw(Canvas canvas);

    int getHealth() {
        return health;
    }

    abstract void hit(int num);

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    int getScore() {
        return score;
    }

    void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
