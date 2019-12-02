package com.example.userinterface.GameManager.HangMan;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * An ModerateDifficulty class.
 */
class ModerateDifficulty extends Difficulty implements Serializable {

    /**
     * constructs a new ModerateDifficulty
     * @param words a list of moderate words
     */
    ModerateDifficulty(List<String[]> words) {
        super(words);
        this.correctGuessPoints = 30;
        this.wrongGuessPoints = -15;
        this.type = "Moderate";
    }

    /**
     * Select a random word at moderate level.
     */
    void setWord() {
        Random random = new Random();
        int i = random.nextInt(words.size());
        String word = words.get(i)[0];
        String category = words.get(i)[1];
        this.keyword = word;
        this.hint = category;
    }

    /**
     * Set the number of lives.
     */
    void setNumLives() {
        this.numLives = 5;
    }

}
