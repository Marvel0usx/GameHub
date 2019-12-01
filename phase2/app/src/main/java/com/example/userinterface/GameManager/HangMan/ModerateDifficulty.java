package com.example.userinterface.GameManager.HangMan;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public class ModerateDifficulty extends Difficulty implements Serializable {

    public ModerateDifficulty(List<String[]> words) {
        super(words);
    }
    void setWord() {
        this.keyword = "ANDROID PLAY";

    }

    void setNumLives() {
        this.numLives = 5;
    }

}
