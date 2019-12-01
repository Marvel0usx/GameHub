package com.example.userinterface.GameManager.TowerDefense;


import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.userinterface.R;

/**
 * This is a class that holds all the possible locations that a tower can be put at.
 */
class TowerPositions {
    private Button[] spots;
    private static int width = 0, height = 0;
    private boolean towerClicked;

    /**
     * create a new TowerPositions where the buttons are given as a parameter
     * @param spots the buttons that can be clicked on for users to put down towers.
     */
    TowerPositions(Button[] spots) {
        this.spots = spots;
        for (Button button : this.spots) {
            button.setLayoutParams(new ConstraintLayout.LayoutParams(width / 7, height / 13));
        }
    }

    /**
     * Set the x location of every button.
     */
    void setXLocation() {
        int i = 0;
        for (Button button : spots) {
            if (i == 0) {
                button.setX(width - 19 * width / 24);
                i = 1;
            } else {
                button.setX(2 * width / 3);
                i = 0;
            }

        }
    }

    /**
     * Set the y location of every button.
     */
    void setYLocation() {
        int i = -2;
        int x = 0;
        for (Button button : spots) {
            if (x % 2 == 0) {
                i += 2;
            }
            button.setY((float) (i * height / 13.5));
            x++;
        }

    }

    /**
     * Turn all the available buttons into green targets. show the user what is available.
     */
    void showAvailable(boolean show) {
        for (Button button : spots) {
            if (button.isEnabled()) {
                if (show) {
                    button.setBackgroundResource(R.drawable.spotselect);
                    towerClicked = true;
                } else {
                    towerClicked = false;
                    button.setBackgroundResource(R.drawable.spot);
                }

            }
        }
    }

    /**
     * @return if there was a tower selected by the user.
     */
    boolean isTowerClicked() {
        return towerClicked;
    }

    /**
     * Ger the index of the button in spot.
     * @param button the button that is clicked
     * @return the index
     */
    int getTowerNumber(Button button) {
        for (int i = 0; i < spots.length; i++) {
            if (button == spots[i]) {
                return i;
            }
        }
        return 0;
    }
    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setWidth(int width) {
        TowerPositions.width = width;
    }

    public static void setHeight(int height) {
        TowerPositions.height = height;
    }
}
