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
public class StatsFragThree extends Fragment {

    TextView hit,left,right;
    StatsBean statsBean = new StatsBean();

    @SuppressLint("ValidFragment")
    public StatsFragThree(StatsBean stats) {
        statsBean = stats;

    }

    public StatsFragThree(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stats_three, container, false);
        hit = (TextView)rootView.findViewById(R.id.hit_fairway_stats);
        right = (TextView)rootView.findViewById(R.id.right_fairway_stats);
        left = (TextView)rootView.findViewById(R.id.left_fairway_stats);

        if (statsBean.getHit_fairway().equalsIgnoreCase("-")){
            hit.setText("-");
        }else {
            hit.setText(statsBean.getHit_fairway()+"%");
        }
        if (statsBean.getLeft_fairway().equalsIgnoreCase("-")){
            left.setText("-");
        }else {
            left.setText(statsBean.getLeft_fairway()+"%");
        }
        if (statsBean.getRight_fairway().equalsIgnoreCase("-")) {
            right.setText("-");
        }else {
            right.setText(statsBean.getRight_fairway()+"%");
        }

        return rootView;
    }
}
