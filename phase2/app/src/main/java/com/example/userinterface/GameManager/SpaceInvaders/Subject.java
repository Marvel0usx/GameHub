package com.example.userinterface.GameManager.SpaceInvaders;

public interface Subject {
    /**
     * Method to register an observer that observes this subject
     *
     * @param observer the observer that is going to observe this subject
     */
    void registerObserver(Observer observer);

    /**
     * Method to unregister all
     */
    void unregisterAllObservers();

    /**
     * Method indicates changes to this subject.
     *
     * @return boolean that indicates changes
     */
    boolean hasChange();

    /**
     * Method to set change flag.
     */
    void setChanged();

    /**
     * Method to clear the change flag.
     */
    void clearChanged();

    /**
     * method to notify observers of change.
     */
    void notifyObservers();

    /**
     * method to get updates from subject
     *
     * @param obs the observer that this subject is going to inform update
     * @return returns new positions of this subject
     */
    int[] getUpdate(Observer obs);
}
