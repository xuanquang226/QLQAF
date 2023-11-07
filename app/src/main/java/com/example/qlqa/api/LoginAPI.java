package com.example.qlqa.api;

import com.example.qlqa.model.Account;
import com.example.qlqa.model.TupleAccountToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginAPI {

    @POST("/api/login2")
    Call<TupleAccountToken<String, Account>> login(@Body Account a);
}
