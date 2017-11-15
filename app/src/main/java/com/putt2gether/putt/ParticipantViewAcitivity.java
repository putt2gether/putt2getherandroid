package com.putt2gether.putt;

import android.app.Activity;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.ParticipantsAdapter;
import com.putt2gether.bean.ParticipantBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.addparticipant.AddParticipantActivity;
import com.putt2gether.putt.participantspack.AcceptParticipants;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 25/08/2016.
 */
public class ParticipantViewAcitivity extends AppCompatActivity {


    ArrayList<ParticipantBean> list = new ArrayList<ParticipantBean>();
    private ParticipantsAdapter ddAdapter;
    private RecyclerView ddRecyclerView;
    private RecyclerView.LayoutManager ddLayoutManager;
    private ImageView backBTN;
    private RelativeLayout addParticipant;
    private RelativeLayout viewRequest;
    private String eventID;
    private String isAdmin;
    private String isGameStarted;
    private LinearLayout bottomLayout;
    private String  noOfPlayer;
    private int totalPlayer;
    private View line;
    RelativeLayout parent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_participant_activity);

        line = (View)findViewById(R.id.preview_line);
        addParticipant = (RelativeLayout)findViewById(R.id.add_partici_btn);
        addParticipant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddParticipantActivity.class);
                intent.putExtra("event_id",eventID);
                startActivity(intent);
            }
        });

        viewRequest = (RelativeLayout)findViewById(R.id.view_request_btn);
        bottomLayout = (LinearLayout)findViewById(R.id.invite_from_create);
        parent = (RelativeLayout)findViewById(R.id.parent_part);
        setupUI(parent);

        isGameStarted = getIntent().getStringExtra("isGameStarted");
        eventID = getIntent().getStringExtra("eventID");
        isAdmin = getIntent().getStringExtra("is_admin");
        noOfPlayer = getIntent().getStringExtra("noOfPlayer");

        Log.v("noOfPlayer",noOfPlayer);

        if (noOfPlayer.equalsIgnoreCase("4+")){
            totalPlayer = Integer.parseInt("1000");
        }else {
            totalPlayer = Integer.parseInt(noOfPlayer);
        }

        ConnectionDetector connectionDetector = new ConnectionDetector(this);
        if (connectionDetector.isConnectingToInternet()){
            if (eventID!=null){
                getParticipantList();
            }
        }

        if (isAdmin.equalsIgnoreCase("0")){
            bottomLayout.setVisibility(View.GONE);
        }



        if (isGameStarted.equalsIgnoreCase("2")||isGameStarted.equalsIgnoreCase("4")){
            bottomLayout.setVisibility(View.GONE);
        }

        line = (View)findViewById(R.id.preview_line);

        viewRequest = (RelativeLayout)findViewById(R.id.view_request_btn);

        viewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AcceptParticipants.class);
                intent.putExtra("eventID",eventID);
                intent.putExtra("is_admin",isAdmin);
                startActivity(intent);
            }
        });

        ddRecyclerView  = (RecyclerView)findViewById(R.id.participant_recycler);
        backBTN = (ImageView)findViewById(R.id.back_participant_view);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ddRecyclerView.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(this);
        ddRecyclerView.setLayoutManager(ddLayoutManager);

        ddRecyclerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                return false;
            }
        });

    }





    @Override
    protected void onRestart() {

        getParticipantList();
        super.onRestart();
    }

    public void getParticipantList(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId",null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("event_id",eventID);
            jsonObject.putOpt("version","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("ViewParticipant Post",jsonObject.toString());

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.PARTICIPANT_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getInvitationList(response);
                Log.e("participantList ", "invitelist" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this,"Error in "+ "ParticipantList URL = " + PUTTAPI.PARTICIPANT_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getInvitationList(JSONObject response){


        ArrayList<ParticipantBean> list = new ArrayList<ParticipantBean>();

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0 ; i < jsonArray.length(); i++){
                        ParticipantBean listBean = new ParticipantBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        listBean.setUserId(jsonObject1.getString("userId"));
                        listBean.setFull_name(jsonObject1.getString("full_name"));
                        listBean.setUser_name(jsonObject1.getString("user_name"));
                        listBean.setRegistered_mobile_number(jsonObject1.getString("registered_mobile_number"));
                        listBean.setPhoto_url(jsonObject1.getString("photo_url"));
                        listBean.setInvitation_status(jsonObject1.getString("invitation_status"));
                        listBean.setThumb_url(jsonObject1.getString("thumb_url"));

                        //listBean.setHandicap(jsonObject1.getString("handicap"));
                        listBean.setAdd_player_type(jsonObject1.getString("add_player_type"));
                        listBean.setHandicap_value(jsonObject1.getString("handicap_value"));


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

        if (list.size()<totalPlayer){
            addParticipant.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }else {
            addParticipant.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }

        if (isGameStarted.equalsIgnoreCase("1") && list.size()<totalPlayer){
            addParticipant.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }else {
            addParticipant.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }


        parent.setVisibility(View.VISIBLE);


        ddAdapter = new ParticipantsAdapter(this,list,eventID,isAdmin,isGameStarted);
        ddRecyclerView.setAdapter(ddAdapter);

    }


    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(ParticipantViewAcitivity.this);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView =activity.getCurrentFocus();
    /*
     * If no view is focused, an NPE will be thrown
     *
     * Maxim Dmitriev
     */
        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
