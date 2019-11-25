package com.example.userinterface.GameManager.TowerDefense.Towers;

public class BombTower extends MainTower implements Towers {
    private final int WAITTIME = 100;
    public static final int COST = 60;

    public BombTower(String side){
        type = "bomb";
        waitTime = WAITTIME;
        time = WAITTIME;
        this.side = side;
        range = 300;
    }
}
