package com.putt2gether.fragments.mygroups;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.NoInternetConnectionAdapter;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 08/09/2016.
 */
public class MyGroups  extends android.support.v4.app.Fragment {
    private RecyclerView ddRecyclerView;
    private MyGroupAdapter ddAdapter1;
    private RecyclerView.LayoutManager ddLayoutManager;
    private RelativeLayout createNewGroupBTN;
    private ArrayList<MyGroupBean> list;
    private TextView title;


    public MyGroups() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mygroup_fragment, container, false);

        createNewGroupBTN = (RelativeLayout)rootView.findViewById(R.id.create_my_group_btn);
        createNewGroupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateGroupActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        ddRecyclerView = (RecyclerView)getActivity(). findViewById(R.id.my_group_list);
        title = (TextView)getActivity().findViewById(R.id.maintitle);

        title.setText("MY GROUPS");

        ddRecyclerView.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(getActivity());
        ddRecyclerView.setLayoutManager(ddLayoutManager);


        if (connectionDetector.isConnectingToInternet()) {
            getMyGroupList();
        } else {
            Toast.makeText(getActivity(), R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onResume() {

        ConnectionDetector con = new ConnectionDetector(getActivity());
        if (con.isConnectingToInternet()) {
            getMyGroupList();
        }else {
            Toast.makeText(getActivity(),"No internet connections",Toast.LENGTH_SHORT).show();
        }

        super.onResume();
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    public void getMyGroupList(){

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
            jsonObject.putOpt("version","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GET_GROUP_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getGroupListResponse(response);
                Log.e("Group List Fragment", " Group List" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(),"Error in "+ "GROUP List URL = " + PUTTAPI.GET_GROUP_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getGroupListResponse(JSONObject response){

        list = new ArrayList<MyGroupBean>();

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0 ; i < jsonArray.length(); i++){
                        MyGroupBean listBean = new MyGroupBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        listBean.setGroup_id(jsonObject1.getString("group_id"));
                        listBean.setGroup_name(jsonObject1.getString("group_name"));
                        listBean.setGroup_image(jsonObject1.getString("profile_img"));
                        listBean.setIs_admin(jsonObject1.getString("is_admin"));

                        list.add(listBean);
                    }
                }else{
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(getActivity(),errorMessage,Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (list.size()>0) {
            ddAdapter1 = new MyGroupAdapter(getActivity(), list);
            ddRecyclerView.setAdapter(ddAdapter1);
        }else {

            ddRecyclerView.setAdapter(new NoInternetConnectionAdapter("You don't have any Group."));

        }

    }


}