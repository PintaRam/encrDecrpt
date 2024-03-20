package com.encrdecrpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {

    Animation blink,top;
    TextInputLayout Id,passwrd;
    Button log,back;
    ImageView scrnimg;
    TextView logtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hooks
        top= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        blink=AnimationUtils.loadAnimation(this,R.anim.blink);

        Id=(TextInputLayout)findViewById(R.id.textInputLayout);
        passwrd=(TextInputLayout)findViewById(R.id.PasswordInputLayout);
        log=(Button) findViewById(R.id.lognbtn);
        back=(Button) findViewById(R.id.backbtn);

        scrnimg=(ImageView)findViewById(R.id.logimg);
        logtext=findViewById(R.id.logtext);
        scrnimg.setAnimation(blink);
        scrnimg.setAnimation(top);
        logtext.setAnimation(top);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "Selected Previous", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(login.this, Register.class);
                startActivity(i);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkdata();
            }
        });

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = username.getText().toString();
//                String pass = password.getText().toString();
//                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass))
//                {
//                    Toast.makeText(login.this, "Please Enter valid information", Toast.LENGTH_SHORT).show();
//
//                }else if(pass.length()<6) {
//                    password.setError("Password length must be greater than 6");
//                }
//                else {
//                    //Please add else part
//                    Toast.makeText(login.this, "Please tell about yourself", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
    }

    private  void checkdata()
    {
        String name=Id.getEditText().getText().toString();
        String password=passwrd.getEditText().getText().toString();

        //geting Data from the DataBase and Validation is pending
        Intent i =new Intent(this, MainActivity.class);
        Toast.makeText(this, "Logged Successfully", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}