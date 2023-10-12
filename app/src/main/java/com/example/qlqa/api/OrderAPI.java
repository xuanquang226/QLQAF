package com.example.qlqa.api;

import com.example.qlqa.model.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderAPI {

    @POST("/api/order")
    Call<Long> createOrder(@Body Order order, @Query("idStaff") long idStaff, @Query("idTable") long idTable);


    @GET("/api/order/{id}")
    Call<Order> getOrderById(@Path("id") long id);

    @PUT("/api/order/{id}")
    Call<String> putOrder(@Body Order order, @Path("id") long id);
}
