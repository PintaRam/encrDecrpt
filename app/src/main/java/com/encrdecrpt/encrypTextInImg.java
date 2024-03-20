package com.encrdecrpt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class encrypTextInImg extends AppCompatActivity {
private  final  int img_Req_Code = 1000;
ImageView imggal;
EditText encredit;
Button encrbtn ,encrybtn2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryp_text_in_img);
        encrbtn = findViewById(R.id.encrbtn);
        encredit= findViewById(R.id.encredit);
        imggal = findViewById(R.id.imggal);
        encrybtn2 = findViewById(R.id.encrbtn2);
        encrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent igallery = new Intent(Intent.ACTION_PICK);
                igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallery , img_Req_Code);

            }
        });
        encrybtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(encrypTextInImg.this,embedImgSaved.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode == img_Req_Code)
            {
                imggal.setImageURI(data.getData());
            }
        }
    }

}
