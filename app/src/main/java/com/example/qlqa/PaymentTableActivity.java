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

import com.example.qlqa.adapter.TableAdapter2;
import com.example.qlqa.api.InfoTableAPI;
import com.example.qlqa.model.DinnerTable;
import com.example.qlqa.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaymentTableActivity extends AppCompatActivity {
    private Retrofit retrofit;

    private Intent intent;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_table_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.list_table);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getDrawable(R.color.moderate_blue));

        intent = getIntent();
        bundle = intent.getExtras();

        retrofit = RetrofitClient.getClient();
        getInfoTable();
    }


    public void getInfoTable(){
        InfoTableAPI infoTableAPI = retrofit.create(InfoTableAPI.class);
        Call<List<DinnerTable>> call = infoTableAPI.getInfoTable(bundle.getString("token"));
        call.enqueue(new Callback<List<DinnerTable>>() {
            @Override
            public void onResponse(Call<List<DinnerTable>> call, Response<List<DinnerTable>> response) {
                TableAdapter2 tableAdapter2 = new TableAdapter2(getApplicationContext(), new TableAdapter2.ItemClickListener() {
                    @Override
                    public void onClick(DinnerTable dinnerTable) {
                        Intent intent1 = new Intent(getApplicationContext(), PaymentActivity.class);
                        bundle.putLong("idTable", dinnerTable.getId());
                        bundle.putLong("idOrder", dinnerTable.getIdOrder());
                        intent1.putExtras(bundle);
                        startActivity(intent1);
                    }
                });
                tableAdapter2.setDinnerTableList(response.body());
                RecyclerView recyclerView = findViewById(R.id.rcv_payment_table_list);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                recyclerView.setAdapter(tableAdapter2);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.addItemDecoration(itemDecoration);
            }

            @Override
            public void onFailure(Call<List<DinnerTable>> call, Throwable t) {

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