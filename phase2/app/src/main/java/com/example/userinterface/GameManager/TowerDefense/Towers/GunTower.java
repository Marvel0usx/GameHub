package com.example.userinterface.GameManager.TowerDefense.Towers;

public class GunTower extends MainTower implements Towers {

    GunTower() {
        this.setType("bullet");
        this.setRange(500);
        int WAITTIME = 25;
        this.setTime(WAITTIME);
        this.setWaitTime(WAITTIME);
    }
}
