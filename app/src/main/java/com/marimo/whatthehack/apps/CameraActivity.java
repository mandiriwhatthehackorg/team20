package com.marimo.whatthehack.apps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class CameraActivity extends AppCompatActivity {

    static final int REQ_IMAGE_CAPTURE = 98;
    static final String TAG = "CameraActivity";

    ImageView thumbnail;
    FloatingActionButton fab;

    String encoded;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camera, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.check_camera:
                submitPhoto();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void submitPhoto(){
        if(!encoded.isEmpty()){
            // DO courier works
            Intent intent = new Intent(CameraActivity.this,RecyclingActivity.class);
            intent.putExtra("imageStr",encoded);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        thumbnail = findViewById(R.id.imgPicture);
        fab = findViewById(R.id.fabPicture);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureIntent();
            }
        });
    }

    private void takePictureIntent(){
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

            thumbnail.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,400,700,false));
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

}
