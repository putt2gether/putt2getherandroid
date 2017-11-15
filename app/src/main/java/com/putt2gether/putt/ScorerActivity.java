package com.putt2gether.putt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.adapter.ScorerAdapter;
import com.putt2gether.bean.LeaderboardBean.ScoreBeanTwo;
import com.putt2gether.bean.ScorerBean;

/**
 * Created by Ajay on 24/06/2016.
 */
public class ScorerActivity extends AppCompatActivity {

    List<ScorerBean> list = new ArrayList<ScorerBean>();
    private ScorerAdapter ddAdapter;
   // private ScorerAdapterthree ddADAPthree;
    private RecyclerView ddRecyclerView;
    private RecyclerView.LayoutManager ddLayoutManager;
    private ImageView backBTN;
    private RelativeLayout continueBTN;
   String scorer_name,scorerid,positiontoapply;
    ArrayList<ScoreBeanTwo> myList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_scorer);


         myList = (ArrayList<ScoreBeanTwo>) getIntent().getSerializableExtra("mylist");

        String fromactivity=getIntent().getStringExtra("ACTIVITY");

        ddRecyclerView  = (RecyclerView)findViewById(R.id.scorer_list);
        backBTN = (ImageView)findViewById(R.id.back_select_scorer);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ddRecyclerView.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(this);
        ddRecyclerView.setLayoutManager(ddLayoutManager);



        if(fromactivity.equals("ONE"))
        {
            ddAdapter = new ScorerAdapter(this,myList);
            ddRecyclerView.setAdapter(ddAdapter);

        }


      /*  continueBTN = (RelativeLayout)findViewById(R.id.scorer_continue_btn);
        continueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EventPreviewActivity.class);
                startActivity(intent);
            }
        });*/


    }

    public String geteventid()
    {
        String event_ID=getIntent().getStringExtra("eventID");

        return event_ID;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        String delegatename1,delegateid1;
        ArrayList<ScoreBeanTwo>myList2 = new ArrayList<ScoreBeanTwo>();

        if(resultCode == 1)
        {
            Log.e("backkk","back");
            scorer_name=data.getStringExtra("playername");
            scorerid=data.getStringExtra("playerid");
            int pos=data.getIntExtra("positionfor",0);
            int posfrom=data.getIntExtra("positionfrom",0);


            ArrayList<ScoreBeanTwo>myList1 = (ArrayList<ScoreBeanTwo>) getIntent().getSerializableExtra("mylist");

            for(int i=0;i<myList1.size();i++) {

                ScoreBeanTwo createBean=new ScoreBeanTwo();

                String name1=myList1.get(i).getName();
                String handicap_value1=myList1.get(i).getHandicap_Value();
                String photo_URL1=myList1.get(i).getPhoto_url();
                String participant_ID1=myList1.get(i).getParticipant_ID();

                delegatename1=myList1.get(i).getSelf_delegate();

                Log.e("ScorerName",scorer_name);

                Log.e("Delegatename",delegatename1);


                createBean.setName(name1);
                createBean.setHandicap_Value(handicap_value1);
                createBean.setPhoto_url(photo_URL1);
                createBean.setParticipant_ID(participant_ID1);

                if(!scorer_name.isEmpty())
                {
                    createBean.setSelf_delegate(scorer_name);

                }
                else
                {
                    createBean.setSelf_delegate("self");
                }

                myList2.add(createBean);

            }


            ddAdapter = new ScorerAdapter(this,myList2,scorerid,pos,posfrom);
            ddRecyclerView.setAdapter(ddAdapter);
            ddAdapter.notifyDataSetChanged();


        }
    }
}
