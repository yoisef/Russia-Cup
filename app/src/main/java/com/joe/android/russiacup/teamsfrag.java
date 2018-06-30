package com.joe.android.russiacup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.joe.android.russiacup.Players.Player;
import com.joe.android.russiacup.Players.Players;
import com.joe.android.russiacup.models.groupadapter;
import com.joe.android.russiacup.modelteams.Team;
import com.joe.android.russiacup.modelteams.Teams;
import com.joe.android.russiacup.modelteams.fixtureadapter;
import com.joe.android.russiacup.modelteams.playersadapter;
import com.joe.android.russiacup.modelteams.teamapter;
import com.joe.android.russiacup.stage.Fixture;
import com.joe.android.russiacup.stage.Stagematches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class teamsfrag extends Fragment {

    RecyclerView teamsrecycle;
   Activity context;
   Call<Teams> mycall;
   Call<Players> playerCall;
   List<Team> teamspart;
   List<Fixture> fixlist;
   HashMap<String,String> hashMap,hashMap2;
   AlertDialog.Builder builder;
   AlertDialog alertDialog;
   SwipeRefreshLayout swipeeamss;
    View myview;
    TextView backbu;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.team,container,false);


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context= (Activity) view.getContext();

        teamspart=new ArrayList<>();
        hashMap=new HashMap<>();
        hashMap2=new HashMap<>();

        fixlist=new ArrayList<>();
        swipeeamss=view.findViewById(R.id.swipeteams);
        swipeeamss.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                teamsrecycle.setAdapter(new teamapter(context));
                swipeeamss.setRefreshing(false);


            }
        });


        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-9508195472439107/9954439930");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitialAd.loadAd(adRequest);
            }
        });


        mAdView = view.findViewById(R.id.teamsadview);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener(){

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);



            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });


     builder = new AlertDialog.Builder(context);

       myview = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.testlayout, null);
        builder.setView(myview);
      alertDialog = builder.create();




        context = getActivity();
        teamsrecycle = (RecyclerView) view.findViewById(R.id.teamsrecycle);

       GridLayoutManager manager = new GridLayoutManager(context,4);
        teamsrecycle.setLayoutManager(manager);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(loggingInterceptor);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.football-data.org/v1/competitions/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        cupinterface service = retrofit.create(cupinterface.class);
        mycall = service.getteams();
        mycall.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse(Call<Teams> call, Response<Teams> response) {

                teamspart = response.body().getTeams();




                for (int h = 0; h < teamspart.size(); h++) {
                    String herf = teamspart.get(h).getLinks().getPlayers().getHref();
                    String herffixtures=teamspart.get(h).getLinks().getFixtures().getHref();
                    String endpoint = herf.substring(38);
                    String endpointF=herffixtures.substring(38);
                    hashMap.put(teamspart.get(h).getName(), endpoint);
                    hashMap2.put(teamspart.get(h).getName(), endpointF);

                }

            }

            @Override
            public void onFailure(Call<Teams> call, Throwable t) {
                Toast.makeText(context,"Check Internet connection",Toast.LENGTH_LONG).show();


            }
        });


        teamsrecycle.setAdapter(new teamapter(context));

         teamsrecycle.addOnItemTouchListener(new RecyclerItemClickListener(context, teamsrecycle, new RecyclerItemClickListener.OnItemClickListener() {
                     @Override
                     public void onItemClick(final View view, final int position) {


                         switch (position) {
                             default: {


                                 if (mInterstitialAd.isLoaded()) {
                                     mInterstitialAd.show();
                                 } else {
                                     Log.d("TAG", "The interstitial wasn't loaded yet.");
                                 }

                                 OkHttpClient.Builder builder1 = new OkHttpClient.Builder();

                                 HttpLoggingInterceptor loggingInterceptor1 = new HttpLoggingInterceptor();
                                 loggingInterceptor1.setLevel(HttpLoggingInterceptor.Level.BODY);

                                 builder1.addInterceptor(loggingInterceptor1);


                                 Retrofit retrofit1 = new Retrofit.Builder()
                                         .baseUrl("http://api.football-data.org/v1/teams/")
                                         .addConverterFactory(GsonConverterFactory.create())
                                         .client(builder1.build())
                                         .build();

                                 cupinterface service1 = retrofit1.create(cupinterface.class);
                                 Call<Stagematches> fixcall = service1.getstagematches(hashMap2.get(teamspart.get(position).getName()));
                                 fixcall.enqueue(new Callback<Stagematches>() {
                                     @Override
                                     public void onResponse(Call<Stagematches> call, Response<Stagematches> response) {

                                         fixlist = response.body().getFixtures();


                                         RecyclerView fixrecyc = myview.findViewById(R.id.fixturematchesrecycleview);

                                         fixrecyc.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));

                                         fixrecyc.setAdapter(new fixtureadapter(context, fixlist));


                                     }

                                     @Override
                                     public void onFailure(Call<Stagematches> call, Throwable t) {
                                         Toast.makeText(context, "Check Internet connection", Toast.LENGTH_LONG).show();


                                     }
                                 });


                                 OkHttpClient.Builder builder = new OkHttpClient.Builder();

                                 HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                                 loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                                 builder.addInterceptor(loggingInterceptor);


                                 Retrofit retrofit = new Retrofit.Builder()
                                         .baseUrl("http://api.football-data.org/v1/teams/")
                                         .addConverterFactory(GsonConverterFactory.create())
                                         .client(builder.build())
                                         .build();

                                 cupinterface service = retrofit.create(cupinterface.class);
                                 playerCall = service.getplayer(hashMap.get(teamspart.get(position).getName()));
                                 playerCall.enqueue(new Callback<Players>() {
                                     @Override
                                     public void onResponse(Call<Players> call, Response<Players> response) {
                                         List<Player> myplayers =new ArrayList<>();
                                         myplayers=response.body().getPlayers();

                                         RecyclerView mrecycle = myview.findViewById(R.id.recycleplayer);
                                         TextView numberofplayers = myview.findViewById(R.id.valuenumberplayers);
                                         TextView backbu = myview.findViewById(R.id.backbutton);
                                         backbu.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {

                                                 alertDialog.dismiss();
                                             }
                                         });


                                         Context con = myview.getContext();
                                         LinearLayoutManager manager = new LinearLayoutManager(context);
                                         mrecycle.setLayoutManager(manager);
                                         mrecycle.setAdapter(new playersadapter(context, myplayers));
                                         mrecycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                                         numberofplayers.setText(String.valueOf(response.body().getCount()));

                                     }

                                     @Override
                                     public void onFailure(Call<Players> call, Throwable t) {
                                         Toast.makeText(context, "Check Internet connection", Toast.LENGTH_LONG).show();


                                     }
                                 });


                                 alertDialog.show();


                                 break;


                             }
                         }
                     }

                     @Override
                     public void onLongItemClick(View view, int position) {

                     }


                 })
         );
    }





}
