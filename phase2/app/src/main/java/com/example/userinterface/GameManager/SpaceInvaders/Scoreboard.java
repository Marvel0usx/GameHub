package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Scoreboard {
    float height, width;
    int lives = 0;
    private String appearance;
    private Paint paintText = new Paint();

    Scoreboard(float height, float width) {
        this.appearance = "";
        this.paintText.setColor(Color.RED);
        this.paintText.setTextSize(40);
        this.paintText.setFakeBoldText(true);
        this.paintText.setTypeface(Typeface.MONOSPACE);
        this.height = height;
        this.width = width;
    }

    void setAppearance(int lives, int score) {
        this.appearance = "Score: " + score + "";
        this.lives = lives;
    }

    public void draw(Canvas canvas) {
        canvas.drawText(this.appearance, 250, this.height - 250, paintText);
        canvas.drawText("HP", 250, this.height - 200, paintText);
        canvas.drawRect(300, this.height - 220, this.lives + 300, this.height - 200, paintText);
    }

}
