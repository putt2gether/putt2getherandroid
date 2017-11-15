package com.putt2gether.putt;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.putt2gether.bean.InviteDetailBean;
import com.putt2gether.bean.InviteUserBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.addScore.AddScoreNew;
import com.putt2gether.putt.addScore.AutoPressScoreCard;
import com.putt2gether.putt.addScore.ScoreTable;
import com.putt2gether.putt.participantspack.AcceptParticipants;
import com.putt2gether.putt.score_card.NewGameScoreCard;
import com.putt2gether.utils.Utility;
import com.putt2gether.view.LatoTextView;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 08/08/2016.
 */
public class InviteEventDetail extends AppCompatActivity {

    private ImageView backBTN;
    private RelativeLayout scorerBTN;
    private RelativeLayout submitBTN;

    private String adminFound;
    private TextView golf_coursename;
    private TextView event_cretedBy;
    private TextView no_of_player;
    private TextView individualTeam;
    private TextView format;
    private RelativeLayout selectTeeM, selectTeeJ, selectTeeW;
    private TextView eventTime;
    private TextView noOfHole;
    private TextView eventType;
    private TextView hole;
    private RelativeLayout selectHoleLay;
    private TextView mText, jText, wText;
    private View view;
    private ImageView plusIcon;

    String reqstType = "0";

    String createby;
    String golfcourseName;
    String dateTime;
    String radioText;
    String teamText;
    String formateName;
    String holeText;
    String eventTypeText;
    String holeBackFront;
    String colorM;
    String colorJ;
    String colorW;
    private TextView eventDetailTitle;
    String eventID;
    LatoTextView inviteeventbottomtext;
    private String isAdmin;
    private RelativeLayout multiScreenLay;
    private TextView multiText;

    private TextView eventTvBTN;
    private RelativeLayout eventTypeLayout;
    private RelativeLayout teamLayout;
    private String submitBTNTxt;
    private String number_of_player;
    RelativeLayout parent;
    String requestToParticipate;
    String formatID;

    String isSpotType;

    private RelativeLayout closestBTN1;
    private RelativeLayout closestBTN2;
    private RelativeLayout closestBTN3;
    private RelativeLayout closestBTN4;

    private RelativeLayout longDriveBTN1;
    private RelativeLayout longDriveBTN2;
    private RelativeLayout longDriveBTN3;
    private RelativeLayout longDriveBTN4;

    private RelativeLayout straightBTN1;
    private RelativeLayout straightBTN2;
    private RelativeLayout straightBTN3;
    private RelativeLayout straightBTN4;

    private TextView closestText1, closestText2, closestText3, closestText4;
    private TextView longDrivetxt1, longDrivetxt2, longDrivetxt3, longDrivetxt4;
    private TextView straightDriveText1, straightDriveText2, straightDriveText3, straightDriveText4;

    ArrayList<String> closestArray = new ArrayList<String>();
    ArrayList<String> straightArray = new ArrayList<String>();
    ArrayList<String> longestArray = new ArrayList<String>();

    private LinearLayout spotPrizeLayout;

    RelativeLayout declineBTN;

    String banner_id;
    String banner_url;
    String banner_img;

    float dX;
    float dY;
    int lastAction;
    private String isPublic;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail_person);

        requestToParticipate = getIntent().getStringExtra("requestToParticipate");
        if (requestToParticipate != null) {
            reqstType = "1";
        } else {
            reqstType = "0";
        }


        declineBTN = (RelativeLayout)findViewById(R.id.declineBTN);
        declineBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    TextView yesBTN, noBTN;

                    final Dialog dialog = new Dialog(InviteEventDetail.this, android.R.style.Theme_Translucent_NoTitleBar);

                    dialog.setCanceledOnTouchOutside(true);
                    Window window = dialog.getWindow();
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    WindowManager.LayoutParams wlp = window.getAttributes();
                    wlp.gravity = Gravity.BOTTOM;
                    wlp.dimAmount = 0.7f;
                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    window.setAttributes(wlp);
                    // Include dialog.xml file
                    dialog.setContentView(R.layout.decline_popup);

                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    yesBTN = (TextView) dialog.findViewById(R.id.yes_decline);
                    noBTN = (TextView) dialog.findViewById(R.id.no_decline);
                    yesBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            declineInvite();
                            Toast.makeText(InviteEventDetail.this, "You have decline invitation", Toast.LENGTH_LONG).show();
                            dialog.cancel();

                        }
                    });
                    noBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });


                    dialog.show();

            }
        });

        plusIcon = (ImageView) findViewById(R.id.plus_icon_view);
        parent = (RelativeLayout) findViewById(R.id.parent_Edetail);
        eventID = getIntent().getStringExtra("eventID");
        eventTypeLayout = (RelativeLayout) findViewById(R.id.event_type_layout);
        teamLayout = (RelativeLayout) findViewById(R.id.create_individual_layout);

        multiScreenLay = (RelativeLayout) findViewById(R.id.ed_multi_screen_layout);
        multiText = (TextView) findViewById(R.id.ed_multi_screenTV);
        eventTvBTN = (TextView) findViewById(R.id.event_detail_text);

        ConnectionDetector connectionDetector = new ConnectionDetector(this);
        if (connectionDetector.isConnectingToInternet()) {
            getEventInvitationDetail();
            getBannerList();
        }


        submitBTN = (RelativeLayout) findViewById(R.id.person_SRTART_EVENT);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strBTn = eventTvBTN.getText().toString();

                if (strBTn.equalsIgnoreCase("Accept")) {
                    acceptInvite();
                } else if (strBTn.equalsIgnoreCase("Event Not Started")) {
                    Toast.makeText(getApplicationContext(), "Event Not Started", Toast.LENGTH_SHORT).show();
                } else if (strBTn.equalsIgnoreCase("Start Event")) {

                    alertPopup();


                } else if (strBTn.equalsIgnoreCase("Started Round")) {

                    Intent intent = new Intent(getApplicationContext(), AddScoreNew.class);
                    intent.putExtra("eventID", eventID);
                    startActivity(intent);

                } else if (strBTn.equalsIgnoreCase("Start Round")) {

                    if (banner_img!=null && banner_img.length()>10){
                        bannerFullMethod(banner_img,banner_url);
                    }else {

                        Intent intent = new Intent(getApplicationContext(), AddScoreNew.class);
                        intent.putExtra("eventID", eventID);
                        startActivity(intent);
                    }



                } else if (strBTn.equalsIgnoreCase("Resume Round")) {
                    Intent intent = new Intent(getApplicationContext(), AddScoreNew.class);
                    intent.putExtra("eventID", eventID);
                    startActivity(intent);

                } else if (strBTn.equalsIgnoreCase("REQUEST TO PARTICIPATE")) {
                    reqToParticipate();
                } else if (strBTn.equalsIgnoreCase("VIEW SCORE")) {

                    if (formatID != null) {
                        if (formatID.equalsIgnoreCase("10") || formatID.equalsIgnoreCase("12") || formatID.equalsIgnoreCase("13") || formatID.equalsIgnoreCase("14")) {
                            startActivity(new Intent(getApplicationContext(), NewGameScoreCard.class).putExtra("eventID", eventID).putExtra("from", "1"));
                        } else if (formatID.equalsIgnoreCase("11")) {
                            startActivity(new Intent(getApplicationContext(), AutoPressScoreCard.class).putExtra("eventID", eventID).putExtra("from", "1"));

                        } else {
                            startActivity(new Intent(getApplicationContext(), ScoreTable.class).putExtra("eventID", eventID).putExtra("isDelegate", "1").putExtra("playerID","noneed"));
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Format id not found", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


        plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusPopup();


            }
        });

        plusIcon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
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

        selectHoleLay = (RelativeLayout) findViewById(R.id.select_hole_layout);
        golf_coursename = (TextView) findViewById(R.id.person_golf_course_name);
        event_cretedBy = (TextView) findViewById(R.id.person_event_name);
        no_of_player = (TextView) findViewById(R.id.person_player_number);
        individualTeam = (TextView) findViewById(R.id.person_team_type);
        format = (TextView) findViewById(R.id.person_formate);
        selectTeeM = (RelativeLayout) findViewById(R.id.person_tee_m);
        selectTeeJ = (RelativeLayout) findViewById(R.id.person_tee_j);
        selectTeeW = (RelativeLayout) findViewById(R.id.person_tee_w);
        eventTime = (TextView) findViewById(R.id.date_time_editText);
        noOfHole = (TextView) findViewById(R.id.person_holes_number);
        eventType = (TextView) findViewById(R.id.person_event_type);
        hole = (TextView) findViewById(R.id.person_frntBck_hole);
        mText = (TextView) findViewById(R.id.mText_person);
        jText = (TextView) findViewById(R.id.jText_person);
        wText = (TextView) findViewById(R.id.wtext_person);
        eventDetailTitle = (TextView) findViewById(R.id.eventDetailTitle);


        backBTN = (ImageView) findViewById(R.id.back_person_event);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        spotPrizeLayout = (LinearLayout) findViewById(R.id.spot_prize_yes_lay);

        longDriveBTN1 = (RelativeLayout) findViewById(R.id.long_drive_btn1);
        longDriveBTN2 = (RelativeLayout) findViewById(R.id.long_drive_btn2);
        longDriveBTN3 = (RelativeLayout) findViewById(R.id.long_drive_btn3);
        longDriveBTN4 = (RelativeLayout) findViewById(R.id.long_drive_btn4);

        straightBTN1 = (RelativeLayout) findViewById(R.id.straight_drive_btn1);
        straightBTN2 = (RelativeLayout) findViewById(R.id.straight_drive_btn2);
        straightBTN3 = (RelativeLayout) findViewById(R.id.straight_drive_btn3);
        straightBTN4 = (RelativeLayout) findViewById(R.id.straight_drive_btn4);

        closestBTN1 = (RelativeLayout) findViewById(R.id.closest_pin_btn1);
        closestBTN2 = (RelativeLayout) findViewById(R.id.closest_pin_btn2);
        closestBTN3 = (RelativeLayout) findViewById(R.id.closest_pin_btn3);
        closestBTN4 = (RelativeLayout) findViewById(R.id.closest_pin_btn4);

        closestText1 = (TextView) findViewById(R.id.closest_pin_text1);
        longDrivetxt1 = (TextView) findViewById(R.id.long_drive_text1);
        straightDriveText1 = (TextView) findViewById(R.id.straight_drive_text1);

        closestText2 = (TextView) findViewById(R.id.closest_pin_text2);
        longDrivetxt2 = (TextView) findViewById(R.id.long_drive_text2);
        straightDriveText2 = (TextView) findViewById(R.id.straight_drive_text2);

        closestText3 = (TextView) findViewById(R.id.closest_pin_text3);
        longDrivetxt3 = (TextView) findViewById(R.id.long_drive_text3);
        straightDriveText3 = (TextView) findViewById(R.id.straight_drive_text3);

        closestText4 = (TextView) findViewById(R.id.closest_pin_text4);
        longDrivetxt4 = (TextView) findViewById(R.id.long_drive_text4);
        straightDriveText4 = (TextView) findViewById(R.id.straight_drive_text4);


    }

    @Override
    protected void onRestart() {

        getEventInvitationDetail();

        super.onRestart();
    }

    public void getEventInvitationDetail() {

        final ProgressDialog pDialog = new ProgressDialog(this, R.style.MyTheme);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;
        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);

        closestArray = new ArrayList<String>();
        straightArray = new ArrayList<String>();
        longestArray = new ArrayList<String>();

        String eventID = getIntent().getStringExtra("eventID");

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("event_id", eventID);
            jsonObject.putOpt("request_to_participate", reqstType);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.EVENT_INVITATION_DETAIL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getInvitationDetail(response);
                parent.setVisibility(View.VISIBLE);
                Log.e("GetEVENt DETAIL URL", "GetEVENt DETAIL URL" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "GetEVENt DETAIL URL = " + PUTTAPI.EVENT_INVITATION_DETAIL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getInvitationDetail(JSONObject response) {

        ArrayList<InviteDetailBean> list = new ArrayList<InviteDetailBean>();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    // JSONObject jsonObjectD = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        InviteDetailBean listBean = new InviteDetailBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        listBean.setGolf_course_name(jsonObject1.getString("golf_course_name"));
                        listBean.setEvent_name(jsonObject1.getString("event_name"));
                        listBean.setNo_of_player(jsonObject1.getString("no_of_player"));
                        listBean.setPlayers_in_game(jsonObject1.getString("players_in_game"));
                        listBean.setIsAdmin(jsonObject1.getString("is_admin"));
                        listBean.setFormat_name(jsonObject1.getString("format_name"));
                        listBean.setEvent_start_date_time(jsonObject1.getString("event_start_date_time"));
                        listBean.setIs_individual(jsonObject1.getString("is_individual"));
                        listBean.setTotal_hole_num(jsonObject1.getString("total_hole_num"));
                        listBean.setType(jsonObject1.getString("type"));
                        listBean.setIsSingleScreen(jsonObject1.getString("is_singlescreen"));
                        isSpotType = jsonObject1.getString("is_spot");

                        isPublic = jsonObject1.getString("is_public");

                        JSONArray jsClosArray = jsonObject1.getJSONArray("closest_pin");

                        for (int c = 0; c < jsClosArray.length(); c++) {
                            JSONObject jb1 = jsClosArray.getJSONObject(c);
                            String cl = jb1.getString("hole_number");
                            closestArray.add(cl);
                        }

                        JSONArray jsLongArray = jsonObject1.getJSONArray("long_drive");

                        for (int c = 0; c < jsLongArray.length(); c++) {
                            JSONObject jb1 = jsLongArray.getJSONObject(c);
                            String cl = jb1.getString("hole_number");
                            longestArray.add(cl);
                        }

                        JSONArray jsStraightArray = jsonObject1.getJSONArray("straight_drive");

                        for (int c = 0; c < jsStraightArray.length(); c++) {
                            JSONObject jb1 = jsStraightArray.getJSONObject(c);
                            String cl = jb1.getString("hole_number");
                            straightArray.add(cl);
                        }


                        formatID = jsonObject1.getString("format_id");

                        submitBTNTxt = jsonObject1.getString("start_round_status");

                        number_of_player = listBean.getPlayers_in_game();

                       /* if (number_of_player!=null && number_of_player.equalsIgnoreCase("4+")){
                            spotPrizeLayout1.setVisibility(View.VISIBLE);
                        }else {
                            spotPrizeLayout1.setVisibility(View.GONE);
                        }*/

                        Log.v("hhhjolijolj", submitBTNTxt);

                        if (submitBTNTxt != null) {

                            if (submitBTNTxt.equalsIgnoreCase("1")) {
                                eventTvBTN.setText("START EVENT");
                                declineBTN.setVisibility(View.GONE);
                            } else if (submitBTNTxt.equalsIgnoreCase("2")) {
                                eventTvBTN.setText("RESUME ROUND");
                                declineBTN.setVisibility(View.GONE);
                            } else if (submitBTNTxt.equalsIgnoreCase("3")) {
                                eventTvBTN.setText("EVENT NOT STARTED");
                                declineBTN.setVisibility(View.GONE);
                            } else if (submitBTNTxt.equalsIgnoreCase("4")) {
                                eventTvBTN.setText("STARTED ROUND");
                                declineBTN.setVisibility(View.GONE);
                            } else if (submitBTNTxt.equalsIgnoreCase("5")) {
                                eventTvBTN.setText("ACCEPT");
                                declineBTN.setVisibility(View.VISIBLE);
                            } else if (submitBTNTxt.equalsIgnoreCase("6")) {
                                eventTvBTN.setText("REQUEST TO PARTICIPATE");
                                declineBTN.setVisibility(View.GONE);
                            } else if (submitBTNTxt.equalsIgnoreCase("7")) {
                                eventTvBTN.setText("PARTICIPATION REQUEST PENDING");
                                declineBTN.setVisibility(View.GONE);
                            } else if (submitBTNTxt.equalsIgnoreCase("8")) {
                                eventTvBTN.setText("VIEW SCORE");
                                declineBTN.setVisibility(View.GONE);
                            } else if (submitBTNTxt.equalsIgnoreCase("9")) {
                                eventTvBTN.setText("START ROUND");
                                declineBTN.setVisibility(View.GONE);
                            }
                        }
                        adminFound = listBean.getIsAdmin();
                        String str = listBean.getTotal_hole_num();
                        if (str.equalsIgnoreCase("9")) {
                            listBean.setHole(jsonObject1.getString("holes"));
                        }

                        JSONObject jsonObject2 = jsonObject1.getJSONObject("tee_id");

                        listBean.setMenColor(jsonObject2.getString("Men"));
                        listBean.setLadiesColor(jsonObject2.getString("Ladies"));
                        listBean.setJuniorColor(jsonObject2.getString("Junior"));
                        listBean.setMessage(jsonObject.getString("msg"));


                        String isSingle = listBean.getIsSingleScreen();
                        if (isSingle.isEmpty() || isSingle==null) {
                            multiScreenLay.setVisibility(View.GONE);
                        } else if (isSingle.equalsIgnoreCase("1")) {

                            multiText.setText("YES");
                        } else {

                            multiText.setText("NO");
                        }

                        String pl = listBean.getPlayers_in_game();
                        String frmt = listBean.getFormateID();
                        if (pl!=null && (!pl.equalsIgnoreCase("4+") && !pl.equalsIgnoreCase("1"))){

                            if (formatID.equalsIgnoreCase("10") || formatID.equalsIgnoreCase("12") || formatID.equalsIgnoreCase("13") || formatID.equalsIgnoreCase("14")) {

                                multiScreenLay.setVisibility(View.GONE);
                            }else {
                                multiScreenLay.setVisibility(View.VISIBLE);
                            }

                        }else {
                            multiScreenLay.setVisibility(View.GONE);
                        }


                        isAdmin = listBean.getIsAdmin();


                        createby = listBean.getEvent_name();
                        golfcourseName = listBean.getGolf_course_name();
                        dateTime = listBean.getEvent_start_date_time();
                        radioText = listBean.getNo_of_player();

                        String teamIndividual = listBean.getIs_individual();
                        if (teamIndividual.equalsIgnoreCase("Individual")) {
                            teamText = listBean.getIs_individual();
                            teamLayout.setVisibility(View.GONE);
                        } else {
                            teamText = "TEAM";
                            teamLayout.setVisibility(View.VISIBLE);
                        }

                        formateName = listBean.getFormat_name();
                        holeText = listBean.getTotal_hole_num();
                        eventTypeText = listBean.getType();
                        holeBackFront = listBean.getHole();
                        colorM = listBean.getMenColor();
                        colorJ = listBean.getJuniorColor();
                        colorW = listBean.getLadiesColor();

                        if (holeText != null) {
                            if (holeText.equalsIgnoreCase("18")) {
                                selectHoleLay.setVisibility(View.GONE);
                            } else {
                                selectHoleLay.setVisibility(View.VISIBLE);
                            }
                        }

                        if (golfcourseName != null) {
                            golf_coursename.setText(golfcourseName.toUpperCase());
                        }
                        event_cretedBy.setText(createby.toUpperCase());
                        eventDetailTitle.setText(createby.toUpperCase());
                        //no_of_player.setText();
                        individualTeam.setText(teamText.toUpperCase());
                        format.setText(formateName.toUpperCase());
                        no_of_player.setText(radioText.toUpperCase());


                        if (isSpotType.equalsIgnoreCase("1")) {
                           // eventTypeLayout.setVisibility(View.VISIBLE);
                          /*  spotPrizeText.setText("YES");
                            spotPrizeLayout1.setVisibility(View.VISIBLE);*/
                            spotPrizeLayout.setVisibility(View.VISIBLE);

                            for (int c = 0; c < closestArray.size(); c++) {
                                if (c == 0) {
                                    closestBTN1.setVisibility(View.VISIBLE);
                                    closestText1.setText(closestArray.get(0));
                                } else if (c == 1) {
                                    closestBTN2.setVisibility(View.VISIBLE);
                                    closestText2.setText(closestArray.get(1));
                                } else if (c == 2) {
                                    closestBTN3.setVisibility(View.VISIBLE);
                                    closestText3.setText(closestArray.get(2));
                                } else if (c == 3) {
                                    closestBTN4.setVisibility(View.VISIBLE);
                                    closestText4.setText(closestArray.get(3));
                                }
                            }

                            for (int c = 0; c < longestArray.size(); c++) {
                                if (c == 0) {
                                    longDriveBTN1.setVisibility(View.VISIBLE);
                                    longDrivetxt1.setText(longestArray.get(0));
                                } else if (c == 1) {
                                    longDriveBTN2.setVisibility(View.VISIBLE);
                                    longDrivetxt2.setText(longestArray.get(1));
                                } else if (c == 2) {
                                    longDriveBTN3.setVisibility(View.VISIBLE);
                                    longDrivetxt3.setText(longestArray.get(2));
                                } else if (c == 3) {
                                    longDriveBTN4.setVisibility(View.VISIBLE);
                                    longDrivetxt4.setText(longestArray.get(3));
                                }
                            }

                            for (int c = 0; c < straightArray.size(); c++) {
                                if (c == 0) {
                                    straightBTN1.setVisibility(View.VISIBLE);
                                    straightDriveText1.setText(straightArray.get(0));
                                } else if (c == 1) {
                                    straightBTN2.setVisibility(View.VISIBLE);
                                    straightDriveText2.setText(straightArray.get(1));
                                } else if (c == 2) {
                                    straightBTN3.setVisibility(View.VISIBLE);
                                    straightDriveText3.setText(straightArray.get(2));
                                } else if (c == 3) {
                                    straightBTN4.setVisibility(View.VISIBLE);
                                    straightDriveText4.setText(straightArray.get(3));
                                }
                            }

                        } else {
                          //  eventTypeLayout.setVisibility(View.GONE);
                           /* spotPrizeLayout1.setVisibility(View.GONE);*/
                            spotPrizeLayout.setVisibility(View.GONE);
                        }

                        eventTime.setText(dateTime);
                        if (holeText != null) {
                            noOfHole.setText(holeText);
                        }
                        if (eventTypeText != null) {
                            eventType.setText(eventTypeText.toUpperCase());
                        }
                        if (holeBackFront != null) {
                            hole.setText(holeBackFront.toUpperCase());
                        }


                        if (colorM.equalsIgnoreCase("Red")) {
                            selectTeeM.setBackgroundResource(R.drawable.red_tee);

                        } else if (colorM.equalsIgnoreCase("Green")) {
                            selectTeeM.setBackgroundResource(R.drawable.green_tee);

                        } else if (colorM.equalsIgnoreCase("Yellow")) {
                            selectTeeM.setBackgroundResource(R.drawable.yello_tee);
                            mText.setTextColor(Color.BLACK);

                        } else if (colorM.equalsIgnoreCase("Black")) {
                            selectTeeM.setBackgroundResource(R.drawable.black_tee);

                        } else if (colorM.equalsIgnoreCase("White")) {
                            selectTeeM.setBackgroundResource(R.drawable.white_tee);
                            mText.setTextColor(Color.BLACK);

                        } else if (colorM.equalsIgnoreCase("Blue")) {
                            selectTeeM.setBackgroundResource(R.drawable.blue_tee);

                        } else if (colorM.equalsIgnoreCase("Gold")) {
                            selectTeeM.setBackgroundResource(R.drawable.gold_tee);
                            mText.setTextColor(Color.BLACK);

                        } else if (colorM.equalsIgnoreCase("Silver")) {
                            selectTeeM.setBackgroundResource(R.drawable.silver_tee);
                            mText.setTextColor(Color.BLACK);

                        }

                        if (colorJ.equalsIgnoreCase("Red")) {
                            selectTeeJ.setBackgroundResource(R.drawable.red_tee);

                        } else if (colorJ.equalsIgnoreCase("Green")) {
                            selectTeeJ.setBackgroundResource(R.drawable.green_tee);

                        } else if (colorJ.equalsIgnoreCase("Yellow")) {
                            selectTeeJ.setBackgroundResource(R.drawable.yello_tee);
                            jText.setTextColor(Color.BLACK);

                        } else if (colorJ.equalsIgnoreCase("Black")) {
                            selectTeeJ.setBackgroundResource(R.drawable.black_tee);

                        } else if (colorJ.equalsIgnoreCase("White")) {
                            selectTeeJ.setBackgroundResource(R.drawable.white_tee);
                            jText.setTextColor(Color.BLACK);

                        } else if (colorJ.equalsIgnoreCase("Blue")) {
                            selectTeeJ.setBackgroundResource(R.drawable.blue_tee);

                        } else if (colorJ.equalsIgnoreCase("Gold")) {
                            selectTeeJ.setBackgroundResource(R.drawable.gold_tee);
                            jText.setTextColor(Color.BLACK);

                        } else if (colorJ.equalsIgnoreCase("Silver")) {
                            selectTeeJ.setBackgroundResource(R.drawable.silver_tee);
                            jText.setTextColor(Color.BLACK);

                        }

                        if (colorW.equalsIgnoreCase("Red")) {
                            selectTeeW.setBackgroundResource(R.drawable.red_tee);

                        } else if (colorW.equalsIgnoreCase("Green")) {
                            selectTeeW.setBackgroundResource(R.drawable.green_tee);

                        } else if (colorW.equalsIgnoreCase("Yellow")) {
                            selectTeeW.setBackgroundResource(R.drawable.yello_tee);
                            wText.setTextColor(Color.BLACK);

                        } else if (colorW.equalsIgnoreCase("Black")) {
                            selectTeeW.setBackgroundResource(R.drawable.black_tee);

                        } else if (colorW.equalsIgnoreCase("White")) {
                            selectTeeW.setBackgroundResource(R.drawable.white_tee);
                            wText.setTextColor(Color.BLACK);

                        } else if (colorW.equalsIgnoreCase("Blue")) {
                            selectTeeW.setBackgroundResource(R.drawable.blue_tee);

                        } else if (colorW.equalsIgnoreCase("Gold")) {
                            selectTeeW.setBackgroundResource(R.drawable.gold_tee);
                            wText.setTextColor(Color.BLACK);

                        } else if (colorW.equalsIgnoreCase("Silver")) {
                            selectTeeW.setBackgroundResource(R.drawable.silver_tee);
                            wText.setTextColor(Color.BLACK);

                        }
                        //  list.add(listBean);

                      /*  if (number_of_player!=null && number_of_player.equalsIgnoreCase("4+")){
                            spotPrizeLayout1.setVisibility(View.VISIBLE);
                        }else {
                            spotPrizeLayout1.setVisibility(View.GONE);
                        }
*/
                    }
                } else {
                    String errorMessage = jsonObject.getString("msg");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onBackPressed() {

        Intent intent  =  new Intent(getApplicationContext(),HomeActivity.class);
        intent.putExtra("fromEventPreview","1");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    public void startevent() {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();

            jsonObject.putOpt("event_id", eventID);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.STARTEVENTAPI, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                starttheevent(response);
                Log.e("STARTEVENTAPI URL", "STARTEVENTAPI URL" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "STARTEVENTAPI URL = " + PUTTAPI.STARTEVENTAPI);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


    }


    public void starttheevent(JSONObject response) {

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    Intent intent = new Intent(getApplicationContext(), AddScoreNew.class);
                    intent.putExtra("eventID", eventID);
                    startActivity(intent);

                } else {
                  //  Toast.makeText(getApplicationContext(), "Event already started.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), AddScoreNew.class);
                    intent.putExtra("eventID", eventID);
                    startActivity(intent);
                }
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }
    }


    public void acceptInvite() {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("event_id", eventID);
            jsonObject.putOpt("status", "1");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ACCEPT_REJECT_INVITE_API, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getAcceptResponse(response);
                Log.e("ACCEPT REJECT Fragment", "CCEPT REJECT Fragment" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getAcceptResponse(JSONObject response) {

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    String message = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    ConnectionDetector connectionDetector = new ConnectionDetector(this);
                    if (connectionDetector.isConnectingToInternet()) {
                        getEventInvitationDetail();
                    }

                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void reqToParticipate() {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);

        try {
            jsonObject = new JSONObject();

            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("event_id", eventID);
            jsonObject.putOpt("type", "1");
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.REQUEST_TO_PARTICIPATE_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                reqparticipateResponse(response);
                Log.e("REQUEST TO PARTICIPATE", "REQUEST TO PARTICIPATE" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "REQUEST TO PARTICIPATE URL = " + PUTTAPI.REQUEST_TO_PARTICIPATE_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


    }

    public void reqparticipateResponse(JSONObject response) {

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    String msg = jsonObject.getString("msg");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    String msg = jsonObject.getString("msg");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    finish();
                }
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }
    }



    public void declineInvite() {


        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        final String user_ID = pref.getString("userId", null);
        String eventId = eventID;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("event_id",eventId);
            jsonObject.putOpt("status","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ACCEPT_REJECT_INVITE_API, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getDeclineResponse(response);

                Log.e("ACCEPT REJECT Fragment", "CCEPT REJECT Fragment" + response.toString());
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

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getDeclineResponse(JSONObject response){

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){

                    String message = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    finish();

                }else{
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void getBannerList() {


        JSONObject jsonObject = null;


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("type", "6");
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("event_id",eventID);


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

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void bannerFullMethod(String image, final String url){
        final Dialog dialogB = new Dialog(InviteEventDetail.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialogB.setCanceledOnTouchOutside(true);
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

        ImageView close = (ImageView)dialogB.findViewById(R.id.banner_full_close);
        ImageView bannerImage = (ImageView)dialogB.findViewById(R.id.banner_image_full);
        if (image!=null){
            Picasso.with(this).load(image).into(bannerImage);
        }

        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (url!=null && url.length()>10) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), " Please install a web browser",  Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), " URL not found.",  Toast.LENGTH_LONG).show();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogB.cancel();
                Intent intent = new Intent(getApplicationContext(), AddScoreNew.class);
                intent.putExtra("eventID", eventID);
                startActivity(intent);
            }
        });


        dialogB.show();
    }

    public void alertPopup(){

        TextView yes, no;

      //  final Dialog dialog = new Dialog(InviteEventDetail.this, android.R.style.Theme_Translucent_NoTitleBar);
        final Dialog dialog = new Dialog(InviteEventDetail.this);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(wlp);
        // Include dialog.xml file
        dialog.setContentView(R.layout.decline_popup);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView)dialog.findViewById(R.id.e1);

        text.setText("Last chance to edit handicap to this event");

        yes = (TextView) dialog.findViewById(R.id.yes_decline);
        yes.setText("Edit Handicap");
        no = (TextView) dialog.findViewById(R.id.no_decline);
        no.setText("Start Event");
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                startevent();

            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //plusPopup();
                dialog.cancel();
                Intent intent = new Intent(getApplicationContext(), ParticipantViewAcitivity.class);
                intent.putExtra("eventID", eventID);
                intent.putExtra("is_admin", isAdmin);
                intent.putExtra("isGameStarted", submitBTNTxt);
                intent.putExtra("noOfPlayer", number_of_player);
                startActivity(intent);
            }
        });


        dialog.show();
    }


    public void plusPopup(){

        final Dialog dialog = new Dialog(InviteEventDetail.this, android.R.style.Theme_Translucent_NoTitleBar);
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
        dialog.setContentView(R.layout.pop_up_layout_person);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout viewParticipant = (LinearLayout) dialog.findViewById(R.id.viewParicipint);
        LinearLayout viewRequest = (LinearLayout) dialog.findViewById(R.id.viewRequest);
        LinearLayout editEvent = (LinearLayout) dialog.findViewById(R.id.editEvent);
        LinearLayout selectScorer = (LinearLayout) dialog.findViewById(R.id.selectScorer);

        if (adminFound.equalsIgnoreCase("0")) {
            viewRequest.setVisibility(View.GONE);
            editEvent.setVisibility(View.GONE);
            selectScorer.setVisibility(View.GONE);
        } else {

            if (submitBTNTxt.equalsIgnoreCase("1")) {
                viewRequest.setVisibility(View.VISIBLE);
                editEvent.setVisibility(View.VISIBLE);
                selectScorer.setVisibility(View.GONE);

            } else {

                viewRequest.setVisibility(View.VISIBLE);
                editEvent.setVisibility(View.GONE);
                selectScorer.setVisibility(View.GONE);
            }
        }

        if (isPublic.equalsIgnoreCase("1")){
            viewRequest.setVisibility(View.VISIBLE);
        }else {
            viewRequest.setVisibility(View.GONE);
        }

        editEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                Intent intent = new Intent(getApplicationContext(), EditEventActivity.class);
                intent.putExtra("eventID", eventID);
                startActivity(intent);

            }
        });

        selectScorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       /* Intent intent = new Intent(getApplicationContext(),ScorerActivity.class);
                        startActivity(intent)*/
                ;

            }
        });

        viewParticipant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();

                Intent intent = new Intent(getApplicationContext(), ParticipantViewAcitivity.class);
                intent.putExtra("eventID", eventID);
                intent.putExtra("is_admin", isAdmin);
                intent.putExtra("isGameStarted", submitBTNTxt);
                intent.putExtra("noOfPlayer", number_of_player);
                startActivity(intent);
            }
        });

        viewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();

                Intent intent = new Intent(getApplicationContext(), AcceptParticipants.class);
                intent.putExtra("eventID", eventID);
                intent.putExtra("is_admin", isAdmin);
                startActivity(intent);

            }
        });

        ImageView closeBTN = (ImageView) dialog.findViewById(R.id.close_dialog);
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


        dialog.show();
    }

}