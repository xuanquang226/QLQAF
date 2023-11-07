package com.example.qlqa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlqa.adapter.PayrollStaffAdapter;
import com.example.qlqa.api.PayrollAPI;
import com.example.qlqa.api.PayrollStaffAPI;
import com.example.qlqa.model.Payroll;
import com.example.qlqa.model.PayrollStaff;
import com.example.qlqa.utils.RetrofitClient;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PayrollActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int month, year;
    private Retrofit retrofit;
    private TextView tvMonth;
    private RecyclerView recyclerView;
    private PayrollStaffAdapter payrollStaffAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.ItemDecoration itemDecoration;

    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payroll_layout);

        intent = getIntent();
        bundle = intent.getExtras();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getDrawable(R.color.moderate_blue));

        retrofit = RetrofitClient.getClient();
        payrollStaffAdapter = new PayrollStaffAdapter(PayrollActivity.this);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView = findViewById(R.id.rcv_list_payroll_staff);
        recyclerView.addItemDecoration(itemDecoration);

        Button btnGetPayroll = findViewById(R.id.btn_get_payroll);
        tvMonth = findViewById(R.id.tv_month);


        createSpinner();
        btnGetPayroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPayrollStaff();
            }
        });

        createPayroll();
    }


    public void createSpinner() {
        Spinner spinnerMonth = findViewById(R.id.spinner_month);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(i);
        }
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_layout, list);
        spinnerMonth.setAdapter(arrayAdapter);
        spinnerMonth.setOnItemSelectedListener(this);

        Spinner spinnerYear = findViewById(R.id.spinner_year);
        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(this, R.array.year, R.layout.spinner_item_layout);
        spinnerYear.setAdapter(arrayAdapter1);
        spinnerYear.setOnItemSelectedListener(this);
    }

    public void postAndGetIDPayroll() {
        PayrollAPI payrollAPI = retrofit.create(PayrollAPI.class);
        Payroll payroll = new Payroll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        payroll.setDateCreatePayroll(timestamp);

        Call<Long> call = payrollAPI.postAndGetIDPayroll(payroll, bundle.getString("token"));
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                PayrollStaffAPI payrollStaffAPI = retrofit.create(PayrollStaffAPI.class);
                Call<Void> callPayrollStaffAPI = payrollStaffAPI.postPayrollStaff(response.body(), bundle.getString("token"));
                callPayrollStaffAPI.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }

    public void getPayrollStaff() {
        PayrollAPI payrollAPI = retrofit.create(PayrollAPI.class);
        Call<Long> call = payrollAPI.queryPayrollAndGetIdPayroll(month, year, bundle.getString("token"));
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                if (response.body() != null) {

                    PayrollStaffAPI payrollStaffAPI = retrofit.create(PayrollStaffAPI.class);
                    Call<List<PayrollStaff>> call1 = payrollStaffAPI.getPayrollStaff(response.body(), bundle.getString("token"));
                    call1.enqueue(new Callback<List<PayrollStaff>>() {
                        @Override
                        public void onResponse(Call<List<PayrollStaff>> call, Response<List<PayrollStaff>> response) {

                            payrollStaffAdapter.setPayrollStaffList(response.body());
                            recyclerView.setAdapter(payrollStaffAdapter);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            tvMonth.setText(String.valueOf(month));
                        }

                        @Override
                        public void onFailure(Call<List<PayrollStaff>> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(PayrollActivity.this, "Không có bảng lương tháng bạn chọn", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinner_month:
                month = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                break;
            case R.id.spinner_year:
                year = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void createPayroll() {
        // Kiểm tra hôm nay có phải ngày cuối cùng của tháng
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int currentDay = timestamp.getDate();
        LocalDate localDate = LocalDate.parse(timestamp.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).with(TemporalAdjusters.lastDayOfMonth());
        int lastDayOfMonth = localDate.getDayOfMonth();

        if (currentDay == lastDayOfMonth) {
            Toast.makeText(this, "Nay la ngay cuối cùng của tháng", Toast.LENGTH_SHORT).show();
            postAndGetIDPayroll();
        } else {
            System.out.println("Khong phai ngay cuoi cung");
        }
    }
}