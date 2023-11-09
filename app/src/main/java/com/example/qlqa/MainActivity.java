package com.example.qlqa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlqa.api.LoginAPI;
import com.example.qlqa.api.StaffAPI;
import com.example.qlqa.model.Account;
import com.example.qlqa.model.Staff;
import com.example.qlqa.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Button btnOrder, btnPayment, btnStatistics, btnTimekeeping, btnMenuAdjustment, btnPayroll;
    private boolean typeAccount;
    private Intent intent;
    private Bundle bundle;
    private TextView tv_user;
    private Retrofit retrofit;
    private SQLiteDatabase database;

    private int idToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getDrawable(R.color.moderate_blue));


        if(getIntent().getExtras() != null){
            intent = getIntent();
            bundle = intent.getExtras();
            Log.d("intent", "not null");
        }else{
            bundle = new Bundle();
        }

        retrofit = RetrofitClient.getClient();
        tv_user = (TextView) findViewById(R.id.tv_name);





        btnOrder = (Button) findViewById(R.id.btn_order);
        btnPayment = (Button) findViewById(R.id.btn_payment);
        btnStatistics = (Button) findViewById(R.id.btn_statistics);
        btnTimekeeping = (Button) findViewById(R.id.btn_timekeeping);
        btnMenuAdjustment = (Button) findViewById(R.id.btn_menu_adjust);
        btnPayroll = (Button) findViewById(R.id.btn_payroll);

        transOrderActivity();
        transPaymentActivity();
        transStatisticsActivity();
        transTimeKeepingActivity();
        transMenuAdjustActivity();
        transPayrollActivity();

        localDatabase();


        // Logout
        ImageButton imgBtn = (ImageButton) findViewById(R.id.ibtn_info);
        PopupMenu dropDownMenu = new PopupMenu(this, imgBtn);
        Menu menu = dropDownMenu.getMenu();
        dropDownMenu.getMenuInflater().inflate(R.menu.menu_img_button, menu);
        dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.itemLogout:
                        intent = new Intent(MainActivity.this, IntroduceActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        database.delete("Auth", "idToken = ?", new String[]{Integer.toString(idToken)});

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

    public void getStaff(long idAccount, String token) {
        Retrofit retrofit = RetrofitClient.getClient();
        StaffAPI staffAPI = retrofit.create(StaffAPI.class);
        Call<Staff> call = staffAPI.getStaff(idAccount, token);
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

    public void transOrderActivity() {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, OrderAcitivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void transPaymentActivity() {
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, PaymentTableActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void transStatisticsActivity() {
        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeAccount) {
                    Toast.makeText(MainActivity.this, "Chuc nang dang duoc cap nhat", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Tai khoan cua ban khong du dieu kien su dung chuc nang nay", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void transTimeKeepingActivity() {
        btnTimekeeping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, TimeKeepingActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void transMenuAdjustActivity() {
        btnMenuAdjustment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeAccount) {
                    Intent intent = new Intent(MainActivity.this, MenuAdjustment.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Tai khoan cua ban khong du dieu kien su dung chuc nang nay", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void transPayrollActivity(){
        btnPayroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(typeAccount){
                    Intent intent = new Intent(MainActivity.this, PayrollActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Tai khoan cua ban khong du dieu kien su dung chuc nang nay", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void localDatabase(){
        String token;

        database = openOrCreateDatabase("Authorization.db", MODE_PRIVATE, null);
        try{
            String sql = "CREATE TABLE Auth(idToken INT, token TEXT)";
            database.execSQL(sql);
        }catch(Exception e){
            Log.e("Error", "Table was exist");
        }

//        ContentValues values = new ContentValues();
//        values.put("idToken", 1);
//        values.put("token", bundle.getString("token"));
//        database.insert("Auth", null, values);

        // Duyệt table Auth nếu tồn tại jwt thì lấy ra gán vào biến String token để getAccount, không có dữ liệu thì insert rồi duyệt lại và gán vào String token.
        Cursor c = database.query("Auth", null, null, null, null, null, null);

        if(c.moveToFirst()){
            token = c.getString(1);
            idToken = c.getInt(0);
        }else{
            ContentValues values = new ContentValues();
            values.put("idToken", 1);
            values.put("token", bundle.getString("token"));
            database.insert("Auth", null, values);

            Cursor c1 = database.query("Auth", null, null, null, null, null, null);
            c1.moveToFirst();
            token = c1.getString(1);
            idToken = c1.getInt(0);
        }
        getAccount(token, idToken);
//        try {
//            String sql = "Drop TABLE Auth";
//            database.execSQL(sql);
//        }catch(Exception e){
//            e.printStackTrace();;
//        }

    }

    public void getAccount(String token, int idToken){
        System.out.println(token);

        LoginAPI loginAPI = retrofit.create(LoginAPI.class);
        Call<Account> call = loginAPI.getAccount(token);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
//                bundle.putString("username", response.body().getUsername());
//                bundle.putString("password", response.body().getPassword());
                if(response.body() != null) {
                    bundle.putLong("idS", response.body().getId());
                    bundle.putString("token", token);
                    typeAccount = response.body().isTypeA();
                    getStaff(response.body().getId(), token);
                }else{
                    // Token hết hạn, xoá token trong sqlite và chuyển về màn hình đăng nhập
                    database.delete("Auth", "idToken = ?", new String[]{Integer.toString(idToken)});

                    Toast.makeText(MainActivity.this, "Hết hạn đăng nhập bạn cần đăng nhập lại", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, IntroduceActivity.class));
                };
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (bundle.isEmpty()) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Dang xuat de ve man hinh dang nhap", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}