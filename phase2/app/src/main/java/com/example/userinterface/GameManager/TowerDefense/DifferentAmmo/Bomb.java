package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class Bomb extends Ammunition implements Ammo {

    public Bomb() {
        setDamage(15);
        setAppearance( "💣");
        setSpeed(6);
    }
}
