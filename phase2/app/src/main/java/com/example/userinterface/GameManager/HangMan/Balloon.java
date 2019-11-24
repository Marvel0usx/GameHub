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
     * @param balloon the ImageView object that this Balloon object represents
     */
    Balloon(ImageView balloon) {
        this.balloonImage = balloon;
        this.balloonImage.setVisibility(View.VISIBLE);
    }

    /**
     * Set the balloon invisible.
     */
    void disappear() {
        this.balloonImage.setVisibility(View.INVISIBLE);
    }


}
