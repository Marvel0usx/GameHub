package com.example.userinterface.GameManager;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private int level;
    private int currScore;
    private int highScore;

    public int getNumOfGames() {
        return numOfGames;
    }

    private int numOfGames;

    public void addGame(){
        numOfGames +=1;
    }

    public String getEmail() {
        return email;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setNumOfGames(int numOfGames) {
        this.numOfGames = numOfGames;
    }

    public void addToCurrScore(int gameScore){
        currScore += gameScore;
    }

    public User(String email, int level){
        this.email = email;
        this.level = level;
    }

}
