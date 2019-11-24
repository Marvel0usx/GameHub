package com.example.userinterface.GameManager.TowerDefense.Towers;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.AmmoFactory;

public class MainTower implements Towers{
    public int waitTime, time;
    String type;

    String side;
    int x;

    int y;

    public Ammo generateBullet() {
        AmmoFactory ammoFactory = new AmmoFactory();
        if (time <= 0){
            time = waitTime;
            return ammoFactory.buildAmmunition(type,side);
        }else{
            time --;
            return null;
        }
    }

    @Override
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
