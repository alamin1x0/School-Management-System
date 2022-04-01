package com.example.shc_learing_app.developer;

import static com.example.shc_learing_app.until.Utils.WebSite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.shc_learing_app.R;
import com.example.shc_learing_app.webview.WebviewAcitivity;

public class DeveloperActivity extends AppCompatActivity {

    RelativeLayout RLWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        RLWebsite = findViewById(R.id.RLWebsite);

        RLWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DeveloperActivity.this, WebviewAcitivity.class);
                i.putExtra("URL", WebSite);
                i.putExtra("Title", "Al-Amin");
                startActivity(i);
            }
        });
    }
}