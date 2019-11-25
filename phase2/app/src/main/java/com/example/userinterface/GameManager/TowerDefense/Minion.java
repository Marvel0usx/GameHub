package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

public class Minion extends Enemy {

    Minion() {
        health = 10;
        speed = 4;
        score = 50;
        appearence = "👻";

    }

    public void move() {
        y += speed;
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
