package com.putt2gether.fragments.selectcourse;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import com.putt2gether.R;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/9/2016.
 */
public class BrowsegolfCourse extends Fragment {


    private StickyListHeadersListView mListViewService;

    ArrayList<String> countryname;
    ArrayList<String> countryid;
    ArrayList<String> hasEvent;


    private RecyclerView.LayoutManager ddLayoutManager;

    MyAdapter ma;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.browsegolf, container, false);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        if (connectionDetector.isConnectingToInternet()) {
            getCountryList();
        } else {
            Toast.makeText(getActivity(), "No internet connectios", Toast.LENGTH_SHORT).show();
        }
        mListViewService = (StickyListHeadersListView) getActivity().findViewById(R.id.liststicky);

        mListViewService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String id1 = countryid.get(position);


                Toast.makeText(getContext(), id1, Toast.LENGTH_SHORT).show();

                Statelist ldf = new Statelist();
                Bundle args = new Bundle();
                args.putString("countryid", id1);
                ldf.setArguments(args);

//Inflate the fragment

                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, ldf);
                fragmentTransaction.commit();

            }
        });


    }


    private void getCountryList() {
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
            jsonObject.putOpt("version", "2");
        } catch (JSONException je) {
            je.printStackTrace();
        }


        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.COUNTRY_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getcountrylist(response);
                Log.e("BROWSECOUNTRY", "GetcountryList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(), "Error in " + "GetDealList URL = " + PUTTAPI.COUNTRY_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    private void getcountrylist(JSONObject response) {

        countryname = new ArrayList<String>();
        countryid = new ArrayList<String>();
        hasEvent = new ArrayList<String>();

        if (response != null) {
            try {

                // JSONObject jsonObject = response.getJSONObject("output");

                JSONArray jsonArray = response.getJSONArray("CountryList");
                for (int i = 0; i < jsonArray.length(); i++) {

                    String id = jsonArray.getJSONObject(i).getString("country_id");
                    String name = jsonArray.getJSONObject(i).getString("country_name");
                    String has_event = jsonArray.getJSONObject(i).getString("has_event");

                    hasEvent.add(has_event);
                    countryname.add(name);
                    countryid.add(id);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ma = new MyAdapter(getContext(), countryid, countryname,hasEvent);
        mListViewService.setAdapter(ma);
    }


}
