package com.putt2gether.fragments.addscorefrags;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.LeaderAda.LeaderAdapterTwo;
import com.putt2gether.bean.LeaderboardBean.LeaderTwo;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.addScore.Leaderboard;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 14/10/2016.
 */
public class BoardNine  extends Fragment {


    RecyclerView recyclerboardsecond;
    ArrayList<LeaderTwo> leaderArrayList;

    String event_name ;
    String event_start_date;
    String golf_course_name ;
    String total_hole_num ;
    String format_name ;
    String tab_ID;
    TextView feetOrYard;
    SwipeRefreshLayout pullDownRefresh;
    String isSpotType;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.board_second, container, false);

        recyclerboardsecond=(RecyclerView)v.findViewById(R.id.recycler_board_second);
        feetOrYard = (TextView)v.findViewById(R.id.holesplayed_net);

        isSpotType = ((Leaderboard) getActivity()).isSpotType(7);
        tab_ID= ((Leaderboard) getActivity()).tabID(7);

        recyclerboardsecond.setLayoutManager(new LinearLayoutManager(getContext()));

        String eventID=((Leaderboard) getActivity()).geteventID();

        getLeaderBoard(eventID);

        pullDownRefresh = (SwipeRefreshLayout)v.findViewById(R.id.swipeRefreshLayout);
        pullDownRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                isSpotType = ((Leaderboard) getActivity()).isSpotType(7);
                tab_ID= ((Leaderboard) getActivity()).tabID(7);
                String eventID=((Leaderboard) getActivity()).geteventID();
                getLeaderBoard(eventID);
                pullDownRefresh.setRefreshing(false);
            }
        });

        return v;
    }



    public void getLeaderBoard(String Event_Id) {

        String format_ID = ((Leaderboard) getActivity()).formatID();

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        JSONObject jsonObject = null;


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("format_id",format_ID );
            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("is_spot_type",isSpotType);
            jsonObject.putOpt("is_spot_hole_number",tab_ID);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GET_LEADERBOARD_INDIVIDUAL_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                laederBoardResponse(response);
                Log.e("GET_leaderboard", "GET_leaderboard" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(), "Error in " + "GET_leaderboard URL = " + PUTTAPI.GET_LEADERBOARD_INDIVIDUAL_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    public void laederBoardResponse(JSONObject response) {
        try {
            JSONObject output = response.getJSONObject("output");
            JSONObject data = output.getJSONObject("data");



            leaderArrayList=new ArrayList<LeaderTwo>();


            event_name = data.getString("event_name");
            event_start_date = data.getString("event_start_date");
            golf_course_name = data.getString("golf_course_name");
            total_hole_num = data.getString("total_hole_num");
            format_name = data.getString("format_name");

            String isSpotType = data.getString("is_spot_type");
            if (isSpotType.equalsIgnoreCase("3")){

                feetOrYard.setText("YARD  ");
            }else {
                feetOrYard.setText("FEET & INCH");
            }

            JSONArray js = data.getJSONArray("player_score");

            int i = js.length();


            for (int j = 0; j < i; j++) {
                LeaderTwo typeBean = new LeaderTwo();
                JSONObject jsonObject1 = js.getJSONObject(j);


                String feet = jsonObject1.getString("feet");
                String player_id = jsonObject1.getString("player_id");
                String handicap_value = jsonObject1.getString("handicap_value");

                String full_name = jsonObject1.getString("full_name");
                String inches = jsonObject1.getString("inches");
                String total = jsonObject1.getString("total");

                String current_position = jsonObject1.getString("current_position");
                typeBean.setRanktwo(current_position);

                typeBean.setPlayerID(player_id);
                typeBean.setPlayertwo(full_name);
                typeBean.setSubplayertwo(handicap_value);

                if (feet.equalsIgnoreCase("-1")){
                    feet = "-";
                }
                if (inches.equalsIgnoreCase("-1")){
                    inches = "-";
                }


                if (isSpotType.equalsIgnoreCase("3")){
                    typeBean.setFeet_inchtwo(feet);
                }else {
                    if (feet.equalsIgnoreCase("-"))
                    {
                        typeBean.setFeet_inchtwo(feet+ "  " + inches);
                    }else {
                        typeBean.setFeet_inchtwo(feet + "'" + "  " + inches + "''");
                    }
                }

                leaderArrayList.add(typeBean);


            }

            recyclerboardsecond.setAdapter(new LeaderAdapterTwo(leaderArrayList,getActivity()));

        } catch (JSONException je)

        {
            je.printStackTrace();
        }
    }
}