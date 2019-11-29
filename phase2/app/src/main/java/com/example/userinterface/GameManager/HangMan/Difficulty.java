package com.example.userinterface.GameManager.HangMan;

import java.io.InputStream;
import java.io.Serializable;

public abstract class Difficulty implements Serializable {

    String keyword;
    int numLives;
    CSVReader csvReader;
    InputStream inputStream;

    public Difficulty(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    abstract void setWord(); //set the keywordsss

    abstract void setNumLives();

    int getNumLives() {
        return numLives;
    }

}
