package com.example.qlqa.api;

import com.example.qlqa.model.TimeSheets;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TimeSheetsAPI {

    @POST("/api/timesheets/getid")
    Call<Long> postAndGetId(@Body TimeSheets timeSheets, @Header("Authorization") String token);

    @GET("/api/timesheets/date")
    Call<TimeSheets> getTimeSheetsWithDate(@Header("Authorization") String token);
}
