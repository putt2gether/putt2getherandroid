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
public class PuttingFragment  extends Fragment {

    String holeAvg,girAvg,roundAvg;
    TextView hole,gir,round;

    @SuppressLint("ValidFragment")
    public PuttingFragment(String per_hole_avg, String per_gir_avg, String per_round_avg) {

        holeAvg=per_hole_avg;
        girAvg = per_gir_avg;
        roundAvg = per_round_avg;

    }

    public PuttingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.putting_fragment_home, container, false);

        hole = (TextView)rootView.findViewById(R.id.perhole);
        gir= (TextView)rootView.findViewById(R.id.pergir);
        round = (TextView)rootView.findViewById(R.id.perround);



        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (holeAvg.equalsIgnoreCase("-")){
            hole.setText("-");
        }else {
            hole.setText(holeAvg);
        }
        if (girAvg.equalsIgnoreCase("-")){
            gir.setText("-");
        }else {
            gir.setText(girAvg);
        }
        if (roundAvg.equalsIgnoreCase("-")){
            round.setText("-");
        }else {
            round.setText(roundAvg);
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