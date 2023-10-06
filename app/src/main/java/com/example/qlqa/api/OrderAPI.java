package com.example.qlqa.api;

import com.example.qlqa.model.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderAPI {

    @POST("/api/order")
    Call<Void> createOrder(@Body Order order, @Query("idStaff") long idStaff, @Query("idTable") long idTable);

}
