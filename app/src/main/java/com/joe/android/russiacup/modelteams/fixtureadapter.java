package com.joe.android.russiacup.modelteams;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.joe.android.russiacup.R;
import com.joe.android.russiacup.cupinterface;
import com.joe.android.russiacup.imgloading;
import com.joe.android.russiacup.matches.adaptermatches;
import com.joe.android.russiacup.stage.Fixture;

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

public class fixtureadapter extends RecyclerView.Adapter<fixtureadapter.viewholder> {

    Context context;
    List<Fixture> teammatches;
    Call<Teams> callcuimg;
    List<Team> myteams;
    HashMap<String,String> hashMap;

    public fixtureadapter(Context context, List<Fixture> fixlist) {


        this.teammatches=fixlist;
        this.context=context;
        hashMap=new HashMap<>();
        myteams=new ArrayList<>();

        OkHttpClient.Builder builder1 = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor1 = new HttpLoggingInterceptor();
        loggingInterceptor1.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder1.addInterceptor(loggingInterceptor1);


        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://api.football-data.org/v1/competitions/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder1.build())
                .build();

        cupinterface service1 = retrofit1.create(cupinterface.class);
        callcuimg = service1.getteams();
        callcuimg.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse(Call<Teams> call, Response<Teams> response) {

                myteams=  response.body().getTeams();
                for (int j=0 ; j <32;j++) {

                    hashMap.put(myteams.get(j).getName(), myteams.get(j).getCrestUrl());
                }
            }

            @Override
            public void onFailure(Call<Teams> call, Throwable t) {

            }
        });

    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.matcheslayout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {





        if (teammatches.get(position).getResult().getGoalsHomeTeam()==null)
        {
            holder.homeresultt.setText(String.valueOf("0"));
        }
        else {
            holder.homeresultt.setText(String.valueOf((int) ((Double)  teammatches.get(position).getResult().getGoalsHomeTeam()).doubleValue()));
        }

        if (teammatches.get(position).getResult().getGoalsAwayTeam()==null)
        {
            holder.awayresultt.setText(String.valueOf("0"));
        }
        else {
            holder.awayresultt.setText(String.valueOf((int) ((Double)  teammatches.get(position).getResult().getGoalsAwayTeam()).doubleValue()));
        }




        holder.countname1.setText(teammatches.get(position).getHomeTeamName());
        holder.countryname2.setText(teammatches.get(position).getAwayTeamName());
        holder.matchdataa.setText(teammatches.get(position).getDate());
        holder.matchstatuses.setText(teammatches.get(position).getStatus());


        String homeimg=teammatches.get(position).getHomeTeamName();
        String awayteam=teammatches.get(position).getAwayTeamName();
        String v1=hashMap.get(homeimg);
        String v2=hashMap.get(awayteam);



        SvgLoader.pluck()
                .with((Activity) context)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(String.valueOf(v1), holder.homeflagg);

        SvgLoader.pluck()
                .with((Activity) context)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(String.valueOf(v2),holder.awayflagg);

    }

    @Override
    public int getItemCount() {
        return teammatches.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        ImageView homeflagg, awayflagg;
        TextView homeresultt, awayresultt, matchdataa, countname1, countryname2,matchstatuses;
        LinearLayout linear;
        SwipeRefreshLayout refreshLayout;

        public viewholder(View itemView) {
            super(itemView);

            matchstatuses=itemView.findViewById(R.id.matchstatus);



            homeflagg = itemView.findViewById(R.id.homeflag);
            awayflagg = itemView.findViewById(R.id.awayflag);

            countname1 = itemView.findViewById(R.id.cou_nom1);
            countryname2 = itemView.findViewById(R.id.cou_nom2);



            homeresultt = itemView.findViewById(R.id.homeresult);
            awayresultt = itemView.findViewById(R.id.awayresult);

//                linear = itemView.findViewById(R.id.mylinar);


            matchdataa = itemView.findViewById(R.id.matchdata);
        }
    }
}
