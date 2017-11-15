package com.putt2gether.fragments.selectcourse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import com.putt2gether.R;

/**
 * Created by Abc on 9/9/2016.
 */
public class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    ArrayList<String> countryid;
    ArrayList<String> countryname;
    ArrayList<String> hasEvent;
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<String> countryid,ArrayList<String> countryname,ArrayList<String> has_event) {
        inflater = LayoutInflater.from(context);
        this.countryid = countryid;
        this.countryname=countryname;
        this.hasEvent = has_event;

    }

    @Override
    public int getCount() {
        return countryname.size();
    }

    @Override
    public Object getItem(int position) {
        return countryname.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.browse_list_layout, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.countryname);
            holder.blueDot = (RelativeLayout)convertView.findViewById(R.id.bluedot_country);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(countryname.get(position));

        if (hasEvent.get(position).equalsIgnoreCase("0")){
            holder.blueDot.setVisibility(View.GONE);
        }else {
            holder.blueDot.setVisibility(View.VISIBLE);
        }


        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.list_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.listheader);
            holder.blueDot = (RelativeLayout)convertView.findViewById(R.id.bluedot_country);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String name1=countryname.get(position);

        if(!name1.equals(""))
        {
            String headerText = "" + countryname.get(position).subSequence(0, 1).charAt(0);

            holder.text.setText(headerText);
        }

      else
        {

              holder.text.setText("Z");
        }



        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon

        char ch='z';

        String name1=countryname.get(position);
        if(!name1.equals(""))
        {
            ch=countryname.get(position).subSequence(0,1).charAt(0);


        }
        return ch ;

    }

    class HeaderViewHolder {
        TextView text;
        RelativeLayout blueDot;
    }

    class ViewHolder {
        TextView text;
        RelativeLayout blueDot;
    }

}