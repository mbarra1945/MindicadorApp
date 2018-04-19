package com.example.mbarra.mindicadorapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Utility {
    private static final String TAG = Utility.class.getSimpleName();

    // units of measurements
    public static final String PESO = "CLP";
    public static final String DOLAR = "USD";

    public static ArrayList<Object> convertToArrayList(JSONArray jsonArray) {
        ArrayList<Object> arrayList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                arrayList.add(new Serie(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            Log.e(TAG, "" + e.getMessage());
        }
        return arrayList;
    }

    public static String formatCurrency(Double valor) {
        BigDecimal bg = BigDecimal.valueOf(valor);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        return "$" + numberFormat.format(bg);
    }

    public static String formatCurrency(BigDecimal bg) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        return "$" + numberFormat.format(bg);
    }

    public static BigDecimal difference(Double param1, Double param2) {
        BigDecimal a = BigDecimal.valueOf(param1);
        BigDecimal b = BigDecimal.valueOf(param2);
        return a.subtract(b);
    }

    public static String formatDate(String fecha) {
        String formattedDate = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = inputFormat.parse(fecha);
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            Log.e(TAG, "" + e.getMessage());
        }
        return formattedDate;
    }

    public static String convertToCLP(String param1, Double param2) {
        BigDecimal a = new BigDecimal(param1);
        BigDecimal b = BigDecimal.valueOf(param2);
        BigDecimal result = a.multiply(b);
        return formatCurrency(result);
    }
}
