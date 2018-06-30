package com.joe.android.russiacup;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.joe.android.russiacup.models.Groups;
import com.joe.android.russiacup.models.groupadapter;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class groupfrag extends Fragment {


    TextView texttest;
    Activity context;
    ImageView countryflag;
    SwipeRefreshLayout mygrouprefresh;


    RecyclerView groubrecycleview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.group,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity();


        groubrecycleview = view.findViewById(R.id.grouprecycle);

        LinearLayoutManager manager = new LinearLayoutManager(context);
        groubrecycleview.setLayoutManager(manager);

        groubrecycleview.setAdapter(new groupadapter(context));

        mygrouprefresh=view.findViewById(R.id.swipegroup);
        mygrouprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                groubrecycleview.setAdapter(new groupadapter(context));
                mygrouprefresh.setRefreshing(false);

            }
        });










    }
}
