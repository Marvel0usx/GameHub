package com.example.userinterface.GameManager.HangMan;

import java.io.InputStream;

public class ModerateDifficulty extends Difficulty {

    public ModerateDifficulty(InputStream inputStream) {
        super(inputStream);
        this.csvReader = new CSVReader(inputStream);
    }

    void setWord() {
        this.keyword = "ANDROID";

    }

    void setNumLives() {
        this.numLives = 5;
    }

}
