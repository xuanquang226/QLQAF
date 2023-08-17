package com.example.qlqa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("info");

        TextView tv_user = (TextView) findViewById(R.id.tv_name);
        tv_user.setText(bundle.getString("username"));
    }
}