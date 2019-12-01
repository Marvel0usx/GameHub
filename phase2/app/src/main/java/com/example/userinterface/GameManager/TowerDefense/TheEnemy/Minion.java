package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Minion extends Enemy {

    public Minion() {
        setHealth(5);
        setSpeed(5);
        setScore(50);
        setAppearence("👻");
        setMoneyGain(10);
    }

    public void move() {
        setY(getY()+getSpeed());
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawText(getAppearence(), getX(), getY(), paint);
        Paint paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(80);
        canvas.drawRect(this.getX(), this.getY()-60, this.getX()+ getHealth()*12, this.getY() -50, paintText);
        // decide each body parts' coordinates

    }

}
