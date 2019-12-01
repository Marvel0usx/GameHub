package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

class Bomb extends Ammunition implements Ammo {

    Bomb() {
        this.setDamage(15);
        this.setAppearance("💣");
        this.setSpeed(6);
    }
}
