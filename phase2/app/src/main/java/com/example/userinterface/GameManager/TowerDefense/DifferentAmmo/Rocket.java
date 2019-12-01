package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

class Rocket extends Ammunition implements Ammo {

    Rocket() {
        this.setDamage(10);
        this.setAppearance("🚀");
        this.setSpeed(6);
    }

}
