package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    public static Canvas canvas;
    private final SurfaceHolder surfaceHolder;
    private SpaceView spaceView;
    private boolean isRunning;

    MainThread(SurfaceHolder surfaceHolder, SpaceView spaceView) {
        this.surfaceHolder = surfaceHolder;
        this.spaceView = spaceView;
    }

    @Override
    public void run() {
        while (isRunning) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.spaceView.update();
                    this.spaceView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
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
        this.isRunning = isRunning;
    }
}

