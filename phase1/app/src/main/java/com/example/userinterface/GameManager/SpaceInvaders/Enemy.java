package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

public class Enemy extends Ship {

    int directionVector;

    public Enemy(float x, float y, int lives){
        super(x,y,lives);
        this.appearance = "E";
        this.paintText.setColor(Color.GREEN);
        this.paintText.setTextSize(36);
        this.directionVector = 20;
    }

    @Override
    public void move(){
        //to be implemented as a movement pattern
        this.setX(this.getX() + this.directionVector);
    }
    @Override
    public Bullet shoot(){
        return new Bullet(this.getX(), this.getY()+1 , 15);
    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
    }
    public void shift(){
        this.directionVector = this.directionVector * -1;
    }
}
