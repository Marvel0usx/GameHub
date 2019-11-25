package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class Bomb extends Ammunition implements Ammo  {

    public Bomb(String direction){
        damage = 10;
        appearence = "💣";
        speed = 4;
        this.direction = direction;
    }
}
