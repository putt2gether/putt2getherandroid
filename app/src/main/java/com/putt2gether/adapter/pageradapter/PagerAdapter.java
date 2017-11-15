package com.putt2gether.adapter.pageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.putt2gether.fragments.HistoryScore.ClosestPin;
import com.putt2gether.fragments.HistoryScore.LongDrive;
import com.putt2gether.fragments.HistoryScore.NetStroke;
import com.putt2gether.fragments.HistoryScore.StraightDrive;

/**
 * Created by Abc on 8/24/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                NetStroke tab1 = new NetStroke();
                return tab1;
            case 1:
                ClosestPin tab2 = new ClosestPin();
                return tab2;
            case 2:
                LongDrive tab3 = new LongDrive();
                return tab3;
            case 3:
                StraightDrive tab4 = new StraightDrive();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
