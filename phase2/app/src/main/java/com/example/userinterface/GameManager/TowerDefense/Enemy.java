package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;

public abstract class Enemy { //common interface for all Enemies
    int health;
    int speed;
    int moneyGain;
    int score;
    int x;
    int y;
    String appearence;

    public abstract void move();

    public abstract void draw(Canvas canvas);

    int getHealth() {
        return health;
    }

    public void decreaseHealth(int damage){
        health -= damage;
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getMoneyGain(){
        return moneyGain;
    }

    int getScore() {
        return score;
    }

    public int getSpeed(){
        return speed;
    }

    void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
