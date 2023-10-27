package com.example.qlqa.api;

import com.example.qlqa.model.PayrollStaff;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PayrollStaffAPI {

    @POST("/api/payrollstaff/{idPayroll}")
    Call<Void> postPayrollStaff(@Path("idPayroll") long idPayroll);

    @GET("/api/payrollstaff")
    Call<List<PayrollStaff>> getPayrollStaff(@Query("idPayroll") long idPayroll);
}
