package com.putt2gether.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.HomeStatsAdapter;
import com.putt2gether.adapter.NavDrawerListAdapter;
import com.putt2gether.bean.HomeStatsBean;
import com.putt2gether.bean.NavDrawerItem;
import com.putt2gether.bean.PieDataBean;
import com.putt2gether.custome.CirclePageIndicator;
import com.putt2gether.custome.MyValueFormatter;
import com.putt2gether.custome.PageIndicator;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.CreateEventActivity;
import com.putt2gether.putt.HomeActivity;
import com.putt2gether.putt.InviteEventDetail;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 21/07/2016.
 */
public class HomeFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    public static final int[] Player1 = new int[]{Color.rgb(147, 148, 148), Color.rgb(0, 0, 0), Color.rgb(244, 170, 67), Color.rgb(10, 92, 135), Color.rgb(50, 86, 4)};
    public static final int[] Player2 = new int[]{Color.rgb(147, 148, 148), Color.rgb(0, 0, 0), Color.rgb(244, 170, 67), Color.rgb(10, 92, 135), Color.rgb(50, 86, 4)};
    public static final int[] Player3 = new int[]{Color.rgb(147, 148, 148), Color.rgb(0, 0, 0), Color.rgb(244, 170, 67), Color.rgb(10, 92, 135), Color.rgb(50, 86, 4)};
    public static final int[] Player4 = new int[]{Color.rgb(147, 148, 148), Color.rgb(0, 0, 0), Color.rgb(244, 170, 67), Color.rgb(10, 92, 135), Color.rgb(50, 86, 4)};
    public static final int[] Player5 = new int[]{Color.rgb(147, 148, 148), Color.rgb(0, 0, 0), Color.rgb(244, 170, 67), Color.rgb(10, 92, 135), Color.rgb(50, 86, 4)};


    private ArrayList<NavDrawerItem> navDrawerItems;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ListView mDrawerList;

    private NavDrawerListAdapter adapterNav;

    String eagle;
    String birdie;
    String bogey;
    String par;
    String doublebogey;
    String average_piechart;

    int chartFlag=0;

    ViewPager mPager;
    HomeStatsAdapter adapter;
    PageIndicator mIndicator;

    String upcoming_eventid;
    String upcoming_eventname;
    String upcoming_golfcoursename;
    String upcoming_startDate;


    private PieChart mChart;
    private SharedPreferences mSharedPreferences;
    private PieDataBean pieDataBean;
    private HomeStatsBean statsBeanHome;

    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private Constant constant;
    Typeface Lato_Regular;
    Typeface Lato_Bold;
    private String averageValue,pieRank;
    private Typeface tf;
    private RelativeLayout createEvent;
    private float in1, in2, in3, in4, in5;
    private RelativeLayout openIMG,openUpcoming;

    private LinearLayout openLayout;

    private Context context;

    private RelativeLayout last10BTN;
    private TextView lastText;
    CirclePageIndicator indicator;
    View rootView;
    RelativeLayout parentLay;
    TextView upcomingText;
    String isUpcoming;
    SpannableString s;


    protected String[] mMonths = new String[]{
            "BOGEY", "D.BOGEY+", "EAGLE+", "BIRDIE", "PAR"};

    protected String[] mParties = new String[]{"BOGEY", "D.BOGEY+", "EAGLE+", "BIRDIE", "PAR"};

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((HomeActivity) getActivity()).addtitle("PUTT2GETHER");

        constant = new Constant();
        Lato_Regular = Typeface.createFromAsset(getActivity().getAssets(), constant.Lato_Regular);
        Lato_Bold = Typeface.createFromAsset(getActivity().getAssets(), constant.Lato_Bold);

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        mDrawerList = (ListView) getActivity().findViewById(R.id.list_slidermenu);

        upcomingText = (TextView)rootView.findViewById(R.id.upcoming_event_text);

        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        mSharedPreferences = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        ConnectionDetector con = new ConnectionDetector(getActivity());
        if (con.isConnectingToInternet()) {

            getUpcomingEvent();
        } else {
            Toast.makeText(getActivity(), "No internet connections", Toast.LENGTH_SHORT).show();
        }

        parentLay = (RelativeLayout)rootView.findViewById(R.id.parent_home);

        last10BTN = (RelativeLayout) rootView.findViewById(R.id.last_tenBTN);
        lastText = (TextView) rootView.findViewById(R.id.last_text);
        last10BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog);

                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                dialog.setCanceledOnTouchOutside(true);
                Window window = dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);

                // Include dialog.xml file
                dialog.setContentView(R.layout.top_ten_popup);
                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                ImageView crossBTN = (ImageView) dialog.findViewById(R.id.cross_btn);
                RelativeLayout last10 = (RelativeLayout) dialog.findViewById(R.id.last_lay_10);
                RelativeLayout last5 = (RelativeLayout) dialog.findViewById(R.id.last_lay_5);
                RelativeLayout last20 = (RelativeLayout) dialog.findViewById(R.id.last_lay_20);
                RelativeLayout lastgame = (RelativeLayout) dialog.findViewById(R.id.last_lay);
                RelativeLayout overAll = (RelativeLayout) dialog.findViewById(R.id.last_lay_overall);

                last5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();

                        lastText.setText("LAST 5");
                        getPieChartValue("5");
                    }
                });

                last10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        lastText.setText("LAST 10");
                        getPieChartValue("10");
                    }
                });

                last20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        lastText.setText("LAST 20");
                        getPieChartValue("20");
                    }
                });

                lastgame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        lastText.setText("LAST GAME");
                        getPieChartValue("1");
                    }
                });

                overAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        lastText.setText("OVERALL");
                        getPieChartValue("0");
                    }
                });


                dialog.show();

                crossBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });


        if (con.isConnectingToInternet()) {
            String stats = mSharedPreferences.getString("stats",null);

            if (stats==null){
                getPieChartValue("10");
                lastText.setText("LAST 10");
            } else if (stats.equalsIgnoreCase("10")) {
                getPieChartValue("10");
                lastText.setText("LAST 10");
            }else if (stats.equalsIgnoreCase("5")) {
                getPieChartValue("5");
                lastText.setText("LAST 5");
            }else if (stats.equalsIgnoreCase("20")) {
                getPieChartValue("20");
                lastText.setText("LAST 20");
            }else if (stats.equalsIgnoreCase("0")) {
                lastText.setText("OVERALL");
                getPieChartValue("0");
            }else if (stats.equalsIgnoreCase("1")) {
                lastText.setText("LAST GAME");
                getPieChartValue("1");
            }


        } else {
            Toast.makeText(getActivity(), "No internet connections", Toast.LENGTH_SHORT).show();
        }


        openIMG = (RelativeLayout) rootView.findViewById(R.id.upcoming_logo_open);
        openUpcoming = (RelativeLayout) rootView.findViewById(R.id.upcomin_event_home);

        mChart = (PieChart) rootView.findViewById(R.id.pieChart);
        mPager = (ViewPager) rootView.findViewById(R.id.pager);

        createEvent = (RelativeLayout) rootView.findViewById(R.id.crete_event_home);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateEventActivity.class);
                startActivity(intent);
            }
        });
        indicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator);

        openUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*  openLayout.setVisibility(View.VISIBLE);
                openIMG.setVisibility(View.GONE);
                closeIMG.setVisibility(View.VISIBLE);
                scrollView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                },500);  */
                open_dialog();
            }
        });


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void setData(int count, float range) {

        float mult = range;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

      /*  SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        final String eagle = pref.getString("eagel",null);
        final String birdie = pref.getString("birdie",null);
        final String bogey = pref.getString("bogey",null);
        final String par = pref.getString("par",null);
        final String doublebogey = pref.getString("doublebogey",null);*/


        if (eagle.equalsIgnoreCase("-")) {
            in1 = 20;//Integer.parseInt(bogey);
            in2 = 20;//Integer.parseInt(doublebogey);
            in3 = 20;//Integer.parseInt(eagle);
            in4 = 20;//Integer.parseInt(birdie);
            in5 = 20;//Integer.parseInt(par);


        } else {

            in1 = Float.parseFloat(bogey);
            in2 = Float.parseFloat(doublebogey);
            in3 = Float.parseFloat(eagle);
            in4 = Float.parseFloat(birdie);
            in5 = Float.parseFloat(par);

        }


        yVals1.add(new Entry(in1, 0));
        yVals1.add(new Entry(in2, 1));
        yVals1.add(new Entry(in3, 2));
        yVals1.add(new Entry(in4, 3));
        yVals1.add(new Entry(in5, 4));

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
      /*  for (int i = 0; i < count + 1; i++) {
            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
        }  */

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count + 1; i++)
            xVals.add(mParties[i % mParties.length]);


        PieDataSet dataSet = new PieDataSet(yVals1, "Players Score");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(3f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();


        for (int c : Player1)
            colors.add(c);

        for (int c : Player2)
            colors.add(c);

        for (int c : Player3)
            colors.add(c);

        for (int c : Player4)
            colors.add(c);

        for (int c : Player5)
            colors.add(c);

        //colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        // data.setValueFormatter(new PercentFormatter());
        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(13f);
        data.setValueTypeface(Lato_Bold);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText1() {

        if (pieRank==null) {
            pieRank = "-";
        }else if (pieRank.length()>2){
            s = new SpannableString(pieRank);
            s.setSpan(new RelativeSizeSpan(4.5f), 0, s.length(), 0);
            s.setSpan(new StyleSpan(Typeface.BOLD), 0, s.length(), 0);
            s.setSpan(Lato_Regular, 0, s.length(), 0);
            s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.avg_color)), 0, s.length(), 0);
            // s.setSpan(new RelativeSizeSpan(0.8f), 3, s.length(), 0);
            // s.setSpan(new StyleSpan(Typeface.NORMAL), 3, s.length(), 0);
            // s.setSpan(new ForegroundColorSpan(Color.GRAY), 2, s.length(), 0);
            // s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 2, s.length(), 0);

        }else {
            s = new SpannableString(pieRank);
            s.setSpan(new RelativeSizeSpan(5.8f), 0, s.length(), 0);
            s.setSpan(new StyleSpan(Typeface.BOLD), 0, s.length(), 0);
            s.setSpan(Lato_Regular, 0, s.length(), 0);
            s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.avg_color)), 0, s.length(), 0);
            // s.setSpan(new RelativeSizeSpan(0.8f), 3, s.length(), 0);
            // s.setSpan(new StyleSpan(Typeface.NORMAL), 3, s.length(), 0);
            // s.setSpan(new ForegroundColorSpan(Color.GRAY), 2, s.length(), 0);
            // s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 2, s.length(), 0);

        }

        return s;
    }

    private SpannableString generateCenterSpannableText() {

        if (averageValue.equalsIgnoreCase("-")) {
            averageValue = "--";
        }

        SpannableString s = new SpannableString(averageValue+" " + "\nAVG. SCORE");
        s.setSpan(new RelativeSizeSpan(5.8f), 0, averageValue.length(), 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0, averageValue.length(), 0);
        //s.setSpan(Lato_Regular, 0, 2, 0);
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.avg_color)), 0, averageValue.length(), 0);
        s.setSpan(new RelativeSizeSpan(0.8f), 4, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 4, s.length(), 0);
        // s.setSpan(new ForegroundColorSpan(Color.GRAY), 2, s.length(), 0);
        // s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 2, s.length(), 0);
        return s;
    }


    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED", "Value: " + e.getVal() + ", xIndex: " + e.getXIndex() + ", DataSet index: " + dataSetIndex);

        if (chartFlag==0) {
            chartFlag = 1;
            mChart.setCenterText(generateCenterSpannableText());

        }else {
            chartFlag = 0;
            mChart.setCenterText(generateCenterSpannableText1());

        }

    }


    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }


    public void open_dialog() {
        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
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

        if (upcoming_eventname==null) {

            dialog.setContentView(R.layout.progress_loader);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }else {

            dialog.setContentView(R.layout.up_coming_popup);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            LinearLayout parent = (LinearLayout)dialog.findViewById(R.id.parent_upcoming);

            ImageView closeBTN = (ImageView) dialog.findViewById(R.id.close_dialog_pop);
            TextView eventName = (TextView) dialog.findViewById(R.id.upcoming_event_name);
            TextView date = (TextView) dialog.findViewById(R.id.upcoming_date);
            TextView golfCourse = (TextView) dialog.findViewById(R.id.upcoming_golfcourse);
            TextView upcoming_Text = (TextView)dialog.findViewById(R.id.upcomingText);

            if (upcoming_eventname != null) {
                eventName.setText(upcoming_eventname);
            }
            if (upcoming_golfcoursename != null) {
                golfCourse.setText(upcoming_golfcoursename);
            }
            if (upcoming_startDate != null) {
                date.setText(upcoming_startDate);
            }


            if (isUpcoming!=null && isUpcoming.equalsIgnoreCase("1")){

                upcoming_Text.setText("RESUME ROUND");
                upcomingText.setText("RESUME ROUND");

                upcoming_Text.setTextColor(Color.parseColor("#239D36"));
                upcomingText.setTextColor(Color.parseColor("#239D36"));

            }else {
                upcoming_Text.setText("UPCOMING EVENT");
                upcomingText.setText("UPCOMING EVENT");

                upcoming_Text.setTextColor(Color.parseColor("#000000"));
                upcomingText.setTextColor(Color.parseColor("#000000"));
            }


            closeBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });

            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =  new Intent(getActivity(), InviteEventDetail.class);
                    intent.putExtra("eventID",upcoming_eventid);
                    startActivity(intent);
                   // getActivity().finish();

                }
            });
        }


        dialog.show();
    }


    public void getUpcomingEvent() {

      //  final ProgressDialog pDialog = new ProgressDialog(getActivity(),android.R.style.Theme_Light_NoTitleBar_Fullscreen);
      //  pDialog.setMessage("Loading...");
      //  pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);
        //  String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("admin_id", user_ID);
            //  jsonObject.putOpt("current_date",date);
            jsonObject.putOpt("version", "2");


            Log.v("upcoming request", jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.UPCOMING_EVENT_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getUpcomingResponse(response);
                Log.e("UPCOMING URL", "UPCOMING URL" + response.toString());
              //  pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
               // pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(), "Error in " + "UPCOMING URL = " + PUTTAPI.UPCOMING_EVENT_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getUpcomingResponse(JSONObject response) {


        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    JSONObject joDATA = jsonObject.getJSONObject("data");

                    upcoming_eventid = joDATA.getString("event_id");
                    upcoming_eventname = joDATA.getString("event_name");
                    upcoming_golfcoursename = joDATA.getString("golf_course_name");
                    upcoming_startDate = joDATA.getString("start_date");

                    isUpcoming = joDATA.getString("is_resume");

                    if (isUpcoming!=null && isUpcoming.equalsIgnoreCase("1")){

                        upcomingText.setText("RESUME ROUND");
                        upcomingText.setTextColor(Color.parseColor("#239D36"));
                    }else {
                        upcomingText.setText("UPCOMING EVENT");
                        upcomingText.setTextColor(Color.parseColor("#000000"));
                    }

                } else {
                    String msg = jsonObject.getString("message");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    public void getPieChartValue(final String last) {

        final ProgressDialog pDialog = new ProgressDialog(getActivity(),R.style.MyTheme);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);


        JSONObject jsonObject = null;
        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        final String user_ID = pref.getString("userId", null);
        final String accessToken = pref.getString("authorization_key", null);
        jsonObject = new JSONObject();
        try {
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("no_of_event", last);
            jsonObject.putOpt("version", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // jsonObject.putOpt("access_token", accessToken);

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.PIECHART_STATS_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                Log.e("pie chart", "OnResponse =" + response.toString());
                getPieResponse(response,last);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getActivity(), "error_network_timeout",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                    Toast.makeText(getActivity(), "AuthFailureError", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    //TODO
                    Toast.makeText(getActivity(), "ServerError", Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(getActivity(), "NetworkError", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(getActivity(), "ParseError", Toast.LENGTH_LONG).show();
                }
                pDialog.dismiss();

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Sign Up", "Url= " + PUTTAPI.PIECHART_STATS_URL + " PostObject = " + jsonObject.toString());
        //requestQueue.add(jsonObjectRequest);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    private void getPieResponse(JSONObject response,String last) {
        //  mSharedPreferences = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        pieDataBean = new PieDataBean();
        statsBeanHome = new HomeStatsBean();

        if (response != null) {
            parentLay.setVisibility(View.VISIBLE);
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")) {
                    JSONObject data = jsonObject.getJSONObject("data");

                    JSONObject jsonObject1 = data.getJSONObject("pichart");
                    pieDataBean.setEagle(jsonObject1.getString("no_of_eagle"));
                    pieDataBean.setBirdie(jsonObject1.getString("no_of_birdies"));
                    pieDataBean.setPar(jsonObject1.getString("no_of_pars"));
                    pieDataBean.setBogey(jsonObject1.getString("no_of_bogeys"));
                    pieDataBean.setDoublebogey(jsonObject1.getString("no_of_double_bogeys"));
                    pieDataBean.setAverage(jsonObject1.getString("gross_score"));

                    pieDataBean.setCurent_position(jsonObject1.getString("curent_position"));


                    JSONObject jsonObject_score = data.getJSONObject("score_stats");
                    statsBeanHome.setAvg_par3s(jsonObject_score.getString("avg_par3s"));
                    statsBeanHome.setAvg_par4s(jsonObject_score.getString("avg_par4s"));
                    statsBeanHome.setAvg_par5s(jsonObject_score.getString("avg_par5s"));


                    JSONObject jsonObject_gir = data.getJSONObject("gir_percentage");
                    statsBeanHome.setHit(jsonObject_gir.getString("hit"));
                    statsBeanHome.setMissed(jsonObject_gir.getString("missed"));


                    JSONObject jsonObject_fair = data.getJSONObject("fairway_percentage");
                    statsBeanHome.setFairway_hit(jsonObject_fair.getString("hit"));
                    statsBeanHome.setFairway_left(jsonObject_fair.getString("left"));
                    statsBeanHome.setFairway_right(jsonObject_fair.getString("right"));


                    JSONObject jsonObject_putting = data.getJSONObject("putting_stats");
                    statsBeanHome.setPer_round_avg(jsonObject_putting.getString("per_round_avg"));
                    statsBeanHome.setPer_gir_avg(jsonObject_putting.getString("per_gir_avg"));
                    statsBeanHome.setPer_hole_avg(jsonObject_putting.getString("per_hole_avg"));


                    JSONObject jsonObject_rec = data.getJSONObject("recovery_stats");
                    statsBeanHome.setScrmbl_avg(jsonObject_rec.getString("scrmbl_avg"));
                    statsBeanHome.setSand_avg(jsonObject_rec.getString("sand_avg"));

                    if (mSharedPreferences == null)
                        return;
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("stats", last);
                    editor.commit();


                } else {
                    String errorMessage = jsonObject.getString("message");

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            eagle = pieDataBean.getEagle();
            birdie = pieDataBean.getBirdie();
            bogey = pieDataBean.getBogey();
            par = pieDataBean.getPar();
            doublebogey = pieDataBean.getDoublebogey();
            averageValue = pieDataBean.getAverage();

            pieRank = pieDataBean.getCurent_position();

            adapter = new HomeStatsAdapter(getActivity().getSupportFragmentManager(), statsBeanHome);
            mPager.setAdapter(adapter);
            mPager.setCurrentItem(0);

            mIndicator = indicator;
            indicator.setViewPager(mPager);

            final float density = getResources().getDisplayMetrics().density;
            indicator.setBackgroundColor(80000000);
            indicator.setRadius(4.0F * density);
            //indicator.setPageColor(0xFF888888);
            // indicator.setFillColor(0xFF888888);
            indicator.setFillColor(getResources().getColor(R.color.action));
            indicator.setStrokeColor(getResources().getColor(R.color.action));
            indicator.setStrokeWidth(1.0F * density);

            mChart.setDrawSliceText(false);
            mChart.invalidate();
            mChart.setUsePercentValues(true);
            mChart.setDescription("");
            mChart.setExtraOffsets(5, 10, 5, 5);

            mChart.setDragDecelerationFrictionCoef(0.95f);
            mChart.getLegend().setEnabled(false);

            if (chartFlag==0) {

                mChart.setCenterText(generateCenterSpannableText());
            }else {
                mChart.setCenterText(generateCenterSpannableText1());
            }

            mChart.setDrawHoleEnabled(true);
            mChart.setHoleColor(Color.WHITE);

            mChart.setTransparentCircleColor(Color.WHITE);
            mChart.setTransparentCircleAlpha(110);

            mChart.setHoleRadius(70f);
            mChart.setTransparentCircleRadius(70f);

            mChart.setDrawCenterText(true);

            mChart.setRotationAngle(0);
            // enable rotation of the chart by touch
            mChart.setRotationEnabled(true);
            mChart.setHighlightPerTapEnabled(true);

            mChart.setOnChartValueSelectedListener(this);

            setData(4, 100);
            mChart.animateY(1200, Easing.EasingOption.EaseInOutQuad);

            if (eagle.equalsIgnoreCase("-")|| birdie.equalsIgnoreCase("-")||par.equalsIgnoreCase("-")||bogey.equalsIgnoreCase("-")||doublebogey.equalsIgnoreCase("-")) {
                mChart.getData().setDrawValues(false);
            }else {
                mChart.getData().setDrawValues(true);
            }

        }
    }

    @Override
    public void onResume() {
        ConnectionDetector co = new ConnectionDetector(getActivity());
        if (co.isConnectingToInternet()) {
            getProfile();
            getUpcomingEvent();
        }else {

        }
        super.onResume();
    }

    private void getProfile() {


        JSONObject jsonObject = null;
        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        String user_ID = pref.getString("userId",null);
        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);

            jsonObject.putOpt("version", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.USER_PROFILE_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Main frag Profile", "OnResponse =" + response.toString());
                getProfileResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Error in Profile",Toast.LENGTH_LONG).show();

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Main fragment", "Url= " + PUTTAPI.USER_PROFILE_URL + " PostObject = " + jsonObject.toString());
        //requestQueue.add(jsonObjectRequest);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void getProfileResponse(JSONObject response) {

        if (response != null){
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")){
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    String notificationType = jsonObject1.getString("notifications_count");
                    if (notificationType.equalsIgnoreCase("0")){
                        navDrawerItems = new ArrayList<NavDrawerItem>();
                        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
                        // Find People
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
                        // Photos
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
                        // Communities, Will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
                        // Pages
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
                        // What's hot, We  will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(9, -1)));

                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
                        // Pages
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
                        // What's hot, We  will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));

                        adapterNav = new NavDrawerListAdapter(getActivity(), navDrawerItems);
                        mDrawerList.setAdapter(adapterNav);

                    }else {
                        navDrawerItems = new ArrayList<NavDrawerItem>();
                        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
                        // adding nav drawer items to array
                        // Home
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
                        // Find People
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
                        // Photos
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
                        // Communities, Will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
                        // Pages
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
                        // What's hot, We  will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));

                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
                        // Pages
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
                        // What's hot, We  will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));

                        adapterNav = new NavDrawerListAdapter(getActivity(), navDrawerItems);
                        mDrawerList.setAdapter(adapterNav);

                    }

                }else{

                    String str = jsonObject.getString("message");
                    Toast.makeText(getActivity(),str,Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


}
