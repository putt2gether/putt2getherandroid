package com.putt2gether.fragments;

/**
 * Created by Ajay on 11/07/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.putt2gether.R;
import com.putt2gether.putt.DefineTeamActivity;
import com.putt2gether.putt.InvitePlayerActivity;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;


/**
 * Created by Ajay on 23/06/2016.
 */
public class TeamCreateFragment extends android.support.v4.app.Fragment {



    private LinearLayout addBTN;
    private LinearLayout player2Layout,player15Layout;
    private LinearLayout player3Layout,player10Layout,player11Layout,player12Layout,player13Layout,player14Layout;
    private LinearLayout player4Layout,player5Layout,player6Layout,player7Layout,player8Layout,player9Layout;
    private RelativeLayout parentID;

    EditText namePlayer1,handicapPlayer1,emailPlayer1;

    String player1Name,player1Email,player1Handicap;


    private RelativeLayout submitBTN;
    private String count;

    private SharedPreferences pref;
    private Constant constant;
    Typeface Lato_Regular;
    int handicap;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_create, container, false);

        parentID = (RelativeLayout)rootView.findViewById(R.id.parent_id_create);
        setupUI(parentID);




        constant = new Constant();
        Lato_Regular = Typeface.createFromAsset(getContext().getAssets(), constant.Lato_Regular);



        submitBTN = (RelativeLayout)rootView.findViewById(R.id.add_create_btn);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                pref = getActivity().getSharedPreferences("craete_event", Context.MODE_PRIVATE);

                player1Email = emailPlayer1.getText().toString();
                player1Name = namePlayer1.getText().toString();
                player1Handicap = handicapPlayer1.getText().toString();


                if (player1Handicap.length()!=0) {
                    handicap = Integer.parseInt(player1Handicap);
                }

                if (player1Name.length() == 0) {
                    namePlayer1.setError(getString(R.string.empty_name));
                }else if (handicap >30) {
                    handicapPlayer1.setError(getString(R.string.empty_handicap));
                }else if (player1Handicap.length()==0) {
                    handicapPlayer1.setError(getString(R.string.empty_handicap));
                }

                else if (player1Email.length() == 0) {
                    emailPlayer1.setError(getString(R.string.invalid_email));
                } else if (!Utility.isEmailValid(player1Email)) {
                    emailPlayer1.setError(getString(R.string.invalid_email));
                }
                else {
                    namePlayer1.setError(null);
                    handicapPlayer1.setError(null);
                    emailPlayer1.setError(null);

                    SharedPreferences pref = getActivity().getSharedPreferences("craete_event", Context.MODE_PRIVATE);

                    final String tag = pref.getString("tag",null);
                    final String activityType = pref.getString("activity_type",null);
                    Intent intent;

                    if (tag.equalsIgnoreCase("tag1")) {

                        if (player1Email != null && player1Name != null && player1Handicap != null) {
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("player1Email", player1Email);
                            editor.putString("player1Name", player1Name);
                            editor.putString("player1Handicap", player1Handicap);
                            editor.putString("count", "3");
                            editor.commit();
                        }
                    }else if (tag.equalsIgnoreCase("tag2")) {

                        if (player1Email != null && player1Name != null && player1Handicap != null) {
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("player2Email", player1Email);
                            editor.putString("player2Name", player1Name);
                            editor.putString("player2Handicap", player1Handicap);
                            editor.putString("count","3");
                            editor.commit();
                        }
                    }else if (tag.equalsIgnoreCase("tag3")) {

                        if (player1Email != null && player1Name != null && player1Handicap != null) {
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("player3Email", player1Email);
                            editor.putString("player3Name", player1Name);
                            editor.putString("player3Handicap", player1Handicap);
                            editor.putString("count", "3");
                            editor.commit();
                        }
                    }

                    if (activityType.equalsIgnoreCase("invite")){

                        intent = new Intent(getActivity(),InvitePlayerActivity.class);
                    }else {

                        intent = new Intent(getActivity(),DefineTeamActivity.class);
                    }

                    intent.putExtra("tag",tag);
                   // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);
                    getActivity().finish();
                }

            }
        });
        namePlayer1 = (EditText)rootView.findViewById(R.id.name_playe1);
        handicapPlayer1 = (EditText)rootView.findViewById(R.id.handicap_player1);
        emailPlayer1 = (EditText)rootView.findViewById(R.id.email_player1);


        namePlayer1.setTypeface(Lato_Regular);
        handicapPlayer1.setTypeface(Lato_Regular);
        emailPlayer1.setTypeface(Lato_Regular);

        addBTN = (LinearLayout)rootView.findViewById(R.id.create_playerADD);

        player2Layout = (LinearLayout)rootView.findViewById(R.id.player2_layout);
        player3Layout = (LinearLayout) rootView.findViewById(R.id.player3_layout);
        player2Layout = (LinearLayout)rootView.findViewById(R.id.player2_layout);
        player4Layout = (LinearLayout)rootView.findViewById(R.id.player4_layout);
        player5Layout = (LinearLayout)rootView.findViewById(R.id.player5_layout);
        player6Layout = (LinearLayout)rootView.findViewById(R.id.player6_layout);
        player7Layout = (LinearLayout)rootView.findViewById(R.id.player7_layout);
        player8Layout = (LinearLayout)rootView.findViewById(R.id.player8_layout);
        player9Layout = (LinearLayout)rootView.findViewById(R.id.player9_layout);
        player10Layout = (LinearLayout)rootView.findViewById(R.id.player10_layout);
        player11Layout = (LinearLayout)rootView.findViewById(R.id.player11_layout);
        player12Layout = (LinearLayout)rootView.findViewById(R.id.player12_layout);
        player13Layout = (LinearLayout)rootView.findViewById(R.id.player13_layout);

        player14Layout = (LinearLayout)rootView.findViewById(R.id.player14_layout);
        player15Layout = (LinearLayout)rootView.findViewById(R.id.player15_layout);

        addBTN.setVisibility(View.GONE);
        player2Layout.setVisibility(View.GONE);



        emailPlayer1.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus)
                    emailPlayer1.setHint("");
                else
                    emailPlayer1.setHint("ENTER UNIQUE EMAIL ID");
            }
        });

        handicapPlayer1.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus)
                    handicapPlayer1.setHint("");
                else
                    handicapPlayer1.setHint("ENTER HANDICAP");
            }
        });

        namePlayer1.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus)
                    namePlayer1.setHint("");
                else
                    namePlayer1.setHint("ENTER NAME");
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


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

}
