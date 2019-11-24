package com.example.userinterface.GameManager.TowerDefense.Towers;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Bullet;


public class RocketTower implements Towers {
    private final int WAITTIME = 500;
    private int time = WAITTIME;

    @Override
    public Ammo generateBullet() {
        if (time <= 0){
            time = WAITTIME;
            return new Bullet();
        }else{
            time --;
            return null;
        }
    }
}

