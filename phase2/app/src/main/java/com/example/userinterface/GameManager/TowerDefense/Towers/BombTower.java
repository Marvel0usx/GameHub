package com.example.userinterface.GameManager.TowerDefense.Towers;


public class BombTower extends MainTower implements Towers {

    BombTower() {
        this.setType("bomb");
        int WAITTIME = 100;
        this.setWaitTime(WAITTIME);
        this.setRange(300);
        this.setTime(WAITTIME);
    }
}
