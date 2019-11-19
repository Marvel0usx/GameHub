package com.example.userinterface.GameManager.TowerDefense;


import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

public class TowerPositions {
    static int width = 0, height = 0;
    Button button;

    public TowerPositions(Button button){
        this.button = button;
        this.button.setLayoutParams(new ConstraintLayout.LayoutParams(width, height));
    }

    public void setButtonX(int x){
        button.setX(x);
    }

    public void setButtonY(int y){
        button.setY(y);
    }

}
