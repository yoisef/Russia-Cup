package com.joe.android.russiacup.models;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;

import com.joe.android.russiacup.APIHelper;
import com.joe.android.russiacup.R;
import com.joe.android.russiacup.RetryableCallback;
import com.joe.android.russiacup.cupinterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class groupadapter extends RecyclerView.Adapter<groupadapter.viewholder> {

    Context context;

    List<Standings> lists;
    Call<Groups> mycall;
    List<A> groupa ;
    List<B> groupb ;
    List<C> groupc ;
    List<D> groupd ;
    List<E> groupe ;
    List<F> groupf ;
    List<G> groupg ;
    List<H> grouph ;
    List<publicgroup> standingss;

    RecyclerView grouprecycle;
    ProgressBar progressBar;



    public groupadapter(final Activity context)
    {
        this.context=context;




        View view=LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.group,null);

        grouprecycle=view.findViewById(R.id.grouprecycle);









        lists=new ArrayList<>();
        groupa=new ArrayList<>();
      groupb=new ArrayList<>();
      groupc=new ArrayList<>();
      groupd=new ArrayList<>();
      groupe=new ArrayList<>();
        groupf=new ArrayList<>();
        groupg=new ArrayList<>();
      grouph=new ArrayList<>();
      standingss=new ArrayList<>();

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
        mycall = service.getstandings();

        APIHelper.enqueueWithRetry(mycall, 5, new RetryableCallback<Groups>(mycall,5) {
            @Override
            public void onResponse(Call<Groups> call, Response<Groups> response) {
                super.onResponse(call, response);

                 try {


                     groupa = response.body().getStandings().getA();
                     notifyDataSetChanged();
                     groupb = response.body().getStandings().getB();
                     notifyDataSetChanged();
                     groupc = response.body().getStandings().getC();
                     notifyDataSetChanged();
                     groupd = response.body().getStandings().getD();
                     notifyDataSetChanged();
                     groupe = response.body().getStandings().getE();
                     notifyDataSetChanged();
                     groupf = response.body().getStandings().getF();
                     notifyDataSetChanged();
                     groupg = response.body().getStandings().getG();
                     notifyDataSetChanged();
                     grouph = response.body().getStandings().getH();
                     notifyDataSetChanged();

                 }
                 catch (Exception e)
                 {
                     e.printStackTrace();
                 }







            }

            @Override
            public void onFailure(Call<Groups> call, Throwable t) {
                super.onFailure(call, t);

                Toast.makeText(context,"Check Internet connection",Toast.LENGTH_LONG).show();



            }
        });









    }









    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myrow= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.grouplayout2, parent,false);
        viewholder holderrow=new viewholder(myrow);
        return holderrow;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {


        switch (position)
        {
            case 0:{


                holder.md1.setText(String.valueOf(groupa.get(0).getPlayedGames()));
                holder.md2.setText(String.valueOf(groupa.get(1).getPlayedGames()));
                holder.md3.setText(String.valueOf(groupa.get(2).getPlayedGames()));
                holder.md4.setText(String.valueOf(groupa.get(3).getPlayedGames()));

                holder.ga1.setText(String.valueOf(groupa.get(0).getGoalsAgainst()));
                holder.ga2.setText(String.valueOf(groupa.get(1).getGoalsAgainst()));
                holder.ga3.setText(String.valueOf(groupa.get(2).getGoalsAgainst()));
                holder.ga4.setText(String.valueOf(groupa.get(3).getGoalsAgainst()));

                holder.po1.setText(String.valueOf(groupa.get(0).getPoints()));
                holder.po2.setText(String.valueOf(groupa.get(1).getPoints()));
                holder.po3.setText(String.valueOf(groupa.get(2).getPoints()));
                holder.po4.setText(String.valueOf(groupa.get(3).getPoints()));

                holder.gd1.setText(String.valueOf(groupa.get(0).getGoalDifference()));
                holder.gd2.setText(String.valueOf(groupa.get(1).getGoalDifference()));
                holder.gd3.setText(String.valueOf(groupa.get(2).getGoalDifference()));
                holder.gd4.setText(String.valueOf(groupa.get(3).getGoalDifference()));



                holder.rank.setText(String.valueOf(groupa.get(0).getRank()));
                holder.rank2.setText(String.valueOf(groupa.get(1).getRank()));
                holder.rank3.setText(String.valueOf(groupa.get(2).getRank()));
                holder.rank4.setText(String.valueOf(groupa.get(3).getRank()));



                holder.goal1.setText(String.valueOf(groupa.get(0).getGoals()));
                holder.goal2.setText(String.valueOf(groupa.get(1).getGoals()));
                holder.goal3.setText(String.valueOf(groupa.get(2).getGoals()));
                holder.goal4.setText(String.valueOf(groupa.get(3).getGoals()));





                holder.groupname.setText((groupa.get(0).getGroup()));

                holder.countryname1.setText(groupa.get(0).getTeam());
                holder.countryname2.setText(groupa.get(1).getTeam());
                holder.countryname3.setText(groupa.get(2).getTeam());
                holder.countryname44.setText(groupa.get(3).getTeam());
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupa.get(0).getCrestURI(), holder.countryflag1);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupa.get(1).getCrestURI(), holder.countryflag22);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupa.get(2).getCrestURI(), holder.countryflag3);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupa.get(3).getCrestURI(), holder.countryflag4);
                break;

            }
            case 1:{


                holder.md1.setText(String.valueOf(groupb.get(0).getPlayedGames()));
                holder.md2.setText(String.valueOf(groupb.get(1).getPlayedGames()));
                holder.md3.setText(String.valueOf(groupb.get(2).getPlayedGames()));
                holder.md4.setText(String.valueOf(groupb.get(3).getPlayedGames()));

                holder.ga1.setText(String.valueOf(groupb.get(0).getGoalsAgainst()));
                holder.ga2.setText(String.valueOf(groupb.get(1).getGoalsAgainst()));
                holder.ga3.setText(String.valueOf(groupb.get(2).getGoalsAgainst()));
                holder.ga4.setText(String.valueOf(groupb.get(3).getGoalsAgainst()));

                holder.po1.setText(String.valueOf(groupb.get(0).getPoints()));
                holder.po2.setText(String.valueOf(groupb.get(1).getPoints()));
                holder.po3.setText(String.valueOf(groupb.get(2).getPoints()));
                holder.po4.setText(String.valueOf(groupb.get(3).getPoints()));

                holder.gd1.setText(String.valueOf(groupb.get(0).getGoalDifference()));
                holder.gd2.setText(String.valueOf(groupb.get(1).getGoalDifference()));
                holder.gd3.setText(String.valueOf(groupb.get(2).getGoalDifference()));
                holder.gd4.setText(String.valueOf(groupb.get(3).getGoalDifference()));

                holder.rank.setText(String.valueOf(groupb.get(0).getRank()));
                holder.rank2.setText(String.valueOf(groupb.get(1).getRank()));
                holder.rank3.setText(String.valueOf(groupb.get(2).getRank()));
                holder.rank4.setText(String.valueOf(groupb.get(3).getRank()));

                holder.goal1.setText(String.valueOf(groupb.get(0).getGoals()));
                holder.goal2.setText(String.valueOf(groupb.get(1).getGoals()));
                holder.goal3.setText(String.valueOf(groupb.get(2).getGoals()));
                holder.goal4.setText(String.valueOf(groupb.get(3).getGoals()));

                holder.groupname.setText(groupb.get(0).getGroup());
                holder.rank.setText(String.valueOf(groupb.get(0).getRank()));
                holder.countryname1.setText(groupb.get(0).getTeam());
                holder.countryname2.setText(groupb.get(1).getTeam());
                holder.countryname3.setText(groupb.get(2).getTeam());
                holder.countryname44.setText(groupb.get(3).getTeam());
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupb.get(0).getCrestURI(), holder.countryflag1);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupb.get(1).getCrestURI(), holder.countryflag22);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupb.get(2).getCrestURI(), holder.countryflag3);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupb.get(3).getCrestURI(), holder.countryflag4);
                break;

            }
            case 2:{

                holder.md1.setText(String.valueOf(groupc.get(0).getPlayedGames()));
                holder.md2.setText(String.valueOf(groupc.get(1).getPlayedGames()));
                holder.md3.setText(String.valueOf(groupc.get(2).getPlayedGames()));
                holder.md4.setText(String.valueOf(groupc.get(3).getPlayedGames()));

                holder.ga1.setText(String.valueOf(groupc.get(0).getGoalsAgainst()));
                holder.ga2.setText(String.valueOf(groupc.get(1).getGoalsAgainst()));
                holder.ga3.setText(String.valueOf(groupc.get(2).getGoalsAgainst()));
                holder.ga4.setText(String.valueOf(groupc.get(3).getGoalsAgainst()));

                holder.po1.setText(String.valueOf(groupc.get(0).getPoints()));
                holder.po2.setText(String.valueOf(groupc.get(1).getPoints()));
                holder.po3.setText(String.valueOf(groupc.get(2).getPoints()));
                holder.po4.setText(String.valueOf(groupc.get(3).getPoints()));

                holder.gd1.setText(String.valueOf(groupc.get(0).getGoalDifference()));
                holder.gd2.setText(String.valueOf(groupc.get(1).getGoalDifference()));
                holder.gd3.setText(String.valueOf(groupc.get(2).getGoalDifference()));
                holder.gd4.setText(String.valueOf(groupc.get(3).getGoalDifference()));

                holder.rank.setText(String.valueOf(groupc.get(0).getRank()));
                holder.rank2.setText(String.valueOf(groupc.get(1).getRank()));
                holder.rank3.setText(String.valueOf(groupc.get(2).getRank()));
                holder.rank4.setText(String.valueOf(groupc.get(3).getRank()));

                holder.goal1.setText(String.valueOf(groupc.get(0).getGoals()));
                holder.goal2.setText(String.valueOf(groupc.get(1).getGoals()));
                holder.goal3.setText(String.valueOf(groupc.get(2).getGoals()));
                holder.goal4.setText(String.valueOf(groupc.get(3).getGoals()));

                holder.groupname.setText(groupc.get(0).getGroup());
                holder.rank.setText(String.valueOf(groupc.get(0).getRank()));
                holder.countryname1.setText(groupc.get(0).getTeam());
                holder.countryname2.setText(groupc.get(1).getTeam());
                holder.countryname3.setText(groupc.get(2).getTeam());
                holder.countryname44.setText(groupc.get(3).getTeam());
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupc.get(0).getCrestURI(), holder.countryflag1);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupc.get(1).getCrestURI(), holder.countryflag22);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupc.get(2).getCrestURI(), holder.countryflag3);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupc.get(3).getCrestURI(), holder.countryflag4);
                break;


            }
            case 3:{


                holder.md1.setText(String.valueOf(groupd.get(0).getPlayedGames()));
                holder.md2.setText(String.valueOf(groupd.get(1).getPlayedGames()));
                holder.md3.setText(String.valueOf(groupd.get(2).getPlayedGames()));
                holder.md4.setText(String.valueOf(groupd.get(3).getPlayedGames()));

                holder.ga1.setText(String.valueOf(groupd.get(0).getGoalsAgainst()));
                holder.ga2.setText(String.valueOf(groupd.get(1).getGoalsAgainst()));
                holder.ga3.setText(String.valueOf(groupd.get(2).getGoalsAgainst()));
                holder.ga4.setText(String.valueOf(groupd.get(3).getGoalsAgainst()));

                holder.po1.setText(String.valueOf(groupd.get(0).getPoints()));
                holder.po2.setText(String.valueOf(groupd.get(1).getPoints()));
                holder.po3.setText(String.valueOf(groupd.get(2).getPoints()));
                holder.po4.setText(String.valueOf(groupd.get(3).getPoints()));

                holder.gd1.setText(String.valueOf(groupd.get(0).getGoalDifference()));
                holder.gd2.setText(String.valueOf(groupd.get(1).getGoalDifference()));
                holder.gd3.setText(String.valueOf(groupd.get(2).getGoalDifference()));
                holder.gd4.setText(String.valueOf(groupd.get(3).getGoalDifference()));

                holder.rank.setText(String.valueOf(groupd.get(0).getRank()));
                holder.rank2.setText(String.valueOf(groupd.get(1).getRank()));
                holder.rank3.setText(String.valueOf(groupd.get(2).getRank()));
                holder.rank4.setText(String.valueOf(groupd.get(3).getRank()));


                holder.goal1.setText(String.valueOf(groupc.get(0).getGoals()));
                holder.goal2.setText(String.valueOf(groupc.get(1).getGoals()));
                holder.goal3.setText(String.valueOf(groupc.get(2).getGoals()));
                holder.goal4.setText(String.valueOf(groupc.get(3).getGoals()));

                holder.groupname.setText(groupd.get(0).getGroup());
                holder.rank.setText(String.valueOf(groupd.get(0).getRank()));
                holder.countryname1.setText(groupd.get(0).getTeam());
                holder.countryname2.setText(groupd.get(1).getTeam());
                holder.countryname3.setText(groupd.get(2).getTeam());
                holder.countryname44.setText(groupd.get(3).getTeam());
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupd.get(0).getCrestURI(), holder.countryflag1);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupd.get(1).getCrestURI(), holder.countryflag22);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupd.get(2).getCrestURI(), holder.countryflag3);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupd.get(3).getCrestURI(), holder.countryflag4);
                break;
            }
            case 4:{

                holder.md1.setText(String.valueOf(groupe.get(0).getPlayedGames()));
                holder.md2.setText(String.valueOf(groupe.get(1).getPlayedGames()));
                holder.md3.setText(String.valueOf(groupe.get(2).getPlayedGames()));
                holder.md4.setText(String.valueOf(groupe.get(3).getPlayedGames()));

                holder.ga1.setText(String.valueOf(groupe.get(0).getGoalsAgainst()));
                holder.ga2.setText(String.valueOf(groupe.get(1).getGoalsAgainst()));
                holder.ga3.setText(String.valueOf(groupe.get(2).getGoalsAgainst()));
                holder.ga4.setText(String.valueOf(groupe.get(3).getGoalsAgainst()));

                holder.po1.setText(String.valueOf(groupe.get(0).getPoints()));
                holder.po2.setText(String.valueOf(groupe.get(1).getPoints()));
                holder.po3.setText(String.valueOf(groupe.get(2).getPoints()));
                holder.po4.setText(String.valueOf(groupe.get(3).getPoints()));

                holder.gd1.setText(String.valueOf(groupe.get(0).getGoalDifference()));
                holder.gd2.setText(String.valueOf(groupe.get(1).getGoalDifference()));
                holder.gd3.setText(String.valueOf(groupe.get(2).getGoalDifference()));
                holder.gd4.setText(String.valueOf(groupe.get(3).getGoalDifference()));

                holder.rank.setText(String.valueOf(groupe.get(0).getRank()));
                holder.rank2.setText(String.valueOf(groupe.get(1).getRank()));
                holder.rank3.setText(String.valueOf(groupe.get(2).getRank()));
                holder.rank4.setText(String.valueOf(groupe.get(3).getRank()));

                holder.goal1.setText(String.valueOf(groupe.get(0).getGoals()));
                holder.goal2.setText(String.valueOf(groupe.get(1).getGoals()));
                holder.goal3.setText(String.valueOf(groupe.get(2).getGoals()));
                holder.goal4.setText(String.valueOf(groupe.get(3).getGoals()));


                holder.groupname.setText(groupe.get(0).getGroup());
                holder.rank.setText(String.valueOf(groupe.get(0).getRank()));
                holder.countryname1.setText(groupe.get(0).getTeam());
                holder.countryname2.setText(groupe.get(1).getTeam());
                holder.countryname3.setText(groupe.get(2).getTeam());
                holder.countryname44.setText(groupe.get(3).getTeam());
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupe.get(0).getCrestURI(), holder.countryflag1);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupe.get(1).getCrestURI(), holder.countryflag22);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupe.get(2).getCrestURI(), holder.countryflag3);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupe.get(3).getCrestURI(), holder.countryflag4);
                break;
            }
            case 5:{

                holder.md1.setText(String.valueOf(groupf.get(0).getPlayedGames()));
                holder.md2.setText(String.valueOf(groupf.get(1).getPlayedGames()));
                holder.md3.setText(String.valueOf(groupf.get(2).getPlayedGames()));
                holder.md4.setText(String.valueOf(groupf.get(3).getPlayedGames()));

                holder.ga1.setText(String.valueOf(groupf.get(0).getGoalsAgainst()));
                holder.ga2.setText(String.valueOf(groupf.get(1).getGoalsAgainst()));
                holder.ga3.setText(String.valueOf(groupf.get(2).getGoalsAgainst()));
                holder.ga4.setText(String.valueOf(groupf.get(3).getGoalsAgainst()));

                holder.po1.setText(String.valueOf(groupf.get(0).getPoints()));
                holder.po2.setText(String.valueOf(groupf.get(1).getPoints()));
                holder.po3.setText(String.valueOf(groupf.get(2).getPoints()));
                holder.po4.setText(String.valueOf(groupf.get(3).getPoints()));

                holder.gd1.setText(String.valueOf(groupf.get(0).getGoalDifference()));
                holder.gd2.setText(String.valueOf(groupf.get(1).getGoalDifference()));
                holder.gd3.setText(String.valueOf(groupf.get(2).getGoalDifference()));
                holder.gd4.setText(String.valueOf(groupf.get(3).getGoalDifference()));

                holder.rank.setText(String.valueOf(groupf.get(0).getRank()));
                holder.rank2.setText(String.valueOf(groupf.get(1).getRank()));
                holder.rank3.setText(String.valueOf(groupf.get(2).getRank()));
                holder.rank4.setText(String.valueOf(groupf.get(3).getRank()));

                holder.goal1.setText(String.valueOf(groupf.get(0).getGoals()));
                holder.goal2.setText(String.valueOf(groupf.get(1).getGoals()));
                holder.goal3.setText(String.valueOf(groupf.get(2).getGoals()));
                holder.goal4.setText(String.valueOf(groupf.get(3).getGoals()));

                holder.groupname.setText(groupf.get(0).getGroup());
                holder.rank.setText(String.valueOf(groupf.get(0).getRank()));
                holder.countryname1.setText(groupf.get(0).getTeam());
                holder.countryname2.setText(groupf.get(1).getTeam());
                holder.countryname3.setText(groupf.get(2).getTeam());
                holder.countryname44.setText(groupf.get(3).getTeam());
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupf.get(0).getCrestURI(), holder.countryflag1);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupf.get(1).getCrestURI(), holder.countryflag22);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupf.get(2).getCrestURI(), holder.countryflag3);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupf.get(3).getCrestURI(), holder.countryflag4);
                break;
            }
            case 6:{
                holder.md1.setText(String.valueOf(groupg.get(0).getPlayedGames()));
                holder.md2.setText(String.valueOf(groupg.get(1).getPlayedGames()));
                holder.md3.setText(String.valueOf(groupg.get(2).getPlayedGames()));
                holder.md4.setText(String.valueOf(groupg.get(3).getPlayedGames()));

                holder.ga1.setText(String.valueOf(groupg.get(0).getGoalsAgainst()));
                holder.ga2.setText(String.valueOf(groupg.get(1).getGoalsAgainst()));
                holder.ga3.setText(String.valueOf(groupg.get(2).getGoalsAgainst()));
                holder.ga4.setText(String.valueOf(groupg.get(3).getGoalsAgainst()));

                holder.po1.setText(String.valueOf(groupg.get(0).getPoints()));
                holder.po2.setText(String.valueOf(groupg.get(1).getPoints()));
                holder.po3.setText(String.valueOf(groupg.get(2).getPoints()));
                holder.po4.setText(String.valueOf(groupg.get(3).getPoints()));

                holder.gd1.setText(String.valueOf(groupg.get(0).getGoalDifference()));
                holder.gd2.setText(String.valueOf(groupg.get(1).getGoalDifference()));
                holder.gd3.setText(String.valueOf(groupg.get(2).getGoalDifference()));
                holder.gd4.setText(String.valueOf(groupg.get(3).getGoalDifference()));


                holder.rank.setText(String.valueOf(groupg.get(0).getRank()));
                holder.rank2.setText(String.valueOf(groupg.get(1).getRank()));
                holder.rank3.setText(String.valueOf(groupg.get(2).getRank()));
                holder.rank4.setText(String.valueOf(groupg.get(3).getRank()));


                holder.goal1.setText(String.valueOf(groupg.get(0).getGoals()));
                holder.goal2.setText(String.valueOf(groupg.get(1).getGoals()));
                holder.goal3.setText(String.valueOf(groupg.get(2).getGoals()));
                holder.goal4.setText(String.valueOf(groupg.get(3).getGoals()));

                holder.groupname.setText(groupg.get(0).getGroup());
                holder.rank.setText(String.valueOf(groupg.get(0).getRank()));
                holder.countryname1.setText(groupg.get(0).getTeam());
                holder.countryname2.setText(groupg.get(1).getTeam());
                holder.countryname3.setText(groupg.get(2).getTeam());
                holder.countryname44.setText(groupg.get(3).getTeam());
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupg.get(0).getCrestURI(), holder.countryflag1);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupg.get(1).getCrestURI(), holder.countryflag22);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupg.get(2).getCrestURI(), holder.countryflag3);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(groupg.get(3).getCrestURI(), holder.countryflag4);
                break;
            }
            case 7:{


                holder.md1.setText(String.valueOf(grouph.get(0).getPlayedGames()));
                holder.md2.setText(String.valueOf(grouph.get(1).getPlayedGames()));
                holder.md3.setText(String.valueOf(grouph.get(2).getPlayedGames()));
                holder.md4.setText(String.valueOf(grouph.get(3).getPlayedGames()));

                holder.ga1.setText(String.valueOf(grouph.get(0).getGoalsAgainst()));
                holder.ga2.setText(String.valueOf(grouph.get(1).getGoalsAgainst()));
                holder.ga3.setText(String.valueOf(grouph.get(2).getGoalsAgainst()));
                holder.ga4.setText(String.valueOf(grouph.get(3).getGoalsAgainst()));

                holder.po1.setText(String.valueOf(grouph.get(0).getPoints()));
                holder.po2.setText(String.valueOf(grouph.get(1).getPoints()));
                holder.po3.setText(String.valueOf(grouph.get(2).getPoints()));
                holder.po4.setText(String.valueOf(grouph.get(3).getPoints()));

                holder.gd1.setText(String.valueOf(grouph.get(0).getGoalDifference()));
                holder.gd2.setText(String.valueOf(grouph.get(1).getGoalDifference()));
                holder.gd3.setText(String.valueOf(grouph.get(2).getGoalDifference()));
                holder.gd4.setText(String.valueOf(grouph.get(3).getGoalDifference()));

                holder.rank.setText(String.valueOf(grouph.get(0).getRank()));
                holder.rank2.setText(String.valueOf(grouph.get(1).getRank()));
                holder.rank3.setText(String.valueOf(grouph.get(2).getRank()));
                holder.rank4.setText(String.valueOf(grouph.get(3).getRank()));

                holder.goal1.setText(String.valueOf(grouph.get(0).getGoals()));
                holder.goal2.setText(String.valueOf(grouph.get(1).getGoals()));
                holder.goal3.setText(String.valueOf(grouph.get(2).getGoals()));
                holder.goal4.setText(String.valueOf(grouph.get(3).getGoals()));


                holder.groupname.setText(grouph.get(0).getGroup());
                holder.rank.setText(String.valueOf(grouph.get(0).getRank()));
                holder.countryname1.setText(grouph.get(0).getTeam());
                holder.countryname2.setText(grouph.get(1).getTeam());
                holder.countryname3.setText(grouph.get(2).getTeam());
                holder.countryname44.setText(grouph.get(3).getTeam());
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(grouph.get(0).getCrestURI(), holder.countryflag1);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(grouph.get(1).getCrestURI(), holder.countryflag22);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(grouph.get(2).getCrestURI(), holder.countryflag3);
                SvgLoader.pluck()
                        .with((Activity)context)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(grouph.get(3).getCrestURI(), holder.countryflag4);
                break;
            }
        }
        }






    @Override
    public int getItemCount() {
        return groupa.size()+groupe.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        ImageView countryflag1,countryflag22,countryflag3,countryflag4;

        TextView rank,rank2,rank3,rank4,po1,po2,po3,po4,ga1,ga2,ga3,ga4,gd1,gd2,gd3,gd4,md1,md2,md3,md4;
        TextView countryname1,countryname2,countryname3,countryname44,groupname;
        TextView goal1,goal2,goal3,goal4;


        public viewholder(View itemView) {
            super(itemView);

            rank=itemView.findViewById(R.id.rankvalu1);
            rank2=itemView.findViewById(R.id.rankvalu2);
            rank3=itemView.findViewById(R.id.rankvalu3);
            rank4=itemView.findViewById(R.id.rankvalu4);

            po1=itemView.findViewById(R.id.ptsvalu1);
            po2=itemView.findViewById(R.id.ptsvalu2);
            po3=itemView.findViewById(R.id.ptsvalu3);
            po4=itemView.findViewById(R.id.ptsvalu4);

            ga1=itemView.findViewById(R.id.Gavalu1);
            ga2=itemView.findViewById(R.id.Gavalu2);
            ga3=itemView.findViewById(R.id.Gavalu3);
            ga4=itemView.findViewById(R.id.Gavalu4);

            gd1=itemView.findViewById(R.id.Gdvalu1);
            gd2=itemView.findViewById(R.id.Gdvalu2);
            gd3=itemView.findViewById(R.id.Gdvalu3);
            gd4=itemView.findViewById(R.id.Gdvalu4);

            md1=itemView.findViewById(R.id.MPvalu1);
            md2=itemView.findViewById(R.id.MPvalu2);
            md3=itemView.findViewById(R.id.MPvalu3);
            md4=itemView.findViewById(R.id.MPvalu4);





            countryname1=itemView.findViewById(R.id.Cname1);
            countryname2=itemView.findViewById(R.id.Cname2);
            countryname3=itemView.findViewById(R.id.Cname3);
            countryname44=itemView.findViewById(R.id.Cname4);

            groupname=itemView.findViewById(R.id.groupid);

            countryflag1=itemView.findViewById(R.id.imgvalu1);
            countryflag22=itemView.findViewById(R.id.imgvalu2);
            countryflag3=itemView.findViewById(R.id.imgvalu3);
            countryflag4=itemView.findViewById(R.id.imgvalu4);

            goal1=itemView.findViewById(R.id.Gsvalu1);
            goal2=itemView.findViewById(R.id.Gsvalu2);
            goal3=itemView.findViewById(R.id.Gsvalu3);
            goal4=itemView.findViewById(R.id.Gsvalu4);
        }
    }
}
