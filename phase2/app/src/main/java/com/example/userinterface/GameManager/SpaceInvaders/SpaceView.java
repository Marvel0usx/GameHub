package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Pair;

import java.util.List;

public class SpaceView {

    public void draw(Canvas canvas, List<Pair<Bitmap, Rect>> processedObjs) {
        for (Pair<Bitmap, Rect> p : processedObjs) {
            canvas.drawBitmap(p.first, null, p.second, null);
        }
    }
}
