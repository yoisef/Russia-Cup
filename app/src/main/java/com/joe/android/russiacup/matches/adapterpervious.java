package com.joe.android.russiacup.matches;

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
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;
import com.joe.android.russiacup.R;
import com.joe.android.russiacup.cupinterface;
import com.joe.android.russiacup.modelteams.Team;
import com.joe.android.russiacup.modelteams.Teams;

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

public class adapterpervious extends RecyclerView.Adapter<adapterpervious.viewholder> {

    Context context;
    Call<Teams> callcuimg;
    List<Team> myteams;
    List<Fixture> permatches;
    Call<Matches> mycall;
    HashMap<String,String> hashMap;

    public adapterpervious(final Context context) {
        this.context = context;
        this.permatches = new ArrayList<>();
        hashMap =new HashMap<>();
        myteams=new ArrayList<>();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(loggingInterceptor);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.football-data.org/v1/competitions/467/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        cupinterface service = retrofit.create(cupinterface.class);
        mycall = service.getperviousmatches();

        mycall.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {

                permatches = response.body().getFixtures();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Toast.makeText(context,"Check Internet connection",Toast.LENGTH_LONG).show();


            }
        });






        OkHttpClient.Builder builder1 = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor1 = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder1.addInterceptor(loggingInterceptor1);


        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://api.football-data.org/v1/competitions/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
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
    public adapterpervious.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View matches = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.matcheslayout, parent, false);
        adapterpervious.viewholder viewholder = new adapterpervious.viewholder(matches);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterpervious.viewholder holder, int position) {

        holder.homeresultt.setText(String.valueOf(permatches.get(position).getResult().getGoalsHomeTeam()));
        holder.awayresultt.setText(String.valueOf(permatches.get(position).getResult().getGoalsAwayTeam()));
        holder.matchdataa.setText(permatches.get(position).getDate().toString());
        holder.countname1.setText(permatches.get(position).getHomeTeamName());
        holder.countryname2.setText(permatches.get(position).getAwayTeamName());
        holder.matchstatuses.setText(permatches.get(position).getStatus());

        String homename = permatches.get(position).getHomeTeamName();
        String awayname = permatches.get(position).getAwayTeamName();

        SvgLoader.pluck()
                .with((Activity) context)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(String.valueOf(hashMap.get(homename)), holder.homeflagg);
        SvgLoader.pluck()
                .with((Activity) context)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(String.valueOf(hashMap.get(awayname)), holder.awayflagg);

    }

    @Override
    public int getItemCount() {
        return permatches.size();
    }

    class viewholder extends RecyclerView.ViewHolder {

        ImageView homeflagg, awayflagg;
        TextView homeresultt, awayresultt, matchdataa, countname1, countryname2, matchstatuses;
        LinearLayout linear;
        SwipeRefreshLayout refreshLayout;

        public viewholder(View itemView) {
            super(itemView);

            matchstatuses = itemView.findViewById(R.id.matchstatus);


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
