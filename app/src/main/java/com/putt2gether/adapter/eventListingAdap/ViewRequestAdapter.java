package com.putt2gether.adapter.eventListingAdap;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.EventListBean.ViewRequestBean;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.OtherUserProfile;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 18/10/2016.
 */
public class ViewRequestAdapter extends RecyclerView.Adapter<ViewRequestAdapter.ViewHolder>  {

    private List<ViewRequestBean> list;
    private Context context;
    // private ItemFilter mFilter = new ItemFilter();
    //private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ViewRequestAdapter(Context mContext,List<ViewRequestBean> list) {
        this.context = mContext;
        this.list = list;
    }

    public void remove(int position){
        if (position < getItemCount()  ) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public ViewRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.accept_decline_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(final ViewRequestAdapter.ViewHolder holder, final int position) {


        String user_name = list.get(position).getUser();
        holder.userName.setText(user_name);
        String str2 = list.get(position).getThumb_url();
        if (str2!=null) {
            Picasso.with(context).load(str2).into(holder.requestImage);
        }

        holder.requestImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OtherUserProfile.class);
                intent.putExtra("profileID",list.get(position).getUser_id());
                context.startActivity(intent);
              //  ((Activity)context).finish();
            }
        });

        String handicap = list.get(position).getHandicap();
        if ( handicap!=null){
            holder.handicapValue.setText(handicap);
        }


        String isPending = list.get(position).getIs_accepted();
        if (isPending.equalsIgnoreCase("Pending")){
            holder.acceptText.setText("ACCEPT");
        }else {
            holder.acceptText.setText("ACCEPTED");
        }




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
                   // holder.acceptBTN.setBackgroundResource(R.drawable.accepted_btn_invite);
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
        String user_id = list.get(position).getUser_id();

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("admin_id", user_ID);
            jsonObject.putOpt("player_id", user_id);
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("event_id",eventId);
            jsonObject.putOpt("status","1");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("kjufyhueiyhoqi",eventId);
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ACCEPT_REJECT_EVENT_URL, jsonObject, new Response.Listener<JSONObject>() {
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
        String user_id = list.get(position).getUser_id();

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("admin_id", user_ID);
            jsonObject.putOpt("player_id", user_id);
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("event_id",eventId);
            jsonObject.putOpt("status","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ACCEPT_REJECT_EVENT_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getDeclineResponse(response);
                remove(position);
                Log.e("ACCEPT REJECT Fragment", "ACCEPT REJECT Fragment" + response.toString());
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

    private void getDeclineResponse(JSONObject response){

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){
                    JSONObject jsonObjectD = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = jsonObjectD.getJSONArray("Invitation");
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



    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView userName;
        private LinearLayout acceptBTN,declineBTN;
        TextView acceptText,handicapValue;
        ImageView requestImage;

        public ViewHolder(View view) {
            super(view);
            //view.setOnClickListener(this);

            requestImage = (ImageView)view.findViewById(R.id.request_image);
            userName = (TextView)view.findViewById(R.id.request_name);

            acceptBTN = (LinearLayout)view.findViewById(R.id.accept_request);
            declineBTN = (LinearLayout)view.findViewById(R.id.decline_request);

            handicapValue = (TextView)view.findViewById(R.id.request_handicap);

            acceptText = (TextView)view.findViewById(R.id.request_text);



        }

        /*@Override
        public void onClick(View v) {

            Intent intent = new Intent(context, InviteEventDetail.class);
            ViewRequestBean bean = list.get(getAdapterPosition());
            String eventID = bean.getEvent_id();
            intent.putExtra("eventID",eventID);
            Log.v("EventIDDDDD",eventID);
            context.startActivity(intent);

        }*/

    }
}