package com.putt2gether.fragments.notification;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.NavDrawerListAdapter;
import com.putt2gether.adapter.NoInternetConnectionAdapter;
import com.putt2gether.bean.InviteUserBean;
import com.putt2gether.bean.NavDrawerItem;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.HomeActivity;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/8/2016.
 */
public class Notification extends Fragment {
    RecyclerView notilist;
    ArrayList<NotilistBean> noiti = new ArrayList<NotilistBean>();
    private ArrayList<NavDrawerItem> navDrawerItems;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ListView mDrawerList;
    private NavDrawerListAdapter adapterNav;
    private SharedPreferences mSharedPreferences;
    ImageView bannerImage;
    String banner_id;
    String banner_url;
    String banner_img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notification_, container, false);

        notilist = (RecyclerView) view.findViewById(R.id.notilist);

        bannerImage = (ImageView)view.findViewById(R.id.banner_notification_list);
        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (banner_url!=null && banner_url.length()>10) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(banner_url));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getActivity(), " Please install a web browser",  Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getActivity(), " URL not found.",  Toast.LENGTH_LONG).show();
                }

            }
        });

        mSharedPreferences = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        mDrawerList = (ListView) getActivity().findViewById(R.id.list_slidermenu);

        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        ((HomeActivity) getActivity()).addtitle("NOTIFICATIONS");

        ConnectionDetector con = new ConnectionDetector(getActivity());
        if (con.isConnectingToInternet()) {
            getnotificationlist();
            getBannerList();
        } else {
            Toast.makeText(getActivity(), "No internet connections.", Toast.LENGTH_SHORT).show();
        }



        return view;
    }


    public void getnotificationlist() {

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;
        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);

            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("dlskjdaskj",jsonObject.toString());

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.NOTIFICATIONAPI, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getnotificationdetail(response);
                Log.e("NOTIFICATIONAPI URL", "NOTIFICATIONAPIURL" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();

                notilist.setLayoutManager(new LinearLayoutManager(getContext()));
                notilist.setAdapter(new NoInternetConnectionAdapter("Internet connections error."));

            }
        });
        Utility.showLogError(getActivity(), "Error in " + "NOTIFICATIONAPI URL = " + PUTTAPI.NOTIFICATIONAPI);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    public void getnotificationdetail(JSONObject response) {
        if (response != null) {
            try {

                noiti = new ArrayList<NotilistBean>();

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        NotilistBean bean = new NotilistBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        bean.setNotieventid(jsonObject1.getString("event_id"));
                        bean.setNotificationID(jsonObject1.getString("alert_id"));
                        bean.setAdmin_id(jsonObject1.getString("admin_id"));
                        bean.setPushType(jsonObject1.getString("push_type"));
                        bean.setNotiname(jsonObject1.getString("message"));
                        bean.setNotitime(jsonObject1.getString("send_date"));
                        bean.setNotiread_status(jsonObject1.getString("read_status"));

                        noiti.add(bean);


                    }
                    Log.e("listnoti", noiti.size() + "");

                    notilist.setLayoutManager(new LinearLayoutManager(getContext()));

                    if (noiti.size() > 0) {
                        notilist.setAdapter(new NotiAdapter(getActivity(), noiti));
                    } else {
                        notilist.setAdapter(new NoInternetConnectionAdapter("You don't have any notifications."));
                    }

                }
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        ConnectionDetector co = new ConnectionDetector(getActivity());
        if (co.isConnectingToInternet()) {
          //  getnotificationlist();
            getProfile();
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

    public void getBannerList() {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("type", "8");
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
        Utility.showLogError(getActivity(), "Error in " + "banner invitelist URL = " + PUTTAPI.EVENT_INVITATION_LIST);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getBannerResponse(JSONObject response) {

        ArrayList<InviteUserBean> list = new ArrayList<InviteUserBean>();

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
                        Picasso.with(getActivity()).load(banner_img).into(bannerImage);
                    }else {
                        bannerImage.setVisibility(View.GONE);
                    }

                } else {
                    String errorMessage = jsonObject.getString("message");
                  //  Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

}
