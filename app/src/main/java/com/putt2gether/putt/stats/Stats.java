package com.putt2gether.putt.stats;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import com.putt2gether.R;
import com.putt2gether.adapter.pageradapter.StatsPagerAdapter;
import com.putt2gether.fragments.statsfrag.StatsBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.HomeActivity;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Abc on 9/7/2016.
 */
public class Stats extends Fragment {


    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    ViewPager viewPager;

    ImageView viewone, viewtwo, viewthree, viewfour, viewfive;
    Button last_10_stats;

    StatsBean statsBean;
    LinearLayout f1, f2, f3, f4, f5;
    SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stats, container, false);

        viewone = (ImageView) rootView.findViewById(R.id.firstview);
        viewtwo = (ImageView) rootView.findViewById(R.id.secondview);
        viewthree = (ImageView) rootView.findViewById(R.id.thirdview);
        viewfour = (ImageView) rootView.findViewById(R.id.fourthview);
        viewfive = (ImageView) rootView.findViewById(R.id.fifthview);

        f1 = (LinearLayout) rootView.findViewById(R.id.f1);
        f2 = (LinearLayout) rootView.findViewById(R.id.f2);
        f3 = (LinearLayout) rootView.findViewById(R.id.f3);
        f4 = (LinearLayout) rootView.findViewById(R.id.f4);
        f5 = (LinearLayout) rootView.findViewById(R.id.f5);

        last_10_stats = (Button) rootView.findViewById(R.id.last_10_stats);

        viewPager = (ViewPager) rootView.findViewById(R.id.statsviewpager);
        ((HomeActivity) getActivity()).addtitle("MY STATS");

        mSharedPreferences = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        ConnectionDetector con = new ConnectionDetector(getActivity());
        if (con.isConnectingToInternet()) {
            String stats = mSharedPreferences.getString("stats",null);

            if (stats==null){
                getPieChartValue("10");
                last_10_stats.setText("LAST 10");
            } else if (stats.equalsIgnoreCase("10")) {
                getPieChartValue("10");
                last_10_stats.setText("LAST 10");
            }else if (stats.equalsIgnoreCase("5")) {
                getPieChartValue("5");
                last_10_stats.setText("LAST 5");
            }else if (stats.equalsIgnoreCase("20")) {
                getPieChartValue("20");
                last_10_stats.setText("LAST 20");
            }else if (stats.equalsIgnoreCase("0")) {
                last_10_stats.setText("OVERALL");
                getPieChartValue("0");
            }else if (stats.equalsIgnoreCase("1")) {
                last_10_stats.setText("LAST GAME");
                getPieChartValue("1");
            }


        } else {
            Toast.makeText(getActivity(), "No internet connections", Toast.LENGTH_SHORT).show();
        }

        viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {
                    viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                    viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                }

                if (position == 1) {
                    viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                    viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                }

                if (position == 2) {
                    viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                    viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                }

                if (position == 3) {
                    viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                    viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                }

                if (position == 4) {
                    viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewPager.setCurrentItem(0);
            }
        });

        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewPager.setCurrentItem(1);
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewPager.setCurrentItem(2);
            }
        });

        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewPager.setCurrentItem(3);
            }
        });

        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                viewPager.setCurrentItem(4);
            }
        });


        last_10_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog);

                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                dialog.setCanceledOnTouchOutside(true);
                Window window = dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);


                // Include dialog.xml file
                dialog.setContentView(R.layout.top_ten_popup);
                // window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                ImageView crossBTN = (ImageView) dialog.findViewById(R.id.cross_btn);
                RelativeLayout last10 = (RelativeLayout) dialog.findViewById(R.id.last_lay_10);
                RelativeLayout last5 = (RelativeLayout) dialog.findViewById(R.id.last_lay_5);
                RelativeLayout last20 = (RelativeLayout) dialog.findViewById(R.id.last_lay_20);
                RelativeLayout lastgame = (RelativeLayout) dialog.findViewById(R.id.last_lay);

                RelativeLayout overAll = (RelativeLayout) dialog.findViewById(R.id.last_lay_overall);

                last5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        last_10_stats.setText("LAST 5");
                        getPieChartValue("5");
                        viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                        viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    }
                });

                last10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        last_10_stats.setText("LAST 10");

                        getPieChartValue("10");
                        viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                        viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    }
                });

                last20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        last_10_stats.setText("LAST 20");
                        getPieChartValue("20");
                        viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                        viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    }
                });

                lastgame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        last_10_stats.setText("LAST GAME");
                        getPieChartValue("1");
                        viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                        viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    }
                });

                overAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        last_10_stats.setText("OVERALL");
                        getPieChartValue("0");
                        viewone.setBackground(getResources().getDrawable(R.mipmap.mystatsclicked));
                        viewtwo.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewthree.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewfour.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                        viewfive.setBackground(getResources().getDrawable(R.mipmap.mystatsnotclicked));
                    }
                });


                dialog.show();

                crossBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });

        return rootView;
    }


    public void getPieChartValue(final String last) {

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        final String user_ID = pref.getString("userId", null);

        jsonObject = new JSONObject();
        try {
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("no_of_event", last);
            jsonObject.putOpt("version", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // jsonObject.putOpt("access_token", accessToken);

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.PIECHART_STATS_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                Log.e("pie chart", "OnResponse =" + response.toString());
                getPieResponse(response,last);

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
        Log.e("Sign Up", "Url= " + PUTTAPI.PIECHART_STATS_URL + " PostObject = " + jsonObject.toString());
        //requestQueue.add(jsonObjectRequest);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    private void getPieResponse(JSONObject response,String last) {


        statsBean = new StatsBean();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")) {
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject jsonObject1 = data.getJSONObject("pichart");
                 /*   pieDataBean.setEagle(jsonObject1.getString("no_of_eagle"));
                    pieDataBean.setBirdie(jsonObject1.getString("no_of_birdies"));
                    pieDataBean.setPar(jsonObject1.getString("no_of_pars"));
                    pieDataBean.setBogey(jsonObject1.getString("no_of_bogeys"));
                    pieDataBean.setDoublebogey(jsonObject1.getString("no_of_double_bogeys"));
                    pieDataBean.setAverage(jsonObject1.getString("gross_score"));
*/
                    JSONObject jsonObject_score = data.getJSONObject("score_stats");

                    statsBean.setAvg_par3s(jsonObject_score.getString("avg_par3s"));
                    statsBean.setAvg_par4s(jsonObject_score.getString("avg_par4s"));
                    statsBean.setAvg_par5s(jsonObject_score.getString("avg_par5s"));
                    statsBean.setAvg_gross_score(jsonObject_score.getString("avg_gross_score"));
                    statsBean.setAvg_in(jsonObject_score.getString("avg_in"));
                    statsBean.setAvg_out(jsonObject_score.getString("avg_out"));

                    statsBean.setLast_gross_score(jsonObject_score.getString("last_gross_score"));
                    statsBean.setLast_in(jsonObject_score.getString("last_in"));
                    statsBean.setLast_out(jsonObject_score.getString("last_out"));
                    statsBean.setLast_par3s(jsonObject_score.getString("last_par3s"));
                    statsBean.setLast_par4s(jsonObject_score.getString("last_par4s"));
                    statsBean.setLast_par5s(jsonObject_score.getString("last_par5s"));


                    statsBean.setGscore_change(jsonObject_score.getString("gscore_change"));
                    statsBean.setGscore_change_color(jsonObject_score.getString("gscore_change_color"));
                    statsBean.setPar3_change(jsonObject_score.getString("par3_change"));
                    statsBean.setPar3_change_color(jsonObject_score.getString("par3_change_color"));
                    statsBean.setPar4_change(jsonObject_score.getString("par4_change"));
                    statsBean.setPar4_change_color(jsonObject_score.getString("par4_change_color"));
                    statsBean.setPar5_change(jsonObject_score.getString("par5_change"));
                    statsBean.setPar5_change_color(jsonObject_score.getString("par5_change_color"));
                    statsBean.setIn_change(jsonObject_score.getString("in_change"));
                    statsBean.setIn_change_color(jsonObject_score.getString("in_change_color"));
                    statsBean.setOut_change(jsonObject_score.getString("out_change"));
                    statsBean.setOut_change_color(jsonObject_score.getString("out_change_color"));


                    JSONObject jsonObject_gir = data.getJSONObject("gir_percentage");
                    statsBean.setGir_hit(jsonObject_gir.getString("hit"));
                    statsBean.setGir_missed(jsonObject_gir.getString("missed"));


                    JSONObject jsonObject_fair = data.getJSONObject("fairway_percentage");
                    statsBean.setLeft_fairway(jsonObject_fair.getString("left"));
                    statsBean.setRight_fairway(jsonObject_fair.getString("right"));
                    statsBean.setHit_fairway(jsonObject_fair.getString("hit"));

                    JSONObject jsonObject_putting = data.getJSONObject("putting_stats");
                    statsBean.setPer_hole_avg(jsonObject_putting.getString("per_hole_avg"));
                    statsBean.setPer_gir_avg(jsonObject_putting.getString("per_gir_avg"));
                    statsBean.setPer_round_avg(jsonObject_putting.getString("per_round_avg"));

                    JSONObject jsonObject_rec = data.getJSONObject("recovery_stats");
                    statsBean.setScrmbl_avg(jsonObject_rec.getString("scrmbl_avg"));
                    statsBean.setSand_avg(jsonObject_rec.getString("sand_avg"));

                    if (mSharedPreferences == null)
                        return;
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("stats", last);
                    editor.commit();

                } else {
                    String errorMessage = jsonObject.getString("message");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            StatsPagerAdapter adapter = new StatsPagerAdapter(getActivity().getSupportFragmentManager(), statsBean);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);


        }


        //averageValue

        //  Log.v("avg",pieDataBean.getAverage());
    }


}
