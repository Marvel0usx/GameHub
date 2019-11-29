package com.example.userinterface.GameManager.TowerDefense;

interface TowerDefenseView {
    void setButtonVisible();

    void endGame(boolean won, int score);

    void showTowerPositions();
}
