package com.putt2gether.fragments;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.putt2gether.adapter.HistoryListAdapter;
import com.putt2gether.adapter.NoInternetConnectionAdapter;
import com.putt2gether.bean.InviteUserBean;
import com.putt2gether.bean.ScoreHistoryBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.HomeActivity;
import com.putt2gether.utils.Utility;
import com.putt2gether.view.DividerItemDecorationRecycle;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 8/23/2016.
 */
public class MyScoreFragments extends Fragment {


    RecyclerView recyclerViewhistry;
    ArrayList<ScoreHistoryBean> list = new ArrayList<ScoreHistoryBean>();
    ScoreHistoryBean sscoreHistoryBean;

    ImageView bannerImage;
    String banner_id;
    String banner_url;
    String banner_img;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.myscore, container, false);

        ((HomeActivity) getActivity()).addtitle("MY SCORE");

        recyclerViewhistry = (RecyclerView)rootView.findViewById(R.id.histrylist);

        bannerImage = (ImageView)rootView.findViewById(R.id.banner_myscore_list);
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


        recyclerViewhistry.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewhistry.addItemDecoration(new DividerItemDecorationRecycle(getContext()));

        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        if (connectionDetector.isConnectingToInternet()) {
            getEventHistoryList();
            getBannerList();
        } else {
            Toast.makeText(getActivity(), R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }





       /* recyclerViewhistry.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerViewhistry, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                sscoreHistoryBean = ( ScoreHistoryBean ) list.get( position );

                String eventID = sscoreHistoryBean.getEventID();

                Intent i=new Intent(getActivity(), Leaderboard.class);
                i.putExtra("eventID",eventID);
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
*/

        return rootView;
    }


    /*public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
*/




    public void getEventHistoryList(){

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
        Utility.showLogError(getActivity(),"Error in "+ "History list URL = " + PUTTAPI.EVENT_HISTORY_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getHistoryList(JSONObject response){

        list = new ArrayList<ScoreHistoryBean>();

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0 ; i < jsonArray.length(); i++){
                        ScoreHistoryBean listBean = new ScoreHistoryBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        listBean.setEventID(jsonObject1.getString("event_id"));
                        listBean.setEventName(jsonObject1.getString("event_name"));


                        listBean.setFormatName(jsonObject1.getString("format_name"));
                        listBean.setFormatID(jsonObject1.getString("format_id"));

                        listBean.setTotal(jsonObject1.getString("total"));

                        //listBean.setHandicap(jsonObject1.getString("handicap"));
                        listBean.setCurrentPosion(jsonObject1.getString("current_position"));
                        listBean.setGolfCourseName(jsonObject1.getString("golf_course_name"));


                        listBean.setEagle(jsonObject1.getString("eagle"));
                        listBean.setGrossScore(jsonObject1.getString("gross_score"));
                        listBean.setNoOfBirdies(jsonObject1.getString("no_of_birdies"));
                        listBean.setNoOfPars(jsonObject1.getString("no_of_pars"));
                        listBean.setNoOfPlayer(jsonObject1.getString("no_of_player"));
                        listBean.setDate(jsonObject1.getString("event_start_date"));

                        listBean.setNo_of_player_accepted(jsonObject1.getString("no_of_player_accepted"));

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

            recyclerViewhistry.setAdapter(new HistoryListAdapter(getActivity(), list));
        }else {

            recyclerViewhistry.setAdapter(new NoInternetConnectionAdapter("No History found"));

        }
    }

    public void getBannerList() {


        JSONObject jsonObject = null;


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("type", "7");
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
