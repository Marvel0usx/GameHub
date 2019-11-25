package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

import android.graphics.Canvas;

import com.example.userinterface.GameManager.TowerDefense.Enemy;

public interface Ammo {
    void update(Enemy enemy);

    boolean ifHit(int x);

    void setLocation(int x, int y);

    void draw(Canvas canvas);
}
