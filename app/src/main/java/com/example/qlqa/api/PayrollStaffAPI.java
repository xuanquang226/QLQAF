package com.example.qlqa.api;

import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PayrollStaffAPI {

    @POST("/api/payrollstaff/{idPayroll}")
    public void postPayrollStaff(@Path("idPayroll") long idPayroll);
}
