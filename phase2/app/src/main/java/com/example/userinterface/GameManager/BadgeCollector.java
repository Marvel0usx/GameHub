package com.example.userinterface.GameManager;

public interface BadgeCollector {
    /**
     * @return if user has collected fortunate badge
     */
    boolean collectFortunateBadge();

    /**
     * @return if user has collected strategic badge
     */
    boolean collectStrategicBadge();

    /**
     * @return if user has collected adventurous badge
     */
    boolean collectAdventurousBadge();
}
