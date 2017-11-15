package com.putt2gether.fragments.addscorefrags.scoreTable;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.view.LatoTextView;

/**
 * Created by Ajay on 19/10/2016.
 */
public class FourTwoZeroAdapter extends RecyclerView.Adapter<FourTwoZeroAdapter.ViewHolder> {

    private ArrayList<String> holeArray;
    private ArrayList<String> playerIDArray;

    private ArrayList<String> totalArray;
    private ArrayList<String> score1Array;
    private ArrayList<String> score2Array;
    private ArrayList<String> score3Array;


    private Activity context;


    public FourTwoZeroAdapter(Activity mContext, ArrayList<String> hole, ArrayList<String> id, ArrayList<String> score1, ArrayList<String> score2, ArrayList<String> score3, ArrayList<String> total) {
        this.context = mContext;
        this.holeArray = hole;
        this.playerIDArray = id;
        this.score1Array=score1;
        this.score2Array=score2;
        this.score3Array=score3;
        this.totalArray=total;

    }

    @Override
    public FourTwoZeroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.four_two_zero_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FourTwoZeroAdapter.ViewHolder holder, final int position) {

       if (position<score1Array.size()){
        holder.hole_num.setText(holeArray.get(position));
        holder.score1.setText(score1Array.get(position));
        holder.score2.setText(score2Array.get(position));
        holder.score3.setText(score3Array.get(position));
        }else {
           if (totalArray.size()!=0) {
               holder.hole_num.setText("TOTAL");
               holder.score1.setText(totalArray.get(0));
               holder.score2.setText(totalArray.get(1));
               holder.score3.setText(totalArray.get(2));
           }
        }





    }

    @Override
    public int getItemCount() {
        return score3Array.size()+1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private LatoTextView hole_num, score1, score2, score3;

        public ViewHolder(View view) {
            super(view);


            hole_num = (LatoTextView) view.findViewById(R.id.hole_no_420);
            score1 = (LatoTextView) view.findViewById(R.id.score1_420);
            score2 = (LatoTextView) view.findViewById(R.id.score2_420);
            score3 = (LatoTextView) view.findViewById(R.id.score3_420);


        }


    }
}