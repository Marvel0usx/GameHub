package com.example.userinterface.HangMan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.userinterface.R;

public class HangManActivity extends Activity {

    HangManManager hangManManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hang_man);
    }
}
