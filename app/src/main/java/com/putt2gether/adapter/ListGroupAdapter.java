package com.putt2gether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.bean.GroupPopBean;

/**
 * Created by Ajay on 31/01/2017.
 */
public class ListGroupAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<GroupPopBean> objects;
    ViewHolder holder = null;

    public ListGroupAdapter(Context context, ArrayList<GroupPopBean> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.multiple_choice_lay, parent, false);
            holder = new ViewHolder();
            view.setTag(holder);
            holder.txt = (TextView)view.findViewById(R.id.name_friend);
            holder.img = (ImageView) view.findViewById(R.id.img_friend);
            holder.cbBuy = (CheckBox) view.findViewById(R.id.checkBox1);


        }else {
            holder = (ViewHolder) view.getTag();

        }

        GroupPopBean p = getProduct(position);

        if (p.group_image!=null && p.group_image.length()>10) {

            Picasso.with(ctx).load(p.group_image).into(holder.img);
        }else {
            holder.img.setImageResource(R.drawable.vishal);
        }

        holder.txt.setText((p.group_name).toUpperCase());

        holder.cbBuy.setOnCheckedChangeListener(myCheckChangList);
        holder.cbBuy.setTag(position);
        String chek = p.action;

        if (chek.equalsIgnoreCase("2")){
            holder.cbBuy.setChecked(true);
        }
        holder.cbBuy.setChecked(p.box);
        return view;
    }

    GroupPopBean getProduct(int position) {
        return ((GroupPopBean) getItem(position));
    }

    public ArrayList<GroupPopBean> getBox() {
        ArrayList<GroupPopBean> box = new ArrayList<GroupPopBean>();
        for (GroupPopBean p : objects) {
            if (p.box)
                box.add(p);
        }
        return box;
    }

    CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            getProduct((Integer) buttonView.getTag()).box = isChecked;
        }
    };


    private static class ViewHolder {
       public TextView txt = null;
       public ImageView img = null;
       public CheckBox cbBuy = null;
    }
}