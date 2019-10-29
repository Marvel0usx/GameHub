package com.example.userinterface.GameManager;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private ScoreSystem scoreSystem;
    private int level;

    public String getEmail() {
        return email;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public User(String email, int level){
        this.email = email;
        this.level = level;
    }

    public void setScoreSystem(int games, int high, int current){
        this.scoreSystem = new ScoreSystem(games, high, current);
    }
}
