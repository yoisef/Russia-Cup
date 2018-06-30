package com.joe.android.russiacup.matches;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
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
import com.joe.android.russiacup.models.Groups;
import com.joe.android.russiacup.modelteams.Team;
import com.joe.android.russiacup.modelteams.Teams;
import com.joe.android.russiacup.teamflag.Teamflag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class adaptermatches extends RecyclerView.Adapter<adaptermatches.viewholder> {

    Context context;
    Call<Matches> mycall;
    Call<Teams> callcuimg;
    List<Fixture> allmatches;
    Call<Groups> newcall;
    SwipeRefreshLayout swipeRefreshLayou;
    ArrayList<String> endpointhome,endpointaway,imgaways;

    HashMap<String,String> hashMap;



    public adaptermatches(final Context context) {
        this.context = context;
        this.allmatches =new ArrayList<>();
        endpointhome= new ArrayList<>();
        endpointaway=new ArrayList<>();
        imgaways=new ArrayList<>();
       hashMap =new HashMap<>();


        callmain();

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
        callcuimg = service.getteams();
        callcuimg.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse(Call<Teams> call, Response<Teams> response) {

              List<Team>  myteams=  response.body().getTeams();
                   for (int j=0 ; j <32;j++) {

                       hashMap.put(myteams.get(j).getName(), myteams.get(j).getCrestUrl());
                   }
            }

            @Override
            public void onFailure(Call<Teams> call, Throwable t) {

            }
        });

    }

    public HashMap<String,String> gethash()
    {
        return hashMap;
    }




    public void callmain() {
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
        mycall = service.grtsoonmatches();

        mycall.enqueue(new Callback<Matches>()

        {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {

                try {
                    allmatches = response.body().getFixtures();

                    notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {

                Toast.makeText(context,"Check Internet connection",Toast.LENGTH_LONG).show();

            }
        });

    }











        @NonNull
        @Override
        public viewholder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
            View matches = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.matcheslayout, parent, false);
            viewholder viewholder = new viewholder(matches);
            return viewholder;
        }

        @Override
        public void onBindViewHolder (@NonNull viewholder holder,int position){


            if (allmatches.get(position).getResult().getGoalsHomeTeam()==null)
            {
                holder.homeresultt.setText(String.valueOf("0"));
            }
            else {
                holder.homeresultt.setText(String.valueOf(allmatches.get(position).getResult().getGoalsHomeTeam()));
            }

            if (allmatches.get(position).getResult().getGoalsAwayTeam()==null)
            {
                holder.awayresultt.setText(String.valueOf("0"));
            }
            else {
                holder.awayresultt.setText(String.valueOf(allmatches.get(position).getResult().getGoalsAwayTeam()));
            }
            holder.matchdataa.setText(allmatches.get(position).getDate().toString());
            holder.countname1.setText(allmatches.get(position).getHomeTeamName());
            holder.countryname2.setText(allmatches.get(position).getAwayTeamName());
            holder.matchstatuses.setText(allmatches.get(position).getStatus());

            String homename=allmatches.get(position).getHomeTeamName();
            String awayname=allmatches.get(position).getAwayTeamName();

            SvgLoader.pluck()
                    .with((Activity) context)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(String.valueOf(hashMap.get(homename)), holder.homeflagg);
            SvgLoader.pluck()
                    .with((Activity) context)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(String.valueOf(hashMap.get(awayname)), holder.awayflagg);






            String urlimg = allmatches.get(position).getLinks().getHomeTeam().getHref();
            String myendpoint = urlimg.substring(38);
          //  endpointhome.add(myendpoint);
      //     getflaghomecountry(myendpoint, holder);
            String imgaway = allmatches.get(position).getLinks().getAwayTeam().getHref();
            String awayendpoint = imgaway.substring(38);
            //endpointaway.add(awayendpoint);
         //  getflagawaycountry(awayendpoint, holder);


        }

        @Override
        public int getItemCount () {
            return allmatches.size();
        }


        class viewholder extends RecyclerView.ViewHolder {

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
