package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bullet extends SpaceObject {


    Bullet(int x, int y) {
        super(x, y);
        this.appearance = "*";
        this.paintText.setColor(Color.RED);
        this.paintText.setTextSize(36);
    }

    @Override
    public void move() {
        super.move();
    }
    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
    }
}
