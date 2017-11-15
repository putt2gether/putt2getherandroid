package com.putt2gether.putt;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
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
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.bean.DataBean;
import com.putt2gether.bean.EventPostBean;
import com.putt2gether.bean.FormatBean;
import com.putt2gether.bean.GolfCourseBean;
import com.putt2gether.bean.HoleBeanFirst;
import com.putt2gether.bean.HoleBeanSecond;
import com.putt2gether.bean.InviteDetailBean;
import com.putt2gether.bean.TeamIndividualBean;
import com.putt2gether.bean.TeeBean;
import com.putt2gether.bean.TeeBeanJ;
import com.putt2gether.bean.TeeBeanW;
import com.putt2gether.custome.TimePickerDialog;
import com.putt2gether.data.Type;
import com.putt2gether.listener.OnDateSetListener;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.GPSTracker;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.parser.PuttParser;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 23/08/2016.
 */
public class EditEventActivity  extends AppCompatActivity implements OnDateSetListener {


    private String mID,lID,jID;
    String userID ;
    private TextView teamIndivi;
    private TextView playerNo_edit;
    private String spotRadioTxt = "No";
    private RelativeLayout selectGolfCourse;
    private EditText eventName;
    private RelativeLayout team_individual_spinner;

    private RelativeLayout date_time_lay;
    private TextView dateTime_Text;
    private RelativeLayout hole_9_layout,hole_18_layout;
    private RelativeLayout public_lay,private_lay;
    private RelativeLayout fron9_lay,back9_lay;
    private RelativeLayout invite_player_layout;
    private ImageView backButton;
    private Toolbar toolbarCreateEvent;
    private TextView selectedGolfCourseTEXT;
    private String golf_couse_id;
    private PuttParser parser;
    private ArrayList<FormatBean> formatList;
    private ArrayList<TeeBean> teeList;

    private ArrayList<TeamIndividualBean> teamIndividual;
    private String formatArr[];
    private String teeArrM[];
    private String teeArrW[];
    private String teeArrJ[];
    private String teamIndividaulArr[];
    private Spinner formatSpinner;
    private String format_id;
    private String option;

    private String formate_name;

    private String ind_team_id = "2";

    private String numberOfPlayer;
    private String holetxt;
    private String eventTypetxt;
    private String holeBackFront;

    private TextView eventType;

    private RelativeLayout selectHolesLayout;
    private RadioButton yesRadio;
    private RadioButton noRadio;
    private LinearLayout spotPrizeLayout;

    private TextView closestText1,closestText2,closestText3,closestText4;
    private TextView longDrivetxt1,longDrivetxt2,longDrivetxt3,longDrivetxt4;
    private TextView straightDriveText1,straightDriveText2,straightDriveText3,straightDriveText4;

    private RelativeLayout mTEE_radio;
    private RelativeLayout wTEE_radio;
    private RelativeLayout jTEE_radio;
    private String mColorName=null,wColorName=null,jColorName=null;

    private TextView hole1,hole2,hole3,hole4,hole5,hole6,hole7,hole8,hole9,
            hole10,hole11,hole12,hole13,hole14,hole15,hole16,hole17,hole18,hole19,hole20;

    private TextView m1,m2,m3,m4,m5,m6,m7,m8;
    private TextView w1,w2,w3,w4,w5,w6,w7,w8;
    private TextView j1,j2,j3,j4,j5,j6,j7,j8;
    private TextView doneBTN_Tee;
    private RelativeLayout parentTee;

    private TextView[] holeArray = {hole1,hole2,hole3,hole4,hole5,hole6,hole7,hole8,hole9,
            hole10,hole11,hole12,hole13,hole14,hole15,hole16,hole17,hole18,hole19,hole20};
    private TextView[] holeArray2 = {hole1,hole2,hole3,hole4,hole5,hole6,hole7,hole8,hole9,
            hole10,hole11,hole12,hole13,hole14,hole15,hole16,hole17,hole18,hole19,hole20};
    private TextView[] mArray = {m1,m2,m3,m4,m5,m6,m7,m8};
    private TextView[] jArray = {j1,j2,j3,j4,j5,j6,j7,j8};
    private TextView[] wArray = {w1,w2,w3,w4,w5,w6,w7,w8};
    private LinearLayout holeParent;

    String isSpot = "0";


    private RelativeLayout saveBTN;
    private GPSTracker gpsTracker;
    private double latitude;
    private double longitude;
    List<GolfCourseBean> list1;
    GolfCourseBean typeBean;
    private EditText createdEventName;
    private String teamText = "Individual";
    private String radioText = "1";
    private SharedPreferences mSharedPreferences;

    private Constant constant;
    Typeface Lato_Regular;
    private ImageView backBTN;

    private TextView mText,wText,jText;
    private int year;
    private int month;
    private int day;
    private String createby;
    private ScrollView scroll;



    static final int DATE_DIALOG_ID = 1;

    TimePickerDialog mDialogMonthDayHourMinute;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MMM-dd HH:mm");
    private TextView inviteTEXT;
    private ImageView iconINFO;

    private String formatId;
    private TextView noOfHole;

    private TextView hole;

    String editcreateby ;
    String editgolfcourseName ;
    String editdateTime;
    String editradioText ;
    String editteamText;
    String editformateName ;
    String edit_golf_course_id;
    String editholeText ;
    String editeventTypeText ;
    String editholeBackFront ;
    String editcolorM;
    String editcolorJ ;
    String editcolorW ;
    String golfCourseName;
    private SharedPreferences pref;
    private String noOFPlayer;

    String strH1;
    String strH2;
    String strH3;
    String strH4;

    String strL1;
    String strL2;
    String strL3;
    String strL4;

    String strS1;
    String strS2;
    String strS3;
    String strS4;

    TextView previewDate;

    int holeConter=0;

    int hole1_flag=0,hole2_flag=0,hole3_flag=0,hole4_flag=0,hole5_flag=0,hole6_flag=0,hole7_flag=0,
            hole8_flag=0,hole9_flag=0,hole10_flag=0,hole11_flag=0,hole12_flag=0,hole13_flag=0,hole14_flag=0,
            hole15_flag=0,hole16_flag=0,hole17_flag=0,hole18_flag=0,hole19_flag=0,hole20_flag=0;

    int totalHole=4;


    int holeConter_l=0;

    int hole1_flag_l=0,hole2_flag_l=0,hole3_flag_l=0,hole4_flag_l=0,hole5_flag_l=0,hole6_flag_l=0,hole7_flag_l=0,
            hole8_flag_l=0,hole9_flag_l=0,hole10_flag_l=0,hole11_flag_l=0,hole12_flag_l=0,hole13_flag_l=0,hole14_flag_l=0,
            hole15_flag_l=0,hole16_flag_l=0,hole17_flag_l=0,hole18_flag_l=0,hole19_flag_l=0,hole20_flag_l=0;

    int holeConter_s=0;

    int hole1_flag_s=0,hole2_flag_s=0,hole3_flag_s=0,hole4_flag_s=0,hole5_flag_s=0,hole6_flag_s=0,hole7_flag_s=0,
            hole8_flag_s=0,hole9_flag_s=0,hole10_flag_s=0,hole11_flag_s=0,hole12_flag_s=0,hole13_flag_s=0,hole14_flag_s=0,
            hole15_flag_s=0,hole16_flag_s=0,hole17_flag_s=0,hole18_flag_s=0,hole19_flag_s=0,hole20_flag_s=0;

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
    String public_private;
    private RelativeLayout spotPrizeLay;

    private RelativeLayout singleScreenLayout;
    RadioGroup singleGroup;
    RadioButton yesSingle;
    RadioButton noSingle;
    String singleType;
    String is_singlescreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_event_activity);

        spotPrizeLay = (RelativeLayout)findViewById(R.id.spot_prize_layout);
        singleScreenLayout = (RelativeLayout)findViewById(R.id.single_screen_layout);
        yesSingle = (RadioButton)findViewById(R.id.radioSingle_yes);
        noSingle = (RadioButton)findViewById(R.id.radioSingle_no);

        yesSingle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                noSingle.setTextColor(Color.WHITE);
                yesSingle.setTextColor(Color.BLACK);

                is_singlescreen="2";

            }
        });


        noSingle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                noSingle.setTextColor(Color.BLACK);
                yesSingle.setTextColor(Color.WHITE);
                is_singlescreen="1";
            }
        });


        SharedPreferences createSharedPreferences = this.getSharedPreferences("craete_event", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorCreate = createSharedPreferences.edit();
        editorCreate.clear();
        editorCreate.commit();

        pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        userID = pref.getString("userId",null);

        noOfHole = (TextView)findViewById(R.id.edit_holes_number);

        hole = (TextView)findViewById(R.id.edit_frntBck_hole);
        teamIndivi = (TextView)findViewById(R.id.edit_team_type);
        inviteTEXT = (TextView)findViewById(R.id.invite_Text);

        scroll = (ScrollView)findViewById(R.id.scroll_view_create);
        mText = (TextView)findViewById(R.id.m_txt);
        jText = (TextView)findViewById(R.id.j_txt);
        wText = (TextView)findViewById(R.id.w_txt);
        mTEE_radio = (RelativeLayout)findViewById(R.id.tee_m);
        wTEE_radio = (RelativeLayout)findViewById(R.id.tee_w);
        jTEE_radio = (RelativeLayout)findViewById(R.id.tee_j);
        createdEventName = (EditText)findViewById(R.id.edit_event_userName);
        createdEventName.setTypeface(Lato_Regular);
        backBTN = (ImageView)findViewById(R.id.edit_back_create_event);

        team_individual_spinner = (RelativeLayout) findViewById(R.id.create_individual_layout);
        selectGolfCourse = (RelativeLayout)findViewById(R.id.edit_select_golf_course);
        selectedGolfCourseTEXT = (TextView)findViewById(R.id.edit_select_golf_course_tv);
        formatSpinner = (Spinner)findViewById(R.id.edit_strokeplay_spinner);

        selectHolesLayout = (RelativeLayout)findViewById(R.id.select_hole_layout);
        spotPrizeLayout = (LinearLayout)findViewById(R.id.spot_prize_yes_lay);
        yesRadio = (RadioButton)findViewById(R.id.radioButton_yes);
        noRadio = (RadioButton)findViewById(R.id.radioButton_no);

        eventType = (TextView) findViewById(R.id.edit_event_type_text);

        yesRadio.setTypeface(Lato_Regular);
        noRadio.setTypeface(Lato_Regular);


        ConnectionDetector con = new ConnectionDetector(this);
        if (con.isConnectingToInternet()) {
            getEventInvitationListDetail();
        }else {
            Toast.makeText(getApplicationContext(),"No internet connections",Toast.LENGTH_SHORT).show();
        }

        saveBTN = (RelativeLayout)findViewById(R.id.edit_invite_from_create);


        if (radioText=="1"){
            inviteTEXT.setText("NEXT");
        }


        mDialogMonthDayHourMinute = new TimePickerDialog.Builder().setType(Type.MONTH_DAY_HOUR_MIN)
                .setCallBack(EditEventActivity.this)
                .build();

        final Calendar calendar = Calendar.getInstance();
        year  = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day   = calendar.get(Calendar.DAY_OF_MONTH);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        constant = new Constant();
        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);

        gpsTracker = new GPSTracker(this);
        if (gpsTracker.canGetLocation()) {

            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();

        } else {

            gpsTracker.showSettingsAlert();
        }

        mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        parser = new PuttParser();

        // getGolfCourse();

        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId",null);
        final String accessToken = pref.getString("authorization_key",null);
        final String eventCreatedName = pref.getString("displayName",null);
        String uparCaseName  = eventCreatedName.toUpperCase();




        createdEventName.setTextColor(getResources().getColor(R.color.action));


        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editEventTask();
            }
        });




        mTEE_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                teeMethod();

            }

        });

        wTEE_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                // Create custom dialog object
                teeMethod();
            }

        });

        jTEE_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                // Create custom dialog object
                teeMethod();
            }

        });


        Calendar c2 = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd HH:mm");
        String formattedDate = df.format(c2.getTime());
        dateTime_Text = (TextView)findViewById(R.id.date_time_editText);

        dateTime_Text.setText(formattedDate);
        //showDateAndTimePicker();

        dateTime_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //showDateAndTimePicker();

                dateTimeDialog();

              //  mDialogMonthDayHourMinute.show(getSupportFragmentManager(), "month_day_hour_minute");
            }
        });




        formatSpinner.getBackground().setColorFilter(getResources().getColor(R.color.white_color), PorterDuff.Mode.SRC_ATOP);

        playerNo_edit = (TextView)findViewById(R.id.edit_player_number);

       /* if (playerNo_edit.getText().toString().equalsIgnoreCase("1")){

            teamText = "Individual";
            ind_team_id  = "2";
            radioText =  "1";
            team_individual_spinner.setVisibility(View.GONE);
            getFormatList();
        }else if (playerNo_edit.getText().toString().equalsIgnoreCase("2")){

            teamText = "Individual";
            ind_team_id  = "2";
            radioText =  "2";
            team_individual_spinner.setVisibility(View.GONE);
            getFormatList();
        }else if (playerNo_edit.getText().toString().equalsIgnoreCase("3")){

            teamText = "Individual";
            ind_team_id  = "2";
            radioText =  "3";
            team_individual_spinner.setVisibility(View.GONE);
            getFormatList();
        }else if (playerNo_edit.getText().toString().equalsIgnoreCase("4")){

            if (teamIndivi.getText().toString().equalsIgnoreCase("team")) {
                teamText = "Team";
                ind_team_id  = "1";
                radioText = "4";
                team_individual_spinner.setVisibility(View.GONE);
                getFormatList();
            }else {
                teamText = "Individual";
                ind_team_id = "2";
                radioText = "4";
                team_individual_spinner.setVisibility(View.GONE);
                getFormatList();
            }
        }else if (playerNo_edit.getText().toString().equalsIgnoreCase("4+")){

            teamText = "Individual";
            ind_team_id  = "2";
            radioText =  "4+";
            team_individual_spinner.setVisibility(View.GONE);
            getFormatList();
        }*/

        if (noOfHole.getText().toString().equalsIgnoreCase("9")){
            selectHolesLayout.setVisibility(View.VISIBLE);
            holetxt = "9";
        }else if (noOfHole.getText().toString().equalsIgnoreCase("18")){
            selectHolesLayout.setVisibility(View.GONE);

            holetxt = "18";
        }



        yesRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yesRadio.setTextColor(Color.WHITE);
                    noRadio.setTextColor(Color.BLACK);
                    spotPrizeLayout.setVisibility(View.VISIBLE);
                    spotRadioTxt = yesRadio.getText().toString();
                    isSpot ="1";

                }
            }
        });

        noRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yesRadio.setTextColor(Color.BLACK);
                    noRadio.setTextColor(Color.WHITE);
                    spotPrizeLayout.setVisibility(View.GONE);
                    spotRadioTxt = noRadio.getText().toString();
                    isSpot ="0";

                }
            }
        });

        // getFormatList();






        selectGolfCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventID = getIntent().getStringExtra("eventID");
                Intent intent = new Intent(getApplicationContext(),SelectGolfCourse.class);
                SharedPreferences pref1 = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor1 = pref1.edit();
                editor1.putString("resultFor","editEvent");
                editor1.putString("eventid",eventID);

                editor1.commit();
                startActivity(intent);
                finish();
            }
        });




        longDriveBTN1 = (RelativeLayout)findViewById(R.id.long_drive_btn1);
        longDriveBTN2 = (RelativeLayout)findViewById(R.id.long_drive_btn2);
        longDriveBTN3 = (RelativeLayout)findViewById(R.id.long_drive_btn3);
        longDriveBTN4 = (RelativeLayout)findViewById(R.id.long_drive_btn4);

        straightBTN1 = (RelativeLayout)findViewById(R.id.straight_drive_btn1);
        straightBTN2 = (RelativeLayout)findViewById(R.id.straight_drive_btn2);
        straightBTN3 = (RelativeLayout)findViewById(R.id.straight_drive_btn3);
        straightBTN4 = (RelativeLayout)findViewById(R.id.straight_drive_btn4);

        straightBTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strStraight = "stringStraight";

                stokeMethod2(strStraight);
            }
        });

        straightBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strStraight = "stringStraight";
                stokeMethod2(strStraight);
            }
        });

        straightBTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strStraight = "stringStraight";
                stokeMethod2(strStraight);
            }
        });

        straightBTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strStraight = "stringStraight";
                stokeMethod2(strStraight);
            }
        });

        longDriveBTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strLongDrive = "strLongDrive";
                stokeMethod1(strLongDrive);
            }
        });

        longDriveBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLongDrive = "strLongDrive";
                stokeMethod1(strLongDrive);
            }
        });

        longDriveBTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLongDrive = "strLongDrive";
                stokeMethod1(strLongDrive);
            }
        });

        longDriveBTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLongDrive = "strLongDrive";
                stokeMethod1(strLongDrive);
            }
        });


        closestBTN1 = (RelativeLayout)findViewById(R.id.closest_pin_btn1);
        closestBTN2 = (RelativeLayout)findViewById(R.id.closest_pin_btn2);
        closestBTN3 = (RelativeLayout)findViewById(R.id.closest_pin_btn3);
        closestBTN4 = (RelativeLayout)findViewById(R.id.closest_pin_btn4);

        closestText1 = (TextView)findViewById(R.id.closest_pin_text1);
        longDrivetxt1 = (TextView)findViewById(R.id.long_drive_text1);
        straightDriveText1 = (TextView)findViewById(R.id.straight_drive_text1);

        closestText2 = (TextView)findViewById(R.id.closest_pin_text2);
        longDrivetxt2 = (TextView)findViewById(R.id.long_drive_text2);
        straightDriveText2 = (TextView)findViewById(R.id.straight_drive_text2);

        closestText3 = (TextView)findViewById(R.id.closest_pin_text3);
        longDrivetxt3 = (TextView)findViewById(R.id.long_drive_text3);
        straightDriveText3 = (TextView)findViewById(R.id.straight_drive_text3);

        closestText4 = (TextView)findViewById(R.id.closest_pin_text4);
        longDrivetxt4 = (TextView)findViewById(R.id.long_drive_text4);
        straightDriveText4 = (TextView)findViewById(R.id.straight_drive_text4);

        closestBTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String str = "1";
                stokeMethod(str);
            }

        });
        closestBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String str = "2";
                stokeMethod(str);
            }
        });

        closestBTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String str = "3";
                stokeMethod(str);
            }
        });

        closestBTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String str = "4";
                stokeMethod(str);
            }
        });

    }



    private void getTEEList(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;

        jsonObject = new JSONObject();

        try {
            jsonObject.putOpt("golfcourseid",golf_couse_id);
            jsonObject.putOpt("version","2");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.TEE_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Tee List", "create Account = OnResponse= " + response.toString());

                teeResponseList(response);
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getLocalClassName(), "Volley Error = " + error);
                pDialog.cancel();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Tee list", "create Account = URL= " + PUTTAPI.TEE_LIST_URL + " Object " + jsonObject.toString());
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void teeResponseList(JSONObject response) {
        final List<TeeBean> list = new ArrayList<TeeBean>();
        final List<TeeBeanW> list1 = new ArrayList<TeeBeanW>();
        final List<TeeBeanJ> list2 = new ArrayList<TeeBeanJ>();

        // TeeBean teeBean = new TeeBean();

        if(response != null){
            try {


                JSONObject jObject = new JSONObject(response.toString());
                JSONObject jsonObject = jObject.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    JSONObject jsonObject1 = jsonObject.getJSONObject("GolfCourseTee");
                    JSONArray jsonArray = jsonObject1.getJSONArray("Men");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        list.add(new TeeBean(jsonArray.getJSONObject(i).getString("code"), jsonArray.getJSONObject(i).getString("tee_id"), jsonArray.getJSONObject(i).getString("tee_name"), jsonArray.getJSONObject(i).getString("tee_color")));
                    }

                    JSONArray jsonArray1 = jsonObject1.getJSONArray("Ladies");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        list1.add(new TeeBeanW(jsonArray1.getJSONObject(i).getString("code"), jsonArray1.getJSONObject(i).getString("tee_id"), jsonArray1.getJSONObject(i).getString("tee_name"), jsonArray1.getJSONObject(i).getString("tee_color")));

                    }
                    JSONArray jsonArray2 = jsonObject1.getJSONArray("Junior");
                    for (int i = 0; i < jsonArray2.length(); i++) {
                        list2.add(new TeeBeanJ(jsonArray2.getJSONObject(i).getString("code"), jsonArray2.getJSONObject(i).getString("tee_id"), jsonArray2.getJSONObject(i).getString("tee_name"), jsonArray2.getJSONObject(i).getString("tee_color")));
                    }

                }else {
                    String msg = jsonObject.getString("message");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(list, TeeBean.StuNameComparator);
        Collections.sort(list1, TeeBeanW.StuNameComparator);
        Collections.sort(list2, TeeBeanJ.StuNameComparator);
        for (int i=0;i<list.size();i++){
            String tee_name = list.get(i).getM_tee_name().toString();
            Log.v("Color Name Men",tee_name);
            if (tee_name.equalsIgnoreCase("Red")){

                mArray[i].setText("mr");
                mArray[i].setBackgroundResource(R.drawable.red_tee);
                mArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Green")){

                mArray[i].setText("mg");
                mArray[i].setBackgroundResource(R.drawable.green_tee);
                mArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Yellow")){

                mArray[i].setText("my");
                mArray[i].setBackgroundResource(R.drawable.yello_tee);
                mArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Black")){

                mArray[i].setText("mb");
                mArray[i].setBackgroundResource(R.drawable.black_tee);
                mArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("White")){

                mArray[i].setText("mw");
                mArray[i].setBackgroundResource(R.drawable.white_tee);
                mArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Blue")){

                mArray[i].setText("mu");
                mArray[i].setBackgroundResource(R.drawable.blue_tee);
                mArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Gold")){

                mArray[i].setText("ml");
                mArray[i].setBackgroundResource(R.drawable.gold_tee);
                mArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Silver")){

                mArray[i].setText("ms");
                mArray[i].setBackgroundResource(R.drawable.silver_tee);
                mArray[i].setVisibility(View.VISIBLE);
            }
        }
        for (int i=0;i<list1.size();i++){
            String tee_name = list1.get(i).getW_tee_name().toString();
            Log.v("Color Name Ladies",tee_name);
            if (tee_name.equalsIgnoreCase("Red")){

                wArray[i].setText("wr");
                wArray[i].setBackgroundResource(R.drawable.red_tee);
                wArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Green")){

                wArray[i].setText("wg");
                wArray[i].setBackgroundResource(R.drawable.green_tee);
                wArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Yellow")){

                wArray[i].setText("wy");
                wArray[i].setBackgroundResource(R.drawable.yello_tee);
                wArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Black")){

                wArray[i].setText("wb");
                wArray[i].setBackgroundResource(R.drawable.black_tee);
                wArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("White")){

                wArray[i].setText("ww");
                wArray[i].setBackgroundResource(R.drawable.white_tee);
                wArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Blue")){

                wArray[i].setText("wu");
                wArray[i].setBackgroundResource(R.drawable.blue_tee);
                wArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Gold")){

                wArray[i].setText("wl");
                wArray[i].setBackgroundResource(R.drawable.gold_tee);
                wArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Silver")){

                wArray[i].setText("ws");
                wArray[i].setBackgroundResource(R.drawable.silver_tee);
                wArray[i].setVisibility(View.VISIBLE);
            }
        }
        for (int i=0;i<list2.size();i++){
            String tee_name = list2.get(i).getJ_tee_name().toString();
            Log.v("Color Name Junior",tee_name);
            if (tee_name.equalsIgnoreCase("Red")){

                jArray[i].setText("jr");
                jArray[i].setBackgroundResource(R.drawable.red_tee);
                jArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Green")){

                jArray[i].setText("jg");
                jArray[i].setBackgroundResource(R.drawable.green_tee);
                jArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Yellow")){

                jArray[i].setText("jy");
                jArray[i].setBackgroundResource(R.drawable.yello_tee);
                jArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Black")){

                jArray[i].setText("jb");
                jArray[i].setBackgroundResource(R.drawable.black_tee);
                jArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("White")){

                jArray[i].setText("jw");
                jArray[i].setBackgroundResource(R.drawable.white_tee);
                jArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Blue")){

                jArray[i].setText("ju");
                jArray[i].setBackgroundResource(R.drawable.blue_tee);
                jArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Gold")){

                jArray[i].setText("jl");
                jArray[i].setBackgroundResource(R.drawable.gold_tee);
                jArray[i].setVisibility(View.VISIBLE);
            }else if (tee_name.equalsIgnoreCase("Silver")){

                jArray[i].setText("js");
                jArray[i].setBackgroundResource(R.drawable.silver_tee);
                jArray[i].setVisibility(View.VISIBLE);
            }
        }
        parentTee.setVisibility(View.VISIBLE);

    }



    private void getFormatList(){

        String response = "";
        JSONObject jsonObjectF = null;

        if (noOFPlayer.equalsIgnoreCase("1")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"GROSS STROKEPLAY\",\"format_id\":2},{\"format_name\":\"GROSS STABLEFORD\",\"format_id\":5}],\"message\":\"Stroke List\"}}";

        }else if (noOFPlayer.equalsIgnoreCase("2")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"MATCHPLAY\",\"format_id\":10},{\"format_name\":\"AUTOPRESS\",\"format_id\":11},{\"format_name\":\"GROSS STROKEPLAY\",\"format_id\":2},{\"format_name\":\"NET STROKEPLAY\",\"format_id\":3},{\"format_name\":\"3\\/4TH NET STROKEPLAY\",\"format_id\":4},{\"format_name\":\"GROSS STABLEFORD\",\"format_id\":5},{\"format_name\":\"NET STABLEFORD\",\"format_id\":6},{\"format_name\":\"3\\/4TH NET STABLEFORD\",\"format_id\":7}],\"message\":\"Stroke List\"}}";
        }else if (noOFPlayer.equalsIgnoreCase("3")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"4-2-0\",\"format_id\":12},{\"format_name\":\"GROSS STROKEPLAY\",\"format_id\":2},{\"format_name\":\"NET STROKEPLAY\",\"format_id\":3},{\"format_name\":\"3\\/4TH NET STROKEPLAY\",\"format_id\":4},{\"format_name\":\"GROSS STABLEFORD\",\"format_id\":5},{\"format_name\":\"NET STABLEFORD\",\"format_id\":6},{\"format_name\":\"3\\/4TH NET STABLEFORD\",\"format_id\":7}],\"message\":\"Stroke List\"}}";
        }else if (noOFPlayer.equalsIgnoreCase("4") && ind_team_id.equalsIgnoreCase("1")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"MATCHPLAY\",\"format_id\":10},{\"format_name\":\"AUTOPRESS\",\"format_id\":11},{\"format_name\":\"VEGAS\",\"format_id\":13},{\"format_name\":\"2-1\",\"format_id\":14}],\"message\":\"Stroke List\"}}";
        }else if (noOFPlayer.equalsIgnoreCase("4") && ind_team_id.equalsIgnoreCase("2")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"GROSS STROKEPLAY\",\"format_id\":2},{\"format_name\":\"NET STROKEPLAY\",\"format_id\":3},{\"format_name\":\"3\\/4TH NET STROKEPLAY\",\"format_id\":4},{\"format_name\":\"GROSS STABLEFORD\",\"format_id\":5},{\"format_name\":\"NET STABLEFORD\",\"format_id\":6},{\"format_name\":\"3\\/4TH NET STABLEFORD\",\"format_id\":7}],\"message\":\"Stroke List\"}}";

        }else if (noOFPlayer.equalsIgnoreCase("4+")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"GROSS STROKEPLAY\",\"format_id\":2},{\"format_name\":\"NET STROKEPLAY\",\"format_id\":3},{\"format_name\":\"3\\/4TH NET STROKEPLAY\",\"format_id\":4},{\"format_name\":\"GROSS STABLEFORD\",\"format_id\":5},{\"format_name\":\"NET STABLEFORD\",\"format_id\":6},{\"format_name\":\"3\\/4TH NET STABLEFORD\",\"format_id\":7}],\"message\":\"Stroke List\"}}";
        }

        try {
            jsonObjectF = new JSONObject(response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


                DataBean bean = new DataBean();
                bean = parser.getFormateResponseList(jsonObjectF);
                formatList = new ArrayList<FormatBean>();
                formatList = bean.getFormatList();
                formatArr = new String[formatList.size()];
                for (int i = 0; i < formatList.size(); i++) {
                    formatArr[i] = formatList.get(i).getFormat_name();
                }
                // hiding progressDialog

                ArrayAdapter<String> eventTypeAdapter = new ArrayAdapter<String>(EditEventActivity.this, R.layout.spinner_item, formatArr);
                eventTypeAdapter.setDropDownViewResource(R.layout.custome_spinner);
                // event.setAdapter(new NothingSelectedSpinnerAdapter(eventTypeAdapter, R.layout.spinner_row_nothing_selected, MainActivity.this));
                //event.setPrompt("Select");

                formatSpinner.setAdapter(eventTypeAdapter);
                // eventSpinner.setAdapter(eventTypeAdapter);
                String myString = editformateName.toUpperCase(); //the value you want the position for

                if (!myString.equals(null)) {
                    int spinnerPosition = eventTypeAdapter.getPosition(myString);
                    formatSpinner.setSelection(spinnerPosition);

                    Log.v("posuisakoldjas",""+spinnerPosition+myString);
                }

                if (format_id.equalsIgnoreCase("10")||format_id.equalsIgnoreCase("11")||format_id.equalsIgnoreCase("12")||format_id.equalsIgnoreCase("13")||format_id.equalsIgnoreCase("14")){
                    singleScreenLayout.setVisibility(View.GONE);
                    is_singlescreen ="1";
                }else {
                    if (noOFPlayer.equalsIgnoreCase("4+")){
                        singleScreenLayout.setVisibility(View.GONE);

                    }else if (noOFPlayer.equalsIgnoreCase("1")){
                        singleScreenLayout.setVisibility(View.GONE);

                    }
                    else {

                        singleScreenLayout.setVisibility(View.VISIBLE);
                        if (singleType.equalsIgnoreCase("0")){

                            noSingle.setChecked(true);
                            yesSingle.setChecked(false);
                            noSingle.setTextColor(Color.WHITE);
                            yesSingle.setTextColor(Color.BLACK);

                        }else {

                            noSingle.setChecked(false);
                            yesSingle.setChecked(true);
                            noSingle.setTextColor(Color.BLACK);
                            yesSingle.setTextColor(Color.WHITE);


                        }
                    }
                }



                final DataBean finalBean = bean;
                formatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        format_id = finalBean.getFormatList().get(position).getFormat_id();
                        formate_name = finalBean.getFormatList().get(position).getFormat_name();

                        if (format_id.equalsIgnoreCase("10")||format_id.equalsIgnoreCase("11")||format_id.equalsIgnoreCase("12")||format_id.equalsIgnoreCase("13")||format_id.equalsIgnoreCase("14")){
                            singleScreenLayout.setVisibility(View.GONE);
                            is_singlescreen ="1";
                        }else {
                            if (noOFPlayer.equalsIgnoreCase("4+")){
                                singleScreenLayout.setVisibility(View.GONE);
                            }if (noOFPlayer.equalsIgnoreCase("1")){
                                singleScreenLayout.setVisibility(View.GONE);
                            }
                            else {

                                singleScreenLayout.setVisibility(View.VISIBLE);
                                if (singleType.equalsIgnoreCase("0")){

                                    noSingle.setChecked(true);
                                    yesSingle.setChecked(false);
                                    noSingle.setTextColor(Color.WHITE);
                                    yesSingle.setTextColor(Color.BLACK);

                                }else {

                                    noSingle.setChecked(false);
                                    yesSingle.setChecked(true);
                                    noSingle.setTextColor(Color.BLACK);
                                    yesSingle.setTextColor(Color.WHITE);

                                }
                            }
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight()
                    || y < w.getTop() || y > w.getBottom()) ) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }


    private void teeMethod(){

        final Dialog dialog = new Dialog(EditEventActivity.this,android.R.style.Theme_Translucent_NoTitleBar);

        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog.setContentView(R.layout.select_tee_popup);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        doneBTN_Tee = (TextView)dialog.findViewById(R.id.tee_doneTXT);

        parentTee = (RelativeLayout)dialog.findViewById(R.id.parentTee);
        m1 = (TextView)dialog.findViewById(R.id.m1);
        m2 = (TextView)dialog.findViewById(R.id.m2);
        m3 = (TextView)dialog.findViewById(R.id.m3);
        m4 = (TextView)dialog.findViewById(R.id.m4);
        m5 = (TextView)dialog.findViewById(R.id.m5);
        m6 = (TextView)dialog.findViewById(R.id.m6);
        m7 = (TextView)dialog.findViewById(R.id.m7);
        m8 = (TextView)dialog.findViewById(R.id.m8);

        w1 = (TextView)dialog.findViewById(R.id.w1);
        w2 = (TextView)dialog.findViewById(R.id.w2);
        w3 = (TextView)dialog.findViewById(R.id.w3);
        w4 = (TextView)dialog.findViewById(R.id.w4);
        w5 = (TextView)dialog.findViewById(R.id.w5);
        w6 = (TextView)dialog.findViewById(R.id.w6);
        w7 = (TextView)dialog.findViewById(R.id.w7);
        w8 = (TextView)dialog.findViewById(R.id.w8);

        j1 = (TextView)dialog.findViewById(R.id.j1);
        j2 = (TextView)dialog.findViewById(R.id.j2);
        j3 = (TextView)dialog.findViewById(R.id.j3);
        j4 = (TextView)dialog.findViewById(R.id.j4);
        j5 = (TextView)dialog.findViewById(R.id.j5);
        j6 = (TextView)dialog.findViewById(R.id.j6);
        j7 = (TextView)dialog.findViewById(R.id.j7);
        j8 = (TextView)dialog.findViewById(R.id.j8);

        mArray[0] = m1;
        mArray[1] = m2;
        mArray[2] = m3;
        mArray[3] = m4;
        mArray[4] = m5;
        mArray[5] = m6;
        mArray[6] = m7;
        mArray[7] = m8;

        wArray[0] = w1;
        wArray[1] = w2;
        wArray[2] = w3;
        wArray[3] = w4;
        wArray[4] = w5;
        wArray[5] = w6;
        wArray[6] = w7;
        wArray[7] = w8;

        jArray[0] = j1;
        jArray[1] = j2;
        jArray[2] = j3;
        jArray[3] = j4;
        jArray[4] = j5;
        jArray[5] = j6;
        jArray[6] = j7;
        jArray[7] = j8;

        getTEEList();
        dialog.show();

        j1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = j1.getText().toString();
                if (red.equalsIgnoreCase("jr")){
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jb")){
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jy")){
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jg")){
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jw")){
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jl")){
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ju")){
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("js")){
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }


                j1.setAlpha(0.5f);
                j2.setAlpha(1.0f);
                j3.setAlpha(1.0f);
                j4.setAlpha(1.0f);
                j5.setAlpha(1.0f);
                j6.setAlpha(1.0f);
                j7.setAlpha(1.0f);
                j8.setAlpha(1.0f);





                //  dialog.hide();
            }
        });

        j2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = j2.getText().toString();
                if (red.equalsIgnoreCase("jr")){
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jb")){
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jy")){
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jg")){
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jw")){
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jl")){
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ju")){
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("js")){
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                j2.setAlpha(0.5f);
                j1.setAlpha(1.0f);
                j3.setAlpha(1.0f);
                j4.setAlpha(1.0f);
                j5.setAlpha(1.0f);
                j6.setAlpha(1.0f);
                j7.setAlpha(1.0f);
                j8.setAlpha(1.0f);
                // dialog.hide();
            }
        });


        j3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = j3.getText().toString();
                if (red.equalsIgnoreCase("jr")){
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jb")){
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jy")){
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jg")){
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jw")){
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jl")){
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ju")){
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("js")){
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                // dialog.hide();
                j3.setAlpha(0.5f);
                j2.setAlpha(1.0f);
                j1.setAlpha(1.0f);
                j4.setAlpha(1.0f);
                j5.setAlpha(1.0f);
                j6.setAlpha(1.0f);
                j7.setAlpha(1.0f);
                j8.setAlpha(1.0f);
            }
        });

        j4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = j4.getText().toString();
                if (red.equalsIgnoreCase("jr")){
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jb")){
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jy")){
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jg")){
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jw")){
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jl")){
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ju")){
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("js")){
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                // dialog.hide();
                j4.setAlpha(0.5f);
                j2.setAlpha(1.0f);
                j3.setAlpha(1.0f);
                j1.setAlpha(1.0f);
                j5.setAlpha(1.0f);
                j6.setAlpha(1.0f);
                j7.setAlpha(1.0f);
                j8.setAlpha(1.0f);
            }
        });

        j5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = j5.getText().toString();
                if (red.equalsIgnoreCase("jr")){
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jb")){
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jy")){
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jg")){
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jw")){
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jl")){
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ju")){
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("js")){
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                // dialog.hide();
                j5.setAlpha(0.5f);
                j2.setAlpha(1.0f);
                j3.setAlpha(1.0f);
                j4.setAlpha(1.0f);
                j1.setAlpha(1.0f);
                j6.setAlpha(1.0f);
                j7.setAlpha(1.0f);
                j8.setAlpha(1.0f);
            }
        });

        j6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = j6.getText().toString();
                if (red.equalsIgnoreCase("jr")){
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jb")){
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jy")){
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jg")){
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jw")){
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jl")){
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ju")){
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("js")){
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                // dialog.hide();
                j6.setAlpha(0.5f);
                j2.setAlpha(1.0f);
                j3.setAlpha(1.0f);
                j4.setAlpha(1.0f);
                j5.setAlpha(1.0f);
                j1.setAlpha(1.0f);
                j7.setAlpha(1.0f);
                j8.setAlpha(1.0f);
            }
        });

        j7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = j7.getText().toString();
                if (red.equalsIgnoreCase("jr")){
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jb")){
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jy")){
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jg")){
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jw")){
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jl")){
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ju")){
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("js")){
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //  dialog.hide();
                j7.setAlpha(0.5f);
                j2.setAlpha(1.0f);
                j3.setAlpha(1.0f);
                j4.setAlpha(1.0f);
                j5.setAlpha(1.0f);
                j6.setAlpha(1.0f);
                j1.setAlpha(1.0f);
                j8.setAlpha(1.0f);
            }
        });

        j8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = j8.getText().toString();
                if (red.equalsIgnoreCase("jr")){
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jb")){
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jy")){
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jg")){
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("jw")){
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("jl")){
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ju")){
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("js")){
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                // dialog.hide();
                j8.setAlpha(0.5f);
                j2.setAlpha(1.0f);
                j3.setAlpha(1.0f);
                j4.setAlpha(1.0f);
                j5.setAlpha(1.0f);
                j6.setAlpha(1.0f);
                j7.setAlpha(1.0f);
                j1.setAlpha(1.0f);
            }
        });




        w1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = w1.getText().toString();
                if (red.equalsIgnoreCase("wr")){
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wb")){
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wy")){
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wg")){
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ww")){
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wl")){
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wu")){
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ws")){
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //dialog.hide();
                w1.setAlpha(0.5f);
                w2.setAlpha(1.0f);
                w3.setAlpha(1.0f);
                w4.setAlpha(1.0f);
                w5.setAlpha(1.0f);
                w6.setAlpha(1.0f);
                w7.setAlpha(1.0f);
                w8.setAlpha(1.0f);
            }
        });

        w2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = w2.getText().toString();
                if (red.equalsIgnoreCase("wr")){
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wb")){
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wy")){
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wg")){
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ww")){
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wl")){
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wu")){
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ws")){
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                // dialog.hide();
                w2.setAlpha(0.5f);
                w1.setAlpha(1.0f);
                w3.setAlpha(1.0f);
                w4.setAlpha(1.0f);
                w5.setAlpha(1.0f);
                w6.setAlpha(1.0f);
                w7.setAlpha(1.0f);
                w8.setAlpha(1.0f);
            }
        });


        w3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = w3.getText().toString();
                if (red.equalsIgnoreCase("wr")){
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wb")){
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wy")){
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wg")){
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ww")){
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wl")){
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wu")){
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ws")){
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //  dialog.hide();
                w3.setAlpha(0.5f);
                w2.setAlpha(1.0f);
                w1.setAlpha(1.0f);
                w4.setAlpha(1.0f);
                w5.setAlpha(1.0f);
                w6.setAlpha(1.0f);
                w7.setAlpha(1.0f);
                w8.setAlpha(1.0f);
            }
        });

        w4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = w4.getText().toString();
                if (red.equalsIgnoreCase("wr")){
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wb")){
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wy")){
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wg")){
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ww")){
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wl")){
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wu")){
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ws")){
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                // dialog.hide();
                w4.setAlpha(0.5f);
                w2.setAlpha(1.0f);
                w3.setAlpha(1.0f);
                w1.setAlpha(1.0f);
                w5.setAlpha(1.0f);
                w6.setAlpha(1.0f);
                w7.setAlpha(1.0f);
                w8.setAlpha(1.0f);
            }
        });

        w5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = w5.getText().toString();
                if (red.equalsIgnoreCase("wr")){
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wb")){
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wy")){
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wg")){
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ww")){
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wl")){
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wu")){
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ws")){
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //  dialog.hide();
                w5.setAlpha(0.5f);
                w2.setAlpha(1.0f);
                w3.setAlpha(1.0f);
                w4.setAlpha(1.0f);
                w1.setAlpha(1.0f);
                w6.setAlpha(1.0f);
                w7.setAlpha(1.0f);
                w8.setAlpha(1.0f);
            }
        });

        w6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = w6.getText().toString();
                if (red.equalsIgnoreCase("wr")){
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wb")){
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wy")){
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wg")){
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ww")){
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wl")){
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wu")){
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ws")){
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //  dialog.hide();
                w6.setAlpha(0.5f);
                w2.setAlpha(1.0f);
                w3.setAlpha(1.0f);
                w4.setAlpha(1.0f);
                w5.setAlpha(1.0f);
                w1.setAlpha(1.0f);
                w7.setAlpha(1.0f);
                w8.setAlpha(1.0f);
            }
        });

        w7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = w7.getText().toString();
                if (red.equalsIgnoreCase("wr")){
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wb")){
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wy")){
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wg")){
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ww")){
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wl")){
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wu")){
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ws")){
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //  dialog.hide();
                w7.setAlpha(0.5f);
                w2.setAlpha(1.0f);
                w3.setAlpha(1.0f);
                w4.setAlpha(1.0f);
                w5.setAlpha(1.0f);
                w6.setAlpha(1.0f);
                w1.setAlpha(1.0f);
                w8.setAlpha(1.0f);
            }
        });

        w8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = w8.getText().toString();
                if (red.equalsIgnoreCase("wr")){
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wb")){
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("wy")){
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wg")){
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ww")){
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wl")){
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("wu")){
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ws")){
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //  dialog.hide();
                w8.setAlpha(0.5f);
                w2.setAlpha(1.0f);
                w3.setAlpha(1.0f);
                w4.setAlpha(1.0f);
                w5.setAlpha(1.0f);
                w6.setAlpha(1.0f);
                w7.setAlpha(1.0f);
                w1.setAlpha(1.0f);
            }
        });





        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = m1.getText().toString();
                if (red.equalsIgnoreCase("mr")){
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mb")){
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("my")){
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mg")){
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mw")){
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ml")){
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mu")){
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ms")){
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                // dialog.hide();
                m1.setAlpha(0.5f);
                m2.setAlpha(1.0f);
                m3.setAlpha(1.0f);
                m4.setAlpha(1.0f);
                m5.setAlpha(1.0f);
                m6.setAlpha(1.0f);
                m7.setAlpha(1.0f);
                m8.setAlpha(1.0f);
            }
        });

        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String red = m2.getText().toString();
                if (red.equalsIgnoreCase("mr")){
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mb")){
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("my")){
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mg")){
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mw")){
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ml")){
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mu")){
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ms")){
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //  dialog.hide();
                m2.setAlpha(0.5f);
                m1.setAlpha(1.0f);
                m3.setAlpha(1.0f);
                m4.setAlpha(1.0f);
                m5.setAlpha(1.0f);
                m6.setAlpha(1.0f);
                m7.setAlpha(1.0f);
                m8.setAlpha(1.0f);
            }
        });


        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = m3.getText().toString();
                if (red.equalsIgnoreCase("mr")){
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mb")){
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("my")){
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mg")){
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mw")){
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ml")){
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mu")){
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ms")){
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                // dialog.hide();
                m3.setAlpha(0.5f);
                m2.setAlpha(1.0f);
                m1.setAlpha(1.0f);
                m4.setAlpha(1.0f);
                m5.setAlpha(1.0f);
                m6.setAlpha(1.0f);
                m7.setAlpha(1.0f);
                m8.setAlpha(1.0f);
            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = m4.getText().toString();
                if (red.equalsIgnoreCase("mr")){
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mb")){
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("my")){
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mg")){
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mw")){
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ml")){
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mu")){
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ms")){
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //  dialog.hide();
                m4.setAlpha(0.5f);
                m2.setAlpha(1.0f);
                m3.setAlpha(1.0f);
                m1.setAlpha(1.0f);
                m5.setAlpha(1.0f);
                m6.setAlpha(1.0f);
                m7.setAlpha(1.0f);
                m8.setAlpha(1.0f);
            }
        });

        m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = m5.getText().toString();
                if (red.equalsIgnoreCase("mr")){
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mb")){
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("my")){
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mg")){
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mw")){
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ml")){
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mu")){
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ms")){
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //   dialog.hide();
                m5.setAlpha(0.5f);
                m2.setAlpha(1.0f);
                m3.setAlpha(1.0f);
                m4.setAlpha(1.0f);
                m1.setAlpha(1.0f);
                m6.setAlpha(1.0f);
                m7.setAlpha(1.0f);
                m8.setAlpha(1.0f);
            }
        });

        m6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = m6.getText().toString();
                if (red.equalsIgnoreCase("mr")){
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mb")){
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("my")){
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mg")){
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mw")){
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ml")){
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mu")){
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ms")){
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //  dialog.hide();
                m6.setAlpha(0.5f);
                m2.setAlpha(1.0f);
                m3.setAlpha(1.0f);
                m4.setAlpha(1.0f);
                m5.setAlpha(1.0f);
                m1.setAlpha(1.0f);
                m7.setAlpha(1.0f);
                m8.setAlpha(1.0f);
            }
        });

        m7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = m7.getText().toString();
                if (red.equalsIgnoreCase("mr")){
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mb")){
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("my")){
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mg")){
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mw")){
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ml")){
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mu")){
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ms")){
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                //  dialog.hide();
                m7.setAlpha(0.5f);
                m2.setAlpha(1.0f);
                m3.setAlpha(1.0f);
                m4.setAlpha(1.0f);
                m5.setAlpha(1.0f);
                m6.setAlpha(1.0f);
                m1.setAlpha(1.0f);
                m8.setAlpha(1.0f);
            }
        });

        m8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = m8.getText().toString();
                if (red.equalsIgnoreCase("mr")){
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mb")){
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("my")){
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mg")){
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("mw")){
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("ml")){
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                }else if (red.equalsIgnoreCase("mu")){
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                }else if (red.equalsIgnoreCase("ms")){
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(getApplicationContext(),"No Color Found",Toast.LENGTH_LONG).show();
                }
                // dialog.hide();
                m8.setAlpha(0.5f);
                m2.setAlpha(1.0f);
                m3.setAlpha(1.0f);
                m4.setAlpha(1.0f);
                m5.setAlpha(1.0f);
                m6.setAlpha(1.0f);
                m7.setAlpha(1.0f);
                m1.setAlpha(1.0f);
            }
        });

        doneBTN_Tee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (jColorName.equalsIgnoreCase("Red")){
                    jID = "3";

                }else if (jColorName.equalsIgnoreCase("Green")){
                    jID = "6";

                }else if (jColorName.equalsIgnoreCase("Yellow")){
                    jID = "4";
                    jText.setTextColor(Color.BLACK);

                }else if (jColorName.equalsIgnoreCase("Black")){
                    jID = "1";

                }else if (jColorName.equalsIgnoreCase("White")){
                    jID = "5";

                }else if (jColorName.equalsIgnoreCase("Blue")){
                    jID = "2";

                }else if (jColorName.equalsIgnoreCase("Gold")){
                    jID = "7";

                }else if (jColorName.equalsIgnoreCase("Silver")){

                    jID = "8";

                }

                if (wColorName.equalsIgnoreCase("Red")){
                    lID = "3";

                }else if (wColorName.equalsIgnoreCase("Green")){
                    lID = "6";

                }else if (wColorName.equalsIgnoreCase("Yellow")){
                    lID = "4";

                }else if (wColorName.equalsIgnoreCase("Black")){
                    lID = "1";

                }else if (wColorName.equalsIgnoreCase("White")){
                    lID = "5";


                }else if (wColorName.equalsIgnoreCase("Blue")){
                    lID = "2";

                }else if (wColorName.equalsIgnoreCase("Gold")){
                    lID = "7";

                }else if (wColorName.equalsIgnoreCase("Silver")){
                    lID = "8";

                }


                if (mColorName.equalsIgnoreCase("Red")){
                    mID = "3";

                }else if (mColorName.equalsIgnoreCase("Green")){
                    mID = "6";

                }else if (mColorName.equalsIgnoreCase("Yellow")){
                    mID = "4";


                }else if (mColorName.equalsIgnoreCase("Black")){
                    mID = "1";

                }else if (mColorName.equalsIgnoreCase("White")){

                    mID = "5";

                }else if (mColorName.equalsIgnoreCase("Blue")){
                    mID = "2";

                }else if (mColorName.equalsIgnoreCase("Gold")){

                    mID = "7";

                }else if (mColorName.equalsIgnoreCase("Silver")){
                    mID = "8";

                }


                dialog.hide();
            }
        });

    }


    private void getStokeHoleList(){

        strH1 = closestText1.getText().toString();
        strH2 = closestText2.getText().toString();
        strH3 = closestText3.getText().toString();
        strH4 = closestText4.getText().toString();

        if (noOfHole.getText().toString().equalsIgnoreCase("18")){

        }else {
            if (hole.getText().toString().equalsIgnoreCase("front 9")) {
                option = "1";
                holeBackFront = "FRONT 9";
            } else if (hole.getText().toString().equalsIgnoreCase("back 9")) {
                option = "2";
                holeBackFront = "BACK 9";
            }
        }

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        pDialog.show();

        JSONObject jsonObject = null;

        jsonObject = new JSONObject();

        try {
            jsonObject.putOpt("golf_course_id",golf_couse_id);
            jsonObject.putOpt("option",option);
            jsonObject.putOpt("version","2");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.HOLE_NUMBER_SHOW, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Hole List", "create Account = OnResponse= " + response.toString());

                stokeHoleResponse(response);
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getLocalClassName(), "Volley Error = " + error);
                pDialog.cancel();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Hole List", "create Account = URL= " + PUTTAPI.HOLE_NUMBER_SHOW + " Object " + jsonObject.toString());
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void stokeHoleResponse(JSONObject response) {
        final List<HoleBeanFirst> list = new ArrayList<HoleBeanFirst>();
        final List<HoleBeanSecond> list1 = new ArrayList<HoleBeanSecond>();


        if(response != null){
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                holeConter=0;

                String str1 = "100";
                String str2= "100" ;
                String str3= "100" ;
                String str4= "100" ;
                String str5= "100" ;
                String str6= "100" ;
                String str7 = "100";
                String str8= "100";
                String str9= "100" ;
                String str10= "100" ;
                String str11= "100";
                String str12= "100";
                String str13= "100" ;
                String str14= "100";
                String str15= "100" ;
                String str16= "100" ;
                String str17= "100" ;
                String str18= "100" ;

                JSONArray jsonArray = jsonObject1.getJSONArray("par3_holes");
                for (int i = 0; i < jsonArray.length(); i++) {
                    HoleBeanFirst holeBeanFirst = new HoleBeanFirst();
                    holeBeanFirst.setHole(jsonArray.getJSONObject(i).getString("hole"));

                    list.add(holeBeanFirst);
                    // mArray[i].setVisibility(View.VISIBLE);
                    holeArray[i].setVisibility(View.VISIBLE);
                    holeArray[i].setText(holeBeanFirst.getHole());

                   if (i==0) {
                        str1 = holeBeanFirst.getHole();

                   }else if (i==1){
                       str2 = holeBeanFirst.getHole();

                   }else if (i==2){
                       str3 = holeBeanFirst.getHole();

                   }else if (i==3){
                       str4 = holeBeanFirst.getHole();

                   }else if (i==4){
                       str5 = holeBeanFirst.getHole();

                   }else if (i==5){
                       str6 = holeBeanFirst.getHole();

                   }else if (i==6){
                       str7 = holeBeanFirst.getHole();

                   }else if (i==7){
                       str8 = holeBeanFirst.getHole();

                   }else if (i==8){
                       str9 = holeBeanFirst.getHole();

                   }else if (i==9){
                       str10 = holeBeanFirst.getHole();

                   }else if (i==10){
                       str11 = holeBeanFirst.getHole();

                   }else if (i==11){
                       str12 = holeBeanFirst.getHole();

                   }else if (i==12){
                       str13 = holeBeanFirst.getHole();

                   }else if (i==13){
                       str14 = holeBeanFirst.getHole();

                   }else if (i==14){
                       str15 = holeBeanFirst.getHole();

                   }else if (i==15){
                       str16 = holeBeanFirst.getHole();

                   }else if (i==16){
                       str17 = holeBeanFirst.getHole();

                   }else if (i==17){
                       str18 = holeBeanFirst.getHole();

                   }

                    if (strH1!=null && !strH1.equalsIgnoreCase("-")){
                        holeConter = 1;
                    }
                    if (strH2!=null && !strH2.equalsIgnoreCase("-")){
                        holeConter = 2;
                    }
                    if (strH3!=null && !strH3.equalsIgnoreCase("-")){
                        holeConter = 3;
                    }
                    if (strH4!=null && !strH4.equalsIgnoreCase("-")){
                        holeConter= 4;
                    }


                    if (str1.equalsIgnoreCase(strH1)){
                        hole1_flag = 1;

                    }else  if (str2.equalsIgnoreCase(strH1)){
                        hole2_flag = 1;

                    }else  if (str3.equalsIgnoreCase(strH1)){
                        hole3_flag = 1;

                    }else  if (str4.equalsIgnoreCase(strH1)){
                        hole4_flag = 1;

                    }else  if (str5.equalsIgnoreCase(strH1)){
                        hole5_flag = 1;

                    }else  if (str6.equalsIgnoreCase(strH1)){
                        hole6_flag = 1;

                    }else  if (str7.equalsIgnoreCase(strH1)){
                        hole7_flag = 1;


                    }else  if (str8.equalsIgnoreCase(strH1)){
                        hole8_flag = 1;

                    }else  if (str9.equalsIgnoreCase(strH1)){
                        hole9_flag = 1;

                    }else  if (str10.equalsIgnoreCase(strH1)){
                        hole10_flag = 1;

                    }else  if (str11.equalsIgnoreCase(strH1)){
                        hole11_flag = 1;

                    }else  if (str12.equalsIgnoreCase(strH1)){
                        hole12_flag = 1;

                    }else  if (str13.equalsIgnoreCase(strH1)){
                        hole13_flag = 1;

                    }else  if (str14.equalsIgnoreCase(strH1)){
                        hole14_flag = 1;

                    }else  if (str15.equalsIgnoreCase(strH1)){
                        hole15_flag = 1;

                    }else  if (str16.equalsIgnoreCase(strH1)){
                        hole16_flag = 1;

                    }else  if (str17.equalsIgnoreCase(strH1)){
                        hole17_flag = 1;

                    }else  if (str18.equalsIgnoreCase(strH1)){
                        hole18_flag = 1;

                    }

                    if (str1.equalsIgnoreCase(strH2)){
                        hole1_flag = 1;

                    }else  if (str2.equalsIgnoreCase(strH2)){
                        hole2_flag = 1;

                    }else  if (str3.equalsIgnoreCase(strH2)){
                        hole3_flag = 1;

                    }else  if (str4.equalsIgnoreCase(strH2)){
                        hole4_flag = 1;

                    }else  if (str5.equalsIgnoreCase(strH2)){
                        hole5_flag = 1;

                    }else  if (str6.equalsIgnoreCase(strH2)){
                        hole6_flag = 1;

                    }else  if (str7.equalsIgnoreCase(strH2)){
                        hole7_flag = 1;

                    }else  if (str8.equalsIgnoreCase(strH2)){
                        hole8_flag = 1;

                    }else  if (str9.equalsIgnoreCase(strH2)){
                        hole9_flag = 1;

                    }else  if (str10.equalsIgnoreCase(strH2)){
                        hole10_flag = 1;

                    }else  if (str11.equalsIgnoreCase(strH2)){
                        hole11_flag = 1;

                    }else  if (str12.equalsIgnoreCase(strH2)){
                        hole12_flag = 1;

                    }else  if (str13.equalsIgnoreCase(strH2)){
                        hole13_flag = 1;

                    }else  if (str14.equalsIgnoreCase(strH2)){
                        hole14_flag = 1;

                    }else  if (str15.equalsIgnoreCase(strH2)){
                        hole15_flag = 1;

                    }else  if (str16.equalsIgnoreCase(strH2)){
                        hole16_flag = 1;

                    }else  if (str17.equalsIgnoreCase(strH2)){
                        hole17_flag = 1;

                    }else  if (str18.equalsIgnoreCase(strH2)){
                        hole18_flag = 1;

                    }

                    if (str1.equalsIgnoreCase(strH3)){
                        hole1_flag = 1;

                    }else  if (str2.equalsIgnoreCase(strH3)){
                        hole2_flag = 1;

                    }else  if (str3.equalsIgnoreCase(strH3)){
                        hole3_flag = 1;

                    }else  if (str4.equalsIgnoreCase(strH3)){
                        hole4_flag = 1;

                    }else  if (str5.equalsIgnoreCase(strH3)){
                        hole5_flag = 1;

                    }else  if (str6.equalsIgnoreCase(strH3)){
                        hole6_flag = 1;

                    }else  if (str7.equalsIgnoreCase(strH3)){
                        hole7_flag = 1;

                    }else  if (str8.equalsIgnoreCase(strH3)){
                        hole8_flag = 1;

                    }else  if (str9.equalsIgnoreCase(strH3)){
                        hole9_flag = 1;

                    }else  if (str10.equalsIgnoreCase(strH3)){
                        hole10_flag = 1;

                    }else  if (str11.equalsIgnoreCase(strH3)){
                        hole11_flag = 1;

                    }else  if (str12.equalsIgnoreCase(strH3)){
                        hole12_flag = 1;

                    }else  if (str13.equalsIgnoreCase(strH3)){
                        hole13_flag = 1;

                    }else  if (str14.equalsIgnoreCase(strH3)){
                        hole14_flag = 1;

                    }else  if (str15.equalsIgnoreCase(strH3)){
                        hole15_flag = 1;

                    }else  if (str16.equalsIgnoreCase(strH3)){
                        hole16_flag = 1;

                    }else  if (str17.equalsIgnoreCase(strH3)){
                        hole17_flag = 1;

                    }else  if (str18.equalsIgnoreCase(strH3)){
                        hole18_flag = 1;

                    }

                    if (str1.equalsIgnoreCase(strH4)){
                        hole1_flag = 1;

                    }else  if (str2.equalsIgnoreCase(strH4)){
                        hole2_flag = 1;

                    }else  if (str3.equalsIgnoreCase(strH4)){
                        hole3_flag = 1;

                    }else  if (str4.equalsIgnoreCase(strH4)){
                        hole4_flag = 1;

                    }else  if (str5.equalsIgnoreCase(strH4)){
                        hole5_flag = 1;

                    }else  if (str6.equalsIgnoreCase(strH4)){
                        hole6_flag = 1;

                    }else  if (str7.equalsIgnoreCase(strH4)){
                        hole7_flag = 1;

                    }else  if (str8.equalsIgnoreCase(strH4)){
                        hole8_flag = 1;

                    }else  if (str9.equalsIgnoreCase(strH4)){
                        hole9_flag = 1;

                    }else  if (str10.equalsIgnoreCase(strH4)){
                        hole10_flag = 1;

                    }else  if (str11.equalsIgnoreCase(strH4)){
                        hole11_flag = 1;

                    }else  if (str12.equalsIgnoreCase(strH4)){
                        hole12_flag = 1;

                    }else  if (str13.equalsIgnoreCase(strH4)){
                        hole13_flag = 1;

                    }else  if (str14.equalsIgnoreCase(strH4)){
                        hole14_flag = 1;

                    }else  if (str15.equalsIgnoreCase(strH4)){
                        hole15_flag = 1;

                    }else  if (str16.equalsIgnoreCase(strH4)){
                        hole16_flag = 1;

                    }else  if (str17.equalsIgnoreCase(strH4)){
                        hole17_flag = 1;

                    }else  if (str18.equalsIgnoreCase(strH4)){
                        hole18_flag = 1;


                    }


                }
                JSONArray jsonArray1 = jsonObject1.getJSONArray("par4n5_holes");
                for (int i = 0; i < jsonArray1.length(); i++) {
                    HoleBeanSecond holeBeanSecond = new HoleBeanSecond();
                    holeBeanSecond.setHole(jsonArray1.getJSONObject(i).getString("hole"));

                    list1.add(holeBeanSecond);
                    //  holeArray2[i].setVisibility(View.VISIBLE);
                    //  holeArray2[i].setText(holeBeanSecond.getHole());
                }


                holeParent.setVisibility(View.VISIBLE);

                Log.v("holllllllll",totalHole+"-"+holeConter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private void getStokeHoleList1(final String type){

        if (noOfHole.getText().toString().equalsIgnoreCase("18")){

        }else {
            if (hole.getText().toString().equalsIgnoreCase("front 9")) {
                option = "1";
                holeBackFront = "FRONT 9";
            } else if (hole.getText().toString().equalsIgnoreCase("back 9")) {
                option = "2";
                holeBackFront = "BACK 9";
            }
        }

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;

        jsonObject = new JSONObject();

        try {
            jsonObject.putOpt("golf_course_id",golf_couse_id);
            jsonObject.putOpt("option",option);
            jsonObject.putOpt("version","2");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.HOLE_NUMBER_SHOW, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Hole List", "create Account = OnResponse= " + response.toString());

                stokeHoleResponse1(response,type);
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getLocalClassName(), "Volley Error = " + error);
                pDialog.cancel();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Hole List", "create Account = URL= " + PUTTAPI.TEE_LIST_URL + " Object " + jsonObject.toString());
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void stokeHoleResponse1(JSONObject response,String type) {
        final List<HoleBeanFirst> list = new ArrayList<HoleBeanFirst>();
        final List<HoleBeanSecond> list1 = new ArrayList<HoleBeanSecond>();

        holeConter_s =0;
        holeConter_l =0;

        if(response != null){
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                String str1 = "100";
                String str2= "100" ;
                String str3= "100" ;
                String str4= "100" ;
                String str5= "100" ;
                String str6= "100" ;
                String str7 = "100";
                String str8= "100";
                String str9= "100" ;
                String str10= "100" ;
                String str11= "100";
                String str12= "100";
                String str13= "100" ;
                String str14= "100";
                String str15= "100" ;
                String str16= "100" ;
                String str17= "100" ;
                String str18= "100" ;

                JSONArray jsonArray = jsonObject1.getJSONArray("par3_holes");
                for (int i = 0; i < jsonArray.length(); i++) {
                    HoleBeanFirst holeBeanFirst = new HoleBeanFirst();
                    holeBeanFirst.setHole(jsonArray.getJSONObject(i).getString("hole"));

                    list.add(holeBeanFirst);

                }
                JSONArray jsonArray1 = jsonObject1.getJSONArray("par4n5_holes");
                for (int i = 0; i < jsonArray1.length(); i++) {
                    HoleBeanSecond holeBeanSecond = new HoleBeanSecond();
                    holeBeanSecond.setHole(jsonArray1.getJSONObject(i).getString("hole"));

                    list1.add(holeBeanSecond);
                    holeArray2[i].setVisibility(View.VISIBLE);
                    holeArray2[i].setText(holeBeanSecond.getHole());

                    if (type.equalsIgnoreCase("stringStraight")) {

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        Log.v("straightHole",st1+"S"+st2+"S"+st3+"S"+st4);

                        if (st1!=null && !st1.equalsIgnoreCase("-")){
                            holeConter_s = 1;
                        }
                        if (st2!=null && !st2.equalsIgnoreCase("-")){
                            holeConter_s = 2;
                        }
                        if (st3!=null && !st3.equalsIgnoreCase("-")){
                            holeConter_s = 3;
                        }
                        if (st4!=null && !st4.equalsIgnoreCase("-")){
                            holeConter_s = 4;
                        }

                        if (i == 0) {
                            str1 = holeBeanSecond.getHole();

                        } else if (i == 1) {
                            str2 = holeBeanSecond.getHole();

                        } else if (i == 2) {
                            str3 = holeBeanSecond.getHole();

                        } else if (i == 3) {
                            str4 = holeBeanSecond.getHole();

                        } else if (i == 4) {
                            str5 = holeBeanSecond.getHole();

                        } else if (i == 5) {
                            str6 = holeBeanSecond.getHole();

                        } else if (i == 6) {
                            str7 = holeBeanSecond.getHole();

                        } else if (i == 7) {
                            str8 = holeBeanSecond.getHole();

                        } else if (i == 8) {
                            str9 = holeBeanSecond.getHole();

                        } else if (i == 9) {
                            str10 = holeBeanSecond.getHole();

                        } else if (i == 10) {
                            str11 = holeBeanSecond.getHole();

                        } else if (i == 11) {
                            str12 = holeBeanSecond.getHole();

                        } else if (i == 12) {
                            str13 = holeBeanSecond.getHole();

                        } else if (i == 13) {
                            str14 = holeBeanSecond.getHole();

                        } else if (i == 14) {
                            str15 = holeBeanSecond.getHole();

                        } else if (i == 15) {
                            str16 = holeBeanSecond.getHole();

                        } else if (i == 16) {
                            str17 = holeBeanSecond.getHole();

                        } else if (i == 17) {
                            str18 = holeBeanSecond.getHole();

                        }


                        if (str1.equalsIgnoreCase(st1)) {
                            hole1_flag_s = 1;

                        } else if (str2.equalsIgnoreCase(st1)) {
                            hole2_flag_s = 1;

                        } else if (str3.equalsIgnoreCase(st1)) {
                            hole3_flag_s = 1;

                        } else if (str4.equalsIgnoreCase(st1)) {
                            hole4_flag_s = 1;

                        } else if (str5.equalsIgnoreCase(st1)) {
                            hole5_flag_s = 1;

                        } else if (str6.equalsIgnoreCase(st1)) {
                            hole6_flag_s = 1;

                        } else if (str7.equalsIgnoreCase(st1)) {
                            hole7_flag_s = 1;

                        } else if (str8.equalsIgnoreCase(st1)) {
                            hole8_flag_s = 1;

                        } else if (str9.equalsIgnoreCase(st1)) {
                            hole9_flag_s = 1;

                        } else if (str10.equalsIgnoreCase(st1)) {
                            hole10_flag_s = 1;

                        } else if (str11.equalsIgnoreCase(st1)) {
                            hole11_flag_s = 1;

                        } else if (str12.equalsIgnoreCase(st1)) {
                            hole12_flag_s = 1;

                        } else if (str13.equalsIgnoreCase(st1)) {
                            hole13_flag_s = 1;

                        } else if (str14.equalsIgnoreCase(st1)) {
                            hole14_flag_s = 1;

                        } else if (str15.equalsIgnoreCase(st1)) {
                            hole15_flag_s = 1;

                        } else if (str16.equalsIgnoreCase(st1)) {
                            hole16_flag_s = 1;

                        } else if (str17.equalsIgnoreCase(st1)) {
                            hole17_flag_s = 1;

                        } else if (str18.equalsIgnoreCase(st1)) {
                            hole18_flag_s = 1;

                        }

                        if (str1.equalsIgnoreCase(st2)) {
                            hole1_flag_s = 1;

                        } else if (str2.equalsIgnoreCase(st2)) {
                            hole2_flag_s = 1;

                        } else if (str3.equalsIgnoreCase(st2)) {
                            hole3_flag_s = 1;

                        } else if (str4.equalsIgnoreCase(st2)) {
                            hole4_flag_s = 1;

                        } else if (str5.equalsIgnoreCase(st2)) {
                            hole5_flag_s = 1;

                        } else if (str6.equalsIgnoreCase(st2)) {
                            hole6_flag_s = 1;

                        } else if (str7.equalsIgnoreCase(st2)) {
                            hole7_flag_s = 1;

                        } else if (str8.equalsIgnoreCase(st2)) {
                            hole8_flag_s = 1;

                        } else if (str9.equalsIgnoreCase(st2)) {
                            hole9_flag_s = 1;

                        } else if (str10.equalsIgnoreCase(st2)) {
                            hole10_flag_s = 1;

                        } else if (str11.equalsIgnoreCase(st2)) {
                            hole11_flag_s = 1;

                        } else if (str12.equalsIgnoreCase(st2)) {
                            hole12_flag_s = 1;

                        } else if (str13.equalsIgnoreCase(st2)) {
                            hole13_flag_s = 1;

                        } else if (str14.equalsIgnoreCase(st2)) {
                            hole14_flag_s = 1;

                        } else if (str15.equalsIgnoreCase(st2)) {
                            hole15_flag_s = 1;

                        } else if (str16.equalsIgnoreCase(st2)) {
                            hole16_flag_s = 1;

                        } else if (str17.equalsIgnoreCase(st2)) {
                            hole17_flag_s = 1;

                        } else if (str18.equalsIgnoreCase(st2)) {
                            hole18_flag_s = 1;

                        }

                        if (str1.equalsIgnoreCase(st3)) {
                            hole1_flag_s = 1;

                        } else if (str2.equalsIgnoreCase(st3)) {
                            hole2_flag_s = 1;

                        } else if (str3.equalsIgnoreCase(st3)) {
                            hole3_flag_s = 1;

                        } else if (str4.equalsIgnoreCase(st3)) {
                            hole4_flag_s = 1;

                        } else if (str5.equalsIgnoreCase(st3)) {
                            hole5_flag_s = 1;

                        } else if (str6.equalsIgnoreCase(st3)) {
                            hole6_flag_s = 1;

                        } else if (str7.equalsIgnoreCase(st3)) {
                            hole7_flag_s = 1;

                        } else if (str8.equalsIgnoreCase(st3)) {
                            hole8_flag_s = 1;

                        } else if (str9.equalsIgnoreCase(st3)) {
                            hole9_flag_s = 1;

                        } else if (str10.equalsIgnoreCase(st3)) {
                            hole10_flag_s = 1;

                        } else if (str11.equalsIgnoreCase(st3)) {
                            hole11_flag_s = 1;

                        } else if (str12.equalsIgnoreCase(st3)) {
                            hole12_flag_s = 1;

                        } else if (str13.equalsIgnoreCase(st3)) {
                            hole13_flag_s = 1;

                        } else if (str14.equalsIgnoreCase(st3)) {
                            hole14_flag_s = 1;

                        } else if (str15.equalsIgnoreCase(st3)) {
                            hole15_flag_s = 1;

                        } else if (str16.equalsIgnoreCase(st3)) {
                            hole16_flag_s = 1;

                        } else if (str17.equalsIgnoreCase(st3)) {
                            hole17_flag_s = 1;

                        } else if (str18.equalsIgnoreCase(st3)) {
                            hole18_flag_s = 1;

                        }

                        if (str1.equalsIgnoreCase(st4)) {
                            hole1_flag_s = 1;

                        } else if (str2.equalsIgnoreCase(st4)) {
                            hole2_flag_s = 1;

                        } else if (str3.equalsIgnoreCase(st4)) {
                            hole3_flag_s = 1;

                        } else if (str4.equalsIgnoreCase(st4)) {
                            hole4_flag_s = 1;

                        } else if (str5.equalsIgnoreCase(st4)) {
                            hole5_flag_s = 1;

                        } else if (str6.equalsIgnoreCase(st4)) {
                            hole6_flag_s = 1;

                        } else if (str7.equalsIgnoreCase(st4)) {
                            hole7_flag_s = 1;

                        } else if (str8.equalsIgnoreCase(st4)) {
                            hole8_flag_s = 1;

                        } else if (str9.equalsIgnoreCase(st4)) {
                            hole9_flag_s = 1;

                        } else if (str10.equalsIgnoreCase(st4)) {
                            hole10_flag_s = 1;

                        } else if (str11.equalsIgnoreCase(st4)) {
                            hole11_flag_s = 1;

                        } else if (str12.equalsIgnoreCase(st4)) {
                            hole12_flag_s = 1;

                        } else if (str13.equalsIgnoreCase(st4)) {
                            hole13_flag_s = 1;

                        } else if (str14.equalsIgnoreCase(st4)) {
                            hole14_flag_s = 1;

                        } else if (str15.equalsIgnoreCase(st4)) {
                            hole15_flag_s = 1;

                        } else if (str16.equalsIgnoreCase(st4)) {
                            hole16_flag_s = 1;

                        } else if (str17.equalsIgnoreCase(st4)) {
                            hole17_flag_s = 1;

                        } else if (str18.equalsIgnoreCase(st4)) {
                            hole18_flag_s = 1;

                        }
                    }else {

                        String L1 = longDrivetxt1.getText().toString();
                        String L2 = longDrivetxt2.getText().toString();
                        String L3 = longDrivetxt3.getText().toString();
                        String L4 = longDrivetxt4.getText().toString();

                        Log.v("longDriveHole",L1+"S"+L2+"S"+L3+"S"+L4);

                        if (L1!=null && !L1.equalsIgnoreCase("-")){
                            holeConter_l = 1;
                        }
                        if (L2!=null && !L2.equalsIgnoreCase("-")){
                            holeConter_l = 2;
                        }
                        if (L3!=null && !L3.equalsIgnoreCase("-")){
                            holeConter_l = 3;
                        }
                        if (L4!=null && !L4.equalsIgnoreCase("-")){
                            holeConter_l = 4;
                        }

                        if (i == 0) {
                            str1 = holeBeanSecond.getHole();

                        } else if (i == 1) {
                            str2 = holeBeanSecond.getHole();

                        } else if (i == 2) {
                            str3 = holeBeanSecond.getHole();

                        } else if (i == 3) {
                            str4 = holeBeanSecond.getHole();

                        } else if (i == 4) {
                            str5 = holeBeanSecond.getHole();

                        } else if (i == 5) {
                            str6 = holeBeanSecond.getHole();

                        } else if (i == 6) {
                            str7 = holeBeanSecond.getHole();

                        } else if (i == 7) {
                            str8 = holeBeanSecond.getHole();

                        } else if (i == 8) {
                            str9 = holeBeanSecond.getHole();

                        } else if (i == 9) {
                            str10 = holeBeanSecond.getHole();

                        } else if (i == 10) {
                            str11 = holeBeanSecond.getHole();

                        } else if (i == 11) {
                            str12 = holeBeanSecond.getHole();

                        } else if (i == 12) {
                            str13 = holeBeanSecond.getHole();

                        } else if (i == 13) {
                            str14 = holeBeanSecond.getHole();

                        } else if (i == 14) {
                            str15 = holeBeanSecond.getHole();

                        } else if (i == 15) {
                            str16 = holeBeanSecond.getHole();

                        } else if (i == 16) {
                            str17 = holeBeanSecond.getHole();

                        } else if (i == 17) {
                            str18 = holeBeanSecond.getHole();

                        }


                        if (str1.equalsIgnoreCase(L1)) {
                            hole1_flag_l = 1;

                        } else if (str2.equalsIgnoreCase(L1)) {
                            hole2_flag_l = 1;

                        } else if (str3.equalsIgnoreCase(L1)) {
                            hole3_flag_l = 1;

                        } else if (str4.equalsIgnoreCase(L1)) {
                            hole4_flag_l = 1;


                        } else if (str5.equalsIgnoreCase(L1)) {
                            hole5_flag_l = 1;


                        } else if (str6.equalsIgnoreCase(L1)) {
                            hole6_flag_l = 1;

                        } else if (str7.equalsIgnoreCase(L1)) {
                            hole7_flag_l = 1;

                        } else if (str8.equalsIgnoreCase(L1)) {
                            hole8_flag_l = 1;

                        } else if (str9.equalsIgnoreCase(L1)) {
                            hole9_flag_l = 1;

                        } else if (str10.equalsIgnoreCase(L1)) {
                            hole10_flag_l = 1;

                        } else if (str11.equalsIgnoreCase(L1)) {
                            hole11_flag_l = 1;

                        } else if (str12.equalsIgnoreCase(L1)) {
                            hole12_flag_l = 1;

                        } else if (str13.equalsIgnoreCase(L1)) {
                            hole13_flag_l = 1;

                        } else if (str14.equalsIgnoreCase(L1)) {
                            hole14_flag_l = 1;

                        } else if (str15.equalsIgnoreCase(L1)) {
                            hole15_flag_l = 1;

                        } else if (str16.equalsIgnoreCase(L1)) {
                            hole16_flag_l = 1;

                        } else if (str17.equalsIgnoreCase(L1)) {
                            hole17_flag_l = 1;

                        } else if (str18.equalsIgnoreCase(L1)) {
                            hole18_flag_l = 1;

                        }

                        if (str1.equalsIgnoreCase(L2)) {
                            hole1_flag_l = 1;

                        } else if (str2.equalsIgnoreCase(L2)) {
                            hole2_flag_l = 1;

                        } else if (str3.equalsIgnoreCase(L2)) {
                            hole3_flag_l = 1;

                        } else if (str4.equalsIgnoreCase(L2)) {
                            hole4_flag_l = 1;

                        } else if (str5.equalsIgnoreCase(L2)) {
                            hole5_flag_l = 1;

                        } else if (str6.equalsIgnoreCase(L2)) {
                            hole6_flag_l = 1;

                        } else if (str7.equalsIgnoreCase(L2)) {
                            hole7_flag_l = 1;

                        } else if (str8.equalsIgnoreCase(L2)) {
                            hole8_flag_l = 1;

                        } else if (str9.equalsIgnoreCase(L2)) {
                            hole9_flag_l = 1;

                        } else if (str10.equalsIgnoreCase(L2)) {
                            hole10_flag_l = 1;

                        } else if (str11.equalsIgnoreCase(L2)) {
                            hole11_flag_l = 1;

                        } else if (str12.equalsIgnoreCase(L2)) {
                            hole12_flag_l = 1;

                        } else if (str13.equalsIgnoreCase(L2)) {
                            hole13_flag_l = 1;

                        } else if (str14.equalsIgnoreCase(L2)) {
                            hole14_flag_l = 1;

                        } else if (str15.equalsIgnoreCase(L2)) {
                            hole15_flag_l = 1;

                        } else if (str16.equalsIgnoreCase(L2)) {
                            hole16_flag_l = 1;

                        } else if (str17.equalsIgnoreCase(L2)) {
                            hole17_flag_l = 1;

                        } else if (str18.equalsIgnoreCase(L2)) {
                            hole18_flag_l = 1;

                        }

                        if (str1.equalsIgnoreCase(L3)) {
                            hole1_flag_l = 1;

                        } else if (str2.equalsIgnoreCase(L3)) {
                            hole2_flag_l = 1;

                        } else if (str3.equalsIgnoreCase(L3)) {
                            hole3_flag_l = 1;

                        } else if (str4.equalsIgnoreCase(L3)) {
                            hole4_flag_l = 1;

                        } else if (str5.equalsIgnoreCase(L3)) {
                            hole5_flag_l = 1;

                        } else if (str6.equalsIgnoreCase(L3)) {
                            hole6_flag_l = 1;

                        } else if (str7.equalsIgnoreCase(L3)) {
                            hole7_flag_l = 1;

                        } else if (str8.equalsIgnoreCase(L3)) {
                            hole8_flag_l = 1;

                        } else if (str9.equalsIgnoreCase(L3)) {
                            hole9_flag_l = 1;

                        } else if (str10.equalsIgnoreCase(L3)) {
                            hole10_flag_l = 1;

                        } else if (str11.equalsIgnoreCase(L3)) {
                            hole11_flag_l = 1;

                        } else if (str12.equalsIgnoreCase(L3)) {
                            hole12_flag_l = 1;

                        } else if (str13.equalsIgnoreCase(L3)) {
                            hole13_flag_l = 1;

                        } else if (str14.equalsIgnoreCase(L3)) {
                            hole14_flag_l = 1;

                        } else if (str15.equalsIgnoreCase(L3)) {
                            hole15_flag_l = 1;

                        } else if (str16.equalsIgnoreCase(L3)) {
                            hole16_flag_l = 1;

                        } else if (str17.equalsIgnoreCase(L3)) {
                            hole17_flag_l = 1;

                        } else if (str18.equalsIgnoreCase(L3)) {
                            hole18_flag_l = 1;

                        }

                        if (str1.equalsIgnoreCase(L4)) {
                            hole1_flag_l = 1;

                        } else if (str2.equalsIgnoreCase(L4)) {
                            hole2_flag_l = 1;

                        } else if (str3.equalsIgnoreCase(L4)) {
                            hole3_flag_l = 1;

                        } else if (str4.equalsIgnoreCase(L4)) {
                            hole4_flag_l = 1;

                        } else if (str5.equalsIgnoreCase(L4)) {
                            hole5_flag_l = 1;

                        } else if (str6.equalsIgnoreCase(L4)) {
                            hole6_flag_l = 1;

                        } else if (str7.equalsIgnoreCase(L4)) {
                            hole7_flag_l = 1;

                        } else if (str8.equalsIgnoreCase(L4)) {
                            hole8_flag_l = 1;

                        } else if (str9.equalsIgnoreCase(L4)) {
                            hole9_flag_l = 1;

                        } else if (str10.equalsIgnoreCase(L4)) {
                            hole10_flag_l = 1;

                        } else if (str11.equalsIgnoreCase(L4)) {
                            hole11_flag_l = 1;

                        } else if (str12.equalsIgnoreCase(L4)) {
                            hole12_flag_l = 1;

                        } else if (str13.equalsIgnoreCase(L4)) {
                            hole13_flag_l = 1;

                        } else if (str14.equalsIgnoreCase(L4)) {
                            hole14_flag_l = 1;

                        } else if (str15.equalsIgnoreCase(L4)) {
                            hole15_flag_l = 1;

                        } else if (str16.equalsIgnoreCase(L4)) {
                            hole16_flag_l = 1;

                        } else if (str17.equalsIgnoreCase(L4)) {
                            hole17_flag_l = 1;

                        } else if (str18.equalsIgnoreCase(L4)) {
                            hole18_flag_l = 1;

                        }

                    }


                }


                holeParent.setVisibility(View.VISIBLE);
                Log.v("hollllStraght",totalHole+"-"+holeConter_l+"-"+holeConter_s);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerDialog, long millseconds) {
        String text = getDateToString(millseconds);
        dateTime_Text.setText(text);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }


    public void getEventInvitationListDetail(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;
        SharedPreferences pref =this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId",null);
        String eventID = getIntent().getStringExtra("eventID");

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id",user_ID);
            jsonObject.putOpt("event_id",eventID);
            jsonObject.putOpt("version","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.EVENT_INVITATION_DETAIL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getInvitationDetail(response);
                Log.e("GetEVENt DETAIL URL", "GetEVENt DETAIL URL" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this,"Error in "+ "GetEVENt DETAIL URL = " + PUTTAPI.EVENT_INVITATION_DETAIL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getInvitationDetail(JSONObject response){

        ArrayList<InviteDetailBean> list = new ArrayList<InviteDetailBean>();

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){
                    // JSONObject jsonObjectD = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0 ; i < jsonArray.length(); i++){
                        InviteDetailBean listBean = new InviteDetailBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        listBean.setGolf_course_name(jsonObject1.getString("golf_course_name"));
                        listBean.setFormateID(jsonObject1.getString("format_id"));
                        listBean.setGolfID(jsonObject1.getString("golf_course_id"));
                        listBean.setEvent_name(jsonObject1.getString("event_name"));
                        listBean.setNo_of_player(jsonObject1.getString("no_of_player"));
                        listBean.setPlayers_in_game(jsonObject1.getString("players_in_game"));
                        listBean.setFormat_name(jsonObject1.getString("format_name"));
                        listBean.setEvent_start_date_time(jsonObject1.getString("event_start_date_time"));
                        listBean.setIs_individual(jsonObject1.getString("is_individual"));
                        listBean.setTotal_hole_num(jsonObject1.getString("total_hole_num"));

                        singleType = jsonObject1.getString("is_singlescreen");
                        if (singleType.equalsIgnoreCase("0")){

                            is_singlescreen = "2";
                        }else {
                            is_singlescreen = "1";
                        }


                        public_private = jsonObject1.getString("is_public");

                        listBean.setType(jsonObject1.getString("type"));

                        listBean.setIs_spot(jsonObject1.getString("is_spot"));





                        String str = listBean.getTotal_hole_num();
                        if (str.equalsIgnoreCase("9")) {
                            listBean.setHole(jsonObject1.getString("holes"));


                            totalHole = 2;

                            closestBTN3.setVisibility(View.GONE);
                            closestBTN4.setVisibility(View.GONE);
                            closestBTN1.setVisibility(View.VISIBLE);
                            closestBTN2.setVisibility(View.GONE);

                            longDriveBTN3.setVisibility(View.GONE);
                            longDriveBTN4.setVisibility(View.GONE);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                            longDriveBTN2.setVisibility(View.GONE);

                            straightBTN3.setVisibility(View.GONE);
                            straightBTN4.setVisibility(View.GONE);
                            straightBTN1.setVisibility(View.VISIBLE);
                            straightBTN2.setVisibility(View.GONE);

                            holeConter = 0;
                            hole1_flag = 0; hole2_flag = 0; hole3_flag = 0; hole4_flag = 0; hole5_flag = 0; hole6_flag = 0; hole7_flag = 0;
                            hole8_flag = 0; hole9_flag = 0; hole10_flag = 0; hole11_flag = 0; hole12_flag = 0; hole13_flag = 0; hole14_flag = 0;
                            hole15_flag = 0; hole16_flag = 0; hole17_flag = 0; hole18_flag = 0; hole19_flag = 0; hole20_flag = 0;
                            closestText1.setText("-");closestText2.setText("-");closestText3.setText("-");closestText4.setText("-");

                            holeConter_l = 0;
                            hole1_flag_l = 0; hole2_flag_l = 0; hole3_flag_l = 0; hole4_flag_l = 0; hole5_flag_l = 0; hole6_flag_l = 0; hole7_flag_l = 0;
                            hole8_flag_l = 0; hole9_flag_l = 0; hole10_flag_l = 0; hole11_flag_l = 0; hole12_flag_l = 0; hole13_flag_l = 0; hole14_flag_l = 0;
                            hole15_flag_l = 0; hole16_flag_l = 0; hole17_flag_l = 0; hole18_flag_l = 0; hole19_flag_l = 0; hole20_flag_l = 0;
                            longDrivetxt1.setText("-");longDrivetxt2.setText("-");longDrivetxt3.setText("-");longDrivetxt4.setText("-");

                            holeConter_s = 0;
                            hole1_flag_s = 0; hole2_flag_s = 0; hole3_flag_s = 0; hole4_flag_s = 0; hole5_flag_s = 0; hole6_flag_s = 0; hole7_flag_s = 0;
                            hole8_flag_s = 0; hole9_flag_s = 0; hole10_flag_s = 0; hole11_flag_s = 0; hole12_flag_s = 0; hole13_flag_s = 0; hole14_flag_s = 0;
                            hole15_flag_s = 0; hole16_flag_s = 0; hole17_flag_s = 0; hole18_flag_s = 0; hole19_flag_s = 0; hole20_flag_s = 0;
                            straightDriveText1.setText("-"); straightDriveText2.setText("-"); straightDriveText3.setText("-"); straightDriveText4.setText("-");


                        }else {
                            totalHole = 4;

                            closestBTN3.setVisibility(View.GONE);
                            closestBTN4.setVisibility(View.GONE);
                            closestBTN1.setVisibility(View.VISIBLE);
                            closestBTN2.setVisibility(View.GONE);

                            longDriveBTN3.setVisibility(View.GONE);
                            longDriveBTN4.setVisibility(View.GONE);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                            longDriveBTN2.setVisibility(View.GONE);

                            straightBTN3.setVisibility(View.GONE);
                            straightBTN4.setVisibility(View.GONE);
                            straightBTN1.setVisibility(View.VISIBLE);
                            straightBTN2.setVisibility(View.GONE);

                            holeConter = 0;
                            hole1_flag = 0; hole2_flag = 0; hole3_flag = 0; hole4_flag = 0; hole5_flag = 0; hole6_flag = 0; hole7_flag = 0;
                            hole8_flag = 0; hole9_flag = 0; hole10_flag = 0; hole11_flag = 0; hole12_flag = 0; hole13_flag = 0; hole14_flag = 0;
                            hole15_flag = 0; hole16_flag = 0; hole17_flag = 0; hole18_flag = 0; hole19_flag = 0; hole20_flag = 0;
                            closestText1.setText("-");closestText2.setText("-");closestText3.setText("-");closestText4.setText("-");

                            holeConter_l = 0;
                            hole1_flag_l = 0; hole2_flag_l = 0; hole3_flag_l = 0; hole4_flag_l = 0; hole5_flag_l = 0; hole6_flag_l = 0; hole7_flag_l = 0;
                            hole8_flag_l = 0; hole9_flag_l = 0; hole10_flag_l = 0; hole11_flag_l = 0; hole12_flag_l = 0; hole13_flag_l = 0; hole14_flag_l = 0;
                            hole15_flag_l = 0; hole16_flag_l = 0; hole17_flag_l = 0; hole18_flag_l = 0; hole19_flag_l = 0; hole20_flag_l = 0;
                            longDrivetxt1.setText("-");longDrivetxt2.setText("-");longDrivetxt3.setText("-");longDrivetxt4.setText("-");

                            holeConter_s = 0;
                            hole1_flag_s = 0; hole2_flag_s = 0; hole3_flag_s = 0; hole4_flag_s = 0; hole5_flag_s = 0; hole6_flag_s = 0; hole7_flag_s = 0;
                            hole8_flag_s = 0; hole9_flag_s = 0; hole10_flag_s = 0; hole11_flag_s = 0; hole12_flag_s = 0; hole13_flag_s = 0; hole14_flag_s = 0;
                            hole15_flag_s = 0; hole16_flag_s = 0; hole17_flag_s = 0; hole18_flag_s = 0; hole19_flag_s = 0; hole20_flag_s = 0;
                            straightDriveText1.setText("-"); straightDriveText2.setText("-"); straightDriveText3.setText("-"); straightDriveText4.setText("-");

                        }

                        JSONObject jsonObject2 = jsonObject1.getJSONObject("tee_id");
                        listBean.setMenColor(jsonObject2.getString("Men"));
                        listBean.setLadiesColor(jsonObject2.getString("Ladies"));
                        listBean.setJuniorColor(jsonObject2.getString("Junior"));
                        listBean.setMessage(jsonObject.getString("msg"));

                        JSONArray jsonArrayCloseset = jsonObject1.getJSONArray("closest_pin");
                        JSONArray jsonArrayLong = jsonObject1.getJSONArray("long_drive");
                        JSONArray jsonArrayStraight = jsonObject1.getJSONArray("straight_drive");

                        for (int c = 0; c <jsonArrayCloseset.length();c++){
                            String clst_txt = jsonArrayCloseset.getJSONObject(c).getString("hole_number");
                            if (c==0){
                                closestText1.setText(clst_txt);
                                closestBTN1.setVisibility(View.VISIBLE);


                            }else if (c==1){
                                closestText2.setText(clst_txt);
                                closestBTN2.setVisibility(View.VISIBLE);


                            }else if (c==2){
                                closestText3.setText(clst_txt);
                                closestBTN3.setVisibility(View.VISIBLE);

                            }else if (c==3){
                                closestText4.setText(clst_txt);
                                closestBTN4.setVisibility(View.VISIBLE);


                            }
                        }

                        for (int l = 0; l <jsonArrayLong.length();l++){
                            String lng_txt = jsonArrayLong.getJSONObject(l).getString("hole_number");
                            if (l==0){
                                longDrivetxt1.setText(lng_txt);
                                longDriveBTN1.setVisibility(View.VISIBLE);



                            }else if (l==1){
                                longDrivetxt2.setText(lng_txt);
                                longDriveBTN2.setVisibility(View.VISIBLE);



                            }else if (l==2){
                                longDrivetxt3.setText(lng_txt);
                                longDriveBTN3.setVisibility(View.VISIBLE);


                            }else if (l==3){
                                longDrivetxt4.setText(lng_txt);
                                longDriveBTN4.setVisibility(View.VISIBLE);

                            }
                        }

                        for (int s = 0; s <jsonArrayStraight.length();s++){
                            String srght_txt = jsonArrayStraight.getJSONObject(s).getString("hole_number");
                            if (s==0){
                                straightDriveText1.setText(srght_txt);
                                straightBTN1.setVisibility(View.VISIBLE);


                            }else if (s==1){
                                straightDriveText2.setText(srght_txt);
                                straightBTN2.setVisibility(View.VISIBLE);


                            }else if (s==2){
                                straightDriveText3.setText(srght_txt);
                                straightBTN3.setVisibility(View.VISIBLE);


                            }else if (s==3){
                                straightDriveText4.setText(srght_txt);
                                straightBTN4.setVisibility(View.VISIBLE);

                            }
                        }



                        editcreateby = listBean.getEvent_name();
                        editgolfcourseName = listBean.getGolf_course_name();
                        edit_golf_course_id = listBean.getGolfID();
                        golf_couse_id = edit_golf_course_id;
                        Log.v("goghohohohoh",edit_golf_course_id);
                        editdateTime = listBean.getEvent_start_date_time();
                        editradioText = listBean.getNo_of_player();

                        noOFPlayer = listBean.getPlayers_in_game();


                        String teamIndividual = listBean.getIs_individual();
                        if (teamIndividual.equalsIgnoreCase("Individual")){
                            editteamText = listBean.getIs_individual();
                            ind_team_id = "2";
                        }else {
                            editteamText = "TEAM";
                            ind_team_id = "1";
                        }

                        format_id = listBean.getFormateID();
                        editformateName = listBean.getFormat_name();
                        editholeText = listBean.getTotal_hole_num();
                        editeventTypeText = listBean.getType();
                        editholeBackFront = listBean.getHole();
                        editcolorM = listBean.getMenColor();
                        editcolorJ = listBean.getJuniorColor();
                        editcolorW = listBean.getLadiesColor();
                        if (listBean.getIs_spot().equalsIgnoreCase("1")){
                            yesRadio.setChecked(true);
                            noRadio.setChecked(false);
                            spotPrizeLayout.setVisibility(View.VISIBLE);
                         //   spotPrizeLay.setVisibility(View.VISIBLE);
                            isSpot ="1";

                        }else {
                            yesRadio.setChecked(false);
                            noRadio.setChecked(true);
                            spotPrizeLayout.setVisibility(View.GONE);
                            //spotPrizeLay.setVisibility(View.GONE);
                            isSpot = "0";
                        }


                        if (editholeText!=null){
                            if (editholeText.equalsIgnoreCase("18")){
                                selectHolesLayout.setVisibility(View.GONE);
                            }else {
                                selectHolesLayout.setVisibility(View.VISIBLE);
                            }}

                        if (editgolfcourseName != null) {
                            selectedGolfCourseTEXT.setText(editgolfcourseName.toUpperCase());
                        }
                        if (editcreateby!=null) {
                            createdEventName.setText(editcreateby.toUpperCase());
                        }

                        //no_of_player.setText();
                        teamIndivi.setText(editteamText.toUpperCase());

                        playerNo_edit.setText(editradioText.toUpperCase());
                        dateTime_Text.setText(editdateTime);
                        if (editholeText !=null) {
                            noOfHole.setText(editholeText);
                        }
                        if (editeventTypeText!=null) {
                            eventType.setText(editeventTypeText);

                        }if (editholeBackFront!=null) {
                            hole.setText(editholeBackFront.toUpperCase());
                        }



                        String a = getIntent().getStringExtra("golfCourseName");
                        String c = getIntent().getStringExtra("golfCourseNameSearch");
                        String golfID = getIntent().getStringExtra("golf_couse_id");
                        String golfIDSEARCH = getIntent().getStringExtra("golf_couse_id_search");

                        if (a != null){
                            String golfName = a.toUpperCase();
                            selectedGolfCourseTEXT.setText(golfName);
                            golf_couse_id = golfID;
                            getTEEList1();

                            closestText1.setText("-");
                            closestText2.setText("-");
                            closestText3.setText("-");
                            closestText4.setText("-");

                            straightDriveText1.setText("-");
                            straightDriveText2.setText("-");
                            straightDriveText3.setText("-");
                            straightDriveText4.setText("-");

                            longDrivetxt1.setText("-");
                            longDrivetxt2.setText("-");
                            longDrivetxt3.setText("-");
                            longDrivetxt4.setText("-");


                        }else if (c !=null){
                            String golfNameSearch = c.toUpperCase();
                            selectedGolfCourseTEXT.setText(golfNameSearch);
                            golf_couse_id = golfIDSEARCH;
                            getTEEList1();

                            closestText1.setText("-");
                            closestText2.setText("-");
                            closestText3.setText("-");
                            closestText4.setText("-");

                            straightDriveText1.setText("-");
                            straightDriveText2.setText("-");
                            straightDriveText3.setText("-");
                            straightDriveText4.setText("-");

                            longDrivetxt1.setText("-");
                            longDrivetxt2.setText("-");
                            longDrivetxt3.setText("-");
                            longDrivetxt4.setText("-");

                        }else {


                            String b = editgolfcourseName;
                            if (b!=null) {
                                golfCourseName = b.toUpperCase();
                            }
                            golf_couse_id = edit_golf_course_id;
                            selectedGolfCourseTEXT.setText(golfCourseName);
                           // getTEEList1();
                        }
                        team_individual_spinner.setVisibility(View.GONE);

                        getFormatList();



                        if (editcolorM.equalsIgnoreCase("Red")){
                            mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                            mText.setTextColor(Color.WHITE);
                            mID = "3";
                            mColorName = "red";

                        }else if (editcolorM.equalsIgnoreCase("Green")){
                            mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                            mID = "6";
                            mColorName = "Green";

                        }else if (editcolorM.equalsIgnoreCase("Yellow")){
                            mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                            mID = "4";
                            mText.setTextColor(Color.BLACK);
                            mColorName = "Yellow";

                        }else if (editcolorM.equalsIgnoreCase("Black")){
                            mID = "1";
                            mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                            mColorName = "Black";

                        }else if (editcolorM.equalsIgnoreCase("White")){
                            mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                            mID = "5";
                            mText.setTextColor(Color.BLACK);
                            mColorName = "White";

                        }else if (editcolorM.equalsIgnoreCase("Blue")){
                            mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                            mID = "2";
                            mColorName = "Blue";

                        }else if (editcolorM.equalsIgnoreCase("Gold")){
                            mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                            mID = "7";
                            mText.setTextColor(Color.BLACK);
                            mColorName = "Gold";

                        }else if (editcolorM.equalsIgnoreCase("Silver")){
                            mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                            mID = "8";
                            mText.setTextColor(Color.BLACK);
                            mColorName = "Silver";

                        }

                        if (editcolorJ.equalsIgnoreCase("Red")){
                            jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                            jID = "3";
                            jColorName = "Red";

                        }else if (editcolorJ.equalsIgnoreCase("Green")){
                            jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                            jID = "6";
                            jColorName = "Green";

                        }else if (editcolorJ.equalsIgnoreCase("Yellow")){
                            jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                            jID = "4";
                            jText.setTextColor(Color.BLACK);
                            jColorName = "Yellow";

                        }else if (editcolorJ.equalsIgnoreCase("Black")){
                            jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                            jID = "1";
                            jColorName = "Black";

                        }else if (editcolorJ.equalsIgnoreCase("White")){
                            jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                            jText.setTextColor(Color.BLACK);
                            jID = "5";
                            jColorName = "White";

                        }else if (editcolorJ.equalsIgnoreCase("Blue")){
                            jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                            jID = "2";
                            jColorName = "Blue";

                        }else if (editcolorJ.equalsIgnoreCase("Gold")){
                            jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                            jText.setTextColor(Color.BLACK);
                            jID = "7";
                            jColorName = "Gold";

                        }else if (editcolorJ.equalsIgnoreCase("Silver")){
                            jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                            jText.setTextColor(Color.BLACK);
                            jID = "8";
                            jColorName = "Silver";

                        }

                        if (editcolorW.equalsIgnoreCase("Red")){
                            wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                            lID = "3";
                            wColorName = "Red";

                        }else if (editcolorW.equalsIgnoreCase("Green")){
                            wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                            lID = "6";
                            wColorName = "Green";

                        }else if (editcolorW.equalsIgnoreCase("Yellow")){
                            wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                            lID = "4";
                            wText.setTextColor(Color.BLACK);
                            wColorName = "Yellow";

                        }else if (editcolorW.equalsIgnoreCase("Black")){
                            wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                            lID = "1";
                            wColorName = "Black";

                        }else if (editcolorW.equalsIgnoreCase("White")){
                            wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                            lID = "5";
                            wText.setTextColor(Color.BLACK);

                            wColorName = "White";

                        }else if (editcolorW.equalsIgnoreCase("Blue")){
                            wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                            lID = "2";
                            wColorName = "Blue";

                        }else if (editcolorW.equalsIgnoreCase("Gold")){
                            wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                            lID = "7";
                            wText.setTextColor(Color.BLACK);
                            wColorName = "Gold";

                        }else if (editcolorW.equalsIgnoreCase("Silver")){
                            wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                            lID = "8";
                            wText.setTextColor(Color.BLACK);
                            wColorName = "Silver";

                        }//  list.add(listBean);
                    }


                }else{
                    String errorMessage = jsonObject.getString("msg");
                    Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    private void editEventTask() {


        final Dialog dialog = new Dialog(EditEventActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
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
        dialogText.setText("DO YOU WANT TO EDIT YOUR EVENT? ");

        dialog.show();



        editBTN.setVisibility(View.VISIBLE);
        createBTN.setVisibility(View.GONE);

        editBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                editEvent();
            }
        });


    }
    private void getCreateResponse(JSONObject response) {
        mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        EventPostBean createBean = new EventPostBean();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")){

                    String msg = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),""+msg,Toast.LENGTH_SHORT).show();
                    finish();
                }else{

                    String str = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void editEvent(){


        if (jColorName.equalsIgnoreCase("Red")){
            jID = "3";

        }else if (jColorName.equalsIgnoreCase("Green")){
            jID = "6";

        }else if (jColorName.equalsIgnoreCase("Yellow")){
            jID = "4";
            jText.setTextColor(Color.BLACK);

        }else if (jColorName.equalsIgnoreCase("Black")){
            jID = "1";

        }else if (jColorName.equalsIgnoreCase("White")){
            jID = "5";

        }else if (jColorName.equalsIgnoreCase("Blue")){
            jID = "2";

        }else if (jColorName.equalsIgnoreCase("Gold")){
            jID = "7";

        }else if (jColorName.equalsIgnoreCase("Silver")){

            jID = "8";

        }

        if (wColorName.equalsIgnoreCase("Red")){
            lID = "3";

        }else if (wColorName.equalsIgnoreCase("Green")){
            lID = "6";

        }else if (wColorName.equalsIgnoreCase("Yellow")){
            lID = "4";

        }else if (wColorName.equalsIgnoreCase("Black")){
            lID = "1";

        }else if (wColorName.equalsIgnoreCase("White")){
            lID = "5";


        }else if (wColorName.equalsIgnoreCase("Blue")){
            lID = "2";

        }else if (wColorName.equalsIgnoreCase("Gold")){
            lID = "7";

        }else if (wColorName.equalsIgnoreCase("Silver")){
            lID = "8";

        }


        if (mColorName.equalsIgnoreCase("Red")){
            mID = "3";

        }else if (mColorName.equalsIgnoreCase("Green")){
            mID = "6";

        }else if (mColorName.equalsIgnoreCase("Yellow")){
            mID = "4";


        }else if (mColorName.equalsIgnoreCase("Black")){
            mID = "1";

        }else if (mColorName.equalsIgnoreCase("White")){

            mID = "5";

        }else if (mColorName.equalsIgnoreCase("Blue")){
            mID = "2";

        }else if (mColorName.equalsIgnoreCase("Gold")){

            mID = "7";

        }else if (mColorName.equalsIgnoreCase("Silver")){
            mID = "8";

        }


        String datTime = dateTime_Text.getText().toString();
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
        String startTime = newFormat.substring(11,datTime.length()-1);

        String noOfPlayer = playerNo_edit.getText().toString();

        String eventID = getIntent().getStringExtra("eventID");

        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("event_id",eventID);
            jsonObject.putOpt("event_is_public",public_private);
            jsonObject.putOpt("event_golf_course_id", golf_couse_id);
            jsonObject.putOpt("version", "2");

            jsonObject.putOpt("event_name", createdEventName.getText().toString());

            jsonObject.putOpt("no_of_player", noOfPlayer);
            jsonObject.putOpt("event_format_id", format_id);
            jsonObject.putOpt("event_start_date",startDate);
            jsonObject.putOpt("event_start_time",startTime);

            jsonObject.putOpt("is_singlescreen",is_singlescreen);

            jsonObject.putOpt("event_is_public",eventTypetxt );

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
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.putOpt("men",mID);
            jsonObject1.putOpt("ladies",lID);
            jsonObject1.putOpt("junior",jID);
            jsonArray.put(jsonObject1);
            jsonObject.putOpt("event_tee_id",jsonArray);

            jsonObject.putOpt("is_spot",isSpot);

            JSONObject closestObject = new JSONObject();
            if (closestText1.getText().toString().equalsIgnoreCase("-")){

            }else {
                closestObject.putOpt("hole_1",closestText1.getText().toString());
            }
            if (closestText2.getText().toString().equalsIgnoreCase("-")){

            }else {
                closestObject.putOpt("hole_2",closestText2.getText().toString());
            }
            if (closestText3.getText().toString().equalsIgnoreCase("-")){

            }else {
                closestObject.putOpt("hole_3",closestText3.getText().toString());
            }
            if (closestText4.getText().toString().equalsIgnoreCase("-")){

            }else {
                closestObject.putOpt("hole_4",closestText4.getText().toString());
            }
            jsonObject.putOpt("closest_pin",closestObject);

            JSONObject longDriveObject = new JSONObject();
            if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")){

            }else {
                longDriveObject.putOpt("hole_1",longDrivetxt1.getText().toString());
            }
            if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")){

            }else {
                longDriveObject.putOpt("hole_2",longDrivetxt2.getText().toString());
            }
            if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")){

            }else {
                longDriveObject.putOpt("hole_3",longDrivetxt3.getText().toString());
            }
            if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")){

            }else {
                longDriveObject.putOpt("hole_4",longDrivetxt4.getText().toString());
            }
            jsonObject.putOpt("long_drive",longDriveObject);


            JSONObject sraightDriveObject = new JSONObject();
            if (straightDriveText1.getText().toString().equalsIgnoreCase("-")){

            }else {
                sraightDriveObject.putOpt("hole_1",straightDriveText1.getText().toString());
            }
            if (straightDriveText2.getText().toString().equalsIgnoreCase("-")){

            }else {
                sraightDriveObject.putOpt("hole_2",straightDriveText2.getText().toString());
            }
            if (straightDriveText3.getText().toString().equalsIgnoreCase("-")){

            }else {
                sraightDriveObject.putOpt("hole_3",straightDriveText3.getText().toString());
            }
            if (straightDriveText4.getText().toString().equalsIgnoreCase("-")){

            }else {
                sraightDriveObject.putOpt("hole_4",straightDriveText4.getText().toString());
            }
            jsonObject.putOpt("straight_drive",sraightDriveObject);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("Edit Event",jsonObject.toString());
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        pDialog.show();


        Log.v("editPost Data",jsonObject.toString());

        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.EDIT_EVENT_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("Edit Event", "OnResponse =" + response.toString());
                getCreateResponse(response);
                pDialog.cancel();

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
                pDialog.cancel();

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Edit EVENT", "Url= " + PUTTAPI.EDIT_EVENT_URL + " PostObject = " + jsonObject.toString());
        //requestQueue.add(jsonObjectRequest);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }


    public void dateTimeDialog(){

        final Dialog dialog = new Dialog(EditEventActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        dialog.setContentView(R.layout.date_time_new_dialog);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        final SingleDateAndTimePicker singeDateTime = (SingleDateAndTimePicker) dialog.findViewById(R.id.single_date_time);
        RelativeLayout doneBTN = (RelativeLayout) dialog.findViewById(R.id.timeDone_btn);
        previewDate = (TextView)dialog.findViewById(R.id.popup_preview_date);


        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        singeDateTime.setIsAmPm(false);
        singeDateTime.setCyclic(false);

        singeDateTime.setStepMinutes(1);

        Date d = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_YEAR, +(365*30));

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(d);
        calendar1.add(Calendar.DAY_OF_YEAR, -1);
        Date min = calendar1.getTime();
        Date max = calendar.getTime();

        singeDateTime.setMinDate(min);
        singeDateTime.setMaxDate(max);

        singeDateTime.setScrollBarDefaultDelayBeforeFade(0);



        singeDateTime.setListener(new SingleDateAndTimePicker.Listener() {
            @Override
            public void onDateChanged(String displayed, Date date) {


                display(displayed, date);


            }
        });

        dialog.show();
    }

    private void display(String toDisplay, Date date) {
        // Toast.makeText(this, toDisplay, Toast.LENGTH_SHORT).show();

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MMM-dd HH:mm");
        String formattedDate1 = df1.format(date.getTime());

        previewDate.setText(formattedDate1);

        dateTime_Text.setText(formattedDate1);
    }

    public void stokeMethod(final String strHole) {

         strH1 = closestText1.getText().toString();
         strH2 = closestText2.getText().toString();
         strH3 = closestText3.getText().toString();
         strH4 = closestText4.getText().toString();

        // Create custom dialog object
        final Dialog dialog = new Dialog(EditEventActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        //  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Include dialog.xml file
        dialog.setContentView(R.layout.select_hole_layout);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        dialog.show();

        TextView submit = (TextView) dialog.findViewById(R.id.done);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holeConter == 0) {
                    closestText1.setText("-");
                    closestBTN1.setVisibility(View.VISIBLE);
                    closestBTN2.setVisibility(View.GONE);
                    closestBTN3.setVisibility(View.GONE);
                    closestBTN4.setVisibility(View.GONE);
                } else {
                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();

                    if (!st1.equalsIgnoreCase("-")) {

                        closestBTN1.setVisibility(View.VISIBLE);
                    } else {
                        closestBTN1.setVisibility(View.GONE);
                    }
                    if (!st2.equalsIgnoreCase("-")) {

                        closestBTN2.setVisibility(View.VISIBLE);
                    } else {
                        closestBTN2.setVisibility(View.GONE);
                    }
                    if (!st3.equalsIgnoreCase("-")) {

                        closestBTN3.setVisibility(View.VISIBLE);
                    } else {
                        closestBTN3.setVisibility(View.GONE);
                    }
                    if (!st4.equalsIgnoreCase("-")) {

                        closestBTN4.setVisibility(View.VISIBLE);
                    } else {
                        closestBTN4.setVisibility(View.GONE);
                    }
                }

                dialog.cancel();
            }
        });

        holeParent = (LinearLayout) dialog.findViewById(R.id.holeParent);
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
        hole19 = (TextView) dialog.findViewById(R.id.hole19_text);
        hole20 = (TextView) dialog.findViewById(R.id.hole20_text);



        holeArray[0] = hole1;
        holeArray[1] = hole2;
        holeArray[2] = hole3;
        holeArray[3] = hole4;
        holeArray[4] = hole5;
        holeArray[5] = hole6;
        holeArray[6] = hole7;
        holeArray[7] = hole8;
        holeArray[8] = hole9;
        holeArray[9] = hole10;
        holeArray[10] = hole11;
        holeArray[11] = hole12;
        holeArray[12] = hole13;
        holeArray[13] = hole14;
        holeArray[14] = hole15;
        holeArray[15] = hole16;
        holeArray[16] = hole17;
        holeArray[17] = hole18;
        holeArray[18] = hole19;
        holeArray[19] = hole20;

        getStokeHoleList();





        if (hole1_flag == 1) {
            hole1.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole1.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole2_flag == 1) {
            hole2.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole2.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole3_flag == 1) {
            hole3.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole3.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole4_flag == 1) {
            hole4.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole4.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole5_flag == 1) {
            hole5.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole5.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole6_flag == 1) {
            hole6.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole6.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole7_flag == 1) {
            hole7.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole7.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole8_flag == 1) {
            hole8.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole8.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole9_flag == 1) {
            hole9.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole9.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole10_flag == 1) {
            hole10.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole10.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole11_flag == 1) {
            hole11.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole11.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole12_flag == 1) {
            hole12.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole12.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole13_flag == 1) {
            hole13.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole13.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole14_flag == 1) {
            hole14.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole14.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole15_flag == 1) {
            hole15.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole15.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole16_flag == 1) {
            hole16.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole16.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole17_flag == 1) {
            hole17.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole17.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole18_flag == 1) {
            hole18.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole18.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole19_flag == 1) {
            hole19.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole19.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole20_flag == 1) {
            hole20.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole20.setBackgroundResource(R.drawable.hole_background);
        }

        hole1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hole1_flag == 0 && holeConter < totalHole) {

                    hole1_flag = 1;
                    hole1.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole1.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole1_flag == 1 && holeConter <= totalHole) {
                    hole1_flag = 0;

                    hole1.setBackgroundResource(R.drawable.hole_background);

                    String value = hole1.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();

                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }

            }
        });
        hole2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole2_flag == 0 && holeConter < totalHole) {

                    hole2_flag = 1;
                    hole2.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole2.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }


                    holeConter++;

                } else if (hole2_flag == 1 && holeConter <= totalHole) {
                    hole2_flag = 0;

                    hole2.setBackgroundResource(R.drawable.hole_background);

                    String value = hole2.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }

                    holeConter--;
                }


            }
        });
        hole3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole3_flag == 0 && holeConter < totalHole) {

                    hole3_flag = 1;
                    hole3.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole3.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole3_flag == 1 && holeConter <= totalHole) {
                    hole3_flag = 0;

                    hole3.setBackgroundResource(R.drawable.hole_background);

                    String value = hole3.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }

            }
        });
        hole4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole4_flag == 0 && holeConter < totalHole) {

                    hole4_flag = 1;
                    hole4.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole4.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }


                    holeConter++;

                } else if (hole4_flag == 1 && holeConter <= totalHole) {
                    hole4_flag = 0;

                    hole4.setBackgroundResource(R.drawable.hole_background);
                    String value = hole4.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }

                    holeConter--;
                }


            }
        });
        hole5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole5_flag == 0 && holeConter < totalHole) {

                    hole5_flag = 1;
                    hole5.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole5.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }
                    holeConter++;

                } else if (hole5_flag == 1 && holeConter <= totalHole) {
                    hole5_flag = 0;

                    hole5.setBackgroundResource(R.drawable.hole_background);
                    String value = hole5.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }


            }
        });
        hole6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole6_flag == 0 && holeConter < totalHole) {

                    hole6_flag = 1;
                    hole6.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole6.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole6_flag == 1 && holeConter <= totalHole) {
                    hole6_flag = 0;

                    hole6.setBackgroundResource(R.drawable.hole_background);
                    String value = hole6.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }

                    holeConter--;
                }

            }
        });
        hole7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole7_flag == 0 && holeConter < totalHole) {
                    hole7_flag = 1;
                    hole7.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole7.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole7_flag == 1 && holeConter <= totalHole) {
                    hole7_flag = 0;

                    hole7.setBackgroundResource(R.drawable.hole_background);

                    String value = hole7.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }
                    holeConter--;
                }


            }
        });
        hole8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole8_flag == 0 && holeConter < totalHole) {

                    hole8_flag = 1;
                    hole8.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole8.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }


                    holeConter++;

                } else if (hole8_flag == 1 && holeConter <= totalHole) {
                    hole8_flag = 0;

                    hole8.setBackgroundResource(R.drawable.hole_background);

                    String value = hole8.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }


            }
        });
        hole9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole9_flag == 0 && holeConter < totalHole) {

                    hole9_flag = 1;

                    hole9.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole9.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole9_flag == 1 && holeConter <= totalHole) {
                    hole9_flag = 0;

                    hole9.setBackgroundResource(R.drawable.hole_background);
                    String value = hole9.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }
                    holeConter--;
                }


            }
        });
        hole10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole10_flag == 0 && holeConter < totalHole) {

                    hole10_flag = 1;
                    hole10.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole10.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole10_flag == 1 && holeConter <= totalHole) {
                    hole10_flag = 0;

                    hole10.setBackgroundResource(R.drawable.hole_background);
                    String value = hole10.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }
                    holeConter--;
                }

            }
        });
        hole11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole11_flag == 0 && holeConter < totalHole) {

                    hole11_flag = 1;
                    hole11.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole11.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }


                    holeConter++;

                } else if (hole11_flag == 1 && holeConter <= totalHole) {
                    hole11_flag = 0;

                    hole11.setBackgroundResource(R.drawable.hole_background);

                    String value = hole11.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }

            }
        });
        hole12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole12_flag == 0 && holeConter < totalHole) {

                    hole12_flag = 1;
                    hole12.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole12.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }


                    holeConter++;

                } else if (hole12_flag == 1 && holeConter <= totalHole) {
                    hole12_flag = 0;

                    hole12.setBackgroundResource(R.drawable.hole_background);

                    String value = hole12.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }

            }
        });
        hole13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole13_flag == 0 && holeConter < totalHole) {

                    hole13_flag = 1;

                    hole13.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole13.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }


                    holeConter++;

                } else if (hole13_flag == 1 && holeConter <= totalHole) {
                    hole13_flag = 0;

                    hole13.setBackgroundResource(R.drawable.hole_background);

                    String value = hole13.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }
                    holeConter--;
                }

            }
        });
        hole14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole14_flag == 0 && holeConter < totalHole) {

                    hole14_flag = 1;
                    hole14.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole14.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole14_flag == 1 && holeConter <= totalHole) {
                    hole14_flag = 0;

                    hole14.setBackgroundResource(R.drawable.hole_background);

                    String value = hole14.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }

            }
        });
        hole15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole15_flag == 0 && holeConter < totalHole) {

                    hole15_flag = 1;
                    hole15.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole15.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole15_flag == 1 && holeConter <= totalHole) {
                    hole15_flag = 0;

                    hole15.setBackgroundResource(R.drawable.hole_background);

                    String value = hole15.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }

            }
        });
        hole16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole16_flag == 0 && holeConter < totalHole) {

                    hole16_flag = 1;

                    hole16.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole16.getText().toString();
                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole16_flag == 1 && holeConter <= totalHole) {
                    hole16_flag = 0;

                    hole16.setBackgroundResource(R.drawable.hole_background);
                    String value = hole16.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }

            }
        });
        hole17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole17_flag == 0 && holeConter < totalHole) {

                    hole17_flag = 1;
                    hole17.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole17.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole17_flag == 1 && holeConter <= totalHole) {
                    hole17_flag = 0;

                    hole17.setBackgroundResource(R.drawable.hole_background);

                    String value = hole17.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }

            }
        });
        hole18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole18_flag == 0 && holeConter < totalHole) {

                    hole18_flag = 1;
                    hole18.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole18.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }


                    holeConter--;

                } else if (hole18_flag == 1 && holeConter <= totalHole) {
                    hole18_flag = 0;

                    hole18.setBackgroundResource(R.drawable.hole_background);
                    String value = hole18.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }
                    holeConter--;
                }

            }
        });
        hole19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole19_flag == 0 && holeConter < totalHole) {

                    hole19_flag = 1;

                    hole19.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole19.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }

                    holeConter++;

                } else if (hole19_flag == 1 && holeConter <= totalHole) {
                    hole19_flag = 0;

                    hole19.setBackgroundResource(R.drawable.hole_background);

                    String value = hole19.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();


                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }


                    holeConter--;
                }

            }
        });
        hole20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hole20_flag == 0 && holeConter < totalHole) {

                    hole20_flag = 1;
                    hole20.setBackgroundResource(R.drawable.hole_selecte);

                    String hole1Text = hole20.getText().toString();

                    if (closestText1.getText().toString().equalsIgnoreCase("-")) {
                        closestText1.setText(hole1Text);
                        closestBTN1.setVisibility(View.VISIBLE);
                    } else if (closestText2.getText().toString().equalsIgnoreCase("-")) {
                        closestText2.setText(hole1Text);
                        closestBTN2.setVisibility(View.VISIBLE);
                    } else if (closestText3.getText().toString().equalsIgnoreCase("-")) {
                        closestText3.setText(hole1Text);
                        closestBTN3.setVisibility(View.VISIBLE);
                    } else if (closestText4.getText().toString().equalsIgnoreCase("-")) {
                        closestText4.setText(hole1Text);
                        closestBTN4.setVisibility(View.VISIBLE);
                    }
                    holeConter++;

                } else if (hole20_flag == 1 && holeConter <= totalHole) {
                    hole20_flag = 0;

                    hole20.setBackgroundResource(R.drawable.hole_background);

                    String value = hole20.getText().toString();

                    String st1 = closestText1.getText().toString();
                    String st2 = closestText2.getText().toString();
                    String st3 = closestText3.getText().toString();
                    String st4 = closestText4.getText().toString();

                    if (value.equalsIgnoreCase(st1)) {
                        closestText1.setText("-");
                        closestBTN1.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st2)) {
                        closestText2.setText("-");
                        closestBTN2.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st3)) {
                        closestText3.setText("-");
                        closestBTN3.setVisibility(View.GONE);
                    } else if (value.equalsIgnoreCase(st4)) {
                        closestText4.setText("-");
                        closestBTN4.setVisibility(View.GONE);
                    }

                    holeConter--;
                }

            }
        });
    }


    public void stokeMethod1(final String stringLong) {


        strL1 = straightDriveText1.getText().toString();
        strL2 = straightDriveText2.getText().toString();
        strL3 = straightDriveText3.getText().toString();
        strL4 = straightDriveText4.getText().toString();
        // Create custom dialog object
        final Dialog dialog = new Dialog(EditEventActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        //  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Include dialog.xml file
        dialog.setContentView(R.layout.select_hole_layout);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        dialog.show();

        TextView submit = (TextView) dialog.findViewById(R.id.done);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holeConter_l == 0) {
                    longDrivetxt1.setText("-");
                    longDriveBTN1.setVisibility(View.VISIBLE);
                    longDriveBTN2.setVisibility(View.GONE);
                    longDriveBTN3.setVisibility(View.GONE);
                    longDriveBTN4.setVisibility(View.GONE);
                } else {
                    String st1 = longDrivetxt1.getText().toString();
                    String st2 = longDrivetxt2.getText().toString();
                    String st3 = longDrivetxt3.getText().toString();
                    String st4 = longDrivetxt4.getText().toString();

                    if (!st1.equalsIgnoreCase("-")) {

                        longDriveBTN1.setVisibility(View.VISIBLE);
                    }
                    if (!st2.equalsIgnoreCase("-")) {

                        longDriveBTN2.setVisibility(View.VISIBLE);
                    }
                    if (!st3.equalsIgnoreCase("-")) {

                        longDriveBTN3.setVisibility(View.VISIBLE);
                    }
                    if (!st4.equalsIgnoreCase("-")) {

                        longDriveBTN4.setVisibility(View.VISIBLE);
                    }
                }
                dialog.cancel();
            }
        });

        holeParent = (LinearLayout) dialog.findViewById(R.id.holeParent);
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
        hole19 = (TextView) dialog.findViewById(R.id.hole19_text);
        hole20 = (TextView) dialog.findViewById(R.id.hole20_text);


        holeArray2[0] = hole1;
        holeArray2[1] = hole2;
        holeArray2[2] = hole3;
        holeArray2[3] = hole4;
        holeArray2[4] = hole5;
        holeArray2[5] = hole6;
        holeArray2[6] = hole7;
        holeArray2[7] = hole8;
        holeArray2[8] = hole9;
        holeArray2[9] = hole10;
        holeArray2[10] = hole11;
        holeArray2[11] = hole12;
        holeArray2[12] = hole13;
        holeArray2[13] = hole14;
        holeArray2[14] = hole15;
        holeArray2[15] = hole16;
        holeArray2[16] = hole17;
        holeArray2[17] = hole18;
        holeArray2[18] = hole19;
        holeArray2[19] = hole20;

        getStokeHoleList1(stringLong);


        if (hole1_flag_l == 1) {
            hole1.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole1.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole2_flag_l == 1) {
            hole2.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole2.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole3_flag_l == 1) {
            hole3.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole3.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole4_flag_l == 1) {
            hole4.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole4.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole5_flag_l == 1) {
            hole5.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole5.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole6_flag_l == 1) {
            hole6.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole6.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole7_flag_l == 1) {
            hole7.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole7.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole8_flag_l == 1) {
            hole8.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole8.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole9_flag_l == 1) {
            hole9.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole9.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole10_flag_l == 1) {
            hole10.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole10.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole11_flag_l == 1) {
            hole11.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole11.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole12_flag_l == 1) {
            hole12.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole12.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole13_flag_l == 1) {
            hole13.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole13.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole14_flag_l == 1) {
            hole14.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole14.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole15_flag_l == 1) {
            hole15.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole15.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole16_flag_l == 1) {
            hole16.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole16.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole17_flag_l == 1) {
            hole17.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole17.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole18_flag_l == 1) {
            hole18.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole18.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole19_flag_l == 1) {
            hole19.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole19.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole20_flag_l == 1) {
            hole20.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole20.setBackgroundResource(R.drawable.hole_background);
        }


        hole1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole1.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole1_flag_l == 0 && holeConter_l < totalHole) {

                        hole1_flag_l = 1;

                        hole1.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole1_flag_l == 1 && holeConter_l <= totalHole) {

                        hole1_flag_l = 0;

                        hole1.setBackgroundResource(R.drawable.hole_background);

                        String value = hole1.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {

                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();


                }
            }
        });
        hole2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole2.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {

                    if (hole2_flag_l == 0 && holeConter_l < totalHole) {

                        hole2_flag_l = 1;

                        hole2.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole2_flag_l == 1 && holeConter_l <= totalHole) {
                        hole2_flag_l = 0;

                        hole2.setBackgroundResource(R.drawable.hole_background);
                        String value = hole2.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {

                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();


                }
            }
        });
        hole3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole3.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {

                    if (hole3_flag_l == 0 && holeConter_l < totalHole) {
                        hole3_flag_l = 1;
                        hole3.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole3_flag_l == 1 && holeConter_l <= totalHole) {
                        hole3_flag_l = 0;

                        hole3.setBackgroundResource(R.drawable.hole_background);
                        String value = hole3.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole4.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole4_flag_l == 0 && holeConter_l < totalHole) {

                        hole4_flag_l = 1;

                        hole4.setBackgroundResource(R.drawable.hole_selecte);


                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole4_flag_l == 1 && holeConter_l <= totalHole) {
                        hole4_flag_l = 0;

                        hole4.setBackgroundResource(R.drawable.hole_background);
                        String value = hole4.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole5.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole5_flag_l == 0 && holeConter_l < totalHole) {

                        hole5_flag_l = 1;

                        hole5.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole5_flag_l == 1 && holeConter_l <= totalHole) {
                        hole5_flag_l = 0;

                        hole5.setBackgroundResource(R.drawable.hole_background);
                        String value = hole5.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole6.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole6_flag_l == 0 && holeConter_l < totalHole) {

                        hole6_flag_l = 1;

                        hole6.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole6_flag_l == 1 && holeConter_l <= totalHole) {
                        hole6_flag_l = 0;

                        hole6.setBackgroundResource(R.drawable.hole_background);
                        String value = hole6.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole7.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole7_flag_l == 0 && holeConter_l < totalHole) {

                        hole7_flag_l = 1;

                        hole7.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole7_flag_l == 1 && holeConter_l <= totalHole) {
                        hole7_flag_l = 0;

                        hole7.setBackgroundResource(R.drawable.hole_background);
                        String value = hole7.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole8.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole8_flag_l == 0 && holeConter_l < totalHole) {
                        hole8_flag_l = 1;
                        hole8.setBackgroundResource(R.drawable.hole_selecte);


                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole8_flag_l == 1 && holeConter_l <= totalHole) {
                        hole8_flag_l = 0;

                        hole8.setBackgroundResource(R.drawable.hole_background);
                        String value = hole8.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole9.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole9_flag_l == 0 && holeConter_l < totalHole) {
                        hole9_flag_l = 1;
                        hole9.setBackgroundResource(R.drawable.hole_selecte);


                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_l++;

                    } else if (hole9_flag_l == 1 && holeConter_l <= totalHole) {
                        hole9_flag_l = 0;

                        hole9.setBackgroundResource(R.drawable.hole_background);
                        String value = hole9.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole10.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole10_flag_l == 0 && holeConter_l < totalHole) {
                        hole10_flag_l = 1;
                        hole10.setBackgroundResource(R.drawable.hole_selecte);


                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole10_flag_l == 1 && holeConter_l <= totalHole) {
                        hole10_flag_l = 0;

                        hole10.setBackgroundResource(R.drawable.hole_background);
                        String value = hole10.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole11.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole11_flag_l == 0 && holeConter_l < totalHole) {
                        hole11_flag_l = 1;
                        hole11.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole11_flag_l == 1 && holeConter_l <= totalHole) {
                        hole11_flag_l = 0;

                        hole11.setBackgroundResource(R.drawable.hole_background);
                        String value = hole11.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole12.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole12_flag_l == 0 && holeConter_l < totalHole) {

                        hole12_flag_l = 1;

                        hole12.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole12_flag_l == 1 && holeConter_l <= totalHole) {
                        hole12_flag_l = 0;

                        hole12.setBackgroundResource(R.drawable.hole_background);
                        String value = hole12.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole13.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole13_flag_l == 0 && holeConter_l < totalHole) {

                        hole13_flag_l = 1;

                        hole13.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole13_flag_l == 1 && holeConter_l <= totalHole) {
                        hole13_flag_l = 0;

                        hole13.setBackgroundResource(R.drawable.hole_background);

                        String value = hole13.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole14.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole14_flag_l == 0 && holeConter_l < totalHole) {

                        hole14_flag_l = 1;

                        hole14.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole14_flag_l == 1 && holeConter_l <= totalHole) {
                        hole14_flag_l = 0;

                        hole14.setBackgroundResource(R.drawable.hole_background);
                        String value = hole14.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }
                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole15.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {

                    if (hole15_flag_l == 0 && holeConter_l < totalHole) {

                        hole15_flag_l = 1;

                        hole15.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole15_flag_l == 1 && holeConter_l <= totalHole) {
                        hole15_flag_l = 0;

                        hole15.setBackgroundResource(R.drawable.hole_background);
                        String value = hole15.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole16.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole16_flag_l == 0 && holeConter_l < totalHole) {

                        hole16_flag_l = 1;

                        hole16.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_l++;

                    } else if (hole16_flag_l == 1 && holeConter_l <= totalHole) {
                        hole16_flag_l = 0;

                        hole16.setBackgroundResource(R.drawable.hole_background);

                        String value = hole16.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole17.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {

                    if (hole17_flag_l == 0 && holeConter_l < totalHole) {

                        hole17_flag_l = 1;

                        hole17.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole17_flag_l == 1 && holeConter_l <= totalHole) {
                        hole17_flag_l = 0;

                        hole17.setBackgroundResource(R.drawable.hole_background);

                        String value = hole17.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole18.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole18_flag_l == 0 && holeConter_l < totalHole) {

                        hole18_flag_l = 1;

                        hole18.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole18_flag_l == 1 && holeConter_l <= totalHole) {
                        hole18_flag_l = 0;

                        hole18.setBackgroundResource(R.drawable.hole_background);
                        String value = hole18.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole19.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole19_flag_l == 0 && holeConter_l < totalHole) {

                        hole19_flag_l = 1;

                        hole19.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole19_flag_l == 1 && holeConter_l <= totalHole) {
                        hole19_flag_l = 0;

                        hole19.setBackgroundResource(R.drawable.hole_background);

                        String value = hole19.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole20.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4)) ) {


                    if (hole20_flag_l == 0 && holeConter_l < totalHole) {

                        hole20_flag_l = 1;

                        hole20.setBackgroundResource(R.drawable.hole_selecte);

                        if (longDrivetxt1.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt1.setText(hole1Text);
                            longDriveBTN1.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt2.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt2.setText(hole1Text);
                            longDriveBTN2.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt3.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt3.setText(hole1Text);
                            longDriveBTN3.setVisibility(View.VISIBLE);
                        } else if (longDrivetxt4.getText().toString().equalsIgnoreCase("-")) {
                            longDrivetxt4.setText(hole1Text);
                            longDriveBTN4.setVisibility(View.VISIBLE);
                        }

                        holeConter_l++;

                    } else if (hole20_flag_l == 1 && holeConter_l <= totalHole) {
                        hole20_flag_l = 0;

                        hole20.setBackgroundResource(R.drawable.hole_background);

                        String value = hole20.getText().toString();

                        String st1 = longDrivetxt1.getText().toString();
                        String st2 = longDrivetxt2.getText().toString();
                        String st3 = longDrivetxt3.getText().toString();
                        String st4 = longDrivetxt4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            longDrivetxt1.setText("-");
                            longDriveBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            longDrivetxt2.setText("-");
                            longDriveBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            longDrivetxt3.setText("-");
                            longDriveBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            longDrivetxt4.setText("-");
                            longDriveBTN4.setVisibility(View.GONE);
                        }


                        holeConter_l--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void stokeMethod2(final String stringStraight) {


        strS1 = longDrivetxt1.getText().toString();
        strS2 = longDrivetxt2.getText().toString();
        strS3 = longDrivetxt3.getText().toString();
        strS4 = longDrivetxt4.getText().toString();


        final Dialog dialog = new Dialog(EditEventActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        //  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Include dialog.xml file
        dialog.setContentView(R.layout.select_hole_layout);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        dialog.show();

        TextView submit = (TextView) dialog.findViewById(R.id.done);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("straightHoleCounter",""+holeConter_s);

                if (holeConter_s == 0) {
                    straightDriveText1.setText("-");
                    straightBTN1.setVisibility(View.VISIBLE);
                    straightBTN2.setVisibility(View.GONE);
                    straightBTN3.setVisibility(View.GONE);
                    straightBTN4.setVisibility(View.GONE);
                } else {
                    String st1 = straightDriveText1.getText().toString();
                    String st2 = straightDriveText2.getText().toString();
                    String st3 = straightDriveText3.getText().toString();
                    String st4 = straightDriveText4.getText().toString();

                    if (!st1.equalsIgnoreCase("-")) {

                        straightBTN1.setVisibility(View.VISIBLE);
                    } else {
                        straightBTN1.setVisibility(View.GONE);
                    }
                    if (!st2.equalsIgnoreCase("-")) {

                        straightBTN2.setVisibility(View.VISIBLE);
                    } else {
                        straightBTN2.setVisibility(View.GONE);
                    }
                    if (!st3.equalsIgnoreCase("-")) {

                        straightBTN3.setVisibility(View.VISIBLE);
                    } else {
                        straightBTN3.setVisibility(View.GONE);
                    }
                    if (!st4.equalsIgnoreCase("-")) {

                        straightBTN4.setVisibility(View.VISIBLE);
                    } else {
                        straightBTN4.setVisibility(View.GONE);
                    }
                }

                dialog.cancel();
            }
        });

        holeParent = (LinearLayout) dialog.findViewById(R.id.holeParent);
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
        hole19 = (TextView) dialog.findViewById(R.id.hole19_text);
        hole20 = (TextView) dialog.findViewById(R.id.hole20_text);


        holeArray2[0] = hole1;
        holeArray2[1] = hole2;
        holeArray2[2] = hole3;
        holeArray2[3] = hole4;
        holeArray2[4] = hole5;
        holeArray2[5] = hole6;
        holeArray2[6] = hole7;
        holeArray2[7] = hole8;
        holeArray2[8] = hole9;
        holeArray2[9] = hole10;
        holeArray2[10] = hole11;
        holeArray2[11] = hole12;
        holeArray2[12] = hole13;
        holeArray2[13] = hole14;
        holeArray2[14] = hole15;
        holeArray2[15] = hole16;
        holeArray2[16] = hole17;
        holeArray2[17] = hole18;
        holeArray2[18] = hole19;
        holeArray2[19] = hole20;

        getStokeHoleList1(stringStraight);


        if (hole1_flag_s == 1) {
            hole1.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole1.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole2_flag_s == 1) {
            hole2.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole2.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole3_flag_s == 1) {
            hole3.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole3.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole4_flag_s == 1) {
            hole4.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole4.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole5_flag_s == 1) {
            hole5.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole5.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole6_flag_s == 1) {
            hole6.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole6.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole7_flag_s == 1) {
            hole7.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole7.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole8_flag_s == 1) {
            hole8.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole8.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole9_flag_s == 1) {
            hole9.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole9.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole10_flag_s == 1) {
            hole10.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole10.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole11_flag_s == 1) {
            hole11.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole11.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole12_flag_s == 1) {
            hole12.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole12.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole13_flag_s == 1) {
            hole13.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole13.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole14_flag_s == 1) {
            hole14.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole14.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole15_flag_s == 1) {
            hole15.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole15.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole16_flag_s == 1) {
            hole16.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole16.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole17_flag_s == 1) {
            hole17.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole17.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole18_flag_s == 1) {
            hole18.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole18.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole19_flag_s == 1) {
            hole19.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole19.setBackgroundResource(R.drawable.hole_background);
        }
        if (hole20_flag_s == 1) {
            hole20.setBackgroundResource(R.drawable.hole_selecte);
        } else {
            hole20.setBackgroundResource(R.drawable.hole_background);
        }

        hole1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole1.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {

                    if (hole1_flag_s == 0 && holeConter_s < totalHole) {
                        hole1_flag_s = 1;
                        hole1.setBackgroundResource(R.drawable.hole_selecte);


                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole1_flag_s == 1 && holeConter_s <= totalHole) {
                        hole1_flag_s = 0;

                        hole1.setBackgroundResource(R.drawable.hole_background);

                        String value = hole1.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }

                        holeConter_s--;
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();


                }

            }


        });
        hole2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole2.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {

                    if (hole2_flag_s == 0 && holeConter_s < totalHole) {
                        hole2_flag_s = 1;
                        hole2.setBackgroundResource(R.drawable.hole_selecte);


                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole2_flag_s == 1 && holeConter_s <= totalHole) {
                        hole2_flag_s = 0;

                        hole2.setBackgroundResource(R.drawable.hole_background);

                        String value = hole2.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.VISIBLE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole3.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {

                    if (hole3_flag_s == 0 && holeConter_s < totalHole) {
                        hole3_flag_s = 1;
                        hole3.setBackgroundResource(R.drawable.hole_selecte);


                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;

                    } else if (hole3_flag_s == 1 && holeConter_s <= totalHole) {
                        hole3_flag_s = 0;

                        hole3.setBackgroundResource(R.drawable.hole_background);

                        String value = hole3.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        hole4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole4.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {


                    if (hole4_flag_s == 0 && holeConter_s < totalHole) {
                        hole4_flag_s = 1;
                        hole4.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole4_flag_s == 1 && holeConter_s <= totalHole) {
                        hole4_flag_s = 0;

                        hole4.setBackgroundResource(R.drawable.hole_background);

                        String value = hole4.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        hole5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole5.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {

                    if (hole5_flag_s == 0 && holeConter_s < totalHole) {
                        hole5_flag_s = 1;
                        hole5.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole5_flag_s == 1 && holeConter_s <= totalHole) {
                        hole5_flag_s = 0;

                        hole5.setBackgroundResource(R.drawable.hole_background);

                        String value = hole5.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole6.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {

                    if (hole6_flag_s == 0 && holeConter_s < totalHole) {
                        hole6_flag_s = 1;
                        hole6.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole6_flag_s == 1 && holeConter_s <= totalHole) {
                        hole6_flag_s = 0;

                        hole6.setBackgroundResource(R.drawable.hole_background);

                        String value = hole6.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        hole7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole7.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole7_flag_s == 0 && holeConter_s < totalHole) {
                        hole7_flag_s = 1;
                        hole7.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole7_flag_s == 1 && holeConter_s <= totalHole) {
                        hole7_flag_s = 0;

                        hole7.setBackgroundResource(R.drawable.hole_background);

                        String value = hole7.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole8.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole8_flag_s == 0 && holeConter_s < totalHole) {
                        hole8_flag_s = 1;
                        hole8.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole8_flag_s == 1 && holeConter_s <= totalHole) {
                        hole8_flag_s = 0;

                        hole8.setBackgroundResource(R.drawable.hole_background);

                        String value = hole8.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }

                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole9.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole9_flag_s == 0 && holeConter_s < totalHole) {
                        hole9_flag_s = 1;
                        hole9.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole9_flag_s == 1 && holeConter_s <= totalHole) {
                        hole9_flag_s = 0;

                        hole9.setBackgroundResource(R.drawable.hole_background);

                        String value = hole9.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }

                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        hole10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole10.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole10_flag_s == 0 && holeConter_s < totalHole) {
                        hole10_flag_s = 1;
                        hole10.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole10_flag_s == 1 && holeConter_s <= totalHole) {
                        hole10_flag_s = 0;

                        hole10.setBackgroundResource(R.drawable.hole_background);

                        String value = hole10.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        hole11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole11.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole11_flag_s == 0 && holeConter_s < totalHole) {
                        hole11_flag_s = 1;
                        hole11.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole11_flag_s == 1 && holeConter_s <= totalHole) {
                        hole11_flag_s = 0;

                        hole11.setBackgroundResource(R.drawable.hole_background);

                        String value = hole11.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole12.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {

                    if (hole12_flag_s == 0 && holeConter_s < totalHole) {
                        hole12_flag_s = 1;
                        hole12.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole12_flag_s == 1 && holeConter_s <= totalHole) {
                        hole12_flag_s = 0;

                        hole12.setBackgroundResource(R.drawable.hole_background);

                        String value = hole12.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        hole13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole13.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole13_flag_s == 0 && holeConter_s < totalHole) {
                        hole13_flag_s = 1;
                        hole13.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole13_flag_s == 1 && holeConter_s <= totalHole) {
                        hole13_flag_s = 0;

                        hole13.setBackgroundResource(R.drawable.hole_background);

                        String value = hole13.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        hole14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole14.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole14_flag_s == 0 && holeConter_s < totalHole) {
                        hole14_flag_s = 1;
                        hole14.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole14_flag_s == 1 && holeConter_s <= totalHole) {
                        hole14_flag_s = 0;

                        hole14.setBackgroundResource(R.drawable.hole_background);

                        String value = hole14.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();
                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }

                        holeConter_s--;
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole15.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole15_flag_s == 0 && holeConter_s < totalHole) {
                        hole15_flag_s = 1;
                        hole15.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole15_flag_s == 1 && holeConter_s <= totalHole) {
                        hole15_flag_s = 0;

                        hole15.setBackgroundResource(R.drawable.hole_background);

                        String value = hole15.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }

                        holeConter_s--;
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole16.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole16_flag_s == 0 && holeConter_s < totalHole) {
                        hole16_flag_s = 1;
                        hole16.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole1_flag_s == 1 && holeConter_s <= totalHole) {
                        hole1_flag_s = 0;

                        hole1.setBackgroundResource(R.drawable.hole_background);

                        String value = hole16.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.VISIBLE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole17.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole17_flag_s == 0 && holeConter_s < totalHole) {
                        hole17_flag_s = 1;
                        hole17.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;

                    } else if (hole17_flag_s == 1 && holeConter_s <= totalHole) {
                        hole17_flag_s = 0;

                        hole17.setBackgroundResource(R.drawable.hole_background);

                        String value = hole17.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }


                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole18.getText().toString();
                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole18_flag_s == 0 && holeConter_s < totalHole) {
                        hole18_flag_s = 1;
                        hole18.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole18_flag_s == 1 && holeConter_s <= totalHole) {
                        hole18_flag_s = 0;

                        hole18.setBackgroundResource(R.drawable.hole_background);
                        String value = hole18.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();

                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }
                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole19.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {
                    if (hole19_flag_s == 0 && holeConter_s < totalHole) {
                        hole19_flag_s = 1;
                        hole19.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole19_flag_s == 1 && holeConter_s <= totalHole) {
                        hole19_flag_s = 0;

                        hole19.setBackgroundResource(R.drawable.hole_background);

                        String value = hole19.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();
                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }

                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        hole20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole20.getText().toString();

                if ( (!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4)) ) {

                    if (hole20_flag_s == 0 && holeConter_s < totalHole) {
                        hole20_flag_s = 1;
                        hole20.setBackgroundResource(R.drawable.hole_selecte);

                        if (straightDriveText1.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText1.setText(hole1Text);
                            straightBTN1.setVisibility(View.VISIBLE);


                        } else if (straightDriveText2.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText2.setText(hole1Text);
                            straightBTN2.setVisibility(View.VISIBLE);


                        } else if (straightDriveText3.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText3.setText(hole1Text);
                            straightBTN3.setVisibility(View.VISIBLE);


                        } else if (straightDriveText4.getText().toString().equalsIgnoreCase("-")) {
                            straightDriveText4.setText(hole1Text);
                            straightBTN4.setVisibility(View.VISIBLE);
                        }
                        holeConter_s++;


                    } else if (hole20_flag_s == 1 && holeConter_s <= totalHole) {
                        hole20_flag_s = 0;

                        hole20.setBackgroundResource(R.drawable.hole_background);

                        String value = hole20.getText().toString();

                        String st1 = straightDriveText1.getText().toString();
                        String st2 = straightDriveText2.getText().toString();
                        String st3 = straightDriveText3.getText().toString();
                        String st4 = straightDriveText4.getText().toString();
                        if (value.equalsIgnoreCase(st1)) {
                            straightDriveText1.setText("-");
                            straightBTN1.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st2)) {
                            straightDriveText2.setText("-");
                            straightBTN2.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st3)) {
                            straightDriveText3.setText("-");
                            straightBTN3.setVisibility(View.GONE);
                        } else if (value.equalsIgnoreCase(st4)) {
                            straightDriveText4.setText("-");
                            straightBTN4.setVisibility(View.GONE);
                        }
                        holeConter_s--;
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private void getTEEList1() {

        final ProgressDialog pDialog = new ProgressDialog(this, R.style.MyTheme);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.show();
        JSONObject jsonObject = null;

        jsonObject = new JSONObject();

        try {
            jsonObject.putOpt("golfcourseid", golf_couse_id);
            jsonObject.putOpt("version", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.TEE_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Tee List", "create Account = OnResponse= " + response.toString());

                teeResponseList1(response);
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getLocalClassName(), "Volley Error = " + error);
                pDialog.cancel();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Tee list", "create Account = URL= " + PUTTAPI.TEE_LIST_URL + " Object " + jsonObject.toString());
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void teeResponseList1(JSONObject response) {

        if (response != null) {
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                JSONObject jsonObject1 = jsonObject.getJSONObject("GolfCourseTee");

                JSONArray jsonArray = jsonObject1.getJSONArray("Men");
                for (int i = 0; i < 1; i++) {

                    String tee_name = jsonArray.getJSONObject(0).getString("tee_name");
                    if (tee_name.equalsIgnoreCase("Red")) {
                        mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                        mColorName = "Red";
                    } else if (tee_name.equalsIgnoreCase("Green")) {
                        mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                        mColorName = "Green";
                    } else if (tee_name.equalsIgnoreCase("Yellow")) {
                        mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                        mColorName = "Yellow";
                    } else if (tee_name.equalsIgnoreCase("Black")) {
                        mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                        mColorName = "Black";
                    } else if (tee_name.equalsIgnoreCase("White")) {
                        mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                        mText.setTextColor(Color.BLACK);
                        mColorName = "White";
                    } else if (tee_name.equalsIgnoreCase("Blue")) {
                        mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                        mColorName = "Blue";
                    } else if (tee_name.equalsIgnoreCase("Gold")) {
                        mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                        mColorName = "Gold";
                    } else if (tee_name.equalsIgnoreCase("Silver")) {
                        mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                        mColorName = "Silver";
                    }

                }
                JSONArray jsonArray1 = jsonObject1.getJSONArray("Ladies");
                for (int i = 0; i < 1; i++) {

                    String tee_name = jsonArray1.getJSONObject(0).getString("tee_name");
                    if (tee_name.equalsIgnoreCase("Red")) {
                        wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                        wColorName = "Red";
                    } else if (tee_name.equalsIgnoreCase("Green")) {
                        wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                        wColorName = "Green";
                    } else if (tee_name.equalsIgnoreCase("Yellow")) {
                        wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                        wColorName = "Yellow";
                    } else if (tee_name.equalsIgnoreCase("Black")) {
                        wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                        wColorName = "Black";
                    } else if (tee_name.equalsIgnoreCase("White")) {
                        wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                        wColorName = "White";
                        wText.setTextColor(Color.BLACK);
                    } else if (tee_name.equalsIgnoreCase("Blue")) {
                        wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                        wColorName = "Blue";
                    } else if (tee_name.equalsIgnoreCase("Gold")) {
                        wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                        wColorName = "Gold";
                    } else if (tee_name.equalsIgnoreCase("Silver")) {
                        wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                        wColorName = "Silver";
                    }

                }

                JSONArray jsonArray2 = jsonObject1.getJSONArray("Junior");
                for (int i = 0; i < 1; i++) {

                    String tee_name = jsonArray2.getJSONObject(0).getString("tee_name");
                    if (tee_name.equalsIgnoreCase("Red")) {
                        jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                        jColorName = "Red";
                    } else if (tee_name.equalsIgnoreCase("Green")) {
                        jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                        jColorName = "Green";
                    } else if (tee_name.equalsIgnoreCase("Yellow")) {
                        jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                        jColorName = "Yellow";
                    } else if (tee_name.equalsIgnoreCase("Black")) {
                        jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                        jColorName = "Black";
                    } else if (tee_name.equalsIgnoreCase("White")) {
                        jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                        jColorName = "White";
                        jText.setTextColor(Color.BLACK);
                    } else if (tee_name.equalsIgnoreCase("Blue")) {
                        jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                        jColorName = "Blue";
                    } else if (tee_name.equalsIgnoreCase("Gold")) {
                        jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                        jColorName = "Gold";
                    } else if (tee_name.equalsIgnoreCase("Silver")) {
                        jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                        jColorName = "Silver";
                    }


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




}
