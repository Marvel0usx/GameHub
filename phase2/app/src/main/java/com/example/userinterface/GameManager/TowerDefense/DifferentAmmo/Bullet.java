package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class Bullet extends Ammunition implements Ammo {

    public Bullet() {
        this.setDamage(1);
        this.setAppearance("🌕");
        this.setSpeed(7);
        this.setTextSize(20);
    }

}
