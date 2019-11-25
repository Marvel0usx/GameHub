package com.example.userinterface.GameManager.TowerDefense.Towers;

public class GunTower extends MainTower implements Towers {
    private final int WAITTIME =50;
    public static final int COST = 40;

    public GunTower(String side){
        type = "bullet";
        waitTime = WAITTIME;
        time = WAITTIME;
        this.side = side;
        range = 500;
    }
}
