package com.example.userinterface.GameManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.userinterface.GameManager.HangMan.HangManActivity;
import com.example.userinterface.GameManager.HangMan.HangManGameActivity;
import com.example.userinterface.GameManager.SpaceInvaders.SpaceActivity;
import com.example.userinterface.GameManager.TowerDefense.TowerDefenseActivity;

import java.io.Serializable;

public class GameManager implements Serializable, Games{
    private User user;
    private Class[] classes;
    private int gameNum;
    private int saved;

    User getUser() {
        gameNum = 0;
        return user;
    }

    public GameManager(User user){
        this.user = user;
        classes = new Class[]{MenuActivity.class, HangManActivity.class, HangManGameActivity.class, TowerDefenseActivity.class, SpaceActivity.class};

    }

    public void reLocate(Context context, int saved, int destination){
        GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity(context);
        gameBackgroundActivity.execute("quit", user, saved);
        user.setLevel(saved);
        Intent intent = new Intent(context,classes[destination]);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Game", this);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
