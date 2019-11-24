package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;

public class Ammunition implements Ammo {

    int damage;
    int x,y;

    public void update(int x, int y){

    }

    @Override
    public boolean ifHit(int x, int y){
        return true;
    }

}
