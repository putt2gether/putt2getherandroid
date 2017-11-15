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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import com.putt2gether.R;
import com.putt2gether.bean.LoginBean;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 18/06/2016.
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEdit;
    private Button resetPasswordBtn;

    /** Declaration of Constant **/

    private Constant constant;
    Typeface Lato_Bold;
    Typeface Lato_Regular;


    private LinearLayout backToLogin;
    private String strEmail;
    private SharedPreferences mSharedPreferences;
    LinearLayout signUPBTN;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        iniUI();
        addListener();
    }
    public void iniUI(){
        emailEdit = (EditText)findViewById(R.id.input_email_forgot);
        resetPasswordBtn = (Button)findViewById(R.id.get_new_pass_btn);

        backToLogin = (LinearLayout)findViewById(R.id.back_lay_forgot);

        constant = new Constant();

        Lato_Bold = Typeface.createFromAsset(getAssets(), constant.Lato_Bold);

        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);



        emailEdit.setTypeface(Lato_Regular);
        resetPasswordBtn.setTypeface(Lato_Regular);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signUPBTN = (LinearLayout)findViewById(R.id.signup_btn);
        signUPBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(in);
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
               doForgotTask();
                //Intent intent = new Intent(getApplicationContext(),ResetPasswordActivity.class);
               // startActivity(intent);
            }
        });
    }

    private void doForgotTask() {

         strEmail = emailEdit.getText().toString();
        if (strEmail.length() == 0) {
            emailEdit.setError("Email Not Valid");
        } else if (!Utility.isEmailValid(strEmail.trim())) {
            emailEdit.setError("Email Not Valid");
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
                    String responseUserID = jsonObject.getString("user_id");



                    Toast.makeText(getApplicationContext(),responseEmail,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPasswordActivity.this, OTPVerifyActivity.class);
                    intent.putExtra("email",strEmail);
                    intent.putExtra("user_id",responseUserID);

                    //intent.putExtra("user_id",user_id);
                    startActivity(intent);
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