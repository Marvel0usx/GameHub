package com.example.userinterface.GameManager.HangMan;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    InputStream inputStream;

    public CSVReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<String[]> read(){
        List<String[]> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                result.add(row);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

