package com.example.qlqa.api;

import com.example.qlqa.model.Payroll;
import com.example.qlqa.model.PayrollStaff;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PayrollAPI {
    @GET("/api/payroll/{id}")
    public Payroll get(@Path("id")long id);

    @GET("/api/payroll/getprs")
    public PayrollStaff getPRS(@Query(value = "staff") long idStaff, @Query(value = "payroll") long idPayroll);

    @POST("/api/payroll")
    public void postAndGetIDPayroll(@Body Payroll pr);
}
