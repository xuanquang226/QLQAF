package com.example.qlqa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlqa.api.GetDish;
import com.example.qlqa.model.DinnerTable;
import com.example.qlqa.model.Dish;
import com.example.qlqa.model.Row;
import com.example.qlqa.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderTwoActivity extends AppCompatActivity {
    ArrayList<String> nameDish;
    private Retrofit retrofit;
    private ListView lv;
    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv_total_amount;

    private ImageButton imgButton, imgButton2;

    private TableRow ntableRow, tableRow1;
    private TableLayout tableLayout;

    private int basePrice = 0;
    private double totalPrice;
    private EditText edt_dialog_note;
    private Button btn_dialog_confirm;

    private String note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_two_layout);

        DinnerTable dinnerTable = (DinnerTable) getIntent().getSerializableExtra("infoTable");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bàn " + dinnerTable.getoNumber());
        actionBar.setDisplayHomeAsUpEnabled(true);


//        lv = findViewById(R.id.lv_list_dish);
//        listView();

        createMenuTable();

        tv_total_amount = (TextView) findViewById(R.id.tv_total_amount);
    }

    public void createMenuTable() {
        retrofit = RetrofitClient.getClient();
        GetDish getDish = retrofit.create(GetDish.class);
        Call<List<Dish>> call = getDish.getListDish();
        call.enqueue(new Callback<List<Dish>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                nameDish = new ArrayList<>();
                for (Dish dish : response.body()) {
                    nameDish.add(dish.getName());
                }
//                lv.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, nameDish));


                // Create table layout
                tableLayout = (TableLayout) findViewById(R.id.tl_nameDish);

                // Table row 1
                tableRow1 = new TableRow(getApplicationContext());
                tableRow1.setBackgroundColor(getColor(R.color.moderate_blue));
                tableRow1.setDividerDrawable(getDrawable(R.color.black));
                tableRow1.setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
                tableRow1.setGravity(Gravity.CENTER);


                tv1 = new TextView(getApplicationContext());
                tv1.setText(R.string.list_dish);
                tv1.setTextColor(getColor(R.color.white_b));
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv1.setGravity(Gravity.CENTER);
                tableRow1.addView(tv1, new TableRow.LayoutParams(1, TableRow.LayoutParams.WRAP_CONTENT));


                tv2 = new TextView(getApplicationContext());
                tv2.setText(R.string.quantity);
                tv2.setTextColor(getColor(R.color.white_b));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv2.setGravity(Gravity.CENTER);
                tableRow1.addView(tv2, new TableRow.LayoutParams(1, TableRow.LayoutParams.WRAP_CONTENT));

                tv3 = new TextView(getApplicationContext());
                tv3.setText(R.string.into_money);
                tv3.setTextColor(getColor(R.color.white_b));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv3.setGravity(Gravity.CENTER);
                tableRow1.addView(tv3, new TableRow.LayoutParams(1, TableRow.LayoutParams.WRAP_CONTENT));

                tv4 = new TextView(getApplicationContext());
                tv4.setText(R.string.note);
                tv4.setTextColor(getColor(R.color.white_b));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv4.setGravity(Gravity.CENTER);
                tableRow1.addView(tv4, new TableRow.LayoutParams(1, TableRow.LayoutParams.WRAP_CONTENT));

                tableLayout.addView(tableRow1);

                // Table row 2
                ntableRow = new TableRow(getApplicationContext());
                for (int i = 0; i < nameDish.size(); i++) {
                    if ((i % 2) == 0) {
                        ntableRow.setBackgroundColor(getColor(R.color.light_blue));
                    } else {
                        ntableRow.setBackgroundColor(getColor(R.color.back_ground));
                    }

                    ntableRow = new TableRow(getApplicationContext());
                    ntableRow.setDividerDrawable(getDrawable(R.color.black));
                    ntableRow.setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);


                    //Column 1
                    tv5 = new TextView(getApplicationContext());
                    tv5.setText(response.body().get(i).getName());
                    tv5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                    tv5.setTextColor(getColor(R.color.black));
                    tv5.setGravity(Gravity.CENTER);
                    //tv5.setPadding(2,2,2,2);
                    ntableRow.addView(tv5, new TableRow.LayoutParams(1, 150));


                    // Column 2
                    imgButton = new ImageButton(getApplicationContext());
                    imgButton.setId(R.id.imgButton);
                    imgButton.setImageResource(R.drawable.baseline_remove_24);
                    imgButton.setBackground(getDrawable(R.drawable.img_button_color));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
                    params.setMarginEnd(15);
                    imgButton.setLayoutParams(params);

                    tv6 = new TextView(getApplicationContext());
                    tv6.setText("0");
                    tv6.setTextColor(getColor(R.color.black));

                    imgButton2 = new ImageButton(getApplicationContext());
                    imgButton2.setId(R.id.imgButton2);
                    imgButton2.setImageResource(R.drawable.baseline_add_24);
                    imgButton2.setBackground(getDrawable(R.drawable.img_button_color));
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(50, 50);
                    params1.setMarginStart(15);
                    imgButton2.setLayoutParams(params1);

                    LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout.setGravity(Gravity.CENTER);
                    linearLayout.addView(imgButton);
                    linearLayout.addView(tv6);
                    linearLayout.addView(imgButton2);
                    ntableRow.addView(linearLayout, new TableRow.LayoutParams(1, 150));


                    // Column 3
                    tv7 = new TextView(getApplicationContext());
                    tv7.setText("0.0");
                    tv7.setGravity(Gravity.CENTER);
                    ntableRow.addView(tv7, new TableRow.LayoutParams(1, TableRow.LayoutParams.WRAP_CONTENT));


                    // Column 4
                    tv8 = new TextView(getApplicationContext());
                    tv8.setText("Nhấn");
                    tv8.setGravity(Gravity.CENTER);
                    ntableRow.addView(tv8, new TableRow.LayoutParams(1, TableRow.LayoutParams.WRAP_CONTENT));
                    tableLayout.addView(ntableRow);
                }
                handleImgButton(0, response);
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    public void handleImgButton(int initQuantity, Response<List<Dish>> response) {

        // Get table row
        List<TableRow> tableRowList = new ArrayList<>();
        for (int i = 1; i < tableLayout.getChildCount(); i++) {
            tableRowList.add((TableRow) tableLayout.getChildAt(i));
        }

        // Get view of row
        List<Row> rowListAtt = new ArrayList<>();
        for (int i = 0; i < tableRowList.size(); i++) {
            rowListAtt.add(new Row((TextView) tableRowList.get(i).getChildAt(0), (LinearLayout) tableRowList.get(i).getChildAt(1)
                    , (TextView) tableRowList.get(i).getChildAt(2), (TextView) tableRowList.get(i).getChildAt(3)));
        }

        // Get view of linearlayout quantity
        for (int i = 0; i < rowListAtt.size(); i++) {
            rowListAtt.get(i).setImgBtnDecrease((ImageButton) rowListAtt.get(i).getLinearLayoutQuantity().getChildAt(0));
            rowListAtt.get(i).setTvQuantity((TextView) rowListAtt.get(i).getLinearLayoutQuantity().getChildAt(1));
            rowListAtt.get(i).setImgBtnIncrease((ImageButton) rowListAtt.get(i).getLinearLayoutQuantity().getChildAt(2));

        }


        // Event click
        for (int i = 0; i < rowListAtt.size(); i++) {
            int position = i;
            List<Dish> dishList = response.body();
            double price = dishList.get(position).getPrice();
            int quantity = dishList.get(position).getQuantity();

            rowListAtt.get(position).getImgBtnIncrease().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tvQuantity = Integer.parseInt(rowListAtt.get(position).getTvQuantity().getText().toString());
                    if (quantity != 0 && tvQuantity < quantity) {
                        // Quantity to price
                        rowListAtt.get(position).getTvQuantity().setText(String.valueOf(tvQuantity + 1));
                        int tvQuantityChange = Integer.parseInt(rowListAtt.get(position).getTvQuantity().getText().toString());
                        if (tvQuantityChange > 0) {
                            rowListAtt.get(position).getTvPrice().setText(String.valueOf(price * tvQuantityChange));
                        }

                        // Set total price
                        totalPrice = 0;
                        for (int j = 0; j < rowListAtt.size(); j++) {
                            totalPrice += Double.parseDouble(rowListAtt.get(j).getTvPrice().getText().toString());
                        }
                        tv_total_amount.setText(String.valueOf(totalPrice) + " Đ");
                    } else {
                        Toast.makeText(OrderTwoActivity.this, "Hết món " + rowListAtt.get(position).getTvNameDish().getText(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

            rowListAtt.get(position).getImgBtnDecrease().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tvQuantity = Integer.parseInt(rowListAtt.get(position).getTvQuantity().getText().toString());
                    if (tvQuantity > 0) {
                        rowListAtt.get(position).getTvQuantity().setText(String.valueOf(tvQuantity - 1));
                        int tvQuantityChange = Integer.parseInt(rowListAtt.get(position).getTvQuantity().getText().toString());
                        rowListAtt.get(position).getTvPrice().setText(String.valueOf(price * tvQuantityChange));
                        if(tvQuantityChange == 0){
                            rowListAtt.get(position).getTvNote().setText("Nhấn");
                            edt_dialog_note.setText("");
                        }
                    }else{
                        rowListAtt.get(position).getTvNote().setText("Nhấn");
                        edt_dialog_note.setText("");
                    }
                    totalPrice = 0;
                    for (int j = 0; j < rowListAtt.size(); j++) {
                        totalPrice += Double.parseDouble(rowListAtt.get(j).getTvPrice().getText().toString());
                    }
                    tv_total_amount.setText(String.valueOf(totalPrice) + " Đ");
                }
            });


            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_note_layout);
            rowListAtt.get(position).getTvNote().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Window window = dialog.getWindow();
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    WindowManager.LayoutParams windowAtt = window.getAttributes();
                    windowAtt.gravity = Gravity.CENTER;
                    window.setAttributes(windowAtt);
                    btn_dialog_confirm = dialog.findViewById(R.id.btn_dialog_confirm);
                    edt_dialog_note = dialog.findViewById(R.id.edt_dialog_note);
                    btn_dialog_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rowListAtt.get(position).getTvNote().setText(edt_dialog_note.getText());
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }
}