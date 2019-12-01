package com.example.userinterface.GameManager.TowerDefense.Towers;

public class RocketTower extends MainTower implements Towers {
    private final int WAITTIME = 70;

    public RocketTower() {
        setType("rocket");
        setWaitTime(WAITTIME);
        setTime(WAITTIME);
        setRange(400);
    }
}

