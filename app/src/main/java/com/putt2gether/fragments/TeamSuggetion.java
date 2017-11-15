package com.putt2gether.fragments;

/**
 * Created by Ajay on 11/07/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.NoInternetConnectionAdapter;
import com.putt2gether.adapter.SuggestionsAdapter;
import com.putt2gether.bean.SuggestionBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.EventPreviewActivity;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 23/06/2016.
 */
public class TeamSuggetion extends android.support.v4.app.Fragment {
    private RecyclerView ddRecyclerView;
    private SuggestionsAdapter ddAdapter;
    private RecyclerView.LayoutManager ddLayoutManager;
    private RelativeLayout parentID;
    private RelativeLayout nextBTN;
    private EditText editSearch;
    private Constant constant;
    Typeface Lato_Regular;
    SwipeRefreshLayout pullDownRefreshg;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragments_suggestions, container, false);

        constant = new Constant();
        Lato_Regular = Typeface.createFromAsset(getContext().getAssets(), constant.Lato_Regular);
        parentID = (RelativeLayout)rootView.findViewById(R.id.parent_id_sugg);
        setupUI(parentID);


        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        ddRecyclerView = (RecyclerView)rootView. findViewById(R.id.suggestions_list);
        pullDownRefreshg = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeRefreshLayout);
        ddRecyclerView.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(getActivity());
        ddRecyclerView.setLayoutManager(ddLayoutManager);


        if (connectionDetector.isConnectingToInternet()) {
            getSuggestionListList();
        } else {

            NoInternetConnectionAdapter ddAdapter11 = new NoInternetConnectionAdapter("Please connect with internet and Pull down to refresh");
            ddRecyclerView.setAdapter(ddAdapter11);
        }

        pullDownRefreshg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getSuggestionListList();
                pullDownRefreshg.setRefreshing(false);
            }
        });

        nextBTN = (RelativeLayout)rootView.findViewById(R.id.next_btn_suggestions);
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventPreviewActivity.class);
                startActivity(intent);
            }
        });
        editSearch = (EditText)rootView.findViewById(R.id.edit_search_suggetions);
        editSearch.setTypeface(Lato_Regular);
        addListener(rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }



    public void addListener(View rootView){

        //in your Activity or Fragment where of Adapter is instantiated :

        editSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text ["+s+"]");

                ddAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    public void getSuggestionListList(){

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
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
            jsonObject.putOpt("user_id",user_ID);
            jsonObject.putOpt("searchkey","");
            jsonObject.putOpt("version","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.SUGGESTION_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getMyDealList(response);
                Log.e("Suggestion Fragment", "GetSuggestionList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();

                NoInternetConnectionAdapter ddAdapter11 = new NoInternetConnectionAdapter("Please connect with internet and Pull down to refresh");
                ddRecyclerView.setAdapter(ddAdapter11);
            }
        });
        Utility.showLogError(getActivity(),"GetSuggestionList URL = " + PUTTAPI.SUGGESTION_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getMyDealList(JSONObject response){

        ArrayList<SuggestionBean> list = new ArrayList<SuggestionBean>();

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                JSONArray jsonArray = jsonObject.getJSONArray("Suggestion List");

                for (int i = 0 ; i < jsonArray.length(); i++){
                    SuggestionBean listBean = new SuggestionBean();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    listBean.setUser_id(jsonObject1.getString("user_id"));
                    listBean.setFull_name(jsonObject1.getString("full_name"));
                    listBean.setRegistered_mobile_number(jsonObject1.getString("registered_mobile_number"));
                    listBean.setLast_modified_date(jsonObject1.getString("last_modified_date"));
                    listBean.setCountry(jsonObject1.getString("country"));
                    listBean.setSelf_handicap(jsonObject1.getString("self_handicap"));
                    listBean.setPlayed(jsonObject1.getString("played"));

                    //listBean.setHandicap(jsonObject1.getString("handicap"));
                    listBean.setProfile_image(jsonObject1.getString("profile_image"));
                    listBean.setThumb_image(jsonObject1.getString("thumb_image"));

                    list.add(listBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (list.size()>0) {
            ddAdapter = new SuggestionsAdapter(getActivity(), list);
            ddRecyclerView.setAdapter(ddAdapter);
        }else {
            NoInternetConnectionAdapter ddAdapter11 = new NoInternetConnectionAdapter("Empty suggestion list.");
            ddRecyclerView.setAdapter(ddAdapter11);
        }

    }


}