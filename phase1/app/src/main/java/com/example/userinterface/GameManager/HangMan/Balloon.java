package com.example.userinterface.GameManager.HangMan;

import android.widget.ImageView;

public class Balloon {

    private boolean display;
    private ImageView balloonImage;

    public Balloon(ImageView balloon) {
        this.balloonImage = balloon;
        display = true;
    }

    protected void displayInitialBalloons(){
        /** display all 6 initial balloons */
    }

    protected void disappear(){
        /** make a balloon disappear when user gets the wrong letter */
        display = false;
    }
    

}
