package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class Bullet extends Ammunition implements Ammo {

    public Bullet(){
        damage = 2;
    }

    @Override
    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean ifHit(int x, int y) {
        return false;
    }

}
