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
import android.widget.Toast;


import com.example.qlqa.adapter.TableAdapter;
import com.example.qlqa.api.InfoTableAPI;
import com.example.qlqa.model.DinnerTable;
import com.example.qlqa.utils.RetrofitClient;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderAcitivity extends AppCompatActivity {
    private TableAdapter tableAdapter;
    List<DinnerTable> llTable;
    private Bundle bundle;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);


        intent = getIntent();
        bundle = intent.getExtras();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.list_table);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getDrawable(R.color.moderate_blue));

        getInfoTable();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return false;
        }
    }

//    public List<DinnerTable> createTempData(){
//        llTable = new ArrayList<DinnerTable>();
//        llTable.add(new DinnerTable(1, true));
//        llTable.add(new DinnerTable(2, true));
//        llTable.add(new DinnerTable(3, false));
//        llTable.add(new DinnerTable(4, true));
//        return llTable;
//    }

    public void getInfoTable() {
        Retrofit retrofit = RetrofitClient.getClient();
        InfoTableAPI infoTableAPI = retrofit.create(InfoTableAPI.class);
        Call<List<DinnerTable>> call = infoTableAPI.getInfoTable(bundle.getString("token"));
        call.enqueue(new Callback<List<DinnerTable>>() {
            @Override
            public void onResponse(Call<List<DinnerTable>> call, Response<List<DinnerTable>> response) {
                if (response.body() != null) {
                tableAdapter = new TableAdapter(getApplicationContext(), new TableAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(DinnerTable dinnerTable) {
                        bundle = intent.getExtras();
                        bundle.putLong("idTable", dinnerTable.getId());
                        Intent intent2 = new Intent(OrderAcitivity.this, OrderTwoActivity.class);
                        intent2.putExtras(bundle);
                        startActivity(intent2);
                    }
                });

                    tableAdapter.setData(response.body());
                    RecyclerView recyclerView = findViewById(R.id.rcv_ltb);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(OrderAcitivity.this, LinearLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(tableAdapter);
                    RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                    recyclerView.addItemDecoration(itemDecoration);
                }else{
                    startActivity(new Intent(OrderAcitivity.this, MainActivity.class));
                }

            }

            @Override
            public void onFailure(Call<List<DinnerTable>> call, Throwable t) {

            }
        });
    }
}