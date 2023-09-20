package com.example.qlqa.api;

import com.example.qlqa.model.Dish;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDish {

    @GET("/aa")
    Call<List<Dish>> getListDish();
}
