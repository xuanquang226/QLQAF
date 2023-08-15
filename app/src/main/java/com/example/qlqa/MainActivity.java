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

        TextView tv_username = (TextView) findViewById(R.id.tv_username);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("info");

        tv_username.setText(bundle.getString("username"));
    }
}