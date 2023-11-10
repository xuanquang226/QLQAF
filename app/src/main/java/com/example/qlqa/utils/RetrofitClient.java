package com.example.qlqa.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //private static final String BASE_URL = "https://qlqa-production.up.railway.app";
    private static final String BASE_URL = "http://192.168.56.2:8080";

    private static final Gson gson = new GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            .create();
    public static Retrofit getClient(){

        return new Retrofit.Builder().baseUrl(BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    .build();
    }
}
