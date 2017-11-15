package com.putt2gether.fragments.addscorefrags.scoreCardPlayers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.putt2gether.R;
import com.putt2gether.adapter.pageradapter.PagerAdapterScore;
import com.putt2gether.view.LatoTextView;

/**
 * Created by Abc on 9/15/2016.
 */
public class ScoreCardPlayers extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorecardmain);
        tabLayout = (TabLayout) findViewById(R.id.scorecardmaintab);

        maketabs(4);

    }

    private void createTabIcons(int numbertabs) {

        if (numbertabs == 4) {

            String firstname = "RAHUL";
            String secondname = "SHIKHAR";
            String thirdname = "PAUL";
            String fourthname = "ROGER";

            String frsthandicap = "1";
            String secondhandicap = "5";
            String thrdhandicap = "5";
            String fourthhandicap = "2";

            View tabOne = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv = (LatoTextView) tabOne.findViewById(R.id.tab_title_name);
            tv.setText(firstname + "(" + frsthandicap + ")");
            RelativeLayout tab_bg = (RelativeLayout) tabOne.findViewById(R.id.tab_bg);
            View tabdivider = (View) tabOne.findViewById(R.id.tabdivider);

            tabLayout.getTabAt(0).setCustomView(tabOne);


            View twotab = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv2 = (LatoTextView) twotab.findViewById(R.id.tab_title_name);
            tv2.setText(secondname + "(" + secondhandicap + ")");

            tabLayout.getTabAt(1).setCustomView(twotab);

            View tabthree = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv3 = (LatoTextView) tabthree.findViewById(R.id.tab_title_name);
            RelativeLayout tab_bg3 = (RelativeLayout) tabthree.findViewById(R.id.tab_bg);
            tab_bg3.setBackgroundResource(R.color.decline);
            tv3.setText(thirdname + "(" + thrdhandicap + ")");

            tabLayout.getTabAt(2).setCustomView(tabthree);

            View tabfour = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            LatoTextView tv4 = (LatoTextView) tabfour.findViewById(R.id.tab_title_name);
            RelativeLayout tab_bg4 = (RelativeLayout) tabfour.findViewById(R.id.tab_bg);
            tab_bg4.setBackgroundResource(R.color.decline);
            tv4.setText(fourthname + "(" + fourthhandicap + ")");
            View v = (View) tabfour.findViewById(R.id.tabdivider);
            v.setVisibility(View.GONE);

            tabLayout.getTabAt(3).setCustomView(tabfour);
        }
    }

    private void createViewPager(ViewPager viewPager, int numberoftabs) {
        if (numberoftabs == 4) {
            PagerAdapterScore adapter = new PagerAdapterScore(getSupportFragmentManager());

            adapter.addFrag(new ScoreCardTabOne(), "Tab 1");

            adapter.addFrag(new ScoreCardTabOne(), "Tab 2");
            adapter.addFrag(new ScoreCardTabOne(), "Tab 3");
            adapter.addFrag(new ScoreCardTabOne(), "Tab 4");
            viewPager.setAdapter(adapter);
        }
    }


    public void maketabs(int numberoftabs) {
        viewPager = (ViewPager) findViewById(R.id.scorecardpager);

        createViewPager(viewPager, numberoftabs);

        tabLayout.setupWithViewPager(viewPager);

        createTabIcons(numberoftabs);
    }
}
