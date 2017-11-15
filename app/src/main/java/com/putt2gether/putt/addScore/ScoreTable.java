package com.putt2gether.putt.addScore;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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
import com.putt2gether.adapter.pageradapter.PagerAdapterScore;
import com.putt2gether.fragments.addscorefrags.ScoreCardtabtwo;
import com.putt2gether.fragments.addscorefrags.ScoreCardthree;
import com.putt2gether.fragments.addscorefrags.ScorecardFour;
import com.putt2gether.fragments.addscorefrags.ScorecardTableFragment;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.score_card.ScoreCardTemplate;
import com.putt2gether.utils.Utility;
import com.putt2gether.view.LatoTextView;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 8/31/2016.
 */
public class ScoreTable extends AppCompatActivity {

    TabLayout tab_scorecard;
    ViewPager viewPagerscorecard;
    LinearLayout score_addscore,score_leaderboard;
    ImageView back_preview_scorecard;
    String eventid;
    String playerID;
    TextView event_name_scoretable,golf_course_address_scoretable;

    ArrayList<String> tabname=new ArrayList<String>();
    ArrayList<String> tabplayer_id=new ArrayList<String>();
    ArrayList<String> tabhandicap=new ArrayList<String>();
    View line;
    LinearLayout shareBTN;
    LinearLayout bottomBTNS;

    ImageView bannerImage;
    String banner_id;
    String banner_url;
    String banner_img;
    LinearLayout stats_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.score_table);

        stats_btn = (LinearLayout)findViewById(R.id.stats_btn);
        bottomBTNS = (LinearLayout)findViewById(R.id.bottom_scorecard);
        String statsType = getIntent().getStringExtra("stats_visible");
        if (statsType!=null && statsType.equalsIgnoreCase("yes")){

            stats_btn.setVisibility(View.VISIBLE);
            bottomBTNS.setVisibility(View.GONE);
        }

        line = (View)findViewById(R.id.line11);

        shareBTN = (LinearLayout)findViewById(R.id.shareScoreCard);



        shareBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                getScreenShot(rootView);

            }
        });

        bannerImage = (ImageView)findViewById(R.id.banner_oldscorecard);
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




        event_name_scoretable=(LatoTextView)findViewById(R.id.event_name_scoretable);
        golf_course_address_scoretable=(LatoTextView)findViewById(R.id.golf_course_address_scoretable);

        tab_scorecard=(TabLayout)findViewById(R.id.tab_layout_scorecard);
        viewPagerscorecard=(ViewPager)findViewById(R.id.pager_scorecard);

        score_addscore=(LinearLayout)findViewById(R.id.score_addscore);

        String isDelegate = getIntent().getStringExtra("isDelegate");
        if (isDelegate!=null && isDelegate.equalsIgnoreCase("1")){
            score_addscore.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }else {
            score_addscore.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }

        score_leaderboard=(LinearLayout)findViewById(R.id.score_leaderboard);

        back_preview_scorecard=(ImageView)findViewById(R.id.back_preview_scorecard);

        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        final String admin_ID = pref.getString("userId", null);

        eventid = geteventID();
        playerID = getPlayerID();
        if (playerID.equalsIgnoreCase("noneed")){
            playerID=admin_ID;
        }

        ConnectionDetector connectionDetector = new ConnectionDetector(this);
        if (connectionDetector.isConnectingToInternet()) {
            getscorecarddata(playerID, eventid);
            getBannerList(eventid);
        }



        back_preview_scorecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreTable.this.finish();
            }
        });

        score_addscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),AddScoreNew.class);
                intent.putExtra("eventID",eventid);
                startActivity(intent);

                finish();

            }
        });

        stats_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScoreCardTemplate.class);
                intent.putExtra("event_id",eventid);
                intent.putExtra("player_id",admin_ID);
                startActivity(intent);
            }
        });

        score_leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isDelegate = getIntent().getStringExtra("isDelegate");
                if (isDelegate!=null && isDelegate.equalsIgnoreCase("1")){

                    Intent intent = new Intent(getApplicationContext(),Leaderboard.class);
                    intent.putExtra("eventID",eventid);
                    intent.putExtra("from","history");
                    startActivity(intent);
                    finish();

                }else {
                   /* Intent intent = new Intent(getApplicationContext(),Leaderboard.class);
                    intent.putExtra("eventID",eventid);
                    startActivity(intent);*/
                    finish();
                }


            }
        });

    }


    private void createTabIcons(int numbertabs) {

        if(numbertabs==4) {

            String firstname=tabname.get(0).toUpperCase();
            String secondname=tabname.get(1).toUpperCase();
            String thirdname=tabname.get(2).toUpperCase();
            String fourthname=tabname.get(3).toUpperCase();

            String frsthandicap=tabhandicap.get(0);
            String secondhandicap=tabhandicap.get(1);
            String thrdhandicap=tabhandicap.get(2);
            String fourthhandicap=tabhandicap.get(3);

            View tabOne = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname+" "+frsthandicap+" ");
            tv.setTextColor(getResources().getColor(R.color.accept));
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            View tabdivider = (View) tabOne.findViewById(R.id.tabdivider);
            tab_bg.setBackgroundColor(getResources().getColor(R.color.white));
            tab_scorecard.getTabAt(0).setCustomView(tabOne);


            View twotab = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setText(secondname+" "+secondhandicap+" ");
            tv2.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundColor(getResources().getColor(R.color.accept));
            tab_scorecard.getTabAt(1).setCustomView(twotab);

            View tabthree = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv3 = (LatoTextView) tabthree.findViewById(R.id.tab_title_name);
            RelativeLayout tab_bg3 = (RelativeLayout) tabthree.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundColor(getResources().getColor(R.color.accept));
            tv3.setText(thirdname+" "+thrdhandicap+" ");
            tv3.setTextColor(getResources().getColor(R.color.white));
            tab_scorecard.getTabAt(2).setCustomView(tabthree);

            View tabfour = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv4 = (LatoTextView) tabfour.findViewById(R.id.tab_title_name);
            RelativeLayout tab_bg4 = (RelativeLayout) tabfour.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundColor(getResources().getColor(R.color.accept));
            tv4.setText(fourthname+" "+fourthhandicap+" ");
            tv4.setTextColor(getResources().getColor(R.color.white));
            View v = (View) tabfour.findViewById(R.id.tabdivider);
            v.setVisibility(View.GONE);
            tab_scorecard.getTabAt(3).setCustomView(tabfour);
        }

        if(numbertabs==3)
        {

            String firstname=tabname.get(0).toUpperCase();
            String secondname=tabname.get(1).toUpperCase();
            String thirdname=tabname.get(2).toUpperCase();


            String frsthandicap=tabhandicap.get(0);
            String secondhandicap=tabhandicap.get(1);
            String thrdhandicap=tabhandicap.get(2);


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname+" "+frsthandicap+" ");
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            tv.setTextColor(getResources().getColor(R.color.accept));
            tab_bg.setBackgroundColor(getResources().getColor(R.color.white));
            tab_scorecard.getTabAt(0).setCustomView(tabOne);


            View twotab = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setText(secondname+" "+secondhandicap+" ");
            tv2.setTextColor(getResources().getColor(R.color.white));
            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundResource(R.color.accept);
            tab_scorecard.getTabAt(1).setCustomView(twotab);

            View tabthree = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv3 = (LatoTextView) tabthree.findViewById(R.id.tab_title_name);
            RelativeLayout tab_bg3 = (RelativeLayout) tabthree.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundResource(R.color.accept);
            tv3.setText(thirdname+" "+thrdhandicap+" ");
            View v = (View) tabthree.findViewById(R.id.tabdivider);
            v.setVisibility(View.GONE);
            tab_scorecard.getTabAt(2).setCustomView(tabthree);
        }
        if(numbertabs==2)
        {
            String firstname=tabname.get(0).toUpperCase();
            String secondname=tabname.get(1).toUpperCase();

            String frsthandicap=tabhandicap.get(0);
            String secondhandicap=tabhandicap.get(1);


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname+" "+frsthandicap+" ");
            tv.setTextColor(getResources().getColor(R.color.accept));
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            tab_bg.setBackgroundResource(R.color.white);
            View tabdivider = (View) tabOne.findViewById(R.id.tabdivider);
            tab_scorecard.getTabAt(0).setCustomView(tabOne);


            View twotab = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setText(secondname+" "+secondhandicap+" ");
            tv2.setTextColor(getResources().getColor(R.color.white));
            View tabddiv2 = (View) twotab.findViewById(R.id.tabdivider);
            RelativeLayout tab_bg2 = (RelativeLayout) twotab.findViewById(R.id.tab_bg);
            tab_bg2.setBackgroundResource(R.color.accept);
            tabddiv2.setVisibility(View.GONE);
            tab_scorecard.getTabAt(1).setCustomView(twotab);

        }
        if(numbertabs==1)
        {
            String firstname=tabname.get(0).toUpperCase();


            String frsthandicap=tabhandicap.get(0);


            View tabOne = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname+" "+frsthandicap+" ");
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            View tabdivider = (View) tabOne.findViewById(R.id.tabdivider);

            tabdivider.setVisibility(View.GONE);

            tab_scorecard.getTabAt(0).setCustomView(tabOne);
        }
    }

    private void createViewPager(ViewPager viewPager, int numberoftabs) {



        if (numberoftabs == 4) {
            PagerAdapterScore adapter = new PagerAdapterScore(getSupportFragmentManager());

            adapter.addFrag(new ScorecardTableFragment(), "Tab 1");

            adapter.addFrag(new ScoreCardtabtwo(), "Tab 2");
            adapter.addFrag(new ScoreCardthree(), "Tab 3");
            adapter.addFrag(new ScorecardFour(), "Tab 4");
            viewPager.setAdapter(adapter);



        }
        if (numberoftabs == 3) {
            PagerAdapterScore adapter = new PagerAdapterScore(getSupportFragmentManager());
            adapter.addFrag(new ScorecardTableFragment(), "Tab 1");

            adapter.addFrag(new ScoreCardtabtwo(), "Tab 2");
            adapter.addFrag(new ScoreCardthree(), "Tab 3");
            viewPager.setAdapter(adapter);


        }
        if (numberoftabs == 2) {
            PagerAdapterScore adapter = new PagerAdapterScore(getSupportFragmentManager());

            adapter.addFrag(new ScorecardTableFragment(), "Tab 1");

            adapter.addFrag(new ScoreCardtabtwo(), "Tab 2");
            viewPager.setAdapter(adapter);



        }
        if (numberoftabs == 1) {
            PagerAdapterScore adapter = new PagerAdapterScore(getSupportFragmentManager());

            adapter.addFrag(new ScorecardTableFragment(), "Tab 1");

            viewPager.setAdapter(adapter);

        }


       /* PagerAdapterScore adapter = new PagerAdapterScore(getSupportFragmentManager());

        adapter.addFrag(new ScorecardTableFragment(), "Tab 1");

        adapter.addFrag(new ScorecardTableFragment(), "Tab 2");
        adapter.addFrag(new ScorecardTableFragment(), "Tab 3");
        adapter.addFrag(new ScorecardTableFragment(), "Tab 4");
        viewPager.setAdapter(adapter);*/
    }


    public void getscorecarddata(String Admin_ID, String Event_Id) {

        Log.e("ADMINID",Admin_ID);
        Log.e("EVENTID",Event_Id);

        final ProgressDialog pDialog = new ProgressDialog(ScoreTable.this);
        pDialog.setMessage("Loading...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("admin_id", Admin_ID);
            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GETSCORECARDDATA, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                requestaddscorevalue(response);
                Log.e("ADDSCORE CLASS", "Get Scorecard Data" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(ScoreTable.this, "Error in " + "Get Scorecard Data URL = " + PUTTAPI.GETSCORECARDDATA);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void requestaddscorevalue(JSONObject response) {


        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");



                if (status.equalsIgnoreCase("1")) {

                    String golf_course_name = jsonObject.getString("golf_course_name");
                    String events_name = jsonObject.getString("event_name");
                    addValues_next(events_name, golf_course_name);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    int i = jsonArray.length();

                    Log.e("ArrayLength",i+"");



                    for(int j=0;j<i;j++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                        String fullname=jsonObject1.getString("full_name");
                        String handicapnum=jsonObject1.getString("self_handicap");
                        String player_id=jsonObject1.getString("player_id");
                        tabname.add(fullname);
                        tabhandicap.add(handicapnum);
                        tabplayer_id.add(player_id);

                    }
                    if(!tabname.isEmpty()) {
                        maketabs(i);
                    }


                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(ScoreTable.this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    public String geteventID() {

        return getIntent().getStringExtra("eventID").toString();
    }
    public String getPlayerID(){

        return getIntent().getStringExtra("playerID").toString();

    }

    public void addValues_next(String name, String golfname) {
        event_name_scoretable.setText(name.toUpperCase());
        golf_course_address_scoretable.setText(golfname.toUpperCase());
    }


    public void maketabs(int numberoftabs) {

        createViewPager(viewPagerscorecard, numberoftabs);

        tab_scorecard.setupWithViewPager(viewPagerscorecard);


        createTabIcons(numberoftabs);

        viewPagerscorecard.addOnPageChangeListener(new MyPageScrollListener(tab_scorecard));
        tab_scorecard.setOnTabSelectedListener(onTabSelectedListener(viewPagerscorecard));


    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener(final ViewPager pager) {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());

                View v=tab.getCustomView();
                LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                tv.setTextColor(getResources().getColor(R.color.accept));
                RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                tab_bg.setBackgroundColor(getResources().getColor(R.color.white));

                tab_scorecard.getTabAt(tab.getPosition()).setCustomView(v);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                int tabpos=tab.getPosition();

                if(tabpos==0)
                {
                    View v=tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.accept));

                    tab_scorecard.getTabAt(tab.getPosition()).setCustomView(v);

                }
                if(tabpos==1)
                {
                    View v=tab.getCustomView();
                    LatoTextView tv = (LatoTextView) v.findViewById(R.id.tab_title_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    RelativeLayout tab_bg = (RelativeLayout) v.findViewById(R.id.tab_bg);
                    tab_bg.setBackgroundColor(getResources().getColor(R.color.accept));

                    tab_scorecard.getTabAt(tab.getPosition()).setCustomView(v);
                }
                if(tabpos==2)
                {
                    View tabthree = tab.getCustomView();
                    LatoTextView tv3 = (LatoTextView) tabthree.findViewById(R.id.tab_title_name);
                    RelativeLayout tab_bg3 = (RelativeLayout) tabthree.findViewById(R.id.tab_bg);
                    tab_bg3.setBackgroundColor(getResources().getColor(R.color.accept));
                    tv3.setTextColor(getResources().getColor(R.color.white));

                    tab_scorecard.getTabAt(tabpos).setCustomView(tabthree);
                }
                if(tabpos==3)
                {
                    View tabthree = tab.getCustomView();
                    LatoTextView tv3 = (LatoTextView) tabthree.findViewById(R.id.tab_title_name);
                    RelativeLayout tab_bg3 = (RelativeLayout) tabthree.findViewById(R.id.tab_bg);
                    tab_bg3.setBackgroundColor(getResources().getColor(R.color.accept));
                    tv3.setTextColor(getResources().getColor(R.color.white));

                    tab_scorecard.getTabAt(tabpos).setCustomView(tabthree);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    public String playerID(int tabNO)
    {
        return tabplayer_id.get(tabNO);
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
            if(mTabLayout != null) {
                mTabLayout.getTabAt(position).select();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
                //    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


}
