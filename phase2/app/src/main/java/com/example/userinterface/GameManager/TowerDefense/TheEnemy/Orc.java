package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Orc extends Enemy {

    private boolean ifSpeedUp = false;

    public Orc() {
        this.setHealth(30);
        this.setAppearence("👹");
        this.setSpeed(1);
        this.setMoneyGain(20);
        this.setScore(60);
    }

    public void move() {
        if (this.getHealth() > 20)
            this.setY(this.getY() + this.getSpeed());
        else {
            if (!ifSpeedUp) {
                setSpeed(this.getSpeed() * 3);
                ifSpeedUp = true;
            }
            this.setY(this.getY() + this.getSpeed());
        }

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawText(this.getAppearence(), this.getX(), this.getY(), paint);
        Paint paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(80);
        canvas.drawRect(this.getX(), this.getY() - 60, this.getX() + this.getHealth()
                * 3, this.getY() - 50, paintText);
        // decide each body parts' coordinates

    }

}
