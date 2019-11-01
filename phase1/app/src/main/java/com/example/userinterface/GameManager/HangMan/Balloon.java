package com.example.userinterface.GameManager.HangMan;

import android.view.View;
import android.widget.ImageView;

public class Balloon {

    private boolean display;
    private ImageView balloonImage;
    // private ImageView[] balloons;

    public Balloon(ImageView balloon){
        this.balloonImage = balloon;
    }

    protected void displayInitialBalloons(int numLives){
        this.balloonImage.setVisibility(View.VISIBLE);
    }

    protected void disappear(){
        this.balloonImage.setVisibility(View.INVISIBLE);
    }
    

}
