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
import com.example.qlqa.model.TupleAccountToken;
import com.example.qlqa.utils.RetrofitClient;
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

        // Button back
        actionBar.setDisplayHomeAsUpEnabled(true);


        EditText edt_username = (EditText) findViewById(R.id.edt_username);
        EditText edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        retrofit = RetrofitClient.getClient();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edt_username.getText().toString().isEmpty() && !edt_password.getText().toString().isEmpty()) {
                    login(new Account(edt_username.getText().toString().trim(), edt_password.getText().toString().trim()));
                } else {
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
        Call<TupleAccountToken<String, Account>> call = accountAPI.login(a);
        call.enqueue(new Callback<TupleAccountToken<String, Account>>() {
            @Override
            public void onResponse(Call<TupleAccountToken<String, Account>> call, Response<TupleAccountToken<String, Account>> response) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();

                if(response.body() != null){
                    TupleAccountToken<String, Account> accountAndToken = response.body();
                    bundle.putString("username", accountAndToken.getAccount().getUsername());
                    bundle.putString("password", accountAndToken.getAccount().getPassword());
                    bundle.putBoolean("typeA", accountAndToken.getAccount().isTypeA());
                    bundle.putLong("idS", accountAndToken.getAccount().getId());
                    bundle.putString("token", accountAndToken.getToken());
                    String token = accountAndToken.getToken();

                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Sai mật khẩu hoặc password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TupleAccountToken<String, Account>> call, Throwable t) {
                Log.d("loi", t.toString());
                Toast.makeText(LoginActivity.this, "Khong ket noi duoc", Toast.LENGTH_SHORT).show();
            }
        });
    }

}