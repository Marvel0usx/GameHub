package com.example.userinterface.GameManager.TowerDefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import com.example.userinterface.R;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public MainThread getThread() {
        return thread;
    }

    private MainThread thread;
    public TowerDefensePresenter towerDefensePresenter;
    boolean gameStart = false, gameOver = false;
    private Background background;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public void setTowerDefensePresenter(TowerDefensePresenter towerDefensePresenter) {
        this.towerDefensePresenter = towerDefensePresenter;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void update() {
        if (gameStart && !gameOver) {
            towerDefensePresenter.update();
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            background.draw(canvas);
            towerDefensePresenter.draw(canvas);

        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
        background = new Background(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.map)), getWidth(), getHeight() - getHeight() / 10);

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }
}
