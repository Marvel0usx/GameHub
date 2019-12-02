package com.example.userinterface.GameManager.TowerDefense.Towers;

class RocketTower extends MainTower implements Towers {

    RocketTower() {
        this.setType("rocket");
        int WAITTIME = 70;
        this.setWaitTime(WAITTIME);
        this.setTime(WAITTIME);
        this.setRange(400);
    }
}

