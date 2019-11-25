package com.example.userinterface.GameManager.TowerDefense.Towers;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.Enemy;

public interface Towers {
    Ammo generateBullet(Enemy enemy);

    void setLocation(int x, int y);

    int getX();

    int getY();

    int getRange();
}
