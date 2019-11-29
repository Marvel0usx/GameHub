package com.example.userinterface.GameManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.userinterface.R;

import java.util.concurrent.ExecutionException;

public class ScoreBoard extends AppCompatActivity {

    LinearLayout layout;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        layout = findViewById(R.id.linearLayout);
//        constraintLayout = new ConstraintLayout(this);
//        constraintLayout.setId(R.id.constraintlayout);
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
        String [][] totalInfo = new String[infoList.length][2];
        for (int i = 0; i< infoList.length;i++){
            totalInfo[i] = infoList[i].split(",");
        }
        sort(totalInfo,0, totalInfo.length-1);
        for (String[] users: totalInfo){
//            ConstraintSet constraintSet = new ConstraintSet();
//            constraintSet.clone(constraintLayout);
            String username = users[0];
            String highScore= users[1];
            TextView textView =  new TextView(getApplicationContext());
            String newUsername = username;
            for (int i = 0; i < 15-username.length();i++ ){
                newUsername+="- ";
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            textView.setLayoutParams(params);
            textView.setText("User"+": " + newUsername+"Score:"+highScore);
            textView.setTextSize(20);

//            constraintSet.connect(textView.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 18);
//            constraintSet.connect(textView2.getId(), ConstraintSet.LEFT, textView.getId(), ConstraintSet.RIGHT, 18);
//            constraintSet.connect(textView2.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 18);
//            constraintSet.applyTo(constraintLayout);
            layout.addView(textView);

        }
    }

    void sort(String[][] arr, int l, int r)
    {
        if (l < r)
        {
            int m = (l+r)/2;
            sort(arr, l, m);
            sort(arr , m+1, r);
            merge(arr, l, m, r);
        }
    }

    void merge(String [][] arr, int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        String[][] left = new String[n1][];
        String[][] right = new String[n2][];

        for (int i=0; i<n1; ++i)
            left[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            right[j] = arr[m +1+ j];
        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
            if (Integer.parseInt(left[i][1]) >= Integer.parseInt(right[j][1]))
            {
                arr[k] = left[i];
                i++;
            }
            else
            {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}
