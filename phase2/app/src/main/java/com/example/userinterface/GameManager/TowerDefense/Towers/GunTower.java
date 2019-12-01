package com.example.userinterface.GameManager.TowerDefense.Towers;

public class GunTower extends MainTower implements Towers {
    private final int WAITTIME = 25;

    public GunTower() {
        this.setType("bullet");
        this.setRange(500);
        this.setTime(WAITTIME);
        this.setWaitTime(WAITTIME);
    }
}
