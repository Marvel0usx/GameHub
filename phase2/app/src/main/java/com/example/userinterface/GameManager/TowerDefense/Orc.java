package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

public class Orc extends Enemy {
    Orc() {
        health = 60;
        speed = 1;
        score = 60;
        appearence = "👹";

    }

    public void move() {
        if (health > 20)
            y += speed;
        else
            y += 3 * speed;

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
