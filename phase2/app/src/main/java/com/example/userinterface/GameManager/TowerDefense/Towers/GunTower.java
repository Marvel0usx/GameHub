package com.example.userinterface.GameManager.TowerDefense.Towers;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Bullet;
import com.example.userinterface.GameManager.TowerDefense.Towers.Towers;

public class GunTower implements Towers {
    private final int WAITTIME = 300;
    private int time = WAITTIME;

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
