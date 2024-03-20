package com.encrdecrpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import  android.os.Handler;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    Animation top,bottom,blink;
    ImageView img;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Hooks
        top=AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom=AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        blink=AnimationUtils.loadAnimation(this,R.anim.blink);

        img=(ImageView)findViewById(R.id.splashimg);
        txt=(TextView)findViewById(R.id.splashtext);

        img.setAnimation(top);
        img.setAnimation(blink);
        txt.setAnimation(bottom);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this, Register.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}