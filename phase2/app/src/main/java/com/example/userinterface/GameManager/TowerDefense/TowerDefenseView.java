package com.example.userinterface.GameManager.TowerDefense;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.userinterface.R;

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

    void setInstructionVisible();
}

