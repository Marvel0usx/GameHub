package com.example.userinterface.TowerDefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

public class Minion extends Enemy {

    String[] appearence;
    public Minion(){
        health = 10;
        speed = 1;
        reward = 10;
        x = 400;
        y = 0;
        appearence = new String[]{"  o  ", " /|\\ ", "  ^  ", " / \\ "};

    }

    public void move(){
        y += speed;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(20);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.RED);
        for (int i = 0; i<appearence.length;i++){
            canvas.drawText(appearence[i],x,y+((i+1)*20),paint);
        }
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void hit() {
        health -= TowerDefense.clicker;
        TowerDefense.clicker = 0;
        Log.d(health+"","this is the health"+health);
    }


}
