package com.putt2gether.adapter.pageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.putt2gether.fragments.statsfrag.StatsBean;
import com.putt2gether.fragments.statsfrag.StatsFragFive;
import com.putt2gether.fragments.statsfrag.StatsFragFour;
import com.putt2gether.fragments.statsfrag.StatsFragOne;
import com.putt2gether.fragments.statsfrag.StatsFragThree;
import com.putt2gether.fragments.statsfrag.StatsFragTwo;

/**
 * Created by Abc on 9/7/2016.
 */
public class StatsPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs=5;

    StatsBean stats = new StatsBean();

    public StatsPagerAdapter(FragmentManager fm, StatsBean statsBean) {
        super(fm);
        stats= statsBean;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                StatsFragOne tab1 = new StatsFragOne(stats);
                return tab1;
            case 1:
                StatsFragThree tab3 = new StatsFragThree(stats);
                return tab3;
            case 2:
                StatsFragFour tab4 = new StatsFragFour(stats);
                return tab4;
            case 3:
                StatsFragTwo tab2 = new StatsFragTwo(stats);
                return tab2;
            case 4:
                StatsFragFive tab5 = new StatsFragFive(stats);
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}