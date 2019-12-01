package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;

public abstract class Enemy implements Enemies { //common interface for all Enemies
    private int health;
    private int speed;
    private int moneyGain;
    private int score;
    private int x;
    private int y;
    private int damage = 1;
    private String appearence;

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

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setMoneyGain(int moneyGain) {
        this.moneyGain = moneyGain;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getAppearence() {
        return appearence;
    }

    public void setAppearence(String appearence) {
        this.appearence = appearence;
    }
}
