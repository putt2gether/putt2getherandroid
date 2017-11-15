package com.putt2gether.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.putt2gether.bean.HomeStatsBean;
import com.putt2gether.swipefragments.FairwaysFragment;
import com.putt2gether.swipefragments.GreenRevolutionFragment;
import com.putt2gether.swipefragments.PuttingFragment;
import com.putt2gether.swipefragments.RecoveryFragment;
import com.putt2gether.swipefragments.ScoringFragment;

/**
 * Created by Ajay on 06/12/2016.
 */
public class HomeStatsAdapter  extends FragmentStatePagerAdapter {
    HomeStatsBean statsBeanHome = new HomeStatsBean();

    public HomeStatsAdapter(FragmentManager fm, HomeStatsBean statsHome) {
        super(fm);
        statsBeanHome = statsHome;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ScoringFragment tab4 = new ScoringFragment(statsBeanHome.getAvg_par3s(), statsBeanHome.getAvg_par4s(), statsBeanHome.getAvg_par5s());
                return tab4;
            case 1:
                FairwaysFragment tab1 = new FairwaysFragment(statsBeanHome.getFairway_hit(), statsBeanHome.getFairway_left(), statsBeanHome.getFairway_right());
                return tab1;
            case 2:
                PuttingFragment tab2 = new PuttingFragment(statsBeanHome.getPer_hole_avg(), statsBeanHome.getPer_gir_avg(), statsBeanHome.getPer_round_avg());
                return tab2;
            case 3:
                GreenRevolutionFragment tab5 = new GreenRevolutionFragment(statsBeanHome.getHit(), statsBeanHome.getMissed());
                return tab5;
            case 4:
                RecoveryFragment tab3 = new RecoveryFragment(statsBeanHome.getScrmbl_avg(), statsBeanHome.getSand_avg());
                return tab3;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}
