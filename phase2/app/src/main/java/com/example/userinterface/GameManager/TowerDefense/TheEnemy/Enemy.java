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

    void setHealth(int health) {
        this.health = health;
    }

    public void decreaseHealth(int damage) {
        if (damage >= health) {
            health = 0;
        } else {
            health -= damage;
        }

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getMoneyGain() {
        return moneyGain;
    }

    void setMoneyGain(int moneyGain) {
        this.moneyGain = moneyGain;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    String getAppearence() {
        return appearence;
    }

    void setAppearence(String appearence) {
        this.appearence = appearence;
    }
}
