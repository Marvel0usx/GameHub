package com.example.userinterface.GameManager;

import java.io.Serializable;

/**
 * A User that plays through the games
 */
public class User implements Serializable {

    private String email;
    private StatTracker statTracker;

    public User(String email, StatTracker statTracker) {
        this.email = email;
        this.statTracker = statTracker;
    }

    public StatTracker getStatTracker() {
        return statTracker;
    }

    String getEmail() {
        return email;
    }


}
