package com.example.userinterface.GameManager.HangMan;

import java.io.Serializable;
import java.util.List;

abstract class Difficulty implements Serializable {

    String keyword;
    int numLives;
    List<String[]> words;
    String hint;
    Integer correctGuessPoints;
    Integer wrongGuessPoints;
    String type;

    Difficulty(List<String[]> words) {
        this.words = words;
    }

    abstract void setWord(); //set the keyword

    abstract void setNumLives();

    int getNumLives() {
        return numLives;
    }


}
