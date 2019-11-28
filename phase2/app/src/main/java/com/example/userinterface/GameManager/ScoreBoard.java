package com.example.userinterface.GameManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.userinterface.R;

import java.util.concurrent.ExecutionException;

public class ScoreBoard extends AppCompatActivity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        layout = findViewById(R.id.linearLayout);
        show();
    }

    public void show(){
        String info = "";
        GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity(this);
        try {
            info = gameBackgroundActivity.execute("scoreboard").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String [] infoList = info.split(";");
        for (String users: infoList){
            String username = users.split(",")[0];
            String highscore= users.split(",")[1];
            TextView textView =  new TextView(getApplicationContext());
            textView.setText("User"+": " + username+"      Score:"+highscore);
            textView.setTextSize(30);
            layout.addView(textView);
        }
    }
}
