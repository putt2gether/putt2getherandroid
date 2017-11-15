package com.putt2gether.fragments.mygroups;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 29/11/2016.
 */
public class AddMembersActivity extends AppCompatActivity implements AddMemberAdapter.AddPlayerAdapterCallback {

    private RecyclerView ddRecyclerView;
    private RecyclerView ddRecyclerView1;
    private AddMemberAdapter ddAdapter1;
    private RecyclerView.LayoutManager ddLayoutManager;
    private RelativeLayout parentID;
    private RelativeLayout saveBTN;
    private EditText editSearch;
    private Constant constant;
    Typeface Lato_Regular;
    int inCount;
    int po;
    ArrayList<AddmemberBean> list;
    ArrayList<String> listIDs = new ArrayList<String>();
    ImageView backBTN;
    RelativeLayout searchLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_member_activity);

        searchLayout = (RelativeLayout)findViewById(R.id.searc_addmember_lay);

        constant = new Constant();
        ConnectionDetector connectionDetector = new ConnectionDetector(this);
        ddRecyclerView = (RecyclerView)findViewById(R.id.members_list);

        backBTN = (ImageView)findViewById(R.id.back_tab_addmember);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ddRecyclerView.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(this);
        ddRecyclerView.setLayoutManager(ddLayoutManager);

        if (connectionDetector.isConnectingToInternet()) {
            getMemberList();
        } else {
            Toast.makeText(this, R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }

        Lato_Regular = Typeface.createFromAsset(this.getAssets(), constant.Lato_Regular);
        parentID = (RelativeLayout)findViewById(R.id.parent_id_addmember);
        setupUI(parentID);
        saveBTN = (RelativeLayout)findViewById(R.id.addMember_btn);
        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getApplicationContext(), EventPreviewActivity.class);
                startActivity(intent);*/
                Log.v("lisisoiuaoisw",""+listIDs.toString());
                addGroupMember();


            }
        });
        editSearch = (EditText)findViewById(R.id.edit_search_addmemeber);
        editSearch.setTypeface(Lato_Regular);




            editSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    System.out.println("Text [" + s + "]");
                    ddAdapter1.getFilter().filter(s.toString());

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
                    hideSoftKeyboard(AddMembersActivity.this);
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




    public void getMemberList(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;
        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId",null);
        String groupID = getIntent().getStringExtra("groupId");

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("group_id",groupID);
            jsonObject.putOpt("user_id",user_ID);
            jsonObject.putOpt("version","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ADD_GROUP_MEMBERS, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getMemberListResponse(response);
                Log.e("Add Member ", "Add Member " + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this,"Error in "+ "ADDMemberList URL = " + PUTTAPI.ADD_GROUP_MEMBERS);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getMemberListResponse(JSONObject response){

        list = new ArrayList<AddmemberBean>();

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status!=null && status.equalsIgnoreCase("1")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("Suggestion List");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        AddmemberBean listBean = new AddmemberBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        listBean.setUser_id(jsonObject1.getString("member_id"));
                        listBean.setFull_name(jsonObject1.getString("full_name"));

                        listBean.setProfile_image(jsonObject1.getString("profile_image"));
                        listBean.setThumb_image(jsonObject1.getString("thumb_image"));

                        list.add(listBean);
                    }
                }else {


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (list.size()>0) {
            searchLayout.setVisibility(View.VISIBLE);
            saveBTN.setVisibility(View.VISIBLE);
            ddAdapter1 = new AddMemberAdapter( this,list);
            ddAdapter1.setCallback(AddMembersActivity.this);
            ddRecyclerView.setAdapter(ddAdapter1);
          //  ddRecyclerView.scrollToPosition(list.size() - 1);
        }else {

            saveBTN.setVisibility(View.GONE);
            searchLayout.setVisibility(View.GONE);
            NoInternetConnectionAdapter add = new NoInternetConnectionAdapter("Empty Member List.");
            ddRecyclerView.setAdapter(add);
        }


    }

    @Override
    public void deletePressed(int position) {
        String idd = String.valueOf(position);

        for (int i=0 ;i<listIDs.size();i++){

            if (idd.equalsIgnoreCase(listIDs.get(i))){

                Toast.makeText(getApplicationContext(),"Removed",Toast.LENGTH_SHORT).show();
                listIDs.remove(i);
            }
        }
    }

    @Override
    public void addPressed(int position) {

        listIDs.add(String.valueOf(position));

    }

    public void addGroupMember(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId",null);

        String groupID = getIntent().getStringExtra("groupId");

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id",user_ID);
            jsonObject.putOpt("group_id",groupID);

            JSONArray jsonArray = new JSONArray();
            for (int i=0 ; i< listIDs.size();i++){
                JSONObject jso = new JSONObject();
                jso.putOpt("member_id",listIDs.get(i));
                jsonArray.put(jso);
            }
            jsonObject.putOpt("group_member_list",jsonArray);
            jsonObject.putOpt("version","2");
            Log.v("createGroup",jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ADD_MEMBERS_InGroup, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                geCreateGroupResponse(response);
                Log.e("Add Group members", "Add Group membersList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this,"Error in "+ "Add group Members List URL = " + PUTTAPI.ADD_MEMBERS_InGroup);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void geCreateGroupResponse(JSONObject response){

        if(response != null){

            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status  = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){

                    String msg= jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                    finish();

                }else {
                    String msgError = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),msgError,Toast.LENGTH_SHORT).show();
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
