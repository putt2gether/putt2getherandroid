package com.putt2gether.putt;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.putt2gether.R;
import com.putt2gether.bean.DataBean;
import com.putt2gether.bean.FormatBean;
import com.putt2gether.bean.GolfCourseBean;
import com.putt2gether.bean.HoleBeanFirst;
import com.putt2gether.bean.HoleBeanSecond;
import com.putt2gether.bean.TeamIndividualBean;
import com.putt2gether.bean.TeeBean;
import com.putt2gether.bean.TeeBeanJ;
import com.putt2gether.bean.TeeBeanW;
import com.putt2gether.custome.TimePickerDialog;
import com.putt2gether.data.Type;
import com.putt2gether.listener.OnDateSetListener;
import com.putt2gether.network.GPSTracker;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.parser.PuttParser;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 20/06/2016.
 */
public class CreateEventActivity extends AppCompatActivity implements OnDateSetListener {


    private String spotRadioTxt = "No";
    private String multiRadioTxt = "No";
    private String multiType = "0";

    private RelativeLayout selectGolfCourse;
    private EditText eventName;
    private RelativeLayout team_individual_spinner;

    private RelativeLayout date_time_lay;
    private TextView dateTime_Text;
    TextView previewDate;
    private RelativeLayout hole_9_layout, hole_18_layout;
    private RelativeLayout public_lay, private_lay;
    private RelativeLayout fron9_lay, back9_lay;
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
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    private String formate_name;

    private String ind_team_id = "2";

    private RadioButton team_yes, team_no;
    private RadioGroup playersRadioGroup;
    private RadioButton radio_player1, radio_player2, radio_player3, radio_player4, radio_player5;
    private String numberOfPlayer;

    private RadioGroup holesRadioGroup;
    private RadioButton radio_hole9, radio_hole18;
    private String holetxt;
    private RadioGroup event_type_RadioGroup;
    private RadioButton radio_public, radio_private;
    private String eventTypetxt = "private";

    private RadioGroup holes_radioGroup;
    private RadioButton fron_radio, back_radio;
    private String holeBackFront;

    private RelativeLayout selectHolesLayout;
    private RadioButton yesRadio;
    private RadioButton noRadio;
    private LinearLayout spotPrizeLayout;
    private RelativeLayout spotLay;

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


    private TextView closestText1;
    private TextView longDrivetxt1;
    private TextView straightDriveText1;

    private TextView closestText2;
    private TextView longDrivetxt2;
    private TextView straightDriveText2;

    private TextView closestText3;
    private TextView longDrivetxt3;
    private TextView straightDriveText3;

    private TextView closestText4;
    private TextView longDrivetxt4;
    private TextView straightDriveText4;

    private LinearLayout closestPinBTN;
    private LinearLayout longDriveBTN;
    private LinearLayout straightDriveBTN;

    private RelativeLayout mTEE_radio;
    private RelativeLayout wTEE_radio;
    private RelativeLayout jTEE_radio;
    private String mColorName, wColorName, jColorName;

    private TextView hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9,
            hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18, hole19, hole20;


    private TextView m1, m2, m3, m4, m5, m6, m7, m8;
    private TextView w1, w2, w3, w4, w5, w6, w7, w8;
    private TextView j1, j2, j3, j4, j5, j6, j7, j8;
    private TextView doneBTN_Tee;
    private RelativeLayout parentTee;

    private TextView[] holeArray = {hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9,
            hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18, hole19, hole20};
    private TextView[] holeArray2 = {hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9,
            hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18, hole19, hole20};
    private TextView[] mArray = {m1, m2, m3, m4, m5, m6, m7, m8};
    private TextView[] jArray = {j1, j2, j3, j4, j5, j6, j7, j8};
    private TextView[] wArray = {w1, w2, w3, w4, w5, w6, w7, w8};
    private LinearLayout holeParent;


    private RelativeLayout inviteBTN;
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

    private long date;
    private DateFormat format;
    String currentDateandTime;
    String nameEven;
    private TextView mText, wText, jText;
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
    private RelativeLayout multiScreenLayout;
    private RadioButton yesMultiRadio;
    private RadioButton noMultiRadio;
    private RelativeLayout publicPrivateLayout;

    String flag = "0";
    int count = 0;

    int holeConter = 0;

    int hole1_flag = 0, hole2_flag = 0, hole3_flag = 0, hole4_flag = 0, hole5_flag = 0, hole6_flag = 0, hole7_flag = 0,
            hole8_flag = 0, hole9_flag = 0, hole10_flag = 0, hole11_flag = 0, hole12_flag = 0, hole13_flag = 0, hole14_flag = 0,
            hole15_flag = 0, hole16_flag = 0, hole17_flag = 0, hole18_flag = 0, hole19_flag = 0, hole20_flag = 0;

    int totalHole = 4;


    int holeConter_l = 0;

    int hole1_flag_l = 0, hole2_flag_l = 0, hole3_flag_l = 0, hole4_flag_l = 0, hole5_flag_l = 0, hole6_flag_l = 0, hole7_flag_l = 0,
            hole8_flag_l = 0, hole9_flag_l = 0, hole10_flag_l = 0, hole11_flag_l = 0, hole12_flag_l = 0, hole13_flag_l = 0, hole14_flag_l = 0,
            hole15_flag_l = 0, hole16_flag_l = 0, hole17_flag_l = 0, hole18_flag_l = 0, hole19_flag_l = 0, hole20_flag_l = 0;

    int holeConter_s = 0;

    int hole1_flag_s = 0, hole2_flag_s = 0, hole3_flag_s = 0, hole4_flag_s = 0, hole5_flag_s = 0, hole6_flag_s = 0, hole7_flag_s = 0,
            hole8_flag_s = 0, hole9_flag_s = 0, hole10_flag_s = 0, hole11_flag_s = 0, hole12_flag_s = 0, hole13_flag_s = 0, hole14_flag_s = 0,
            hole15_flag_s = 0, hole16_flag_s = 0, hole17_flag_s = 0, hole18_flag_s = 0, hole19_flag_s = 0, hole20_flag_s = 0;



    ImageView formatIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_activity);

        if (checkAndRequestPermissions()) {
        }

        SharedPreferences createSharedPreferences = this.getSharedPreferences("craete_event", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorCreate = createSharedPreferences.edit();
        editorCreate.clear();
        editorCreate.commit();

        formatIcon = (ImageView)findViewById(R.id.format_icon);

        formatIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (format_id!=null && format_id.equalsIgnoreCase("2")){
                    String msg = getString(R.string.grossText);
                    formatIconPopup(msg,formate_name);
                }else if (format_id!=null && format_id.equalsIgnoreCase("3")){
                    String msg = getString(R.string.net_stokeplay);
                    formatIconPopup(msg,formate_name);
                }else if (format_id!=null && format_id.equalsIgnoreCase("4")){
                    String msg = getString(R.string.handicap_strokeplay);
                    formatIconPopup(msg,formate_name);
                }else if (format_id!=null && format_id.equalsIgnoreCase("5")){
                    String msg = getString(R.string.gross_stableford);
                    formatIconPopup(msg,formate_name);
                }else if (format_id!=null && format_id.equalsIgnoreCase("6")){
                    String msg = getString(R.string.net_stable_ford);
                    formatIconPopup(msg,formate_name);
                }else if (format_id!=null && format_id.equalsIgnoreCase("7")){
                    String msg = getString(R.string.net34_stable);
                    formatIconPopup(msg,formate_name);
                }else if (format_id!=null && format_id.equalsIgnoreCase("10")){
                    String msg = getString(R.string.match_play);
                    formatIconPopup(msg,formate_name);
                }else if (format_id!=null && format_id.equalsIgnoreCase("11")){
                    String msg = getString(R.string.auto_press);
                    formatIconPopup(msg,formate_name);
                }else if (format_id!=null && format_id.equalsIgnoreCase("12")){
                    String msg = getString(R.string.frou_two_zero);
                    formatIconPopup(msg,formate_name);
                }else if (format_id!=null && format_id.equalsIgnoreCase("13")){
                    String msg = getString(R.string.vegas);
                    formatIconPopup(msg,formate_name);
                }else if (format_id!=null && format_id.equalsIgnoreCase("14")){
                    String msg = getString(R.string.two_one);
                    formatIconPopup(msg,formate_name);
                }
            }
        });

        scroll = (ScrollView) findViewById(R.id.scroll_view_create);
        publicPrivateLayout = (RelativeLayout) findViewById(R.id.event_type_layout);

        multiScreenLayout = (RelativeLayout) findViewById(R.id.multi_screen_layout);
        yesMultiRadio = (RadioButton) findViewById(R.id.radioButton_Multiyes);
        noMultiRadio = (RadioButton) findViewById(R.id.radioButton_Multino);

        spotPrizeLayout = (LinearLayout) findViewById(R.id.spot_prize_yes_lay);
        spotLay = (RelativeLayout) findViewById(R.id.spot_prize_layout);

        yesRadio = (RadioButton) findViewById(R.id.radioButton_yes);
        noRadio = (RadioButton) findViewById(R.id.radioButton_no);
        yesRadio.setTypeface(Lato_Regular);
        noRadio.setTypeface(Lato_Regular);

        iconINFO = (ImageView) findViewById(R.id.info_icon_event);
        iconINFO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(CreateEventActivity.this, android.R.style.Theme_Holo_Light_Dialog);

                dialog.setCanceledOnTouchOutside(true);
                Window window = dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);

                // Include dialog.xml file
                dialog.setContentView(R.layout.event_icon_popup);

                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                dialog.show();


            }
        });

        inviteTEXT = (TextView) findViewById(R.id.invite_Text);
        if (radioText == "1") {
            inviteTEXT.setText("NEXT");
        }

        mText = (TextView) findViewById(R.id.m_txt);
        jText = (TextView) findViewById(R.id.j_txt);
        wText = (TextView) findViewById(R.id.w_txt);

        mDialogMonthDayHourMinute = new TimePickerDialog.Builder().setType(Type.MONTH_DAY_HOUR_MIN)
                .setCallBack(CreateEventActivity.this)
                .build();

        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        backBTN = (ImageView) findViewById(R.id.back_create_event);
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


        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);
        final String accessToken = pref.getString("authorization_key", null);
        final String eventCreatedName = pref.getString("displayName", null);

        String uparCaseName = eventCreatedName.toUpperCase();

        nameEven = uparCaseName + "'S" + " " + "EVENT";

        createdEventName = (EditText) findViewById(R.id.event_userName);
        createdEventName.setTypeface(Lato_Regular);

        createdEventName.setHint(nameEven);
        createdEventName.setTextColor(getResources().getColor(R.color.action));


        inviteBTN = (RelativeLayout) findViewById(R.id.invite_from_create);

        inviteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences createSharedPreferences = getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorCreate = createSharedPreferences.edit();
                editorCreate.clear();
                editorCreate.commit();

                if (mSharedPreferences == null)
                    return;

                String createby1 = createdEventName.getText().toString();
                if (createby1.length() != 0) {
                    createby = createdEventName.getText().toString();

                } else {
                    createby = createdEventName.getHint().toString();
                }
                String golfcourseName = selectedGolfCourseTEXT.getText().toString();
                String dateTime = dateTime_Text.getText().toString();
                String upToNCharacters = dateTime.substring(0, Math.min(dateTime.length(), 11));

                SharedPreferences.Editor editor1 = mSharedPreferences.edit();
                editor1.putString("createby", createby);
                editor1.putString("golfcourseName", golfcourseName);
                editor1.putString("dateTime", upToNCharacters);
                editor1.putString("dateTime1", dateTime);
                editor1.putString("radioText", radioText);
                editor1.putString("teamText", teamText);
                editor1.putString("formateName", formate_name);
                editor1.putString("holeText", holetxt);
                editor1.putString("eventTypeText", eventTypetxt);
                editor1.putString("holeBackFront", holeBackFront);
                editor1.putString("colorM", mColorName);
                editor1.putString("colorJ", jColorName);
                editor1.putString("colorW", wColorName);
                editor1.putString("golfCourseID", golf_couse_id);
                editor1.putString("formateID", format_id);

                editor1.putString("multiScreenText", multiRadioTxt);
                editor1.putString("multiType", multiType);

                editor1.putString("spotPrizeType", spotRadioTxt);

                editor1.putString("closestText1", closestText1.getText().toString());
                editor1.putString("longDrivetxt1", longDrivetxt1.getText().toString());
                editor1.putString("straightDriveText1", straightDriveText1.getText().toString());

                editor1.putString("closestText2", closestText2.getText().toString());
                editor1.putString("longDrivetxt2", longDrivetxt2.getText().toString());
                editor1.putString("straightDriveText2", straightDriveText2.getText().toString());

                editor1.putString("closestText3", closestText3.getText().toString());
                editor1.putString("longDrivetxt3", longDrivetxt3.getText().toString());
                editor1.putString("straightDriveText3", straightDriveText3.getText().toString());

                editor1.putString("closestText4", closestText4.getText().toString());
                editor1.putString("longDrivetxt4", longDrivetxt4.getText().toString());
                editor1.putString("straightDriveText4", straightDriveText4.getText().toString());

                editor1.commit();

                if (golfcourseName.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select golfcourse", Toast.LENGTH_LONG).show();
                } else if (createby.length() == 0) {
                    createdEventName.setError(getString(R.string.created_by));

                } else {
                    createdEventName.setError(null);

                    if (teamText.equalsIgnoreCase("Team")) {
                        Intent intent = new Intent(getApplicationContext(), DefineTeamActivity.class);
                        startActivity(intent);

                    } else if (teamText.equalsIgnoreCase("Individual")) {
                        //int playerNo = Integer.parseInt(radioText);
                        if (radioText.equalsIgnoreCase("1")) {
                            Intent intent = new Intent(getApplicationContext(), EventPreviewActivity.class);
                            startActivity(intent);
                        } else if (radioText.equalsIgnoreCase("4+")) {

                            if (spotRadioTxt.equalsIgnoreCase("No")) {
                                Intent intent = new Intent(getApplicationContext(), InviteTabPlusActivity.class);
                                startActivity(intent);
                            }else if (spotRadioTxt.equalsIgnoreCase("Yes") && (!closestText1.getText().toString().equalsIgnoreCase("-") || !longDrivetxt1.getText().toString().equalsIgnoreCase("-") || !straightDriveText1.getText().toString().equalsIgnoreCase("-") )){

                                Intent intent = new Intent(getApplicationContext(), InviteTabPlusActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),"Please select atleast one hole.",Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Intent intent = new Intent(getApplicationContext(), InvitePlayerActivity.class);

                            SharedPreferences createSharedPreferences1 = getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editorCreate1 = createSharedPreferences1.edit();
                            editorCreate1.putString("player_nnno", radioText);
                            editorCreate1.commit();

                            startActivity(intent);
                        }
                    }
                }
            }
        });

        createdEventName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    createdEventName.setHint("");
                else
                    createdEventName.setHint(nameEven);
            }
        });
        mTEE_radio = (RelativeLayout) findViewById(R.id.tee_m);
        wTEE_radio = (RelativeLayout) findViewById(R.id.tee_w);
        jTEE_radio = (RelativeLayout) findViewById(R.id.tee_j);


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


        selectHolesLayout = (RelativeLayout) findViewById(R.id.select_hole_layout);

        playersRadioGroup = (RadioGroup) findViewById(R.id.radioGroup_players);
        holesRadioGroup = (RadioGroup) findViewById(R.id.radioGroup_noOf_holes);
        event_type_RadioGroup = (RadioGroup) findViewById(R.id.radioGroup_event_type);
        holes_radioGroup = (RadioGroup) findViewById(R.id.radioGroup_hole_type);

        radio_public = (RadioButton) findViewById(R.id.radioButton_public);
        radio_private = (RadioButton) findViewById(R.id.radioButton_private);

        radio_public.setTypeface(Lato_Regular);
        radio_private.setTypeface(Lato_Regular);

        radio_public.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radio_public.setTextColor(Color.WHITE);
                    radio_private.setTextColor(Color.BLACK);
                    eventTypetxt = radio_public.getText().toString();

                }
            }
        });

        radio_private.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radio_public.setTextColor(Color.BLACK);
                    radio_private.setTextColor(Color.WHITE);
                    eventTypetxt = radio_private.getText().toString();

                }
            }
        });


        fron_radio = (RadioButton) findViewById(R.id.radioButton_front9);
        fron_radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fron_radio.setTextColor(Color.WHITE);
                    back_radio.setTextColor(Color.BLACK);
                    option = "1";

                    holeBackFront = fron_radio.getText().toString();

                }
            }
        });

        back_radio = (RadioButton) findViewById(R.id.radioButton_back9);

        fron_radio.setTypeface(Lato_Regular);
        back_radio.setTypeface(Lato_Regular);
        back_radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    back_radio.setTextColor(Color.WHITE);
                    fron_radio.setTextColor(Color.BLACK);
                    option = "2";

                    holeBackFront = back_radio.getText().toString();

                }
            }
        });


        int player_radio = playersRadioGroup.getCheckedRadioButtonId();
        radio_player1 = (RadioButton) findViewById(player_radio);
        String player_hole = radio_player1.getText().toString();

        int holes_radio = holesRadioGroup.getCheckedRadioButtonId();
        radio_hole9 = (RadioButton) findViewById(holes_radio);


        Calendar c2 = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd HH:mm");
        String formattedDate = df.format(c2.getTime());

        dateTime_Text = (TextView) findViewById(R.id.date_time_editText);

        dateTime_Text.setText(formattedDate);
        //showDateAndTimePicker();

        dateTime_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateTimeDialog();

              /*  new SingleDateAndTimePickerDialog.Builder(CreateEventActivity.this)
                        .bottomSheet()
                        .title("SELECT TIME")
                        .titleTextColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.action))
                        .mainColor(Color.WHITE)
                        .curved().display();*/

                //  mDialogMonthDayHourMinute.show(getSupportFragmentManager(), "month_day_hour_minute");
            }
        });


        selectGolfCourse = (RelativeLayout) findViewById(R.id.select_golf_course);

        team_individual_spinner = (RelativeLayout) findViewById(R.id.create_individual_layout);

        selectGolfCourse = (RelativeLayout) findViewById(R.id.select_golf_course);
        selectedGolfCourseTEXT = (TextView) findViewById(R.id.select_golf_course_tv);
        formatSpinner = (Spinner) findViewById(R.id.strokeplay_spinner);

        formatSpinner.getBackground().setColorFilter(getResources().getColor(R.color.white_color), PorterDuff.Mode.SRC_ATOP);

        team_yes = (RadioButton) findViewById(R.id.radioButton_yes_team);
        team_no = (RadioButton) findViewById(R.id.radioButton_no_team);

        radio_player1 = (RadioButton) findViewById(R.id.radioButton_player1);
        multiScreenLayout.setVisibility(View.GONE);

        radio_player1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    spotPrizeLayout.setVisibility(View.GONE);

                    SharedPreferences createSharedPreferences = getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorCreate = createSharedPreferences.edit();
                    editorCreate.clear();
                    editorCreate.commit();

                    teamText = "Individual";
                    eventTypetxt = "private";

                    multiType = "1";

                    ind_team_id = "2";

                    inviteTEXT.setText("NEXT");

                    radioText = radio_player1.getText().toString();
                    radio_player1.setTextColor(Color.WHITE);
                    radio_player2.setTextColor(Color.BLACK);
                    radio_player3.setTextColor(Color.BLACK);
                    radio_player4.setTextColor(Color.BLACK);
                    radio_player5.setTextColor(Color.BLACK);
                    team_individual_spinner.setVisibility(View.GONE);

                    publicPrivateLayout.setVisibility(View.GONE);
                    multiScreenLayout.setVisibility(View.GONE);
                    spotLay.setVisibility(View.GONE);

                    getFormatList("1");

                }

            }
        });

        radio_player2 = (RadioButton) findViewById(R.id.radioButton_player2);
        radio_player3 = (RadioButton) findViewById(R.id.radioButton_player3);
        radio_player4 = (RadioButton) findViewById(R.id.radioButton_player4);
        radio_player5 = (RadioButton) findViewById(R.id.radioButton_player_more);

        radio_player1.setTypeface(Lato_Regular);
        radio_player2.setTypeface(Lato_Regular);
        radio_player3.setTypeface(Lato_Regular);
        radio_player4.setTypeface(Lato_Regular);
        radio_player5.setTypeface(Lato_Regular);

        team_yes.setTypeface(Lato_Regular);
        team_no.setTypeface(Lato_Regular);

        radio_player2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    spotPrizeLayout.setVisibility(View.GONE);


                    multiScreenLayout.setVisibility(View.VISIBLE);
                    SharedPreferences createSharedPreferences = getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorCreate = createSharedPreferences.edit();
                    editorCreate.clear();
                    editorCreate.commit();
                    teamText = "Individual";
                    eventTypetxt = "private";

                    multiType = "1";

                    inviteTEXT.setText("INVITE PLAYERS");
                    ind_team_id = "2";
                    radioText = radio_player2.getText().toString();

                    radio_player2.setTextColor(Color.WHITE);
                    radio_player1.setTextColor(Color.BLACK);
                    radio_player3.setTextColor(Color.BLACK);
                    radio_player4.setTextColor(Color.BLACK);
                    radio_player5.setTextColor(Color.BLACK);
                    team_individual_spinner.setVisibility(View.GONE);

                    publicPrivateLayout.setVisibility(View.GONE);
                    multiScreenLayout.setVisibility(View.VISIBLE);
                    spotLay.setVisibility(View.GONE);


                    getFormatList("2");
                }


            }
        });

        radio_player3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    spotPrizeLayout.setVisibility(View.GONE);

                    SharedPreferences createSharedPreferences = getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorCreate = createSharedPreferences.edit();
                    editorCreate.clear();
                    editorCreate.commit();

                    teamText = "Individual";
                    eventTypetxt = "private";

                    multiType = "1";

                    inviteTEXT.setText("INVITE PLAYERS");
                    ind_team_id = "2";
                    radioText = radio_player3.getText().toString();
                    radio_player3.setTextColor(Color.WHITE);
                    radio_player2.setTextColor(Color.BLACK);
                    radio_player1.setTextColor(Color.BLACK);
                    radio_player4.setTextColor(Color.BLACK);
                    radio_player5.setTextColor(Color.BLACK);
                    team_individual_spinner.setVisibility(View.GONE);

                    publicPrivateLayout.setVisibility(View.GONE);
                    multiScreenLayout.setVisibility(View.VISIBLE);
                    spotLay.setVisibility(View.GONE);


                    getFormatList("3");

                }
            }
        });

        radio_player4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                    team_yes.setChecked(true);
                    team_no.setChecked(false);

                    team_yes.setTextColor(Color.WHITE);
                    team_no.setTextColor(Color.BLACK);
                    spotPrizeLayout.setVisibility(View.GONE);

                    SharedPreferences createSharedPreferences = getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorCreate = createSharedPreferences.edit();
                    editorCreate.clear();
                    editorCreate.commit();

                    teamText = "Team";
                    multiType = "0";

                    ind_team_id = "1";

                    getFormatList("4");
                    inviteTEXT.setText("INVITE PLAYERS");
                    eventTypetxt = "private";

                    radioText = radio_player4.getText().toString();
                    radio_player4.setTextColor(Color.WHITE);
                    radio_player2.setTextColor(Color.BLACK);
                    radio_player3.setTextColor(Color.BLACK);
                    radio_player1.setTextColor(Color.BLACK);
                    radio_player5.setTextColor(Color.BLACK);
                    team_individual_spinner.setVisibility(View.VISIBLE);

                    publicPrivateLayout.setVisibility(View.GONE);
                    multiScreenLayout.setVisibility(View.GONE);
                    spotLay.setVisibility(View.GONE);

                    team_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                teamText = "Team";
                                multiType = "0";

                                ind_team_id = "1";
                                team_yes.setTextColor(Color.WHITE);
                                team_no.setTextColor(Color.BLACK);

                                multiScreenLayout.setVisibility(View.GONE);
                                spotLay.setVisibility(View.GONE);

                                getFormatList("4");
                            }
                        }
                    });

                    team_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                teamText = "Individual";
                                ind_team_id = "2";
                                team_no.setTextColor(Color.WHITE);
                                team_yes.setTextColor(Color.BLACK);

                                multiType = "1";

                                multiScreenLayout.setVisibility(View.VISIBLE);
                                spotLay.setVisibility(View.GONE);

                                getFormatList("4");
                            }
                        }
                    });


                }

            }
        });

        radio_player5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    SharedPreferences createSharedPreferences = getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorCreate = createSharedPreferences.edit();
                    editorCreate.clear();
                    editorCreate.commit();

                    noRadio.setChecked(true);
                    yesRadio.setChecked(false);
                    yesRadio.setTextColor(Color.BLACK);
                    noRadio.setTextColor(Color.WHITE);
                    spotRadioTxt = noRadio.getText().toString();

                    teamText = "Individual";
                    eventTypetxt = "public";

                    multiType = "0";

                    ind_team_id = "2";
                    inviteTEXT.setText("INVITE PLAYERS");
                    radioText = radio_player5.getText().toString();
                    //radioText = 4+;
                    radio_player5.setTextColor(Color.WHITE);
                    radio_player2.setTextColor(Color.BLACK);
                    radio_player3.setTextColor(Color.BLACK);
                    radio_player4.setTextColor(Color.BLACK);
                    radio_player1.setTextColor(Color.BLACK);
                    team_individual_spinner.setVisibility(View.GONE);


                    publicPrivateLayout.setVisibility(View.VISIBLE);
                    multiScreenLayout.setVisibility(View.GONE);
                    spotLay.setVisibility(View.VISIBLE);

                    getFormatList("4+");

                }
            }
        });

        radio_hole9 = (RadioButton) findViewById(R.id.radioButton_hole9);
        radio_hole18 = (RadioButton) findViewById(R.id.radioButton_hole18);

        radio_hole9.setTypeface(Lato_Regular);
        radio_hole18.setTypeface(Lato_Regular);

        radio_hole9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    radio_hole18.setTextColor(Color.BLACK);
                    radio_hole9.setTextColor(Color.WHITE);
                    selectHolesLayout.setVisibility(View.VISIBLE);
                    holetxt = radio_hole9.getText().toString();

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
                    hole1_flag = 0;
                    hole2_flag = 0;
                    hole3_flag = 0;
                    hole4_flag = 0;
                    hole5_flag = 0;
                    hole6_flag = 0;
                    hole7_flag = 0;
                    hole8_flag = 0;
                    hole9_flag = 0;
                    hole10_flag = 0;
                    hole11_flag = 0;
                    hole12_flag = 0;
                    hole13_flag = 0;
                    hole14_flag = 0;
                    hole15_flag = 0;
                    hole16_flag = 0;
                    hole17_flag = 0;
                    hole18_flag = 0;
                    hole19_flag = 0;
                    hole20_flag = 0;
                    closestText1.setText("-");
                    closestText2.setText("-");
                    closestText3.setText("-");
                    closestText4.setText("-");

                    holeConter_l = 0;
                    hole1_flag_l = 0;
                    hole2_flag_l = 0;
                    hole3_flag_l = 0;
                    hole4_flag_l = 0;
                    hole5_flag_l = 0;
                    hole6_flag_l = 0;
                    hole7_flag_l = 0;
                    hole8_flag_l = 0;
                    hole9_flag_l = 0;
                    hole10_flag_l = 0;
                    hole11_flag_l = 0;
                    hole12_flag_l = 0;
                    hole13_flag_l = 0;
                    hole14_flag_l = 0;
                    hole15_flag_l = 0;
                    hole16_flag_l = 0;
                    hole17_flag_l = 0;
                    hole18_flag_l = 0;
                    hole19_flag_l = 0;
                    hole20_flag_l = 0;
                    longDrivetxt1.setText("-");
                    longDrivetxt2.setText("-");
                    longDrivetxt3.setText("-");
                    longDrivetxt4.setText("-");

                    holeConter_s = 0;
                    hole1_flag_s = 0;
                    hole2_flag_s = 0;
                    hole3_flag_s = 0;
                    hole4_flag_s = 0;
                    hole5_flag_s = 0;
                    hole6_flag_s = 0;
                    hole7_flag_s = 0;
                    hole8_flag_s = 0;
                    hole9_flag_s = 0;
                    hole10_flag_s = 0;
                    hole11_flag_s = 0;
                    hole12_flag_s = 0;
                    hole13_flag_s = 0;
                    hole14_flag_s = 0;
                    hole15_flag_s = 0;
                    hole16_flag_s = 0;
                    hole17_flag_s = 0;
                    hole18_flag_s = 0;
                    hole19_flag_s = 0;
                    hole20_flag_s = 0;
                    straightDriveText1.setText("-");
                    straightDriveText2.setText("-");
                    straightDriveText3.setText("-");
                    straightDriveText4.setText("-");

                }
            }
        });

        radio_hole18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radio_hole9.setTextColor(Color.BLACK);
                    radio_hole18.setTextColor(Color.WHITE);
                    selectHolesLayout.setVisibility(View.GONE);

                    holetxt = radio_hole18.getText().toString();

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
                    hole1_flag = 0;
                    hole2_flag = 0;
                    hole3_flag = 0;
                    hole4_flag = 0;
                    hole5_flag = 0;
                    hole6_flag = 0;
                    hole7_flag = 0;
                    hole8_flag = 0;
                    hole9_flag = 0;
                    hole10_flag = 0;
                    hole11_flag = 0;
                    hole12_flag = 0;
                    hole13_flag = 0;
                    hole14_flag = 0;
                    hole15_flag = 0;
                    hole16_flag = 0;
                    hole17_flag = 0;
                    hole18_flag = 0;
                    hole19_flag = 0;
                    hole20_flag = 0;
                    closestText1.setText("-");
                    closestText2.setText("-");
                    closestText3.setText("-");
                    closestText4.setText("-");

                    holeConter_l = 0;
                    hole1_flag_l = 0;
                    hole2_flag_l = 0;
                    hole3_flag_l = 0;
                    hole4_flag_l = 0;
                    hole5_flag_l = 0;
                    hole6_flag_l = 0;
                    hole7_flag_l = 0;
                    hole8_flag_l = 0;
                    hole9_flag_l = 0;
                    hole10_flag_l = 0;
                    hole11_flag_l = 0;
                    hole12_flag_l = 0;
                    hole13_flag_l = 0;
                    hole14_flag_l = 0;
                    hole15_flag_l = 0;
                    hole16_flag_l = 0;
                    hole17_flag_l = 0;
                    hole18_flag_l = 0;
                    hole19_flag_l = 0;
                    hole20_flag_l = 0;
                    longDrivetxt1.setText("-");
                    longDrivetxt2.setText("-");
                    longDrivetxt3.setText("-");
                    longDrivetxt4.setText("-");

                    holeConter_s = 0;
                    hole1_flag_s = 0;
                    hole2_flag_s = 0;
                    hole3_flag_s = 0;
                    hole4_flag_s = 0;
                    hole5_flag_s = 0;
                    hole6_flag_s = 0;
                    hole7_flag_s = 0;
                    hole8_flag_s = 0;
                    hole9_flag_s = 0;
                    hole10_flag_s = 0;
                    hole11_flag_s = 0;
                    hole12_flag_s = 0;
                    hole13_flag_s = 0;
                    hole14_flag_s = 0;
                    hole15_flag_s = 0;
                    hole16_flag_s = 0;
                    hole17_flag_s = 0;
                    hole18_flag_s = 0;
                    hole19_flag_s = 0;
                    hole20_flag_s = 0;
                    straightDriveText1.setText("-");
                    straightDriveText2.setText("-");
                    straightDriveText3.setText("-");
                    straightDriveText4.setText("-");


                }
            }
        });


        yesMultiRadio.setTypeface(Lato_Regular);
        noMultiRadio.setTypeface(Lato_Regular);

        yesMultiRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yesMultiRadio.setTextColor(Color.WHITE);
                    noMultiRadio.setTextColor(Color.BLACK);
                    multiRadioTxt = yesMultiRadio.getText().toString();

                }
            }
        });

        noMultiRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yesMultiRadio.setTextColor(Color.BLACK);
                    noMultiRadio.setTextColor(Color.WHITE);
                    multiRadioTxt = noMultiRadio.getText().toString();

                }
            }
        });


        yesRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yesRadio.setTextColor(Color.WHITE);
                    noRadio.setTextColor(Color.BLACK);
                    spotPrizeLayout.setVisibility(View.VISIBLE);
                    spotRadioTxt = yesRadio.getText().toString();

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

                }
            }
        });
        // getFormatList();
        String a = getIntent().getStringExtra("golfCourseName");
        String c = getIntent().getStringExtra("golfCourseNameSearch");
        String b = getIntent().getStringExtra("golfNameTemp");
        String golfTempID = getIntent().getStringExtra("golf_couse_id_temp");


        String golfID = getIntent().getStringExtra("golf_couse_id");
        String golfIDSEARCH = getIntent().getStringExtra("golf_couse_id_search");

        if (a != null) {
            String golfName = a.toUpperCase();
            selectedGolfCourseTEXT.setText(golfName);
            golf_couse_id = golfID;
            getTEEList1();
        } else if (c != null) {
            String golfNameSearch = c.toUpperCase();
            selectedGolfCourseTEXT.setText(golfNameSearch);
            golf_couse_id = golfIDSEARCH;
            getTEEList1();
        } else if (b != null) {
            String golfNameTemp = b.toUpperCase();
            selectedGolfCourseTEXT.setText(golfNameTemp);
            golf_couse_id = golfTempID;
            getTEEList1();
        } else {
            getGolfCourse();
        }


        selectGolfCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectGolfCourse.class);
                SharedPreferences pref1 = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor1 = pref1.edit();
                editor1.putString("resultFor", "createEvent");
                editor1.commit();
                startActivity(intent);
                // finish();
            }
        });

        closestPinBTN = (LinearLayout) findViewById(R.id.closest_pin_btn);
        straightDriveBTN = (LinearLayout) findViewById(R.id.straight_drive_btn);
        longDriveBTN = (LinearLayout) findViewById(R.id.long_drive_btn);

        longDriveBTN1 = (RelativeLayout) findViewById(R.id.long_drive_btn1);
        longDriveBTN2 = (RelativeLayout) findViewById(R.id.long_drive_btn2);
        longDriveBTN3 = (RelativeLayout) findViewById(R.id.long_drive_btn3);
        longDriveBTN4 = (RelativeLayout) findViewById(R.id.long_drive_btn4);

        straightBTN1 = (RelativeLayout) findViewById(R.id.straight_drive_btn1);
        straightBTN2 = (RelativeLayout) findViewById(R.id.straight_drive_btn2);
        straightBTN3 = (RelativeLayout) findViewById(R.id.straight_drive_btn3);
        straightBTN4 = (RelativeLayout) findViewById(R.id.straight_drive_btn4);

        straightBTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strStraight = "1";

                stokeMethod2(strStraight);
            }
        });

        straightBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strStraight = "2";
                stokeMethod2(strStraight);
            }
        });

        straightBTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strStraight = "3";
                stokeMethod2(strStraight);
            }
        });

        straightBTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strStraight = "4";
                stokeMethod2(strStraight);
            }
        });

        longDriveBTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strLongDrive = "1";
                stokeMethod1(strLongDrive);
            }
        });

        longDriveBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLongDrive = "2";
                stokeMethod1(strLongDrive);
            }
        });

        longDriveBTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLongDrive = "3";
                stokeMethod1(strLongDrive);
            }
        });

        longDriveBTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLongDrive = "4";
                stokeMethod1(strLongDrive);
            }
        });


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
                String str = "1";
                stokeMethod(str);
            }

        });
        closestBTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String str = "1";
                stokeMethod(str);
            }

        });
        closestBTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String str = "1";
                stokeMethod(str);
            }

        });


        getFormatList("1");
        //getTeamIndividualList();
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
                refreshTEE();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
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


    private void getTEEList() {

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

        if (response != null) {
            try {


                JSONObject jObject = new JSONObject(response.toString());
                JSONObject jsonObject = jObject.getJSONObject("output");
                JSONObject jsonObject1 = jsonObject.getJSONObject("GolfCourseTee");
                JSONArray jsonArray = jsonObject1.getJSONArray("Men");
                for (int i = 0; i < jsonArray.length(); i++) {
                    //TeeBean typeBean = new TeeBean();

                    list.add(new TeeBean(jsonArray.getJSONObject(i).getString("code"), jsonArray.getJSONObject(i).getString("tee_id"), jsonArray.getJSONObject(i).getString("tee_name"), jsonArray.getJSONObject(i).getString("tee_color")));
                    //   typeBean.setM_tee_id(jsonArray.getJSONObject(i).getString("tee_id"));
                    //  typeBean.setM_tee_name(jsonArray.getJSONObject(i).getString("tee_name"));
                    //  typeBean.setM_tee_color(jsonArray.getJSONObject(i).getString("tee_color"));
                    //  typeBean.setCode(jsonArray.getJSONObject(i).getString("code"));
                    //String tee_name = jsonArray.getJSONObject(i).getString("tee_name");


                    //list.add(typeBean);
                }


                JSONArray jsonArray1 = jsonObject1.getJSONArray("Ladies");
                for (int i = 0; i < jsonArray1.length(); i++) {
                    // TeeBeanW typeBeanW = new TeeBeanW();
                    // typeBeanW.setW_tee_id(jsonArray1.getJSONObject(i).getString("tee_id"));
                    // typeBeanW.setW_tee_name(jsonArray1.getJSONObject(i).getString("tee_name"));
                    //  typeBeanW.setW_tee_color(jsonArray1.getJSONObject(i).getString("tee_color"));
                    //  typeBeanW.setCode(jsonArray1.getJSONObject(i).getString("code"));

                    list1.add(new TeeBeanW(jsonArray1.getJSONObject(i).getString("code"), jsonArray1.getJSONObject(i).getString("tee_id"), jsonArray1.getJSONObject(i).getString("tee_name"), jsonArray1.getJSONObject(i).getString("tee_color")));

                }


                JSONArray jsonArray2 = jsonObject1.getJSONArray("Junior");
                for (int i = 0; i < jsonArray2.length(); i++) {

                    list2.add(new TeeBeanJ(jsonArray2.getJSONObject(i).getString("code"), jsonArray2.getJSONObject(i).getString("tee_id"), jsonArray2.getJSONObject(i).getString("tee_name"), jsonArray2.getJSONObject(i).getString("tee_color")));
                    //  TeeBeanJ typeBeanJ = new TeeBeanJ();
                    //   typeBeanJ.setJ_tee_id(jsonArray2.getJSONObject(i).getString("tee_id"));
                    //   typeBeanJ.setJ_tee_name(jsonArray2.getJSONObject(i).getString("tee_name"));
                    //  typeBeanJ.setJ_tee_color(jsonArray2.getJSONObject(i).getString("tee_color"));
                    //  typeBeanJ.setCode(jsonArray2.getJSONObject(i).getString("code"));
                    //String tee_name = jsonArray.getJSONObject(i).getString("tee_name");

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(list, TeeBean.StuNameComparator);
        Collections.sort(list1, TeeBeanW.StuNameComparator);
        Collections.sort(list2, TeeBeanJ.StuNameComparator);
        for (int i = 0; i < list.size(); i++) {
            String tee_name = list.get(i).getM_tee_name().toString();
            Log.v("Color Name Men", tee_name);
            if (tee_name.equalsIgnoreCase("Red")) {

                mArray[i].setText("mr");
                mArray[i].setBackgroundResource(R.drawable.red_tee);
                mArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Green")) {

                mArray[i].setText("mg");
                mArray[i].setBackgroundResource(R.drawable.green_tee);
                mArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Yellow")) {

                mArray[i].setText("my");
                mArray[i].setBackgroundResource(R.drawable.yello_tee);
                mArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Black")) {

                mArray[i].setText("mb");
                mArray[i].setBackgroundResource(R.drawable.black_tee);
                mArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("White")) {

                mArray[i].setText("mw");
                mArray[i].setBackgroundResource(R.drawable.white_tee);
                mArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Blue")) {

                mArray[i].setText("mu");
                mArray[i].setBackgroundResource(R.drawable.blue_tee);
                mArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Gold")) {

                mArray[i].setText("ml");
                mArray[i].setBackgroundResource(R.drawable.gold_tee);
                mArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Silver")) {

                mArray[i].setText("ms");
                mArray[i].setBackgroundResource(R.drawable.silver_tee);
                mArray[i].setVisibility(View.VISIBLE);
            }
        }
        for (int i = 0; i < list1.size(); i++) {
            String tee_name = list1.get(i).getW_tee_name().toString();
            Log.v("Color Name Ladies", tee_name);
            if (tee_name.equalsIgnoreCase("Red")) {

                wArray[i].setText("wr");
                wArray[i].setBackgroundResource(R.drawable.red_tee);
                wArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Green")) {

                wArray[i].setText("wg");
                wArray[i].setBackgroundResource(R.drawable.green_tee);
                wArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Yellow")) {

                wArray[i].setText("wy");
                wArray[i].setBackgroundResource(R.drawable.yello_tee);
                wArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Black")) {

                wArray[i].setText("wb");
                wArray[i].setBackgroundResource(R.drawable.black_tee);
                wArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("White")) {

                wArray[i].setText("ww");
                wArray[i].setBackgroundResource(R.drawable.white_tee);
                wArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Blue")) {

                wArray[i].setText("wu");
                wArray[i].setBackgroundResource(R.drawable.blue_tee);
                wArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Gold")) {

                wArray[i].setText("wl");
                wArray[i].setBackgroundResource(R.drawable.gold_tee);
                wArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Silver")) {

                wArray[i].setText("ws");
                wArray[i].setBackgroundResource(R.drawable.silver_tee);
                wArray[i].setVisibility(View.VISIBLE);
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            String tee_name = list2.get(i).getJ_tee_name().toString();
            Log.v("Color Name Junior", tee_name);
            if (tee_name.equalsIgnoreCase("Red")) {

                jArray[i].setText("jr");
                jArray[i].setBackgroundResource(R.drawable.red_tee);
                jArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Green")) {

                jArray[i].setText("jg");
                jArray[i].setBackgroundResource(R.drawable.green_tee);
                jArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Yellow")) {

                jArray[i].setText("jy");
                jArray[i].setBackgroundResource(R.drawable.yello_tee);
                jArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Black")) {

                jArray[i].setText("jb");
                jArray[i].setBackgroundResource(R.drawable.black_tee);
                jArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("White")) {

                jArray[i].setText("jw");
                jArray[i].setBackgroundResource(R.drawable.white_tee);
                jArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Blue")) {

                jArray[i].setText("ju");
                jArray[i].setBackgroundResource(R.drawable.blue_tee);
                jArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Gold")) {

                jArray[i].setText("jl");
                jArray[i].setBackgroundResource(R.drawable.gold_tee);
                jArray[i].setVisibility(View.VISIBLE);
            } else if (tee_name.equalsIgnoreCase("Silver")) {

                jArray[i].setText("js");
                jArray[i].setBackgroundResource(R.drawable.silver_tee);
                jArray[i].setVisibility(View.VISIBLE);
            }
        }
        parentTee.setVisibility(View.VISIBLE);

    }


    private void getFormatList(String playerNO) {

        String response = "";
        JSONObject jsonObjectF = null;

        if (playerNO.equalsIgnoreCase("1")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"GROSS STROKEPLAY\",\"format_id\":2},{\"format_name\":\"GROSS STABLEFORD\",\"format_id\":5}],\"message\":\"Stroke List\"}}";

        }else if (playerNO.equalsIgnoreCase("2")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"MATCHPLAY\",\"format_id\":10},{\"format_name\":\"AUTOPRESS\",\"format_id\":11},{\"format_name\":\"GROSS STROKEPLAY\",\"format_id\":2},{\"format_name\":\"NET STROKEPLAY\",\"format_id\":3},{\"format_name\":\"3\\/4TH NET STROKEPLAY\",\"format_id\":4},{\"format_name\":\"GROSS STABLEFORD\",\"format_id\":5},{\"format_name\":\"NET STABLEFORD\",\"format_id\":6},{\"format_name\":\"3\\/4TH NET STABLEFORD\",\"format_id\":7}],\"message\":\"Stroke List\"}}";
        }else if (playerNO.equalsIgnoreCase("3")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"4-2-0\",\"format_id\":12},{\"format_name\":\"GROSS STROKEPLAY\",\"format_id\":2},{\"format_name\":\"NET STROKEPLAY\",\"format_id\":3},{\"format_name\":\"3\\/4TH NET STROKEPLAY\",\"format_id\":4},{\"format_name\":\"GROSS STABLEFORD\",\"format_id\":5},{\"format_name\":\"NET STABLEFORD\",\"format_id\":6},{\"format_name\":\"3\\/4TH NET STABLEFORD\",\"format_id\":7}],\"message\":\"Stroke List\"}}";
        }else if (playerNO.equalsIgnoreCase("4") && ind_team_id.equalsIgnoreCase("1")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"MATCHPLAY\",\"format_id\":10},{\"format_name\":\"AUTOPRESS\",\"format_id\":11},{\"format_name\":\"VEGAS\",\"format_id\":13},{\"format_name\":\"2-1\",\"format_id\":14}],\"message\":\"Stroke List\"}}";
        }else if (playerNO.equalsIgnoreCase("4") && ind_team_id.equalsIgnoreCase("2")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"GROSS STROKEPLAY\",\"format_id\":2},{\"format_name\":\"NET STROKEPLAY\",\"format_id\":3},{\"format_name\":\"3\\/4TH NET STROKEPLAY\",\"format_id\":4},{\"format_name\":\"GROSS STABLEFORD\",\"format_id\":5},{\"format_name\":\"NET STABLEFORD\",\"format_id\":6},{\"format_name\":\"3\\/4TH NET STABLEFORD\",\"format_id\":7}],\"message\":\"Stroke List\"}}";

        }else if (playerNO.equalsIgnoreCase("4+")){

            response = "{\"output\":{\"status\":\"1\",\"data\":[{\"format_name\":\"GROSS STROKEPLAY\",\"format_id\":2},{\"format_name\":\"NET STROKEPLAY\",\"format_id\":3},{\"format_name\":\"3\\/4TH NET STROKEPLAY\",\"format_id\":4},{\"format_name\":\"GROSS STABLEFORD\",\"format_id\":5},{\"format_name\":\"NET STABLEFORD\",\"format_id\":6},{\"format_name\":\"3\\/4TH NET STABLEFORD\",\"format_id\":7}],\"message\":\"Stroke List\"}}";
        }

        try {
             jsonObjectF = new JSONObject(response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("ihoiohil",playerNO+"jj"+ind_team_id);

        DataBean bean = new DataBean();
        bean = parser.getFormateResponseList(jsonObjectF);
        formatList = new ArrayList<FormatBean>();
        formatList = bean.getFormatList();
        formatArr = new String[formatList.size()];
        for (int i = 0; i < formatList.size(); i++) {
            formatArr[i] = formatList.get(i).getFormat_name();
        }

        ArrayAdapter<String> eventTypeAdapter = new ArrayAdapter<String>(CreateEventActivity.this, R.layout.spinner_item, formatArr);
        eventTypeAdapter.setDropDownViewResource(R.layout.custome_spinner);
        // event.setAdapter(new NothingSelectedSpinnerAdapter(eventTypeAdapter, R.layout.spinner_row_nothing_selected, MainActivity.this));
        //event.setPrompt("Select");

        formatSpinner.setAdapter(eventTypeAdapter);
        // eventSpinner.setAdapter(eventTypeAdapter);

        final DataBean finalBean = bean;
        formatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                format_id = finalBean.getFormatList().get(position).getFormat_id();
                formate_name = finalBean.getFormatList().get(position).getFormat_name();
                //getTEEList();
                if (radioText.equalsIgnoreCase("1")) {
                    multiRadioTxt = "yes";
                    multiType = "0";

                    multiScreenLayout.setVisibility(View.GONE);
                } else if (radioText.equalsIgnoreCase("4+")) {

                    multiRadioTxt = "no";
                    multiType = "0";

                    multiScreenLayout.setVisibility(View.GONE);

                } else if (format_id.equalsIgnoreCase("10") || format_id.equalsIgnoreCase("11") || format_id.equalsIgnoreCase("12") || format_id.equalsIgnoreCase("13") || format_id.equalsIgnoreCase("14")) {
                    multiRadioTxt = "yes";
                    multiType = "0";
                    multiScreenLayout.setVisibility(View.GONE);
                } else {
                    multiScreenLayout.setVisibility(View.VISIBLE);
                    multiType = "1";
                    multiRadioTxt = "No";

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
                    || y < w.getTop() || y > w.getBottom())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }


    public void getGolfCourse() {

        final ProgressDialog pDialog = new ProgressDialog(this, R.style.MyTheme);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        JSONObject jsonObject = null;

        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        final String user_ID = pref.getString("userId", null);
        final String accessToken = pref.getString("authorization_key", null);


        String latitude = String.valueOf(gpsTracker.getLatitude());
        String Longitude = String.valueOf(gpsTracker.getLongitude());


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("latitude", latitude);
            jsonObject.putOpt("longitude", Longitude);
            jsonObject.putOpt("version", "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GOLF_COURSE_NEAREST_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getGolfListResponse(response);
                scroll.setVisibility(View.VISIBLE);
                Log.e("DealsActivity", "GetDealList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();

                refreshGolf();
            }
        });
        Utility.showLogError(this, "Error in " + "GetDealList URL = " + PUTTAPI.GOLF_COURSE_NEAREST_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getGolfListResponse(JSONObject response) {

        list1 = new ArrayList<GolfCourseBean>();

        if (response != null) {
            try {

                // JSONObject jsonObject = response.getJSONObject("output");

                JSONArray jsonArray = response.getJSONArray("GolfcourseNerabyDistance");
                String b = jsonArray.getJSONObject(0).getString("golf_course_name");
                String golfCourseName = b.toUpperCase();
                String city_name = jsonArray.getJSONObject(0).getString("city_name");
                String golfID = jsonArray.getJSONObject(0).getString("golf_course_id");

                golf_couse_id = golfID;
                selectedGolfCourseTEXT.setText(golfCourseName);

                getTEEList1();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void teeMethod() {

        final Dialog dialog = new Dialog(CreateEventActivity.this, android.R.style.Theme_Translucent_NoTitleBar);

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


        doneBTN_Tee = (TextView) dialog.findViewById(R.id.tee_doneTXT);

        parentTee = (RelativeLayout) dialog.findViewById(R.id.parentTee);
        m1 = (TextView) dialog.findViewById(R.id.m1);
        m2 = (TextView) dialog.findViewById(R.id.m2);
        m3 = (TextView) dialog.findViewById(R.id.m3);
        m4 = (TextView) dialog.findViewById(R.id.m4);
        m5 = (TextView) dialog.findViewById(R.id.m5);
        m6 = (TextView) dialog.findViewById(R.id.m6);
        m7 = (TextView) dialog.findViewById(R.id.m7);
        m8 = (TextView) dialog.findViewById(R.id.m8);

        w1 = (TextView) dialog.findViewById(R.id.w1);
        w2 = (TextView) dialog.findViewById(R.id.w2);
        w3 = (TextView) dialog.findViewById(R.id.w3);
        w4 = (TextView) dialog.findViewById(R.id.w4);
        w5 = (TextView) dialog.findViewById(R.id.w5);
        w6 = (TextView) dialog.findViewById(R.id.w6);
        w7 = (TextView) dialog.findViewById(R.id.w7);
        w8 = (TextView) dialog.findViewById(R.id.w8);

        j1 = (TextView) dialog.findViewById(R.id.j1);
        j2 = (TextView) dialog.findViewById(R.id.j2);
        j3 = (TextView) dialog.findViewById(R.id.j3);
        j4 = (TextView) dialog.findViewById(R.id.j4);
        j5 = (TextView) dialog.findViewById(R.id.j5);
        j6 = (TextView) dialog.findViewById(R.id.j6);
        j7 = (TextView) dialog.findViewById(R.id.j7);
        j8 = (TextView) dialog.findViewById(R.id.j8);

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
                if (red.equalsIgnoreCase("jr")) {
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jb")) {
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jy")) {
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jg")) {
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jw")) {
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jl")) {
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ju")) {
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("js")) {
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }


                j1.setAlpha(0.5f);
                j2.setAlpha(1.0f);
                j3.setAlpha(1.0f);
                j4.setAlpha(1.0f);
                j5.setAlpha(1.0f);
                j6.setAlpha(1.0f);
                j7.setAlpha(1.0f);
                j8.setAlpha(1.0f);

                //  dialog.cancel();
            }
        });

        j2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = j2.getText().toString();
                if (red.equalsIgnoreCase("jr")) {
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jb")) {
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jy")) {
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jg")) {
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jw")) {
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jl")) {
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ju")) {
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("js")) {
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                j2.setAlpha(0.5f);
                j1.setAlpha(1.0f);
                j3.setAlpha(1.0f);
                j4.setAlpha(1.0f);
                j5.setAlpha(1.0f);
                j6.setAlpha(1.0f);
                j7.setAlpha(1.0f);
                j8.setAlpha(1.0f);
                // dialog.cancel();
            }
        });


        j3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String red = j3.getText().toString();
                if (red.equalsIgnoreCase("jr")) {
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jb")) {
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jy")) {
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jg")) {
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jw")) {
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jl")) {
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ju")) {
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("js")) {
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                // dialog.cancel();
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
                if (red.equalsIgnoreCase("jr")) {
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jb")) {
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jy")) {
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jg")) {
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jw")) {
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jl")) {
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ju")) {
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("js")) {
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                // dialog.cancel();
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
                if (red.equalsIgnoreCase("jr")) {
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jb")) {
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jy")) {
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jg")) {
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jw")) {
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jl")) {
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ju")) {
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("js")) {
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                // dialog.cancel();
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
                if (red.equalsIgnoreCase("jr")) {
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jb")) {
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jy")) {
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jg")) {
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jw")) {
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jl")) {
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ju")) {
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("js")) {
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                // dialog.cancel();
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
                if (red.equalsIgnoreCase("jr")) {
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jb")) {
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jy")) {
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jg")) {
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jw")) {
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jl")) {
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ju")) {
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("js")) {
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //  dialog.cancel();
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
                if (red.equalsIgnoreCase("jr")) {
                    jTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    jColorName = "Red";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jb")) {
                    jTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    jColorName = "Black";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jy")) {
                    jTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    jColorName = "Yellow";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jg")) {
                    jTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    jColorName = "green";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("jw")) {
                    jTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    jColorName = "White";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("jl")) {
                    jTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    jColorName = "Gold";
                    jText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ju")) {
                    jTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    jColorName = "Blue";
                    jText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("js")) {
                    jTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    jColorName = "Silver";
                    jText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                // dialog.cancel();
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
                if (red.equalsIgnoreCase("wr")) {
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wb")) {
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wy")) {
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wg")) {
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ww")) {
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wl")) {
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wu")) {
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ws")) {
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //dialog.cancel();
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
                if (red.equalsIgnoreCase("wr")) {
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wb")) {
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wy")) {
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wg")) {
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ww")) {
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wl")) {
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wu")) {
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ws")) {
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                // dialog.cancel();
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
                if (red.equalsIgnoreCase("wr")) {
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wb")) {
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wy")) {
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wg")) {
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ww")) {
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wl")) {
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wu")) {
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ws")) {
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //  dialog.cancel();
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
                if (red.equalsIgnoreCase("wr")) {
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wb")) {
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wy")) {
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wg")) {
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ww")) {
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wl")) {
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wu")) {
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ws")) {
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                // dialog.cancel();
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
                if (red.equalsIgnoreCase("wr")) {
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wb")) {
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wy")) {
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wg")) {
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ww")) {
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wl")) {
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wu")) {
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ws")) {
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //  dialog.cancel();
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
                if (red.equalsIgnoreCase("wr")) {
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wb")) {
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wy")) {
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wg")) {
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ww")) {
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wl")) {
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wu")) {
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ws")) {
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //  dialog.cancel();
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
                if (red.equalsIgnoreCase("wr")) {
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wb")) {
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wy")) {
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wg")) {
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ww")) {
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wl")) {
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wu")) {
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ws")) {
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //  dialog.cancel();
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
                if (red.equalsIgnoreCase("wr")) {
                    wTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    wColorName = "Red";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wb")) {
                    wTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    wColorName = "Black";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("wy")) {
                    wTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    wColorName = "Yellow";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wg")) {
                    wTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    wColorName = "green";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ww")) {
                    wTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    wColorName = "White";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wl")) {
                    wTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    wColorName = "Gold";
                    wText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("wu")) {
                    wTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    wColorName = "Blue";
                    wText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ws")) {
                    wTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    wColorName = "Silver";
                    wText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //  dialog.cancel();
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
                if (red.equalsIgnoreCase("mr")) {
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mb")) {
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("my")) {
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mg")) {
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mw")) {
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ml")) {
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mu")) {
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ms")) {
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                // dialog.cancel();
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
                if (red.equalsIgnoreCase("mr")) {
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mb")) {
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("my")) {
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mg")) {
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mw")) {
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ml")) {
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mu")) {
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ms")) {
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //  dialog.cancel();
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
                if (red.equalsIgnoreCase("mr")) {
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mb")) {
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("my")) {
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mg")) {
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mw")) {
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ml")) {
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mu")) {
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ms")) {
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                // dialog.cancel();
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
                if (red.equalsIgnoreCase("mr")) {
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mb")) {
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("my")) {
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mg")) {
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mw")) {
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ml")) {
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mu")) {
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ms")) {
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //  dialog.cancel();
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
                if (red.equalsIgnoreCase("mr")) {
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mb")) {
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("my")) {
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mg")) {
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mw")) {
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ml")) {
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mu")) {
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ms")) {
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //   dialog.cancel();
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
                if (red.equalsIgnoreCase("mr")) {
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mb")) {
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("my")) {
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mg")) {
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mw")) {
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ml")) {
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mu")) {
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ms")) {
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //  dialog.cancel();
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
                if (red.equalsIgnoreCase("mr")) {
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mb")) {
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("my")) {
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mg")) {
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mw")) {
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ml")) {
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mu")) {
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ms")) {
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                //  dialog.cancel();
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
                if (red.equalsIgnoreCase("mr")) {
                    mTEE_radio.setBackgroundResource(R.drawable.red_tee);
                    mColorName = "Red";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mb")) {
                    mTEE_radio.setBackgroundResource(R.drawable.black_tee);
                    mColorName = "Black";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("my")) {
                    mTEE_radio.setBackgroundResource(R.drawable.yello_tee);
                    mColorName = "Yellow";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mg")) {
                    mTEE_radio.setBackgroundResource(R.drawable.green_tee);
                    mColorName = "green";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("mw")) {
                    mTEE_radio.setBackgroundResource(R.drawable.white_tee);
                    mColorName = "White";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("ml")) {
                    mTEE_radio.setBackgroundResource(R.drawable.gold_tee);
                    mColorName = "Gold";
                    mText.setTextColor(Color.BLACK);
                } else if (red.equalsIgnoreCase("mu")) {
                    mTEE_radio.setBackgroundResource(R.drawable.blue_tee);
                    mColorName = "Blue";
                    mText.setTextColor(Color.WHITE);
                } else if (red.equalsIgnoreCase("ms")) {
                    mTEE_radio.setBackgroundResource(R.drawable.silver_tee);
                    mColorName = "Silver";
                    mText.setTextColor(Color.BLACK);
                } else {
                    Toast.makeText(getApplicationContext(), "No Color Found", Toast.LENGTH_LONG).show();
                }
                // dialog.cancel();
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
                dialog.cancel();
            }
        });

    }

    public void stokeMethod(final String strHole) {

        strH1 = closestText1.getText().toString();
        strH2 = closestText2.getText().toString();
        strH3 = closestText3.getText().toString();
        strH4 = closestText4.getText().toString();

        // Create custom dialog object
        final Dialog dialog = new Dialog(CreateEventActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
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

    private void getStokeHoleList() {

        final ProgressDialog pDialog = new ProgressDialog(this, R.style.MyTheme);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.show();
        JSONObject jsonObject = null;

        jsonObject = new JSONObject();

        try {
            jsonObject.putOpt("golf_course_id", golf_couse_id);
            jsonObject.putOpt("option", option);
            jsonObject.putOpt("version", "2");
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


        if (response != null) {
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                JSONArray jsonArray = jsonObject1.getJSONArray("par3_holes");
                for (int i = 0; i < jsonArray.length(); i++) {
                    HoleBeanFirst holeBeanFirst = new HoleBeanFirst();
                    holeBeanFirst.setHole(jsonArray.getJSONObject(i).getString("hole"));

                    list.add(holeBeanFirst);
                    // mArray[i].setVisibility(View.VISIBLE);
                    holeArray[i].setVisibility(View.VISIBLE);
                    holeArray[i].setText(holeBeanFirst.getHole());

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

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void stokeMethod1(final String stringLong) {


        strL1 = straightDriveText1.getText().toString();
        strL2 = straightDriveText2.getText().toString();
        strL3 = straightDriveText3.getText().toString();
        strL4 = straightDriveText4.getText().toString();
        // Create custom dialog object
        final Dialog dialog = new Dialog(CreateEventActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
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

        getStokeHoleList1();


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

                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {

                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();


                }
            }
        });
        hole2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole2.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {

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
                } else {

                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();


                }
            }
        });
        hole3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole3.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {

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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole4.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole5.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole6.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole7.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole8.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole9.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole10.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole11.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole12.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole13.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole14.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole15.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {

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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole16.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole17.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {

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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole18.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole19.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in straight drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hole1Text = hole20.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strL1) && !hole1Text.equalsIgnoreCase(strL2)) && (!hole1Text.equalsIgnoreCase(strL3) && !hole1Text.equalsIgnoreCase(strL4))) {


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
                } else {
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


        final Dialog dialog = new Dialog(CreateEventActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
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

                Log.v("straightHoleCounter", "" + holeConter_s);

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

        getStokeHoleList1();


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

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {

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

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {

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

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {

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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        hole4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole4.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {


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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        hole5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole5.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {

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

                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole6.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {

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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        hole7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole7.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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

                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole8.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole9.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        hole10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole10.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        hole11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole11.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        hole12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole12.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {

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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        hole13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole13.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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

                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        hole14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole14.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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

                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole15.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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

                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole16.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole17.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole18.getText().toString();
                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hole19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole19.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {
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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        hole20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hole1Text = hole20.getText().toString();

                if ((!hole1Text.equalsIgnoreCase(strS1) && !hole1Text.equalsIgnoreCase(strS2)) && (!hole1Text.equalsIgnoreCase(strS3) && !hole1Text.equalsIgnoreCase(strS4))) {

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
                } else {
                    Toast.makeText(getApplicationContext(), "you have already selected in long drive ", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private void getStokeHoleList1() {

        final ProgressDialog pDialog = new ProgressDialog(this, R.style.MyTheme);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.show();
        JSONObject jsonObject = null;

        jsonObject = new JSONObject();

        try {
            jsonObject.putOpt("golf_course_id", golf_couse_id);
            jsonObject.putOpt("option", option);
            jsonObject.putOpt("version", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.HOLE_NUMBER_SHOW, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Hole List", "create Account = OnResponse= " + response.toString());

                stokeHoleResponse1(response);
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

    private void stokeHoleResponse1(JSONObject response) {
        final List<HoleBeanFirst> list = new ArrayList<HoleBeanFirst>();
        final List<HoleBeanSecond> list1 = new ArrayList<HoleBeanSecond>();


        if (response != null) {
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                JSONArray jsonArray = jsonObject1.getJSONArray("par3_holes");
                for (int i = 0; i < jsonArray.length(); i++) {
                    HoleBeanFirst holeBeanFirst = new HoleBeanFirst();
                    holeBeanFirst.setHole(jsonArray.getJSONObject(i).getString("hole"));

                    list.add(holeBeanFirst);
                    // mArray[i].setVisibility(View.VISIBLE);
                    //  holeArray[i].setVisibility(View.VISIBLE);
                    //  holeArray[i].setText(holeBeanFirst.getHole());

                }
                JSONArray jsonArray1 = jsonObject1.getJSONArray("par4n5_holes");
                for (int i = 0; i < jsonArray1.length(); i++) {
                    HoleBeanSecond holeBeanSecond = new HoleBeanSecond();
                    holeBeanSecond.setHole(jsonArray1.getJSONObject(i).getString("hole"));

                    list1.add(holeBeanSecond);
                    holeArray2[i].setVisibility(View.VISIBLE);
                    holeArray2[i].setText(holeBeanSecond.getHole());
                }


                holeParent.setVisibility(View.VISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerDialog, long millseconds) {
        String text = getDateToString(millseconds);
        // dateTime_Text.setText(text);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }


    public void dateTimeDialog() {
        //  bdkjfhdkjs
        final Dialog dialog = new Dialog(CreateEventActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
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
        previewDate = (TextView) dialog.findViewById(R.id.popup_preview_date);


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
        calendar.add(Calendar.DAY_OF_YEAR, +(365 * 30));

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

    private boolean checkAndRequestPermissions() {

        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissionsNeeded = new ArrayList<String>();
            final List<String> permissionsList = new ArrayList<String>();
            if (!addPermission(permissionsList, android.Manifest.permission.ACCESS_FINE_LOCATION))
                permissionsNeeded.add("GPS");
            if (!addPermission(permissionsList, android.Manifest.permission.CAMERA))
                permissionsNeeded.add("CAMERA");

            if (!permissionsNeeded.isEmpty()) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);

                return false;
            }

        } else {
            // Pre-Marshmallow
        }


        return true;

    }


    @TargetApi(Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(android.Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);

                perms.put(android.Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && perms.get(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    // insertDummyContact();
                } else {
                    // Permission Denied
                    Toast.makeText(CreateEventActivity.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /*public void refreshFormat(){

        final Dialog dialog = new Dialog(CreateEventActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog.setContentView(R.layout.refresh_tee_format);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView dialogText =(TextView) dialog.findViewById(R.id.popup_preview);

        RelativeLayout cancelBTN = (RelativeLayout)dialog.findViewById(R.id.cancel_popup);

        RelativeLayout createBTN = (RelativeLayout)dialog.findViewById(R.id.refresh_popup);
        String popUpMsg = "Due to internet connection format can't load please refresh.";

        dialogText.setText(popUpMsg );
        dialog.show();

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  getFormatList();
                dialog.cancel();

            }
        });
    }*/


    public void refreshTEE() {

        final Dialog dialog = new Dialog(CreateEventActivity.this, android.R.style.Theme_Translucent_NoTitleBar);

        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams wlp = window.getAttributes();
        dialog.setCanceledOnTouchOutside(false);

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog.setContentView(R.layout.refresh_tee_format);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView dialogText = (TextView) dialog.findViewById(R.id.popup_preview);

        RelativeLayout cancelBTN = (RelativeLayout) dialog.findViewById(R.id.cancel_popup);
        RelativeLayout createBTN = (RelativeLayout) dialog.findViewById(R.id.refresh_popup);
        String popUpMsg = "Due to slow internet connection TEE Colors can't load, so please refresh.";

        dialogText.setText(popUpMsg);
        dialog.show();

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();

                getTEEList1();



            }
        });
    }

    public void refreshGolf() {

        final Dialog dialog = new Dialog(CreateEventActivity.this, android.R.style.Theme_Translucent_NoTitleBar);

        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams wlp = window.getAttributes();
        dialog.setCanceledOnTouchOutside(false);

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog.setContentView(R.layout.refresh_tee_format);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView dialogText = (TextView) dialog.findViewById(R.id.popup_preview);

        RelativeLayout cancelBTN = (RelativeLayout) dialog.findViewById(R.id.cancel_popup);
        RelativeLayout createBTN = (RelativeLayout) dialog.findViewById(R.id.refresh_popup);
        String popUpMsg = "Due to slow internet connection Golf Courses can't load, so please refresh.";

        dialogText.setText(popUpMsg);
        dialog.show();

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();

                getGolfCourse();



            }
        });
    }


    public void formatIconPopup(String msg,String title){

        final Dialog dialog = new Dialog(CreateEventActivity.this, android.R.style.Theme_Holo_Light_Dialog);

        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        // Include dialog.xml file
        dialog.setContentView(R.layout.format_icon_popup);

        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView msgText = (TextView)dialog.findViewById(R.id.format_info);
        TextView titleText = (TextView)dialog.findViewById(R.id.e1);

        msgText.setText(msg);
        titleText.setText(title);


        dialog.show();
    }


}
