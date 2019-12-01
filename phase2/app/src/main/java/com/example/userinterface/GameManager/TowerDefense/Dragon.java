package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;


class Dragon extends Enemy {
    private int waveSpecifications;
    Dragon(int waveNumber){
        waveSpecifications = waveNumber+1;
        health = 80*waveSpecifications;
        speed = 1;
        score = 100 * waveSpecifications;
        appearence = "🐉";
        moneyGain = 50;
        damage = waveSpecifications;

    }

    public void move() {
        y += speed;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        if (waveSpecifications == 1)
            paint.setTextSize(150);
        else
            paint.setTextSize(250);
        canvas.drawText(appearence, x, y, paint);
        // decide each body parts' coordinates

    }

}
