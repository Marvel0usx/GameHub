package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int width, height;

    public Background(Bitmap res, int width, int height) {
        image = res;
        this.height = height;
        this.width = width;
    }

    public void draw(Canvas canvas) {
        Bitmap resized = Bitmap.createScaledBitmap(image, width, height, true);
        canvas.drawBitmap(resized, 0, 0, null);
    }


}
