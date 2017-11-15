package com.putt2gether.custome;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.putt2gether.R;

/**
 * Created by Ajay on 19/08/2016.
 */
public class TestFragmentAdapter extends FragmentPagerAdapter {
    private int[] offerImages = {
            R.drawable.vishal,
            R.drawable.vishal,
            R.drawable.vishal,
            R.drawable.vishal,
            R.drawable.vishal
    };

    private int mCount = offerImages.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }




    @Override
    public Fragment getItem(int position) {
        return new TestFragment(offerImages[position]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}