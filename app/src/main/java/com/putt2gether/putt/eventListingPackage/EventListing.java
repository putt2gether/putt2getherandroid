package com.putt2gether.putt.eventListingPackage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.eventListingAdap.EventListingAdapter;
import com.putt2gether.bean.EventListBean.EventBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/2/2016.
 */
public class EventListing extends AppCompatActivity {

    private RecyclerView ddRecyclerView;
    private RecyclerView.LayoutManager ddLayoutManager;
    String date,golfcourseid;
    int GOLF_ID;

    EventListingAdapter ddAdapter1;
    ImageView back_listing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_listing);

        back_listing=(ImageView)findViewById(R.id.back_listing);

        Intent i=getIntent();
        date= i.getStringExtra("getSelectedDatesString");
        golfcourseid=i.getStringExtra("golfcourseid");

        GOLF_ID=Integer.parseInt(golfcourseid);

        ConnectionDetector connectionDetector = new ConnectionDetector(this);

        ddRecyclerView = (RecyclerView)findViewById(R.id.event_list_view);

        ddRecyclerView.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(this);
        ddRecyclerView.setLayoutManager(ddLayoutManager);

        if (connectionDetector.isConnectingToInternet()) {
            getEventListing();
        } else {
            Toast.makeText(EventListing.this, R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }

        back_listing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListing.this.finish();
            }
        });
    }


    public void getEventListing()
    {

        final ProgressDialog pDialog = new ProgressDialog(EventListing.this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JSONObject jsonObject = null;

        SharedPreferences pref =this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId",null);

        try {

            jsonObject = new JSONObject();

            jsonObject.putOpt("user_id",user_ID);
            jsonObject.putOpt("current_date",date);
            jsonObject.putOpt("golf_course_id",golfcourseid);
            jsonObject.putOpt("version","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("jkljj",jsonObject.toString());

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GET_EVENT_LISTING, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                eventlist(response);
                Log.e("Suggestion Fragment", "GetSuggestionList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(EventListing.this,"Error in "+ "GetSuggestionList URL = " + PUTTAPI.GET_EVENT_LISTING);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    public void eventlist(JSONObject response)
    {

        ArrayList<EventBean> list = new ArrayList<EventBean>();

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){
                    JSONArray jsonArray = jsonObject.getJSONArray("data");



                    for (int i = 0 ; i < jsonArray.length(); i++){
                        EventBean listBean = new EventBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        listBean.setEvent_id(jsonObject1.getString("event_id"));
                        listBean.setEvent_name(jsonObject1.getString("event_name"));
                        listBean.setEvent_display_number(jsonObject1.getString("event_display_number"));
                        listBean.setStart_date(jsonObject1.getString("start_date"));
                        listBean.setSatrt_time(jsonObject1.getString("event_start_time"));
                        listBean.setGolf_name(jsonObject1.getString("golf_course_name"));
                        listBean.setGolf_course_id(jsonObject1.getString("golf_course_id"));
                        listBean.setAdmin_id(jsonObject1.getString("admin_id"));
                        listBean.setAdmin_name(jsonObject1.getString("admin"));

                        list.add(listBean);
                    }
                }else{
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(EventListing.this,errorMessage,Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ddAdapter1 = new EventListingAdapter(EventListing.this,list);
        ddRecyclerView.setAdapter(ddAdapter1);

    }

}
