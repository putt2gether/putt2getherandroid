package com.putt2gether.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.ParticipantBean;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.OtherUserProfile;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 25/08/2016.
 */
public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsAdapter.ViewHolder> {

    private static ArrayList<ParticipantBean>
            listSuggesion;

    private LayoutInflater mInflater;
    private String event_id;
    private String isAdmin;

    private int flag = 0;


    private List<ParticipantBean> list;
    private Context context;
    private Activity mActivity1;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private String isGameStarted;

    public ParticipantsAdapter(Context mContext, ArrayList<ParticipantBean> list, String eventid, String admin, String isStarted) {
        this.context = mContext;
        this.list = list;
        this.listSuggesion = list;
        this.event_id = eventid;
        this.isGameStarted = isStarted;
        this.isAdmin = admin;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public ParticipantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.participant_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mActivity1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ParticipantsAdapter.ViewHolder holder, final int position) {

        String capsName = list.get(position).getFull_name();
        String caps = capsName.toUpperCase();
        holder.name.setText(caps);

        if (list.get(position).getInvitation_status().equalsIgnoreCase("pending")) {

            holder.editHandiBTN.setBackgroundResource(R.drawable.edit_handi_bg);
            GradientDrawable drawable = (GradientDrawable) holder.editHandiBTN.getBackground();
            drawable.setColor(Color.parseColor("#d2d2d2"));

            holder.pending.setText(list.get(position).getInvitation_status());
            holder.editHandi.setTextColor(Color.BLACK);
            holder.pending.setTextColor(Color.BLACK);
            holder.editLine.setBackgroundColor(Color.BLACK);
            holder.editIcon.setImageResource(R.drawable.edit_black);

        } else {

            holder.editHandiBTN.setBackgroundResource(R.drawable.edit_handi_bg);
            GradientDrawable drawable = (GradientDrawable) holder.editHandiBTN.getBackground();
            drawable.setColor(Color.parseColor("#064474"));

            holder.pending.setText(list.get(position).getInvitation_status());
            holder.editHandi.setTextColor(Color.WHITE);
            holder.pending.setTextColor(Color.WHITE);
            holder.editLine.setBackgroundColor(Color.WHITE);
            holder.editIcon.setImageResource(R.drawable.ic_edit_white);
        }

        holder.pending.setText(list.get(position).getInvitation_status());
        holder.partiTextStatus.setText(list.get(position).getInvitation_status());
        // holder.budget.setText(list.get(position).getBudget());

        String imageCheck = list.get(position).getThumb_url();
        int isImageFound = imageCheck.length();

        if (isImageFound != 0) {
            Picasso.with(context).load(list.get(position).getThumb_url()).into(holder.image);
        } else {
            holder.image.setImageResource(R.drawable.vishal);
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OtherUserProfile.class);
                intent.putExtra("profileID", list.get(position).getUserId());
                context.startActivity(intent);
              //  ((Activity) context).finish();
            }
        });


        holder.handicap.setText(list.get(position).getHandicap_value());

        holder.editHandicapValue.setText(list.get(position).getHandicap_value());

        if (isAdmin.equalsIgnoreCase("0")) {
            SharedPreferences pref = context.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            final String user_ID = pref.getString("userId", null);
            String userIDTeamp = list.get(position).getUserId();

            holder.editHandiBTN.setVisibility(View.GONE);
            holder.partiTextStatus.setVisibility(View.VISIBLE);

        } else {
            holder.editHandiBTN.setVisibility(View.VISIBLE);
            holder.partiTextStatus.setVisibility(View.GONE);
        }

        holder.editHandiBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isGameStarted.equalsIgnoreCase("2") || isGameStarted.equalsIgnoreCase("4") ||  isGameStarted.equalsIgnoreCase("8") ) {
                    Toast.makeText(context, "Event Already Started", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    holder.editHandicapValue.setText(holder.handicap.getText().toString());
                    String str = holder.handicap.getText().toString();
                    if (str != null) {
                        holder.editHandicapValue.setSelection(str.length());
                    }

                    if (flag == 0) {
                        holder.editHandiLay.setVisibility(View.VISIBLE);
                        flag = 1;
                    } else {
                        holder.editHandiLay.setVisibility(View.GONE);
                        flag = 0;
                    }


                    holder.saveHandicapBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                            final String handiValue = holder.editHandicapValue.getText().toString();
                            if (handiValue != null && handiValue.length() > 0) {
                                holder.editHandicapValue.setError(null);
                                int hand = Integer.parseInt(handiValue);
                                if (hand > 0 && hand < 30) {
                                    holder.editHandiLay.setVisibility(View.GONE);
                                    saveEditHandicapValue(handiValue, position);
                                    holder.handicap.setText(handiValue);
                                } else {
                                    holder.editHandicapValue.setError("Please select handicap between 0 to 30 ");
                                }
                            } else {
                                holder.editHandicapValue.setError("Please select handicap handicap.");

                            }
                        }
                    });

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private ImageView image;
        private TextView pending;
        private TextView handicap;
        private LinearLayout editHandiBTN;
        private TextView editHandi;

        private RelativeLayout saveHandicapBTN;
        private EditText editHandicapValue;
        private RelativeLayout editHandiLay;

        private TextView partiTextStatus;

        private ImageView editIcon;
        private View editLine;

        public ViewHolder(View view, Activity mActivity) {
            super(view);
            mActivity1 = mActivity;
            view.setOnClickListener(this);

            partiTextStatus = (TextView)view.findViewById(R.id.pend_accpt_parti_txt);

            editIcon = (ImageView) view.findViewById(R.id.editIcon);
            editLine = (View) view.findViewById(R.id.edit_line);

            name = (TextView) view.findViewById(R.id.participant_name);
            image = (ImageView) view.findViewById(R.id.participant_image);
            handicap = (TextView) view.findViewById(R.id.participant_handicap);
            pending = (TextView) view.findViewById(R.id.pend_accpt_parti);
            editHandiBTN = (LinearLayout) view.findViewById(R.id.edit_handicap);

            saveHandicapBTN = (RelativeLayout) view.findViewById(R.id.save_handicap);
            editHandicapValue = (EditText) view.findViewById(R.id.edit_handicap_value);
            editHandiLay = (RelativeLayout) view.findViewById(R.id.editHandiLay);
            editHandi = (TextView) view.findViewById(R.id.editHandi);

        }

        @Override
        public void onClick(View v) {

        }
    }


    public void saveEditHandicapValue(String handicapValue, int position) {

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = context.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("handicap_value", handicapValue);
            jsonObject.putOpt("event_id", event_id);
            jsonObject.putOpt("user_id", list.get(position).getUserId());
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Post edit handicap ", "handicap edit" + jsonObject.toString());


        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.EditHandiCAP_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                saveHandicapResponse(response);
                Log.e("edit handicap ", "handicap edit" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        // Utility.showLogError(context.getApplicationContext(),"Error in "+ "ParticipantList URL = " + PUTTAPI.PARTICIPANT_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void saveHandicapResponse(JSONObject response) {


        ArrayList<ParticipantBean> list = new ArrayList<ParticipantBean>();

        if (response != null) {


            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    String msg = jsonObject.getString("message");

                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}