package com.putt2gether.putt.delegates;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.InviteEventDetail;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/13/2016.
 */
public class Delegates extends AppCompatActivity {


    RecyclerView delegate_list;
    private RecyclerView.LayoutManager ddLayoutManager;
    private RecyclerView.LayoutManager popUpDdLayoutManager;


    ArrayList<String> playername = new ArrayList<String>();
    ArrayList<String> playerholenumber = new ArrayList<String>();
    ArrayList<String> playerdelegate = new ArrayList<String>();
    DelegatesAdapter da;
    ImageView backdelegate;
    TextView txtStatusChange;
    TextView txtDeligateID;
    String event_ID;
    ArrayList<DelegateBean> list;
    ArrayList<DelegateBean> listTO;
    String player_id, deligate_id;
    RecyclerView lvdelegate;
    RelativeLayout continueBTN;
    String delegateID;
    String formatID;
    ArrayList<String> listA = new ArrayList<String>();
    ArrayList<String> listB = new ArrayList<String>();
    String player_id1, player_id2, player_id3, player_id4;
    String player_name1, player_name2, player_name3, player_name4;
    String deligate_id1, deligate_id2, deligate_id3, deligate_id4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.delegates);
        backdelegate = (ImageView) findViewById(R.id.back_delegate);
        event_ID = getIntent().getStringExtra("eventID");
        formatID = getIntent().getStringExtra("formatID");

        list = new ArrayList<DelegateBean>();


        ConnectionDetector con = new ConnectionDetector(this);
        if (con.isConnectingToInternet()) {

            delegateList(player_id);
        } else {
            Toast.makeText(getApplicationContext(), "No internet connections", Toast.LENGTH_SHORT).show();
        }


        continueBTN = (RelativeLayout) findViewById(R.id.continue_delegate_btn);

        continueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionDetector con = new ConnectionDetector(Delegates.this);
                if (con.isConnectingToInternet()) {
                    delegateContinue();

                   // Log.v("deligatejuh","p1"+player_id1+"p1"+deligate_id1+"p2"+player_id2+"p2"+deligate_id2);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connections", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delegate_list = (RecyclerView) findViewById(R.id.delegate_list);
        delegate_list.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(this);
        delegate_list.setLayoutManager(ddLayoutManager);


        backdelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delegates.this.finish();
            }
        });


        delegate_list.addOnItemTouchListener(new RecyclerTouchListener(this, delegate_list, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                txtStatusChange = (TextView) view.findViewById(R.id.delegate_to);
                txtDeligateID = (TextView) view.findViewById(R.id.delegate_to_id);
                player_id = list.get(position).getPlayerID();

                open_dialog(player_id, position);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }


    public interface ClickListener {
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


    public static class RecyclerTouchListenerTo implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListenerTo(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
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


    public void open_dialog(final String playerID, final int pos) {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);

        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog.setContentView(R.layout.delegate_pop_list);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels / 3;
        int width = displaymetrics.widthPixels;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, height);


        lvdelegate = (RecyclerView) dialog.findViewById(R.id.delegate_player_list);
        lvdelegate.setHasFixedSize(true);

        popUpDdLayoutManager = new LinearLayoutManager(dialog.getContext());
        lvdelegate.setLayoutManager(popUpDdLayoutManager);


        delegateList(playerID);


        lvdelegate.addOnItemTouchListener(new RecyclerTouchListenerTo(dialog.getContext(), lvdelegate, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                String counter = listTO.get(position).getScorerCount();
                int count = Integer.parseInt(counter);

                if (count<4) {

                    txtStatusChange.setVisibility(View.VISIBLE);
                    String delegate_Name = listTO.get(position).getPlayerName();
                    txtStatusChange.setText(delegate_Name.toUpperCase());
                    String delegate = listTO.get(position).getPlayerID();

                    txtDeligateID.setText(delegate);
                    dialog.cancel();

                    if (pos == 0) {
                        player_id1 = playerID;
                        deligate_id1 = txtDeligateID.getText().toString();
                    } else if (pos == 1) {
                        player_id2 = playerID;
                        deligate_id2 = txtDeligateID.getText().toString();
                    } else if (pos == 2) {
                        player_id3 = playerID;
                        deligate_id3 = txtDeligateID.getText().toString();
                    } else if (pos == 3) {
                        player_id4 = playerID;
                        deligate_id4 = txtDeligateID.getText().toString();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"You can't delegate",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        dialog.show();
    }
    public void delegateList(final String playerIDD) {

        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("event_id", event_ID);
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("player_id", playerIDD);
            jsonObject.putOpt("version", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("delegate request", jsonObject.toString());
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.DELEGATE_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getDelegateList(response, playerIDD);
                Log.e("Delegate LIST", "DELEGATE List" + response.toString());


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Utility.showLogError(this, "Error in " + "Delegate List URL = " + PUTTAPI.DELEGATE_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
    private void getDelegateList(JSONObject response, String delegatType) {
        listTO = new ArrayList<DelegateBean>();

        if (response != null) {
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        DelegateBean listBean = new DelegateBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        listBean.setScorerCount(jsonObject1.getString("scorer_count"));
                        listBean.setPlayerID(jsonObject1.getString("player_id"));
                        listBean.setScorerID(jsonObject1.getString("scorere_id"));
                        listBean.setEventID(jsonObject1.getString("event_id"));
                        listBean.setPlayerName(jsonObject1.getString("player_name"));
                        listBean.setScorerName(jsonObject1.getString("scorer_name"));
                        listBean.setHandicapValue(jsonObject1.getString("handicap_value"));
                        if (delegatType == null) {
                            list.add(listBean);
                        } else {
                            listTO.add(listBean);
                        }
                    }
                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (delegatType == null) {
            delegate_list.setAdapter(new DelegatesAdapter(Delegates.this, list));
        } else {
            lvdelegate.setAdapter(new DelegateToAdapter(Delegates.this, listTO));
        }
    }

    public void delegateContinue() {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("event_id", event_ID);
            jsonObject.putOpt("user_id", user_ID);

            JSONArray jsonArray = new JSONArray();


            for (int i = 0; i < list.size(); i++) {

                JSONObject joo = new JSONObject();
                if (i == 0) {
                    if ((deligate_id1) != null && (player_id1) != null) {
                        joo.putOpt("delegated_to", (deligate_id1));
                        joo.putOpt("player_id", (player_id1));
                    }
                    jsonArray.put(joo);
                }
                else if (i == 1) {
                    if ((deligate_id2) != null && (player_id2) != null) {
                        joo.putOpt("delegated_to", (deligate_id2));
                        joo.putOpt("player_id", (player_id2));
                    }
                    jsonArray.put(joo);
                }
                else if (i == 2) {
                    if ((deligate_id3) != null && (player_id3) != null) {
                        joo.putOpt("delegated_to", (deligate_id3));
                        joo.putOpt("player_id", (player_id3));
                    }
                    jsonArray.put(joo);
                }
                else if (i == 3) {
                    if ((deligate_id4) != null && (player_id4) != null) {
                        joo.putOpt("delegated_to", (deligate_id4));
                        joo.putOpt("player_id", (player_id4));
                    }
                    jsonArray.put(joo);
                }else {

                }
            }
            jsonObject.putOpt("delegate_player", jsonArray);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("delegate request", jsonObject.toString());

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.POST_DELEGATE_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getDelegateSubmitResponse(response);
                Log.e("Delegate submit", "DELEGATE submit" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "Delegate submit URL = " + PUTTAPI.POST_DELEGATE_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getDelegateSubmitResponse(JSONObject response) {

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

              //      final Dialog dialog = new Dialog(Delegates.this,android.R.style.Theme_Translucent_NoTitleBar);
                    final Dialog dialog = new Dialog(Delegates.this);

                    dialog.setCanceledOnTouchOutside(false);
                    Window window = dialog.getWindow();
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


                    WindowManager.LayoutParams wlp = window.getAttributes();

                    wlp.gravity = Gravity.CENTER;
                    wlp.dimAmount = 0.7f;
                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                    window.setAttributes(wlp);


                    // Include dialog.xml file
                    dialog.setContentView(R.layout.delegate);

                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    TextView dialogText =(TextView) dialog.findViewById(R.id.popup_msg);
                    RelativeLayout editBTN = (RelativeLayout)dialog.findViewById(R.id.ok_popup);

                    dialogText.setText("YOU HAVE SUCCESSFULLY DELEGATED YOUR EVENT");
                    dialog.show();
                    editBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.cancel();
                            Intent intent = new Intent(getApplicationContext(), InviteEventDetail.class);
                            intent.putExtra("from", "delegate");
                            intent.putExtra("eventID", event_ID);
                            intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }
                    });







                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


}
