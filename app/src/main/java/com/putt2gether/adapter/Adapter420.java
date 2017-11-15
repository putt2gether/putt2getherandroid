package com.putt2gether.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.Bean420;
import com.putt2gether.volley_class.AppController;

/**
 * Created by Ajay on 27/02/2017.
 */
public class Adapter420  extends RecyclerView.Adapter<Adapter420.ViewHolder> {

    private List<Bean420> list;
    private Context context;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    String scorer_ID="";
    int pos,posfrom;

    public Adapter420(Context mContext,List<Bean420> list) {
        this.context = mContext;
        this.list = list;
    }

    public Adapter420(Context mcontext,List<Bean420> list,String scorer_id,int pos,int posfrom)
    {
        this.context = mcontext;
        this.list = list;
        this.scorer_ID=scorer_id;

        this.pos=pos;
        this.posfrom=posfrom;

    }

    @Override
    public Adapter420.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_420,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(Adapter420.ViewHolder holder, final int position) {


        holder.hole.setText(list.get(position).getHoleNo());
        holder.holeScore1.setText(list.get(position).getHoleSocre1());
        holder.holeScore1.setTextColor(Color.parseColor(list.get(position).getHoleColor1()));

        holder.holeScore2.setText(list.get(position).getHoleScore2());
        holder.holeScore2.setTextColor(Color.parseColor(list.get(position).getHoleColor2()));

        holder.holeScore3.setText(list.get(position).getHoleScore3());
        holder.holeScore3.setTextColor(Color.parseColor(list.get(position).getHoleColor3()));

        holder.aggScore1.setText(list.get(position).getAggScore1());
        holder.aggScore1.setTextColor(Color.parseColor(list.get(position).getAggColor1()));

        holder.aggScore2.setText(list.get(position).getAggScore2());
        holder.aggScore2.setTextColor(Color.parseColor(list.get(position).getAggColor2()));

        holder.aggScore3.setText(list.get(position).getAggScore3());
        holder.aggScore3.setTextColor(Color.parseColor(list.get(position).getAggColor3()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView hole,holeScore1,holeScore2,holeScore3,aggScore1,aggScore2,aggScore3;

        public ViewHolder(View view) {
            super(view);

            hole = (TextView)view.findViewById(R.id.hole_21_row);
            holeScore1 = (TextView)view.findViewById(R.id.holeScore1_420_row);
            aggScore1 = (TextView)view.findViewById(R.id.aggScore1_420_row);
            holeScore2 = (TextView)view.findViewById(R.id.holeScore2_420_row);
            aggScore2 = (TextView)view.findViewById(R.id.aggScore2_420_row);
            holeScore3 = (TextView)view.findViewById(R.id.holeScore3_420_row);
            aggScore3 = (TextView)view.findViewById(R.id.aggScore3_420_row);



        }


    }


}