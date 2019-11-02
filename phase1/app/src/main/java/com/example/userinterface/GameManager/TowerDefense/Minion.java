package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

public class Minion extends Enemy {

    private String[] appearence;

    Minion() {
        health = 10;
        speed = 3;
        score = 50;
        appearence = new String[]{"  o  ", " /|\\ ", "  ^  ", " / \\ "};

    }

    public void move() {
        y += speed;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(20);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.RED);
        for (int i = 0; i < appearence.length; i++) {
            canvas.drawText(appearence[i], x, y + (i * 20), paint);
            // decide each body parts' coordinates
        }
    }

    public void hit(int num) {
        health -= num;
        Log.d(health + "", "this is the health" + health);
    }


}
