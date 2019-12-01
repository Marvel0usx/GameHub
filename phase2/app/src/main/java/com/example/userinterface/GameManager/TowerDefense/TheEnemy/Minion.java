package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Minion extends Enemy {

    public Minion() {
        health = 5;
        speed = 4;
        score = 50;
        appearence = "👻";
        moneyGain = 10;
    }

    public void move() {
        y += speed;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawText(appearence, x, y, paint);
        Paint paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(80);
        canvas.drawRect(this.getX(), this.getY()-60, this.getX()+ health*12, this.getY() -50, paintText);
        // decide each body parts' coordinates

    }

}
