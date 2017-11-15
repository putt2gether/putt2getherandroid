package com.putt2gether.putt.participantspack;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.putt2gether.adapter.NoInternetConnectionAdapter;
import com.putt2gether.adapter.eventListingAdap.ViewRequestAdapter;
import com.putt2gether.bean.EventListBean.ViewRequestBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/16/2016.
 */
public class AcceptParticipants extends AppCompatActivity {

    ImageView back_participantnew;
    private RecyclerView requestViewList;
    private ViewRequestAdapter ddAdapter1;
    private String event_id ;
    private RecyclerView.LayoutManager ddLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant_accept_reject);

        event_id = getIntent().getStringExtra("eventID");

        back_participantnew=(ImageView)findViewById(R.id.back_participantnew);
        requestViewList = (RecyclerView)findViewById(R.id.view_request_list);


        requestViewList.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(this);
        requestViewList.setLayoutManager(ddLayoutManager);


        ConnectionDetector connectionDetector = new ConnectionDetector(this);
        if (connectionDetector.isConnectingToInternet()){

            viewRequestList();

        }


        back_participantnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcceptParticipants.this.finish();
            }
        });
    }
    public void viewRequestList(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId",null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("event_id",event_id);
            jsonObject.putOpt("admin_id",user_ID);
            jsonObject.putOpt("version","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.VIEW_REQUEST_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getInvitationList(response);
                Log.e("View Request", "viewRequestList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this,"Error in "+ "viewRequestList URL = " + PUTTAPI.VIEW_REQUEST_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getInvitationList(JSONObject response){

        ArrayList<ViewRequestBean> list = new ArrayList<ViewRequestBean>();

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){
                    JSONObject jsonObjectD = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = jsonObjectD.getJSONArray("Request");

                    for (int i = 0 ; i < jsonArray.length(); i++){
                        ViewRequestBean listBean = new ViewRequestBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        listBean.setIs_accepted(jsonObject1.getString("is_accepted"));
                        listBean.setEvent_id(jsonObject1.getString("event_id"));
                        listBean.setEvent_name(jsonObject1.getString("event_name"));
                        listBean.setUser_id(jsonObject1.getString("user_id"));
                        listBean.setUser(jsonObject1.getString("user"));
                        listBean.setPhoto_url(jsonObject1.getString("photo_url"));
                        listBean.setIs_started(jsonObject1.getString("is_started"));

                        //listBean.setHandicap(jsonObject1.getString("handicap"));
                        listBean.setThumb_url(jsonObject1.getString("thumb_url"));


                        list.add(listBean);
                    }
                }else{
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (list.size()>0) {
            ddAdapter1 = new ViewRequestAdapter(this, list);
            requestViewList.setAdapter(ddAdapter1);
        }else {

            String txt = "You don't have any request.";
            NoInternetConnectionAdapter adapterNo = new NoInternetConnectionAdapter(txt);
            requestViewList.setAdapter(adapterNo);
        }

    }
}
