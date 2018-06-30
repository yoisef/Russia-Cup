package com.joe.android.russiacup.groupsadapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.joe.android.russiacup.APIHelper;
import com.joe.android.russiacup.R;
import com.joe.android.russiacup.RetryableCallback;
import com.joe.android.russiacup.cupinterface;
import com.joe.android.russiacup.models.A;
import com.joe.android.russiacup.models.B;
import com.joe.android.russiacup.models.Groups;
import com.joe.android.russiacup.models.H;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class adapterH extends RecyclerView.Adapter<adapterH.viewholder> {

    Context context;
    Call<Groups> mycall;
    List<H> myateams;

    public adapterH(Context contextt)
    {
        this.context=contextt;
        myateams=new ArrayList<>();

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

        mycall.enqueue(new Callback<Groups>() {
            @Override
            public void onResponse(Call<Groups> call, Response<Groups> response) {

                myateams=response.body().getStandings().getH();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Groups> call, Throwable t) {

            }
        });
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewgroup= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.grouprowforall,parent,false);
        return new viewholder(viewgroup);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.cn.setText(myateams.get(position).getTeam());
        holder.rank.setText(String.valueOf(myateams.get(position).getRank()));
        holder.ga.setText(String.valueOf(myateams.get(position).getGoalsAgainst()));
        holder.gs.setText(String.valueOf(myateams.get(position).getGoals()));
        holder.gd.setText(String.valueOf(myateams.get(position).getGoalDifference()));
        holder.pt.setText(String.valueOf(myateams.get(position).getPoints()));
        holder.mp.setText(String.valueOf(myateams.get(position).getPlayedGames()));

        SvgLoader.pluck()
                .with((Activity) context)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(myateams.get(position).getCrestURI(), holder.imageView);


    }

    @Override
    public int getItemCount() {
        return myateams.size();
    }

    class viewholder extends RecyclerView.ViewHolder{


        ImageView imageView;
        TextView cn,mp,ga,gs,gd,pt,rank,groupchar;

        public viewholder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imgstate);

            cn=itemView.findViewById(R.id.namestate);
            mp=itemView.findViewById(R.id.mpstate);
            ga=itemView.findViewById(R.id.gastate);
            gs=itemView.findViewById(R.id.gsstate);
            gd=itemView.findViewById(R.id.gdstate);
            pt=itemView.findViewById(R.id.ptsstate);
            rank=itemView.findViewById(R.id.rankstate);
        }
    }
}
