package com.example.userinterface.GameManager.HangMan;


import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * An EasyDifficulty class.
 */
class EasyDifficulty extends Difficulty implements Serializable {

    /**
     * constructs a new EasyDifficulty
     * @param words a list of easy words
     */
    EasyDifficulty(List<String[]> words) {
        super(words);
        this.correctGuessPoints = 20;
        this.wrongGuessPoints = -20;
        this.type = "Easy";
    }

    /**
     * Select a random word at easy level.
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
        this.numLives = 6;
    }

}
