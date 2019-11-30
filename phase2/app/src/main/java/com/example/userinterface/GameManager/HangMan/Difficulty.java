package com.example.userinterface.GameManager.HangMan;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public abstract class Difficulty implements Serializable {

    String keyword;
    int numLives;
    List<String[]> words;
    String category;

    public Difficulty(List<String[]> words) {
        this.words = words;
    }

    abstract void setWord(); //set the keywordsss

    abstract void setNumLives();

    int getNumLives() {
        return numLives;
    }


}
