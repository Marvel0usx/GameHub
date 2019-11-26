package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class InfromationBoard {
    private String money;
    private Paint paintText = new Paint();
    float height, width;
    int lives = 0;

    InfromationBoard(float height, float width) {
        this.money = "";
        this.paintText.setColor(Color.BLACK);
        this.paintText.setTextSize(40);
        this.paintText.setFakeBoldText(true);
        this.paintText.setTypeface(Typeface.SANS_SERIF);
        this.height = height;
        this.width = width;
    }

    void setAppearance(int money) {
        this.money = "Money: " + money + "";
    }

    public void draw(Canvas canvas) {
        canvas.drawText(this.money, width / 8, height * 12 / 16, paintText);
    }

}
