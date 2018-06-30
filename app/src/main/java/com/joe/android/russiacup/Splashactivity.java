package com.joe.android.russiacup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splashactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler myhandler=new Handler();
        myhandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(Splashactivity.this,Home.class));
                finish();

            }
        },3000);
    }

    }
