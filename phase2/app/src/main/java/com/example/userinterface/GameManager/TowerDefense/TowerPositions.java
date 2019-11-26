package com.example.userinterface.GameManager.TowerDefense;


import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.userinterface.R;

public class TowerPositions {
    private Button[] spots;
    static int width = 0, height = 0;
    private boolean towerClicked;

    public TowerPositions(Button[] spots) {
        this.spots = spots;
        for (Button button : this.spots) {
            button.setLayoutParams(new ConstraintLayout.LayoutParams(width / 7, height / 13));
        }
    }

    public void setXLocation() {
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

    public void setYLocation() {
        int i = -2;
        int x = 0;
        for (Button button : spots) {
            if (x % 2 == 0) {
                i += 2;
            }
            button.setY((float) (i * height / 14.5));
            x++;
        }

    }

    public void showAvailable(boolean show) {
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

    public boolean isTowerClicked() {
        return towerClicked;
    }

    public int getTowerNumber(Button button) {
        for (int i = 0; i < spots.length; i++) {
            if (button == spots[i]) {
                return i;
            }
        }
        return 0;
    }
}
