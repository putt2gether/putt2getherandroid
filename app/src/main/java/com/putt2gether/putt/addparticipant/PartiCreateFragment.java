package com.putt2gether.putt.addparticipant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import com.putt2gether.R;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 27/02/2017.
 */
public class PartiCreateFragment extends android.support.v4.app.Fragment {


    private LinearLayout addBTN;
    private LinearLayout player2Layout, player15Layout;
    private LinearLayout player3Layout, player10Layout, player11Layout, player12Layout, player13Layout, player14Layout;
    private LinearLayout player4Layout, player5Layout, player6Layout, player7Layout, player8Layout, player9Layout;
    private RelativeLayout parentID;

    EditText namePlayer1, handicapPlayer1, emailPlayer1;
    EditText namePlayer2, handicapPlayer2, emailPlayer2;
    EditText namePlayer3, handicapPlayer3, emailPlayer3;
    EditText namePlayer4, handicapPlayer4, emailPlayer4;
    EditText namePlayer5, handicapPlayer5, emailPlayer5;
    EditText namePlayer6, handicapPlayer6, emailPlayer6;
    EditText namePlayer7, handicapPlayer7, emailPlayer7;
    EditText namePlayer8, handicapPlayer8, emailPlayer8;
    EditText namePlayer9, handicapPlayer9, emailPlayer9;
    EditText namePlayer10, handicapPlayer10, emailPlayer10;
    EditText namePlayer11, handicapPlayer11, emailPlayer11;
    EditText namePlayer12, handicapPlayer12, emailPlayer12;
    EditText namePlayer13, handicapPlayer13, emailPlayer13;
    EditText namePlayer14, handicapPlayer14, emailPlayer14;
    EditText namePlayer15, handicapPlayer15, emailPlayer15;
    String player1Name, player1Email, player1Handicap;
    String player2Name, player2Email, player2Handicap;
    String player3Name, player3Email, player3Handicap;
    String player4Name, player4Email, player4Handicap;
    String player5Name, player5Email, player5Handicap;
    String player6Name, player6Email, player6Handicap;
    String player7Name, player7Email, player7Handicap;
    String player8Name, player8Email, player8Handicap;
    String player9Name, player9Email, player9Handicap;
    private RelativeLayout submitBTN;
    private String count;

    private SharedPreferences addPartiSharedPreferences;
    private Constant constant;
    Typeface Lato_Regular;
    int handicap;
    String event_ID;

    private int top;
    private int topGroup;
    private int count_ed;

    @SuppressLint("ValidFragment")
    public PartiCreateFragment(String event_id) {
        event_ID = event_id;
    }

    public PartiCreateFragment( ) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create, container, false);

        parentID = (RelativeLayout) rootView.findViewById(R.id.parent_id_create);
        setupUI(parentID);


        constant = new Constant();
        Lato_Regular = Typeface.createFromAsset(getContext().getAssets(), constant.Lato_Regular);


        submitBTN = (RelativeLayout) rootView.findViewById(R.id.add_create_btn);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addPartiSharedPreferences = getActivity().getSharedPreferences("add_participant", Context.MODE_PRIVATE);

                player1Email = emailPlayer1.getText().toString();
                player1Name = namePlayer1.getText().toString();
                player1Handicap = handicapPlayer1.getText().toString();

                player2Email = emailPlayer2.getText().toString();
                player2Name = namePlayer2.getText().toString();
                player2Handicap = handicapPlayer2.getText().toString();


                player3Email = emailPlayer3.getText().toString();
                player3Name = namePlayer3.getText().toString();
                player3Handicap = handicapPlayer3.getText().toString();

                player4Email = emailPlayer4.getText().toString();
                player4Name = namePlayer4.getText().toString();
                player4Handicap = handicapPlayer4.getText().toString();

                player5Email = emailPlayer5.getText().toString();
                player5Name = namePlayer5.getText().toString();
                player5Handicap = handicapPlayer5.getText().toString();


                player6Email = emailPlayer6.getText().toString();
                player6Name = namePlayer6.getText().toString();
                player6Handicap = handicapPlayer6.getText().toString();

                player7Email = emailPlayer7.getText().toString();
                player7Name = namePlayer7.getText().toString();
                player7Handicap = handicapPlayer7.getText().toString();

                player8Email = emailPlayer8.getText().toString();
                player8Name = namePlayer8.getText().toString();
                player8Handicap = handicapPlayer8.getText().toString();


                player9Email = emailPlayer9.getText().toString();
                player9Name = namePlayer9.getText().toString();
                player9Handicap = handicapPlayer9.getText().toString();

                if (player1Handicap.length() != 0) {
                    handicap = Integer.parseInt(player1Handicap);
                }

                if (player1Name.length() == 0) {
                    namePlayer1.setError(getString(R.string.empty_name));
                } else if (handicap > 30) {
                    handicapPlayer1.setError(getString(R.string.empty_handicap));
                } else if (player1Handicap.length() == 0) {
                    handicapPlayer1.setError(getString(R.string.empty_handicap));
                } else if (player1Email.length() == 0) {
                    emailPlayer1.setError(getString(R.string.invalid_email));
                } else if (!Utility.isEmailValid(player1Email)) {
                    emailPlayer1.setError(getString(R.string.invalid_email));
                } else {
                    namePlayer1.setError(null);
                    handicapPlayer1.setError(null);
                    emailPlayer1.setError(null);

                    //    final String friendID = pref.getString("friendID0", null);

                    //    Log.v("friendIDmjkkj;lk",friendID);

                    // if (friendID==null) {
                    if (player1Email.length() > 0 && player1Name.length() > 0 && player1Handicap.length() > 0) {
                        SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
                        editor.putString("player1Email", player1Email);
                        editor.putString("player1Name", player1Name);
                        editor.putString("player1Handicap", player1Handicap);
                        editor.putString("count", "1");
                        editor.commit();

                    }
                    if (player2Email.length() > 0 && player2Name.length() > 0 && player2Handicap.length() > 0) {
                        SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
                        editor.putString("player2Email", player2Email);
                        editor.putString("player2Name", player2Name);
                        editor.putString("player2Handicap", player2Handicap);
                        editor.putString("count", "2");
                        editor.commit();
                    }
                    if (player3Email.length() > 0 && player3Name.length() > 0 && player3Handicap.length() > 0) {
                        SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
                        editor.putString("player3Email", player3Email);
                        editor.putString("player3Name", player3Name);
                        editor.putString("player3Handicap", player3Handicap);
                        editor.putString("count", "3");
                        editor.commit();
                    }

                    if (player4Email.length() > 0 && player4Name.length() > 0 && player4Handicap.length() > 0) {
                        SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
                        editor.putString("player4Email", player4Email);
                        editor.putString("player4Name", player4Name);
                        editor.putString("player4Handicap", player4Handicap);
                        editor.putString("count", "4");
                        editor.commit();
                    }

                    if (player5Email.length() > 0 && player5Name.length() > 0 && player5Handicap.length() > 0) {
                        SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
                        editor.putString("player5Email", player5Email);
                        editor.putString("player5Name", player5Name);
                        editor.putString("player5Handicap", player5Handicap);
                        editor.putString("count", "5");
                        editor.commit();
                    }

                    if (player6Email.length() > 0 && player6Name.length() > 0 && player6Handicap.length() > 0) {
                        SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
                        editor.putString("player6Email", player6Email);
                        editor.putString("player6Name", player6Name);
                        editor.putString("player6Handicap", player6Handicap);
                        editor.putString("count", "6");
                        editor.commit();
                    }

                    if (player7Email.length() > 0 && player7Name.length() > 0 && player7Handicap.length() > 0) {
                        SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
                        editor.putString("player7Email", player7Email);
                        editor.putString("player7Name", player7Name);
                        editor.putString("player7Handicap", player7Handicap);
                        editor.putString("count", "7");
                        editor.commit();
                    }

                    if (player8Email.length() > 0 && player8Name.length() > 0 && player8Handicap.length() > 0) {
                        SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
                        editor.putString("player8Email", player8Email);
                        editor.putString("player8Name", player8Name);
                        editor.putString("player8Handicap", player8Handicap);
                        editor.putString("count", "8");
                        editor.commit();
                    }

                    if (player9Email.length() > 0 && player9Name.length() > 0 && player9Handicap.length() > 0) {
                        SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
                        editor.putString("player9Email", player9Email);
                        editor.putString("player9Name", player9Name);
                        editor.putString("player9Handicap", player9Handicap);
                        editor.putString("count", "9");
                        editor.commit();
                    }

                    if (player1Email.length() > 0 && player1Name.length() > 0 && player1Handicap.length() > 0) {
                        submitTask();
                    } else {
                        Log.v("no friend Added", "no friend Added");
                    }

                }

            }
        });
        namePlayer1 = (EditText) rootView.findViewById(R.id.name_playe1);
        handicapPlayer1 = (EditText) rootView.findViewById(R.id.handicap_player1);
        emailPlayer1 = (EditText) rootView.findViewById(R.id.email_player1);
        namePlayer2 = (EditText) rootView.findViewById(R.id.name_playe2);
        handicapPlayer2 = (EditText) rootView.findViewById(R.id.handicap_player2);
        emailPlayer2 = (EditText) rootView.findViewById(R.id.email_player2);

        namePlayer3 = (EditText) rootView.findViewById(R.id.name_playe3);
        handicapPlayer3 = (EditText) rootView.findViewById(R.id.handicap_player3);
        emailPlayer3 = (EditText) rootView.findViewById(R.id.email_player3);
        namePlayer4 = (EditText) rootView.findViewById(R.id.name_playe4);
        handicapPlayer4 = (EditText) rootView.findViewById(R.id.handicap_player4);
        emailPlayer4 = (EditText) rootView.findViewById(R.id.email_player4);

        namePlayer5 = (EditText) rootView.findViewById(R.id.name_playe5);
        handicapPlayer5 = (EditText) rootView.findViewById(R.id.handicap_player5);
        emailPlayer5 = (EditText) rootView.findViewById(R.id.email_player5);
        namePlayer6 = (EditText) rootView.findViewById(R.id.name_playe6);
        handicapPlayer6 = (EditText) rootView.findViewById(R.id.handicap_player6);
        emailPlayer6 = (EditText) rootView.findViewById(R.id.email_player6);

        namePlayer7 = (EditText) rootView.findViewById(R.id.name_playe7);
        handicapPlayer7 = (EditText) rootView.findViewById(R.id.handicap_player7);
        emailPlayer7 = (EditText) rootView.findViewById(R.id.email_player7);
        namePlayer8 = (EditText) rootView.findViewById(R.id.name_playe8);
        handicapPlayer8 = (EditText) rootView.findViewById(R.id.handicap_player8);
        emailPlayer8 = (EditText) rootView.findViewById(R.id.email_player8);

        namePlayer9 = (EditText) rootView.findViewById(R.id.name_playe9);
        handicapPlayer9 = (EditText) rootView.findViewById(R.id.handicap_player9);
        emailPlayer9 = (EditText) rootView.findViewById(R.id.email_player9);
        namePlayer10 = (EditText) rootView.findViewById(R.id.name_playe10);
        handicapPlayer10 = (EditText) rootView.findViewById(R.id.handicap_player10);
        emailPlayer10 = (EditText) rootView.findViewById(R.id.email_player10);

        namePlayer11 = (EditText) rootView.findViewById(R.id.name_playe11);
        handicapPlayer11 = (EditText) rootView.findViewById(R.id.handicap_player11);
        emailPlayer11 = (EditText) rootView.findViewById(R.id.email_player11);
        namePlayer12 = (EditText) rootView.findViewById(R.id.name_playe12);
        handicapPlayer12 = (EditText) rootView.findViewById(R.id.handicap_player12);
        emailPlayer12 = (EditText) rootView.findViewById(R.id.email_player12);

        namePlayer13 = (EditText) rootView.findViewById(R.id.name_playe13);
        handicapPlayer13 = (EditText) rootView.findViewById(R.id.handicap_player13);
        emailPlayer13 = (EditText) rootView.findViewById(R.id.email_player13);
        namePlayer14 = (EditText) rootView.findViewById(R.id.name_playe14);
        handicapPlayer14 = (EditText) rootView.findViewById(R.id.handicap_player14);
        emailPlayer14 = (EditText) rootView.findViewById(R.id.email_player14);

        namePlayer15 = (EditText) rootView.findViewById(R.id.name_playe15);
        handicapPlayer15 = (EditText) rootView.findViewById(R.id.handicap_player15);
        emailPlayer15 = (EditText) rootView.findViewById(R.id.email_player15);


        namePlayer1.setTypeface(Lato_Regular);
        namePlayer2.setTypeface(Lato_Regular);
        handicapPlayer1.setTypeface(Lato_Regular);
        handicapPlayer2.setTypeface(Lato_Regular);
        emailPlayer1.setTypeface(Lato_Regular);
        emailPlayer2.setTypeface(Lato_Regular);

        namePlayer3.setTypeface(Lato_Regular);
        namePlayer4.setTypeface(Lato_Regular);
        handicapPlayer3.setTypeface(Lato_Regular);
        handicapPlayer4.setTypeface(Lato_Regular);
        emailPlayer3.setTypeface(Lato_Regular);
        emailPlayer4.setTypeface(Lato_Regular);

        namePlayer5.setTypeface(Lato_Regular);
        namePlayer6.setTypeface(Lato_Regular);
        handicapPlayer5.setTypeface(Lato_Regular);
        handicapPlayer6.setTypeface(Lato_Regular);
        emailPlayer5.setTypeface(Lato_Regular);
        emailPlayer6.setTypeface(Lato_Regular);

        namePlayer7.setTypeface(Lato_Regular);
        namePlayer8.setTypeface(Lato_Regular);
        handicapPlayer7.setTypeface(Lato_Regular);
        handicapPlayer8.setTypeface(Lato_Regular);
        emailPlayer7.setTypeface(Lato_Regular);
        emailPlayer8.setTypeface(Lato_Regular);

        namePlayer9.setTypeface(Lato_Regular);
        namePlayer10.setTypeface(Lato_Regular);
        handicapPlayer9.setTypeface(Lato_Regular);
        handicapPlayer10.setTypeface(Lato_Regular);
        emailPlayer9.setTypeface(Lato_Regular);
        emailPlayer10.setTypeface(Lato_Regular);

        namePlayer11.setTypeface(Lato_Regular);
        namePlayer12.setTypeface(Lato_Regular);
        handicapPlayer11.setTypeface(Lato_Regular);
        handicapPlayer12.setTypeface(Lato_Regular);
        emailPlayer11.setTypeface(Lato_Regular);
        emailPlayer12.setTypeface(Lato_Regular);

        namePlayer13.setTypeface(Lato_Regular);
        namePlayer14.setTypeface(Lato_Regular);
        handicapPlayer13.setTypeface(Lato_Regular);
        handicapPlayer14.setTypeface(Lato_Regular);
        emailPlayer13.setTypeface(Lato_Regular);
        emailPlayer14.setTypeface(Lato_Regular);

        namePlayer15.setTypeface(Lato_Regular);
        handicapPlayer15.setTypeface(Lato_Regular);
        emailPlayer15.setTypeface(Lato_Regular);

        addBTN = (LinearLayout) rootView.findViewById(R.id.create_playerADD);
        player3Layout = (LinearLayout) rootView.findViewById(R.id.player3_layout);
        player2Layout = (LinearLayout) rootView.findViewById(R.id.player2_layout);
        player4Layout = (LinearLayout) rootView.findViewById(R.id.player4_layout);
        player5Layout = (LinearLayout) rootView.findViewById(R.id.player5_layout);
        player6Layout = (LinearLayout) rootView.findViewById(R.id.player6_layout);
        player7Layout = (LinearLayout) rootView.findViewById(R.id.player7_layout);
        player8Layout = (LinearLayout) rootView.findViewById(R.id.player8_layout);
        player9Layout = (LinearLayout) rootView.findViewById(R.id.player9_layout);
        player10Layout = (LinearLayout) rootView.findViewById(R.id.player10_layout);
        player11Layout = (LinearLayout) rootView.findViewById(R.id.player11_layout);
        player12Layout = (LinearLayout) rootView.findViewById(R.id.player12_layout);
        player13Layout = (LinearLayout) rootView.findViewById(R.id.player13_layout);

        player14Layout = (LinearLayout) rootView.findViewById(R.id.player14_layout);
        player15Layout = (LinearLayout) rootView.findViewById(R.id.player15_layout);


        emailPlayer1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    emailPlayer1.setHint("");
                else
                    emailPlayer1.setHint("ENTER UNIQUE EMAIL ID");
            }
        });

        handicapPlayer1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    handicapPlayer1.setHint("");
                else
                    handicapPlayer1.setHint("ENTER HANDICAP");
            }
        });

        namePlayer1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    namePlayer1.setHint("");
                else
                    namePlayer1.setHint("ENTER NAME");
            }
        });


        emailPlayer2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    emailPlayer2.setHint("");
                else
                    emailPlayer2.setHint("ENTER UNIQUE EMAIL ID");
            }
        });

        handicapPlayer2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    handicapPlayer2.setHint("");
                else
                    handicapPlayer2.setHint("ENTER HANDICAP");
            }
        });

        namePlayer2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    namePlayer2.setHint("");
                else
                    namePlayer2.setHint("ENTER NAME");
            }
        });

        emailPlayer3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    emailPlayer3.setHint("");
                else
                    emailPlayer3.setHint("ENTER UNIQUE EMAIL ID");
            }
        });

        handicapPlayer3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    handicapPlayer3.setHint("");
                else
                    handicapPlayer3.setHint("ENTER HANDICAP");
            }
        });

        namePlayer3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    namePlayer3.setHint("");
                else
                    namePlayer3.setHint("ENTER NAME");
            }
        });


        emailPlayer4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    emailPlayer4.setHint("");
                else
                    emailPlayer4.setHint("ENTER UNIQUE EMAIL ID");
            }
        });

        handicapPlayer4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    handicapPlayer4.setHint("");
                else
                    handicapPlayer4.setHint("ENTER HANDICAP");
            }
        });

        namePlayer4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    handicapPlayer4.setHint("");
                else
                    handicapPlayer4.setHint("ENTER NAME");
            }
        });


        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player14Layout.getVisibility() == View.VISIBLE) {
                    player15Layout.setVisibility(View.VISIBLE);
                    addBTN.setVisibility(View.GONE);
                } else if (player13Layout.getVisibility() == View.VISIBLE) {
                    player14Layout.setVisibility(View.VISIBLE);
                } else if (player12Layout.getVisibility() == View.VISIBLE) {
                    player13Layout.setVisibility(View.VISIBLE);
                } else if (player11Layout.getVisibility() == View.VISIBLE) {
                    player12Layout.setVisibility(View.VISIBLE);
                } else if (player10Layout.getVisibility() == View.VISIBLE) {
                    player11Layout.setVisibility(View.VISIBLE);
                } else if (player9Layout.getVisibility() == View.VISIBLE) {
                    player10Layout.setVisibility(View.VISIBLE);
                } else if (player8Layout.getVisibility() == View.VISIBLE) {
                    player9Layout.setVisibility(View.VISIBLE);
                } else if (player7Layout.getVisibility() == View.VISIBLE) {
                    player8Layout.setVisibility(View.VISIBLE);
                } else if (player6Layout.getVisibility() == View.VISIBLE) {
                    player7Layout.setVisibility(View.VISIBLE);
                } else if (player5Layout.getVisibility() == View.VISIBLE) {
                    player6Layout.setVisibility(View.VISIBLE);
                } else if (player4Layout.getVisibility() == View.VISIBLE) {
                    player5Layout.setVisibility(View.VISIBLE);
                } else if (player3Layout.getVisibility() == View.VISIBLE) {
                    player4Layout.setVisibility(View.VISIBLE);
                } else if (player2Layout.getVisibility() == View.VISIBLE) {
                    player3Layout.setVisibility(View.VISIBLE);
                }
            }
        });


        addBTN.setVisibility(View.VISIBLE);


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onPause() {


        if (player1Name!=null && player1Email.length() > 0 && player1Name.length() > 0 && player1Handicap.length() > 0) {
            SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
            editor.putString("player1Email", player1Email);
            editor.putString("player1Name", player1Name);
            editor.putString("player1Handicap", player1Handicap);
            editor.putString("count", "1");
            editor.commit();

        }
        if (player2Name!=null && player2Email.length() > 0 && player2Name.length() > 0 && player2Handicap.length() > 0) {
            SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
            editor.putString("player2Email", player2Email);
            editor.putString("player2Name", player2Name);
            editor.putString("player2Handicap", player2Handicap);
            editor.putString("count", "2");
            editor.commit();
        }
        if (player3Name!=null &&player3Email.length() > 0 && player3Name.length() > 0 && player3Handicap.length() > 0) {
            SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
            editor.putString("player3Email", player3Email);
            editor.putString("player3Name", player3Name);
            editor.putString("player3Handicap", player3Handicap);
            editor.putString("count", "3");
            editor.commit();
        }

        if (player4Name!=null && player4Email.length() > 0 && player4Name.length() > 0 && player4Handicap.length() > 0) {
            SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
            editor.putString("player4Email", player4Email);
            editor.putString("player4Name", player4Name);
            editor.putString("player4Handicap", player4Handicap);
            editor.putString("count", "4");
            editor.commit();
        }

        if (player5Name!=null && player5Email.length() > 0 && player5Name.length() > 0 && player5Handicap.length() > 0) {
            SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
            editor.putString("player5Email", player5Email);
            editor.putString("player5Name", player5Name);
            editor.putString("player5Handicap", player5Handicap);
            editor.putString("count", "5");
            editor.commit();
        }

        if (player6Name!=null && player6Email.length() > 0 && player6Name.length() > 0 && player6Handicap.length() > 0) {
            SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
            editor.putString("player6Email", player6Email);
            editor.putString("player6Name", player6Name);
            editor.putString("player6Handicap", player6Handicap);
            editor.putString("count", "6");
            editor.commit();
        }

        if (player7Name!=null && player7Email.length() > 0 && player7Name.length() > 0 && player7Handicap.length() > 0) {
            SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
            editor.putString("player7Email", player7Email);
            editor.putString("player7Name", player7Name);
            editor.putString("player7Handicap", player7Handicap);
            editor.putString("count", "7");
            editor.commit();
        }

        if (player8Name!=null && player8Email.length() > 0 && player8Name.length() > 0 && player8Handicap.length() > 0) {
            SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
            editor.putString("player8Email", player8Email);
            editor.putString("player8Name", player8Name);
            editor.putString("player8Handicap", player8Handicap);
            editor.putString("count", "8");
            editor.commit();
        }

        if (player9Name!=null && player9Email.length() > 0 && player9Name.length() > 0 && player9Handicap.length() > 0) {
            SharedPreferences.Editor editor = addPartiSharedPreferences.edit();
            editor.putString("player9Email", player9Email);
            editor.putString("player9Name", player9Name);
            editor.putString("player9Handicap", player9Handicap);
            editor.putString("count", "9");
            editor.commit();
        }

        super.onPause();
    }


    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

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

    public void submitTask() {


        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        String userID = pref.getString("userId", null);

        try {
            jsonObject = new JSONObject();

            jsonObject.putOpt("event_id", event_ID);
            jsonObject.putOpt("event_admin_id", userID);
            jsonObject.putOpt("version", "2");
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
                count_ed = Integer.parseInt(countSTR);
                Log.v("COUNTTTT", countSTR);
            }

            if (topGroup != 0) {
                JSONArray jsonArrayG = new JSONArray();
                for (int i = 0; i < topGroup; i++) {
                    JSONObject jsonObjectG = new JSONObject();
                    String groupID = addPartiSharedPreferences.getString("groupID" + i, null);
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
                jsonObject.putOpt("event_friend_num", top);
            }

            if (count_ed != 0) {
                JSONArray jsonArray2 = new JSONArray();
                for (int i = 0; i < count_ed; i++) {
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
        } catch (JSONException e) {
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

    public void participantResponse(JSONObject response) {


        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    JSONObject jsObject = jsonObject.getJSONObject("Event");

                    String eventID = jsObject.getString("event_id");
                    String message = jsObject.getString("message");
                    JSONObject data = jsObject.getJSONObject("data");
                    String adminName = data.getString("admin_name");

                    Toast.makeText(getActivity(), "" + message, Toast.LENGTH_LONG).show();

                    SharedPreferences createSharedPreferences = getActivity().getSharedPreferences("add_participant", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = createSharedPreferences.edit();
                    editor1.clear();
                    editor1.commit();

                    getActivity().finish();

                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}