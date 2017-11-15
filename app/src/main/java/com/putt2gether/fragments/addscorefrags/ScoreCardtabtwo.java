package com.putt2gether.fragments.addscorefrags;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.putt2gether.R;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.addScore.ScoreTable;
import com.putt2gether.putt.score_card.ScoreCardTemplate;
import com.putt2gether.utils.Utility;
import com.putt2gether.view.LatoTextView;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/29/2016.
 */
public class ScoreCardtabtwo extends Fragment {

    LinearLayout parent;

    LatoTextView ind_up_one, ind_up_two, ind_up_three, ind_up_four, ind_up_five, ind_up_six, ind_up_seven, ind_up_eight, ind_up_nine;
    LatoTextView par_up_one, par_up_two, par_up_three, par_up_four, par_up_five, par_up_six, par_up_seven, par_up_eight, par_up_nine, par_up_total;
    LatoTextView g_up_one, g_up_two, g_up_three, g_up_four, g_up_five, g_up_six, g_up_seven, g_up_eight, g_up_nine, g_up_total;
    LatoTextView pos_up_one, pos_up_two, pos_up_three, pos_up_four, pos_up_five, pos_up_six, pos_up_seven, pos_up_eight, pos_up_nine, pos_up_total;


    LatoTextView ind_down_one, ind_down_two, ind_down_three, ind_down_four, ind_down_five, ind_down_six, ind_down_seven, ind_down_eight, ind_down_nine;
    LatoTextView par_down_one, par_down_two, par_down_three, par_down_four, par_down_five, par_down_six, par_down_seven, par_down_eight, par_down_nine, par_back_total, par_down_total;
    LatoTextView g_down_one, g_down_two, g_down_three, g_down_four, g_down_five, g_down_six, g_down_seven, g_down_eight, g_down_nine, g_back_total, g_down_total;
    LatoTextView pos_down_one, pos_down_two, pos_down_three, pos_down_four, pos_down_five, pos_down_six, pos_down_seven, pos_down_eight, pos_down_nine, pos_back_total, pos_down_total;

    LinearLayout color_lay1, color_lay2, color_lay3, color_lay4, color_lay5, color_lay6, color_lay7, color_lay8, color_lay9, color_lay10, color_lay11, color_lay12, color_lay13, color_lay14, color_lay15, color_lay16, color_lay17, color_lay18;

    LatoTextView eagle_plus_index, par_index, bardie_index, bogey_index, d_bogey_index;

    LatoTextView positionTextUP, positionTextDown;

    TableLayout fronLayout;
    TableLayout backLayout;
    String gross_score_1,gross_score_2,gross_score_3,gross_score_4,gross_score_5,gross_score_6,gross_score_7,gross_score_8,gross_score_9,gross_score_10,gross_score_11,gross_score_12,gross_score_13,gross_score_14,gross_score_15,gross_score_16,gross_score_17,gross_score_18;

    LinearLayout score_leaderboard;
    String totalPlayer;
    View line;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_table, container, false);


        score_leaderboard=(LinearLayout)getActivity().findViewById(R.id.score_leaderboard);
        line  = (View)getActivity().findViewById(R.id.line11);


        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        final String admin_ID = pref.getString("userId", null);
        final String eventID = ((ScoreTable) getActivity()).geteventID();

        parent = (LinearLayout)v.findViewById(R.id.old_scorecard_parent);

        fronLayout = (TableLayout) v.findViewById(R.id.front_nine);
        backLayout = (TableLayout) v.findViewById(R.id.back_nine_total);

        positionTextUP = (LatoTextView) v.findViewById(R.id.position_up);
        positionTextDown = (LatoTextView) v.findViewById(R.id.position_down);

        eagle_plus_index = (LatoTextView) v.findViewById(R.id.eagle_plus_scorecard);
        par_index = (LatoTextView) v.findViewById(R.id.par_scorecard);
        bardie_index = (LatoTextView) v.findViewById(R.id.birdie_scorecard);
        bogey_index = (LatoTextView) v.findViewById(R.id.bogey_scorecard);
        d_bogey_index = (LatoTextView) v.findViewById(R.id.d_bogey_scorecard);

        color_lay1 = (LinearLayout) v.findViewById(R.id.g_lay1);
        color_lay2 = (LinearLayout) v.findViewById(R.id.g_lay2);
        color_lay3 = (LinearLayout) v.findViewById(R.id.g_lay3);
        color_lay4 = (LinearLayout) v.findViewById(R.id.g_lay4);
        color_lay5 = (LinearLayout) v.findViewById(R.id.g_lay5);
        color_lay6 = (LinearLayout) v.findViewById(R.id.g_lay6);
        color_lay7 = (LinearLayout) v.findViewById(R.id.g_lay7);
        color_lay8 = (LinearLayout) v.findViewById(R.id.g_lay8);
        color_lay9 = (LinearLayout) v.findViewById(R.id.g_lay9);
        color_lay10 = (LinearLayout) v.findViewById(R.id.g_lay10);
        color_lay11 = (LinearLayout) v.findViewById(R.id.g_lay11);
        color_lay12 = (LinearLayout) v.findViewById(R.id.g_lay12);
        color_lay13 = (LinearLayout) v.findViewById(R.id.g_lay13);
        color_lay14 = (LinearLayout) v.findViewById(R.id.g_lay14);
        color_lay15 = (LinearLayout) v.findViewById(R.id.g_lay15);
        color_lay16 = (LinearLayout) v.findViewById(R.id.g_lay16);
        color_lay17 = (LinearLayout) v.findViewById(R.id.g_lay17);
        color_lay18 = (LinearLayout) v.findViewById(R.id.g_lay18);


        ind_up_one = (LatoTextView) v.findViewById(R.id.index_up_one);
        ind_up_two = (LatoTextView) v.findViewById(R.id.index_up_two);
        ind_up_three = (LatoTextView) v.findViewById(R.id.index_up_three);
        ind_up_four = (LatoTextView) v.findViewById(R.id.index_up_four);
        ind_up_five = (LatoTextView) v.findViewById(R.id.index_up_five);
        ind_up_six = (LatoTextView) v.findViewById(R.id.index_up_six);
        ind_up_seven = (LatoTextView) v.findViewById(R.id.index_up_seven);
        ind_up_eight = (LatoTextView) v.findViewById(R.id.index_up_eight);
        ind_up_nine = (LatoTextView) v.findViewById(R.id.index_up_nine);

        par_up_one = (LatoTextView) v.findViewById(R.id.par_up_one);
        par_up_two = (LatoTextView) v.findViewById(R.id.par_up_two);
        par_up_three = (LatoTextView) v.findViewById(R.id.par_up_three);
        par_up_four = (LatoTextView) v.findViewById(R.id.par_up_four);
        par_up_five = (LatoTextView) v.findViewById(R.id.par_up_five);
        par_up_six = (LatoTextView) v.findViewById(R.id.par_up_six);
        par_up_seven = (LatoTextView) v.findViewById(R.id.par_up_seven);
        par_up_eight = (LatoTextView) v.findViewById(R.id.par_up_eight);
        par_up_nine = (LatoTextView) v.findViewById(R.id.par_up_nine);

        par_up_total = (LatoTextView) v.findViewById(R.id.par_up_total);

        g_up_one = (LatoTextView) v.findViewById(R.id.g_up_one);
        g_up_two = (LatoTextView) v.findViewById(R.id.g_up_two);

        g_up_three = (LatoTextView) v.findViewById(R.id.g_up_three);
        g_up_four = (LatoTextView) v.findViewById(R.id.g_up_four);
        g_up_five = (LatoTextView) v.findViewById(R.id.g_up_five);
        g_up_six = (LatoTextView) v.findViewById(R.id.g_up_six);
        g_up_seven = (LatoTextView) v.findViewById(R.id.g_up_seven);
        g_up_eight = (LatoTextView) v.findViewById(R.id.g_up_eight);
        g_up_nine = (LatoTextView) v.findViewById(R.id.g_up_nine);

        g_up_total = (LatoTextView) v.findViewById(R.id.g_up_total);

        g_back_total = (LatoTextView) v.findViewById(R.id.g_back_total);


        pos_up_one = (LatoTextView) v.findViewById(R.id.pos_up_one);
        pos_up_two = (LatoTextView) v.findViewById(R.id.pos_up_two);
        pos_up_three = (LatoTextView) v.findViewById(R.id.pos_up_three);
        pos_up_four = (LatoTextView) v.findViewById(R.id.pos_up_four);
        pos_up_five = (LatoTextView) v.findViewById(R.id.pos_up_five);
        pos_up_six = (LatoTextView) v.findViewById(R.id.pos_up_six);
        pos_up_seven = (LatoTextView) v.findViewById(R.id.pos_up_seven);
        pos_up_eight = (LatoTextView) v.findViewById(R.id.pos_up_eight);
        pos_up_nine = (LatoTextView) v.findViewById(R.id.pos_up_nine);

        pos_up_total = (LatoTextView) v.findViewById(R.id.pos_up_total);

        ind_down_one = (LatoTextView) v.findViewById(R.id.index_down_one);
        ind_down_two = (LatoTextView) v.findViewById(R.id.index_down_two);
        ind_down_three = (LatoTextView) v.findViewById(R.id.index_down_three);
        ind_down_four = (LatoTextView) v.findViewById(R.id.index_down_four);
        ind_down_five = (LatoTextView) v.findViewById(R.id.index_down_five);
        ind_down_six = (LatoTextView) v.findViewById(R.id.index_down_six);
        ind_down_seven = (LatoTextView) v.findViewById(R.id.index_down_seven);
        ind_down_eight = (LatoTextView) v.findViewById(R.id.index_down_eight);
        ind_down_nine = (LatoTextView) v.findViewById(R.id.index_down_nine);


        par_down_one = (LatoTextView) v.findViewById(R.id.par_down_one);
        par_down_two = (LatoTextView) v.findViewById(R.id.par_down_two);
        par_down_three = (LatoTextView) v.findViewById(R.id.par_down_three);
        par_down_four = (LatoTextView) v.findViewById(R.id.par_down_four);
        par_down_five = (LatoTextView) v.findViewById(R.id.par_down_five);
        par_down_six = (LatoTextView) v.findViewById(R.id.par_down_six);
        par_down_seven = (LatoTextView) v.findViewById(R.id.par_down_seven);
        par_down_eight = (LatoTextView) v.findViewById(R.id.par_down_eight);
        par_down_nine = (LatoTextView) v.findViewById(R.id.par_down_nine);

        par_back_total = (LatoTextView) v.findViewById(R.id.par_back_total);
        par_down_total = (LatoTextView) v.findViewById(R.id.par_down_total);

        g_down_one = (LatoTextView) v.findViewById(R.id.g_down_one);
        g_down_two = (LatoTextView) v.findViewById(R.id.g_down_two);
        g_down_three = (LatoTextView) v.findViewById(R.id.g_down_three);
        g_down_four = (LatoTextView) v.findViewById(R.id.g_down_four);
        g_down_five = (LatoTextView) v.findViewById(R.id.g_down_five);
        g_down_six = (LatoTextView) v.findViewById(R.id.g_down_six);
        g_down_seven = (LatoTextView) v.findViewById(R.id.g_down_seven);
        g_down_eight = (LatoTextView) v.findViewById(R.id.g_down_eight);
        g_down_nine = (LatoTextView) v.findViewById(R.id.g_down_nine);

        g_down_total = (LatoTextView) v.findViewById(R.id.g_down_total);

        pos_down_one = (LatoTextView) v.findViewById(R.id.pos_down_one);
        pos_down_two = (LatoTextView) v.findViewById(R.id.pos_down_two);
        pos_down_three = (LatoTextView) v.findViewById(R.id.pos_down_three);
        pos_down_four = (LatoTextView) v.findViewById(R.id.pos_down_four);
        pos_down_five = (LatoTextView) v.findViewById(R.id.pos_down_five);
        pos_down_six = (LatoTextView) v.findViewById(R.id.pos_down_six);
        pos_down_seven = (LatoTextView) v.findViewById(R.id.pos_down_seven);
        pos_down_eight = (LatoTextView) v.findViewById(R.id.pos_down_eight);
        pos_down_nine = (LatoTextView) v.findViewById(R.id.pos_down_nine);

        pos_back_total = (LatoTextView) v.findViewById(R.id.pos_back_total);

        pos_down_total = (LatoTextView) v.findViewById(R.id.pos_down_total);

        final String playerID = ((ScoreTable) getActivity()).playerID(1);

        LinearLayout share = (LinearLayout)getActivity().findViewById(R.id.stats_btn);
        String statsType = getActivity().getIntent().getStringExtra("stats_visible");
        if (statsType!=null && statsType.equalsIgnoreCase("yes")){

            share.setVisibility(View.VISIBLE);
        }
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScoreCardTemplate.class);
                intent.putExtra("event_id",eventID);
                intent.putExtra("player_id",playerID);
                startActivity(intent);
            }
        });


        getscorecardtable(playerID, eventID);

        return v;
    }

    public void getscorecardtable(String playerid, String Event_Id) {

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("player_id", playerid);
            jsonObject.putOpt("event_id", Event_Id);

            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GETLATESTFULLSCORE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                tabledata(response);
                Log.e("GETLATESTFULLSCORE", "GETLATESTFULLSCORE" + response.toString());
                pDialog.cancel();
                parent.setVisibility(View.VISIBLE);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(), "Error in " + "GETLATESTFULLSCORE URL = " + PUTTAPI.GETLATESTFULLSCORE);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }


    public void tabledata(JSONObject response) {
        try {
            JSONObject output = response.getJSONObject("output");

            JSONObject data = output.getJSONObject("data");

            String totalHole = data.getString("total_num_hole");
            totalPlayer = data.getString("total_player");

            if (totalHole.equalsIgnoreCase("18")) {

                String parvalue1 = data.getString("par_value_1");
                par_up_one.setText(parvalue1);
                String parvalue2 = data.getString("par_value_2");
                par_up_two.setText(parvalue2);
                String parvalue3 = data.getString("par_value_3");
                par_up_three.setText(parvalue3);
                String parvalue4 = data.getString("par_value_4");
                par_up_four.setText(parvalue4);
                String parvalue5 = data.getString("par_value_5");
                par_up_five.setText(parvalue5);
                String parvalue6 = data.getString("par_value_6");
                par_up_six.setText(parvalue6);
                String parvalue7 = data.getString("par_value_7");
                par_up_seven.setText(parvalue7);
                String parvalue8 = data.getString("par_value_8");
                par_up_eight.setText(parvalue8);
                String parvalue9 = data.getString("par_value_9");
                par_up_nine.setText(parvalue9);
                String parvalue10 = data.getString("par_value_10");
                par_down_one.setText(parvalue10);
                String parvalue11 = data.getString("par_value_11");
                par_down_two.setText(parvalue11);
                String parvalue12 = data.getString("par_value_12");
                par_down_three.setText(parvalue12);
                String parvalue13 = data.getString("par_value_13");
                par_down_four.setText(parvalue13);
                String parvalue14 = data.getString("par_value_14");
                par_down_five.setText(parvalue14);
                String parvalue15 = data.getString("par_value_15");
                par_down_six.setText(parvalue15);
                String parvalue16 = data.getString("par_value_16");
                par_down_seven.setText(parvalue16);
                String parvalue17 = data.getString("par_value_17");
                par_down_eight.setText(parvalue17);
                String parvalue18 = data.getString("par_value_18");
                par_down_nine.setText(parvalue18);


                int partotal_up_9 = Integer.parseInt(parvalue1) + Integer.parseInt(parvalue2) + Integer.parseInt(parvalue3) + Integer.parseInt(parvalue4) + Integer.parseInt(parvalue5) + Integer.parseInt(parvalue6) + Integer.parseInt(parvalue7) + Integer.parseInt(parvalue8) + Integer.parseInt(parvalue9);

                String total_up_par = partotal_up_9 + "";

                par_up_total.setText(total_up_par);

                int partotal_down_9 = Integer.parseInt(parvalue10) + Integer.parseInt(parvalue11) + Integer.parseInt(parvalue12) + Integer.parseInt(parvalue13) + Integer.parseInt(parvalue14) + Integer.parseInt(parvalue15) + Integer.parseInt(parvalue16) + Integer.parseInt(parvalue17) + Integer.parseInt(parvalue18);

                String total_down_par = partotal_down_9 + "";

                Log.e("parback9", total_down_par);

                par_back_total.setText(total_down_par);

                int par_total = partotal_up_9 + partotal_down_9;

                String partotal = par_total + "";

                par_down_total.setText(partotal + "");

                String eagle_index = data.getString("eagle_counter");
                eagle_plus_index.setText(eagle_index);
                String birdie_index = data.getString("birdie_counter");
                bardie_index.setText(birdie_index);
                String par_index1 = data.getString("par_counter");
                par_index.setText(par_index1);
                String bogey_index1 = data.getString("bogey_counter");
                bogey_index.setText(bogey_index1);
                String d_bogey_index1 = data.getString("doublebogey_counter");
                d_bogey_index.setText(d_bogey_index1);


                String hole_index_1 = data.getString("hole_index_1");
                ind_up_one.setText(hole_index_1);
                String hole_index_2 = data.getString("hole_index_2");
                ind_up_two.setText(hole_index_2);
                String hole_index_3 = data.getString("hole_index_3");
                ind_up_three.setText(hole_index_3);
                String hole_index_4 = data.getString("hole_index_4");
                ind_up_four.setText(hole_index_4);
                String hole_index_5 = data.getString("hole_index_5");
                ind_up_five.setText(hole_index_5);
                String hole_index_6 = data.getString("hole_index_6");
                ind_up_six.setText(hole_index_6);
                String hole_index_7 = data.getString("hole_index_7");
                ind_up_seven.setText(hole_index_7);
                String hole_index_8 = data.getString("hole_index_8");
                ind_up_eight.setText(hole_index_8);
                String hole_index_9 = data.getString("hole_index_9");
                ind_up_nine.setText(hole_index_9);
                String hole_index_10 = data.getString("hole_index_10");
                ind_down_one.setText(hole_index_10);
                String hole_index_11 = data.getString("hole_index_11");
                ind_down_two.setText(hole_index_11);
                String hole_index_12 = data.getString("hole_index_12");
                ind_down_three.setText(hole_index_12);
                String hole_index_13 = data.getString("hole_index_13");
                ind_down_four.setText(hole_index_13);
                String hole_index_14 = data.getString("hole_index_14");
                ind_down_five.setText(hole_index_14);
                String hole_index_15 = data.getString("hole_index_15");
                ind_down_six.setText(hole_index_15);
                String hole_index_16 = data.getString("hole_index_16");
                ind_down_seven.setText(hole_index_16);
                String hole_index_17 = data.getString("hole_index_17");
                ind_down_eight.setText(hole_index_17);
                String hole_index_18 = data.getString("hole_index_18");
                ind_down_nine.setText(hole_index_18);

                String stoke_play_id = data.getString("event_stroke_play_id");
                JSONArray player_hole_score = data.getJSONArray("player_hole_score");

                if (stoke_play_id.equalsIgnoreCase("5") || stoke_play_id.equalsIgnoreCase("6") || stoke_play_id.equalsIgnoreCase("7")) {

                    gross_score_1 = player_hole_score.getJSONObject(0).getString("gross_score_1");
                    gross_score_2 = player_hole_score.getJSONObject(0).getString("gross_score_2");
                    gross_score_3 = player_hole_score.getJSONObject(0).getString("gross_score_3");
                    gross_score_4 = player_hole_score.getJSONObject(0).getString("gross_score_4");
                    gross_score_5 = player_hole_score.getJSONObject(0).getString("gross_score_5");
                    gross_score_6 = player_hole_score.getJSONObject(0).getString("gross_score_6");
                    gross_score_7 = player_hole_score.getJSONObject(0).getString("gross_score_7");
                    gross_score_8 = player_hole_score.getJSONObject(0).getString("gross_score_8");
                    gross_score_9 = player_hole_score.getJSONObject(0).getString("gross_score_9");
                    gross_score_10 = player_hole_score.getJSONObject(0).getString("gross_score_10");
                    gross_score_11 = player_hole_score.getJSONObject(0).getString("gross_score_11");
                    gross_score_12 = player_hole_score.getJSONObject(0).getString("gross_score_12");
                    gross_score_13 = player_hole_score.getJSONObject(0).getString("gross_score_13");
                    gross_score_14 = player_hole_score.getJSONObject(0).getString("gross_score_14");
                    gross_score_15 = player_hole_score.getJSONObject(0).getString("gross_score_15");
                    gross_score_16 = player_hole_score.getJSONObject(0).getString("gross_score_16");
                    gross_score_17 = player_hole_score.getJSONObject(0).getString("gross_score_17");
                    gross_score_18 = player_hole_score.getJSONObject(0).getString("gross_score_18");
                }else {
                    gross_score_1 = player_hole_score.getJSONObject(0).getString("hole_num_1");
                    gross_score_2 = player_hole_score.getJSONObject(0).getString("hole_num_2");
                    gross_score_3 = player_hole_score.getJSONObject(0).getString("hole_num_3");
                    gross_score_4 = player_hole_score.getJSONObject(0).getString("hole_num_4");
                    gross_score_5 = player_hole_score.getJSONObject(0).getString("hole_num_5");
                    gross_score_6 = player_hole_score.getJSONObject(0).getString("hole_num_6");
                    gross_score_7 = player_hole_score.getJSONObject(0).getString("hole_num_7");
                    gross_score_8 = player_hole_score.getJSONObject(0).getString("hole_num_8");
                    gross_score_9 = player_hole_score.getJSONObject(0).getString("hole_num_9");
                    gross_score_10 = player_hole_score.getJSONObject(0).getString("hole_num_10");
                    gross_score_11 = player_hole_score.getJSONObject(0).getString("hole_num_11");
                    gross_score_12 = player_hole_score.getJSONObject(0).getString("hole_num_12");
                    gross_score_13 = player_hole_score.getJSONObject(0).getString("hole_num_13");
                    gross_score_14 = player_hole_score.getJSONObject(0).getString("hole_num_14");
                    gross_score_15 = player_hole_score.getJSONObject(0).getString("hole_num_15");
                    gross_score_16 = player_hole_score.getJSONObject(0).getString("hole_num_16");
                    gross_score_17 = player_hole_score.getJSONObject(0).getString("hole_num_17");
                    gross_score_18 = player_hole_score.getJSONObject(0).getString("hole_num_18");
                }


                if (gross_score_1 != null) {
                    g_up_one.setText(gross_score_1);
                }
                if (gross_score_2 != null) {
                    g_up_two.setText(gross_score_2);
                }
                if (gross_score_3 != null) {
                    g_up_three.setText(gross_score_3);
                }
                if (gross_score_4 != null) {
                    g_up_four.setText(gross_score_4);
                }
                if (gross_score_5 != null) {
                    g_up_five.setText(gross_score_5);
                }
                if (gross_score_6 != null) {
                    g_up_six.setText(gross_score_6);
                }
                if (gross_score_7 != null) {
                    g_up_seven.setText(gross_score_7);
                }
                if (gross_score_8 != null) {
                    g_up_eight.setText(gross_score_8);
                }
                if (gross_score_9 != null) {
                    g_up_nine.setText(gross_score_9);
                }
                if (gross_score_10 != null) {
                    g_down_one.setText(gross_score_10);
                }
                if (gross_score_11 != null) {
                    g_down_two.setText(gross_score_11);
                }
                if (gross_score_12 != null) {
                    g_down_three.setText(gross_score_12);
                }
                if (gross_score_13 != null) {
                    g_down_four.setText(gross_score_13);
                }
                if (gross_score_14 != null) {
                    g_down_five.setText(gross_score_14);
                }
                if (gross_score_15 != null) {
                    g_down_six.setText(gross_score_15);
                }
                if (gross_score_16 != null) {
                    g_down_seven.setText(gross_score_16);
                }
                if (gross_score_17 != null) {
                    g_down_eight.setText(gross_score_17);
                }
                if (gross_score_18 != null) {
                    g_down_nine.setText(gross_score_18);
                }


                int gross_total_up = Integer.parseInt(gross_score_1) + Integer.parseInt(gross_score_2) + Integer.parseInt(gross_score_3) + Integer.parseInt(gross_score_4) + Integer.parseInt(gross_score_5) + Integer.parseInt(gross_score_6) + Integer.parseInt(gross_score_7) + Integer.parseInt(gross_score_8) + Integer.parseInt(gross_score_9);

                g_up_total.setText(gross_total_up + "");

                int gross_total_down = Integer.parseInt(gross_score_10) + Integer.parseInt(gross_score_11) + Integer.parseInt(gross_score_12) + Integer.parseInt(gross_score_13) + Integer.parseInt(gross_score_14) + Integer.parseInt(gross_score_15) + Integer.parseInt(gross_score_16) + Integer.parseInt(gross_score_17) + Integer.parseInt(gross_score_18);

                g_back_total.setText(gross_total_down + "");

                int gross_total = gross_total_up + gross_total_down;

                g_down_total.setText(gross_total + "");


                Log.v("stoke_id", stoke_play_id);

                if (stoke_play_id.equalsIgnoreCase("5") || stoke_play_id.equalsIgnoreCase("6") || stoke_play_id.equalsIgnoreCase("7")) {
                    positionTextUP.setText("STABLEFORD");
                    positionTextDown.setText("STABLEFORD");
                    String stableford_1 = player_hole_score.getJSONObject(0).getString("hole_num_1");
                    pos_up_one.setText(stableford_1);

                    String stableford_2 = player_hole_score.getJSONObject(0).getString("hole_num_2");
                    pos_up_two.setText(stableford_2);
                    String stableford_3 = player_hole_score.getJSONObject(0).getString("hole_num_3");
                    pos_up_three.setText(stableford_3);
                    String stableford_4 = player_hole_score.getJSONObject(0).getString("hole_num_4");
                    pos_up_four.setText(stableford_4);
                    String stableford_5 = player_hole_score.getJSONObject(0).getString("hole_num_5");
                    pos_up_five.setText(stableford_5);
                    String stableford_6 = player_hole_score.getJSONObject(0).getString("hole_num_6");
                    pos_up_six.setText(stableford_6);
                    String stableford_7 = player_hole_score.getJSONObject(0).getString("hole_num_7");
                    pos_up_seven.setText(stableford_7);
                    String stableford_8 = player_hole_score.getJSONObject(0).getString("hole_num_8");
                    pos_up_eight.setText(stableford_8);
                    String stableford_9 = player_hole_score.getJSONObject(0).getString("hole_num_9");
                    pos_up_nine.setText(stableford_9);
                    String stableford_10 = player_hole_score.getJSONObject(0).getString("hole_num_10");
                    pos_down_one.setText(stableford_10);
                    String stableford_11 = player_hole_score.getJSONObject(0).getString("hole_num_11");
                    pos_down_two.setText(stableford_11);
                    String stableford_12 = player_hole_score.getJSONObject(0).getString("hole_num_12");
                    pos_down_three.setText(stableford_12);
                    String stableford_13 = player_hole_score.getJSONObject(0).getString("hole_num_13");
                    pos_down_four.setText(stableford_13);
                    String stableford_14 = player_hole_score.getJSONObject(0).getString("hole_num_14");
                    pos_down_five.setText(stableford_14);
                    String stableford_15 = player_hole_score.getJSONObject(0).getString("hole_num_15");
                    pos_down_six.setText(stableford_15);
                    String stableford_16 = player_hole_score.getJSONObject(0).getString("hole_num_16");
                    pos_down_seven.setText(stableford_16);
                    String stableford_17 = player_hole_score.getJSONObject(0).getString("hole_num_17");
                    pos_down_eight.setText(stableford_17);
                    String stableford_18 = player_hole_score.getJSONObject(0).getString("hole_num_18");
                    pos_down_nine.setText(stableford_18);


                } else {

                    positionTextUP.setText("POSITION");
                    positionTextDown.setText("POSITION");

                    String position_1 = player_hole_score.getJSONObject(0).getString("position_1");
                    pos_up_one.setText(position_1);
                    String position_2 = player_hole_score.getJSONObject(0).getString("position_2");
                    pos_up_two.setText(position_2);
                    String position_3 = player_hole_score.getJSONObject(0).getString("position_3");
                    pos_up_three.setText(position_3);
                    String position_4 = player_hole_score.getJSONObject(0).getString("position_4");
                    pos_up_four.setText(position_4);
                    String position_5 = player_hole_score.getJSONObject(0).getString("position_5");
                    pos_up_five.setText(position_5);
                    String position_6 = player_hole_score.getJSONObject(0).getString("position_6");
                    pos_up_six.setText(position_6);
                    String position_7 = player_hole_score.getJSONObject(0).getString("position_7");
                    pos_up_seven.setText(position_7);
                    String position_8 = player_hole_score.getJSONObject(0).getString("position_8");
                    pos_up_eight.setText(position_8);
                    String position_9 = player_hole_score.getJSONObject(0).getString("position_9");

                    pos_up_nine.setText(position_9);
                    String position_10 = player_hole_score.getJSONObject(0).getString("position_10");
                    pos_down_one.setText(position_10);
                    String position_11 = player_hole_score.getJSONObject(0).getString("position_11");
                    pos_down_two.setText(position_11);
                    String position_12 = player_hole_score.getJSONObject(0).getString("position_12");
                    pos_down_three.setText(position_12);
                    String position_13 = player_hole_score.getJSONObject(0).getString("position_13");
                    pos_down_four.setText(position_13);
                    String position_14 = player_hole_score.getJSONObject(0).getString("position_14");
                    pos_down_five.setText(position_14);
                    String position_15 = player_hole_score.getJSONObject(0).getString("position_15");
                    pos_down_six.setText(position_15);
                    String position_16 = player_hole_score.getJSONObject(0).getString("position_16");
                    pos_down_seven.setText(position_16);
                    String position_17 = player_hole_score.getJSONObject(0).getString("position_17");
                    pos_down_eight.setText(position_17);
                    String position_18 = player_hole_score.getJSONObject(0).getString("position_18");
                    pos_down_nine.setText(position_18);


                    pos_up_total.setText(position_9 + "");
                    pos_back_total.setText(position_18 + "");

                    Log.e("pos_down_total", position_18 + "");
                    pos_down_total.setText(position_18 + "");

                }

                String color_1 = player_hole_score.getJSONObject(0).getString("hole_color_1");

                if (color_1.equalsIgnoreCase("#ffffff")){
                    color_lay1.setBackgroundResource(R.drawable.cell_default);
                    g_up_one.setTextColor(Color.BLACK);
                }else {
                    color_lay1.setBackgroundColor(Color.parseColor(color_1));
                }

                String color_2 = player_hole_score.getJSONObject(0).getString("hole_color_2");

                if (color_2.equalsIgnoreCase("#ffffff")){
                    color_lay2.setBackgroundResource(R.drawable.cell_default);
                    g_up_two.setTextColor(Color.BLACK);
                }else {
                    color_lay2.setBackgroundColor(Color.parseColor(color_2));
                }

                String color_3 = player_hole_score.getJSONObject(0).getString("hole_color_3");
                if (color_3.equalsIgnoreCase("#ffffff")){
                    color_lay3.setBackgroundResource(R.drawable.cell_default);
                    g_up_three.setTextColor(Color.BLACK);
                }else {
                    color_lay3.setBackgroundColor(Color.parseColor(color_3));
                }
                String color_4 = player_hole_score.getJSONObject(0).getString("hole_color_4");
                if (color_4.equalsIgnoreCase("#ffffff")){
                    color_lay4.setBackgroundResource(R.drawable.cell_default);
                    g_up_four.setTextColor(Color.BLACK);
                }else {
                    color_lay4.setBackgroundColor(Color.parseColor(color_4));
                }
                String color_5 = player_hole_score.getJSONObject(0).getString("hole_color_5");
                if (color_5.equalsIgnoreCase("#ffffff")){
                    color_lay5.setBackgroundResource(R.drawable.cell_default);
                    g_up_five.setTextColor(Color.BLACK);
                }else {
                    color_lay5.setBackgroundColor(Color.parseColor(color_5));
                }
                String color_6 = player_hole_score.getJSONObject(0).getString("hole_color_6");
                if (color_6.equalsIgnoreCase("#ffffff")){
                    color_lay6.setBackgroundResource(R.drawable.cell_default);
                    g_up_six.setTextColor(Color.BLACK);
                }else {
                    color_lay6.setBackgroundColor(Color.parseColor(color_6));
                }
                String color_7 = player_hole_score.getJSONObject(0).getString("hole_color_7");
                if (color_7.equalsIgnoreCase("#ffffff")){
                    color_lay7.setBackgroundResource(R.drawable.cell_default);
                    g_up_seven.setTextColor(Color.BLACK);
                }else {
                    color_lay7.setBackgroundColor(Color.parseColor(color_7));
                }
                String color_8 = player_hole_score.getJSONObject(0).getString("hole_color_8");
                if (color_8.equalsIgnoreCase("#ffffff")){
                    color_lay8.setBackgroundResource(R.drawable.cell_default);
                    g_up_eight.setTextColor(Color.BLACK);
                }else {
                    color_lay8.setBackgroundColor(Color.parseColor(color_8));
                }
                String color_9 = player_hole_score.getJSONObject(0).getString("hole_color_9");

                if (color_9.equalsIgnoreCase("#ffffff")){
                    color_lay9.setBackgroundResource(R.drawable.cell_default);
                    g_up_nine.setTextColor(Color.BLACK);
                }else {
                    color_lay9.setBackgroundColor(Color.parseColor(color_9));
                }
                String color_10 = player_hole_score.getJSONObject(0).getString("hole_color_10");

                if (color_10.equalsIgnoreCase("#ffffff")){
                    color_lay10.setBackgroundResource(R.drawable.cell_default);
                    g_down_one.setTextColor(Color.BLACK);
                }else {
                    color_lay10.setBackgroundColor(Color.parseColor(color_10));
                }

                String color_11 = player_hole_score.getJSONObject(0).getString("hole_color_11");
                if (color_11.equalsIgnoreCase("#ffffff")){
                    color_lay11.setBackgroundResource(R.drawable.cell_default);
                    g_down_two.setTextColor(Color.BLACK);
                }else {
                    color_lay11.setBackgroundColor(Color.parseColor(color_11));
                }
                String color_12 = player_hole_score.getJSONObject(0).getString("hole_color_12");
                if (color_12.equalsIgnoreCase("#ffffff")){
                    color_lay12.setBackgroundResource(R.drawable.cell_default);
                    g_down_three.setTextColor(Color.BLACK);
                }else {
                    color_lay12.setBackgroundColor(Color.parseColor(color_12));
                }
                String color_13 = player_hole_score.getJSONObject(0).getString("hole_color_13");
                if (color_13.equalsIgnoreCase("#ffffff")){
                    color_lay13.setBackgroundResource(R.drawable.cell_default);
                    g_down_four.setTextColor(Color.BLACK);
                }else {
                    color_lay13.setBackgroundColor(Color.parseColor(color_13));
                }
                String color_14 = player_hole_score.getJSONObject(0).getString("hole_color_14");
                if (color_14.equalsIgnoreCase("#ffffff")){
                    color_lay14.setBackgroundResource(R.drawable.cell_default);
                    g_down_five.setTextColor(Color.BLACK);
                }else {
                    color_lay14.setBackgroundColor(Color.parseColor(color_14));
                }
                String color_15 = player_hole_score.getJSONObject(0).getString("hole_color_15");
                if (color_15.equalsIgnoreCase("#ffffff")){
                    color_lay15.setBackgroundResource(R.drawable.cell_default);
                    g_down_six.setTextColor(Color.BLACK);
                }else {
                    color_lay15.setBackgroundColor(Color.parseColor(color_15));
                }
                String color_16 = player_hole_score.getJSONObject(0).getString("hole_color_16");
                if (color_16.equalsIgnoreCase("#ffffff")){
                    color_lay16.setBackgroundResource(R.drawable.cell_default);
                    g_down_seven.setTextColor(Color.BLACK);
                }else {
                    color_lay16.setBackgroundColor(Color.parseColor(color_16));
                }
                String color_17 = player_hole_score.getJSONObject(0).getString("hole_color_17");
                if (color_17.equalsIgnoreCase("#ffffff")){
                    color_lay17.setBackgroundResource(R.drawable.cell_default);
                    g_down_eight.setTextColor(Color.BLACK);
                }else {
                    color_lay17.setBackgroundColor(Color.parseColor(color_17));
                }
                String color_18 = player_hole_score.getJSONObject(0).getString("hole_color_18");
                if (color_18.equalsIgnoreCase("#ffffff")){
                    color_lay18.setBackgroundResource(R.drawable.cell_default);
                    g_down_nine.setTextColor(Color.BLACK);
                }else {
                    color_lay18.setBackgroundColor(Color.parseColor(color_18));
                }

            } else {
                String startFrom = data.getString("hole_start_from");
                if (startFrom.equalsIgnoreCase("1")) {

                    fronLayout.setVisibility(View.VISIBLE);
                    backLayout.setVisibility(View.GONE);

                    String parvalue1 = data.getString("par_value_1");
                    par_up_one.setText(parvalue1);
                    String parvalue2 = data.getString("par_value_2");
                    par_up_two.setText(parvalue2);
                    String parvalue3 = data.getString("par_value_3");
                    par_up_three.setText(parvalue3);
                    String parvalue4 = data.getString("par_value_4");
                    par_up_four.setText(parvalue4);
                    String parvalue5 = data.getString("par_value_5");
                    par_up_five.setText(parvalue5);
                    String parvalue6 = data.getString("par_value_6");
                    par_up_six.setText(parvalue6);
                    String parvalue7 = data.getString("par_value_7");
                    par_up_seven.setText(parvalue7);
                    String parvalue8 = data.getString("par_value_8");
                    par_up_eight.setText(parvalue8);
                    String parvalue9 = data.getString("par_value_9");
                    par_up_nine.setText(parvalue9);


                    int partotal_up_9 = Integer.parseInt(parvalue1) + Integer.parseInt(parvalue2) + Integer.parseInt(parvalue3) + Integer.parseInt(parvalue4) + Integer.parseInt(parvalue5) + Integer.parseInt(parvalue6) + Integer.parseInt(parvalue7) + Integer.parseInt(parvalue8) + Integer.parseInt(parvalue9);
                    String total_up_par = partotal_up_9 + "";
                    par_up_total.setText(total_up_par);

                    String eagle_index = data.getString("eagle_counter");
                    eagle_plus_index.setText(eagle_index);
                    String birdie_index = data.getString("birdie_counter");
                    bardie_index.setText(birdie_index);
                    String par_index1 = data.getString("par_counter");
                    par_index.setText(par_index1);
                    String bogey_index1 = data.getString("bogey_counter");
                    bogey_index.setText(bogey_index1);
                    String d_bogey_index1 = data.getString("doublebogey_counter");
                    d_bogey_index.setText(d_bogey_index1);


                    String hole_index_1 = data.getString("hole_index_1");
                    ind_up_one.setText(hole_index_1);
                    String hole_index_2 = data.getString("hole_index_2");
                    ind_up_two.setText(hole_index_2);
                    String hole_index_3 = data.getString("hole_index_3");
                    ind_up_three.setText(hole_index_3);
                    String hole_index_4 = data.getString("hole_index_4");
                    ind_up_four.setText(hole_index_4);
                    String hole_index_5 = data.getString("hole_index_5");
                    ind_up_five.setText(hole_index_5);
                    String hole_index_6 = data.getString("hole_index_6");
                    ind_up_six.setText(hole_index_6);
                    String hole_index_7 = data.getString("hole_index_7");
                    ind_up_seven.setText(hole_index_7);
                    String hole_index_8 = data.getString("hole_index_8");
                    ind_up_eight.setText(hole_index_8);
                    String hole_index_9 = data.getString("hole_index_9");
                    ind_up_nine.setText(hole_index_9);

                    String stoke_play_id = data.getString("event_stroke_play_id");
                    JSONArray player_hole_score = data.getJSONArray("player_hole_score");
                    if (stoke_play_id.equalsIgnoreCase("5") || stoke_play_id.equalsIgnoreCase("6") || stoke_play_id.equalsIgnoreCase("7")) {

                        gross_score_1 = player_hole_score.getJSONObject(0).getString("gross_score_1");
                        gross_score_2 = player_hole_score.getJSONObject(0).getString("gross_score_2");
                        gross_score_3 = player_hole_score.getJSONObject(0).getString("gross_score_3");
                        gross_score_4 = player_hole_score.getJSONObject(0).getString("gross_score_4");
                        gross_score_5 = player_hole_score.getJSONObject(0).getString("gross_score_5");
                        gross_score_6 = player_hole_score.getJSONObject(0).getString("gross_score_6");
                        gross_score_7 = player_hole_score.getJSONObject(0).getString("gross_score_7");
                        gross_score_8 = player_hole_score.getJSONObject(0).getString("gross_score_8");
                        gross_score_9 = player_hole_score.getJSONObject(0).getString("gross_score_9");

                    }else {
                        gross_score_1 = player_hole_score.getJSONObject(0).getString("hole_num_1");
                        gross_score_2 = player_hole_score.getJSONObject(0).getString("hole_num_2");
                        gross_score_3 = player_hole_score.getJSONObject(0).getString("hole_num_3");
                        gross_score_4 = player_hole_score.getJSONObject(0).getString("hole_num_4");
                        gross_score_5 = player_hole_score.getJSONObject(0).getString("hole_num_5");
                        gross_score_6 = player_hole_score.getJSONObject(0).getString("hole_num_6");
                        gross_score_7 = player_hole_score.getJSONObject(0).getString("hole_num_7");
                        gross_score_8 = player_hole_score.getJSONObject(0).getString("hole_num_8");
                        gross_score_9 = player_hole_score.getJSONObject(0).getString("hole_num_9");

                    }


                    if (gross_score_1 != null) {
                        g_up_one.setText(gross_score_1);
                    }
                    if (gross_score_2 != null) {
                        g_up_two.setText(gross_score_2);
                    }
                    if (gross_score_3 != null) {
                        g_up_three.setText(gross_score_3);
                    }
                    if (gross_score_4 != null) {
                        g_up_four.setText(gross_score_4);
                    }
                    if (gross_score_5 != null) {
                        g_up_five.setText(gross_score_5);
                    }
                    if (gross_score_6 != null) {
                        g_up_six.setText(gross_score_6);
                    }
                    if (gross_score_7 != null) {
                        g_up_seven.setText(gross_score_7);
                    }
                    if (gross_score_8 != null) {
                        g_up_eight.setText(gross_score_8);
                    }
                    if (gross_score_9 != null) {
                        g_up_nine.setText(gross_score_9);
                    }


                    int gross_total_up = Integer.parseInt(gross_score_1) + Integer.parseInt(gross_score_2) + Integer.parseInt(gross_score_3) + Integer.parseInt(gross_score_4) + Integer.parseInt(gross_score_5) + Integer.parseInt(gross_score_6) + Integer.parseInt(gross_score_7) + Integer.parseInt(gross_score_8) + Integer.parseInt(gross_score_9);

                    g_up_total.setText(gross_total_up + "");


                    Log.v("stoke_id", stoke_play_id);

                    if (stoke_play_id.equalsIgnoreCase("5") || stoke_play_id.equalsIgnoreCase("6") || stoke_play_id.equalsIgnoreCase("7")) {
                        positionTextUP.setText("STABLEFORD");
                        positionTextDown.setText("STABLEFORD");
                        String stableford_1 = player_hole_score.getJSONObject(0).getString("hole_num_1");
                        pos_up_one.setText(stableford_1);

                        String stableford_2 = player_hole_score.getJSONObject(0).getString("hole_num_2");
                        pos_up_two.setText(stableford_2);
                        String stableford_3 = player_hole_score.getJSONObject(0).getString("hole_num_3");
                        pos_up_three.setText(stableford_3);
                        String stableford_4 = player_hole_score.getJSONObject(0).getString("hole_num_4");
                        pos_up_four.setText(stableford_4);
                        String stableford_5 = player_hole_score.getJSONObject(0).getString("hole_num_5");
                        pos_up_five.setText(stableford_5);
                        String stableford_6 = player_hole_score.getJSONObject(0).getString("hole_num_6");
                        pos_up_six.setText(stableford_6);
                        String stableford_7 = player_hole_score.getJSONObject(0).getString("hole_num_7");
                        pos_up_seven.setText(stableford_7);
                        String stableford_8 = player_hole_score.getJSONObject(0).getString("hole_num_8");
                        pos_up_eight.setText(stableford_8);
                        String stableford_9 = player_hole_score.getJSONObject(0).getString("hole_num_9");
                        pos_up_nine.setText(stableford_9);


                    } else {

                        positionTextUP.setText("POSITION");
                        positionTextDown.setText("POSITION");

                        String position_1 = player_hole_score.getJSONObject(0).getString("position_1");
                        pos_up_one.setText(position_1);
                        String position_2 = player_hole_score.getJSONObject(0).getString("position_2");
                        pos_up_two.setText(position_2);
                        String position_3 = player_hole_score.getJSONObject(0).getString("position_3");
                        pos_up_three.setText(position_3);
                        String position_4 = player_hole_score.getJSONObject(0).getString("position_4");
                        pos_up_four.setText(position_4);
                        String position_5 = player_hole_score.getJSONObject(0).getString("position_5");
                        pos_up_five.setText(position_5);
                        String position_6 = player_hole_score.getJSONObject(0).getString("position_6");
                        pos_up_six.setText(position_6);
                        String position_7 = player_hole_score.getJSONObject(0).getString("position_7");
                        pos_up_seven.setText(position_7);
                        String position_8 = player_hole_score.getJSONObject(0).getString("position_8");
                        pos_up_eight.setText(position_8);
                        String position_9 = player_hole_score.getJSONObject(0).getString("position_9");

                        pos_up_nine.setText(position_9);

                        pos_up_total.setText(position_9 + "");

                    }

                    String color_1 = player_hole_score.getJSONObject(0).getString("hole_color_1");

                    if (color_1.equalsIgnoreCase("#ffffff")){
                        color_lay1.setBackgroundResource(R.drawable.cell_default);
                        g_up_one.setTextColor(Color.BLACK);
                    }else {
                        color_lay1.setBackgroundColor(Color.parseColor(color_1));
                    }

                    String color_2 = player_hole_score.getJSONObject(0).getString("hole_color_2");

                    if (color_2.equalsIgnoreCase("#ffffff")){
                        color_lay2.setBackgroundResource(R.drawable.cell_default);
                        g_up_two.setTextColor(Color.BLACK);
                    }else {
                        color_lay2.setBackgroundColor(Color.parseColor(color_2));
                    }

                    String color_3 = player_hole_score.getJSONObject(0).getString("hole_color_3");
                    if (color_3.equalsIgnoreCase("#ffffff")){
                        color_lay3.setBackgroundResource(R.drawable.cell_default);
                        g_up_three.setTextColor(Color.BLACK);
                    }else {
                        color_lay3.setBackgroundColor(Color.parseColor(color_3));
                    }
                    String color_4 = player_hole_score.getJSONObject(0).getString("hole_color_4");
                    if (color_4.equalsIgnoreCase("#ffffff")){
                        color_lay4.setBackgroundResource(R.drawable.cell_default);
                        g_up_four.setTextColor(Color.BLACK);
                    }else {
                        color_lay4.setBackgroundColor(Color.parseColor(color_4));
                    }
                    String color_5 = player_hole_score.getJSONObject(0).getString("hole_color_5");
                    if (color_5.equalsIgnoreCase("#ffffff")){
                        color_lay5.setBackgroundResource(R.drawable.cell_default);
                        g_up_five.setTextColor(Color.BLACK);
                    }else {
                        color_lay5.setBackgroundColor(Color.parseColor(color_5));
                    }
                    String color_6 = player_hole_score.getJSONObject(0).getString("hole_color_6");
                    if (color_6.equalsIgnoreCase("#ffffff")){
                        color_lay6.setBackgroundResource(R.drawable.cell_default);
                        g_up_six.setTextColor(Color.BLACK);
                    }else {
                        color_lay6.setBackgroundColor(Color.parseColor(color_6));
                    }
                    String color_7 = player_hole_score.getJSONObject(0).getString("hole_color_7");
                    if (color_7.equalsIgnoreCase("#ffffff")){
                        color_lay7.setBackgroundResource(R.drawable.cell_default);
                        g_up_seven.setTextColor(Color.BLACK);
                    }else {
                        color_lay7.setBackgroundColor(Color.parseColor(color_7));
                    }
                    String color_8 = player_hole_score.getJSONObject(0).getString("hole_color_8");
                    if (color_8.equalsIgnoreCase("#ffffff")){
                        color_lay8.setBackgroundResource(R.drawable.cell_default);
                        g_up_eight.setTextColor(Color.BLACK);
                    }else {
                        color_lay8.setBackgroundColor(Color.parseColor(color_8));
                    }
                    String color_9 = player_hole_score.getJSONObject(0).getString("hole_color_9");

                    if (color_9.equalsIgnoreCase("#ffffff")){
                        color_lay9.setBackgroundResource(R.drawable.cell_default);
                        g_up_nine.setTextColor(Color.BLACK);
                    }else {
                        color_lay9.setBackgroundColor(Color.parseColor(color_9));
                    }

                } else {

                    fronLayout.setVisibility(View.GONE);
                    backLayout.setVisibility(View.VISIBLE);

                    String parvalue10 = data.getString("par_value_10");
                    par_down_one.setText(parvalue10);
                    String parvalue11 = data.getString("par_value_11");
                    par_down_two.setText(parvalue11);
                    String parvalue12 = data.getString("par_value_12");
                    par_down_three.setText(parvalue12);
                    String parvalue13 = data.getString("par_value_13");
                    par_down_four.setText(parvalue13);
                    String parvalue14 = data.getString("par_value_14");
                    par_down_five.setText(parvalue14);
                    String parvalue15 = data.getString("par_value_15");
                    par_down_six.setText(parvalue15);
                    String parvalue16 = data.getString("par_value_16");
                    par_down_seven.setText(parvalue16);
                    String parvalue17 = data.getString("par_value_17");
                    par_down_eight.setText(parvalue17);
                    String parvalue18 = data.getString("par_value_18");
                    par_down_nine.setText(parvalue18);

                    int partotal_down_9 = Integer.parseInt(parvalue10) + Integer.parseInt(parvalue11) + Integer.parseInt(parvalue12) + Integer.parseInt(parvalue13) + Integer.parseInt(parvalue14) + Integer.parseInt(parvalue15) + Integer.parseInt(parvalue16) + Integer.parseInt(parvalue17) + Integer.parseInt(parvalue18);

                    String total_down_par = partotal_down_9 + "";

                    Log.e("parback9", total_down_par);

                    par_back_total.setText(total_down_par);


                    String eagle_index = data.getString("eagle_counter");
                    eagle_plus_index.setText(eagle_index);
                    String birdie_index = data.getString("birdie_counter");
                    bardie_index.setText(birdie_index);
                    String par_index1 = data.getString("par_counter");
                    par_index.setText(par_index1);
                    String bogey_index1 = data.getString("bogey_counter");
                    bogey_index.setText(bogey_index1);
                    String d_bogey_index1 = data.getString("doublebogey_counter");
                    d_bogey_index.setText(d_bogey_index1);


                    String hole_index_10 = data.getString("hole_index_10");
                    ind_down_one.setText(hole_index_10);
                    String hole_index_11 = data.getString("hole_index_11");
                    ind_down_two.setText(hole_index_11);
                    String hole_index_12 = data.getString("hole_index_12");
                    ind_down_three.setText(hole_index_12);
                    String hole_index_13 = data.getString("hole_index_13");
                    ind_down_four.setText(hole_index_13);
                    String hole_index_14 = data.getString("hole_index_14");
                    ind_down_five.setText(hole_index_14);
                    String hole_index_15 = data.getString("hole_index_15");
                    ind_down_six.setText(hole_index_15);
                    String hole_index_16 = data.getString("hole_index_16");
                    ind_down_seven.setText(hole_index_16);
                    String hole_index_17 = data.getString("hole_index_17");
                    ind_down_eight.setText(hole_index_17);
                    String hole_index_18 = data.getString("hole_index_18");
                    ind_down_nine.setText(hole_index_18);

                    String stoke_play_id = data.getString("event_stroke_play_id");
                    JSONArray player_hole_score = data.getJSONArray("player_hole_score");

                    if (stoke_play_id.equalsIgnoreCase("5") || stoke_play_id.equalsIgnoreCase("6") || stoke_play_id.equalsIgnoreCase("7")) {
                        gross_score_10 = player_hole_score.getJSONObject(0).getString("gross_score_10");
                        gross_score_11 = player_hole_score.getJSONObject(0).getString("gross_score_11");
                        gross_score_12 = player_hole_score.getJSONObject(0).getString("gross_score_12");
                        gross_score_13 = player_hole_score.getJSONObject(0).getString("gross_score_13");
                        gross_score_14 = player_hole_score.getJSONObject(0).getString("gross_score_14");
                        gross_score_15 = player_hole_score.getJSONObject(0).getString("gross_score_15");
                        gross_score_16 = player_hole_score.getJSONObject(0).getString("gross_score_16");
                        gross_score_17 = player_hole_score.getJSONObject(0).getString("gross_score_17");
                        gross_score_18 = player_hole_score.getJSONObject(0).getString("gross_score_18");
                    }else {
                        gross_score_10 = player_hole_score.getJSONObject(0).getString("hole_num_10");
                        gross_score_11 = player_hole_score.getJSONObject(0).getString("hole_num_11");
                        gross_score_12 = player_hole_score.getJSONObject(0).getString("hole_num_12");
                        gross_score_13 = player_hole_score.getJSONObject(0).getString("hole_num_13");
                        gross_score_14 = player_hole_score.getJSONObject(0).getString("hole_num_14");
                        gross_score_15 = player_hole_score.getJSONObject(0).getString("hole_num_15");
                        gross_score_16 = player_hole_score.getJSONObject(0).getString("hole_num_16");
                        gross_score_17 = player_hole_score.getJSONObject(0).getString("hole_num_17");
                        gross_score_18 = player_hole_score.getJSONObject(0).getString("hole_num_18");
                    }


                    if (gross_score_10 != null) {
                        g_down_one.setText(gross_score_10);
                    }
                    if (gross_score_11 != null) {
                        g_down_two.setText(gross_score_11);
                    }
                    if (gross_score_12 != null) {
                        g_down_three.setText(gross_score_12);
                    }
                    if (gross_score_13 != null) {
                        g_down_four.setText(gross_score_13);
                    }
                    if (gross_score_14 != null) {
                        g_down_five.setText(gross_score_14);
                    }
                    if (gross_score_15 != null) {
                        g_down_six.setText(gross_score_15);
                    }
                    if (gross_score_16 != null) {
                        g_down_seven.setText(gross_score_16);
                    }
                    if (gross_score_17 != null) {
                        g_down_eight.setText(gross_score_17);
                    }
                    if (gross_score_18 != null) {
                        g_down_nine.setText(gross_score_18);
                    }


                    int gross_total_down = Integer.parseInt(gross_score_10) + Integer.parseInt(gross_score_11) + Integer.parseInt(gross_score_12) + Integer.parseInt(gross_score_13) + Integer.parseInt(gross_score_14) + Integer.parseInt(gross_score_15) + Integer.parseInt(gross_score_16) + Integer.parseInt(gross_score_17) + Integer.parseInt(gross_score_18);

                    g_back_total.setText(gross_total_down + "");


                    Log.v("stoke_id", stoke_play_id);

                    if (stoke_play_id.equalsIgnoreCase("5") || stoke_play_id.equalsIgnoreCase("6") || stoke_play_id.equalsIgnoreCase("7")) {
                        positionTextUP.setText("STABLEFORD");
                        positionTextDown.setText("STABLEFORD");

                        String stableford_10 = player_hole_score.getJSONObject(0).getString("hole_num_10");
                        pos_down_one.setText(stableford_10);
                        String stableford_11 = player_hole_score.getJSONObject(0).getString("hole_num_11");
                        pos_down_two.setText(stableford_11);
                        String stableford_12 = player_hole_score.getJSONObject(0).getString("hole_num_12");
                        pos_down_three.setText(stableford_12);
                        String stableford_13 = player_hole_score.getJSONObject(0).getString("hole_num_13");
                        pos_down_four.setText(stableford_13);
                        String stableford_14 = player_hole_score.getJSONObject(0).getString("hole_num_14");
                        pos_down_five.setText(stableford_14);
                        String stableford_15 = player_hole_score.getJSONObject(0).getString("hole_num_15");
                        pos_down_six.setText(stableford_15);
                        String stableford_16 = player_hole_score.getJSONObject(0).getString("hole_num_16");
                        pos_down_seven.setText(stableford_16);
                        String stableford_17 = player_hole_score.getJSONObject(0).getString("hole_num_17");
                        pos_down_eight.setText(stableford_17);
                        String stableford_18 = player_hole_score.getJSONObject(0).getString("hole_num_18");
                        pos_down_nine.setText(stableford_18);


                    } else {

                        positionTextUP.setText("POSITION");
                        positionTextDown.setText("POSITION");

                        String position_10 = player_hole_score.getJSONObject(0).getString("position_10");
                        pos_down_one.setText(position_10);
                        String position_11 = player_hole_score.getJSONObject(0).getString("position_11");
                        pos_down_two.setText(position_11);
                        String position_12 = player_hole_score.getJSONObject(0).getString("position_12");
                        pos_down_three.setText(position_12);
                        String position_13 = player_hole_score.getJSONObject(0).getString("position_13");
                        pos_down_four.setText(position_13);
                        String position_14 = player_hole_score.getJSONObject(0).getString("position_14");
                        pos_down_five.setText(position_14);
                        String position_15 = player_hole_score.getJSONObject(0).getString("position_15");
                        pos_down_six.setText(position_15);
                        String position_16 = player_hole_score.getJSONObject(0).getString("position_16");
                        pos_down_seven.setText(position_16);
                        String position_17 = player_hole_score.getJSONObject(0).getString("position_17");
                        pos_down_eight.setText(position_17);
                        String position_18 = player_hole_score.getJSONObject(0).getString("position_18");
                        pos_down_nine.setText(position_18);

                        pos_back_total.setText(position_18 + "");

                        Log.e("pos_down_total", position_18 + "");
                        pos_down_total.setText(position_18 + "");

                    }

                    String color_10 = player_hole_score.getJSONObject(0).getString("hole_color_10");

                    if (color_10.equalsIgnoreCase("#ffffff")){
                        color_lay10.setBackgroundResource(R.drawable.cell_default);
                        g_down_one.setTextColor(Color.BLACK);
                    }else {
                        color_lay10.setBackgroundColor(Color.parseColor(color_10));
                    }

                    String color_11 = player_hole_score.getJSONObject(0).getString("hole_color_11");
                    if (color_11.equalsIgnoreCase("#ffffff")){
                        color_lay11.setBackgroundResource(R.drawable.cell_default);
                        g_down_two.setTextColor(Color.BLACK);
                    }else {
                        color_lay11.setBackgroundColor(Color.parseColor(color_11));
                    }
                    String color_12 = player_hole_score.getJSONObject(0).getString("hole_color_12");
                    if (color_12.equalsIgnoreCase("#ffffff")){
                        color_lay12.setBackgroundResource(R.drawable.cell_default);
                        g_down_three.setTextColor(Color.BLACK);
                    }else {
                        color_lay12.setBackgroundColor(Color.parseColor(color_12));
                    }
                    String color_13 = player_hole_score.getJSONObject(0).getString("hole_color_13");
                    if (color_13.equalsIgnoreCase("#ffffff")){
                        color_lay13.setBackgroundResource(R.drawable.cell_default);
                        g_down_four.setTextColor(Color.BLACK);
                    }else {
                        color_lay13.setBackgroundColor(Color.parseColor(color_13));
                    }
                    String color_14 = player_hole_score.getJSONObject(0).getString("hole_color_14");
                    if (color_14.equalsIgnoreCase("#ffffff")){
                        color_lay14.setBackgroundResource(R.drawable.cell_default);
                        g_down_five.setTextColor(Color.BLACK);
                    }else {
                        color_lay14.setBackgroundColor(Color.parseColor(color_14));
                    }
                    String color_15 = player_hole_score.getJSONObject(0).getString("hole_color_15");
                    if (color_15.equalsIgnoreCase("#ffffff")){
                        color_lay15.setBackgroundResource(R.drawable.cell_default);
                        g_down_six.setTextColor(Color.BLACK);
                    }else {
                        color_lay15.setBackgroundColor(Color.parseColor(color_15));
                    }
                    String color_16 = player_hole_score.getJSONObject(0).getString("hole_color_16");
                    if (color_16.equalsIgnoreCase("#ffffff")){
                        color_lay16.setBackgroundResource(R.drawable.cell_default);
                        g_down_seven.setTextColor(Color.BLACK);
                    }else {
                        color_lay16.setBackgroundColor(Color.parseColor(color_16));
                    }
                    String color_17 = player_hole_score.getJSONObject(0).getString("hole_color_17");
                    if (color_17.equalsIgnoreCase("#ffffff")){
                        color_lay17.setBackgroundResource(R.drawable.cell_default);
                        g_down_eight.setTextColor(Color.BLACK);
                    }else {
                        color_lay17.setBackgroundColor(Color.parseColor(color_17));
                    }
                    String color_18 = player_hole_score.getJSONObject(0).getString("hole_color_18");
                    if (color_18.equalsIgnoreCase("#ffffff")){
                        color_lay18.setBackgroundResource(R.drawable.cell_default);
                        g_down_nine.setTextColor(Color.BLACK);
                    }else {
                        color_lay18.setBackgroundColor(Color.parseColor(color_18));
                    }
                }

            }

            if (totalPlayer!=null && totalPlayer.equalsIgnoreCase("1")){
                line.setVisibility(View.GONE);
                score_leaderboard.setVisibility(View.GONE);
            }else {
                line.setVisibility(View.VISIBLE);
                score_leaderboard.setVisibility(View.VISIBLE);
            }


        } catch (JSONException je)

        {
            je.printStackTrace();
        }
    }
}