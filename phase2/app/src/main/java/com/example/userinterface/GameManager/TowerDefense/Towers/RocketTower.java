package com.example.userinterface.GameManager.TowerDefense.Towers;

public class RocketTower extends MainTower implements Towers {
    private final int WAITTIME = 70;

    public RocketTower() {
        this.setType("rocket");
        this.setWaitTime(WAITTIME);
        this.setTime(WAITTIME);
        this.setRange(400);
    }
}

