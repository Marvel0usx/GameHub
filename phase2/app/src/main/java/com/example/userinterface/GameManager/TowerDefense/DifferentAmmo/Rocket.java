package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class Rocket extends Ammunition implements Ammo {

    public Rocket(String direction) {
        damage = 15;
        appearence = "🚀";
        speed = 5;
        this.direction = direction;
    }

}
