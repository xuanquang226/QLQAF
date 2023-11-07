package com.example.qlqa.api;

import com.example.qlqa.model.TimeSheetsStaff;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TimeSheetsStaffAPI {

    @POST("api/timesheetsStaff")
    Call<String> postTimeSheetsStaff(@Body TimeSheetsStaff timeSheetsStaff, @Query("idStaff") long idStaff, @Header("Authorization") String token);

    @GET("api/timesheetsStaff/check/{idStaff}")
    Call<Boolean> checkTimeKeeping(@Path("idStaff") long idStaff, @Header("Authorization") String token);

    @GET("api/timesheetsStaff/count")
    public Map<String, Integer> countTimeSheetsStaff(@Query("idStaff") List<Long> idStaff, @Header("Authorization") String token);

    @GET("api/timesheetsStaff")
    public List<TimeSheetsStaff> getLTimeSheetsStaff(@Header("Authorization") String token);
}
