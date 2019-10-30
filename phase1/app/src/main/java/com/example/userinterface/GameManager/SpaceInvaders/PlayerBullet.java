package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Color;

import java.util.ArrayList;

public class PlayerBullet extends Bullet {
    PlayerBullet(int x, int y, int damage, int ySpeed){
        super(x, y, damage, ySpeed);
        paintText.setColor(Color.RED);
    }
}
