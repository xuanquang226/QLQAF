package com.example.qlqa.api;

import com.example.qlqa.model.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPI {

    @POST("api/login")
    Call<Account> login(@Body Account a);

}
