package com.example.mbarra.mindicadorapp;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class JSONSerializer {
    private static final String TAG = JSONSerializer.class.getSimpleName();

    // A String to hold the filename where the data will be saved
    private String mFileName;
    // A Context object that is necessary in Android to write data to a file
    private Context mContext;

    public JSONSerializer(String fn, Context con) {
        this.mFileName = fn;
        this.mContext = con;
    }

    public void save(ArrayList<Indicador> indicadores) throws IOException, JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Indicador i: indicadores) {
            jsonArray.put(i.convertToJSON());
        }
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFileName, mContext.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Indicador> load() throws IOException, JSONException {
        ArrayList<Indicador> indicadores = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                indicadores.add(new Indicador(jsonArray.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "" + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return indicadores;
    }
}
