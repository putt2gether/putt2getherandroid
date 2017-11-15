package com.putt2gether.putt.addScore;

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
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.putt2gether.adapter.pageradapter.PagerAdapterLeaderBoard;
import com.putt2gether.fragments.addscorefrags.BoardEight;
import com.putt2gether.fragments.addscorefrags.BoardEleven;
import com.putt2gether.fragments.addscorefrags.BoardFive;
import com.putt2gether.fragments.addscorefrags.BoardFour;
import com.putt2gether.fragments.addscorefrags.BoardNine;
import com.putt2gether.fragments.addscorefrags.BoardOne;
import com.putt2gether.fragments.addscorefrags.BoardSeven;
import com.putt2gether.fragments.addscorefrags.BoardSix;
import com.putt2gether.fragments.addscorefrags.BoardTen;
import com.putt2gether.fragments.addscorefrags.BoardThree;
import com.putt2gether.fragments.addscorefrags.BoardThrtn;
import com.putt2gether.fragments.addscorefrags.BoardTwelv;
import com.putt2gether.fragments.addscorefrags.BoardTwo;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.HomeActivity;
import com.putt2gether.putt.score_card.ScoreCardTemplate;
import com.putt2gether.utils.Utility;
import com.putt2gether.view.LatoTextView;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 8/30/2016.
 */
public class Leaderboard extends AppCompatActivity {

    TabLayout tab_leader;
    TabLayout tab_leader2;
    ViewPager viewPagerleader;
    LinearLayout bottom_leader;
    ImageView back_preview_leader;
    private LinearLayout mTabsLinearLayout;

    ArrayList<String> tabname = new ArrayList<String>();
    ArrayList<String> tab_id = new ArrayList<String>();
    ArrayList<String> isSpotType = new ArrayList<String>();


    private LatoTextView eventName;
    private LatoTextView golfCourseName;

    String format_id;
    String event_Id;
    String isSpotValue;
    String format_name ;
    String golf_course_name;
    String event_name;
    String tabFlag = null;
    String eventID;
    private LinearLayout shareBTN;

    ImageView bannerImage;
    String banner_id;
    String banner_url;
    String banner_img;
    LinearLayout stats_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        stats_btn = (LinearLayout)findViewById(R.id.stats_btn);
        String statsType = getIntent().getStringExtra("stats_visible");
        if (statsType!=null && statsType.equalsIgnoreCase("yes")){

            stats_btn.setVisibility(View.VISIBLE);
        }

        eventID = getIntent().getStringExtra("eventID");

        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        final String admin_ID = pref.getString("userId", null);

        stats_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScoreCardTemplate.class);
                intent.putExtra("event_id",eventID);
                intent.putExtra("player_id",admin_ID);
                startActivity(intent);
            }
        });

        ConnectionDetector connectionDetector = new ConnectionDetector(this);

        if (connectionDetector.isConnectingToInternet()) {

            getBannerList(eventID);
            getLeaderBoard(eventID);

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connections ", Toast.LENGTH_SHORT).show();
        }

        shareBTN  = (LinearLayout)findViewById(R.id.shareLeaderBoard);

        shareBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                getScreenShot(rootView);

            }
        });

        bannerImage = (ImageView)findViewById(R.id.banner_leaderboard);
        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (banner_url!=null && banner_url.length()>10) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(banner_url));
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

        tab_leader2 = (TabLayout)findViewById(R.id.tab_layout_leaderboard2);
        tab_leader = (TabLayout) findViewById(R.id.tab_layout_leaderboard);
        bottom_leader = (LinearLayout) findViewById(R.id.bottom_leader);
        back_preview_leader = (ImageView) findViewById(R.id.back_preview_leader);
        viewPagerleader = (ViewPager) findViewById(R.id.pager_leaderboard);
        eventName = (LatoTextView) findViewById(R.id.eventName);
        golfCourseName = (LatoTextView) findViewById(R.id.golfCourseName);


        String from = getIntent().getStringExtra("from");
        if (from!=null){
            bottom_leader.setVisibility(View.GONE);
        }else {
            bottom_leader.setVisibility(View.VISIBLE);
        }

        //set gravity for tab bar
    //    tab_leader.setTabGravity(TabLayout.GRAVITY_FILL);

        back_preview_leader.setOnClickListener(new View.OnClickListener() {
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

      /*  String stackClear = getIntent().getStringExtra("stack_manage");
        if (stackClear !=null && stackClear.equalsIgnoreCase("1")) {
            bottom_leader.setVisibility(View.GONE);
        }else {
            bottom_leader.setVisibility(View.VISIBLE);
        }
*/

        bottom_leader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stackClear = getIntent().getStringExtra("from");
                if (stackClear !=null && stackClear.equalsIgnoreCase("delegate")) {

                    Intent intent = new Intent(getApplicationContext(),  AddScoreNew.class);
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

    }



    private void createTabIcons(int numbertabs) {

        Log.v("tabbb",tabFlag);

        if (numbertabs == 13) {

            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();
            String fourthname = tabname.get(2).toUpperCase();
            String fivename = tabname.get(3).toUpperCase();
            String sixname = tabname.get(4).toUpperCase();
            String sevenname = tabname.get(5).toUpperCase();
            String eightname = tabname.get(6).toUpperCase();
            String ninename = tabname.get(7).toUpperCase();
            String tenname = tabname.get(8).toUpperCase();
            String elevenname = tabname.get(9).toUpperCase();
            String twelname = tabname.get(10).toUpperCase();
            String thrtnname = tabname.get(11).toUpperCase();


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);

            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));
            tab_leader.getTabAt(0).setCustomView(tabOne);



            View twotab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setTextColor(getResources().getColor(R.color.white));
            tv2.setText(secondname);

            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(1).setCustomView(twotab);

            View threetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv3 = (LatoTextView) threetab.findViewById(R.id.tab_title_name);
            tv3.setTextColor(getResources().getColor(R.color.white));
            tv3.setText(thirdname);
            RelativeLayout tab_bg3 = (RelativeLayout) threetab.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(2).setCustomView(threetab);

            View fourtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv4 = (LatoTextView) fourtab.findViewById(R.id.tab_title_name);
            tv4.setTextColor(getResources().getColor(R.color.white));
            tv4.setText(fourthname);
            RelativeLayout tab_bg4 = (RelativeLayout) fourtab.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(3).setCustomView(fourtab);


            View fivetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv5 = (LatoTextView) fivetab.findViewById(R.id.tab_title_name);
            tv5.setTextColor(getResources().getColor(R.color.white));
            tv5.setText(fivename);
            RelativeLayout tab_bg5 = (RelativeLayout) fivetab.findViewById(R.id.tab_bg);
            tab_bg5.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(4).setCustomView(fivetab);

            View sixtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv6 = (LatoTextView) sixtab.findViewById(R.id.tab_title_name);
            tv6.setTextColor(getResources().getColor(R.color.white));
            tv6.setText(sixname);
            RelativeLayout tab_bg6 = (RelativeLayout) sixtab.findViewById(R.id.tab_bg);
            tab_bg6.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(5).setCustomView(sixtab);

            View seventab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv7 = (LatoTextView) seventab.findViewById(R.id.tab_title_name);
            tv7.setTextColor(getResources().getColor(R.color.white));
            tv7.setText(sevenname);
            RelativeLayout tab_bg7 = (RelativeLayout) seventab.findViewById(R.id.tab_bg);
            tab_bg7.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(6).setCustomView(seventab);

            View eighttab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv8 = (LatoTextView) eighttab.findViewById(R.id.tab_title_name);
            tv8.setTextColor(getResources().getColor(R.color.white));
            tv8.setText(eightname);
            RelativeLayout tab_bg8 = (RelativeLayout) eighttab.findViewById(R.id.tab_bg);
            tab_bg8.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(7).setCustomView(eighttab);

            View ninetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv9 = (LatoTextView) ninetab.findViewById(R.id.tab_title_name);
            tv9.setTextColor(getResources().getColor(R.color.white));
            tv9.setText(ninename);
            RelativeLayout tab_bg9 = (RelativeLayout) ninetab.findViewById(R.id.tab_bg);
            tab_bg9.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(8).setCustomView(ninetab);

            View tentab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv10 = (LatoTextView) tentab.findViewById(R.id.tab_title_name);
            tv10.setTextColor(getResources().getColor(R.color.white));
            tv10.setText(tenname);
            RelativeLayout tab_bg10 = (RelativeLayout) tentab.findViewById(R.id.tab_bg);
            tab_bg10.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(9).setCustomView(tentab);

            View eleventab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv11 = (LatoTextView) eleventab.findViewById(R.id.tab_title_name);
            tv11.setTextColor(getResources().getColor(R.color.white));
            tv11.setText(elevenname);
            RelativeLayout tab_bg11 = (RelativeLayout) eleventab.findViewById(R.id.tab_bg);
            tab_bg11.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(10).setCustomView(eleventab);

            View telvtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv12 = (LatoTextView) telvtab.findViewById(R.id.tab_title_name);
            tv12.setTextColor(getResources().getColor(R.color.white));
            tv12.setText(twelname);
            RelativeLayout tab_bg12 = (RelativeLayout) telvtab.findViewById(R.id.tab_bg);
            tab_bg12.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(11).setCustomView(telvtab);


            View thrtntab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv13 = (LatoTextView) thrtntab.findViewById(R.id.tab_title_name);
            tv13.setTextColor(getResources().getColor(R.color.white));
            tv13.setText(thrtnname);
            RelativeLayout tab_bg13 = (RelativeLayout) thrtntab.findViewById(R.id.tab_bg);
            tab_bg13.setBackgroundColor(getResources().getColor(R.color.puttText));
            View tabdivider = (View) thrtntab.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);
            tab_leader.getTabAt(12).setCustomView(thrtntab);
        }

        if (numbertabs == 12) {

            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();
            String fourthname = tabname.get(2).toUpperCase();
            String fivename = tabname.get(3).toUpperCase();
            String sixname = tabname.get(4).toUpperCase();
            String sevenname = tabname.get(5).toUpperCase();
            String eightname = tabname.get(6).toUpperCase();
            String ninename = tabname.get(7).toUpperCase();
            String tenname = tabname.get(8).toUpperCase();
            String elevenname = tabname.get(9).toUpperCase();
            String twelname = tabname.get(10).toUpperCase();

           // Log.v("hghkjhkjhiohoih",firstname+secondname+thirdname+fourthname+fivename+sixname);

            View tabOne = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);

            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));
            tab_leader.getTabAt(0).setCustomView(tabOne);


            View twotab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setTextColor(getResources().getColor(R.color.white));
            tv2.setText(secondname);
            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(1).setCustomView(twotab);

            View threetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv3 = (LatoTextView) threetab.findViewById(R.id.tab_title_name);
            tv3.setTextColor(getResources().getColor(R.color.white));
            tv3.setText(thirdname);
            RelativeLayout tab_bg3 = (RelativeLayout) threetab.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(2).setCustomView(threetab);

            View fourtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv4 = (LatoTextView) fourtab.findViewById(R.id.tab_title_name);
            tv4.setTextColor(getResources().getColor(R.color.white));
            tv4.setText(fourthname);
            RelativeLayout tab_bg4 = (RelativeLayout) fourtab.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(3).setCustomView(fourtab);


            View fivetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv5 = (LatoTextView) fivetab.findViewById(R.id.tab_title_name);
            tv5.setTextColor(getResources().getColor(R.color.white));
            tv5.setText(fivename);
            RelativeLayout tab_bg5 = (RelativeLayout) fivetab.findViewById(R.id.tab_bg);
            tab_bg5.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(4).setCustomView(fivetab);

            View sixtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv6 = (LatoTextView) sixtab.findViewById(R.id.tab_title_name);
            tv6.setTextColor(getResources().getColor(R.color.white));
            tv6.setText(sixname);
            RelativeLayout tab_bg6 = (RelativeLayout) sixtab.findViewById(R.id.tab_bg);
            tab_bg6.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(5).setCustomView(sixtab);

            View seventab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv7 = (LatoTextView) seventab.findViewById(R.id.tab_title_name);
            tv7.setTextColor(getResources().getColor(R.color.white));
            tv7.setText(sevenname);
            RelativeLayout tab_bg7 = (RelativeLayout) seventab.findViewById(R.id.tab_bg);
            tab_bg7.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(6).setCustomView(seventab);

            View eighttab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv8 = (LatoTextView) eighttab.findViewById(R.id.tab_title_name);
            tv8.setTextColor(getResources().getColor(R.color.white));
            tv8.setText(eightname);
            RelativeLayout tab_bg8 = (RelativeLayout) eighttab.findViewById(R.id.tab_bg);
            tab_bg8.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(7).setCustomView(eighttab);

            View ninetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv9 = (LatoTextView) ninetab.findViewById(R.id.tab_title_name);
            tv9.setTextColor(getResources().getColor(R.color.white));
            tv9.setText(ninename);
            RelativeLayout tab_bg9 = (RelativeLayout) ninetab.findViewById(R.id.tab_bg);
            tab_bg9.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(8).setCustomView(ninetab);

            View tentab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv10 = (LatoTextView) tentab.findViewById(R.id.tab_title_name);
            tv10.setTextColor(getResources().getColor(R.color.white));
            tv10.setText(tenname);
            RelativeLayout tab_bg10 = (RelativeLayout) tentab.findViewById(R.id.tab_bg);
            tab_bg10.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(9).setCustomView(tentab);

            View eleventab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv11 = (LatoTextView) eleventab.findViewById(R.id.tab_title_name);
            tv11.setTextColor(getResources().getColor(R.color.white));
            tv11.setText(elevenname);
            RelativeLayout tab_bg11 = (RelativeLayout) eleventab.findViewById(R.id.tab_bg);
            tab_bg11.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(10).setCustomView(eleventab);

            View telvtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv12 = (LatoTextView) telvtab.findViewById(R.id.tab_title_name);
            tv12.setTextColor(getResources().getColor(R.color.white));
            tv12.setText(twelname);
            RelativeLayout tab_bg12 = (RelativeLayout) telvtab.findViewById(R.id.tab_bg);
            tab_bg12.setBackgroundColor(getResources().getColor(R.color.puttText));
            View tabdivider = (View) telvtab.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);
            tab_leader.getTabAt(11).setCustomView(telvtab);
        }

        if (numbertabs == 11) {

            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();
            String fourthname = tabname.get(2).toUpperCase();
            String fivename = tabname.get(3).toUpperCase();
            String sixname = tabname.get(4).toUpperCase();
            String sevenname = tabname.get(5).toUpperCase();
            String eightname = tabname.get(6).toUpperCase();
            String ninename = tabname.get(7).toUpperCase();
            String tenname = tabname.get(8).toUpperCase();
            String elevenname = tabname.get(9).toUpperCase();


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);

            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));
            tab_leader.getTabAt(0).setCustomView(tabOne);



            View twotab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setTextColor(getResources().getColor(R.color.white));
            tv2.setText(secondname);

            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(1).setCustomView(twotab);

            View threetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv3 = (LatoTextView) threetab.findViewById(R.id.tab_title_name);
            tv3.setTextColor(getResources().getColor(R.color.white));
            tv3.setText(thirdname);
            RelativeLayout tab_bg3 = (RelativeLayout) threetab.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(2).setCustomView(threetab);

            View fourtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv4 = (LatoTextView) fourtab.findViewById(R.id.tab_title_name);
            tv4.setTextColor(getResources().getColor(R.color.white));
            tv4.setText(fourthname);
            RelativeLayout tab_bg4 = (RelativeLayout) fourtab.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(3).setCustomView(fourtab);


            View fivetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv5 = (LatoTextView) fivetab.findViewById(R.id.tab_title_name);
            tv5.setTextColor(getResources().getColor(R.color.white));
            tv5.setText(fivename);
            RelativeLayout tab_bg5 = (RelativeLayout) fivetab.findViewById(R.id.tab_bg);
            tab_bg5.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(4).setCustomView(fivetab);

            View sixtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv6 = (LatoTextView) sixtab.findViewById(R.id.tab_title_name);
            tv6.setTextColor(getResources().getColor(R.color.white));
            tv6.setText(sixname);
            RelativeLayout tab_bg6 = (RelativeLayout) sixtab.findViewById(R.id.tab_bg);
            tab_bg6.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(5).setCustomView(sixtab);

            View seventab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv7 = (LatoTextView) seventab.findViewById(R.id.tab_title_name);
            tv7.setTextColor(getResources().getColor(R.color.white));
            tv7.setText(sevenname);
            RelativeLayout tab_bg7 = (RelativeLayout) seventab.findViewById(R.id.tab_bg);
            tab_bg7.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(6).setCustomView(seventab);

            View eighttab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv8 = (LatoTextView) eighttab.findViewById(R.id.tab_title_name);
            tv8.setTextColor(getResources().getColor(R.color.white));
            tv8.setText(eightname);
            RelativeLayout tab_bg8 = (RelativeLayout) eighttab.findViewById(R.id.tab_bg);
            tab_bg8.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(7).setCustomView(eighttab);

            View ninetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv9 = (LatoTextView) ninetab.findViewById(R.id.tab_title_name);
            tv9.setTextColor(getResources().getColor(R.color.white));
            tv9.setText(ninename);
            RelativeLayout tab_bg9 = (RelativeLayout) ninetab.findViewById(R.id.tab_bg);
            tab_bg9.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(8).setCustomView(ninetab);

            View tentab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv10 = (LatoTextView) tentab.findViewById(R.id.tab_title_name);
            tv10.setTextColor(getResources().getColor(R.color.white));
            tv10.setText(tenname);
            RelativeLayout tab_bg10 = (RelativeLayout) tentab.findViewById(R.id.tab_bg);
            tab_bg10.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(9).setCustomView(tentab);

            View eleventab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv11 = (LatoTextView) eleventab.findViewById(R.id.tab_title_name);
            tv11.setTextColor(getResources().getColor(R.color.white));
            tv11.setText(elevenname);
            RelativeLayout tab_bg11 = (RelativeLayout) eleventab.findViewById(R.id.tab_bg);
            tab_bg11.setBackgroundColor(getResources().getColor(R.color.puttText));
            View tabdivider = (View) eleventab.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);
            tab_leader.getTabAt(10).setCustomView(eleventab);
        }

        if (numbertabs == 10) {

            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();
            String fourthname = tabname.get(2).toUpperCase();
            String fivename = tabname.get(3).toUpperCase();
            String sixname = tabname.get(4).toUpperCase();
            String sevenname = tabname.get(5).toUpperCase();
            String eightname = tabname.get(6).toUpperCase();
            String ninename = tabname.get(7).toUpperCase();
            String tenname = tabname.get(8).toUpperCase();


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);

            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));
            tab_leader.getTabAt(0).setCustomView(tabOne);



            View twotab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setTextColor(getResources().getColor(R.color.white));
            tv2.setText(secondname);
            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(1).setCustomView(twotab);

            View threetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv3 = (LatoTextView) threetab.findViewById(R.id.tab_title_name);
            tv3.setTextColor(getResources().getColor(R.color.white));
            tv3.setText(thirdname);
            RelativeLayout tab_bg3 = (RelativeLayout) threetab.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(2).setCustomView(threetab);

            View fourtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv4 = (LatoTextView) fourtab.findViewById(R.id.tab_title_name);
            tv4.setTextColor(getResources().getColor(R.color.white));
            tv4.setText(fourthname);
            RelativeLayout tab_bg4 = (RelativeLayout) fourtab.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(3).setCustomView(fourtab);


            View fivetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv5 = (LatoTextView) fivetab.findViewById(R.id.tab_title_name);
            tv5.setTextColor(getResources().getColor(R.color.white));
            tv5.setText(fivename);
            RelativeLayout tab_bg5 = (RelativeLayout) fivetab.findViewById(R.id.tab_bg);
            tab_bg5.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(4).setCustomView(fivetab);

            View sixtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv6 = (LatoTextView) sixtab.findViewById(R.id.tab_title_name);
            tv6.setTextColor(getResources().getColor(R.color.white));
            tv6.setText(sixname);
            RelativeLayout tab_bg6 = (RelativeLayout) sixtab.findViewById(R.id.tab_bg);
            tab_bg6.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(5).setCustomView(sixtab);

            View seventab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv7 = (LatoTextView) seventab.findViewById(R.id.tab_title_name);
            tv7.setText(sevenname);
            tv7.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg7 = (RelativeLayout) seventab.findViewById(R.id.tab_bg);
            tab_bg7.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(6).setCustomView(seventab);

            View eighttab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv8 = (LatoTextView) eighttab.findViewById(R.id.tab_title_name);
            tv8.setTextColor(getResources().getColor(R.color.white));
            tv8.setText(eightname);
            RelativeLayout tab_bg8 = (RelativeLayout) eighttab.findViewById(R.id.tab_bg);
            tab_bg8.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(7).setCustomView(eighttab);

            View ninetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv9 = (LatoTextView) ninetab.findViewById(R.id.tab_title_name);
            tv9.setTextColor(getResources().getColor(R.color.white));
            tv9.setText(ninename);
            RelativeLayout tab_bg9 = (RelativeLayout) ninetab.findViewById(R.id.tab_bg);
            tab_bg9.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(8).setCustomView(ninetab);

            View tentab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv10 = (LatoTextView) tentab.findViewById(R.id.tab_title_name);
            tv10.setTextColor(getResources().getColor(R.color.white));
            tv10.setText(tenname);
            RelativeLayout tab_bg10 = (RelativeLayout) tentab.findViewById(R.id.tab_bg);
            tab_bg10.setBackgroundColor(getResources().getColor(R.color.puttText));
            View tabdivider = (View) tentab.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);
            tab_leader.getTabAt(9).setCustomView(tentab);
        }

        if (numbertabs == 9) {

            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();
            String fourthname = tabname.get(2).toUpperCase();
            String fivename = tabname.get(3).toUpperCase();
            String sixname = tabname.get(4).toUpperCase();
            String sevenname = tabname.get(5).toUpperCase();
            String eightname = tabname.get(6).toUpperCase();
            String ninename = tabname.get(7).toUpperCase();


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);

            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));
            tab_leader.getTabAt(0).setCustomView(tabOne);


            View twotab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setTextColor(getResources().getColor(R.color.white));
            tv2.setText(secondname);

            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(1).setCustomView(twotab);

            View threetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv3 = (LatoTextView) threetab.findViewById(R.id.tab_title_name);
            tv3.setTextColor(getResources().getColor(R.color.white));
            tv3.setText(thirdname);
            RelativeLayout tab_bg3 = (RelativeLayout) threetab.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(2).setCustomView(threetab);

            View fourtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv4 = (LatoTextView) fourtab.findViewById(R.id.tab_title_name);
            tv4.setTextColor(getResources().getColor(R.color.white));
            tv4.setText(fourthname);
            RelativeLayout tab_bg4 = (RelativeLayout) fourtab.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(3).setCustomView(fourtab);


            View fivetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv5 = (LatoTextView) fivetab.findViewById(R.id.tab_title_name);
            tv5.setTextColor(getResources().getColor(R.color.white));
            tv5.setText(fivename);
            RelativeLayout tab_bg5 = (RelativeLayout) fivetab.findViewById(R.id.tab_bg);
            tab_bg5.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(4).setCustomView(fivetab);

            View sixtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv6 = (LatoTextView) sixtab.findViewById(R.id.tab_title_name);
            tv6.setTextColor(getResources().getColor(R.color.white));
            tv6.setText(sixname);
            RelativeLayout tab_bg6 = (RelativeLayout) sixtab.findViewById(R.id.tab_bg);
            tab_bg6.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(5).setCustomView(sixtab);

            View seventab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv7 = (LatoTextView) seventab.findViewById(R.id.tab_title_name);
            tv7.setTextColor(getResources().getColor(R.color.white));
            tv7.setText(sevenname);
            RelativeLayout tab_bg7 = (RelativeLayout) seventab.findViewById(R.id.tab_bg);
            tab_bg7.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(6).setCustomView(seventab);

            View eighttab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv8 = (LatoTextView) eighttab.findViewById(R.id.tab_title_name);
            tv8.setTextColor(getResources().getColor(R.color.white));
            tv8.setText(eightname);
            RelativeLayout tab_bg8 = (RelativeLayout) eighttab.findViewById(R.id.tab_bg);
            tab_bg8.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(7).setCustomView(eighttab);

            View ninetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv9 = (LatoTextView) ninetab.findViewById(R.id.tab_title_name);
            tv9.setTextColor(getResources().getColor(R.color.white));
            tv9.setText(ninename);
            RelativeLayout tab_bg9 = (RelativeLayout) ninetab.findViewById(R.id.tab_bg);
            tab_bg9.setBackgroundColor(getResources().getColor(R.color.puttText));

            View tabdivider = (View) ninetab.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);
            tab_leader.getTabAt(8).setCustomView(ninetab);
        }

        if (numbertabs == 8) {
            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();
            String fourthname = tabname.get(2).toUpperCase();
            String fivename = tabname.get(3).toUpperCase();
            String sixname = tabname.get(4).toUpperCase();
            String sevenname = tabname.get(5).toUpperCase();
            String eightname = tabname.get(6).toUpperCase();


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);

            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));
            tab_leader.getTabAt(0).setCustomView(tabOne);



            View twotab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setTextColor(getResources().getColor(R.color.white));
            tv2.setText(secondname);

            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(1).setCustomView(twotab);

            View threetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv3 = (LatoTextView) threetab.findViewById(R.id.tab_title_name);
            tv3.setTextColor(getResources().getColor(R.color.white));
            tv3.setText(thirdname);
            RelativeLayout tab_bg3 = (RelativeLayout) threetab.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(2).setCustomView(threetab);

            View fourtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv4 = (LatoTextView) fourtab.findViewById(R.id.tab_title_name);
            tv4.setTextColor(getResources().getColor(R.color.white));
            tv4.setText(fourthname);
            RelativeLayout tab_bg4 = (RelativeLayout) fourtab.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(3).setCustomView(fourtab);


            View fivetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv5 = (LatoTextView) fivetab.findViewById(R.id.tab_title_name);
            tv5.setTextColor(getResources().getColor(R.color.white));
            tv5.setText(fivename);
            RelativeLayout tab_bg5 = (RelativeLayout) fivetab.findViewById(R.id.tab_bg);
            tab_bg5.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(4).setCustomView(fivetab);

            View sixtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv6 = (LatoTextView) sixtab.findViewById(R.id.tab_title_name);
            tv6.setTextColor(getResources().getColor(R.color.white));
            tv6.setText(sixname);

            RelativeLayout tab_bg6 = (RelativeLayout) sixtab.findViewById(R.id.tab_bg);
            tab_bg6.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(5).setCustomView(sixtab);

            View seventab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv7 = (LatoTextView) seventab.findViewById(R.id.tab_title_name);
            tv7.setTextColor(getResources().getColor(R.color.white));
            tv7.setText(sevenname);

            RelativeLayout tab_bg7 = (RelativeLayout) seventab.findViewById(R.id.tab_bg);
            tab_bg7.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(6).setCustomView(seventab);

            View eighttab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv8 = (LatoTextView) eighttab.findViewById(R.id.tab_title_name);
            tv8.setTextColor(getResources().getColor(R.color.white));
            tv8.setText(eightname);
            RelativeLayout tab_bg8 = (RelativeLayout) eighttab.findViewById(R.id.tab_bg);
            tab_bg8.setBackgroundColor(getResources().getColor(R.color.puttText));
            View tabdivider = (View) eighttab.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);

            tab_leader.getTabAt(7).setCustomView(eighttab);
        }

        if (numbertabs == 7) {

            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();
            String fourthname = tabname.get(2).toUpperCase();
            String fivename = tabname.get(3).toUpperCase();
            String sixname = tabname.get(4).toUpperCase();
            String sevenname = tabname.get(5).toUpperCase();


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));
            tab_leader.getTabAt(0).setCustomView(tabOne);

            View twotab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setTextColor(getResources().getColor(R.color.white));
            tv2.setText(secondname);

            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(1).setCustomView(twotab);

            View threetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv3 = (LatoTextView) threetab.findViewById(R.id.tab_title_name);
            tv3.setTextColor(getResources().getColor(R.color.white));
            tv3.setText(thirdname);

            RelativeLayout tab_bg3 = (RelativeLayout) threetab.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(2).setCustomView(threetab);

            View fourtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv4 = (LatoTextView) fourtab.findViewById(R.id.tab_title_name);
            tv4.setTextColor(getResources().getColor(R.color.white));
            tv4.setText(fourthname);

            RelativeLayout tab_bg4 = (RelativeLayout) fourtab.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(3).setCustomView(fourtab);


            View fivetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv5 = (LatoTextView) fivetab.findViewById(R.id.tab_title_name);
            tv5.setTextColor(getResources().getColor(R.color.white));
            tv5.setText(fivename);

            RelativeLayout tab_bg5 = (RelativeLayout) fivetab.findViewById(R.id.tab_bg);
            tab_bg5.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(4).setCustomView(fivetab);

            View sixtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv6 = (LatoTextView) sixtab.findViewById(R.id.tab_title_name);
            tv6.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg6 = (RelativeLayout) sixtab.findViewById(R.id.tab_bg);
            tab_bg6.setBackgroundColor(getResources().getColor(R.color.puttText));
            tv6.setText(sixname);
            tab_leader.getTabAt(5).setCustomView(sixtab);

            View seventab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv7 = (LatoTextView) seventab.findViewById(R.id.tab_title_name);
            tv7.setTextColor(getResources().getColor(R.color.white));
            tv7.setText(sevenname);
            RelativeLayout tab_bg7 = (RelativeLayout) seventab.findViewById(R.id.tab_bg);
            tab_bg7.setBackgroundColor(getResources().getColor(R.color.puttText));
            View tabdivider = (View) seventab.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);
            tab_leader.getTabAt(6).setCustomView(seventab);

        }

        if (numbertabs == 6) {

            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();
            String fourthname = tabname.get(2).toUpperCase();
            String fivename = tabname.get(3).toUpperCase();
            String sixname = tabname.get(4).toUpperCase();


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));

            tab_leader.getTabAt(0).setCustomView(tabOne);



            View twotab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setTextColor(getResources().getColor(R.color.white));
            tv2.setText(secondname);
            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(1).setCustomView(twotab);

            View threetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv3 = (LatoTextView) threetab.findViewById(R.id.tab_title_name);
            tv3.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg3 = (RelativeLayout) threetab.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tv3.setText(thirdname);
            tab_leader.getTabAt(2).setCustomView(threetab);

            View fourtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv4 = (LatoTextView) fourtab.findViewById(R.id.tab_title_name);

            tv4.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg4 = (RelativeLayout) fourtab.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.puttText));
            tv4.setText(fourthname);

            tab_leader.getTabAt(3).setCustomView(fourtab);


            View fivetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv5 = (LatoTextView) fivetab.findViewById(R.id.tab_title_name);
            tv5.setText(fivename);
            tv5.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg5 = (RelativeLayout) fivetab.findViewById(R.id.tab_bg);
            tab_bg5.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(4).setCustomView(fivetab);

            View sixtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv6 = (LatoTextView) sixtab.findViewById(R.id.tab_title_name);
            tv6.setText(sixname);
            tv6.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg6 = (RelativeLayout) sixtab.findViewById(R.id.tab_bg);
            tab_bg6.setBackgroundColor(getResources().getColor(R.color.puttText));

            View tabdivider = (View) sixtab.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);

            tab_leader.getTabAt(5).setCustomView(sixtab);

        }

        if (numbertabs == 5) {

            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();
            String fourthname = tabname.get(2).toUpperCase();
            String fivename = tabname.get(3).toUpperCase();


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));
            tab_leader.getTabAt(0).setCustomView(tabOne);



            View twotab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setText(secondname);
            tv2.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(1).setCustomView(twotab);

            View threetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv3 = (LatoTextView) threetab.findViewById(R.id.tab_title_name);
            tv3.setText(thirdname);
            tv3.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg3 = (RelativeLayout) threetab.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(2).setCustomView(threetab);

            View fourtab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv4 = (LatoTextView) fourtab.findViewById(R.id.tab_title_name);
            tv4.setText(fourthname);
            tv4.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg4 = (RelativeLayout) fourtab.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(3).setCustomView(fourtab);


            View fivetab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv5 = (LatoTextView) fivetab.findViewById(R.id.tab_title_name);
            tv5.setText(fivename);
            tv5.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg5 = (RelativeLayout) fivetab.findViewById(R.id.tab_bg);
            tab_bg5.setBackgroundColor(getResources().getColor(R.color.puttText));

            View tabdivider = (View) fivetab.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);

            tab_leader.getTabAt(4).setCustomView(fivetab);

        }


        if (numbertabs == 4) {

            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();
            String fourthname = tabname.get(2).toUpperCase();


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));
            tab_leader.getTabAt(0).setCustomView(tabOne);



            View twotab = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setText(secondname);
            tv2.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            tab_leader.getTabAt(1).setCustomView(twotab);

            View tabthree = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv3 = (LatoTextView) tabthree.findViewById(R.id.tab_title_name);
            tv3.setTextColor(Color.WHITE);
            RelativeLayout tab_bg3 = (RelativeLayout) tabthree.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tv3.setText(thirdname);
            tab_leader.getTabAt(2).setCustomView(tabthree);

            View tabfour = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
            LatoTextView tv4 = (LatoTextView) tabfour.findViewById(R.id.tab_title_name);
            RelativeLayout tab_bg4 = (RelativeLayout) tabfour.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.puttText));
            tv4.setText(fourthname);
            tv4.setTextColor(Color.WHITE);
            View tabdivider = (View) tabfour.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);
            tab_leader.getTabAt(3).setCustomView(tabfour);
        }

        if (numbertabs == 3) {

            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();
            String thirdname = tabname.get(1).toUpperCase();


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));
            View tabdivider = (View) tabOne.findViewById(R.id.tabdivider);
            if (tabFlag.equalsIgnoreCase("0")) {
                tab_leader.getTabAt(0).setCustomView(tabOne);
            }else {
                tab_leader2.getTabAt(0).setCustomView(tabOne);
            }


            View twotab = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setText(secondname);
            tv2.setTextColor(Color.WHITE);
            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            if (tabFlag.equalsIgnoreCase("0")) {
                tab_leader.getTabAt(1).setCustomView(twotab);
            }else {
                tab_leader2.getTabAt(1).setCustomView(twotab);
            }

            View tabthree = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv3 = (LatoTextView) tabthree.findViewById(R.id.tab_title_name);
            tv3.setTextColor(Color.WHITE);

            RelativeLayout tab_bg3 = (RelativeLayout) tabthree.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.puttText));
            tv3.setText(thirdname);

            View v = (View) tabthree.findViewById(R.id.tabdivider);
            v.setVisibility(View.GONE);

            if (tabFlag.equalsIgnoreCase("0")) {
                tab_leader.getTabAt(2).setCustomView(tabthree);
            }else {
                tab_leader2.getTabAt(2).setCustomView(tabthree);
            }
        }
        if (numbertabs == 2) {
            String firstname = format_name.toUpperCase();
            String secondname = tabname.get(0).toUpperCase();

            View tabOne = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            tv.setTextColor(getResources().getColor(R.color.edit));
            tab_bg.setBackgroundColor(getResources().getColor(R.color.white_color));

            if (tabFlag.equalsIgnoreCase("0")) {
                tab_leader.getTabAt(0).setCustomView(tabOne);
            }else {
                tab_leader2.getTabAt(0).setCustomView(tabOne);
            }

            View twotab = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setTextColor(Color.WHITE);
            tv2.setText(secondname);
            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.puttText));
            View tabddiv2 = (View) twotab.findViewById(R.id.tabdivider);
            tabddiv2.setVisibility(View.GONE);

            if (tabFlag.equalsIgnoreCase("0")) {
                tab_leader.getTabAt(1).setCustomView(twotab);
            }else {
                tab_leader2.getTabAt(1).setCustomView(twotab);
            }

        }
        if (numbertabs == 1) {
            String firstname = format_name.toUpperCase();
            View tabOne = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname);
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            tv.setTextColor(getResources().getColor(R.color.white_color));
            tab_bg.setBackgroundColor(getResources().getColor(R.color.edit));
            View tabdivider = (View) tabOne.findViewById(R.id.tabdivider);
            tabdivider.setVisibility(View.GONE);
            if (tabFlag.equalsIgnoreCase("0")) {
                tab_leader.getTabAt(0).setCustomView(tabOne);
            }else {
                tab_leader2.getTabAt(0).setCustomView(tabOne);
            }
        }
    }

    private void createViewPager(ViewPager viewPager, int numberoftabs) {


        if (numberoftabs == 13) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");
            adapter.addFrag(new BoardFour(), "Tab 4");
            adapter.addFrag(new BoardFive(), "Tab 5");
            adapter.addFrag(new BoardSix(), "Tab 6");
            adapter.addFrag(new BoardSeven(), "Tab 7");
            adapter.addFrag(new BoardEight(), "Tab 8");
            adapter.addFrag(new BoardNine(), "Tab 9");
            adapter.addFrag(new BoardTen(), "Tab 10");
            adapter.addFrag(new BoardEleven(), "Tab 11");
            adapter.addFrag(new BoardTwelv(), "Tab 12");
            adapter.addFrag(new BoardThrtn(), "Tab 13");

            viewPager.setAdapter(adapter);

        }
        if (numberoftabs == 12) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");
            adapter.addFrag(new BoardFour(), "Tab 4");
            adapter.addFrag(new BoardFive(), "Tab 5");
            adapter.addFrag(new BoardSix(), "Tab 6");
            adapter.addFrag(new BoardSeven(), "Tab 7");
            adapter.addFrag(new BoardEight(), "Tab 8");
            adapter.addFrag(new BoardNine(), "Tab 9");
            adapter.addFrag(new BoardTen(), "Tab 10");
            adapter.addFrag(new BoardEleven(), "Tab 11");
            adapter.addFrag(new BoardTwelv(), "Tab 12");
            viewPager.setAdapter(adapter);

        }
        if (numberoftabs == 11) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");
            adapter.addFrag(new BoardFour(), "Tab 4");
            adapter.addFrag(new BoardFive(), "Tab 5");
            adapter.addFrag(new BoardSix(), "Tab 6");
            adapter.addFrag(new BoardSeven(), "Tab 7");
            adapter.addFrag(new BoardEight(), "Tab 8");
            adapter.addFrag(new BoardNine(), "Tab 9");
            adapter.addFrag(new BoardTen(), "Tab 10");
            adapter.addFrag(new BoardEleven(), "Tab 11");

            viewPager.setAdapter(adapter);

        }
        if (numberoftabs == 10) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");
            adapter.addFrag(new BoardFour(), "Tab 4");
            adapter.addFrag(new BoardFive(), "Tab 5");
            adapter.addFrag(new BoardSix(), "Tab 6");
            adapter.addFrag(new BoardSeven(), "Tab 7");
            adapter.addFrag(new BoardEight(), "Tab 8");
            adapter.addFrag(new BoardNine(), "Tab 9");
            adapter.addFrag(new BoardTen(), "Tab 10");



            viewPager.setAdapter(adapter);

        }
        if (numberoftabs == 9) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());


            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");
            adapter.addFrag(new BoardFour(), "Tab 4");
            adapter.addFrag(new BoardFive(), "Tab 5");
            adapter.addFrag(new BoardSix(), "Tab 6");
            adapter.addFrag(new BoardSeven(), "Tab 7");
            adapter.addFrag(new BoardEight(), "Tab 8");
            adapter.addFrag(new BoardNine(), "Tab 9");



            viewPager.setAdapter(adapter);

        }
        if (numberoftabs == 8) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");
            adapter.addFrag(new BoardFour(), "Tab 4");
            adapter.addFrag(new BoardFive(), "Tab 5");
            adapter.addFrag(new BoardSix(), "Tab 6");
            adapter.addFrag(new BoardSeven(), "Tab 7");
            adapter.addFrag(new BoardEight(), "Tab 8");



            viewPager.setAdapter(adapter);

        }
        if (numberoftabs == 7) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");
            adapter.addFrag(new BoardFour(), "Tab 4");
            adapter.addFrag(new BoardFive(), "Tab 5");
            adapter.addFrag(new BoardSix(), "Tab 6");
            adapter.addFrag(new BoardSeven(), "Tab 7");


            viewPager.setAdapter(adapter);

        }
        if (numberoftabs == 6) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");
            adapter.addFrag(new BoardFour(), "Tab 4");
            adapter.addFrag(new BoardFive(), "Tab 5");
            adapter.addFrag(new BoardSix(), "Tab 6");

            viewPager.setAdapter(adapter);

        }
        if (numberoftabs == 5) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");
            adapter.addFrag(new BoardFour(), "Tab 4");
            adapter.addFrag(new BoardFive(), "Tab 5");


            viewPager.setAdapter(adapter);

        }


        if (numberoftabs == 4) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");
            adapter.addFrag(new BoardFour(), "Tab 4");

            viewPager.setAdapter(adapter);

        }
        if (numberoftabs == 3) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");
            adapter.addFrag(new BoardThree(), "Tab 3");

            viewPager.setAdapter(adapter);


        }
        if (numberoftabs == 2) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            adapter.addFrag(new BoardTwo(), "Tab 2");

            viewPager.setAdapter(adapter);


        }
        if (numberoftabs == 1) {
            PagerAdapterLeaderBoard adapter = new PagerAdapterLeaderBoard(getSupportFragmentManager());

            adapter.addFrag(new BoardOne(), "Tab 1");
            viewPager.setAdapter(adapter);


        }
    }


    public void maketabs(int numberoftabs) {
        createViewPager(viewPagerleader, numberoftabs);
        if (tabFlag.equalsIgnoreCase("0")) {
            tab_leader.setupWithViewPager(viewPagerleader);
        }else {
            tab_leader2.setupWithViewPager(viewPagerleader);
        }

        for (int i = 0 ; i < tabname.size();i++){
            Log.v("XYSNAME",tabname.get(i));
        }

        if (tabFlag.equalsIgnoreCase("0")){
            viewPagerleader.addOnPageChangeListener(new MyPageScrollListener(tab_leader));
            tab_leader.setOnTabSelectedListener(onTabSelectedListener(viewPagerleader));
        }else {
            viewPagerleader.addOnPageChangeListener(new MyPageScrollListener(tab_leader2));
            tab_leader2.setOnTabSelectedListener(onTabSelectedListener(viewPagerleader));
        }

        createTabIcons(numberoftabs);

    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener(final ViewPager pager) {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());

                View v = tab.getCustomView();
                LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                tv.setTextColor(getResources().getColor(R.color.accept));
                RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                tab_bg.setBackgroundColor(getResources().getColor(R.color.white));

                if (tabFlag.equalsIgnoreCase("0")) {
                    tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                }else {

                    tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                int tabpos = tab.getPosition();

                if (tabpos == 0) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }


                }
                if (tabpos == 1) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));
                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    };

                }
                if (tabpos == 2) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }
                if (tabpos == 3) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }

                if (tabpos == 4) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }

                if (tabpos == 5) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }

                if (tabpos == 6) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }

                if (tabpos == 7) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }

                if (tabpos == 8) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }

                if (tabpos == 9) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }

                if (tabpos == 10) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }

                if (tabpos == 11) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }

                if (tabpos == 12) {
                    View v = tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.puttText));

                    if (tabFlag.equalsIgnoreCase("0")) {
                        tab_leader.getTabAt(tab.getPosition()).setCustomView(v);
                    }else {

                        tab_leader2.getTabAt(tab.getPosition()).setCustomView(v);
                    }

                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    private class MyPageScrollListener implements ViewPager.OnPageChangeListener {
        private TabLayout mTabLayout;

        public MyPageScrollListener(TabLayout tabLayout) {
            this.mTabLayout = tabLayout;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (mTabLayout != null) {
                mTabLayout.getTabAt(position).select();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }




    public String geteventID() {

         /*getIntent().getStringExtra("eventID").toString()*/
        String a = getIntent().getStringExtra("eventID").toString();
        return a;
    }


    public String isSpotType(int tabNO){
        return isSpotType.get(tabNO);
    }

    public String tabID(int tabNO) {
        return tab_id.get(tabNO);
    }

    public String formatID() {
        return format_id;
    }

    public int gettabcount() {

        int count;

        if (tabFlag.equalsIgnoreCase("0")) {
             count = tab_leader.getTabCount();
        }else {

             count = tab_leader2.getTabCount();
        }



        return count;
    }


    public void getLeaderBoard(String Event_Id) {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            //  jsonObject.putOpt("player_id", playerid);
            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("version", "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GET_LEADERBOARD_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                laederBoardResponse(response);
                Log.e("GET_leaderboard", "GET_leaderboard data" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "GET_leaderboard URL = " + PUTTAPI.GET_LEADERBOARD_URL);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    public void laederBoardResponse(JSONObject response) {
        tab_id = new ArrayList<String>();
        isSpotType = new ArrayList<String>();

        try {
            JSONObject output = response.getJSONObject("output");
            JSONObject data = output.getJSONObject("data");
            isSpotValue = data.getString("is_spot_value");
            if (isSpotValue.equalsIgnoreCase("1")) {

                JSONArray js = data.getJSONArray("is_spot");

                int i = js.length();

                for (int j = 0; j < i; j++) {
                    JSONObject jsonObject1 = js.getJSONObject(j);
                    String dislay_data = jsonObject1.getString("dislay_data");
                    String hole_number = jsonObject1.getString("hole_number");
                    String is_spot_type = jsonObject1.getString("is_spot_type");
                    tabname.add(dislay_data);
                    isSpotType.add(is_spot_type);
                    tab_id.add(hole_number);

                    Log.v("tabID",hole_number);
                }
            }

            format_id = data.getString("format_id");
            event_Id = data.getString("event_id");
            isSpotValue = data.getString("is_spot_value");
            format_name = data.getString("format_name");
            golf_course_name = data.getString("golf_course_name");
            event_name = data.getString("event_name");

            Log.v("EventName",format_name);

            eventName.setText(event_name);
            golfCourseName.setText(golf_course_name);
            if (tabname.size()>2){
                tabFlag = "0";
            }else {
                tabFlag = "1";
            }
            if (!tabname.isEmpty()) {
                maketabs(tabname.size()+1);

            }else {
                maketabs(1);
            }



        } catch (JSONException je)

        {
            je.printStackTrace();
        }

        if (tabFlag.equalsIgnoreCase("0")){
            tab_leader2.setVisibility(View.GONE);
            tab_leader.setVisibility(View.VISIBLE);
        }else {
            tab_leader.setVisibility(View.GONE);
            tab_leader2.setVisibility(View.VISIBLE);
        }
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

    public void getBannerList(String event_ID) {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("type", "3");
            jsonObject.putOpt("event_id",event_ID);
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
                //    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

}
