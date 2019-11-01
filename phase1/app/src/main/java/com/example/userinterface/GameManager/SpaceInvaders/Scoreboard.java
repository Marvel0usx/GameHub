package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Scoreboard {


    private String appearance;
    private int score;
    private int lives;
    private Paint paintText = new Paint();

    public Scoreboard(){
        this.appearance = "";
        this.paintText.setColor(Color.CYAN);
        this.paintText.setTextSize(80);
    }

    public void setAppearance(int lives, int score) {
        this.appearance = lives + " " + score;
    }

    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, 500, 200, paintText);
    }

}
