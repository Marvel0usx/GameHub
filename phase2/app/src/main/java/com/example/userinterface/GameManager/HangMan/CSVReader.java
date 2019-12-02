package com.example.userinterface.GameManager.HangMan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A CVS reader.
 */
class CSVReader implements Serializable {

    private InputStream inputStream;

    /**
     * Construct a new CVS reader
     * @param inputStream the input stream of a csv file
     */
    CSVReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Read the csv file
     * @return a list of string array of word and hint
     */
    List<String[]> read(){
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

