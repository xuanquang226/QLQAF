package com.example.qlqa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlqa.api.LoginAPI;
import com.example.qlqa.config.RetrofitClient;
import com.example.qlqa.model.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    public static Retrofit retrofit;


    public Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);



        // Set title for activity
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.login);


        // Set logo
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.star);
        actionBar.setDisplayUseLogoEnabled(true);

        // Add button back
        actionBar.setDisplayHomeAsUpEnabled(true);


        EditText edt_username = (EditText) findViewById(R.id.edt_username);
        EditText edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        retrofit = RetrofitClient.getClient();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt_username.getText().toString().isEmpty() && !edt_password.getText().toString().isEmpty()){
                    login(new Account(edt_username.getText().toString().trim(), edt_password.getText().toString().trim()));
                }else{
                    Toast.makeText(LoginActivity.this, "Ban chua nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }
                
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void login(Account a) {
        LoginAPI accountAPI = retrofit.create(LoginAPI.class);
        Call<Account> call = accountAPI.login(a);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",response.body().getUsername());
                bundle.putString("password", response.body().getPassword());
                bundle.putBoolean("typeA", response.body().getTypeAccount());
                intent.putExtra("info", bundle);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.d("loi", t.toString());
                Toast.makeText(LoginActivity.this, "Tai khoan khong dung ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}