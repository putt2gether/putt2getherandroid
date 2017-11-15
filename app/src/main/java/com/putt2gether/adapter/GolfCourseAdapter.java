package com.putt2gether.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.GolfCourseBean;
import com.putt2gether.putt.CreateEventActivity;
import com.putt2gether.putt.EditEventActivity;

/**
 * Created by Ajay on 21/06/2016.
 */
public class GolfCourseAdapter extends RecyclerView.Adapter<GolfCourseAdapter.ViewHolder>  {

    private List<GolfCourseBean> list;
    private List<GolfCourseBean>filteredData = null;
    private Context context;
   // private ItemFilter mFilter = new ItemFilter();
    //private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public GolfCourseAdapter(Context mContext,List<GolfCourseBean> list) {
        this.context = mContext;
        this.list = list;
        filteredData = list;
    }

    @Override
    public GolfCourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.golf_course_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(GolfCourseAdapter.ViewHolder holder, int position) {

        String str1 = list.get(position).getGolfCorseName();
        String golfUP = str1.toUpperCase();

        holder.golfCourseName.setText(golfUP);
        // holder.budget.setText(list.get(position).getBudget());

        String str2 = list.get(position).getGolfCourseAddress();
        String cityUP = str2.toUpperCase();
        holder.golfCourseAddress.setText(cityUP);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView golfCourseName;

        private TextView golfCourseAddress;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            golfCourseName = (TextView)view.findViewById(R.id.golf_course_name_row);

            golfCourseAddress = (TextView)view.findViewById(R.id.golf_course_address_row);

        }

        @Override
        public void onClick(View v) {


            SharedPreferences pref = context.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
            String resultFor = pref.getString("resultFor",null);
            if (resultFor.equalsIgnoreCase("createEvent")) {

                Intent intent = new Intent(context, CreateEventActivity.class);
                GolfCourseBean bean = list.get(getAdapterPosition());
                String name = bean.getGolfCorseName();
                String golfID = bean.getGolfCourseID();
                String address = bean.getGolfCourseAddress();
                intent.putExtra("golfCourseName", name);
                intent.putExtra("golfCourseNameAddress", address);
                intent.putExtra("golf_couse_id", golfID);
                context.startActivity(intent);
                ((Activity)context).finish();

            }else if (resultFor.equalsIgnoreCase("editEvent")){

                String eventID = pref.getString("eventid",null);

                Intent intent = new Intent(context, EditEventActivity.class);
                GolfCourseBean bean = list.get(getAdapterPosition());
                String name = bean.getGolfCorseName();
                String golfID = bean.getGolfCourseID();
                String address = bean.getGolfCourseAddress();
                intent.putExtra("golfCourseName", name);
                intent.putExtra("golfCourseNameAddress", address);
                intent.putExtra("golf_couse_id", golfID);

                intent.putExtra("eventID",eventID);

                context.startActivity(intent);

                ((Activity)context).finish();
            }


        }

    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<GolfCourseBean> list2 = list ;
            int count = list2.size();
            final ArrayList<GolfCourseBean> nlist = new ArrayList<GolfCourseBean>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = ""+list2.get(i).getGolfCorseName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    GolfCourseBean golfCourseBean = list2.get(i);
                    nlist.add(golfCourseBean);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<GolfCourseBean>) results.values;
            notifyDataSetChanged();
        }
    }


}
