package com.example.userinterface.GameManager.HangMan;

import java.io.InputStream;

public class HardDifficulty extends Difficulty {

    public HardDifficulty(InputStream inputStream) {
        super(inputStream);
        this.csvReader = new CSVReader(inputStream);
    }


    void setWord() {
        this.keyword = "BULLETPROOF";

    }

    void setNumLives() {
        this.numLives = 4;
    }
}
