package com.putt2gether.putt;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.putt2gether.R;
import com.putt2gether.adapter.HomeGolfAdapter;
import com.putt2gether.bean.CountryBean;
import com.putt2gether.bean.DataBean;
import com.putt2gether.bean.HomeGolfBean;
import com.putt2gether.bean.LoginBean;
import com.putt2gether.bean.SignUpBean;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.GPSTracker;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.network.RefferenceWrapper;
import com.putt2gether.parser.PuttParser;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 18/06/2016.
 */
public class SignUpActivity extends AppCompatActivity {

    private RelativeLayout backToLogin;
    private Button signUP;

    private EditText nameET;
    private EditText emailET;
    private EditText passwordET;
    private EditText handicapET;
    private EditText countryET;
    private AutoCompleteTextView homeGolf;
    ArrayList<HomeGolfBean> homeGolfList;

    HomeGolfAdapter adapterGolfList;

    private EditText mobileET;
    private EditText codeET;
    private RelativeLayout parentID;
    private RefferenceWrapper refferenceWrapper;
    private GPSTracker gpsTracker;
    private Double latitude;
    private Double longitude;
    private String autoCountry;
    private PuttParser parser;
    private DataBean bean;
    private ArrayList<CountryBean> countryList;
    private String countryArr[];
    private Spinner countrySpinner;
    private String country_id;
    private String country_code;
    private String country_name;
    int handicap;
    private Constant constant;
    Typeface Lato_Regular;
    private String homeGolfId;
    private LinearLayout fblogin;
    private SharedPreferences mSharedPreferences;
    private CallbackManager callbackManager;
    private String deviceToken;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    String id;
    String redirectID;


    LinearLayout privacyBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);


        privacyBtn = (LinearLayout) findViewById(R.id.privacy_policyBtn);

        privacyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        ConnectionDetector connectionDetector = new ConnectionDetector(this);
        constant = new Constant();
        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);

        parser = new PuttParser();
        gpsTracker = new GPSTracker(this);

        if (gpsTracker.canGetLocation()) {

            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();

        } else {

            gpsTracker.showSettingsAlert();
        }

        if (connectionDetector.isConnectingToInternet()) {

            Geocoder gcd = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = gcd.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses != null) {
                if (addresses.size() > 0) {
                    autoCountry = addresses.get(0).getCountryName();
                }
            }

            getCountryList();
        } else {
            Toast.makeText(this, "No Internet Access", Toast.LENGTH_SHORT).show();
        }


        Log.e("latLong", String.valueOf(latitude));


        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        getFbKeyHash(getPackageName());


        //autoCountry = "india";
        refferenceWrapper = RefferenceWrapper.getReferanceWapper(this);

        parentID = (RelativeLayout) findViewById(R.id.parent_id);
        setupUI(parentID);


        deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();

        homeGolf = (AutoCompleteTextView) findViewById(R.id.input_home_golf);

        homeGolf.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {

                HomeGolfBean frnd = (HomeGolfBean) parent.getItemAtPosition(pos);
                Log.v("Golf Name name", frnd.getGolfName() + "" + frnd.getGolfID());

                homeGolfId = frnd.getGolfID();
                homeGolf.setText(frnd.getGolfName());


            }
        });


        fblogin = (LinearLayout) findViewById(R.id.bottom);

        fblogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLoginTask();
            }
        });

        nameET = (EditText) findViewById(R.id.input_name);
        emailET = (EditText) findViewById(R.id.input_email);
        passwordET = (EditText) findViewById(R.id.input_password);
        handicapET = (EditText) findViewById(R.id.input_handicap);
        countryET = (EditText) findViewById(R.id.input_country);
        mobileET = (EditText) findViewById(R.id.input_mobile);
        codeET = (EditText) findViewById(R.id.input_mobile_code);
        countrySpinner = (Spinner) findViewById(R.id.spinner_profile_city);
        signUP = (Button) findViewById(R.id.btn_signUp);


        nameET.setTypeface(Lato_Regular);
        emailET.setTypeface(Lato_Regular);
        passwordET.setTypeface(Lato_Regular);
        handicapET.setTypeface(Lato_Regular);
        countryET.setTypeface(Lato_Regular);

        mobileET.setTypeface(Lato_Regular);
        codeET.setTypeface(Lato_Regular);
        // countrySpinner.setTypeface(regularTF);
        handicapET.setTypeface(Lato_Regular);
        signUP.setTypeface(Lato_Regular);

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignUpTask();
            }
        });


        backToLogin = (RelativeLayout) findViewById(R.id.backToLogin);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void doSignUpTask() {

        String strName = nameET.getText().toString();
        String strHandicap = handicapET.getText().toString();
        if (strHandicap.length() != 0) {
            handicap = Integer.parseInt(strHandicap);
        }
        // String strCountry = countryET.getText().toString();
        String strCODE = codeET.getText().toString();
        String strMobile = mobileET.getText().toString();
        String strEmail = emailET.getText().toString();
        String strPassword = passwordET.getText().toString();


        if (strName.length() == 0) {
            nameET.setError(getString(R.string.empty_name));
        } else if (handicap > 30) {
            handicapET.setError(getString(R.string.empty_handicap));
        } else if (strHandicap.length() == 0) {
            handicapET.setError(getString(R.string.empty_handicap));
        } else if (strCODE.length() == 0) {
            codeET.setError(getString(R.string.empty_code));
        } else if (strMobile.length() < 8) {
            mobileET.setError(getString(R.string.empty_mobile));
        } else if (strEmail.length() == 0) {
            emailET.setError(getString(R.string.invalid_email));
        } else if (!Utility.isEmailValid(strEmail)) {
            emailET.setError(getString(R.string.invalid_email));
        } else if (strPassword.length() < 6) {
            passwordET.setError(getString(R.string.password_empty));
        } else {
            nameET.setError(null);
            handicapET.setError(null);
            countryET.setError(null);
            codeET.setError(null);
            mobileET.setError(null);
            emailET.setError(null);
            passwordET.setError(null);
            //  phone_noView.setError(null);

            String deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();

            JSONObject jsonObject = null;


            try {
                jsonObject = new JSONObject();
                jsonObject.putOpt("email", strEmail);
                jsonObject.putOpt("password", strPassword);
                jsonObject.putOpt("fullname", strName);
                jsonObject.putOpt("handicap", strHandicap);
                jsonObject.putOpt("token", "");
                jsonObject.putOpt("device_token", deviceToken);
                jsonObject.putOpt("device_os", "2");
                jsonObject.putOpt("country", country_name);
                jsonObject.putOpt("country_code", strCODE);
                jsonObject.putOpt("phone", strMobile);
                jsonObject.putOpt("golf_course_id", homeGolfId);

                jsonObject.putOpt("version", "2");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);

            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.SIGN_UP_URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    pDialog.dismiss();
                    Log.e("Sign Up", "OnResponse =" + response.toString());
                    getSignUpResponse(response);

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
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Log.e("Sign Up", "Url= " + PUTTAPI.SIGN_UP_URL + " PostObject = " + jsonObject.toString());
            //requestQueue.add(jsonObjectRequest);
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        }

    }

    private void getSignUpResponse(JSONObject response) {

        if (response != null) {
            try {

                if (response.has("User")) {
                    SignUpBean signUpBean = new SignUpBean();
                    JSONObject jsonObject1 = response.getJSONObject("User");
                    signUpBean.setUser_id(jsonObject1.getString("user_id"));
                    signUpBean.setPhoto_url(jsonObject1.getString("photo_url"));
                    signUpBean.setMsg(jsonObject1.getString("message"));
                    signUpBean.setAccess_token(jsonObject1.getString("token"));
                    Toast.makeText(getApplicationContext(), "You have successfully registered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    JSONObject jsonObject1 = response.getJSONObject("Error");
                    String msg = jsonObject1.getString("message");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(SignUpActivity.this);
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
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    private void getCountryList() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        final String user_ID = pref.getString("userId", null);
        final String accessToken = pref.getString("authorization_key", null);
        jsonObject = new JSONObject();
        // jsonObject.putOpt("user_id", user_ID);
        // jsonObject.putOpt("access_token", accessToken);
        try {
            jsonObject.putOpt("show_all","1");
            jsonObject.putOpt("version", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.COUNTRY_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Utility.showLogError(SignUpActivity.this, "Get Country ListResponse" + response.toString());
                DataBean bean = new DataBean();
                bean = parser.getCountryList(response);
                countryList = new ArrayList<CountryBean>();
                countryList = bean.getCountryList();
                countryArr = new String[countryList.size()];
                for (int i = 0; i < countryList.size(); i++) {
                    countryArr[i] = countryList.get(i).getCountry_name();
                }

                // hiding progressDialog
                pDialog.cancel();
                ArrayAdapter<String> eventTypeAdapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_item_black, countryArr);
                eventTypeAdapter.setDropDownViewResource(R.layout.simple_spinner_country_dropdown);
                // event.setAdapter(new NothingSelectedSpinnerAdapter(eventTypeAdapter, R.layout.spinner_row_nothing_selected, MainActivity.this));
                //event.setPrompt("Select");
                countrySpinner.setAdapter(eventTypeAdapter);
                //countryET.setThreshold(1);
                String myString = autoCountry; //the value you want the position for

                ArrayAdapter myAdap = (ArrayAdapter) countrySpinner.getAdapter(); //cast to an ArrayAdapter

                int spinnerPosition = myAdap.getPosition(myString);

                countrySpinner.setSelection(spinnerPosition);

                final DataBean finalBean = bean;
                countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        country_id = finalBean.getCountryList().get(position).getCountry_id();
                        country_code = finalBean.getCountryList().get(position).getPhonecode();
                        country_name = finalBean.getCountryList().get(position).getCountry_name();

                        Log.e("Country Code", country_code);
                        codeET.setText("+" + country_code);

                        getGolfCourseList(country_id);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
                Utility.showToastMsg(SignUpActivity.this, error.getMessage());

                refreshPermission();
            }
        });
        Utility.showLogError(SignUpActivity.this, "GetCountryList URL = " + PUTTAPI.COUNTRY_LIST_URL);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void getGolfCourseList(String countryID) {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCancelable(false);
        pDialog.show();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("country_id", countryID);
            jsonObject.putOpt("version", "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.HOME_GOLF_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Utility.showLogError(SignUpActivity.this, "Home Golf Response" + response.toString());
                getHomeGolfResponse(response);
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Utility.showLogError(this, "Home Golf URL = " + PUTTAPI.HOME_GOLF_URL);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void getHomeGolfResponse(JSONObject response) {

        homeGolfList = new ArrayList<HomeGolfBean>();
        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        HomeGolfBean typeBean = new HomeGolfBean();

                        typeBean.setGolfName(jsonArray.getJSONObject(i).getString("golf_course_name"));
                        typeBean.setGolfID(jsonArray.getJSONObject(i).getString("golf_course_id"));

                        homeGolfList.add(typeBean);
                    }
                } else {

                    String errorMSG = jsonObject.getString("message");
                    Log.e("Error Message", errorMSG);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (homeGolfList.size() > 0) {
            adapterGolfList = new HomeGolfAdapter(this, homeGolfList);
            homeGolf.setThreshold(2);
            homeGolf.setAdapter(adapterGolfList);
            homeGolf.setTextColor(Color.BLACK);
        }
    }


    private void facebookLoginTask() {


        LoginManager.getInstance().logInWithReadPermissions(SignUpActivity.this,
                Arrays.asList("public_profile", "user_friends", "email", "user_location", "user_birthday"));

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("Facebook Login ", "URL = www.mfacebook.com/vishal.tripathi.90");
                        if (response.getError() != null) {
                            Toast.makeText(SignUpActivity.this, "Sorry!! can't connect to Facebook. Try later", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = object.getJSONObject("picture");
                                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                                String url = jsonObject1.optString("url");

                                String email = object.optString("email");
                                id = object.optString("id");

                                String first_name = object.optString("first_name");
                                String last_name = object.optString("last_name");
                                String full_name = first_name + " " + last_name;

                                Log.e("Image", url + "hfgf" + object);

                                loginBySocialSites(postJsonObject(full_name, email, id, url));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location ,picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onError(FacebookException e) {
                if (e instanceof FacebookAuthorizationException) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        LoginManager.getInstance().logOut();
                    }
                }
            }

            @Override
            public void onCancel() {
                Log.e("cancel by user", "= = ");
            }
        });
    }

    private JSONObject postJsonObject(String full_name, String email, String fb_id, String url) {


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("full_name", full_name);
            jsonObject.putOpt("email_id", email);
            jsonObject.putOpt("facebook_id", fb_id);
            jsonObject.putOpt("device_token", deviceToken);
            jsonObject.putOpt("photo_url", url);
            jsonObject.putOpt("device_os", "2");
            jsonObject.putOpt("version", "2");

        } catch (JSONException e)

        {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private void loginBySocialSites(JSONObject jsonObject) {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.SOCIAL_LOGIN_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Facebook Login ", "Response = " + response.toString());

                getfacebookResponse(response);
                pDialog.cancel();

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
                pDialog.cancel();


            }
        });
        //  Log.e("Facebook Login ","URL = "+PUTTAPI.SOCAIL_LOGIN_URL+" PostObject=  "+jsonObject.toString());
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }


    public void getFbKeyHash(String packageName) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("YourKeyHash :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                System.out.println("YourKeyHash: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.i("Facebook Login", "OnActivityResult...");
    }


    public void getfacebookResponse(JSONObject response) {
        mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        LoginBean loginBean = new LoginBean();


        if (response != null) {
            try {

                if (response.has("data")) {

                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONArray jsonArray = jsonObject.getJSONArray("Full Name");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        loginBean.setUser_id(jsonArray.getJSONObject(i).getString("user_id"));
                        loginBean.setUser_name(jsonArray.getJSONObject(i).getString("user_name"));
                        redirectID = jsonArray.getJSONObject(i).getString("new_user");
                        //loginBean.setFull_name(jsonArray.getJSONObject(i).getString("full_name"));
                        loginBean.setDisplay_name(jsonArray.getJSONObject(i).getString("display_name"));
                        loginBean.setPhotoURL(jsonArray.getJSONObject(i).getString("photo_url"));
                        loginBean.setSelf_handicap(jsonArray.getJSONObject(i).getString("self_handicap"));

                    }

                    JSONArray jsonArray1 = jsonObject.getJSONArray("Event");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        // loginBean.setLatestEventId(jsonArray.getJSONObject(i).getString("latest_event_id"));
                        // loginBean.setFormateId(jsonArray.getJSONObject(i).getString("format_id"));

                    }
                    loginBean.setMessage(jsonObject.getString("msg"));
                    // Toast.makeText(getApplicationContext(), "Login Successful " + loginBean.getFull_name(), Toast.LENGTH_LONG).show();

                    if (redirectID != null && redirectID.equalsIgnoreCase("0")) {

                        if (mSharedPreferences == null)
                            return;

                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                        editor.putString("userId", loginBean.getUser_id());
                        editor.putString("displayName", loginBean.getDisplay_name());
                        editor.putString("user_handicap", loginBean.getSelf_handicap());
                        editor.putString("user_image", loginBean.getPhotoURL());
                        editor.putString("emailID", loginBean.getUser_name());
                        //editor.putString("authorization_key",loginBean.getAuthorization_key());
                        editor.commit();

                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                        intent.putExtra("fromEventPreview", "0");
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    } else {
                        Intent intent = new Intent(SignUpActivity.this, FacebookUpdateProfile.class);
                        intent.putExtra("userId", loginBean.getUser_id());
                        intent.putExtra("displayName", loginBean.getDisplay_name());
                        intent.putExtra("user_handicap", loginBean.getSelf_handicap());
                        intent.putExtra("user_image", loginBean.getPhotoURL());
                        intent.putExtra("emailID", loginBean.getUser_name());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                } else {
                    JSONObject jsonObject = response.getJSONObject("Error");
                    String stringError = jsonObject.getString("msg");
                    Toast.makeText(getApplicationContext(), stringError, Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        /*if(mSharedPreferences==null)
            return;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("userId", loginBean.getUser_id());
        editor.putString("displayName",loginBean.getDisplay_name());
        editor.putString("user_handicap",loginBean.getSelf_handicap());
        editor.putString("user_image",loginBean.getPhotoURL());
        editor.putString("emailID",loginBean.getUser_name());
        //editor.putString("authorization_key",loginBean.getAuthorization_key());
        editor.commit();*/


    }





    private void refreshPermission() {


        //  final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        final Dialog dialog1 = new Dialog(this);
        dialog1.setCanceledOnTouchOutside(false);
        Window window = dialog1.getWindow();
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.dimAmount = 0.7f;
        dialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog1.setContentView(R.layout.popup_refresh);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView dialogText =(TextView) dialog1.findViewById(R.id.popup_preview);
        RelativeLayout yesBTN = (RelativeLayout)dialog1.findViewById(R.id.yes_popup);
        RelativeLayout noBTN = (RelativeLayout)dialog1.findViewById(R.id.no_popup);
        dialogText.setText("Something went wrong. Refresh country list" );
        dialog1.show();
        yesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Geocoder gcd = new Geocoder(SignUpActivity.this, Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(latitude, longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addresses != null) {
                    if (addresses.size() > 0) {
                        autoCountry = addresses.get(0).getCountryName();
                    }
                }

                getCountryList();
            }
        });

        noBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog1.cancel();

            }
        });

    }


}
