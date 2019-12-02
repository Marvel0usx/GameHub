package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Dragon extends Enemy {
    private int waveSpecifications;

    public Dragon(int waveNumber) {
        waveSpecifications = waveNumber + 1;
        this.setHealth(80 * waveSpecifications);
        this.setDamage(waveSpecifications + 2);
        this.setSpeed(1);
        this.setAppearence("🐉");
        this.setScore(100 * waveSpecifications);
        this.setMoneyGain(50);
    }

    public void move() {
        setY(getY() + getSpeed());
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        int size;
        if (waveSpecifications == 1) {
            paint.setTextSize(150);
            size = 150;
        } else {
            paint.setTextSize(250);
            size = 250;
        }
        canvas.drawText(this.getAppearence(), this.getX(), this.getY(), paint);
        Paint paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(80);
        canvas.drawRect(this.getX(), this.getY() - size, this.getX() + (float)
                (getHealth() * 2), this.getY() - size + 10, paintText);
        // decide each body parts' coordinates

    }

}
