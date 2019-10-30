package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

abstract class Bullet extends SpaceObject{
    Bullet(int x, int y){
        super(x, y);
        this.appearance = "*";
        this.paintText.setColor(Color.RED);
        this.paintText.setTextSize(36);
    }

    Bullet(int x, int y, int damage, int ySpeed){
        super(x, y, damage, ySpeed);
        this.appearance = "*";
        this.paintText.setColor(Color.RED);
        this.paintText.setTextSize(36);
    }

    // Implements Subject Interface
    // If this is a bullet fired by enemy, the observer will be player
    // Method to update this object
    public int[] getUpdate(Observer obj) {
        return new int[] {getX(), getY()};
    }

    // Utils
    void move() {
        setChanged();
        setX(getX() + getxSpeed());
        setY(getY() + getySpeed());
    }

    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
    }
}
