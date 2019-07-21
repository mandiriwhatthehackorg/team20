package com.marimo.whatthehack.apps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.marimo.whatthehack.apps.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button button, daftar;
    String userStr,passStr;

    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.login_usernameEdt);
        password = findViewById(R.id.login_passwordEdt);
        button = findViewById(R.id.loginBtn);
        daftar = findViewById(R.id.login_daftar_button);

        editor = getSharedPreferences("LoginPerfs",MODE_PRIVATE).edit();

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, PendaftaranActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkLoginUser();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void checkLoginUser(){
        if(!username.getText().toString().isEmpty()){
            userStr = username.getText().toString();
        }else{
            Toast.makeText(this,"Masukan Username anda", Toast.LENGTH_SHORT);
        }


        if(!password.getText().toString().isEmpty()){
            passStr = password.getText().toString();
        }else{
            Toast.makeText(this,"Masukan Password anda", Toast.LENGTH_SHORT);
        }

        if(!passStr.isEmpty() && !userStr.isEmpty()){
            validateUser(userStr,passStr);
        }
    }

    private void validateUser(final String username, final String password){

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.
                        try{
                            JSONObject jsonObject = new JSONObject(ServerResponse);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject explrObject = jsonArray.getJSONObject(i);

                                editor.putInt("user_id", explrObject.getInt("id"));
                                editor.putString("name", explrObject.getString("name"));
                                editor.putString("alamat", explrObject.getString("alamat"));
                                editor.putString("rtrw", explrObject.getString("rtrw"));
                                editor.putString("kelurahan", explrObject.getString("kelurahan"));
                                editor.putString("kecamatan", explrObject.getString("kecamatan"));
                                editor.apply();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        // Get Json array
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.

                        // Showing error message if something goes wrong.
                        Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
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
                params.put("password", password);

                return params;
            }
        };
        int socketTimeout = 3000000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }
}
