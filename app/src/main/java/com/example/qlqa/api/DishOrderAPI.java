package com.example.qlqa.api;

import com.example.qlqa.model.DishOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DishOrderAPI {
    @GET("/api/dishorder/{idOrder}")
    Call<List<DishOrder>> getListDishOrderWithIdOrder(@Path("idOrder") long idOrder, @Header("Authorization") String token);
}
