package com.putt2gether.fragments.addscorefrags.scoreCardPlayers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.putt2gether.R;

/**
 * Created by Abc on 9/15/2016.
 */
public class ScoreCardTabOne extends Fragment {


    RecyclerView screcardlistmain;

    ArrayList<String> frstscore=new ArrayList<String>();
    ArrayList<String> secndscore=new ArrayList<String>();
    ArrayList<String> thirdscore=new ArrayList<String>();
    ArrayList<String> fourthscore=new ArrayList<String>();
    ArrayList<String> fifthscore=new ArrayList<String>();
    private RecyclerView.LayoutManager ddLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.scorecard_mainplayers,container,false);

        screcardlistmain=(RecyclerView)view.findViewById(R.id.screcardlistmain);

        for(int i=1;i<19;i++) {
            frstscore.add(""+i);
            secndscore.add("W");
            thirdscore.add("34 45 62 65 61");
            fourthscore.add("&");
            fifthscore.add("4 2 0");
        }

        screcardlistmain.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(getActivity());
        screcardlistmain.setLayoutManager(ddLayoutManager);

        PlayerScoreAdapter a=new PlayerScoreAdapter(getActivity(),frstscore,secndscore,thirdscore,fourthscore,fifthscore);
        screcardlistmain.setAdapter(a);



        return view;
    }
}
