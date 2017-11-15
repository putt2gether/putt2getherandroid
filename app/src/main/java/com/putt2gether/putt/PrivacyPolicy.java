package com.putt2gether.putt;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
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
 * Created by Ajay on 03/03/2017.
 */
public class PrivacyPolicy extends AppCompatActivity {

    TextView textView;
    WebView webView;
    ImageView backBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy_activity);

        textView = (TextView) findViewById(R.id.policy_text);
        webView = (WebView) findViewById(R.id.webView_privacy);
        backBTN  = (ImageView)findViewById(R.id.back_privacy_policy);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPrivacyPolicy();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void getPrivacyPolicy(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("version", "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.PRIVACY_POLICY_API, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("Privacy Policy", "OnResponse =" + response.toString());
                getPrivacyPolicyResponse(response);
                pDialog.cancel();

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
        Log.e("Privacy Policy", "Url= " + PUTTAPI.PRIVACY_POLICY_API + " PostObject = " + jsonObject.toString());

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    public void getPrivacyPolicyResponse(JSONObject response) {


        if (response != null) {
            try {
                JSONObject jsonObject = response.getJSONObject("output");

                String privacyPolicy = jsonObject.getString("data");

                Spanned htmlAsSpanned = Html.fromHtml(privacyPolicy);

                textView.setText(htmlAsSpanned);
                webView.loadDataWithBaseURL(null, privacyPolicy, "text/html", "utf-8", null);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

}
