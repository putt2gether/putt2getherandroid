package com.putt2gether.fragments.statsfrag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.putt2gether.R;

/**
 * Created by Abc on 9/7/2016.
 */
public class StatsFragTwo extends Fragment {

    TextView hit,missed;
    StatsBean statsBean = new StatsBean();

    @SuppressLint("ValidFragment")
    public StatsFragTwo(StatsBean stats) {

        statsBean = stats;
    }

    public StatsFragTwo(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stats_two, container, false);

        hit = (TextView)rootView.findViewById(R.id.gir_hit_stats);
        missed = (TextView)rootView.findViewById(R.id.gir_missed_stats);

        if (statsBean.getGir_hit().equalsIgnoreCase("-")){
            hit.setText("-");
        }else {
            hit.setText(statsBean.getGir_hit()+"%");
        }
        if (statsBean.getGir_missed().equalsIgnoreCase("-")){
            missed.setText("-");
        }else {
            missed.setText(statsBean.getGir_missed()+"%");
        }

        return rootView;
    }
}
