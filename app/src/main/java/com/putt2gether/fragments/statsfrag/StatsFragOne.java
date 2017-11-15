package com.putt2gether.fragments.statsfrag;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.putt2gether.R;

/**
 * Created by Abc on 9/7/2016.
 */
public class StatsFragOne extends Fragment {

    TextView gross_last, in_last, out_last, par3_last, par4_last, par5_last;
    TextView gross_avg, in_avg, out_avg, par3_avg, par4_avg, par5_avg;

    int flag = 1;
    TextView changeAvg;
    LinearLayout clickLayout;

    StatsBean statsBean = new StatsBean();

    @SuppressLint("ValidFragment")
    public StatsFragOne(StatsBean stats) {
        statsBean = stats;
    }

    public StatsFragOne(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stats_one, container, false);

        clickLayout = (LinearLayout) rootView.findViewById(R.id.click_stats);
        changeAvg = (TextView) rootView.findViewById(R.id.avg_change);

        gross_last = (TextView) rootView.findViewById(R.id.gross_last);
        in_last = (TextView) rootView.findViewById(R.id.in_last);
        out_last = (TextView) rootView.findViewById(R.id.out_last);
        par3_last = (TextView) rootView.findViewById(R.id.par3_last);
        par4_last = (TextView) rootView.findViewById(R.id.par4s_last);
        par5_last = (TextView) rootView.findViewById(R.id.par5_last);

        gross_avg = (TextView) rootView.findViewById(R.id.gross_score_avg);
        in_avg = (TextView) rootView.findViewById(R.id.in_avg);
        out_avg = (TextView) rootView.findViewById(R.id.out_avg);
        par3_avg = (TextView) rootView.findViewById(R.id.par3_avg);
        par4_avg = (TextView) rootView.findViewById(R.id.par4_avg);
        par5_avg = (TextView) rootView.findViewById(R.id.par5_avg);

        gross_last.setText(statsBean.getLast_gross_score());
        in_last.setText(statsBean.getLast_in());
        out_last.setText(statsBean.getLast_out());
        par3_last.setText(statsBean.getLast_par3s());
        par4_last.setText(statsBean.getLast_par4s());
        par5_last.setText(statsBean.getLast_par5s());


        gross_avg.setText(statsBean.getAvg_gross_score());
        in_avg.setText(statsBean.getAvg_in());
        out_avg.setText(statsBean.getAvg_out());
        par3_avg.setText(statsBean.getAvg_par3s());
        par4_avg.setText(statsBean.getAvg_par4s());
        par5_avg.setText(statsBean.getAvg_par5s());


        clickLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {

                    changeAvg.setText("AVERAGE");

                    gross_avg.setText(statsBean.getAvg_gross_score());
                    in_avg.setText(statsBean.getAvg_in());
                    out_avg.setText(statsBean.getAvg_out());
                    par3_avg.setText(statsBean.getAvg_par3s());
                    par4_avg.setText(statsBean.getAvg_par4s());
                    par5_avg.setText(statsBean.getAvg_par5s());

                    gross_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable = (GradientDrawable) gross_avg.getBackground();
                    drawable.setColor(Color.parseColor("#0b5a97"));

                    in_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable2 = (GradientDrawable) in_avg.getBackground();
                    drawable2.setColor(Color.parseColor("#0b5a97"));

                    out_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable3 = (GradientDrawable) out_avg.getBackground();
                    drawable3.setColor(Color.parseColor("#0b5a97"));

                    par3_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable4 = (GradientDrawable) par3_avg.getBackground();
                    drawable4.setColor(Color.parseColor("#0b5a97"));

                    par4_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable5 = (GradientDrawable) par4_avg.getBackground();
                    drawable5.setColor(Color.parseColor("#0b5a97"));

                    par5_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable6 = (GradientDrawable) par5_avg.getBackground();
                    drawable6.setColor(Color.parseColor("#0b5a97"));


                    flag = 1;
                } else {

                    changeAvg.setText("CHANGE");

                    gross_avg.setText(statsBean.getGscore_change());
                    in_avg.setText(statsBean.getIn_change());
                    out_avg.setText(statsBean.getOut_change());
                    par3_avg.setText(statsBean.getPar3_change());
                    par4_avg.setText(statsBean.getPar4_change());
                    par5_avg.setText(statsBean.getPar5_change());


                    gross_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable = (GradientDrawable) gross_avg.getBackground();
                    drawable.setColor(Color.parseColor(statsBean.getGscore_change_color()));

                    in_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable2 = (GradientDrawable) in_avg.getBackground();
                    drawable2.setColor(Color.parseColor(statsBean.getIn_change_color()));

                    out_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable3 = (GradientDrawable) out_avg.getBackground();
                    drawable3.setColor(Color.parseColor(statsBean.getOut_change_color()));

                    par3_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable4 = (GradientDrawable) par3_avg.getBackground();
                    drawable4.setColor(Color.parseColor(statsBean.getPar3_change_color()));

                    par4_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable5 = (GradientDrawable) par4_avg.getBackground();
                    drawable5.setColor(Color.parseColor(statsBean.getPar4_change_color()));

                    par5_avg.setBackgroundResource(R.drawable.border_circular_score);
                    GradientDrawable drawable6 = (GradientDrawable) par5_avg.getBackground();
                    drawable6.setColor(Color.parseColor(statsBean.getPar5_change_color()));

                    flag = 0;
                }
            }
        });


        return rootView;
    }
}
