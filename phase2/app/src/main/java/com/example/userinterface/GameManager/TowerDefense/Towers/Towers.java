package com.example.userinterface.GameManager.TowerDefense.Towers;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Enemies;
import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Enemy;

public interface Towers {
    Ammo generateBullet(Enemies enemy);

    void setLocation(int x, int y);

    int getX();

    int getY();

    int getRange();
}
