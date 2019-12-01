package com.example.userinterface.GameManager.HangMan;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class ModerateDifficulty extends Difficulty implements Serializable {

    public ModerateDifficulty(List<String[]> words) {
        super(words);
        this.correctGuessPoints = 30;
        this.wrongGuessPoints = -15;
        this.type = "Moderate";
    }

    void setWord() {
        Random random = new Random();
        int i = random.nextInt(words.size());
        String word = words.get(i)[0];
        String category = words.get(i)[1];
        this.keyword = word;
        this.hint = category;
    }

    void setNumLives() {
        this.numLives = 5;
    }

}
