package com.putt2gether.fragments.mygroups;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;

/**
 * Created by Ajay on 23/11/2016.
 */
public class MyGroupDetailAdapter  extends RecyclerView.Adapter<MyGroupDetailAdapter.SimpleViewHolder> implements Filterable{

    private final Context mContext;
    private List<GroupDetailBean> mData;

    int count = 0;
    String isAdminType;
    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();

    public EditPlayerAdapterCallback callback;


    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public  class SimpleViewHolder extends RecyclerView.ViewHolder {
        //public final TextView title;

        private TextView name;
        private ImageView image;
        private TextView removeIcon;
        private ImageView starIcon;
        private TextView age;
        private String radioText;

        public SimpleViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.suggestion_row_name);
            image = (ImageView)view.findViewById(R.id.suggestions_row_image);
            removeIcon = (TextView) view.findViewById(R.id.suggestion_row_addBTN);
            starIcon = (ImageView)view.findViewById(R.id.suggestion_row_star);
            age = (TextView)view.findViewById(R.id.suggestion_row_age);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isAdminType.equalsIgnoreCase("1")) {

                        if (mData.get(getPosition()).getIs_admin().equalsIgnoreCase("1")){
                            removeIcon.setVisibility(View.GONE);
                        }else {
                            removeAt(getPosition());
                        }
                    }else {
                        Toast.makeText(mContext,"Only admin can delete.",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


    }
    public void removeAt(int position) {


        if(callback != null) {

            callback.deletePressed(position);
        }

        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    public MyGroupDetailAdapter(Context context,ArrayList<GroupDetailBean> data,String adminType) {
        mContext = context;
        isAdminType = adminType;
        if (data != null) {
            mData = data;
            mInflater = LayoutInflater.from(context);
        } else mData = new ArrayList<GroupDetailBean>();
    }



    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.group_detail_row, parent, false);



        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, final int position) {

        String capsName = mData.get(position).getDisplay_name();
        String caps = capsName.toUpperCase();
        holder.name.setText(caps);
        // holder.budget.setText(list.get(position).getBudget());

        String imageCheck = mData.get(position).getPhoto_url();
        int isImageFound = imageCheck.length();

        if (isImageFound >10) {
            Picasso.with(mContext).load(mData.get(position).getPhoto_url()).into(holder.image);

        }else {
            holder.image.setImageResource(R.drawable.vishal);
        }
        holder.age.setText(mData.get(position).getSelf_handicap());

        if (mData.get(position).getIs_admin().equalsIgnoreCase("1")){

            holder.removeIcon.setText("ADMIN");
        }else {
            holder.removeIcon.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<GroupDetailBean> list = mData;
            int count = list.size();
            final ArrayList<GroupDetailBean> nlist = new ArrayList<GroupDetailBean>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = ""+list.get(i).getDisplay_name();
                if (filterableString.toLowerCase().contains(filterString)) {
                    GroupDetailBean mBookServiceModel = list.get(i);
                    nlist.add(mBookServiceModel);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData = (ArrayList<GroupDetailBean>) results.values;
            notifyDataSetChanged();
        }
    }

    public void setCallback(EditPlayerAdapterCallback callback){

        this.callback = callback;
    }


    public interface EditPlayerAdapterCallback {

        public void deletePressed(int position);
    }
}