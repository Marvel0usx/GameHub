package com.example.userinterface.GameManager.TowerDefense.TheEnemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Orc extends Enemy {

    private boolean ifSpeedUp = false;

    public Orc() {
        setHealth(30);
        setAppearence("👹");
        setSpeed(1);
        setMoneyGain(20);
        setScore(60);
    }

    public void move() {
        if (getHealth() > 20)
            setY(getY()+getSpeed());
        else {
            if (!ifSpeedUp) {
                setSpeed(getSpeed()*3);
                ifSpeedUp = true;
            }
            setY(getY()+getSpeed());
        }

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawText(getAppearence(), getX(), getY(), paint);
        Paint paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(80);
        canvas.drawRect(this.getX(), this.getY()-60, this.getX()+ getHealth()*3, this.getY() -50, paintText);
        // decide each body parts' coordinates

    }

}
