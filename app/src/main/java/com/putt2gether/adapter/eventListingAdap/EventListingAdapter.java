package com.putt2gether.adapter.eventListingAdap;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.EventListBean.EventBean;
import com.putt2gether.putt.InviteEventDetail;
import com.putt2gether.view.LatoTextView;

/**
 * Created by Abc on 9/2/2016.
 */
public class EventListingAdapter extends RecyclerView.Adapter<EventListingAdapter.ViewHolder>  {

    private List<EventBean> list;
    private Activity context;
    EventBean eb;

    public EventListingAdapter(Activity mContext,List<EventBean> list) {
        this.context = mContext;
        this.list = list;
    }


    @Override
    public EventListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventlistingrow,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(final EventListingAdapter.ViewHolder holder, final int position) {


        eb = (EventBean) list.get( position );
        String eventname = eb.getEvent_name().toUpperCase();
        holder.listing_event_name.setText(eventname);

        String time=eb.getSatrt_time().toUpperCase();
        holder.listing_time.setText(time);

        String date=eb.getStart_date().toUpperCase();
        holder.listing_date.setText(date);

        String adminname=eb.getAdmin_name().toUpperCase();
        holder.listing_admin_name.setText(adminname);

        String golfname=eb.getGolf_name().toUpperCase();
        holder.listing_golf_name.setText(golfname);

        holder.detail_event_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InviteEventDetail.class);
                String eventid = list.get(position).getEvent_id();
                intent.putExtra("eventID",eventid);

                intent.putExtra("requestToParticipate","RTP");

                context.startActivity(intent);

            }
        });


    }


  @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        private LatoTextView listing_event_name,listing_time,listing_date,listing_admin_name,listing_golf_name;
        private RelativeLayout detail_event_row;

        public ViewHolder(View view) {
            super(view);


            listing_event_name = (LatoTextView)view.findViewById(R.id.listing_event_name);
            listing_time = (LatoTextView)view.findViewById(R.id.listing_time);
            listing_date = (LatoTextView)view.findViewById(R.id.listing_date);
            listing_admin_name = (LatoTextView)view.findViewById(R.id.listing_admin_name);
            listing_golf_name = (LatoTextView)view.findViewById(R.id.listing_golf_name);

            detail_event_row=(RelativeLayout)view.findViewById(R.id.detail_event_row);

        }



    }
}