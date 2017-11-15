package com.putt2gether.network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.putt2gether.bean.DeviceInfoBean;
import com.putt2gether.bean.LoginBean;


/**
 * Created by Ajay on 17/06/2016.
 */
public class RefferenceWrapper {

    private static RefferenceWrapper referanceWapper;
    private Activity activity;
    private SharedPreferences sharedPreferences;
    private DeviceInfoBean deviceInformationBean;
    private LoginBean loginInfoBean;

    private RefferenceWrapper(Activity activity) {
        this.activity = activity;
    }


    public static RefferenceWrapper getReferanceWapper(Activity activity) {
        if (referanceWapper == null) {
            referanceWapper = new RefferenceWrapper(activity);
        }
        return referanceWapper;
    }


    public SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public DeviceInfoBean getDeviceInformationBean() {
        if (deviceInformationBean == null) {
            deviceInformationBean = new DeviceInfoBean();
        }
        return deviceInformationBean;
    }

    public LoginBean getLoginInfo() {
        if (loginInfoBean == null) {
            loginInfoBean = new LoginBean();
        }
        return loginInfoBean;
    }
}
