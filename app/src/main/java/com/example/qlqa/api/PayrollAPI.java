package com.example.qlqa.api;

import com.example.qlqa.model.Payroll;
import com.example.qlqa.model.PayrollStaff;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PayrollAPI {
    @GET("/api/payroll/{id}")
    Call<Payroll> get(@Path("id")long id);

    @GET("/api/payroll/getprs")
    Call<PayrollStaff> getPRS(@Query(value = "staff") long idStaff, @Query(value = "payroll") long idPayroll);

    @POST("/api/payroll")
    Call<Long> postAndGetIDPayroll(@Body Payroll pr);

    @GET("/api/payroll/getIdPayrollStaff")
    Call<Long> queryPayrollAndGetIdPayroll(@Query("month") int month, @Query("year") int year);
}
