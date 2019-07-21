package com.marimo.whatthehack.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button recycling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycling = findViewById(R.id.recyclingBtn);
        try{
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){
        }

        recycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DaftarGawai.class);
                startActivity(intent);
            }
        });
    }
}
