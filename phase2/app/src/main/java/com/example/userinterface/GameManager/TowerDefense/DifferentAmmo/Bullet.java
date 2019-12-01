package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class Bullet extends Ammunition implements Ammo {

    public Bullet() {
        setDamage(1);
        setAppearance("🌕");
        setSpeed(7);
        setTextSize(20);
    }

}
