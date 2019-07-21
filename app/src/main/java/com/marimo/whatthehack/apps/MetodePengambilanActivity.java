package com.marimo.whatthehack.apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MetodePengambilanActivity extends AppCompatActivity {

    LinearLayout courier,merchant;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengambilan);

        courier = findViewById(R.id.order_by_courier);
        merchant = findViewById(R.id.order_by_merchant);

        courier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KonfirmasiPengambilan.class);
                startActivity(intent);
            }
        });

        merchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KonfirmasiPengambilan.class);
                startActivity(intent);
            }
        });
    }
}
