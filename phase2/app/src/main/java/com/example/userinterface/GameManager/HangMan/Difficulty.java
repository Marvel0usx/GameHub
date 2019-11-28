package com.example.userinterface.GameManager.HangMan;

import java.io.Serializable;

public abstract class Difficulty implements Serializable {

    String keyword;
    int numLives;

    abstract void setWord(); //set the keywordsss

    abstract void setNumLives();

    int getNumLives() {
        return numLives;
    }

}
