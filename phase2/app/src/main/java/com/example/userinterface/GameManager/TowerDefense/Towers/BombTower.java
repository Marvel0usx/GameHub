package com.example.userinterface.GameManager.TowerDefense.Towers;


public class BombTower extends MainTower implements Towers {
    private final int WAITTIME = 100;

    public BombTower() {
        this.setType("bomb");
        this.setWaitTime(WAITTIME);
        this.setRange(300);
        this.setTime(WAITTIME);
    }
}
