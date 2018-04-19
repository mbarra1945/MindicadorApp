package com.example.mbarra.mindicadorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    private ArrayList<Indicador> mIndicadores;

    private TextView txtFecha;
    private TextView txtValor;
    private EditText inputValor;

    // fecha y valor actual
    private String mFecha;
    private Double mValor;

    // position
    private static final int DOLAR = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtFecha = findViewById(R.id.txtFecha);
        txtValor = findViewById(R.id.txtValor);

        inputValor = findViewById(R.id.inputValor);

        mIndicadores = new ArrayList<>();
        JSONSerializer serializer = new JSONSerializer("mindicador.json", HomeActivity.this);
        try {
            mIndicadores = serializer.load();
            Serie serie = (Serie) mIndicadores.get(DOLAR).getSerie().get(0);
            mFecha = serie.getFecha();
            mValor = serie.getValor();
        } catch (IOException e) {
            Log.e(TAG, "" + e.getMessage());
        } catch (JSONException e) {
            Log.e(TAG, "" + e.getMessage());
        } finally {
            initViews();
        }
        Serie serie = (Serie) mIndicadores.get(0).getSerie().get(0);
        txtFecha.setText(Utility.formatDate(serie.getFecha()));

        inputValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    txtValor.setText(Utility.convertToCLP(inputValor.getText().toString(), mValor));
                } else {
                    // temporary code
                    txtValor.setText(Utility.formatCurrency(mValor));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void initViews() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new DataAdapter(mIndicadores);
        recyclerView.setAdapter(adapter);
    }
}
