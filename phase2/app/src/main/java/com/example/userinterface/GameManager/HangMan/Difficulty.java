package com.example.userinterface.GameManager.HangMan;

import java.io.Serializable;

public abstract class Difficulty implements Serializable {

    String keyword;
    int numLives;
    private Balloon[] balloons; // an array of Balloon objects

    abstract void setWord(); //set the keywordsss

    abstract void setNumLives();

    int getNumLives() {
        return numLives;
    }

}
