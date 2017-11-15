package com.putt2gether.fragments.selectcourse;

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
import com.putt2gether.adapter.NearGolfCourseAdapterInvite;
import com.putt2gether.bean.GolfCourseBean;
import com.putt2gether.network.GPSTracker;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/9/2016.
 */
public class RecentGolfCourse extends Fragment {


    private EditText searchEdit;
    private EditText editBTN;
    private RecyclerView mListViewService;
    private GPSTracker gpsTracker;

    List<GolfCourseBean> listSearch;
    GolfCourseBean beanSearch;
    private NearGolfCourseAdapterInvite searchGolfCourseAdapter;
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



        getrecentGolfCourse();
        mListViewService = (RecyclerView)getActivity().findViewById(R.id.golf_course_list_search);
        mListViewService.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(getActivity());
        mListViewService.setLayoutManager(ddLayoutManager);

    }


    private void getrecentGolfCourse()
    { final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;

        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        final String user_ID = pref.getString("userId",null);


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("player_id", user_ID);
            jsonObject.putOpt("limit","0");
            jsonObject.putOpt("version", "2");
        }
        catch (JSONException je)
        {
            je.printStackTrace();
        }



        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.RECENT_GOLF_COURSE_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getstatenameid(response);
                Log.e("Recent GOLFLIST", "Recent GOLFLIST" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(),"Error in "+ "GetRecent Golf List URL = " + PUTTAPI.RECENT_GOLF_COURSE_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }



    private void getstatenameid(JSONObject response)
    {

        listSearch = new ArrayList<GolfCourseBean>();

        if(response != null){
            try{

                JSONObject jsonObject = response.getJSONObject("output");

                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        beanSearch = new GolfCourseBean();
                        beanSearch.setGolfCorseName(jsonArray.getJSONObject(i).getString("golf_course_name"));
                        beanSearch.setGolfCourseAddress(jsonArray.getJSONObject(i).getString("city_name"));
                        beanSearch.setGolfCourseID(jsonArray.getJSONObject(i).getString("golf_course_id"));
                        beanSearch.setHasEvent(jsonArray.getJSONObject(i).getString("has_event"));

                        listSearch.add(beanSearch);
                    }
                }else {
                    String msg = jsonObject.getString("message");
                    Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        if (listSearch!=null) {
            searchGolfCourseAdapter = new NearGolfCourseAdapterInvite(getActivity(),listSearch);
            mListViewService.setAdapter(searchGolfCourseAdapter);
        }else {
            Toast.makeText(getActivity(),"Golf Course not found",Toast.LENGTH_LONG).show();
        }

    }
}
