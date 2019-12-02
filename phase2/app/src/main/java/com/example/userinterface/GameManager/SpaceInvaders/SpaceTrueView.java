package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Pair;

import java.util.List;

public class SpaceTrueView {

    public void draw(Canvas canvas, List<Pair<Bitmap, Rect>> processedObjs){
        for (Pair<Bitmap, Rect> p : processedObjs){
            canvas.drawBitmap(p.first, null, p.second, null);
        }
    }

    public void draw(Canvas canvas, Scoreboard scoreboard){
        canvas.drawText(scoreboard.getAppearance(), 250, scoreboard.getHeight() - 250, scoreboard.getPaintText());
        canvas.drawText("HP", 250, scoreboard.getHeight() - 200, scoreboard.getPaintText());
        canvas.drawRect(320, scoreboard.getHeight() - 220, scoreboard.getLives() + 300, scoreboard.getHeight() - 200, scoreboard.getPaintText());
        canvas.drawText("Stage: " + scoreboard.getLevel(), 40, 40, scoreboard.getPaintText());
    }
}
