package com.putt2gether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.SearchGolfCouseBean;
import com.putt2gether.putt.addScore.DatePickerClass;

/**
 * Created by Ajay on 04/08/2016.
 */
public class SearchGolfCourseAdapterInvite extends RecyclerView.Adapter<SearchGolfCourseAdapterInvite.ViewHolder>  {

    private List<SearchGolfCouseBean> list;
    private List<SearchGolfCouseBean>filteredData = null;
    private Context context;
    // private ItemFilter mFilter = new ItemFilter();
    //private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public SearchGolfCourseAdapterInvite(Context mContext,List<SearchGolfCouseBean> list) {
        this.context = mContext;
        this.list = list;
        filteredData = list;
    }

    @Override
    public SearchGolfCourseAdapterInvite.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.golf_course_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(SearchGolfCourseAdapterInvite.ViewHolder holder, int position) {

        String str1 = list.get(position).getGolf_course_name();
        String capsGolf = str1.toUpperCase();
        holder.golfCourseName.setText(capsGolf);
        // holder.budget.setText(list.get(position).getBudget());
        String str2 = list.get(position).getCity_name();
        String capsCity = str2.toUpperCase();
        holder.golfCourseAddress.setText(capsCity);
        if (list.get(position).getHas_event().equalsIgnoreCase("0")){
            holder.blueDot.setVisibility(View.GONE);
        }else {
            holder.blueDot.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView golfCourseName;

        private RelativeLayout blueDot;
        private TextView golfCourseAddress;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            golfCourseName = (TextView)view.findViewById(R.id.golf_course_name_row);
            blueDot = (RelativeLayout)view.findViewById(R.id.bluedot_golfCours);
            golfCourseAddress = (TextView)view.findViewById(R.id.golf_course_address_row);

        }

        @Override
        public void onClick(View v) {


          /*  Intent intent = new Intent(context, CreateEventActivity.class);

            SearchGolfCouseBean bean = list.get(getAdapterPosition());
            String name = bean.getGolf_course_name();
            String address = bean.getCity_name();
            String golfID = bean.getGolf_course_id();
            intent.putExtra("golfCourseNameSearch",name);
            intent.putExtra("golfCourseNameAddressSearch",address);
            intent.putExtra("golf_couse_id_search",golfID);
            context.startActivity(intent);   */

            SearchGolfCouseBean bean = list.get(getAdapterPosition());
            String golfID = bean.getGolf_course_id();

            Intent i=new Intent(context, DatePickerClass.class);
            i.putExtra("golf_couse_id_",golfID);
            context.startActivity(i);

        }

    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<SearchGolfCouseBean> list2 = list ;
            int count = list2.size();
            final ArrayList<SearchGolfCouseBean> nlist = new ArrayList<SearchGolfCouseBean>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = ""+list2.get(i).getGolf_course_name();
                if (filterableString.toLowerCase().contains(filterString)) {
                    SearchGolfCouseBean golfCourseBean = list2.get(i);
                    nlist.add(golfCourseBean);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<SearchGolfCouseBean>) results.values;
            notifyDataSetChanged();
        }
    }


}