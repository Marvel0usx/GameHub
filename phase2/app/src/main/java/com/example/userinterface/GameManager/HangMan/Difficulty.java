package com.example.userinterface.GameManager.HangMan;

import java.io.Serializable;
import java.util.List;

/**
 * A difficulty class
 */
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

    /**
     * sets the correct keyword
     */
    abstract void setWord();

    /**
     * sets number of lives
     */
    abstract void setNumLives(); //set the number of lives

    /**
     *
     * @return the number of lives
     */
    int getNumLives() {
        return numLives;
    }


}
