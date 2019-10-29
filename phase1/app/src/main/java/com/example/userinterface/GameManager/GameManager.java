package com.example.userinterface.GameManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

public class GameManager implements Serializable{
    private User user;

    public User getUser() {
        return user;
    }

    public GameManager(User user){
        this.user = user;
    }

    public void reLocate(Context context, Class aClass, int save){
        GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity(context);
        gameBackgroundActivity.execute("quit", user, save);
        user.setLevel(save);
        Intent intent = new Intent(context,aClass);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Game", this);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
