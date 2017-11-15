package com.putt2gether.putt.addScore;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
import com.putt2gether.putt.delegates.Delegates;
import com.putt2gether.putt.score_card.NewGameScoreCard;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.view.LatoTextView;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 27/12/2016.
 */
public class AddScoreNew extends AppCompatActivity {


    RecyclerView dd21_RecyclerView;
    private RecyclerView.LayoutManager dd21_LayoutManager;
    LinearLayout r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18;

    TextView hole_a1, hole_a2, hole_a3, hole_a4, hole_a5, hole_a6, hole_a7, hole_a8, hole_a9, hole_a10, hole_a11, hole_a12, hole_a13, hole_a14, hole_a15, hole_a16, hole_a17, hole_a18;

    TextView f1_r1, f1_r2, f1_r3, f1_r4, f1_r5, f1_r6, f1_r7, f1_r8, f1_r9, f1_r10;
    TextView f2_r1, f2_r2, f2_r3, f2_r4, f2_r5, f2_r6, f2_r7, f2_r8, f2_r9, f2_r10;
    TextView f3_r1, f3_r2, f3_r3, f3_r4, f3_r5, f3_r6, f3_r7, f3_r8, f3_r9, f3_r10;
    TextView f4_r1, f4_r2, f4_r3, f4_r4, f4_r5, f4_r6, f4_r7, f4_r8, f4_r9, f4_r10;
    TextView f5_r1, f5_r2, f5_r3, f5_r4, f5_r5, f5_r6, f5_r7, f5_r8, f5_r9, f5_r10;
    TextView f6_r1, f6_r2, f6_r3, f6_r4, f6_r5, f6_r6, f6_r7, f6_r8, f6_r9, f6_r10;
    TextView f7_r1, f7_r2, f7_r3, f7_r4, f7_r5, f7_r6, f7_r7, f7_r8, f7_r9, f7_r10;
    TextView f8_r1, f8_r2, f8_r3, f8_r4, f8_r5, f8_r6, f8_r7, f8_r8, f8_r9, f8_r10;
    TextView f9_r1, f9_r2, f9_r3, f9_r4, f9_r5, f9_r6, f9_r7, f9_r8, f9_r9, f9_r10;
    TextView f10_r1, f10_r2, f10_r3, f10_r4, f10_r5, f10_r6, f10_r7, f10_r8, f10_r9, f10_r10;

    TextView b1_r1, b1_r2, b1_r3, b1_r4, b1_r5, b1_r6, b1_r7, b1_r8, b1_r9, b1_r10;
    TextView b2_r1, b2_r2, b2_r3, b2_r4, b2_r5, b2_r6, b2_r7, b2_r8, b2_r9, b2_r10;
    TextView b3_r1, b3_r2, b3_r3, b3_r4, b3_r5, b3_r6, b3_r7, b3_r8, b3_r9, b3_r10;
    TextView b4_r1, b4_r2, b4_r3, b4_r4, b4_r5, b4_r6, b4_r7, b4_r8, b4_r9, b4_r10;
    TextView b5_r1, b5_r2, b5_r3, b5_r4, b5_r5, b5_r6, b5_r7, b5_r8, b5_r9, b5_r10;
    TextView b6_r1, b6_r2, b6_r3, b6_r4, b6_r5, b6_r6, b6_r7, b6_r8, b6_r9, b6_r10;
    TextView b7_r1, b7_r2, b7_r3, b7_r4, b7_r5, b7_r6, b7_r7, b7_r8, b7_r9, b7_r10;
    TextView b8_r1, b8_r2, b8_r3, b8_r4, b8_r5, b8_r6, b8_r7, b8_r8, b8_r9, b8_r10;
    TextView b9_r1, b9_r2, b9_r3, b9_r4, b9_r5, b9_r6, b9_r7, b9_r8, b9_r9, b9_r10;
    TextView b10_r1, b10_r2, b10_r3, b10_r4, b10_r5, b10_r6, b10_r7, b10_r8, b10_r9, b10_r10;

    TextView f1_r11, f1_r12, f1_r13, f1_r14, f1_r15, f1_r16, f1_r17, f1_r18, f1_r19, f1_r20;
    TextView f2_r11, f2_r12, f2_r13, f2_r14, f2_r15, f2_r16, f2_r17, f2_r18, f2_r19, f2_r20;
    TextView f3_r11, f3_r12, f3_r13, f3_r14, f3_r15, f3_r16, f3_r17, f3_r18, f3_r19, f3_r20;
    TextView f4_r11, f4_r12, f4_r13, f4_r14, f4_r15, f4_r16, f4_r17, f4_r18, f4_r19, f4_r20;
    TextView f5_r11, f5_r12, f5_r13, f5_r14, f5_r15, f5_r16, f5_r17, f5_r18, f5_r19, f5_r20;
    TextView f6_r11, f6_r12, f6_r13, f6_r14, f6_r15, f6_r16, f6_r17, f6_r18, f6_r19, f6_r20;
    TextView f7_r11, f7_r12, f7_r13, f7_r14, f7_r15, f7_r16, f7_r17, f7_r18, f7_r19, f7_r20;
    TextView f8_r11, f8_r12, f8_r13, f8_r14, f8_r15, f8_r16, f8_r17, f8_r18, f8_r19, f8_r20;
    TextView f9_r11, f9_r12, f9_r13, f9_r14, f9_r15, f9_r16, f9_r17, f9_r18, f9_r19, f9_r20;
    TextView f10_r11, f10_r12, f10_r13, f10_r14, f10_r15, f10_r16, f10_r17, f10_r18, f10_r19, f10_r20;

    TextView b1_r11, b1_r12, b1_r13, b1_r14, b1_r15, b1_r16, b1_r17, b1_r18, b1_r19, b1_r20;
    TextView b2_r11, b2_r12, b2_r13, b2_r14, b2_r15, b2_r16, b2_r17, b2_r18, b2_r19, b2_r20;
    TextView b3_r11, b3_r12, b3_r13, b3_r14, b3_r15, b3_r16, b3_r17, b3_r18, b3_r19, b3_r20;
    TextView b4_r11, b4_r12, b4_r13, b4_r14, b4_r15, b4_r16, b4_r17, b4_r18, b4_r19, b4_r20;
    TextView b5_r11, b5_r12, b5_r13, b5_r14, b5_r15, b5_r16, b5_r17, b5_r18, b5_r19, b5_r20;
    TextView b6_r11, b6_r12, b6_r13, b6_r14, b6_r15, b6_r16, b6_r17, b6_r18, b6_r19, b6_r20;
    TextView b7_r11, b7_r12, b7_r13, b7_r14, b7_r15, b7_r16, b7_r17, b7_r18, b7_r19, b7_r20;
    TextView b8_r11, b8_r12, b8_r13, b8_r14, b8_r15, b8_r16, b8_r17, b8_r18, b8_r19, b8_r20;
    TextView b9_r11, b9_r12, b9_r13, b9_r14, b9_r15, b9_r16, b9_r17, b9_r18, b9_r19, b9_r20;
    TextView b10_r11, b10_r12, b10_r13, b10_r14, b10_r15, b10_r16, b10_r17, b10_r18, b10_r19, b10_r20;
    LinearLayout front1_hole1, front1_hole2, front1_hole3, front1_hole4, front1_hole5, front1_hole6, front1_hole7, front1_hole8, front1_hole9, front1_hole10, front1_hole11, front1_hole12, front1_hole13, front1_hole14, front1_hole15, front1_hole16, front1_hole17, front1_hole18;
    LinearLayout back1_hole1, back1_hole2, back1_hole3, back1_hole4, back1_hole5, back1_hole6, back1_hole7, back1_hole8, back1_hole9, back1_hole10, back1_hole11, back1_hole12, back1_hole13, back1_hole14, back1_hole15, back1_hole16, back1_hole17, back1_hole18;
    TextView and1, and2, and3, and4, and5, and6, and7, and8, and9, and10, and11, and12, and13, and14, and15, and16, and17, and18;

    TextView title;
    TextView hole, par;
    String scoreUp;

    int hole_count;

    TextView player1_name, player2_name, player3_name, player4_name;
    TextView handicap1, handicap2, handicap3, handicap4;

    TextView gross_score1, gross_score2, gross_score3, gross_score4;
    RelativeLayout gross_plus1, gross_plus2, gross_plus3, gross_plus4;
    RelativeLayout gross_minus1, gross_minus2, gross_minus3, gross_minus4;

    RelativeLayout putt1_minus, putt2_minus, putt3_minus, putt4_minus;
    RelativeLayout putt1_plus, putt2_plus, putt3_plus, putt4_plus;

    RelativeLayout sand1_minus, sand2_minus, sand3_minus, sand4_minus;
    RelativeLayout sand1_plus, sand2_plus, sand3_plus, sand4_plus;

    //Expand fairway sand putt layout here
    LinearLayout expandLay1, expandLay2, expandLay3, expandLay4;

    int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0;
    RelativeLayout player_lay1, player_lay2, player_lay3, player_lay4;

    TextView sandEditText1, sandEditText2, sandEditText3, sandEditText4;
    TextView puttsnumber1, puttsnumber2, puttsnumber3, puttsnumber4;
    int sandCounter1 = 0, sandCounter2 = 0, sandCounter3 = 0, sandCounter4 = 0;
    int puttCounter1 = 0, puttCounter2 = 0, puttCounter3 = 0, puttCounter4 = 0;
    int puttValidation1, puttValidation2, puttValidation3, puttValidation4;
    int sandValidation1, sandValidation2, sandValidation3, sandValidation4;

    int count_gross_score1, count_gross_score2, count_gross_score3, count_gross_score4;

    ImageView backBTN;

    int holeNO;
    int lastHole;
    String format_id;
    String isSpot;
    String isDelegate;

    String holeNumber;
    String golf_course_id;

    public String parvalue_;

    String holeValidation;
    String totalHoleValidation;

    String event_adminID;

    int totalPlayer = 0;
    String is4PlusGame = null;

    ArrayList<String> tabname = new ArrayList<String>();
    ArrayList<String> tabShortName = new ArrayList<String>();
    ArrayList<String> tabPlayerColor = new ArrayList<String>();

    ArrayList<String> lastholeplayed = new ArrayList<String>();
    ArrayList<String> tabplayer_id = new ArrayList<String>();
    ArrayList<String> tabhandicap = new ArrayList<String>();
    LinearLayout player1Layout, player2Layout, player3Layout, player4Layout;
    ImageView plusIcon;

    float dX;
    float dY;
    int lastAction;

    String isTemp = "0";

    LatoTextView teamA_Exp, player1A_Exp, player2A_Exp;
    LatoTextView teamB_Exp, player1B_Exp, player2B_Exp;
    LatoTextView standings_Expand, hole_Expand;
    String no_of_hole_played;

    String current_winner, winner1, winner2;
    LinearLayout winnerBackgroundLay;
    LatoTextView player1_420score, player2_420score, player3_420score;
    LatoTextView player1_420name, player2_420name, player3_420name;
    LinearLayout layout_340_scorcard, teamtabLayout;

    LinearLayout play1_340_lay, play2_340_lay, play3_340_lay;

    LinearLayout sandLayout1, fairwaysLayout1;
    LinearLayout sandLayout2, fairwaysLayout2;
    LinearLayout sandLayout3, fairwaysLayout3;
    LinearLayout sandLayout4, fairwaysLayout4;

    LinearLayout bottom_back, bottom_next, save_score, leaderboard, mainscorelayout;

    TextView hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9, hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18;
    Boolean flag = false;
    LinearLayout topHole;
    RadioButton radioLeft1, radioRight1, radioCenter1;
    RadioButton radioLeft2, radioRight2, radioCenter2;
    RadioButton radioLeft3, radioRight3, radioCenter3;
    RadioButton radioLeft4, radioRight4, radioCenter4;

    private TextView spotPrizeText1, spotPrizeText2, spotPrizeText3, spotPrizeText4, feetText1, feetText2, feetText3, feetText4;
    private LinearLayout spotPrizeLayout1, spotPrizeLayout2, spotPrizeLayout3, spotPrizeLayout4;
    private EditText feetValue1, feetValue2, feetValue3, feetValue4;

    //String no_of_hole_played;
    String score_value_saved;
    String feet_saved, inch_saved;

    ArrayList<String> inch_savedArray = new ArrayList<String>();
    ArrayList<String> feet_savedArray = new ArrayList<String>();

    ArrayList<String> value_saved = new ArrayList<String>();

    ArrayList<String> fare_savedArray = new ArrayList<String>();
    ArrayList<String> sand_savedArray = new ArrayList<String>();
    ArrayList<String> putt_savedArray = new ArrayList<String>();
    ArrayList<String> spot_savedArray = new ArrayList<String>();
    ArrayList<String> isTemp_savedArray = new ArrayList<String>();

    String selected_fairway1 = "0";
    String selected_fairway2 = "0";
    String selected_fairway3 = "0";
    String selected_fairway4 = "0";
    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;

    String grossscore1, grossscore2, grossscore3, grossscore4;
    String no_of_putts1, no_of_putts2, no_of_putts3, no_of_putts4;


    String sand_value1, sand_value2, sand_value3, sand_value4;

    private Constant constant;
    Typeface Lato_Bold;
    Typeface Lato_Regular;

    View player1Color, player2Color, player3Color, player4Color;

    String playerColor = "#0b5a97";


    RelativeLayout autopressLayout;

    LinearLayout front, back;
    LatoTextView front1, front2, front3, front4, front5, front6, front7, front8, front9, front10;
    LatoTextView back1, back2, back3, back4, back5, back6, back7, back8, back9;

    ArrayList<String> scoreArrayfront = new ArrayList<String>();
    ArrayList<String> colorArrayfront = new ArrayList<String>();

    ArrayList<String> scoreArrayback = new ArrayList<String>();
    ArrayList<String> colorArrayback = new ArrayList<String>();

    private LinearLayout scoreCardBTN;
    private TextView scoreCardText;
    private String totalPlayers;
    FrameLayout pageIndicator;
    String delegated_status;

    String banner_id;
    String banner_url;
    String banner_img;
    int count=0;
    TextView save_text;

    private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds

    long lastClickTime = 0;

    private android.widget.RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_score_new);


        constant = new Constant();

        Lato_Bold = Typeface.createFromAsset(getAssets(), constant.Lato_Bold);
        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);

        backBTN = (ImageView) findViewById(R.id.back_addscore_new_event);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        scoreCardBTN = (LinearLayout) findViewById(R.id.scorecard_Btn);
        scoreCardText = (TextView) findViewById(R.id.scoreText);

        save_text = (TextView)findViewById(R.id.saveText);

        plusIcon = (ImageView) findViewById(R.id.plus_icon_score);
        plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTemp.equalsIgnoreCase("1")) {
                    SharedPreferences pref2 = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                    final String admin_ID1 = pref2.getString("userId", null);
                    String eventID1 = geteventID();
                    String holevalue1 = getholevalue();
                    tempScoreData(admin_ID1, eventID1, holevalue1);

                } else {

                }
                open_dialog();
            }
        });



        plusIcon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setY(event.getRawY() + dY);
                        v.setX(event.getRawX() + dX);
                        lastAction = MotionEvent.ACTION_MOVE;
                        break;

                    case MotionEvent.ACTION_UP:
                        if (lastAction == MotionEvent.ACTION_DOWN)
                        break;

                    default:
                        return false;
                }
                return false;
            }
        });






        autopressLayout = (RelativeLayout) findViewById(R.id.autopress_scorcard_tab);

        autopressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Fragment fragment = new AutoPressExpand();
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.expand_container, fragment).commit();
                    hjdwosah
                }*/


                autopressPopup();
            }
        });

        front = (LinearLayout) findViewById(R.id.front);
        back = (LinearLayout) findViewById(R.id.back);

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


        player1Color = (View) findViewById(R.id.player1_color);
        player2Color = (View) findViewById(R.id.player2_color);
        player3Color = (View) findViewById(R.id.player3_color);
        player4Color = (View) findViewById(R.id.player4_color);

        layout_340_scorcard = (LinearLayout) findViewById(R.id.layout_340_scorcard);

        layout_340_scorcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand420_Popup();
            }
        });

        teamtabLayout = (LinearLayout) findViewById(R.id.downrowscorecard);

        teamtabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (format_id.equalsIgnoreCase("14")) {
                    expand21Popup();
                } else if (format_id.equalsIgnoreCase("13")) {
                    expand21Popup();
                }
            }
        });

        topHole = (LinearLayout) findViewById(R.id.hole_top_lay);

        teamA_Exp = (LatoTextView) findViewById(R.id.teamA_expend);
        teamB_Exp = (LatoTextView) findViewById(R.id.teamB_expend);

        player1A_Exp = (LatoTextView) findViewById(R.id.player1_teamA);
        player2A_Exp = (LatoTextView) findViewById(R.id.player2_teamA);
        player1B_Exp = (LatoTextView) findViewById(R.id.player1_teamB);
        player2B_Exp = (LatoTextView) findViewById(R.id.player2_teamB);

        standings_Expand = (LatoTextView) findViewById(R.id.up_expend);
        hole_Expand = (LatoTextView) findViewById(R.id.thru_expend_hole);


        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioLeft1 = (RadioButton) findViewById(R.id.radio_left1);
        radioRight1 = (RadioButton) findViewById(R.id.radio_right1);
        radioCenter1 = (RadioButton) findViewById(R.id.radio_center1);

        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioLeft2 = (RadioButton) findViewById(R.id.radio_left2);
        radioRight2 = (RadioButton) findViewById(R.id.radio_right2);
        radioCenter2 = (RadioButton) findViewById(R.id.radio_center2);

        radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);
        radioLeft3 = (RadioButton) findViewById(R.id.radio_left3);
        radioRight3 = (RadioButton) findViewById(R.id.radio_right3);
        radioCenter3 = (RadioButton) findViewById(R.id.radio_center3);

        radioGroup4 = (RadioGroup) findViewById(R.id.radioGroup4);
        radioLeft4 = (RadioButton) findViewById(R.id.radio_left4);
        radioRight4 = (RadioButton) findViewById(R.id.radio_right4);
        radioCenter4 = (RadioButton) findViewById(R.id.radio_center4);


        feetText1 = (TextView) findViewById(R.id.fee_text1);
        feetText2 = (TextView) findViewById(R.id.fee_text2);
        feetText3 = (TextView) findViewById(R.id.fee_text3);
        feetText4 = (TextView) findViewById(R.id.fee_text4);

        feetValue1 = (EditText) findViewById(R.id.feetmain1);
        feetValue2 = (EditText) findViewById(R.id.feetmain2);
        feetValue3 = (EditText) findViewById(R.id.feetmain3);
        feetValue4 = (EditText) findViewById(R.id.feetmain4);

        feetValue1.setTypeface(Lato_Bold);
        feetValue2.setTypeface(Lato_Bold);
        feetValue3.setTypeface(Lato_Bold);
        feetValue4.setTypeface(Lato_Bold);

        feetValue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    feetInchPopUp1();
                } else if (isSpot.equalsIgnoreCase("3")) {
                    yardPopUp1();
                }
            }
        });

        feetValue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    feetInchPopUp2();
                } else if (isSpot.equalsIgnoreCase("3")) {
                    yardPopUp2();
                }
            }
        });

        feetValue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    feetInchPopUp3();
                } else if (isSpot.equalsIgnoreCase("3")) {
                    yardPopUp3();
                }
            }
        });

        feetValue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    feetInchPopUp4();
                } else if (isSpot.equalsIgnoreCase("3")) {
                    yardPopUp4();
                }
            }
        });

        spotPrizeLayout1 = (LinearLayout) findViewById(R.id.spot_prize_layout1_save);
        spotPrizeText1 = (LatoTextView) findViewById(R.id.spot_prizeTV_addscore1);
        spotPrizeLayout2 = (LinearLayout) findViewById(R.id.spot_prize_layout2_save);
        spotPrizeText2 = (LatoTextView) findViewById(R.id.spot_prizeTV_addscore2);
        spotPrizeLayout3 = (LinearLayout) findViewById(R.id.spot_prize_layout3_save);
        spotPrizeText3 = (LatoTextView) findViewById(R.id.spot_prizeTV_addscore3);
        spotPrizeLayout4 = (LinearLayout) findViewById(R.id.spot_prize_layout4_save);
        spotPrizeText4 = (LatoTextView) findViewById(R.id.spot_prizeTV_addscore4);

        fairwaysLayout1 = (LinearLayout) findViewById(R.id.fairway_layout);
        sandLayout1 = (LinearLayout) findViewById(R.id.sand_layout);

        fairwaysLayout2 = (LinearLayout) findViewById(R.id.fairway_layout2);
        sandLayout2 = (LinearLayout) findViewById(R.id.sand_layout2);

        fairwaysLayout3 = (LinearLayout) findViewById(R.id.fairway_layout3);
        sandLayout3 = (LinearLayout) findViewById(R.id.sand_layout3);

        fairwaysLayout4 = (LinearLayout) findViewById(R.id.fairway_layout4);
        sandLayout4 = (LinearLayout) findViewById(R.id.sand_layout4);

        bottom_back = (LinearLayout) findViewById(R.id.bottom_back);
        bottom_next = (LinearLayout) findViewById(R.id.bottom_next);


        player1Layout = (LinearLayout) findViewById(R.id.player1_total_layout);
        player2Layout = (LinearLayout) findViewById(R.id.player2_total_layout);
        player3Layout = (LinearLayout) findViewById(R.id.player3_total_layout);
        player4Layout = (LinearLayout) findViewById(R.id.player4_total_layout);

        player1_name = (TextView) findViewById(R.id.add_score_new_player1Name);
        player2_name = (TextView) findViewById(R.id.add_score_new_player2Name);
        player3_name = (TextView) findViewById(R.id.add_score_new_player3Name);
        player4_name = (TextView) findViewById(R.id.add_score_new_player4Name);

        handicap1 = (TextView) findViewById(R.id.add_score_new_player1handi);
        handicap2 = (TextView) findViewById(R.id.add_score_new_player2handi);
        handicap3 = (TextView) findViewById(R.id.add_score_new_player3handi);
        handicap4 = (TextView) findViewById(R.id.add_score_new_player4handi);

        puttsnumber1 = (TextView) findViewById(R.id.putts_number1);
        puttsnumber2 = (TextView) findViewById(R.id.putts_number2);
        puttsnumber3 = (TextView) findViewById(R.id.putts_number3);
        puttsnumber4 = (TextView) findViewById(R.id.putts_number4);

        sandEditText1 = (TextView) findViewById(R.id.sand_number1);
        sandEditText2 = (TextView) findViewById(R.id.sand_number2);
        sandEditText3 = (TextView) findViewById(R.id.sand_number3);
        sandEditText4 = (TextView) findViewById(R.id.sand_number4);

        par = (TextView) findViewById(R.id.add_score_new_par);
        hole = (TextView) findViewById(R.id.add_score_new_holeno);
        title = (TextView) findViewById(R.id.add_score_new_eventname);

        gross_score1 = (TextView) findViewById(R.id.gross_score1);
        gross_score2 = (TextView) findViewById(R.id.gross_score2);
        gross_score3 = (TextView) findViewById(R.id.gross_score3);
        gross_score4 = (TextView) findViewById(R.id.gross_score4);

        putt1_minus = (RelativeLayout) findViewById(R.id.putts_minus1);
        putt2_minus = (RelativeLayout) findViewById(R.id.putts_minus2);
        putt3_minus = (RelativeLayout) findViewById(R.id.putts_minus3);
        putt4_minus = (RelativeLayout) findViewById(R.id.putts_minus4);

        putt1_plus = (RelativeLayout) findViewById(R.id.putts_add1);
        putt2_plus = (RelativeLayout) findViewById(R.id.putts_add2);
        putt3_plus = (RelativeLayout) findViewById(R.id.putts_add3);
        putt4_plus = (RelativeLayout) findViewById(R.id.putts_add4);


        sand1_minus = (RelativeLayout) findViewById(R.id.sand_minus1);
        sand2_minus = (RelativeLayout) findViewById(R.id.sand_minus2);
        sand3_minus = (RelativeLayout) findViewById(R.id.sand_minus3);
        sand4_minus = (RelativeLayout) findViewById(R.id.sand_minus4);

        sand1_plus = (RelativeLayout) findViewById(R.id.sand_add1);
        sand2_plus = (RelativeLayout) findViewById(R.id.sand_add2);
        sand3_plus = (RelativeLayout) findViewById(R.id.sand_add3);
        sand4_plus = (RelativeLayout) findViewById(R.id.sand_add4);

        gross_minus1 = (RelativeLayout) findViewById(R.id.gross_score_minus1);
        gross_minus2 = (RelativeLayout) findViewById(R.id.gross_score_minus2);
        gross_minus3 = (RelativeLayout) findViewById(R.id.gross_score_minus3);
        gross_minus4 = (RelativeLayout) findViewById(R.id.gross_score_minus4);

        gross_plus1 = (RelativeLayout) findViewById(R.id.gross_score_plus1);
        gross_plus2 = (RelativeLayout) findViewById(R.id.gross_score_plus2);
        gross_plus3 = (RelativeLayout) findViewById(R.id.gross_score_plus3);
        gross_plus4 = (RelativeLayout) findViewById(R.id.gross_score_plus4);

        expandLay1 = (LinearLayout) findViewById(R.id.player1_expand);
        expandLay2 = (LinearLayout) findViewById(R.id.player2_expand);
        expandLay3 = (LinearLayout) findViewById(R.id.player3_expand);
        expandLay4 = (LinearLayout) findViewById(R.id.player4_expand);

        player_lay1 = (RelativeLayout) findViewById(R.id.player1Lay);
        player_lay2 = (RelativeLayout) findViewById(R.id.player2Lay);
        player_lay3 = (RelativeLayout) findViewById(R.id.player3Lay);
        player_lay4 = (RelativeLayout) findViewById(R.id.player4Lay);


        // New game format bottom score tab

        layout_340_scorcard = (LinearLayout) findViewById(R.id.layout_340_scorcard);
        teamtabLayout = (LinearLayout) findViewById(R.id.downrowscorecard);

        player1_420score = (LatoTextView) findViewById(R.id.player1_420score);
        player2_420score = (LatoTextView) findViewById(R.id.player2_420score);
        player3_420score = (LatoTextView) findViewById(R.id.player3_420score);

        play1_340_lay = (LinearLayout) findViewById(R.id.player1_420Layout);
        play2_340_lay = (LinearLayout) findViewById(R.id.player2_420Layout);
        play3_340_lay = (LinearLayout) findViewById(R.id.player3_420Layout);

        player1_420name = (LatoTextView) findViewById(R.id.player1_420name);
        player2_420name = (LatoTextView) findViewById(R.id.player2_420name);
        player3_420name = (LatoTextView) findViewById(R.id.player3_420name);
        winnerBackgroundLay = (LinearLayout) findViewById(R.id.team_idicator_layout);


        save_score = (LinearLayout) findViewById(R.id.save_score);

        save_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectionDetector co = new ConnectionDetector(AddScoreNew.this);

                if (co.isConnectingToInternet()) {
                    check_validation();
                } else {
                    Toast.makeText(AddScoreNew.this, "No Internet Connections", Toast.LENGTH_SHORT).show();
                }

            }
        });

        player_lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref2 = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                if (pref2 != null) {
                    SharedPreferences.Editor editor = pref2.edit();
                    editor.putString("contact_swipe", "1");
                    editor.commit();
                }

                if (flag1 == 0) {
                    expandLay1.setVisibility(View.VISIBLE);
                    expandLay2.setVisibility(View.GONE);
                    expandLay3.setVisibility(View.GONE);
                    expandLay4.setVisibility(View.GONE);

                    flag1 = 1;
                    flag3 = 0;
                    flag2 = 0;
                    flag4 = 0;
                } else {
                    expandLay1.setVisibility(View.GONE);
                    expandLay2.setVisibility(View.GONE);
                    expandLay3.setVisibility(View.GONE);
                    expandLay4.setVisibility(View.GONE);

                    flag1 = 0;
                    flag3 = 0;
                    flag2 = 0;
                    flag4 = 0;
                }
            }
        });


        player_lay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if (flag2 == 0) {
                    expandLay2.setVisibility(View.VISIBLE);
                    expandLay1.setVisibility(View.GONE);
                    expandLay3.setVisibility(View.GONE);
                    expandLay4.setVisibility(View.GONE);

                    flag2 = 1;
                    flag3 = 0;
                    flag1 = 0;
                    flag4 = 0;
                } else {
                    expandLay1.setVisibility(View.GONE);
                    expandLay2.setVisibility(View.GONE);
                    expandLay3.setVisibility(View.GONE);
                    expandLay4.setVisibility(View.GONE);

                    flag1 = 0;
                    flag3 = 0;
                    flag2 = 0;
                    flag4 = 0;
                }*/
            }
        });

        player_lay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (flag3 == 0) {
                    expandLay3.setVisibility(View.VISIBLE);
                    expandLay2.setVisibility(View.GONE);
                    expandLay1.setVisibility(View.GONE);
                    expandLay4.setVisibility(View.GONE);

                    flag3 = 1;
                    flag1 = 0;
                    flag2 = 0;
                    flag4 = 0;
                } else {
                    expandLay1.setVisibility(View.GONE);
                    expandLay2.setVisibility(View.GONE);
                    expandLay3.setVisibility(View.GONE);
                    expandLay4.setVisibility(View.GONE);

                    flag1 = 0;
                    flag3 = 0;
                    flag2 = 0;
                    flag4 = 0;
                }*/
            }
        });

        player_lay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if (flag4 == 0) {
                    expandLay4.setVisibility(View.VISIBLE);
                    expandLay2.setVisibility(View.GONE);
                    expandLay3.setVisibility(View.GONE);
                    expandLay1.setVisibility(View.GONE);

                    flag4 = 1;
                    flag3 = 0;
                    flag2 = 0;
                    flag1 = 0;
                } else {
                    expandLay1.setVisibility(View.GONE);
                    expandLay2.setVisibility(View.GONE);
                    expandLay3.setVisibility(View.GONE);
                    expandLay4.setVisibility(View.GONE);

                    flag1 = 0;
                    flag3 = 0;
                    flag2 = 0;
                    flag4 = 0;
                }*/
            }
        });


        gross_plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grossPlus1();
                String s1 = sandEditText1.getText().toString().trim();
                String s2 = puttsnumber1.getText().toString().trim();
                String g1 = gross_score1.getText().toString().trim();
                int a1=0,a2=0;
                if (s1!=null && s1.equalsIgnoreCase("-")){
                    a1=0;
                }else {
                    a1= Integer.parseInt(s1);
                }
                if (s2!=null && s2.equalsIgnoreCase("-")){
                    a2=0;
                }else {
                    a2 =  Integer.parseInt(s2);
                }
                if (Integer.parseInt(g1) <=(a1+a2) ) {

                    sandEditText1.setText("-");
                    puttsnumber1.setText("-");
                    puttCounter1 = 0;
                    sandCounter1 = 0;
                }
            }
        });
        gross_minus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grossMinus1();

                String s1 = sandEditText1.getText().toString().trim();
                String s2 = puttsnumber1.getText().toString().trim();
                String g1 = gross_score1.getText().toString().trim();
                int a1=0,a2=0;
                if (s1!=null && s1.equalsIgnoreCase("-")){
                    a1=0;
                }else {
                    a1= Integer.parseInt(s1);
                }
                if (s2!=null && s2.equalsIgnoreCase("-")){
                    a2=0;
                }else {
                    a2 =  Integer.parseInt(s2);
                }
                if (Integer.parseInt(g1) <=(a1+a2) ) {

                    sandEditText1.setText("-");
                    puttsnumber1.setText("-");
                    puttCounter1 = 0;
                    sandCounter1 = 0;
                }
            }
        });

        sand1_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandPlus1();


            }
        });
        sand1_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandMinus1();


            }
        });

        putt1_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puttPlus1();
            }
        });
        putt1_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puttMinus1();
            }
        });


        gross_plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grossPlus2();


                String s1 = sandEditText2.getText().toString().trim();
                String s2 = puttsnumber2.getText().toString().trim();
                String g1 = gross_score2.getText().toString().trim();
                int a1=0,a2=0;
                if (s1!=null && s1.equalsIgnoreCase("-")){
                    a1=0;
                }else {
                    a1= Integer.parseInt(s1);
                }
                if (s2!=null && s2.equalsIgnoreCase("-")){
                    a2=0;
                }else {
                    a2 =  Integer.parseInt(s2);
                }
                if (Integer.parseInt(g1) <=(a1+a2) ) {

                    sandEditText2.setText("-");
                    puttsnumber2.setText("-");
                    puttCounter2 = 0;
                    sandCounter2 = 0;
                }
            }
        });
        gross_minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grossMinus2();
                String s1 = sandEditText2.getText().toString().trim();
                String s2 = puttsnumber2.getText().toString().trim();
                String g1 = gross_score2.getText().toString().trim();
                int a1=0,a2=0;
                if (s1!=null && s1.equalsIgnoreCase("-")){
                    a1=0;
                }else {
                    a1= Integer.parseInt(s1);
                }
                if (s2!=null && s2.equalsIgnoreCase("-")){
                    a2=0;
                }else {
                    a2 =  Integer.parseInt(s2);
                }
                if (Integer.parseInt(g1) <=(a1+a2) ) {

                    sandEditText2.setText("-");
                    puttsnumber2.setText("-");
                    puttCounter2 = 0;
                    sandCounter2 = 0;
                }
            }
        });

        sand2_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandPlus2();


            }
        });
        sand2_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandMinus2();


            }
        });

        putt2_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puttPlus2();
            }
        });
        putt2_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puttMinus2();
            }
        });

        gross_plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grossPlus3();
                String s1 = sandEditText3.getText().toString().trim();
                String s2 = puttsnumber3.getText().toString().trim();
                String g1 = gross_score3.getText().toString().trim();
                int a1=0,a2=0;
                if (s1!=null && s1.equalsIgnoreCase("-")){
                    a1=0;
                }else {
                    a1= Integer.parseInt(s1);
                }
                if (s2!=null && s2.equalsIgnoreCase("-")){
                    a2=0;
                }else {
                    a2 =  Integer.parseInt(s2);
                }
                if (Integer.parseInt(g1) <=(a1+a2) ) {

                    sandEditText3.setText("-");
                    puttsnumber3.setText("-");
                    puttCounter3 = 0;
                    sandCounter3 = 0;
                }
            }
        });
        gross_minus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grossMinus3();
                String s1 = sandEditText3.getText().toString().trim();
                String s2 = puttsnumber3.getText().toString().trim();
                String g1 = gross_score3.getText().toString().trim();
                int a1=0,a2=0;
                if (s1!=null && s1.equalsIgnoreCase("-")){
                    a1=0;
                }else {
                    a1= Integer.parseInt(s1);
                }
                if (s2!=null && s2.equalsIgnoreCase("-")){
                    a2=0;
                }else {
                    a2 =  Integer.parseInt(s2);
                }
                if (Integer.parseInt(g1) <=(a1+a2) ) {

                    sandEditText3.setText("-");
                    puttsnumber3.setText("-");
                    puttCounter3 = 0;
                    sandCounter3 = 0;
                }
            }
        });

        sand3_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandPlus3();


            }
        });
        sand3_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandMinus3();


            }
        });

        putt3_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puttPlus3();
            }
        });
        putt3_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puttMinus3();
            }
        });


        gross_plus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grossPlus4();
                String s1 = sandEditText4.getText().toString().trim();
                String s2 = puttsnumber4.getText().toString().trim();
                String g1 = gross_score4.getText().toString().trim();
                int a1=0,a2=0;
                if (s1!=null && s1.equalsIgnoreCase("-")){
                    a1=0;
                }else {
                    a1= Integer.parseInt(s1);
                }
                if (s2!=null && s2.equalsIgnoreCase("-")){
                    a2=0;
                }else {
                    a2 =  Integer.parseInt(s2);
                }
                if (Integer.parseInt(g1) <=(a1+a2) ) {

                    sandEditText4.setText("-");
                    puttsnumber4.setText("-");
                    puttCounter4 = 0;
                    sandCounter4 = 0;
                }
            }
        });
        gross_minus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grossMinus4();
                String s1 = sandEditText4.getText().toString().trim();
                String s2 = puttsnumber4.getText().toString().trim();
                String g1 = gross_score4.getText().toString().trim();
                int a1=0,a2=0;
                if (s1!=null && s1.equalsIgnoreCase("-")){
                    a1=0;
                }else {
                    a1= Integer.parseInt(s1);
                }
                if (s2!=null && s2.equalsIgnoreCase("-")){
                    a2=0;
                }else {
                    a2 =  Integer.parseInt(s2);
                }
                if (Integer.parseInt(g1) <=(a1+a2) ) {

                    sandEditText4.setText("-");
                    puttsnumber4.setText("-");
                    puttCounter4 = 0;
                    sandCounter4 = 0;
                }
            }
        });

        sand4_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandPlus4();


            }
        });
        sand4_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandMinus4();

            }
        });

        putt4_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puttPlus4();
            }
        });
        putt4_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puttMinus4();
            }
        });


        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        final String admin_ID = pref.getString("userId", null);
        String eventID = getIntent().getStringExtra("eventID").toString();

        ConnectionDetector connectionDetector = new ConnectionDetector(this);
        if (connectionDetector.isConnectingToInternet()) {
            delegated_status = "1";
            getscorecarddata(admin_ID, eventID, delegated_status);

            getBannerList(eventID);

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connections", Toast.LENGTH_SHORT).show();

        }

        bottom_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    String holeStartFrom = getHoleValidation();
                    int counter = Integer.parseInt(no_of_hole_played);
                    String currentHole = getholevalue();
                    if (counter>0) {

                        if (holeStartFrom.equalsIgnoreCase(currentHole)){

                            backMethod();
                           // Toast.makeText(getApplicationContext(),"This is last saved hole.",Toast.LENGTH_SHORT).show();
                        }else {
                            bottomprevious();
                            count++;

                        }
                    }
                } else {
                    bottomprevious();
                }

            }
        });

        bottom_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    String holeStartFrom = getHoleValidation();
                    int counter = Integer.parseInt(no_of_hole_played);

                    if (count>0 && count<=counter) {

                        bottomnext();
                        count--;
                    }else {

                      //  Toast.makeText(getApplicationContext(),"This is last saved hole.",Toast.LENGTH_SHORT).show();
                        nextMethod();
                    }
                } else {
                    bottomnext();
                }
            }
        });

        topHole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {


                    if (flag == false) {

                        open_hole_num_selection_dialog(getholevalue());



                    } else {
                        Toast.makeText(getApplicationContext(), "You can't select any hole", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    open_hole_num_selection_dialog(getholevalue());
                }
            }
        });


        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_left1:

                        selected_fairway1 = "1";
                        break;

                    case R.id.radio_center1:

                        selected_fairway1 = "2";
                        break;

                    case R.id.radio_right1:

                        selected_fairway1 = "3";
                        break;

                }


            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_left2:

                        selected_fairway2 = "1";
                        break;

                    case R.id.radio_center2:

                        selected_fairway2 = "2";
                        break;

                    case R.id.radio_right2:

                        selected_fairway2 = "3";
                        break;

                }


            }
        });

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_left3:

                        selected_fairway3 = "1";
                        break;

                    case R.id.radio_center3:

                        selected_fairway3 = "2";
                        break;

                    case R.id.radio_right3:

                        selected_fairway3 = "3";
                        break;

                }


            }
        });

        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_left4:

                        selected_fairway4 = "1";
                        break;

                    case R.id.radio_center4:

                        selected_fairway4 = "2";
                        break;

                    case R.id.radio_right4:

                        selected_fairway4 = "3";
                        break;
                }
            }
        });


        pageIndicator = (FrameLayout) findViewById(R.id.swap_frame);

        SharedPreferences pref1 = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        String hide = pref1.getString("contact_swipe", null);

        if (hide == null) {
            pageIndicator.setVisibility(View.VISIBLE);
        } else if (hide.equalsIgnoreCase("1")) {

            pageIndicator.setVisibility(View.INVISIBLE);
        } else {
            pageIndicator.setVisibility(View.VISIBLE);
        }

        pageIndicator.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageIndicator.setVisibility(View.INVISIBLE);
                return false;
            }
        });

    }


    public String getholevalue() {

        String hole_numbervalue = hole.getText().toString();
        return hole_numbervalue;
    }

    public void bottomnext() {

        if (isTemp.equalsIgnoreCase("1")) {


            SharedPreferences pref2 = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
            final String admin_ID1 = pref2.getString("userId", null);
            String eventID1 = geteventID();
            String holevalue1 = getholevalue();
            tempScoreData(admin_ID1, eventID1, holevalue1);

        } else {


        }

        String totalHole = getTotalHoleValidation();
        String holeStartFrom = getHoleValidation();

        String holevalue = getholevalue();
        int i = Integer.parseInt(holevalue);
        hole_count = i + 1;

        if (totalHole.equalsIgnoreCase("18")) {

            if (hole_count > 18) {
                Toast.makeText(this, "last hole number", Toast.LENGTH_SHORT).show();
                setCounthole(1 + "");
            } else {
                setCounthole(hole_count + "");
            }
        } else if (totalHole.equalsIgnoreCase("9") && holeStartFrom.equalsIgnoreCase("1")) {

            if (hole_count > 9) {
                Toast.makeText(this, "last hole number", Toast.LENGTH_SHORT).show();
                setCounthole(1 + "");
            } else {
                setCounthole(hole_count + "");
            }
        } else if (totalHole.equalsIgnoreCase("9") && holeStartFrom.equalsIgnoreCase("10")) {
            if (hole_count > 18) {
                Toast.makeText(this, "last hole number", Toast.LENGTH_SHORT).show();
                setCounthole(10 + "");
            } else {
                setCounthole(hole_count + "");
            }
        }


    }

    public void bottomprevious() {


        if (isTemp.equalsIgnoreCase("1")) {
            SharedPreferences pref2 = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
            final String admin_ID1 = pref2.getString("userId", null);
            String eventID1 = geteventID();
            String holevalue1 = getholevalue();
            tempScoreData(admin_ID1, eventID1, holevalue1);

        } else {

        }

        String holevalue = getholevalue();
        String totalHole = getTotalHoleValidation();
        String holeStartFrom = getHoleValidation();

        int i = Integer.parseInt(holevalue);

        hole_count = i - 1;

        if (totalHole.equalsIgnoreCase("18")) {

            if (hole_count < 1) {
                // Toast.makeText(getActivity(), "Min Selected", Toast.LENGTH_SHORT).show();
                setCounthole(18 + "");
            } else {
                setCounthole(hole_count + "");
            }
        } else if (totalHole.equalsIgnoreCase("9") && holeStartFrom.equalsIgnoreCase("1")) {

            if (hole_count < 1) {
                Toast.makeText(this, "last hole number", Toast.LENGTH_SHORT).show();
                setCounthole(9 + "");
            } else {
                setCounthole(hole_count + "");
            }
        } else if (totalHole.equalsIgnoreCase("9") && holeStartFrom.equalsIgnoreCase("10")) {
            if (hole_count < 10) {
                Toast.makeText(this, "last hole number", Toast.LENGTH_SHORT).show();
                setCounthole(18 + "");
            } else {
                setCounthole(hole_count + "");
            }
        }


    }


    public void bottomnext1() {


        String totalHole = getTotalHoleValidation();
        String holeStartFrom = getHoleValidation();

        String holevalue = getholevalue();
        int i = Integer.parseInt(holevalue);
        hole_count = i + 1;

        if (totalHole.equalsIgnoreCase("18")) {

            if (hole_count > 18) {
                Toast.makeText(this, "last hole number", Toast.LENGTH_SHORT).show();
                setCounthole(1 + "");
            } else {
                setCounthole(hole_count + "");
            }
        } else if (totalHole.equalsIgnoreCase("9") && holeStartFrom.equalsIgnoreCase("1")) {

            if (hole_count > 9) {
                Toast.makeText(this, "last hole number", Toast.LENGTH_SHORT).show();
                setCounthole(1 + "");
            } else {
                setCounthole(hole_count + "");
            }
        } else if (totalHole.equalsIgnoreCase("9") && holeStartFrom.equalsIgnoreCase("10")) {
            if (hole_count > 18) {
                Toast.makeText(this, "last hole number", Toast.LENGTH_SHORT).show();
                setCounthole(10 + "");
            } else {
                setCounthole(hole_count + "");
            }
        }


    }


    public String geteventID() {

        return getIntent().getStringExtra("eventID").toString();
    }

    public void grossPlus1() {
        String score = gross_score1.getText().toString();
        int g_score = Integer.parseInt(score);
        count_gross_score1 = g_score + 1;
        if (count_gross_score1 > 20) {
            Toast.makeText(this, "Max Selected", Toast.LENGTH_SHORT).show();
        } else {
            gross_score1.setText("" + count_gross_score1);
        }
    }

    public void grossMinus1() {
        String score = gross_score1.getText().toString();
        String sandnum = sandEditText1.getText().toString();
        String puttScore = puttsnumber1.getText().toString();
        int san=0,put=0;
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }
        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        int g_score = Integer.parseInt(score);

        if (g_score<= (san+put+1)){

            if (put!=0) {
                puttMinus1();
            }else if (san!=0){
                sandMinus1();
            }else {
                sandEditText1.setText("-");
                puttsnumber1.setText("-");
            }
            count_gross_score1 = g_score - 1;

            if (count_gross_score1 < 1) {
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();

            } else {
                gross_score1.setText("" + count_gross_score1);

            }
        }else {


            count_gross_score1 = g_score - 1;

            if (count_gross_score1 < 1) {
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();

            } else {
                gross_score1.setText("" + count_gross_score1);

            }
        }
    }


    public void sandPlus1() {

        int ps = 0;
        int san = 0 , put =0 ,gs = 0;
        String sandnum = sandEditText1.getText().toString();
        String grossscore = gross_score1.getText().toString();
        String puttScore = puttsnumber1.getText().toString();

        if (grossscore.equalsIgnoreCase("-")){
            gs = 0;
        }else {
            gs = Integer.parseInt(grossscore);
        }
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }

        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        if (sandnum.equalsIgnoreCase("-")) {
            sandEditText1.setText("0");
        }else {
            if (san < gs - 1) {

                if ((san + put + 1) == gs) {
                    puttMinus1();
                    sandEditText1.setText("" + (san + 1));

                } else {
                    sandEditText1.setText("" + (san + 1));
                }

            }
        }

    }


    public void grossPlus2() {
        String score = gross_score2.getText().toString();
        int g_score = Integer.parseInt(score);
        count_gross_score2 = g_score + 1;
        if (count_gross_score2 > 20) {
            Toast.makeText(this, "Max Selected", Toast.LENGTH_SHORT).show();
        } else {
            gross_score2.setText("" + count_gross_score2);
        }
    }

    public void grossMinus2() {
        String score = gross_score2.getText().toString();
        String sandnum = sandEditText2.getText().toString();
        String puttScore = puttsnumber2.getText().toString();
        int san=0,put=0;
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }
        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        int g_score = Integer.parseInt(score);

        if (g_score<= (san+put+1)){

            if (put!=0) {
                puttMinus2();
            }else if (san!=0){
                sandMinus2();
            }else {
                sandEditText2.setText("-");
                puttsnumber2.setText("-");
            }
            count_gross_score2 = g_score - 1;

            if (count_gross_score2 < 1) {
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();

            } else {
                gross_score2.setText("" + count_gross_score2);

            }
        }else {


            count_gross_score2 = g_score - 1;

            if (count_gross_score2 < 1) {
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();

            } else {
                gross_score2.setText("" + count_gross_score2);

            }
        }
    }


    public void sandPlus2() {
        int ps = 0;
        int san = 0 , put =0 ,gs = 0;
        String sandnum = sandEditText2.getText().toString();
        String grossscore = gross_score2.getText().toString();
        String puttScore = puttsnumber2.getText().toString();

        if (grossscore.equalsIgnoreCase("-")){
            gs = 0;
        }else {
            gs = Integer.parseInt(grossscore);
        }
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }

        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        if (sandnum.equalsIgnoreCase("-")) {
            sandEditText2.setText("0");
        }else {
            if (san < gs - 1) {

                if ((san + put + 1) == gs) {
                    puttMinus2();
                    sandEditText2.setText("" + (san + 1));

                } else {
                    sandEditText2.setText("" + (san + 1));
                }

            }
        }

    }

    public void grossPlus3() {
        String score = gross_score3.getText().toString();
        int g_score = Integer.parseInt(score);
        count_gross_score3 = g_score + 1;
        if (count_gross_score3 > 20) {
            Toast.makeText(this, "Max Selected", Toast.LENGTH_SHORT).show();
        } else {
            gross_score3.setText("" + count_gross_score3);
        }
    }

    public void grossMinus3() {
        String score = gross_score3.getText().toString();
        String sandnum = sandEditText3.getText().toString();
        String puttScore = puttsnumber3.getText().toString();
        int san=0,put=0;
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }
        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        int g_score = Integer.parseInt(score);

        if (g_score<= (san+put+1)){

            if (put!=0) {
                puttMinus3();
            }else if (san!=0){
                sandMinus3();
            }else {
                sandEditText3.setText("-");
                puttsnumber3.setText("-");
            }
            count_gross_score3 = g_score - 1;

            if (count_gross_score3 < 1) {
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();

            } else {
                gross_score3.setText("" + count_gross_score3);

            }
        }else {


            count_gross_score3 = g_score - 1;

            if (count_gross_score3 < 1) {
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();

            } else {
                gross_score3.setText("" + count_gross_score3);

            }
        }
    }


    public void sandPlus3() {
        int ps = 0;
        int san = 0 , put =0 ,gs = 0;
        String sandnum = sandEditText3.getText().toString();
        String grossscore = gross_score3.getText().toString();
        String puttScore = puttsnumber3.getText().toString();

        if (grossscore.equalsIgnoreCase("-")){
            gs = 0;
        }else {
            gs = Integer.parseInt(grossscore);
        }
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }

        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        if (sandnum.equalsIgnoreCase("-")) {
            sandEditText3.setText("0");
        }else {
            if (san < gs - 1) {

                if ((san + put + 1) == gs) {
                    puttMinus3();
                    sandEditText3.setText("" + (san + 1));

                } else {
                    sandEditText3.setText("" + (san + 1));
                }

            }
        }
    }

    public void grossPlus4() {
        String score = gross_score4.getText().toString();
        int g_score = Integer.parseInt(score);
        count_gross_score4 = g_score + 1;
        if (count_gross_score4 > 20) {
            Toast.makeText(this, "Max Selected", Toast.LENGTH_SHORT).show();
        } else {
            gross_score4.setText("" + count_gross_score4);
        }
    }

    public void grossMinus4() {
        String score = gross_score4.getText().toString();
        String sandnum = sandEditText4.getText().toString();
        String puttScore = puttsnumber4.getText().toString();
        int san=0,put=0;
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }
        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        int g_score = Integer.parseInt(score);

        if (g_score<= (san+put+1)){

            if (put!=0) {
                puttMinus4();
            }else if (san!=0){
                sandMinus4();
            }else {
                sandEditText4.setText("-");
                puttsnumber4.setText("-");
            }
            count_gross_score4 = g_score - 1;

            if (count_gross_score4 < 1) {
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();

            } else {
                gross_score4.setText("" + count_gross_score4);

            }
        }else {


            count_gross_score4 = g_score - 1;

            if (count_gross_score4 < 1) {
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();

            } else {
                gross_score4.setText("" + count_gross_score4);

            }
        }
    }


    public void sandPlus4() {
        int ps = 0;
        int san = 0 , put =0 ,gs = 0;
        String sandnum = sandEditText4.getText().toString();
        String grossscore = gross_score4.getText().toString();
        String puttScore = puttsnumber4.getText().toString();

        if (grossscore.equalsIgnoreCase("-")){
            gs = 0;
        }else {
            gs = Integer.parseInt(grossscore);
        }
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }

        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        if (sandnum.equalsIgnoreCase("-")) {
            sandEditText4.setText("0");
        }else {
            if (san < gs - 1) {

                if ((san + put + 1) == gs) {
                    puttMinus4();
                    sandEditText4.setText("" + (san + 1));

                } else {
                    sandEditText4.setText("" + (san + 1));
                }

            }
        }
    }

    public void sandMinus1() {
        String sandnum = sandEditText1.getText().toString();

        if (sandnum.equals("-")) {
            Toast.makeText(this, "No Selection", Toast.LENGTH_SHORT).show();
        } else {
            int s_score = Integer.parseInt(sandnum);
            sandCounter1 = s_score - 1;
            if (sandCounter1 < 0) {
                sandEditText1.setText("-");
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();
            } else {
                sandEditText1.setText("" + sandCounter1);
            }
        }
    }

    public void puttPlus1() {
        int ps = 0;
        int san = 0 , put =0 ,gs = 0;
        String sandnum = sandEditText1.getText().toString();
        String grossscore = gross_score1.getText().toString();
        String puttScore = puttsnumber1.getText().toString();

        if (grossscore.equalsIgnoreCase("-")){
            gs = 0;
        }else {
            gs = Integer.parseInt(grossscore);
        }
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }

        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        if (puttScore.equalsIgnoreCase("-")){
            puttsnumber1.setText("0");
        }else {

            if (put < gs - 1) {

                if ((san + put + 1) == gs) {
                    sandMinus1();
                    puttsnumber1.setText("" + (put + 1));

                } else {
                    puttsnumber1.setText("" + (put + 1));
                }

            }
        }

    }

    public void puttMinus1() {
        String puttnum = puttsnumber1.getText().toString();
        if (puttnum.equals("-")) {
            Toast.makeText(this, "No Selection", Toast.LENGTH_SHORT).show();
        } else {
            int p_score = Integer.parseInt(puttnum);
            puttCounter1 = p_score - 1;

            if (puttCounter1 < 0) {

                puttsnumber1.setText("-");
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();
            } else {
                puttsnumber1.setText("" + puttCounter1);
            }
        }
    }

    public void sandMinus2() {
        String sandnum = sandEditText2.getText().toString();

        if (sandnum.equals("-")) {
            Toast.makeText(this, "No Selection", Toast.LENGTH_SHORT).show();
        } else {
            int s_score = Integer.parseInt(sandnum);
            sandCounter2 = s_score - 1;
            if (sandCounter2 < 0) {

                sandEditText2.setText("-");
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();
            } else {
                sandEditText2.setText("" + sandCounter2);
            }
        }
    }

    public void puttPlus2() {
        int ps = 0;
        int san = 0 , put =0 ,gs = 0;
        String sandnum = sandEditText2.getText().toString();
        String grossscore = gross_score2.getText().toString();
        String puttScore = puttsnumber2.getText().toString();

        if (grossscore.equalsIgnoreCase("-")){
            gs = 0;
        }else {
            gs = Integer.parseInt(grossscore);
        }
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }

        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        if (puttScore.equalsIgnoreCase("-")){
            puttsnumber2.setText("0");
        }else {

            if (put < gs - 1) {

                if ((san + put + 1) == gs) {
                    sandMinus2();
                    puttsnumber2.setText("" + (put + 1));

                } else {
                    puttsnumber2.setText("" + (put + 1));
                }

            }
        }
    }

    public void puttMinus2() {
        String puttnum = puttsnumber2.getText().toString();
        if (puttnum.equals("-")) {
            Toast.makeText(this, "No Selection", Toast.LENGTH_SHORT).show();
        } else {
            int p_score = Integer.parseInt(puttnum);
            puttCounter2 = p_score - 1;

            if (puttCounter2 < 0) {

                puttsnumber2.setText("-");
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();
            } else {
                puttsnumber2.setText("" + puttCounter2);
            }
        }
    }

    public void sandMinus3() {
        String sandnum = sandEditText3.getText().toString();

        if (sandnum.equals("-")) {
            Toast.makeText(this, "No Selection", Toast.LENGTH_SHORT).show();
        } else {
            int s_score = Integer.parseInt(sandnum);
            sandCounter3 = s_score - 1;
            if (sandCounter3 < 0) {

                sandEditText3.setText("-");
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();
            } else {
                sandEditText3.setText("" + sandCounter3);
            }
        }
    }

    public void puttPlus3() {
        int ps = 0;
        int san = 0 , put =0 ,gs = 0;
        String sandnum = sandEditText3.getText().toString();
        String grossscore = gross_score3.getText().toString();
        String puttScore = puttsnumber3.getText().toString();

        if (grossscore.equalsIgnoreCase("-")){
            gs = 0;
        }else {
            gs = Integer.parseInt(grossscore);
        }
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }

        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        if (puttScore.equalsIgnoreCase("-")){
            puttsnumber3.setText("0");
        }else {

            if (put < gs - 1) {

                if ((san + put + 1) == gs) {
                    sandMinus3();
                    puttsnumber3.setText("" + (put + 1));

                } else {
                    puttsnumber3.setText("" + (put + 1));
                }

            }
        }
    }

    public void puttMinus3() {
        String puttnum = puttsnumber3.getText().toString();
        if (puttnum.equals("-")) {
            Toast.makeText(this, "No Selection", Toast.LENGTH_SHORT).show();
        } else {
            int p_score = Integer.parseInt(puttnum);
            puttCounter3 = p_score - 1;

            if (puttCounter3 < 0) {

                puttsnumber3.setText("-");
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();
            } else {
                puttsnumber3.setText("" + puttCounter3);
            }
        }
    }

    public void sandMinus4() {
        String sandnum = sandEditText4.getText().toString();

        if (sandnum.equals("-")) {
            Toast.makeText(this, "No Selection", Toast.LENGTH_SHORT).show();
        } else {
            int s_score = Integer.parseInt(sandnum);
            sandCounter4 = s_score - 1;
            if (sandCounter4 < 0) {

                sandEditText4.setText("-");
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();
            } else {
                sandEditText4.setText("" + sandCounter4);
            }
        }
    }

    public void puttPlus4() {
        int ps = 0;
        int san = 0 , put =0 ,gs = 0;
        String sandnum = sandEditText4.getText().toString();
        String grossscore = gross_score4.getText().toString();
        String puttScore = puttsnumber4.getText().toString();

        if (grossscore.equalsIgnoreCase("-")){
            gs = 0;
        }else {
            gs = Integer.parseInt(grossscore);
        }
        if (sandnum.equalsIgnoreCase("-")){
            san = 0;
        }else {
            san = Integer.parseInt(sandnum);
        }

        if (puttScore.equalsIgnoreCase("-")){
            put = 0;
        }else {
            put = Integer.parseInt(puttScore);
        }

        if (puttScore.equalsIgnoreCase("-")){
            puttsnumber4.setText("0");
        }else {

            if (put < gs - 1) {

                if ((san + put + 1) == gs) {
                    sandMinus4();
                    puttsnumber4.setText("" + (put + 1));

                } else {
                    puttsnumber4.setText("" + (put + 1));
                }

            }
        }
    }

    public void puttMinus4() {
        String puttnum = puttsnumber4.getText().toString();
        if (puttnum.equals("-")) {
            Toast.makeText(this, "No Selection", Toast.LENGTH_SHORT).show();
        } else {
            int p_score = Integer.parseInt(puttnum);
            puttCounter4 = p_score - 1;

            if (puttCounter4 < 0) {

                puttsnumber4.setText("-");
                Toast.makeText(this, "Min Selected", Toast.LENGTH_SHORT).show();
            } else {
                puttsnumber4.setText("" + puttCounter4);
            }
        }
    }

    public void getscorecarddata(String Admin_ID, String Event_Id, String status) {

        Log.e("ADMINID", Admin_ID);
        Log.e("EVENTID", Event_Id);

        final ProgressDialog pDialog = new ProgressDialog(AddScoreNew.this, R.style.MyTheme);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("admin_id", Admin_ID);
            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("delegated_status", status);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GETSCORECARDDATA, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                requestaddscorevalue(response);

                Log.e("ScoreCrad CLASS", "scorecard data" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(AddScoreNew.this, "Error in " + "scorecard data URL = " + PUTTAPI.GETSCORECARDDATA);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void requestaddscorevalue(JSONObject response) {


        tabname = new ArrayList<String>();
        tabShortName = new ArrayList<String>();
        tabPlayerColor = new ArrayList<String>();

        lastholeplayed = new ArrayList<String>();
        tabplayer_id = new ArrayList<String>();
        tabhandicap = new ArrayList<String>();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                String golf_course_name = jsonObject.getString("golf_course_name");
                String events_name = jsonObject.getString("event_name");

                totalPlayers = jsonObject.getString("total_players");

                is4PlusGame = jsonObject.getString("is_4plus_game");

                format_id = jsonObject.getString("format_id");

                golf_course_id = jsonObject.getString("golf_course_id");

                parvalue_ = jsonObject.getString("par_value");
                String indexvalue_ = jsonObject.getString("hole_index");

                String hole_number = jsonObject.getString("hole_start_from");
                String total_hole_num = jsonObject.getString("total_hole_num");

                holeValidation = hole_number;
                totalHoleValidation = total_hole_num;


                isSpot = jsonObject.getString("is_spot_type");

                if (hole_number != null) {
                    holeNO = Integer.parseInt(hole_number);
                }
                addValues_next(events_name, golf_course_name);

                sethole_index_par(String.valueOf(holeNO), indexvalue_, parvalue_);


                if (status.equalsIgnoreCase("1")) {

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    int i = jsonArray.length();

                    totalPlayer = i;


                    Log.e("ArrayLength", totalPlayer + "");


                    for (int j = 0; j < i; j++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(j);

                        event_adminID = jsonObject1.getString("event_admin_id");

                        String fullname = jsonObject1.getString("full_name");
                        String short_name = jsonObject1.getString("short_name");

                        if (jsonObject1.has("player_color_code")) {
                            playerColor = jsonObject1.getString("player_color_code");
                        }

                        String handicapnum = jsonObject1.getString("self_handicap");
                        String player_id = jsonObject1.getString("player_id");
                        String last_hole_played = jsonObject1.getString("last_hole_played");

                        if (last_hole_played != null) {
                            if (total_hole_num.equalsIgnoreCase("18") && last_hole_played.equalsIgnoreCase("18")) {
                                lastHole = Integer.parseInt(last_hole_played);
                            } else if (total_hole_num.equalsIgnoreCase("9") && last_hole_played.equalsIgnoreCase("9")) {
                                lastHole = Integer.parseInt(last_hole_played);
                            } else if (total_hole_num.equalsIgnoreCase("9") && last_hole_played.equalsIgnoreCase("18")) {
                                lastHole = Integer.parseInt(last_hole_played);
                            } else {
                                lastHole = Integer.parseInt(last_hole_played) + 1;
                            }
                        }
                        lastholeplayed.add(String.valueOf(lastHole));
                        tabname.add(fullname);
                        tabShortName.add(short_name);
                        tabPlayerColor.add(playerColor);

                        tabhandicap.add(handicapnum);
                        tabplayer_id.add(player_id);

                    }
                    if (!String.valueOf(lastHole).equalsIgnoreCase("1")) {
                        hole.setText(String.valueOf(lastHole));
                    }

                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(AddScoreNew.this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < tabShortName.size(); i++) {
                if (i == 0) {
                    player1Layout.setVisibility(View.VISIBLE);
                    player1_name.setText(tabname.get(i));
                    handicap1.setText("(" + tabhandicap.get(i) + ")");

                    player1Color.setBackgroundColor(Color.parseColor(tabPlayerColor.get(i)));


                } else if (i == 1) {
                    player2Layout.setVisibility(View.VISIBLE);
                    player2_name.setText(tabname.get(i));
                    handicap2.setText("(" + tabhandicap.get(i) + ")");
                    player2Color.setBackgroundColor(Color.parseColor(tabPlayerColor.get(i)));
                } else if (i == 2) {
                    player3Layout.setVisibility(View.VISIBLE);
                    player3_name.setText(tabname.get(i));
                    handicap3.setText("(" + tabhandicap.get(i) + ")");
                    player3Color.setBackgroundColor(Color.parseColor(tabPlayerColor.get(i)));
                } else if (i == 3) {
                    player4Layout.setVisibility(View.VISIBLE);
                    player4_name.setText(tabname.get(i));
                    handicap4.setText("(" + tabhandicap.get(i) + ")");
                    player4Color.setBackgroundColor(Color.parseColor(tabPlayerColor.get(i)));
                }

            }

            if (totalPlayer == 1) {
                expandLay1.setVisibility(View.VISIBLE);

                pageIndicator.setVisibility(View.INVISIBLE);
            } else {
                expandLay1.setVisibility(View.GONE);
            }

            getParIndexValue1(golf_course_id, geteventID(), getholevalue());


        }

    }

    public void addValues_next(String name, String golfname) {
        title.setText(name.toUpperCase());
        // Golf_Course_Name.setText(golfname.toUpperCase());
    }

    public String getTotalHoleValidation() {
        return totalHoleValidation;
    }

    public String getHoleValidation() {
        return holeValidation;
    }

    public void sethole_index_par(String hole_number, String indexvalue, String par_value) {
        par.setText("PAR " + par_value);
        // Index_Value.setText(indexvalue);

        hole = (TextView) findViewById(R.id.add_score_new_holeno);
        hole.setText(hole_number);
    }

    public void open_nextview() {

        String format_ID = format_id;
        if (format_ID.equalsIgnoreCase("10") || format_ID.equalsIgnoreCase("12") || format_ID.equalsIgnoreCase("13") || format_ID.equalsIgnoreCase("14")) {
            String eventID = geteventID();
            startActivity(new Intent(getApplicationContext(), NewGameScoreCard.class).putExtra("eventID", eventID));
        } else if (format_ID.equalsIgnoreCase("11")) {
            String eventID = geteventID();
            startActivity(new Intent(getApplicationContext(), AutoPressScoreCard.class).putExtra("eventID", eventID));
        } else {

            String eventID = geteventID();
            startActivity(new Intent(getApplicationContext(), ScoreTable.class).putExtra("eventID", eventID).putExtra("playerID", "noneed"));
        }
    }

    public void endRound() {
        final Dialog dialog1 = new Dialog(AddScoreNew.this);
        dialog1.setCanceledOnTouchOutside(true);
        Window window = dialog1.getWindow();
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.dimAmount = 0.7f;
        dialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog1.setContentView(R.layout.popup_save_end);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView dialogText = (TextView) dialog1.findViewById(R.id.popup_preview);
        RelativeLayout deleteBTN = (RelativeLayout) dialog1.findViewById(R.id.delete_popup);
        RelativeLayout saveBTN = (RelativeLayout) dialog1.findViewById(R.id.save_popup);

        RelativeLayout modifyBTN = (RelativeLayout) dialog1.findViewById(R.id.modify_popup);

        String popUpMsg = "Do you want to end this round? Select Save Round to save this round otherwise Delete Round to delete score for the event.";

        dialogText.setText(popUpMsg);
        dialog1.show();
        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.cancel();
                SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                final String admin_ID = pref.getString("userId", null);
                String eventID = geteventID();
                String playerID = tabplayer_id.get(0);

                if (banner_img != null && banner_img.length() > 10) {
                    bannerFullMethod(banner_img, banner_url, admin_ID, eventID, playerID);
                } else {
                    saveRoundRequest(admin_ID, eventID, playerID);
                }

            }
        });

        deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.cancel();
                SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                final String admin_ID = pref.getString("userId", null);
                String eventID = geteventID();
                String playerID = tabplayer_id.get(0);
                endRoundRequest(admin_ID, eventID, playerID);


            }
        });

        modifyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.cancel();
            }
        });
    }


    public void open_dialog() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);

        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        dialog.setContentView(R.layout.pop_up_scorelist);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout scorecard = (LinearLayout) dialog.findViewById(R.id.scorecard_row_dialog);
        LinearLayout end_row = (LinearLayout) dialog.findViewById(R.id.end_round_row_dialog);
        LinearLayout delegate = (LinearLayout) dialog.findViewById(R.id.delegate_row_dialog);
        leaderboard = (LinearLayout) dialog.findViewById(R.id.leaderboard_row_dialog);

        if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

            leaderboard.setVisibility(View.GONE);

        } else {

            if (is4PlusGame !=null && is4PlusGame.equalsIgnoreCase("1")){

                leaderboard.setVisibility(View.VISIBLE);

            }else {

                if (totalPlayers.equalsIgnoreCase("1")) {

                    leaderboard.setVisibility(View.GONE);
                } else {
                    leaderboard.setVisibility(View.VISIBLE);
                }
            }
        }


        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                String eventID = geteventID();
                startActivity(new Intent(getApplicationContext(), Leaderboard.class).putExtra("eventID", eventID));


            }
        });


        scorecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                open_nextview();
            }
        });
        end_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();

                final Dialog dialog1 = new Dialog(AddScoreNew.this);
                dialog1.setCanceledOnTouchOutside(true);
                Window window = dialog1.getWindow();
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);

                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                wlp.dimAmount = 0.7f;
                dialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);


                // Include dialog.xml file
                dialog1.setContentView(R.layout.popup_save_end);

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView dialogText = (TextView) dialog1.findViewById(R.id.popup_preview);
                RelativeLayout deleteBTN = (RelativeLayout) dialog1.findViewById(R.id.delete_popup);
                RelativeLayout saveBTN = (RelativeLayout) dialog1.findViewById(R.id.save_popup);

                RelativeLayout modifyBTN = (RelativeLayout) dialog1.findViewById(R.id.modify_popup);

                String popUpMsg = "Do you want to end this round? Select Save Round to save this round otherwise Delete Round to delete score for the event.";

                dialogText.setText(popUpMsg);
                dialog1.show();
                saveBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.cancel();
                        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                        final String admin_ID = pref.getString("userId", null);
                        String eventID = geteventID();
                        String playerID = tabplayer_id.get(0);
                        if (banner_img != null && banner_img.length() > 10) {
                            bannerFullMethod(banner_img, banner_url, admin_ID, eventID, playerID);
                        } else {
                            saveRoundRequest(admin_ID, eventID, playerID);
                        }

                    }
                });

                deleteBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.cancel();
                        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                        final String admin_ID = pref.getString("userId", null);
                        String eventID = geteventID();
                        String playerID = tabplayer_id.get(0);
                        endRoundRequest(admin_ID, eventID, playerID);


                    }
                });

                modifyBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.cancel();
                    }
                });


            }
        });

        delegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                String eventID = geteventID();
                String format_ID = format_id;
                startActivity(new Intent(getApplicationContext(), Delegates.class).putExtra("eventID", eventID).putExtra("formatID", format_ID));

            }
        });

        ImageView closeBTN = (ImageView) dialog.findViewById(R.id.close_dialog_score);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


        dialog.show();
    }


    public void open_hole_num_selection_dialog(String current_hole) {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCanceledOnTouchOutside(true);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;

        //  window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.TOP;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        //  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Include dialog.xml file
        dialog.setContentView(R.layout.select_hole_add_score);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        dialog.show();

        TextView CurrentHole = (TextView) dialog.findViewById(R.id.current_hole_number);


        hole1 = (TextView) dialog.findViewById(R.id.hole1_text);
        hole2 = (TextView) dialog.findViewById(R.id.hole2_text);
        hole3 = (TextView) dialog.findViewById(R.id.hole3_text);
        hole4 = (TextView) dialog.findViewById(R.id.hole4_text);
        hole5 = (TextView) dialog.findViewById(R.id.hole5_text);
        hole6 = (TextView) dialog.findViewById(R.id.hole6_text);
        hole7 = (TextView) dialog.findViewById(R.id.hole7_text);
        hole8 = (TextView) dialog.findViewById(R.id.hole8_text);
        hole9 = (TextView) dialog.findViewById(R.id.hole9_text);
        hole10 = (TextView) dialog.findViewById(R.id.hole10_text);
        hole11 = (TextView) dialog.findViewById(R.id.hole11_text);
        hole12 = (TextView) dialog.findViewById(R.id.hole12_text);
        hole13 = (TextView) dialog.findViewById(R.id.hole13_text);
        hole14 = (TextView) dialog.findViewById(R.id.hole14_text);
        hole15 = (TextView) dialog.findViewById(R.id.hole15_text);
        hole16 = (TextView) dialog.findViewById(R.id.hole16_text);
        hole17 = (TextView) dialog.findViewById(R.id.hole17_text);
        hole18 = (TextView) dialog.findViewById(R.id.hole18_text);

        CurrentHole.setText(current_hole);


        String totalHole = getTotalHoleValidation();
        String holeStartFrom = getHoleValidation();
        int startFrom = Integer.parseInt(holeStartFrom);


        if (totalHole.equalsIgnoreCase("18")) {

        } else {
            if (startFrom < 10) {

                hole10.setVisibility(View.GONE);
                hole11.setVisibility(View.GONE);
                hole12.setVisibility(View.GONE);
                hole13.setVisibility(View.GONE);
                hole14.setVisibility(View.GONE);
                hole15.setVisibility(View.GONE);
                hole16.setVisibility(View.GONE);
                hole17.setVisibility(View.GONE);
                hole18.setVisibility(View.GONE);
            } else {

                hole1.setVisibility(View.GONE);
                hole2.setVisibility(View.GONE);
                hole3.setVisibility(View.GONE);
                hole4.setVisibility(View.GONE);
                hole5.setVisibility(View.GONE);
                hole6.setVisibility(View.GONE);
                hole7.setVisibility(View.GONE);
                hole8.setVisibility(View.GONE);
                hole9.setVisibility(View.GONE);


            }
        }

        hole1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole1.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole1.getText().toString();
                }


                }
        });

        hole2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                flag = true;
                setCounthole(hole2.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole2.getText().toString();
                }


            }
        });

        hole3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole3.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole3.getText().toString();
                }

            }
        });

        hole4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                flag = true;
                setCounthole(hole4.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole4.getText().toString();
                }

            }
        });

        hole5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                flag = true;
                setCounthole(hole5.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole5.getText().toString();
                }

            }
        });

        hole6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                flag = true;
                setCounthole(hole6.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole6.getText().toString();
                }

            }
        });

        hole7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole7.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole7.getText().toString();
                }
            }
        });

        hole8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                flag = true;
                setCounthole(hole8.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole8.getText().toString();
                }
            }
        });

        hole9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                flag = true;
                setCounthole(hole9.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole9.getText().toString();
                }

            }
        });

        hole10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole10.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole10.getText().toString();
                }
            }
        });

        hole11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole11.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole11.getText().toString();
                }
            }
        });

        hole12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole12.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole12.getText().toString();
                }
            }
        });

        hole13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole13.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole13.getText().toString();
                }
            }
        });

        hole14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole14.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole14.getText().toString();
                }
            }
        });

        hole15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole15.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole15.getText().toString();
                }
            }
        });

        hole16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole16.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole16.getText().toString();
                }
            }
        });

        hole17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                flag = true;
                setCounthole(hole17.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole17.getText().toString();
                }
            }
        });

        hole18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                flag = true;
                setCounthole(hole18.getText().toString());

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                    holeValidation = hole18.getText().toString();
                }
            }
        });

    }

    public void setCounthole(String text) {
        hole = (LatoTextView) findViewById(R.id.add_score_new_holeno);
        ObjectAnimator flip = ObjectAnimator.ofFloat(topHole, "rotationY", 0f, 360f);
        flip.setDuration(2000);
        flip.start();
        hole.setText(text);
        String eventID = geteventID();
        String golfID = golf_course_id;


        getParIndexValue(golfID, eventID, text);


    }


    public void getParIndexValue(String Golf_ID, String Event_Id, String hole_numbe) {

        holeNumber = hole_numbe;

        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        final String user_ID = pref.getString("userId", null);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("golf_course_id", Golf_ID);
            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("hole_number", hole_numbe);

            JSONArray jsId = new JSONArray();
            //JSONObject jsOb = new JSONObject();
            for (int p = 0; p < tabplayer_id.size(); p++) {
                jsId.put(tabplayer_id.get(p));
            }
            jsonObject.putOpt("player_ids", jsId);

            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("par index testing", jsonObject.toString());

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GET_PAR_INDEX_VALUE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                requestParIndexValue(response);
                Log.e("GET_PAR_INDEX_", "GET_PAR_INDEX_VALUE" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Utility.showLogError(this, "Error in " + "GET_PAR_INDEX_VALUE URL = " + PUTTAPI.GET_PAR_INDEX_VALUE);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void requestParIndexValue(JSONObject response) {

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");

                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                String holeindex = jsonObject1.getString("hole_index");
                parvalue_ = jsonObject1.getString("par_value");
                isSpot = jsonObject1.getString("is_spot_type");

                isDelegate = jsonObject1.getString("is_delegated");

                if (isSpot.equalsIgnoreCase("0")) {
                    spotPrizeLayout1.setVisibility(View.GONE);
                    spotPrizeLayout2.setVisibility(View.GONE);
                    spotPrizeLayout3.setVisibility(View.GONE);
                    spotPrizeLayout4.setVisibility(View.GONE);
                    feetValue1.setText("");
                    feetValue2.setText("");
                    feetValue3.setText("");
                    feetValue4.setText("");

                } else if (isSpot.equalsIgnoreCase("1")) {
                    spotPrizeLayout1.setVisibility(View.VISIBLE);
                    spotPrizeText1.setText("CLOSEST TO PIN");
                    feetText1.setText("FEET");

                    feetValue1.setText("");

                    spotPrizeLayout2.setVisibility(View.VISIBLE);
                    spotPrizeText2.setText("CLOSEST TO PIN");
                    feetText2.setText("FEET");
                    feetValue2.setText("");

                    spotPrizeLayout3.setVisibility(View.VISIBLE);
                    spotPrizeText3.setText("CLOSEST TO PIN");
                    feetText3.setText("FEET");
                    feetValue3.setText("");

                    spotPrizeLayout4.setVisibility(View.VISIBLE);
                    spotPrizeText4.setText("CLOSEST TO PIN");
                    feetText4.setText("FEET");
                    feetValue4.setText("");

                } else if (isSpot.equalsIgnoreCase("2")) {
                    spotPrizeLayout1.setVisibility(View.VISIBLE);
                    spotPrizeText1.setText("STRAIGHT DRIVE");
                    feetText1.setText("FEET");
                    feetValue1.setText("");

                    spotPrizeLayout2.setVisibility(View.VISIBLE);
                    spotPrizeText2.setText("STRAIGHT DRIVE");
                    feetText2.setText("FEET");
                    feetValue2.setText("");

                    spotPrizeLayout3.setVisibility(View.VISIBLE);
                    spotPrizeText3.setText("STRAIGHT DRIVE");
                    feetText3.setText("FEET");
                    feetValue3.setText("");

                    spotPrizeLayout4.setVisibility(View.VISIBLE);
                    spotPrizeText4.setText("STRAIGHT DRIVE");
                    feetText4.setText("FEET");
                    feetValue4.setText("");

                } else if (isSpot.equalsIgnoreCase("3")) {
                    spotPrizeLayout1.setVisibility(View.VISIBLE);
                    spotPrizeText1.setText("LONG DRIVE");
                    feetText1.setText("YARD");
                    feetValue1.setText("");

                    spotPrizeLayout2.setVisibility(View.VISIBLE);
                    spotPrizeText2.setText("LONG DRIVE");
                    feetText2.setText("YARD");
                    feetValue2.setText("");

                    spotPrizeLayout3.setVisibility(View.VISIBLE);
                    spotPrizeText3.setText("LONG DRIVE");
                    feetText3.setText("YARD");
                    feetValue3.setText("");

                    spotPrizeLayout4.setVisibility(View.VISIBLE);
                    spotPrizeText4.setText("LONG DRIVE");
                    feetText4.setText("YARD");
                    feetValue4.setText("");


                }

                String errorMessage = jsonObject.getString("message");

                String is_team = jsonObject1.getString("is_team");

                JSONArray jsonArray = jsonObject.getJSONArray("total");

                value_saved = new ArrayList<String>();
                fare_savedArray = new ArrayList<String>();
                putt_savedArray = new ArrayList<String>();
                sand_savedArray = new ArrayList<String>();
                isTemp_savedArray = new ArrayList<String>();
                // fare_savedArray = new ArrayList<String>();


                inch_savedArray = new ArrayList<String>();
                feet_savedArray = new ArrayList<String>();

                // fare_savedArray = new ArrayList<String>();

                for (int i = 0; i < jsonArray.length(); i++) {

                    score_value_saved = jsonArray.getJSONObject(i).getString("score_value");
                    value_saved.add(score_value_saved);

                    String feet_saved1 = jsonArray.getJSONObject(i).getString("closest_feet");
                    feet_savedArray.add(feet_saved1);

                    String inch_saved1 = jsonArray.getJSONObject(i).getString("closest_inch");
                    inch_savedArray.add(inch_saved1);

                    String no_of_putt1 = jsonArray.getJSONObject(i).getString("no_of_putt");
                    putt_savedArray.add(no_of_putt1);

                    String sand1 = jsonArray.getJSONObject(i).getString("sand");
                    sand_savedArray.add(sand1);

                    String fairway1 = jsonArray.getJSONObject(i).getString("fairway");
                    fare_savedArray.add(fairway1);

                    isTemp = jsonArray.getJSONObject(i).getString("is_temporary");

                    no_of_hole_played = jsonArray.getJSONObject(i).getString("no_of_holes_played");
                    // isTemp_savedArray.add(isTemp);

                    String playerID = playerID(0);
                    String play_id = jsonArray.getJSONObject(i).getString("player_id");
                    if (playerID.equalsIgnoreCase(play_id)) {

                        //   no_of_hole_played = jsonArray.getJSONObject(i).getString("no_of_holes_played");
                        String is_handicap_gain = jsonArray.getJSONObject(i).getString("is_handicap_gain");
                        String no_of_putt = jsonArray.getJSONObject(i).getString("no_of_putt");
                        String sand = jsonArray.getJSONObject(i).getString("sand");
                        String fairway = jsonArray.getJSONObject(i).getString("fairway");
                        String current_hole_number = jsonArray.getJSONObject(i).getString("current_hole_number");

                        int hollll = Integer.parseInt(current_hole_number) - 1;

                        String playerName = jsonArray.getJSONObject(i).getString("player_name");


                        if (is_handicap_gain.equalsIgnoreCase("1")) {
                            //  s_symbol.setVisibility(View.VISIBLE);
                        } else {
                            // s_symbol.setVisibility(View.INVISIBLE);
                        }

                    }
                    if (i == 0) {

                        String current_hole_number = jsonArray.getJSONObject(i).getString("current_hole_number");

                        int hollll = Integer.parseInt(current_hole_number) - 1;
                        String playerName = jsonArray.getJSONObject(i).getString("player_name");

                        if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {


                            if (is_team.equalsIgnoreCase("0")) {
                                winner1 = jsonArray.getJSONObject(i).getString("player_id");
                                teamA_Exp.setText(playerName);
                                player1A_Exp.setVisibility(View.GONE);
                                player2A_Exp.setVisibility(View.GONE);

                                JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                                for (int m = 0; m < last_score.length(); m++) {
                                    String scoreValue = last_score.getJSONObject(m).getString("score_value");
                                    String winner = last_score.getJSONObject(m).getString("winner");

                                    current_winner = winner;
                                    String color = last_score.getJSONObject(m).getString("color");

                                    hole_Expand.setText(no_of_hole_played);
                                    standings_Expand.setText(scoreValue);

                                }
                            }

                        } else if (format_id.equalsIgnoreCase("11")) {

                            if (is_team.equalsIgnoreCase("0")) {
                                JSONArray last_score = jsonArray.getJSONObject(0).getJSONArray("last_score");

                                if (last_score.length() > 0) {

                                    JSONObject currObject = last_score.getJSONObject(0);


                                    //  String curentHole = currObject.getString("hole_number");


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

                                    for (int s = 0; s < scoreArrayfront.size(); s++) {

                                        autopressLayout.setVisibility(View.VISIBLE);

                                        if (s == 0) {
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


                                        } else if (s == 1) {
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

                                        } else if (s == 2) {
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
                                        } else if (s == 3) {
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
                                        } else if (s == 4) {
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
                                        } else if (s == 5) {
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
                                        } else if (s == 6) {
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
                                        } else if (s == 7) {
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

                                        } else if (s == 8) {
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
                                        } else if (s == 9) {
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

                                    if (scoreArrayback.size() < 1) {
                                        back.setVisibility(View.GONE);
                                    } else {
                                        back.setVisibility(View.VISIBLE);
                                        for (int s = 0; s < scoreArrayback.size(); s++) {

                                            autopressLayout.setVisibility(View.VISIBLE);

                                            if (s == 0) {

                                                back1.setText(scoreArrayback.get(s));
                                                back1.setTextColor(Color.parseColor(colorArrayback.get(s)));

                                                back1.setVisibility(View.VISIBLE);
                                                back2.setVisibility(View.GONE);
                                                back3.setVisibility(View.GONE);
                                                back4.setVisibility(View.GONE);
                                                back5.setVisibility(View.GONE);
                                                back6.setVisibility(View.GONE);
                                                back7.setVisibility(View.GONE);
                                                back8.setVisibility(View.GONE);
                                                back9.setVisibility(View.GONE);


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

                            }


                        } else if (format_id.equalsIgnoreCase("12")) {

                            JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                            for (int m = 0; m < last_score.length(); m++) {
                                layout_340_scorcard.setVisibility(View.VISIBLE);
                                String scoreValue = last_score.getJSONObject(m).getString("score_value");

                                player1_420score.setText(scoreValue);
                                player1_420name.setText(playerName);

                                play1_340_lay.setBackgroundColor(Color.parseColor(tabPlayerColor.get(0)));

                            }
                        }


                    } else if (i == 1) {

                        if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                            if (is_team.equalsIgnoreCase("0")) {

                                JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                                for (int m = 0; m < last_score.length(); m++) {
                                    String scoreValue = last_score.getJSONObject(m).getString("score_value");
                                    String winner = last_score.getJSONObject(m).getString("winner");
                                    current_winner = winner;
                                }


                                String playerName = jsonArray.getJSONObject(i).getString("player_name");
                                winner2 = jsonArray.getJSONObject(i).getString("player_id");
                                teamB_Exp.setText(playerName);
                                player1B_Exp.setVisibility(View.GONE);
                                player2B_Exp.setVisibility(View.GONE);
                            }
                        } else if (format_id.equalsIgnoreCase("11")) {


                            if (is_team.equalsIgnoreCase("0")) {

                                JSONArray last_score = jsonArray.getJSONObject(0).getJSONArray("last_score");

                                if (last_score.length() > 0) {

                                    JSONObject currObject = last_score.getJSONObject(0);


                                    //  String curentHole = currObject.getString("hole_number");


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

                                    for (int s = 0; s < scoreArrayfront.size(); s++) {

                                        autopressLayout.setVisibility(View.VISIBLE);

                                        if (s == 0) {
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


                                        } else if (s == 1) {
                                            front2.setText(scoreArrayfront.get(s));
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

                                        } else if (s == 2) {
                                            front3.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 3) {
                                            front4.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 4) {
                                            front5.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 5) {
                                            front6.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 6) {
                                            front7.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 7) {
                                            front8.setText(scoreArrayfront.get(s));
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

                                        } else if (s == 8) {
                                            front9.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 9) {
                                            front10.setText(scoreArrayfront.get(s));
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

                                    if (scoreArrayback.size() < 1) {
                                        back.setVisibility(View.GONE);
                                    } else {
                                        back.setVisibility(View.VISIBLE);
                                        for (int s = 0; s < scoreArrayback.size(); s++) {

                                            autopressLayout.setVisibility(View.VISIBLE);

                                            if (s == 0) {

                                                back1.setText(scoreArrayback.get(s));
                                                back1.setTextColor(Color.parseColor(colorArrayback.get(s)));

                                                back1.setVisibility(View.VISIBLE);
                                                back2.setVisibility(View.GONE);
                                                back3.setVisibility(View.GONE);
                                                back4.setVisibility(View.GONE);
                                                back5.setVisibility(View.GONE);
                                                back6.setVisibility(View.GONE);
                                                back7.setVisibility(View.GONE);
                                                back8.setVisibility(View.GONE);
                                                back9.setVisibility(View.GONE);


                                            } else if (s == 1) {
                                                back2.setText(scoreArrayback.get(s));
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
                                                back3.setText(scoreArrayback.get(s));
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
                                                back4.setText(scoreArrayback.get(s));
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
                                                back5.setText(scoreArrayback.get(s));
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
                                                back6.setText(scoreArrayback.get(s));
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
                                                back7.setText(scoreArrayback.get(s));
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
                                                back8.setText(scoreArrayback.get(s));
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
                                                back9.setText(scoreArrayback.get(s));
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
                            }

                        } else if (format_id.equalsIgnoreCase("12")) {

                            String playerName = jsonArray.getJSONObject(i).getString("player_name");
                            JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                            for (int m = 0; m < last_score.length(); m++) {
                                String scoreValue = last_score.getJSONObject(m).getString("score_value");

                                layout_340_scorcard.setVisibility(View.VISIBLE);
                                player2_420score.setText(scoreValue);
                                player2_420name.setText(playerName);
                                play2_340_lay.setBackgroundColor(Color.parseColor(tabPlayerColor.get(1)));
                            }
                        }
                    } else if (i == 2) {

                        if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                            if (is_team.equalsIgnoreCase("0")) {

                                JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                                for (int m = 0; m < last_score.length(); m++) {
                                    String scoreValue = last_score.getJSONObject(m).getString("score_value");
                                    String winner = last_score.getJSONObject(m).getString("winner");
                                    current_winner = winner;
                                }


                                String playerName = jsonArray.getJSONObject(i).getString("player_name");
                                winner2 = jsonArray.getJSONObject(i).getString("player_id");
                                teamB_Exp.setText(playerName);
                                player1B_Exp.setVisibility(View.GONE);
                                player2B_Exp.setVisibility(View.GONE);
                            }
                        } else if (format_id.equalsIgnoreCase("11")) {
                            if (is_team.equalsIgnoreCase("0")) {

                                JSONArray last_score = jsonArray.getJSONObject(0).getJSONArray("last_score");

                                JSONObject currObject = last_score.getJSONObject(0);


                                //  String curentHole = currObject.getString("hole_number");
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

                                for (int s = 0; s < scoreArrayfront.size(); s++) {

                                    autopressLayout.setVisibility(View.VISIBLE);

                                    if (s == 0) {
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


                                    } else if (s == 1) {
                                        front2.setText(scoreArrayfront.get(s));
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

                                    } else if (s == 2) {
                                        front3.setText(scoreArrayfront.get(s));
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
                                    } else if (s == 3) {
                                        front4.setText(scoreArrayfront.get(s));
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
                                    } else if (s == 4) {
                                        front5.setText(scoreArrayfront.get(s));
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
                                    } else if (s == 5) {
                                        front6.setText(scoreArrayfront.get(s));
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
                                    } else if (s == 6) {
                                        front7.setText(scoreArrayfront.get(s));
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
                                    } else if (s == 7) {
                                        front8.setText(scoreArrayfront.get(s));
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

                                    } else if (s == 8) {
                                        front9.setText(scoreArrayfront.get(s));
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
                                    } else if (s == 9) {
                                        front10.setText(scoreArrayfront.get(s));
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
                                if (scoreArrayback.size() < 1) {
                                    back.setVisibility(View.GONE);
                                } else {
                                    back.setVisibility(View.VISIBLE);
                                    for (int s = 0; s < scoreArrayback.size(); s++) {

                                        autopressLayout.setVisibility(View.VISIBLE);

                                        if (s == 0) {

                                            back1.setText(scoreArrayback.get(s));
                                            back1.setTextColor(Color.parseColor(colorArrayback.get(s)));

                                            back1.setVisibility(View.VISIBLE);
                                            back2.setVisibility(View.GONE);
                                            back3.setVisibility(View.GONE);
                                            back4.setVisibility(View.GONE);
                                            back5.setVisibility(View.GONE);
                                            back6.setVisibility(View.GONE);
                                            back7.setVisibility(View.GONE);
                                            back8.setVisibility(View.GONE);
                                            back9.setVisibility(View.GONE);


                                        } else if (s == 1) {
                                            back2.setText(scoreArrayback.get(s));
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
                                            back3.setText(scoreArrayback.get(s));
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
                                            back4.setText(scoreArrayback.get(s));
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
                                            back5.setText(scoreArrayback.get(s));
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
                                            back6.setText(scoreArrayback.get(s));
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
                                            back7.setText(scoreArrayback.get(s));
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
                                            back8.setText(scoreArrayback.get(s));
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
                                            back9.setText(scoreArrayback.get(s));
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
                        } else if (format_id.equalsIgnoreCase("12")) {

                            String playerName = jsonArray.getJSONObject(i).getString("player_name");
                            JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                            for (int m = 0; m < last_score.length(); m++) {
                                String scoreValue = last_score.getJSONObject(m).getString("score_value");

                                layout_340_scorcard.setVisibility(View.VISIBLE);
                                player3_420score.setText(scoreValue);
                                player3_420name.setText(playerName);
                                play3_340_lay.setBackgroundColor(Color.parseColor(tabPlayerColor.get(2)));
                            }
                        }
                    }


                }

             /*   if (totalScore1.equalsIgnoreCase("0") && totalScore2.equalsIgnoreCase("0") && totalScore3.equalsIgnoreCase("0")) {
                    downrowscorecard.setVisibility(View.GONE);
                } else {
                    downrowscorecard.setVisibility(View.VISIBLE);
                }*/

                if (isTemp.equalsIgnoreCase("1")){
                    save_text.setText("SAVE");
                }else {
                    save_text.setText("UPDATE");
                }
                par.setText("PAR " + parvalue_);
                //  index_value.setText(holeindex);
                if (value_saved.size() == 1) {
                    gross_score1.setText(value_saved.get(0));

                    if (sand_savedArray.get(0).equalsIgnoreCase("-1")) {
                        sandEditText1.setText("-");
                    } else {
                        sandEditText1.setText(sand_savedArray.get(0));
                    }

                    if (putt_savedArray.get(0).equalsIgnoreCase("-1")) {
                        puttsnumber1.setText("-");
                    } else {
                        puttsnumber1.setText(putt_savedArray.get(0));
                    }

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0) + "'" + inch_savedArray.get(0) + "''");
                        } else {
                            feetValue1.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0));
                        }else {
                            feetValue1.setText("-");
                        }
                    }

                    if (fare_savedArray.get(0).equalsIgnoreCase("4")) {
                        fairwaysLayout1.setVisibility(View.GONE);
                        //  radioGroup1.check(R.id.radio_left1);
                        radioGroup1.clearCheck();
                        //  radioLeft1.setChecked(false);
                        //  radioCenter1.setChecked(false);
                        //  radioRight1.setChecked(false);
                        selected_fairway1 = "0";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("1")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(true);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_left1);
                        // radioGroup1.clearCheck();

                        selected_fairway1 = "1";

                    } else if (fare_savedArray.get(0).equalsIgnoreCase("2")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(true);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_center1);

                        selected_fairway1 = "2";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("3")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                        /*radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(true);*/

                        radioGroup1.check(R.id.radio_right1);

                        selected_fairway1 = "3";
                    } else {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                      /*  radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/
                        radioGroup1.clearCheck();
                        selected_fairway1 = "0";
                    }

                    //  saveTxt.setText("SAVED");
                } else if (value_saved.size() == 2) {
                    gross_score1.setText(value_saved.get(0));
                    gross_score2.setText(value_saved.get(1));

                    if (sand_savedArray.get(0).equalsIgnoreCase("-1")) {
                        sandEditText1.setText("-");
                    } else {
                        sandEditText1.setText(sand_savedArray.get(0));
                    }

                    if (putt_savedArray.get(0).equalsIgnoreCase("-1")) {
                        puttsnumber1.setText("-");
                    } else {
                        puttsnumber1.setText(putt_savedArray.get(0));
                    }
                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0) + "'" + inch_savedArray.get(0) + "''");
                        } else {
                            feetValue1.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0));
                        }else {
                            feetValue1.setText("-");
                        }
                    }

                    if (fare_savedArray.get(0).equalsIgnoreCase("4")) {
                        fairwaysLayout1.setVisibility(View.GONE);

                        radioGroup1.clearCheck();
                        selected_fairway1 = "0";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("1")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(true);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_left1);

                        selected_fairway1 = "1";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("2")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                      /*  radioLeft1.setChecked(false);
                        radioCenter1.setChecked(true);
                        radioRight1.setChecked(false);*/
                        radioGroup1.check(R.id.radio_center1);
                        selected_fairway1 = "2";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("3")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);

                        radioGroup1.check(R.id.radio_right1);

                        selected_fairway1 = "3";
                    } else {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/
                        radioGroup1.clearCheck();
                        selected_fairway1 = "0";
                    }

                    if (sand_savedArray.get(1).equalsIgnoreCase("-1")) {
                        sandEditText2.setText("-");
                    } else {
                        sandEditText2.setText(sand_savedArray.get(1));
                    }

                    if (putt_savedArray.get(1).equalsIgnoreCase("-1")) {
                        puttsnumber2.setText("-");
                    } else {
                        puttsnumber2.setText(putt_savedArray.get(1));
                    }

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1) + "'" + inch_savedArray.get(1) + "''");
                        } else {
                            feetValue2.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1));
                        }else {
                            feetValue2.setText("-");
                        }
                    }


                    if (fare_savedArray.get(1).equalsIgnoreCase("4")) {
                        fairwaysLayout2.setVisibility(View.GONE);

                        radioGroup2.clearCheck();
                        selected_fairway2 = "0";

                    } else if (fare_savedArray.get(1).equalsIgnoreCase("1")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                        radioGroup2.check(R.id.radio_left2);
                        selected_fairway2 = "1";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("2")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                        radioGroup2.check(R.id.radio_center2);
                        selected_fairway2 = "2";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("3")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(true);*/
                        radioGroup2.check(R.id.radio_right2);

                        selected_fairway2 = "3";
                    } else {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                      /*  radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.clearCheck();

                        selected_fairway2 = "0";
                    }

                    //  saveTxt.setText("SAVED");
                } else if (value_saved.size() == 3) {
                    gross_score1.setText(value_saved.get(0));
                    gross_score2.setText(value_saved.get(1));
                    gross_score3.setText(value_saved.get(2));

                    if (sand_savedArray.get(0).equalsIgnoreCase("-1")) {
                        sandEditText1.setText("-");
                    } else {
                        sandEditText1.setText(sand_savedArray.get(0));
                    }

                    if (putt_savedArray.get(0).equalsIgnoreCase("-1")) {
                        puttsnumber1.setText("-");
                    } else {
                        puttsnumber1.setText(putt_savedArray.get(0));
                    }
                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0) + "'" + inch_savedArray.get(0) + "''");
                        } else {
                            feetValue1.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0));
                        }else {
                            feetValue1.setText("-");
                        }
                    }

                    if (fare_savedArray.get(0).equalsIgnoreCase("4")) {
                        fairwaysLayout1.setVisibility(View.GONE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/
                        radioGroup1.clearCheck();
                        selected_fairway1 = "0";

                    } else if (fare_savedArray.get(0).equalsIgnoreCase("1")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);

                        radioGroup1.check(R.id.radio_left1);
                        selected_fairway1 = "1";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("2")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                        /*radioLeft1.setChecked(false);
                        radioCenter1.setChecked(true);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_center1);

                        selected_fairway1 = "2";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("3")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(true);*/

                        radioGroup1.check(R.id.radio_right1);

                        selected_fairway1 = "3";
                    } else {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/

                        radioGroup1.clearCheck();

                        selected_fairway1 = "0";
                    }

                    if (sand_savedArray.get(1).equalsIgnoreCase("-1")) {
                        sandEditText2.setText("-");
                    } else {
                        sandEditText2.setText(sand_savedArray.get(1));
                    }

                    if (putt_savedArray.get(1).equalsIgnoreCase("-1")) {
                        puttsnumber2.setText("-");
                    } else {
                        puttsnumber2.setText(putt_savedArray.get(1));
                    }

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1) + "'" + inch_savedArray.get(1) + "''");
                        } else {
                            feetValue2.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1));
                        }else {
                            feetValue2.setText("-");
                        }
                    }

                    if (fare_savedArray.get(1).equalsIgnoreCase("4")) {
                        fairwaysLayout2.setVisibility(View.GONE);
                        /*radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.clearCheck();

                        selected_fairway2 = "0";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("1")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(true);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.check(R.id.radio_left2);

                        selected_fairway2 = "1";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("2")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                        /*radioLeft2.setChecked(false);
                        radioCenter2.setChecked(true);
                        radioRight2.setChecked(false);*/

                        radioGroup2.check(R.id.radio_center2);

                        selected_fairway2 = "2";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("3")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                        /*radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(true);*/

                        radioGroup2.check(R.id.radio_right2);

                        selected_fairway2 = "3";
                    } else {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.clearCheck();

                        selected_fairway2 = "0";
                    }


                    if (sand_savedArray.get(2).equalsIgnoreCase("-1")) {
                        sandEditText3.setText("-");
                    } else {
                        sandEditText3.setText(sand_savedArray.get(2));
                    }

                    if (putt_savedArray.get(2).equalsIgnoreCase("-1")) {
                        puttsnumber3.setText("-");
                    } else {
                        puttsnumber3.setText(putt_savedArray.get(2));
                    }
                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(2).length() > 0 && !feet_savedArray.get(2).equalsIgnoreCase("-1")) {

                            feetValue3.setText(feet_savedArray.get(2) + "'" + inch_savedArray.get(2) + "''");
                        } else {
                            feetValue3.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(2).length() > 0 && !feet_savedArray.get(2).equalsIgnoreCase("-1")) {
                            feetValue3.setText(feet_savedArray.get(2));
                        }else {
                            feetValue3.setText("-");
                        }
                    }

                    if (fare_savedArray.get(2).equalsIgnoreCase("4")) {
                        fairwaysLayout3.setVisibility(View.GONE);
                        /*radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/

                        radioGroup3.clearCheck();

                        selected_fairway3 = "0";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("1")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                       /* radioLeft3.setChecked(true);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/

                        radioGroup3.check(R.id.radio_left3);

                        selected_fairway3 = "1";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("2")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                       /* radioLeft3.setChecked(false);
                        radioCenter3.setChecked(true);
                        radioRight3.setChecked(false);*/

                        radioGroup3.check(R.id.radio_center3);
                        selected_fairway3 = "2";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("3")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                        /*radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(true);*/

                        radioGroup3.check(R.id.radio_right3);

                        selected_fairway3 = "3";
                    } else {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                       /* radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/

                        radioGroup3.clearCheck();

                        selected_fairway3 = "0";
                    }


                    //  saveTxt.setText("SAVED");
                } else if (value_saved.size() == 4) {
                    gross_score1.setText(value_saved.get(0));
                    gross_score2.setText(value_saved.get(1));
                    gross_score3.setText(value_saved.get(2));
                    gross_score4.setText(value_saved.get(3));
                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0) + "'" + inch_savedArray.get(0) + "''");
                        } else {
                            feetValue1.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0));
                        }else {
                            feetValue1.setText("-");
                        }
                    }

                    if (sand_savedArray.get(0).equalsIgnoreCase("-1")) {
                        sandEditText1.setText("-");
                    } else {
                        sandEditText1.setText(sand_savedArray.get(0));
                    }

                    if (putt_savedArray.get(0).equalsIgnoreCase("-1")) {
                        puttsnumber1.setText("-");
                    } else {
                        puttsnumber1.setText(putt_savedArray.get(0));
                    }

                    if (fare_savedArray.get(0).equalsIgnoreCase("4")) {
                        fairwaysLayout1.setVisibility(View.GONE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/

                        radioGroup1.clearCheck();

                        selected_fairway1 = "0";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("1")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                      /*  radioLeft1.setChecked(true);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_left1);

                        selected_fairway1 = "1";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("2")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(true);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_center1);

                        selected_fairway1 = "2";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("3")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(true);*/
                        radioGroup1.check(R.id.radio_right1);

                        selected_fairway1 = "3";
                    } else {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/
                        radioGroup3.clearCheck();

                        selected_fairway1 = "0";
                    }

                    if (sand_savedArray.get(1).equalsIgnoreCase("-1")) {
                        sandEditText2.setText("-");
                    } else {
                        sandEditText2.setText(sand_savedArray.get(1));
                    }

                    if (putt_savedArray.get(1).equalsIgnoreCase("-1")) {
                        puttsnumber2.setText("-");
                    } else {
                        puttsnumber2.setText(putt_savedArray.get(1));
                    }
                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1) + "'" + inch_savedArray.get(1) + "''");
                        } else {
                            feetValue2.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1));
                        }else {
                            feetValue2.setText("-");
                        }
                    }

                    if (fare_savedArray.get(1).equalsIgnoreCase("4")) {
                        fairwaysLayout2.setVisibility(View.GONE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.clearCheck();

                        selected_fairway2 = "0";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("1")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                        /*radioLeft2.setChecked(true);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.check(R.id.radio_left2);

                        selected_fairway2 = "1";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("2")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(true);
                        radioRight2.setChecked(false);*/
                        radioGroup2.check(R.id.radio_center2);

                        selected_fairway2 = "2";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("3")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(true);*/

                        radioGroup2.check(R.id.radio_right2);
                        selected_fairway2 = "3";
                    } else {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.clearCheck();

                        selected_fairway2 = "0";
                    }


                    if (sand_savedArray.get(2).equalsIgnoreCase("-1")) {
                        sandEditText3.setText("-");
                    } else {
                        sandEditText3.setText(sand_savedArray.get(2));
                    }

                    if (putt_savedArray.get(2).equalsIgnoreCase("-1")) {
                        puttsnumber3.setText("-");
                    } else {
                        puttsnumber3.setText(putt_savedArray.get(2));
                    }

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(2).length() > 0 && !feet_savedArray.get(2).equalsIgnoreCase("-1")) {

                            feetValue3.setText(feet_savedArray.get(2) + "'" + inch_savedArray.get(2) + "''");
                        } else {
                            feetValue3.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(2).length() > 0 && !feet_savedArray.get(2).equalsIgnoreCase("-1")) {
                            feetValue3.setText(feet_savedArray.get(2));
                        }else {
                            feetValue3.setText("-");
                        }
                    }

                    if (fare_savedArray.get(2).equalsIgnoreCase("4")) {
                        fairwaysLayout3.setVisibility(View.GONE);
                        /*radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/

                        radioGroup3.clearCheck();
                        selected_fairway3 = "0";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("1")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                        /*radioLeft3.setChecked(true);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/

                        radioGroup3.check(R.id.radio_left3);

                        selected_fairway3 = "1";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("2")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                        /*radioLeft3.setChecked(false);
                        radioCenter3.setChecked(true);
                        radioRight3.setChecked(false);*/

                        radioGroup3.check(R.id.radio_center3);

                        selected_fairway3 = "2";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("3")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                       /* radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(true);*/
                        radioGroup3.check(R.id.radio_right3);
                        selected_fairway3 = "3";
                    } else {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                       /* radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/
                        radioGroup3.clearCheck();
                        selected_fairway3 = "0";
                    }

                    if (sand_savedArray.get(3).equalsIgnoreCase("-1")) {
                        sandEditText4.setText("-");
                    } else {
                        sandEditText4.setText(sand_savedArray.get(3));
                    }

                    if (putt_savedArray.get(3).equalsIgnoreCase("-1")) {
                        puttsnumber4.setText("-");
                    } else {
                        puttsnumber4.setText(putt_savedArray.get(3));
                    }


                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(3).length() > 0 && !feet_savedArray.get(3).equalsIgnoreCase("-1")) {
                            feetValue4.setText(feet_savedArray.get(3) + "'" + inch_savedArray.get(3) + "''");
                        } else {
                            feetValue4.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(3).length() > 0 && !feet_savedArray.get(3).equalsIgnoreCase("-1")) {
                            feetValue4.setText(feet_savedArray.get(3));
                        }else {
                            feetValue4.setText("-");
                        }
                    }
                    if (fare_savedArray.get(3).equalsIgnoreCase("4")) {
                        fairwaysLayout4.setVisibility(View.GONE);
                        /*radioLeft4.setChecked(false);
                        radioCenter4.setChecked(false);
                        radioRight4.setChecked(false);*/

                        radioGroup4.clearCheck();

                        selected_fairway4 = "0";
                    } else if (fare_savedArray.get(3).equalsIgnoreCase("1")) {
                        fairwaysLayout4.setVisibility(View.VISIBLE);
                        /*radioLeft4.setChecked(true);
                        radioCenter4.setChecked(false);
                        radioRight4.setChecked(false);*/

                        radioGroup4.check(R.id.radio_left4);
                        selected_fairway4 = "1";
                    } else if (fare_savedArray.get(3).equalsIgnoreCase("2")) {
                        fairwaysLayout4.setVisibility(View.VISIBLE);
                       /* radioLeft4.setChecked(false);
                        radioCenter4.setChecked(true);
                        radioRight4.setChecked(false);*/
                        radioGroup4.check(R.id.radio_center4);

                        selected_fairway4 = "2";
                    } else if (fare_savedArray.get(3).equalsIgnoreCase("3")) {
                        fairwaysLayout4.setVisibility(View.VISIBLE);
                       /* radioLeft4.setChecked(false);
                        radioCenter4.setChecked(false);
                        radioRight4.setChecked(true);*/

                        radioGroup4.check(R.id.radio_right4);
                        selected_fairway4 = "3";
                    } else {
                        fairwaysLayout4.setVisibility(View.VISIBLE);
                       /* radioLeft4.setChecked(false);
                        radioCenter4.setChecked(false);
                        radioRight4.setChecked(false);*/
                        radioGroup4.clearCheck();
                        selected_fairway4 = "0";
                    }


                    //  saveTxt.setText("SAVED");
                } else {
                    //   gross_score1.setText(parvalue_);
                    //   gross_score2.setText(parvalue_);
                    //   gross_score3.setText(parvalue_);
                    //  gross_score4.setText(parvalue_);
                    //  saveTxt.setText("SAVE");
                }

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {


                    if (is_team.equalsIgnoreCase("1")) {
                        JSONArray teamArray = jsonObject1.getJSONArray("teamdata");

                        for (int j = 0; j < teamArray.length(); j++) {
                            if (j == 0) {
                                String teamA = teamArray.getJSONObject(j).getString("team_name");
                                winner1 = teamArray.getJSONObject(j).getString("team_id");

                                teamA_Exp.setText(teamA);
                                JSONArray plArray = teamArray.getJSONObject(j).getJSONArray("player_list");
                                for (int p = 0; p < plArray.length(); p++) {

                                    if (p == 0) {
                                        String teamA_player1 = plArray.getJSONObject(p).getString("name");
                                        String teamA_player1_handi = plArray.getJSONObject(p).getString("handicap_value");
                                        player1A_Exp.setText(teamA_player1 + " " + teamA_player1_handi);
                                    }
                                    if (p == 1) {
                                        String teamA_player2 = plArray.getJSONObject(p).getString("name");
                                        String teamA_player2_handi = plArray.getJSONObject(p).getString("handicap_value");
                                        player2A_Exp.setText(teamA_player2 + " " + teamA_player2_handi);
                                    }
                                }
                            }
                            if (j == 1) {
                                String teamB = teamArray.getJSONObject(j).getString("team_name");
                                winner2 = teamArray.getJSONObject(j).getString("team_id");
                                teamB_Exp.setText(teamB);
                                JSONArray plArray = teamArray.getJSONObject(j).getJSONArray("player_list");
                                for (int p = 0; p < plArray.length(); p++) {

                                    if (p == 0) {
                                        String teamB_player1 = plArray.getJSONObject(p).getString("name");
                                        String teamB_player1_handi = plArray.getJSONObject(p).getString("handicap_value");
                                        player1B_Exp.setText(teamB_player1 + " " + teamB_player1_handi);
                                    }
                                    if (p == 1) {
                                        String teamB_player2 = plArray.getJSONObject(p).getString("name");
                                        String teamB_player2_handi = plArray.getJSONObject(p).getString("handicap_value");
                                        player2B_Exp.setText(teamB_player2 + " " + teamB_player2_handi);
                                    }

                                }

                            }
                            if (j == 2) {
                                JSONArray curntArray = teamArray.getJSONObject(j).getJSONArray("current_standing");
                                if (curntArray.length() > 0) {

                                    for (int c = 0; c < curntArray.length(); c++) {

                                        JSONObject curntObj = curntArray.getJSONObject(c);

                                        String holeNUM = curntObj.getString("hole_number");
                                        String scoreCurrent = curntObj.getString("score_value");
                                        current_winner = curntObj.getString("winner");
                                        hole_Expand.setText(no_of_hole_played);
                                        standings_Expand.setText(scoreCurrent);
                                    }
                                }

                            }

                        }
                    }


                } else if (format_id.equalsIgnoreCase("11")) {


                    if (is_team.equalsIgnoreCase("1")) {
                        JSONArray teamArray = jsonObject1.getJSONArray("teamdata");
                        JSONObject data = teamArray.getJSONObject(2);
                        JSONArray jss = data.getJSONArray("current_standing");
                        JSONObject currObject = jss.getJSONObject(0);

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

                        for (int s = 0; s < scoreArrayfront.size(); s++) {

                            autopressLayout.setVisibility(View.VISIBLE);

                            if (s == 0) {
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


                            } else if (s == 1) {
                                front2.setText(scoreArrayfront.get(s));
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

                            } else if (s == 2) {
                                front3.setText(scoreArrayfront.get(s));
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
                            } else if (s == 3) {
                                front4.setText(scoreArrayfront.get(s));
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
                            } else if (s == 4) {
                                front5.setText(scoreArrayfront.get(s));
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
                            } else if (s == 5) {
                                front6.setText(scoreArrayfront.get(s));
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
                            } else if (s == 6) {
                                front7.setText(scoreArrayfront.get(s));
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
                            } else if (s == 7) {
                                front8.setText(scoreArrayfront.get(s));
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

                            } else if (s == 8) {
                                front9.setText(scoreArrayfront.get(s));
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
                            } else if (s == 9) {
                                front10.setText(scoreArrayfront.get(s));
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
                        if (scoreArrayback.size() < 1) {
                            back.setVisibility(View.GONE);
                        } else {
                            back.setVisibility(View.VISIBLE);
                            for (int s = 0; s < scoreArrayback.size(); s++) {

                                autopressLayout.setVisibility(View.VISIBLE);

                                if (s == 0) {

                                    back1.setText(scoreArrayback.get(s));
                                    back1.setTextColor(Color.parseColor(colorArrayback.get(s)));

                                    back1.setVisibility(View.VISIBLE);
                                    back2.setVisibility(View.GONE);
                                    back3.setVisibility(View.GONE);
                                    back4.setVisibility(View.GONE);
                                    back5.setVisibility(View.GONE);
                                    back6.setVisibility(View.GONE);
                                    back7.setVisibility(View.GONE);
                                    back8.setVisibility(View.GONE);
                                    back9.setVisibility(View.GONE);


                                } else if (s == 1) {
                                    back2.setText(scoreArrayback.get(s));
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
                                    back3.setText(scoreArrayback.get(s));
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
                                    back4.setText(scoreArrayback.get(s));
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
                                    back5.setText(scoreArrayback.get(s));
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
                                    back6.setText(scoreArrayback.get(s));
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
                                    back7.setText(scoreArrayback.get(s));
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
                                    back8.setText(scoreArrayback.get(s));
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
                                    back9.setText(scoreArrayback.get(s));
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

                } else if (format_id.equalsIgnoreCase("12")) {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

          /*  bottom_next.setVisibility(View.GONE);
            bottom_back.setVisibility(View.GONE);*/

            if (no_of_hole_played.equalsIgnoreCase("18") && (totalHoleValidation.equalsIgnoreCase("18"))) {
                scoreCardText.setText("END ROUND");

                scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        endRound();
                    }
                });


            }else  if (no_of_hole_played.equalsIgnoreCase("9") && (totalHoleValidation.equalsIgnoreCase("9"))) {
                scoreCardText.setText("END ROUND");

                scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        endRound();
                    }
                });


            }
            else {

                scoreCardText.setText("SCORECARD");
                scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        open_nextview();
                    }
                });
            }
        } else {

            bottom_next.setVisibility(View.VISIBLE);
            bottom_back.setVisibility(View.VISIBLE);

            if (no_of_hole_played.equalsIgnoreCase("18") && (totalHoleValidation.equalsIgnoreCase("18"))) {
                scoreCardText.setText("END ROUND");

                scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        endRound();
                    }
                });


            }else  if (no_of_hole_played.equalsIgnoreCase("9") && (totalHoleValidation.equalsIgnoreCase("9"))) {
                scoreCardText.setText("END ROUND");

                scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        endRound();
                    }
                });


            } else {

                if (is4PlusGame!=null && is4PlusGame.equalsIgnoreCase("0")) {

                    if (totalPlayers.equalsIgnoreCase("1")) {

                        scoreCardText.setText("SCORECARD");

                        scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String eventid = getIntent().getStringExtra("eventID").toString();

                                startActivity(new Intent(getApplicationContext(), ScoreTable.class).putExtra("eventID", eventid).putExtra("playerID", "noneed"));
                                //  finish();
                            }
                        });

                    }else {

                        scoreCardText.setText("LEADERBOARD");

                        scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String eventid = getIntent().getStringExtra("eventID").toString();

                                Intent intent = new Intent(getApplicationContext(), Leaderboard.class);
                                intent.putExtra("eventID", eventid);
                                startActivity(intent);
                                //  finish();
                            }
                        });
                    }
                }else {
                    scoreCardText.setText("LEADERBOARD");

                    scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String eventid = getIntent().getStringExtra("eventID").toString();

                            Intent intent = new Intent(getApplicationContext(), Leaderboard.class);
                            intent.putExtra("eventID", eventid);
                            startActivity(intent);
                            //  finish();
                        }
                    });
                }
            }
        }

        Log.v("winner", "cur" + current_winner + "win1" + winner1 + "win2" + winner2);

        if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {


            if (current_winner != null && current_winner.length() != 0) {
                teamtabLayout.setVisibility(View.VISIBLE);
                layout_340_scorcard.setVisibility(View.GONE);
                autopressLayout.setVisibility(View.GONE);
                if (current_winner.equalsIgnoreCase(winner1)) {
                    winnerBackgroundLay.setBackgroundResource(R.mipmap.indicate_blue);
                } else if (current_winner.equalsIgnoreCase(winner2)) {
                    winnerBackgroundLay.setBackgroundResource(R.mipmap.indicate_red);

                } else {
                    winnerBackgroundLay.setBackgroundResource(R.color.black_color);
                }
            } else {
                teamtabLayout.setVisibility(View.GONE);
                layout_340_scorcard.setVisibility(View.GONE);
                autopressLayout.setVisibility(View.GONE);
            }
        } else if (format_id.equalsIgnoreCase("12")) {
            teamtabLayout.setVisibility(View.GONE);

            autopressLayout.setVisibility(View.GONE);

        } else if (format_id.equalsIgnoreCase("11")) {
            teamtabLayout.setVisibility(View.GONE);
            layout_340_scorcard.setVisibility(View.GONE);

        } else {
            teamtabLayout.setVisibility(View.GONE);
            layout_340_scorcard.setVisibility(View.GONE);
            autopressLayout.setVisibility(View.GONE);
        }

        if (isDelegate.equalsIgnoreCase("1")) {
            SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
            final String admin_ID = pref.getString("userId", null);
            String eventID = getIntent().getStringExtra("eventID").toString();
            delegated_status = "1";
            getscorecarddata(admin_ID, eventID, delegated_status);
        }
    }


    public void getParIndexValue1(String Golf_ID, String Event_Id, String hole_numbe) {

        holeNumber = hole_numbe;

        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        final String user_ID = pref.getString("userId", null);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("golf_course_id", Golf_ID);
            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("hole_number", hole_numbe);
            JSONArray jsId = new JSONArray();
            //JSONObject jsOb = new JSONObject();
            for (int p = 0; p < tabplayer_id.size(); p++) {
                jsId.put(tabplayer_id.get(p));
            }
            jsonObject.putOpt("player_ids", jsId);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("par index testing", jsonObject.toString());

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GET_PAR_INDEX_VALUE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                requestParIndexValue1(response);
                Log.e("GET_PAR_INDEX_1", "GET_PAR_INDEX_VALUE1" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Utility.showLogError(this, "Error in " + "GET_PAR_INDEX_VALUE 1 URL = " + PUTTAPI.GET_PAR_INDEX_VALUE);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void requestParIndexValue1(JSONObject response) {

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");

                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                String holeindex = jsonObject1.getString("hole_index");
                parvalue_ = jsonObject1.getString("par_value");
                isSpot = jsonObject1.getString("is_spot_type");

                isDelegate = jsonObject1.getString("is_delegated");


                if (isSpot.equalsIgnoreCase("0")) {
                    spotPrizeLayout1.setVisibility(View.GONE);
                    spotPrizeLayout2.setVisibility(View.GONE);
                    spotPrizeLayout3.setVisibility(View.GONE);
                    spotPrizeLayout4.setVisibility(View.GONE);

                } else if (isSpot.equalsIgnoreCase("1")) {
                    spotPrizeLayout1.setVisibility(View.VISIBLE);
                    spotPrizeText1.setText("CLOSEST TO PIN");
                    feetText1.setText("FEET");

                    spotPrizeLayout2.setVisibility(View.VISIBLE);
                    spotPrizeText2.setText("CLOSEST TO PIN");
                    feetText2.setText("FEET");

                    spotPrizeLayout3.setVisibility(View.VISIBLE);
                    spotPrizeText3.setText("CLOSEST TO PIN");
                    feetText3.setText("FEET");

                    spotPrizeLayout4.setVisibility(View.VISIBLE);
                    spotPrizeText4.setText("CLOSEST TO PIN");
                    feetText4.setText("FEET");

                } else if (isSpot.equalsIgnoreCase("2")) {
                    spotPrizeLayout1.setVisibility(View.VISIBLE);
                    spotPrizeText1.setText("STRAIGHT DRIVE");
                    feetText1.setText("FEET");

                    spotPrizeLayout2.setVisibility(View.VISIBLE);
                    spotPrizeText2.setText("STRAIGHT DRIVE");
                    feetText2.setText("FEET");

                    spotPrizeLayout3.setVisibility(View.VISIBLE);
                    spotPrizeText3.setText("STRAIGHT DRIVE");
                    feetText3.setText("FEET");

                    spotPrizeLayout4.setVisibility(View.VISIBLE);
                    spotPrizeText4.setText("STRAIGHT DRIVE");
                    feetText4.setText("FEET");

                } else if (isSpot.equalsIgnoreCase("3")) {
                    spotPrizeLayout1.setVisibility(View.VISIBLE);
                    spotPrizeText1.setText("LONG DRIVE");
                    feetText1.setText("YARD");

                    spotPrizeLayout2.setVisibility(View.VISIBLE);
                    spotPrizeText2.setText("LONG DRIVE");
                    feetText2.setText("YARD");

                    spotPrizeLayout3.setVisibility(View.VISIBLE);
                    spotPrizeText3.setText("LONG DRIVE");
                    feetText3.setText("YARD");

                    spotPrizeLayout4.setVisibility(View.VISIBLE);
                    spotPrizeText4.setText("LONG DRIVE");
                    feetText4.setText("YARD");


                }

                String errorMessage = jsonObject.getString("message");

                String is_team = jsonObject1.getString("is_team");

                JSONArray jsonArray = jsonObject.getJSONArray("total");

                value_saved = new ArrayList<String>();
                fare_savedArray = new ArrayList<String>();
                putt_savedArray = new ArrayList<String>();
                sand_savedArray = new ArrayList<String>();
                isTemp_savedArray = new ArrayList<String>();

                inch_savedArray = new ArrayList<String>();
                feet_savedArray = new ArrayList<String>();

                // fare_savedArray = new ArrayList<String>();

                for (int i = 0; i < jsonArray.length(); i++) {

                    score_value_saved = jsonArray.getJSONObject(i).getString("score_value");
                    value_saved.add(score_value_saved);

                    String feet_saved1 = jsonArray.getJSONObject(i).getString("closest_feet");
                    feet_savedArray.add(feet_saved1);

                    String inch_saved1 = jsonArray.getJSONObject(i).getString("closest_inch");
                    inch_savedArray.add(inch_saved1);

                    String no_of_putt1 = jsonArray.getJSONObject(i).getString("no_of_putt");
                    putt_savedArray.add(no_of_putt1);

                    String sand1 = jsonArray.getJSONObject(i).getString("sand");
                    sand_savedArray.add(sand1);

                    String fairway1 = jsonArray.getJSONObject(i).getString("fairway");
                    fare_savedArray.add(fairway1);

                    isTemp = jsonArray.getJSONObject(i).getString("is_temporary");

                    no_of_hole_played = jsonArray.getJSONObject(i).getString("no_of_holes_played");

                    // isTemp_savedArray.add(isTemp);

                    String playerID = playerID(0);
                    String play_id = jsonArray.getJSONObject(i).getString("player_id");
                    if (playerID.equalsIgnoreCase(play_id)) {

                        //   no_of_hole_played = jsonArray.getJSONObject(i).getString("no_of_holes_played");
                        String is_handicap_gain = jsonArray.getJSONObject(i).getString("is_handicap_gain");
                        String no_of_putt = jsonArray.getJSONObject(i).getString("no_of_putt");
                        String sand = jsonArray.getJSONObject(i).getString("sand");
                        String fairway = jsonArray.getJSONObject(i).getString("fairway");
                        String current_hole_number = jsonArray.getJSONObject(i).getString("current_hole_number");

                        int hollll = Integer.parseInt(current_hole_number) - 1;

                        String playerName = jsonArray.getJSONObject(i).getString("player_name");


                        if (is_handicap_gain.equalsIgnoreCase("1")) {
                            //  s_symbol.setVisibility(View.VISIBLE);
                        } else {
                            // s_symbol.setVisibility(View.INVISIBLE);
                        }

                    }
                    if (i == 0) {

                        String current_hole_number = jsonArray.getJSONObject(i).getString("current_hole_number");

                        int hollll = Integer.parseInt(current_hole_number) - 1;
                        String playerName = jsonArray.getJSONObject(i).getString("player_name");

                        if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {


                            if (is_team.equalsIgnoreCase("0")) {
                                winner1 = jsonArray.getJSONObject(i).getString("player_id");
                                teamA_Exp.setText(playerName);
                                player1A_Exp.setVisibility(View.GONE);
                                player2A_Exp.setVisibility(View.GONE);

                                JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                                for (int m = 0; m < last_score.length(); m++) {
                                    String scoreValue = last_score.getJSONObject(m).getString("score_value");
                                    String winner = last_score.getJSONObject(m).getString("winner");

                                    current_winner = winner;
                                    String color = last_score.getJSONObject(m).getString("color");

                                    hole_Expand.setText(no_of_hole_played);
                                    standings_Expand.setText(scoreValue);

                                }
                            }

                        } else if (format_id.equalsIgnoreCase("11")) {

                            if (is_team.equalsIgnoreCase("0")) {
                                JSONArray last_score = jsonArray.getJSONObject(0).getJSONArray("last_score");

                                if (last_score.length() > 0) {


                                    JSONObject currObject = last_score.getJSONObject(0);
                                    //  String curentHole = currObject.getString("hole_number");


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

                                    for (int s = 0; s < scoreArrayfront.size(); s++) {

                                        autopressLayout.setVisibility(View.VISIBLE);

                                        if (s == 0) {
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


                                        } else if (s == 1) {
                                            front2.setText(scoreArrayfront.get(s));
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

                                        } else if (s == 2) {
                                            front3.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 3) {
                                            front4.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 4) {
                                            front5.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 5) {
                                            front6.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 6) {
                                            front7.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 7) {
                                            front8.setText(scoreArrayfront.get(s));
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

                                        } else if (s == 8) {
                                            front9.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 9) {
                                            front10.setText(scoreArrayfront.get(s));
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
                                    if (scoreArrayback.size() < 1) {
                                        back.setVisibility(View.GONE);
                                    } else {
                                        back.setVisibility(View.VISIBLE);
                                        for (int s = 0; s < scoreArrayback.size(); s++) {

                                            autopressLayout.setVisibility(View.VISIBLE);

                                            if (s == 0) {

                                                back1.setText(scoreArrayback.get(s));
                                                back1.setTextColor(Color.parseColor(colorArrayback.get(s)));

                                                back1.setVisibility(View.VISIBLE);
                                                back2.setVisibility(View.GONE);
                                                back3.setVisibility(View.GONE);
                                                back4.setVisibility(View.GONE);
                                                back5.setVisibility(View.GONE);
                                                back6.setVisibility(View.GONE);
                                                back7.setVisibility(View.GONE);
                                                back8.setVisibility(View.GONE);
                                                back9.setVisibility(View.GONE);


                                            } else if (s == 1) {
                                                back2.setText(scoreArrayback.get(s));
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
                                                back3.setText(scoreArrayback.get(s));
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
                                                back4.setText(scoreArrayback.get(s));
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
                                                back5.setText(scoreArrayback.get(s));
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
                                                back6.setText(scoreArrayback.get(s));
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
                                                back7.setText(scoreArrayback.get(s));
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
                                                back8.setText(scoreArrayback.get(s));
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
                                                back9.setText(scoreArrayback.get(s));
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

                            }


                        } else if (format_id.equalsIgnoreCase("12")) {

                            JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                            for (int m = 0; m < last_score.length(); m++) {
                                String scoreValue = last_score.getJSONObject(m).getString("score_value");

                                player1_420score.setText(scoreValue);
                                player1_420name.setText(playerName);

                                play1_340_lay.setBackgroundColor(Color.parseColor(tabPlayerColor.get(0)));

                            }
                        }


                    } else if (i == 1) {

                        if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                            if (is_team.equalsIgnoreCase("0")) {

                                JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                                for (int m = 0; m < last_score.length(); m++) {
                                    String scoreValue = last_score.getJSONObject(m).getString("score_value");
                                    String winner = last_score.getJSONObject(m).getString("winner");
                                    current_winner = winner;
                                }


                                String playerName = jsonArray.getJSONObject(i).getString("player_name");
                                winner2 = jsonArray.getJSONObject(i).getString("player_id");
                                teamB_Exp.setText(playerName);
                                player1B_Exp.setVisibility(View.GONE);
                                player2B_Exp.setVisibility(View.GONE);
                            }
                        } else if (format_id.equalsIgnoreCase("11")) {


                            if (is_team.equalsIgnoreCase("0")) {

                                JSONArray last_score = jsonArray.getJSONObject(0).getJSONArray("last_score");

                                if (last_score.length() > 0) {
                                    JSONObject currObject = last_score.getJSONObject(0);


                                    //  String curentHole = currObject.getString("hole_number");

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

                                    for (int s = 0; s < scoreArrayfront.size(); s++) {

                                        autopressLayout.setVisibility(View.VISIBLE);

                                        if (s == 0) {
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


                                        } else if (s == 1) {
                                            front2.setText(scoreArrayfront.get(s));
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

                                        } else if (s == 2) {
                                            front3.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 3) {
                                            front4.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 4) {
                                            front5.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 5) {
                                            front6.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 6) {
                                            front7.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 7) {
                                            front8.setText(scoreArrayfront.get(s));
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

                                        } else if (s == 8) {
                                            front9.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 9) {
                                            front10.setText(scoreArrayfront.get(s));
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

                                    if (scoreArrayback.size() < 1) {
                                        back.setVisibility(View.GONE);
                                    } else {
                                        back.setVisibility(View.VISIBLE);
                                        for (int s = 0; s < scoreArrayback.size(); s++) {

                                            autopressLayout.setVisibility(View.VISIBLE);

                                            if (s == 0) {
                                                back1.setText(scoreArrayback.get(s));
                                                back1.setTextColor(Color.parseColor(colorArrayback.get(s)));
                                            } else if (s == 1) {
                                                back2.setText(scoreArrayback.get(s));
                                                back2.setTextColor(Color.parseColor(colorArrayback.get(s)));
                                            } else if (s == 2) {
                                                back3.setText(scoreArrayback.get(s));
                                                back3.setTextColor(Color.parseColor(colorArrayback.get(s)));
                                            } else if (s == 3) {
                                                back4.setText(scoreArrayback.get(s));
                                                back4.setTextColor(Color.parseColor(colorArrayback.get(s)));
                                            } else if (s == 4) {
                                                back5.setText(scoreArrayback.get(s));
                                                back5.setTextColor(Color.parseColor(colorArrayback.get(s)));
                                            } else if (s == 5) {
                                                back6.setText(scoreArrayback.get(s));
                                                back6.setTextColor(Color.parseColor(colorArrayback.get(s)));
                                            } else if (s == 6) {
                                                back7.setText(scoreArrayback.get(s));
                                                back7.setTextColor(Color.parseColor(colorArrayback.get(s)));
                                            } else if (s == 7) {
                                                back8.setText(scoreArrayback.get(s));
                                                back8.setTextColor(Color.parseColor(colorArrayback.get(s)));
                                            } else if (s == 8) {
                                                back9.setText(scoreArrayback.get(s));
                                                back9.setTextColor(Color.parseColor(colorArrayback.get(s)));
                                            }
                                        }

                                    }
                                }
                            }

                        } else if (format_id.equalsIgnoreCase("12")) {

                            String playerName = jsonArray.getJSONObject(i).getString("player_name");
                            JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                            for (int m = 0; m < last_score.length(); m++) {
                                layout_340_scorcard.setVisibility(View.VISIBLE);
                                String scoreValue = last_score.getJSONObject(m).getString("score_value");

                                player2_420score.setText(scoreValue);
                                player2_420name.setText(playerName);
                                play2_340_lay.setBackgroundColor(Color.parseColor(tabPlayerColor.get(1)));
                            }
                        }
                    } else if (i == 2) {

                        if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                            if (is_team.equalsIgnoreCase("0")) {

                                JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                                for (int m = 0; m < last_score.length(); m++) {
                                    String scoreValue = last_score.getJSONObject(m).getString("score_value");
                                    String winner = last_score.getJSONObject(m).getString("winner");
                                    current_winner = winner;
                                }


                                String playerName = jsonArray.getJSONObject(i).getString("player_name");
                                winner2 = jsonArray.getJSONObject(i).getString("player_id");
                                teamB_Exp.setText(playerName);
                                player1B_Exp.setVisibility(View.GONE);
                                player2B_Exp.setVisibility(View.GONE);
                            }
                        } else if (format_id.equalsIgnoreCase("11")) {
                            if (is_team.equalsIgnoreCase("0")) {

                                JSONArray last_score = jsonArray.getJSONObject(0).getJSONArray("last_score");

                                if (last_score.length() > 0) {

                                    JSONObject currObject = last_score.getJSONObject(0);


                                    //  String curentHole = currObject.getString("hole_number");
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

                                    for (int s = 0; s < scoreArrayfront.size(); s++) {

                                        autopressLayout.setVisibility(View.VISIBLE);

                                        if (s == 0) {
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


                                        } else if (s == 1) {
                                            front2.setText(scoreArrayfront.get(s));
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

                                        } else if (s == 2) {
                                            front3.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 3) {
                                            front4.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 4) {
                                            front5.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 5) {
                                            front6.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 6) {
                                            front7.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 7) {
                                            front8.setText(scoreArrayfront.get(s));
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

                                        } else if (s == 8) {
                                            front9.setText(scoreArrayfront.get(s));
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
                                        } else if (s == 9) {
                                            front10.setText(scoreArrayfront.get(s));
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

                                    if (scoreArrayback.size() < 1) {
                                        back.setVisibility(View.GONE);
                                    } else {
                                        back.setVisibility(View.VISIBLE);
                                        for (int s = 0; s < scoreArrayback.size(); s++) {

                                            autopressLayout.setVisibility(View.VISIBLE);

                                            if (s == 0) {

                                                back1.setText(scoreArrayback.get(s));
                                                back1.setTextColor(Color.parseColor(colorArrayback.get(s)));

                                                back1.setVisibility(View.VISIBLE);
                                                back2.setVisibility(View.GONE);
                                                back3.setVisibility(View.GONE);
                                                back4.setVisibility(View.GONE);
                                                back5.setVisibility(View.GONE);
                                                back6.setVisibility(View.GONE);
                                                back7.setVisibility(View.GONE);
                                                back8.setVisibility(View.GONE);
                                                back9.setVisibility(View.GONE);


                                            } else if (s == 1) {
                                                back2.setText(scoreArrayback.get(s));
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
                                                back3.setText(scoreArrayback.get(s));
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
                                                back4.setText(scoreArrayback.get(s));
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
                                                back5.setText(scoreArrayback.get(s));
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
                                                back6.setText(scoreArrayback.get(s));
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
                                                back7.setText(scoreArrayback.get(s));
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
                                                back8.setText(scoreArrayback.get(s));
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
                                                back9.setText(scoreArrayback.get(s));
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
                            }
                        } else if (format_id.equalsIgnoreCase("12")) {


                            String playerName = jsonArray.getJSONObject(i).getString("player_name");
                            JSONArray last_score = jsonArray.getJSONObject(i).getJSONArray("last_score");
                            for (int m = 0; m < last_score.length(); m++) {
                                layout_340_scorcard.setVisibility(View.VISIBLE);
                                String scoreValue = last_score.getJSONObject(m).getString("score_value");

                                player3_420score.setText(scoreValue);
                                player3_420name.setText(playerName);
                                play3_340_lay.setBackgroundColor(Color.parseColor(tabPlayerColor.get(2)));
                            }
                        }
                    }


                }

                if (no_of_hole_played.equalsIgnoreCase("0")) {

                    if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                        flag = false;
                        open_hole_num_selection_dialog(getholevalue());
                    }

                } else {
                    if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {

                        flag = true;

                    }

                }

             /*   if (totalScore1.equalsIgnoreCase("0") && totalScore2.equalsIgnoreCase("0") && totalScore3.equalsIgnoreCase("0")) {
                    downrowscorecard.setVisibility(View.GONE);
                } else {
                    downrowscorecard.setVisibility(View.VISIBLE);
                }*/

                par.setText("PAR " + parvalue_);
                //  index_value.setText(holeindex);
                if (value_saved.size() == 1) {
                    gross_score1.setText(value_saved.get(0));
                    if (sand_savedArray.get(0).equalsIgnoreCase("-1")) {
                        sandEditText1.setText("-");
                    } else {
                        sandEditText1.setText(sand_savedArray.get(0));
                    }

                    if (putt_savedArray.get(0).equalsIgnoreCase("-1")) {
                        puttsnumber1.setText("-");
                    } else {
                        puttsnumber1.setText(putt_savedArray.get(0));
                    }

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0) + "'" + inch_savedArray.get(0) + "''");
                        } else {
                            feetValue1.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0));
                        }else {
                            feetValue1.setText("-");
                        }
                    }

                    if (fare_savedArray.get(0).equalsIgnoreCase("4")) {
                        fairwaysLayout1.setVisibility(View.GONE);
                        //  radioGroup1.check(R.id.radio_left1);
                        radioGroup1.clearCheck();
                        //  radioLeft1.setChecked(false);
                        //  radioCenter1.setChecked(false);
                        //  radioRight1.setChecked(false);
                        selected_fairway1 = "0";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("1")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(true);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_left1);
                        // radioGroup1.clearCheck();

                        selected_fairway1 = "1";

                    } else if (fare_savedArray.get(0).equalsIgnoreCase("2")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(true);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_center1);

                        selected_fairway1 = "2";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("3")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);

                        /*radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(true);*/
                        radioGroup1.check(R.id.radio_right1);

                        selected_fairway1 = "3";
                    } else {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                      /*  radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/
                        radioGroup1.clearCheck();
                        selected_fairway1 = "0";
                    }

                    //  saveTxt.setText("SAVED");
                } else if (value_saved.size() == 2) {
                    gross_score1.setText(value_saved.get(0));
                    gross_score2.setText(value_saved.get(1));

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0) + "'" + inch_savedArray.get(0) + "''");
                        } else {
                            feetValue1.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0));
                        }else {
                            feetValue1.setText("-");
                        }
                    }

                    if (sand_savedArray.get(0).equalsIgnoreCase("-1")) {
                        sandEditText1.setText("-");
                    } else {
                        sandEditText1.setText(sand_savedArray.get(0));
                    }

                    if (putt_savedArray.get(0).equalsIgnoreCase("-1")) {
                        puttsnumber1.setText("-");
                    } else {
                        puttsnumber1.setText(putt_savedArray.get(0));
                    }

                    if (fare_savedArray.get(0).equalsIgnoreCase("4")) {
                        fairwaysLayout1.setVisibility(View.GONE);

                        radioGroup1.clearCheck();
                        selected_fairway1 = "0";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("1")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(true);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_left1);

                        selected_fairway1 = "1";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("2")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                      /*  radioLeft1.setChecked(false);
                        radioCenter1.setChecked(true);
                        radioRight1.setChecked(false);*/
                        radioGroup1.check(R.id.radio_center1);
                        selected_fairway1 = "2";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("3")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);

                        radioGroup1.check(R.id.radio_right1);

                        selected_fairway1 = "3";
                    } else {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/
                        radioGroup1.clearCheck();
                        selected_fairway1 = "0";
                    }

                    if (sand_savedArray.get(1).equalsIgnoreCase("-1")) {
                        sandEditText2.setText("-");
                    } else {
                        sandEditText2.setText(sand_savedArray.get(1));
                    }

                    if (putt_savedArray.get(1).equalsIgnoreCase("-1")) {
                        puttsnumber2.setText("-");
                    } else {
                        puttsnumber2.setText(putt_savedArray.get(1));
                    }

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1) + "'" + inch_savedArray.get(1) + "''");
                        } else {
                            feetValue2.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1));
                        }else {
                            feetValue2.setText("-");
                        }
                    }
                    if (fare_savedArray.get(1).equalsIgnoreCase("4")) {
                        fairwaysLayout2.setVisibility(View.GONE);

                        radioGroup2.clearCheck();
                        selected_fairway2 = "0";

                    } else if (fare_savedArray.get(1).equalsIgnoreCase("1")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                        radioGroup2.check(R.id.radio_left2);
                        selected_fairway2 = "1";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("2")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                        radioGroup2.check(R.id.radio_center2);
                        selected_fairway2 = "2";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("3")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(true);*/
                        radioGroup2.check(R.id.radio_right2);

                        selected_fairway2 = "3";
                    } else {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                      /*  radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.clearCheck();

                        selected_fairway2 = "0";
                    }

                    //  saveTxt.setText("SAVED");
                } else if (value_saved.size() == 3) {
                    gross_score1.setText(value_saved.get(0));
                    gross_score2.setText(value_saved.get(1));
                    gross_score3.setText(value_saved.get(2));

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0) + "'" + inch_savedArray.get(0) + "''");
                        } else {
                            feetValue1.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0));
                        }else {
                            feetValue1.setText("-");
                        }
                    }
                    if (sand_savedArray.get(0).equalsIgnoreCase("-1")) {
                        sandEditText1.setText("-");
                    } else {
                        sandEditText1.setText(sand_savedArray.get(0));
                    }

                    if (putt_savedArray.get(0).equalsIgnoreCase("-1")) {
                        puttsnumber1.setText("-");
                    } else {
                        puttsnumber1.setText(putt_savedArray.get(0));
                    }

                    if (fare_savedArray.get(0).equalsIgnoreCase("4")) {
                        fairwaysLayout1.setVisibility(View.GONE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/
                        radioGroup1.clearCheck();
                        selected_fairway1 = "0";

                    } else if (fare_savedArray.get(0).equalsIgnoreCase("1")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);

                        radioGroup1.check(R.id.radio_left1);
                        selected_fairway1 = "1";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("2")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                        /*radioLeft1.setChecked(false);
                        radioCenter1.setChecked(true);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_center1);

                        selected_fairway1 = "2";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("3")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(true);*/

                        radioGroup1.check(R.id.radio_right1);

                        selected_fairway1 = "3";
                    } else {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/

                        radioGroup1.clearCheck();

                        selected_fairway1 = "0";
                    }

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1) + "'" + inch_savedArray.get(1) + "''");
                        } else {
                            feetValue2.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1));
                        }else {
                            feetValue2.setText("-");
                        }
                    }
                    if (sand_savedArray.get(1).equalsIgnoreCase("-1")) {
                        sandEditText2.setText("-");
                    } else {
                        sandEditText2.setText(sand_savedArray.get(1));
                    }

                    if (putt_savedArray.get(1).equalsIgnoreCase("-1")) {
                        puttsnumber2.setText("-");
                    } else {
                        puttsnumber2.setText(putt_savedArray.get(1));
                    }

                    if (fare_savedArray.get(1).equalsIgnoreCase("4")) {
                        fairwaysLayout2.setVisibility(View.GONE);
                        /*radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.clearCheck();

                        selected_fairway2 = "0";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("1")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(true);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.check(R.id.radio_left2);

                        selected_fairway2 = "1";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("2")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                        /*radioLeft2.setChecked(false);
                        radioCenter2.setChecked(true);
                        radioRight2.setChecked(false);*/

                        radioGroup2.check(R.id.radio_center2);

                        selected_fairway2 = "2";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("3")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                        /*radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(true);*/

                        radioGroup2.check(R.id.radio_right2);

                        selected_fairway2 = "3";
                    } else {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.clearCheck();

                        selected_fairway2 = "0";
                    }

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(2).length() > 0 && !feet_savedArray.get(2).equalsIgnoreCase("-1")) {

                            feetValue3.setText(feet_savedArray.get(2) + "'" + inch_savedArray.get(2) + "''");
                        } else {
                            feetValue3.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(2).length() > 0 && !feet_savedArray.get(2).equalsIgnoreCase("-1")) {
                            feetValue3.setText(feet_savedArray.get(2));
                        }else {
                            feetValue3.setText("-");
                        }
                    }

                    if (sand_savedArray.get(2).equalsIgnoreCase("-1")) {
                        sandEditText3.setText("-");
                    } else {
                        sandEditText3.setText(sand_savedArray.get(2));
                    }

                    if (putt_savedArray.get(2).equalsIgnoreCase("-1")) {
                        puttsnumber3.setText("-");
                    } else {
                        puttsnumber3.setText(putt_savedArray.get(2));
                    }

                    if (fare_savedArray.get(2).equalsIgnoreCase("4")) {
                        fairwaysLayout3.setVisibility(View.GONE);
                        /*radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/

                        radioGroup3.clearCheck();

                        selected_fairway3 = "0";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("1")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                       /* radioLeft3.setChecked(true);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/

                        radioGroup3.check(R.id.radio_left3);

                        selected_fairway3 = "1";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("2")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                       /* radioLeft3.setChecked(false);
                        radioCenter3.setChecked(true);
                        radioRight3.setChecked(false);*/

                        radioGroup3.check(R.id.radio_center3);
                        selected_fairway3 = "2";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("3")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                        /*radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(true);*/

                        radioGroup3.check(R.id.radio_right3);

                        selected_fairway3 = "3";
                    } else {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                       /* radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/

                        radioGroup3.clearCheck();

                        selected_fairway3 = "0";
                    }


                    //  saveTxt.setText("SAVED");
                } else if (value_saved.size() == 4) {
                    gross_score1.setText(value_saved.get(0));
                    gross_score2.setText(value_saved.get(1));
                    gross_score3.setText(value_saved.get(2));
                    gross_score4.setText(value_saved.get(3));

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0) + "'" + inch_savedArray.get(0) + "''");
                        } else {
                            feetValue1.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(0).length() > 0 && !feet_savedArray.get(0).equalsIgnoreCase("-1")) {
                            feetValue1.setText(feet_savedArray.get(0));
                        }else {
                            feetValue1.setText("-");
                        }
                    }
                    if (sand_savedArray.get(0).equalsIgnoreCase("-1")) {
                        sandEditText1.setText("-");
                    } else {
                        sandEditText1.setText(sand_savedArray.get(0));
                    }

                    if (putt_savedArray.get(0).equalsIgnoreCase("-1")) {
                        puttsnumber1.setText("-");
                    } else {
                        puttsnumber1.setText(putt_savedArray.get(0));
                    }

                    if (fare_savedArray.get(0).equalsIgnoreCase("4")) {
                        fairwaysLayout1.setVisibility(View.GONE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/

                        radioGroup1.clearCheck();

                        selected_fairway1 = "0";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("1")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                      /*  radioLeft1.setChecked(true);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_left1);

                        selected_fairway1 = "1";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("2")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(true);
                        radioRight1.setChecked(false);*/

                        radioGroup1.check(R.id.radio_center1);

                        selected_fairway1 = "2";
                    } else if (fare_savedArray.get(0).equalsIgnoreCase("3")) {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(true);*/
                        radioGroup1.check(R.id.radio_right1);

                        selected_fairway1 = "3";
                    } else {
                        fairwaysLayout1.setVisibility(View.VISIBLE);
                       /* radioLeft1.setChecked(false);
                        radioCenter1.setChecked(false);
                        radioRight1.setChecked(false);*/
                        radioGroup3.clearCheck();

                        selected_fairway1 = "0";
                    }

                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1) + "'" + inch_savedArray.get(1) + "''");
                        } else {
                            feetValue2.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(1).length() > 0 && !feet_savedArray.get(1).equalsIgnoreCase("-1")) {
                            feetValue2.setText(feet_savedArray.get(1));
                        }else {
                            feetValue2.setText("-");
                        }
                    }
                    if (sand_savedArray.get(1).equalsIgnoreCase("-1")) {
                        sandEditText2.setText("-");
                    } else {
                        sandEditText2.setText(sand_savedArray.get(1));
                    }

                    if (putt_savedArray.get(1).equalsIgnoreCase("-1")) {
                        puttsnumber2.setText("-");
                    } else {
                        puttsnumber2.setText(putt_savedArray.get(1));
                    }

                    if (fare_savedArray.get(1).equalsIgnoreCase("4")) {
                        fairwaysLayout2.setVisibility(View.GONE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.clearCheck();

                        selected_fairway2 = "0";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("1")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                        /*radioLeft2.setChecked(true);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.check(R.id.radio_left2);

                        selected_fairway2 = "1";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("2")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(true);
                        radioRight2.setChecked(false);*/
                        radioGroup2.check(R.id.radio_center2);

                        selected_fairway2 = "2";
                    } else if (fare_savedArray.get(1).equalsIgnoreCase("3")) {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(true);*/

                        radioGroup2.check(R.id.radio_right2);
                        selected_fairway2 = "3";
                    } else {
                        fairwaysLayout2.setVisibility(View.VISIBLE);
                       /* radioLeft2.setChecked(false);
                        radioCenter2.setChecked(false);
                        radioRight2.setChecked(false);*/

                        radioGroup2.clearCheck();

                        selected_fairway2 = "0";
                    }


                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(2).length() > 0 && !feet_savedArray.get(2).equalsIgnoreCase("-1")) {

                            feetValue3.setText(feet_savedArray.get(2) + "'" + inch_savedArray.get(2) + "''");
                        } else {
                            feetValue3.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(2).length() > 0 && !feet_savedArray.get(2).equalsIgnoreCase("-1")) {
                            feetValue3.setText(feet_savedArray.get(2));
                        }else {
                            feetValue3.setText("-");
                        }
                    }
                    if (sand_savedArray.get(2).equalsIgnoreCase("-1")) {
                        sandEditText3.setText("-");
                    } else {
                        sandEditText3.setText(sand_savedArray.get(2));
                    }

                    if (putt_savedArray.get(2).equalsIgnoreCase("-1")) {
                        puttsnumber3.setText("-");
                    } else {
                        puttsnumber3.setText(putt_savedArray.get(2));
                    }
                    if (fare_savedArray.get(2).equalsIgnoreCase("4")) {
                        fairwaysLayout3.setVisibility(View.GONE);
                        /*radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/

                        radioGroup3.clearCheck();
                        selected_fairway3 = "0";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("1")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                        /*radioLeft3.setChecked(true);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/

                        radioGroup3.check(R.id.radio_left3);

                        selected_fairway3 = "1";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("2")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                        /*radioLeft3.setChecked(false);
                        radioCenter3.setChecked(true);
                        radioRight3.setChecked(false);*/

                        radioGroup3.check(R.id.radio_center3);

                        selected_fairway3 = "2";
                    } else if (fare_savedArray.get(2).equalsIgnoreCase("3")) {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                       /* radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(true);*/
                        radioGroup3.check(R.id.radio_right3);
                        selected_fairway3 = "3";
                    } else {
                        fairwaysLayout3.setVisibility(View.VISIBLE);
                       /* radioLeft3.setChecked(false);
                        radioCenter3.setChecked(false);
                        radioRight3.setChecked(false);*/
                        radioGroup3.clearCheck();
                        selected_fairway3 = "0";
                    }


                    if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                        if (feet_savedArray.get(3).length() > 0 && !feet_savedArray.get(3).equalsIgnoreCase("-1")) {
                            feetValue4.setText(feet_savedArray.get(3) + "'" + inch_savedArray.get(3) + "''");
                        } else {
                            feetValue4.setText("-");
                        }
                    } else if (isSpot.equalsIgnoreCase("3")) {
                        if (feet_savedArray.get(3).length() > 0 && !feet_savedArray.get(3).equalsIgnoreCase("-1")) {
                            feetValue4.setText(feet_savedArray.get(3));
                        }else {
                            feetValue4.setText("-");
                        }
                    }
                    if (sand_savedArray.get(3).equalsIgnoreCase("-1")) {
                        sandEditText4.setText("-");
                    } else {
                        sandEditText4.setText(sand_savedArray.get(3));
                    }

                    if (putt_savedArray.get(3).equalsIgnoreCase("-1")) {
                        puttsnumber4.setText("-");
                    } else {
                        puttsnumber4.setText(putt_savedArray.get(3));
                    }

                    if (fare_savedArray.get(3).equalsIgnoreCase("4")) {
                        fairwaysLayout4.setVisibility(View.GONE);
                        /*radioLeft4.setChecked(false);
                        radioCenter4.setChecked(false);
                        radioRight4.setChecked(false);*/

                        radioGroup4.clearCheck();

                        selected_fairway4 = "0";
                    } else if (fare_savedArray.get(3).equalsIgnoreCase("1")) {
                        fairwaysLayout4.setVisibility(View.VISIBLE);
                        /*radioLeft4.setChecked(true);
                        radioCenter4.setChecked(false);
                        radioRight4.setChecked(false);*/

                        radioGroup4.check(R.id.radio_left4);
                        selected_fairway4 = "1";
                    } else if (fare_savedArray.get(3).equalsIgnoreCase("2")) {
                        fairwaysLayout4.setVisibility(View.VISIBLE);
                       /* radioLeft4.setChecked(false);
                        radioCenter4.setChecked(true);
                        radioRight4.setChecked(false);*/
                        radioGroup4.check(R.id.radio_center4);

                        selected_fairway4 = "2";
                    } else if (fare_savedArray.get(3).equalsIgnoreCase("3")) {
                        fairwaysLayout4.setVisibility(View.VISIBLE);
                       /* radioLeft4.setChecked(false);
                        radioCenter4.setChecked(false);
                        radioRight4.setChecked(true);*/

                        radioGroup4.check(R.id.radio_right4);
                        selected_fairway4 = "3";
                    } else {
                        fairwaysLayout4.setVisibility(View.VISIBLE);
                       /* radioLeft4.setChecked(false);
                        radioCenter4.setChecked(false);
                        radioRight4.setChecked(false);*/
                        radioGroup4.clearCheck();
                        selected_fairway4 = "0";
                    }


                    //  saveTxt.setText("SAVED");
                } else {
                    //   gross_score1.setText(parvalue_);
                    //   gross_score2.setText(parvalue_);
                    //   gross_score3.setText(parvalue_);
                    //  gross_score4.setText(parvalue_);
                    //  saveTxt.setText("SAVE");
                }

                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {


                    if (is_team.equalsIgnoreCase("1")) {
                        JSONArray teamArray = jsonObject1.getJSONArray("teamdata");

                        for (int j = 0; j < teamArray.length(); j++) {
                            if (j == 0) {
                                String teamA = teamArray.getJSONObject(j).getString("team_name");
                                winner1 = teamArray.getJSONObject(j).getString("team_id");

                                teamA_Exp.setText(teamA);
                                JSONArray plArray = teamArray.getJSONObject(j).getJSONArray("player_list");
                                for (int p = 0; p < plArray.length(); p++) {

                                    if (p == 0) {
                                        String teamA_player1 = plArray.getJSONObject(p).getString("name");
                                        String teamA_player1_handi = plArray.getJSONObject(p).getString("handicap_value");
                                        player1A_Exp.setText(teamA_player1 + " " + teamA_player1_handi);
                                    }
                                    if (p == 1) {
                                        String teamA_player2 = plArray.getJSONObject(p).getString("name");
                                        String teamA_player2_handi = plArray.getJSONObject(p).getString("handicap_value");
                                        player2A_Exp.setText(teamA_player2 + " " + teamA_player2_handi);
                                    }
                                }
                            }
                            if (j == 1) {
                                String teamB = teamArray.getJSONObject(j).getString("team_name");
                                winner2 = teamArray.getJSONObject(j).getString("team_id");
                                teamB_Exp.setText(teamB);
                                JSONArray plArray = teamArray.getJSONObject(j).getJSONArray("player_list");
                                for (int p = 0; p < plArray.length(); p++) {

                                    if (p == 0) {
                                        String teamB_player1 = plArray.getJSONObject(p).getString("name");
                                        String teamB_player1_handi = plArray.getJSONObject(p).getString("handicap_value");
                                        player1B_Exp.setText(teamB_player1 + " " + teamB_player1_handi);
                                    }
                                    if (p == 1) {
                                        String teamB_player2 = plArray.getJSONObject(p).getString("name");
                                        String teamB_player2_handi = plArray.getJSONObject(p).getString("handicap_value");
                                        player2B_Exp.setText(teamB_player2 + " " + teamB_player2_handi);
                                    }

                                }

                            }
                            if (j == 2) {
                                JSONArray curntArray = teamArray.getJSONObject(j).getJSONArray("current_standing");
                                if (curntArray.length() > 0) {

                                    for (int c = 0; c < curntArray.length(); c++) {

                                        JSONObject curntObj = curntArray.getJSONObject(c);

                                        String holeNUM = curntObj.getString("hole_number");
                                        String scoreCurrent = curntObj.getString("score_value");
                                        current_winner = curntObj.getString("winner");
                                        hole_Expand.setText(no_of_hole_played);
                                        standings_Expand.setText(scoreCurrent);
                                    }
                                }

                            }

                        }
                    }


                } else if (format_id.equalsIgnoreCase("11")) {


                    if (is_team.equalsIgnoreCase("1")) {
                        JSONArray teamArray = jsonObject1.getJSONArray("teamdata");
                        JSONObject data = teamArray.getJSONObject(2);

                        JSONArray jss = data.getJSONArray("current_standing");
                        JSONObject currObject = jss.getJSONObject(0);


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

                        for (int s = 0; s < scoreArrayfront.size(); s++) {

                            autopressLayout.setVisibility(View.VISIBLE);

                            if (s == 0) {
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


                            } else if (s == 1) {
                                front2.setText(scoreArrayfront.get(s));
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

                            } else if (s == 2) {
                                front3.setText(scoreArrayfront.get(s));
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
                            } else if (s == 3) {
                                front4.setText(scoreArrayfront.get(s));
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
                            } else if (s == 4) {
                                front5.setText(scoreArrayfront.get(s));
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
                            } else if (s == 5) {
                                front6.setText(scoreArrayfront.get(s));
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
                            } else if (s == 6) {
                                front7.setText(scoreArrayfront.get(s));
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
                            } else if (s == 7) {
                                front8.setText(scoreArrayfront.get(s));
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

                            } else if (s == 8) {
                                front9.setText(scoreArrayfront.get(s));
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
                            } else if (s == 9) {
                                front10.setText(scoreArrayfront.get(s));
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
                        if (scoreArrayback.size() < 1) {
                            back.setVisibility(View.GONE);
                        } else {
                            back.setVisibility(View.VISIBLE);
                            for (int s = 0; s < scoreArrayback.size(); s++) {

                                autopressLayout.setVisibility(View.VISIBLE);

                                if (s == 0) {

                                    back1.setText(scoreArrayback.get(s));
                                    back1.setTextColor(Color.parseColor(colorArrayback.get(s)));

                                    back1.setVisibility(View.VISIBLE);
                                    back2.setVisibility(View.GONE);
                                    back3.setVisibility(View.GONE);
                                    back4.setVisibility(View.GONE);
                                    back5.setVisibility(View.GONE);
                                    back6.setVisibility(View.GONE);
                                    back7.setVisibility(View.GONE);
                                    back8.setVisibility(View.GONE);
                                    back9.setVisibility(View.GONE);


                                } else if (s == 1) {
                                    back2.setText(scoreArrayback.get(s));
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
                                    back3.setText(scoreArrayback.get(s));
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
                                    back4.setText(scoreArrayback.get(s));
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
                                    back5.setText(scoreArrayback.get(s));
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
                                    back6.setText(scoreArrayback.get(s));
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
                                    back7.setText(scoreArrayback.get(s));
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
                                    back8.setText(scoreArrayback.get(s));
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
                                    back9.setText(scoreArrayback.get(s));
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

                } else if (format_id.equalsIgnoreCase("12")) {

                }


                if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {


                  /*  bottom_next.setVisibility(View.GONE);
                    bottom_back.setVisibility(View.GONE);
*/
                    if (no_of_hole_played.equalsIgnoreCase("18") && (totalHoleValidation.equalsIgnoreCase("18"))) {
                        scoreCardText.setText("END ROUND");

                        scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                endRound();
                            }
                        });


                    }else  if (no_of_hole_played.equalsIgnoreCase("9") && (totalHoleValidation.equalsIgnoreCase("9"))) {
                        scoreCardText.setText("END ROUND");

                        scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                endRound();
                            }
                        });


                    } else {

                        scoreCardText.setText("SCORECARD");
                        scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                open_nextview();
                            }
                        });
                    }
                } else {

                    bottom_next.setVisibility(View.VISIBLE);
                    bottom_back.setVisibility(View.VISIBLE);

                    if (no_of_hole_played.equalsIgnoreCase("18") && (totalHoleValidation.equalsIgnoreCase("18"))) {
                        scoreCardText.setText("END ROUND");

                        scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                endRound();
                            }
                        });


                    }else  if (no_of_hole_played.equalsIgnoreCase("9") && (totalHoleValidation.equalsIgnoreCase("9"))) {
                        scoreCardText.setText("END ROUND");

                        scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                endRound();
                            }
                        });


                    } else if (is4PlusGame!=null && is4PlusGame.equalsIgnoreCase("0")) {


                        if (totalPlayers.equalsIgnoreCase("1")) {

                            scoreCardText.setText("SCORECARD");

                            scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    String eventid = getIntent().getStringExtra("eventID").toString();
                                    startActivity(new Intent(getApplicationContext(), ScoreTable.class).putExtra("eventID", eventid).putExtra("playerID", "noneed"));
                                    //  finish();
                                }
                            });

                        } else {
                            scoreCardText.setText("LEADERBOARD");

                            scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    String eventid = getIntent().getStringExtra("eventID").toString();

                                    Intent intent = new Intent(getApplicationContext(), Leaderboard.class);
                                    intent.putExtra("eventID", eventid);
                                    startActivity(intent);
                                    //  finish();
                                }
                            });
                        }
                    }else {

                        scoreCardText.setText("LEADERBOARD");

                        scoreCardBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String eventid = getIntent().getStringExtra("eventID").toString();

                                Intent intent = new Intent(getApplicationContext(), Leaderboard.class);
                                intent.putExtra("eventID", eventid);
                                startActivity(intent);
                                //  finish();
                            }
                        });
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        Log.v("winner", "cur" + current_winner + "win1" + winner1 + "win2" + winner2);

        if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {


            if (current_winner != null && current_winner.length() != 0) {
                teamtabLayout.setVisibility(View.VISIBLE);
                layout_340_scorcard.setVisibility(View.GONE);
                autopressLayout.setVisibility(View.GONE);
                if (current_winner.equalsIgnoreCase(winner1)) {
                    winnerBackgroundLay.setBackgroundResource(R.mipmap.indicate_blue);
                } else if (current_winner.equalsIgnoreCase(winner2)) {
                    winnerBackgroundLay.setBackgroundResource(R.mipmap.indicate_red);

                } else {
                    winnerBackgroundLay.setBackgroundResource(R.color.black_color);
                }
            } else {
                teamtabLayout.setVisibility(View.GONE);
                layout_340_scorcard.setVisibility(View.GONE);
                autopressLayout.setVisibility(View.GONE);
            }
        } else if (format_id.equalsIgnoreCase("12")) {


        } else if (format_id.equalsIgnoreCase("11")) {


        } else {
            teamtabLayout.setVisibility(View.GONE);
            layout_340_scorcard.setVisibility(View.GONE);
            autopressLayout.setVisibility(View.GONE);
        }

        if (isDelegate.equalsIgnoreCase("1")) {
            SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
            final String admin_ID = pref.getString("userId", null);
            String eventID = getIntent().getStringExtra("eventID").toString();
            delegated_status = "1";
            getscorecarddata(admin_ID, eventID, delegated_status);

        }

    }


    public void check_validation() {

        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        final String admin_ID = pref.getString("userId", null);
        String eventID = geteventID();

        String holevalue = getholevalue();

       /* if (tabplayer_id.size() == 1) {

            grossscore1 = gross_score1.getText().toString();
            no_of_putts1 = puttsnumber1.getText().toString();
            closest_feet1 = feetText1.getText().toString();
            sand_value1 = sandEditText1.getText().toString();
            saveScoreData(admin_ID, eventID, holevalue);

        } else if (tabplayer_id.size() == 2) {
            grossscore1 = gross_score1.getText().toString();
            no_of_putts1 = puttsnumber1.getText().toString();
            closest_feet1 = feetText1.getText().toString();
            sand_value1 = sandEditText1.getText().toString();

            grossscore2 = gross_score2.getText().toString();
            no_of_putts2 = puttsnumber2.getText().toString();
            closest_feet2 = feetText2.getText().toString();
            sand_value2 = sandEditText2.getText().toString();

            saveScoreData(admin_ID, eventID, holevalue);


        } else if (tabplayer_id.size() == 3) {
            grossscore1 = gross_score1.getText().toString();
            no_of_putts1 = puttsnumber1.getText().toString();
            closest_feet1 = feetText1.getText().toString();
            sand_value1 = sandEditText1.getText().toString();

            grossscore2 = gross_score2.getText().toString();
            no_of_putts2 = puttsnumber2.getText().toString();
            closest_feet2 = feetText2.getText().toString();
            sand_value2 = sandEditText2.getText().toString();

            grossscore3 = gross_score3.getText().toString();
            no_of_putts3 = puttsnumber3.getText().toString();
            closest_feet3 = feetText3.getText().toString();
            sand_value3 = sandEditText3.getText().toString();

            saveScoreData(admin_ID, eventID, holevalue);

        } else if (tabplayer_id.size() == 4) {*/


        saveScoreData(admin_ID, eventID, holevalue);
        //}

       /* if (!closest_inch.isEmpty()) {

            int i = Integer.parseInt(closest_inch);
            Log.e("Scoremain", "ScoreMain");
            if (i <= 11) {

                saveScoreData(admin_ID, eventID, playerID, holevalue, grossscore, no_of_putts, selected_fairway, sand_value, closest_feet, closest_inch);

            } else {
                Toast.makeText(this, "Please Select inches value less than or equal to 11", Toast.LENGTH_SHORT).show();
            }


        } else {

            saveScoreData(admin_ID, eventID, playerID, holevalue, grossscore, no_of_putts, selected_fairway, sand_value, closest_feet, closest_inch);

        }*/

    }


    public void saveScoreData(String Admin_ID, String Event_ID, String Hole_Value) {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        JSONObject jsonObject = null;

        String closest_feet1 = null;
        String closest_inch1 = null;
        String closest_feet2 = null;
        String closest_inch2 = null;
        String closest_feet3 = null;
        String closest_inch3 = null;
        String closest_feet4 = null;
        String closest_inch4 = null;

        try {

            jsonObject = new JSONObject();
            jsonObject.putOpt("admin_id", Admin_ID);
            jsonObject.putOpt("event_id", Event_ID);
            jsonObject.putOpt("par", parvalue_);

            jsonObject.putOpt("hole_number", Hole_Value);

            JSONArray jsonArray = new JSONArray();
            int s = tabplayer_id.size();
            if (s == 1) {


                grossscore1 = gross_score1.getText().toString();


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue1.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet1 = parts[0];
                        closest_inch1 = parts[1];

                        if (closest_feet1.equalsIgnoreCase("-")){
                            closest_feet1 = "-1";
                        }
                        if (closest_inch1.equalsIgnoreCase("-")){
                            closest_inch1 = "-1";
                        }
                    }else {
                        closest_feet1 = "-1";
                        closest_inch1 = "0";
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet1 = feetValue1.getText().toString();
                    if (closest_feet1.equalsIgnoreCase("-")){
                        closest_feet1 = "-1";
                    }
                }

                if (puttsnumber1.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts1 = "-1";
                } else {
                    no_of_putts1 = puttsnumber1.getText().toString();
                }
                if (sandEditText1.getText().toString().equalsIgnoreCase("-")) {
                    sand_value1 = "-1";
                } else {
                    sand_value1 = sandEditText1.getText().toString();
                }


                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.putOpt("player_id", playerID(0));
                jsonObject1.putOpt("score", grossscore1);
                jsonObject1.putOpt("no_of_putt", no_of_putts1);
                jsonObject1.putOpt("fairway", selected_fairway1);
                jsonObject1.putOpt("sand", sand_value1);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);
                    jsonObject1.putOpt("closest_inch", closest_inch1);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);

                }
                jsonArray.put(jsonObject1);
            } else if (s == 2) {


                grossscore1 = gross_score1.getText().toString();


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue1.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet1 = parts[0];
                        closest_inch1 = parts[1];

                        if (closest_feet1.equalsIgnoreCase("-")){
                            closest_feet1 = "-1";
                        }
                        if (closest_inch1.equalsIgnoreCase("-")){
                            closest_inch1 = "-1";
                        }
                    }else {
                        closest_feet1 = "-1";
                        closest_inch1 = "0";
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet1 = feetValue1.getText().toString();
                    if (closest_feet1.equalsIgnoreCase("-")){
                        closest_feet1 = "-1";
                    }
                }


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue2.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet2 = parts[0];
                        closest_inch2 = parts[1];

                        if (closest_feet2.equalsIgnoreCase("-")){
                            closest_feet2 = "-1";
                        }
                        if (closest_inch2.equalsIgnoreCase("-")){
                            closest_inch2 = "-1";
                        }
                    }else {
                        closest_feet2 = "-1";
                        closest_inch2 = "0";
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet2 = feetValue2.getText().toString();
                    if (closest_feet2.equalsIgnoreCase("-")){
                        closest_feet2 = "-1";
                    }
                }


                if (puttsnumber1.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts1 = "-1";
                } else {
                    no_of_putts1 = puttsnumber1.getText().toString();
                }
                if (sandEditText1.getText().toString().equalsIgnoreCase("-")) {
                    sand_value1 = "-1";
                } else {
                    sand_value1 = sandEditText1.getText().toString();
                }

                grossscore2 = gross_score2.getText().toString();
                if (puttsnumber2.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts2 = "-1";
                } else {
                    no_of_putts2 = puttsnumber2.getText().toString();
                }
                if (sandEditText2.getText().toString().equalsIgnoreCase("-")) {
                    sand_value2 = "-1";
                } else {
                    sand_value2 = sandEditText2.getText().toString();
                }


                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.putOpt("player_id", playerID(0));
                jsonObject1.putOpt("score", grossscore1);
                jsonObject1.putOpt("no_of_putt", no_of_putts1);
                jsonObject1.putOpt("fairway", selected_fairway1);
                jsonObject1.putOpt("sand", sand_value1);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);
                    jsonObject1.putOpt("closest_inch", closest_inch1);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);

                }
                jsonArray.put(jsonObject1);

                JSONObject jsonObject2 = new JSONObject();

                jsonObject2.putOpt("player_id", playerID(1));
                jsonObject2.putOpt("score", grossscore2);
                jsonObject2.putOpt("no_of_putt", no_of_putts2);
                jsonObject2.putOpt("fairway", selected_fairway2);
                jsonObject2.putOpt("sand", sand_value2);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);
                    jsonObject2.putOpt("closest_inch", closest_inch2);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);

                }

                jsonArray.put(jsonObject2);
            } else if (s == 3) {


                grossscore1 = gross_score1.getText().toString();


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue1.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet1 = parts[0];
                        closest_inch1 = parts[1];

                        if (closest_feet1.equalsIgnoreCase("-")){
                            closest_feet1 = "-1";
                        }
                        if (closest_inch1.equalsIgnoreCase("-")){
                            closest_inch1 = "-1";
                        }
                    }else {
                        closest_feet1 = "-1";
                        closest_inch1 = "0";
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet1 = feetValue1.getText().toString();
                    if (closest_feet1.equalsIgnoreCase("-")){
                        closest_feet1 = "-1";
                    }
                }


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue2.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet2 = parts[0];
                        closest_inch2 = parts[1];

                        if (closest_feet2.equalsIgnoreCase("-")){
                            closest_feet2 = "-1";
                        }
                        if (closest_inch2.equalsIgnoreCase("-")){
                            closest_inch2 = "-1";
                        }
                    }else {
                        closest_feet2 = "-1";
                        closest_inch2 = "0";
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet2 = feetValue2.getText().toString();
                    if (closest_feet2.equalsIgnoreCase("-")){
                        closest_feet2 = "-1";
                    }
                }


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue3.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet3 = parts[0];
                        closest_inch3 = parts[1];

                        if (closest_feet3.equalsIgnoreCase("-")){
                            closest_feet3 = "-1";
                        }
                        if (closest_inch3.equalsIgnoreCase("-")){
                            closest_inch3 = "-1";
                        }
                    }else {
                        closest_feet3 = "-1";
                        closest_inch3 = "0";
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet3 = feetValue3.getText().toString();
                    if (closest_feet3.equalsIgnoreCase("-")){
                        closest_feet3 = "-1";
                    }
                }

                if (puttsnumber1.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts1 = "-1";
                } else {
                    no_of_putts1 = puttsnumber1.getText().toString();
                }
                if (sandEditText1.getText().toString().equalsIgnoreCase("-")) {
                    sand_value1 = "-1";
                } else {
                    sand_value1 = sandEditText1.getText().toString();
                }

                grossscore2 = gross_score2.getText().toString();
                if (puttsnumber2.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts2 = "-1";
                } else {
                    no_of_putts2 = puttsnumber2.getText().toString();
                }
                if (sandEditText2.getText().toString().equalsIgnoreCase("-")) {
                    sand_value2 = "-1";
                } else {
                    sand_value2 = sandEditText2.getText().toString();
                }

                grossscore3 = gross_score3.getText().toString();
                if (puttsnumber3.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts3 = "-1";
                } else {
                    no_of_putts3 = puttsnumber3.getText().toString();
                }
                if (sandEditText3.getText().toString().equalsIgnoreCase("-")) {
                    sand_value3 = "-1";
                } else {
                    sand_value3 = sandEditText3.getText().toString();
                }


                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.putOpt("player_id", playerID(0));
                jsonObject1.putOpt("score", grossscore1);
                jsonObject1.putOpt("no_of_putt", no_of_putts1);
                jsonObject1.putOpt("fairway", selected_fairway1);
                jsonObject1.putOpt("sand", sand_value1);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);
                    jsonObject1.putOpt("closest_inch", closest_inch1);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);

                }
                jsonArray.put(jsonObject1);

                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.putOpt("player_id", playerID(1));
                jsonObject2.putOpt("score", grossscore2);
                jsonObject2.putOpt("no_of_putt", no_of_putts2);
                jsonObject2.putOpt("fairway", selected_fairway2);
                jsonObject2.putOpt("sand", sand_value2);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);
                    jsonObject2.putOpt("closest_inch", closest_inch2);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);

                }
                jsonArray.put(jsonObject2);

                JSONObject jsonObject3 = new JSONObject();
                jsonObject3.putOpt("player_id", playerID(2));
                jsonObject3.putOpt("score", grossscore3);
                jsonObject3.putOpt("no_of_putt", no_of_putts3);
                jsonObject3.putOpt("fairway", selected_fairway3);
                jsonObject3.putOpt("sand", sand_value3);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject3.putOpt("closest_feet", closest_feet3);
                    jsonObject3.putOpt("closest_inch", closest_inch3);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject3.putOpt("closest_feet", closest_feet3);

                }
                jsonArray.put(jsonObject3);


            } else if (s == 4) {


                grossscore1 = gross_score1.getText().toString();

                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue1.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet1 = parts[0];
                        closest_inch1 = parts[1];

                        if (closest_feet1.equalsIgnoreCase("-")){
                            closest_feet1 = "-1";
                        }
                        if (closest_inch1.equalsIgnoreCase("-")){
                            closest_inch1 = "-1";
                        }
                    }else {
                        closest_feet1 = "-1";
                        closest_inch1 = "0";
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet1 = feetValue1.getText().toString();
                    if (closest_feet1.equalsIgnoreCase("-")){
                        closest_feet1 = "-1";
                    }
                }


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue2.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet2 = parts[0];
                        closest_inch2 = parts[1];

                        if (closest_feet2.equalsIgnoreCase("-")){
                            closest_feet2 = "-1";
                        }
                        if (closest_inch2.equalsIgnoreCase("-")){
                            closest_inch2 = "-1";
                        }
                    }else {
                        closest_feet2 = "-1";
                        closest_inch2 = "0";
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet2 = feetValue2.getText().toString();
                    if (closest_feet2.equalsIgnoreCase("-")){
                        closest_feet2 = "-1";
                    }
                }


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue3.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet3 = parts[0];
                        closest_inch3 = parts[1];

                        if (closest_feet3.equalsIgnoreCase("-")){
                            closest_feet3 = "-1";
                        }
                        if (closest_inch3.equalsIgnoreCase("-")){
                            closest_inch3 = "-1";
                        }
                    }else {
                        closest_feet3 = "-1";
                        closest_inch3 = "0";
                    }
            } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet3 = feetValue3.getText().toString();
                    if (closest_feet3.equalsIgnoreCase("-")){
                        closest_feet3 = "-1";
                    }
                }

                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue4.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet4 = parts[0];
                        closest_inch4 = parts[1];

                        if (closest_feet4.equalsIgnoreCase("-")){
                            closest_feet4 = "-1";
                        }
                        if (closest_inch4.equalsIgnoreCase("-")){
                            closest_inch4 = "-1";
                        }
                    }else {
                        closest_feet4 = "-1";
                        closest_inch4 = "0";
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet4 = feetValue4.getText().toString();
                    if (closest_feet4.equalsIgnoreCase("-")){
                        closest_feet4 = "-1";
                    }
                }


                if (puttsnumber1.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts1 = "-1";
                } else {
                    no_of_putts1 = puttsnumber1.getText().toString();
                }
                if (sandEditText1.getText().toString().equalsIgnoreCase("-")) {
                    sand_value1 = "-1";
                } else {
                    sand_value1 = sandEditText1.getText().toString();
                }

                grossscore2 = gross_score2.getText().toString();

                if (puttsnumber2.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts2 = "-1";
                } else {
                    no_of_putts2 = puttsnumber2.getText().toString();
                }
                if (sandEditText2.getText().toString().equalsIgnoreCase("-")) {
                    sand_value2 = "-1";
                } else {
                    sand_value2 = sandEditText2.getText().toString();
                }

                grossscore3 = gross_score3.getText().toString();
                if (puttsnumber3.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts3 = "-1";
                } else {
                    no_of_putts3 = puttsnumber3.getText().toString();
                }
                if (sandEditText3.getText().toString().equalsIgnoreCase("-")) {
                    sand_value3 = "-1";
                } else {
                    sand_value3 = sandEditText3.getText().toString();
                }

                grossscore4 = gross_score4.getText().toString();
                if (puttsnumber4.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts4 = "-1";
                } else {
                    no_of_putts4 = puttsnumber4.getText().toString();
                }
                if (sandEditText4.getText().toString().equalsIgnoreCase("-")) {
                    sand_value4 = "-1";
                } else {
                    sand_value4 = sandEditText4.getText().toString();
                }


                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.putOpt("player_id", playerID(0));
                jsonObject1.putOpt("score", grossscore1);
                jsonObject1.putOpt("no_of_putt", no_of_putts1);
                jsonObject1.putOpt("fairway", selected_fairway1);
                jsonObject1.putOpt("sand", sand_value1);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);
                    jsonObject1.putOpt("closest_inch", closest_inch1);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);

                }
                jsonArray.put(jsonObject1);

                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.putOpt("player_id", playerID(1));
                jsonObject2.putOpt("score", grossscore2);
                jsonObject2.putOpt("no_of_putt", no_of_putts2);
                jsonObject2.putOpt("fairway", selected_fairway2);
                jsonObject2.putOpt("sand", sand_value2);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);
                    jsonObject2.putOpt("closest_inch", closest_inch2);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);

                }
                jsonArray.put(jsonObject2);

                JSONObject jsonObject3 = new JSONObject();
                jsonObject3.putOpt("player_id", playerID(2));
                jsonObject3.putOpt("score", grossscore3);
                jsonObject3.putOpt("no_of_putt", no_of_putts3);
                jsonObject3.putOpt("fairway", selected_fairway3);
                jsonObject3.putOpt("sand", sand_value3);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject3.putOpt("closest_feet", closest_feet3);
                    jsonObject3.putOpt("closest_inch", closest_inch3);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject3.putOpt("closest_feet", closest_feet3);

                }
                jsonArray.put(jsonObject3);

                JSONObject jsonObject4 = new JSONObject();
                jsonObject4.putOpt("player_id", playerID(3));
                jsonObject4.putOpt("score", grossscore4);
                jsonObject4.putOpt("no_of_putt", no_of_putts4);
                jsonObject4.putOpt("fairway", selected_fairway4);
                jsonObject4.putOpt("sand", sand_value4);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject4.putOpt("closest_feet", closest_feet4);
                    jsonObject4.putOpt("closest_inch", closest_inch4);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject4.putOpt("closest_feet", closest_feet4);

                }
                jsonArray.put(jsonObject4);
            }

            jsonObject.putOpt("player_score", jsonArray);
            jsonObject.putOpt("version", "2");

            Log.v("post score", jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.SAVE_PLAYER_SCORE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                responseAddsScoreValue(response);
                Log.e("SCORETABMAIN", "getsavescoredata" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Utility.showLogError(this, "Error in " + "getsavescoredata URL = " + PUTTAPI.SAVE_PLAYER_SCORE);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


    }

    public void responseAddsScoreValue(JSONObject response) {

        try {
            JSONObject j = response.getJSONObject("output");

            String message = j.getString("message");
            JSONObject jsonObject1 = j.getJSONObject("hole_data");
            String holeindex = jsonObject1.getString("hole_index");
            String parvalue = jsonObject1.getString("par_value");
            isSpot = jsonObject1.getString("is_spot_type");

            sandEditText1.setText("-");
            puttsnumber1.setText("-");

            radioGroup1.clearCheck();
            selected_fairway1 = "0";


            feetValue1.setText("-");

            sandEditText2.setText("-");
            puttsnumber2.setText("-");

            radioGroup2.clearCheck();
            selected_fairway2 = "0";


            feetValue2.setText("-");

            sandEditText3.setText("-");
            puttsnumber3.setText("-");

            radioGroup3.clearCheck();
            selected_fairway3 = "0";

            feetValue3.setText("-");

            sandEditText4.setText("-");
            puttsnumber4.setText("-");

            radioGroup4.clearCheck();
            selected_fairway4 = "0";

            feetValue4.setText("-");

            bottomnext1();


        } catch (JSONException je) {
            je.printStackTrace();
        }
    }


    public void tempScoreData(String Admin_ID, String Event_ID, String Hole_Value) {

        String closest_feet1 = null;
        String closest_inch1 = null;
        String closest_feet2 = null;
        String closest_inch2 = null;
        String closest_feet3 = null;
        String closest_inch3 = null;
        String closest_feet4 = null;
        String closest_inch4 = null;

        JSONObject jsonObject = null;

        try {

            jsonObject = new JSONObject();

            jsonObject.putOpt("event_id", Event_ID);
            jsonObject.putOpt("par", parvalue_);

            jsonObject.putOpt("hole_number", Hole_Value);

            JSONArray jsonArray = new JSONArray();
            int s = tabplayer_id.size();

            if (s == 1) {


                grossscore1 = gross_score1.getText().toString();


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue1.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet1 = parts[0];
                        closest_inch1 = parts[1];

                        if (closest_feet1.equalsIgnoreCase("-")){
                            closest_feet1 = "-1";
                        }
                        if (closest_inch1.equalsIgnoreCase("-")){
                            closest_inch1 = "-1";
                        }
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet1 = feetValue1.getText().toString();
                    if (closest_feet1.equalsIgnoreCase("-")){
                        closest_feet1 = "-1";
                    }
                }



                if (puttsnumber1.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts1 = "-1";
                } else {
                    no_of_putts1 = puttsnumber1.getText().toString();
                }
                if (sandEditText1.getText().toString().equalsIgnoreCase("-")) {
                    sand_value1 = "-1";
                } else {
                    sand_value1 = sandEditText1.getText().toString();
                }


                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.putOpt("player_id", playerID(0));
                jsonObject1.putOpt("score", grossscore1);
                jsonObject1.putOpt("no_of_putt", no_of_putts1);
                jsonObject1.putOpt("fairway", selected_fairway1);
                jsonObject1.putOpt("sand", sand_value1);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);
                    jsonObject1.putOpt("closest_inch", closest_inch1);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);

                }
                jsonArray.put(jsonObject1);
            } else if (s == 2) {


                grossscore1 = gross_score1.getText().toString();


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch1 = feetValue1.getText().toString();

                    if (feetInch1.length() > 3) {
                        String[] parts = feetInch1.split("'");
                        closest_feet1 = parts[0];
                        closest_inch1 = parts[1];

                        if (closest_feet1.equalsIgnoreCase("-")){
                            closest_feet1 = "-1";
                        }
                        if (closest_inch1.equalsIgnoreCase("-")){
                            closest_inch1 = "-1";
                        }
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet1 = feetValue1.getText().toString();
                    if (closest_feet1.equalsIgnoreCase("-")){
                        closest_feet1 = "-1";
                    }
                }



                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch2 = feetValue2.getText().toString();


                    if (feetInch2.length() > 3) {
                        String[] parts = feetInch2.split("'");
                        closest_feet2 = parts[0];
                        closest_inch2 = parts[1];

                        if (closest_feet2.equalsIgnoreCase("-")){
                            closest_feet2 = "-1";
                        }
                        if (closest_inch2.equalsIgnoreCase("-")){
                            closest_inch2 = "-1";
                        }
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet2 = feetValue2.getText().toString();
                    if (closest_feet2.equalsIgnoreCase("-")){
                        closest_feet2 = "-1";
                    }
                }



                if (puttsnumber1.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts1 = "-1";
                } else {
                    no_of_putts1 = puttsnumber1.getText().toString();
                }
                if (sandEditText1.getText().toString().equalsIgnoreCase("-")) {
                    sand_value1 = "-1";
                } else {
                    sand_value1 = sandEditText1.getText().toString();
                }

                grossscore2 = gross_score2.getText().toString();
                if (puttsnumber2.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts2 = "-1";
                } else {
                    no_of_putts2 = puttsnumber2.getText().toString();
                }
                if (sandEditText2.getText().toString().equalsIgnoreCase("-")) {
                    sand_value2 = "-1";
                } else {
                    sand_value2 = sandEditText2.getText().toString();
                }


                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.putOpt("player_id", playerID(0));
                jsonObject1.putOpt("score", grossscore1);
                jsonObject1.putOpt("no_of_putt", no_of_putts1);
                jsonObject1.putOpt("fairway", selected_fairway1);
                jsonObject1.putOpt("sand", sand_value1);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);
                    jsonObject1.putOpt("closest_inch", closest_inch1);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);

                }
                jsonArray.put(jsonObject1);

                JSONObject jsonObject2 = new JSONObject();

                jsonObject2.putOpt("player_id", playerID(1));
                jsonObject2.putOpt("score", grossscore2);
                jsonObject2.putOpt("no_of_putt", no_of_putts2);
                jsonObject2.putOpt("fairway", selected_fairway2);
                jsonObject2.putOpt("sand", sand_value2);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);
                    jsonObject2.putOpt("closest_inch", closest_inch2);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);

                }

                jsonArray.put(jsonObject2);
            } else if (s == 3) {


                grossscore1 = gross_score1.getText().toString();


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue1.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet1 = parts[0];
                        closest_inch1 = parts[1];

                        if (closest_feet1.equalsIgnoreCase("-")){
                            closest_feet1 = "-1";
                        }
                        if (closest_inch1.equalsIgnoreCase("-")){
                            closest_inch1 = "-1";
                        }
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet1 = feetValue1.getText().toString();
                    if (closest_feet1.equalsIgnoreCase("-")){
                        closest_feet1 = "-1";
                    }
                }


                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue2.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet2 = parts[0];
                        closest_inch2 = parts[1];

                        if (closest_feet2.equalsIgnoreCase("-")){
                            closest_feet2 = "-1";
                        }
                        if (closest_inch2.equalsIgnoreCase("-")){
                            closest_inch2 = "-1";
                        }
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet2 = feetValue2.getText().toString();
                    if (closest_feet2.equalsIgnoreCase("-")){
                        closest_feet2 = "-1";
                    }
                }



                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue3.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet3 = parts[0];
                        closest_inch3 = parts[1];

                        if (closest_feet3.equalsIgnoreCase("-")){
                            closest_feet3 = "-1";
                        }
                        if (closest_inch3.equalsIgnoreCase("-")){
                            closest_inch3 = "-1";
                        }
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet3 = feetValue3.getText().toString();
                    if (closest_feet3.equalsIgnoreCase("-")){
                        closest_feet3 = "-1";
                    }
                }


                if (puttsnumber1.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts1 = "-1";
                } else {
                    no_of_putts1 = puttsnumber1.getText().toString();
                }
                if (sandEditText1.getText().toString().equalsIgnoreCase("-")) {
                    sand_value1 = "-1";
                } else {
                    sand_value1 = sandEditText1.getText().toString();
                }

                grossscore2 = gross_score2.getText().toString();
                if (puttsnumber2.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts2 = "-1";
                } else {
                    no_of_putts2 = puttsnumber2.getText().toString();
                }
                if (sandEditText2.getText().toString().equalsIgnoreCase("-")) {
                    sand_value2 = "-1";
                } else {
                    sand_value2 = sandEditText2.getText().toString();
                }

                grossscore3 = gross_score3.getText().toString();
                if (puttsnumber3.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts3 = "-1";
                } else {
                    no_of_putts3 = puttsnumber3.getText().toString();
                }
                if (sandEditText3.getText().toString().equalsIgnoreCase("-")) {
                    sand_value3 = "-1";
                } else {
                    sand_value3 = sandEditText3.getText().toString();
                }


                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.putOpt("player_id", playerID(0));
                jsonObject1.putOpt("score", grossscore1);
                jsonObject1.putOpt("no_of_putt", no_of_putts1);
                jsonObject1.putOpt("fairway", selected_fairway1);
                jsonObject1.putOpt("sand", sand_value1);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);
                    jsonObject1.putOpt("closest_inch", closest_inch1);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);

                }
                jsonArray.put(jsonObject1);

                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.putOpt("player_id", playerID(1));
                jsonObject2.putOpt("score", grossscore2);
                jsonObject2.putOpt("no_of_putt", no_of_putts2);
                jsonObject2.putOpt("fairway", selected_fairway2);
                jsonObject2.putOpt("sand", sand_value2);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);
                    jsonObject2.putOpt("closest_inch", closest_inch2);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);

                }
                jsonArray.put(jsonObject2);

                JSONObject jsonObject3 = new JSONObject();
                jsonObject3.putOpt("player_id", playerID(2));
                jsonObject3.putOpt("score", grossscore3);
                jsonObject3.putOpt("no_of_putt", no_of_putts3);
                jsonObject3.putOpt("fairway", selected_fairway3);
                jsonObject3.putOpt("sand", sand_value3);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject3.putOpt("closest_feet", closest_feet3);
                    jsonObject3.putOpt("closest_inch", closest_inch3);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject3.putOpt("closest_feet", closest_feet3);

                }
                jsonArray.put(jsonObject3);


            } else if (s == 4) {


                grossscore1 = gross_score1.getText().toString();

                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue1.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet1 = parts[0];
                        closest_inch1 = parts[1];

                        if (closest_feet1.equalsIgnoreCase("-")){
                            closest_feet1 = "-1";
                        }
                        if (closest_inch1.equalsIgnoreCase("-")){
                            closest_inch1 = "-1";
                        }
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet1 = feetValue1.getText().toString();
                    if (closest_feet1.equalsIgnoreCase("-")){
                        closest_feet1 = "-1";
                    }
                }



                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue2.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet2 = parts[0];
                        closest_inch2 = parts[1];

                        if (closest_feet2.equalsIgnoreCase("-")){
                            closest_feet2 = "-1";
                        }
                        if (closest_inch2.equalsIgnoreCase("-")){
                            closest_inch2 = "-1";
                        }
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet2 = feetValue2.getText().toString();
                    if (closest_feet2.equalsIgnoreCase("-")){
                        closest_feet2 = "-1";
                    }
                }



                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue3.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet3 = parts[0];
                        closest_inch3 = parts[1];

                        if (closest_feet3.equalsIgnoreCase("-")){
                            closest_feet3 = "-1";
                        }
                        if (closest_inch3.equalsIgnoreCase("-")){
                            closest_inch3 = "-1";
                        }
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet3 = feetValue3.getText().toString();
                    if (closest_feet3.equalsIgnoreCase("-")){
                        closest_feet3 = "-1";
                    }
                }

                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    String feetInch = feetValue4.getText().toString();

                    if (feetInch.length() > 3) {
                        String[] parts = feetInch.split("'");
                        closest_feet4 = parts[0];
                        closest_inch4 = parts[1];

                        if (closest_feet4.equalsIgnoreCase("-")){
                            closest_feet4 = "-1";
                        }
                        if (closest_inch4.equalsIgnoreCase("-")){
                            closest_inch4 = "-1";
                        }
                    }
                } else if (isSpot.equalsIgnoreCase("3")) {
                    closest_feet4 = feetValue4.getText().toString();
                    if (closest_feet4.equalsIgnoreCase("-")){
                        closest_feet4 = "-1";
                    }
                }



                if (puttsnumber1.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts1 = "-1";
                } else {
                    no_of_putts1 = puttsnumber1.getText().toString();
                }
                if (sandEditText1.getText().toString().equalsIgnoreCase("-")) {
                    sand_value1 = "-1";
                } else {
                    sand_value1 = sandEditText1.getText().toString();
                }

                grossscore2 = gross_score2.getText().toString();

                if (puttsnumber2.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts2 = "-1";
                } else {
                    no_of_putts2 = puttsnumber2.getText().toString();
                }
                if (sandEditText2.getText().toString().equalsIgnoreCase("-")) {
                    sand_value2 = "-1";
                } else {
                    sand_value2 = sandEditText2.getText().toString();
                }

                grossscore3 = gross_score3.getText().toString();
                if (puttsnumber3.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts3 = "-1";
                } else {
                    no_of_putts3 = puttsnumber3.getText().toString();
                }
                if (sandEditText3.getText().toString().equalsIgnoreCase("-")) {
                    sand_value3 = "-1";
                } else {
                    sand_value3 = sandEditText3.getText().toString();
                }

                grossscore4 = gross_score4.getText().toString();
                if (puttsnumber4.getText().toString().equalsIgnoreCase("-")) {
                    no_of_putts4 = "-1";
                } else {
                    no_of_putts4 = puttsnumber4.getText().toString();
                }
                if (sandEditText4.getText().toString().equalsIgnoreCase("-")) {
                    sand_value4 = "-1";
                } else {
                    sand_value4 = sandEditText4.getText().toString();
                }

                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.putOpt("player_id", playerID(0));
                jsonObject1.putOpt("score", grossscore1);
                jsonObject1.putOpt("no_of_putt", no_of_putts1);
                jsonObject1.putOpt("fairway", selected_fairway1);
                jsonObject1.putOpt("sand", sand_value1);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);
                    jsonObject1.putOpt("closest_inch", closest_inch1);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject1.putOpt("closest_feet", closest_feet1);

                }
                jsonArray.put(jsonObject1);

                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.putOpt("player_id", playerID(1));
                jsonObject2.putOpt("score", grossscore2);
                jsonObject2.putOpt("no_of_putt", no_of_putts2);
                jsonObject2.putOpt("fairway", selected_fairway2);
                jsonObject2.putOpt("sand", sand_value2);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);
                    jsonObject2.putOpt("closest_inch", closest_inch2);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject2.putOpt("closest_feet", closest_feet2);

                }
                jsonArray.put(jsonObject2);

                JSONObject jsonObject3 = new JSONObject();
                jsonObject3.putOpt("player_id", playerID(2));
                jsonObject3.putOpt("score", grossscore3);
                jsonObject3.putOpt("no_of_putt", no_of_putts3);
                jsonObject3.putOpt("fairway", selected_fairway3);
                jsonObject3.putOpt("sand", sand_value3);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject3.putOpt("closest_feet", closest_feet3);
                    jsonObject3.putOpt("closest_inch", closest_inch3);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject3.putOpt("closest_feet", closest_feet3);

                }
                jsonArray.put(jsonObject3);

                JSONObject jsonObject4 = new JSONObject();
                jsonObject4.putOpt("player_id", playerID(3));
                jsonObject4.putOpt("score", grossscore4);
                jsonObject4.putOpt("no_of_putt", no_of_putts4);
                jsonObject4.putOpt("fairway", selected_fairway4);
                jsonObject4.putOpt("sand", sand_value4);
                if (isSpot.equalsIgnoreCase("1") || isSpot.equalsIgnoreCase("2")) {
                    jsonObject4.putOpt("closest_feet", closest_feet4);
                    jsonObject4.putOpt("closest_inch", closest_inch4);
                } else if (isSpot.equalsIgnoreCase("3")) {
                    jsonObject4.putOpt("closest_feet", closest_feet4);

                }
                jsonArray.put(jsonObject4);
            }

            jsonObject.putOpt("player_score", jsonArray);
            jsonObject.putOpt("version", "2");

            Log.v("tempraryScore", jsonObject.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.TEMP_SAVE_SCORE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                responseTempScoreValue(response);

                Log.e("Temp Score", "temp score" + response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Utility.showLogError(this, "Error in " + "Temp score URL = " + PUTTAPI.TEMP_SAVE_SCORE);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


    }

    public void responseTempScoreValue(JSONObject response) {

        try {
            JSONObject j = response.getJSONObject("output");

            String status = j.getString("status");
            if (status.equalsIgnoreCase("1")) {

                String message = j.getString("message");
            } else {
                String msg = j.getString("message");
            }


        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    public String playerID(int tabNO) {
        return tabplayer_id.get(tabNO);
    }


    public void endRoundRequest(String Admin_ID, String Event_ID, String Player_ID) {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        JSONObject jsonObject = null;
        try {

            jsonObject = new JSONObject();
            //  jsonObject.putOpt("admin_id", Admin_ID);
            jsonObject.putOpt("event_id", Event_ID);
            jsonObject.putOpt("user_id", Admin_ID);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.END_ROUND_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                endRoundScoreResponse(response);
                Log.e("End Round", "End Round" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Utility.showLogError(this, "Error in " + "End Round URL = " + PUTTAPI.END_ROUND_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


    }

    public void endRoundScoreResponse(JSONObject response) {

        try {
            JSONObject j = response.getJSONObject("output");
            String message = j.getString("message");

            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();

        } catch (JSONException je) {
            je.printStackTrace();
        }
    }


    public void saveRoundRequest(String Admin_ID, String Event_ID, String Player_ID) {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;

        try {

            jsonObject = new JSONObject();
            //  jsonObject.putOpt("admin_id", Admin_ID);
            jsonObject.putOpt("event_id", Event_ID);

            jsonObject.putOpt("user_id", Admin_ID);

            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.SAVE_SUBMIT_ROUND_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                saveRoundScoreResponse(response);
                Log.e("SAVE Round", "SAVE Round" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Utility.showLogError(this, "Error in " + "SAVE Round URL = " + PUTTAPI.SAVE_SUBMIT_ROUND_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


    }

    public void saveRoundScoreResponse(JSONObject response) {

        try {
            JSONObject j = response.getJSONObject("output");
            String message = j.getString("message");
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();

        } catch (JSONException je) {
            je.printStackTrace();
        }
    }


    public void feetInchPopUp1() {

        String feet1 = "0";
        String inch1 = "0";

        final Dialog d = new Dialog(this);
        d.setTitle("Pick your distance");

        d.setContentView(R.layout.feet_inch_dialog);
        Button set = (Button) d.findViewById(R.id.btn_setheight);
        Button reset  = (Button)d.findViewById(R.id.btn_resetheight);
        d.setCancelable(false);

        final NumberPicker feetpick = (NumberPicker) d
                .findViewById(R.id.feetpicker);
        feetpick.setMaxValue(50);
        feetpick.setMinValue(0);
        feetpick.setValue(Integer.parseInt(feet1));
        feetpick.setWrapSelectorWheel(false);

        final NumberPicker inchPick = (NumberPicker) d
                .findViewById(R.id.inchpicker);
        inchPick.setMaxValue(11);
        inchPick.setMinValue(0);
        inchPick.setValue(Integer.parseInt(inch1));
        inchPick.setWrapSelectorWheel(false);

        d.show();
        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String feet = String.valueOf(feetpick.getValue());
                String inch = String.valueOf(inchPick.getValue());

                feetValue1.setText(feet + "'" + inch + "''");

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feetValue1.setText("-" + "'" + "-" + "''");

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });
    }

    public void feetInchPopUp2() {


        String feet2 = "0";
        String inch2 = "0";


        final Dialog d = new Dialog(this);
        d.setTitle("Pick your distance");

        d.setContentView(R.layout.feet_inch_dialog);
        Button set = (Button) d.findViewById(R.id.btn_setheight);
        Button reset  = (Button)d.findViewById(R.id.btn_resetheight);
        d.setCancelable(false);

        final NumberPicker feetpick = (NumberPicker) d
                .findViewById(R.id.feetpicker);
        feetpick.setMaxValue(50);
        feetpick.setMinValue(0);
        feetpick.setValue(Integer.parseInt(feet2));
        feetpick.setWrapSelectorWheel(false);

        final NumberPicker inchPick = (NumberPicker) d
                .findViewById(R.id.inchpicker);
        inchPick.setMaxValue(11);
        inchPick.setMinValue(0);
        inchPick.setValue(Integer.parseInt(inch2));
        inchPick.setWrapSelectorWheel(false);

        d.show();
        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String feet = String.valueOf(feetpick.getValue());
                String inch = String.valueOf(inchPick.getValue());

                feetValue2.setText(feet + "'" + inch + "''");

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feetValue2.setText("-" + "'" + "-" + "''");
                d.dismiss();
            }
        });

    }


    public void feetInchPopUp3() {


        String feet3 = "0";
        String inch3 = "0";

        final Dialog d = new Dialog(this);
        d.setTitle("Pick your distance");

        d.setContentView(R.layout.feet_inch_dialog);
        Button set = (Button) d.findViewById(R.id.btn_setheight);
        Button reset  = (Button)d.findViewById(R.id.btn_resetheight);
        d.setCancelable(false);

        final NumberPicker feetpick = (NumberPicker) d
                .findViewById(R.id.feetpicker);
        feetpick.setMaxValue(50);
        feetpick.setMinValue(0);
        feetpick.setValue(Integer.parseInt(feet3));
        feetpick.setWrapSelectorWheel(false);

        final NumberPicker inchPick = (NumberPicker) d
                .findViewById(R.id.inchpicker);
        inchPick.setMaxValue(11);
        inchPick.setMinValue(0);
        inchPick.setValue(Integer.parseInt(inch3));
        inchPick.setWrapSelectorWheel(false);

        d.show();
        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String feet = String.valueOf(feetpick.getValue());
                String inch = String.valueOf(inchPick.getValue());

                feetValue3.setText(feet + "'" + inch + "''");

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feetValue3.setText("-" + "'" + "-" + "''");

                d.dismiss();
            }
        });
    }


    public void feetInchPopUp4() {


        String feet4 = "0";
        String inch4 = "0";


        final Dialog d = new Dialog(this);
        d.setTitle("Pick your distance");

        d.setContentView(R.layout.feet_inch_dialog);
        Button set = (Button) d.findViewById(R.id.btn_setheight);
        Button reset  = (Button)d.findViewById(R.id.btn_resetheight);
        d.setCancelable(false);

        final NumberPicker feetpick = (NumberPicker) d
                .findViewById(R.id.feetpicker);
        feetpick.setMaxValue(50);
        feetpick.setMinValue(0);
        feetpick.setValue(Integer.parseInt(feet4));
        feetpick.setWrapSelectorWheel(false);

        final NumberPicker inchPick = (NumberPicker) d
                .findViewById(R.id.inchpicker);
        inchPick.setMaxValue(11);
        inchPick.setMinValue(0);
        inchPick.setValue(Integer.parseInt(inch4));
        inchPick.setWrapSelectorWheel(false);

        d.show();
        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String feet = String.valueOf(feetpick.getValue());
                String inch = String.valueOf(inchPick.getValue());

                feetValue4.setText(feet + "'" + inch + "''");

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feetValue4.setText("-" + "'" + "-" + "''");

                d.dismiss();
            }
        });

    }


    public void yardPopUp1() {

        String yard1 = "300";


        final Dialog d = new Dialog(this);
        d.setTitle("Pick your distance");

        d.setContentView(R.layout.feet_inch_dialog);
        Button set = (Button) d.findViewById(R.id.btn_setheight);
        Button reset  = (Button)d.findViewById(R.id.btn_resetheight);
        LinearLayout inchLay = (LinearLayout) d.findViewById(R.id.inchLay);
        inchLay.setVisibility(View.GONE);

        TextView feetText = (TextView) d.findViewById(R.id.textView1);
        feetText.setText("Yard");

        d.setCancelable(true);

        String yrd1_value = feetValue1.getText().toString();
        if (yrd1_value != null && yrd1_value.length() > 3) {
            yard1 = yrd1_value;
        }

        final NumberPicker feetpick = (NumberPicker) d
                .findViewById(R.id.feetpicker);
        feetpick.setMaxValue(450);
        feetpick.setMinValue(150);
        feetpick.setValue(Integer.parseInt(yard1));
        feetpick.setWrapSelectorWheel(false);


        final NumberPicker inchPick = (NumberPicker) d.findViewById(R.id.inchpicker);
        inchPick.setMaxValue(11);
        inchPick.setMinValue(0);
       // inchPick.setValue(Integer.parseInt(yard1));
        inchPick.setWrapSelectorWheel(false);
        inchPick.setVisibility(View.GONE);

        d.show();
        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String yard = String.valueOf(feetpick.getValue());

                feetValue1.setText(yard);

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feetValue1.setText("-");

                d.dismiss();
            }
        });

    }

    public void yardPopUp2() {

        String yard2 = "300";

        final Dialog d = new Dialog(this);
        d.setTitle("Pick your distance");

        d.setContentView(R.layout.feet_inch_dialog);
        Button set = (Button) d.findViewById(R.id.btn_setheight);
        Button reset  = (Button)d.findViewById(R.id.btn_resetheight);
        LinearLayout inchLay = (LinearLayout) d.findViewById(R.id.inchLay);
        inchLay.setVisibility(View.GONE);

        TextView feetText = (TextView) d.findViewById(R.id.textView1);
        feetText.setText("Yard");

        d.setCancelable(true);

        String yrd2_value = feetValue2.getText().toString();
        if (yrd2_value != null && yrd2_value.length() > 3) {
            yard2 = yrd2_value;
        }

        final NumberPicker feetpick = (NumberPicker) d
                .findViewById(R.id.feetpicker);
        feetpick.setMaxValue(450);
        feetpick.setMinValue(150);
        feetpick.setValue(Integer.parseInt(yard2));
        feetpick.setWrapSelectorWheel(false);

        ;

        final NumberPicker inchPick = (NumberPicker) d.findViewById(R.id.inchpicker);
        inchPick.setMaxValue(11);
        inchPick.setMinValue(0);
       // inchPick.setValue(Integer.parseInt(yard2));
        inchPick.setWrapSelectorWheel(false);
        inchPick.setVisibility(View.GONE);

        d.show();
        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String yard = String.valueOf(feetpick.getValue());

                feetValue2.setText(yard);

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feetValue2.setText("-");

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });

    }

    public void yardPopUp3() {

        String yard3 = "300";

        final Dialog d = new Dialog(this);
        d.setTitle("Pick your distance");

        d.setContentView(R.layout.feet_inch_dialog);
        Button set = (Button) d.findViewById(R.id.btn_setheight);
        Button reset  = (Button)d.findViewById(R.id.btn_resetheight);
        LinearLayout inchLay = (LinearLayout) d.findViewById(R.id.inchLay);
        inchLay.setVisibility(View.GONE);

        TextView feetText = (TextView) d.findViewById(R.id.textView1);
        feetText.setText("Yard");

        d.setCancelable(true);

        String yrd3_value = feetValue3.getText().toString();
        if (yrd3_value != null && yrd3_value.length() > 3) {
            yard3 = yrd3_value;
        }

        final NumberPicker feetpick = (NumberPicker) d.findViewById(R.id.feetpicker);
        feetpick.setMaxValue(450);
        feetpick.setMinValue(150);
        feetpick.setValue(Integer.parseInt(yard3));
        feetpick.setWrapSelectorWheel(false);


        final NumberPicker inchPick = (NumberPicker) d.findViewById(R.id.inchpicker);
        inchPick.setMaxValue(11);
        inchPick.setMinValue(0);
       // inchPick.setValue(Integer.parseInt(yard3));
        inchPick.setWrapSelectorWheel(false);
        inchPick.setVisibility(View.GONE);

        d.show();
        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String yard = String.valueOf(feetpick.getValue());

                feetValue3.setText(yard);

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feetValue3.setText("-");


                d.dismiss();
            }
        });

    }

    public void yardPopUp4() {

        String yard4 = "300";

        final Dialog d = new Dialog(this);
        d.setTitle("Pick your distance");

        d.setContentView(R.layout.feet_inch_dialog);
        Button set = (Button) d.findViewById(R.id.btn_setheight);
        Button reset  = (Button)d.findViewById(R.id.btn_resetheight);
        LinearLayout inchLay = (LinearLayout) d.findViewById(R.id.inchLay);
        inchLay.setVisibility(View.GONE);

        TextView feetText = (TextView) d.findViewById(R.id.textView1);
        feetText.setText("Yard");

        d.setCancelable(true);
        String yrd4_value = feetValue4.getText().toString();
        if (yrd4_value != null && yrd4_value.length() > 3) {
            yard4 = yrd4_value;
        }

        final NumberPicker feetpick = (NumberPicker) d
                .findViewById(R.id.feetpicker);
        feetpick.setMaxValue(450);
        feetpick.setMinValue(150);
        feetpick.setValue(Integer.parseInt(yard4));
        feetpick.setWrapSelectorWheel(false);


        final NumberPicker inchPick = (NumberPicker) d.findViewById(R.id.inchpicker);
        inchPick.setMaxValue(11);
        inchPick.setMinValue(0);
       // inchPick.setValue(Integer.parseInt(yard4));
        inchPick.setWrapSelectorWheel(false);
        inchPick.setVisibility(View.GONE);

        d.show();
        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String yard = String.valueOf(feetpick.getValue());

                feetValue4.setText(yard);

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feetValue4.setText("-");

                // UserDataModel.setHeight(feet + " , " + inch);
                // displayHeight(feet, inch);
                d.dismiss();
            }
        });
    }

    public void autopressPopup() {

        ImageView finishdialogfrag;

        String eventID;

        final Dialog dialog = new Dialog(AddScoreNew.this, android.R.style.Theme_Translucent_NoTitleBar);
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


        finishdialogfrag = (ImageView) dialog.findViewById(R.id.finishdialogfrag);

        front1_hole1 = (LinearLayout) dialog.findViewById(R.id.front_layout_r1);
        front1_hole2 = (LinearLayout) dialog.findViewById(R.id.front_layout_r2);
        front1_hole3 = (LinearLayout) dialog.findViewById(R.id.front_layout_r3);
        front1_hole4 = (LinearLayout) dialog.findViewById(R.id.front_layout_r4);
        front1_hole5 = (LinearLayout) dialog.findViewById(R.id.front_layout_r5);
        front1_hole6 = (LinearLayout) dialog.findViewById(R.id.front_layout_r6);
        front1_hole7 = (LinearLayout) dialog.findViewById(R.id.front_layout_r7);
        front1_hole8 = (LinearLayout) dialog.findViewById(R.id.front_layout_r8);
        front1_hole9 = (LinearLayout) dialog.findViewById(R.id.front_layout_r9);
        front1_hole10 = (LinearLayout) dialog.findViewById(R.id.front_layout_r10);
        front1_hole11 = (LinearLayout) dialog.findViewById(R.id.front_layout_r11);
        front1_hole12 = (LinearLayout) dialog.findViewById(R.id.front_layout_r12);
        front1_hole13 = (LinearLayout) dialog.findViewById(R.id.front_layout_r13);
        front1_hole14 = (LinearLayout) dialog.findViewById(R.id.front_layout_r14);
        front1_hole15 = (LinearLayout) dialog.findViewById(R.id.front_layout_r15);
        front1_hole16 = (LinearLayout) dialog.findViewById(R.id.front_layout_r16);
        front1_hole17 = (LinearLayout) dialog.findViewById(R.id.front_layout_r17);
        front1_hole18 = (LinearLayout) dialog.findViewById(R.id.front_layout_r18);

        back1_hole1 = (LinearLayout) dialog.findViewById(R.id.back_layout_r1);
        back1_hole2 = (LinearLayout) dialog.findViewById(R.id.back_layout_r2);
        back1_hole3 = (LinearLayout) dialog.findViewById(R.id.back_layout_r3);
        back1_hole4 = (LinearLayout) dialog.findViewById(R.id.back_layout_r4);
        back1_hole5 = (LinearLayout) dialog.findViewById(R.id.back_layout_r5);
        back1_hole6 = (LinearLayout) dialog.findViewById(R.id.back_layout_r6);
        back1_hole7 = (LinearLayout) dialog.findViewById(R.id.back_layout_r7);
        back1_hole8 = (LinearLayout) dialog.findViewById(R.id.back_layout_r8);
        back1_hole9 = (LinearLayout) dialog.findViewById(R.id.back_layout_r9);
        back1_hole10 = (LinearLayout) dialog.findViewById(R.id.back_layout_r10);
        back1_hole11 = (LinearLayout) dialog.findViewById(R.id.back_layout_r11);
        back1_hole12 = (LinearLayout) dialog.findViewById(R.id.back_layout_r12);
        back1_hole13 = (LinearLayout) dialog.findViewById(R.id.back_layout_r13);
        back1_hole14 = (LinearLayout) dialog.findViewById(R.id.back_layout_r14);
        back1_hole15 = (LinearLayout) dialog.findViewById(R.id.back_layout_r15);
        back1_hole16 = (LinearLayout) dialog.findViewById(R.id.back_layout_r16);
        back1_hole17 = (LinearLayout) dialog.findViewById(R.id.back_layout_r17);
        back1_hole18 = (LinearLayout) dialog.findViewById(R.id.back_layout_r18);

        and1 = (TextView) dialog.findViewById(R.id.andText_r1);
        and2 = (TextView) dialog.findViewById(R.id.andText_r2);
        and3 = (TextView) dialog.findViewById(R.id.andText_r3);
        and4 = (TextView) dialog.findViewById(R.id.andText_r4);
        and5 = (TextView) dialog.findViewById(R.id.andText_r5);
        and6 = (TextView) dialog.findViewById(R.id.andText_r6);
        and7 = (TextView) dialog.findViewById(R.id.andText_r7);
        and8 = (TextView) dialog.findViewById(R.id.andText_r8);
        and9 = (TextView) dialog.findViewById(R.id.andText_r9);
        and10 = (TextView) dialog.findViewById(R.id.andText_r10);
        and11 = (TextView) dialog.findViewById(R.id.andText_r11);
        and12 = (TextView) dialog.findViewById(R.id.andText_r12);
        and13 = (TextView) dialog.findViewById(R.id.andText_r13);
        and14 = (TextView) dialog.findViewById(R.id.andText_r14);
        and15 = (TextView) dialog.findViewById(R.id.andText_r15);
        and16 = (TextView) dialog.findViewById(R.id.andText_r16);
        and17 = (TextView) dialog.findViewById(R.id.andText_r17);
        and18 = (TextView) dialog.findViewById(R.id.andText_r18);


        hole_a1 = (TextView) dialog.findViewById(R.id.hole_auto_r1);
        hole_a2 = (TextView) dialog.findViewById(R.id.hole_auto_r2);
        hole_a3 = (TextView) dialog.findViewById(R.id.hole_auto_r3);
        hole_a4 = (TextView) dialog.findViewById(R.id.hole_auto_r4);
        hole_a5 = (TextView) dialog.findViewById(R.id.hole_auto_r5);
        hole_a6 = (TextView) dialog.findViewById(R.id.hole_auto_r6);
        hole_a7 = (TextView) dialog.findViewById(R.id.hole_auto_r7);
        hole_a8 = (TextView) dialog.findViewById(R.id.hole_auto_r8);
        hole_a9 = (TextView) dialog.findViewById(R.id.hole_auto_r9);
        hole_a10 = (TextView) dialog.findViewById(R.id.hole_auto_r10);
        hole_a11 = (TextView) dialog.findViewById(R.id.hole_auto_r11);
        hole_a12 = (TextView) dialog.findViewById(R.id.hole_auto_r12);
        hole_a13 = (TextView) dialog.findViewById(R.id.hole_auto_r13);
        hole_a14 = (TextView) dialog.findViewById(R.id.hole_auto_r14);
        hole_a15 = (TextView) dialog.findViewById(R.id.hole_auto_r15);
        hole_a16 = (TextView) dialog.findViewById(R.id.hole_auto_r16);
        hole_a17 = (TextView) dialog.findViewById(R.id.hole_auto_r17);
        hole_a18 = (TextView) dialog.findViewById(R.id.hole_auto_r18);

        r1 = (LinearLayout) dialog.findViewById(R.id.r1);
        r2 = (LinearLayout) dialog.findViewById(R.id.r2);
        r3 = (LinearLayout) dialog.findViewById(R.id.r3);
        r4 = (LinearLayout) dialog.findViewById(R.id.r4);
        r5 = (LinearLayout) dialog.findViewById(R.id.r5);
        r6 = (LinearLayout) dialog.findViewById(R.id.r6);
        r7 = (LinearLayout) dialog.findViewById(R.id.r7);
        r8 = (LinearLayout) dialog.findViewById(R.id.r8);
        r9 = (LinearLayout) dialog.findViewById(R.id.r9);
        r10 = (LinearLayout) dialog.findViewById(R.id.r10);
        r11 = (LinearLayout) dialog.findViewById(R.id.r11);
        r12 = (LinearLayout) dialog.findViewById(R.id.r12);
        r13 = (LinearLayout) dialog.findViewById(R.id.r13);
        r14 = (LinearLayout) dialog.findViewById(R.id.r14);
        r15 = (LinearLayout) dialog.findViewById(R.id.r15);
        r16 = (LinearLayout) dialog.findViewById(R.id.r16);
        r17 = (LinearLayout) dialog.findViewById(R.id.r17);
        r18 = (LinearLayout) dialog.findViewById(R.id.r18);


        f1_r1 = (TextView) dialog.findViewById(R.id.f1_r1);
        f1_r2 = (TextView) dialog.findViewById(R.id.f1_r2);
        f1_r3 = (TextView) dialog.findViewById(R.id.f1_r3);
        f1_r4 = (TextView) dialog.findViewById(R.id.f1_r4);
        f1_r5 = (TextView) dialog.findViewById(R.id.f1_r5);
        f1_r6 = (TextView) dialog.findViewById(R.id.f1_r6);
        f1_r7 = (TextView) dialog.findViewById(R.id.f1_r7);
        f1_r8 = (TextView) dialog.findViewById(R.id.f1_r8);
        f1_r9 = (TextView) dialog.findViewById(R.id.f1_r9);
        f1_r10 = (TextView) dialog.findViewById(R.id.f1_r10);
        b1_r1 = (TextView) dialog.findViewById(R.id.b1_r1);
        b1_r2 = (TextView) dialog.findViewById(R.id.b1_r2);
        b1_r3 = (TextView) dialog.findViewById(R.id.b1_r3);
        b1_r4 = (TextView) dialog.findViewById(R.id.b1_r4);
        b1_r5 = (TextView) dialog.findViewById(R.id.b1_r5);
        b1_r6 = (TextView) dialog.findViewById(R.id.b1_r6);
        b1_r7 = (TextView) dialog.findViewById(R.id.b1_r7);
        b1_r8 = (TextView) dialog.findViewById(R.id.b1_r8);
        b1_r9 = (TextView) dialog.findViewById(R.id.b1_r9);
        b1_r10 = (TextView) dialog.findViewById(R.id.b1_r10);

        f2_r1 = (TextView) dialog.findViewById(R.id.f2_r1);
        f2_r2 = (TextView) dialog.findViewById(R.id.f2_r2);
        f2_r3 = (TextView) dialog.findViewById(R.id.f2_r3);
        f2_r4 = (TextView) dialog.findViewById(R.id.f2_r4);
        f2_r5 = (TextView) dialog.findViewById(R.id.f2_r5);
        f2_r6 = (TextView) dialog.findViewById(R.id.f2_r6);
        f2_r7 = (TextView) dialog.findViewById(R.id.f2_r7);
        f2_r8 = (TextView) dialog.findViewById(R.id.f2_r8);
        f2_r9 = (TextView) dialog.findViewById(R.id.f2_r9);
        f2_r10 = (TextView) dialog.findViewById(R.id.f2_r10);
        b2_r1 = (TextView) dialog.findViewById(R.id.b2_r1);
        b2_r2 = (TextView) dialog.findViewById(R.id.b2_r2);
        b2_r3 = (TextView) dialog.findViewById(R.id.b2_r3);
        b2_r4 = (TextView) dialog.findViewById(R.id.b2_r4);
        b2_r5 = (TextView) dialog.findViewById(R.id.b2_r5);
        b2_r6 = (TextView) dialog.findViewById(R.id.b2_r6);
        b2_r7 = (TextView) dialog.findViewById(R.id.b2_r7);
        b2_r8 = (TextView) dialog.findViewById(R.id.b2_r8);
        b2_r9 = (TextView) dialog.findViewById(R.id.b2_r9);
        b2_r10 = (TextView) dialog.findViewById(R.id.b2_r10);

        f3_r1 = (TextView) dialog.findViewById(R.id.f3_r1);
        f3_r2 = (TextView) dialog.findViewById(R.id.f3_r2);
        f3_r3 = (TextView) dialog.findViewById(R.id.f3_r3);
        f3_r4 = (TextView) dialog.findViewById(R.id.f3_r4);
        f3_r5 = (TextView) dialog.findViewById(R.id.f3_r5);
        f3_r6 = (TextView) dialog.findViewById(R.id.f3_r6);
        f3_r7 = (TextView) dialog.findViewById(R.id.f3_r7);
        f3_r8 = (TextView) dialog.findViewById(R.id.f3_r8);
        f3_r9 = (TextView) dialog.findViewById(R.id.f3_r9);
        f3_r10 = (TextView) dialog.findViewById(R.id.f3_r10);
        b3_r1 = (TextView) dialog.findViewById(R.id.b3_r1);
        b3_r2 = (TextView) dialog.findViewById(R.id.b3_r2);
        b3_r3 = (TextView) dialog.findViewById(R.id.b3_r3);
        b3_r4 = (TextView) dialog.findViewById(R.id.b3_r4);
        b3_r5 = (TextView) dialog.findViewById(R.id.b3_r5);
        b3_r6 = (TextView) dialog.findViewById(R.id.b3_r6);
        b3_r7 = (TextView) dialog.findViewById(R.id.b3_r7);
        b3_r8 = (TextView) dialog.findViewById(R.id.b3_r8);
        b3_r9 = (TextView) dialog.findViewById(R.id.b3_r9);
        b3_r10 = (TextView) dialog.findViewById(R.id.b3_r10);

        f4_r1 = (TextView) dialog.findViewById(R.id.f4_r1);
        f4_r2 = (TextView) dialog.findViewById(R.id.f4_r2);
        f4_r3 = (TextView) dialog.findViewById(R.id.f4_r3);
        f4_r4 = (TextView) dialog.findViewById(R.id.f4_r4);
        f4_r5 = (TextView) dialog.findViewById(R.id.f4_r5);
        f4_r6 = (TextView) dialog.findViewById(R.id.f4_r6);
        f4_r7 = (TextView) dialog.findViewById(R.id.f4_r7);
        f4_r8 = (TextView) dialog.findViewById(R.id.f4_r8);
        f4_r9 = (TextView) dialog.findViewById(R.id.f4_r9);
        f4_r10 = (TextView) dialog.findViewById(R.id.f4_r10);
        b4_r1 = (TextView) dialog.findViewById(R.id.b4_r1);
        b4_r2 = (TextView) dialog.findViewById(R.id.b4_r2);
        b4_r3 = (TextView) dialog.findViewById(R.id.b4_r3);
        b4_r4 = (TextView) dialog.findViewById(R.id.b4_r4);
        b4_r5 = (TextView) dialog.findViewById(R.id.b4_r5);
        b4_r6 = (TextView) dialog.findViewById(R.id.b4_r6);
        b4_r7 = (TextView) dialog.findViewById(R.id.b4_r7);
        b4_r8 = (TextView) dialog.findViewById(R.id.b4_r8);
        b4_r9 = (TextView) dialog.findViewById(R.id.b4_r9);
        b4_r10 = (TextView) dialog.findViewById(R.id.b4_r10);

        f5_r1 = (TextView) dialog.findViewById(R.id.f5_r1);
        f5_r2 = (TextView) dialog.findViewById(R.id.f5_r2);
        f5_r3 = (TextView) dialog.findViewById(R.id.f5_r3);
        f5_r4 = (TextView) dialog.findViewById(R.id.f5_r4);
        f5_r5 = (TextView) dialog.findViewById(R.id.f5_r5);
        f5_r6 = (TextView) dialog.findViewById(R.id.f5_r6);
        f5_r7 = (TextView) dialog.findViewById(R.id.f5_r7);
        f5_r8 = (TextView) dialog.findViewById(R.id.f5_r8);
        f5_r9 = (TextView) dialog.findViewById(R.id.f5_r9);
        f5_r10 = (TextView) dialog.findViewById(R.id.f5_r10);
        b5_r1 = (TextView) dialog.findViewById(R.id.b5_r1);
        b5_r2 = (TextView) dialog.findViewById(R.id.b5_r2);
        b5_r3 = (TextView) dialog.findViewById(R.id.b5_r3);
        b5_r4 = (TextView) dialog.findViewById(R.id.b5_r4);
        b5_r5 = (TextView) dialog.findViewById(R.id.b5_r5);
        b5_r6 = (TextView) dialog.findViewById(R.id.b5_r6);
        b5_r7 = (TextView) dialog.findViewById(R.id.b5_r7);
        b5_r8 = (TextView) dialog.findViewById(R.id.b5_r8);
        b5_r9 = (TextView) dialog.findViewById(R.id.b5_r9);
        b5_r10 = (TextView) dialog.findViewById(R.id.b5_r10);

        f6_r1 = (TextView) dialog.findViewById(R.id.f6_r1);
        f6_r2 = (TextView) dialog.findViewById(R.id.f6_r2);
        f6_r3 = (TextView) dialog.findViewById(R.id.f6_r3);
        f6_r4 = (TextView) dialog.findViewById(R.id.f6_r4);
        f6_r5 = (TextView) dialog.findViewById(R.id.f6_r5);
        f6_r6 = (TextView) dialog.findViewById(R.id.f6_r6);
        f6_r7 = (TextView) dialog.findViewById(R.id.f6_r7);
        f6_r8 = (TextView) dialog.findViewById(R.id.f6_r8);
        f6_r9 = (TextView) dialog.findViewById(R.id.f6_r9);
        f6_r10 = (TextView) dialog.findViewById(R.id.f6_r10);
        b6_r1 = (TextView) dialog.findViewById(R.id.b6_r1);
        b6_r2 = (TextView) dialog.findViewById(R.id.b6_r2);
        b6_r3 = (TextView) dialog.findViewById(R.id.b6_r3);
        b6_r4 = (TextView) dialog.findViewById(R.id.b6_r4);
        b6_r5 = (TextView) dialog.findViewById(R.id.b6_r5);
        b6_r6 = (TextView) dialog.findViewById(R.id.b6_r6);
        b6_r7 = (TextView) dialog.findViewById(R.id.b6_r7);
        b6_r8 = (TextView) dialog.findViewById(R.id.b6_r8);
        b6_r9 = (TextView) dialog.findViewById(R.id.b6_r9);
        b6_r10 = (TextView) dialog.findViewById(R.id.b6_r10);

        f7_r1 = (TextView) dialog.findViewById(R.id.f7_r1);
        f7_r2 = (TextView) dialog.findViewById(R.id.f7_r2);
        f7_r3 = (TextView) dialog.findViewById(R.id.f7_r3);
        f7_r4 = (TextView) dialog.findViewById(R.id.f7_r4);
        f7_r5 = (TextView) dialog.findViewById(R.id.f7_r5);
        f7_r6 = (TextView) dialog.findViewById(R.id.f7_r6);
        f7_r7 = (TextView) dialog.findViewById(R.id.f7_r7);
        f7_r8 = (TextView) dialog.findViewById(R.id.f7_r8);
        f7_r9 = (TextView) dialog.findViewById(R.id.f7_r9);
        f7_r10 = (TextView) dialog.findViewById(R.id.f7_r10);
        b7_r1 = (TextView) dialog.findViewById(R.id.b7_r1);
        b7_r2 = (TextView) dialog.findViewById(R.id.b7_r2);
        b7_r3 = (TextView) dialog.findViewById(R.id.b7_r3);
        b7_r4 = (TextView) dialog.findViewById(R.id.b7_r4);
        b7_r5 = (TextView) dialog.findViewById(R.id.b7_r5);
        b7_r6 = (TextView) dialog.findViewById(R.id.b7_r6);
        b7_r7 = (TextView) dialog.findViewById(R.id.b7_r7);
        b7_r8 = (TextView) dialog.findViewById(R.id.b7_r8);
        b7_r9 = (TextView) dialog.findViewById(R.id.b7_r9);
        b7_r10 = (TextView) dialog.findViewById(R.id.b7_r10);

        f8_r1 = (TextView) dialog.findViewById(R.id.f8_r1);
        f8_r2 = (TextView) dialog.findViewById(R.id.f8_r2);
        f8_r3 = (TextView) dialog.findViewById(R.id.f8_r3);
        f8_r4 = (TextView) dialog.findViewById(R.id.f8_r4);
        f8_r5 = (TextView) dialog.findViewById(R.id.f8_r5);
        f8_r6 = (TextView) dialog.findViewById(R.id.f8_r6);
        f8_r7 = (TextView) dialog.findViewById(R.id.f8_r7);
        f8_r8 = (TextView) dialog.findViewById(R.id.f8_r8);
        f8_r9 = (TextView) dialog.findViewById(R.id.f8_r9);
        f8_r10 = (TextView) dialog.findViewById(R.id.f8_r10);
        b8_r1 = (TextView) dialog.findViewById(R.id.b8_r1);
        b8_r2 = (TextView) dialog.findViewById(R.id.b8_r2);
        b8_r3 = (TextView) dialog.findViewById(R.id.b8_r3);
        b8_r4 = (TextView) dialog.findViewById(R.id.b8_r4);
        b8_r5 = (TextView) dialog.findViewById(R.id.b8_r5);
        b8_r6 = (TextView) dialog.findViewById(R.id.b8_r6);
        b8_r7 = (TextView) dialog.findViewById(R.id.b8_r7);
        b8_r8 = (TextView) dialog.findViewById(R.id.b8_r8);
        b8_r9 = (TextView) dialog.findViewById(R.id.b8_r9);
        b8_r10 = (TextView) dialog.findViewById(R.id.b8_r10);

        f9_r1 = (TextView) dialog.findViewById(R.id.f9_r1);
        f9_r2 = (TextView) dialog.findViewById(R.id.f9_r2);
        f9_r3 = (TextView) dialog.findViewById(R.id.f9_r3);
        f9_r4 = (TextView) dialog.findViewById(R.id.f9_r4);
        f9_r5 = (TextView) dialog.findViewById(R.id.f9_r5);
        f9_r6 = (TextView) dialog.findViewById(R.id.f9_r6);
        f9_r7 = (TextView) dialog.findViewById(R.id.f9_r7);
        f9_r8 = (TextView) dialog.findViewById(R.id.f9_r8);
        f9_r9 = (TextView) dialog.findViewById(R.id.f9_r9);
        f9_r10 = (TextView) dialog.findViewById(R.id.f9_r10);
        b9_r1 = (TextView) dialog.findViewById(R.id.b9_r1);
        b9_r2 = (TextView) dialog.findViewById(R.id.b9_r2);
        b9_r3 = (TextView) dialog.findViewById(R.id.b9_r3);
        b9_r4 = (TextView) dialog.findViewById(R.id.b9_r4);
        b9_r5 = (TextView) dialog.findViewById(R.id.b9_r5);
        b9_r6 = (TextView) dialog.findViewById(R.id.b9_r6);
        b9_r7 = (TextView) dialog.findViewById(R.id.b9_r7);
        b9_r8 = (TextView) dialog.findViewById(R.id.b9_r8);
        b9_r9 = (TextView) dialog.findViewById(R.id.b9_r9);
        b9_r10 = (TextView) dialog.findViewById(R.id.b9_r10);

        f10_r1 = (TextView) dialog.findViewById(R.id.f10_r1);
        f10_r2 = (TextView) dialog.findViewById(R.id.f10_r2);
        f10_r3 = (TextView) dialog.findViewById(R.id.f10_r3);
        f10_r4 = (TextView) dialog.findViewById(R.id.f10_r4);
        f10_r5 = (TextView) dialog.findViewById(R.id.f10_r5);
        f10_r6 = (TextView) dialog.findViewById(R.id.f10_r6);
        f10_r7 = (TextView) dialog.findViewById(R.id.f10_r7);
        f10_r8 = (TextView) dialog.findViewById(R.id.f10_r8);
        f10_r9 = (TextView) dialog.findViewById(R.id.f10_r9);
        f10_r10 = (TextView) dialog.findViewById(R.id.f10_r10);
        b10_r1 = (TextView) dialog.findViewById(R.id.b10_r1);
        b10_r2 = (TextView) dialog.findViewById(R.id.b10_r2);
        b10_r3 = (TextView) dialog.findViewById(R.id.b10_r3);
        b10_r4 = (TextView) dialog.findViewById(R.id.b10_r4);
        b10_r5 = (TextView) dialog.findViewById(R.id.b10_r5);
        b10_r6 = (TextView) dialog.findViewById(R.id.b10_r6);
        b10_r7 = (TextView) dialog.findViewById(R.id.b10_r7);
        b10_r8 = (TextView) dialog.findViewById(R.id.b10_r8);
        b10_r9 = (TextView) dialog.findViewById(R.id.b10_r9);
        b10_r10 = (TextView) dialog.findViewById(R.id.b10_r10);

        f1_r11 = (TextView) dialog.findViewById(R.id.f1_r11);
        f1_r12 = (TextView) dialog.findViewById(R.id.f1_r12);
        f1_r13 = (TextView) dialog.findViewById(R.id.f1_r13);
        f1_r14 = (TextView) dialog.findViewById(R.id.f1_r14);
        f1_r15 = (TextView) dialog.findViewById(R.id.f1_r15);
        f1_r16 = (TextView) dialog.findViewById(R.id.f1_r16);
        f1_r17 = (TextView) dialog.findViewById(R.id.f1_r17);
        f1_r18 = (TextView) dialog.findViewById(R.id.f1_r18);


        b1_r11 = (TextView) dialog.findViewById(R.id.b1_r11);
        b1_r12 = (TextView) dialog.findViewById(R.id.b1_r12);
        b1_r13 = (TextView) dialog.findViewById(R.id.b1_r13);
        b1_r14 = (TextView) dialog.findViewById(R.id.b1_r14);
        b1_r15 = (TextView) dialog.findViewById(R.id.b1_r15);
        b1_r16 = (TextView) dialog.findViewById(R.id.b1_r16);
        b1_r17 = (TextView) dialog.findViewById(R.id.b1_r17);
        b1_r18 = (TextView) dialog.findViewById(R.id.b1_r18);


        f2_r11 = (TextView) dialog.findViewById(R.id.f2_r11);
        f2_r12 = (TextView) dialog.findViewById(R.id.f2_r12);
        f2_r13 = (TextView) dialog.findViewById(R.id.f2_r13);
        f2_r14 = (TextView) dialog.findViewById(R.id.f2_r14);
        f2_r15 = (TextView) dialog.findViewById(R.id.f2_r15);
        f2_r16 = (TextView) dialog.findViewById(R.id.f2_r16);
        f2_r17 = (TextView) dialog.findViewById(R.id.f2_r17);
        f2_r18 = (TextView) dialog.findViewById(R.id.f2_r18);

        b2_r11 = (TextView) dialog.findViewById(R.id.b2_r11);
        b2_r12 = (TextView) dialog.findViewById(R.id.b2_r12);
        b2_r13 = (TextView) dialog.findViewById(R.id.b2_r13);
        b2_r14 = (TextView) dialog.findViewById(R.id.b2_r14);
        b2_r15 = (TextView) dialog.findViewById(R.id.b2_r15);
        b2_r16 = (TextView) dialog.findViewById(R.id.b2_r16);
        b2_r17 = (TextView) dialog.findViewById(R.id.b2_r17);
        b2_r18 = (TextView) dialog.findViewById(R.id.b2_r18);


        f3_r11 = (TextView) dialog.findViewById(R.id.f3_r11);
        f3_r12 = (TextView) dialog.findViewById(R.id.f3_r12);
        f3_r13 = (TextView) dialog.findViewById(R.id.f3_r13);
        f3_r14 = (TextView) dialog.findViewById(R.id.f3_r14);
        f3_r15 = (TextView) dialog.findViewById(R.id.f3_r15);
        f3_r16 = (TextView) dialog.findViewById(R.id.f3_r16);
        f3_r17 = (TextView) dialog.findViewById(R.id.f3_r17);
        f3_r18 = (TextView) dialog.findViewById(R.id.f3_r18);

        b3_r11 = (TextView) dialog.findViewById(R.id.b3_r11);
        b3_r12 = (TextView) dialog.findViewById(R.id.b3_r12);
        b3_r13 = (TextView) dialog.findViewById(R.id.b3_r13);
        b3_r14 = (TextView) dialog.findViewById(R.id.b3_r14);
        b3_r15 = (TextView) dialog.findViewById(R.id.b3_r15);
        b3_r16 = (TextView) dialog.findViewById(R.id.b3_r16);
        b3_r17 = (TextView) dialog.findViewById(R.id.b3_r17);
        b3_r18 = (TextView) dialog.findViewById(R.id.b3_r18);


        f4_r11 = (TextView) dialog.findViewById(R.id.f4_r11);
        f4_r12 = (TextView) dialog.findViewById(R.id.f4_r12);
        f4_r13 = (TextView) dialog.findViewById(R.id.f4_r13);
        f4_r14 = (TextView) dialog.findViewById(R.id.f4_r14);
        f4_r15 = (TextView) dialog.findViewById(R.id.f4_r15);
        f4_r16 = (TextView) dialog.findViewById(R.id.f4_r16);
        f4_r17 = (TextView) dialog.findViewById(R.id.f4_r17);
        f4_r18 = (TextView) dialog.findViewById(R.id.f4_r18);

        b4_r11 = (TextView) dialog.findViewById(R.id.b4_r11);
        b4_r12 = (TextView) dialog.findViewById(R.id.b4_r12);
        b4_r13 = (TextView) dialog.findViewById(R.id.b4_r13);
        b4_r14 = (TextView) dialog.findViewById(R.id.b4_r14);
        b4_r15 = (TextView) dialog.findViewById(R.id.b4_r15);
        b4_r16 = (TextView) dialog.findViewById(R.id.b4_r16);
        b4_r17 = (TextView) dialog.findViewById(R.id.b4_r17);
        b4_r18 = (TextView) dialog.findViewById(R.id.b4_r18);


        f5_r11 = (TextView) dialog.findViewById(R.id.f5_r11);
        f5_r12 = (TextView) dialog.findViewById(R.id.f5_r12);
        f5_r13 = (TextView) dialog.findViewById(R.id.f5_r13);
        f5_r14 = (TextView) dialog.findViewById(R.id.f5_r14);
        f5_r15 = (TextView) dialog.findViewById(R.id.f5_r15);
        f5_r16 = (TextView) dialog.findViewById(R.id.f5_r16);
        f5_r17 = (TextView) dialog.findViewById(R.id.f5_r17);
        f5_r18 = (TextView) dialog.findViewById(R.id.f5_r18);

        b5_r11 = (TextView) dialog.findViewById(R.id.b5_r11);
        b5_r12 = (TextView) dialog.findViewById(R.id.b5_r12);
        b5_r13 = (TextView) dialog.findViewById(R.id.b5_r13);
        b5_r14 = (TextView) dialog.findViewById(R.id.b5_r14);
        b5_r15 = (TextView) dialog.findViewById(R.id.b5_r15);
        b5_r16 = (TextView) dialog.findViewById(R.id.b5_r16);
        b5_r17 = (TextView) dialog.findViewById(R.id.b5_r17);
        b5_r18 = (TextView) dialog.findViewById(R.id.b5_r18);

        f6_r11 = (TextView) dialog.findViewById(R.id.f6_r11);
        f6_r12 = (TextView) dialog.findViewById(R.id.f6_r12);
        f6_r13 = (TextView) dialog.findViewById(R.id.f6_r13);
        f6_r14 = (TextView) dialog.findViewById(R.id.f6_r14);
        f6_r15 = (TextView) dialog.findViewById(R.id.f6_r15);
        f6_r16 = (TextView) dialog.findViewById(R.id.f6_r16);
        f6_r17 = (TextView) dialog.findViewById(R.id.f6_r17);
        f6_r18 = (TextView) dialog.findViewById(R.id.f6_r18);

        b6_r11 = (TextView) dialog.findViewById(R.id.b6_r11);
        b6_r12 = (TextView) dialog.findViewById(R.id.b6_r12);
        b6_r13 = (TextView) dialog.findViewById(R.id.b6_r13);
        b6_r14 = (TextView) dialog.findViewById(R.id.b6_r14);
        b6_r15 = (TextView) dialog.findViewById(R.id.b6_r15);
        b6_r16 = (TextView) dialog.findViewById(R.id.b6_r16);
        b6_r17 = (TextView) dialog.findViewById(R.id.b6_r17);
        b6_r18 = (TextView) dialog.findViewById(R.id.b6_r18);

        f7_r11 = (TextView) dialog.findViewById(R.id.f7_r11);
        f7_r12 = (TextView) dialog.findViewById(R.id.f7_r12);
        f7_r13 = (TextView) dialog.findViewById(R.id.f7_r13);
        f7_r14 = (TextView) dialog.findViewById(R.id.f7_r14);
        f7_r15 = (TextView) dialog.findViewById(R.id.f7_r15);
        f7_r16 = (TextView) dialog.findViewById(R.id.f7_r16);
        f7_r17 = (TextView) dialog.findViewById(R.id.f7_r17);
        f7_r18 = (TextView) dialog.findViewById(R.id.f7_r18);

        b7_r11 = (TextView) dialog.findViewById(R.id.b7_r11);
        b7_r12 = (TextView) dialog.findViewById(R.id.b7_r12);
        b7_r13 = (TextView) dialog.findViewById(R.id.b7_r13);
        b7_r14 = (TextView) dialog.findViewById(R.id.b7_r14);
        b7_r15 = (TextView) dialog.findViewById(R.id.b7_r15);
        b7_r16 = (TextView) dialog.findViewById(R.id.b7_r16);
        b7_r17 = (TextView) dialog.findViewById(R.id.b7_r17);
        b7_r18 = (TextView) dialog.findViewById(R.id.b7_r18);

        f8_r11 = (TextView) dialog.findViewById(R.id.f8_r11);
        f8_r12 = (TextView) dialog.findViewById(R.id.f8_r12);
        f8_r13 = (TextView) dialog.findViewById(R.id.f8_r13);
        f8_r14 = (TextView) dialog.findViewById(R.id.f8_r14);
        f8_r15 = (TextView) dialog.findViewById(R.id.f8_r15);
        f8_r16 = (TextView) dialog.findViewById(R.id.f8_r16);
        f8_r17 = (TextView) dialog.findViewById(R.id.f8_r17);
        f8_r18 = (TextView) dialog.findViewById(R.id.f8_r18);

        b8_r11 = (TextView) dialog.findViewById(R.id.b8_r11);
        b8_r12 = (TextView) dialog.findViewById(R.id.b8_r12);
        b8_r13 = (TextView) dialog.findViewById(R.id.b8_r13);
        b8_r14 = (TextView) dialog.findViewById(R.id.b8_r14);
        b8_r15 = (TextView) dialog.findViewById(R.id.b8_r15);
        b8_r16 = (TextView) dialog.findViewById(R.id.b8_r16);
        b8_r17 = (TextView) dialog.findViewById(R.id.b8_r17);
        b8_r18 = (TextView) dialog.findViewById(R.id.b8_r18);

        f9_r11 = (TextView) dialog.findViewById(R.id.f9_r11);
        f9_r12 = (TextView) dialog.findViewById(R.id.f9_r12);
        f9_r13 = (TextView) dialog.findViewById(R.id.f9_r13);
        f9_r14 = (TextView) dialog.findViewById(R.id.f9_r14);
        f9_r15 = (TextView) dialog.findViewById(R.id.f9_r15);
        f9_r16 = (TextView) dialog.findViewById(R.id.f9_r16);
        f9_r17 = (TextView) dialog.findViewById(R.id.f9_r17);
        f9_r18 = (TextView) dialog.findViewById(R.id.f9_r18);

        b9_r11 = (TextView) dialog.findViewById(R.id.b9_r11);
        b9_r12 = (TextView) dialog.findViewById(R.id.b9_r12);
        b9_r13 = (TextView) dialog.findViewById(R.id.b9_r13);
        b9_r14 = (TextView) dialog.findViewById(R.id.b9_r14);
        b9_r15 = (TextView) dialog.findViewById(R.id.b9_r15);
        b9_r16 = (TextView) dialog.findViewById(R.id.b9_r16);
        b9_r17 = (TextView) dialog.findViewById(R.id.b9_r17);
        b9_r18 = (TextView) dialog.findViewById(R.id.b9_r18);


        f10_r11 = (TextView) dialog.findViewById(R.id.f10_r11);
        f10_r12 = (TextView) dialog.findViewById(R.id.f10_r12);
        f10_r13 = (TextView) dialog.findViewById(R.id.f10_r13);
        f10_r14 = (TextView) dialog.findViewById(R.id.f10_r14);
        f10_r15 = (TextView) dialog.findViewById(R.id.f10_r15);
        f10_r16 = (TextView) dialog.findViewById(R.id.f10_r16);
        f10_r17 = (TextView) dialog.findViewById(R.id.f10_r17);
        f10_r18 = (TextView) dialog.findViewById(R.id.f10_r18);

        b10_r11 = (TextView) dialog.findViewById(R.id.b10_r11);
        b10_r12 = (TextView) dialog.findViewById(R.id.b10_r12);
        b10_r13 = (TextView) dialog.findViewById(R.id.b10_r13);
        b10_r14 = (TextView) dialog.findViewById(R.id.b10_r14);
        b10_r15 = (TextView) dialog.findViewById(R.id.b10_r15);
        b10_r16 = (TextView) dialog.findViewById(R.id.b10_r16);
        b10_r17 = (TextView) dialog.findViewById(R.id.b10_r17);
        b10_r18 = (TextView) dialog.findViewById(R.id.b10_r18);

        eventID = ((AddScoreNew) this).geteventID();

        getExpandAuto(eventID, dialog);

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
                for (int i = 0; i < data.length(); i++) {

                    if (i == 0) {

                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a1.setText(holeNumber);
                        r1.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r1.setText(" " + score);
                                f1_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r1.setText(" " + score);
                                f2_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r1.setText(" " + score);
                                f3_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r1.setText(" " + score);
                                f4_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r1.setText(" " + score);
                                f5_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r1.setText(" " + score);
                                f6_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r1.setText(" " + score);
                                f7_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r1.setText(" " + score);
                                f8_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r1.setText(" " + score);
                                f9_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r1.setText(" " + score);
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
                                b1_r1.setText(" " + score);
                                b1_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r1.setText(" " + score);
                                b2_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r1.setText(" " + score);
                                b3_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r1.setText(" " + score);
                                b4_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r1.setText(" " + score);
                                b5_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r1.setText(" " + score);
                                b6_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r1.setText(" " + score);
                                b7_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r1.setText(" " + score);
                                b8_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r1.setText(" " + score);
                                b9_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r1.setText(" " + score);
                                b10_r1.setTextColor(Color.parseColor(color));
                            } else {

                            }


                        }
                    } else if (i == 1) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a2.setText(holeNumber);
                        r2.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r2.setText(" " + score);
                                f1_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r2.setText(" " + score);
                                f2_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r2.setText(" " + score);
                                f3_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r2.setText(" " + score);
                                f4_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r2.setText(" " + score);
                                f5_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r2.setText(" " + score);
                                f6_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r2.setText(" " + score);
                                f7_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r2.setText(" " + score);
                                f8_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r2.setText(" " + score);
                                f9_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r2.setText(" " + score);
                                f10_r2.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and2.setVisibility(View.VISIBLE);
                            back1_hole2.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r2.setText(" " + score);
                                b1_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r2.setText(" " + score);
                                b2_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r2.setText(" " + score);
                                b3_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r2.setText(" " + score);
                                b4_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r2.setText(" " + score);
                                b5_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r2.setText(" " + score);
                                b6_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r2.setText(" " + score);
                                b7_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r2.setText(" " + score);
                                b8_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r2.setText(" " + score);
                                b9_r2.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r2.setText(" " + score);
                                b10_r2.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 2) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a3.setText(holeNumber);
                        r3.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r3.setText(" " + score);
                                f1_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r3.setText(" " + score);
                                f2_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r3.setText(" " + score);
                                f3_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r3.setText(" " + score);
                                f4_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r3.setText(" " + score);
                                f5_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r3.setText(" " + score);
                                f6_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r3.setText(" " + score);
                                f7_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r3.setText(" " + score);
                                f8_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r3.setText(" " + score);
                                f9_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r3.setText(" " + score);
                                f10_r3.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and3.setVisibility(View.VISIBLE);
                            back1_hole3.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r3.setText(" " + score);
                                b1_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r3.setText(" " + score);
                                b2_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r3.setText(" " + score);
                                b3_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r3.setText(" " + score);
                                b4_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r3.setText(" " + score);
                                b5_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r3.setText(" " + score);
                                b6_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r3.setText(" " + score);
                                b7_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r3.setText(" " + score);
                                b8_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r3.setText(" " + score);
                                b9_r3.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r3.setText(" " + score);
                                b10_r3.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 3) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a4.setText(holeNumber);
                        r4.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r4.setText(" " + score);
                                f1_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r4.setText(" " + score);
                                f2_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r4.setText(" " + score);
                                f3_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r4.setText(" " + score);
                                f4_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r4.setText(" " + score);
                                f5_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r4.setText(" " + score);
                                f6_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r4.setText(" " + score);
                                f7_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r4.setText(score);
                                f8_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r4.setText(" " + score);
                                f9_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r4.setText(" " + score);
                                f10_r4.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and4.setVisibility(View.VISIBLE);
                            back1_hole4.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r4.setText(" " + score);
                                b1_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r4.setText(" " + score);
                                b2_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r4.setText(" " + score);
                                b3_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r4.setText(" " + score);
                                b4_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r4.setText(" " + score);
                                b5_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r4.setText(" " + score);
                                b6_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r4.setText(" " + score);
                                b7_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r4.setText(" " + score);
                                b8_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r4.setText(" " + score);
                                b9_r4.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r4.setText(" " + score);
                                b10_r4.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 4) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a5.setText(holeNumber);
                        r5.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r5.setText(" " + score);
                                f1_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r5.setText(" " + score);
                                f2_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r5.setText(" " + score);
                                f3_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r5.setText(" " + score);
                                f4_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r5.setText(" " + score);
                                f5_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r5.setText(" " + score);
                                f6_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r5.setText(" " + score);
                                f7_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r5.setText(" " + score);
                                f8_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r5.setText(" " + score);
                                f9_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r5.setText(" " + score);
                                f10_r5.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and5.setVisibility(View.VISIBLE);
                            back1_hole5.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r5.setText(" " + score);
                                b1_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r5.setText(" " + score);
                                b2_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r5.setText(" " + score);
                                b3_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r5.setText(" " + score);
                                b4_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r5.setText(" " + score);
                                b5_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r5.setText(" " + score);
                                b6_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r5.setText(" " + score);
                                b7_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r5.setText(" " + score);
                                b8_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r5.setText(" " + score);
                                b9_r5.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r5.setText(" " + score);
                                b10_r5.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 5) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a6.setText(holeNumber);
                        r6.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r6.setText(" " + score);
                                f1_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r6.setText(" " + score);
                                f2_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r6.setText(" " + score);
                                f3_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r6.setText(" " + score);
                                f4_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r6.setText(" " + score);
                                f5_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r6.setText(" " + score);
                                f6_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r6.setText(" " + score);
                                f7_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r6.setText(" " + score);
                                f8_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r6.setText(" " + score);
                                f9_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r6.setText(" " + score);
                                f10_r6.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and6.setVisibility(View.VISIBLE);
                            back1_hole6.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r6.setText(" " + score);
                                b1_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r6.setText(" " + score);
                                b2_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r6.setText(" " + score);
                                b3_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r6.setText(" " + score);
                                b4_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r6.setText(" " + score);
                                b5_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r6.setText(" " + score);
                                b6_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r6.setText(" " + score);
                                b7_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r6.setText(" " + score);
                                b8_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r6.setText(" " + score);
                                b9_r6.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r6.setText(" " + score);
                                b10_r6.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 6) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a7.setText(holeNumber);
                        r7.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r7.setText(" " + score);
                                f1_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r7.setText(" " + score);
                                f2_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r7.setText(" " + score);
                                f3_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r7.setText(" " + score);
                                f4_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r7.setText(" " + score);
                                f5_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r7.setText(" " + score);
                                f6_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r7.setText(" " + score);
                                f7_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r7.setText(" " + score);
                                f8_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r7.setText(" " + score);
                                f9_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r7.setText(" " + score);
                                f10_r7.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and7.setVisibility(View.VISIBLE);
                            back1_hole7.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r7.setText(" " + score);
                                b1_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r7.setText(" " + score);
                                b2_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r7.setText(" " + score);
                                b3_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r7.setText(" " + score);
                                b4_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r7.setText(" " + score);
                                b5_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r7.setText(" " + score);
                                b6_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r7.setText(" " + score);
                                b7_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r7.setText(" " + score);
                                b8_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r7.setText(" " + score);
                                b9_r7.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r7.setText(" " + score);
                                b10_r7.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 7) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a8.setText(holeNumber);
                        r8.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r8.setText(" " + score);
                                f1_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r8.setText(" " + score);
                                f2_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r8.setText(" " + score);
                                f3_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r8.setText(" " + score);
                                f4_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r8.setText(" " + score);
                                f5_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r8.setText(" " + score);
                                f6_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r8.setText(" " + score);
                                f7_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r8.setText(" " + score);
                                f8_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r8.setText(" " + score);
                                f9_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r8.setText(" " + score);
                                f10_r8.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and8.setVisibility(View.VISIBLE);
                            back1_hole8.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r8.setText(" " + score);
                                b1_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r8.setText(" " + score);
                                b2_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r8.setText(" " + score);
                                b3_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r8.setText(" " + score);
                                b4_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r8.setText(" " + score);
                                b5_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r8.setText(" " + score);
                                b6_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r8.setText(" " + score);
                                b7_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r8.setText(" " + score);
                                b8_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r8.setText(" " + score);
                                b9_r8.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r8.setText(" " + score);
                                b10_r8.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 8) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a9.setText(holeNumber);
                        r9.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r9.setText(" " + score);
                                f1_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r9.setText(" " + score);
                                f2_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r9.setText(" " + score);
                                f3_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r9.setText(" " + score);
                                f4_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r9.setText(" " + score);
                                f5_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r9.setText(" " + score);
                                f6_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r9.setText(" " + score);
                                f7_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r9.setText(" " + score);
                                f8_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r9.setText(" " + score);
                                f9_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r9.setText(" " + score);
                                f10_r9.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and9.setVisibility(View.VISIBLE);
                            back1_hole9.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r9.setText(" " + score);
                                b1_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r9.setText(" " + score);
                                b2_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r9.setText(" " + score);
                                b3_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r9.setText(" " + score);
                                b4_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r9.setText(" " + score);
                                b5_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r9.setText(" " + score);
                                b6_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r9.setText(" " + score);
                                b7_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r9.setText(" " + score);
                                b8_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r9.setText(" " + score);
                                b9_r9.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r9.setText(" " + score);
                                b10_r9.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 9) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a10.setText(holeNumber);
                        r10.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r10.setText(" " + score);
                                f1_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r10.setText(" " + score);
                                f2_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r10.setText(" " + score);
                                f3_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r10.setText(" " + score);
                                f4_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r10.setText(" " + score);
                                f5_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r10.setText(" " + score);
                                f6_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r10.setText(" " + score);
                                f7_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r10.setText(" " + score);
                                f8_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r10.setText(" " + score);
                                f9_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r10.setText(" " + score);
                                f10_r10.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {


                            and10.setVisibility(View.VISIBLE);
                            back1_hole10.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r10.setText(" " + score);
                                b1_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r10.setText(" " + score);
                                b2_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r10.setText(" " + score);
                                b3_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r10.setText(" " + score);
                                b4_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r10.setText(" " + score);
                                b5_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r10.setText(" " + score);
                                b6_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r10.setText(" " + score);
                                b7_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r10.setText(" " + score);
                                b8_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r10.setText(" " + score);
                                b9_r10.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r10.setText(" " + score);
                                b10_r10.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 10) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a11.setText(holeNumber);
                        r11.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r11.setText(" " + score);
                                f1_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r11.setText(" " + score);
                                f2_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r11.setText(" " + score);
                                f3_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r11.setText(" " + score);
                                f4_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r11.setText(" " + score);
                                f5_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r11.setText(" " + score);
                                f6_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r11.setText(" " + score);
                                f7_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r11.setText(" " + score);
                                f8_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r11.setText(" " + score);
                                f9_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r11.setText(" " + score);
                                f10_r11.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and11.setVisibility(View.VISIBLE);
                            back1_hole11.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r11.setText(" " + score);
                                b1_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r11.setText(" " + score);
                                b2_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r11.setText(" " + score);
                                b3_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r11.setText(" " + score);
                                b4_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r11.setText(" " + score);
                                b5_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r11.setText(" " + score);
                                b6_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r11.setText(" " + score);
                                b7_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r11.setText(" " + score);
                                b8_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r11.setText(" " + score);
                                b9_r11.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r11.setText(" " + score);
                                b10_r11.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 11) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a12.setText(holeNumber);
                        r12.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r12.setText(" " + score);
                                f1_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r12.setText(" " + score);
                                f2_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r12.setText(" " + score);
                                f3_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r12.setText(" " + score);
                                f4_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r12.setText(" " + score);
                                f5_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r12.setText(" " + score);
                                f6_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r12.setText(" " + score);
                                f7_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r12.setText(" " + score);
                                f8_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r12.setText(" " + score);
                                f9_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r12.setText(" " + score);
                                f10_r12.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and12.setVisibility(View.VISIBLE);
                            back1_hole12.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r12.setText(" " + score);
                                b1_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r12.setText(" " + score);
                                b2_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r12.setText(" " + score);
                                b3_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r12.setText(" " + score);
                                b4_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r12.setText(" " + score);
                                b5_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r12.setText(" " + score);
                                b6_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r12.setText(" " + score);
                                b7_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r12.setText(" " + score);
                                b8_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r12.setText(" " + score);
                                b9_r12.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r12.setText(" " + score);
                                b10_r12.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 12) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a13.setText(holeNumber);
                        r13.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r13.setText(" " + score);
                                f1_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r13.setText(" " + score);
                                f2_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r13.setText(" " + score);
                                f3_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r13.setText(" " + score);
                                f4_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r13.setText(" " + score);
                                f5_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r13.setText(" " + score);
                                f6_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r13.setText(" " + score);
                                f7_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r13.setText(" " + score);
                                f8_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r13.setText(" " + score);
                                f9_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r13.setText(" " + score);
                                f10_r13.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and13.setVisibility(View.VISIBLE);
                            back1_hole13.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r13.setText(" " + score);
                                b1_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r13.setText(" " + score);
                                b2_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r13.setText(" " + score);
                                b3_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r13.setText(" " + score);
                                b4_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r13.setText(" " + score);
                                b5_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r13.setText(" " + score);
                                b6_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r13.setText(" " + score);
                                b7_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r13.setText(" " + score);
                                b8_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r13.setText(" " + score);
                                b9_r13.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r13.setText(" " + score);
                                b10_r13.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 13) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a14.setText(holeNumber);
                        r14.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r14.setText(" " + score);
                                f1_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r14.setText(" " + score);
                                f2_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r14.setText(" " + score);
                                f3_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r14.setText(" " + score);
                                f4_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r14.setText(" " + score);
                                f5_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r14.setText(" " + score);
                                f6_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r14.setText(" " + score);
                                f7_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r14.setText(" " + score);
                                f8_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r14.setText(" " + score);
                                f9_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r14.setText(" " + score);
                                f10_r14.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and14.setVisibility(View.VISIBLE);
                            back1_hole14.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r14.setText(" " + score);
                                b1_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r14.setText(" " + score);
                                b2_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r14.setText(" " + score);
                                b3_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r14.setText(" " + score);
                                b4_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r14.setText(" " + score);
                                b5_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r14.setText(" " + score);
                                b6_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r14.setText(" " + score);
                                b7_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r14.setText(" " + score);
                                b8_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r14.setText(" " + score);
                                b9_r14.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r14.setText(" " + score);
                                b10_r14.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 14) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a15.setText(holeNumber);
                        r15.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r15.setText(" " + score);
                                f1_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r15.setText(" " + score);
                                f2_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r15.setText(" " + score);
                                f3_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r15.setText(" " + score);
                                f4_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r15.setText(" " + score);
                                f5_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r15.setText(" " + score);
                                f6_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r15.setText(" " + score);
                                f7_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r15.setText(" " + score);
                                f8_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r15.setText(" " + score);
                                f9_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r15.setText(" " + score);
                                f10_r15.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and15.setVisibility(View.VISIBLE);
                            back1_hole15.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r15.setText(" " + score);
                                b1_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r15.setText(" " + score);
                                b2_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r15.setText(" " + score);
                                b3_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r15.setText(" " + score);
                                b4_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r15.setText(" " + score);
                                b5_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r15.setText(" " + score);
                                b6_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r15.setText(" " + score);
                                b7_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r15.setText(" " + score);
                                b8_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r15.setText(" " + score);
                                b9_r15.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r15.setText(" " + score);
                                b10_r15.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 15) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a16.setText(holeNumber);
                        r16.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r16.setText(" " + score);
                                f1_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r16.setText(" " + score);
                                f2_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r16.setText(" " + score);
                                f3_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r16.setText(" " + score);
                                f4_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r16.setText(" " + score);
                                f5_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r16.setText(" " + score);
                                f6_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r16.setText(" " + score);
                                f7_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r16.setText(" " + score);
                                f8_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r16.setText(" " + score);
                                f9_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r16.setText(" " + score);
                                f10_r16.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and16.setVisibility(View.VISIBLE);
                            back1_hole16.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r16.setText(" " + score);
                                b1_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r16.setText(" " + score);
                                b2_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r16.setText(" " + score);
                                b3_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r16.setText(" " + score);
                                b4_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r16.setText(" " + score);
                                b5_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r16.setText(" " + score);
                                b6_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r16.setText(" " + score);
                                b7_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r16.setText(" " + score);
                                b8_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r16.setText(" " + score);
                                b9_r16.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r16.setText(" " + score);
                                b10_r16.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 16) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a17.setText(holeNumber);
                        r17.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r17.setText(" " + score);
                                f1_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r17.setText(" " + score);
                                f2_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r17.setText(" " + score);
                                f3_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r17.setText(" " + score);
                                f4_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r17.setText(" " + score);
                                f5_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r17.setText(" " + score);
                                f6_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r17.setText(" " + score);
                                f7_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r17.setText(" " + score);
                                f8_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r17.setText(" " + score);
                                f9_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r17.setText(" " + score);
                                f10_r17.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and17.setVisibility(View.VISIBLE);
                            back1_hole17.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r17.setText(" " + score);
                                b1_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r17.setText(" " + score);
                                b2_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r17.setText(" " + score);
                                b3_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r17.setText(" " + score);
                                b4_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r17.setText(" " + score);
                                b5_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r17.setText(" " + score);
                                b6_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r17.setText(" " + score);
                                b7_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r17.setText(" " + score);
                                b8_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r17.setText(" " + score);
                                b9_r17.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r17.setText(" " + score);
                                b10_r17.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    } else if (i == 17) {
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole_a18.setText(holeNumber);
                        r18.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r18.setText(" " + score);
                                f1_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r18.setText(" " + score);
                                f2_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r18.setText(" " + score);
                                f3_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r18.setText(" " + score);
                                f4_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r18.setText(" " + score);
                                f5_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r18.setText(" " + score);
                                f6_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r18.setText(" " + score);
                                f7_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r18.setText(" " + score);
                                f8_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r18.setText(" " + score);
                                f9_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r18.setText(" " + score);
                                f10_r18.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and18.setVisibility(View.VISIBLE);
                            back1_hole18.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r18.setText(" " + score);
                                b1_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r18.setText(" " + score);
                                b2_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r18.setText(" " + score);
                                b3_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r18.setText(" " + score);
                                b4_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r18.setText(" " + score);
                                b5_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r18.setText(" " + score);
                                b6_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r18.setText(" " + score);
                                b7_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r18.setText(" " + score);
                                b8_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r18.setText(" " + score);
                                b9_r18.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r18.setText(" " + score);
                                b10_r18.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                    }
                }

            } else {

            }
        } catch (JSONException je)

        {
            je.printStackTrace();
        }
    }


    public void expand21Popup() {

        ImageView finishdialogfrag;

        String eventID;

        final Dialog dialog21 = new Dialog(AddScoreNew.this, android.R.style.Theme_Translucent_NoTitleBar);
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


        eventID = ((AddScoreNew) this).geteventID();
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


    public void expand420_Popup() {

        ImageView finishdialogfrag;

        String eventID;

        final Dialog dialog420 = new Dialog(AddScoreNew.this, android.R.style.Theme_Translucent_NoTitleBar);
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


        eventID = ((AddScoreNew) this).geteventID();
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


    public void getBannerList(String eventID) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("type", "5");
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("event_id", eventID);


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


                } else {
                    String errorMessage = jsonObject.getString("message");
                    // Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void bannerFullMethod(String image, final String url, final String adm_ID, final String eve_ID, final String pl_ID) {
        final Dialog dialogB = new Dialog(AddScoreNew.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialogB.setCanceledOnTouchOutside(false);
        Window window = dialogB.getWindow();
        dialogB.requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialogB.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        // Include dialog.xml file
        dialogB.setContentView(R.layout.full_banner_layout);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        ImageView close = (ImageView) dialogB.findViewById(R.id.banner_full_close);
        ImageView bannerImage = (ImageView) dialogB.findViewById(R.id.banner_image_full);
        if (image != null) {
            Picasso.with(this).load(image).into(bannerImage);
        }

        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (url != null && url.length() > 10) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
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

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogB.cancel();
                saveRoundRequest(adm_ID, eve_ID, pl_ID);
            }
        });


        dialogB.show();
    }





    private void backMethod(){

        AlertDialog.Builder builder = new AlertDialog.Builder(AddScoreNew.this);
        builder.setTitle("").
                setMessage("This is first hole saved. Going back is not allowed.").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();


    }


    private void nextMethod(){

        AlertDialog.Builder builder = new AlertDialog.Builder(AddScoreNew.this);
        builder.setTitle("").
                setMessage("Save score to continue. Skipping of the holes is not allowed.").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }




}
