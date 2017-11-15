package com.putt2gether.fragments.addscorefrags.scoreTable;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.addScore.AddScoreNew;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 19/10/2016.
 */
public class FourTwoZero  extends Fragment {

    ImageView finishdialogfrag;
    RecyclerView recyclerviewScoretabl;
    private RecyclerView.LayoutManager ddLayoutManager;

    ArrayList<String> hole_array=new ArrayList<String>();
    ArrayList<String> player_id_array=new ArrayList<String>();

    ArrayList<String> score1_value_array=new ArrayList<String>();
    ArrayList<String> score2_value_array=new ArrayList<String>();
    ArrayList<String> score3_value_array=new ArrayList<String>();
    ArrayList<String> total_array=new ArrayList<String>();

    String eventID;
    String playerID;
    String totalScore = "";
    String totalScoreBack = "";
    TextView player1,player2,player3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.four_two_zero_layout, container, false);

        finishdialogfrag=(ImageView)view.findViewById(R.id.finishdialogfrag);
        recyclerviewScoretabl=(RecyclerView)view.findViewById(R.id.recyclerview420);

        recyclerviewScoretabl.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(getActivity());
        recyclerviewScoretabl.setLayoutManager(ddLayoutManager);


        finishdialogfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(FourTwoZero.this).commit();
            }
        });


        player1 = (TextView)view.findViewById(R.id.player1_420);
        player2 = (TextView)view.findViewById(R.id.player2_420);
        player3 = (TextView)view.findViewById(R.id.player3_420);
        eventID=((AddScoreNew) getActivity()).geteventID();
        playerID= ((AddScoreNew) getActivity()).playerID(0);

        Log.v("Disp","eventID"+eventID+"playerID"+playerID);

        getscorecardtable(playerID,eventID);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void getscorecardtable(String playerid, String Event_Id) {
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;
        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        final String admin_ID = pref.getString("userId", null);
        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("admin_id", admin_ID);
            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.NEW_SCORE_CARD_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                newScoreCardResponse(response);
                Log.e("GETNEWSCORE", "GETNEWSCORE" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(), "Error in " + "GETNEWSCORE URL = " + PUTTAPI.NEW_SCORE_CARD_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }


    public void newScoreCardResponse(JSONObject response) {
        try {
            JSONObject output = response.getJSONObject("output");
            String status = output.getString("status");
            if (status.equalsIgnoreCase("1")) {

                JSONArray data = output.getJSONArray("data");

                for (int i = 0 ; i < data.length(); i++){
                    JSONObject elem = data.getJSONObject(i);
                    String hhh = String.valueOf(i+1);
                    JSONArray hole_no = elem.getJSONArray(hhh);
                    for (int j = 0 ; j < hole_no.length(); j++){

                        String player_id = hole_no.getJSONObject(j).getString("player_id");
                        if (j==0) {
                            String score_value1 = hole_no.getJSONObject(j).getString("score_value");
                            score1_value_array.add(score_value1);
                            String hole_number = hole_no.getJSONObject(j).getString("hole_number");
                            player1.setText(hole_no.getJSONObject(j).getString("full_name").toUpperCase());
                        }else if (j==1) {
                            String score_value2 = hole_no.getJSONObject(j).getString("score_value");
                            score2_value_array.add(score_value2);
                            String hole_number = hole_no.getJSONObject(j).getString("hole_number");
                            player2.setText(hole_no.getJSONObject(j).getString("full_name").toUpperCase());
                        }else if (j==2){
                            String score_value3= hole_no.getJSONObject(j).getString("score_value");
                            score3_value_array.add(score_value3);
                            String hole_number = hole_no.getJSONObject(j).getString("hole_number");
                            hole_array.add(hole_number);
                            player3.setText(hole_no.getJSONObject(j).getString("full_name").toUpperCase());
                        }

                        player_id_array.add(player_id);




                    }

                }
                JSONArray total = output.getJSONArray("total");
                for (int t = 0 ;t <total.length();t++){
                    String totalScore = total.getJSONObject(t).getString("total_score");
                    total_array.add(totalScore);
                }

            }else {

            }

            FourTwoZeroAdapter a=new FourTwoZeroAdapter(getActivity(),hole_array,player_id_array,score1_value_array,score2_value_array,score3_value_array,total_array);
            recyclerviewScoretabl.setAdapter(a);

        } catch (JSONException je)

        {
            je.printStackTrace();
        }
    }
}