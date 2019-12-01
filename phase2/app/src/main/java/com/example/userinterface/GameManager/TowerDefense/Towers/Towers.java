package com.example.userinterface.GameManager.TowerDefense.Towers;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Enemies;

public interface Towers {
    Ammo generateBullet(Enemies enemy);

    void setLocation(int x, int y);

    int getX();

    int getY();

    int getRange();
}
