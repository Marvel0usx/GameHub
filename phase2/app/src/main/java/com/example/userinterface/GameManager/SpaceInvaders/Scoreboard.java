package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Scoreboard {
    private float height, width;
    private int lives = 0;
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

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public int getLives() {
        return lives;
    }

    public String getAppearance() {
        return appearance;
    }

    public Paint getPaintText() {
        return paintText;
    }

    public int getLevel() {
        return level;
    }


}
