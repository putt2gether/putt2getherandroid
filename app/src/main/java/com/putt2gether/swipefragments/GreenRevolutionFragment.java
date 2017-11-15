package com.putt2gether.swipefragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.putt2gether.R;
import com.putt2gether.network.ConnectionDetector;

/**
 * Created by Ajay on 20/08/2016.
 */
public class GreenRevolutionFragment  extends Fragment {
    private String hitvalue,missedvalue;
    private TextView hitText,missedText;

    @SuppressLint("ValidFragment")
    public GreenRevolutionFragment(String hit, String missed) {

        hitvalue = hit;
        missedvalue = missed;
    }

    public GreenRevolutionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.green_revolution, container, false);

        hitText = (TextView)rootView.findViewById(R.id.hit_gir);
        missedText = (TextView)rootView.findViewById(R.id.missed_gir);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (hitvalue.equalsIgnoreCase("-")){
            hitText.setText("-");
        }else {
            hitText.setText(hitvalue+"%");
        }
        if (missedvalue.equalsIgnoreCase("-")){
            missedText.setText("-");
        }else {
            missedText.setText(missedvalue+"%");
        }

        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());

        if (connectionDetector.isConnectingToInternet()) {

        } else {
            Toast.makeText(getActivity(), R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


}