package com.marimo.whatthehack.apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PenukaranActivity extends AppCompatActivity {
    Button fpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penukaran);
        fpButton = findViewById(R.id.pengambilan_fiesta_button);

        fpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MetodePengambilanActivity.class);
                startActivity(intent);
            }
        });
    }
}
