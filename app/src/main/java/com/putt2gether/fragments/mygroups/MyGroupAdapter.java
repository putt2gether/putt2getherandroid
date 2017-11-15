package com.putt2gether.fragments.mygroups;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.volley_class.AppController;


/**
 * Created by Ajay on 08/09/2016.
 */
public class MyGroupAdapter  extends RecyclerView.Adapter<MyGroupAdapter.ViewHolder>{

    private static ArrayList<MyGroupBean> listSuggesion;
    private LayoutInflater mInflater;
    private List<MyGroupBean> list;
    private Context context;
    private Activity mActivity1;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public MyGroupAdapter(Context mContext,ArrayList<MyGroupBean> list) {
        this.context = mContext;
        this.list = list;
        this.listSuggesion = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_group_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,mActivity1);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        String capsName = list.get(position).getGroup_name();
        String caps = capsName.toUpperCase();
        holder.name.setText(caps);


        String imageCheck = list.get(position).getGroup_image();
        int isImageFound = imageCheck.length();

        if (isImageFound !=0) {
            Picasso.with(context).load(list.get(position).getGroup_image()).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private ImageView image;
        private RelativeLayout nextICON;


        public ViewHolder(View view,Activity mActivity) {
            super(view);
            mActivity1=mActivity;
            view.setOnClickListener(this);

            name = (TextView)view.findViewById(R.id.mygroup_row_name);
            image = (ImageView)view.findViewById(R.id.mygroup_row_image);
            nextICON = (RelativeLayout)view.findViewById(R.id.mygroup_row_next);

        }


        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context,MyGroupDetail.class);
            intent.putExtra("groupID",list.get(getAdapterPosition()).getGroup_id());
            context.startActivity(intent);


        }
    }
}