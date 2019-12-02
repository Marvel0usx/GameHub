package com.example.userinterface.GameManager.TowerDefense.Towers;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.AmmoFactory;
import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Enemies;

public abstract class MainTower implements Towers {
    private int waitTime, time;
    private String type;
    private int x;
    private int y;
    private int range;

    public Ammo generateBullet(Enemies enemy) {
        AmmoFactory ammoFactory = new AmmoFactory();
        if (enemy.getY() > y - range && enemy.getY() < y + range) {
            if (time <= 0) {
                time = waitTime;
                Ammo ammo = ammoFactory.buildAmmunition(type);
                System.out.println(ammo);
                return ammo;
            } else {
                time--;
                return null;
            }
        } else {
            time = 0;
            return null;
        }
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRange() {
        return range;
    }

    void setRange(int range) {
        this.range = range;
    }

    void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    void setTime(int time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }
}
