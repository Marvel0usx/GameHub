package com.example.userinterface.GameManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static java.lang.Integer.parseInt;

public class GameBackgroundActivity extends AsyncTask<Object, Void, String> {


    @SuppressLint("StaticFieldLeak")
    private Context context;

    public GameBackgroundActivity(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(Object... objects) {
        /**
         * This is the background!, these methods will connect to the server we set up and update and
         * get the information in the server.
         */
        String urlUpdate = "http://159.203.20.150/save.php";
        String urlStat = "http://159.203.20.150/single_stat.php";
        String task = (String) objects[0];
        if (task.equals("quit")) {
            //This is the case where the user quits the game in the middle and we save the level
            // they are on and the current score they have.
            try {
                User user = (User) objects[1];
                int level = user.getStatTracker().getLevel();
                int current = user.getStatTracker().getCurrScore();
                URL url = new URL(urlUpdate);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(user.getEmail(), "UTF-8")
                        + "&" + URLEncoder.encode("level", "UTF-8") + "=" + URLEncoder.encode(level + "", "UTF-8")
                        + "&" + URLEncoder.encode("current", "UTF-8") + "=" + URLEncoder.encode(current + "", "UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String dataResponse = "";
                String inputLine = "";
                while ((inputLine = bufferedReader.readLine()) != null) {
                    dataResponse += inputLine;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return dataResponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(task.equals("stats")){
            URL url = null;
            User user = (User)objects[1];
            try {
                /**
                 * This is the case where the user finishes a game and we update all the information
                 * that is needed to update.
                 */
                int high = user.getStatTracker().getHighScore();
                int gamesplayed = user.getStatTracker().getNumOfGames();
                int level = 0;
                int current = 0;
                url = new URL(urlStat);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(user.getEmail())+"&"+
                        URLEncoder.encode("highscore", "UTF-8")+"="+URLEncoder.encode(high+"","UTF-8")+"&"+
                        URLEncoder.encode("games", "UTF-8")+"="+URLEncoder.encode(gamesplayed+"","UTF-8")+"&"+
                        URLEncoder.encode("level", "UTF-8")+"="+URLEncoder.encode(level+"","UTF-8")+"&"+
                        URLEncoder.encode("current", "UTF-8")+"="+URLEncoder.encode(current+"","UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String dataResponse = "";
                String inputLine = "";
                while((inputLine = bufferedReader.readLine()) != null){
                    dataResponse += inputLine;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return dataResponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void onPostExecute(String s) {
        Log.d("message", s);
    }

}
