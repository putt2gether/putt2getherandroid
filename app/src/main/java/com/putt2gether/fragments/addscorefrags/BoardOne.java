package com.putt2gether.fragments.addscorefrags;

import android.app.ProgressDialog;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.LeaderAda.LeaderAdapter;
import com.putt2gether.bean.LeaderboardBean.Leader;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.addScore.Leaderboard;
import com.putt2gether.utils.Utility;
import com.putt2gether.view.LatoTextView;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 8/30/2016.
 */
public class BoardOne extends Fragment {

    ArrayList<Leader> leaderArrayList;


    RecyclerView recyclerboardone;

   String event_name ;
   String event_start_date;
   String golf_course_name ;
   String total_hole_num ;
   String format_name ;
    String tab_ID;

    LinearLayout toggleBTN;
    String flag = "0";
    String event_ID;
    String formatID;
    TextView toggleNet;
    LatoTextView eventName;
    String toggleTitle;

    SwipeRefreshLayout pullDownRefreshg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.board_first, container, false);

        pullDownRefreshg = (SwipeRefreshLayout)v.findViewById(R.id.swipeRefreshLayout);
        recyclerboardone = (RecyclerView) v.findViewById(R.id.recycler_board_first);
        toggleBTN = (LinearLayout)v.findViewById(R.id.gross_toggle);
        toggleNet = (TextView)v.findViewById(R.id.toggle_net);

        eventName = (LatoTextView) getActivity().findViewById(R.id.eventName);


        pullDownRefreshg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                event_ID=((Leaderboard) getActivity()).geteventID();
                formatID = ((Leaderboard) getActivity()).formatID();
                getLeaderBoard(event_ID,formatID);
                pullDownRefreshg.setRefreshing(false);
            }
        });



        toggleBTN.setVisibility(View.VISIBLE);


        //tab_ID= ((Leaderboard) getActivity()).tabID(0);

        recyclerboardone.setLayoutManager(new LinearLayoutManager(getContext()));

         event_ID=((Leaderboard) getActivity()).geteventID();
         formatID = ((Leaderboard) getActivity()).formatID();

        getLeaderBoard(event_ID,formatID);

        if (formatID.equalsIgnoreCase("2")){
           // toggleBTN.setVisibility(View.INVISIBLE);

            toggleBTN.setClickable(false);
        }else {
            toggleBTN.setClickable(true);
          //  toggleBTN.setVisibility(View.VISIBLE);
            toggleBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag.equalsIgnoreCase("1")){

                        if (toggleTitle!=null){
                            toggleNet.setText(toggleTitle);
                        }

                        toggleNet.setTextColor(Color.WHITE);
                        toggleNet.setBackgroundResource(R.drawable.border_circular_score);
                        getLeaderBoard(event_ID,formatID);
                        flag="0";
                    }else {
                        toggleNet.setText("GROSS STROKEPLAY");
                        toggleNet.setTextColor(getResources().getColor(R.color.action));
                        toggleNet.setBackgroundResource(R.drawable.toggle_clicked);
                        getLeaderBoard(event_ID,"2");
                        flag = "1";
                    }
                }
            });
        }



        return v;
    }


    public void getLeaderBoard(String Event_Id, String format_ID) {




        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("format_id",format_ID );
            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("is_spot_hole_number","0");
            jsonObject.putOpt("is_spot_type","0");
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("ekuirfueiwyhf",jsonObject.toString());

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

            event_name = data.getString("event_name");
            event_start_date = data.getString("event_start_date");
            golf_course_name = data.getString("golf_course_name");
            total_hole_num = data.getString("total_hole_num");
            format_name = data.getString("format_name");

            toggleTitle = format_name;
            if (toggleTitle!=null){
                toggleNet.setText(toggleTitle);
            }

            JSONArray js = data.getJSONArray("player_score");

            int i = js.length();

            leaderArrayList = new ArrayList<Leader>();

            for (int j = 0; j < i; j++) {
                Leader typeBean = new Leader();
                JSONObject jsonObject1 = js.getJSONObject(j);
                String player_id = jsonObject1.getString("player_id");
                String current_position = jsonObject1.getString("current_position");
                String handicap_value = jsonObject1.getString("handicap_value");

                String full_name = jsonObject1.getString("full_name");
                String no_of_hole_played = jsonObject1.getString("no_of_hole_played");
                String total = jsonObject1.getString("total");

                typeBean.setRank(current_position);
                typeBean.setPlayer(full_name);
                typeBean.setSubplayer(handicap_value);
                typeBean.setHolesplayed(no_of_hole_played);
                if (total.equalsIgnoreCase("0")){
                    typeBean.setNet_gross_play("Even");
                }else {
                    typeBean.setNet_gross_play(total);
                }

                typeBean.setPlayer_id(player_id);

                leaderArrayList.add(typeBean);


            }

            if (leaderArrayList.size()>0) {
                recyclerboardone.setAdapter(new LeaderAdapter(leaderArrayList, getActivity()));
            }else {

            }

        } catch (JSONException je)

        {
            je.printStackTrace();
        }
    }
}
