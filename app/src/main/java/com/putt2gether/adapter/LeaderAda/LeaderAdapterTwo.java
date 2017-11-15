package com.putt2gether.adapter.LeaderAda;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.bean.LeaderboardBean.LeaderTwo;
import com.putt2gether.putt.addScore.Leaderboard;
import com.putt2gether.putt.addScore.ScoreTable;
import com.putt2gether.view.LatoTextView;

/**
 * Created by Abc on 8/30/2016.
 */
public class LeaderAdapterTwo extends RecyclerView.Adapter<LeaderAdapterTwo.MyViewHolder> {

  /*  private ArrayList<String> score1;
    private ArrayList<String> rank1;
    private ArrayList<String> name1;
    private ArrayList<String> date1;
    private ArrayList<String> venue1;
    private ArrayList<String> image1;*/

    ArrayList<LeaderTwo> leaderlist=new ArrayList<LeaderTwo>();
    LeaderTwo leaderbean;
    Activity a1;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LatoTextView rank_net_second,  player_name_second,player_sub_second,secondleader_row_feet;
        RelativeLayout leader_row;

        public MyViewHolder(View view) {
            super(view);
            rank_net_second = (LatoTextView) view.findViewById(R.id.rank_net_second);
            player_name_second = (LatoTextView) view.findViewById(R.id.player_name_second);
            player_sub_second = (LatoTextView) view.findViewById(R.id.player_sub_second);
            secondleader_row_feet = (LatoTextView) view.findViewById(R.id.secondleader_row_feet);
            leader_row = (RelativeLayout)view.findViewById(R.id.leader_row);



        }
    }


    public LeaderAdapterTwo(ArrayList<LeaderTwo> leaderlist1, Activity a) {
        this.leaderlist = leaderlist1;
        this.a1=a;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leader_row_second, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        leaderbean = ( LeaderTwo ) leaderlist.get( position );

        holder.rank_net_second.setText(leaderbean.getRanktwo().toUpperCase());
        holder.player_name_second.setText(leaderbean.getPlayertwo().toUpperCase());
        holder.player_sub_second.setText(leaderbean.getSubplayertwo().toUpperCase());
        holder.secondleader_row_feet.setText(leaderbean.getFeet_inchtwo().toUpperCase());

        holder.leader_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("pos",position+"");
                Intent i=new Intent(a1,ScoreTable.class);
                i.putExtra("stats_visible", "yes");
                i.putExtra("eventID", ((Leaderboard)a1).geteventID());
                i.putExtra("playerID",leaderlist.get(position).getPlayerID());
                a1.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return leaderlist.size();
    }
}