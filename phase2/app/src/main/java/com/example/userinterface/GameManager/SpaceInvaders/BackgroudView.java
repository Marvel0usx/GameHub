package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BackgroudView {
    private Bitmap image;
    private int width, height;

    void configBackground(Bitmap img, int width, int height){
        image = img;
        this.width = width;
        this.height = height;
    }

    /**
     * draw the background of the gmae
     *
     * @param canvas where to draw the background
     */
    public void draw(Canvas canvas) {
        Bitmap resized = Bitmap.createScaledBitmap(image, width, height, true);
        canvas.drawBitmap(resized, 0, 0, null);
    }
}
