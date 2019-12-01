package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class Rocket extends Ammunition implements Ammo {

    public Rocket() {
        this.setDamage(10);
        this.setAppearance("🚀");
        this.setSpeed(6);
    }

}
