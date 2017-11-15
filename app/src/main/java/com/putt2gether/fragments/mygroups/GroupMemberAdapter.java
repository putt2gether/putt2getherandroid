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

import com.putt2gether.custome.ItemTouchHelperAdapter;

/**
 * Created by Ajay on 08/09/2016.
 */
public class GroupMemberAdapter  extends RecyclerView.Adapter<GroupMemberAdapter.SimpleViewHolder> implements Filterable,ItemTouchHelperAdapter {

    private final Context mContext;
    private List<GroupMemberBean> mData;

    private static ArrayList<GroupMemberBean> listSuggesion;
    int count = 0;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences createSharedPreferences;


    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();
    int top = 0;
    private int noPlayer;

    public void add(GroupMemberBean s,int position) {
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
        private ImageView removeIcon;
        private ImageView starIcon;
        private TextView age;
        private String radioText;

        public SimpleViewHolder(View view) {
            super(view);



            name = (TextView) view.findViewById(R.id.suggestion_row_name);
            image = (ImageView)view.findViewById(R.id.suggestions_row_image);

            addIcon = (TextView) view.findViewById(R.id.suggestion_row_addBTN);

            starIcon = (ImageView)view.findViewById(R.id.suggestion_row_star);
            age = (TextView)view.findViewById(R.id.suggestion_row_age);
        }
    }

    public GroupMemberAdapter(Context context,ArrayList<GroupMemberBean> data) {
        mContext = context;
        if (data != null) {
            mData = data;
            this.listSuggesion = data;
            mInflater = LayoutInflater.from(context);
        }
        else mData = new ArrayList<GroupMemberBean>();
    }



    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.suggestion_list_row, parent, false);

        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, final int position) {


        mSharedPreferences = mContext.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        createSharedPreferences = mContext.getSharedPreferences("craete_event", Context.MODE_PRIVATE);



        String capsName = mData.get(position).getFull_name();
        String caps = capsName.toUpperCase();
        holder.name.setText(caps);
        // holder.budget.setText(list.get(position).getBudget());


        String imageCheck = mData.get(position).getThumb_image();
        int isImageFound = imageCheck.length();

        if (isImageFound !=0) {

            Picasso.with(mContext).load(mData.get(position).getThumb_image()).into(holder.image);
            //new DownloadImageTask(holder.image).execute(mData.get(position).getThumb_image());
        }
        // holder.image.setImageResource(list.get(position).getImage());

        holder.age.setText(mData.get(position).getSelf_handicap());

        String str = mData.get(position).getPlayed();
        int star = Integer.parseInt(str);
        if (star == 1) {
            holder.starIcon.setVisibility(View.VISIBLE);
        }


        final GroupMemberBean pojo = mData.get(position);

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
                 //   SharedPreferences pref = mContext.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                //    SharedPreferences.Editor editor = pref.edit();
                 //   final String radioText = pref.getString("radioText", null);

                    noPlayer = listSuggesion.size()+1;

                    if (top < noPlayer-1) {

                        GroupMemberBean bean = new GroupMemberBean();
                        pojo.setClicked(true);


                        //add(bean,2);
                        onItemMove(holder.getAdapterPosition(), top);
                        notifyDataSetChanged();
                        top = top + 1;

                        holder.addIcon.setText("REMOVE");

                        if(createSharedPreferences==null)
                            return;
                        SharedPreferences.Editor editor1 = createSharedPreferences.edit();
                        editor1.putString("top", String.valueOf(top));
                        editor1.commit();

                        Log.v("Clicked Position", String.valueOf(holder.getAdapterPosition()));
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

                    GroupMemberBean bean = new GroupMemberBean();
                    pojo.setClicked(false);

                    //add(bean,2);
                    int in = mData.size() - 1;
                    onItemMove(holder.getAdapterPosition(),in);
                    notifyDataSetChanged();
                    holder.addIcon.setText("ADD");
                    top = top-1;

                    Log.v("topppp", String.valueOf(top));
                    if(createSharedPreferences==null)
                        return;
                  //  SharedPreferences.Editor editor1 = createSharedPreferences.edit();
                  //  editor1.putString("top", String.valueOf(top));
                  //  editor1.commit();

                    Log.v("Clicked Position", String.valueOf(holder.getAdapterPosition()));

                }
            });


            for (int i = 0 ; i < top ; i++){


                String id1 = listSuggesion.get(i).getUser_id();
                Log.v("idddddddd"+i,id1);


                if(createSharedPreferences==null)
                    return;
                SharedPreferences.Editor editor1 = createSharedPreferences.edit();
                editor1.putString("top", String.valueOf(top));
                editor1.putString("friendID"+i, id1);
                editor1.commit();
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

            final List<GroupMemberBean> list = listSuggesion;
            int count = list.size();
            final ArrayList<GroupMemberBean> nlist = new ArrayList<GroupMemberBean>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = ""+list.get(i).getFull_name();
                if (filterableString.toLowerCase().contains(filterString)) {
                    GroupMemberBean mBookServiceModel = list.get(i);
                    nlist.add(mBookServiceModel);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData = (ArrayList<GroupMemberBean>) results.values;
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
        //notifyDataSetChanged();

    }
}