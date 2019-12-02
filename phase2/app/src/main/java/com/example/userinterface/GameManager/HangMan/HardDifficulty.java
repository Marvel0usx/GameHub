package com.example.userinterface.GameManager.HangMan;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * An HardDifficulty class.
 */
class HardDifficulty extends Difficulty implements Serializable {

    /**
     * constructs a new HardDifficulty
     * @param words a list of hard words
     */
    HardDifficulty(List<String[]> words) {
        super(words);
        this.correctGuessPoints = 40;
        this.wrongGuessPoints = -20;
        this.type = "Hard";
    }

    /**
     * Select a random word at hard level.
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
        this.numLives = 4;
    }
}
