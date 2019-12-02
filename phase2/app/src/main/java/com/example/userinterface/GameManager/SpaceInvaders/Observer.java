package com.example.userinterface.GameManager.SpaceInvaders;

public interface Observer {
    /**
     * Method to update the observer of what has changed.
     * Used by subjects.
     * @param obj SpaceObject that has changed.
     */
    void update(SpaceObject obj);

    /**
     * Register an subject to observe and get update from it.
     * @param subject subject to be observed.
     */
    void registerSubject(Subject subject);

    /**
     * Method to unregister an subject that this observer is observing.
     * @param subject the subject that has been observing.
     */
    void unregisterSubject(Subject subject);

    /**
     * Method to unregister to all of the subjects.
     */
    void unregisterAll();
}
