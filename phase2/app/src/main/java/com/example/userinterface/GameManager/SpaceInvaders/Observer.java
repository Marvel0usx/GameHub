package com.example.userinterface.GameManager.SpaceInvaders;

public interface Observer {
    // Method to update the observer, used by subject.
    void update(SpaceObject obj);

    // Register subject to observe
    void registerSubject(Subject subject);

    void unregisterSubject(Subject subject);

    void unregisterAll();
}
