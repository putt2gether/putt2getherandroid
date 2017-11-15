package com.putt2gether.utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by Ajay on 17/06/2016.
 */
public class DialogUtility {

    private Activity activity;
    private ProgressDialog dialog;

    public DialogUtility(Activity activity) {
        this.activity = activity;
    }


    public  void showProgressDialog(){
        dialog = new ProgressDialog(activity);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Loading...");
        dialog.setMessage("Please wait..");
        dialog.show();
    }

    public  void dismissProgressDialog(){
        dialog.cancel();
        dialog.dismiss();
    }

}