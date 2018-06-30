package com.joe.android.russiacup;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.joe.android.russiacup.Newes.Datum;
import com.joe.android.russiacup.Newes.News;
import com.joe.android.russiacup.Newes.adapternews;
import com.joe.android.russiacup.matches.Fixture;
import com.joe.android.russiacup.matches.Matches;
import com.joe.android.russiacup.matches.adaptermatches;
import com.joe.android.russiacup.matches.adapterpervious;
import com.joe.android.russiacup.models.Groups;
import com.joe.android.russiacup.models.groupadapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class homefrag extends Fragment {

    TextView test;
    Call<Matches> mycall;
    List<Fixture> matches;
    RecyclerView recyclematchesnext,newsrecycle,perviousrecycle;
    Activity context;
    SwipeRefreshLayout myswipe;
    Call<Matches> comingmatches;
    Call<News> callnews;
    List<Datum> listnewsz;
    ProgressBar progressBar;
    private AdView mAdView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity();

        matches=new ArrayList<>();





        mAdView = view.findViewById(R.id.homeadView);
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












        recyclematchesnext = (RecyclerView) view.findViewById(R.id.matchesrecyclenext);

        perviousrecycle=view.findViewById(R.id.matchesrecyclepervious);


        LinearLayoutManager manager = new LinearLayoutManager(context);
        LinearLayoutManager manager1= new LinearLayoutManager(context);
        LinearLayoutManager manager2 = new LinearLayoutManager(context);
        recyclematchesnext.setLayoutManager(manager);

        perviousrecycle.setLayoutManager(manager2);
        recyclematchesnext.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        perviousrecycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));


        recyclematchesnext.setAdapter(new adaptermatches(context));
        perviousrecycle.setAdapter(new adapterpervious(context));


    }

    private void refreshContent(){


        }






}
