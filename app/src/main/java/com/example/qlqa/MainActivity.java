package com.example.qlqa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_order, btn_payment, btn_statistics, btn_timekeeping, btn_menuAdjustment;
    private boolean typeAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("info");


        TextView tv_user = (TextView) findViewById(R.id.tv_name);
        tv_user.setText(bundle.getString("username"));
        typeAccount = bundle.getBoolean("typeA");


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
    }

    public void transOrderActivity(){
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderAcitivity.class));
            }
        });
    }
    public void transPaymentActivity(){
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PaymentActivity.class));
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


}