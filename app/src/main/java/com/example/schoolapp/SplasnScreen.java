package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplasnScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasn_screen);

        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    Intent intern = new Intent(com.example.schoolapp.SplasnScreen.this,MainActivity.class);
                    startActivity(intern);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }


}