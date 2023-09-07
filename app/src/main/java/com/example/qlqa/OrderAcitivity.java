package com.example.qlqa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import com.example.qlqa.adapter.TableAdapter;
import com.example.qlqa.api.InfoTableAPI;
import com.example.qlqa.model.DinnerTable;
import com.example.qlqa.utils.RetrofitClient;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderAcitivity extends AppCompatActivity {
    private TableAdapter tableAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.list_table);
        actionBar.setDisplayHomeAsUpEnabled(true);

        getInfoTable();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    public List<DinnerTable> createTempData(){
//        List<DinnerTable> llTable = new ArrayList<DinnerTable>();
//        llTable.add(new DinnerTable(1, true));
//        llTable.add(new DinnerTable(2, true));
//        llTable.add(new DinnerTable(3, false));
//        llTable.add(new DinnerTable(4, true));
//        return llTable;
//    }

    public void getInfoTable(){
        Retrofit retrofit = RetrofitClient.getClient();
        InfoTableAPI infoTableAPI = retrofit.create(InfoTableAPI.class);
        Call<List<DinnerTable>> call = infoTableAPI.getInfoTable();
        call.enqueue(new Callback<List<DinnerTable>>() {
            @Override
            public void onResponse(Call<List<DinnerTable>> call, Response<List<DinnerTable>> response) {
                tableAdapter = new TableAdapter(OrderAcitivity.this);
                tableAdapter.setData(response.body());

                LinearLayoutManager layoutManager = new LinearLayoutManager(OrderAcitivity.this, RecyclerView.VERTICAL, false);
                RecyclerView rcv_table = findViewById(R.id.rcv_ltb);

                rcv_table.setAdapter(tableAdapter);
                rcv_table.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<DinnerTable>> call, Throwable t) {

            }
        });
    }

}