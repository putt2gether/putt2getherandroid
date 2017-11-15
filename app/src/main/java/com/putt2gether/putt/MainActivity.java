package com.putt2gether.putt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import com.putt2gether.R;


public class MainActivity extends AppCompatActivity {

    String className = this.getClass().getSimpleName();
    boolean doubleBackToExitPressedOnce = false;

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";

    String SENDER_ID = "1063115356821";
    String token_id;


    static final String TAG = "GCMDemo";

    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();

    Context context;
    TextView splashYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        splashYear = (TextView) findViewById(R.id.splashyear);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        splashYear.setText("PUTT2GETHER APP " + year);

        context = getApplicationContext();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                final String userId = pref.getString("userId", null);
                if (userId == null) {
                    Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(i);

                    finish();
                } else {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    i.putExtra("fromEventPreview", "0");
                    startActivity(i);

                    finish();
                }
            }
        }, SPLASH_TIME_OUT);


    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


}

