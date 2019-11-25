package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class Bullet extends Ammunition implements Ammo {

    public Bullet(String direction){
        damage = 2;
        appearence = "🌕";
        speed = 7;
        this.direction = direction;
        textSize = 20;
    }

}
