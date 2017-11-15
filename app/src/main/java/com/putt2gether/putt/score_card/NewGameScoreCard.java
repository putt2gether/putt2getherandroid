package com.putt2gether.putt.score_card;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.Adapter21;
import com.putt2gether.adapter.Adapter420;
import com.putt2gether.adapter.NoInternetConnectionAdapter;
import com.putt2gether.bean.Bean420;
import com.putt2gether.bean.ExpandBean;
import com.putt2gether.bean.InviteUserBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.HomeActivity;
import com.putt2gether.utils.Utility;
import com.putt2gether.view.LatoTextView;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 03/02/2017.
 */
public class NewGameScoreCard extends AppCompatActivity {

    RecyclerView dd21_RecyclerView;
    private RecyclerView.LayoutManager dd21_LayoutManager;
    String format_id;

    RelativeLayout parentNewGame;
    LatoTextView ind_up_one, ind_up_two, ind_up_three, ind_up_four, ind_up_five, ind_up_six, ind_up_seven, ind_up_eight, ind_up_nine;
    LatoTextView par_up_one, par_up_two, par_up_three, par_up_four, par_up_five, par_up_six, par_up_seven, par_up_eight, par_up_nine, par_up_total;

    LatoTextView player1_up_g1, player1_up_g2, player1_up_g3, player1_up_g4, player1_up_g5, player1_up_g6, player1_up_g7, player1_up_g8, player1_up_g9;
    LatoTextView player1_down_g1, player1_down_g2, player1_down_g3, player1_down_g4, player1_down_g5, player1_down_g6, player1_down_g7, player1_down_g8, player1_down_g9;

    LatoTextView player2_up_g1, player2_up_g2, player2_up_g3, player2_up_g4, player2_up_g5, player2_up_g6, player2_up_g7, player2_up_g8, player2_up_g9;
    LatoTextView player2_down_g1, player2_down_g2, player2_down_g3, player2_down_g4, player2_down_g5, player2_down_g6, player2_down_g7, player2_down_g8, player2_down_g9;

    LatoTextView player3_up_g1, player3_up_g2, player3_up_g3, player3_up_g4, player3_up_g5, player3_up_g6, player3_up_g7, player3_up_g8, player3_up_g9;
    LatoTextView player3_down_g1, player3_down_g2, player3_down_g3, player3_down_g4, player3_down_g5, player3_down_g6, player3_down_g7, player3_down_g8, player3_down_g9;

    LatoTextView player4_up_g1, player4_up_g2, player4_up_g3, player4_up_g4, player4_up_g5, player4_up_g6, player4_up_g7, player4_up_g8, player4_up_g9;
    LatoTextView player4_down_g1, player4_down_g2, player4_down_g3, player4_down_g4, player4_down_g5, player4_down_g6, player4_down_g7, player4_down_g8, player4_down_g9;


    LinearLayout player1_up_lay1, player1_up_lay2, player1_up_lay3, player1_up_lay4, player1_up_lay5, player1_up_lay6, player1_up_lay7, player1_up_lay8, player1_up_lay9, player1_up_layTotal;
    LinearLayout player1_down_lay1, player1_down_lay2, player1_down_lay3, player1_down_lay4, player1_down_lay5, player1_down_lay6, player1_down_lay7, player1_down_lay8, player1_down_lay9, player1_down_layTotal;

    LinearLayout player3_up_lay1, player3_up_lay2, player3_up_lay3, player3_up_lay4, player3_up_lay5, player3_up_lay6, player3_up_lay7, player3_up_lay8, player3_up_lay9, player3_up_layTotal;
    LinearLayout player3_down_lay1, player3_down_lay2, player3_down_lay3, player3_down_lay4, player3_down_lay5, player3_down_lay6, player3_down_lay7, player3_down_lay8, player3_down_lay9, player3_down_layTotal;

    LinearLayout player2_up_lay1, player2_up_lay2, player2_up_lay3, player2_up_lay4, player2_up_lay5, player2_up_lay6, player2_up_lay7, player2_up_lay8, player2_up_lay9, player2_up_layTotal;
    LinearLayout player2_down_lay1, player2_down_lay2, player2_down_lay3, player2_down_lay4, player2_down_lay5, player2_down_lay6, player2_down_lay7, player2_down_lay8, player2_down_lay9, player2_down_layTotal;

    LinearLayout player4_up_lay1, player4_up_lay2, player4_up_lay3, player4_up_lay4, player4_up_lay5, player4_up_lay6, player4_up_lay7, player4_up_lay8, player4_up_lay9, player4_up_layTotal;
    LinearLayout player4_down_lay1, player4_down_lay2, player4_down_lay3, player4_down_lay4, player4_down_lay5, player4_down_lay6, player4_down_lay7, player4_down_lay8, player4_down_lay9, player4_down_layTotal;


    LatoTextView ind_down_one, ind_down_two, ind_down_three, ind_down_four, ind_down_five, ind_down_six, ind_down_seven, ind_down_eight, ind_down_nine;
    LatoTextView par_down_one, par_down_two, par_down_three, par_down_four, par_down_five, par_down_six, par_down_seven, par_down_eight, par_down_nine, par_back_total, par_down_total;

    LinearLayout color_lay1, color_lay2, color_lay3, color_lay4, color_lay5, color_lay6, color_lay7, color_lay8, color_lay9, color_lay10, color_lay11, color_lay12, color_lay13, color_lay14, color_lay15, color_lay16, color_lay17, color_lay18;

    LatoTextView positionTextUP, positionTextDown;

    int p1_s1, p1_s2, p1_s3, p1_s4, p1_s5, p1_s6, p1_s7, p1_s8, p1_s9, p1_s10, p1_s11, p1_s12, p1_s13, p1_s14, p1_s15, p1_s16, p1_s17, p1_s18;
    int p2_s1, p2_s2, p2_s3, p2_s4, p2_s5, p2_s6, p2_s7, p2_s8, p2_s9, p2_s10, p2_s11, p2_s12, p2_s13, p2_s14, p2_s15, p2_s16, p2_s17, p2_s18;
    int p3_s1, p3_s2, p3_s3, p3_s4, p3_s5, p3_s6, p3_s7, p3_s8, p3_s9, p3_s10, p3_s11, p3_s12, p3_s13, p3_s14, p3_s15, p3_s16, p3_s17, p3_s18;
    int p4_s1, p4_s2, p4_s3, p4_s4, p4_s5, p4_s6, p4_s7, p4_s8, p4_s9, p4_s10, p4_s11, p4_s12, p4_s13, p4_s14, p4_s15, p4_s16, p4_s17, p4_s18;
    int par_s1, par_s2, par_s3, par_s4, par_s5, par_s6, par_s7, par_s8, par_s9, par_s10, par_s11, par_s12, par_s13, par_s14, par_s15, par_s16, par_s17, par_s18;
    int index_s1, index_s2, index_s3, index_s4, index_s5, index_s6, index_s7, index_s8, index_s9, index_s10, index_s11, index_s12, index_s13, index_s14, index_s15, index_s16, index_s17, index_s18;


    TableLayout fronLayout;
    TableLayout backLayout;


    LatoTextView player1_name_up, player1_name_down, player1_up_total, player1_down_total, player1_total;
    LatoTextView player2_name_up, player2_name_down, player2_up_total, player2_down_total, player2_total;
    LatoTextView player3_name_up, player3_name_down, player3_up_total, player3_down_total, player3_total;
    LatoTextView player4_name_up, player4_name_down, player4_up_total, player4_down_total, player4_total;


    String player1_id, player1_Name, player1_handicap, player1_color, team_id1;
    String player2_id, player2_Name, player2_handicap, player2_color, team_id2;
    String player3_id, player3_Name, player3_handicap, player3_color;
    String player4_id, player4_Name, player4_handicap, player4_color;
    String teamAname, teamBname;

    LinearLayout hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9, hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18;
    LatoTextView holeText1, holeText2, holeText3, holeText4, holeText5, holeText6, holeText7, holeText8, holeText9, holeText10, holeText11, holeText12, holeText13, holeText14, holeText15, holeText16, holeText17, holeText18;

    LatoTextView standingName_up, standingName_down, index_upTotal, index_downTotal, indexTotal;
    LatoTextView standings1, standings2, standings3, standings4, standings5, standings6, standings7, standings8, standings9;
    LatoTextView standings10, standings12, standings13, standings14, standings15, standings16, standings17, standings18, standings11;

    TableRow player1_up_row, player2_up_row, player3_up_row, player4_up_row, standings_up_row;
    TableRow player1_down_row, player2_down_row, player3_down_row, player4_down_row, standings_down_row;
    private LatoTextView eventName, golfCourseName, parTotal;
    private ImageView backBTN;
    private LinearLayout allTable;
    private LinearLayout player1LayoutBack, player2LayoutBack, player3LayoutBack;
    private LinearLayout player1uLayoutBack, player2uLayoutBack, player3uLayoutBack;
    LatoTextView teamAPlayer1, teamAPlayer2, teamBPlayer1, teamBPlayer2;
    LatoTextView teamTabA, teamTabB;
    LatoTextView tabWinnerStandings, thruHole;
    LinearLayout scoreCardTab;
    LinearLayout layout420_score;
    TextView player1_420_score, player2_420_score, player3_420_score;
    TextView player1_420_name, player2_420_name, player3_420_name;
    LinearLayout winnerBackGround;
    String winner1, winner2;
    String current_winner;
    LinearLayout addScoreBTN;
    String eventID;
    LinearLayout shareBTN;
    String handicap_value = "";
    String hadicap_value2 = "";
    String hadicap_value = "";
    LinearLayout player1bg_420;
    String from;

    ImageView bannerImage;
    String banner_id;
    String banner_url;
    String banner_img;

    LinearLayout stats_btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game_scorecard);

        stats_btn = (LinearLayout)findViewById(R.id.stats_btn);
        String statsType = getIntent().getStringExtra("stats_visible");
        if (statsType!=null && statsType.equalsIgnoreCase("yes")){

            stats_btn.setVisibility(View.VISIBLE);
        }

        shareBTN = (LinearLayout) findViewById(R.id.shareScoreCard);

        bannerImage = (ImageView)findViewById(R.id.banner_newscorecard);
        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (banner_url != null && banner_url.length() > 10) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(banner_url));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), " Please install a web browser", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), " URL not found.", Toast.LENGTH_LONG).show();
                }

            }
        });


        parentNewGame = (RelativeLayout) findViewById(R.id.parent_newgame);
        winnerBackGround = (LinearLayout) findViewById(R.id.team_winner_layout);

        player3_up_total = (LatoTextView) findViewById(R.id.player3_up_total);
        player3_down_total = (LatoTextView) findViewById(R.id.player3_down_total);
        player3_total = (LatoTextView) findViewById(R.id.player3_total);


        layout420_score = (LinearLayout) findViewById(R.id.layout_340_scorcard);


        player1_420_name = (TextView) findViewById(R.id.player1_420name);
        player2_420_name = (TextView) findViewById(R.id.player2_420name);
        player3_420_name = (TextView) findViewById(R.id.player3_420name);
        player1_420_score = (TextView) findViewById(R.id.player1_420score);
        player2_420_score = (TextView) findViewById(R.id.player2_420score);
        player3_420_score = (TextView) findViewById(R.id.player3_420score);

        index_downTotal = (LatoTextView) findViewById(R.id.index_down_total);
        index_upTotal = (LatoTextView) findViewById(R.id.index_up_total);
        indexTotal = (LatoTextView) findViewById(R.id.index_total);

        hole1 = (LinearLayout) findViewById(R.id.hole_up_one);
        hole2 = (LinearLayout) findViewById(R.id.hole_up_two);
        hole3 = (LinearLayout) findViewById(R.id.hole_up_three);
        hole4 = (LinearLayout) findViewById(R.id.hole_up_four);
        hole5 = (LinearLayout) findViewById(R.id.hole_up_five);
        hole6 = (LinearLayout) findViewById(R.id.hole_up_six);
        hole7 = (LinearLayout) findViewById(R.id.hole_up_seven);
        hole8 = (LinearLayout) findViewById(R.id.hole_up_eight);
        hole9 = (LinearLayout) findViewById(R.id.hole_up_nine);

        hole10 = (LinearLayout) findViewById(R.id.hole_down_one);
        hole11 = (LinearLayout) findViewById(R.id.hole_down_two);
        hole12 = (LinearLayout) findViewById(R.id.hole_down_three);
        hole13 = (LinearLayout) findViewById(R.id.hole_down_four);
        hole14 = (LinearLayout) findViewById(R.id.hole_down_five);
        hole15 = (LinearLayout) findViewById(R.id.hole_down_six);
        hole16 = (LinearLayout) findViewById(R.id.hole_down_seven);
        hole17 = (LinearLayout) findViewById(R.id.hole_down_eight);
        hole18 = (LinearLayout) findViewById(R.id.hole_down_nine);

        holeText1 = (LatoTextView) findViewById(R.id.hole1_text);
        holeText2 = (LatoTextView) findViewById(R.id.hole2_text);
        holeText3 = (LatoTextView) findViewById(R.id.hole3_text);
        holeText4 = (LatoTextView) findViewById(R.id.hole4_text);
        holeText5 = (LatoTextView) findViewById(R.id.hole5_text);
        holeText6 = (LatoTextView) findViewById(R.id.hole6_text);
        holeText7 = (LatoTextView) findViewById(R.id.hole7_text);
        holeText8 = (LatoTextView) findViewById(R.id.hole8_text);
        holeText9 = (LatoTextView) findViewById(R.id.hole9_text);
        holeText10 = (LatoTextView) findViewById(R.id.hole10_text);
        holeText11 = (LatoTextView) findViewById(R.id.hole11_text);
        holeText12 = (LatoTextView) findViewById(R.id.hole12_text);
        holeText13 = (LatoTextView) findViewById(R.id.hole13_text);
        holeText14 = (LatoTextView) findViewById(R.id.hole14_text);
        holeText15 = (LatoTextView) findViewById(R.id.hole15_text);
        holeText16 = (LatoTextView) findViewById(R.id.hole16_text);
        holeText17 = (LatoTextView) findViewById(R.id.hole17_text);
        holeText18 = (LatoTextView) findViewById(R.id.hole18_text);

        player1bg_420 = (LinearLayout) findViewById(R.id.player1_420_bg);


        addScoreBTN = (LinearLayout) findViewById(R.id.bottom_scorecard_new);

        shareBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                getScreenShot(rootView);

            }
        });


        from = getIntent().getStringExtra("from");
        if (from != null) {
            addScoreBTN.setVisibility(View.GONE);
        } else {
            addScoreBTN.setVisibility(View.VISIBLE);
        }

        scoreCardTab = (LinearLayout) findViewById(R.id.new_scorcard_tab);

        standingName_up = (LatoTextView) findViewById(R.id.standings_name_up);

        standingName_down = (LatoTextView) findViewById(R.id.standings_name_down);

        fronLayout = (TableLayout) findViewById(R.id.tableFront9);
        backLayout = (TableLayout) findViewById(R.id.tableBack9);

        player1LayoutBack = (LinearLayout) findViewById(R.id.player1_lay_up);
        player2LayoutBack = (LinearLayout) findViewById(R.id.player2_lay_up);
        player3LayoutBack = (LinearLayout) findViewById(R.id.player3_lay_up);
        player1uLayoutBack = (LinearLayout) findViewById(R.id.player1_lay_down);
        player2uLayoutBack = (LinearLayout) findViewById(R.id.player2_lay_down);
        player3uLayoutBack = (LinearLayout) findViewById(R.id.player3_lay_down);

        allTable = (LinearLayout) findViewById(R.id.table_all_new);

        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        final String admin_ID = pref.getString("userId", null);
        eventID = getIntent().getStringExtra("eventID");

        Log.v("EventID", eventID);


        player1LayoutBack = (LinearLayout) findViewById(R.id.player1_lay_up);
        player2LayoutBack = (LinearLayout) findViewById(R.id.player2_lay_up);

        eventName = (LatoTextView) findViewById(R.id.event_name_new_scoretable);
        golfCourseName = (LatoTextView) findViewById(R.id.golf_course_address_new_scoretable);
        backBTN = (ImageView) findViewById(R.id.back_new_scorecard);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stackClear = getIntent().getStringExtra("from");
                if (stackClear != null && stackClear.equalsIgnoreCase("delegate")) {

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("eventID", eventID);
                    intent.putExtra("fromEventPreview", "1");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    finish();
                }
            }
        });

        player1_name_up = (LatoTextView) findViewById(R.id.player1_name_up);
        player2_name_up = (LatoTextView) findViewById(R.id.player2_name_up);
        player3_name_up = (LatoTextView) findViewById(R.id.player3_name_up);
        player4_name_up = (LatoTextView) findViewById(R.id.player4_name_up);

        player1_name_down = (LatoTextView) findViewById(R.id.player1_name_down);
        player2_name_down = (LatoTextView) findViewById(R.id.player2_name_down);
        player3_name_down = (LatoTextView) findViewById(R.id.player3_name_down);
        player4_name_down = (LatoTextView) findViewById(R.id.player4_name_down);

        teamAPlayer1 = (LatoTextView) findViewById(R.id.teamA_player1);
        teamAPlayer2 = (LatoTextView) findViewById(R.id.teamA_player2);
        teamBPlayer1 = (LatoTextView) findViewById(R.id.teamB_player1);
        teamBPlayer2 = (LatoTextView) findViewById(R.id.teamB_player2);

        teamTabA = (LatoTextView) findViewById(R.id.teamA_tabName);
        teamTabB = (LatoTextView) findViewById(R.id.teamB_tabName);

        tabWinnerStandings = (LatoTextView) findViewById(R.id.tab_team_position);
        thruHole = (LatoTextView) findViewById(R.id.tab_thru_hole);


        standings1 = (LatoTextView) findViewById(R.id.standigs1);
        standings2 = (LatoTextView) findViewById(R.id.standings2);
        standings3 = (LatoTextView) findViewById(R.id.standigs3);

        standings4 = (LatoTextView) findViewById(R.id.standigs4);
        standings5 = (LatoTextView) findViewById(R.id.standigs5);
        standings6 = (LatoTextView) findViewById(R.id.standigs6);

        standings7 = (LatoTextView) findViewById(R.id.standigs7);
        standings8 = (LatoTextView) findViewById(R.id.standigs8);
        standings9 = (LatoTextView) findViewById(R.id.standigs9);

        standings10 = (LatoTextView) findViewById(R.id.standigs10);
        standings11 = (LatoTextView) findViewById(R.id.standigs11);
        standings12 = (LatoTextView) findViewById(R.id.standigs12);

        standings13 = (LatoTextView) findViewById(R.id.standigs13);
        standings14 = (LatoTextView) findViewById(R.id.standigs14);
        standings15 = (LatoTextView) findViewById(R.id.standigs15);

        standings16 = (LatoTextView) findViewById(R.id.standigs16);
        standings17 = (LatoTextView) findViewById(R.id.standigs17);
        standings18 = (LatoTextView) findViewById(R.id.standigs18);


        player1_up_total = (LatoTextView) findViewById(R.id.player1_up_total);
        player2_up_total = (LatoTextView) findViewById(R.id.player2_up_total);
        player3_up_total = (LatoTextView) findViewById(R.id.player3_up_total);
        player4_up_total = (LatoTextView) findViewById(R.id.player4_up_total);

        player1_down_total = (LatoTextView) findViewById(R.id.player1_down_total);
        player2_down_total = (LatoTextView) findViewById(R.id.player2_down_total);
        player3_down_total = (LatoTextView) findViewById(R.id.player3_down_total);
        player4_down_total = (LatoTextView) findViewById(R.id.player4_down_total);

        player1_total = (LatoTextView) findViewById(R.id.player1_total);
        player2_total = (LatoTextView) findViewById(R.id.player2_total);
        player3_total = (LatoTextView) findViewById(R.id.player3_total);
        player4_total = (LatoTextView) findViewById(R.id.player4_total);


        player1_up_row = (TableRow) findViewById(R.id.player1_row_up);
        player2_up_row = (TableRow) findViewById(R.id.player2_row_up);
        player3_up_row = (TableRow) findViewById(R.id.player3_row_up);
        player4_up_row = (TableRow) findViewById(R.id.player4_row_up);
        standings_up_row = (TableRow) findViewById(R.id.standings_row_up);

        player1_down_row = (TableRow) findViewById(R.id.player1_row_down);
        player2_down_row = (TableRow) findViewById(R.id.player2_row_down);
        player3_down_row = (TableRow) findViewById(R.id.player3_row_down);
        player4_down_row = (TableRow) findViewById(R.id.player4_row_down);
        standings_down_row = (TableRow) findViewById(R.id.standings_row_down);


        player1_up_g1 = (LatoTextView) findViewById(R.id.player1_Score1);
        player1_up_g2 = (LatoTextView) findViewById(R.id.player1_Score2);
        player1_up_g3 = (LatoTextView) findViewById(R.id.player1_Score3);
        player1_up_g4 = (LatoTextView) findViewById(R.id.player1_Score4);
        player1_up_g5 = (LatoTextView) findViewById(R.id.player1_Score5);
        player1_up_g6 = (LatoTextView) findViewById(R.id.player1_Score6);
        player1_up_g7 = (LatoTextView) findViewById(R.id.player1_Score7);
        player1_up_g8 = (LatoTextView) findViewById(R.id.player1_Score8);
        player1_up_g9 = (LatoTextView) findViewById(R.id.player1_Score9);
        player1_down_g1 = (LatoTextView) findViewById(R.id.player1_Score10);
        player1_down_g2 = (LatoTextView) findViewById(R.id.player1_Score11);
        player1_down_g3 = (LatoTextView) findViewById(R.id.player1_Score12);
        player1_down_g4 = (LatoTextView) findViewById(R.id.player1_Score13);
        player1_down_g5 = (LatoTextView) findViewById(R.id.player1_Score14);
        player1_down_g6 = (LatoTextView) findViewById(R.id.player1_Score15);
        player1_down_g7 = (LatoTextView) findViewById(R.id.player1_Score16);
        player1_down_g8 = (LatoTextView) findViewById(R.id.player1_Score17);
        player1_down_g9 = (LatoTextView) findViewById(R.id.player1_Score18);

        player2_up_g1 = (LatoTextView) findViewById(R.id.player2_Score1);
        player2_up_g2 = (LatoTextView) findViewById(R.id.player2_Score2);
        player2_up_g3 = (LatoTextView) findViewById(R.id.player2_Score3);
        player2_up_g4 = (LatoTextView) findViewById(R.id.player2_Score4);
        player2_up_g5 = (LatoTextView) findViewById(R.id.player2_Score5);
        player2_up_g6 = (LatoTextView) findViewById(R.id.player2_Score6);
        player2_up_g7 = (LatoTextView) findViewById(R.id.player2_Score7);
        player2_up_g8 = (LatoTextView) findViewById(R.id.player2_Score8);
        player2_up_g9 = (LatoTextView) findViewById(R.id.player2_Score9);
        player2_down_g1 = (LatoTextView) findViewById(R.id.player2_Score10);
        player2_down_g2 = (LatoTextView) findViewById(R.id.player2_Score11);
        player2_down_g3 = (LatoTextView) findViewById(R.id.player2_Score12);
        player2_down_g4 = (LatoTextView) findViewById(R.id.player2_Score13);
        player2_down_g5 = (LatoTextView) findViewById(R.id.player2_Score14);
        player2_down_g6 = (LatoTextView) findViewById(R.id.player2_Score15);
        player2_down_g7 = (LatoTextView) findViewById(R.id.player2_Score16);
        player2_down_g8 = (LatoTextView) findViewById(R.id.player2_Score17);
        player2_down_g9 = (LatoTextView) findViewById(R.id.player2_Score18);

        player3_up_g1 = (LatoTextView) findViewById(R.id.player3_Score1);
        player3_up_g2 = (LatoTextView) findViewById(R.id.player3_Score2);
        player3_up_g3 = (LatoTextView) findViewById(R.id.player3_Score3);
        player3_up_g4 = (LatoTextView) findViewById(R.id.player3_Score4);
        player3_up_g5 = (LatoTextView) findViewById(R.id.player3_Score5);
        player3_up_g6 = (LatoTextView) findViewById(R.id.player3_Score6);
        player3_up_g7 = (LatoTextView) findViewById(R.id.player3_Score7);
        player3_up_g8 = (LatoTextView) findViewById(R.id.player3_Score8);
        player3_up_g9 = (LatoTextView) findViewById(R.id.player3_Score9);
        player3_down_g1 = (LatoTextView) findViewById(R.id.player3_Score10);
        player3_down_g2 = (LatoTextView) findViewById(R.id.player3_Score11);
        player3_down_g3 = (LatoTextView) findViewById(R.id.player3_Score12);
        player3_down_g4 = (LatoTextView) findViewById(R.id.player3_Score13);
        player3_down_g5 = (LatoTextView) findViewById(R.id.player3_Score14);
        player3_down_g6 = (LatoTextView) findViewById(R.id.player3_Score15);
        player3_down_g7 = (LatoTextView) findViewById(R.id.player3_Score16);
        player3_down_g8 = (LatoTextView) findViewById(R.id.player3_Score17);
        player3_down_g9 = (LatoTextView) findViewById(R.id.player3_Score18);

        player4_up_g1 = (LatoTextView) findViewById(R.id.player4_Score1);
        player4_up_g2 = (LatoTextView) findViewById(R.id.player4_Score2);
        player4_up_g3 = (LatoTextView) findViewById(R.id.player4_Score3);
        player4_up_g4 = (LatoTextView) findViewById(R.id.player4_Score4);
        player4_up_g5 = (LatoTextView) findViewById(R.id.player4_Score5);
        player4_up_g6 = (LatoTextView) findViewById(R.id.player4_Score6);
        player4_up_g7 = (LatoTextView) findViewById(R.id.player4_Score7);
        player4_up_g8 = (LatoTextView) findViewById(R.id.player4_Score8);
        player4_up_g9 = (LatoTextView) findViewById(R.id.player4_Score9);
        player4_down_g1 = (LatoTextView) findViewById(R.id.player4_Score10);
        player4_down_g2 = (LatoTextView) findViewById(R.id.player4_Score11);
        player4_down_g3 = (LatoTextView) findViewById(R.id.player4_Score12);
        player4_down_g4 = (LatoTextView) findViewById(R.id.player4_Score13);
        player4_down_g5 = (LatoTextView) findViewById(R.id.player4_Score14);
        player4_down_g6 = (LatoTextView) findViewById(R.id.player4_Score15);
        player4_down_g7 = (LatoTextView) findViewById(R.id.player4_Score16);
        player4_down_g8 = (LatoTextView) findViewById(R.id.player4_Score17);
        player4_down_g9 = (LatoTextView) findViewById(R.id.player4_Score18);


        player1_up_lay1 = (LinearLayout) findViewById(R.id.player1_hole1_up);
        player1_up_lay2 = (LinearLayout) findViewById(R.id.player1_hole2_up);
        player1_up_lay3 = (LinearLayout) findViewById(R.id.player1_hole3_up);
        player1_up_lay4 = (LinearLayout) findViewById(R.id.player1_hole4_up);
        player1_up_lay5 = (LinearLayout) findViewById(R.id.player1_hole5_up);
        player1_up_lay6 = (LinearLayout) findViewById(R.id.player1_hole6_up);
        player1_up_lay7 = (LinearLayout) findViewById(R.id.player1_hole7_up);
        player1_up_lay8 = (LinearLayout) findViewById(R.id.player1_hole8_up);
        player1_up_lay9 = (LinearLayout) findViewById(R.id.player1_hole9_up);
        player1_down_lay1 = (LinearLayout) findViewById(R.id.player1_hole10_down);
        player1_down_lay2 = (LinearLayout) findViewById(R.id.player1_hole11_down);
        player1_down_lay3 = (LinearLayout) findViewById(R.id.player1_hole12_down);
        player1_down_lay4 = (LinearLayout) findViewById(R.id.player1_hole13_down);
        player1_down_lay5 = (LinearLayout) findViewById(R.id.player1_hole14_down);
        player1_down_lay6 = (LinearLayout) findViewById(R.id.player1_hole15_down);
        player1_down_lay7 = (LinearLayout) findViewById(R.id.player1_hole16_down);
        player1_down_lay8 = (LinearLayout) findViewById(R.id.player1_hole17_down);
        player1_down_lay9 = (LinearLayout) findViewById(R.id.player1_hole18_down);

        player2_up_lay1 = (LinearLayout) findViewById(R.id.player2_hole1_up);
        player2_up_lay2 = (LinearLayout) findViewById(R.id.player2_hole2_up);
        player2_up_lay3 = (LinearLayout) findViewById(R.id.player2_hole3_up);
        player2_up_lay4 = (LinearLayout) findViewById(R.id.player2_hole4_up);
        player2_up_lay5 = (LinearLayout) findViewById(R.id.player2_hole5_up);
        player2_up_lay6 = (LinearLayout) findViewById(R.id.player2_hole6_up);
        player2_up_lay7 = (LinearLayout) findViewById(R.id.player2_hole7_up);
        player2_up_lay8 = (LinearLayout) findViewById(R.id.player2_hole8_up);
        player2_up_lay9 = (LinearLayout) findViewById(R.id.player2_hole9_up);
        player2_down_lay1 = (LinearLayout) findViewById(R.id.player2_hole10_down);
        player2_down_lay2 = (LinearLayout) findViewById(R.id.player2_hole11_down);
        player2_down_lay3 = (LinearLayout) findViewById(R.id.player2_hole12_down);
        player2_down_lay4 = (LinearLayout) findViewById(R.id.player2_hole13_down);
        player2_down_lay5 = (LinearLayout) findViewById(R.id.player2_hole14_down);
        player2_down_lay6 = (LinearLayout) findViewById(R.id.player2_hole15_down);
        player2_down_lay7 = (LinearLayout) findViewById(R.id.player2_hole16_down);
        player2_down_lay8 = (LinearLayout) findViewById(R.id.player2_hole17_down);
        player2_down_lay9 = (LinearLayout) findViewById(R.id.player2_hole18_down);

        player3_up_lay1 = (LinearLayout) findViewById(R.id.player3_hole1_up);
        player3_up_lay2 = (LinearLayout) findViewById(R.id.player3_hole2_up);
        player3_up_lay3 = (LinearLayout) findViewById(R.id.player3_hole3_up);
        player3_up_lay4 = (LinearLayout) findViewById(R.id.player3_hole4_up);
        player3_up_lay5 = (LinearLayout) findViewById(R.id.player3_hole5_up);
        player3_up_lay6 = (LinearLayout) findViewById(R.id.player3_hole6_up);
        player3_up_lay7 = (LinearLayout) findViewById(R.id.player3_hole7_up);
        player3_up_lay8 = (LinearLayout) findViewById(R.id.player3_hole8_up);
        player3_up_lay9 = (LinearLayout) findViewById(R.id.player3_hole9_up);
        player3_down_lay1 = (LinearLayout) findViewById(R.id.player3_hole10_down);
        player3_down_lay2 = (LinearLayout) findViewById(R.id.player3_hole11_down);
        player3_down_lay3 = (LinearLayout) findViewById(R.id.player3_hole12_down);
        player3_down_lay4 = (LinearLayout) findViewById(R.id.player3_hole13_down);
        player3_down_lay5 = (LinearLayout) findViewById(R.id.player3_hole14_down);
        player3_down_lay6 = (LinearLayout) findViewById(R.id.player3_hole15_down);
        player3_down_lay7 = (LinearLayout) findViewById(R.id.player3_hole16_down);
        player3_down_lay8 = (LinearLayout) findViewById(R.id.player3_hole17_down);
        player3_down_lay9 = (LinearLayout) findViewById(R.id.player3_hole18_down);

        player4_up_lay1 = (LinearLayout) findViewById(R.id.player4_hole1_up);
        player4_up_lay2 = (LinearLayout) findViewById(R.id.player4_hole2_up);
        player4_up_lay3 = (LinearLayout) findViewById(R.id.player4_hole3_up);
        player4_up_lay4 = (LinearLayout) findViewById(R.id.player4_hole4_up);
        player4_up_lay5 = (LinearLayout) findViewById(R.id.player4_hole5_up);
        player4_up_lay6 = (LinearLayout) findViewById(R.id.player4_hole6_up);
        player4_up_lay7 = (LinearLayout) findViewById(R.id.player4_hole7_up);
        player4_up_lay8 = (LinearLayout) findViewById(R.id.player4_hole8_up);
        player4_up_lay9 = (LinearLayout) findViewById(R.id.player4_hole9_up);
        player4_down_lay1 = (LinearLayout) findViewById(R.id.player4_hole10_down);
        player4_down_lay2 = (LinearLayout) findViewById(R.id.player4_hole11_down);
        player4_down_lay3 = (LinearLayout) findViewById(R.id.player4_hole12_down);
        player4_down_lay4 = (LinearLayout) findViewById(R.id.player4_hole13_down);
        player4_down_lay5 = (LinearLayout) findViewById(R.id.player4_hole14_down);
        player4_down_lay6 = (LinearLayout) findViewById(R.id.player4_hole15_down);
        player4_down_lay7 = (LinearLayout) findViewById(R.id.player4_hole16_down);
        player4_down_lay8 = (LinearLayout) findViewById(R.id.player4_hole17_down);
        player4_down_lay9 = (LinearLayout) findViewById(R.id.player4_hole18_down);

        ind_up_one = (LatoTextView) findViewById(R.id.index_up_one);
        ind_up_two = (LatoTextView) findViewById(R.id.index_up_two);
        ind_up_three = (LatoTextView) findViewById(R.id.index_up_three);
        ind_up_four = (LatoTextView) findViewById(R.id.index_up_four);
        ind_up_five = (LatoTextView) findViewById(R.id.index_up_five);
        ind_up_six = (LatoTextView) findViewById(R.id.index_up_six);
        ind_up_seven = (LatoTextView) findViewById(R.id.index_up_seven);
        ind_up_eight = (LatoTextView) findViewById(R.id.index_up_eight);
        ind_up_nine = (LatoTextView) findViewById(R.id.index_up_nine);

        par_up_one = (LatoTextView) findViewById(R.id.par_up_one);
        par_up_two = (LatoTextView) findViewById(R.id.par_up_two);
        par_up_three = (LatoTextView) findViewById(R.id.par_up_three);
        par_up_four = (LatoTextView) findViewById(R.id.par_up_four);
        par_up_five = (LatoTextView) findViewById(R.id.par_up_five);
        par_up_six = (LatoTextView) findViewById(R.id.par_up_six);
        par_up_seven = (LatoTextView) findViewById(R.id.par_up_seven);
        par_up_eight = (LatoTextView) findViewById(R.id.par_up_eight);
        par_up_nine = (LatoTextView) findViewById(R.id.par_up_nine);

        par_up_total = (LatoTextView) findViewById(R.id.par_up_total);


        ind_down_one = (LatoTextView) findViewById(R.id.index_down_one);
        ind_down_two = (LatoTextView) findViewById(R.id.index_down_two);
        ind_down_three = (LatoTextView) findViewById(R.id.index_down_three);
        ind_down_four = (LatoTextView) findViewById(R.id.index_down_four);
        ind_down_five = (LatoTextView) findViewById(R.id.index_down_five);
        ind_down_six = (LatoTextView) findViewById(R.id.index_down_six);
        ind_down_seven = (LatoTextView) findViewById(R.id.index_down_seven);
        ind_down_eight = (LatoTextView) findViewById(R.id.index_down_eight);
        ind_down_nine = (LatoTextView) findViewById(R.id.index_down_nine);


        par_down_one = (LatoTextView) findViewById(R.id.par_down_one);
        par_down_two = (LatoTextView) findViewById(R.id.par_down_two);
        par_down_three = (LatoTextView) findViewById(R.id.par_down_three);
        par_down_four = (LatoTextView) findViewById(R.id.par_down_four);
        par_down_five = (LatoTextView) findViewById(R.id.par_down_five);
        par_down_six = (LatoTextView) findViewById(R.id.par_down_six);
        par_down_seven = (LatoTextView) findViewById(R.id.par_down_seven);
        par_down_eight = (LatoTextView) findViewById(R.id.par_down_eight);
        par_down_nine = (LatoTextView) findViewById(R.id.par_down_nine);

        par_back_total = (LatoTextView) findViewById(R.id.par_up_total);
        par_down_total = (LatoTextView) findViewById(R.id.par_down_total);
        parTotal = (LatoTextView) findViewById(R.id.par_total);


        stats_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScoreCardTemplate.class);
                intent.putExtra("event_id",eventID);
                intent.putExtra("player_id",admin_ID);
                startActivity(intent);
            }
        });

        ConnectionDetector con = new ConnectionDetector(this);
        if (con.isConnectingToInternet()) {
            getscorecardtable(eventID);
            getBannerList(eventID);
        }else {
            Toast.makeText(getApplicationContext(),"No internet connections.",Toast.LENGTH_SHORT).show();
        }

        layout420_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand420_Popup();
            }
        });

        scoreCardTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (format_id.equalsIgnoreCase("14")) {
                    expand21Popup();
                } else if (format_id.equalsIgnoreCase("13")) {
                    expand21Popup();
                }
            }
        });

    }

    public void getscorecardtable(String Event_Id) {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("version", "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GETLATESTFULLSCORE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                scoreCardResponse(response);


                Log.e("GETLATESTFULLSCORE", "GETLATESTFULLSCORE" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "GETLATESTFULLSCORE URL = " + PUTTAPI.GETLATESTFULLSCORE);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }


    public void scoreCardResponse(JSONObject response) {
        try {
            JSONObject output = response.getJSONObject("output");

            JSONObject data = output.getJSONObject("data");
            parentNewGame.setVisibility(View.VISIBLE);
            allTable.setVisibility(View.VISIBLE);

            String golf_CourseName = data.getString("golf_course_name");
            golfCourseName.setText(golf_CourseName);
            String event_Name = data.getString("event_name");
            eventName.setText(event_Name);
            String totalHole = data.getString("total_num_hole");

            String hole_start_from = data.getString("hole_start_from");

            if (hole_start_from.equalsIgnoreCase("1")) {
                hole1.setBackgroundColor(Color.BLACK);
                holeText1.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("2")) {
                hole2.setBackgroundColor(Color.BLACK);
                holeText2.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("3")) {
                hole3.setBackgroundColor(Color.BLACK);
                holeText3.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("4")) {
                hole4.setBackgroundColor(Color.BLACK);
                holeText4.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("5")) {
                hole5.setBackgroundColor(Color.BLACK);
                holeText5.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("6")) {
                hole6.setBackgroundColor(Color.BLACK);
                holeText6.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("7")) {
                hole7.setBackgroundColor(Color.BLACK);
                holeText7.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("8")) {
                hole8.setBackgroundColor(Color.BLACK);
                holeText8.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("9")) {
                hole9.setBackgroundColor(Color.BLACK);
                holeText9.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("10")) {
                hole10.setBackgroundColor(Color.BLACK);
                holeText10.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("11")) {
                hole11.setBackgroundColor(Color.BLACK);
                holeText11.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("12")) {
                hole12.setBackgroundColor(Color.BLACK);
                holeText12.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("13")) {
                hole13.setBackgroundColor(Color.BLACK);
                holeText13.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("14")) {
                hole14.setBackgroundColor(Color.BLACK);
                holeText14.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("15")) {
                hole15.setBackgroundColor(Color.BLACK);
                holeText15.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("16")) {
                hole16.setBackgroundColor(Color.BLACK);
                holeText16.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("17")) {
                hole17.setBackgroundColor(Color.BLACK);
                holeText17.setTextColor(Color.WHITE);
            } else if (hole_start_from.equalsIgnoreCase("18")) {
                hole18.setBackgroundColor(Color.BLACK);
                holeText18.setTextColor(Color.WHITE);
            }

            String event_id = data.getString("event_stroke_play_id");

            format_id = event_id;


            String is_team = data.getString("is_team");
            String totalPlayer = data.getString("total_player");
            if (totalPlayer.equalsIgnoreCase("4")) {
                player1_up_row.setVisibility(View.VISIBLE);
                player1_down_row.setVisibility(View.VISIBLE);
                player2_up_row.setVisibility(View.VISIBLE);
                player2_down_row.setVisibility(View.VISIBLE);
                player3_up_row.setVisibility(View.VISIBLE);
                player3_down_row.setVisibility(View.VISIBLE);
                player4_up_row.setVisibility(View.VISIBLE);
                player4_down_row.setVisibility(View.VISIBLE);

                standings_up_row.setVisibility(View.VISIBLE);
                standings_down_row.setVisibility(View.VISIBLE);
            } else if (totalPlayer.equalsIgnoreCase("2")) {

                player1_up_row.setVisibility(View.VISIBLE);
                player1_down_row.setVisibility(View.VISIBLE);
                player2_up_row.setVisibility(View.GONE);
                player2_down_row.setVisibility(View.GONE);

                player3_up_row.setVisibility(View.VISIBLE);
                player3_down_row.setVisibility(View.VISIBLE);
                player4_up_row.setVisibility(View.GONE);
                player4_down_row.setVisibility(View.GONE);

                standings_up_row.setVisibility(View.VISIBLE);
                standings_down_row.setVisibility(View.VISIBLE);

            } else {
                player1_up_row.setVisibility(View.VISIBLE);
                player1_down_row.setVisibility(View.VISIBLE);
                player2_up_row.setVisibility(View.VISIBLE);
                player2_down_row.setVisibility(View.VISIBLE);
                player3_up_row.setVisibility(View.VISIBLE);
                player3_down_row.setVisibility(View.VISIBLE);

                player4_up_row.setVisibility(View.GONE);
                player4_down_row.setVisibility(View.GONE);
                standings_up_row.setVisibility(View.GONE);
                standings_down_row.setVisibility(View.GONE);
            }

            /* current stndings parsing */

            if (event_id.equalsIgnoreCase("12")) {

                JSONArray currArray = data.getJSONArray("current_standing");
                if (currArray.length() > 0) {

                    scoreCardTab.setVisibility(View.GONE);
                    layout420_score.setVisibility(View.VISIBLE);


                    for (int c = 0; c < currArray.length(); c++) {

                        JSONObject currObject = currArray.getJSONObject(c);

                        JSONArray lastArray = currObject.getJSONArray("last");

                        for (int l = 0; l < lastArray.length(); l++) {

                            if (l == 0) {

                                String holeNumber = lastArray.getJSONObject(l).getString("hole_number");
                                String playerID = lastArray.getJSONObject(l).getString("player_id");
                                String scoreVALUE = lastArray.getJSONObject(l).getString("score_value");
                                String total = lastArray.getJSONObject(l).getString("total");
                                String fullNAme = lastArray.getJSONObject(l).getString("full_name");
                                player1_420_name.setText(fullNAme);
                                player1_420_score.setText(total);


                            } else if (l == 1) {

                                String holeNumber = lastArray.getJSONObject(l).getString("hole_number");
                                String playerID = lastArray.getJSONObject(l).getString("player_id");
                                String scoreVALUE = lastArray.getJSONObject(l).getString("score_value");
                                String total = lastArray.getJSONObject(l).getString("total");
                                String fullNAme = lastArray.getJSONObject(l).getString("full_name");
                                player2_420_name.setText(fullNAme);
                                player2_420_score.setText(total);

                            } else if (l == 2) {

                                String holeNumber = lastArray.getJSONObject(l).getString("hole_number");
                                String playerID = lastArray.getJSONObject(l).getString("player_id");
                                String scoreVALUE = lastArray.getJSONObject(l).getString("score_value");
                                String total = lastArray.getJSONObject(l).getString("total");
                                String fullNAme = lastArray.getJSONObject(l).getString("full_name");
                                player3_420_name.setText(fullNAme);
                                player3_420_score.setText(total);

                            }
                        }
                    }
                } else {
                    layout420_score.setVisibility(View.GONE);
                    scoreCardTab.setVisibility(View.GONE);
                }

            } else {

                JSONArray currArray = data.getJSONArray("current_standing");
                if (currArray.length() > 0) {

                    layout420_score.setVisibility(View.GONE);
                    scoreCardTab.setVisibility(View.VISIBLE);

                    for (int c = 0; c < currArray.length(); c++) {

                        JSONObject currObject = currArray.getJSONObject(c);

                        String curentHole = currObject.getString("hole_number");
                        String curr_scoreValue = currObject.getString("score_value");
                        current_winner = currObject.getString("winner");
                        tabWinnerStandings.setText(curr_scoreValue);
                        thruHole.setText("THRU " + curentHole);
                    }
                } else {
                    layout420_score.setVisibility(View.GONE);
                    scoreCardTab.setVisibility(View.GONE);
                }

            }

            JSONArray front_9_array = data.getJSONArray("front_9_data");
            for (int f = 0; f < front_9_array.length(); f++) {

                fronLayout.setVisibility(View.VISIBLE);

                JSONObject jsObj = front_9_array.getJSONObject(f).getJSONObject("team_a");
                teamAname = jsObj.getString("team_name");
                JSONArray pl_Array = jsObj.getJSONArray("player_list");
                for (int p = 0; p < pl_Array.length(); p++) {

                    if (p == 0) {

                        JSONObject jsPl_Object = pl_Array.getJSONObject(p);

                        player1_id = jsPl_Object.getString("player_id");
                        player1_Name = jsPl_Object.getString("name");
                        player1_handicap = jsPl_Object.getString("handicap_value");
                        player1_color = jsPl_Object.getString("color");

                        team_id1 = jsPl_Object.getString("team_id");

                        if (is_team.equalsIgnoreCase("1")) {
                            winner1 = team_id1;
                        } else {
                            winner1 = player1_id;
                        }

                        String short_name = jsPl_Object.getString("short_name");

                        player1_name_up.setTextColor(Color.parseColor(player1_color));
                        player1_name_up.setText(short_name + " " + player1_handicap);

                        JSONObject job_hole_Score = jsPl_Object.getJSONObject("hole_score");

                        JSONObject jb_Score1 = job_hole_Score.getJSONObject("hole_num_1");

                        String score1_up = jb_Score1.getString("score");
                        player1_up_g1.setText(score1_up);
                        String color_1 = jb_Score1.getString("color");
                        String is_lowest = jb_Score1.getString("is_lowest");
                        if (is_lowest.equalsIgnoreCase("1")) {
                            player1_up_lay1.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g1.setTextColor(Color.WHITE);
                        } else {
                            player1_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g1.setTextColor(Color.BLACK);
                        }

                       /* if (color_1.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g1.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay1.setBackgroundColor(Color.parseColor(color_1));
                        }
*/
                        JSONObject jb_Score2 = job_hole_Score.getJSONObject("hole_num_2");

                        String score2_up = jb_Score2.getString("score");
                        player1_up_g2.setText(score2_up);
                        String color_2 = jb_Score2.getString("color");
                        String is_lowest2 = jb_Score2.getString("is_lowest");

                        if (is_lowest2.equalsIgnoreCase("1")) {
                            player1_up_lay2.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g2.setTextColor(Color.WHITE);
                        } else {
                            player1_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g2.setTextColor(Color.BLACK);
                        }

                      /*  if (color_2.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g2.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay2.setBackgroundColor(Color.parseColor(color_2));
                        }*/

                        JSONObject jb_Score3 = job_hole_Score.getJSONObject("hole_num_3");
                        String score3_up = jb_Score3.getString("score");
                        player1_up_g3.setText(score3_up);
                        String color_3 = jb_Score3.getString("color");

                        String is_lowest3 = jb_Score3.getString("is_lowest");
                        if (is_lowest3.equalsIgnoreCase("1")) {
                            player1_up_lay3.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g3.setTextColor(Color.WHITE);
                        } else {
                            player1_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g3.setTextColor(Color.BLACK);
                        }

                       /* if (color_3.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g3.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay3.setBackgroundColor(Color.parseColor(color_3));
                        }*/

                        JSONObject jb_Score4 = job_hole_Score.getJSONObject("hole_num_4");
                        String score4_up = jb_Score4.getString("score");
                        player1_up_g4.setText(score4_up);
                        String color_4 = jb_Score4.getString("color");
                        String is_lowest4 = jb_Score4.getString("is_lowest");
                        if (is_lowest4.equalsIgnoreCase("1")) {
                            player1_up_lay4.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g4.setTextColor(Color.WHITE);
                        } else {
                            player1_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g4.setTextColor(Color.BLACK);
                        }

                       /* if (color_4.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g4.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay4.setBackgroundColor(Color.parseColor(color_4));
                        }*/

                        JSONObject jb_Score5 = job_hole_Score.getJSONObject("hole_num_5");
                        String score5_up = jb_Score5.getString("score");
                        player1_up_g5.setText(score5_up);
                        String color_5 = jb_Score5.getString("color");

                        String is_lowest5 = jb_Score5.getString("is_lowest");
                        if (is_lowest5.equalsIgnoreCase("1")) {
                            player1_up_lay5.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g5.setTextColor(Color.WHITE);
                        } else {
                            player1_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g5.setTextColor(Color.BLACK);
                        }

                       /* if (color_5.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g5.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay5.setBackgroundColor(Color.parseColor(color_5));
                        }*/

                        JSONObject jb_Score6 = job_hole_Score.getJSONObject("hole_num_6");
                        String score6_up = jb_Score6.getString("score");
                        player1_up_g6.setText(score6_up);
                        String color_6 = jb_Score6.getString("color");

                        String is_lowest6 = jb_Score6.getString("is_lowest");
                        if (is_lowest6.equalsIgnoreCase("1")) {
                            player1_up_lay6.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g6.setTextColor(Color.WHITE);
                        } else {
                            player1_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g6.setTextColor(Color.BLACK);
                        }

                      /*  if (color_6.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g6.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay6.setBackgroundColor(Color.parseColor(color_6));
                        }*/

                        JSONObject jb_Score7 = job_hole_Score.getJSONObject("hole_num_7");
                        String score7_up = jb_Score7.getString("score");
                        player1_up_g7.setText(score7_up);
                        String color_7 = jb_Score7.getString("color");

                        String is_lowest7 = jb_Score7.getString("is_lowest");
                        if (is_lowest7.equalsIgnoreCase("1")) {
                            player1_up_lay7.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g7.setTextColor(Color.WHITE);
                        } else {
                            player1_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g7.setTextColor(Color.BLACK);
                        }

                       /* if (color_7.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g7.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay7.setBackgroundColor(Color.parseColor(color_7));
                        }*/

                        JSONObject jb_Score8 = job_hole_Score.getJSONObject("hole_num_8");
                        String score8_up = jb_Score8.getString("score");
                        player1_up_g8.setText(score8_up);
                        String color_8 = jb_Score8.getString("color");

                        String is_lowest8 = jb_Score8.getString("is_lowest");
                        if (is_lowest8.equalsIgnoreCase("1")) {
                            player1_up_lay8.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g8.setTextColor(Color.WHITE);
                        } else {
                            player1_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g8.setTextColor(Color.BLACK);
                        }

                       /* if (color_8.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g8.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay8.setBackgroundColor(Color.parseColor(color_8));
                        }*/

                        JSONObject jb_Score9 = job_hole_Score.getJSONObject("hole_num_9");
                        String score9_up = jb_Score9.getString("score");
                        player1_up_g9.setText(score9_up);
                        String color_9 = jb_Score9.getString("color");

                        String is_lowest9 = jb_Score9.getString("is_lowest");
                        if (is_lowest9.equalsIgnoreCase("1")) {
                            player1_up_lay9.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g9.setTextColor(Color.WHITE);
                        } else {
                            player1_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g9.setTextColor(Color.BLACK);
                        }

                       /* if (color_9.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g9.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }
*/

                        if (score1_up.isEmpty() || score1_up.equalsIgnoreCase("-")) {
                            p1_s1 = 0;
                        } else {
                            p1_s1 = Integer.parseInt(score1_up);
                        }
                        if (score2_up.isEmpty() || score2_up.equalsIgnoreCase("-")) {
                            p1_s2 = 0;
                        } else {
                            p1_s2 = Integer.parseInt(score2_up);
                        }
                        if (score3_up.isEmpty() || score3_up.equalsIgnoreCase("-")) {
                            p1_s3 = 0;
                        } else {
                            p1_s3 = Integer.parseInt(score3_up);
                        }
                        if (score4_up.isEmpty() || score4_up.equalsIgnoreCase("-")) {
                            p1_s4 = 0;
                        } else {
                            p1_s4 = Integer.parseInt(score4_up);
                        }
                        if (score5_up.isEmpty() || score5_up.equalsIgnoreCase("-")) {
                            p1_s5 = 0;
                        } else {
                            p1_s5 = Integer.parseInt(score5_up);
                        }
                        if (score6_up.isEmpty() || score6_up.equalsIgnoreCase("-")) {
                            p1_s6 = 0;
                        } else {
                            p1_s6 = Integer.parseInt(score6_up);
                        }
                        if (score7_up.isEmpty() || score7_up.equalsIgnoreCase("-")) {
                            p1_s7 = 0;
                        } else {
                            p1_s7 = Integer.parseInt(score7_up);
                        }
                        if (score8_up.isEmpty() || score8_up.equalsIgnoreCase("-")) {
                            p1_s8 = 0;
                        } else {
                            p1_s8 = Integer.parseInt(score8_up);
                        }
                        if (score9_up.isEmpty() || score9_up.equalsIgnoreCase("-")) {
                            p1_s9 = 0;
                        } else {
                            p1_s9 = Integer.parseInt(score9_up);
                        }

                        String p1_up_total = String.valueOf(p1_s1 + p1_s2 + p1_s3 + p1_s4 + p1_s5 + p1_s6 + p1_s7 + p1_s8 + p1_s9);
                        player1_up_total.setText(p1_up_total);


                    } else if (p == 1) {

                        JSONObject jsPl_Object = pl_Array.getJSONObject(p);

                        player2_id = jsPl_Object.getString("player_id");
                        player2_Name = jsPl_Object.getString("name");
                        player2_handicap = jsPl_Object.getString("handicap_value");
                        player2_color = jsPl_Object.getString("color");

                        team_id1 = jsPl_Object.getString("team_id");
                        if (is_team.equalsIgnoreCase("1")) {
                            winner1 = team_id1;
                        } else {
                            winner1 = player2_id;
                        }
                        String short_name = jsPl_Object.getString("short_name");

                        player2_name_up.setTextColor(Color.parseColor(player2_color));
                        player2_name_up.setText(short_name + " " + player2_handicap);

                        JSONObject job_hole_Score = jsPl_Object.getJSONObject("hole_score");

                        JSONObject jb_Score1 = job_hole_Score.getJSONObject("hole_num_1");

                        String score1_up = jb_Score1.getString("score");
                        player2_up_g1.setText(score1_up);
                        String color_1 = jb_Score1.getString("color");

                        String is_lowest1 = jb_Score1.getString("is_lowest");
                        if (is_lowest1.equalsIgnoreCase("1")) {
                            player2_up_lay1.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g1.setTextColor(Color.WHITE);
                        } else {
                            player2_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g1.setTextColor(Color.BLACK);
                        }

                       /* if (color_1.equalsIgnoreCase("#ffffff")) {
                            player2_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g1.setTextColor(Color.BLACK);
                        } else {
                            player2_up_lay1.setBackgroundColor(Color.parseColor(color_1));
                        }*/

                        JSONObject jb_Score2 = job_hole_Score.getJSONObject("hole_num_2");
                        String score2_up = jb_Score2.getString("score");
                        player2_up_g2.setText(score2_up);
                        String color_2 = jb_Score2.getString("color");

                        String is_lowest2 = jb_Score2.getString("is_lowest");
                        if (is_lowest2.equalsIgnoreCase("1")) {
                            player2_up_lay2.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g2.setTextColor(Color.WHITE);
                        } else {
                            player2_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g2.setTextColor(Color.BLACK);
                        }

                       /* if (color_2.equalsIgnoreCase("#ffffff")) {
                            player2_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g2.setTextColor(Color.BLACK);
                        } else {
                            player2_up_lay2.setBackgroundColor(Color.parseColor(color_2));
                        }*/

                        JSONObject jb_Score3 = job_hole_Score.getJSONObject("hole_num_3");
                        String score3_up = jb_Score3.getString("score");
                        player2_up_g3.setText(score3_up);
                        String color_3 = jb_Score3.getString("color");

                        String is_lowest3 = jb_Score3.getString("is_lowest");
                        if (is_lowest3.equalsIgnoreCase("1")) {
                            player2_up_lay3.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g3.setTextColor(Color.WHITE);
                        } else {
                            player2_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g3.setTextColor(Color.BLACK);
                        }

                       /* if (color_3.equalsIgnoreCase("#ffffff")) {
                            player2_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g3.setTextColor(Color.BLACK);
                        } else {
                            player2_up_lay3.setBackgroundColor(Color.parseColor(color_3));
                        }*/

                        JSONObject jb_Score4 = job_hole_Score.getJSONObject("hole_num_4");
                        String score4_up = jb_Score4.getString("score");
                        player2_up_g4.setText(score4_up);
                        String color_4 = jb_Score4.getString("color");
                        String is_lowest4 = jb_Score4.getString("is_lowest");
                        if (is_lowest4.equalsIgnoreCase("1")) {
                            player2_up_lay4.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g4.setTextColor(Color.WHITE);
                        } else {
                            player2_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g4.setTextColor(Color.BLACK);
                        }

                       /* if (color_4.equalsIgnoreCase("#ffffff")) {
                            player2_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g4.setTextColor(Color.BLACK);
                        } else {
                            player2_up_lay4.setBackgroundColor(Color.parseColor(color_4));
                        }*/

                        JSONObject jb_Score5 = job_hole_Score.getJSONObject("hole_num_5");
                        String score5_up = jb_Score5.getString("score");
                        player2_up_g5.setText(score5_up);
                        String color_5 = jb_Score5.getString("color");

                        String is_lowest5 = jb_Score5.getString("is_lowest");
                        if (is_lowest5.equalsIgnoreCase("1")) {
                            player2_up_lay5.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g5.setTextColor(Color.WHITE);
                        } else {
                            player2_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g5.setTextColor(Color.BLACK);
                        }
                        /*if (color_5.equalsIgnoreCase("#ffffff")) {
                            player2_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g5.setTextColor(Color.BLACK);
                        } else {
                            player2_up_lay5.setBackgroundColor(Color.parseColor(color_5));
                        }*/

                        JSONObject jb_Score6 = job_hole_Score.getJSONObject("hole_num_6");
                        String score6_up = jb_Score6.getString("score");
                        player2_up_g6.setText(score6_up);
                        String color_6 = jb_Score6.getString("color");

                        String is_lowest6 = jb_Score6.getString("is_lowest");
                        if (is_lowest6.equalsIgnoreCase("1")) {
                            player2_up_lay6.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g6.setTextColor(Color.WHITE);
                        } else {
                            player2_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g6.setTextColor(Color.BLACK);
                        }

                       /* if (color_6.equalsIgnoreCase("#ffffff")) {
                            player2_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g6.setTextColor(Color.BLACK);
                        } else {
                            player2_up_lay6.setBackgroundColor(Color.parseColor(color_6));
                        }*/

                        JSONObject jb_Score7 = job_hole_Score.getJSONObject("hole_num_7");
                        String score7_up = jb_Score7.getString("score");
                        player2_up_g7.setText(score7_up);
                        String color_7 = jb_Score7.getString("color");

                        String is_lowest7 = jb_Score7.getString("is_lowest");
                        if (is_lowest7.equalsIgnoreCase("1")) {
                            player2_up_lay7.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g7.setTextColor(Color.WHITE);
                        } else {
                            player2_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g7.setTextColor(Color.BLACK);
                        }

                        /*if (color_7.equalsIgnoreCase("#ffffff")) {
                            player2_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g7.setTextColor(Color.BLACK);
                        } else {
                            player2_up_lay7.setBackgroundColor(Color.parseColor(color_7));
                        }*/

                        JSONObject jb_Score8 = job_hole_Score.getJSONObject("hole_num_8");
                        String score8_up = jb_Score8.getString("score");
                        player2_up_g8.setText(score8_up);
                        String color_8 = jb_Score8.getString("color");

                        String is_lowest8 = jb_Score8.getString("is_lowest");
                        if (is_lowest8.equalsIgnoreCase("1")) {
                            player2_up_lay8.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g8.setTextColor(Color.WHITE);
                        } else {
                            player2_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g8.setTextColor(Color.BLACK);
                        }

                       /* if (color_8.equalsIgnoreCase("#ffffff")) {
                            player2_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g8.setTextColor(Color.BLACK);
                        } else {
                            player2_up_lay8.setBackgroundColor(Color.parseColor(color_8));
                        }*/

                        JSONObject jb_Score9 = job_hole_Score.getJSONObject("hole_num_9");
                        String score9_up = jb_Score9.getString("score");
                        player2_up_g9.setText(score9_up);
                        String color_9 = jb_Score9.getString("color");

                        String is_lowest9 = jb_Score9.getString("is_lowest");
                        if (is_lowest9.equalsIgnoreCase("1")) {
                            player2_up_lay9.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g9.setTextColor(Color.WHITE);
                        } else {
                            player2_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g9.setTextColor(Color.BLACK);
                        }

                       /* if (color_9.equalsIgnoreCase("#ffffff")) {
                            player2_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g9.setTextColor(Color.BLACK);
                        } else {
                            player2_up_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }
*/

                        if (score1_up.isEmpty() || score1_up.equalsIgnoreCase("-")) {
                            p2_s1 = 0;
                        } else {
                            p2_s1 = Integer.parseInt(score1_up);
                        }
                        if (score2_up.isEmpty() || score2_up.equalsIgnoreCase("-")) {
                            p2_s2 = 0;
                        } else {
                            p2_s2 = Integer.parseInt(score2_up);
                        }
                        if (score3_up.isEmpty() || score3_up.equalsIgnoreCase("-")) {
                            p2_s3 = 0;
                        } else {
                            p2_s3 = Integer.parseInt(score3_up);
                        }
                        if (score4_up.isEmpty() || score4_up.equalsIgnoreCase("-")) {
                            p2_s4 = 0;
                        } else {
                            p2_s4 = Integer.parseInt(score4_up);
                        }
                        if (score5_up.isEmpty() || score5_up.equalsIgnoreCase("-")) {
                            p2_s5 = 0;
                        } else {
                            p2_s5 = Integer.parseInt(score5_up);
                        }
                        if (score6_up.isEmpty() || score6_up.equalsIgnoreCase("-")) {
                            p2_s6 = 0;
                        } else {
                            p2_s6 = Integer.parseInt(score6_up);
                        }
                        if (score7_up.isEmpty() || score7_up.equalsIgnoreCase("-")) {
                            p2_s7 = 0;
                        } else {
                            p2_s7 = Integer.parseInt(score7_up);
                        }
                        if (score8_up.isEmpty() || score8_up.equalsIgnoreCase("-")) {
                            p2_s8 = 0;
                        } else {
                            p2_s8 = Integer.parseInt(score8_up);
                        }
                        if (score9_up.isEmpty() || score9_up.equalsIgnoreCase("-")) {
                            p2_s9 = 0;
                        } else {
                            p2_s9 = Integer.parseInt(score9_up);
                        }

                        String p2_up_total = String.valueOf(p2_s1 + p2_s2 + p2_s3 + p2_s4 + p2_s5 + p2_s6 + p2_s7 + p2_s8 + p2_s9);
                        player2_up_total.setText(p2_up_total);


                    } else if (p == 2) {
                        JSONObject jsPl_Object = pl_Array.getJSONObject(p);

                        player3_id = jsPl_Object.getString("player_id");
                        player3_Name = jsPl_Object.getString("name");
                        player3_handicap = jsPl_Object.getString("handicap_value");
                        player3_color = jsPl_Object.getString("color");

                        team_id2 = jsPl_Object.getString("team_id");
                        if (is_team.equalsIgnoreCase("1")) {
                            winner2 = team_id2;
                        } else {
                            winner2 = player3_id;
                        }
                        String short_name = jsPl_Object.getString("short_name");

                        player3_name_up.setTextColor(Color.parseColor(player3_color));
                        player3_name_up.setText(short_name + " " + player3_handicap);

                        JSONObject job_hole_Score = jsPl_Object.getJSONObject("hole_score");

                        JSONObject jb_Score1 = job_hole_Score.getJSONObject("hole_num_1");

                        String score1_up = jb_Score1.getString("score");
                        player3_up_g1.setText(score1_up);
                        String color_1 = jb_Score1.getString("color");

                        String is_lowest1 = jb_Score1.getString("is_lowest");
                        if (is_lowest1.equalsIgnoreCase("1")) {
                            player3_up_lay1.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g1.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g1.setTextColor(Color.BLACK);
                        }

                      /*  if (color_1.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g1.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay1.setBackgroundColor(Color.parseColor(color_1));
                        }*/

                        JSONObject jb_Score2 = job_hole_Score.getJSONObject("hole_num_2");
                        String score2_up = jb_Score2.getString("score");
                        player3_up_g2.setText(score2_up);
                        String color_2 = jb_Score2.getString("color");

                        String is_lowest2 = jb_Score2.getString("is_lowest");
                        if (is_lowest2.equalsIgnoreCase("1")) {
                            player3_up_lay2.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g2.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g2.setTextColor(Color.BLACK);
                        }
                       /*
                        if (color_2.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g2.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay2.setBackgroundColor(Color.parseColor(color_2));
                        }
*/
                        JSONObject jb_Score3 = job_hole_Score.getJSONObject("hole_num_3");
                        String score3_up = jb_Score3.getString("score");
                        player3_up_g3.setText(score3_up);
                        String color_3 = jb_Score3.getString("color");

                        String is_lowest3 = jb_Score3.getString("is_lowest");
                        if (is_lowest3.equalsIgnoreCase("1")) {
                            player3_up_lay3.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g3.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g3.setTextColor(Color.BLACK);
                        }

                       /* if (color_3.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g3.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay3.setBackgroundColor(Color.parseColor(color_3));
                        }*/

                        JSONObject jb_Score4 = job_hole_Score.getJSONObject("hole_num_4");
                        String score4_up = jb_Score4.getString("score");
                        player3_up_g4.setText(score4_up);
                        String color_4 = jb_Score4.getString("color");

                        String is_lowest4 = jb_Score4.getString("is_lowest");
                        if (is_lowest4.equalsIgnoreCase("1")) {
                            player3_up_lay4.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g4.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g4.setTextColor(Color.BLACK);
                        }

                        /*if (color_4.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g4.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay4.setBackgroundColor(Color.parseColor(color_4));
                        }*/

                        JSONObject jb_Score5 = job_hole_Score.getJSONObject("hole_num_5");
                        String score5_up = jb_Score5.getString("score");
                        player3_up_g5.setText(score5_up);
                        String color_5 = jb_Score5.getString("color");

                        String is_lowest5 = jb_Score5.getString("is_lowest");
                        if (is_lowest5.equalsIgnoreCase("1")) {
                            player3_up_lay5.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g5.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g5.setTextColor(Color.BLACK);
                        }

                       /* if (color_5.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g5.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay5.setBackgroundColor(Color.parseColor(color_5));
                        }*/

                        JSONObject jb_Score6 = job_hole_Score.getJSONObject("hole_num_6");
                        String score6_up = jb_Score6.getString("score");
                        player3_up_g6.setText(score6_up);
                        String color_6 = jb_Score6.getString("color");

                        String is_lowest6 = jb_Score6.getString("is_lowest");
                        if (is_lowest6.equalsIgnoreCase("1")) {
                            player3_up_lay6.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g6.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g6.setTextColor(Color.BLACK);
                        }

                       /* if (color_6.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g6.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay6.setBackgroundColor(Color.parseColor(color_6));
                        }*/

                        JSONObject jb_Score7 = job_hole_Score.getJSONObject("hole_num_7");
                        String score7_up = jb_Score7.getString("score");
                        player3_up_g7.setText(score7_up);
                        String color_7 = jb_Score7.getString("color");

                        String is_lowest7 = jb_Score7.getString("is_lowest");
                        if (is_lowest7.equalsIgnoreCase("1")) {
                            player3_up_lay7.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g7.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g7.setTextColor(Color.BLACK);
                        }

                        /*if (color_7.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g7.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay7.setBackgroundColor(Color.parseColor(color_7));
                        }*/

                        JSONObject jb_Score8 = job_hole_Score.getJSONObject("hole_num_8");
                        String score8_up = jb_Score8.getString("score");
                        player3_up_g8.setText(score8_up);
                        String color_8 = jb_Score8.getString("color");

                        String is_lowest8 = jb_Score8.getString("is_lowest");
                        if (is_lowest8.equalsIgnoreCase("1")) {
                            player3_up_lay8.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g8.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g8.setTextColor(Color.BLACK);
                        }

                        /*if (color_8.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g8.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay8.setBackgroundColor(Color.parseColor(color_8));
                        }*/

                        JSONObject jb_Score9 = job_hole_Score.getJSONObject("hole_num_9");
                        String score9_up = jb_Score9.getString("score");
                        player3_up_g9.setText(score9_up);
                        String color_9 = jb_Score9.getString("color");

                        String is_lowest9 = jb_Score9.getString("is_lowest");
                        if (is_lowest9.equalsIgnoreCase("1")) {
                            player3_up_lay9.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g9.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g9.setTextColor(Color.BLACK);
                        }

                        /*if (color_9.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g9.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }*/


                        if (score1_up.isEmpty() || score1_up.equalsIgnoreCase("-")) {
                            p3_s1 = 0;
                        } else {
                            p3_s1 = Integer.parseInt(score1_up);
                        }
                        if (score2_up.isEmpty() || score2_up.equalsIgnoreCase("-")) {
                            p3_s2 = 0;
                        } else {
                            p3_s2 = Integer.parseInt(score2_up);
                        }
                        if (score3_up.isEmpty() || score3_up.equalsIgnoreCase("-")) {
                            p3_s3 = 0;
                        } else {
                            p3_s3 = Integer.parseInt(score3_up);
                        }
                        if (score4_up.isEmpty() || score4_up.equalsIgnoreCase("-")) {
                            p3_s4 = 0;
                        } else {
                            p3_s4 = Integer.parseInt(score4_up);
                        }
                        if (score5_up.isEmpty() || score5_up.equalsIgnoreCase("-")) {
                            p3_s5 = 0;
                        } else {
                            p3_s5 = Integer.parseInt(score5_up);
                        }
                        if (score6_up.isEmpty() || score6_up.equalsIgnoreCase("-")) {
                            p3_s6 = 0;
                        } else {
                            p3_s6 = Integer.parseInt(score6_up);
                        }
                        if (score7_up.isEmpty() || score7_up.equalsIgnoreCase("-")) {
                            p3_s7 = 0;
                        } else {
                            p3_s7 = Integer.parseInt(score7_up);
                        }
                        if (score8_up.isEmpty() || score8_up.equalsIgnoreCase("-")) {
                            p3_s8 = 0;
                        } else {
                            p3_s8 = Integer.parseInt(score8_up);
                        }
                        if (score9_up.isEmpty() || score9_up.equalsIgnoreCase("-")) {
                            p3_s9 = 0;
                        } else {
                            p3_s9 = Integer.parseInt(score9_up);
                        }

                        String p3_up_total = String.valueOf(p3_s1 + p3_s2 + p3_s3 + p3_s4 + p3_s5 + p3_s6 + p3_s7 + p3_s8 + p3_s9);
                        player3_up_total.setText(p3_up_total);
                    }
                }


                JSONObject jsObj_b = front_9_array.getJSONObject(f).getJSONObject("team_b");
                teamBname = jsObj_b.getString("team_name");
                JSONArray pl_Array_teamb = jsObj_b.getJSONArray("player_list");
                for (int p = 0; p < pl_Array_teamb.length(); p++) {

                    if (p == 0) {
                        JSONObject jsPl_Object = pl_Array_teamb.getJSONObject(p);

                        player3_id = jsPl_Object.getString("player_id");
                        player3_Name = jsPl_Object.getString("name");
                        player3_handicap = jsPl_Object.getString("handicap_value");
                        player3_color = jsPl_Object.getString("color");

                        team_id2 = jsPl_Object.getString("team_id");
                        if (is_team.equalsIgnoreCase("1")) {
                            winner2 = team_id2;
                        } else {
                            winner2 = player3_id;
                        }
                        String short_name = jsPl_Object.getString("short_name");

                        player3_name_up.setTextColor(Color.parseColor(player3_color));
                        player3_name_up.setText(short_name + " " + player3_handicap);

                        JSONObject job_hole_Score = jsPl_Object.getJSONObject("hole_score");

                        JSONObject jb_Score1 = job_hole_Score.getJSONObject("hole_num_1");

                        String score1_up = jb_Score1.getString("score");
                        player3_up_g1.setText(score1_up);
                        String color_1 = jb_Score1.getString("color");

                        String is_lowest1 = jb_Score1.getString("is_lowest");
                        if (is_lowest1.equalsIgnoreCase("1")) {
                            player3_up_lay1.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g1.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g1.setTextColor(Color.BLACK);
                        }

                       /* if (color_1.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g1.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay1.setBackgroundColor(Color.parseColor(color_1));
                        }*/

                        JSONObject jb_Score2 = job_hole_Score.getJSONObject("hole_num_2");
                        String score2_up = jb_Score2.getString("score");
                        player3_up_g2.setText(score2_up);
                        String color_2 = jb_Score2.getString("color");

                        String is_lowest2 = jb_Score2.getString("is_lowest");
                        if (is_lowest2.equalsIgnoreCase("1")) {
                            player3_up_lay2.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g2.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g2.setTextColor(Color.BLACK);
                        }

                       /* if (color_2.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g2.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay2.setBackgroundColor(Color.parseColor(color_2));
                        }*/

                        JSONObject jb_Score3 = job_hole_Score.getJSONObject("hole_num_3");
                        String score3_up = jb_Score3.getString("score");
                        player3_up_g3.setText(score3_up);
                        String color_3 = jb_Score3.getString("color");

                        String is_lowest3 = jb_Score3.getString("is_lowest");
                        if (is_lowest3.equalsIgnoreCase("1")) {
                            player3_up_lay3.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g3.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g3.setTextColor(Color.BLACK);
                        }

                        /*if (color_3.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g3.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay3.setBackgroundColor(Color.parseColor(color_3));
                        }*/

                        JSONObject jb_Score4 = job_hole_Score.getJSONObject("hole_num_4");
                        String score4_up = jb_Score4.getString("score");
                        player3_up_g4.setText(score4_up);
                        String color_4 = jb_Score4.getString("color");

                        String is_lowest4 = jb_Score4.getString("is_lowest");
                        if (is_lowest4.equalsIgnoreCase("1")) {
                            player3_up_lay4.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g4.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g4.setTextColor(Color.BLACK);
                        }

                       /* if (color_4.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g4.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay4.setBackgroundColor(Color.parseColor(color_4));
                        }*/

                        JSONObject jb_Score5 = job_hole_Score.getJSONObject("hole_num_5");
                        String score5_up = jb_Score5.getString("score");
                        player3_up_g5.setText(score5_up);
                        String color_5 = jb_Score5.getString("color");

                        String is_lowest5 = jb_Score5.getString("is_lowest");
                        if (is_lowest5.equalsIgnoreCase("1")) {
                            player3_up_lay5.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g5.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g5.setTextColor(Color.BLACK);
                        }

                       /* if (color_5.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g5.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay5.setBackgroundColor(Color.parseColor(color_5));
                        }*/

                        JSONObject jb_Score6 = job_hole_Score.getJSONObject("hole_num_6");
                        String score6_up = jb_Score6.getString("score");
                        player3_up_g6.setText(score6_up);
                        String color_6 = jb_Score6.getString("color");

                        String is_lowest6 = jb_Score6.getString("is_lowest");
                        if (is_lowest6.equalsIgnoreCase("1")) {
                            player3_up_lay6.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g6.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g6.setTextColor(Color.BLACK);
                        }

                       /* if (color_6.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g6.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay6.setBackgroundColor(Color.parseColor(color_6));
                        }*/

                        JSONObject jb_Score7 = job_hole_Score.getJSONObject("hole_num_7");
                        String score7_up = jb_Score7.getString("score");
                        player3_up_g7.setText(score7_up);
                        String color_7 = jb_Score7.getString("color");

                        String is_lowest7 = jb_Score7.getString("is_lowest");
                        if (is_lowest7.equalsIgnoreCase("1")) {
                            player3_up_lay7.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g7.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g7.setTextColor(Color.BLACK);
                        }

                        /*if (color_7.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g7.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay7.setBackgroundColor(Color.parseColor(color_7));
                        }*/

                        JSONObject jb_Score8 = job_hole_Score.getJSONObject("hole_num_8");
                        String score8_up = jb_Score8.getString("score");
                        player3_up_g8.setText(score8_up);
                        String color_8 = jb_Score8.getString("color");

                        String is_lowest8 = jb_Score8.getString("is_lowest");
                        if (is_lowest8.equalsIgnoreCase("1")) {
                            player3_up_lay8.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g8.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g8.setTextColor(Color.BLACK);
                        }

                       /* if (color_8.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g8.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay8.setBackgroundColor(Color.parseColor(color_8));
                        }*/

                        JSONObject jb_Score9 = job_hole_Score.getJSONObject("hole_num_9");
                        String score9_up = jb_Score9.getString("score");
                        player3_up_g9.setText(score9_up);
                        String color_9 = jb_Score9.getString("color");

                        String is_lowest9 = jb_Score9.getString("is_lowest");
                        if (is_lowest9.equalsIgnoreCase("1")) {
                            player3_up_lay9.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g9.setTextColor(Color.WHITE);
                        } else {
                            player3_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g9.setTextColor(Color.BLACK);
                        }

                        /*if (color_9.equalsIgnoreCase("#ffffff")) {
                            player3_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g9.setTextColor(Color.BLACK);
                        } else {
                            player3_up_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }*/


                        if (score1_up.isEmpty() || score1_up.equalsIgnoreCase("-")) {
                            p3_s1 = 0;
                        } else {
                            p3_s1 = Integer.parseInt(score1_up);
                        }
                        if (score2_up.isEmpty() || score2_up.equalsIgnoreCase("-")) {
                            p3_s2 = 0;
                        } else {
                            p3_s2 = Integer.parseInt(score2_up);
                        }
                        if (score3_up.isEmpty() || score3_up.equalsIgnoreCase("-")) {
                            p3_s3 = 0;
                        } else {
                            p3_s3 = Integer.parseInt(score3_up);
                        }
                        if (score4_up.isEmpty() || score4_up.equalsIgnoreCase("-")) {
                            p3_s4 = 0;
                        } else {
                            p3_s4 = Integer.parseInt(score4_up);
                        }
                        if (score5_up.isEmpty() || score5_up.equalsIgnoreCase("-")) {
                            p3_s5 = 0;
                        } else {
                            p3_s5 = Integer.parseInt(score5_up);
                        }
                        if (score6_up.isEmpty() || score6_up.equalsIgnoreCase("-")) {
                            p3_s6 = 0;
                        } else {
                            p3_s6 = Integer.parseInt(score6_up);
                        }
                        if (score7_up.isEmpty() || score7_up.equalsIgnoreCase("-")) {
                            p3_s7 = 0;
                        } else {
                            p3_s7 = Integer.parseInt(score7_up);
                        }
                        if (score8_up.isEmpty() || score8_up.equalsIgnoreCase("-")) {
                            p3_s8 = 0;
                        } else {
                            p3_s8 = Integer.parseInt(score8_up);
                        }
                        if (score9_up.isEmpty() || score9_up.equalsIgnoreCase("-")) {
                            p3_s9 = 0;
                        } else {
                            p3_s9 = Integer.parseInt(score9_up);
                        }

                        String p3_up_total = String.valueOf(p3_s1 + p3_s2 + p3_s3 + p3_s4 + p3_s5 + p3_s6 + p3_s7 + p3_s8 + p3_s9);
                        player3_up_total.setText(p3_up_total);

                    } else if (p == 1) {
                        JSONObject jsPl_Object = pl_Array_teamb.getJSONObject(p);

                        player4_id = jsPl_Object.getString("player_id");
                        player4_Name = jsPl_Object.getString("name");
                        player4_handicap = jsPl_Object.getString("handicap_value");
                        player4_color = jsPl_Object.getString("color");

                        team_id2 = jsPl_Object.getString("team_id");
                        if (is_team.equalsIgnoreCase("1")) {
                            winner2 = team_id2;
                        } else {
                            winner2 = player4_id;
                        }
                        String short_name = jsPl_Object.getString("short_name");

                        player4_name_up.setTextColor(Color.parseColor(player4_color));
                        player4_name_up.setText(short_name + " " + player4_handicap);

                        JSONObject job_hole_Score = jsPl_Object.getJSONObject("hole_score");

                        JSONObject jb_Score1 = job_hole_Score.getJSONObject("hole_num_1");

                        String score1_up = jb_Score1.getString("score");
                        player4_up_g1.setText(score1_up);
                        String color_1 = jb_Score1.getString("color");

                        String is_lowest1 = jb_Score1.getString("is_lowest");
                        if (is_lowest1.equalsIgnoreCase("1")) {
                            player4_up_lay1.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g1.setTextColor(Color.WHITE);
                        } else {
                            player4_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g1.setTextColor(Color.BLACK);
                        }

                        /*if (color_1.equalsIgnoreCase("#ffffff")) {
                            player4_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g1.setTextColor(Color.BLACK);
                        } else {
                            player4_up_lay1.setBackgroundColor(Color.parseColor(color_1));
                        }*/

                        JSONObject jb_Score2 = job_hole_Score.getJSONObject("hole_num_2");
                        String score2_up = jb_Score2.getString("score");
                        player4_up_g2.setText(score2_up);
                        String color_2 = jb_Score2.getString("color");

                        String is_lowest2 = jb_Score2.getString("is_lowest");
                        if (is_lowest2.equalsIgnoreCase("1")) {
                            player4_up_lay2.setBackgroundColor(Color.parseColor(player3_color));
                            player4_up_g2.setTextColor(Color.WHITE);
                        } else {
                            player4_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g2.setTextColor(Color.BLACK);
                        }

                        /*if (color_2.equalsIgnoreCase("#ffffff")) {
                            player4_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g2.setTextColor(Color.BLACK);
                        } else {
                            player4_up_lay2.setBackgroundColor(Color.parseColor(color_2));
                        }*/

                        JSONObject jb_Score3 = job_hole_Score.getJSONObject("hole_num_3");
                        String score3_up = jb_Score3.getString("score");
                        player4_up_g3.setText(score3_up);
                        String color_3 = jb_Score3.getString("color");

                        String is_lowest3 = jb_Score3.getString("is_lowest");
                        if (is_lowest3.equalsIgnoreCase("1")) {
                            player4_up_lay3.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g3.setTextColor(Color.WHITE);
                        } else {
                            player4_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g3.setTextColor(Color.BLACK);
                        }

                        /*if (color_3.equalsIgnoreCase("#ffffff")) {
                            player4_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g3.setTextColor(Color.BLACK);
                        } else {
                            player4_up_lay3.setBackgroundColor(Color.parseColor(color_3));
                        }*/

                        JSONObject jb_Score4 = job_hole_Score.getJSONObject("hole_num_4");
                        String score4_up = jb_Score4.getString("score");
                        player4_up_g4.setText(score4_up);
                        String color_4 = jb_Score4.getString("color");

                        String is_lowest4 = jb_Score4.getString("is_lowest");
                        if (is_lowest4.equalsIgnoreCase("1")) {
                            player4_up_lay4.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g4.setTextColor(Color.WHITE);
                        } else {
                            player4_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g4.setTextColor(Color.BLACK);
                        }

                       /* if (color_4.equalsIgnoreCase("#ffffff")) {
                            player4_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g4.setTextColor(Color.BLACK);
                        } else {
                            player4_up_lay4.setBackgroundColor(Color.parseColor(color_4));
                        }*/

                        JSONObject jb_Score5 = job_hole_Score.getJSONObject("hole_num_5");
                        String score5_up = jb_Score5.getString("score");
                        player4_up_g5.setText(score5_up);
                        String color_5 = jb_Score5.getString("color");

                        String is_lowest5 = jb_Score5.getString("is_lowest");
                        if (is_lowest5.equalsIgnoreCase("1")) {
                            player4_up_lay5.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g5.setTextColor(Color.WHITE);
                        } else {
                            player4_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g5.setTextColor(Color.BLACK);
                        }

                        /*if (color_5.equalsIgnoreCase("#ffffff")) {
                            player4_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g5.setTextColor(Color.BLACK);
                        } else {
                            player4_up_lay5.setBackgroundColor(Color.parseColor(color_5));
                        }*/

                        JSONObject jb_Score6 = job_hole_Score.getJSONObject("hole_num_6");
                        String score6_up = jb_Score6.getString("score");
                        player4_up_g6.setText(score6_up);
                        String color_6 = jb_Score6.getString("color");

                        String is_lowest6 = jb_Score6.getString("is_lowest");
                        if (is_lowest6.equalsIgnoreCase("1")) {
                            player4_up_lay6.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g6.setTextColor(Color.WHITE);
                        } else {
                            player4_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g6.setTextColor(Color.BLACK);
                        }

                       /* if (color_6.equalsIgnoreCase("#ffffff")) {
                            player4_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g6.setTextColor(Color.BLACK);
                        } else {
                            player4_up_lay6.setBackgroundColor(Color.parseColor(color_6));
                        }*/

                        JSONObject jb_Score7 = job_hole_Score.getJSONObject("hole_num_7");
                        String score7_up = jb_Score7.getString("score");
                        player4_up_g7.setText(score7_up);
                        String color_7 = jb_Score7.getString("color");

                        String is_lowest7 = jb_Score7.getString("is_lowest");
                        if (is_lowest7.equalsIgnoreCase("1")) {
                            player4_up_lay7.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g7.setTextColor(Color.WHITE);
                        } else {
                            player4_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g7.setTextColor(Color.BLACK);
                        }

                        /*if (color_7.equalsIgnoreCase("#ffffff")) {
                            player4_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g7.setTextColor(Color.BLACK);
                        } else {
                            player4_up_lay7.setBackgroundColor(Color.parseColor(color_7));
                        }*/

                        JSONObject jb_Score8 = job_hole_Score.getJSONObject("hole_num_8");
                        String score8_up = jb_Score8.getString("score");
                        player4_up_g8.setText(score8_up);
                        String color_8 = jb_Score8.getString("color");

                        String is_lowest8 = jb_Score8.getString("is_lowest");
                        if (is_lowest8.equalsIgnoreCase("1")) {
                            player4_up_lay8.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g8.setTextColor(Color.WHITE);
                        } else {
                            player4_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g8.setTextColor(Color.BLACK);
                        }

                        /*if (color_8.equalsIgnoreCase("#ffffff")) {
                            player4_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g8.setTextColor(Color.BLACK);
                        } else {
                            player4_up_lay8.setBackgroundColor(Color.parseColor(color_8));
                        }*/

                        JSONObject jb_Score9 = job_hole_Score.getJSONObject("hole_num_9");
                        String score9_up = jb_Score9.getString("score");
                        player4_up_g9.setText(score9_up);
                        String color_9 = jb_Score9.getString("color");

                        String is_lowest9 = jb_Score9.getString("is_lowest");
                        if (is_lowest9.equalsIgnoreCase("1")) {
                            player4_up_lay9.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g9.setTextColor(Color.WHITE);
                        } else {
                            player4_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g9.setTextColor(Color.BLACK);
                        }

                       /* if (color_9.equalsIgnoreCase("#ffffff")) {
                            player4_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g9.setTextColor(Color.BLACK);
                        } else {
                            player4_up_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }*/

                        if (score1_up.isEmpty() || score1_up.equalsIgnoreCase("-")) {
                            p4_s1 = 0;
                        } else {
                            p4_s1 = Integer.parseInt(score1_up);
                        }
                        if (score2_up.isEmpty() || score2_up.equalsIgnoreCase("-")) {
                            p4_s2 = 0;
                        } else {
                            p4_s2 = Integer.parseInt(score2_up);
                        }
                        if (score3_up.isEmpty() || score3_up.equalsIgnoreCase("-")) {
                            p4_s3 = 0;
                        } else {
                            p4_s3 = Integer.parseInt(score3_up);
                        }
                        if (score4_up.isEmpty() || score4_up.equalsIgnoreCase("-")) {
                            p4_s4 = 0;
                        } else {
                            p4_s4 = Integer.parseInt(score4_up);
                        }
                        if (score5_up.isEmpty() || score5_up.equalsIgnoreCase("-")) {
                            p4_s5 = 0;
                        } else {
                            p4_s5 = Integer.parseInt(score5_up);
                        }
                        if (score6_up.isEmpty() || score6_up.equalsIgnoreCase("-")) {
                            p4_s6 = 0;
                        } else {
                            p4_s6 = Integer.parseInt(score6_up);
                        }
                        if (score7_up.isEmpty() || score7_up.equalsIgnoreCase("-")) {
                            p4_s7 = 0;
                        } else {
                            p4_s7 = Integer.parseInt(score7_up);
                        }
                        if (score8_up.isEmpty() || score8_up.equalsIgnoreCase("-")) {
                            p4_s8 = 0;
                        } else {
                            p4_s8 = Integer.parseInt(score8_up);
                        }
                        if (score9_up.isEmpty() || score9_up.equalsIgnoreCase("-")) {
                            p4_s9 = 0;
                        } else {
                            p4_s9 = Integer.parseInt(score9_up);
                        }

                        String p4_up_total = String.valueOf(p4_s1 + p4_s2 + p4_s3 + p4_s4 + p4_s5 + p4_s6 + p4_s7 + p4_s8 + p4_s9);
                        player4_up_total.setText(p4_up_total);
                    }
                }

                if (event_id.equalsIgnoreCase("12")) {

                } else {

                    JSONArray standings_front = front_9_array.getJSONObject(f).getJSONArray("standings");
                    for (int s = 0; s < standings_front.length(); s++) {

                        if (s == 0) {
                            String score_value = standings_front.getJSONObject(s).getString("score_value");
                            String hole_number = standings_front.getJSONObject(s).getString("hole_number");
                            String winner = standings_front.getJSONObject(s).getString("winner");
                            String color = standings_front.getJSONObject(s).getString("color");
                            standings1.setText(score_value);
                            if (color.length() > 5) {
                                standings1.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 1) {
                            String score_value = standings_front.getJSONObject(s).getString("score_value");
                            String hole_number = standings_front.getJSONObject(s).getString("hole_number");
                            String winner = standings_front.getJSONObject(s).getString("winner");
                            String color = standings_front.getJSONObject(s).getString("color");
                            standings2.setText(score_value);
                            if (color.length() > 5) {

                                standings2.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 2) {
                            String score_value = standings_front.getJSONObject(s).getString("score_value");
                            String hole_number = standings_front.getJSONObject(s).getString("hole_number");
                            String winner = standings_front.getJSONObject(s).getString("winner");
                            String color = standings_front.getJSONObject(s).getString("color");
                            standings3.setText(score_value);
                            if (color.length() > 5) {
                                standings3.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 3) {
                            String score_value = standings_front.getJSONObject(s).getString("score_value");
                            String hole_number = standings_front.getJSONObject(s).getString("hole_number");
                            String winner = standings_front.getJSONObject(s).getString("winner");
                            String color = standings_front.getJSONObject(s).getString("color");

                            standings4.setText(score_value);

                            if (color.length() > 5) {

                                standings4.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 4) {
                            String score_value = standings_front.getJSONObject(s).getString("score_value");
                            String hole_number = standings_front.getJSONObject(s).getString("hole_number");
                            String winner = standings_front.getJSONObject(s).getString("winner");
                            String color = standings_front.getJSONObject(s).getString("color");
                            standings5.setText(score_value);
                            if (color.length() > 5) {
                                standings5.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 5) {
                            String score_value = standings_front.getJSONObject(s).getString("score_value");
                            String hole_number = standings_front.getJSONObject(s).getString("hole_number");
                            String winner = standings_front.getJSONObject(s).getString("winner");
                            String color = standings_front.getJSONObject(s).getString("color");
                            standings6.setText(score_value);
                            if (color.length() > 5) {
                                // standings1.setTextColor(P);
                                standings6.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 6) {
                            String score_value = standings_front.getJSONObject(s).getString("score_value");
                            String hole_number = standings_front.getJSONObject(s).getString("hole_number");
                            String winner = standings_front.getJSONObject(s).getString("winner");
                            String color = standings_front.getJSONObject(s).getString("color");
                            standings7.setText(score_value);
                            if (color.length() > 5) {
                                // standings1.setTextColor(P);
                                standings7.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 7) {
                            String score_value = standings_front.getJSONObject(s).getString("score_value");
                            String hole_number = standings_front.getJSONObject(s).getString("hole_number");
                            String winner = standings_front.getJSONObject(s).getString("winner");
                            String color = standings_front.getJSONObject(s).getString("color");
                            standings8.setText(score_value);
                            if (color.length() > 5) {
                                // standings1.setTextColor(P);
                                standings8.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 8) {
                            String score_value = standings_front.getJSONObject(s).getString("score_value");
                            String hole_number = standings_front.getJSONObject(s).getString("hole_number");
                            String winner = standings_front.getJSONObject(s).getString("winner");
                            String color = standings_front.getJSONObject(s).getString("color");
                            standings9.setText(score_value);
                            if (color.length() > 5) {
                                // standings1.setTextColor(P);
                                standings9.setTextColor(Color.parseColor(color));
                            }
                        }
                    }
                }

                JSONObject par_value = front_9_array.getJSONObject(f).getJSONObject("par_value");
                String parvalue1 = par_value.getString("par_value_1");
                par_up_one.setText(parvalue1);
                String parvalue2 = par_value.getString("par_value_2");
                par_up_two.setText(parvalue2);
                String parvalue3 = par_value.getString("par_value_3");
                par_up_three.setText(parvalue3);
                String parvalue4 = par_value.getString("par_value_4");
                par_up_four.setText(parvalue4);
                String parvalue5 = par_value.getString("par_value_5");
                par_up_five.setText(parvalue5);
                String parvalue6 = par_value.getString("par_value_6");
                par_up_six.setText(parvalue6);
                String parvalue7 = par_value.getString("par_value_7");
                par_up_seven.setText(parvalue7);
                String parvalue8 = par_value.getString("par_value_8");
                par_up_eight.setText(parvalue8);
                String parvalue9 = par_value.getString("par_value_9");
                par_up_nine.setText(parvalue9);

                if (parvalue1.isEmpty() || parvalue1.equalsIgnoreCase("-")) {
                    par_s1 = 0;
                } else {
                    par_s1 = Integer.parseInt(parvalue1);
                }
                if (parvalue2.isEmpty() || parvalue2.equalsIgnoreCase("-")) {
                    par_s2 = 0;
                } else {
                    par_s2 = Integer.parseInt(parvalue2);
                }
                if (parvalue3.isEmpty() || parvalue3.equalsIgnoreCase("-")) {
                    par_s3 = 0;
                } else {
                    par_s3 = Integer.parseInt(parvalue3);
                }
                if (parvalue4.isEmpty() || parvalue4.equalsIgnoreCase("-")) {
                    par_s4 = 0;
                } else {
                    par_s4 = Integer.parseInt(parvalue4);
                }
                if (parvalue5.isEmpty() || parvalue5.equalsIgnoreCase("-")) {
                    par_s5 = 0;
                } else {
                    par_s5 = Integer.parseInt(parvalue5);
                }
                if (parvalue6.isEmpty() || parvalue6.equalsIgnoreCase("-")) {
                    par_s6 = 0;
                } else {
                    par_s6 = Integer.parseInt(parvalue6);
                }
                if (parvalue7.isEmpty() || parvalue7.equalsIgnoreCase("-")) {
                    par_s7 = 0;
                } else {
                    par_s7 = Integer.parseInt(parvalue7);
                }
                if (parvalue8.isEmpty() || parvalue8.equalsIgnoreCase("-")) {
                    par_s8 = 0;
                } else {
                    par_s8 = Integer.parseInt(parvalue8);
                }
                if (parvalue9.isEmpty() || parvalue9.equalsIgnoreCase("-")) {
                    par_s9 = 0;
                } else {
                    par_s9 = Integer.parseInt(parvalue9);
                }

                String paruptotal = String.valueOf(par_s1 + par_s2 + par_s3 + par_s4 + par_s5 + par_s6 + par_s7 + par_s8 + par_s9);
                par_up_total.setText(paruptotal);

                JSONObject hole_index = front_9_array.getJSONObject(f).getJSONObject("hole_index");
                String hole_index_1 = hole_index.getString("hole_index_1");
                ind_up_one.setText(hole_index_1);
                String hole_index_2 = hole_index.getString("hole_index_2");
                ind_up_two.setText(hole_index_2);
                String hole_index_3 = hole_index.getString("hole_index_3");
                ind_up_three.setText(hole_index_3);
                String hole_index_4 = hole_index.getString("hole_index_4");
                ind_up_four.setText(hole_index_4);
                String hole_index_5 = hole_index.getString("hole_index_5");
                ind_up_five.setText(hole_index_5);
                String hole_index_6 = hole_index.getString("hole_index_6");
                ind_up_six.setText(hole_index_6);
                String hole_index_7 = hole_index.getString("hole_index_7");
                ind_up_seven.setText(hole_index_7);
                String hole_index_8 = hole_index.getString("hole_index_8");
                ind_up_eight.setText(hole_index_8);
                String hole_index_9 = hole_index.getString("hole_index_9");
                ind_up_nine.setText(hole_index_9);

                if (hole_index_1.isEmpty() || hole_index_1.equalsIgnoreCase("-")) {
                    index_s1 = 0;
                } else {
                    index_s1 = Integer.parseInt(hole_index_1);
                }
                if (hole_index_2.isEmpty() || hole_index_2.equalsIgnoreCase("-")) {
                    index_s2 = 0;
                } else {
                    index_s2 = Integer.parseInt(hole_index_2);
                }
                if (hole_index_3.isEmpty() || hole_index_3.equalsIgnoreCase("-")) {
                    index_s3 = 0;
                } else {
                    index_s3 = Integer.parseInt(hole_index_3);
                }
                if (hole_index_4.isEmpty() || hole_index_4.equalsIgnoreCase("-")) {
                    index_s4 = 0;
                } else {
                    index_s4 = Integer.parseInt(hole_index_4);
                }
                if (hole_index_5.isEmpty() || hole_index_5.equalsIgnoreCase("-")) {
                    index_s5 = 0;
                } else {
                    index_s5 = Integer.parseInt(hole_index_5);
                }
                if (hole_index_6.isEmpty() || hole_index_6.equalsIgnoreCase("-")) {
                    index_s6 = 0;
                } else {
                    index_s6 = Integer.parseInt(hole_index_6);
                }
                if (hole_index_7.isEmpty() || hole_index_7.equalsIgnoreCase("-")) {
                    index_s7 = 0;
                } else {
                    index_s7 = Integer.parseInt(hole_index_7);
                }
                if (hole_index_8.isEmpty() || hole_index_8.equalsIgnoreCase("-")) {
                    index_s8 = 0;
                } else {
                    index_s8 = Integer.parseInt(hole_index_8);
                }
                if (hole_index_9.isEmpty() || hole_index_9.equalsIgnoreCase("-")) {
                    index_s9 = 0;
                } else {
                    index_s9 = Integer.parseInt(hole_index_9);
                }

                String indexuptotal = String.valueOf(index_s1 + index_s2 + index_s3 + index_s4 + index_s5 + index_s6 + index_s7 + index_s8 + index_s9);
                index_upTotal.setText(indexuptotal);


            }


            JSONArray back_9_array = data.getJSONArray("back_9_data");
            for (int f = 0; f < back_9_array.length(); f++) {
                backLayout.setVisibility(View.VISIBLE);
                JSONObject jsObj = back_9_array.getJSONObject(f).getJSONObject("team_a");
                teamAname = jsObj.getString("team_name");
                JSONArray pl_Array = jsObj.getJSONArray("player_list");
                for (int p = 0; p < pl_Array.length(); p++) {

                    if (p == 0) {

                        JSONObject jsPl_Object = pl_Array.getJSONObject(p);

                        player1_id = jsPl_Object.getString("player_id");
                        player1_Name = jsPl_Object.getString("name");
                        player1_handicap = jsPl_Object.getString("handicap_value");
                        player1_color = jsPl_Object.getString("color");

                        team_id1 = jsPl_Object.getString("team_id");
                        if (is_team.equalsIgnoreCase("1")) {
                            winner1 = team_id1;
                        } else {
                            winner1 = player1_id;
                        }

                        String short_name = jsPl_Object.getString("short_name");

                        player1_name_down.setTextColor(Color.parseColor(player1_color));
                        player1_name_down.setText(short_name + " " + player1_handicap);

                        JSONObject job_hole_Score = jsPl_Object.getJSONObject("hole_score");
                        JSONObject jb_Score10 = job_hole_Score.getJSONObject("hole_num_10");

                        String score1_down = jb_Score10.getString("score");
                        player1_down_g1.setText(score1_down);
                        String color_1 = jb_Score10.getString("color");

                        String is_lowest10 = jb_Score10.getString("is_lowest");
                        if (is_lowest10.equalsIgnoreCase("1")) {
                            player1_down_lay1.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g1.setTextColor(Color.WHITE);
                        } else {
                            player1_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g1.setTextColor(Color.BLACK);
                        }

                       /* if (color_1.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g1.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay1.setBackgroundColor(Color.parseColor(color_1));
                        }*/

                        JSONObject jb_Score11 = job_hole_Score.getJSONObject("hole_num_11");
                        String score2_down = jb_Score11.getString("score");
                        player1_down_g2.setText(score2_down);
                        String color_2 = jb_Score11.getString("color");

                        String is_lowest11 = jb_Score11.getString("is_lowest");
                        if (is_lowest11.equalsIgnoreCase("1")) {
                            player1_down_lay2.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g2.setTextColor(Color.WHITE);
                        } else {
                            player1_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g2.setTextColor(Color.BLACK);
                        }

                       /* if (color_2.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g2.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay2.setBackgroundColor(Color.parseColor(color_2));
                        }*/

                        JSONObject jb_Score12 = job_hole_Score.getJSONObject("hole_num_12");
                        String score3_down = jb_Score12.getString("score");
                        player1_down_g3.setText(score3_down);
                        String color_3 = jb_Score12.getString("color");

                        String is_lowest12 = jb_Score12.getString("is_lowest");
                        if (is_lowest12.equalsIgnoreCase("1")) {
                            player1_down_lay3.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g3.setTextColor(Color.WHITE);
                        } else {
                            player1_down_lay3.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g3.setTextColor(Color.BLACK);
                        }

                        /*if (color_3.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay3.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g3.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay3.setBackgroundColor(Color.parseColor(color_3));
                        }*/

                        JSONObject jb_Score13 = job_hole_Score.getJSONObject("hole_num_13");
                        String score4_down = jb_Score13.getString("score");
                        player1_down_g4.setText(score4_down);
                        String color_4 = jb_Score13.getString("color");

                        String is_lowest13 = jb_Score13.getString("is_lowest");
                        if (is_lowest13.equalsIgnoreCase("1")) {
                            player1_down_lay4.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g4.setTextColor(Color.WHITE);
                        } else {
                            player1_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g4.setTextColor(Color.BLACK);
                        }

                        /*if (color_4.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g4.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay4.setBackgroundColor(Color.parseColor(color_4));
                        }*/

                        JSONObject jb_Score14 = job_hole_Score.getJSONObject("hole_num_14");
                        String score5_down = jb_Score14.getString("score");
                        player1_down_g5.setText(score5_down);
                        String color_5 = jb_Score14.getString("color");

                        String is_lowest14 = jb_Score14.getString("is_lowest");
                        if (is_lowest14.equalsIgnoreCase("1")) {
                            player1_down_lay5.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g5.setTextColor(Color.WHITE);
                        } else {
                            player1_down_lay5.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g5.setTextColor(Color.BLACK);
                        }

                        /*if (color_5.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay5.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g5.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay5.setBackgroundColor(Color.parseColor(color_5));
                        }*/

                        JSONObject jb_Score15 = job_hole_Score.getJSONObject("hole_num_15");
                        String score6_down = jb_Score15.getString("score");
                        player1_down_g6.setText(score6_down);
                        String color_6 = jb_Score15.getString("color");

                        String is_lowest15 = jb_Score15.getString("is_lowest");
                        if (is_lowest15.equalsIgnoreCase("1")) {
                            player1_down_lay6.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g6.setTextColor(Color.WHITE);
                        } else {
                            player1_down_lay6.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g6.setTextColor(Color.BLACK);
                        }

                        /*if (color_6.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay6.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g6.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay6.setBackgroundColor(Color.parseColor(color_6));
                        }*/

                        JSONObject jb_Score16 = job_hole_Score.getJSONObject("hole_num_16");
                        String score7_down = jb_Score16.getString("score");
                        player1_down_g7.setText(score7_down);
                        String color_7 = jb_Score16.getString("color");

                        String is_lowest16 = jb_Score16.getString("is_lowest");
                        if (is_lowest16.equalsIgnoreCase("1")) {
                            player1_down_lay7.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g7.setTextColor(Color.WHITE);
                        } else {
                            player1_down_lay7.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g7.setTextColor(Color.BLACK);
                        }

                        /*if (color_7.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay7.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g7.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay7.setBackgroundColor(Color.parseColor(color_7));
                        }*/

                        JSONObject jb_Score17 = job_hole_Score.getJSONObject("hole_num_17");
                        String score8_down = jb_Score17.getString("score");
                        player1_down_g8.setText(score8_down);
                        String color_8 = jb_Score17.getString("color");

                        String is_lowest17 = jb_Score17.getString("is_lowest");
                        if (is_lowest17.equalsIgnoreCase("1")) {
                            player1_down_lay8.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g8.setTextColor(Color.WHITE);
                        } else {
                            player1_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g8.setTextColor(Color.BLACK);
                        }

                        /*if (color_8.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g8.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay8.setBackgroundColor(Color.parseColor(color_8));
                        }*/

                        JSONObject jb_Score18 = job_hole_Score.getJSONObject("hole_num_18");
                        String score9_down = jb_Score18.getString("score");
                        player1_down_g9.setText(score9_down);
                        String color_9 = jb_Score18.getString("color");

                        String is_lowest18 = jb_Score18.getString("is_lowest");
                        if (is_lowest18.equalsIgnoreCase("1")) {
                            player1_down_lay9.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g9.setTextColor(Color.WHITE);
                        } else {
                            player1_down_lay9.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g9.setTextColor(Color.BLACK);
                        }

                        /*if (color_9.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay9.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g9.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }*/


                        if (score1_down.isEmpty() || score1_down.equalsIgnoreCase("-")) {
                            p1_s10 = 0;
                        } else {
                            p1_s10 = Integer.parseInt(score1_down);
                        }
                        if (score2_down.isEmpty() || score2_down.equalsIgnoreCase("-")) {
                            p1_s11 = 0;
                        } else {
                            p1_s11 = Integer.parseInt(score2_down);
                        }
                        if (score3_down.isEmpty() || score3_down.equalsIgnoreCase("-")) {
                            p1_s12 = 0;
                        } else {
                            p1_s12 = Integer.parseInt(score3_down);
                        }
                        if (score4_down.isEmpty() || score4_down.equalsIgnoreCase("-")) {
                            p1_s13 = 0;
                        } else {
                            p1_s13 = Integer.parseInt(score4_down);
                        }
                        if (score5_down.isEmpty() || score5_down.equalsIgnoreCase("-")) {
                            p1_s14 = 0;
                        } else {
                            p1_s14 = Integer.parseInt(score5_down);
                        }
                        if (score6_down.isEmpty() || score6_down.equalsIgnoreCase("-")) {
                            p1_s15 = 0;
                        } else {
                            p1_s15 = Integer.parseInt(score6_down);
                        }
                        if (score7_down.isEmpty() || score7_down.equalsIgnoreCase("-")) {
                            p1_s16 = 0;
                        } else {
                            p1_s16 = Integer.parseInt(score7_down);
                        }
                        if (score8_down.isEmpty() || score8_down.equalsIgnoreCase("-")) {
                            p1_s17 = 0;
                        } else {
                            p1_s17 = Integer.parseInt(score8_down);
                        }
                        if (score9_down.isEmpty() || score9_down.equalsIgnoreCase("-")) {
                            p1_s18 = 0;
                        } else {
                            p1_s18 = Integer.parseInt(score9_down);
                        }

                        String p1_down_total = String.valueOf(p1_s10 + p1_s12 + p1_s13 + p1_s14 + p1_s15 + p1_s16 + p1_s17 + p1_s18 + p1_s11);
                        player1_down_total.setText(p1_down_total);


                    } else if (p == 1) {

                        JSONObject jsPl_Object = pl_Array.getJSONObject(p);

                        player2_id = jsPl_Object.getString("player_id");
                        player2_Name = jsPl_Object.getString("name");
                        player2_handicap = jsPl_Object.getString("handicap_value");
                        player2_color = jsPl_Object.getString("color");


                        team_id1 = jsPl_Object.getString("team_id");
                        if (is_team.equalsIgnoreCase("1")) {
                            winner1 = team_id1;
                        } else {
                            winner1 = player2_id;
                        }

                        String short_name = jsPl_Object.getString("short_name");

                        player2_name_down.setTextColor(Color.parseColor(player2_color));
                        player2_name_down.setText(short_name + " " + player2_handicap);

                        JSONObject job_hole_Score = jsPl_Object.getJSONObject("hole_score");
                        JSONObject jb_Score10 = job_hole_Score.getJSONObject("hole_num_10");


                        String score1_down = jb_Score10.getString("score");
                        player2_down_g1.setText(score1_down);
                        String color_1 = jb_Score10.getString("color");

                        String is_lowest10 = jb_Score10.getString("is_lowest");
                        if (is_lowest10.equalsIgnoreCase("1")) {
                            player2_down_lay1.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g1.setTextColor(Color.WHITE);
                        } else {
                            player2_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g1.setTextColor(Color.BLACK);
                        }

                        /*if (color_1.equalsIgnoreCase("#ffffff")) {
                            player2_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g1.setTextColor(Color.BLACK);
                        } else {
                            player2_down_lay1.setBackgroundColor(Color.parseColor(color_1));
                        }*/

                        JSONObject jb_Score11 = job_hole_Score.getJSONObject("hole_num_11");
                        String score2_down = jb_Score11.getString("score");
                        player2_down_g2.setText(score2_down);
                        String color_2 = jb_Score11.getString("color");

                        String is_lowest11 = jb_Score11.getString("is_lowest");
                        if (is_lowest11.equalsIgnoreCase("1")) {
                            player2_down_lay2.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g2.setTextColor(Color.WHITE);
                        } else {
                            player2_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g2.setTextColor(Color.BLACK);
                        }

                        /*if (color_2.equalsIgnoreCase("#ffffff")) {
                            player2_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g2.setTextColor(Color.BLACK);
                        } else {
                            player2_down_lay2.setBackgroundColor(Color.parseColor(color_2));
                        }*/

                        JSONObject jb_Score12 = job_hole_Score.getJSONObject("hole_num_12");

                        String score3_down = jb_Score12.getString("score");
                        player2_down_g3.setText(score3_down);
                        String color_3 = jb_Score12.getString("color");

                        String is_lowest12 = jb_Score12.getString("is_lowest");
                        if (is_lowest12.equalsIgnoreCase("1")) {
                            player2_down_lay3.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g3.setTextColor(Color.WHITE);
                        } else {
                            player2_down_lay3.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g3.setTextColor(Color.BLACK);
                        }

                        /*if (color_3.equalsIgnoreCase("#ffffff")) {
                            player2_down_lay3.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g3.setTextColor(Color.BLACK);
                        } else {
                            player2_down_lay3.setBackgroundColor(Color.parseColor(color_3));
                        }*/

                        JSONObject jb_Score13 = job_hole_Score.getJSONObject("hole_num_13");
                        String score4_down = jb_Score13.getString("score");
                        player2_down_g4.setText(score4_down);
                        String color_4 = jb_Score13.getString("color");

                        String is_lowest13 = jb_Score13.getString("is_lowest");
                        if (is_lowest13.equalsIgnoreCase("1")) {
                            player2_down_lay4.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g4.setTextColor(Color.WHITE);
                        } else {
                            player2_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g4.setTextColor(Color.BLACK);
                        }

                        /*if (color_4.equalsIgnoreCase("#ffffff")) {
                            player2_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g4.setTextColor(Color.BLACK);
                        } else {
                            player2_down_lay4.setBackgroundColor(Color.parseColor(color_4));
                        }*/

                        JSONObject jb_Score14 = job_hole_Score.getJSONObject("hole_num_14");
                        String score5_down = jb_Score14.getString("score");
                        player2_down_g5.setText(score5_down);
                        String color_5 = jb_Score14.getString("color");

                        String is_lowest14 = jb_Score14.getString("is_lowest");
                        if (is_lowest14.equalsIgnoreCase("1")) {
                            player2_down_lay5.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g5.setTextColor(Color.WHITE);
                        } else {
                            player2_down_lay5.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g5.setTextColor(Color.BLACK);
                        }

                        /*if (color_5.equalsIgnoreCase("#ffffff")) {
                            player2_down_lay5.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g5.setTextColor(Color.BLACK);
                        } else {
                            player2_down_lay5.setBackgroundColor(Color.parseColor(color_5));
                        }*/

                        JSONObject jb_Score15 = job_hole_Score.getJSONObject("hole_num_15");

                        String score6_down = jb_Score15.getString("score");
                        player2_down_g6.setText(score6_down);
                        String color_6 = jb_Score15.getString("color");

                        String is_lowest15 = jb_Score15.getString("is_lowest");
                        if (is_lowest15.equalsIgnoreCase("1")) {
                            player2_down_lay6.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g6.setTextColor(Color.WHITE);
                        } else {
                            player2_down_lay6.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g6.setTextColor(Color.BLACK);
                        }

                        /*if (color_6.equalsIgnoreCase("#ffffff")) {
                            player2_down_lay6.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g6.setTextColor(Color.BLACK);
                        } else {
                            player2_down_lay6.setBackgroundColor(Color.parseColor(color_6));
                        }*/

                        JSONObject jb_Score16 = job_hole_Score.getJSONObject("hole_num_16");
                        String score7_down = jb_Score16.getString("score");
                        player2_down_g7.setText(score7_down);
                        String color_7 = jb_Score16.getString("color");

                        String is_lowest16 = jb_Score16.getString("is_lowest");
                        if (is_lowest16.equalsIgnoreCase("1")) {
                            player2_down_lay7.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g7.setTextColor(Color.WHITE);
                        } else {
                            player2_down_lay7.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g7.setTextColor(Color.BLACK);
                        }

                        /*if (color_7.equalsIgnoreCase("#ffffff")) {
                            player2_down_lay7.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g7.setTextColor(Color.BLACK);
                        } else {
                            player2_down_lay7.setBackgroundColor(Color.parseColor(color_7));
                        }*/

                        JSONObject jb_Score17 = job_hole_Score.getJSONObject("hole_num_17");
                        String score8_down = jb_Score17.getString("score");
                        player2_down_g8.setText(score8_down);
                        String color_8 = jb_Score17.getString("color");

                        String is_lowest17 = jb_Score17.getString("is_lowest");
                        if (is_lowest17.equalsIgnoreCase("1")) {
                            player2_down_lay8.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g8.setTextColor(Color.WHITE);
                        } else {
                            player2_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g8.setTextColor(Color.BLACK);
                        }

                       /* if (color_8.equalsIgnoreCase("#ffffff")) {
                            player2_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g8.setTextColor(Color.BLACK);
                        } else {
                            player2_down_lay8.setBackgroundColor(Color.parseColor(color_8));
                        }*/

                        JSONObject jb_Score18 = job_hole_Score.getJSONObject("hole_num_18");

                        String score9_down = jb_Score18.getString("score");
                        player2_down_g9.setText(score9_down);
                        String color_9 = jb_Score18.getString("color");

                        String is_lowest18 = jb_Score18.getString("is_lowest");
                        if (is_lowest18.equalsIgnoreCase("1")) {
                            player2_down_lay9.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g9.setTextColor(Color.WHITE);
                        } else {
                            player2_down_lay9.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g9.setTextColor(Color.BLACK);
                        }

                       /* if (color_9.equalsIgnoreCase("#ffffff")) {
                            player2_down_lay9.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g9.setTextColor(Color.BLACK);
                        } else {
                            player2_down_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }*/

                        if (score1_down.isEmpty() || score1_down.equalsIgnoreCase("-")) {
                            p2_s10 = 0;
                        } else {
                            p2_s10 = Integer.parseInt(score1_down);
                        }
                        if (score2_down.isEmpty() || score2_down.equalsIgnoreCase("-")) {
                            p2_s11 = 0;
                        } else {
                            p2_s11 = Integer.parseInt(score2_down);
                        }
                        if (score3_down.isEmpty() || score3_down.equalsIgnoreCase("-")) {
                            p2_s12 = 0;
                        } else {
                            p2_s12 = Integer.parseInt(score3_down);
                        }
                        if (score4_down.isEmpty() || score4_down.equalsIgnoreCase("-")) {
                            p2_s13 = 0;
                        } else {
                            p2_s13 = Integer.parseInt(score4_down);
                        }
                        if (score5_down.isEmpty() || score5_down.equalsIgnoreCase("-")) {
                            p2_s14 = 0;
                        } else {
                            p2_s14 = Integer.parseInt(score5_down);
                        }
                        if (score6_down.isEmpty() || score6_down.equalsIgnoreCase("-")) {
                            p2_s15 = 0;
                        } else {
                            p2_s15 = Integer.parseInt(score6_down);
                        }
                        if (score7_down.isEmpty() || score7_down.equalsIgnoreCase("-")) {
                            p2_s16 = 0;
                        } else {
                            p2_s16 = Integer.parseInt(score7_down);
                        }
                        if (score8_down.isEmpty() || score8_down.equalsIgnoreCase("-")) {
                            p2_s17 = 0;
                        } else {
                            p2_s17 = Integer.parseInt(score8_down);
                        }
                        if (score9_down.isEmpty() || score9_down.equalsIgnoreCase("-")) {
                            p2_s18 = 0;
                        } else {
                            p2_s18 = Integer.parseInt(score9_down);
                        }

                        String p2_down_total = String.valueOf(p2_s11 + p2_s12 + p2_s13 + p2_s14 + p2_s15 + p2_s16 + p2_s17 + p2_s18 + p2_s10);
                        player2_down_total.setText(p2_down_total);
                    }
                    if (p == 2) {

                        JSONObject jsPl_Object = pl_Array.getJSONObject(p);

                        player3_id = jsPl_Object.getString("player_id");
                        player3_Name = jsPl_Object.getString("name");
                        player3_handicap = jsPl_Object.getString("handicap_value");
                        player3_color = jsPl_Object.getString("color");

                        team_id2 = jsPl_Object.getString("team_id");
                        if (is_team.equalsIgnoreCase("1")) {
                            winner2 = team_id2;
                        } else {
                            winner2 = player3_id;
                        }
                        String short_name = jsPl_Object.getString("short_name");

                        player3_name_down.setTextColor(Color.parseColor(player3_color));
                        player3_name_down.setText(short_name + " " + player3_handicap);

                        JSONObject job_hole_Score = jsPl_Object.getJSONObject("hole_score");
                        JSONObject jb_Score10 = job_hole_Score.getJSONObject("hole_num_10");

                        String score1_down = jb_Score10.getString("score");
                        player3_down_g1.setText(score1_down);
                        String color_1 = jb_Score10.getString("color");

                        String is_lowest10 = jb_Score10.getString("is_lowest");
                        if (is_lowest10.equalsIgnoreCase("1")) {
                            player3_down_lay1.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g1.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g1.setTextColor(Color.BLACK);
                        }

                           /* if (color_1.equalsIgnoreCase("#ffffff")) {
                                player3_down_lay1.setBackgroundResource(R.drawable.cell_default);
                                player3_down_g1.setTextColor(Color.BLACK);
                            } else {
                                player3_down_lay1.setBackgroundColor(Color.parseColor(color_1));
                            }*/

                        JSONObject jb_Score11 = job_hole_Score.getJSONObject("hole_num_11");
                        String score2_down = jb_Score11.getString("score");
                        player3_down_g2.setText(score2_down);
                        String color_2 = jb_Score11.getString("color");

                        String is_lowest11 = jb_Score11.getString("is_lowest");
                        if (is_lowest11.equalsIgnoreCase("1")) {
                            player3_down_lay2.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g2.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g2.setTextColor(Color.BLACK);
                        }

                            /*if (color_2.equalsIgnoreCase("#ffffff")) {
                                player3_down_lay2.setBackgroundResource(R.drawable.cell_default);
                                player3_down_g2.setTextColor(Color.BLACK);
                            } else {
                                player3_down_lay2.setBackgroundColor(Color.parseColor(color_2));
                            }*/

                        JSONObject jb_Score12 = job_hole_Score.getJSONObject("hole_num_12");
                        String score3_down = jb_Score12.getString("score");
                        player3_down_g3.setText(score3_down);
                        String color_3 = jb_Score12.getString("color");

                        String is_lowest12 = jb_Score12.getString("is_lowest");
                        if (is_lowest12.equalsIgnoreCase("1")) {
                            player3_down_lay3.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g3.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay3.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g3.setTextColor(Color.BLACK);
                        }


                       /* if (color_3.equalsIgnoreCase("#ffffff")) {
                                player3_down_lay3.setBackgroundResource(R.drawable.cell_default);
                                player3_down_g3.setTextColor(Color.BLACK);
                            } else {
                                player3_down_lay3.setBackgroundColor(Color.parseColor(color_3));
                            }*/

                        JSONObject jb_Score13 = job_hole_Score.getJSONObject("hole_num_13");
                        String score4_down = jb_Score13.getString("score");
                        player3_down_g4.setText(score4_down);
                        String color_4 = jb_Score13.getString("color");

                        String is_lowest13 = jb_Score13.getString("is_lowest");
                        if (is_lowest13.equalsIgnoreCase("1")) {
                            player3_down_lay4.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g4.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g4.setTextColor(Color.BLACK);
                        }


                        /*if (color_4.equalsIgnoreCase("#ffffff")) {
                                player3_down_lay4.setBackgroundResource(R.drawable.cell_default);
                                player3_down_g4.setTextColor(Color.BLACK);
                            } else {
                                player3_down_lay4.setBackgroundColor(Color.parseColor(color_4));
                            }*/

                        JSONObject jb_Score14 = job_hole_Score.getJSONObject("hole_num_14");
                        String score5_down = jb_Score14.getString("score");
                        player3_down_g5.setText(score5_down);
                        String color_5 = jb_Score14.getString("color");

                        String is_lowest14 = jb_Score14.getString("is_lowest");
                        if (is_lowest14.equalsIgnoreCase("1")) {
                            player3_down_lay5.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g5.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay5.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g5.setTextColor(Color.BLACK);
                        }


                           /* if (color_5.equalsIgnoreCase("#ffffff")) {
                                player3_down_lay5.setBackgroundResource(R.drawable.cell_default);
                                player3_down_g5.setTextColor(Color.BLACK);
                            } else {
                                player3_down_lay5.setBackgroundColor(Color.parseColor(color_5));
                            }*/

                        JSONObject jb_Score15 = job_hole_Score.getJSONObject("hole_num_15");
                        String score6_down = jb_Score15.getString("score");
                        player3_down_g6.setText(score6_down);
                        String color_6 = jb_Score15.getString("color");

                        String is_lowest15 = jb_Score15.getString("is_lowest");
                        if (is_lowest15.equalsIgnoreCase("1")) {
                            player3_down_lay6.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g6.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay6.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g6.setTextColor(Color.BLACK);
                        }


                            /*if (color_6.equalsIgnoreCase("#ffffff")) {
                                player3_down_lay6.setBackgroundResource(R.drawable.cell_default);
                                player3_down_g6.setTextColor(Color.BLACK);
                            } else {
                                player3_down_lay6.setBackgroundColor(Color.parseColor(color_6));
                            }*/

                        JSONObject jb_Score16 = job_hole_Score.getJSONObject("hole_num_16");
                        String score7_down = jb_Score16.getString("score");
                        player3_down_g7.setText(score7_down);
                        String color_7 = jb_Score16.getString("color");

                        String is_lowest16 = jb_Score16.getString("is_lowest");
                        if (is_lowest16.equalsIgnoreCase("1")) {
                            player3_down_lay7.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g7.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay7.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g7.setTextColor(Color.BLACK);
                        }


                            /*if (color_7.equalsIgnoreCase("#ffffff")) {
                                player3_down_lay7.setBackgroundResource(R.drawable.cell_default);
                                player3_down_g7.setTextColor(Color.BLACK);
                            } else {
                                player3_down_lay7.setBackgroundColor(Color.parseColor(color_7));
                            }*/

                        JSONObject jb_Score17 = job_hole_Score.getJSONObject("hole_num_17");
                        String score8_down = jb_Score17.getString("score");
                        player3_down_g8.setText(score8_down);
                        String color_8 = jb_Score17.getString("color");

                        String is_lowest17 = jb_Score17.getString("is_lowest");
                        if (is_lowest17.equalsIgnoreCase("1")) {
                            player3_down_lay8.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g8.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g8.setTextColor(Color.BLACK);
                        }


                            /*if (color_8.equalsIgnoreCase("#ffffff")) {
                                player3_down_lay8.setBackgroundResource(R.drawable.cell_default);
                                player3_down_g8.setTextColor(Color.BLACK);
                            } else {
                                player3_down_lay8.setBackgroundColor(Color.parseColor(color_8));
                            }*/

                        JSONObject jb_Score18 = job_hole_Score.getJSONObject("hole_num_18");

                        String score9_down = jb_Score18.getString("score");
                        player3_down_g9.setText(score9_down);
                        String color_9 = jb_Score18.getString("color");

                        String is_lowest18 = jb_Score18.getString("is_lowest");
                        if (is_lowest18.equalsIgnoreCase("1")) {
                            player3_down_lay9.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g9.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay9.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g9.setTextColor(Color.BLACK);
                        }


                            /*if (color_9.equalsIgnoreCase("#ffffff")) {
                                player3_down_lay9.setBackgroundResource(R.drawable.cell_default);
                                player3_down_g9.setTextColor(Color.BLACK);
                            } else {
                                player3_down_lay9.setBackgroundColor(Color.parseColor(color_9));
                            }*/


                        if (score1_down.isEmpty() || score1_down.equalsIgnoreCase("-")) {
                            p3_s10 = 0;
                        } else {
                            p3_s10 = Integer.parseInt(score1_down);
                        }
                        if (score2_down.isEmpty() || score2_down.equalsIgnoreCase("-")) {
                            p3_s11 = 0;
                        } else {
                            p3_s11 = Integer.parseInt(score2_down);
                        }
                        if (score3_down.isEmpty() || score3_down.equalsIgnoreCase("-")) {
                            p3_s12 = 0;
                        } else {
                            p3_s12 = Integer.parseInt(score3_down);
                        }
                        if (score4_down.isEmpty() || score4_down.equalsIgnoreCase("-")) {
                            p3_s13 = 0;
                        } else {
                            p3_s13 = Integer.parseInt(score4_down);
                        }
                        if (score5_down.isEmpty() || score5_down.equalsIgnoreCase("-")) {
                            p3_s14 = 0;
                        } else {
                            p3_s14 = Integer.parseInt(score5_down);
                        }
                        if (score6_down.isEmpty() || score6_down.equalsIgnoreCase("-")) {
                            p3_s15 = 0;
                        } else {
                            p3_s15 = Integer.parseInt(score6_down);
                        }
                        if (score7_down.isEmpty() || score7_down.equalsIgnoreCase("-")) {
                            p3_s16 = 0;
                        } else {
                            p3_s16 = Integer.parseInt(score7_down);
                        }
                        if (score8_down.isEmpty() || score8_down.equalsIgnoreCase("-")) {
                            p3_s17 = 0;
                        } else {
                            p3_s17 = Integer.parseInt(score8_down);
                        }
                        if (score9_down.isEmpty() || score9_down.equalsIgnoreCase("-")) {
                            p3_s18 = 0;
                        } else {
                            p3_s18 = Integer.parseInt(score9_down);
                        }

                        String p3_down_total = String.valueOf(p3_s11 + p3_s12 + p3_s13 + p3_s14 + p3_s15 + p3_s16 + p3_s17 + p3_s18 + p3_s10);
                        player3_down_total.setText(p3_down_total);
                    }
                }


                JSONObject jsObj_b = back_9_array.getJSONObject(f).getJSONObject("team_b");
                teamBname = jsObj_b.getString("team_name");
                JSONArray pl_Array_teamb = jsObj_b.getJSONArray("player_list");
                for (int p = 0; p < pl_Array_teamb.length(); p++) {

                    if (p == 0) {
                        JSONObject jsPl_Object = pl_Array_teamb.getJSONObject(p);

                        player3_id = jsPl_Object.getString("player_id");
                        player3_Name = jsPl_Object.getString("name");
                        player3_handicap = jsPl_Object.getString("handicap_value");
                        player3_color = jsPl_Object.getString("color");

                        team_id2 = jsPl_Object.getString("team_id");
                        if (is_team.equalsIgnoreCase("1")) {
                            winner2 = team_id2;
                        } else {
                            winner2 = player3_id;
                        }
                        String short_name = jsPl_Object.getString("short_name");

                        player3_name_down.setTextColor(Color.parseColor(player3_color));
                        player3_name_down.setText(short_name + " " + player3_handicap);

                        JSONObject job_hole_Score = jsPl_Object.getJSONObject("hole_score");
                        JSONObject jb_Score10 = job_hole_Score.getJSONObject("hole_num_10");

                        String score1_down = jb_Score10.getString("score");
                        player3_down_g1.setText(score1_down);
                        String color_1 = jb_Score10.getString("color");

                        String is_lowest10 = jb_Score10.getString("is_lowest");
                        if (is_lowest10.equalsIgnoreCase("1")) {
                            player3_down_lay1.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g1.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g1.setTextColor(Color.BLACK);
                        }


                        /*if (color_1.equalsIgnoreCase("#ffffff")) {
                            player3_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g1.setTextColor(Color.BLACK);
                        } else {
                            player3_down_lay1.setBackgroundColor(Color.parseColor(color_1));
                        }*/

                        JSONObject jb_Score11 = job_hole_Score.getJSONObject("hole_num_11");
                        String score2_down = jb_Score11.getString("score");
                        player3_down_g2.setText(score2_down);
                        String color_2 = jb_Score11.getString("color");

                        String is_lowest11 = jb_Score11.getString("is_lowest");
                        if (is_lowest11.equalsIgnoreCase("1")) {
                            player3_down_lay2.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g2.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g2.setTextColor(Color.BLACK);
                        }


                       /* if (color_2.equalsIgnoreCase("#ffffff")) {
                            player3_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g2.setTextColor(Color.BLACK);
                        } else {
                            player3_down_lay2.setBackgroundColor(Color.parseColor(color_2));
                        }*/

                        JSONObject jb_Score12 = job_hole_Score.getJSONObject("hole_num_12");
                        String score3_down = jb_Score12.getString("score");
                        player3_down_g3.setText(score3_down);
                        String color_3 = jb_Score12.getString("color");

                        String is_lowest12 = jb_Score12.getString("is_lowest");
                        if (is_lowest12.equalsIgnoreCase("1")) {
                            player3_down_lay3.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g3.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay3.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g3.setTextColor(Color.BLACK);
                        }


                        /*if (color_3.equalsIgnoreCase("#ffffff")) {
                            player3_down_lay3.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g3.setTextColor(Color.BLACK);
                        } else {
                            player3_down_lay3.setBackgroundColor(Color.parseColor(color_3));
                        }*/

                        JSONObject jb_Score13 = job_hole_Score.getJSONObject("hole_num_13");
                        String score4_down = jb_Score13.getString("score");
                        player3_down_g4.setText(score4_down);
                        String color_4 = jb_Score13.getString("color");

                        String is_lowest13 = jb_Score13.getString("is_lowest");
                        if (is_lowest13.equalsIgnoreCase("1")) {
                            player3_down_lay4.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g4.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g4.setTextColor(Color.BLACK);
                        }


                        /*if (color_4.equalsIgnoreCase("#ffffff")) {
                            player3_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g4.setTextColor(Color.BLACK);
                        } else {
                            player3_down_lay4.setBackgroundColor(Color.parseColor(color_4));
                        }*/

                        JSONObject jb_Score14 = job_hole_Score.getJSONObject("hole_num_14");
                        String score5_down = jb_Score14.getString("score");
                        player3_down_g5.setText(score5_down);
                        String color_5 = jb_Score14.getString("color");

                        String is_lowest14 = jb_Score14.getString("is_lowest");
                        if (is_lowest14.equalsIgnoreCase("1")) {
                            player3_down_lay5.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g5.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay5.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g5.setTextColor(Color.BLACK);
                        }


                        /*if (color_5.equalsIgnoreCase("#ffffff")) {
                            player3_down_lay5.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g5.setTextColor(Color.BLACK);
                        } else {
                            player3_down_lay5.setBackgroundColor(Color.parseColor(color_5));
                        }*/

                        JSONObject jb_Score15 = job_hole_Score.getJSONObject("hole_num_15");
                        String score6_down = jb_Score15.getString("score");
                        player3_down_g6.setText(score6_down);
                        String color_6 = jb_Score15.getString("color");

                        String is_lowest15 = jb_Score15.getString("is_lowest");
                        if (is_lowest15.equalsIgnoreCase("1")) {
                            player3_down_lay6.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g6.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay6.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g6.setTextColor(Color.BLACK);
                        }


                        /*if (color_6.equalsIgnoreCase("#ffffff")) {
                            player3_down_lay6.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g6.setTextColor(Color.BLACK);
                        } else {
                            player3_down_lay6.setBackgroundColor(Color.parseColor(color_6));
                        }*/

                        JSONObject jb_Score16 = job_hole_Score.getJSONObject("hole_num_16");
                        String score7_down = jb_Score16.getString("score");
                        player3_down_g7.setText(score7_down);
                        String color_7 = jb_Score16.getString("color");

                        String is_lowest16 = jb_Score16.getString("is_lowest");
                        if (is_lowest16.equalsIgnoreCase("1")) {
                            player3_down_lay7.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g7.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay7.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g7.setTextColor(Color.BLACK);
                        }


                        /*if (color_7.equalsIgnoreCase("#ffffff")) {
                            player3_down_lay7.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g7.setTextColor(Color.BLACK);
                        } else {
                            player3_down_lay7.setBackgroundColor(Color.parseColor(color_7));
                        }*/

                        JSONObject jb_Score17 = job_hole_Score.getJSONObject("hole_num_17");
                        String score8_down = jb_Score17.getString("score");
                        player3_down_g8.setText(score8_down);
                        String color_8 = jb_Score17.getString("color");

                        String is_lowest17 = jb_Score17.getString("is_lowest");
                        if (is_lowest17.equalsIgnoreCase("1")) {
                            player3_down_lay8.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g8.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g8.setTextColor(Color.BLACK);
                        }


                        /*if (color_8.equalsIgnoreCase("#ffffff")) {
                            player3_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g8.setTextColor(Color.BLACK);
                        } else {
                            player3_down_lay8.setBackgroundColor(Color.parseColor(color_8));
                        }*/

                        JSONObject jb_Score18 = job_hole_Score.getJSONObject("hole_num_18");

                        String score9_down = jb_Score18.getString("score");
                        player3_down_g9.setText(score9_down);
                        String color_9 = jb_Score18.getString("color");

                        String is_lowest18 = jb_Score18.getString("is_lowest");
                        if (is_lowest18.equalsIgnoreCase("1")) {
                            player3_down_lay9.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g9.setTextColor(Color.WHITE);
                        } else {
                            player3_down_lay9.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g9.setTextColor(Color.BLACK);
                        }


                        /*if (color_9.equalsIgnoreCase("#ffffff")) {
                            player3_down_lay9.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g9.setTextColor(Color.BLACK);
                        } else {
                            player3_down_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }*/


                        if (score1_down.isEmpty() || score1_down.equalsIgnoreCase("-")) {
                            p3_s10 = 0;
                        } else {
                            p3_s10 = Integer.parseInt(score1_down);
                        }
                        if (score2_down.isEmpty() || score2_down.equalsIgnoreCase("-")) {
                            p3_s11 = 0;
                        } else {
                            p3_s11 = Integer.parseInt(score2_down);
                        }
                        if (score3_down.isEmpty() || score3_down.equalsIgnoreCase("-")) {
                            p3_s12 = 0;
                        } else {
                            p3_s12 = Integer.parseInt(score3_down);
                        }
                        if (score4_down.isEmpty() || score4_down.equalsIgnoreCase("-")) {
                            p3_s13 = 0;
                        } else {
                            p3_s13 = Integer.parseInt(score4_down);
                        }
                        if (score5_down.isEmpty() || score5_down.equalsIgnoreCase("-")) {
                            p3_s14 = 0;
                        } else {
                            p3_s14 = Integer.parseInt(score5_down);
                        }
                        if (score6_down.isEmpty() || score6_down.equalsIgnoreCase("-")) {
                            p3_s15 = 0;
                        } else {
                            p3_s15 = Integer.parseInt(score6_down);
                        }
                        if (score7_down.isEmpty() || score7_down.equalsIgnoreCase("-")) {
                            p3_s16 = 0;
                        } else {
                            p3_s16 = Integer.parseInt(score7_down);
                        }
                        if (score8_down.isEmpty() || score8_down.equalsIgnoreCase("-")) {
                            p3_s17 = 0;
                        } else {
                            p3_s17 = Integer.parseInt(score8_down);
                        }
                        if (score9_down.isEmpty() || score9_down.equalsIgnoreCase("-")) {
                            p3_s18 = 0;
                        } else {
                            p3_s18 = Integer.parseInt(score9_down);
                        }

                        String p3_down_total = String.valueOf(p3_s11 + p3_s12 + p3_s13 + p3_s14 + p3_s15 + p3_s16 + p3_s17 + p3_s18 + p3_s10);
                        player3_down_total.setText(p3_down_total);

                    } else if (p == 1) {
                        JSONObject jsPl_Object = pl_Array_teamb.getJSONObject(p);

                        player4_id = jsPl_Object.getString("player_id");
                        player4_Name = jsPl_Object.getString("name");
                        player4_handicap = jsPl_Object.getString("handicap_value");
                        player4_color = jsPl_Object.getString("color");

                        team_id2 = jsPl_Object.getString("team_id");
                        if (is_team.equalsIgnoreCase("1")) {
                            winner2 = team_id2;
                        } else {
                            winner2 = player4_id;
                        }
                        String short_name = jsPl_Object.getString("short_name");

                        player4_name_down.setTextColor(Color.parseColor(player4_color));
                        player4_name_down.setText(short_name + " " + player4_handicap);

                        JSONObject job_hole_Score = jsPl_Object.getJSONObject("hole_score");
                        JSONObject jb_Score10 = job_hole_Score.getJSONObject("hole_num_10");

                        String score1_down = jb_Score10.getString("score");
                        player4_down_g1.setText(score1_down);
                        String color_1 = jb_Score10.getString("color");

                        String is_lowest10 = jb_Score10.getString("is_lowest");
                        if (is_lowest10.equalsIgnoreCase("1")) {
                            player4_down_lay1.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g1.setTextColor(Color.WHITE);
                        } else {
                            player4_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g1.setTextColor(Color.BLACK);
                        }


                        /*if (color_1.equalsIgnoreCase("#ffffff")) {
                            player4_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g1.setTextColor(Color.BLACK);
                        } else {
                            player4_down_lay1.setBackgroundColor(Color.parseColor(color_1));
                        }*/

                        JSONObject jb_Score11 = job_hole_Score.getJSONObject("hole_num_11");
                        String score2_down = jb_Score11.getString("score");
                        player4_down_g2.setText(score2_down);
                        String color_2 = jb_Score11.getString("color");

                        String is_lowest11 = jb_Score11.getString("is_lowest");
                        if (is_lowest11.equalsIgnoreCase("1")) {
                            player4_down_lay2.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g2.setTextColor(Color.WHITE);
                        } else {
                            player4_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g2.setTextColor(Color.BLACK);
                        }

                        /*if (color_2.equalsIgnoreCase("#ffffff")) {
                            player4_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g2.setTextColor(Color.BLACK);
                        } else {
                            player4_down_lay2.setBackgroundColor(Color.parseColor(color_2));
                        }*/

                        JSONObject jb_Score12 = job_hole_Score.getJSONObject("hole_num_12");
                        String score3_down = jb_Score12.getString("score");
                        player4_down_g3.setText(score3_down);
                        String color_3 = jb_Score12.getString("color");

                        String is_lowest12 = jb_Score12.getString("is_lowest");
                        if (is_lowest12.equalsIgnoreCase("1")) {
                            player4_down_lay3.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g3.setTextColor(Color.WHITE);
                        } else {
                            player4_down_lay3.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g3.setTextColor(Color.BLACK);
                        }

                        /*if (color_3.equalsIgnoreCase("#ffffff")) {
                            player4_down_lay3.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g3.setTextColor(Color.BLACK);
                        } else {
                            player4_down_lay3.setBackgroundColor(Color.parseColor(color_3));
                        }*/

                        JSONObject jb_Score13 = job_hole_Score.getJSONObject("hole_num_13");
                        String score4_down = jb_Score13.getString("score");
                        player4_down_g4.setText(score4_down);
                        String color_4 = jb_Score13.getString("color");

                        String is_lowest13 = jb_Score13.getString("is_lowest");
                        if (is_lowest13.equalsIgnoreCase("1")) {
                            player4_down_lay4.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g4.setTextColor(Color.WHITE);
                        } else {
                            player4_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g4.setTextColor(Color.BLACK);
                        }

                       /* if (color_4.equalsIgnoreCase("#ffffff")) {
                            player4_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g4.setTextColor(Color.BLACK);
                        } else {
                            player4_down_lay4.setBackgroundColor(Color.parseColor(color_4));
                        }*/

                        JSONObject jb_Score14 = job_hole_Score.getJSONObject("hole_num_14");

                        String score5_down = jb_Score14.getString("score");
                        player4_down_g5.setText(score5_down);
                        String color_5 = jb_Score14.getString("color");

                        String is_lowest14 = jb_Score14.getString("is_lowest");
                        if (is_lowest14.equalsIgnoreCase("1")) {
                            player4_down_lay5.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g5.setTextColor(Color.WHITE);
                        } else {
                            player4_down_lay5.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g5.setTextColor(Color.BLACK);
                        }

                       /* if (color_5.equalsIgnoreCase("#ffffff")) {
                            player4_down_lay5.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g5.setTextColor(Color.BLACK);
                        } else {
                            player4_down_lay5.setBackgroundColor(Color.parseColor(color_5));
                        }*/

                        JSONObject jb_Score15 = job_hole_Score.getJSONObject("hole_num_15");
                        String score6_down = jb_Score15.getString("score");
                        player4_down_g6.setText(score6_down);
                        String color_6 = jb_Score15.getString("color");

                        String is_lowest15 = jb_Score15.getString("is_lowest");
                        if (is_lowest15.equalsIgnoreCase("1")) {
                            player4_down_lay6.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g6.setTextColor(Color.WHITE);
                        } else {
                            player4_down_lay6.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g6.setTextColor(Color.BLACK);
                        }

                        /*if (color_6.equalsIgnoreCase("#ffffff")) {
                            player4_down_lay6.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g6.setTextColor(Color.BLACK);
                        } else {
                            player4_down_lay6.setBackgroundColor(Color.parseColor(color_6));
                        }*/

                        JSONObject jb_Score16 = job_hole_Score.getJSONObject("hole_num_16");
                        String score7_down = jb_Score16.getString("score");
                        player4_down_g7.setText(score7_down);
                        String color_7 = jb_Score16.getString("color");

                        String is_lowest16 = jb_Score16.getString("is_lowest");
                        if (is_lowest16.equalsIgnoreCase("1")) {
                            player4_down_lay7.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g7.setTextColor(Color.WHITE);
                        } else {
                            player4_down_lay7.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g7.setTextColor(Color.BLACK);
                        }

                        /*if (color_7.equalsIgnoreCase("#ffffff")) {
                            player4_down_lay7.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g7.setTextColor(Color.BLACK);
                        } else {
                            player4_down_lay7.setBackgroundColor(Color.parseColor(color_7));
                        }*/

                        JSONObject jb_Score17 = job_hole_Score.getJSONObject("hole_num_17");
                        String score8_down = jb_Score17.getString("score");
                        player4_down_g8.setText(score8_down);
                        String color_8 = jb_Score17.getString("color");

                        String is_lowest17 = jb_Score17.getString("is_lowest");
                        if (is_lowest17.equalsIgnoreCase("1")) {
                            player4_down_lay8.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g8.setTextColor(Color.WHITE);
                        } else {
                            player4_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g8.setTextColor(Color.BLACK);
                        }

                        /*if (color_8.equalsIgnoreCase("#ffffff")) {
                            player4_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g8.setTextColor(Color.BLACK);
                        } else {
                            player4_down_lay8.setBackgroundColor(Color.parseColor(color_8));
                        }*/

                        JSONObject jb_Score18 = job_hole_Score.getJSONObject("hole_num_18");
                        String score9_down = jb_Score18.getString("score");
                        player4_down_g9.setText(score9_down);
                        String color_9 = jb_Score18.getString("color");

                        String is_lowest18 = jb_Score18.getString("is_lowest");
                        if (is_lowest18.equalsIgnoreCase("1")) {
                            player4_down_lay9.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g9.setTextColor(Color.WHITE);
                        } else {
                            player4_down_lay9.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g9.setTextColor(Color.BLACK);
                        }

                        /*if (color_9.equalsIgnoreCase("#ffffff")) {
                            player4_down_lay9.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g9.setTextColor(Color.BLACK);
                        } else {
                            player4_down_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }*/

                        if (score1_down.isEmpty() || score1_down.equalsIgnoreCase("-")) {
                            p4_s10 = 0;
                        } else {
                            p4_s10 = Integer.parseInt(score1_down);
                        }
                        if (score2_down.isEmpty() || score2_down.equalsIgnoreCase("-")) {
                            p4_s11 = 0;
                        } else {
                            p4_s11 = Integer.parseInt(score2_down);
                        }
                        if (score3_down.isEmpty() || score3_down.equalsIgnoreCase("-")) {
                            p4_s12 = 0;
                        } else {
                            p4_s12 = Integer.parseInt(score3_down);
                        }
                        if (score4_down.isEmpty() || score4_down.equalsIgnoreCase("-")) {
                            p4_s13 = 0;
                        } else {
                            p4_s13 = Integer.parseInt(score4_down);
                        }
                        if (score5_down.isEmpty() || score5_down.equalsIgnoreCase("-")) {
                            p4_s14 = 0;
                        } else {
                            p4_s14 = Integer.parseInt(score5_down);
                        }
                        if (score6_down.isEmpty() || score6_down.equalsIgnoreCase("-")) {
                            p4_s15 = 0;
                        } else {
                            p4_s15 = Integer.parseInt(score6_down);
                        }
                        if (score7_down.isEmpty() || score7_down.equalsIgnoreCase("-")) {
                            p4_s16 = 0;
                        } else {
                            p4_s16 = Integer.parseInt(score7_down);
                        }
                        if (score8_down.isEmpty() || score8_down.equalsIgnoreCase("-")) {
                            p4_s17 = 0;
                        } else {
                            p4_s17 = Integer.parseInt(score8_down);
                        }
                        if (score9_down.isEmpty() || score9_down.equalsIgnoreCase("-")) {
                            p4_s18 = 0;
                        } else {
                            p4_s18 = Integer.parseInt(score9_down);
                        }

                        String p4_down_total = String.valueOf(p4_s10 + p4_s12 + p4_s13 + p4_s14 + p4_s15 + p4_s16 + p4_s17 + p4_s18 + p4_s11);
                        player4_down_total.setText(p4_down_total);
                    }
                }

                if (event_id.equalsIgnoreCase("12")) {

                } else {

                    JSONArray standings_back = back_9_array.getJSONObject(f).getJSONArray("standings");

                    for (int s = 0; s < standings_back.length(); s++) {

                        if (s == 0) {
                            String score_value = standings_back.getJSONObject(s).getString("score_value");
                            String hole_number = standings_back.getJSONObject(s).getString("hole_number");
                            String winner = standings_back.getJSONObject(s).getString("winner");
                            String color = standings_back.getJSONObject(s).getString("color");
                            standings10.setText(score_value);
                            if (color.length() > 5) {
                                standings10.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 1) {
                            String score_value = standings_back.getJSONObject(s).getString("score_value");
                            String hole_number = standings_back.getJSONObject(s).getString("hole_number");
                            String winner = standings_back.getJSONObject(s).getString("winner");
                            String color = standings_back.getJSONObject(s).getString("color");
                            standings11.setText(score_value);
                            if (color.length() > 5) {

                                standings11.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 2) {
                            String score_value = standings_back.getJSONObject(s).getString("score_value");
                            String hole_number = standings_back.getJSONObject(s).getString("hole_number");
                            String winner = standings_back.getJSONObject(s).getString("winner");
                            String color = standings_back.getJSONObject(s).getString("color");
                            standings12.setText(score_value);
                            if (color.length() > 5) {
                                standings12.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 3) {
                            String score_value = standings_back.getJSONObject(s).getString("score_value");
                            String hole_number = standings_back.getJSONObject(s).getString("hole_number");
                            String winner = standings_back.getJSONObject(s).getString("winner");
                            String color = standings_back.getJSONObject(s).getString("color");

                            standings13.setText(score_value);

                            if (color.length() > 5) {

                                standings13.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 4) {
                            String score_value = standings_back.getJSONObject(s).getString("score_value");
                            String hole_number = standings_back.getJSONObject(s).getString("hole_number");
                            String winner = standings_back.getJSONObject(s).getString("winner");
                            String color = standings_back.getJSONObject(s).getString("color");
                            standings14.setText(score_value);
                            if (color.length() > 5) {
                                standings14.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 5) {
                            String score_value = standings_back.getJSONObject(s).getString("score_value");
                            String hole_number = standings_back.getJSONObject(s).getString("hole_number");
                            String winner = standings_back.getJSONObject(s).getString("winner");
                            String color = standings_back.getJSONObject(s).getString("color");
                            standings15.setText(score_value);
                            if (color.length() > 5) {
                                // standings1.setTextColor(P);
                                standings15.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 6) {
                            String score_value = standings_back.getJSONObject(s).getString("score_value");
                            String hole_number = standings_back.getJSONObject(s).getString("hole_number");
                            String winner = standings_back.getJSONObject(s).getString("winner");
                            String color = standings_back.getJSONObject(s).getString("color");
                            standings16.setText(score_value);
                            if (color.length() > 5) {
                                // standings1.setTextColor(P);
                                standings16.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 7) {
                            String score_value = standings_back.getJSONObject(s).getString("score_value");
                            String hole_number = standings_back.getJSONObject(s).getString("hole_number");
                            String winner = standings_back.getJSONObject(s).getString("winner");
                            String color = standings_back.getJSONObject(s).getString("color");
                            standings17.setText(score_value);
                            if (color.length() > 5) {
                                // standings1.setTextColor(P);
                                standings17.setTextColor(Color.parseColor(color));
                            }
                        }
                        if (s == 8) {
                            String score_value = standings_back.getJSONObject(s).getString("score_value");
                            String hole_number = standings_back.getJSONObject(s).getString("hole_number");
                            String winner = standings_back.getJSONObject(s).getString("winner");
                            String color = standings_back.getJSONObject(s).getString("color");
                            standings18.setText(score_value);
                            if (color.length() > 5) {
                                // standings1.setTextColor(P);
                                standings18.setTextColor(Color.parseColor(color));
                            }
                        }
                    }
                }

                JSONObject par_value = back_9_array.getJSONObject(f).getJSONObject("par_value");
                String parvalue1 = par_value.getString("par_value_10");
                par_down_one.setText(parvalue1);
                String parvalue2 = par_value.getString("par_value_11");
                par_down_two.setText(parvalue2);
                String parvalue3 = par_value.getString("par_value_12");
                par_down_three.setText(parvalue3);
                String parvalue4 = par_value.getString("par_value_13");
                par_down_four.setText(parvalue4);
                String parvalue5 = par_value.getString("par_value_14");
                par_down_five.setText(parvalue5);
                String parvalue6 = par_value.getString("par_value_15");
                par_down_six.setText(parvalue6);
                String parvalue7 = par_value.getString("par_value_16");
                par_down_seven.setText(parvalue7);
                String parvalue8 = par_value.getString("par_value_17");
                par_down_eight.setText(parvalue8);
                String parvalue9 = par_value.getString("par_value_18");
                par_down_nine.setText(parvalue9);

                if (parvalue1.isEmpty() || parvalue1.equalsIgnoreCase("-")) {
                    par_s10 = 0;
                } else {
                    par_s10 = Integer.parseInt(parvalue1);
                }
                if (parvalue2.isEmpty() || parvalue2.equalsIgnoreCase("-")) {
                    par_s11 = 0;
                } else {
                    par_s11 = Integer.parseInt(parvalue2);
                }
                if (parvalue3.isEmpty() || parvalue3.equalsIgnoreCase("-")) {
                    par_s12 = 0;
                } else {
                    par_s12 = Integer.parseInt(parvalue3);
                }
                if (parvalue4.isEmpty() || parvalue4.equalsIgnoreCase("-")) {
                    par_s13 = 0;
                } else {
                    par_s13 = Integer.parseInt(parvalue4);
                }
                if (parvalue5.isEmpty() || parvalue5.equalsIgnoreCase("-")) {
                    par_s14 = 0;
                } else {
                    par_s14 = Integer.parseInt(parvalue5);
                }
                if (parvalue6.isEmpty() || parvalue6.equalsIgnoreCase("-")) {
                    par_s15 = 0;
                } else {
                    par_s15 = Integer.parseInt(parvalue6);
                }
                if (parvalue7.isEmpty() || parvalue7.equalsIgnoreCase("-")) {
                    par_s16 = 0;
                } else {
                    par_s16 = Integer.parseInt(parvalue7);
                }
                if (parvalue8.isEmpty() || parvalue8.equalsIgnoreCase("-")) {
                    par_s17 = 0;
                } else {
                    par_s17 = Integer.parseInt(parvalue8);
                }
                if (parvalue9.isEmpty() || parvalue9.equalsIgnoreCase("-")) {
                    par_s18 = 0;
                } else {
                    par_s18 = Integer.parseInt(parvalue9);
                }

                String pardowntotal = String.valueOf(par_s10 + par_s12 + par_s13 + par_s14 + par_s15 + par_s16 + par_s17 + par_s18 + par_s11);
                par_down_total.setText(pardowntotal);

                JSONObject hole_index = back_9_array.getJSONObject(f).getJSONObject("hole_index");
                String hole_index_1 = hole_index.getString("hole_index_10");
                ind_down_one.setText(hole_index_1);
                String hole_index_2 = hole_index.getString("hole_index_11");
                ind_down_two.setText(hole_index_2);
                String hole_index_3 = hole_index.getString("hole_index_12");
                ind_down_three.setText(hole_index_3);
                String hole_index_4 = hole_index.getString("hole_index_13");
                ind_down_four.setText(hole_index_4);
                String hole_index_5 = hole_index.getString("hole_index_14");
                ind_down_five.setText(hole_index_5);
                String hole_index_6 = hole_index.getString("hole_index_15");
                ind_down_six.setText(hole_index_6);
                String hole_index_7 = hole_index.getString("hole_index_16");
                ind_down_seven.setText(hole_index_7);
                String hole_index_8 = hole_index.getString("hole_index_17");
                ind_down_eight.setText(hole_index_8);
                String hole_index_9 = hole_index.getString("hole_index_18");
                ind_down_nine.setText(hole_index_9);

                if (hole_index_1.isEmpty() || hole_index_1.equalsIgnoreCase("-")) {
                    index_s10 = 0;
                } else {
                    index_s10 = Integer.parseInt(hole_index_1);
                }
                if (hole_index_2.isEmpty() || hole_index_2.equalsIgnoreCase("-")) {
                    index_s11 = 0;
                } else {
                    index_s11 = Integer.parseInt(hole_index_2);
                }
                if (hole_index_3.isEmpty() || hole_index_3.equalsIgnoreCase("-")) {
                    index_s12 = 0;
                } else {
                    index_s12 = Integer.parseInt(hole_index_3);
                }
                if (hole_index_4.isEmpty() || hole_index_4.equalsIgnoreCase("-")) {
                    index_s13 = 0;
                } else {
                    index_s13 = Integer.parseInt(hole_index_4);
                }
                if (hole_index_5.isEmpty() || hole_index_5.equalsIgnoreCase("-")) {
                    index_s14 = 0;
                } else {
                    index_s14 = Integer.parseInt(hole_index_5);
                }
                if (hole_index_6.isEmpty() || hole_index_6.equalsIgnoreCase("-")) {
                    index_s15 = 0;
                } else {
                    index_s15 = Integer.parseInt(hole_index_6);
                }
                if (hole_index_7.isEmpty() || hole_index_7.equalsIgnoreCase("-")) {
                    index_s16 = 0;
                } else {
                    index_s16 = Integer.parseInt(hole_index_7);
                }
                if (hole_index_8.isEmpty() || hole_index_8.equalsIgnoreCase("-")) {
                    index_s17 = 0;
                } else {
                    index_s17 = Integer.parseInt(hole_index_8);
                }
                if (hole_index_9.isEmpty() || hole_index_9.equalsIgnoreCase("-")) {
                    index_s18 = 0;
                } else {
                    index_s18 = Integer.parseInt(hole_index_9);
                }

                String indexdowntotal = String.valueOf(index_s11 + index_s12 + index_s13 + index_s14 + index_s15 + index_s16 + index_s17 + index_s18 + index_s10);
                index_downTotal.setText(indexdowntotal);


            }

            if (event_id.equalsIgnoreCase("12")) {

            } else {

                if (is_team.equalsIgnoreCase("1")) {

                    teamTabA.setText(teamAname);
                    teamTabA.setTextColor(Color.parseColor(player1_color));
                    teamTabB.setText(teamBname);
                    teamTabB.setTextColor(Color.parseColor(player3_color));
                    teamAPlayer1.setText(player1_Name+" "+player1_handicap);
                    //  teamAPlayer1.setTextColor(Color.parseColor(player1_color));
                    teamAPlayer2.setText(player2_Name+" "+player2_handicap);
                    //   teamAPlayer2.setTextColor(Color.parseColor(player2_color));
                    teamBPlayer1.setText(player3_Name+" "+player3_handicap);
                    //  teamBPlayer1.setTextColor(Color.parseColor(player3_color));
                    teamBPlayer2.setText(player4_Name+" "+player4_handicap);
                    //  teamBPlayer2.setTextColor(Color.parseColor(player4_color));
                } else {
                    teamTabA.setText(player1_Name+" "+player1_handicap);
                    teamTabA.setTextColor(Color.parseColor(player1_color));
                    teamTabB.setText(player3_Name+" "+player3_handicap);
                    teamTabB.setTextColor(Color.parseColor(player3_color));

                    teamAPlayer1.setVisibility(View.GONE);

                    teamAPlayer2.setVisibility(View.GONE);

                    teamBPlayer1.setVisibility(View.GONE);

                    teamBPlayer2.setVisibility(View.GONE);


                }
            }

            String strIndex_up = index_upTotal.getText().toString();
            String strIndex_down = index_downTotal.getText().toString();
            int indexTOT = Integer.parseInt(strIndex_up) + Integer.parseInt(strIndex_down);
            indexTotal.setText("" + indexTOT);

            String strP1_up = player1_up_total.getText().toString();
            String strP1_down = player1_down_total.getText().toString();
            int p1 = Integer.parseInt(strP1_up) + Integer.parseInt(strP1_down);
            player1_total.setText("" + p1);

            String strP2_up = player2_up_total.getText().toString();
            String strP2_down = player2_down_total.getText().toString();
            int p2 = Integer.parseInt(strP2_up) + Integer.parseInt(strP2_down);
            player2_total.setText("" + p2);

            String strP3_up = player3_up_total.getText().toString();
            String strP3_down = player3_down_total.getText().toString();
            int p3 = Integer.parseInt(strP3_up) + Integer.parseInt(strP3_down);
            player3_total.setText("" + p3);

            String strP4_up = player4_up_total.getText().toString();
            String strP4_down = player4_down_total.getText().toString();
            int p4 = Integer.parseInt(strP4_up) + Integer.parseInt(strP4_down);
            player4_total.setText("" + p4);

            String parUp = par_back_total.getText().toString();
            String parDown = par_down_total.getText().toString();
            int parTT = Integer.parseInt(parUp) + Integer.parseInt(parDown);
            parTotal.setText("" + parTT);

            if (player1_color != null && player1_color.length() > 5) {
                player1bg_420.setBackgroundColor(Color.parseColor(player1_color));
            }


        } catch (JSONException je)

        {
            je.printStackTrace();
        }


       /* winner tab layout at top */

        Log.v("winner", "cur" + current_winner + "win1" + winner1 + "win2" + winner2);

        if (current_winner != null && current_winner.length() != 0) {
            if (current_winner.equalsIgnoreCase(winner1)) {
                winnerBackGround.setBackgroundResource(R.mipmap.indicate_blue);
            } else if (current_winner.equalsIgnoreCase(winner2)) {
                winnerBackGround.setBackgroundResource(R.mipmap.indicate_red);

            } else {
                winnerBackGround.setBackgroundResource(R.color.black_color);
            }
        }

        addScoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

        String stackClear = getIntent().getStringExtra("from");
        if (stackClear != null && stackClear.equalsIgnoreCase("delegate")) {

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("eventID", eventID);
            intent.putExtra("fromEventPreview", "1");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            finish();
        }
    }


    public Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);

        store(bitmap, "putt.png");

        return bitmap;
    }

    public void store(Bitmap bm, String fileName) {
        final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
        File dir = new File(dirPath);
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(dirPath, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
            shareImage(file);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void shareImage(File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "No App Available", Toast.LENGTH_SHORT).show();
        }
    }


    public void expand420_Popup() {

        ImageView finishdialogfrag;

        final Dialog dialog420 = new Dialog(NewGameScoreCard.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog420.setCanceledOnTouchOutside(true);
        Window window = dialog420.getWindow();
        dialog420.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog420.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog420.setContentView(R.layout.view_score_21);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        dd21_RecyclerView = (RecyclerView) dialog420.findViewById(R.id.list_21_view);
        dd21_RecyclerView.setHasFixedSize(true);
        dd21_LayoutManager = new LinearLayoutManager(this);
        dd21_RecyclerView.setLayoutManager(dd21_LayoutManager);

        finishdialogfrag = (ImageView) dialog420.findViewById(R.id.finishdialogfrag);


        getExpand420(eventID, dialog420);

        finishdialogfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog420.cancel();
            }
        });
    }

    public void getExpand420(String Event_Id, final Dialog dialog420) {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();

            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.EXPAND_SCORE_VIEW, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                expand420_Response(response, dialog420);

                Log.e("420 Expand", "auto press expand" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "420 expand URL = " + PUTTAPI.EXPAND_SCORE_VIEW);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void expand420_Response(JSONObject response, final Dialog dialog420) {

        ArrayList<Bean420> list420 = new ArrayList<Bean420>();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Bean420 listBean = new Bean420();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        listBean.setHoleNo(jsonObject1.getString("hole_number"));
                        JSONArray scoreArray = jsonObject1.getJSONArray("first_array");
                        for (int s = 0; s < scoreArray.length(); s++) {
                            if (s == 0) {
                                String score = scoreArray.getJSONObject(s).getString("score");
                                String color = scoreArray.getJSONObject(s).getString("color");
                                listBean.setHoleSocre1(score);
                                listBean.setHoleColor1(color);
                            } else if (s == 1) {
                                String score = scoreArray.getJSONObject(s).getString("score");
                                String color = scoreArray.getJSONObject(s).getString("color");
                                listBean.setHoleScore2(score);
                                listBean.setHoleColor2(color);
                            } else if (s == 2) {
                                String score = scoreArray.getJSONObject(s).getString("score");
                                String color = scoreArray.getJSONObject(s).getString("color");
                                listBean.setHoleScore3(score);
                                listBean.setHoleColor3(color);
                            }
                        }


                        JSONArray scoreArraySecond = jsonObject1.getJSONArray("second_array");
                        for (int s = 0; s < scoreArray.length(); s++) {
                            if (s == 0) {
                                String score = scoreArraySecond.getJSONObject(s).getString("score");
                                String color = scoreArraySecond.getJSONObject(s).getString("color");
                                listBean.setAggScore1(score);
                                listBean.setAggColor1(color);
                            } else if (s == 1) {
                                String score = scoreArraySecond.getJSONObject(s).getString("score");
                                String color = scoreArraySecond.getJSONObject(s).getString("color");
                                listBean.setAggScore2(score);
                                listBean.setAggColor2(color);
                            } else if (s == 2) {
                                String score = scoreArraySecond.getJSONObject(s).getString("score");
                                String color = scoreArraySecond.getJSONObject(s).getString("color");
                                listBean.setAggScore3(score);
                                listBean.setAggColor3(color);
                            }
                        }


                        list420.add(listBean);
                    }
                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (list420.size() > 0) {
            dialog420.show();
            Adapter420 dd420_Adapter = new Adapter420(this, list420);
            dd21_RecyclerView.setAdapter(dd420_Adapter);
        } else {

            dd21_RecyclerView.setAdapter(new NoInternetConnectionAdapter("Something went wrong. "));
        }
    }

    public void expand21Popup() {

        ImageView finishdialogfrag;

        final Dialog dialog21 = new Dialog(NewGameScoreCard.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog21.setCanceledOnTouchOutside(true);
        Window window = dialog21.getWindow();
        dialog21.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog21.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog21.setContentView(R.layout.view_score_21);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        dd21_RecyclerView = (RecyclerView) dialog21.findViewById(R.id.list_21_view);
        dd21_RecyclerView.setHasFixedSize(true);
        dd21_LayoutManager = new LinearLayoutManager(this);
        dd21_RecyclerView.setLayoutManager(dd21_LayoutManager);

        finishdialogfrag = (ImageView) dialog21.findViewById(R.id.finishdialogfrag);


        getExpand21(eventID, dialog21);

        finishdialogfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog21.cancel();
            }
        });
    }

    public void getExpand21(String Event_Id, final Dialog dialog21) {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();

            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.EXPAND_SCORE_VIEW, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                expand21_Response(response, dialog21);

                Log.e("AutoPress Expand", "auto press expand" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "autopress expand URL = " + PUTTAPI.EXPAND_SCORE_VIEW);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void expand21_Response(JSONObject response, final Dialog dialog21) {

        ArrayList<ExpandBean> list21 = new ArrayList<ExpandBean>();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        ExpandBean listBean = new ExpandBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        listBean.setHole_number(jsonObject1.getString("hole_number"));
                        listBean.setHole_score(jsonObject1.getString("hole_score"));
                        listBean.setHole_color(jsonObject1.getString("hole_color"));
                        listBean.setAgg_score(jsonObject1.getString("agg_score"));
                        listBean.setAgg_color(jsonObject1.getString("agg_color"));

                        list21.add(listBean);
                    }
                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (list21.size() > 0) {
            dialog21.show();
            Adapter21 dd21_Adapter = new Adapter21(this, list21);
            dd21_RecyclerView.setAdapter(dd21_Adapter);
        } else {

            dd21_RecyclerView.setAdapter(new NoInternetConnectionAdapter("Something went wrong. "));

        }
    }

    public void getBannerList(String event_id) {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("type", "4");
            jsonObject.putOpt("event_id",event_id);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GET_BANNER_API, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getBannerResponse(response);
                Log.e("Banner", "banner invitelist" + response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        Utility.showLogError(this, "Error in " + "banner invitelist URL = " + PUTTAPI.EVENT_INVITATION_LIST);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getBannerResponse(JSONObject response) {

        ArrayList<InviteUserBean> list = new ArrayList<InviteUserBean>();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    banner_id = jsonArray.getJSONObject(0).getString("id");
                    banner_url = jsonArray.getJSONObject(0).getString("image_href");
                    banner_img = jsonArray.getJSONObject(0).getString("image_path");

                    if (banner_img!=null && banner_img.length()>10){
                        bannerImage.setVisibility(View.VISIBLE);
                        Picasso.with(this).load(banner_img).into(bannerImage);
                    }else {
                        bannerImage.setVisibility(View.GONE);
                    }

                } else {
                    String errorMessage = jsonObject.getString("message");
                  //  Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

}