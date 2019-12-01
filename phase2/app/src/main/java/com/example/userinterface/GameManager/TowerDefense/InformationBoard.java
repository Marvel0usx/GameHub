package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class InformationBoard {
    private String money;
    private Paint paintText = new Paint();
    float height, width;
    String lives;

    InformationBoard(float height, float width) {
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

    void setLivesAppearance(int lives){
        this.lives = "Lives: "+lives;
    }

    public void draw(Canvas canvas) {
        canvas.drawText(this.money, width / 8, height * 13/ 16, paintText);
        canvas.drawText(this.lives,width / 8, height * 13 / 16+45, paintText);
    }

}
