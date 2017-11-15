package com.putt2gether.fragments.selectcourse;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.adapter.GolfCourseAdapterCity;
import com.putt2gether.bean.SearchGolfCouseBean;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/9/2016.
 */
public class GolfCourseList extends Fragment {

    private RecyclerView mListViewService;

    private GolfCourseAdapterCity searchGolfCourseAdapter;

    ArrayList<String> countryname;
    ArrayList<String> countryid;
    List<SearchGolfCouseBean> listSearch;
    SearchGolfCouseBean beanSearch;

    private RecyclerView.LayoutManager ddLayoutManager;

    MyAdapter ma;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchgolf_coures_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String value = getArguments().getString("cityid");
        getcitylist(value);
        mListViewService = (RecyclerView)getActivity().findViewById(R.id.golf_course_list_search);
        mListViewService.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(getActivity());
        mListViewService.setLayoutManager(ddLayoutManager);


    }

    private void getcitylist(String country_id)
    { final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;

        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        final String user_ID = pref.getString("userId",null);
        final String accessToken = pref.getString("authorization_key",null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id",user_ID);
            jsonObject.putOpt("cityId", country_id);
            jsonObject.putOpt("search_keyword","");
            jsonObject.putOpt("version", "2");
        }
        catch (JSONException je)
        {
            je.printStackTrace();
        }



        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.SEARCH_GOLF_COURSE_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getstatenameid(response);
                Log.e("BROWSEGOLFLIST", "BROWSEGOLFLIST" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(),"Error in "+ "GetDealList URL = " + PUTTAPI.SEARCH_GOLF_COURSE_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void getstatenameid(JSONObject response)
    {

        listSearch = new ArrayList<SearchGolfCouseBean>();

        if(response != null){
            try{

                JSONArray jsonArray = response.getJSONArray("CityGolfCourseList");
                for (int i = 0; i < jsonArray.length(); i++) {
                    beanSearch = new SearchGolfCouseBean();
                    beanSearch.setGolf_course_name(jsonArray.getJSONObject(i).getString("golf_course_name"));
                    beanSearch.setGolf_course_id(jsonArray.getJSONObject(i).getString("golf_course_id"));
                    beanSearch.setCity_id(jsonArray.getJSONObject(i).getString("city_id"));
                    beanSearch.setCity_name(jsonArray.getJSONObject(i).getString("city_name"));
                    beanSearch.setEvent_count(jsonArray.getJSONObject(i).getString("event_count"));
                    beanSearch.setHas_event(jsonArray.getJSONObject(i).getString("has_event"));
                    beanSearch.setLatitude(jsonArray.getJSONObject(i).getString("latitude"));
                    beanSearch.setLongitude(jsonArray.getJSONObject(i).getString("longitude"));

                    listSearch.add(beanSearch);
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        if (listSearch!=null) {
            searchGolfCourseAdapter = new GolfCourseAdapterCity(getActivity(), listSearch);
            mListViewService.setAdapter(searchGolfCourseAdapter);
        }else {
            Toast.makeText(getActivity(),"Golf Course not found",Toast.LENGTH_LONG).show();
        }

    }
}