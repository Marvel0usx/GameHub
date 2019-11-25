package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.util.Log;

public abstract class Enemy { //common interface for all Enemies
    int health;
    int speed;

    int score;
    int x;
    int y;
    String appearence;


    public abstract void draw(Canvas canvas);

    int getHealth() {
        return health;
    }

    void hit(int num) {
        health -= num;
        Log.d(health + "", "this is the health" + health);
    }
    abstract  void move();
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
