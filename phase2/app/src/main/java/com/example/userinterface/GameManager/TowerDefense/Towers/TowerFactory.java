package com.example.userinterface.GameManager.TowerDefense.Towers;


public class TowerFactory {

    public Towers buildTower(String tower, String side){
        Towers newTower;
        switch (tower){
            case "guntower":
                newTower = new GunTower(side);
                break;
            case "rockettower":
                newTower = new RocketTower(side);
                break;
            case "bombtower":
                newTower = new BombTower(side);
                break;
             default:
                 newTower = null;
                 break;
        }
        return newTower;
    }
}
