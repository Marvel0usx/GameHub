package com.example.userinterface.GameManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

public class BackgroundActivity extends AsyncTask<String,Void,String> {
    @SuppressLint("StaticFieldLeak")
    private Context context;
    BackgroundActivity (Context context){this.context = context;}


    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
