package com.encrdecrpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username =  findViewById(R.id.editTextText);
        EditText password = findViewById(R.id.editTextText2);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass))
                {
                    Toast.makeText(login.this, "Please Enter valid information", Toast.LENGTH_SHORT).show();

                }else if(pass.length()<6) {
                    password.setError("Password length must be greater than 6");
                }
                else {
                    //Please add else part
                    Toast.makeText(login.this, "Please tell about yourself", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}