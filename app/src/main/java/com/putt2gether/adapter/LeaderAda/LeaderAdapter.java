package com.putt2gether.adapter.LeaderAda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.bean.LeaderboardBean.Leader;
import com.putt2gether.putt.addScore.Leaderboard;
import com.putt2gether.putt.addScore.ScoreTable;
import com.putt2gether.view.LatoTextView;

/**
 * Created by Abc on 8/30/2016.
 */
public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.MyViewHolder> {

    ArrayList<Leader> leaderlist = new ArrayList<Leader>();
    Leader leaderbean;
    Activity a1;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LatoTextView rank_net_num, player_net_name, player_net_sub, holesplayed_net_num, toggle_net_gross;

        public MyViewHolder(View view) {
            super(view);
            rank_net_num = (LatoTextView) view.findViewById(R.id.rank_net_num);
            player_net_name = (LatoTextView) view.findViewById(R.id.player_net_name);
            player_net_sub = (LatoTextView) view.findViewById(R.id.player_net_sub);
            holesplayed_net_num = (LatoTextView) view.findViewById(R.id.holesplayed_net_num);
            toggle_net_gross = (LatoTextView) view.findViewById(R.id.toggle_net_gross);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String playerID = leaderlist.get(getAdapterPosition()).getPlayer_id();

                    String stackClear = a1.getIntent().getStringExtra("from");
                    if (stackClear != null && stackClear.equalsIgnoreCase("delegate")) {
                        Log.e("pos", getPosition() + "");
                        Intent i = new Intent(a1, ScoreTable.class);
                        i.putExtra("eventID", ((Leaderboard) a1).geteventID());
                        i.putExtra("isDelegate", "1");
                        i.putExtra("playerID", playerID);
                        a1.startActivity(i);
                    } else if (stackClear != null && stackClear.equalsIgnoreCase("history")) {
                        Log.e("pos", getPosition() + "");
                        Intent i = new Intent(a1, ScoreTable.class);
                        i.putExtra("eventID", ((Leaderboard) a1).geteventID());
                        i.putExtra("isDelegate", "1");
                        i.putExtra("stats_visible", "yes");
                        i.putExtra("playerID", playerID);
                        a1.startActivity(i);
                    } else {
                        Log.e("pos", getPosition() + "");
                        Intent i = new Intent(a1, ScoreTable.class);
                        i.putExtra("eventID", ((Leaderboard) a1).geteventID());
                        i.putExtra("playerID", playerID);

                        // i.putExtra("eventID", ((Leaderboard)a1).geteventID());

                        a1.startActivity(i);
                    }


                }
            });

        }
    }


    public LeaderAdapter(ArrayList<Leader> leaderlist1, Activity a) {
        this.leaderlist = leaderlist1;
        this.a1 = a;

    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leader_row_, parent, false);


        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        leaderbean = (Leader) leaderlist.get(position);

        holder.rank_net_num.setText(leaderbean.getRank().toUpperCase());
        holder.player_net_name.setText(leaderbean.getPlayer().toUpperCase());
        holder.player_net_sub.setText(leaderbean.getSubplayer().toUpperCase());
        holder.holesplayed_net_num.setText(leaderbean.getHolesplayed().toUpperCase());
        String text = leaderbean.getNet_gross_play();
        if (text!=null && text.length()>0) {
            char first = text.charAt(0);
            String sssss = String.valueOf(first);
            if (sssss.equalsIgnoreCase("-")){
                holder.toggle_net_gross.setText(leaderbean.getNet_gross_play().toUpperCase());
                holder.toggle_net_gross.setTextColor(Color.RED);

            }else {

                holder.toggle_net_gross.setText(leaderbean.getNet_gross_play().toUpperCase());
                holder.toggle_net_gross.setTextColor(Color.BLACK);
            }
        }


    }

    @Override
    public int getItemCount() {
        return leaderlist.size();
    }
}