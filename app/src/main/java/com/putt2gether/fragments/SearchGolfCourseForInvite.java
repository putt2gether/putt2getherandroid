package com.putt2gether.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.putt2gether.adapter.SearchGolfCourseAdapterInvite;
import com.putt2gether.bean.SearchGolfCouseBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.GPSTracker;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 04/08/2016.
 */
public class SearchGolfCourseForInvite  extends Fragment {

    private EditText searchEdit;
    private EditText editBTN;
    private RecyclerView mListViewService;
    private GPSTracker gpsTracker;

    List<SearchGolfCouseBean> listSearch;
    SearchGolfCouseBean beanSearch;
    private SearchGolfCourseAdapterInvite searchGolfCourseAdapter;
    private String searchTEXT;
    private RecyclerView.LayoutManager ddLayoutManager;
    private Constant constant;
    Typeface Lato_Regular;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.searchgolf_coures_fragment, container, false);

        constant = new Constant();
        Lato_Regular = Typeface.createFromAsset(getContext().getAssets(), constant.Lato_Regular);


        return view;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchEdit = (EditText)getActivity().findViewById(R.id.edit_search);
        searchEdit.setTypeface(Lato_Regular);

        searchTEXT = searchEdit.getText().toString();
        ConnectionDetector con = new ConnectionDetector(getActivity());
        if (con.isConnectingToInternet()) {
            getGolfCourse();
        }else {
            Toast.makeText(getActivity(),"No internet connections",Toast.LENGTH_SHORT).show();
        }
        mListViewService = (RecyclerView)getActivity().findViewById(R.id.golf_course_list_search);
        mListViewService.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(getActivity());
        mListViewService.setLayoutManager(ddLayoutManager);

    }


    public void getGolfCourse(){

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        JSONObject jsonObject = null;

        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        final String user_ID = pref.getString("userId", null);
        final String accessToken = pref.getString("authorization_key", null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id",user_ID);
            jsonObject.putOpt("cityId", 0);
            jsonObject.putOpt("search_keyword", searchTEXT);
            jsonObject.putOpt("version","2");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.SEARCH_GOLF_COURSE_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getGolfCouseResponse(response);
                Log.e("Search Golf List", "GetGolfList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(),"Error in "+ "GetSearchGolfCourseURL = " + PUTTAPI.SEARCH_GOLF_COURSE_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getGolfCouseResponse(JSONObject response){

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
        searchGolfCourseAdapter = new SearchGolfCourseAdapterInvite(getActivity(),listSearch);
        mListViewService.setAdapter(searchGolfCourseAdapter);
    }

}