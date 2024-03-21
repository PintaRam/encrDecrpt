package com.encrdecrpt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button mainbtn1 = findViewById(R.id.mainbtn1);
        Button mainbtn2 = findViewById(R.id.mainbtn2);
        mainbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(MainActivity.this , encrypTextInImg.class);
                startActivity(i);
            }
        });
        mainbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change the encrypt to decrypt
                Intent i  = new Intent(MainActivity.this , encrypTextInImg.class);
                startActivity(i);
            }
        });


    }
}