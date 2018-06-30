package com.joe.android.russiacup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.joe.android.russiacup.groupsadapters.adapterA;
import com.joe.android.russiacup.groupsadapters.adapterB;
import com.joe.android.russiacup.groupsadapters.adapterC;
import com.joe.android.russiacup.groupsadapters.adapterD;
import com.joe.android.russiacup.groupsadapters.adapterE;
import com.joe.android.russiacup.groupsadapters.adapterF;
import com.joe.android.russiacup.groupsadapters.adapterG;
import com.joe.android.russiacup.groupsadapters.adapterH;

public class Groupfrag2 extends Fragment {

    RecyclerView recyclerViewA,recyclerViewB,recyclerViewC,recyclerViewD,recyclerViewE,recyclerViewF,recyclerViewG,recyclerViewH;
    Context context;
    private AdView mAdView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.groupfraglayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=getActivity();

        recyclerViewA=view.findViewById(R.id.groupArecycle);
        recyclerViewB=view.findViewById(R.id.groupBrecycle);
        recyclerViewC=view.findViewById(R.id.groupCrecycle);
        recyclerViewD=view.findViewById(R.id.groupDrecycle);
        recyclerViewE=view.findViewById(R.id.groupErecycle);
        recyclerViewF=view.findViewById(R.id.groupFrecycle);
        recyclerViewG=view.findViewById(R.id.groupGrecycle);
        recyclerViewH=view.findViewById(R.id.groupHrecycle);

        LinearLayoutManager manager1 = new LinearLayoutManager(context);
        LinearLayoutManager manager2= new LinearLayoutManager(context);
        LinearLayoutManager manager3 = new LinearLayoutManager(context);
        LinearLayoutManager manager4 = new LinearLayoutManager(context);
        LinearLayoutManager manager5 = new LinearLayoutManager(context);
        LinearLayoutManager manager6 = new LinearLayoutManager(context);
        LinearLayoutManager manager7 = new LinearLayoutManager(context);
        LinearLayoutManager manager8 = new LinearLayoutManager(context);

        recyclerViewA.setLayoutManager(manager1);
        recyclerViewB.setLayoutManager(manager2);
        recyclerViewC.setLayoutManager(manager3);
        recyclerViewD.setLayoutManager(manager4);
        recyclerViewE.setLayoutManager(manager5);
        recyclerViewF.setLayoutManager(manager6);
        recyclerViewG.setLayoutManager(manager7);
        recyclerViewH.setLayoutManager(manager8);


        recyclerViewA.setAdapter(new adapterA(context));
        recyclerViewA.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        recyclerViewB.setAdapter(new adapterB(context));
        recyclerViewB.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        recyclerViewC.setAdapter(new adapterC(context));
        recyclerViewC.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        recyclerViewD.setAdapter(new adapterD(context));
        recyclerViewD.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        recyclerViewE.setAdapter(new adapterE(context));
        recyclerViewE.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        recyclerViewF.setAdapter(new adapterF(context));
        recyclerViewF.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        recyclerViewG.setAdapter(new adapterG(context));
        recyclerViewG.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        recyclerViewH.setAdapter(new adapterH(context));
        recyclerViewH.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));


        mAdView = view.findViewById(R.id.groupadView);
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


    }
}
