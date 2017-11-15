package com.putt2gether.putt.addScore;

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
import com.putt2gether.bean.InviteUserBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.HomeActivity;
import com.putt2gether.putt.score_card.ScoreCardTemplate;
import com.putt2gether.utils.Utility;
import com.putt2gether.view.LatoTextView;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 13/12/2016.
 */
public class AutoPressScoreCard  extends AppCompatActivity {



    LinearLayout r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18;

    TextView hole_a1,hole_a2,hole_a3,hole_a4,hole_a5,hole_a6,hole_a7,hole_a8,hole_a9,hole_a10,hole_a11,hole_a12,hole_a13,hole_a14,hole_a15,hole_a16,hole_a17,hole_a18;

    TextView f1_r1,f1_r2,f1_r3,f1_r4,f1_r5,f1_r6,f1_r7,f1_r8,f1_r9,f1_r10;
    TextView f2_r1,f2_r2,f2_r3,f2_r4,f2_r5,f2_r6,f2_r7,f2_r8,f2_r9,f2_r10;
    TextView f3_r1,f3_r2,f3_r3,f3_r4,f3_r5,f3_r6,f3_r7,f3_r8,f3_r9,f3_r10;
    TextView f4_r1,f4_r2,f4_r3,f4_r4,f4_r5,f4_r6,f4_r7,f4_r8,f4_r9,f4_r10;
    TextView f5_r1,f5_r2,f5_r3,f5_r4,f5_r5,f5_r6,f5_r7,f5_r8,f5_r9,f5_r10;
    TextView f6_r1,f6_r2,f6_r3,f6_r4,f6_r5,f6_r6,f6_r7,f6_r8,f6_r9,f6_r10;
    TextView f7_r1,f7_r2,f7_r3,f7_r4,f7_r5,f7_r6,f7_r7,f7_r8,f7_r9,f7_r10;
    TextView f8_r1,f8_r2,f8_r3,f8_r4,f8_r5,f8_r6,f8_r7,f8_r8,f8_r9,f8_r10;
    TextView f9_r1,f9_r2,f9_r3,f9_r4,f9_r5,f9_r6,f9_r7,f9_r8,f9_r9,f9_r10;
    TextView f10_r1,f10_r2,f10_r3,f10_r4,f10_r5,f10_r6,f10_r7,f10_r8,f10_r9,f10_r10;

    TextView b1_r1,b1_r2,b1_r3,b1_r4,b1_r5,b1_r6,b1_r7,b1_r8,b1_r9,b1_r10;
    TextView b2_r1,b2_r2,b2_r3,b2_r4,b2_r5,b2_r6,b2_r7,b2_r8,b2_r9,b2_r10;
    TextView b3_r1,b3_r2,b3_r3,b3_r4,b3_r5,b3_r6,b3_r7,b3_r8,b3_r9,b3_r10;
    TextView b4_r1,b4_r2,b4_r3,b4_r4,b4_r5,b4_r6,b4_r7,b4_r8,b4_r9,b4_r10;
    TextView b5_r1,b5_r2,b5_r3,b5_r4,b5_r5,b5_r6,b5_r7,b5_r8,b5_r9,b5_r10;
    TextView b6_r1,b6_r2,b6_r3,b6_r4,b6_r5,b6_r6,b6_r7,b6_r8,b6_r9,b6_r10;
    TextView b7_r1,b7_r2,b7_r3,b7_r4,b7_r5,b7_r6,b7_r7,b7_r8,b7_r9,b7_r10;
    TextView b8_r1,b8_r2,b8_r3,b8_r4,b8_r5,b8_r6,b8_r7,b8_r8,b8_r9,b8_r10;
    TextView b9_r1,b9_r2,b9_r3,b9_r4,b9_r5,b9_r6,b9_r7,b9_r8,b9_r9,b9_r10;
    TextView b10_r1,b10_r2,b10_r3,b10_r4,b10_r5,b10_r6,b10_r7,b10_r8,b10_r9,b10_r10;

    TextView f1_r11,f1_r12,f1_r13,f1_r14,f1_r15,f1_r16,f1_r17,f1_r18,f1_r19,f1_r20;
    TextView f2_r11,f2_r12,f2_r13,f2_r14,f2_r15,f2_r16,f2_r17,f2_r18,f2_r19,f2_r20;
    TextView f3_r11,f3_r12,f3_r13,f3_r14,f3_r15,f3_r16,f3_r17,f3_r18,f3_r19,f3_r20;
    TextView f4_r11,f4_r12,f4_r13,f4_r14,f4_r15,f4_r16,f4_r17,f4_r18,f4_r19,f4_r20;
    TextView f5_r11,f5_r12,f5_r13,f5_r14,f5_r15,f5_r16,f5_r17,f5_r18,f5_r19,f5_r20;
    TextView f6_r11,f6_r12,f6_r13,f6_r14,f6_r15,f6_r16,f6_r17,f6_r18,f6_r19,f6_r20;
    TextView f7_r11,f7_r12,f7_r13,f7_r14,f7_r15,f7_r16,f7_r17,f7_r18,f7_r19,f7_r20;
    TextView f8_r11,f8_r12,f8_r13,f8_r14,f8_r15,f8_r16,f8_r17,f8_r18,f8_r19,f8_r20;
    TextView f9_r11,f9_r12,f9_r13,f9_r14,f9_r15,f9_r16,f9_r17,f9_r18,f9_r19,f9_r20;
    TextView f10_r11,f10_r12,f10_r13,f10_r14,f10_r15,f10_r16,f10_r17,f10_r18,f10_r19,f10_r20;

    TextView b1_r11,b1_r12,b1_r13,b1_r14,b1_r15,b1_r16,b1_r17,b1_r18,b1_r19,b1_r20;
    TextView b2_r11,b2_r12,b2_r13,b2_r14,b2_r15,b2_r16,b2_r17,b2_r18,b2_r19,b2_r20;
    TextView b3_r11,b3_r12,b3_r13,b3_r14,b3_r15,b3_r16,b3_r17,b3_r18,b3_r19,b3_r20;
    TextView b4_r11,b4_r12,b4_r13,b4_r14,b4_r15,b4_r16,b4_r17,b4_r18,b4_r19,b4_r20;
    TextView b5_r11,b5_r12,b5_r13,b5_r14,b5_r15,b5_r16,b5_r17,b5_r18,b5_r19,b5_r20;
    TextView b6_r11,b6_r12,b6_r13,b6_r14,b6_r15,b6_r16,b6_r17,b6_r18,b6_r19,b6_r20;
    TextView b7_r11,b7_r12,b7_r13,b7_r14,b7_r15,b7_r16,b7_r17,b7_r18,b7_r19,b7_r20;
    TextView b8_r11,b8_r12,b8_r13,b8_r14,b8_r15,b8_r16,b8_r17,b8_r18,b8_r19,b8_r20;
    TextView b9_r11,b9_r12,b9_r13,b9_r14,b9_r15,b9_r16,b9_r17,b9_r18,b9_r19,b9_r20;
    TextView b10_r11,b10_r12,b10_r13,b10_r14,b10_r15,b10_r16,b10_r17,b10_r18,b10_r19,b10_r20;
    LinearLayout front1_hole1,front1_hole2,front1_hole3,front1_hole4,front1_hole5,front1_hole6,front1_hole7,front1_hole8,front1_hole9,front1_hole10,front1_hole11,front1_hole12,front1_hole13,front1_hole14,front1_hole15,front1_hole16,front1_hole17,front1_hole18;
    LinearLayout back1_hole1,back1_hole2,back1_hole3,back1_hole4,back1_hole5,back1_hole6,back1_hole7,back1_hole8,back1_hole9,back1_hole10,back1_hole11,back1_hole12,back1_hole13,back1_hole14,back1_hole15,back1_hole16,back1_hole17,back1_hole18;
    TextView and1,and2,and3,and4,and5,and6,and7,and8,and9,and10,and11,and12,and13,and14,and15,and16,and17,and18;


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

    LinearLayout front, back;
    LatoTextView front1, front2, front3, front4, front5, front6, front7, front8, front9, front10;
    LatoTextView back1, back2, back3, back4, back5, back6, back7, back8, back9;

    ArrayList<String> scoreArrayfront = new ArrayList<String>();
    ArrayList<String> colorArrayfront = new ArrayList<String>();

    ArrayList<String> scoreArrayback = new ArrayList<String>();
    ArrayList<String> colorArrayback = new ArrayList<String>();



    LatoTextView player1_name_up,player1_name_down,player1_up_total,player1_down_total,player1_total;
    LatoTextView player2_name_up,player2_name_down,player2_up_total,player2_down_total,player2_total;
    LatoTextView player3_name_up,player3_name_down,player3_up_total,player3_down_total,player3_total;
    LatoTextView player4_name_up,player4_name_down,player4_up_total,player4_down_total,player4_total;



    String player1_id,player1_Name,player1_handicap,player1_color,team_id1;
    String player2_id,player2_Name,player2_handicap,player2_color,team_id2;
    String player3_id,player3_Name,player3_handicap,player3_color;
    String player4_id,player4_Name,player4_handicap,player4_color;
    String teamAname,teamBname;

    LinearLayout hole1, hole2,hole3,hole4,hole5,hole6,hole7,hole8,hole9,hole10,hole11,hole12,hole13,hole14,hole15,hole16,hole17,hole18;
    LatoTextView holeText1,holeText2,holeText3,holeText4,holeText5,holeText6,holeText7,holeText8,holeText9,holeText10,holeText11,holeText12,holeText13,holeText14,holeText15,holeText16,holeText17,holeText18;

    LatoTextView standingName_up, standingName_down,index_upTotal,index_downTotal,indexTotal;
    LatoTextView standings1, standings2, standings3, standings4, standings5, standings6, standings7, standings8, standings9;
    LatoTextView standings10, standings12, standings13, standings14, standings15, standings16, standings17, standings18, standings11;

    TableRow player1_up_row, player2_up_row, player3_up_row,player4_up_row,standings_up_row;
    TableRow player1_down_row, player2_down_row, player3_down_row,player4_down_row,standings_down_row;
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
    RelativeLayout autopressLayout;
    TextView player1_420_score, player2_420_score, player3_420_score;
    TextView player1_420_name, player2_420_name, player3_420_name;
    LinearLayout winnerBackGround;
    String winner1,winner2;
    String current_winner;
    LinearLayout addScoreBTN;
    String eventID;
    LinearLayout shareBTN;
    String handicap_value = "";
    String hadicap_value2="";
    String hadicap_value="";
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

        shareBTN = (LinearLayout)findViewById(R.id.shareScoreCard);

        stats_btn = (LinearLayout)findViewById(R.id.stats_btn);
        String statsType = getIntent().getStringExtra("stats_visible");
        if (statsType!=null && statsType.equalsIgnoreCase("yes")){

            stats_btn.setVisibility(View.VISIBLE);
        }

        parentNewGame = (RelativeLayout) findViewById(R.id.parent_newgame);
        winnerBackGround = (LinearLayout)findViewById(R.id.team_winner_layout);

        player3_up_total = (LatoTextView) findViewById(R.id.player3_up_total);
        player3_down_total = (LatoTextView) findViewById(R.id.player3_down_total);
        player3_total = (LatoTextView) findViewById(R.id.player3_total);

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

        autopressLayout = (RelativeLayout) findViewById(R.id.autopress_scorcard_tab);
        front = (LinearLayout) findViewById(R.id.front);
        back = (LinearLayout) findViewById(R.id.back);

        autopressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autopressPopup();
            }
        });

        front1 = (LatoTextView) findViewById(R.id.front1);
        front2 = (LatoTextView) findViewById(R.id.front2);
        front3 = (LatoTextView) findViewById(R.id.front3);
        front4 = (LatoTextView) findViewById(R.id.front4);
        front5 = (LatoTextView) findViewById(R.id.front5);
        front6 = (LatoTextView) findViewById(R.id.front6);
        front7 = (LatoTextView) findViewById(R.id.front7);
        front8 = (LatoTextView) findViewById(R.id.front8);
        front9 = (LatoTextView) findViewById(R.id.front9);
        front10 = (LatoTextView) findViewById(R.id.front10);

        back1 = (LatoTextView) findViewById(R.id.back1);
        back2 = (LatoTextView) findViewById(R.id.back2);
        back3 = (LatoTextView) findViewById(R.id.back3);
        back4 = (LatoTextView) findViewById(R.id.back4);
        back5 = (LatoTextView) findViewById(R.id.back5);
        back6 = (LatoTextView) findViewById(R.id.back6);
        back7 = (LatoTextView) findViewById(R.id.back7);
        back8 = (LatoTextView) findViewById(R.id.back8);
        back9 = (LatoTextView) findViewById(R.id.back9);


        layout420_score = (LinearLayout) findViewById(R.id.layout_340_scorcard);
        player1_420_name = (TextView) findViewById(R.id.player1_420name);
        player2_420_name = (TextView) findViewById(R.id.player2_420name);
        player3_420_name = (TextView) findViewById(R.id.player3_420name);
        player1_420_score = (TextView) findViewById(R.id.player1_420score);
        player2_420_score = (TextView) findViewById(R.id.player2_420score);
        player3_420_score = (TextView) findViewById(R.id.player3_420score);

        index_downTotal = (LatoTextView)findViewById(R.id.index_down_total);
        index_upTotal = (LatoTextView)findViewById(R.id.index_up_total);
        indexTotal = (LatoTextView)findViewById(R.id.index_total);

        hole1 = (LinearLayout)findViewById(R.id.hole_up_one);
        hole2 = (LinearLayout)findViewById(R.id.hole_up_two);
        hole3 = (LinearLayout)findViewById(R.id.hole_up_three);
        hole4 = (LinearLayout)findViewById(R.id.hole_up_four);
        hole5 = (LinearLayout)findViewById(R.id.hole_up_five);
        hole6 = (LinearLayout)findViewById(R.id.hole_up_six);
        hole7 = (LinearLayout)findViewById(R.id.hole_up_seven);
        hole8 = (LinearLayout)findViewById(R.id.hole_up_eight);
        hole9 = (LinearLayout)findViewById(R.id.hole_up_nine);

        hole10 = (LinearLayout)findViewById(R.id.hole_down_one);
        hole11 = (LinearLayout)findViewById(R.id.hole_down_two);
        hole12 = (LinearLayout)findViewById(R.id.hole_down_three);
        hole13 = (LinearLayout)findViewById(R.id.hole_down_four);
        hole14 = (LinearLayout)findViewById(R.id.hole_down_five);
        hole15 = (LinearLayout)findViewById(R.id.hole_down_six);
        hole16 = (LinearLayout)findViewById(R.id.hole_down_seven);
        hole17 = (LinearLayout)findViewById(R.id.hole_down_eight);
        hole18 = (LinearLayout)findViewById(R.id.hole_down_nine);


        holeText1 = (LatoTextView)findViewById(R.id.hole1_text);
        holeText2 = (LatoTextView)findViewById(R.id.hole2_text);
        holeText3 = (LatoTextView)findViewById(R.id.hole3_text);
        holeText4 = (LatoTextView)findViewById(R.id.hole4_text);
        holeText5 = (LatoTextView)findViewById(R.id.hole5_text);
        holeText6 = (LatoTextView)findViewById(R.id.hole6_text);
        holeText7 = (LatoTextView)findViewById(R.id.hole7_text);
        holeText8 = (LatoTextView)findViewById(R.id.hole8_text);
        holeText9 = (LatoTextView)findViewById(R.id.hole9_text);
        holeText10 = (LatoTextView)findViewById(R.id.hole10_text);
        holeText11 = (LatoTextView)findViewById(R.id.hole11_text);
        holeText12 = (LatoTextView)findViewById(R.id.hole12_text);
        holeText13 = (LatoTextView)findViewById(R.id.hole13_text);
        holeText14 = (LatoTextView)findViewById(R.id.hole14_text);
        holeText15 = (LatoTextView)findViewById(R.id.hole15_text);
        holeText16 = (LatoTextView)findViewById(R.id.hole16_text);
        holeText17 = (LatoTextView)findViewById(R.id.hole17_text);
        holeText18 = (LatoTextView)findViewById(R.id.hole18_text);






        addScoreBTN = (LinearLayout)findViewById(R.id.bottom_scorecard_new);

        shareBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                getScreenShot(rootView);

            }
        });


        from = getIntent().getStringExtra("from");
        if (from!=null){
            addScoreBTN.setVisibility(View.GONE);
        }else {
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

        stats_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScoreCardTemplate.class);
                intent.putExtra("event_id",eventID);
                intent.putExtra("player_id",admin_ID);
                startActivity(intent);
            }
        });

        player1LayoutBack = (LinearLayout) findViewById(R.id.player1_lay_up);
        player2LayoutBack = (LinearLayout) findViewById(R.id.player2_lay_up);

        eventName = (LatoTextView) findViewById(R.id.event_name_new_scoretable);
        golfCourseName = (LatoTextView) findViewById(R.id.golf_course_address_new_scoretable);
        backBTN = (ImageView) findViewById(R.id.back_new_scorecard);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stackClear = getIntent().getStringExtra("from");
                if (stackClear !=null && stackClear.equalsIgnoreCase("delegate")) {

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("eventID", eventID);
                    intent.putExtra("fromEventPreview", "1");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }else {
                    finish();
                }
            }
        });

        player1_name_up = (LatoTextView)findViewById(R.id.player1_name_up);
        player2_name_up = (LatoTextView)findViewById(R.id.player2_name_up);
        player3_name_up = (LatoTextView)findViewById(R.id.player3_name_up);
        player4_name_up = (LatoTextView)findViewById(R.id.player4_name_up);

        player1_name_down = (LatoTextView)findViewById(R.id.player1_name_down);
        player2_name_down = (LatoTextView)findViewById(R.id.player2_name_down);
        player3_name_down = (LatoTextView)findViewById(R.id.player3_name_down);
        player4_name_down = (LatoTextView)findViewById(R.id.player4_name_down);

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

        scoreCardTab.setVisibility(View.GONE);
        layout420_score.setVisibility(View.GONE);

        ConnectionDetector con = new ConnectionDetector(this);
        if (con.isConnectingToInternet()) {
            getscorecardtable(eventID);
            getBannerList(eventID);
        }else {
            Toast.makeText(getApplicationContext(),"No internet connections.",Toast.LENGTH_SHORT).show();
        }

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


            JSONArray jss = data.getJSONArray("current_standing");
            for (int i=0;i<jss.length();i++){
            JSONObject currObject = jss.getJSONObject(i);
            String curentHole = currObject.getString("hole_number");
            String current_winner = currObject.getString("winner");

            JSONArray jsonArrayCurrent = currObject.getJSONArray("score_value");
            scoreArrayfront = new ArrayList<String>();
            colorArrayfront = new ArrayList<String>();

            for (int c = 0; c < jsonArrayCurrent.length(); c++) {
                String color = jsonArrayCurrent.getJSONObject(c).getString("color");
                String score = jsonArrayCurrent.getJSONObject(c).getString("score");
                scoreArrayfront.add(score);
                colorArrayfront.add(color);
            }

            JSONArray jsonArrayCurrentB = currObject.getJSONArray("back_to_9_score");
            scoreArrayback = new ArrayList<String>();
            colorArrayback = new ArrayList<String>();

            for (int c = 0; c < jsonArrayCurrentB.length(); c++) {

                String color = jsonArrayCurrentB.getJSONObject(c).getString("color");
                String score = jsonArrayCurrentB.getJSONObject(c).getString("score");
                scoreArrayback.add(score);
                colorArrayback.add(color);

            }

            for (int s =0 ;s<scoreArrayfront.size();s++){
                autopressLayout.setVisibility(View.VISIBLE);
                if (s==0){
                    front1.setText(scoreArrayfront.get(s));
                    front1.setTextColor(Color.parseColor(colorArrayfront.get(s)));
                    front1.setVisibility(View.VISIBLE);
                    front2.setVisibility(View.GONE);
                    front3.setVisibility(View.GONE);
                    front4.setVisibility(View.GONE);
                    front5.setVisibility(View.GONE);
                    front6.setVisibility(View.GONE);
                    front7.setVisibility(View.GONE);
                    front8.setVisibility(View.GONE);
                    front9.setVisibility(View.GONE);
                    front10.setVisibility(View.GONE);
                }else if (s==1){
                    front2.setText(" "+scoreArrayfront.get(s));
                    front2.setTextColor(Color.parseColor(colorArrayfront.get(s)));
                    front1.setVisibility(View.VISIBLE);
                    front2.setVisibility(View.VISIBLE);
                    front3.setVisibility(View.GONE);
                    front4.setVisibility(View.GONE);
                    front5.setVisibility(View.GONE);
                    front6.setVisibility(View.GONE);
                    front7.setVisibility(View.GONE);
                    front8.setVisibility(View.GONE);
                    front9.setVisibility(View.GONE);
                    front10.setVisibility(View.GONE);

                }else if (s==2){
                    front3.setText(" "+scoreArrayfront.get(s));
                    front3.setTextColor(Color.parseColor(colorArrayfront.get(s)));
                    front1.setVisibility(View.VISIBLE);
                    front2.setVisibility(View.VISIBLE);
                    front3.setVisibility(View.VISIBLE);
                    front4.setVisibility(View.GONE);
                    front5.setVisibility(View.GONE);
                    front6.setVisibility(View.GONE);
                    front7.setVisibility(View.GONE);
                    front8.setVisibility(View.GONE);
                    front9.setVisibility(View.GONE);
                    front10.setVisibility(View.GONE);
                }
                else if (s==3){
                    front4.setText(" "+scoreArrayfront.get(s));
                    front4.setTextColor(Color.parseColor(colorArrayfront.get(s)));
                    front1.setVisibility(View.VISIBLE);
                    front2.setVisibility(View.VISIBLE);
                    front3.setVisibility(View.VISIBLE);
                    front4.setVisibility(View.VISIBLE);
                    front5.setVisibility(View.GONE);
                    front6.setVisibility(View.GONE);
                    front7.setVisibility(View.GONE);
                    front8.setVisibility(View.GONE);
                    front9.setVisibility(View.GONE);
                    front10.setVisibility(View.GONE);
                }else if (s==4){
                    front5.setText(" "+scoreArrayfront.get(s));
                    front5.setTextColor(Color.parseColor(colorArrayfront.get(s)));
                    front1.setVisibility(View.VISIBLE);
                    front2.setVisibility(View.VISIBLE);
                    front3.setVisibility(View.VISIBLE);
                    front4.setVisibility(View.VISIBLE);
                    front5.setVisibility(View.VISIBLE);
                    front6.setVisibility(View.GONE);
                    front7.setVisibility(View.GONE);
                    front8.setVisibility(View.GONE);
                    front9.setVisibility(View.GONE);
                    front10.setVisibility(View.GONE);
                }else if (s==5){
                    front6.setText(" "+scoreArrayfront.get(s));
                    front6.setTextColor(Color.parseColor(colorArrayfront.get(s)));
                    front1.setVisibility(View.VISIBLE);
                    front2.setVisibility(View.VISIBLE);
                    front3.setVisibility(View.VISIBLE);
                    front4.setVisibility(View.VISIBLE);
                    front5.setVisibility(View.VISIBLE);
                    front6.setVisibility(View.VISIBLE);
                    front7.setVisibility(View.GONE);
                    front8.setVisibility(View.GONE);
                    front9.setVisibility(View.GONE);
                    front10.setVisibility(View.GONE);
                }else if (s==6){
                    front7.setText(" "+scoreArrayfront.get(s));
                    front7.setTextColor(Color.parseColor(colorArrayfront.get(s)));
                    front1.setVisibility(View.VISIBLE);
                    front2.setVisibility(View.VISIBLE);
                    front3.setVisibility(View.VISIBLE);
                    front4.setVisibility(View.VISIBLE);
                    front5.setVisibility(View.VISIBLE);
                    front6.setVisibility(View.VISIBLE);
                    front7.setVisibility(View.VISIBLE);
                    front8.setVisibility(View.GONE);
                    front9.setVisibility(View.GONE);
                    front10.setVisibility(View.GONE);
                }else if (s==7){
                    front8.setText(" "+scoreArrayfront.get(s));
                    front8.setTextColor(Color.parseColor(colorArrayfront.get(s)));
                    front1.setVisibility(View.VISIBLE);
                    front2.setVisibility(View.VISIBLE);
                    front3.setVisibility(View.VISIBLE);
                    front4.setVisibility(View.VISIBLE);
                    front5.setVisibility(View.VISIBLE);
                    front6.setVisibility(View.VISIBLE);
                    front7.setVisibility(View.VISIBLE);
                    front8.setVisibility(View.VISIBLE);
                    front9.setVisibility(View.GONE);
                    front10.setVisibility(View.GONE);
                }else if (s==8){
                    front9.setText(" "+scoreArrayfront.get(s));
                    front9.setTextColor(Color.parseColor(colorArrayfront.get(s)));
                    front1.setVisibility(View.VISIBLE);
                    front2.setVisibility(View.VISIBLE);
                    front3.setVisibility(View.VISIBLE);
                    front4.setVisibility(View.VISIBLE);
                    front5.setVisibility(View.VISIBLE);
                    front6.setVisibility(View.VISIBLE);
                    front7.setVisibility(View.VISIBLE);
                    front8.setVisibility(View.VISIBLE);
                    front9.setVisibility(View.VISIBLE);
                    front10.setVisibility(View.GONE);
                }else if (s==9){
                    front10.setText(" "+scoreArrayfront.get(s));
                    front10.setTextColor(Color.parseColor(colorArrayfront.get(s)));
                    front1.setVisibility(View.VISIBLE);
                    front2.setVisibility(View.VISIBLE);
                    front3.setVisibility(View.VISIBLE);
                    front4.setVisibility(View.VISIBLE);
                    front5.setVisibility(View.VISIBLE);
                    front6.setVisibility(View.VISIBLE);
                    front7.setVisibility(View.VISIBLE);
                    front8.setVisibility(View.VISIBLE);
                    front9.setVisibility(View.VISIBLE);
                    front10.setVisibility(View.VISIBLE);
                }
            }

            if (scoreArrayback.size() < 1){
                back.setVisibility(View.GONE);
            }else {
                back.setVisibility(View.VISIBLE);
                for (int s = 0; s < scoreArrayback.size(); s++) {
                    autopressLayout.setVisibility(View.VISIBLE);
                    if (s == 0) {
                        back1.setText(scoreArrayback.get(s));
                        back1.setTextColor(Color.parseColor(colorArrayback.get(s)));
                    } else if (s == 1) {
                        back2.setText(" "+scoreArrayback.get(s));
                        back2.setTextColor(Color.parseColor(colorArrayback.get(s)));

                        back1.setVisibility(View.VISIBLE);
                        back2.setVisibility(View.VISIBLE);
                        back3.setVisibility(View.GONE);
                        back4.setVisibility(View.GONE);
                        back5.setVisibility(View.GONE);
                        back6.setVisibility(View.GONE);
                        back7.setVisibility(View.GONE);
                        back8.setVisibility(View.GONE);
                        back9.setVisibility(View.GONE);

                    } else if (s == 2) {
                        back3.setText(" "+scoreArrayback.get(s));
                        back3.setTextColor(Color.parseColor(colorArrayback.get(s)));
                        back1.setVisibility(View.VISIBLE);
                        back2.setVisibility(View.VISIBLE);
                        back3.setVisibility(View.VISIBLE);
                        back4.setVisibility(View.GONE);
                        back5.setVisibility(View.GONE);
                        back6.setVisibility(View.GONE);
                        back7.setVisibility(View.GONE);
                        back8.setVisibility(View.GONE);
                        back9.setVisibility(View.GONE);

                    } else if (s == 3) {
                        back4.setText(" "+scoreArrayback.get(s));
                        back4.setTextColor(Color.parseColor(colorArrayback.get(s)));
                        back1.setVisibility(View.VISIBLE);
                        back2.setVisibility(View.VISIBLE);
                        back3.setVisibility(View.VISIBLE);
                        back4.setVisibility(View.VISIBLE);
                        back5.setVisibility(View.GONE);
                        back6.setVisibility(View.GONE);
                        back7.setVisibility(View.GONE);
                        back8.setVisibility(View.GONE);
                        back9.setVisibility(View.GONE);

                    } else if (s == 4) {
                        back5.setText(" "+scoreArrayback.get(s));
                        back5.setTextColor(Color.parseColor(colorArrayback.get(s)));
                        back1.setVisibility(View.VISIBLE);
                        back2.setVisibility(View.VISIBLE);
                        back3.setVisibility(View.VISIBLE);
                        back4.setVisibility(View.VISIBLE);
                        back5.setVisibility(View.VISIBLE);
                        back6.setVisibility(View.GONE);
                        back7.setVisibility(View.GONE);
                        back8.setVisibility(View.GONE);
                        back9.setVisibility(View.GONE);

                    } else if (s == 5) {
                        back6.setText(" "+scoreArrayback.get(s));
                        back6.setTextColor(Color.parseColor(colorArrayback.get(s)));
                        back1.setVisibility(View.VISIBLE);
                        back2.setVisibility(View.VISIBLE);
                        back3.setVisibility(View.VISIBLE);
                        back4.setVisibility(View.VISIBLE);
                        back5.setVisibility(View.VISIBLE);
                        back6.setVisibility(View.VISIBLE);
                        back7.setVisibility(View.GONE);
                        back8.setVisibility(View.GONE);
                        back9.setVisibility(View.GONE);

                    } else if (s == 6) {
                        back7.setText(" "+scoreArrayback.get(s));
                        back7.setTextColor(Color.parseColor(colorArrayback.get(s)));
                        back1.setVisibility(View.VISIBLE);
                        back2.setVisibility(View.VISIBLE);
                        back3.setVisibility(View.VISIBLE);
                        back4.setVisibility(View.VISIBLE);
                        back5.setVisibility(View.VISIBLE);
                        back6.setVisibility(View.VISIBLE);
                        back7.setVisibility(View.VISIBLE);
                        back8.setVisibility(View.GONE);
                        back9.setVisibility(View.GONE);

                    } else if (s == 7) {
                        back8.setText(" "+scoreArrayback.get(s));
                        back8.setTextColor(Color.parseColor(colorArrayback.get(s)));
                        back1.setVisibility(View.VISIBLE);
                        back2.setVisibility(View.VISIBLE);
                        back3.setVisibility(View.VISIBLE);
                        back4.setVisibility(View.VISIBLE);
                        back5.setVisibility(View.VISIBLE);
                        back6.setVisibility(View.VISIBLE);
                        back7.setVisibility(View.VISIBLE);
                        back8.setVisibility(View.VISIBLE);
                        back9.setVisibility(View.GONE);

                    } else if (s == 8) {
                        back9.setText(" "+scoreArrayback.get(s));
                        back9.setTextColor(Color.parseColor(colorArrayback.get(s)));
                        back1.setVisibility(View.VISIBLE);
                        back2.setVisibility(View.VISIBLE);
                        back3.setVisibility(View.VISIBLE);
                        back4.setVisibility(View.VISIBLE);
                        back5.setVisibility(View.VISIBLE);
                        back6.setVisibility(View.VISIBLE);
                        back7.setVisibility(View.VISIBLE);
                        back8.setVisibility(View.VISIBLE);
                        back9.setVisibility(View.VISIBLE);

                    }
                }
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

                        String is_lowest1 = jb_Score1.getString("is_lowest");
                        if (is_lowest1.equalsIgnoreCase("1")){
                            player1_up_lay1.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g1.setTextColor(Color.WHITE);
                        }else {
                            player1_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g1.setTextColor(Color.BLACK);
                        }

                       /* if (color_1.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g1.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay1.setBackgroundColor(Color.parseColor(color_1));
                        }*/

                        JSONObject jb_Score2 = job_hole_Score.getJSONObject("hole_num_2");

                        String score2_up = jb_Score2.getString("score");
                        player1_up_g2.setText(score2_up);
                        String color_2 = jb_Score2.getString("color");

                        String is_lowest2 = jb_Score2.getString("is_lowest");
                        if (is_lowest2.equalsIgnoreCase("1")){
                            player1_up_lay2.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g2.setTextColor(Color.WHITE);
                        }else {
                            player1_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g2.setTextColor(Color.BLACK);
                        }

                        /*if (color_2.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest3.equalsIgnoreCase("1")){
                            player1_up_lay3.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g3.setTextColor(Color.WHITE);
                        }else {
                            player1_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g3.setTextColor(Color.BLACK);
                        }

                        /*if (color_3.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest4.equalsIgnoreCase("1")){
                            player1_up_lay4.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g4.setTextColor(Color.WHITE);
                        }else {
                            player1_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g4.setTextColor(Color.BLACK);
                        }

                        /*if (color_4.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest5.equalsIgnoreCase("1")){
                            player1_up_lay5.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g5.setTextColor(Color.WHITE);
                        }else {
                            player1_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g5.setTextColor(Color.BLACK);
                        }

                        /*if (color_5.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest6.equalsIgnoreCase("1")){
                            player1_up_lay6.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g6.setTextColor(Color.WHITE);
                        }else {
                            player1_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g6.setTextColor(Color.BLACK);
                        }

                       /* if (color_6.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest7.equalsIgnoreCase("1")){
                            player1_up_lay7.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g7.setTextColor(Color.WHITE);
                        }else {
                            player1_up_lay7.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g7.setTextColor(Color.BLACK);
                        }

                        /*if (color_7.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest8.equalsIgnoreCase("1")){
                            player1_up_lay8.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g8.setTextColor(Color.WHITE);
                        }else {
                            player1_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g8.setTextColor(Color.BLACK);
                        }

                        /*if (color_8.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest9.equalsIgnoreCase("1")){
                            player1_up_lay9.setBackgroundColor(Color.parseColor(player1_color));
                            player1_up_g9.setTextColor(Color.WHITE);
                        }else {
                            player1_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g9.setTextColor(Color.BLACK);
                        }

                        /*if (color_9.equalsIgnoreCase("#ffffff")) {
                            player1_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player1_up_g9.setTextColor(Color.BLACK);
                        } else {
                            player1_up_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }*/


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
                        if (is_lowest1.equalsIgnoreCase("1")){
                            player2_up_lay1.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g1.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest2.equalsIgnoreCase("1")){
                            player2_up_lay2.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g2.setTextColor(Color.WHITE);
                        }else {
                            player2_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g2.setTextColor(Color.BLACK);
                        }

                        /*if (color_2.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest3.equalsIgnoreCase("1")){
                            player2_up_lay3.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g3.setTextColor(Color.WHITE);
                        }else {
                            player2_up_lay3.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g3.setTextColor(Color.BLACK);
                        }

                        /*if (color_3.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest4.equalsIgnoreCase("1")){
                            player2_up_lay4.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g4.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest5.equalsIgnoreCase("1")){
                            player2_up_lay5.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g5.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest6.equalsIgnoreCase("1")){
                            player2_up_lay6.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g6.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest7.equalsIgnoreCase("1")){
                            player2_up_lay7.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g7.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest8.equalsIgnoreCase("1")){
                            player2_up_lay8.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g8.setTextColor(Color.WHITE);
                        }else {
                            player2_up_lay8.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g8.setTextColor(Color.BLACK);
                        }

                        /*if (color_8.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest9.equalsIgnoreCase("1")){
                            player2_up_lay9.setBackgroundColor(Color.parseColor(player2_color));
                            player2_up_g9.setTextColor(Color.WHITE);
                        }else {
                            player2_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g9.setTextColor(Color.BLACK);
                        }

                        /*if (color_9.equalsIgnoreCase("#ffffff")) {
                            player2_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player2_up_g9.setTextColor(Color.BLACK);
                        } else {
                            player2_up_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }*/


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


                    }else if (p==2){
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
                        if (is_lowest1.equalsIgnoreCase("1")){
                            player3_up_lay1.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g1.setTextColor(Color.WHITE);
                        }else {
                            player3_up_lay1.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g1.setTextColor(Color.BLACK);
                        }

                        /*if (color_1.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest2.equalsIgnoreCase("1")){
                            player3_up_lay2.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g2.setTextColor(Color.WHITE);
                        }else {
                            player3_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g2.setTextColor(Color.BLACK);
                        }

                        /*if (color_2.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest3.equalsIgnoreCase("1")){
                            player3_up_lay3.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g3.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest4.equalsIgnoreCase("1")){
                            player3_up_lay4.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g4.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest5.equalsIgnoreCase("1")){
                            player3_up_lay5.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g5.setTextColor(Color.WHITE);
                        }else {
                            player3_up_lay5.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g5.setTextColor(Color.BLACK);
                        }

                        /*if (color_5.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest6.equalsIgnoreCase("1")){
                            player3_up_lay6.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g6.setTextColor(Color.WHITE);
                        }else {
                            player3_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g6.setTextColor(Color.BLACK);
                        }

                        /*if (color_6.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest7.equalsIgnoreCase("1")){
                            player3_up_lay7.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g7.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest8.equalsIgnoreCase("1")){
                            player3_up_lay8.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g8.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest9.equalsIgnoreCase("1")){
                            player3_up_lay9.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g9.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest1.equalsIgnoreCase("1")){
                            player3_up_lay1.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g1.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest2.equalsIgnoreCase("1")){
                            player3_up_lay2.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g2.setTextColor(Color.WHITE);
                        }else {
                            player3_up_lay2.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g2.setTextColor(Color.BLACK);
                        }

                        /*if (color_2.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest3.equalsIgnoreCase("1")){
                            player3_up_lay3.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g3.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest4.equalsIgnoreCase("1")){
                            player3_up_lay4.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g4.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest5.equalsIgnoreCase("1")){
                            player3_up_lay5.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g5.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest6.equalsIgnoreCase("1")){
                            player3_up_lay6.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g6.setTextColor(Color.WHITE);
                        }else {
                            player3_up_lay6.setBackgroundResource(R.drawable.cell_default);
                            player3_up_g6.setTextColor(Color.BLACK);
                        }

                        /*if (color_6.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest7.equalsIgnoreCase("1")){
                            player3_up_lay7.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g7.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest8.equalsIgnoreCase("1")){
                            player3_up_lay8.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g8.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest9.equalsIgnoreCase("1")){
                            player3_up_lay9.setBackgroundColor(Color.parseColor(player3_color));
                            player3_up_g9.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest1.equalsIgnoreCase("1")){
                            player4_up_lay1.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g1.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest2.equalsIgnoreCase("1")){
                            player4_up_lay2.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g2.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest3.equalsIgnoreCase("1")){
                            player4_up_lay3.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g3.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest4.equalsIgnoreCase("1")){
                            player4_up_lay4.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g4.setTextColor(Color.WHITE);
                        }else {
                            player4_up_lay4.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g4.setTextColor(Color.BLACK);
                        }

                        /*if (color_4.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest5.equalsIgnoreCase("1")){
                            player4_up_lay5.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g5.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest6.equalsIgnoreCase("1")){
                            player4_up_lay6.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g6.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest7.equalsIgnoreCase("1")){
                            player4_up_lay7.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g7.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest8.equalsIgnoreCase("1")){
                            player4_up_lay8.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g8.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest9.equalsIgnoreCase("1")){
                            player4_up_lay9.setBackgroundColor(Color.parseColor(player4_color));
                            player4_up_g9.setTextColor(Color.WHITE);
                        }else {
                            player4_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g9.setTextColor(Color.BLACK);
                        }

                        /*if (color_9.equalsIgnoreCase("#ffffff")) {
                            player4_up_lay9.setBackgroundResource(R.drawable.cell_default);
                            player4_up_g9.setTextColor(Color.BLACK);
                        } else {
                            player4_up_lay9.setBackgroundColor(Color.parseColor(color_9));
                        }
*/
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

                if (event_id.equalsIgnoreCase("11")){

                }else {

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
                        if (is_lowest10.equalsIgnoreCase("1")){
                            player1_down_lay1.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g1.setTextColor(Color.WHITE);
                        }else {
                            player1_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g1.setTextColor(Color.BLACK);
                        }

                        /*if (color_1.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest11.equalsIgnoreCase("1")){
                            player1_down_lay2.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g2.setTextColor(Color.WHITE);
                        }else {
                            player1_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g2.setTextColor(Color.BLACK);
                        }

                        /*if (color_2.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay2.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g2.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay2.setBackgroundColor(Color.parseColor(color_2));
                        }
*/
                        JSONObject jb_Score12 = job_hole_Score.getJSONObject("hole_num_12");
                        String score3_down = jb_Score12.getString("score");
                        player1_down_g3.setText(score3_down);
                        String color_3 = jb_Score12.getString("color");

                        String is_lowest12 = jb_Score12.getString("is_lowest");
                        if (is_lowest12.equalsIgnoreCase("1")){
                            player1_down_lay3.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g3.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest13.equalsIgnoreCase("1")){
                            player1_down_lay4.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g4.setTextColor(Color.WHITE);
                        }else {
                            player1_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g4.setTextColor(Color.BLACK);
                        }

                       /* if (color_4.equalsIgnoreCase("#ffffff")) {
                            player1_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player1_down_g4.setTextColor(Color.BLACK);
                        } else {
                            player1_down_lay4.setBackgroundColor(Color.parseColor(color_4));
                        }
*/
                        JSONObject jb_Score14 = job_hole_Score.getJSONObject("hole_num_14");
                        String score5_down = jb_Score14.getString("score");
                        player1_down_g5.setText(score5_down);
                        String color_5 = jb_Score14.getString("color");

                        String is_lowest14 = jb_Score14.getString("is_lowest");
                        if (is_lowest14.equalsIgnoreCase("1")){
                            player1_down_lay5.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g5.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest15.equalsIgnoreCase("1")){
                            player1_down_lay6.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g6.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest16.equalsIgnoreCase("1")){
                            player1_down_lay7.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g7.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest17.equalsIgnoreCase("1")){
                            player1_down_lay8.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g8.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest18.equalsIgnoreCase("1")){
                            player1_down_lay9.setBackgroundColor(Color.parseColor(player1_color));
                            player1_down_g9.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest10.equalsIgnoreCase("1")){
                            player2_down_lay1.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g1.setTextColor(Color.WHITE);
                        }else {
                            player2_down_lay1.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g1.setTextColor(Color.BLACK);
                        }

                       /* if (color_1.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest11.equalsIgnoreCase("1")){
                            player2_down_lay2.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g2.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest12.equalsIgnoreCase("1")){
                            player2_down_lay3.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g3.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest13.equalsIgnoreCase("1")){
                            player2_down_lay4.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g4.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest14.equalsIgnoreCase("1")){
                            player2_down_lay5.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g5.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest15.equalsIgnoreCase("1")){
                            player2_down_lay6.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g6.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest16.equalsIgnoreCase("1")){
                            player2_down_lay7.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g7.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest17.equalsIgnoreCase("1")){
                            player2_down_lay8.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g8.setTextColor(Color.WHITE);
                        }else {
                            player2_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g8.setTextColor(Color.BLACK);
                        }

                        /*if (color_8.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest18.equalsIgnoreCase("1")){
                            player2_down_lay9.setBackgroundColor(Color.parseColor(player2_color));
                            player2_down_g9.setTextColor(Color.WHITE);
                        }else {
                            player2_down_lay9.setBackgroundResource(R.drawable.cell_default);
                            player2_down_g9.setTextColor(Color.BLACK);
                        }

                        /*if (color_9.equalsIgnoreCase("#ffffff")) {
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
                    }if (p==2){

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
                        if (is_lowest10.equalsIgnoreCase("1")){
                            player3_down_lay1.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g1.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest11.equalsIgnoreCase("1")){
                            player3_down_lay2.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g2.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest12.equalsIgnoreCase("1")){
                            player3_down_lay3.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g3.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest13.equalsIgnoreCase("1")){
                            player3_down_lay4.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g4.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest14.equalsIgnoreCase("1")){
                            player3_down_lay5.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g5.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest15.equalsIgnoreCase("1")){
                            player3_down_lay6.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g6.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest16.equalsIgnoreCase("1")){
                            player3_down_lay7.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g7.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest17.equalsIgnoreCase("1")){
                            player3_down_lay8.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g8.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest10.equalsIgnoreCase("1")){
                            player3_down_lay9.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g9.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest10.equalsIgnoreCase("1")){
                            player3_down_lay1.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g1.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest11.equalsIgnoreCase("1")){
                            player3_down_lay2.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g2.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest12.equalsIgnoreCase("1")){
                            player3_down_lay3.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g3.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest13.equalsIgnoreCase("1")){
                            player3_down_lay4.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g4.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest14.equalsIgnoreCase("1")){
                            player3_down_lay5.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g5.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest15.equalsIgnoreCase("1")){
                            player3_down_lay6.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g6.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest16.equalsIgnoreCase("1")){
                            player3_down_lay7.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g7.setTextColor(Color.WHITE);
                        }else {
                            player3_down_lay7.setBackgroundResource(R.drawable.cell_default);
                            player3_down_g7.setTextColor(Color.BLACK);
                        }

                       /* if (color_7.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest17.equalsIgnoreCase("1")){
                            player3_down_lay8.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g8.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest18.equalsIgnoreCase("1")){
                            player3_down_lay9.setBackgroundColor(Color.parseColor(player3_color));
                            player3_down_g9.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest10.equalsIgnoreCase("1")){
                            player4_down_lay1.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g1.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest11.equalsIgnoreCase("1")){
                            player4_down_lay2.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g2.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest12.equalsIgnoreCase("1")){
                            player4_down_lay3.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g3.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest13.equalsIgnoreCase("1")){
                            player4_down_lay4.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g4.setTextColor(Color.WHITE);
                        }else {
                            player4_down_lay4.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g4.setTextColor(Color.BLACK);
                        }

                        /*if (color_4.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest14.equalsIgnoreCase("1")){
                            player4_down_lay5.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g5.setTextColor(Color.WHITE);
                        }else {
                            player4_down_lay5.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g5.setTextColor(Color.BLACK);
                        }

                        /*if (color_5.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest15.equalsIgnoreCase("1")){
                            player4_down_lay6.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g6.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest16.equalsIgnoreCase("1")){
                            player4_down_lay7.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g7.setTextColor(Color.WHITE);
                        }else {
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
                        if (is_lowest17.equalsIgnoreCase("1")){
                            player4_down_lay8.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g8.setTextColor(Color.WHITE);
                        }else {
                            player4_down_lay8.setBackgroundResource(R.drawable.cell_default);
                            player4_down_g8.setTextColor(Color.BLACK);
                        }

                       /* if (color_8.equalsIgnoreCase("#ffffff")) {
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
                        if (is_lowest18.equalsIgnoreCase("1")){
                            player4_down_lay9.setBackgroundColor(Color.parseColor(player4_color));
                            player4_down_g9.setTextColor(Color.WHITE);
                        }else {
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

                if (event_id.equalsIgnoreCase("11")){

                    standings_up_row.setVisibility(View.GONE);
                    standings_down_row.setVisibility(View.GONE);

                }else  {

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

            if (event_id.equalsIgnoreCase("11")){
                standings_up_row.setVisibility(View.GONE);
                standings_down_row.setVisibility(View.GONE);

            }else {

                if (is_team.equalsIgnoreCase("1")) {

                    teamTabA.setText(teamAname);
                    teamTabA.setTextColor(Color.parseColor(player1_color));
                    teamTabB.setText(teamBname);
                    teamTabB.setTextColor(Color.parseColor(player3_color));
                    teamAPlayer1.setText(player1_Name+" "+player1_handicap);
                    teamAPlayer1.setTextColor(Color.parseColor(player1_color));
                    teamAPlayer2.setText(player2_Name+" "+player2_handicap);
                    teamAPlayer2.setTextColor(Color.parseColor(player2_color));
                    teamBPlayer1.setText(player3_Name+" "+player3_handicap);
                    teamBPlayer1.setTextColor(Color.parseColor(player3_color));
                    teamBPlayer2.setText(player4_Name+" "+player4_handicap);
                    teamBPlayer2.setTextColor(Color.parseColor(player4_color));
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
            int indexTOT = Integer.parseInt(strIndex_up)+Integer.parseInt(strIndex_down);
            indexTotal.setText(""+indexTOT);

            String strP1_up = player1_up_total.getText().toString();
            String strP1_down = player1_down_total.getText().toString();
            int p1 = Integer.parseInt(strP1_up)+Integer.parseInt(strP1_down);
            player1_total.setText(""+p1);

            String strP2_up = player2_up_total.getText().toString();
            String strP2_down = player2_down_total.getText().toString();
            int p2 = Integer.parseInt(strP2_up)+Integer.parseInt(strP2_down);
            player2_total.setText(""+p2);

            String strP3_up = player3_up_total.getText().toString();
            String strP3_down = player3_down_total.getText().toString();
            int p3 = Integer.parseInt(strP3_up)+Integer.parseInt(strP3_down);
            player3_total.setText(""+p3);

            String strP4_up = player4_up_total.getText().toString();
            String strP4_down = player4_down_total.getText().toString();
            int p4 = Integer.parseInt(strP4_up)+Integer.parseInt(strP4_down);
            player4_total.setText(""+p4);

            String parUp = par_back_total.getText().toString();
            String parDown = par_down_total.getText().toString();
            int parTT = Integer.parseInt(parUp)+Integer.parseInt(parDown);
            parTotal.setText(""+parTT);





        } catch (JSONException je)

        {
            je.printStackTrace();
        }


       /* winner tab layout at top */

        Log.v("winner","cur"+current_winner+"win1"+winner1+"win2"+winner2);

        if (current_winner!=null && current_winner.length()!=0 ) {
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
        if (stackClear !=null && stackClear.equalsIgnoreCase("delegate")) {

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("eventID", eventID);
            intent.putExtra("fromEventPreview", "1");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }else {
            finish();
        }

    }


    public Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);

        store(bitmap,"putt.png");

        return bitmap;
    }

    public  void store(Bitmap bm, String fileName){
        final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
        File dir = new File(dirPath);
        if(!dir.exists())
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

    private  void shareImage(File file){
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


    public void autopressPopup(){

        ImageView finishdialogfrag;



        final Dialog dialog = new Dialog(AutoPressScoreCard.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog.setContentView(R.layout.addscoretabl);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        finishdialogfrag=(ImageView)dialog.findViewById(R.id.finishdialogfrag);

        front1_hole1 = (LinearLayout)dialog.findViewById(R.id.front_layout_r1);
        front1_hole2 = (LinearLayout)dialog.findViewById(R.id.front_layout_r2);
        front1_hole3 = (LinearLayout)dialog.findViewById(R.id.front_layout_r3);
        front1_hole4 = (LinearLayout)dialog.findViewById(R.id.front_layout_r4);
        front1_hole5 = (LinearLayout)dialog.findViewById(R.id.front_layout_r5);
        front1_hole6 = (LinearLayout)dialog.findViewById(R.id.front_layout_r6);
        front1_hole7 = (LinearLayout)dialog.findViewById(R.id.front_layout_r7);
        front1_hole8 = (LinearLayout)dialog.findViewById(R.id.front_layout_r8);
        front1_hole9 = (LinearLayout)dialog.findViewById(R.id.front_layout_r9);
        front1_hole10 = (LinearLayout)dialog.findViewById(R.id.front_layout_r10);
        front1_hole11 = (LinearLayout)dialog.findViewById(R.id.front_layout_r11);
        front1_hole12 = (LinearLayout)dialog.findViewById(R.id.front_layout_r12);
        front1_hole13 = (LinearLayout)dialog.findViewById(R.id.front_layout_r13);
        front1_hole14 = (LinearLayout)dialog.findViewById(R.id.front_layout_r14);
        front1_hole15 = (LinearLayout)dialog.findViewById(R.id.front_layout_r15);
        front1_hole16 = (LinearLayout)dialog.findViewById(R.id.front_layout_r16);
        front1_hole17 = (LinearLayout)dialog.findViewById(R.id.front_layout_r17);
        front1_hole18 = (LinearLayout)dialog.findViewById(R.id.front_layout_r18);

        back1_hole1 = (LinearLayout)dialog.findViewById(R.id.back_layout_r1);
        back1_hole2 = (LinearLayout)dialog.findViewById(R.id.back_layout_r2);
        back1_hole3 = (LinearLayout)dialog.findViewById(R.id.back_layout_r3);
        back1_hole4 = (LinearLayout)dialog.findViewById(R.id.back_layout_r4);
        back1_hole5 = (LinearLayout)dialog.findViewById(R.id.back_layout_r5);
        back1_hole6 = (LinearLayout)dialog.findViewById(R.id.back_layout_r6);
        back1_hole7 = (LinearLayout)dialog.findViewById(R.id.back_layout_r7);
        back1_hole8 = (LinearLayout)dialog.findViewById(R.id.back_layout_r8);
        back1_hole9 = (LinearLayout)dialog.findViewById(R.id.back_layout_r9);
        back1_hole10 = (LinearLayout)dialog.findViewById(R.id.back_layout_r10);
        back1_hole11 = (LinearLayout)dialog.findViewById(R.id.back_layout_r11);
        back1_hole12 = (LinearLayout)dialog.findViewById(R.id.back_layout_r12);
        back1_hole13 = (LinearLayout)dialog.findViewById(R.id.back_layout_r13);
        back1_hole14 = (LinearLayout)dialog.findViewById(R.id.back_layout_r14);
        back1_hole15 = (LinearLayout)dialog.findViewById(R.id.back_layout_r15);
        back1_hole16 = (LinearLayout)dialog.findViewById(R.id.back_layout_r16);
        back1_hole17 = (LinearLayout)dialog.findViewById(R.id.back_layout_r17);
        back1_hole18 = (LinearLayout)dialog.findViewById(R.id.back_layout_r18);

        and1 = (TextView)dialog.findViewById(R.id.andText_r1);
        and2 = (TextView)dialog.findViewById(R.id.andText_r2);
        and3 = (TextView)dialog.findViewById(R.id.andText_r3);
        and4 = (TextView)dialog.findViewById(R.id.andText_r4);
        and5 = (TextView)dialog.findViewById(R.id.andText_r5);
        and6 = (TextView)dialog.findViewById(R.id.andText_r6);
        and7 = (TextView)dialog.findViewById(R.id.andText_r7);
        and8 = (TextView)dialog.findViewById(R.id.andText_r8);
        and9 = (TextView)dialog.findViewById(R.id.andText_r9);
        and10 = (TextView)dialog.findViewById(R.id.andText_r10);
        and11 = (TextView)dialog.findViewById(R.id.andText_r11);
        and12 = (TextView)dialog.findViewById(R.id.andText_r12);
        and13 = (TextView)dialog.findViewById(R.id.andText_r13);
        and14 = (TextView)dialog.findViewById(R.id.andText_r14);
        and15 = (TextView)dialog.findViewById(R.id.andText_r15);
        and16 = (TextView)dialog.findViewById(R.id.andText_r16);
        and17 = (TextView)dialog.findViewById(R.id.andText_r17);
        and18 = (TextView)dialog.findViewById(R.id.andText_r18);



        hole_a1 = (TextView)dialog.findViewById(R.id.hole_auto_r1);
        hole_a2 = (TextView)dialog.findViewById(R.id.hole_auto_r2);
        hole_a3 = (TextView)dialog.findViewById(R.id.hole_auto_r3);
        hole_a4 = (TextView)dialog.findViewById(R.id.hole_auto_r4);
        hole_a5 = (TextView)dialog.findViewById(R.id.hole_auto_r5);
        hole_a6 = (TextView)dialog.findViewById(R.id.hole_auto_r6);
        hole_a7 = (TextView)dialog.findViewById(R.id.hole_auto_r7);
        hole_a8 = (TextView)dialog.findViewById(R.id.hole_auto_r8);
        hole_a9 = (TextView)dialog.findViewById(R.id.hole_auto_r9);
        hole_a10 = (TextView)dialog.findViewById(R.id.hole_auto_r10);
        hole_a11 = (TextView)dialog.findViewById(R.id.hole_auto_r11);
        hole_a12 = (TextView)dialog.findViewById(R.id.hole_auto_r12);
        hole_a13 = (TextView)dialog.findViewById(R.id.hole_auto_r13);
        hole_a14 = (TextView)dialog.findViewById(R.id.hole_auto_r14);
        hole_a15 = (TextView)dialog.findViewById(R.id.hole_auto_r15);
        hole_a16 = (TextView)dialog.findViewById(R.id.hole_auto_r16);
        hole_a17 = (TextView)dialog.findViewById(R.id.hole_auto_r17);
        hole_a18 = (TextView)dialog.findViewById(R.id.hole_auto_r18);

        r1 = (LinearLayout)dialog.findViewById(R.id.r1);
        r2 = (LinearLayout)dialog.findViewById(R.id.r2);
        r3 = (LinearLayout)dialog.findViewById(R.id.r3);
        r4 = (LinearLayout)dialog.findViewById(R.id.r4);
        r5 = (LinearLayout)dialog.findViewById(R.id.r5);
        r6 = (LinearLayout)dialog.findViewById(R.id.r6);
        r7 = (LinearLayout)dialog.findViewById(R.id.r7);
        r8 = (LinearLayout)dialog.findViewById(R.id.r8);
        r9 = (LinearLayout)dialog.findViewById(R.id.r9);
        r10 = (LinearLayout)dialog.findViewById(R.id.r10);
        r11 = (LinearLayout)dialog.findViewById(R.id.r11);
        r12 = (LinearLayout)dialog.findViewById(R.id.r12);
        r13 = (LinearLayout)dialog.findViewById(R.id.r13);
        r14 = (LinearLayout)dialog.findViewById(R.id.r14);
        r15 = (LinearLayout)dialog.findViewById(R.id.r15);
        r16 = (LinearLayout)dialog.findViewById(R.id.r16);
        r17 = (LinearLayout)dialog.findViewById(R.id.r17);
        r18 = (LinearLayout)dialog.findViewById(R.id.r18);



        f1_r1 = (TextView)dialog.findViewById(R.id.f1_r1);
        f1_r2 = (TextView)dialog.findViewById(R.id.f1_r2);
        f1_r3 = (TextView)dialog.findViewById(R.id.f1_r3);
        f1_r4 = (TextView)dialog.findViewById(R.id.f1_r4);
        f1_r5 = (TextView)dialog.findViewById(R.id.f1_r5);
        f1_r6 = (TextView)dialog.findViewById(R.id.f1_r6);
        f1_r7 = (TextView)dialog.findViewById(R.id.f1_r7);
        f1_r8 = (TextView)dialog.findViewById(R.id.f1_r8);
        f1_r9 = (TextView)dialog.findViewById(R.id.f1_r9);
        f1_r10 = (TextView)dialog.findViewById(R.id.f1_r10);
        b1_r1 = (TextView)dialog.findViewById(R.id.b1_r1);
        b1_r2 = (TextView)dialog.findViewById(R.id.b1_r2);
        b1_r3 = (TextView)dialog.findViewById(R.id.b1_r3);
        b1_r4 = (TextView)dialog.findViewById(R.id.b1_r4);
        b1_r5 = (TextView)dialog.findViewById(R.id.b1_r5);
        b1_r6 = (TextView)dialog.findViewById(R.id.b1_r6);
        b1_r7 = (TextView)dialog.findViewById(R.id.b1_r7);
        b1_r8 = (TextView)dialog.findViewById(R.id.b1_r8);
        b1_r9 = (TextView)dialog.findViewById(R.id.b1_r9);
        b1_r10 = (TextView)dialog.findViewById(R.id.b1_r10);

        f2_r1 = (TextView)dialog.findViewById(R.id.f2_r1);
        f2_r2 = (TextView)dialog.findViewById(R.id.f2_r2);
        f2_r3 = (TextView)dialog.findViewById(R.id.f2_r3);
        f2_r4 = (TextView)dialog.findViewById(R.id.f2_r4);
        f2_r5 = (TextView)dialog.findViewById(R.id.f2_r5);
        f2_r6 = (TextView)dialog.findViewById(R.id.f2_r6);
        f2_r7 = (TextView)dialog.findViewById(R.id.f2_r7);
        f2_r8 = (TextView)dialog.findViewById(R.id.f2_r8);
        f2_r9 = (TextView)dialog.findViewById(R.id.f2_r9);
        f2_r10 = (TextView)dialog.findViewById(R.id.f2_r10);
        b2_r1 = (TextView)dialog.findViewById(R.id.b2_r1);
        b2_r2 = (TextView)dialog.findViewById(R.id.b2_r2);
        b2_r3 = (TextView)dialog.findViewById(R.id.b2_r3);
        b2_r4 = (TextView)dialog.findViewById(R.id.b2_r4);
        b2_r5 = (TextView)dialog.findViewById(R.id.b2_r5);
        b2_r6 = (TextView)dialog.findViewById(R.id.b2_r6);
        b2_r7 = (TextView)dialog.findViewById(R.id.b2_r7);
        b2_r8 = (TextView)dialog.findViewById(R.id.b2_r8);
        b2_r9 = (TextView)dialog.findViewById(R.id.b2_r9);
        b2_r10 = (TextView)dialog.findViewById(R.id.b2_r10);

        f3_r1 = (TextView)dialog.findViewById(R.id.f3_r1);
        f3_r2 = (TextView)dialog.findViewById(R.id.f3_r2);
        f3_r3 = (TextView)dialog.findViewById(R.id.f3_r3);
        f3_r4 = (TextView)dialog.findViewById(R.id.f3_r4);
        f3_r5 = (TextView)dialog.findViewById(R.id.f3_r5);
        f3_r6 = (TextView)dialog.findViewById(R.id.f3_r6);
        f3_r7 = (TextView)dialog.findViewById(R.id.f3_r7);
        f3_r8 = (TextView)dialog.findViewById(R.id.f3_r8);
        f3_r9 = (TextView)dialog.findViewById(R.id.f3_r9);
        f3_r10 = (TextView)dialog.findViewById(R.id.f3_r10);
        b3_r1 = (TextView)dialog.findViewById(R.id.b3_r1);
        b3_r2 = (TextView)dialog.findViewById(R.id.b3_r2);
        b3_r3 = (TextView)dialog.findViewById(R.id.b3_r3);
        b3_r4 = (TextView)dialog.findViewById(R.id.b3_r4);
        b3_r5 = (TextView)dialog.findViewById(R.id.b3_r5);
        b3_r6 = (TextView)dialog.findViewById(R.id.b3_r6);
        b3_r7 = (TextView)dialog.findViewById(R.id.b3_r7);
        b3_r8 = (TextView)dialog.findViewById(R.id.b3_r8);
        b3_r9 = (TextView)dialog.findViewById(R.id.b3_r9);
        b3_r10 = (TextView)dialog.findViewById(R.id.b3_r10);

        f4_r1 = (TextView)dialog.findViewById(R.id.f4_r1);
        f4_r2 = (TextView)dialog.findViewById(R.id.f4_r2);
        f4_r3 = (TextView)dialog.findViewById(R.id.f4_r3);
        f4_r4 = (TextView)dialog.findViewById(R.id.f4_r4);
        f4_r5 = (TextView)dialog.findViewById(R.id.f4_r5);
        f4_r6 = (TextView)dialog.findViewById(R.id.f4_r6);
        f4_r7 = (TextView)dialog.findViewById(R.id.f4_r7);
        f4_r8 = (TextView)dialog.findViewById(R.id.f4_r8);
        f4_r9 = (TextView)dialog.findViewById(R.id.f4_r9);
        f4_r10 = (TextView)dialog.findViewById(R.id.f4_r10);
        b4_r1 = (TextView)dialog.findViewById(R.id.b4_r1);
        b4_r2 = (TextView)dialog.findViewById(R.id.b4_r2);
        b4_r3 = (TextView)dialog.findViewById(R.id.b4_r3);
        b4_r4 = (TextView)dialog.findViewById(R.id.b4_r4);
        b4_r5 = (TextView)dialog.findViewById(R.id.b4_r5);
        b4_r6 = (TextView)dialog.findViewById(R.id.b4_r6);
        b4_r7 = (TextView)dialog.findViewById(R.id.b4_r7);
        b4_r8 = (TextView)dialog.findViewById(R.id.b4_r8);
        b4_r9 = (TextView)dialog.findViewById(R.id.b4_r9);
        b4_r10 = (TextView)dialog.findViewById(R.id.b4_r10);

        f5_r1 = (TextView)dialog.findViewById(R.id.f5_r1);
        f5_r2 = (TextView)dialog.findViewById(R.id.f5_r2);
        f5_r3 = (TextView)dialog.findViewById(R.id.f5_r3);
        f5_r4 = (TextView)dialog.findViewById(R.id.f5_r4);
        f5_r5 = (TextView)dialog.findViewById(R.id.f5_r5);
        f5_r6 = (TextView)dialog.findViewById(R.id.f5_r6);
        f5_r7 = (TextView)dialog.findViewById(R.id.f5_r7);
        f5_r8 = (TextView)dialog.findViewById(R.id.f5_r8);
        f5_r9 = (TextView)dialog.findViewById(R.id.f5_r9);
        f5_r10 = (TextView)dialog.findViewById(R.id.f5_r10);
        b5_r1 = (TextView)dialog.findViewById(R.id.b5_r1);
        b5_r2 = (TextView)dialog.findViewById(R.id.b5_r2);
        b5_r3 = (TextView)dialog.findViewById(R.id.b5_r3);
        b5_r4 = (TextView)dialog.findViewById(R.id.b5_r4);
        b5_r5 = (TextView)dialog.findViewById(R.id.b5_r5);
        b5_r6 = (TextView)dialog.findViewById(R.id.b5_r6);
        b5_r7 = (TextView)dialog.findViewById(R.id.b5_r7);
        b5_r8 = (TextView)dialog.findViewById(R.id.b5_r8);
        b5_r9 = (TextView)dialog.findViewById(R.id.b5_r9);
        b5_r10 = (TextView)dialog.findViewById(R.id.b5_r10);

        f6_r1 = (TextView)dialog.findViewById(R.id.f6_r1);
        f6_r2 = (TextView)dialog.findViewById(R.id.f6_r2);
        f6_r3 = (TextView)dialog.findViewById(R.id.f6_r3);
        f6_r4 = (TextView)dialog.findViewById(R.id.f6_r4);
        f6_r5 = (TextView)dialog.findViewById(R.id.f6_r5);
        f6_r6 = (TextView)dialog.findViewById(R.id.f6_r6);
        f6_r7 = (TextView)dialog.findViewById(R.id.f6_r7);
        f6_r8 = (TextView)dialog.findViewById(R.id.f6_r8);
        f6_r9 = (TextView)dialog.findViewById(R.id.f6_r9);
        f6_r10 = (TextView)dialog.findViewById(R.id.f6_r10);
        b6_r1 = (TextView)dialog.findViewById(R.id.b6_r1);
        b6_r2 = (TextView)dialog.findViewById(R.id.b6_r2);
        b6_r3 = (TextView)dialog.findViewById(R.id.b6_r3);
        b6_r4 = (TextView)dialog.findViewById(R.id.b6_r4);
        b6_r5 = (TextView)dialog.findViewById(R.id.b6_r5);
        b6_r6 = (TextView)dialog.findViewById(R.id.b6_r6);
        b6_r7 = (TextView)dialog.findViewById(R.id.b6_r7);
        b6_r8 = (TextView)dialog.findViewById(R.id.b6_r8);
        b6_r9 = (TextView)dialog.findViewById(R.id.b6_r9);
        b6_r10 = (TextView)dialog.findViewById(R.id.b6_r10);

        f7_r1 = (TextView)dialog.findViewById(R.id.f7_r1);
        f7_r2 = (TextView)dialog.findViewById(R.id.f7_r2);
        f7_r3 = (TextView)dialog.findViewById(R.id.f7_r3);
        f7_r4 = (TextView)dialog.findViewById(R.id.f7_r4);
        f7_r5 = (TextView)dialog.findViewById(R.id.f7_r5);
        f7_r6 = (TextView)dialog.findViewById(R.id.f7_r6);
        f7_r7 = (TextView)dialog.findViewById(R.id.f7_r7);
        f7_r8 = (TextView)dialog.findViewById(R.id.f7_r8);
        f7_r9 = (TextView)dialog.findViewById(R.id.f7_r9);
        f7_r10 = (TextView)dialog.findViewById(R.id.f7_r10);
        b7_r1 = (TextView)dialog.findViewById(R.id.b7_r1);
        b7_r2 = (TextView)dialog.findViewById(R.id.b7_r2);
        b7_r3 = (TextView)dialog.findViewById(R.id.b7_r3);
        b7_r4 = (TextView)dialog.findViewById(R.id.b7_r4);
        b7_r5 = (TextView)dialog.findViewById(R.id.b7_r5);
        b7_r6 = (TextView)dialog.findViewById(R.id.b7_r6);
        b7_r7 = (TextView)dialog.findViewById(R.id.b7_r7);
        b7_r8 = (TextView)dialog.findViewById(R.id.b7_r8);
        b7_r9 = (TextView)dialog.findViewById(R.id.b7_r9);
        b7_r10 = (TextView)dialog.findViewById(R.id.b7_r10);

        f8_r1 = (TextView)dialog.findViewById(R.id.f8_r1);
        f8_r2 = (TextView)dialog.findViewById(R.id.f8_r2);
        f8_r3 = (TextView)dialog.findViewById(R.id.f8_r3);
        f8_r4 = (TextView)dialog.findViewById(R.id.f8_r4);
        f8_r5 = (TextView)dialog.findViewById(R.id.f8_r5);
        f8_r6 = (TextView)dialog.findViewById(R.id.f8_r6);
        f8_r7 = (TextView)dialog.findViewById(R.id.f8_r7);
        f8_r8 = (TextView)dialog.findViewById(R.id.f8_r8);
        f8_r9 = (TextView)dialog.findViewById(R.id.f8_r9);
        f8_r10 = (TextView)dialog.findViewById(R.id.f8_r10);
        b8_r1 = (TextView)dialog.findViewById(R.id.b8_r1);
        b8_r2 = (TextView)dialog.findViewById(R.id.b8_r2);
        b8_r3 = (TextView)dialog.findViewById(R.id.b8_r3);
        b8_r4 = (TextView)dialog.findViewById(R.id.b8_r4);
        b8_r5 = (TextView)dialog.findViewById(R.id.b8_r5);
        b8_r6 = (TextView)dialog.findViewById(R.id.b8_r6);
        b8_r7 = (TextView)dialog.findViewById(R.id.b8_r7);
        b8_r8 = (TextView)dialog.findViewById(R.id.b8_r8);
        b8_r9 = (TextView)dialog.findViewById(R.id.b8_r9);
        b8_r10 = (TextView)dialog.findViewById(R.id.b8_r10);

        f9_r1 = (TextView)dialog.findViewById(R.id.f9_r1);
        f9_r2 = (TextView)dialog.findViewById(R.id.f9_r2);
        f9_r3 = (TextView)dialog.findViewById(R.id.f9_r3);
        f9_r4 = (TextView)dialog.findViewById(R.id.f9_r4);
        f9_r5 = (TextView)dialog.findViewById(R.id.f9_r5);
        f9_r6 = (TextView)dialog.findViewById(R.id.f9_r6);
        f9_r7 = (TextView)dialog.findViewById(R.id.f9_r7);
        f9_r8 = (TextView)dialog.findViewById(R.id.f9_r8);
        f9_r9 = (TextView)dialog.findViewById(R.id.f9_r9);
        f9_r10 = (TextView)dialog.findViewById(R.id.f9_r10);
        b9_r1 = (TextView)dialog.findViewById(R.id.b9_r1);
        b9_r2 = (TextView)dialog.findViewById(R.id.b9_r2);
        b9_r3 = (TextView)dialog.findViewById(R.id.b9_r3);
        b9_r4 = (TextView)dialog.findViewById(R.id.b9_r4);
        b9_r5 = (TextView)dialog.findViewById(R.id.b9_r5);
        b9_r6 = (TextView)dialog.findViewById(R.id.b9_r6);
        b9_r7 = (TextView)dialog.findViewById(R.id.b9_r7);
        b9_r8 = (TextView)dialog.findViewById(R.id.b9_r8);
        b9_r9 = (TextView)dialog.findViewById(R.id.b9_r9);
        b9_r10 = (TextView)dialog.findViewById(R.id.b9_r10);

        f10_r1 = (TextView)dialog.findViewById(R.id.f10_r1);
        f10_r2 = (TextView)dialog.findViewById(R.id.f10_r2);
        f10_r3 = (TextView)dialog.findViewById(R.id.f10_r3);
        f10_r4 = (TextView)dialog.findViewById(R.id.f10_r4);
        f10_r5 = (TextView)dialog.findViewById(R.id.f10_r5);
        f10_r6 = (TextView)dialog.findViewById(R.id.f10_r6);
        f10_r7 = (TextView)dialog.findViewById(R.id.f10_r7);
        f10_r8 = (TextView)dialog.findViewById(R.id.f10_r8);
        f10_r9 = (TextView)dialog.findViewById(R.id.f10_r9);
        f10_r10 = (TextView)dialog.findViewById(R.id.f10_r10);
        b10_r1 = (TextView)dialog.findViewById(R.id.b10_r1);
        b10_r2 = (TextView)dialog.findViewById(R.id.b10_r2);
        b10_r3 = (TextView)dialog.findViewById(R.id.b10_r3);
        b10_r4 = (TextView)dialog.findViewById(R.id.b10_r4);
        b10_r5 = (TextView)dialog.findViewById(R.id.b10_r5);
        b10_r6 = (TextView)dialog.findViewById(R.id.b10_r6);
        b10_r7 = (TextView)dialog.findViewById(R.id.b10_r7);
        b10_r8 = (TextView)dialog.findViewById(R.id.b10_r8);
        b10_r9 = (TextView)dialog.findViewById(R.id.b10_r9);
        b10_r10 = (TextView)dialog.findViewById(R.id.b10_r10);

        f1_r11 = (TextView)dialog.findViewById(R.id.f1_r11);
        f1_r12 = (TextView)dialog.findViewById(R.id.f1_r12);
        f1_r13 = (TextView)dialog.findViewById(R.id.f1_r13);
        f1_r14 = (TextView)dialog.findViewById(R.id.f1_r14);
        f1_r15 = (TextView)dialog.findViewById(R.id.f1_r15);
        f1_r16 = (TextView)dialog.findViewById(R.id.f1_r16);
        f1_r17 = (TextView)dialog.findViewById(R.id.f1_r17);
        f1_r18 = (TextView)dialog.findViewById(R.id.f1_r18);


        b1_r11 = (TextView)dialog.findViewById(R.id.b1_r11);
        b1_r12 = (TextView)dialog.findViewById(R.id.b1_r12);
        b1_r13 = (TextView)dialog.findViewById(R.id.b1_r13);
        b1_r14 = (TextView)dialog.findViewById(R.id.b1_r14);
        b1_r15 = (TextView)dialog.findViewById(R.id.b1_r15);
        b1_r16 = (TextView)dialog.findViewById(R.id.b1_r16);
        b1_r17 = (TextView)dialog.findViewById(R.id.b1_r17);
        b1_r18 = (TextView)dialog.findViewById(R.id.b1_r18);


        f2_r11 = (TextView)dialog.findViewById(R.id.f2_r11);
        f2_r12 = (TextView)dialog.findViewById(R.id.f2_r12);
        f2_r13 = (TextView)dialog.findViewById(R.id.f2_r13);
        f2_r14 = (TextView)dialog.findViewById(R.id.f2_r14);
        f2_r15 = (TextView)dialog.findViewById(R.id.f2_r15);
        f2_r16 = (TextView)dialog.findViewById(R.id.f2_r16);
        f2_r17 = (TextView)dialog.findViewById(R.id.f2_r17);
        f2_r18 = (TextView)dialog.findViewById(R.id.f2_r18);

        b2_r11 = (TextView)dialog.findViewById(R.id.b2_r11);
        b2_r12 = (TextView)dialog.findViewById(R.id.b2_r12);
        b2_r13 = (TextView)dialog.findViewById(R.id.b2_r13);
        b2_r14 = (TextView)dialog.findViewById(R.id.b2_r14);
        b2_r15 = (TextView)dialog.findViewById(R.id.b2_r15);
        b2_r16 = (TextView)dialog.findViewById(R.id.b2_r16);
        b2_r17 = (TextView)dialog.findViewById(R.id.b2_r17);
        b2_r18 = (TextView)dialog.findViewById(R.id.b2_r18);


        f3_r11 = (TextView)dialog.findViewById(R.id.f3_r11);
        f3_r12 = (TextView)dialog.findViewById(R.id.f3_r12);
        f3_r13 = (TextView)dialog.findViewById(R.id.f3_r13);
        f3_r14 = (TextView)dialog.findViewById(R.id.f3_r14);
        f3_r15 = (TextView)dialog.findViewById(R.id.f3_r15);
        f3_r16 = (TextView)dialog.findViewById(R.id.f3_r16);
        f3_r17 = (TextView)dialog.findViewById(R.id.f3_r17);
        f3_r18 = (TextView)dialog.findViewById(R.id.f3_r18);

        b3_r11 = (TextView)dialog.findViewById(R.id.b3_r11);
        b3_r12 = (TextView)dialog.findViewById(R.id.b3_r12);
        b3_r13 = (TextView)dialog.findViewById(R.id.b3_r13);
        b3_r14 = (TextView)dialog.findViewById(R.id.b3_r14);
        b3_r15 = (TextView)dialog.findViewById(R.id.b3_r15);
        b3_r16 = (TextView)dialog.findViewById(R.id.b3_r16);
        b3_r17 = (TextView)dialog.findViewById(R.id.b3_r17);
        b3_r18 = (TextView)dialog.findViewById(R.id.b3_r18);


        f4_r11 = (TextView)dialog.findViewById(R.id.f4_r11);
        f4_r12 = (TextView)dialog.findViewById(R.id.f4_r12);
        f4_r13 = (TextView)dialog.findViewById(R.id.f4_r13);
        f4_r14 = (TextView)dialog.findViewById(R.id.f4_r14);
        f4_r15 = (TextView)dialog.findViewById(R.id.f4_r15);
        f4_r16 = (TextView)dialog.findViewById(R.id.f4_r16);
        f4_r17 = (TextView)dialog.findViewById(R.id.f4_r17);
        f4_r18 = (TextView)dialog.findViewById(R.id.f4_r18);

        b4_r11 = (TextView)dialog.findViewById(R.id.b4_r11);
        b4_r12 = (TextView)dialog.findViewById(R.id.b4_r12);
        b4_r13 = (TextView)dialog.findViewById(R.id.b4_r13);
        b4_r14 = (TextView)dialog.findViewById(R.id.b4_r14);
        b4_r15 = (TextView)dialog.findViewById(R.id.b4_r15);
        b4_r16 = (TextView)dialog.findViewById(R.id.b4_r16);
        b4_r17 = (TextView)dialog.findViewById(R.id.b4_r17);
        b4_r18 = (TextView)dialog.findViewById(R.id.b4_r18);


        f5_r11 = (TextView)dialog.findViewById(R.id.f5_r11);
        f5_r12 = (TextView)dialog.findViewById(R.id.f5_r12);
        f5_r13 = (TextView)dialog.findViewById(R.id.f5_r13);
        f5_r14 = (TextView)dialog.findViewById(R.id.f5_r14);
        f5_r15 = (TextView)dialog.findViewById(R.id.f5_r15);
        f5_r16 = (TextView)dialog.findViewById(R.id.f5_r16);
        f5_r17 = (TextView)dialog.findViewById(R.id.f5_r17);
        f5_r18 = (TextView)dialog.findViewById(R.id.f5_r18);

        b5_r11 = (TextView)dialog.findViewById(R.id.b5_r11);
        b5_r12 = (TextView)dialog.findViewById(R.id.b5_r12);
        b5_r13 = (TextView)dialog.findViewById(R.id.b5_r13);
        b5_r14 = (TextView)dialog.findViewById(R.id.b5_r14);
        b5_r15 = (TextView)dialog.findViewById(R.id.b5_r15);
        b5_r16 = (TextView)dialog.findViewById(R.id.b5_r16);
        b5_r17 = (TextView)dialog.findViewById(R.id.b5_r17);
        b5_r18 = (TextView)dialog.findViewById(R.id.b5_r18);

        f6_r11 = (TextView)dialog.findViewById(R.id.f6_r11);
        f6_r12 = (TextView)dialog.findViewById(R.id.f6_r12);
        f6_r13 = (TextView)dialog.findViewById(R.id.f6_r13);
        f6_r14 = (TextView)dialog.findViewById(R.id.f6_r14);
        f6_r15 = (TextView)dialog.findViewById(R.id.f6_r15);
        f6_r16 = (TextView)dialog.findViewById(R.id.f6_r16);
        f6_r17 = (TextView)dialog.findViewById(R.id.f6_r17);
        f6_r18 = (TextView)dialog.findViewById(R.id.f6_r18);

        b6_r11 = (TextView)dialog.findViewById(R.id.b6_r11);
        b6_r12 = (TextView)dialog.findViewById(R.id.b6_r12);
        b6_r13 = (TextView)dialog.findViewById(R.id.b6_r13);
        b6_r14 = (TextView)dialog.findViewById(R.id.b6_r14);
        b6_r15 = (TextView)dialog.findViewById(R.id.b6_r15);
        b6_r16 = (TextView)dialog.findViewById(R.id.b6_r16);
        b6_r17 = (TextView)dialog.findViewById(R.id.b6_r17);
        b6_r18 = (TextView)dialog.findViewById(R.id.b6_r18);

        f7_r11 = (TextView)dialog.findViewById(R.id.f7_r11);
        f7_r12 = (TextView)dialog.findViewById(R.id.f7_r12);
        f7_r13 = (TextView)dialog.findViewById(R.id.f7_r13);
        f7_r14 = (TextView)dialog.findViewById(R.id.f7_r14);
        f7_r15 = (TextView)dialog.findViewById(R.id.f7_r15);
        f7_r16 = (TextView)dialog.findViewById(R.id.f7_r16);
        f7_r17 = (TextView)dialog.findViewById(R.id.f7_r17);
        f7_r18 = (TextView)dialog.findViewById(R.id.f7_r18);

        b7_r11 = (TextView)dialog.findViewById(R.id.b7_r11);
        b7_r12 = (TextView)dialog.findViewById(R.id.b7_r12);
        b7_r13 = (TextView)dialog.findViewById(R.id.b7_r13);
        b7_r14 = (TextView)dialog.findViewById(R.id.b7_r14);
        b7_r15 = (TextView)dialog.findViewById(R.id.b7_r15);
        b7_r16 = (TextView)dialog.findViewById(R.id.b7_r16);
        b7_r17 = (TextView)dialog.findViewById(R.id.b7_r17);
        b7_r18 = (TextView)dialog.findViewById(R.id.b7_r18);

        f8_r11 = (TextView)dialog.findViewById(R.id.f8_r11);
        f8_r12 = (TextView)dialog.findViewById(R.id.f8_r12);
        f8_r13 = (TextView)dialog.findViewById(R.id.f8_r13);
        f8_r14 = (TextView)dialog.findViewById(R.id.f8_r14);
        f8_r15 = (TextView)dialog.findViewById(R.id.f8_r15);
        f8_r16 = (TextView)dialog.findViewById(R.id.f8_r16);
        f8_r17 = (TextView)dialog.findViewById(R.id.f8_r17);
        f8_r18 = (TextView)dialog.findViewById(R.id.f8_r18);

        b8_r11 = (TextView)dialog.findViewById(R.id.b8_r11);
        b8_r12 = (TextView)dialog.findViewById(R.id.b8_r12);
        b8_r13 = (TextView)dialog.findViewById(R.id.b8_r13);
        b8_r14 = (TextView)dialog.findViewById(R.id.b8_r14);
        b8_r15 = (TextView)dialog.findViewById(R.id.b8_r15);
        b8_r16 = (TextView)dialog.findViewById(R.id.b8_r16);
        b8_r17 = (TextView)dialog.findViewById(R.id.b8_r17);
        b8_r18 = (TextView)dialog.findViewById(R.id.b8_r18);

        f9_r11 = (TextView)dialog.findViewById(R.id.f9_r11);
        f9_r12 = (TextView)dialog.findViewById(R.id.f9_r12);
        f9_r13 = (TextView)dialog.findViewById(R.id.f9_r13);
        f9_r14 = (TextView)dialog.findViewById(R.id.f9_r14);
        f9_r15 = (TextView)dialog.findViewById(R.id.f9_r15);
        f9_r16 = (TextView)dialog.findViewById(R.id.f9_r16);
        f9_r17 = (TextView)dialog.findViewById(R.id.f9_r17);
        f9_r18 = (TextView)dialog.findViewById(R.id.f9_r18);

        b9_r11 = (TextView)dialog.findViewById(R.id.b9_r11);
        b9_r12 = (TextView)dialog.findViewById(R.id.b9_r12);
        b9_r13 = (TextView)dialog.findViewById(R.id.b9_r13);
        b9_r14 = (TextView)dialog.findViewById(R.id.b9_r14);
        b9_r15 = (TextView)dialog.findViewById(R.id.b9_r15);
        b9_r16 = (TextView)dialog.findViewById(R.id.b9_r16);
        b9_r17 = (TextView)dialog.findViewById(R.id.b9_r17);
        b9_r18 = (TextView)dialog.findViewById(R.id.b9_r18);


        f10_r11 = (TextView)dialog.findViewById(R.id.f10_r11);
        f10_r12 = (TextView)dialog.findViewById(R.id.f10_r12);
        f10_r13 = (TextView)dialog.findViewById(R.id.f10_r13);
        f10_r14 = (TextView)dialog.findViewById(R.id.f10_r14);
        f10_r15 = (TextView)dialog.findViewById(R.id.f10_r15);
        f10_r16 = (TextView)dialog.findViewById(R.id.f10_r16);
        f10_r17 = (TextView)dialog.findViewById(R.id.f10_r17);
        f10_r18 = (TextView)dialog.findViewById(R.id.f10_r18);

        b10_r11 = (TextView)dialog.findViewById(R.id.b10_r11);
        b10_r12 = (TextView)dialog.findViewById(R.id.b10_r12);
        b10_r13 = (TextView)dialog.findViewById(R.id.b10_r13);
        b10_r14 = (TextView)dialog.findViewById(R.id.b10_r14);
        b10_r15 = (TextView)dialog.findViewById(R.id.b10_r15);
        b10_r16 = (TextView)dialog.findViewById(R.id.b10_r16);
        b10_r17 = (TextView)dialog.findViewById(R.id.b10_r17);
        b10_r18 = (TextView)dialog.findViewById(R.id.b10_r18);

       // eventID=((AddScoreNew) this).geteventID();

        getExpandAuto(eventID,dialog);

        // dialog.show();
        finishdialogfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    public void getExpandAuto(String Event_Id, final Dialog dialog) {
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

                expandAutopressResponse(response);
                dialog.show();
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


    public void expandAutopressResponse(JSONObject response) {
        try {




            JSONObject output = response.getJSONObject("output");
            String status = output.getString("status");
            if (status.equalsIgnoreCase("1")) {

                JSONArray data = output.getJSONArray("data");
                for (int i = 0 ; i < data.length(); i++){

                    if (i==0) {

                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a1.setText(holeNumber);
                        r1.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r1.setText(" "+score);
                                f1_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r1.setText(" "+score);
                                f2_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r1.setText(" "+score);
                                f3_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r1.setText(" "+score);
                                f4_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r1.setText(" "+score);
                                f5_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r1.setText(" "+score);
                                f6_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r1.setText(" "+score);
                                f7_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r1.setText(" "+score);
                                f8_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r1.setText(" "+score);
                                f9_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r1.setText(" "+score);
                                f10_r1.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and1.setVisibility(View.VISIBLE);
                            back1_hole1.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r1.setText(" "+score);
                                b1_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r1.setText(" "+score);
                                b2_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r1.setText(" "+score);
                                b3_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r1.setText(" "+score);
                                b4_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r1.setText(" "+score);
                                b5_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r1.setText(" "+score);
                                b6_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r1.setText(" "+score);
                                b7_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r1.setText(" "+score);
                                b8_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r1.setText(" "+score);
                                b9_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r1.setText(" "+score);
                                b10_r1.setTextColor(Color.parseColor(color));
                            } else {

                            }


                        }
                    }else if (i==1){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a2.setText(holeNumber);
                        r2.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r2.setText(" "+score);
                                f1_r2.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r2.setText(" "+score);
                                f2_r2.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r2.setText(" "+score);
                                f3_r2.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r2.setText(" "+score);
                                f4_r2.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r2.setText(" "+score);
                                f5_r2.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r2.setText(" "+score);
                                f6_r2.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r2.setText(" "+score);
                                f7_r2.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r2.setText(" "+score);
                                f8_r2.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r2.setText(" "+score);
                                f9_r2.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r2.setText(" "+score);
                                f10_r2.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and2.setVisibility(View.VISIBLE);
                            back1_hole2.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r2.setText(" "+score);
                                b1_r2.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r2.setText(" "+score);
                                b2_r2.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r2.setText(" "+score);
                                b3_r2.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r2.setText(" "+score);
                                b4_r2.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r2.setText(" "+score);
                                b5_r2.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r2.setText(" "+score);
                                b6_r2.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r2.setText(" "+score);
                                b7_r2.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r2.setText(" "+score);
                                b8_r2.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r2.setText(" "+score);
                                b9_r2.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r2.setText(" "+score);
                                b10_r2.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==2){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a3.setText(holeNumber);
                        r3.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r3.setText(" "+score);
                                f1_r3.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r3.setText(" "+score);
                                f2_r3.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r3.setText(" "+score);
                                f3_r3.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r3.setText(" "+score);
                                f4_r3.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r3.setText(" "+score);
                                f5_r3.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r3.setText(" "+score);
                                f6_r3.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r3.setText(" "+score);
                                f7_r3.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r3.setText(" "+score);
                                f8_r3.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r3.setText(" "+score);
                                f9_r3.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r3.setText(" "+score);
                                f10_r3.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and3.setVisibility(View.VISIBLE);
                            back1_hole3.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r3.setText(" "+score);
                                b1_r3.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r3.setText(" "+score);
                                b2_r3.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r3.setText(" "+score);
                                b3_r3.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r3.setText(" "+score);
                                b4_r3.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r3.setText(" "+score);
                                b5_r3.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r3.setText(" "+score);
                                b6_r3.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r3.setText(" "+score);
                                b7_r3.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r3.setText(" "+score);
                                b8_r3.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r3.setText(" "+score);
                                b9_r3.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r3.setText(" "+score);
                                b10_r3.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==3){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a4.setText(holeNumber);
                        r4.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r4.setText(" "+score);
                                f1_r4.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r4.setText(" "+score);
                                f2_r4.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r4.setText(" "+score);
                                f3_r4.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r4.setText(" "+score);
                                f4_r4.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r4.setText(" "+score);
                                f5_r4.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r4.setText(" "+score);
                                f6_r4.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r4.setText(" "+score);
                                f7_r4.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r4.setText(score);
                                f8_r4.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r4.setText(" "+score);
                                f9_r4.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r4.setText(" "+score);
                                f10_r4.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and4.setVisibility(View.VISIBLE);
                            back1_hole4.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r4.setText(" "+score);
                                b1_r4.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r4.setText(" "+score);
                                b2_r4.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r4.setText(" "+score);
                                b3_r4.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r4.setText(" "+score);
                                b4_r4.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r4.setText(" "+score);
                                b5_r4.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r4.setText(" "+score);
                                b6_r4.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r4.setText(" "+score);
                                b7_r4.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r4.setText(" "+score);
                                b8_r4.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r4.setText(" "+score);
                                b9_r4.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r4.setText(" "+score);
                                b10_r4.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==4){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a5.setText(holeNumber);
                        r5.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r5.setText(" "+score);
                                f1_r5.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r5.setText(" "+score);
                                f2_r5.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r5.setText(" "+score);
                                f3_r5.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r5.setText(" "+score);
                                f4_r5.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r5.setText(" "+score);
                                f5_r5.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r5.setText(" "+score);
                                f6_r5.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r5.setText(" "+score);
                                f7_r5.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r5.setText(" "+score);
                                f8_r5.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r5.setText(" "+score);
                                f9_r5.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r5.setText(" "+score);
                                f10_r5.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and5.setVisibility(View.VISIBLE);
                            back1_hole5.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r5.setText(" "+score);
                                b1_r5.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r5.setText(" "+score);
                                b2_r5.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r5.setText(" "+score);
                                b3_r5.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r5.setText(" "+score);
                                b4_r5.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r5.setText(" "+score);
                                b5_r5.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r5.setText(" "+score);
                                b6_r5.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r5.setText(" "+score);
                                b7_r5.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r5.setText(" "+score);
                                b8_r5.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r5.setText(" "+score);
                                b9_r5.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r5.setText(" "+score);
                                b10_r5.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==5){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a6.setText(holeNumber);
                        r6.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r6.setText(" "+score);
                                f1_r6.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r6.setText(" "+score);
                                f2_r6.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r6.setText(" "+score);
                                f3_r6.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r6.setText(" "+score);
                                f4_r6.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r6.setText(" "+score);
                                f5_r6.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r6.setText(" "+score);
                                f6_r6.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r6.setText(" "+score);
                                f7_r6.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r6.setText(" "+score);
                                f8_r6.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r6.setText(" "+score);
                                f9_r6.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r6.setText(" "+score);
                                f10_r6.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and6.setVisibility(View.VISIBLE);
                            back1_hole6.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r6.setText(" "+score);
                                b1_r6.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r6.setText(" "+score);
                                b2_r6.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r6.setText(" "+score);
                                b3_r6.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r6.setText(" "+score);
                                b4_r6.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r6.setText(" "+score);
                                b5_r6.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r6.setText(" "+score);
                                b6_r6.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r6.setText(" "+score);
                                b7_r6.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r6.setText(" "+score);
                                b8_r6.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r6.setText(" "+score);
                                b9_r6.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r6.setText(" "+score);
                                b10_r6.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==6){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a7.setText(holeNumber);
                        r7.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r7.setText(" "+score);
                                f1_r7.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r7.setText(" "+score);
                                f2_r7.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r7.setText(" "+score);
                                f3_r7.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r7.setText(" "+score);
                                f4_r7.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r7.setText(" "+score);
                                f5_r7.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r7.setText(" "+score);
                                f6_r7.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r7.setText(" "+score);
                                f7_r7.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r7.setText(" "+score);
                                f8_r7.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r7.setText(" "+score);
                                f9_r7.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r7.setText(" "+score);
                                f10_r7.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and7.setVisibility(View.VISIBLE);
                            back1_hole7.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r7.setText(" "+score);
                                b1_r7.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r7.setText(" "+score);
                                b2_r7.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r7.setText(" "+score);
                                b3_r7.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r7.setText(" "+score);
                                b4_r7.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r7.setText(" "+score);
                                b5_r7.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r7.setText(" "+score);
                                b6_r7.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r7.setText(" "+score);
                                b7_r7.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r7.setText(" "+score);
                                b8_r7.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r7.setText(" "+score);
                                b9_r7.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r7.setText(" "+score);
                                b10_r7.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==7){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a8.setText(holeNumber);
                        r8.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r8.setText(" "+score);
                                f1_r8.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r8.setText(" "+score);
                                f2_r8.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r8.setText(" "+score);
                                f3_r8.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r8.setText(" "+score);
                                f4_r8.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r8.setText(" "+score);
                                f5_r8.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r8.setText(" "+score);
                                f6_r8.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r8.setText(" "+score);
                                f7_r8.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r8.setText(" "+score);
                                f8_r8.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r8.setText(" "+score);
                                f9_r8.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r8.setText(" "+score);
                                f10_r8.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and8.setVisibility(View.VISIBLE);
                            back1_hole8.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r8.setText(" "+score);
                                b1_r8.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r8.setText(" "+score);
                                b2_r8.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r8.setText(" "+score);
                                b3_r8.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r8.setText(" "+score);
                                b4_r8.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r8.setText(" "+score);
                                b5_r8.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r8.setText(" "+score);
                                b6_r8.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r8.setText(" "+score);
                                b7_r8.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r8.setText(" "+score);
                                b8_r8.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r8.setText(" "+score);
                                b9_r8.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r8.setText(" "+score);
                                b10_r8.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==8){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a9.setText(holeNumber);
                        r9.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r9.setText(" "+score);
                                f1_r9.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r9.setText(" "+score);
                                f2_r9.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r9.setText(" "+score);
                                f3_r9.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r9.setText(" "+score);
                                f4_r9.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r9.setText(" "+score);
                                f5_r9.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r9.setText(" "+score);
                                f6_r9.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r9.setText(" "+score);
                                f7_r9.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r9.setText(" "+score);
                                f8_r9.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r9.setText(" "+score);
                                f9_r9.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r9.setText(" "+score);
                                f10_r9.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and9.setVisibility(View.VISIBLE);
                            back1_hole9.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r9.setText(" "+score);
                                b1_r9.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r9.setText(" "+score);
                                b2_r9.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r9.setText(" "+score);
                                b3_r9.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r9.setText(" "+score);
                                b4_r9.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r9.setText(" "+score);
                                b5_r9.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r9.setText(" "+score);
                                b6_r9.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r9.setText(" "+score);
                                b7_r9.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r9.setText(" "+score);
                                b8_r9.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r9.setText(" "+score);
                                b9_r9.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r9.setText(" "+score);
                                b10_r9.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==9){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a10.setText(holeNumber);
                        r10.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r10.setText(" "+score);
                                f1_r10.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r10.setText(" "+score);
                                f2_r10.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r10.setText(" "+score);
                                f3_r10.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r10.setText(" "+score);
                                f4_r10.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r10.setText(" "+score);
                                f5_r10.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r10.setText(" "+score);
                                f6_r10.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r10.setText(" "+score);
                                f7_r10.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r10.setText(" "+score);
                                f8_r10.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r10.setText(" "+score);
                                f9_r10.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r10.setText(" "+score);
                                f10_r10.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){


                            and10.setVisibility(View.VISIBLE);
                            back1_hole10.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r10.setText(" "+score);
                                b1_r10.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r10.setText(" "+score);
                                b2_r10.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r10.setText(" "+score);
                                b3_r10.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r10.setText(" "+score);
                                b4_r10.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r10.setText(" "+score);
                                b5_r10.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r10.setText(" "+score);
                                b6_r10.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r10.setText(" "+score);
                                b7_r10.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r10.setText(" "+score);
                                b8_r10.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r10.setText(" "+score);
                                b9_r10.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r10.setText(" "+score);
                                b10_r10.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==10){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a11.setText(holeNumber);
                        r11.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r11.setText(" "+score);
                                f1_r11.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r11.setText(" "+score);
                                f2_r11.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r11.setText(" "+score);
                                f3_r11.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r11.setText(" "+score);
                                f4_r11.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r11.setText(" "+score);
                                f5_r11.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r11.setText(" "+score);
                                f6_r11.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r11.setText(" "+score);
                                f7_r11.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r11.setText(" "+score);
                                f8_r11.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r11.setText(" "+score);
                                f9_r11.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r11.setText(" "+score);
                                f10_r11.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and11.setVisibility(View.VISIBLE);
                            back1_hole11.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r11.setText(" "+score);
                                b1_r11.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r11.setText(" "+score);
                                b2_r11.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r11.setText(" "+score);
                                b3_r11.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r11.setText(" "+score);
                                b4_r11.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r11.setText(" "+score);
                                b5_r11.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r11.setText(" "+score);
                                b6_r11.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r11.setText(" "+score);
                                b7_r11.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r11.setText(" "+score);
                                b8_r11.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r11.setText(" "+score);
                                b9_r11.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r11.setText(" "+score);
                                b10_r11.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==11){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a12.setText(holeNumber);
                        r12.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r12.setText(" "+score);
                                f1_r12.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r12.setText(" "+score);
                                f2_r12.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r12.setText(" "+score);
                                f3_r12.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r12.setText(" "+score);
                                f4_r12.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r12.setText(" "+score);
                                f5_r12.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r12.setText(" "+score);
                                f6_r12.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r12.setText(" "+score);
                                f7_r12.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r12.setText(" "+score);
                                f8_r12.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r12.setText(" "+score);
                                f9_r12.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r12.setText(" "+score);
                                f10_r12.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and12.setVisibility(View.VISIBLE);
                            back1_hole12.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r12.setText(" "+score);
                                b1_r12.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r12.setText(" "+score);
                                b2_r12.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r12.setText(" "+score);
                                b3_r12.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r12.setText(" "+score);
                                b4_r12.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r12.setText(" "+score);
                                b5_r12.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r12.setText(" "+score);
                                b6_r12.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r12.setText(" "+score);
                                b7_r12.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r12.setText(" "+score);
                                b8_r12.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r12.setText(" "+score);
                                b9_r12.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r12.setText(" "+score);
                                b10_r12.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==12){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a13.setText(holeNumber);
                        r13.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r13.setText(" "+score);
                                f1_r13.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r13.setText(" "+score);
                                f2_r13.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r13.setText(" "+score);
                                f3_r13.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r13.setText(" "+score);
                                f4_r13.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r13.setText(" "+score);
                                f5_r13.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r13.setText(" "+score);
                                f6_r13.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r13.setText(" "+score);
                                f7_r13.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r13.setText(" "+score);
                                f8_r13.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r13.setText(" "+score);
                                f9_r13.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r13.setText(" "+score);
                                f10_r13.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and13.setVisibility(View.VISIBLE);
                            back1_hole13.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r13.setText(" "+score);
                                b1_r13.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r13.setText(" "+score);
                                b2_r13.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r13.setText(" "+score);
                                b3_r13.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r13.setText(" "+score);
                                b4_r13.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r13.setText(" "+score);
                                b5_r13.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r13.setText(" "+score);
                                b6_r13.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r13.setText(" "+score);
                                b7_r13.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r13.setText(" "+score);
                                b8_r13.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r13.setText(" "+score);
                                b9_r13.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r13.setText(" "+score);
                                b10_r13.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==13){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a14.setText(holeNumber);
                        r14.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r14.setText(" "+score);
                                f1_r14.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r14.setText(" "+score);
                                f2_r14.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r14.setText(" "+score);
                                f3_r14.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r14.setText(" "+score);
                                f4_r14.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r14.setText(" "+score);
                                f5_r14.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r14.setText(" "+score);
                                f6_r14.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r14.setText(" "+score);
                                f7_r14.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r14.setText(" "+score);
                                f8_r14.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r14.setText(" "+score);
                                f9_r14.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r14.setText(" "+score);
                                f10_r14.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and14.setVisibility(View.VISIBLE);
                            back1_hole14.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r14.setText(" "+score);
                                b1_r14.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r14.setText(" "+score);
                                b2_r14.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r14.setText(" "+score);
                                b3_r14.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r14.setText(" "+score);
                                b4_r14.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r14.setText(" "+score);
                                b5_r14.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r14.setText(" "+score);
                                b6_r14.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r14.setText(" "+score);
                                b7_r14.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r14.setText(" "+score);
                                b8_r14.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r14.setText(" "+score);
                                b9_r14.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r14.setText(" "+score);
                                b10_r14.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==14){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a15.setText(holeNumber);
                        r15.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r15.setText(" "+score);
                                f1_r15.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r15.setText(" "+score);
                                f2_r15.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r15.setText(" "+score);
                                f3_r15.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r15.setText(" "+score);
                                f4_r15.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r15.setText(" "+score);
                                f5_r15.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r15.setText(" "+score);
                                f6_r15.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r15.setText(" "+score);
                                f7_r15.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r15.setText(" "+score);
                                f8_r15.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r15.setText(" "+score);
                                f9_r15.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r15.setText(" "+score);
                                f10_r15.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and15.setVisibility(View.VISIBLE);
                            back1_hole15.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r15.setText(" "+score);
                                b1_r15.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r15.setText(" "+score);
                                b2_r15.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r15.setText(" "+score);
                                b3_r15.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r15.setText(" "+score);
                                b4_r15.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r15.setText(" "+score);
                                b5_r15.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r15.setText(" "+score);
                                b6_r15.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r15.setText(" "+score);
                                b7_r15.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r15.setText(" "+score);
                                b8_r15.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r15.setText(" "+score);
                                b9_r15.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r15.setText(" "+score);
                                b10_r15.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==15){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a16.setText(holeNumber);
                        r16.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r16.setText(" "+score);
                                f1_r16.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r16.setText(" "+score);
                                f2_r16.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r16.setText(" "+score);
                                f3_r16.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r16.setText(" "+score);
                                f4_r16.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r16.setText(" "+score);
                                f5_r16.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r16.setText(" "+score);
                                f6_r16.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r16.setText(" "+score);
                                f7_r16.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r16.setText(" "+score);
                                f8_r16.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r16.setText(" "+score);
                                f9_r16.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r16.setText(" "+score);
                                f10_r16.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and16.setVisibility(View.VISIBLE);
                            back1_hole16.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r16.setText(" "+score);
                                b1_r16.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r16.setText(" "+score);
                                b2_r16.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r16.setText(" "+score);
                                b3_r16.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r16.setText(" "+score);
                                b4_r16.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r16.setText(" "+score);
                                b5_r16.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r16.setText(" "+score);
                                b6_r16.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r16.setText(" "+score);
                                b7_r16.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r16.setText(" "+score);
                                b8_r16.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r16.setText(" "+score);
                                b9_r16.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r16.setText(" "+score);
                                b10_r16.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==16){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a17.setText(holeNumber);
                        r17.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r17.setText(" "+score);
                                f1_r17.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r17.setText(" "+score);
                                f2_r17.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r17.setText(" "+score);
                                f3_r17.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r17.setText(" "+score);
                                f4_r17.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r17.setText(" "+score);
                                f5_r17.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r17.setText(" "+score);
                                f6_r17.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r17.setText(" "+score);
                                f7_r17.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r17.setText(" "+score);
                                f8_r17.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r17.setText(" "+score);
                                f9_r17.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r17.setText(" "+score);
                                f10_r17.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and17.setVisibility(View.VISIBLE);
                            back1_hole17.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r17.setText(" "+score);
                                b1_r17.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r17.setText(" "+score);
                                b2_r17.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r17.setText(" "+score);
                                b3_r17.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r17.setText(" "+score);
                                b4_r17.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r17.setText(" "+score);
                                b5_r17.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r17.setText(" "+score);
                                b6_r17.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r17.setText(" "+score);
                                b7_r17.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r17.setText(" "+score);
                                b8_r17.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r17.setText(" "+score);
                                b9_r17.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r17.setText(" "+score);
                                b10_r17.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==17){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a18.setText(holeNumber);
                        r18.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r18.setText(" "+score);
                                f1_r18.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r18.setText(" "+score);
                                f2_r18.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r18.setText(" "+score);
                                f3_r18.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r18.setText(" "+score);
                                f4_r18.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r18.setText(" "+score);
                                f5_r18.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r18.setText(" "+score);
                                f6_r18.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r18.setText(" "+score);
                                f7_r18.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r18.setText(" "+score);
                                f8_r18.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r18.setText(" "+score);
                                f9_r18.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r18.setText(" "+score);
                                f10_r18.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and18.setVisibility(View.VISIBLE);
                            back1_hole18.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r18.setText(" "+score);
                                b1_r18.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r18.setText(" "+score);
                                b2_r18.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r18.setText(" "+score);
                                b3_r18.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r18.setText(" "+score);
                                b4_r18.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r18.setText(" "+score);
                                b5_r18.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r18.setText(" "+score);
                                b6_r18.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r18.setText(" "+score);
                                b7_r18.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r18.setText(" "+score);
                                b8_r18.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r18.setText(" "+score);
                                b9_r18.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r18.setText(" "+score);
                                b10_r18.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }
                }

            }else {

            }
        } catch (JSONException je)

        {
            je.printStackTrace();
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
                 //   Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


}