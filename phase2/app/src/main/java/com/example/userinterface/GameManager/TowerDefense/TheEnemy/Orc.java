package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Orc extends Enemy {

    private boolean ifSpeedUp = false;

    public Orc() {
        health = 30;
        speed = 1;
        score = 60;
        appearence = "👹";
        moneyGain = 20;
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
        canvas.drawText(appearence, x, y, paint);
        Paint paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(80);
        float x  = (float) ((50*0.25)/health);
        canvas.drawRect(this.getX(), this.getY()-60, this.getX()+ health*12, this.getY() -50, paintText);
        // decide each body parts' coordinates

    }

}
