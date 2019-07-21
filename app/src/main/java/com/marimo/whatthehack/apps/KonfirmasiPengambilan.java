package com.marimo.whatthehack.apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class KonfirmasiPengambilan extends AppCompatActivity {

    Button accept;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_recycling);

        accept = findViewById(R.id.detail_recycling_accept_button);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailPengambilan.class);
                startActivity(intent);
            }
        });
    }
}
