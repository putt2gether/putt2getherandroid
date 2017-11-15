package com.putt2gether.push_notification;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.putt2gether.network.RefferenceWrapper;


/**
 * Created by Ajay on 13/09/2016.
 */
public class GCMRegistrationIntentService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    private RefferenceWrapper referanceWapper;
    String refreshedToken;

    @Override
    public void onTokenRefresh() {

        //Getting registration token
         refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);

    }

    public String sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        // Save to SharedPreferences
        editor.putString("registration_id", token);
        editor.apply();
        //Not required for current project
        return token;
    }

    public String getRegistrationToServer() {
        //You can implement this method to store the token on your server
        //Not required for current project
        return refreshedToken;
    }
}