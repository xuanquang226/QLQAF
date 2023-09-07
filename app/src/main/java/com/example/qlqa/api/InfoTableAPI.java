package com.example.qlqa.api;

import com.example.qlqa.model.DinnerTable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InfoTableAPI {

    @GET("api/table")
    Call<List<DinnerTable>> getInfoTable();
}
