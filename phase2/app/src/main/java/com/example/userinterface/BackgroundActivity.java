package com.example.userinterface;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.userinterface.GameManager.GameActivity;
import com.example.userinterface.GameManager.MenuActivity;
import com.example.userinterface.GameManager.StatTracker;
import com.example.userinterface.GameManager.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static java.lang.Integer.parseInt;

/**
 * This is the backend code, to retrieve and store information to the server.
 */
public class BackgroundActivity extends AsyncTask<String, Void, String> {
    private SharedPreferences preferences;

    @SuppressLint("StaticFieldLeak")
    private Context context;

    BackgroundActivity(Context ctx) {
        this.context = ctx;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... params) {

        preferences = context.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("flag", "0");
        editor.apply();

        String urlRegistration = "http://159.203.20.150/register.php";
        String urlLogin = "http://159.203.20.150/login.php";
        String task = params[0];

        /*
          This is the register part of the backend, where userinfo will be passed on to the server
          and stored in the proper order.
         */
        if (task.equals("register")) {
            String regName = params[1];
            String regEmail = params[2];
            String regPassword = params[3];

            try {
                URL url = new URL(urlRegistration);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(regName, "UTF-8") + "&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(regEmail, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(regPassword, "UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder dataResponse = new StringBuilder();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    dataResponse.append(inputLine);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                editor.putString("flag", "register");
                editor.commit();
                return dataResponse.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        /*
        This is the part of the backend for user login.
        the info entered by the user will be passed to the server to check if the user exists and
        if the user entered the correct information.
         */
        if (task.equals("login")) {
            String loginEmail = params[1];
            String loginPassword = params[2];
            try {
                URL url = new URL(urlLogin);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(loginEmail, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(loginPassword, "UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder dataResponse = new StringBuilder();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    dataResponse.append(inputLine);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                editor.putString("flag", "login");
                editor.commit();
                return dataResponse.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * This deal with after the program has connected to the server and passed information.
     *
     * @param s is the String passed to the method by the server.
     */
    @Override
    protected void onPostExecute(String s) {
        String flag = preferences.getString("flag", "0");
        String[] strings = s.split("[,]");
        if (flag.equals("register")) {
            if (strings.length > 1) {
                //If the email is already registered, restart the process.
                Intent intent = new Intent(context, SignupActivity.class);
                context.startActivity(intent);
            }
            Toast.makeText(context, strings[0], Toast.LENGTH_LONG).show();

        } else if (flag.equals("login")) {
            if (strings[0].equals("true")) {
                //set up the game with the proper user and his statistics
                Intent intent = new Intent(context, MenuActivity.class);
                StatTracker statTracker = new StatTracker(parseInt(strings[5]));
                int high = parseInt(strings[2]);
                int games = parseInt(strings[3]);
                int current = parseInt(strings[4]);
                statTracker.setCurrScore(current);
                statTracker.setHighScore(high);
                statTracker.setNumOfGames(games);
                // create a new user instance
                User user = new User(strings[1], statTracker);
                GameActivity.setUser(user);
                System.out.println(user);
                System.out.println(statTracker);
                context.startActivity(intent);
            } else {
                display("That email and password do not match our records :(.");
            }
        } else {
            display("Something weird happened :(.");
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    // Display some message
    private void display(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Login Failed...");
        builder.setMessage(message);
        builder.show();
    }
}