package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int width, height;

    /**
     * The background of the tower defense game
     *
     * @param res    the bit map or the image of the background
     * @param width  the width of the bitmap
     * @param height the height of the bitmap
     */
    Background(Bitmap res, int width, int height) {
        image = res;
        this.height = height;
        this.width = width;
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
