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
public class FairwaysFragment  extends Fragment {

    TextView hit,left,right;
    String far_hit,far_left,far_right;

    @SuppressLint("ValidFragment")
    public FairwaysFragment (String fairway_hit, String fairway_left, String fairway_right) {
        far_hit = fairway_hit;
        far_left = fairway_left;
        far_right = fairway_right;

    }

    public FairwaysFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fareway_fragment_home, container, false);

        hit = (TextView)rootView.findViewById(R.id.fairway_hit);
        left = (TextView)rootView.findViewById(R.id.fairway_left);
        right =(TextView)rootView.findViewById(R.id.fairway_right);



        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (far_hit.equalsIgnoreCase("-")){
            hit.setText("-");
        }else {
            hit.setText(far_hit+"%");
        }

        if (far_left.equalsIgnoreCase("-")){
            left.setText("-");
        }else {
            left.setText(far_left+"%");
        }

        if (far_right.equalsIgnoreCase("-")){
            right.setText("-");
        }else {
            right.setText(far_right+"%");
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