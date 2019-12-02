package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Minion extends Enemy {

    public Minion() {
        this.setHealth(3);
        this.setSpeed(5);
        this.setScore(50);
        this.setAppearence("👻");
        this.setMoneyGain(10);
    }

    public void move() {
        this.setY(this.getY() + this.getSpeed());
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawText(this.getAppearence(), this.getX(), this.getY(), paint);
        Paint paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(80);
        canvas.drawRect(this.getX(), this.getY() - 60, this.getX() + this.getHealth()
                * 12, this.getY() - 50, paintText);
        // decide each body parts' coordinates

    }

}
