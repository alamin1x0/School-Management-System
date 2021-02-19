package com.example.schoolapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class    dev extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Developer");
    }
}