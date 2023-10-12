package com.example.qlqa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlqa.adapter.PaymentAdapter;
import com.example.qlqa.api.DishOrderAPI;
import com.example.qlqa.api.InfoTableAPI;
import com.example.qlqa.api.OrderAPI;
import com.example.qlqa.model.DishOrder;
import com.example.qlqa.model.Order;
import com.example.qlqa.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaymentActivity extends AppCompatActivity {

    private TextView tvTotalPrice;
    private Retrofit retrofit;
    private Bundle bundle;

    private Button btnPayment;
    private Intent intent;
    private OrderAPI orderAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);
        retrofit = RetrofitClient.getClient();

        intent = getIntent();
        bundle = intent.getExtras();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Bàn " + bundle.getLong("idTable"));
        actionBar.setBackgroundDrawable(getDrawable(R.color.moderate_blue));

        tvTotalPrice = findViewById(R.id.tv_total_price);
        btnPayment = findViewById(R.id.btn_confirm_payment);

        createDataForAdapter();
        getOrderById();

    }

    public void createDataForAdapter(){
        DishOrderAPI dishOrderAPI = retrofit.create(DishOrderAPI.class);
        Call<List<DishOrder>> call = dishOrderAPI.getListDishOrderWithIdOrder(bundle.getLong("idOrder"));
        call.enqueue(new Callback<List<DishOrder>>() {
            @Override
            public void onResponse(Call<List<DishOrder>> call, Response<List<DishOrder>> response) {
                PaymentAdapter paymentAdapter = new PaymentAdapter(getApplicationContext());
                paymentAdapter.setDishOrderList(response.body());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

                RecyclerView recyclerView = findViewById(R.id.rcv_dish_order);
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                recyclerView.setAdapter(paymentAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.addItemDecoration(itemDecoration);
            }

            @Override
            public void onFailure(Call<List<DishOrder>> call, Throwable t) {

            }
        });
    }

    public void getOrderById(){
        orderAPI = retrofit.create(OrderAPI.class);
        Call<Order> call = orderAPI.getOrderById(bundle.getLong("idOrder"));
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Order order = response.body();
                tvTotalPrice.setText(String.valueOf(order.getTotalPrice()));

                btnPayment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        order.setState(true);
                        Call<String> callPutOrder = orderAPI.putOrder(order, bundle.getLong("idOrder"));
                        callPutOrder.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(PaymentActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });

                        InfoTableAPI infoTableAPI = retrofit.create(InfoTableAPI.class);
                        Call<Void> callPutIdTable = infoTableAPI.updateIdOrderForTable(bundle.getLong("idTable"), 0);
                        callPutIdTable.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                        Toast.makeText(PaymentActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}