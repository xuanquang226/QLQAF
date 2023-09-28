package com.example.qlqa.api;

import com.example.qlqa.model.Dish;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DishAPI {

    @GET("/dish")
    Call<List<com.example.qlqa.model.Dish>> getListDish();

    @PUT("/dish/{id}")
    Call<String> putNameAQuantityDish(@Path("id") long id, @Body Dish dish);

    @POST("/dish")
    Call<Void> postNewDish(@Body Dish dish);

    @DELETE("/dish/{id}")
    Call<Void> deleteDish(@Path ("id") long id);
}
