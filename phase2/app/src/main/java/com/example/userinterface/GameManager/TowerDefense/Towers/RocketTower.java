package com.example.userinterface.GameManager.TowerDefense.Towers;

public class RocketTower extends MainTower implements Towers {
    private final int WAITTIME = 100;

    public RocketTower() {
        type = "rocket";
        waitTime = WAITTIME;
        time = WAITTIME;
        range = 400;
    }
}

