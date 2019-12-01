package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Dragon extends Enemy {
    private int waveSpecifications;
    public Dragon(int waveNumber){
        waveSpecifications = waveNumber+1;
        health = 80*waveSpecifications;
        speed = 1;
        score = 100 * waveSpecifications;
        appearence = "🐉";
        moneyGain = 50;
        damage = waveSpecifications + 2;

    }

    public void move() {
        y += speed;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        int size;
        if (waveSpecifications == 1) {
            paint.setTextSize(150);
            size = 150;
        }else {
            paint.setTextSize(250);
            size = 250;
        }
        canvas.drawText(appearence, x, y, paint);
        Paint paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(80);
        canvas.drawRect(this.getX(), this.getY()-size, this.getX()+ (float)(health*2), this.getY() -size+10, paintText);
        // decide each body parts' coordinates

    }

}
