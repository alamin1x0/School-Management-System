package com.example.shc_learing_app.developer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.shc_learing_app.R;

public class developer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ডেভোলপার");
    }
}