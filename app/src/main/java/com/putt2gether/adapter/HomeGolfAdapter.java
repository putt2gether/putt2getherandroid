package com.putt2gether.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.bean.HomeGolfBean;


/**
 * Created by Ajay on 18/01/2017.
 */
public class HomeGolfAdapter  extends BaseAdapter implements Filterable {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<HomeGolfBean> objects;

    ArrayList<HomeGolfBean> suggestions ;

    public HomeGolfAdapter(Context context, ArrayList<HomeGolfBean> products) {
        ctx = context;
        objects = products;
        this.suggestions = new ArrayList<HomeGolfBean>();
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }
    @Override
    public Object getItem(int position) {
        return suggestions.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.home_golf_row, parent, false);
        }

        //InviteFriendBean p = getProduct(position);
        ((TextView) view.findViewById(R.id.name_golf)).setText((suggestions.get(position).getGolfName()).toUpperCase());

        RelativeLayout parentRow = (RelativeLayout)view.findViewById(R.id.parent_checkBox);

        return view;
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Log.i("TAG", "convertResultToString :" + resultValue);
            String str = ((HomeGolfBean) resultValue).getGolfName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();


            Log.d("FILTERRES", "" + objects.size() + ", " + constraint);


            if (objects != null && constraint != null) {
                suggestions.clear();

                try {
                    for (int i = 0; i < objects.size(); i++) {
                        //  InviteFriendBean p = getProduct(i);
                        if (objects.get(i).getGolfName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                            // if (p.frnd_name.toLowerCase().startsWith(constraint.toString().toLowerCase())){
                            Log.d("filter", "Found --- " + objects.get(i).getGolfName());
                            suggestions.add(objects.get(i));
                        }
                    }
                } catch (Exception e) {
                }
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                Log.v("hfuiehf",suggestions.get(0).getGolfID());
            }


            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results != null && results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }

        }

    };
}