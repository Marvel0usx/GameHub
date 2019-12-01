package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;

public abstract class Enemy implements Enemies { //common interface for all Enemies
    int health;
    int speed;
    int moneyGain;
    int score;
    int x;
    int y;
    int damage = 1;
    String appearence;

    public abstract void move();

    public abstract void draw(Canvas canvas);

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int damage) {
        if (damage >= health){
            health = 0;
        }else{
            health -= damage;
        }

    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getMoneyGain() {
        return moneyGain;
    }

    public int getScore() {
        return score;
    }

    public int getDamage(){
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
