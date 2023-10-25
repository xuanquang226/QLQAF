package com.example.qlqa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qlqa.adapter.PayrollStaffAdapter;

public class PayrollActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private PayrollStaffAdapter payrollStaffAdapter;
    private int month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payroll_layout);

        payrollStaffAdapter = new PayrollStaffAdapter(PayrollActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);



//        RecyclerView recyclerView = findViewById(R.id.rcv_list_payroll_staff);
//        recyclerView.addItemDecoration(itemDecoration);
//        recyclerView.setAdapter(payrollStaffAdapter);
//        recyclerView.setLayoutManager(linearLayoutManager);


        Spinner spinnerMonth = findViewById(R.id.spinner_month);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.month, android.R.layout.simple_list_item_1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(arrayAdapter);
        spinnerMonth.setOnItemSelectedListener(this);

        Spinner spinnerYear = findViewById(R.id.spinner_year);
        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_list_item_1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(arrayAdapter1);
        spinnerYear.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.spinner_month:
                month = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                Toast.makeText(this, month + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinner_year:
                year = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                Toast.makeText(this, year + "", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}