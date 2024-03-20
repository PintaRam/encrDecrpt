package com.encrdecrpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    TextInputLayout name,email,password,confirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=(TextInputLayout) findViewById(R.id.username);
        email=(TextInputLayout) findViewById(R.id.email);
        password=(TextInputLayout) findViewById(R.id.paswd);
        confirmpassword=(TextInputLayout) findViewById(R.id.confirmpaswd);

    }
}