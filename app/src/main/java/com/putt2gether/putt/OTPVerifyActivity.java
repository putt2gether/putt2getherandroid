package com.putt2gether.putt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.putt2gether.bean.LoginBean;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Constant;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 06/07/2016.
 */
public class OTPVerifyActivity extends AppCompatActivity {

    private EditText otpEdit;
    private Button otpBtn;
    private LinearLayout backToReset;
    String strOTP;
    private LinearLayout resendOTP;

    private Constant constant;

    Typeface Lato_Regular;
    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verification);
        /** Initialization of iniUI() **/
        iniUI();

        /** Initialization of addListener() **/
        addListener();

    }
    public void iniUI(){

        constant = new Constant();

        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);

        otpEdit = (EditText)findViewById(R.id.input_OTP);
        otpBtn = (Button)findViewById(R.id.otp_verify_btn);

        otpEdit.setTypeface(Lato_Regular);
        otpBtn.setTypeface(Lato_Regular);

        resendOTP = (LinearLayout)findViewById(R.id.resend_lay_otp);

        backToReset = (LinearLayout)findViewById(R.id.back_lay_otp);
        backToReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //  emailEdit.setTypeface(regularTF);
        //  resetPasswordBtn.setTypeface(boldTF);

    }

    /*******************************
     * Here we use addListener() Method
     *******/
    public void addListener(){

        otpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOTPVerifyTask();
                //Intent intent = new Intent(getApplicationContext(),ResetPasswordActivity.class);
                // startActivity(intent);
            }
        });

        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendOTPtask();
            }
        });
    }

    private void doOTPVerifyTask() {


        final String user_ID = getIntent().getStringExtra("user_id");

        strOTP = otpEdit.getText().toString();
        if (strOTP.length() == 0) {
            otpEdit.setError("OTP Not Valid");
        }  else {

            JSONObject jsonObject = null;
            //String deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();
            // Double latitude = refferenceWrapper.getDeviceInformationBean().getLatitude();
            // Double longitude = refferenceWrapper.getDeviceInformationBean().getLongitude();

            //   Toast.makeText(getApplicationContext(),"Device Token = "+deviceToken +"\n" +"Latitude = "+latitude+
            //   "\n"+"Longitude="+longitude,Toast.LENGTH_LONG).show();


            try {
                jsonObject = new JSONObject();
                jsonObject.putOpt("user_id",user_ID);
                jsonObject.putOpt("otp", strOTP);
                jsonObject.putOpt("version","2");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();

            Log.v("OTP",user_ID+strOTP);
            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.OTP_VERIFY_URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.e("OTPVErify Activity", "OnResponse =" + response.toString());
                    getOTP(response,user_ID);
                    pDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(getApplicationContext(), "error_network_timeout",
                                Toast.LENGTH_LONG).show();
                    } else if (error instanceof AuthFailureError) {
                        //TODO
                        Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                    } else if (error instanceof ServerError) {
                        //TODO
                        Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_LONG).show();
                    } else if (error instanceof NetworkError) {
                        //TODO
                        Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_LONG).show();
                    } else if (error instanceof ParseError) {
                        //TODO
                        Toast.makeText(getApplicationContext(), "ParseError", Toast.LENGTH_LONG).show();
                    }
                    pDialog.dismiss();

                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Log.e("LoginPage", "Url= " + PUTTAPI.OTP_VERIFY_URL + " PostObject = " + jsonObject.toString());
            //requestQueue.add(jsonObjectRequest);
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        }

    }

    public void getOTP(JSONObject response,String userID) {
        //  mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        LoginBean loginBean = new LoginBean();


        if (response != null){
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")){

                    String responseOTP = jsonObject.getString("message");


                    Toast.makeText(getApplicationContext(),responseOTP,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(OTPVerifyActivity.this, ResetPasswordActivity.class);
                    intent.putExtra("otp",strOTP);
                    intent.putExtra("user_id",userID);

                    startActivity(intent);
                }else{
                    String error = jsonObject.getString("error");
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    private void resendOTPtask() {

        String strEmail = getIntent().getStringExtra("email");
        if(strEmail == null) {
          Toast.makeText(getApplicationContext(),"Email not Found",Toast.LENGTH_LONG).show();
        } else {

            JSONObject jsonObject = null;
            //String deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();
            // Double latitude = refferenceWrapper.getDeviceInformationBean().getLatitude();
            // Double longitude = refferenceWrapper.getDeviceInformationBean().getLongitude();

            //   Toast.makeText(getApplicationContext(),"Device Token = "+deviceToken +"\n" +"Latitude = "+latitude+
            //   "\n"+"Longitude="+longitude,Toast.LENGTH_LONG).show();


            try {
                jsonObject = new JSONObject();
                jsonObject.putOpt("email", strEmail);
                jsonObject.putOpt("version","2");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();

            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.FORGOT_PASSWORD_URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.e("Forgot Activity", "OnResponse =" + response.toString());
                    getForgot(response);

                    pDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Something, wrong please try again",Toast.LENGTH_LONG).show();
                    pDialog.dismiss();

                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Log.e("LoginPage", "Url= " + PUTTAPI.FORGOT_PASSWORD_URL + " PostObject = " + jsonObject.toString());
            //requestQueue.add(jsonObjectRequest);
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        }

    }

    public void getForgot(JSONObject response) {
        mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        LoginBean loginBean = new LoginBean();


        if (response != null){
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")){

                    String responseEmail = jsonObject.getString("Success");
                    String user_id = jsonObject.getString("user_id");




                    Toast.makeText(getApplicationContext(),responseEmail,Toast.LENGTH_LONG).show();
                   // Intent intent = new Intent(OTPVerifyActivity.this, OTPVerifyActivity.class);

                    //intent.putExtra("user_id",user_id);
                   // startActivity(intent);
                }else{
                    String error = jsonObject.getString("Error");
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight()
                    || y < w.getTop() || y > w.getBottom()) ) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }

}