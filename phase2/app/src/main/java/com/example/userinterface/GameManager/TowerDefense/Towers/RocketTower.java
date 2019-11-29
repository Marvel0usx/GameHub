package com.example.userinterface.GameManager.TowerDefense.Towers;

public class RocketTower extends MainTower implements Towers {
    private final int WAITTIME = 70;

    public RocketTower() {
        type = "rocket";
        waitTime = WAITTIME;
        time = WAITTIME;
        range = 400;
    }
}

