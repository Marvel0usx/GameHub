package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bullet extends SpaceObject {

    float velocity;

    Bullet(float x, float y, float velocity) {
        super(x, y);
        this.appearance = "*";
        this.paintText.setColor(Color.RED);
        this.paintText.setTextSize(36);
        this.velocity = velocity;
    }


    public void move(int height) {
        this.setY(this.getY() + this.velocity);
    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
    }
}
