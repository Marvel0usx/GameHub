package com.example.userinterface.GameManager.TowerDefense.Towers;

public class BombTower extends MainTower implements Towers {
    private final int WAITTIME = 100;

    public BombTower() {
        type = "bomb";
        waitTime = WAITTIME;
        time = WAITTIME;
        range = 300;
    }
}
