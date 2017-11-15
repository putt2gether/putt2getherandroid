package com.putt2gether.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.putt2gether.adapter.InviteFragmentAdapter;
import com.putt2gether.adapter.NoInternetConnectionAdapter;
import com.putt2gether.bean.InviteUserBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.HomeActivity;
import com.putt2gether.putt.SelectGolfForInvite;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 04/08/2016.
 */
public class InviteFragmentMain extends android.support.v4.app.Fragment {
    private RecyclerView ddRecyclerView;
    private InviteFragmentAdapter ddAdapter1;
    private RecyclerView.LayoutManager ddLayoutManager;
    private RelativeLayout requestToParticipateBTN;
    private Constant constant;
    Typeface Lato_Regular;
    ArrayList<InviteUserBean> list = new ArrayList<InviteUserBean>();
    RelativeLayout parent;

    ImageView bannerImage;
    String banner_id;
    String banner_url;
    String banner_img;

    public InviteFragmentMain() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_invitation, container, false);

        ((HomeActivity) getActivity()).addtitle("PUTT2GETHER");
        constant = new Constant();
        requestToParticipateBTN = (RelativeLayout) rootView.findViewById(R.id.request_to_parti_btn);
        parent = (RelativeLayout) rootView.findViewById(R.id.parent_eventList);
        bannerImage = (ImageView) rootView.findViewById(R.id.banner_invite_list);
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

        requestToParticipateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectGolfForInvite.class);
                startActivity(intent);
            }
        });


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        ddRecyclerView = (RecyclerView) getActivity().findViewById(R.id.event_invitaion_list);

        ddRecyclerView.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(getActivity());
        ddRecyclerView.setLayoutManager(ddLayoutManager);

        if (connectionDetector.isConnectingToInternet()) {
            getEventInvitationList();
            getBannerList();
        } else {
            Toast.makeText(getActivity(), R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onResume() {

        getEventInvitationList();
        super.onResume();
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    public void getEventInvitationList() {

        final ProgressDialog pDialog = new ProgressDialog(getActivity(), R.style.MyTheme);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

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

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.EVENT_INVITATION_LIST, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getInvitationList(response);

                parent.setVisibility(View.VISIBLE);

                Log.e("Suggestion Fragment", "invitelist" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
                open_dialog();
            }
        });
        Utility.showLogError(getActivity(), "Error in " + "invitelist URL = " + PUTTAPI.EVENT_INVITATION_LIST);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getInvitationList(JSONObject response) {

        ArrayList<InviteUserBean> list = new ArrayList<InviteUserBean>();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    JSONObject jsonObjectD = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = jsonObjectD.getJSONArray("Invitation");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        InviteUserBean listBean = new InviteUserBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        listBean.setIs_accepted(jsonObject1.getString("is_accepted"));
                        listBean.setEvent_id(jsonObject1.getString("event_id"));
                        listBean.setEvent_name(jsonObject1.getString("event_name"));
                        listBean.setEvent_display_number(jsonObject1.getString("event_display_number"));
                        listBean.setStart_date(jsonObject1.getString("start_date"));
                        listBean.setEvent_start_time(jsonObject1.getString("event_start_time"));
                        listBean.setIs_started(jsonObject1.getString("is_started"));

                        //listBean.setHandicap(jsonObject1.getString("handicap"));
                        listBean.setGolf_course_name(jsonObject1.getString("golf_course_name"));
                        listBean.setGolf_course_id(jsonObject1.getString("golf_course_id"));


                        listBean.setFormat_id(jsonObject1.getString("format_id"));
                        listBean.setAdmin_id(jsonObject1.getString("admin_id"));
                        listBean.setAdmin(jsonObject1.getString("admin"));
                        listBean.setCreation_date(jsonObject1.getString("creation_date"));
                        listBean.setIs_submit_score(jsonObject1.getString("is_submit_score"));
                        listBean.setRead_status(jsonObject1.getString("read_status"));
                        listBean.setAdd_player_type(jsonObject1.getString("add_player_type"));

                        //listBean.setHandicap(jsonObject1.getString("handicap"));
                        listBean.setLocation(jsonObject1.getString("location"));
                        listBean.setFormate_name(jsonObject1.getString("formate_name"));
                        listBean.setIs_edit(jsonObject1.getString("is_edit"));
                        listBean.setBanner_image(jsonObject1.getString("banner_image"));
                        listBean.setBanner_href(jsonObject1.getString("banner_href"));

                        list.add(listBean);
                    }
                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (list.size() > 0) {
            ddAdapter1 = new InviteFragmentAdapter(getActivity(), list);
            ddRecyclerView.setAdapter(ddAdapter1);
        } else {

            ddRecyclerView.setAdapter(new NoInternetConnectionAdapter("You don't have any invitation. "));

        }

    }


    public void open_dialog() {
        final Dialog dialog1 = new Dialog(getActivity());

        dialog1.setCanceledOnTouchOutside(true);
        Window window = dialog1.getWindow();
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.dimAmount = 0.7f;
        dialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        dialog1.setContentView(R.layout.refresh_popup);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        RelativeLayout refreshBTN = (RelativeLayout) dialog1.findViewById(R.id.refresh_popup);
        refreshBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.cancel();
                getEventInvitationList();

            }
        });

        dialog1.show();
    }


    public void getBannerList() {


        JSONObject jsonObject = null;


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("type", "1");
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
                   // Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


}