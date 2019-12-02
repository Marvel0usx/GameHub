package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Pair;

import java.util.List;

public class SpaceTrueView {

    public void draw(Canvas canvas, List<Pair<Bitmap, Rect>> processedObjs){
        for (Pair<Bitmap, Rect> p : processedObjs){
            canvas.drawBitmap(p.first, null, p.second, null);
//            canvas.drawBitmap(p.first, 0, 0, new Paint());
        }
    }
}
