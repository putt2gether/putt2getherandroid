package com.putt2gether.fragments.addscorefrags.scoreCardPlayers;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.view.LatoTextView;

/**
 * Created by Abc on 9/15/2016.
 */
public class PlayerScoreAdapter extends RecyclerView.Adapter<PlayerScoreAdapter.ViewHolder> {

    private ArrayList<String> listfirst;
    private ArrayList<String> second;
    private ArrayList<String> third;
    private ArrayList<String> fourth;
    private ArrayList<String> fifth;
    private Activity context;


    public PlayerScoreAdapter(Activity mContext, ArrayList<String> frst,ArrayList<String> secnd,ArrayList<String> thrd,ArrayList<String> frth,ArrayList<String> fifth) {
        this.context = mContext;
        this.listfirst = frst;
        this.second=secnd;
        this.third=thrd;
        this.fourth=frth;
        this.fifth=fifth;

    }


    @Override
    public PlayerScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_score_tab_player, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PlayerScoreAdapter.ViewHolder holder, final int position) {

        holder.firstrowscore.setText(listfirst.get(position));
        holder.secondrowscore.setText(second.get(position));
        holder.thirdrowscore.setText(third.get(position));

        if(position>8) {
            holder.fourthrowscore.setText(fourth.get(position));
            holder.fifthscore.setText(fifth.get(position));
        }
        else
        {
            holder.fourthrowscore.setText("");
            holder.fifthscore.setText("");
        }

    }


    @Override
    public int getItemCount() {
        return listfirst.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private LatoTextView firstrowscore, secondrowscore, thirdrowscore, fourthrowscore,fifthscore;

        public ViewHolder(View view) {
            super(view);


            firstrowscore = (LatoTextView) view.findViewById(R.id.playerscoreone);
            secondrowscore = (LatoTextView) view.findViewById(R.id.playerscoretwo);
            thirdrowscore = (LatoTextView) view.findViewById(R.id.playerscorethree);


            fourthrowscore = (LatoTextView) view.findViewById(R.id.playerscorefour);
            fifthscore = (LatoTextView) view.findViewById(R.id.playerscorefive);


        }


    }
}