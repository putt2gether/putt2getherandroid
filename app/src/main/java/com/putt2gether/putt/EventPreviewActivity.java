package com.putt2gether.putt;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.putt2gether.R;
import com.putt2gether.bean.EventPostBean;
import com.putt2gether.bean.LeaderboardBean.ScoreBeanTwo;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.network.RefferenceWrapper;
import com.putt2gether.utils.Constant;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 24/06/2016.
 */
public class EventPreviewActivity extends AppCompatActivity {

    private ImageView backBTN;
    //  private RelativeLayout scorerBTN;
    private RelativeLayout submitBTN;
    private String event_id;

    private TextView golf_coursename;
    private TextView event_cretedBy;
    private TextView no_of_player;
    private TextView individualTeam;
    private TextView format;
    private RelativeLayout selectTeeM,selectTeeJ,selectTeeW;
    private TextView eventTime;
    private TextView noOfHole;
    private TextView eventType;
    private TextView hole;
    private RelativeLayout selectHoleLay;
    private TextView mText,jText,wText;
    private View view;
    private String deviceToken;
    private RefferenceWrapper refferenceWrapper;
    private String userID;
    private String golfCourseID;
    private String formatID;
    private String mID,lID,jID;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences pref;
    private SharedPreferences createDATA;
    private String friendID;
    private String groupID;
    private String email1,handicap1,name1;
    private String email2,handicap2,name2;
    private String email3,handicap3,name3;
    private String noOfPlayer;
    private String mulText="1";
    private int top;
    private int topGroup;
    private int count;
    JSONObject jsonObject2;

    private String teamA,teamB,teamA2ID,teamB1ID,teamB2ID;
    private String player1,player2,player3;
    LinearLayout spotPrizeLayout;
    private Constant constant;
    Typeface Lato_Regular;
    TextView closestTV1,longDriveTV1,straightDriveTV1;
    TextView closestTV2,longDriveTV2,straightDriveTV2;
    TextView closestTV3,longDriveTV3,straightDriveTV3;
    TextView closestTV4,longDriveTV4,straightDriveTV4;

    private RelativeLayout closestBTN1,longDriveBTN1,straightDriveBTN1;
    private RelativeLayout closestBTN2,longDriveBTN2,straightDriveBTN2;
    private RelativeLayout closestBTN3,longDriveBTN3,straightDriveBTN3;
    private RelativeLayout closestBTN4,longDriveBTN4,straightDriveBTN4;

    String popUpMsg;

    private RelativeLayout teamLayout;
    private String isSpot;
    private RelativeLayout starightDriveLayout,longDriveLayout,closestLayout;
    private RelativeLayout eventTypeLayout;
    private RelativeLayout multiScreenLayout;
    private TextView multiText;
    String teamText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_preview);

        constant = new Constant();
        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);


        multiScreenLayout = (RelativeLayout)findViewById(R.id.multi_screen_layout);
        multiText = (TextView)findViewById(R.id.preview_multi_screenTV);

        eventTypeLayout = (RelativeLayout)findViewById(R.id.event_type_layout);

        starightDriveLayout = (RelativeLayout)findViewById(R.id.straightDrive_pinLayout);
        longDriveLayout = (RelativeLayout)findViewById(R.id.longDrive_pinLayout);
        closestLayout = (RelativeLayout)findViewById(R.id.closest_pinLayout);

        closestBTN1 = (RelativeLayout)findViewById(R.id.preview_closest_btn1);
        closestBTN2 = (RelativeLayout)findViewById(R.id.preview_closest_btn2);
        closestBTN3 = (RelativeLayout)findViewById(R.id.preview_closest_btn3);
        closestBTN4 = (RelativeLayout)findViewById(R.id.preview_closest_btn4);

        longDriveBTN1 = (RelativeLayout)findViewById(R.id.preview_long_drive_lay1);
        longDriveBTN2 = (RelativeLayout)findViewById(R.id.preview_long_drive_lay2);
        longDriveBTN3 = (RelativeLayout)findViewById(R.id.preview_long_drive_lay3);
        longDriveBTN4 = (RelativeLayout)findViewById(R.id.preview_long_drive_lay4);

        straightDriveBTN1 = (RelativeLayout)findViewById(R.id.preview_straight_drive_lay1);
        straightDriveBTN2 = (RelativeLayout)findViewById(R.id.preview_straight_drive_lay2);
        straightDriveBTN3 = (RelativeLayout)findViewById(R.id.preview_straight_drive_lay3);
        straightDriveBTN4 = (RelativeLayout)findViewById(R.id.preview_straight_drive_lay4);



        closestTV1 = (TextView)findViewById(R.id.preview_closest_pin_text1);
        longDriveTV1 = (TextView)findViewById(R.id.preview_long_drive_text1);
        straightDriveTV1 = (TextView)findViewById(R.id.preview_straight_drive_text1);

        closestTV2 = (TextView)findViewById(R.id.preview_closest_pin_text2);
        longDriveTV2 = (TextView)findViewById(R.id.preview_long_drive_text2);
        straightDriveTV2 = (TextView)findViewById(R.id.preview_straight_drive_text2);

        closestTV3 = (TextView)findViewById(R.id.preview_closest_pin_text3);
        longDriveTV3 = (TextView)findViewById(R.id.preview_long_drive_text3);
        straightDriveTV3 = (TextView)findViewById(R.id.preview_straight_drive_text3);

        closestTV4 = (TextView)findViewById(R.id.preview_closest_pin_text4);
        longDriveTV4 = (TextView)findViewById(R.id.preview_long_drive_text4);
        straightDriveTV4 = (TextView)findViewById(R.id.preview_straight_drive_text4);

        spotPrizeLayout = (LinearLayout)findViewById(R.id.preview_spot_prize_yes_lay);


      //  RelativeLayout spotPrizeLay = (RelativeLayout)findViewById(R.id.spot_prize_layout);

        teamA = getIntent().getStringExtra("teamA");
        teamB = getIntent().getStringExtra("teamB");

        teamA2ID = getIntent().getStringExtra("user1");
        teamB1ID = getIntent().getStringExtra("user2");
        teamB2ID = getIntent().getStringExtra("user3");

        player1 = getIntent().getStringExtra("invite_player1");
        player2 = getIntent().getStringExtra("invite_player2");
        player3 = getIntent().getStringExtra("invite_player3");

        Log.v("oihidhoi",player1+"kjkl"+player2+"kjkl"+player3);

        refferenceWrapper = RefferenceWrapper.getReferanceWapper(this);
        pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        createDATA = getSharedPreferences("craete_event", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = createDATA.edit();

        String topSTR = createDATA.getString("top",null);
        String topGroupSTR = createDATA.getString("topGroup",null);

        if (topGroupSTR!=null) {

            topGroup = Integer.parseInt(topGroupSTR);

        }

        if (topSTR!=null) {
            top = Integer.parseInt(topSTR);
        }
        String countSTR = createDATA.getString("count",null);

        if (countSTR!=null) {
            count = Integer.parseInt(countSTR);
            Log.v("COUNTTTT",countSTR);
        }

        userID = pref.getString("userId",null);
        Log.v("userIDkljklj",userID);

        golfCourseID =  pref.getString("golfCourseID",null);
        formatID = pref.getString("formateID",null);
        final String createby = pref.getString("createby",null);
        final String golfcourseName = pref.getString("golfcourseName",null);
        final String dateTime = pref.getString("dateTime1",null);
        final String radioText = pref.getString("radioText",null);

        final String multiScreenType = pref.getString("multiType",null);

        final String multiScreenText = pref.getString("multiScreenText",null);

        teamText = pref.getString("teamText",null);

        if (multiScreenType.equalsIgnoreCase("1")){

            multiText.setText(multiScreenText);
            multiScreenLayout.setVisibility(View.VISIBLE);


        }else {

            multiText.setText(multiScreenText);
            multiScreenLayout.setVisibility(View.GONE);
        }

        Log.v("multiValue","mu"+multiText.getText());



        if (radioText.equalsIgnoreCase("4+")){
            noOfPlayer = "4+";
            mulText = "2";

        }else if (radioText.equalsIgnoreCase("1")){
            noOfPlayer = "1";
            mulText = "1";

        }
        else if (radioText.equalsIgnoreCase("4")){
            if (teamText.equalsIgnoreCase("Team")) {
                noOfPlayer = radioText;
                mulText = "1";
            }else {
                noOfPlayer = radioText;

                String mul = multiText.getText().toString();
                if (mul.equalsIgnoreCase("yes")){
                    mulText = "1";
                }
                else {
                    mulText = "2";
                }
            }

        }else {

            noOfPlayer = radioText;

            String mul = multiText.getText().toString();
            if (mul.equalsIgnoreCase("yes")){
                mulText = "1";
            }
            else {
                mulText = "2";
            }

        }


        final String formateName = pref.getString("formateName",null);
        final String holeText = pref.getString("holeText",null);
        final String eventTypeText = pref.getString("eventTypeText",null);
        final String holeBackFront = pref.getString("holeBackFront",null);
        final String colorM = pref.getString("colorM",null);
        final String colorJ = pref.getString("colorJ",null);
        final String colorW = pref.getString("colorW",null);

        final String spotPrizeYesNo = pref.getString("spotPrizeType",null);

        final String closestText1 = pref.getString("closestText1",null);
        final String longDrivetxt1 = pref.getString("longDrivetxt1",null);
        final String straightDriveText1 = pref.getString("straightDriveText1",null);

        final String closestText2 = pref.getString("closestText2",null);
        final String longDrivetxt2 = pref.getString("longDrivetxt2",null);
        final String straightDriveText2 = pref.getString("straightDriveText2",null);

        final String closestText3 = pref.getString("closestText3",null);
        final String longDrivetxt3 = pref.getString("longDrivetxt3",null);
        final String straightDriveText3 = pref.getString("straightDriveText3",null);

        final String closestText4 = pref.getString("closestText4",null);
        final String longDrivetxt4 = pref.getString("longDrivetxt4",null);
        final String straightDriveText4 = pref.getString("straightDriveText4",null);

        if (closestText1.equalsIgnoreCase("-")&&closestText2.equalsIgnoreCase("-")&&closestText3.equalsIgnoreCase("-")&&closestText4.equalsIgnoreCase("-")){
            closestLayout.setVisibility(View.GONE);
        }

        if (straightDriveText1.equalsIgnoreCase("-")&&straightDriveText2.equalsIgnoreCase("-")&&straightDriveText3.equalsIgnoreCase("-")&&straightDriveText4.equalsIgnoreCase("-")){
            starightDriveLayout.setVisibility(View.GONE);

        }

        if (longDrivetxt1.equalsIgnoreCase("-")&&longDrivetxt2.equalsIgnoreCase("-")&&longDrivetxt3.equalsIgnoreCase("-")&&longDrivetxt4.equalsIgnoreCase("-")){

            longDriveLayout.setVisibility(View.GONE);

        }

        if (radioText!=null){
            //  int pla = Integer.parseInt(radioText);

            if (radioText.equalsIgnoreCase("4+")) {
                popUpMsg = "Your event " + createby + " on " + dateTime.substring(0, 11) + " for " + (top+count+1) + " participants is ready to be created!";
            }else {
                popUpMsg = "Your event " + createby + " on " + dateTime.substring(0, 11) + " for " + radioText + " participants is ready to be created!";
            }
        }



        if (spotPrizeYesNo.equalsIgnoreCase("Yes")){


            isSpot = "1";

          //  spotPrizeLay.setVisibility(View.VISIBLE);

            spotPrizeLayout.setVisibility(View.VISIBLE);

            if (closestText1.equalsIgnoreCase("-")){
                closestBTN1.setVisibility(View.GONE);
                closestTV1.setText("-");

            }else {
                closestTV1.setText(closestText1);
                closestBTN1.setVisibility(View.VISIBLE);
            }
            if (longDrivetxt1.equalsIgnoreCase("-")){
                longDriveBTN1.setVisibility(View.GONE);
                longDriveTV1.setText("-");

            }else {
                longDriveTV1.setText(longDrivetxt1);
                longDriveBTN1.setVisibility(View.VISIBLE);
            }
            if (straightDriveText1.equalsIgnoreCase("-")){
                straightDriveBTN1.setVisibility(View.GONE);
                straightDriveTV1.setText("-");

            }else {

                straightDriveBTN1.setVisibility(View.VISIBLE);
                straightDriveTV1.setText(straightDriveText1);
            }

            if (closestText2.equalsIgnoreCase("-")){
                closestBTN2.setVisibility(View.GONE);
                closestTV2.setText("-");

            }else {
                closestBTN2.setVisibility(View.VISIBLE);
                closestTV2.setText(closestText2);

            }
            if (longDrivetxt2.equalsIgnoreCase("-")){
                longDriveBTN2.setVisibility(View.GONE);
                longDriveTV2.setText("-");

            }else {
                longDriveBTN2.setVisibility(View.VISIBLE);
                longDriveTV2.setText(longDrivetxt2);

            }
            if (straightDriveText2.equalsIgnoreCase("-")){
                straightDriveBTN2.setVisibility(View.GONE);
                straightDriveTV2.setText("-");

            }else {

                straightDriveBTN2.setVisibility(View.VISIBLE);
                straightDriveTV2.setText(straightDriveText2);
            }

            if (closestText3.equalsIgnoreCase("-")){
                closestBTN3.setVisibility(View.GONE);
               closestTV3.setText("-");

            }else {
                closestBTN3.setVisibility(View.VISIBLE);
                closestTV3.setText(closestText3);
            }
            if (longDrivetxt3.equalsIgnoreCase("-")){
                longDriveBTN3.setVisibility(View.GONE);
                longDriveTV3.setText("-");

            }else {
                longDriveBTN3.setVisibility(View.VISIBLE);
                longDriveTV3.setText(longDrivetxt3);
            }
            if (straightDriveText3.equalsIgnoreCase("-")){
                straightDriveBTN3.setVisibility(View.GONE);
                straightDriveTV3.setText("-");

            }else {
                straightDriveBTN3.setVisibility(View.VISIBLE);

                straightDriveTV3.setText(straightDriveText3);
            }

            if (closestText4.equalsIgnoreCase("-")){
                closestBTN4.setVisibility(View.GONE);
                closestTV4.setText("-");

            }else {
                closestBTN4.setVisibility(View.VISIBLE);
                closestTV4.setText(closestText4);
            }
            if (longDrivetxt4.equalsIgnoreCase("-")){
                longDriveBTN4.setVisibility(View.GONE);
                longDriveTV4.setText("-");

            }else {
                longDriveBTN4.setVisibility(View.VISIBLE);
                longDriveTV4.setText(longDrivetxt4);
            }
            if (straightDriveText4.equalsIgnoreCase("-")){
                straightDriveBTN4.setVisibility(View.GONE);
                straightDriveTV4.setText("-");

            }else {

                straightDriveBTN4.setVisibility(View.VISIBLE);
                straightDriveTV4.setText(straightDriveText4);
            }


        }else {

            isSpot = "0";
          //  spotPrizeLay.setVisibility(View.GONE);
            spotPrizeLayout.setVisibility(View.GONE);
        }

        submitBTN = (RelativeLayout)findViewById(R.id.preview_create_event_btn);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(EventPreviewActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
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
                dialog.setContentView(R.layout.pop_up_layout_preview);

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView dialogText =(TextView) dialog.findViewById(R.id.popup_preview);
                RelativeLayout editBTN = (RelativeLayout)dialog.findViewById(R.id.edit_popup);
                RelativeLayout createBTN = (RelativeLayout)dialog.findViewById(R.id.create_popup);
                dialogText.setText(popUpMsg );
                dialog.show();
                editBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                createBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        createEventTask();
                        dialog.cancel();

                    }
                });


            }
        });
      /*  scorerBTN = (RelativeLayout)findViewById(R.id.preview_select_scorer_btn);
        view = (View)findViewById(R.id.preview_line);

        if (radioText!=null){
            if (radioText.equalsIgnoreCase("1")){

                view.setVisibility(View.GONE);
                scorerBTN.setVisibility(View.GONE);

            }else {
                view.setVisibility(View.VISIBLE);
                scorerBTN.setVisibility(View.VISIBLE);
            }
        }
*/
        selectHoleLay = (RelativeLayout)findViewById(R.id.select_hole_layout);
        golf_coursename = (TextView)findViewById(R.id.preview_golf_course_name);
        event_cretedBy = (TextView)findViewById(R.id.preview_event_name);
        no_of_player = (TextView)findViewById(R.id.preview_player_number);

        teamLayout = (RelativeLayout)findViewById(R.id.preview_individual_layout);

        individualTeam = (TextView)findViewById(R.id.preview_team_type);
        format = (TextView)findViewById(R.id.preview_formate);
        selectTeeM = (RelativeLayout)findViewById(R.id.preview_tee_m);
        selectTeeJ = (RelativeLayout)findViewById(R.id.preview_tee_j);
        selectTeeW = (RelativeLayout)findViewById(R.id.preview_tee_w);
        eventTime = (TextView)findViewById(R.id.date_time_editText);
        noOfHole = (TextView)findViewById(R.id.preview_holes_number);
        eventType = (TextView)findViewById(R.id.preview_event_type);
        hole = (TextView)findViewById(R.id.preview_frntBck_hole);
        mText = (TextView)findViewById(R.id.mText_preview);
        jText = (TextView)findViewById(R.id.jText_preview);
        wText = (TextView)findViewById(R.id.wtext_preview);

        if (holeText!=null){
            if (holeText.equalsIgnoreCase("18")){
                selectHoleLay.setVisibility(View.GONE);
            }else {
                selectHoleLay.setVisibility(View.VISIBLE);
            }}

        if (golfcourseName != null) {
            golf_coursename.setText(golfcourseName.toUpperCase());
        }
        event_cretedBy.setText(createby.toUpperCase());

        //no_of_player.setText();



        format.setText(formateName.toUpperCase());
        if (radioText.equalsIgnoreCase("4+")){

            eventTypeLayout.setVisibility(View.VISIBLE);
            int total_player_above = top+count+1;
            String rad = String.valueOf((total_player_above));

            no_of_player.setText(rad);

            individualTeam.setText(teamText.toUpperCase());
            teamLayout.setVisibility(View.GONE);
        }else if (radioText.equalsIgnoreCase("4")){
            teamLayout.setVisibility(View.VISIBLE);
            eventTypeLayout.setVisibility(View.GONE);
            individualTeam.setText(teamText.toUpperCase());
            no_of_player.setText(radioText.toUpperCase());
        }else {
            no_of_player.setText(radioText.toUpperCase());
            individualTeam.setText(teamText.toUpperCase());
            teamLayout.setVisibility(View.GONE);
            eventTypeLayout.setVisibility(View.GONE);
        }
        eventTime.setText(dateTime);
        if (holeText !=null) {
            noOfHole.setText(holeText);
        }
        if (eventTypeText!=null) {
            eventType.setText(eventTypeText.toUpperCase());
        }else {

            eventTypeLayout.setVisibility(View.GONE);
        }
        if (holeBackFront!=null) {
            hole.setText(holeBackFront.toUpperCase());
        }


        if (colorM.equalsIgnoreCase("Red")){
            selectTeeM.setBackgroundResource(R.drawable.red_tee);
            mID = "3";

        }else if (colorM.equalsIgnoreCase("Green")){
            selectTeeM.setBackgroundResource(R.drawable.green_tee);
            mID = "6";

        }else if (colorM.equalsIgnoreCase("Yellow")){
            selectTeeM.setBackgroundResource(R.drawable.yello_tee);
            mID = "4";
            mText.setTextColor(Color.BLACK);

        }else if (colorM.equalsIgnoreCase("Black")){
            mID = "1";
            selectTeeM.setBackgroundResource(R.drawable.black_tee);

        }else if (colorM.equalsIgnoreCase("White")){
            selectTeeM.setBackgroundResource(R.drawable.white_tee);
            mID = "5";
            mText.setTextColor(Color.BLACK);

        }else if (colorM.equalsIgnoreCase("Blue")){
            selectTeeM.setBackgroundResource(R.drawable.blue_tee);
            mID = "2";

        }else if (colorM.equalsIgnoreCase("Gold")){
            selectTeeM.setBackgroundResource(R.drawable.gold_tee);
            mID = "7";
            mText.setTextColor(Color.BLACK);

        }else if (colorM.equalsIgnoreCase("Silver")){
            selectTeeM.setBackgroundResource(R.drawable.silver_tee);
            mID = "8";
            mText.setTextColor(Color.BLACK);

        }

        if (colorJ.equalsIgnoreCase("Red")){
            selectTeeJ.setBackgroundResource(R.drawable.red_tee);
            jID = "3";

        }else if (colorJ.equalsIgnoreCase("Green")){
            selectTeeJ.setBackgroundResource(R.drawable.green_tee);
            jID = "6";

        }else if (colorJ.equalsIgnoreCase("Yellow")){
            selectTeeJ.setBackgroundResource(R.drawable.yello_tee);
            jID = "4";
            jText.setTextColor(Color.BLACK);

        }else if (colorJ.equalsIgnoreCase("Black")){
            selectTeeJ.setBackgroundResource(R.drawable.black_tee);
            jID = "1";

        }else if (colorJ.equalsIgnoreCase("White")){
            selectTeeJ.setBackgroundResource(R.drawable.white_tee);
            jText.setTextColor(Color.BLACK);
            jID = "5";

        }else if (colorJ.equalsIgnoreCase("Blue")){
            selectTeeJ.setBackgroundResource(R.drawable.blue_tee);
            jID = "2";

        }else if (colorJ.equalsIgnoreCase("Gold")){
            selectTeeJ.setBackgroundResource(R.drawable.gold_tee);
            jText.setTextColor(Color.BLACK);
            jID = "7";

        }else if (colorJ.equalsIgnoreCase("Silver")){
            selectTeeJ.setBackgroundResource(R.drawable.silver_tee);
            jText.setTextColor(Color.BLACK);
            jID = "8";

        }

        if (colorW.equalsIgnoreCase("Red")){
            selectTeeW.setBackgroundResource(R.drawable.red_tee);
            lID = "3";

        }else if (colorW.equalsIgnoreCase("Green")){
            selectTeeW.setBackgroundResource(R.drawable.green_tee);
            lID = "6";

        }else if (colorW.equalsIgnoreCase("Yellow")){
            selectTeeW.setBackgroundResource(R.drawable.yello_tee);
            lID = "4";
            wText.setTextColor(Color.BLACK);

        }else if (colorW.equalsIgnoreCase("Black")){
            selectTeeW.setBackgroundResource(R.drawable.black_tee);
            lID = "1";

        }else if (colorW.equalsIgnoreCase("White")){
            selectTeeW.setBackgroundResource(R.drawable.white_tee);
            lID = "5";
            wText.setTextColor(Color.BLACK);

        }else if (colorW.equalsIgnoreCase("Blue")){
            selectTeeW.setBackgroundResource(R.drawable.blue_tee);
            lID = "2";

        }else if (colorW.equalsIgnoreCase("Gold")){
            selectTeeW.setBackgroundResource(R.drawable.gold_tee);
            lID = "7";
            wText.setTextColor(Color.BLACK);

        }else if (colorW.equalsIgnoreCase("Silver")){
            selectTeeW.setBackgroundResource(R.drawable.silver_tee);
            lID = "8";
            wText.setTextColor(Color.BLACK);

        }


      /*  scorerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             *//*   Intent intent =new Intent(getApplicationContext(),ScorerActivity.class);
                startActivity(intent);*//*
                scorerPost();

            }
        });*/

        backBTN = (ImageView)findViewById(R.id.back_preview_event);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }

    private void createEventTask() {
        String datTime = eventTime.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm");
        Date testDate = null;
        try {
            testDate = sdf.parse(datTime);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String newFormat = formatter.format(testDate);


        String startDate = newFormat.substring(0,10);
        String startTime = newFormat.substring(11,eventTime.length()-1);



        String deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("event_golf_course_id", golfCourseID);
            jsonObject.putOpt("version", "2");
            String string = event_cretedBy.getText().toString();

            jsonObject.putOpt("event_name",string);
            jsonObject.putOpt("no_of_player", noOfPlayer);
            jsonObject.putOpt("event_format_id", formatID);
            jsonObject.putOpt("event_start_date",startDate);
            jsonObject.putOpt("event_start_time",startTime);
            jsonObject.putOpt("event_is_spot",isSpot);

            jsonObject.putOpt("is_singlescreen",mulText);

            if (eventType.getText().toString().equalsIgnoreCase("public")) {
                jsonObject.putOpt("event_is_public","1");
            }else {
                jsonObject.putOpt("event_is_public","0");
            }

            if (noOfHole.getText().toString().equalsIgnoreCase("18")) {
                jsonObject.putOpt("num_of_holes", "18");
            }else {
                if (hole.getText().toString().equalsIgnoreCase("front9")) {
                    jsonObject.putOpt("num_of_holes", "9");
                    jsonObject.putOpt("select_holes", "1");
                }else {

                    jsonObject.putOpt("num_of_holes", "9");
                    jsonObject.putOpt("select_holes", "10");
                }
            }
            jsonObject.putOpt("event_admin_id",userID);
            jsonArray = new JSONArray();
            JSONObject jsonObjectM = new JSONObject();
            JSONObject jsonObjectL = new JSONObject();
            JSONObject jsonObjectJ = new JSONObject();

            jsonObjectM.putOpt("men",mID);
            jsonArray.put(jsonObjectM);
            jsonObjectL.putOpt("ladies",lID);
            jsonArray.put(jsonObjectL);
            jsonObjectJ.putOpt("junior",jID);
            jsonArray.put(jsonObjectJ);

            jsonObject.putOpt("event_tee_id",jsonArray);

            for (int i = 0; i<count; i++) {

                name1 = createDATA.getString("player1Name", null);
                email1 = createDATA.getString("player1Email", null);
                handicap1 = createDATA.getString("player1Handicap", null);

            }

            if (name1!=null) {
                Log.v("Checking", name1 + "_" + email1 + "_" + handicap1);
            }

            if (topGroup!=0) {
                JSONArray jsonArrayG = new JSONArray();
                for (int i=0; i<topGroup ;i++){
                    JSONObject jsonObjectG = new JSONObject();
                    groupID = createDATA.getString("groupID"+i,null);
                    jsonObjectG.putOpt("group", groupID);
                    jsonArrayG.put(jsonObjectG);
                }
                jsonObject.putOpt("event_group_list", jsonArrayG);
            }

            if (top!=0) {
                JSONArray jsonArray1 = new JSONArray();
                for (int i=0; i<top ;i++){
                    jsonObject2 = new JSONObject();
                    friendID = createDATA.getString("friendID"+i,null);
                    jsonObject2.putOpt("friend_id", friendID);
                    jsonArray1.put(jsonObject2);
                }
                jsonObject.putOpt("event_friend_list", jsonArray1);
            }

           /* SharedPreferences pref = this.getSharedPreferences("craete_event", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("player3Team", "2");
            editor.commit();*/

            if (count!=0) {
                JSONArray jsonArray2 = new JSONArray();
                for (int i = 0; i < count; i++) {
                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.putOpt("name", createDATA.getString("player" + (i + 1) + "Name", null));
                    jsonObject3.putOpt("email", createDATA.getString("player" + (i + 1) + "Email", null));
                    jsonObject3.putOpt("handicap", createDATA.getString("player" + (i + 1) + "Handicap", null));

                    String createPlayerType = createDATA.getString("player" + (i + 1) + "Team", null);

                    if (createPlayerType!=null){
                        jsonObject3.putOpt("team_number", createPlayerType);
                    }else {
                        jsonObject3.putOpt("team_number","0");
                    }

                    jsonArray2.put(jsonObject3);
                }
                jsonObject.putOpt("invited_email_list", jsonArray2);
            }

            if (noOfPlayer.equalsIgnoreCase("2")) {
                JSONArray jsonArrayThree = new JSONArray();
                JSONObject jsonObjectThree1 = new JSONObject();
                JSONObject jsonObjectThree2 = new JSONObject();

                jsonObjectThree1.putOpt("friend_id", userID);
                jsonArrayThree.put(jsonObjectThree1);

                if (player1 != null) {
                    jsonObjectThree2.putOpt("friend_id", player1);
                    jsonArrayThree.put(jsonObjectThree2);
                }
                jsonObject.putOpt("event_friend_list", jsonArrayThree);

            }

            if (noOfPlayer.equalsIgnoreCase("3")) {

                JSONArray jsonArrayThree = new JSONArray();
                JSONObject jsonObjectThree1 = new JSONObject();
                JSONObject jsonObjectThree2 = new JSONObject();
                JSONObject jsonObjectThree3 = new JSONObject();

                jsonObjectThree1.putOpt("friend_id", userID);
                jsonArrayThree.put(jsonObjectThree1);

                if (player1 != null) {
                    jsonObjectThree2.putOpt("friend_id", player1);
                    jsonArrayThree.put(jsonObjectThree2);
                } if (player2 != null){
                    jsonObjectThree3.putOpt("friend_id", player2);
                    jsonArrayThree.put(jsonObjectThree3);
                }
                jsonObject.putOpt("event_friend_list", jsonArrayThree);

            }

            if (noOfPlayer.equalsIgnoreCase("4")) {

                if (teamText.equalsIgnoreCase("Team")) {

                    jsonObject.putOpt("event_is_team", "1");
                    jsonObject.putOpt("event_team_num", "4");

                    JSONArray jsonArrayTeam = new JSONArray();
                    JSONObject jsonObjectTeamA = new JSONObject();
                    if (teamA != null) {
                        jsonObjectTeamA.putOpt("team_name_1", teamA);
                        jsonObjectTeamA.putOpt("event_friend_num", "2");
                    }
                    JSONArray js = new JSONArray();
                    JSONObject jo = new JSONObject();
                    jo.putOpt("friend_id_1", userID);
                    if (teamA2ID != null) {
                        jo.putOpt("friend_id_2", teamA2ID);
                    }
                    js.put(jo);
                    jsonObjectTeamA.putOpt("event_friend_list", js);
                    jsonArrayTeam.put(jsonObjectTeamA);

                    JSONObject jsTeamB = new JSONObject();
                    if (teamB != null) {
                        jsTeamB.putOpt("team_name_2", teamB);
                        jsTeamB.putOpt("event_friend_num", "2");
                    }
                    JSONArray js1 = new JSONArray();
                    JSONObject jo1 = new JSONObject();
                    if (teamB1ID != null) {
                        jo1.putOpt("friend_id_1", teamB1ID);
                    }
                    if (teamB2ID != null) {
                        jo1.putOpt("friend_id_2", teamB2ID);
                    }
                    js1.put(jo1);
                    jsTeamB.putOpt("event_friend_list", js1);
                    jsonArrayTeam.put(jsTeamB);

                    jsonObject.putOpt("team_list", jsonArrayTeam);
                }else {

                    jsonObject.putOpt("event_is_team", "0");
                    jsonObject.putOpt("event_team_num", "4");
                    JSONArray jsonArrayThree = new JSONArray();
                    JSONObject jsonObjectThree1 = new JSONObject();
                    JSONObject jsonObjectThree2 = new JSONObject();
                    JSONObject jsonObjectThree3 = new JSONObject();
                    JSONObject jsonObjectThree4 = new JSONObject();

                    jsonObjectThree1.putOpt("friend_id", userID);
                    jsonArrayThree.put(jsonObjectThree1);

                    if (player1 != null) {
                        jsonObjectThree2.putOpt("friend_id", player1);
                        jsonArrayThree.put(jsonObjectThree2);
                    } if (player2 != null){
                        jsonObjectThree3.putOpt("friend_id", player2);
                        jsonArrayThree.put(jsonObjectThree3);
                    }
                    if (player3 != null){
                        jsonObjectThree4.putOpt("friend_id", player3);
                        jsonArrayThree.put(jsonObjectThree4);
                    }
                    jsonObject.putOpt("event_friend_list", jsonArrayThree);

                }

            }else {
                jsonObject.putOpt("event_is_team", "0");

            }

            JSONObject closestObject = new JSONObject();

            if (closestTV1.getText().toString().equalsIgnoreCase("-")){

            }else {
                closestObject.putOpt("hole_1",closestTV1.getText().toString());
            }
            if (closestTV2.getText().toString().equalsIgnoreCase("-")){

            }else {
                closestObject.putOpt("hole_2",closestTV2.getText().toString());
            }
            if (closestTV3.getText().toString().equalsIgnoreCase("-")){

            }else {
                closestObject.putOpt("hole_3",closestTV3.getText().toString());
            }
            if (closestTV4.getText().toString().equalsIgnoreCase("-")){

            }else {
                closestObject.putOpt("hole_4",closestTV4.getText().toString());
            }

            jsonObject.putOpt("closest_pin",closestObject);

            JSONObject longDriveObject = new JSONObject();

            if (longDriveTV1.getText().toString().equalsIgnoreCase("-")){

            }else {
                longDriveObject.putOpt("hole_1",longDriveTV1.getText().toString());
            }
            if (longDriveTV2.getText().toString().equalsIgnoreCase("-")){

            }else {
                longDriveObject.putOpt("hole_2",longDriveTV2.getText().toString());
            }
            if (longDriveTV3.getText().toString().equalsIgnoreCase("-")){

            }else {
                longDriveObject.putOpt("hole_3",longDriveTV3.getText().toString());
            }
            if (longDriveTV4.getText().toString().equalsIgnoreCase("-")){

            }else {
                longDriveObject.putOpt("hole_4",longDriveTV4.getText().toString());
            }

            jsonObject.putOpt("long_drive",longDriveObject);


            JSONObject sraightDriveObject = new JSONObject();

            if (straightDriveTV1.getText().toString().equalsIgnoreCase("-")){

            }else {
                sraightDriveObject.putOpt("hole_1",straightDriveTV1.getText().toString());
            }
            if (straightDriveTV2.getText().toString().equalsIgnoreCase("-")){

            }else {
                sraightDriveObject.putOpt("hole_2",straightDriveTV2.getText().toString());
            }
            if (straightDriveTV3.getText().toString().equalsIgnoreCase("-")){

            }else {
                sraightDriveObject.putOpt("hole_3",straightDriveTV3.getText().toString());
            }
            if (straightDriveTV4.getText().toString().equalsIgnoreCase("-")){

            }else {
                sraightDriveObject.putOpt("hole_4",straightDriveTV4.getText().toString());
            }

            jsonObject.putOpt("straight_drive",sraightDriveObject);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("Post Event",jsonObject.toString());
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.CREATE_EVENT_POST, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                Log.e("Create Event", "OnResponse =" + response.toString());
                getCreateResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "error_network_timeout",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "ParseError", Toast.LENGTH_LONG).show();
                }
                pDialog.dismiss();

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("POST EVENT", "Url= " + PUTTAPI.CREATE_EVENT_POST + " PostObject = " + jsonObject.toString());
        //requestQueue.add(jsonObjectRequest);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
    private void getCreateResponse(JSONObject response) {
        mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        EventPostBean createBean = new EventPostBean();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")){
                    JSONObject jsonObject1 = jsonObject.getJSONObject("Event");
                    //createBean.setEvent_id(jsonObject1.getString("event_id"));
                    createBean.setMessage(jsonObject1.getString("message"));
                    event_id = jsonObject1.getString("event_id");



                    SharedPreferences createSharedPreferences = getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = createSharedPreferences.edit();
                    editor1.clear();
                    editor1.commit();

                    Intent intent  =  new Intent(getApplicationContext(),HomeActivity.class);
                    intent.putExtra("eventID",event_id);
                    intent.putExtra("fromEventPreview","1");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
/*

                    final Dialog dialog = new Dialog(EventPreviewActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
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
                    dialog.setContentView(R.layout.pop_up_layout_preview);

                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    TextView dialogText =(TextView) dialog.findViewById(R.id.popup_preview);
                    RelativeLayout editBTN = (RelativeLayout)dialog.findViewById(R.id.edit_popup);
                    RelativeLayout createBTN = (RelativeLayout)dialog.findViewById(R.id.create_popup);
                    dialogText.setText(createBean.getMessage());
                    dialog.show();
                    editBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });

                    createBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.cancel();
                            createEventTask();

                        }
                    });

                    */

                    //  JSONObject jsonObject2 = jsonObject.getJSONObject("data");

                    //  createBean.setEvent_id(jsonObject1.getString("event_id"));
                    // createBean.setAdmin_id(jsonObject1.getString("admin_id"));
                    // createBean.setGolf_course_name(jsonObject1.getString("golf_course_name"));
                    // createBean.setEvent_name(jsonObject1.getString("event_name"));
                    // createBean.setEvent_date(jsonObject1.getString("event_date"));

                    // createBean.setEvent_start_time(jsonObject1.getString("event_start_time"));
                    //  createBean.setTotal_hole_num(jsonObject1.getString("total_hole_num"));
                    //  createBean.setHole_start_from(jsonObject1.getString("hole_start_from"));
                    // createBean.setFormat_name(jsonObject1.getString("format_name"));
                    // createBean.setStoke_name(jsonObject1.getString("stoke_name"));
                    // createBean.setMessage(jsonObject.getString("message"));
                    //Toast.makeText(getApplicationContext(),"Profile "+createBean.getMessage(),Toast.LENGTH_LONG).show();
                    //succesMSG = createBean.getMessage();


                }else{

                    String str = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void scorerPost(){

     /*   noOfPlayer = no_of_player.getText().toString();
        String strrr = no_of_player.getText().toString();
        int ccc = Integer.parseInt(strrr);
        if (ccc<5){

            noOfPlayer = no_of_player.getText().toString();
        }else {
            noOfPlayer = "4+";
        }*/

        String deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("event_admin_id",userID);


            for (int i = 0; i<count; i++) {
                name1 = createDATA.getString("player1Name", null);
                email1 = createDATA.getString("player1Email", null);
                handicap1 = createDATA.getString("player1Handicap", null);

            }

            if (name1!=null) {
                Log.v("Checking", name1 + "_" + email1 + "_" + handicap1);
            }


            if (topGroup!=0) {
                JSONArray jsonArrayG = new JSONArray();
                for (int i=0; i<topGroup ;i++){
                    JSONObject jsonObjectG = new JSONObject();
                    groupID = createDATA.getString("groupID"+i,null);
                    jsonObjectG.putOpt("group", groupID);
                    jsonArrayG.put(jsonObjectG);
                }
                jsonObject.putOpt("event_group_list", jsonArrayG);
            }

            if (top!=0) {
                JSONArray jsonArray1 = new JSONArray();
                for (int i=0; i<top ;i++){
                    jsonObject2 = new JSONObject();
                    friendID = createDATA.getString("friendID"+i,null);
                    jsonObject2.putOpt("friend_id", friendID);
                    jsonArray1.put(jsonObject2);
                }
                jsonObject.putOpt("event_friend_list", jsonArray1);
            }

            if (count!=0) {
                JSONArray jsonArray2 = new JSONArray();
                for (int i = 0; i < count; i++) {
                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.putOpt("name", createDATA.getString("player" + (i + 1) + "Name", null));
                    jsonObject3.putOpt("email", createDATA.getString("player" + (i + 1) + "Email", null));
                    jsonObject3.putOpt("handicap", createDATA.getString("player" + (i + 1) + "Handicap", null));
                    jsonArray2.put(jsonObject3);
                }
                jsonObject.putOpt("invited_email_list", jsonArray2);
            }

            if (noOfPlayer.equalsIgnoreCase("4")) {

                JSONArray jsonArrayTeam = new JSONArray();
                JSONObject jsonObjectTeamA = new JSONObject();
                if (teamA != null) {
                    jsonObjectTeamA.putOpt("team_name_1", teamA);
                    jsonObjectTeamA.putOpt("event_friend_num", "2");
                }
                JSONArray js = new JSONArray();
                JSONObject jo = new JSONObject();
                jo.putOpt("friend_id_1", userID);
                if (teamA2ID != null) {
                    jo.putOpt("friend_id_2", teamA2ID);
                }
                js.put(jo);
                jsonObjectTeamA.putOpt("event_friend_list", js);
                jsonArrayTeam.put(jsonObjectTeamA);

                JSONObject jsonObjectTeamB = new JSONObject();
                if (teamB != null) {
                    jsonObjectTeamA.putOpt("team_name_2", teamB);
                    jsonObjectTeamA.putOpt("event_friend_num", "2");
                }
                JSONArray js1 = new JSONArray();
                JSONObject jo1 = new JSONObject();
                if (teamB1ID != null) {
                    jo1.putOpt("friend_id_1", teamB1ID);
                }
                if (teamB2ID != null) {
                    jo1.putOpt("friend_id_2", teamB2ID);
                }
                js1.put(jo1);
                jsonObjectTeamB.putOpt("event_friend_list", js1);
                jsonArrayTeam.put(jsonObjectTeamB);
                jsonObject.putOpt("team_list", jsonArrayTeam);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("Scorer List",jsonObject.toString());
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GETSCORERLIST, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                Log.e("Create Event", "OnResponse =" + response.toString());
                getScorerList(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "error_network_timeout",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "ParseError", Toast.LENGTH_LONG).show();
                }
                pDialog.dismiss();

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Scorer List", "Url= " + PUTTAPI.GETSCORERLIST + " PostObject = " + jsonObject.toString());
        //requestQueue.add(jsonObjectRequest);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
    private void getScorerList(JSONObject response) {
        mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        ArrayList<ScoreBeanTwo> ab1=new ArrayList<ScoreBeanTwo>();
        ScoreBeanTwo createBean;
        if (response != null) {
            try {

                Log.e("GETSCORELIST",response.toString());

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")){

                    JSONArray data=jsonObject.getJSONArray("data");

                    for(int i=0;i<data.length();i++)
                    {
                        createBean = new ScoreBeanTwo();
                        JSONObject jo2=data.getJSONObject(i);
                        for(int j=0;j<jo2.length();j++)
                        {


                            String participant_ID=jo2.getString("participant_id");
                            String photo_URL=jo2.getString("photo_url");
                            String handicap_Value=jo2.getString("handicap_value");
                            String name=jo2.getString("name");

                            createBean.setName(name);
                            createBean.setHandicap_Value(handicap_Value);
                            createBean.setPhoto_url(photo_URL);
                            createBean.setParticipant_ID(participant_ID);
                            createBean.setSelf_delegate("self");
                        }

                        ab1.add(createBean);

                    }

                    if(ab1.size()>0) {
                        Intent intent = new Intent(getApplicationContext(), ScorerActivity.class);
                        intent.putExtra("ACTIVITY", "ONE");
                        intent.putExtra("eventID", event_id);
                        intent.putExtra("mylist", (Serializable) ab1);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"No scorer",Toast.LENGTH_SHORT).show();
                    }

                }else{

                    String str = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
