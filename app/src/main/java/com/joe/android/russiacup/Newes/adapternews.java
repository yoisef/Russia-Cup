package com.joe.android.russiacup.Newes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.joe.android.russiacup.R;
import com.joe.android.russiacup.cupinterface;
import com.joe.android.russiacup.webpage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class adapternews extends RecyclerView.Adapter<adapternews.viewholder> {

    Context context;

    Call<News> callnews;
    List<Datum> listnewss;
    public adapternews(Context context,List<Datum> listnews)
    {
        this.context=context;
       this.listnewss=listnews;




    }


    @NonNull
    @Override
    public adapternews.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.newsrow,parent,false);

       return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapternews.viewholder holder, int position) {

        Picasso.get().load(listnewss.get(position).getImageLink()).into(holder.newsphoto);
        holder.newswords.setText(listnewss.get(position).getNewsTitle());
        holder.newsdataa.setText(listnewss.get(position).getDate());


         final int pos=holder.getAdapterPosition();


        holder.myrealtive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String url= listnewss.get(pos).getNewsLink();
                Intent myintent=new Intent(context,webpage.class);
                myintent.putExtra("weburl",url);
                context.startActivity(myintent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listnewss.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        ImageView newsphoto;
        TextView newswords,newsdataa;
        RelativeLayout myrealtive;

        public viewholder(View itemView) {
            super(itemView);

            newsphoto=itemView.findViewById(R.id.newsimg);
            newswords=itemView.findViewById(R.id.newstitle);
            newsdataa=itemView.findViewById(R.id.newsdata);
            myrealtive=itemView.findViewById(R.id.newlayout);
        }
    }
}
