package com.putt2gether.putt.score_card;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import com.putt2gether.R;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Admin on 02-06-2017.
 */
public class ScoreCardTemplate  extends AppCompatActivity {

    TextView textView;
    WebView webView;
    ImageView backBTN;
    String eventID,playerID;
    RelativeLayout parent;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_activity);

        eventID = getIntent().getStringExtra("event_id");
        playerID = getIntent().getStringExtra("player_id");

        textView = (TextView) findViewById(R.id.sct_text);
        webView = (WebView) findViewById(R.id.scorecard_template);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);

        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        parent = (RelativeLayout)findViewById(R.id.parent_tm);

        backBTN  = (ImageView)findViewById(R.id.back_sc_t);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getScoreCardTemplate();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void getScoreCardTemplate(){

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Fetching Template...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("version", "2");
            jsonObject.put("event_id",eventID);
            jsonObject.put("player_id",playerID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.SCORECARD_TEMPLATE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("score card template", "OnResponse =" + response.toString());
                getPrivacyPolicyResponse(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something, wrong please try again", Toast.LENGTH_LONG).show();
                pDialog.cancel();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("score card template", "Url= " + PUTTAPI.SCORECARD_TEMPLATE + " PostObject = " + jsonObject.toString());

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    public void getPrivacyPolicyResponse(JSONObject response) {


        if (response != null) {
            try {
                JSONObject jsonObject = response.getJSONObject("output");

                String privacyPolicy = jsonObject.getString("message");

                Spanned htmlAsSpanned = Html.fromHtml(privacyPolicy);

                textView.setText(htmlAsSpanned);
                webView.getSettings().setJavaScriptEnabled(false);
                webView.loadDataWithBaseURL(null, privacyPolicy, "text/html", "utf-8", null);


                webView.setWebViewClient(new WebViewClient() {

                    public void onPageFinished(WebView view, String url) {
                        // do your stuff here


                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // Actions to do after 2 seconds
                                parent.setVisibility(View.VISIBLE);
                                pDialog.cancel();
                            }
                        }, 2000);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

}