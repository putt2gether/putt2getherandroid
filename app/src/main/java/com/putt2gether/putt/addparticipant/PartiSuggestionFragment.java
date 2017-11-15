package com.putt2gether.putt.addparticipant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.adapter.SimpleSectionedRecyclerViewAdapter;
import com.putt2gether.bean.SuggestionBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 27/02/2017.
 */
public class PartiSuggestionFragment  extends android.support.v4.app.Fragment implements PartiSuggestionsAdapter.AddPlayerAdapterCallback{
    private RecyclerView ddRecyclerView;
    private RecyclerView ddRecyclerView1;
    private PartiSuggestionsAdapter ddAdapter1;
    private RecyclerView.LayoutManager ddLayoutManager;
    private RelativeLayout parentID;
    private RelativeLayout nextBTN;
    private EditText editSearch;
    private Constant constant;
    Typeface Lato_Regular;
    int inCount;
    int po;
    ArrayList<String> listIDs = new ArrayList<String>();

    List<SuggestionBean> books = new ArrayList<SuggestionBean>();
    String event_ID;

    SimpleSectionedRecyclerViewAdapter mSectionedAdapter;
    private SharedPreferences addPartiSharedPreferences;
    private int top;
    private int topGroup;
    private int count;
  //  private String email1,handicap1,name1;
    private String groupID;

    @SuppressLint("ValidFragment")
    public PartiSuggestionFragment(String event_id) {
        event_ID = event_id;
    }

    public PartiSuggestionFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragments_suggestions, container, false);

        addPartiSharedPreferences = getActivity().getSharedPreferences("add_participant", Context.MODE_PRIVATE);

        constant = new Constant();
        Lato_Regular = Typeface.createFromAsset(getContext().getAssets(), constant.Lato_Regular);
        parentID = (RelativeLayout)rootView.findViewById(R.id.parent_id_sugg);
        setupUI(parentID);
        nextBTN = (RelativeLayout)rootView.findViewById(R.id.next_btn_suggestions);
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0 ; i < listIDs.size() ; i++){

                    String id1 = listIDs.get(i);
                    Log.v("idddddddd"+i,id1);


                    if(addPartiSharedPreferences==null)
                        return;
                    SharedPreferences.Editor editor1 = addPartiSharedPreferences.edit();
                    // editor1.putString("top", String.valueOf(top));
                    editor1.putString("friendID"+i, id1);
                    editor1.commit();
                }

                if (listIDs.size()>0){
                    submitTask();
                }else {
                    Log.v("no friend Added","no friend Added");
                }


            }
        });
        editSearch = (EditText)rootView.findViewById(R.id.edit_search_suggetions);
        editSearch.setTypeface(Lato_Regular);
        addListener(rootView);

        return rootView;
    }

    @Override
    public void onPause() {

        for (int i = 0 ; i < listIDs.size() ; i++){

            String id1 = listIDs.get(i);
            Log.v("idddddddd"+i,id1);


            if(addPartiSharedPreferences==null)
                return;
            SharedPreferences.Editor editor1 = addPartiSharedPreferences.edit();
            // editor1.putString("top", String.valueOf(top));
            editor1.putString("friendID"+i, id1);
            editor1.commit();
        }

        super.onPause();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        ddRecyclerView = (RecyclerView)getActivity(). findViewById(R.id.suggestions_list);

        ddRecyclerView.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(getActivity());
        ddRecyclerView.setLayoutManager(ddLayoutManager);

        if (connectionDetector.isConnectingToInternet()) {
            getSuggestionListList();
        } else {
            Toast.makeText(getActivity(), R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }

    }

    public void addListener(View rootView){

        //in your Activity or Fragment where of Adapter is instantiated :

        editSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text ["+s+"]");

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
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView =activity.getCurrentFocus();
    /*
     * If no view is focused, an NPE will be thrown
     *
     * Maxim Dmitriev
     */
        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
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
            jsonObject.putOpt("event_id",event_ID);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.SUGGESTION_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getSuggestionList(response);
                Log.e("Suggestion Fragment", "GetSuggestionList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(),"Error in "+ "GetSuggestionList URL = " + PUTTAPI.SUGGESTION_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getSuggestionList(JSONObject response){

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

                    listBean.setIsAdded(jsonObject1.getString("added"));

                    //listBean.setHandicap(jsonObject1.getString("handicap"));
                    listBean.setProfile_image(jsonObject1.getString("profile_image"));
                    listBean.setThumb_image(jsonObject1.getString("thumb_image"));

                    list.add(listBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ddAdapter1 = new PartiSuggestionsAdapter(getActivity(),list);
        ddAdapter1.setCallback(this);
        ddRecyclerView.setAdapter(ddAdapter1);


    }

    @Override
    public void deletePressed(int position) {
        String idd = String.valueOf(position);

        for (int i=0 ;i<listIDs.size();i++){

            if (idd.equalsIgnoreCase(listIDs.get(i))){

                listIDs.remove(i);
            }
        }
    }

    @Override
    public void addPressed(int position) {

        listIDs.add(String.valueOf(position));

    }


    public void submitTask(){


        JSONObject jsonObject = null;

        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        String userID = pref.getString("userId",null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("event_id",event_ID);
            jsonObject.putOpt("event_admin_id",userID);
            jsonObject.putOpt("version","2");

            String topSTR = addPartiSharedPreferences.getString("top", null);
            String topGroupSTR = addPartiSharedPreferences.getString("topGroup", null);

            if (topGroupSTR != null) {

                topGroup = Integer.parseInt(topGroupSTR);

            }

            if (topSTR != null) {
                top = Integer.parseInt(topSTR);
            }
            String countSTR = addPartiSharedPreferences.getString("count", null);

            if (countSTR != null) {
                count = Integer.parseInt(countSTR);
                Log.v("COUNTTTT", countSTR);
            }


            if (topGroup != 0) {
                JSONArray jsonArrayG = new JSONArray();
                for (int i = 0; i < topGroup; i++) {
                    JSONObject jsonObjectG = new JSONObject();
                    groupID = addPartiSharedPreferences.getString("groupID" + i, null);
                    jsonObjectG.putOpt("group", groupID);
                    jsonArrayG.put(jsonObjectG);
                }
                jsonObject.putOpt("event_group_list", jsonArrayG);
            }

            if (top != 0) {
                JSONArray jsonArray1 = new JSONArray();
                for (int i = 0; i < top; i++) {
                    JSONObject jsonObject2 = new JSONObject();
                    String friendID = addPartiSharedPreferences.getString("friendID" + i, null);
                    jsonObject2.putOpt("friend_id", friendID);
                    jsonArray1.put(jsonObject2);
                }
                jsonObject.putOpt("event_friend_list", jsonArray1);
                jsonObject.putOpt("event_friend_num",top);
            }

            if (count != 0) {
                JSONArray jsonArray2 = new JSONArray();
                for (int i = 0; i < count; i++) {
                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.putOpt("name", addPartiSharedPreferences.getString("player" + (i + 1) + "Name", null));
                    jsonObject3.putOpt("email", addPartiSharedPreferences.getString("player" + (i + 1) + "Email", null));
                    jsonObject3.putOpt("handicap", addPartiSharedPreferences.getString("player" + (i + 1) + "Handicap", null));

                    String createPlayerType = addPartiSharedPreferences.getString("player" + (i + 1) + "Team", null);

                    if (createPlayerType != null) {
                        jsonObject3.putOpt("team_number", createPlayerType);
                    } else {
                        jsonObject3.putOpt("team_number", "0");
                    }

                    jsonArray2.put(jsonObject3);
                }
                jsonObject.putOpt("invited_email_list", jsonArray2);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ADD_PARTICIPANT_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                Log.e("Create Event", "OnResponse =" + response.toString());
                participantResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getActivity(), "error_network_timeout",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                    Toast.makeText(getActivity(), "AuthFailureError", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    //TODO
                    Toast.makeText(getActivity(), "ServerError", Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(getActivity(), "NetworkError", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(getActivity(), "ParseError", Toast.LENGTH_LONG).show();
                }
                pDialog.dismiss();

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Add Participant", "Url= " + PUTTAPI.GETSCORERLIST + " PostObject = " + jsonObject.toString());

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }



    public void participantResponse(JSONObject response){


        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){

                    JSONObject jsObject = jsonObject.getJSONObject("Event");

                    String eventID = jsObject.getString("event_id");
                    String message = jsObject.getString("message");
                    JSONObject data = jsObject.getJSONObject("data");
                    String adminName = data.getString("admin_name");

                    Toast.makeText(getActivity(),""+message,Toast.LENGTH_LONG).show();

                    SharedPreferences createSharedPreferences = getActivity().getSharedPreferences("add_participant", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = createSharedPreferences.edit();
                    editor1.clear();
                    editor1.commit();

                    getActivity().finish();

                }else{
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(getActivity(),errorMessage,Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}