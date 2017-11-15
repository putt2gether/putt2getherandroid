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
@SuppressLint("ValidFragment")
public class StatsFragFour extends Fragment {

    TextView per_hole,per_round,per_gir;
    StatsBean statsBean = new StatsBean();
    public StatsFragFour(StatsBean stats) {

        statsBean = stats;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stats_four, container, false);

        per_hole = (TextView)rootView.findViewById(R.id.per_hole_stats);
        per_gir = (TextView)rootView.findViewById(R.id.per_gir_stats);
        per_round = (TextView)rootView.findViewById(R.id.per_round_stats);

        per_hole.setText(statsBean.getPer_hole_avg());
        per_round.setText(statsBean.getPer_round_avg());
        per_gir.setText(statsBean.getPer_gir_avg());

        return rootView;
    }
}
