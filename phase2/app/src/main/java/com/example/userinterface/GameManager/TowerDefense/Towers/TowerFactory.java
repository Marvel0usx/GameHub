package com.example.userinterface.GameManager.TowerDefense.Towers;


public class TowerFactory {

    public Towers buildTower(String tower) {
        Towers newTower;
        switch (tower) {
            case "guntower":
                newTower = new GunTower();
                break;
            case "rockettower":
                newTower = new RocketTower();
                break;
            case "bombtower":
                newTower = new BombTower();
                break;
            default:
                newTower = null;
                break;
        }
        return newTower;
    }
}
