package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class Bomb extends Ammunition implements Ammo {

    public Bomb() {
        this.setDamage(15);
        this.setAppearance( "💣");
        this.setSpeed(6);
    }
}
