package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Color;

class EnemyBullet extends Bullet {
    EnemyBullet(int x, int y, int damage, int speed){
        super(x, y, damage, speed);
        this.paintText.setColor(Color.BLUE);
    }
}
