package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * The information board for the game.
 * it updates as the attribute in the game updates and draw it on the screen
 */
public class InformationBoard {
    private String money;
    private Paint paintText = new Paint();
    private float height, width;
    private String lives;

    InformationBoard(float height, float width) {
        this.money = "";
        this.paintText.setColor(Color.BLACK);
        this.paintText.setTextSize(40);
        this.paintText.setFakeBoldText(true);
        this.paintText.setTypeface(Typeface.SANS_SERIF);
        this.height = height;
        this.width = width;
    }

    /**
     * set the money appearance to what is passed in.
     * @param money the amount of money the user has
     */
    void setMoneyAppearance(int money) {
        this.money = "Money: " + money + "";
    }

    /**
     * set the amount of lives of the user
     * @param lives the amount of lives the user has left
     */
    void setLivesAppearance(int lives){
        this.lives = "Lives: "+lives;
    }

    /**
     * draw the appearances used to the screen
     * @param canvas
     */
    public void draw(Canvas canvas) {
        canvas.drawText(this.money, width / 8, height * 13/ 16-10, paintText);
        canvas.drawText(this.lives,width / 8, height * 13 / 16+15, paintText);
    }

}
