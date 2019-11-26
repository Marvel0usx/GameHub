package com.example.userinterface.GameManager.TowerDefense.Towers;

public class RocketTower extends MainTower implements Towers {
    private final int WAITTIME = 100;
    public static final int COST = 50;

    public RocketTower(String side) {
        type = "rocket";
        waitTime = WAITTIME;
        time = WAITTIME;
        this.side = side;
        range = 400;
    }
}

