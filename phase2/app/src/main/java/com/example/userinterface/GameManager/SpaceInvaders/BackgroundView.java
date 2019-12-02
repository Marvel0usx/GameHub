package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BackgroundView {
    private Bitmap image;

    void configBackground(Bitmap bmp){
        image = bmp;
    }

    /**
     * draw the background of the gmae
     *
     * @param canvas where to draw the background
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, 0, 0, null);
    }
}
