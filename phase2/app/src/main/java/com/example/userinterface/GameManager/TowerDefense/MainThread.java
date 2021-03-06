package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class MainThread extends Thread {
    public static Canvas canvas;
    private final SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;


    MainThread(SurfaceHolder surfaceHolder, GameView gameView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run() {
        while (running) {
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception ignored) {
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    void setRunning(boolean isRunning) {
        running = isRunning;
    }
}
