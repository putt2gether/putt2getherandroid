package com.putt2gether.putt.addScore;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.putt2gether.R;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.eventListingPackage.EventListing;
import com.putt2gether.utils.DayDecorator;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/1/2016.
 */
public class DatePickerClass extends AppCompatActivity implements OnDateSelectedListener, OnMonthChangedListener {


    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    boolean flag = false;

    MaterialCalendarView calenderview;
    String golg_course_id;
    ImageView back_create_calender;

    ArrayList<String> dates = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.datepick);

        back_create_calender = (ImageView) findViewById(R.id.back_create_calender);

        Intent i = getIntent();

        golg_course_id = i.getStringExtra("golf_couse_id_");

        calenderview = (MaterialCalendarView) findViewById(R.id.calendarView);

        calenderview.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));

        calenderview.setHeaderTextAppearance(R.style.HeaderCalenderFont);

        calenderview.setWeekDayTextAppearance(R.style.WeekdayFont);


        calenderview.setDateTextAppearance(R.style.dayFont);

        CalendarDay current_month = calenderview.getCurrentDate();


        int month = current_month.getMonth();
        int year = current_month.getYear();
        int day  = current_month.getDay();

        calenderview.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(year, month, day))
                .setMaximumDate(CalendarDay.from(2116, 5, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);

        ConnectionDetector con = new ConnectionDetector(this);
        if (con.isConnectingToInternet()) {
            getmonthevents(golg_course_id, user_ID, month + 1, year);
        }else {
            Toast.makeText(getApplicationContext(),"No internet connections",Toast.LENGTH_SHORT).show();
        }


        calenderview.setOnDateChangedListener(this);
        calenderview.setOnMonthChangedListener(this);
        back_create_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerClass.this.finish();
            }
        });

    }



    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
      //  Toast.makeText(getApplicationContext(), getSelectedDatesString(), Toast.LENGTH_SHORT).show();


        Intent i = new Intent(DatePickerClass.this, EventListing.class);
        i.putExtra("golfcourseid", golg_course_id);
        i.putExtra("getSelectedDatesString", getSelectedDatesString());
        startActivity(i);

        //  DatePickerClass.this.finish();

    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

        //  String date.getDate();

       widget.getMinimumDate();


        dates.clear();


        int month = date.getMonth();
        int year = date.getYear();

        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);


        ConnectionDetector con = new ConnectionDetector(this);
        if (con.isConnectingToInternet()) {
            getmonthevents(golg_course_id, user_ID, month + 1, year);
        }else {
            Toast.makeText(getApplicationContext(),"No internet connections",Toast.LENGTH_SHORT).show();
        }

    }


    private String getSelectedDatesString() {
        CalendarDay date = calenderview.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return formatter.format(date.getDate());
    }

    public void getmonthevents(String Golf_Course_Id, String User_Id, final int month, final int year) {

        final ProgressDialog pDialog = new ProgressDialog(DatePickerClass.this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JSONObject jsonObject = null;

        int golf_id = Integer.parseInt(Golf_Course_Id);
        int admin_id = Integer.parseInt(User_Id);


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", User_Id);
            jsonObject.putOpt("golf_course_id", golf_id);
            jsonObject.putOpt("month", month);
            jsonObject.putOpt("year", year);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GET_EVENT_DATES, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getdateslist(response, month - 1, year);
                Log.e("Suggestion Fragment", "GetSuggestionList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(DatePickerClass.this, "Error in " + "Get Event by date URL = " + PUTTAPI.GET_EVENT_DATES);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getdateslist(JSONObject response, int month1, int year1) {

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        String DATE = jsonArray.getString(i);
                        dates.add(DATE);
                        int date_events = Integer.parseInt(DATE);

                        CalendarDay day = CalendarDay.from(year1, month1, date_events);
                        ArrayList<CalendarDay> dates = new ArrayList<>();
                        dates.add(day);


                        calenderview.addDecorator(new DayDecorator(Color.WHITE, dates, Color.BLUE, getApplicationContext()));
                        calenderview.invalidateDecorators();

                    }

                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}