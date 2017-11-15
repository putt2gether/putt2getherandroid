package com.putt2gether.fragments.mygroups;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.SuggestionBean;
import com.putt2gether.custome.ItemTouchHelperAdapter;

/**
 * Created by Ajay on 29/11/2016.
 */
public class AddMemberAdapter  extends RecyclerView.Adapter<AddMemberAdapter.SimpleViewHolder> implements Filterable,ItemTouchHelperAdapter {



    private final Context mContext;
    private List<AddmemberBean> mData;
    private static ArrayList<AddmemberBean> listAddMember;
    ArrayList<String> addedMemberList = new ArrayList<String>();

    int count = 0;
    public AddPlayerAdapterCallback callback;


    private SharedPreferences mSharedPreferences;
   // private SharedPreferences createSharedPreferences;


    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();
    int top = 0;
    private int noPlayer;

    public void add(AddmemberBean s,int position) {
        position = position == -1 ? getItemCount()  : position;
        mData.add(position,s);
        notifyItemInserted(position);
    }

    public void remove(int position){
        if (position < getItemCount()  ) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        //public final TextView title;

        private TextView name;
        private ImageView image;
        private TextView addIcon;
        private ImageView starIcon;
        private TextView age;

        public SimpleViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.suggestion_row_name);
            image = (ImageView)view.findViewById(R.id.suggestions_row_image);
            addIcon = (TextView) view.findViewById(R.id.suggestion_row_addBTN);
            starIcon = (ImageView)view.findViewById(R.id.suggestion_row_star);
            age = (TextView)view.findViewById(R.id.suggestion_row_age);
        }
    }

    public AddMemberAdapter(Context context,ArrayList<AddmemberBean> data) {
        mContext = context;
        if (data != null) {
            mData = data;

            this.listAddMember = data;
            mInflater = LayoutInflater.from(context);
        }
        else mData = new ArrayList<AddmemberBean>();
    }



    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.suggestion_list_row, parent, false);

        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, final int position) {


        mSharedPreferences = mContext.getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        String capsName = mData.get(position).getFull_name();
        String caps = capsName.toUpperCase();
        holder.name.setText(caps);
        // holder.budget.setText(list.get(position).getBudget());

        String imageCheck = mData.get(position).getThumb_image();
        int isImageFound = imageCheck.length();
        if (isImageFound !=0) {
            Picasso.with(mContext).load(mData.get(position).getThumb_image()).into(holder.image);
        }else {
            holder.image.setImageResource(R.drawable.vishal);
        }


        final AddmemberBean pojo = mData.get(position);

        if(!pojo.isClicked()) {
            holder.addIcon.setText("ADD");

        } else {
            holder.addIcon.setText("REMOVE");

        }

        if(!pojo.isClicked()) {
            holder.addIcon.setText("ADD");

            holder.addIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    noPlayer = listAddMember.size()+1;
                    if (top < noPlayer-1) {

                        AddmemberBean bean = new AddmemberBean();
                        pojo.setClicked(true);
                        //add(bean,2);

                        String iddd = mData.get(position).getUser_id();
                        if(callback != null) {
                            callback.addPressed(Integer.parseInt(iddd));
                        }

                        Log.v("Clicked Position",position+"liji"+ mData.get(position).getFull_name());
                        onItemMove(position, top);

                        notifyDataSetChanged();
                        top = top + 1;
                        holder.addIcon.setText("REMOVE");


                    }else {
                        Log.v("Clicked Position", String.valueOf(holder.getAdapterPosition()));

                        final Dialog dialog = new Dialog(mContext);

                        dialog.setCanceledOnTouchOutside(true);
                        Window window = dialog.getWindow();
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


                        WindowManager.LayoutParams wlp = window.getAttributes();

                        wlp.gravity = Gravity.CENTER;
                        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                        window.setAttributes(wlp);


                        // Include dialog.xml file
                        dialog.setContentView(R.layout.pop_up_moreselection);
                        TextView text = (TextView)dialog.findViewById(R.id.popup_preview);

                        RelativeLayout close = (RelativeLayout)dialog.findViewById(R.id.close_popup);
                        text.setText("You haven't added same no. of players as selected on Create Event. Please add same no. players to continue.");

                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        dialog.show();

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });



                    }

                }
            });

        } else {
            holder.addIcon.setText("REMOVE");

            holder.addIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SuggestionBean bean = new SuggestionBean();
                    pojo.setClicked(false);

                    int in = mData.size() - 1;

                    String iddd = mData.get(position).getUser_id();
                    Log.v("Clicked Position",position+"liji"+ mData.get(position).getFull_name());

                    if(callback != null) {
                        callback.deletePressed(Integer.parseInt(iddd));
                    }

                    onItemMove(position,in);

                    notifyDataSetChanged();
                    holder.addIcon.setText("ADD");
                    top = top-1;


                }
            });


            for (int i = 0 ; i < top ; i++){

                String id1 = listAddMember.get(i).getUser_id();
                Log.v("idddddddd"+i,id1);
                addedMemberList.add(id1);
            }


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

            final List<AddmemberBean> list = listAddMember;
            int count = list.size();
            final ArrayList<AddmemberBean> nlist = new ArrayList<AddmemberBean>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = ""+list.get(i).getFull_name();
                if (filterableString.toLowerCase().contains(filterString)) {
                    AddmemberBean mBookServiceModel = list.get(i);
                    nlist.add(mBookServiceModel);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData = (ArrayList<AddmemberBean>) results.values;
            notifyDataSetChanged();
        }
    }

  /*  public void swap(int firstPosition, int secondPosition)
    {
        Collections.swap(listSuggesion, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    } */

    @Override
    public void onItemDismiss(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mData, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mData, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        notifyDataSetChanged();
    }

    public void setCallback(AddPlayerAdapterCallback callback){

        this.callback = callback;
    }


    public interface AddPlayerAdapterCallback {

        public void deletePressed(int position);
        public void addPressed(int position);
    }

}