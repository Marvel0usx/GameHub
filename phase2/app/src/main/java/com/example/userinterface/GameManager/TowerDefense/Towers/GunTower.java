package com.example.userinterface.GameManager.TowerDefense.Towers;

public class GunTower extends MainTower implements Towers {
    private final int WAITTIME = 25;

    public GunTower() {
        setType("bullet");
        setRange(500);
        setTime(WAITTIME);
        setWaitTime(WAITTIME);
    }
}
