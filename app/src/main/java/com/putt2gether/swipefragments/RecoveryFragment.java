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
public class RecoveryFragment extends Fragment {

    private String scramble,sand;
    private TextView scrambText,sandText;

    @SuppressLint("ValidFragment")
    public RecoveryFragment(String scrmbl_avg, String sand_avg) {

        scramble=scrmbl_avg;
        sand = sand_avg;

    }

    public RecoveryFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recovery_fragment_home, container, false);
        scrambText = (TextView)rootView.findViewById(R.id.scramble_recovery);
        sandText = (TextView)rootView.findViewById(R.id.sand_recovery);



        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (scramble.equalsIgnoreCase("-")){
            scrambText.setText("-");
        }else {
            scrambText.setText(scramble+"%");
        }
        if (sand.equalsIgnoreCase("-")){
            sandText.setText("-");
        }else {
            sandText.setText(sand+"%");
        }


        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());

        if (connectionDetector.isConnectingToInternet()) {
            // getSuggestionListList();
        } else {
            Toast.makeText(getActivity(), R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }

    }






    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


}