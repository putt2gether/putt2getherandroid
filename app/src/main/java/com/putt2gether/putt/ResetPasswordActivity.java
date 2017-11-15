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
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Constant;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;


/**
 * Created by Ajay on 18/06/2016.
 */
public class ResetPasswordActivity extends AppCompatActivity {

    private EditText inputPassword;
    private EditText inputRePassword;
    private LinearLayout backToSignUp;

    private Button resetPasswordBtn;

    /** Declaration of Constant **/
    Constant constant;

    /** Declaration of Typeface **/

    Typeface Lato_Regular;
    private LinearLayout backToLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password_activity);

        constant = new Constant();

        constant = new Constant();

        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);

        iniUI();

        /** Initialization of addListener() **/
        addListener();

    }
    public void iniUI(){

        backToSignUp = (LinearLayout)findViewById(R.id.signup_btn);
        backToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(in);
                finish();
            }
        });

        inputPassword = (EditText)findViewById(R.id.input_pass_forgot);
        inputRePassword = (EditText)findViewById(R.id.input_REpass_forgot);

        inputPassword.setTypeface(Lato_Regular);
        inputRePassword.setTypeface(Lato_Regular);

        resetPasswordBtn = (Button)findViewById(R.id.submit_pass_btn);
        resetPasswordBtn.setTypeface(Lato_Regular);

        backToLogin = (LinearLayout)findViewById(R.id.back_lay_forgot);
        backToLogin.setOnClickListener(new View.OnClickListener() {
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

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doResetTask();
            }
        });
    }

    private void doResetTask() {


        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = getIntent().getStringExtra("user_id");

        String otpSTR = getIntent().getStringExtra("otp");

        String strPass = inputPassword.getText().toString();
        String strRePass = inputRePassword.getText().toString();
        if (strPass.length() == 0) {
            inputPassword.setError("Password can not be empty");
        } else if (!strPass.equalsIgnoreCase(strRePass)) {
            inputRePassword.setError("Please enter same password");
        } else {

            JSONObject jsonObject = null;
            //String deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();
            // Double latitude = refferenceWrapper.getDeviceInformationBean().getLatitude();
            // Double longitude = refferenceWrapper.getDeviceInformationBean().getLongitude();

            //   Toast.makeText(getApplicationContext(),"Device Token = "+deviceToken +"\n" +"Latitude = "+latitude+
            //   "\n"+"Longitude="+longitude,Toast.LENGTH_LONG).show();


            try {
                jsonObject = new JSONObject();
                jsonObject.putOpt("user_id",user_ID);
                jsonObject.putOpt("otp",otpSTR);

                jsonObject.putOpt("new_password", strPass);
                jsonObject.putOpt("confirm_password", strPass);
                jsonObject.putOpt("version","2");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();

            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.RESET_PASSWORD_URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    pDialog.dismiss();
                    Log.e("RESET Password Activity", "OnResponse =" + response.toString());
                    getForgot(response,user_ID);

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
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Log.e("RESET Password", "Url= " + PUTTAPI.FORGOT_PASSWORD_URL + " PostObject = " + jsonObject.toString());
            //requestQueue.add(jsonObjectRequest);
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        }

    }

    public void getForgot(JSONObject response,String user_id) {
        //  mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);



        if (response != null){
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")){

                    String responseEmail = jsonObject.getString("message");

                    Toast.makeText(getApplicationContext(),responseEmail,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);

                    intent.putExtra("user_id",user_id);
                    startActivity(intent);
                    finish();
                }else{
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();

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