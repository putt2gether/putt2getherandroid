package com.putt2gether.adapter;

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

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.GroupsBean;
import com.putt2gether.bean.SuggestionBean;
import com.putt2gether.custome.ItemTouchHelperAdapter;
import com.putt2gether.volley_class.AppController;

/**
 * Created by Ajay on 23/06/2016.
 */
public class GroupsAdapter  extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> implements Filterable,ItemTouchHelperAdapter {

    private static ArrayList<GroupsBean> listSuggesion;
    private List<GroupsBean> list;
    private Context context;
    int topGroup = 0;
    private int noPlayer;
    SharedPreferences createSharedPreferences;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();
    public AddGroupAdapterCallback callback;

    public void add(GroupsBean s,int position) {
        position = position == -1 ? getItemCount()  : position;
        list.add(position,s);
        notifyItemInserted(position);
    }

    public void remove(int position){
        if (position < getItemCount()  ) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public GroupsAdapter(Context mContext,ArrayList<GroupsBean> list) {
        this.context = mContext;
        this.list = list;
        this.listSuggesion = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.groups_list_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(final GroupsAdapter.ViewHolder holder, final int position) {

        createSharedPreferences = context.getSharedPreferences("craete_event", Context.MODE_PRIVATE);

        String capsName = list.get(position).getName();
        String caps = capsName.toUpperCase();

        holder.name.setText(caps);
        // holder.budget.setText(list.get(position).getBudget());

        String imageCheck = list.get(position).getImage();



        if (imageCheck != null && imageCheck.length()>10) {
            Picasso.with(context).load(list.get(position).getImage()).into(holder.image);
           // new DownloadImageTask(holder.image).execute(list.get(position).getImage());
        }else {
            holder.image.setImageResource(R.drawable.vishal);
        }


        final GroupsBean pojo = list.get(position);

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
                    SharedPreferences pref = context.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    final String radioText = pref.getString("radioText", null);
                    if (radioText.equalsIgnoreCase("4+")){
                        noPlayer = listSuggesion.size();
                    }
                    else {
                        noPlayer = Integer.parseInt(radioText);
                    }


                    if (topGroup < noPlayer) {

                        GroupsBean bean = new GroupsBean();
                        pojo.setClicked(true);


                        String iddd = list.get(position).getId();
                        if(callback != null) {
                            callback.addPressed(Integer.parseInt(iddd));
                        }

                        //add(bean,2);
                        onItemMove(position, topGroup);
                        notifyDataSetChanged();
                        topGroup = topGroup + 1;

                        holder.addIcon.setText("REMOVE");

                        if(createSharedPreferences==null)
                            return;
                        SharedPreferences.Editor editor1 = createSharedPreferences.edit();
                        editor1.putString("topGroup", String.valueOf(topGroup));
                        editor1.commit();


                    }else {


                        final Dialog dialog = new Dialog(context);

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

                    String iddd = list.get(position).getId();
                    if(callback != null) {
                        callback.addPressed(Integer.parseInt(iddd));
                    }

                    //add(bean,2);
                    int in = list.size() - 1;
                    onItemMove(position,in);
                    notifyDataSetChanged();
                    holder.addIcon.setText("ADD");
                    topGroup = topGroup-1;

                    Log.v("topppp", String.valueOf(topGroup));
                    if(createSharedPreferences==null)
                        return;
                    SharedPreferences.Editor editor1 = createSharedPreferences.edit();
                    editor1.putString("topGroup", String.valueOf(topGroup));
                    editor1.commit();


                }
            });



        }



    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(list, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
        //notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;
        private LinearLayout deal_offerLayout;
        private TextView addIcon;

        public ViewHolder(View view) {
            super(view);


            addIcon = (TextView)view.findViewById(R.id.group_row_addBTN);
            name = (TextView)view.findViewById(R.id.group_row_name);

            image = (ImageView)view.findViewById(R.id.group_row_image);

        }

    }


    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<GroupsBean> list = listSuggesion;
            int count = list.size();
            final ArrayList<GroupsBean> nlist = new ArrayList<GroupsBean>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = ""+list.get(i).getName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    GroupsBean mBookServiceModel = list.get(i);
                    nlist.add(mBookServiceModel);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list = (ArrayList<GroupsBean>) results.values;
            notifyDataSetChanged();
        }
    }

    public void setCallback(AddGroupAdapterCallback callback){

        this.callback = callback;
    }


    public interface AddGroupAdapterCallback {

        public void deletePressed(int position);
        public void addPressed(int position);
    }


}
