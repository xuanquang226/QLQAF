package com.example.qlqa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlqa.adapter.DishAdapter;
import com.example.qlqa.api.DishAPI;
import com.example.qlqa.model.Dish;
import com.example.qlqa.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MenuAdjustment extends AppCompatActivity {
    private DishAdapter dishAdapter;
    private DishAPI dishAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_adjustment_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sửa món");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getDrawable(R.color.moderate_blue));

        Retrofit retrofit = RetrofitClient.getClient();
        dishAPI = retrofit.create(DishAPI.class);

        getDish();
    }

    public void getDish(){

        Call<List<com.example.qlqa.model.Dish>> call = dishAPI.getListDish();

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_dish_layout);

        call.enqueue(new Callback<List<com.example.qlqa.model.Dish>>() {
            @Override
            public void onResponse(Call<List<com.example.qlqa.model.Dish>> call, Response<List<com.example.qlqa.model.Dish>> response) {
                dishAdapter = new DishAdapter(new DishAdapter.ItemClickListener() {
                    @Override
                    public void onClick(Dish dish) {

                        Window window = dialog.getWindow();
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        WindowManager.LayoutParams windowAttributes = window.getAttributes();
                        windowAttributes.gravity = Gravity.CENTER;
                        window.setAttributes(windowAttributes);

                        TextView tvNameDish = (TextView) dialog.findViewById(R.id.tv_name_dish_dialog);
                        tvNameDish.setText(dish.getName());
                        EditText edtNewNameDish = (EditText) dialog.findViewById(R.id.edt_name_dish);
                        EditText edtNewQuantityDish = (EditText) dialog.findViewById(R.id.edt_quantity_dish);
                        EditText edtNewPriceDish = (EditText) dialog.findViewById(R.id.edt_price_dish);

                        edtNewNameDish.setText(dish.getName());
                        edtNewQuantityDish.setText(String.valueOf(dish.getQuantity()));
                        edtNewPriceDish.setText(String.valueOf(dish.getPrice()));

                        Button btnDialogConfirm = (Button) dialog.findViewById(R.id.btn_confirm_dialog_dish);
                        Button btnDialogDelete = (Button) dialog.findViewById(R.id.btn_delete_dialog_dish);

                        btnDialogConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dish.setName(edtNewNameDish.getText().toString());
                                dish.setQuantity(Integer.parseInt(edtNewQuantityDish.getText().toString()));
                                dish.setPrice(Double.parseDouble(edtNewPriceDish.getText().toString()));
                                Call<String> call1 = dishAPI.putNameAQuantityDish(dish.getId(), dish);
                                call1.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });
                                dialog.dismiss();
                                finish();
                                overridePendingTransition(0,0);
                                startActivity(getIntent());

                            }
                        });
                        btnDialogDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Call<Void> call = dishAPI.deleteDish(dish.getId());
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Toast.makeText(MenuAdjustment.this, "Món "+ dish.getName() + " đã bị xoá", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                    }
                                });

                                dialog.dismiss();
                                finish();
                                overridePendingTransition(0,0);
                                startActivity(getIntent());
                            }
                        });

                        dialog.show();

                    }
                });
                dishAdapter.setData(response.body());
                RecyclerView rcvDish = (RecyclerView) findViewById(R.id.rcv_list_dish);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MenuAdjustment.this, LinearLayoutManager.VERTICAL, false);
                rcvDish.setLayoutManager(linearLayoutManager);
                rcvDish.setAdapter(dishAdapter);

                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                rcvDish.addItemDecoration(itemDecoration);

            }

            @Override
            public void onFailure(Call<List<com.example.qlqa.model.Dish>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_add:
                Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_dish_layout);

                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams winAtt = window.getAttributes();
                winAtt.gravity = Gravity.CENTER;
                window.setAttributes(winAtt);

                EditText edtNewNameDish = (EditText) dialog.findViewById(R.id.edt_add_name_dish);
                EditText edtNewQuantityDish = (EditText) dialog.findViewById(R.id.edt_add_quantity_dish);
                EditText edtNewPriceDish = (EditText) dialog.findViewById(R.id.edt_add_price_dish);
                Button btnConfirmAddNewDish = (Button) dialog.findViewById(R.id.btn_confirm_dialog_add_dish);


                btnConfirmAddNewDish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dish dish = new Dish();
                        dish.setName(edtNewNameDish.getText().toString());
                        dish.setQuantity(Integer.parseInt(edtNewQuantityDish.getText().toString()));
                        dish.setPrice(Double.parseDouble(edtNewPriceDish.getText().toString()));
                        Call<Void> call = dishAPI.postNewDish(dish);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                        dialog.dismiss();
                        finish();
                        overridePendingTransition(0,0);
                        startActivity(getIntent());
                    }
                });

                dialog.show();
        }
        return false;
    }
}