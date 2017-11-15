package com.putt2gether.putt.delegates;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.putt2gether.R;

/**
 * Created by Abc on 9/13/2016.
 */
public class DelegatesAdapter extends RecyclerView.Adapter<DelegatesAdapter.MyViewHolder> {

    ArrayList<DelegateBean> delegateList = new ArrayList<DelegateBean>();

    TextView delegate;
    Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_hole, delegate_to,hole_number;
        ImageView split_delegate;

        public MyViewHolder(View view) {
            super(view);
            name_hole = (TextView) view.findViewById(R.id.name_hole);
            hole_number=(TextView)view.findViewById(R.id.hole_number);
            delegate_to = (TextView) view.findViewById(R.id.delegate_to);
            split_delegate=(ImageView)view.findViewById(R.id.split_delegate);
        }
    }
    public DelegatesAdapter(Context con,ArrayList<DelegateBean> list) {
        this.delegateList = list;
        this.mContext= con;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.name_hole.setText(delegateList.get(position).getPlayerName().toUpperCase()+" ");
        holder.hole_number.setText(delegateList.get(position).getHandicapValue());

        if (delegateList.get(position).getPlayerName().equalsIgnoreCase(delegateList.get(position).getScorerName())){
            holder.delegate_to.setVisibility(View.VISIBLE);
            holder.delegate_to.setText("DELEGATE TO");
        }else {
            holder.delegate_to.setVisibility(View.VISIBLE);
            holder.delegate_to.setText(delegateList.get(position).getScorerName().toUpperCase());
        }
        holder.split_delegate.setBackgroundResource(R.drawable.down_arrow);


    }



    @Override
    public int getItemCount() {
        return delegateList.size();
    }

    public void setdelegate()
    {

    }
}