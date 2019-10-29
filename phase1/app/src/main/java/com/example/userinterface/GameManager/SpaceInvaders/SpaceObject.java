package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Paint;

abstract class SpaceObject {
    private float x;
    private float y;
    public String appearance;
    public Paint paintText = new Paint();

    public double getSpeed() {
        return speed;
    }


    public void setSpeed(double speed) {
        this.speed = speed;
    }

    private double speed;

    SpaceObject(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void move(){

    }

    public void draw(Canvas canvas){}
}
