package com.example.userinterface.GameManager.TowerDefense.Towers;

public class GunTower extends MainTower implements Towers {
    private final int WAITTIME = 50;

    public GunTower() {
        type = "bullet";
        waitTime = WAITTIME;
        time = WAITTIME;
        range = 500;
    }
}
