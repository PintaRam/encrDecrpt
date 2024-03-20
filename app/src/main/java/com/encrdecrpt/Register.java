package com.encrdecrpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    TextInputLayout name, email, password, confirmpassword;
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Hooks
        name = (TextInputLayout) findViewById(R.id.username);
        email = (TextInputLayout) findViewById(R.id.email);
        password = (TextInputLayout) findViewById(R.id.paswd);
        confirmpassword = (TextInputLayout) findViewById(R.id.confirmpaswd);

        login = (Button) findViewById(R.id.log);
        register = (Button) findViewById(R.id.regis);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate_Data();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Register.this,login.class);
                startActivity(i);

            }
        });
    }

    private void validate_Data() {
        String username = name.getEditText().getText().toString().trim();
        String useremail = email.getEditText().getText().toString();
        String userpswd = password.getEditText().getText().toString();
        String usercfmr = confirmpassword.getEditText().getText().toString();


        if (username.isEmpty()) {
            name.setError("Field can't be empty..");
        } else if (!username.matches("^[a-zA-Z]+")) {
            name.setError("Numbers are not Allowed!!");
        } else if (username.length() < 3) {
            name.setError("Length should be atleast 3");
        } else {
            name.setError(null);
        }

        if (useremail.isEmpty()) {
            email.setError("Field can't be empty..");
        } else if (!useremail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            email.setError("Invalid mail...");
        } else {
            email.setError(null);
        }

        if (userpswd.isEmpty()) {
            password.setError("Field can't be empty..");
        } else if (userpswd.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            password.setError("Password is Strong");
        } else if (userpswd.length() < 6) {
            password.setError("Password length should be atleast 6");
        } else {
            password.setError(null);
        }

        if (usercfmr.isEmpty()) {
            confirmpassword.setError("Field can't be empty..");
        } else if (!usercfmr.equals(userpswd)) {
            confirmpassword.setError("password does not match with original one");
        } else {
            confirmpassword.setError(null);
        }

        if (name.getError() == null && email.getError() == null && password.getError() == null && confirmpassword.getError() == null) {
            Toast.makeText(this, "Registration Successfull !!!!", Toast.LENGTH_LONG).show();
            Intent i=new Intent(this,com.encrdecrpt.login.class);
            startActivity(i);

            //Storing Data in DataBase is Pending
        }
    }
}