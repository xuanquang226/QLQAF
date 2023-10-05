package com.example.qlqa.api;

import com.example.qlqa.model.Staff;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StaffAPI {

    @GET("api/staff/{id}")
    Call<Staff> getStaff(@Path("id") long idAccount);
}
