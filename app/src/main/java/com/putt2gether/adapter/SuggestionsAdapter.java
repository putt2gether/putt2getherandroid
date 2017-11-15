package com.putt2gether.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.SuggestionBean;
import com.putt2gether.putt.DefineTeamActivity;
import com.putt2gether.putt.InvitePlayerActivity;
import com.putt2gether.putt.OtherUserProfile;
import com.putt2gether.volley_class.AppController;

/**
 * Created by Ajay on 23/06/2016.
 */
public class SuggestionsAdapter  extends RecyclerView.Adapter<SuggestionsAdapter.ViewHolder> implements Filterable {

    private static ArrayList<SuggestionBean> listSuggesion;

    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();

    private List<SuggestionBean> list;
    private Context context;
    private Activity mActivity1;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    public SuggestionsAdapter(Context mContext,ArrayList<SuggestionBean> list) {
        this.context = mContext;
        this.list = list;
        this.listSuggesion = list;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public SuggestionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_list_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,mActivity1);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(final SuggestionsAdapter.ViewHolder holder, final int position) {

        String capsName = list.get(position).getFull_name();
        String caps = capsName.toUpperCase();
        holder.name.setText(caps);
        // holder.budget.setText(list.get(position).getBudget());

        String imageCheck = list.get(position).getThumb_image();
        int isImageFound = imageCheck.length();

        if (isImageFound !=0) {

            Picasso.with(context).load(list.get(position).getThumb_image()).into(holder.image);

        }else {
            holder.image.setImageResource(R.drawable.vishal);
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OtherUserProfile.class);
                intent.putExtra("profileID",list.get(position).getUser_id());
                context.startActivity(intent);
             //   ((Activity)context).finish();
            }
        });

        holder.age.setText(list.get(position).getSelf_handicap());

        String str = list.get(position).getPlayed();
        int star = Integer.parseInt(str);
        if (star == 1) {
            holder.starIcon.setVisibility(View.VISIBLE);
        }


        holder.addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = context.getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                final String activityType = pref.getString("activity_type",null);
                Intent intent;


                if (activityType != null && activityType.equalsIgnoreCase("invite")){

                     intent = new Intent(context,InvitePlayerActivity.class);
                }else {

                     intent = new Intent(context,DefineTeamActivity.class);
                }

                //Intent intent = new Intent();

                SuggestionBean bean = list.get(position);
                String user_id = bean.getUser_id();
                String name = bean.getFull_name();
                String handicap = bean.getSelf_handicap();
                String image = bean.getProfile_image();
                holder.addIcon.setText("REMOVE");



             //   intent.putExtra("user_id",user_id);
             //   intent.putExtra("name",name);
               // intent.putExtra("handicap",handicap);
               // intent.putExtra("image",image);
                //context.startActivity(intent);



                final String tag = pref.getString("tag",null);
                if (tag!=null){

                    if (tag.equalsIgnoreCase("tag1")){
                        editor.putString("user_id1",user_id);
                        editor.putString("name1",name);
                        editor.putString("handicap1",handicap);

                        if (image!=null && image.length()>10) {
                            editor.putString("image1", image);
                        }else {
                            editor.putString("image1", null);
                        }
                        editor.putString("text1","REMOVE");
                        editor.commit();
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    } if (tag.equalsIgnoreCase("tag2")){
                        editor.putString("user_id2",user_id);
                        editor.putString("name2",name);
                        editor.putString("handicap2",handicap);
                        editor.putString("text2","REMOVE");
                        if (image!=null && image.length()>10) {
                            editor.putString("image2", image);
                        }else {
                            editor.putString("image2", null);
                        }
                        editor.commit();
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }if (tag.equalsIgnoreCase("tag3")) {
                        editor.putString("user_id3", user_id);
                        editor.putString("name3", name);
                        editor.putString("handicap3", handicap);
                        if (image!=null && image.length()>10) {
                            editor.putString("image3", image);
                        }else {
                            editor.putString("image3", null);
                        }
                        editor.putString("text3","REMOVE");
                        editor.commit();
                        context.startActivity(intent);
                        ((Activity)context).finish();

                    }

                }else {
                    Log.e("Team Error ","activity result found null tag name");
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private ImageView image;
        private TextView addIcon;
        private ImageView starIcon;
        private TextView age;

        public ViewHolder(View view,Activity mActivity) {
            super(view);
            mActivity1=mActivity;
            view.setOnClickListener(this);

            name = (TextView)view.findViewById(R.id.suggestion_row_name);

            image = (ImageView)view.findViewById(R.id.suggestions_row_image);

            addIcon = (TextView)view.findViewById(R.id.suggestion_row_addBTN);
            starIcon = (ImageView)view.findViewById(R.id.suggestion_row_star);
            age = (TextView)view.findViewById(R.id.suggestion_row_age);

        }


        @Override
        public void onClick(View v) {


        }
    }



    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<SuggestionBean> list = listSuggesion;
            int count = list.size();
            final ArrayList<SuggestionBean> nlist = new ArrayList<SuggestionBean>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = ""+list.get(i).getFull_name();
                if (filterableString.toLowerCase().contains(filterString)) {
                    SuggestionBean mBookServiceModel = list.get(i);
                    nlist.add(mBookServiceModel);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list = (ArrayList<SuggestionBean>) results.values;
            notifyDataSetChanged();
        }
    }
}
