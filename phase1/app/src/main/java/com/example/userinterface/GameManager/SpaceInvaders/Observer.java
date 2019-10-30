package com.example.userinterface.GameManager.SpaceInvaders;

public interface Observer {
    // Method to update the observer, used by subject.
    public void update();

    // Register subject to observe
    public void registerSubject(Subject subject);

    public void unregisterSubject(Subject subject);

    public void unregisterAll();
}
