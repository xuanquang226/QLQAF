package com.example.qlqa.api;

import com.example.qlqa.model.DinnerTable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InfoTableAPI {

    @GET("/api/table")
    Call<List<DinnerTable>> getInfoTable();

    @GET("api/table/{id}")
    Call<DinnerTable> getTableById(@Path("id") long idTable);

    @PUT("api/table/{id}")
    Call<Void> updateIdOrderForTable(@Path("id") long idTable, @Query("idOrder") long idOrder);
}
