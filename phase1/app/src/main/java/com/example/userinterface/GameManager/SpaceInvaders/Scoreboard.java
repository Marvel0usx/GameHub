package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Scoreboard {
    private String appearance;
    private Paint paintText = new Paint();

    Scoreboard() {
        this.appearance = "";
        this.paintText.setColor(Color.CYAN);
        this.paintText.setTextSize(80);
    }

    void setAppearance(int lives, int score) {
        this.appearance = "Lives: "+ lives + " " + "Score: " + score;
    }

    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, 200, 200, paintText);
    }

}
