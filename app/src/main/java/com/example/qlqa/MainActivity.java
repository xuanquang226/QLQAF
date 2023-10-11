package com.example.qlqa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlqa.api.StaffAPI;
import com.example.qlqa.model.Staff;
import com.example.qlqa.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Button btn_order, btn_payment, btn_statistics, btn_timekeeping, btn_menuAdjustment;
    private boolean typeAccount;
    private Intent intent;
    private Bundle bundle;
    private TextView tv_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        intent = getIntent();
        bundle = intent.getExtras();

        tv_user = (TextView) findViewById(R.id.tv_name);
        typeAccount = bundle.getBoolean("typeA");

        getStaff(bundle.getLong("idS"));


        btn_order = (Button) findViewById(R.id.btn_order);
        btn_payment = (Button) findViewById(R.id.btn_payment);
        btn_statistics = (Button) findViewById(R.id.btn_statistics);
        btn_timekeeping = (Button) findViewById(R.id.btn_timekeeping);
        btn_menuAdjustment = (Button) findViewById(R.id.btn_menu_adjust);

        transOrderActivity();
        transPaymentActivity();
        transStatisticsActivity();
        transTimeKeepingActivity();
        transMenuAdjustActivity();



        // Logout
        ImageButton imgBtn = (ImageButton) findViewById(R.id.ibtn_info);
        PopupMenu dropDownMenu = new PopupMenu(this, imgBtn);
        Menu menu = dropDownMenu.getMenu();
        dropDownMenu.getMenuInflater().inflate(R.menu.menu_img_button, menu);
        dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.itemLogout:
                        intent = new Intent(MainActivity.this, IntroduceActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.itemExit:
                        Toast.makeText(MainActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                        System.exit(0);
                        return true;
                }
                return false;
            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropDownMenu.show();
            }
        });
    }

    public void getStaff(long idAccount){
        Retrofit retrofit = RetrofitClient.getClient();
        StaffAPI staffAPI = retrofit.create(StaffAPI.class);
        Call<Staff> call = staffAPI.getStaff(idAccount);
        call.enqueue(new Callback<Staff>() {
            @Override
            public void onResponse(Call<Staff> call, Response<Staff> response) {
                Staff staff = response.body();
                tv_user.setText(staff.getNameStaff());
                bundle.putLong("idStaff", staff.getIdStaff());
            }

            @Override
            public void onFailure(Call<Staff> call, Throwable t) {

            }
        });
    }

    public void transOrderActivity(){
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, OrderAcitivity.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
    }
    public void transPaymentActivity(){
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PaymentTableActivity.class));
            }
        });
    }
    public void transStatisticsActivity(){
        btn_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeAccount){
                    startActivity(new Intent(MainActivity.this, StatictisActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "Tai khoan cua ban khong du dieu kien su dung chuc nang nay", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void transTimeKeepingActivity(){
        btn_timekeeping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeAccount){
                    startActivity(new Intent(MainActivity.this, TimeKeepingActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "Tai khoan cua ban khong du dieu kien su dung chuc nang nay", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void transMenuAdjustActivity(){
        btn_menuAdjustment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeAccount){
                    startActivity(new Intent(MainActivity.this, MenuAdjustment.class));
                }else{
                    Toast.makeText(MainActivity.this, "Tai khoan cua ban khong du dieu kien su dung chuc nang nay", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        if(bundle.isEmpty()){
            super.onBackPressed();
        }else{
            Toast.makeText(this, "Dang xuat de ve man hinh dang nhap", Toast.LENGTH_SHORT).show();
        }
    }
}