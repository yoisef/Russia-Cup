package com.joe.android.russiacup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class webpage extends AppCompatActivity {

    WebView mypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);



        mypage=findViewById(R.id.mywebpage);


        //  WebSettings webSettings = mypage.getSettings();
      //  webSettings.setJavaScriptEnabled(true);

        Intent intent=getIntent();
        if (intent!=null)
        {
            if(intent.getExtras().getString("weburl")!=null)
            {
                String webpage=intent.getExtras().getString("weburl");
                mypage.loadUrl(webpage);
            }
            else {
                mypage.loadUrl("https://www.fifa.com/worldcup/");


            }
        }





    }
}
