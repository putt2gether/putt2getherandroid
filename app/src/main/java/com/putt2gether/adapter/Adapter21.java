package com.putt2gether.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.ExpandBean;
import com.putt2gether.volley_class.AppController;

/**
 * Created by Ajay on 27/02/2017.
 */
public class Adapter21  extends RecyclerView.Adapter<Adapter21.ViewHolder> {

    private List<ExpandBean> list;
    private Context context;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    String scorer_ID="";
    int pos,posfrom;

    public Adapter21(Context mContext,List<ExpandBean> list) {
        this.context = mContext;
        this.list = list;
    }

    public Adapter21(Context mcontext,List<ExpandBean> list,String scorer_id,int pos,int posfrom)
    {
        this.context = mcontext;
        this.list = list;
        this.scorer_ID=scorer_id;

        this.pos=pos;
        this.posfrom=posfrom;

    }

    @Override
    public Adapter21.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_21,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(Adapter21.ViewHolder holder, final int position) {


        holder.hole.setText(list.get(position).getHole_number());
        holder.holeScore.setText(list.get(position).getHole_score());

        holder.holeScore.setBackgroundResource(R.drawable.bubble);
        GradientDrawable drawable = (GradientDrawable) holder.holeScore.getBackground();
        drawable.setColor(Color.parseColor(list.get(position).getHole_color()));

        holder.aggScore.setText(list.get(position).getAgg_score());
        holder.aggScore.setBackgroundResource(R.drawable.bubble);
        GradientDrawable drawable1 = (GradientDrawable) holder.aggScore.getBackground();
        drawable1.setColor(Color.parseColor(list.get(position).getAgg_color()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView hole,holeScore,aggScore;

        public ViewHolder(View view) {
            super(view);

            hole = (TextView)view.findViewById(R.id.hole_21_row);
            holeScore = (TextView)view.findViewById(R.id.holeScore_21_row);
            aggScore = (TextView)view.findViewById(R.id.aggScore_21_row);



        }


    }


}