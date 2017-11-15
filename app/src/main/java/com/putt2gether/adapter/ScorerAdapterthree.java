package com.putt2gether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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
 * Created by Abc on 9/26/2016.
 */
public class ScorerAdapterthree extends RecyclerView.Adapter<ScorerAdapterthree.ViewHolder> {

    private List<ScoreBeanTwo> list;
    private Context context;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    String scorer_ID="";
    int pos,posfrom;

    public ScorerAdapterthree(Context mContext,List<ScoreBeanTwo> list) {
        this.context = mContext;
        this.list = list;
    }

    public ScorerAdapterthree(Context mcontext,List<ScoreBeanTwo> list,String scorer_id,int pos,int posfrom)
    {
        this.context = mcontext;
        this.list = list;
        this.scorer_ID=scorer_id;

        this.pos=pos;
        this.posfrom=posfrom;

    }

    @Override
    public ScorerAdapterthree.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(ScorerAdapterthree.ViewHolder holder, final int position) {

        String caps = list.get(position).getName();
        String capsString = caps.toUpperCase();
        holder.name.setText(capsString);
        // holder.budget.setText(list.get(position).getBudget());
        String handicapvalue=list.get(position).getHandicap_Value();
        holder.handicap_value.setText(handicapvalue);


            holder.scorer_name_view.setText(list.get(position).getSelf_delegate());



        holder.openplayerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context,ScoreActivityTwo.class);
                intent.putExtra("mylist", (Serializable) list);
                intent.putExtra("frompost",position);

                ((ScorerActivity) context).startActivityForResult(intent, 1);


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
        private TextView name,handicap_value,scorer_name_view;
        private ImageView image;
        RelativeLayout openplayerlist;





        public ViewHolder(View view) {
            super(view);


            name = (TextView)view.findViewById(R.id.scorer_row_name);

            image = (ImageView)view.findViewById(R.id.scorer_row_image);

            handicap_value=(TextView)view.findViewById(R.id.handicap_value);
            openplayerlist=(RelativeLayout)view.findViewById(R.id.openplayerlist);
            scorer_name_view=(TextView)view.findViewById(R.id.scorer_nameview);

        }


    }


}