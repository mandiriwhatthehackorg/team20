package com.marimo.whatthehack.apps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclingActivity extends AppCompatActivity {

    //Camera matter
    static final int REQ_IMAGE_CAPTURE = 98;
    static final String TAG = "RecyclingCamera";
    ImageView thumbnail;
    String encoded;


    Spinner jenisBarang;

    String selectedSpinnerItem;
    String selectedQuality;

    String descStr;
    EditText desc;

    String jumlahStr;
    EditText jumlah_barang;

    Button image, submit;

    RadioGroup radioGroup;

    SharedPreferences prefs;
    List<String> spinnerList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling);

        try{
            this.getSupportActionBar().setDisplayShowHomeEnabled(true);
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }catch (NullPointerException e){

        }

        prefs = getSharedPreferences("LoginPerfs", MODE_PRIVATE);

        jenisBarang = findViewById(R.id.recycling_spinner);
        radioGroup = findViewById(R.id.radioGroup);
        desc = findViewById(R.id.recycling_descEdt);

        thumbnail = findViewById(R.id.recycling_imageView);
        jumlah_barang = findViewById(R.id.recycling_jumlah_barang);

//        image = findViewById(R.id.recycling_Image);
        submit = findViewById(R.id.recycling_submit);

        spinnerList = new ArrayList<String>();
        spinnerList.add("Komputer");
        spinnerList.add("Smartphone");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisBarang.setAdapter(adapter);

        radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRadioButtonClicked(view);
            }
        });


        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PenukaranActivity.class);
                startActivity(intent);
//
//                selectedSpinnerItem = jenisBarang.getSelectedItem().toString();
//                try{
//                    if(selectedQuality.isEmpty()){
//                        Toast.makeText(RecyclingActivity.this,"Pilih kualitas barang", Toast.LENGTH_SHORT);
//                    }
//
//                    if(encoded.isEmpty()){
//                        Toast.makeText(RecyclingActivity.this,"Masukan Gambar barang", Toast.LENGTH_SHORT);
//
//                    }
//
//                    if(descStr.isEmpty()){
//                        Toast.makeText(RecyclingActivity.this,"Masukan Description barang", Toast.LENGTH_SHORT);
//                    }
//
//                    if(jumlahStr.isEmpty()){
//                        Toast.makeText(RecyclingActivity.this,"Masukan Jumlah barang", Toast.LENGTH_SHORT);
//                    }
//
//                    descStr = desc.getText().toString();
//                    jumlahStr = jumlah_barang.getText().toString();
//                    String userId = prefs.getString("user_id",null);
//
//
//                    submitRecycle(selectedSpinnerItem,selectedQuality,descStr,encoded,userId,jumlahStr, "0");
//
//
//                }catch (NullPointerException e){
//                    e.printStackTrace();
//                }


            }
        });

    }

    private void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.quality_1 :
                if(checked){
                    selectedQuality = "1";
                }
                break;
            case R.id.quality_2 :
                if(checked){
                    selectedQuality = "2";
                }
            case R.id.quality_3:
                if(checked){
                    selectedQuality = "3";
                }
            case R.id.quality_4:
                if(checked){
                    selectedQuality = "4";
                }
            case R.id.quality_5:
                if(checked){
                    selectedQuality = "5";
                }
        }
    }

    private void openCamera(){
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(captureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(captureIntent,REQ_IMAGE_CAPTURE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_IMAGE_CAPTURE && resultCode == RESULT_OK){



            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            final int maxSize = 260;
            int outWidth;
            int outHeight;
            int inWidth = imageBitmap.getWidth();
            int inHeight = imageBitmap.getHeight();
            if(inWidth > inHeight){
                outWidth = maxSize;
                outHeight = (inHeight * maxSize) / inWidth;
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;
            }

            thumbnail.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,outWidth,outHeight,false));
            image2string(imageBitmap);
        }
    }

    private void image2string(Bitmap image){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();

        encoded = Base64.encodeToString(byteArray,Base64.DEFAULT);
        Log.v(TAG, "image2string encoded : " + encoded);
    }

    private void submitRecycle(final String jenis_barang, final String kondisi, final String descStr, final String image,
                               final String userId, final String jumlah_barang, final String status){

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.

                        // Showing Echo Response Message Coming From Server.
                        Toast.makeText(RecyclingActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.

                        // Showing error message if something goes wrong.
                        Toast.makeText(RecyclingActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                        finish();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("jenisBarang", jenis_barang);
                params.put("kondisi", kondisi);
                params.put("description", descStr);
                params.put("image", encoded);
                params.put("user_id", userId);
                params.put("jumlah_barang", jumlahStr);
                params.put("status", status);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
