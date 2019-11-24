package com.example.userinterface.GameManager.TowerDefense.Towers;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;

public interface Towers {
    Ammo generateBullet();

    void setLocation(int x, int y);

    int getX();

    int getY();
}
