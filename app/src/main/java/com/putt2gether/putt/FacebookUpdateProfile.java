package com.putt2gether.putt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.putt2gether.R;
import com.putt2gether.adapter.HomeGolfAdapter;
import com.putt2gether.bean.CountryBean;
import com.putt2gether.bean.DataBean;
import com.putt2gether.bean.HomeGolfBean;
import com.putt2gether.crop_image.CropImage;
import com.putt2gether.crop_image.InternalStorageContentProvider;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.GPSTracker;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.parser.PuttParser;
import com.putt2gether.utils.Constant;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 03/08/2016.
 */
public class FacebookUpdateProfile extends AppCompatActivity {

    private RelativeLayout parentID;


    EditText country, countryCode;
    EditText mobileNO, password, handicap, inputName, inputEmail;

    private Constant constant;
    Typeface Lato_Regular;
    private RelativeLayout saveBTN;
    private Double latitude;
    private Double longitude;
    private GPSTracker gpsTracker;
    private PuttParser parser;
    private String autoCountry;
    private Spinner countrySpinner;
    private ArrayList<CountryBean> countryList;
    private String countryArr[];

    private String country_id;
    private String country_code;
    private String country_name;
    int handicap1;
    Bitmap photo;
    private String strImage;
    private ImageView editProfileImage;


    private Uri mImageCaptureUri;
    private AlertDialog dialog;

    public static final String TEMP_PHOTO_FILE_NAME = "temp_image.jpg";

    public static final int REQUEST_CODE_GALLERY = 0x1;
    public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;
    public static final int REQUEST_CODE_CROP_IMAGE = 0x3;

    private File mFileTemp;

    Bitmap bitmapFB;
    AutoCompleteTextView homeGolf;
    private String homeGolfId;
    ArrayList<HomeGolfBean> homeGolfList;
    HomeGolfAdapter adapterGolfList;

    String user_ID;
    String emailID;
    String user_handicap;
    String user_image;
    String displayName;
    ImageView backBTN;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_your_profile);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(this.getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }

        homeGolf = (AutoCompleteTextView) findViewById(R.id.fb_edit_homegolf);
        homeGolf.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {

                HomeGolfBean frnd = (HomeGolfBean) parent.getItemAtPosition(pos);
                Log.v("Golf Name name", frnd.getGolfName() + "" + frnd.getGolfID());

                homeGolfId = frnd.getGolfID();
                homeGolf.setText(frnd.getGolfName().toUpperCase());


            }
        });

        backBTN = (ImageView)findViewById(R.id.back_tab_comp);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        parentID = (RelativeLayout) findViewById(R.id.parent);
        setupUI(parentID);
        ConnectionDetector connectionDetector = new ConnectionDetector(this);

        captureImageInitialization();
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
            Log.v("this ", "No Internet Access");
        }
        Log.e("latLong", String.valueOf(latitude));


        constant = new Constant();
        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Bold);

        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        user_ID = getIntent().getStringExtra("userId");
        emailID = getIntent().getStringExtra("emailID");
        user_handicap = getIntent().getStringExtra("user_handicap");
        user_image = getIntent().getStringExtra("user_image");
        displayName = getIntent().getStringExtra("displayName");


        inputEmail = (EditText) findViewById(R.id.fb_edit_email);
        countrySpinner = (Spinner) findViewById(R.id.fb_spinner_profile_city);
        inputName = (EditText) findViewById(R.id.fb_name_edit);
        handicap = (EditText) findViewById(R.id.fb_edit_handicap);
        mobileNO = (EditText) findViewById(R.id.fb_input_mobile);
        countryCode = (EditText) findViewById(R.id.fb_input_mobile_code);
        password = (EditText) findViewById(R.id.fb_edit_password);
        country = (EditText) findViewById(R.id.fb_country_edit);
        saveBTN = (RelativeLayout) findViewById(R.id.fb_profile_save);
        editProfileImage = (ImageView) findViewById(R.id.fb_profile_picture);

        inputEmail.setText(emailID);
        inputName.setText(displayName.toUpperCase());
        if (user_image != null) {
            Picasso.with(this).load(user_image).into(editProfileImage);
        }


        RelativeLayout editImag = (RelativeLayout) findViewById(R.id.fb_edit_profile_lay);
        editImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

            }
        });
        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProfileSubmit();
            }
        });


        inputEmail.setTypeface(Lato_Regular);

        inputName.setTypeface(Lato_Regular);
        handicap.setTypeface(Lato_Regular);
        mobileNO.setTypeface(Lato_Regular);
        countryCode.setTypeface(Lato_Regular);
        country.setTypeface(Lato_Regular);
        password.setTypeface(Lato_Regular);
        homeGolf.setTypeface(Lato_Regular);


        inputEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    inputEmail.setHint("");
                else
                    inputEmail.setHint("ENTER NAME");
            }
        });

        inputName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    inputName.setHint("");
                else
                    inputName.setHint("ENTER NAME");
            }
        });

        handicap.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    handicap.setHint("");
                else
                    handicap.setHint("HANDICAP");
            }
        });

        mobileNO.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    mobileNO.setHint("");
                else
                    mobileNO.setHint("ENTER MOBILE No.");
            }
        });


        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    password.setHint("");
                else
                    password.setHint("*******");
            }
        });

    }

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(FacebookUpdateProfile.this);
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
        pDialog.show();
        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        final String user_ID = pref.getString("userId", null);
        final String accessToken = pref.getString("authorization_key", null);
        jsonObject = new JSONObject();
        // jsonObject.putOpt("user_id", user_ID);
        // jsonObject.putOpt("access_token", accessToken);
        try {
            jsonObject.putOpt("show_all", "1");
            jsonObject.putOpt("version", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.COUNTRY_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Utility.showLogError(FacebookUpdateProfile.this, "Get Country ListResponse" + response.toString());
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
                ArrayAdapter<String> eventTypeAdapter = new ArrayAdapter<String>(FacebookUpdateProfile.this, R.layout.country_spinner_item, countryArr);
                eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // event.setAdapter(new NothingSelectedSpinnerAdapter(eventTypeAdapter, R.layout.spinner_row_nothing_selected, MainActivity.this));
                //event.setPrompt("Select");
                countrySpinner.setAdapter(eventTypeAdapter);
                //countryET.setThreshold(1);
                String myString = autoCountry; //the value you want the position for

                ArrayAdapter myAdap = (ArrayAdapter) countrySpinner.getAdapter(); //cast to an ArrayAdapter

                int spinnerPosition = myAdap.getPosition(myString);

//set the default according to value
                countrySpinner.setSelection(spinnerPosition);

              /*  for (int i = 0 ; i< countryArr.length ; i++){
                    if (autoCountry.equalsIgnoreCase(countryArr[i])){
                        countryET.setText(autoCountry);
                    }
                }  */
                // eventSpinner.setAdapter(eventTypeAdapter);

                final DataBean finalBean = bean;
                countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        country_id = finalBean.getCountryList().get(position).getCountry_id();
                        country_code = finalBean.getCountryList().get(position).getPhonecode();
                        country_name = finalBean.getCountryList().get(position).getCountry_name();

                        Log.e("Country Code", country_code);
                        countryCode.setText("+" + country_code);
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
                Utility.showToastMsg(FacebookUpdateProfile.this, error.getMessage());
            }
        });
        Utility.showLogError(FacebookUpdateProfile.this, "GetCountryList URL = " + PUTTAPI.COUNTRY_LIST_URL);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void doProfileSubmit() {
        String strName = inputName.getText().toString();
        String strHandicap = handicap.getText().toString();
        if (strHandicap.length() != 0) {
            handicap1 = Integer.parseInt(strHandicap);
        }
        // String strCountry = countryET.getText().toString();
        String strCODE = countryCode.getText().toString();
        String strMobile = mobileNO.getText().toString();
        String strCountry = country_name;

        String strPassword = password.getText().toString();
        String strEmail = inputEmail.getText().toString();


        if (strName.length() == 0) {
            inputName.setError(getString(R.string.empty_name));
        } else if (strEmail.length() == 0) {
            inputEmail.setError(getString(R.string.invalid_email));
        } else if (!Utility.isEmailValid(strEmail)) {
            inputEmail.setError(getString(R.string.invalid_email));
        } else if (strCODE.length() == 0) {
            countryCode.setError(getString(R.string.empty_code));
        } else if (strMobile.length() < 8) {
            mobileNO.setError(getString(R.string.empty_mobile));
        } else if (strPassword.length() == 0) {
            password.setError(getString(R.string.password_empty));
        } else if (strPassword.length() < 6) {
            password.setError(getString(R.string.password_empty));
        } else if (handicap1 > 30) {
            handicap.setError(getString(R.string.empty_handicap));
        } else if (strHandicap.length() == 0) {
            handicap.setError(getString(R.string.empty_handicap));
        } else {
            inputName.setError(null);
            countryCode.setError(null);
            mobileNO.setError(null);
            password.setError(null);
            handicap.setError(null);


            // String deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();

            JSONObject jsonObject = null;


            BitmapDrawable dd = (BitmapDrawable) editProfileImage.getDrawable();
            if (dd != null) {

                bitmapFB = dd.getBitmap();
                strImage = getStringImage(bitmapFB);
            } else if (photo != null) {
                strImage = getStringImage(photo);
            } else {
                strImage = null;
            }

            try {
                jsonObject = new JSONObject();
                jsonObject.putOpt("user_id", user_ID);
                jsonObject.putOpt("full_name", strName);
                jsonObject.putOpt("email", strEmail);

                jsonObject.putOpt("contact_number", strMobile);
                jsonObject.putOpt("country_code", strCODE);
                jsonObject.putOpt("country", strCountry);

                jsonObject.putOpt("password", strPassword);
                jsonObject.putOpt("handicap_value", strHandicap);
                jsonObject.putOpt("version", "2");

                jsonObject.putOpt("profile_image", strImage);
                jsonObject.putOpt("golf_course_id", homeGolfId);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

            Log.v("post_facebook", jsonObject.toString());


            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.UPDATE_USER_PROFILE, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.e("Sign Up", "OnResponse =" + response.toString());
                    getSignUpResponse(response);
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
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Log.e("Sign Up", "Url= " + PUTTAPI.UPDATE_USER_PROFILE + " PostObject = " + jsonObject.toString());
            //requestQueue.add(jsonObjectRequest);
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        }

    }

    private void getSignUpResponse(JSONObject response) {

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")) {

                    String responseEmail = jsonObject.getString("message");
                    String fbID = jsonObject.getString("facebook_id");

                    SharedPreferences mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                    SharedPreferences mSharedPreferences1 = getSharedPreferences("user_fb", Context.MODE_PRIVATE);

                    if (mSharedPreferences == null)
                        return;
                    SharedPreferences.Editor editor = mSharedPreferences.edit();


                    SharedPreferences.Editor editor1 = mSharedPreferences1.edit();

                    String strName = inputName.getText().toString();
                    String strHandicap = handicap.getText().toString();
                    String strEmail = inputEmail.getText().toString();

                    editor.putString("userId", user_ID);
                    editor.putString("displayName", strName);
                    editor.putString("user_handicap", strHandicap);
                    editor.putString("user_image", user_image);
                    editor.putString("emailID", strEmail);
                    editor1.putString("fbID", fbID);
                    //editor.putString("authorization_key",loginBean.getAuthorization_key());
                    editor.commit();
                    editor1.commit();


                    Toast.makeText(getApplicationContext(), responseEmail, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(FacebookUpdateProfile.this, HomeActivity.class);
                    intent.putExtra("fromEventPreview", "0");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                } else {
                    String errorMessage = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void captureImageInitialization() {
        final String[] items = new String[]{"Take from camera", "Select from gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FacebookUpdateProfile.this, R.layout.select_dialog_row, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(FacebookUpdateProfile.this, R.style.MyDialogTheme);

        builder.setTitle(Html.fromHtml("<font color='#FFFFFF'>SELECT IMAGE</font>"));

        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) { // pick from
                // camera
                if (item == 0) {

                    boolean resultCam = Utility.checkPermissionCamera(FacebookUpdateProfile.this);
                    if (resultCam) {
                        takePicture();
                    }

                } else {

                    boolean resultCam = Utility.checkPermissionGallery(FacebookUpdateProfile.this);
                    if (resultCam) {
                        openGallery();
                    }

                }
            }
        });

        dialog = builder.create();
    }


    private void takePicture() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            Uri mImageCaptureUri = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                mImageCaptureUri = Uri.fromFile(mFileTemp);
            } else {
                /*
                 * The solution is taken from here: http://stackoverflow.com/questions/10042695/how-to-get-camera-result-as-a-uri-in-data-folder
	        	 */
                mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI;
            }
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
        } catch (ActivityNotFoundException e) {

            Log.d("TAG", "cannot take picture", e);
        }
    }

    private void openGallery() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
    }

    private void startCropImage() {

        Intent intent = new Intent(this, CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, mFileTemp.getPath());
        intent.putExtra(CropImage.SCALE, true);

        intent.putExtra(CropImage.ASPECT_X, 3);
        intent.putExtra(CropImage.ASPECT_Y, 3);

        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {

            return;
        }

        photo = null;

        switch (requestCode) {

            case REQUEST_CODE_GALLERY:

                try {

                    InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(mFileTemp);
                    copyStream(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();

                    startCropImage();

                } catch (Exception e) {

                    Log.e("TAG", "Error while creating temp file", e);
                }

                break;
            case REQUEST_CODE_TAKE_PICTURE:

                startCropImage();
                break;
            case REQUEST_CODE_CROP_IMAGE:

                String path = data.getStringExtra(CropImage.IMAGE_PATH);
                if (path == null) {

                    return;
                }

                photo = BitmapFactory.decodeFile(mFileTemp.getPath());
                editProfileImage.setImageBitmap(photo);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public static void copyStream(InputStream input, OutputStream output)
            throws IOException {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
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
                Utility.showLogError(FacebookUpdateProfile.this, "Home Golf Response" + response.toString());
                getHomeGolfResponse(response);
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();

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


}
