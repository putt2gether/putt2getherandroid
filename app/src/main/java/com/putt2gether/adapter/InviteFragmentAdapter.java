package com.putt2gether.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.InviteUserBean;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.EditEventActivity;
import com.putt2gether.putt.InviteEventDetail;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 04/08/2016.
 */
public class InviteFragmentAdapter extends RecyclerView.Adapter<InviteFragmentAdapter.ViewHolder>  {

    private List<InviteUserBean> list;
    private Context context;

    // private ItemFilter mFilter = new ItemFilter();
    //private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public InviteFragmentAdapter(Context mContext,List<InviteUserBean> list1) {
        this.context = mContext;
        this.list = list1;
    }

    public void remove(int position){
        if (position < getItemCount()  ) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public InviteFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_list_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(final InviteFragmentAdapter.ViewHolder holder, final int position) {

        String str1 = list.get(position).getEvent_name();
        String capsGolf = str1.toUpperCase();
        holder.userName.setText(capsGolf);
        String str2 = list.get(position).getAdmin();
        String capsCity = str2.toUpperCase();
        holder.adminName.setText(capsCity);
        holder.eventTime.setText(list.get(position).getEvent_start_time());
        holder.eventDate.setText(list.get(position).getStart_date());
        holder.venue.setText(list.get(position).getGolf_course_name());

        final String banner_url = list.get(position).getBanner_href();
        String banner_img =list.get(position).getBanner_image();
        if (banner_img!=null && banner_img.length()>10){
            holder.bannerLayout.setVisibility(View.VISIBLE);
            Picasso.with(context).load(banner_img).into(holder.bannerImage);
        }else {
            holder.bannerLayout.setVisibility(View.GONE);
        }

        holder.bannerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (banner_url!=null && banner_url.length()>10) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(banner_url));
                        context.startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, " Please install a web browser",  Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(context, " URL not found.",  Toast.LENGTH_LONG).show();
                }

            }
        });

        String isPending = list.get(position).getIs_accepted();

        if (isPending.equalsIgnoreCase("Pending")){


            holder.acceptBTN.setBackgroundResource(R.drawable.accept_btn_invite);
            holder.acceptText.setText("ACCEPT");
        }else {

            holder.acceptBTN.setBackgroundResource(R.drawable.accepted_btn_invite);
            holder.acceptText.setText("ACCEPTED");

        }
        String editDetails = list.get(position).getIs_edit();
        if (editDetails.equalsIgnoreCase("Edit")){

            holder.editBTN.setBackgroundResource(R.drawable.edit_btn_invite);
            holder.editText.setText("EDIT");

            holder.editBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditEventActivity.class);
                    InviteUserBean bean = list.get(position);
                    String eventID = bean.getEvent_id();
                    intent.putExtra("eventID",eventID);
                    context.startActivity(intent);
                }
            });

        }else {
            holder.editBTN.setBackgroundResource(R.drawable.detail_btn_invite);
            holder.editText.setText("DETAILS");
        }

        holder.acceptBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.acceptBTN.setBackgroundResource(R.drawable.accepted_btn_invite);
                holder.acceptText.setText("ACCEPTED");

            }
        });




            holder.declineBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (holder.acceptText.getText().toString().equalsIgnoreCase("Accept")) {
                    TextView yesBTN, noBTN;

                    final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);

                    dialog.setCanceledOnTouchOutside(true);
                    Window window = dialog.getWindow();
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    WindowManager.LayoutParams wlp = window.getAttributes();
                    wlp.gravity = Gravity.BOTTOM;
                    wlp.dimAmount = 0.7f;
                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    window.setAttributes(wlp);
                    // Include dialog.xml file
                    dialog.setContentView(R.layout.decline_popup);

                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    yesBTN = (TextView) dialog.findViewById(R.id.yes_decline);
                    noBTN = (TextView) dialog.findViewById(R.id.no_decline);
                    yesBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            declineInvite(position);
                            Toast.makeText(context, "You have decline invitation", Toast.LENGTH_LONG).show();
                            dialog.cancel();

                        }
                    });
                    noBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });


                    dialog.show();


                    }else {
                        Toast.makeText(context,"You can't decline accepted event",Toast.LENGTH_LONG).show();
                    }

                }
            });


        holder.acceptBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.acceptText.getText().toString().equalsIgnoreCase("ACCEPT")){
                    Log.v("You Clicked","You Clicked On Accept BTN");
                    holder.acceptBTN.setBackgroundResource(R.drawable.accepted_btn_invite);
                    holder.acceptText.setText("ACCEPTED");
                    acceptInvite(position);
                }
            }
        });



    }

    public void acceptInvite(int pos) {

        int position = pos;
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = context.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);
        String eventId = list.get(position).getEvent_id();

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("event_id",eventId);
            jsonObject.putOpt("status","1");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ACCEPT_REJECT_INVITE_API, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getAcceptResponse(response);
                Log.e("ACCEPT REJECT Fragment", "CCEPT REJECT Fragment" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getAcceptResponse(JSONObject response){

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){
                    String message = jsonObject.getString("message");
                    Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                }else{
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void declineInvite(int pos) {

        final int position = pos;
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = context.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);
        String eventId = list.get(position).getEvent_id();

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("event_id",eventId);
            jsonObject.putOpt("status","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ACCEPT_REJECT_INVITE_API, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getDeclineResponse(response);
                remove(position);
                Log.e("ACCEPT REJECT Fragment", "CCEPT REJECT Fragment" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getDeclineResponse(JSONObject response){

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){
                    String message = jsonObject.getString("message");
                    Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                }else{
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView userName;
        private TextView adminName;
        private RelativeLayout acceptBTN,declineBTN,editBTN;
        TextView editText,declineText,acceptText;
        TextView eventTime,eventDate,venue;
        RelativeLayout bannerLayout;
        ImageView bannerImage;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            bannerLayout = (RelativeLayout)view.findViewById(R.id.banner_invite_list_lay);
            bannerImage = (ImageView)view.findViewById(R.id.banner_invite_list_logo);

            userName = (TextView)view.findViewById(R.id.invite_user_name);
            adminName = (TextView)view.findViewById(R.id.invite_name);
            eventDate = (TextView)view.findViewById(R.id.invite_date);
            eventTime = (TextView)view.findViewById(R.id.invite_time);
            venue = (TextView)view.findViewById(R.id.invite_venue);

            acceptBTN = (RelativeLayout)view.findViewById(R.id.invite_accept_btn);
            declineBTN = (RelativeLayout)view.findViewById(R.id.invite_decline_btn);
            editBTN = (RelativeLayout)view.findViewById(R.id.invite_edit_btn);
            editText = (TextView)view.findViewById(R.id.invite_edit_text);
            declineText = (TextView)view.findViewById(R.id.invite_decline_text);
            acceptText = (TextView)view.findViewById(R.id.invite_accept_text);

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context, InviteEventDetail.class);
            InviteUserBean bean = list.get(getAdapterPosition());
            String eventID = bean.getEvent_id();
            intent.putExtra("eventID",eventID);
            Log.v("EventIDDDDD",eventID);
            context.startActivity(intent);

        }

    }
}