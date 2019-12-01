package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

import android.graphics.Canvas;

import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Enemies;
import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Enemy;

public interface Ammo {
    void update();

    boolean ifHit();

    void setLocation(int x, int y);

    void draw(Canvas canvas);

    int getDamage();

    void setTarget(Enemies enemy);

    void predictYPosition();

    double getPrey();
}
