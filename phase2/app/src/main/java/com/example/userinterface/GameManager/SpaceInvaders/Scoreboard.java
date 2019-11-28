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
    private int level;

    Scoreboard(float height, float width) {
        this.appearance = "";
        this.paintText.setColor(Color.RED);
        this.paintText.setTextSize(40);
        this.paintText.setFakeBoldText(true);
        this.paintText.setTypeface(Typeface.MONOSPACE);
        this.height = height;
        this.width = width;
    }

    void setAppearance(int lives, int score, int level) {
        this.appearance = "Score: " + score + "";
        this.lives = lives;
        this.level = level;
    }

    public void draw(Canvas canvas) {
        canvas.drawText(this.appearance, 250, this.height - 250, paintText);
        canvas.drawText("HP", 250, this.height - 200, paintText);
        canvas.drawRect(320, this.height - 220, this.lives + 300, this.height - 200, paintText);
        canvas.drawText("Stage: " + this.level, 40, 40, paintText);
    }

}
