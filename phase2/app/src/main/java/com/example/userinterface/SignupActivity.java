package com.example.userinterface;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


/**
 * This is the sign up activity, it is used for signing up a new user.
 */
public class SignupActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etRePassword;
    String name, email, password, rePassword;
    Button btnRegister;

    //Check if the string passed in is in an email format.
    public static boolean isValid(String email) {
        String emailRegex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return (email).matches(emailRegex);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etName = findViewById(R.id.username);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.register);
        etRePassword = findViewById(R.id.rePassword);

        /*
         * The register button, takes in all the information and check if they are valid.
         * If so, register this user as a new user and store the information to the server.
         */
        btnRegister.setOnClickListener(v -> {
            getInfo();
            //Check if the email is in a valid format and the password is entered the same.
            if (!password.equals(rePassword) || !isValid(email)) {
                errorMessage("Incorrect Info", "Re-enter information.");
            } else {
                String task = "register";
                BackgroundActivity backgroundTask = new
                        BackgroundActivity(SignupActivity.this);
                getInfo();
                backgroundTask.execute(task, name, email, password);
                finish();
            }

        });
    }

    /**
     * Method used to get the users info on the UI
     */
    public void getInfo() {
        name = etName.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        rePassword = etRePassword.getText().toString();
    }

    /**
     * Show error message if there was an error that is being handled by.
     */
    public void errorMessage(String title, String message) {
        AlertDialog.Builder wrongInfo = new AlertDialog.Builder(SignupActivity.this);
        wrongInfo.setCancelable(true);
        wrongInfo.setTitle(title);
        wrongInfo.setMessage(message);
        wrongInfo.show();
        etPassword.setText("");
        etRePassword.setText("");
        etEmail.setText("");
        etName.setText("");
    }
}
