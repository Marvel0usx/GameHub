package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class AmmoFactory {

    public Ammo buildAmmunition(String ammo, String direction) {
        switch (ammo) {
            case "bullet":
                return new Bullet(direction);
            case "bomb":
                return new Bomb(direction);
            case "rocket":
                return new Rocket(direction);
            default:
                return null;
        }
    }
}
