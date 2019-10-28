package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

public class Enemy extends Ship {
    public Enemy(int x, int y, int lives){
        super(x,y,lives);
        this.appearance = "E";
        this.paintText.setColor(Color.GREEN);
        this.paintText.setTextSize(36);
    }

    @Override
    public void move(){
        //to be implemented as a movement pattern
        this.setY(this.getY() + 5);
    }

    @Override
    public void shoot(){
        //fires bullet objects that damage player
    }
    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
    }
}
