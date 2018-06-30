package com.joe.android.russiacup.modelteams;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joe.android.russiacup.Players.Player;
import com.joe.android.russiacup.R;
import com.joe.android.russiacup.teamsfrag;

import java.util.List;

public class playersadapter extends RecyclerView.Adapter<playersadapter.viewholder> {

    Context context;
    List<Player> myplayers;
    teamsfrag h;


    public playersadapter(Context context, List<Player> playerpro)
    {
        this.context=context;

        this.myplayers=playerpro;







    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View player=LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.rowplayer,parent,false);
        return new viewholder(player);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {





        holder.numberpla.setText(String.valueOf(myplayers.get(position).getJerseyNumber()));
        holder.posit.setText(myplayers.get(position).getPosition());
        holder.databirth.setText(myplayers.get(position).getDateOfBirth());
        holder.name.setText(myplayers.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return myplayers.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        TextView name,databirth,posit,numberpla;

        public viewholder(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.Vplayername);
            databirth=itemView.findViewById(R.id.Vplayerbirth);
            posit=itemView.findViewById(R.id.Vplayerposition);
            numberpla=itemView.findViewById(R.id.Vplayernumber);
        }
    }
}
