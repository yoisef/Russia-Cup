package com.joe.android.russiacup;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TableLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.HashMap;

public class Home extends AppCompatActivity {


     TabLayout mytab;
     ViewPager mypager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mytab=findViewById(R.id.mytabapp);
        mypager=findViewById(R.id.pagerview);
        TabLayout.Tab hometap=mytab.newTab();
        TabLayout.Tab grouptap=mytab.newTab();
        TabLayout.Tab teamstap=mytab.newTab();

        MobileAds.initialize(this,
                "ca-app-pub-9508195472439107~6041361061");






        mytab.addTab(hometap);
        mytab.addTab(grouptap);
        mytab.addTab(teamstap);



        mypager.setAdapter(new pageradapter(getSupportFragmentManager()));
        mytab.setupWithViewPager(mypager);
        hometap.setText("Teams");
        //Icon made by Pixel perfect from www.flaticon.com
        hometap.setIcon(R.drawable.teamboth);
        grouptap.setText("Groups");
        //Icon made by Freepik from www.flaticon.com
        grouptap.setIcon(R.drawable.groupboth);
        teamstap.setText("Home");
        //Icon made by Vitaly Gorbachev from www.flaticon.com
        teamstap.setIcon(R.drawable.homeboth);
      //  mytab.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        mytab.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        mytab.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#3498DB"));





    }
    public class pageradapter extends FragmentPagerAdapter{

         public pageradapter(FragmentManager fm) {
             super(fm);
         }

         @Override
         public Fragment getItem(int position) {
             Fragment curfrag=new Fragment();
             switch (position)
             {
                 case 0:curfrag=new homefrag();
                 break;
                 case 1:curfrag=new Groupfrag2();
                 break;
                 case 2:curfrag=new teamsfrag();
                 break;
             }
             return curfrag;
         }

         @Override
         public int getCount() {
             return 3;
         }


     }


}
