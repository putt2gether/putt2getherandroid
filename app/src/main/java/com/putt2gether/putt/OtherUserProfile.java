package com.putt2gether.putt;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.HistoryListAdapter;
import com.putt2gether.adapter.ListGroupAdapter;
import com.putt2gether.adapter.NoInternetConnectionAdapter;
import com.putt2gether.bean.GroupPopBean;
import com.putt2gether.bean.ScoreHistoryBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Utility;
import com.putt2gether.view.DividerItemDecorationRecycle;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 11/01/2017.
 */
public class OtherUserProfile extends AppCompatActivity {

    private ImageView backBTN;
    private TextView title;
    private ImageView profileImage;
    private TextView name, member, averageScore, golfCourseName, country;
    private RecyclerView listEvent;
    String userID;

    ListGroupAdapter listGroupAdapter;
    ListView listView;

    ArrayList<String> groupIdSelectedList = new ArrayList<String>();

    ArrayList<ScoreHistoryBean> list = new ArrayList<ScoreHistoryBean>();

    ArrayList<GroupPopBean> listGroup = new ArrayList<GroupPopBean>();
    ScoreHistoryBean sscoreHistoryBean;
    RelativeLayout addGroup;
    String isAdded = "1";
    RelativeLayout parent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        parent = (RelativeLayout) findViewById(R.id.parent_pro);
        backBTN = (ImageView) findViewById(R.id.back_profile);
        title = (TextView) findViewById(R.id.event_name_new_scoretable);
        profileImage = (ImageView) findViewById(R.id.user_profile_pic);
        name = (TextView) findViewById(R.id.profile_user_name);
        member = (TextView) findViewById(R.id.profile_user_member);
        golfCourseName = (TextView) findViewById(R.id.profile_user_golfname);
        country = (TextView) findViewById(R.id.profile_user_country);

        averageScore = (TextView) findViewById(R.id.profile_user_avgScore);
        listEvent = (RecyclerView) findViewById(R.id.list_profile_events);

        listEvent.setLayoutManager(new LinearLayoutManager(this));
        listEvent.addItemDecoration(new DividerItemDecorationRecycle(this));

        ConnectionDetector connectionDetector = new ConnectionDetector(this);
        if (connectionDetector.isConnectingToInternet()) {
            userID = getIntent().getStringExtra("profileID");
            getOtherUserProfile(userID);
            getEventHistoryList(userID);
        } else {
            Toast.makeText(this, R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }

        addGroup = (RelativeLayout) findViewById(R.id.profile_add_groupLay);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectionDetector connectionDetector1 = new ConnectionDetector(OtherUserProfile.this);
                if (connectionDetector1.isConnectingToInternet()) {
                    getMyGroupList();

                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connections", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void getEventHistoryList(String user_ID) {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.EVENT_HISTORY_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getHistoryList(response);
                Log.e("My Score Fragment", "History list" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "History list URL = " + PUTTAPI.EVENT_HISTORY_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getHistoryList(JSONObject response) {

        list = new ArrayList<ScoreHistoryBean>();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        ScoreHistoryBean listBean = new ScoreHistoryBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        listBean.setEventID(jsonObject1.getString("event_id"));
                        listBean.setEventName(jsonObject1.getString("event_name"));


                        listBean.setFormatName(jsonObject1.getString("format_name"));

                        listBean.setTotal(jsonObject1.getString("total"));

                        //listBean.setHandicap(jsonObject1.getString("handicap"));
                        listBean.setCurrentPosion(jsonObject1.getString("current_position"));
                        listBean.setGolfCourseName(jsonObject1.getString("golf_course_name"));


                        listBean.setFormatID(jsonObject1.getString("format_id"));
                        listBean.setEagle(jsonObject1.getString("eagle"));
                        listBean.setGrossScore(jsonObject1.getString("gross_score"));
                        listBean.setNoOfBirdies(jsonObject1.getString("no_of_birdies"));
                        listBean.setNoOfPars(jsonObject1.getString("no_of_pars"));
                        listBean.setNoOfPlayer(jsonObject1.getString("no_of_player"));
                        listBean.setDate(jsonObject1.getString("event_start_date"));
                        listBean.setNo_of_player_accepted(jsonObject1.getString("no_of_player_accepted"));

                        list.add(listBean);
                    }
                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (list.size() > 0) {

            listEvent.setAdapter(new HistoryListAdapter(this, list));
        }else {
            NoInternetConnectionAdapter adap = new NoInternetConnectionAdapter("No Recent Events");
            listEvent.setAdapter(adap);
        }

    }

    public void addGroupPopup() {

        groupIdSelectedList = new ArrayList<String>();

        //final Dialog dialog = new Dialog(OtherUserProfile.this,android.R.style.Theme_Translucent_NoTitleBar);
        final Dialog dialog = new Dialog(OtherUserProfile.this);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog.setContentView(R.layout.add_group_popup);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout createBTN = (RelativeLayout) dialog.findViewById(R.id.done_addpopup);
        listView = (ListView) dialog.findViewById(R.id.list_addgroup);
        listGroupAdapter = new ListGroupAdapter(this, listGroup);
        listView.setAdapter(listGroupAdapter);


        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showResult();
                dialog.cancel();

                addToGroup(userID);

            }
        });

        dialog.show();
    }

    public void showResult() {
        String result = "Selected Product are :";
        String totalAmount = null;
        for (GroupPopBean p : listGroupAdapter.getBox()) {
            if (p.box) {
                result += "\n" + p.group_name;
                totalAmount += p.group_id;
                groupIdSelectedList.add(p.group_id);
            }
        }
        //  Toast.makeText(this, result+"\n"+"Total Amount:="+totalAmount, Toast.LENGTH_LONG).show();
    }

    public void getMyGroupList() {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("flag", "1");
            jsonObject.putOpt("version", "2");


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
        Utility.showLogError(this, "Error in " + "GROUP List URL = " + PUTTAPI.GET_GROUP_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getGroupListResponse(JSONObject response) {

        listGroup = new ArrayList<GroupPopBean>();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        isAdded = "1";

                        JSONArray json1 = jsonArray.getJSONObject(i).getJSONArray("group_member_id");
                        for (int j = 0; j < json1.length(); j++) {
                            String member = json1.getJSONObject(j).getString("user_id");

                            if (member.equalsIgnoreCase(userID)) {
                                isAdded = "2";
                                Log.v("lkll", "" + member);
                                break;
                            }

                        }
                        GroupPopBean typeBean = new GroupPopBean(jsonArray.getJSONObject(i).getString("group_name"), jsonArray.getJSONObject(i).getString("profile_img"), false, jsonArray.getJSONObject(i).getString("group_id"), isAdded);

                        listGroup.add(typeBean);
                    }

                    addGroupPopup();
                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    public void getOtherUserProfile(String user_ID) {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);


        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.OTHER_USER_PROFILE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getProfileResponse(response);
                Log.e("Profile ", "Profile" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "Profile URL = " + PUTTAPI.OTHER_USER_PROFILE);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getProfileResponse(JSONObject response) {

        list = new ArrayList<ScoreHistoryBean>();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {


                    JSONObject jbData = jsonObject.getJSONObject("data");

                    String memberNO = jbData.getString("total_group_member");
                    String userID = jbData.getString("user_id");
                    String user_name = jbData.getString("user_name");
                    String display_name = jbData.getString("display_name");
                    String contact_no = jbData.getString("contact_no");
                    String country_code = jbData.getString("country_code");
                    String handicap_value = jbData.getString("handicap_value");

                    String average_value = jbData.getString("average_score");

                    String address = jbData.getString("address");
                    String countryName = jbData.getString("country");
                    String designation = jbData.getString("designation");
                    String photo_url = jbData.getString("photo_url");
                    String golf_course_name = jbData.getString("golf_course_name");
                    String thumb_url = jbData.getString("thumb_url");
                    title.setText(display_name.toUpperCase() + " EVENT");

                    if (thumb_url.length() != 0 && thumb_url != null) {

                        Picasso.with(this).load(thumb_url).into(profileImage);
                    }

                    name.setText(display_name.toUpperCase());
                    if (memberNO != null) {
                        member.setText("(Member of " + memberNO + " groups)");
                    }
                    if (golf_course_name.length() > 2) {
                        golfCourseName.setText(golf_course_name);
                    } else {
                        golfCourseName.setText("n/a");
                    }
                    country.setText(countryName);
                    averageScore.setText(average_value);


                    parent.setVisibility(View.VISIBLE);

                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    public void addToGroup(String user_ID) {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String userIDD = pref.getString("userId", null);

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", userIDD);
            jsonObject.putOpt("member_id", user_ID);
            jsonObject.putOpt("version", "2");

            JSONArray jsonArray = new JSONArray();
            for (int j = 0; j < groupIdSelectedList.size(); j++) {
                JSONObject object = new JSONObject();
                object.putOpt("group_id", groupIdSelectedList.get(j));
                jsonArray.put(object);
            }

            jsonObject.putOpt("group_list", jsonArray);

            Log.v("postAdd2group", jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ADD_TO_GROUP, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getAddGroupResponse(response);
                Log.e("Add 2 Group ", "Add2group" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "Add2Group URL = " + PUTTAPI.ADD_TO_GROUP);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getAddGroupResponse(JSONObject response) {

        list = new ArrayList<ScoreHistoryBean>();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
