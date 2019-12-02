package com.example.userinterface.GameManager.SpaceInvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import com.example.userinterface.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class SpaceView extends SurfaceView implements SurfaceHolder.Callback {

    public SpaceInvaders Space;
    public MainThread thread;
    public boolean gameOver;
    private SpaceTrueView vSpace;
    private List<SpaceObject> spaceObjs;
    private List<Pair<Bitmap, Rect>> processedObjs;
    private Map<Class, Bitmap> appearance = new Hashtable<>();

    public SpaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        vSpace = new SpaceTrueView();
    }

    public SpaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        vSpace = new SpaceTrueView();
    }

    public SpaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        vSpace = new SpaceTrueView();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);


        Space.layout();
        initializingAppearanceMap();
        thread.setRunning(true);
        thread.start();
    }

    private void initializingAppearanceMap(){
        Bitmap blueBullet = BitmapFactory.decodeResource(getResources(), R.drawable.bluebullet);
        Bitmap redBullet = BitmapFactory.decodeResource(getResources(), R.drawable.redbullet);
        Bitmap player = BitmapFactory.decodeResource(getResources(), R.drawable.space_player);
        Bitmap boss = BitmapFactory.decodeResource(getResources(), R.drawable.space_boss);
        Bitmap enemy = BitmapFactory.decodeResource(getResources(), R.drawable.space_troop);

        appearance.put(PlayerBullet.class, blueBullet);
        appearance.put(EnemyBullet.class, redBullet);
        appearance.put(Player.class, player);
        appearance.put(Enemy.class, enemy);
        appearance.put(Boss.class, boss);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {}

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

    public void setSpace(SpaceInvaders Space) {
        this.Space = Space;
    }

    public MainThread getThread() {
        return thread;
    }

    public void update() {
        if (!Space.isGameOver()) {
            Space.run();
            spaceObjs = Space.getUpdate();
            processedObjs = parse(spaceObjs);

        } else {
            this.gameOver = true;
        }

    }

    public void draw(Canvas canvas) {
        if (canvas != null) {
            super.draw(canvas);
            vSpace.draw(canvas, processedObjs);
            vSpace.draw(canvas, Space.getScoreboard());
        }
    }

    private List<Pair<Bitmap, Rect>> parse(List<SpaceObject> spaceObjectList){
        List<Pair<Bitmap, Rect>> parsed = new ArrayList<>();
        Bitmap bmp = null;
        for (SpaceObject item : spaceObjectList){
            Rect rect = new Rect(item.getX(), item.getY(), item.getX() + item.getSize(),
                    item.getY() + item.getSize());
            bmp = appearance.get(item.getClass());
            Pair<Bitmap, Rect> pair = new Pair<>(bmp, rect);
            parsed.add(pair);
        }
        return parsed;
    }
}
