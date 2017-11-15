package com.putt2gether.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.adapter.GolfCourseAdapter;
import com.putt2gether.bean.GolfCourseBean;
import com.putt2gether.bean.SearchGolfCouseBean;
import com.putt2gether.network.GPSTracker;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 08/07/2016.
 */
public class NearGolfCoures extends android.support.v4.app.Fragment {


    private RecyclerView mListViewService;
    private GPSTracker gpsTracker;
    List<GolfCourseBean> list1;
    List<SearchGolfCouseBean> listSearch;
    GolfCourseBean typeBean;
    private GolfCourseAdapter golfCorseAdapter;
    private RecyclerView.LayoutManager ddLayoutManager;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.nearby_golfcourse_fragmnet, container, false);

        return view;


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getGolfCourse();
        mListViewService = (RecyclerView)getActivity().findViewById(R.id.golf_course_list);
        mListViewService.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(getActivity());
        mListViewService.setLayoutManager(ddLayoutManager);

    }


    public void getGolfCourse(){

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;

        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        final String user_ID = pref.getString("userId",null);
        final String accessToken = pref.getString("authorization_key",null);

        gpsTracker = new GPSTracker(getActivity());
        String latitude = String.valueOf(gpsTracker.getLatitude());
        String Longitude = String.valueOf(gpsTracker.getLongitude());

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("latitude", latitude);
            jsonObject.putOpt("longitude", Longitude);
            jsonObject.putOpt("version","2");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GOLF_COURSE_NEAREST_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getGolfListResponse(response);
                Log.e("DealsActivity", "GetDealList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(),"Error in "+ "GetDealList URL = " + PUTTAPI.GOLF_COURSE_NEAREST_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getGolfListResponse(JSONObject response){

        list1 = new ArrayList<GolfCourseBean>();

        if(response != null){
            try{

                // JSONObject jsonObject = response.getJSONObject("output");

                JSONArray jsonArray = response.getJSONArray("GolfcourseNerabyDistance");
                for (int i = 0; i < jsonArray.length(); i++) {
                    typeBean = new GolfCourseBean();
                    typeBean.setGolfCorseName(jsonArray.getJSONObject(i).getString("golf_course_name"));
                    typeBean.setGolfCourseAddress(jsonArray.getJSONObject(i).getString("city_name"));
                    typeBean.setGolfCourseID(jsonArray.getJSONObject(i).getString("golf_course_id"));

                    list1.add(typeBean);
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        golfCorseAdapter = new GolfCourseAdapter(getActivity(),list1);
        mListViewService.setAdapter(golfCorseAdapter);
    }
}
