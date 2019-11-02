package com.example.userinterface.GameManager.SpaceInvaders;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class CollisionDetector implements Observer {
    private List<Subject> subjects = new ArrayList<>();

    // Implements Observer
    // Register subject to observe
    public void registerSubject(Subject subject) {
        // if this is an enemy ship, it's subject will be player and player's bullet;
        // player, enemy and their bullets.
        if (!subjects.contains(subject))
            subjects.add(subject);
    }

    @Override
    public void update(SpaceObject object) {
    }

    public void unregisterSubject(Subject subject) {
        subjects.remove(subject);
    }

    public void unregisterAll() {
        subjects.clear();
    }

    // Utils
}
