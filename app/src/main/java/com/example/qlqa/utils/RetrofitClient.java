package com.example.qlqa.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://192.168.56.2:8080/";

    public static Retrofit getClient(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
    }
}
