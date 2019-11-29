package com.example.userinterface.GameManager.HangMan;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EasyDifficulty extends Difficulty implements Serializable {

    public EasyDifficulty(List<String[]> words) {
        super(words);
    }

    void setWord() {
        Random random = new Random();
        int i = random.nextInt(words.size());
        String word = words.get(i)[0];
        this.keyword = word;
    }

    void setNumLives() {
        this.numLives = 6;
    }

}
