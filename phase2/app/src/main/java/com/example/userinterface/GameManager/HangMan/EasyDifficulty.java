package com.example.userinterface.GameManager.HangMan;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EasyDifficulty extends Difficulty {

    public EasyDifficulty(InputStream inputStream) {
        super(inputStream);
        this.csvReader = new CSVReader(inputStream);
    }

    void setWord() {
        List<String[]> result = csvReader.read();
        Random random = new Random();
        int i = random.nextInt(result.size());
        String word= result.get(i)[0];
        this.keyword = word;
    }

    void setNumLives() {
        this.numLives = 6;
    }

}
