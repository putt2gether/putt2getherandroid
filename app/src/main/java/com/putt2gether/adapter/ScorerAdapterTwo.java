package com.putt2gether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.LeaderboardBean.ScoreBeanTwo;
import com.putt2gether.putt.ScoreActivityTwo;
import com.putt2gether.putt.ScorerActivity;
import com.putt2gether.volley_class.AppController;

/**
 * Created by Abc on 9/22/2016.
 */
public class ScorerAdapterTwo  extends RecyclerView.Adapter<ScorerAdapterTwo.ViewHolder> {

    private List<ScoreBeanTwo> list;
    private Context context;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    int frompost;

    public ScorerAdapterTwo(Context mContext,List<ScoreBeanTwo> list,int frompost) {
        this.context = mContext;
        this.list = list;
        this.frompost=frompost;
    }

    @Override
    public ScorerAdapterTwo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.scoreadaptwo,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(final ScorerAdapterTwo.ViewHolder holder,final int position) {

        String caps = list.get(position).getName();
        String capsString = caps.toUpperCase();
        holder.name.setText(capsString);
        // holder.budget.setText(list.get(position).getBudget());
        String handicapvalue=list.get(position).getHandicap_Value();
        holder.handicap_value.setText(handicapvalue);

        holder.openplayerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.tickplayer.setVisibility(View.VISIBLE);

                String playername=list.get(position).getName();
                String playerid=list.get(position).getParticipant_ID();

                String my_id = ((ScoreActivityTwo) context).geteventid();

                Log.e("playername11",playername);


                Intent intent = new Intent(context,ScorerActivity.class);
                intent.putExtra("mylist", (Serializable) list);
                intent.putExtra("ACTIVITY", "TWO");
                intent.putExtra("eventID", my_id);
                intent.putExtra("playername", playername);
                intent.putExtra("playerid", playerid);
                intent.putExtra("positionfor",position);
                intent.putExtra("positionfrom",frompost);

                ((ScoreActivityTwo) context).setResult(1,intent);

                ((ScoreActivityTwo) context).finish();



            }
        });

        String photourl=list.get(position).getPhoto_url();
        if(photourl.isEmpty())
        {
            holder.image.setImageResource(R.drawable.vishal);
        }
        else{
            Picasso.with(context).load(photourl).into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView name,handicap_value;
        private ImageView image,tickplayer;
        RelativeLayout openplayerlist;



        public ViewHolder(View view) {
            super(view);


            name = (TextView)view.findViewById(R.id.scorer_row_name);

            image = (ImageView)view.findViewById(R.id.scorer_row_image);

            handicap_value=(TextView)view.findViewById(R.id.handicap_value);
            openplayerlist=(RelativeLayout)view.findViewById(R.id.tickplayerouter);
            tickplayer=(ImageView)view.findViewById(R.id.tickplayer);

        }


    }


}