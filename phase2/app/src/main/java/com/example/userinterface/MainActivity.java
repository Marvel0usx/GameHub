package com.example.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The Main activity, the activity that the program starts with.
 */
public class MainActivity extends AppCompatActivity {

    EditText etPassword;
    Button btnRegister;
    Button btnLogin;
    EditText etEmail;
    String stringEmail, stringPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegister = findViewById(R.id.register);
        etPassword = findViewById(R.id.password);
        etEmail = findViewById(R.id.email);
        btnLogin = findViewById(R.id.login);

        /*
         * This is the button listener that will login the user if the user had the correct user
         * information
         */
        btnLogin.setOnClickListener(v -> {
            stringEmail = etEmail.getText().toString();
            stringPassword = etPassword.getText().toString();
            String task = "login";
            BackgroundActivity backgroundTask = new BackgroundActivity(MainActivity.this);

            etEmail.setText("");
            etPassword.setText("");
            backgroundTask.execute(task, stringEmail, stringPassword);
        });

        /*
         * This is a button listener that directs the button to the register page.
         */
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}
