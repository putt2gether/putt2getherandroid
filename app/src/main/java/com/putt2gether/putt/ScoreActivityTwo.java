package com.putt2gether.putt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.adapter.ScorerAdapterTwo;
import com.putt2gether.bean.LeaderboardBean.ScoreBeanTwo;
import com.putt2gether.bean.ScorerBean;

/**
 * Created by Abc on 9/22/2016.
 */
public class ScoreActivityTwo extends AppCompatActivity {

    List<ScorerBean> list = new ArrayList<ScorerBean>();
    private ScorerAdapterTwo ddAdapter;
    private RecyclerView ddRecyclerView;
    private RecyclerView.LayoutManager ddLayoutManager;
    private ImageView backBTN;
    private RelativeLayout continueBTN;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_scorer);


        ArrayList<ScoreBeanTwo> myList = (ArrayList<ScoreBeanTwo>) getIntent().getSerializableExtra("mylist");
        int frompost=getIntent().getIntExtra("frompost",0);



        continueBTN = (RelativeLayout)findViewById(R.id.scorer_continue_btn);


        ddRecyclerView  = (RecyclerView)findViewById(R.id.scorer_list);
      /*  backBTN = (ImageView)findViewById(R.id.back_select_scorer);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        ddRecyclerView.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(this);
        ddRecyclerView.setLayoutManager(ddLayoutManager);



        ddAdapter = new ScorerAdapterTwo(this,myList,frompost);
        ddRecyclerView.setAdapter(ddAdapter);


    }

    public String geteventid()
    {
        String event_ID=getIntent().getStringExtra("eventID");

        return event_ID;
    }
}
