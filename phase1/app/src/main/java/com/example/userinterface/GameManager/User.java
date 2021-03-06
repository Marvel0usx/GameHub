package com.example.userinterface.GameManager;

import java.io.Serializable;

/**
 * A User that plays through the games
 */
public class User implements Serializable {
    private String email;

    public StatTracker getStatTracker() {
        return statTracker;
    }

    private StatTracker statTracker;

    public String getEmail() {
        return email;
    }

    public User(String email, StatTracker statTracker) {
        this.email = email;
        this.statTracker = statTracker;
    }


}
