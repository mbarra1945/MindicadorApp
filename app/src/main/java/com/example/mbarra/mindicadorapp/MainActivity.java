package com.example.mbarra.mindicadorapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Task().execute();
    }

    public class Task extends AsyncTask<Void, Void, Void> {
        private ArrayList<Indicador> mIndicadores;
        private JSONSerializer mSerializer;

        public Task() {
            this.mIndicadores = new ArrayList<>();
            this.mSerializer = new JSONSerializer("mindicador.json", MainActivity.this);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(IMindicador.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            IMindicador iMindicador = retrofit.create(IMindicador.class);
            Indicador uf, utm, euro, dolar, bitcoin;
            try {
                if ((uf = iMindicador.indicadores("uf").execute().body()) != null) {
                    mIndicadores.add(uf);
                }
                if ((utm = iMindicador.indicadores("utm").execute().body()) != null) {
                    mIndicadores.add(utm);
                }
                if((euro = iMindicador.indicadores("euro").execute().body()) != null) {
                    mIndicadores.add(euro);
                }
                if ((dolar = iMindicador.indicadores("dolar").execute().body()) != null) {
                    mIndicadores.add(dolar);
                }
                if ((bitcoin = iMindicador.indicadores("bitcoin").execute().body()) != null) {
                    mIndicadores.add(bitcoin);
                }
            } catch (IOException e) {
                Log.e(TAG, "" + e.getMessage());
            } finally {
                try {
                    mSerializer.save(mIndicadores);
                } catch (IOException e) {
                    Log.e(TAG, "" + e.getMessage());
                } catch (JSONException e) {
                    Log.e(TAG, "" + e.getMessage());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Very important
            // @SuppressWarnings("unchecked")
            // ArrayList<Serie> serie = (ArrayList<Serie>)(ArrayList<?>)i.getSerie();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
}
