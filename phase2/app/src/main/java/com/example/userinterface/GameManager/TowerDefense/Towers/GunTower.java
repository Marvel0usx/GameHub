package com.example.userinterface.GameManager.TowerDefense.Towers;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Bullet;
import com.example.userinterface.GameManager.TowerDefense.Towers.Towers;

public class GunTower extends MainTower implements Towers {
    private final int WAITTIME = 100;

    public GunTower(String side){
        type = "bullet";
        waitTime = WAITTIME;
        time = WAITTIME;
        this.side = side;
    }
}
