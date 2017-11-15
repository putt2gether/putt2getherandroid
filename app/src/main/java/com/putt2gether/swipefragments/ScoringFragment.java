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
public class ScoringFragment extends Fragment {

    private String par3,par4,par5;
    private TextView par3text,par4text,par5text;

    @SuppressLint("ValidFragment")
    public ScoringFragment(String avg_par3s, String avg_par4s, String avg_par5s) {
        par3=avg_par3s;
        par4= avg_par4s;
        par5 = avg_par5s;

    }

    public ScoringFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.scoring_fragment_home, container, false);

        par3text = (TextView)rootView.findViewById(R.id.par3s);
        par4text = (TextView)rootView.findViewById(R.id.par4s);
        par5text = (TextView)rootView.findViewById(R.id.par5s);


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (par3.equalsIgnoreCase("-")){
            par3text.setText("-");
        }else {
            par3text.setText(par3);
        }
        if (par4.equalsIgnoreCase("-")){
            par4text.setText("-");
        }else {
            par4text.setText(par4);
        }
        if (par5.equalsIgnoreCase("-")){
            par5text.setText("-");
        }else {
            par5text.setText(par5);
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