package com.example.qlqa;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qlqa.api.StaffAPI;
import com.example.qlqa.api.TimeSheetsAPI;
import com.example.qlqa.api.TimeSheetsStaffAPI;
import com.example.qlqa.model.Capture;
import com.example.qlqa.model.Staff;
import com.example.qlqa.model.TimeSheets;
import com.example.qlqa.model.TimeSheetsStaff;
import com.example.qlqa.utils.RetrofitClient;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TimeKeepingActivity extends AppCompatActivity {
    private Bundle bundle;
    private Button btnGenerate, btnScanner;
    private ImageView imageView;
    private Retrofit retrofit;
    private Bitmap bitmap;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_keeping_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getDrawable(R.color.moderate_blue));


        intent = getIntent();
        bundle = intent.getExtras();

        retrofit = RetrofitClient.getClient();

        btnGenerate = findViewById(R.id.btn_generate);
        btnScanner = findViewById(R.id.btn_scanner);
        imageView = findViewById(R.id.imgQrcode);

        generateQRCode();
        getStaff();
    }

    public void generateQRCode() {
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeSheets timeSheets = new TimeSheets();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                timeSheets.setDateTimeKeeping(timestamp);
                Toast.makeText(TimeKeepingActivity.this, timestamp + "", Toast.LENGTH_SHORT).show();

                Retrofit retrofit = RetrofitClient.getClient();
                TimeSheetsAPI timeSheetsAPI = retrofit.create(TimeSheetsAPI.class);
                Call<Long> call = timeSheetsAPI.postAndGetId(timeSheets, bundle.getString("token"));
                call.enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        if(response.body() != null){
                            // Tạo ra mã chấm công xong trả về id mã để tạo hình và nhân viên quét mã đó sẽ lấy idstaff và id mã đó để tạo chấm công
                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                            try {
                                BitMatrix bitMatrix = multiFormatWriter.encode(String.valueOf(response.body()), BarcodeFormat.QR_CODE, 300, 300);
                                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                bitmap = barcodeEncoder.createBitmap(bitMatrix);
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }else{
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {

                    }
                });
                finish();
                overridePendingTransition(1,1);
                startActivity(getIntent());
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    public void scanCode() {
        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanOptions scanOptions = new ScanOptions();
                scanOptions.setPrompt("Scanning...");
                scanOptions.setBeepEnabled(true);
                scanOptions.setOrientationLocked(true);
                scanOptions.setCaptureActivity(Capture.class);
                barLaucher.launch(scanOptions);
            }
        });
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            TimeSheetsStaff timeSheetsStaff = new TimeSheetsStaff();
            TimeSheets timeSheets = new TimeSheets();
            timeSheets.setId(Long.parseLong(result.getContents()));

            timeSheetsStaff.setTimeSheets(timeSheets);

            Retrofit retrofit = RetrofitClient.getClient();
            TimeSheetsStaffAPI timeSheetsStaffAPI = retrofit.create(TimeSheetsStaffAPI.class);
            Call<String> call = timeSheetsStaffAPI.postTimeSheetsStaff(timeSheetsStaff, bundle.getLong("idStaff"),bundle.getString("token"));
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.body() != null){
                        Toast.makeText(TimeKeepingActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    }else{
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });

        }
    });

    public void getStaff() {
        StaffAPI staffAPI = retrofit.create(StaffAPI.class);
        Call<Staff> call = staffAPI.getStaff(bundle.getLong("idS"), bundle.getString("token"));
        call.enqueue(new Callback<Staff>() {
            @Override
            public void onResponse(Call<Staff> call, Response<Staff> response) {
                if(response.body() != null){
                    Staff staff = response.body();
                    bundle.putLong("idStaff", staff.getIdStaff());

                    if (staff.getAccount().isTypeA()) {
                        btnScanner.setVisibility(View.GONE);

                        TimeSheetsAPI timeSheetsAPI = retrofit.create(TimeSheetsAPI.class);
                        Call<TimeSheets> call2 = timeSheetsAPI.getTimeSheetsWithDate(bundle.getString("token"));
                        call2.enqueue(new Callback<TimeSheets>() {
                            @Override
                            public void onResponse(Call<TimeSheets> call, Response<TimeSheets> response) {
                                if (response.body() != null) {
                                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                                    try {
                                        BitMatrix bitMatrix = multiFormatWriter.encode(String.valueOf(response.body().getId()), BarcodeFormat.QR_CODE, 300, 300);
                                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                        imageView.setImageBitmap(bitmap);
                                    } catch (WriterException e) {
                                        e.printStackTrace();
                                    }
                                    btnGenerate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Toast.makeText(TimeKeepingActivity.this, "Ngày mai tạo mới", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<TimeSheets> call, Throwable t) {

                            }
                        });
                    } else {
                        btnGenerate.setVisibility(View.GONE);

                        TimeSheetsStaffAPI timeSheetsStaffAPI = retrofit.create(TimeSheetsStaffAPI.class);
                        Call<Boolean> call2 = timeSheetsStaffAPI.checkTimeKeeping(response.body().getIdStaff(), bundle.getString("token"));
                        call2.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if(response.body() != null){
                                    if(response.body()){
                                        btnScanner.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Toast.makeText(TimeKeepingActivity.this, "Bạn đã điểm danh ngày hôm nay, mời mai quay lại xin cảm ơn và chúc bạn một ngày làm việc vui vẻ đạt được hiệu suất cao", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }else{
                                        scanCode();
                                    }
                                }else{
                                    startActivity(new Intent(TimeKeepingActivity.this, MainActivity.class));
                                }

                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {

                            }
                        });
                    }
                }else{
                    startActivity(new Intent(TimeKeepingActivity.this, MainActivity.class));
                }

            }

            @Override
            public void onFailure(Call<Staff> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}