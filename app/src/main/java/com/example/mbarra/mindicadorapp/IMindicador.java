package com.example.mbarra.mindicadorapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IMindicador {

    String BASE_URL = "http://mindicador.cl/api/";

    @GET
    Call<Indicador> indicadores(@Url String url);
}
