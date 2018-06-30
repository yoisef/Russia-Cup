package com.joe.android.russiacup.modelteams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.joe.android.russiacup.Players.Player;
import com.joe.android.russiacup.Players.Players;
import com.joe.android.russiacup.R;
import com.joe.android.russiacup.cupinterface;

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

public class teamapter extends RecyclerView.Adapter<teamapter.viewholder> {

    Context context;
    List<Team> teamspart;
    Call<Teams> mycall;
    Call<Players> myplayers;
    HashMap<String,String> playerhash;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    List<Player> teamplayers;

    public teamapter(Context context )
    {

        this.context=context;

       teamspart=new ArrayList<>();
       teamplayers=new ArrayList<>();
       playerhash=new HashMap<>();

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
              try {
                  teamspart = response.body().getTeams();
                  notifyDataSetChanged();
              }
              catch (Exception e){
                  e.printStackTrace();
              }

            }

            @Override
            public void onFailure(Call<Teams> call, Throwable t) {

            }
        });




    }




    @NonNull
    @Override
    public teamapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.teamslayout,parent,false);
        viewholder viewholder=new viewholder(myview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull teamapter.viewholder holder, final int position) {


        String linkplayer=teamspart.get(position).getLinks().getPlayers().getHref();
        String endpointplayer=linkplayer.substring(38);

        playerhash.put(teamspart.get(position).getName(),endpointplayer);










        holder.teamnamee.setText(teamspart.get(position).getName());
        SvgLoader.pluck()
                .with((Activity)context)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(teamspart.get(position).getCrestUrl(), holder.teamflagg);

    }


    @Override
    public int getItemCount() {
        return teamspart.size();
    }
    class viewholder extends RecyclerView.ViewHolder{
        ImageView teamflagg;
        TextView teamnamee;
      LinearLayout linearLayout;

        public viewholder(View itemView) {
            super(itemView);

            teamflagg=itemView.findViewById(R.id.flagteam);
            teamnamee=itemView.findViewById(R.id.nameteam);
           linearLayout=itemView.findViewById(R.id.mycard);
        }
    }

}
