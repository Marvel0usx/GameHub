package com.example.userinterface.GameManager.HangMan;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.userinterface.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

public abstract class Difficulty implements Serializable {

    String keyword;
    private Balloon[] balloons; // an array of Balloon objects
    int numLives;

    abstract void setWord(); //set the keyword

    abstract void setNumLives();

    int getNumLives(){
        return numLives;
    }

}
