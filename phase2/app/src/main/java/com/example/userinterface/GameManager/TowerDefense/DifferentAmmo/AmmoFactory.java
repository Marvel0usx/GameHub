package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

public class AmmoFactory {

    public Ammo buildAmmunition(String ammo) {
        switch (ammo) {
            case "bullet":
                return new Bullet();
            case "bomb":
                return new Bomb();
            case "rocket":
                return new Rocket();
            default:
                return null;
        }
    }
}
