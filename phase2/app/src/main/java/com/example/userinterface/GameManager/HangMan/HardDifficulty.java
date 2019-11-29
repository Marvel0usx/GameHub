package com.example.userinterface.GameManager.HangMan;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public class HardDifficulty extends Difficulty implements Serializable {

    public HardDifficulty(List<String[]> words) {
        super(words);
    }


    void setWord() {
        this.keyword = "BULLETPROOF";

    }

    void setNumLives() {
        this.numLives = 4;
    }
}
