package com.example.userinterface.GameManager.SpaceInvaders;

public interface Subject {
    // Method to register an observer that observes this subject
    void registerObserver(Observer observer);

    // Method to unregister an observer, e.g. unregister when a ship is destroyed
    void unregisterObserver(Observer observer);

    // Method to unregister all
    void unregisterAllObservers();

    // Method indicates changes to this subject
    boolean hasChange();

    void setChanged();

    void clearChanged();

    //method to notify observers of change
    void notifyObservers();

    //method to get updates from subject
    Object getUpdate(Observer obs);
}
