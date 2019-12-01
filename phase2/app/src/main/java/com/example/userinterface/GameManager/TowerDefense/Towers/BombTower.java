package com.example.userinterface.GameManager.TowerDefense.Towers;


public class BombTower extends MainTower implements Towers {
    private final int WAITTIME = 100;

    public BombTower() {
        setType("bomb");
        setWaitTime(WAITTIME);
        setRange(300);
        setTime(WAITTIME);
    }
}
