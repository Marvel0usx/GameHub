package com.example.userinterface.GameManager.HangMan;

import android.view.View;
import android.widget.ImageView;

/**
 * A balloon.
 */

public class Balloon {

    private ImageView balloonImage; //the image view of the balloon.

    /**
     * Initialize a new balloon with its image view.
     *
     * @param balloon
     */
    public Balloon(ImageView balloon) {
        this.balloonImage = balloon;
    }

    /**
     * Set the balloon invisible.
     */
    protected void disappear() {
        this.balloonImage.setVisibility(View.INVISIBLE);
    }


}
