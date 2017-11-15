package com.putt2gether.fragments.notification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.InviteEventDetail;
import com.putt2gether.putt.addScore.AddScoreNew;
import com.putt2gether.putt.participantspack.AcceptParticipants;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/8/2016.
 */
public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.MyViewHolder> {

    ArrayList<NotilistBean> scorehistry = new ArrayList<NotilistBean>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView scoretext, ranktext;
        ImageView imagetext;
        LinearLayout notification_row;


        public MyViewHolder(View view) {
            super(view);
            scoretext = (TextView) view.findViewById(R.id.notieventdetail);
            ranktext = (TextView) view.findViewById(R.id.notieventtime);
            notification_row = (LinearLayout) view.findViewById(R.id.notification_row);
            imagetext = (ImageView) view.findViewById(R.id.noticircle);
        }
    }


    public NotiAdapter(Context ctxt, ArrayList<NotilistBean> scorehistry1) {

        this.context = ctxt;
        this.scorehistry = scorehistry1;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noti_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String para = scorehistry.get(position).getNotiname();
        String yourString;
        yourString = para.replace("<strong>", "<strong><b>");
        yourString = yourString.replace("</strong>", "</b></strong>");


        String newString = Html.fromHtml(yourString).toString();


        final String isReadType = scorehistry.get(position).getNotiread_status();

        final String push_type = scorehistry.get(position).getPushType();
        if (push_type.equalsIgnoreCase("0")){

            holder.notification_row.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.scoretext.setTextColor(Color.parseColor("#D3D3D3"));
            holder.ranktext.setTextColor(Color.parseColor("#D3D3D3"));

        }else {

            if (!push_type.equalsIgnoreCase("0") && isReadType.equalsIgnoreCase("1")) {

                holder.notification_row.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.scoretext.setTextColor(Color.parseColor("#000000"));
                holder.ranktext.setTextColor(Color.parseColor("#000000"));

            } else if (!push_type.equalsIgnoreCase("0") && isReadType.equalsIgnoreCase("0")) {
                //  holder.notification_row.setAlpha(0.3F);
                holder.notification_row.setBackgroundColor(Color.parseColor("#cfe9f9"));

                holder.scoretext.setTextColor(Color.parseColor("#000000"));
                holder.ranktext.setTextColor(Color.parseColor("#000000"));
            }

        }

        holder.scoretext.setText(newString.toUpperCase());
        holder.ranktext.setText(scorehistry.get(position).getNotitime().toUpperCase());


        holder.notification_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a1 = scorehistry.get(position).getNotieventid();
                String a2 = scorehistry.get(position).getAdmin_id();
                String a3 = scorehistry.get(position).getPushType();
                Log.v("kmdk;msm",position+"-"+a1+"-"+a2+"-"+a3);

                if (!push_type.equalsIgnoreCase("0") && isReadType.equalsIgnoreCase("0")) {

                    holder.notification_row.setBackgroundColor(Color.parseColor("#ffffff"));
                    String notificationId = scorehistry.get(position).getNotificationID();
                    String eventID = scorehistry.get(position).getNotieventid();
                    String adminID = scorehistry.get(position).getAdmin_id();
                    String pushType = scorehistry.get(position).getPushType();

                    readNotification(notificationId, eventID, adminID, pushType);
                } else  if (!push_type.equalsIgnoreCase("0") && isReadType.equalsIgnoreCase("1")) {

                    holder.notification_row.setBackgroundColor(Color.parseColor("#ffffff"));
                    String eventID = scorehistry.get(position).getNotieventid();
                    String adminID = scorehistry.get(position).getAdmin_id();
                    String pushType = scorehistry.get(position).getPushType();

                    redirectMethod(pushType,eventID,adminID);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return scorehistry.size();
    }


    private void readNotification(String notificationID, final String eventID, final String adminID, final String pushType) {


        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;
        SharedPreferences pref = context.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        String user_ID = pref.getString("userId", null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("notification_id", notificationID);
            jsonObject.putOpt("is_read","1");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.READ_NOTIFICATION_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("Read Notification", "OnResponse =" + response.toString());
                getReadNotificationResponse(response,eventID,adminID,pushType);
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something, wrong please try again", Toast.LENGTH_LONG).show();
                pDialog.cancel();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Read Notification List", "Url= " + PUTTAPI.READ_NOTIFICATION_URL + " PostObject = " + jsonObject.toString());

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    public void getReadNotificationResponse(JSONObject response,String eventID,String adminID,String pushType) {


        if (response != null) {
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("message");

                Log.v("pushType",pushType+"");

                redirectMethod(pushType,eventID,adminID);

                Log.v("Msg",status);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    public void redirectMethod(String pushType,String eventID,String adminID){

        if (pushType.equalsIgnoreCase("3")){

            Intent intent = new Intent(context, InviteEventDetail.class);
            intent.putExtra("eventID",eventID);
            intent.putExtra("adminID",adminID);
            context.startActivity(intent);

        }else if (pushType.equalsIgnoreCase("5")){

            Intent intent = new Intent(context, AddScoreNew.class);
            intent.putExtra("eventID",eventID);
            intent.putExtra("adminID",adminID);
            context.startActivity(intent);

        }else if (pushType.equalsIgnoreCase("7")){

            Intent intent = new Intent(context, AcceptParticipants.class);
            intent.putExtra("eventID",eventID);
            intent.putExtra("adminID",adminID);
            context.startActivity(intent);

        }else if (pushType.equalsIgnoreCase("2")){

            Intent intent = new Intent(context, InviteEventDetail.class);
            intent.putExtra("eventID",eventID);
            intent.putExtra("adminID",adminID);
            context.startActivity(intent);

        }else if (pushType.equalsIgnoreCase("8")){

            Intent intent = new Intent(context, InviteEventDetail.class);
            intent.putExtra("eventID",eventID);
            intent.putExtra("adminID",adminID);
            context.startActivity(intent);

        }else if (pushType.equalsIgnoreCase("9")){
            Intent intent = new Intent(context, InviteEventDetail.class);
            intent.putExtra("eventID",eventID);
            intent.putExtra("adminID",adminID);
            context.startActivity(intent);
        }else if (pushType.equalsIgnoreCase("0")){

            Toast.makeText(context,"This event has been expired.",Toast.LENGTH_SHORT).show();
        }


    }

}
