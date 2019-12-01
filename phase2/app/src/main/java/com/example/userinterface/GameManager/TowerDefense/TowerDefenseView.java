package com.example.userinterface.GameManager.TowerDefense;

/**
 * This is an interface for the view object in tower defense.
 */
interface TowerDefenseView {
    /**
     * This method will set all the buttons in the view object visible,
     */
    void setButtonVisible();

    /**
     * This method will specify what to do when a game has ended
     *
     * @param won   if the game has been won
     * @param score the score of this game
     */
    void endGame(boolean won, int score);

    /**
     * This method will show all the positions to place a tower.
     */
    void showTowerPositions();
}
