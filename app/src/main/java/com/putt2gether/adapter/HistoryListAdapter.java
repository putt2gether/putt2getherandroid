package com.putt2gether.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.bean.ScoreHistoryBean;
import com.putt2gether.putt.addScore.AutoPressScoreCard;
import com.putt2gether.putt.addScore.Leaderboard;
import com.putt2gether.putt.addScore.ScoreTable;
import com.putt2gether.putt.score_card.NewGameScoreCard;

/**
 * Created by Abc on 8/23/2016.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.MyViewHolder> {

    ArrayList<ScoreHistoryBean> scorehistry=new ArrayList<ScoreHistoryBean>();
    ScoreHistoryBean scorehistrymodel;
    Activity a1;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView scoretext, nametext,datetext,venuetext,ranktext;
        ImageView imageRank;
        TextView imageText;

        ImageView par,eagle,gross,birdies;
        RelativeLayout circleLayout;
        LinearLayout leaderBoard;


        public MyViewHolder(View view) {
            super(view);
            scoretext = (TextView) view.findViewById(R.id.scorenumberhistry);
            ranktext = (TextView) view.findViewById(R.id.rank_tv_history_list);
            nametext = (TextView) view.findViewById(R.id.namemainhistry);
            datetext = (TextView) view.findViewById(R.id.datehistry);
            venuetext = (TextView) view.findViewById(R.id.venuehistry);
            circleLayout = (RelativeLayout)view.findViewById(R.id.circle_layout);

            imageRank = (ImageView)view.findViewById(R.id.rank_image);


            leaderBoard = (LinearLayout)view.findViewById(R.id.badges_layout);

            par = (ImageView)view.findViewById(R.id.par_history);
            birdies = (ImageView)view.findViewById(R.id.birdie_history);
            eagle = (ImageView)view.findViewById(R.id.eagle_history);
            gross = (ImageView)view.findViewById(R.id.gross_history);
        }
    }
    public HistoryListAdapter(Activity a, ArrayList<ScoreHistoryBean> scorehistry1) {
        this.scorehistry = scorehistry1;
        this.a1=a;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.historyrow, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        scorehistrymodel = ( ScoreHistoryBean ) scorehistry.get( position );
        holder.scoretext.setText(scorehistrymodel.getTotal().toUpperCase());
        holder.ranktext.setText(scorehistrymodel.getCurrentPosion().toUpperCase());
        holder.nametext.setText(scorehistrymodel.getEventName().toUpperCase());
        holder.datetext.setText(scorehistrymodel.getDate().toUpperCase());
        holder.venuetext.setText(scorehistrymodel.getGolfCourseName().toUpperCase());

        if (scorehistrymodel.getCurrentPosion().equalsIgnoreCase("1") || scorehistrymodel.getCurrentPosion().equalsIgnoreCase("T1")){
            holder.ranktext.setVisibility(View.GONE);
            holder.imageRank.setVisibility(View.VISIBLE);
            holder.imageRank.setImageResource(R.mipmap.winner_cup);
        }else {
            holder.ranktext.setVisibility(View.VISIBLE);
            holder.imageRank.setVisibility(View.GONE);
            holder.ranktext.setText(scorehistrymodel.getCurrentPosion());
        }


        holder.leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventID = scorehistry.get( position ).getEventID();

                String formatID = scorehistry.get(position).getFormatID();
                String totalPlayers = scorehistry.get(position).getNo_of_player_accepted();


                if (formatID.equalsIgnoreCase("10") || formatID.equalsIgnoreCase("12") || formatID.equalsIgnoreCase("13") || formatID.equalsIgnoreCase("14")) {

                    a1.startActivity(new Intent(a1, NewGameScoreCard.class).putExtra("eventID", eventID).putExtra("from", "history").putExtra("stats_visible", "yes"));
                } else if (formatID.equalsIgnoreCase("11")) {

                    a1.startActivity(new Intent(a1, AutoPressScoreCard.class).putExtra("eventID", eventID).putExtra("from", "history").putExtra("stats_visible", "yes"));
                }else if (totalPlayers.equalsIgnoreCase("1")){


                    Intent i=new Intent(a1,ScoreTable.class);
                    i.putExtra("eventID",eventID);
                    i.putExtra("isDelegate","1");
                    i.putExtra("playerID","noneed");
                    i.putExtra("stats_visible", "yes");
                    a1.startActivity(i);

                }else {
                    Intent i = new Intent(a1, Leaderboard.class);

                    Log.v("eklk;lk", eventID);
                    i.putExtra("eventID", eventID);
                    i.putExtra("from", "history");
                    i.putExtra("stats_visible", "yes");
                    a1.startActivity(i);
                }
               // a1.finish();
            }
        });



        if (scorehistrymodel.getEagle().equalsIgnoreCase("1")){
            holder.eagle.setVisibility(View.VISIBLE);
        }else {
            holder.eagle.setVisibility(View.GONE);
        }

        if (scorehistrymodel.getGrossScore().equalsIgnoreCase("1")){
            holder.gross.setVisibility(View.VISIBLE);
        }else {
            holder.gross.setVisibility(View.GONE);
        }

        if (scorehistrymodel.getNoOfPars().equalsIgnoreCase("1")){
            holder.par.setVisibility(View.VISIBLE);
        }else {
            holder.par.setVisibility(View.GONE);
        }

        if (scorehistrymodel.getNoOfBirdies().equalsIgnoreCase("1")){
            holder.birdies.setVisibility(View.VISIBLE);
        }else {
            holder.birdies.setVisibility(View.GONE);
        }

        if (scorehistrymodel.getNoOfPlayer().equalsIgnoreCase("1")){

            holder.circleLayout.setBackgroundResource(R.mipmap.winner_circle1);
        }else if (scorehistrymodel.getNoOfPlayer().equalsIgnoreCase("2")){

            holder.circleLayout.setBackgroundResource(R.mipmap.winner_circle2);
        }else if (scorehistrymodel.getNoOfPlayer().equalsIgnoreCase("3")){

            holder.circleLayout.setBackgroundResource(R.mipmap.winner_circle3);
        }else if (scorehistrymodel.getNoOfPlayer().equalsIgnoreCase("4")){

            holder.circleLayout.setBackgroundResource(R.mipmap.winner_circle4);
        }else if (scorehistrymodel.getNoOfPlayer().equalsIgnoreCase("4+")){

            holder.circleLayout.setBackgroundResource(R.mipmap.winner_circle5);
        }


        holder.circleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(a1);
                dialog.setCanceledOnTouchOutside(true);
                Window window = dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.CENTER;
                wlp.dimAmount = 0.7f;
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);


                // Include dialog.xml file
                dialog.setContentView(R.layout.history_popup);

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView dialogText =(TextView) dialog.findViewById(R.id.popup_history);
                TextView rankText = (TextView)dialog.findViewById(R.id.rank_tv_history_popup);
                ImageView rankImage = (ImageView)dialog.findViewById(R.id.rank_image_popup);
                if (scorehistry.get( position ).getCurrentPosion().equalsIgnoreCase("1") || scorehistry.get( position ).getCurrentPosion().equalsIgnoreCase("T1")){
                    rankText.setVisibility(View.GONE);
                    rankImage.setVisibility(View.VISIBLE);
                    rankImage.setImageResource(R.mipmap.winner_cup);

                    String noOfPLayer = scorehistry.get(position).getNo_of_player_accepted();
                    String winn = "YOU ARE WINNER";
                    if (noOfPLayer.equalsIgnoreCase("1")){
                         winn = "YOU ARE WINNER OF "+noOfPLayer+" PLAYER EVENT";
                    }else {

                         winn = "YOU ARE WINNER OF " + noOfPLayer + " PLAYERS EVENT";
                    }
                    dialogText.setText(winn);

                }else {
                    rankText.setVisibility(View.VISIBLE);
                    rankImage.setVisibility(View.GONE);
                    rankText.setText(scorehistry.get( position ).getCurrentPosion());

                    String noOfPLayer = scorehistry.get(position).getNo_of_player_accepted();
                    String rankTeXT = scorehistry.get( position ).getCurrentPosion();
                    String winn = "YOU ARE RANKED "+rankTeXT+" FOR "+noOfPLayer+" PLAYERS EVENT";
                    dialogText.setText(winn);
                }


                RelativeLayout img = (RelativeLayout) dialog.findViewById(R.id.circle_layout_pop);

                if (scorehistry.get( position ).getNoOfPlayer().equalsIgnoreCase("1")){

                    img.setBackgroundResource(R.mipmap.winner_circle1);
                }else if (scorehistry.get( position ).getNoOfPlayer().equalsIgnoreCase("2")){

                    img.setBackgroundResource(R.mipmap.winner_circle2);
                }else if (scorehistry.get( position ).getNoOfPlayer().equalsIgnoreCase("3")){

                    img.setBackgroundResource(R.mipmap.winner_circle3);
                }else if (scorehistry.get( position ).getNoOfPlayer().equalsIgnoreCase("4")){

                    img.setBackgroundResource(R.mipmap.winner_circle4);
                }else if (scorehistry.get( position ).getNoOfPlayer().equalsIgnoreCase("4+")){

                    img.setBackgroundResource(R.mipmap.winner_circle5);
                }


                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return scorehistry.size();
    }




}