package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

public class Orc extends Enemy {

    private boolean ifSpeedUp = false;

    Orc() {
        health = 60;
        speed = 1;
        score = 60;
        appearence = "👹";
        moneyGain = 30;
    }

    public void move() {
        if (health > 20)
            y += speed;
        else {
            if (!ifSpeedUp) {
                speed *= 3;
                ifSpeedUp = true;
            }

            y += speed;
        }

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.RED);
        canvas.drawText(appearence, x, y, paint);
        // decide each body parts' coordinates

    }

}
