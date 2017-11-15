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
public class StatsFragFive extends Fragment {

    TextView scramble,sand;
    StatsBean statsBean = new StatsBean();

    @SuppressLint("ValidFragment")
    public StatsFragFive(StatsBean stats) {

        statsBean = stats;
    }

    public StatsFragFive(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stats_five, container, false);

        scramble = (TextView)rootView.findViewById(R.id.scramble_stats);
        sand = (TextView)rootView.findViewById(R.id.sand_stats);

        if (statsBean.getSand_avg().equalsIgnoreCase("-")){
            scramble.setText("-");
        }else {
            scramble.setText(statsBean.getScrmbl_avg()+"%");
        }
        if (statsBean.getSand_avg().equalsIgnoreCase("-")){
            sand.setText("-");
        }else {
            sand.setText(statsBean.getSand_avg()+"%");
        }

        return rootView;
    }
}
