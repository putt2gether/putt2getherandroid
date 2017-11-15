package com.putt2gether.putt;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.NavDrawerListAdapter;
import com.putt2gether.bean.NavDrawerItem;
import com.putt2gether.bean.PieDataBean;
import com.putt2gether.bean.UserProfileBean;
import com.putt2gether.fragments.EditProfile;
import com.putt2gether.fragments.HomeFragment;
import com.putt2gether.fragments.InviteFragmentMain;
import com.putt2gether.fragments.MyScoreFragments;
import com.putt2gether.fragments.faq.Faq;
import com.putt2gether.fragments.mygroups.MyGroups;
import com.putt2gether.fragments.notification.Notification;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.network.RefferenceWrapper;
import com.putt2gether.putt.stats.Stats;
import com.putt2gether.view.LatoTextView;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;


/**
 * Created by Ajay on 20/06/2016.
 */
public class HomeActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();


    boolean doubleBackToExitPressedOnce = false;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private LinearLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private SharedPreferences mSharedPreferences;
    // SharedPreferences preferences;
    LinearLayout drawerProfile_lay;
    private PieDataBean pieDataBean;
    ImageView imageViewPro;
    private TextView profName;
    private UserProfileBean profileBean;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    private RefferenceWrapper refferenceWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        refferenceWrapper = RefferenceWrapper.getReferanceWapper(this);

        ConnectionDetector con = new ConnectionDetector(this);
        if (con.isConnectingToInternet()) {
            getProfile();

        } else {
            Toast.makeText(getApplicationContext(), "No internet Connections", Toast.LENGTH_SHORT).show();
        }


        // preferences = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        ImageView imageView = (ImageView) toolbar.findViewById(R.id.menu);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT); // set Gravity as per your need
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT); // set Gravity as per your need
                }

            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        drawer = (LinearLayout) findViewById(R.id.drawer_layout_w);

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        profName = (TextView) findViewById(R.id.txtUsername_profile);
        imageViewPro = (ImageView) findViewById(R.id.img_profile);


        drawerProfile_lay = (LinearLayout) findViewById(R.id.profile_drawer_lay);

        drawerProfile_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(9);


            }
        });

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_menu_black_24dp, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        String fromactivity = getIntent().getStringExtra("fromEventPreview");


        if (savedInstanceState == null && (fromactivity == null || fromactivity.equals("0"))) {
            // on first time display view for first nav item

            displayView(0);

        } else if (savedInstanceState == null && fromactivity.equals("1")) {

            displayView(1);
        } else if (savedInstanceState == null && fromactivity.equals("push")) {

            displayView(5);
        }
    }

    /**
     * Slide menu item click listener
     */

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new InviteFragmentMain();
                break;
            case 2:
                fragment = new MyScoreFragments();
                break;
            case 3:
                fragment = new Stats();
                break;
            case 4:
                fragment = new MyGroups();
                break;
            case 5:
                fragment = new Notification();
                break;
            case 6:
                fragment = new Faq();
                break;
            case 7:

                Intent sendIntent;
                sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("application/octet-stream");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@putt2gether.com"});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "putt2gether feedback");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "\n\n" + "-Sent via android device-" + "\r");
                sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("mailto:" + "feedback@putt2gether.com"));
                startActivity(Intent.createChooser(sendIntent, "Send Mail"));


                break;
            case 8:
                //fragment = new WhatsHotFragment();
                logout();
                break;

            case 9:
                fragment = new EditProfile();

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(drawer);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }


    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onBackPressed() {


        /*if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container_body);
        if (f instanceof HomeFragment) {    // do something with f
          //  HomeActivity.this.finish();
        }
        else {
            displayView(0);
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {


            Fragment frg = getSupportFragmentManager().findFragmentById(R.id.container_body);
            if (frg instanceof HomeFragment) {    // do something with f
                //  HomeActivity.this.finish();
                exitMethod();
            } else {

                displayView(0);
            }


        }

    }


    private void exitMethod(){

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("EXIT").
                setMessage("Do You want to exit?").
                setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        finish();
                    }
                }).
                setNegativeButton("NO",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private void logout() {
        //TODO delete cookie from db

        JSONObject jsonObject = null;
        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        String user_ID = pref.getString("userId", null);

        String deviceToken = refferenceWrapper.getDeviceInformationBean().getDeviceToken();


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("device_token", deviceToken);
            jsonObject.putOpt("device_os", "2");
            jsonObject.putOpt("version", "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.LOGOUT_API, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("Logout", "OnResponse =" + response.toString());
                logoutResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something, wrong please try again", Toast.LENGTH_LONG).show();

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Logout ", "Url= " + PUTTAPI.LOGOUT_API + " PostObject = " + jsonObject.toString());

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void logoutResponse(JSONObject response) {
        if (response != null) {
            try {

                String status = response.getString("status");
                if (status.equals("1")) {

                    SharedPreferences preferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.remove("userId");
                    editor.remove("count");
                    editor.clear();
                    editor.commit();


                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();


                } else {

                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void profileRefresh(){

        final Dialog dialog = new Dialog(HomeActivity.this, android.R.style.Theme_Translucent_NoTitleBar);

        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams wlp = window.getAttributes();
        dialog.setCanceledOnTouchOutside(false);

        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.7f;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        // Include dialog.xml file
        dialog.setContentView(R.layout.refresh_tee_format);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView dialogText = (TextView) dialog.findViewById(R.id.popup_preview);

        RelativeLayout cancelBTN = (RelativeLayout) dialog.findViewById(R.id.cancel_popup);
        RelativeLayout createBTN = (RelativeLayout) dialog.findViewById(R.id.refresh_popup);
        String popUpMsg = "Due to slow internet connection Profile can't load, so please refresh.";

        dialogText.setText(popUpMsg);
        dialog.show();

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();

                getProfile();



            }
        });
    }

    private void getProfile() {


        JSONObject jsonObject = null;
        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        String user_ID = pref.getString("userId", null);


        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);

            jsonObject.putOpt("version", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.USER_PROFILE_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("Profile", "OnResponse =" + response.toString());
                getProfileResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                profileRefresh();
                Toast.makeText(getApplicationContext(), "Error in Profile", Toast.LENGTH_LONG).show();

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Log.e("LoginPage", "Url= " + PUTTAPI.USER_PROFILE_URL + " PostObject = " + jsonObject.toString());
        //requestQueue.add(jsonObjectRequest);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void getProfileResponse(JSONObject response) {

        profileBean = new UserProfileBean();


        if (response != null) {
            try {
                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")) {
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    profileBean.setUser_id(jsonObject1.getString("user_id"));
                    profileBean.setUser_name(jsonObject1.getString("user_name"));
                    profileBean.setFull_name(jsonObject1.getString("full_name"));
                    profileBean.setDisplay_name(jsonObject1.getString("display_name"));
                    profileBean.setContact_no(jsonObject1.getString("contact_no"));
                    profileBean.setCountry_code(jsonObject1.getString("country_code"));
                    profileBean.setHandicap_value(jsonObject1.getString("handicap_value"));

                    String notificationType = jsonObject1.getString("notifications_count");
                    if (notificationType.equalsIgnoreCase("0")) {
                        navDrawerItems = new ArrayList<NavDrawerItem>();
                        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

                        // adding nav drawer items to array
                        // Home
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
                        // Find People
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
                        // Photos
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
                        // Communities, Will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
                        // Pages
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
                        // What's hot, We  will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(9, -1)));

                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
                        // Pages
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
                        // What's hot, We  will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));

                        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
                        mDrawerList.setAdapter(adapter);

                    } else {
                        navDrawerItems = new ArrayList<NavDrawerItem>();
                        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
                        // adding nav drawer items to array
                        // Home
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
                        // Find People
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
                        // Photos
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
                        // Communities, Will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
                        // Pages
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
                        // What's hot, We  will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));

                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
                        // Pages
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
                        // What's hot, We  will add a counter here
                        navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));

                        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
                        mDrawerList.setAdapter(adapter);

                    }

                    profileBean.setAddress(jsonObject1.getString("address"));
                    profileBean.setCountry(jsonObject1.getString("country"));
                    profileBean.setDesignation(jsonObject1.getString("designation"));
                    profileBean.setPhoto_url(jsonObject1.getString("photo_url"));
                    profileBean.setThumb_url(jsonObject1.getString("thumb_url"));


                    profileBean.setMessage(jsonObject.getString("message"));
                    //  Toast.makeText(getApplicationContext(),"Profile "+profileBean.getMessage(),Toast.LENGTH_LONG).show();

                    if (mSharedPreferences == null)
                        return;

                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("user_image", profileBean.getThumb_url());
                    editor.putString("user_handicap", profileBean.getHandicap_value());
                    editor.putString("displayName", profileBean.getDisplay_name());
                    editor.commit();

                    String st = profileBean.getThumb_url();
                    if (st.length() != 0) {

                        Picasso.with(this).load(profileBean.getThumb_url()).into(imageViewPro);
                        Log.v("IMAG", profileBean.getThumb_url());
                    }
                    profName.setText(profileBean.getDisplay_name());

                    //new DownloadImageTask(profileImage).execute(profileBean.getUser_image());
                } else {

                    String str = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    public void addtitle(String name) {

        LatoTextView tv = (LatoTextView) findViewById(R.id.maintitle);
        tv.setText(name);

    }


}