package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

class ScoreBoardView {
    /**
     * Draws the scoreboard
     * @param canvas the canvas to be drawn on
     * @param scoreboard the scoreboard to be drawn
     */
    public void draw(Canvas canvas, Scoreboard scoreboard){
        canvas.drawText(scoreboard.getAppearance(), 250, scoreboard.getHeight() - 250, scoreboard.getPaintText());
        canvas.drawText("HP", 250, scoreboard.getHeight() - 200, scoreboard.getPaintText());
        canvas.drawRect(320, scoreboard.getHeight() - 220, scoreboard.getLives(), scoreboard.getHeight() - 200, scoreboard.getPaintText());
        canvas.drawText("Stage: " + scoreboard.getLevel(), 40, 40, scoreboard.getPaintText());
    }
}
