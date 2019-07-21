package com.marimo.whatthehack.apps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    EditText username,nama, password, alamat, rtrw, kelurahan, kecamatan;
    String usernamaStr, namaStr, passwordStr, alamatStr, rtrwStr, kelurahanStr, kecamatanStr;

    Button submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        username = findViewById(R.id.usernameEdt);
        nama = findViewById(R.id.nameEdt);
        password = findViewById(R.id.passwordEdt);
        alamat = findViewById(R.id.alamatEdt);
        rtrw = findViewById(R.id.rtrwEdt);
        kelurahan = findViewById(R.id.kelurahanEdt);
        kecamatan = findViewById(R.id.kecamatanEdt);

        submit = findViewById(R.id.submitBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userValidation();
            }
        });
    }

    private void sendMahasiswaData(final String username, final String nama, final String password, final String alamat,
                                   final String rtrw, final String kelurahan, final String kecamatan){


        RequestQueue requestQueue = Volley.newRequestQueue(this);


        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.

                        // Showing Echo Response Message Coming From Server.
                        Toast.makeText(RegistrationActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.

                        // Showing error message if something goes wrong.
                        Toast.makeText(RegistrationActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                        finish();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("username", username);
                params.put("nama", nama);
                params.put("password", password);
                params.put("alamat", alamat);
                params.put("rtrw", rtrw);
                params.put("kelurahan", kelurahan);
                params.put("kecamatan", kecamatan);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void userValidation(){

        if(!username.getText().toString().equals("")){
            usernamaStr = username.getText().toString();
        }else{
            Toast.makeText(getApplicationContext(),"Masukan Username",Toast.LENGTH_SHORT).show();
        }

        if(!nama.getText().toString().equals("")){
            namaStr = nama.getText().toString();
        }else{
            Toast.makeText(getApplicationContext(),"Masukan Nama",Toast.LENGTH_SHORT).show();
        }

        if(!password.getText().toString().equals("")){
            passwordStr = password.getText().toString();
        }else{
            Toast.makeText(getApplicationContext(),"Masukan Password Anda",Toast.LENGTH_SHORT).show();
        }

        if(!alamat.getText().toString().equals("")){
            alamatStr = alamat.getText().toString();
        }else{
            Toast.makeText(getApplicationContext(),"Masukan Alamat Anda",Toast.LENGTH_SHORT).show();
        }

        if(!rtrw.getText().toString().equals("")){
            rtrwStr = rtrw.getText().toString();
        }else{
            Toast.makeText(getApplicationContext(),"Masukan RT / RW Anda",Toast.LENGTH_SHORT).show();
        }

        if(!kelurahan.getText().toString().equals("")){
            kelurahanStr = kelurahan.getText().toString();
        }else{
            Toast.makeText(getApplicationContext(),"Masukan Kelurahan Anda",Toast.LENGTH_SHORT).show();
        }

        if(!kecamatan.getText().toString().equals("")){
            kecamatanStr = kecamatan.getText().toString();
        }else{
            Toast.makeText(getApplicationContext(),"Masukan Kecamatan Anda",Toast.LENGTH_SHORT).show();
        }

        if(!kecamatanStr.isEmpty()){
            sendMahasiswaData(usernamaStr,namaStr,passwordStr,alamatStr,rtrwStr,kelurahanStr,kecamatanStr);
        }
    }

}
