package com.putt2gether.putt;

/**
 * Created by Ajay on 18/06/2016.
 */

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.putt2gether.R;
import com.putt2gether.bean.LoginBean;
import com.putt2gether.network.GPSTracker;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.network.RefferenceWrapper;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 23/05/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText inputName, inputEmail, inputPassword;
    private Button btnLogin;
    private RelativeLayout parent_id;
    private SharedPreferences mSharedPreferences;
    private LinearLayout forgotPassword;
    private CallbackManager callbackManager;
    private GPSTracker gpsTracker;
    private double latitude;
    private double longitude;
    private RelativeLayout facebookLoginBTN;
    private String deviceToken;
    private RefferenceWrapper refferenceWrapper;
    private Constant constant;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    Typeface Lato_Bold;
    Typeface Lato_Regular;
    String id;
    String redirectID;
    String cacheEmail;
    String cachePassword;
    ProgressDialog fbDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.login_activity);

        refferenceWrapper = RefferenceWrapper.getReferanceWapper(this);
        SharedPreferences pSharedPreferences = getSharedPreferences("permanent_preference", Context.MODE_PRIVATE);
        cacheEmail = pSharedPreferences.getString("email",null);
        cachePassword = pSharedPreferences.getString("password",null);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = preferences.getString("registration_id", null);
        refferenceWrapper.getDeviceInformationBean().setDeviceToken(token);

        Log.v("tadyaoidyoaid", token + "vis");

        constant = new Constant();

        Lato_Bold = Typeface.createFromAsset(getAssets(), constant.Lato_Bold);
        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);

        if (checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }

        gpsTracker = new GPSTracker(this);

        if (gpsTracker.canGetLocation()) {

            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            // Toast.makeText(getApplicationContext(),"latitude:\n "+latitude+" longitude: \n"+longitude,Toast.LENGTH_LONG).show();

        } else {

            gpsTracker.showSettingsAlert();
        }


        callbackManager = CallbackManager.Factory.create();
        getFbKeyHash(getPackageName());


        facebookLoginBTN = (RelativeLayout) findViewById(R.id.facebook_login_btn);

        facebookLoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean resultLocation = Utility.checkPermissionLocation(LoginActivity.this);
                if (resultLocation) {
                    facebookLoginTask();
                }
            }
        });

        forgotPassword = (LinearLayout) findViewById(R.id.forgotPass_Btn);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean resultLocation = Utility.checkPermissionLocation(LoginActivity.this);
                if (resultLocation) {
                    Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                    startActivity(intent);
                }
            }
        });


        parent_id = (RelativeLayout) findViewById(R.id.parent_id);
        setupUI(parent_id);

        LinearLayout signup = (LinearLayout) findViewById(R.id.signup_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean resultLocation = Utility.checkPermissionLocation(LoginActivity.this);
                if (resultLocation) {
                    Intent in = new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(in);
                }
            }
        });

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        inputEmail.setTypeface(Lato_Regular);
        btnLogin.setTypeface(Lato_Regular);

        inputPassword.setTypeface(Lato_Regular);

        if (cacheEmail!=null && cacheEmail.length()>0){
            inputEmail.setText(cacheEmail);
            inputEmail.setSelection(cacheEmail.length());
        }
        if (cachePassword!=null && cachePassword.length()>0){
            inputPassword.setText(cachePassword);
            inputPassword.setSelection(inputPassword.length());
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean resultLocation = Utility.checkPermissionLocation(LoginActivity.this);
                if (resultLocation) {


                    String d_Token = refferenceWrapper.getDeviceInformationBean().getDeviceToken();

                    if (d_Token != null) {

                        //  doLoginTask();

                    } else {
                        Toast.makeText(getApplicationContext(), "Device Token Not Found", Toast.LENGTH_SHORT).show();
                    }
                    doLoginTask();
                }else {

                  //  Toast.makeText(getApplicationContext(),"Please Enable Location",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LoginActivity.this);
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

    private void doLoginTask() {

        String strEmail = inputEmail.getText().toString();
        String strPassword = inputPassword.getText().toString();
        if (strEmail.length() == 0) {
            inputEmail.setError("Email Not Valid");
        } else if (!Utility.isEmailValid(strEmail)) {
            inputEmail.setError(getString(R.string.invalid_email));
        } else if (strPassword.length() == 0) {
            inputPassword.setError(getString(R.string.password_empty));
        } else {

            JSONObject jsonObject = null;
            deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();

            try {
                jsonObject = new JSONObject();
                jsonObject.putOpt("email", strEmail);
                jsonObject.putOpt("password", strPassword);
                jsonObject.putOpt("device_token", deviceToken);
                jsonObject.putOpt("device_os", "2");
                jsonObject.putOpt("version", "2");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);

            SharedPreferences pSharedPreferences = getSharedPreferences("permanent_preference", Context.MODE_PRIVATE);

            if (pSharedPreferences == null)
                return;
            SharedPreferences.Editor editor = pSharedPreferences.edit();
            editor.putString("email", strEmail);
            editor.putString("password", strPassword);


            editor.commit();

            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.USER_LOGIN_URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.e("LoginPage", "OnResponse =" + response.toString());
                    getLogin(response);
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
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Utility.showLogError(LoginActivity.this, "GetLoginList URL = " + PUTTAPI.USER_LOGIN_URL);

            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        }

    }

    public void getLogin(JSONObject response) {
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
                        loginBean.setFull_name(jsonArray.getJSONObject(i).getString("full_name"));
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
                    //  Toast.makeText(getApplicationContext(), "Login Successful " + loginBean.getFull_name(), Toast.LENGTH_LONG).show();

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

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("fromEventPreview", "0");
                    startActivity(intent);
                    finish();

                } else {
                    JSONObject jsonObject = response.getJSONObject("Error");
                    String stringError = jsonObject.getString("msg");
                    Toast.makeText(getApplicationContext(), stringError, Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    private void facebookLoginTask() {


        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends", "email", "user_location", "user_birthday"));

        fbDialog = new ProgressDialog(LoginActivity.this);
        fbDialog.setMessage("Login via Facebook please wait...");

        fbDialog.setCancelable(false);
        fbDialog.setCanceledOnTouchOutside(false);

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {



            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.v("Facebook Success","fvsdfvds");

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        fbDialog.cancel();

                        Log.e("Facebook Login ", "URL = www.mfacebook.com/vishal.tripathi.90");
                        if (response.getError() != null) {
                            Toast.makeText(LoginActivity.this, "Sorry!! can't connect to Facebook. Try later", Toast.LENGTH_SHORT).show();
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

                             //   String st = urlToBase64(url);

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

                    fbDialog.cancel();
                }
            }

            @Override
            public void onCancel() {
                Log.e("cancel by user", "= = ");

                fbDialog.cancel();
            }
        });
    }

    private JSONObject postJsonObject(String full_name, String email, String fb_id, String url) {


        deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();

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

        Log.v("facebook post data",jsonObject.toString()+"");

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
        fbDialog.show();

        Log.i("Facebook Login", "OnActivityResult..."+data.toString());
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
                        loginBean.setPhotoURL(jsonArray.getJSONObject(i).getString("photo_url").trim());
                        loginBean.setSelf_handicap(jsonArray.getJSONObject(i).getString("self_handicap"));

                        Log.v("fbImage",jsonArray.getJSONObject(i).getString("photo_url").trim());

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

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("fromEventPreview", "0");
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    } else {
                        Intent intent = new Intent(LoginActivity.this, FacebookUpdateProfile.class);
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

    private boolean checkAndRequestPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissionsNeeded = new ArrayList<String>();
            final List<String> permissionsList = new ArrayList<String>();
            if (!addPermission(permissionsList, android.Manifest.permission.CAMERA))
                permissionsNeeded.add("CAMERA");

            if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                permissionsNeeded.add("EXTERNAL");
            if (!addPermission(permissionsList, android.Manifest.permission.ACCESS_FINE_LOCATION))
                permissionsNeeded.add("GPS");
            if (!addPermission(permissionsList, Manifest.permission.READ_CONTACTS))
                permissionsNeeded.add("READ CONTACT");

            if (!permissionsNeeded.isEmpty()) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);

                return false;
            }

        } else {
            // Pre-Marshmallow
        }


        return true;

    }


    private boolean addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                // Check for Rationale Option
                if (!shouldShowRequestPermissionRationale(permission))
                    return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(android.Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_CONTACTS, PackageManager.PERMISSION_GRANTED);

                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && perms.get(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && perms.get(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
                        ) {
                    // All Permissions Granted
                    // insertDummyContact();
                } else {
                    // Permission Denied

                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

   /* public String urlToBase64(String img) throws IOException {

        URL img_value = new URL(img);
        String encoded_image = null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap mIcon1 = BitmapFactory.decodeStream(img_value.openConnection().getInputStream(), null, options);
        Log.d("taking", "3" + img_value);
        Log.d("taking", "3" + mIcon1);
        Log.d("taking", String.valueOf(mIcon1));

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        mIcon1.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        byte [] ba = bao.toByteArray();
        encoded_image =Base64.encodeToString(ba,Base64.DEFAULT);

        return encoded_image;
    }
*/

}
