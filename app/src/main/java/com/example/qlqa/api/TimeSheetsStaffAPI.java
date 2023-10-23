package com.example.qlqa.api;

import com.example.qlqa.model.TimeSheetsStaff;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TimeSheetsStaffAPI {

    @POST("api/timesheetsStaff")
    Call<Void> postTimeSheetsStaff(@Body TimeSheetsStaff timeSheetsStaff, @Query("idStaff") long idStaff);

    @GET("api/timesheetsStaff/check/{idStaff}")
    Call<Boolean> checkTimeKeeping(@Path("idStaff") long idStaff);
}
