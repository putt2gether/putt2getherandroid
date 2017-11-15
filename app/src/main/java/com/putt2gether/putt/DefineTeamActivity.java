package com.putt2gether.putt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import com.putt2gether.R;
import com.putt2gether.utils.Constant;

/**
 * Created by Ajay on 23/06/2016.
 */
public class DefineTeamActivity extends AppCompatActivity {
    private RelativeLayout nextBTN;
    private TextView addIConA1,addIConB1,addIConB2;
    private ImageView backBTN;
    private ImageView profilePicA1,profilePicA2,profilePicB1,profilePicB2;
    private TextView nameA1,nameA2,nameB1,nameB2;
    private TextView handicapA1,handicapA2,handicapB1,handicapB2;
    private EditText teamAName,teamBName;
    Typeface Lato_Bold;
    Typeface Lato_Regular;
    private SharedPreferences mSharedPreferences;
    private Constant constant;
    private String user1,user2,user3;
    private String teamA,teamB;
    private String teamA2Name,teamB1Name,teamB2Name;

    private RelativeLayout play_lay1,play_lay2,play_lay3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.define_team_activity);
        mSharedPreferences = getSharedPreferences("craete_event", Context.MODE_PRIVATE);

        constant = new Constant();
        Lato_Bold = Typeface.createFromAsset(getAssets(), constant.Lato_Bold);
        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);


        play_lay1 = (RelativeLayout)findViewById(R.id.teamA_player2_layout);
        play_lay2 = (RelativeLayout)findViewById(R.id.teamB_player1_layout);
        play_lay3 = (RelativeLayout)findViewById(R.id.teamB_player2_layout);

        backBTN = (ImageView)findViewById(R.id.back_define_team);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        teamAName = (EditText)findViewById(R.id.team_A_editText);
        nameA1 = (TextView)findViewById(R.id.teamA_playe1);
        profilePicA1 = (ImageView)findViewById(R.id.teamA_player1_image);
        handicapA1 = (TextView)findViewById(R.id.teamA_player1_age);
        addIConA1 = (TextView)this.findViewById(R.id.add_remove_btn_teamA_playe1);
        nameA2 = (TextView)findViewById(R.id.teamA_playe2);
        profilePicA2 = (ImageView)findViewById(R.id.teamA_player2_image);
        handicapA2 = (TextView)findViewById(R.id.teamA_player2_age);

        teamBName = (EditText)findViewById(R.id.team_b_editText);
        nameB1 = (TextView)findViewById(R.id.teamB_playe1);
        profilePicB1 = (ImageView)findViewById(R.id.teamB_player1_image);
        handicapB1 = (TextView)findViewById(R.id.teamB_player1_age);
        addIConB1 = (TextView) findViewById(R.id.add_remove_btn_teamB_playe1);
        addIConB2 = (TextView)findViewById(R.id.add_remove_btn_teamB_playe2);
        nameB2 = (TextView)findViewById(R.id.teamB_playe2);
        profilePicB2 = (ImageView)findViewById(R.id.teamB_player2_image);
        handicapB2 = (TextView)findViewById(R.id.teamB_player2_age);


        teamAName.setTypeface(Lato_Regular);
        teamBName.setTypeface(Lato_Regular);

        nextBTN = (RelativeLayout)findViewById(R.id.countinue_define_team_btn);
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (teamAName.getText().toString().isEmpty()|| teamAName.getText().toString()==null){
                    teamA = teamAName.getHint().toString();
                }else {
                    teamA = teamAName.getText().toString();
                }

                if (teamBName.getText().toString().isEmpty()|| teamBName.getText().toString()==null){
                    teamB = teamBName.getHint().toString();
                }else {
                    teamB = teamBName.getText().toString();
                }

                teamA2Name = nameA2.getText().toString();
                teamB1Name = nameB1.getText().toString();
                teamB2Name = nameB2.getText().toString();

                if (teamA==null && teamB==null && teamA2Name.equalsIgnoreCase("ADD PLAYER")&& teamB1Name.equalsIgnoreCase("ADD PLAYER")&& teamB2Name.equalsIgnoreCase("ADD PLAYER")){
                    Toast.makeText(getApplicationContext(),"PLEASE ADD ALL PLAYER",Toast.LENGTH_LONG).show();
                }else {

                    Intent intent = new Intent(getApplicationContext(), EventPreviewActivity.class);
                    intent.putExtra("teamA", teamA);
                    intent.putExtra("teamB", teamB);
                    intent.putExtra("user1", user1);
                    intent.putExtra("user2", user2);
                    intent.putExtra("user3", user3);
                    startActivity(intent);
                }
            }
        });

        play_lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String button1 = addIConA1.getText().toString();
                if (button1.equalsIgnoreCase("add")){
                    if(mSharedPreferences==null)
                        return;
                    Intent intent = new Intent(getApplicationContext(),TeamTabLayout.class);
                    SharedPreferences.Editor editor1 = mSharedPreferences.edit();
                    editor1.putString("tag", "tag1");
                    editor1.putString("activity_type", "team");

                    editor1.putString("teamA_Name",teamAName.getText().toString());
                    editor1.putString("teamB_Name",teamBName.getText().toString());

                    editor1.commit();
                    startActivity(intent);
                  //  finish();
                }else {

                    SharedPreferences.Editor editorRemove = mSharedPreferences.edit();
                    editorRemove.remove("name1");
                    editorRemove.remove("handicap1");
                    editorRemove.remove("image1");
                    editorRemove.remove("text1");

                    editorRemove.remove("player1Email");
                    editorRemove.remove("player1Name");
                    editorRemove.remove("player1Handicap");
                    editorRemove.remove("player1Team");

                    editorRemove.commit();
                    Intent intent = new Intent(getApplicationContext(),DefineTeamActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });
        play_lay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button2 = addIConB1.getText().toString();
                if (button2.equalsIgnoreCase("add")) {

                    if (mSharedPreferences == null)
                        return;
                    Intent intent = new Intent(getApplicationContext(), TeamTabLayout.class);

                    SharedPreferences.Editor editor1 = mSharedPreferences.edit();
                    editor1.putString("tag", "tag2");
                    editor1.putString("activity_type", "team");

                    editor1.putString("teamA_Name",teamAName.getText().toString());
                    editor1.putString("teamB_Name",teamBName.getText().toString());

                    editor1.commit();
                    startActivity(intent);
                    finish();

                }else {

                    SharedPreferences.Editor editorRemove = mSharedPreferences.edit();
                    editorRemove.remove("name2");
                    editorRemove.remove("handicap2");
                    editorRemove.remove("image2");
                    editorRemove.remove("text2");

                    editorRemove.remove("player2Email");
                    editorRemove.remove("player2Name");
                    editorRemove.remove("player2Handicap");
                    editorRemove.remove("player2Team");

                    editorRemove.commit();
                    Intent intent = new Intent(getApplicationContext(),DefineTeamActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        play_lay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button2 = addIConB2.getText().toString();
                if (button2.equalsIgnoreCase("add")) {
                    if (mSharedPreferences == null)
                        return;
                    Intent intent = new Intent(getApplicationContext(), TeamTabLayout.class);
                    SharedPreferences.Editor editor1 = mSharedPreferences.edit();
                    editor1.putString("tag", "tag3");
                    editor1.putString("activity_type", "team");
                    editor1.putString("teamA_Name",teamAName.getText().toString());
                    editor1.putString("teamB_Name",teamBName.getText().toString());
                    editor1.commit();
                    startActivity(intent);
                    finish();
                }else {
                    SharedPreferences.Editor editorRemove = mSharedPreferences.edit();
                    editorRemove.remove("name3");
                    editorRemove.remove("handicap3");
                    editorRemove.remove("image3");
                    editorRemove.remove("text3");
                    editorRemove.remove("player3Email");
                    editorRemove.remove("player3Name");
                    editorRemove.remove("player3Handicap");
                    editorRemove.remove("player3Team");

                    editorRemove.commit();
                    Intent intent = new Intent(getApplicationContext(),DefineTeamActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


       SharedPreferences mSharedPreferencesADMIN = getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        final String adminNAME = mSharedPreferencesADMIN.getString("displayName",null);
        final String adminHANDICAP = mSharedPreferencesADMIN.getString("user_handicap",null);
        final String adminIMAGE = mSharedPreferencesADMIN.getString("user_image",null);
        nameA1.setText(adminNAME);
        handicapA1.setText(adminHANDICAP);
        if (adminIMAGE.length()!=0){
            Picasso.with(this).load(adminIMAGE).into(profilePicA1);
        }



        final String tag = mSharedPreferences.getString("tag",null);
        if (tag !=null) {
            Log.v("ddd",tag+",,"+tag.length());

            user1 = mSharedPreferences.getString("user_id1",null);
            String teamA_name = mSharedPreferences.getString("teamA_Name",null);
            String teamB_name = mSharedPreferences.getString("teamB_Name",null);

            if (teamA_name!=null) {
                teamAName.setText(teamA_name);
            }
            if (teamB_name!=null) {
                teamBName.setText(teamB_name);
            }

            if (user1!=null) {

                if (!user1.equalsIgnoreCase(user2)&& !user1.equalsIgnoreCase(user3)) {
                    String name1 = mSharedPreferences.getString("name1", null);
                    String handicap1 = mSharedPreferences.getString("handicap1", null);
                    String adminIMAGE1 = mSharedPreferences.getString("image1", null);
                    String text1 = mSharedPreferences.getString("text1", null);

                    String nameC1 = mSharedPreferences.getString("player1Name", null);
                    String handicapC1 = mSharedPreferences.getString("player1Handicap", null);
                    String textC1 = mSharedPreferences.getString("player1Email", null);





                    if (name1 != null && handicap1 != null && text1 != null) {
                        nameA2.setText(name1);
                        handicapA2.setText(handicap1);
                        addIConA1.setText(text1);
                    } else if (nameC1 != null && handicapC1 != null && textC1 != null) {
                        nameA2.setText(nameC1);
                        handicapA2.setText(handicapC1);
                        addIConA1.setText("REMOVE");
                    }
                    if (adminIMAGE1 != null && adminIMAGE1.length()>10) {

                        Picasso.with(this).load(adminIMAGE1).into(profilePicA2);
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "You have already selected that player", Toast.LENGTH_SHORT).show();
                }
            }else {
                String nameC1 = mSharedPreferences.getString("player1Name", null);
                String handicapC1 = mSharedPreferences.getString("player1Handicap", null);
                String textC1 = mSharedPreferences.getString("player1Email", null);


                if (nameC1 != null && handicapC1 != null && textC1 != null) {
                    nameA2.setText(nameC1);
                    handicapA2.setText(handicapC1);
                    addIConA1.setText("REMOVE");

                    SharedPreferences pref = this.getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("player1Team", "1");
                    editor.commit();
                }
            }

            user2 = mSharedPreferences.getString("user_id2",null);

            if (user2!=null) {

                if (!user2.equalsIgnoreCase(user1)&& !user2.equalsIgnoreCase(user3)) {
                    String name2 = mSharedPreferences.getString("name2", null);
                    String handicap2 = mSharedPreferences.getString("handicap2", null);
                    String adminIMAGE2 = mSharedPreferences.getString("image2", null);
                    String text2 = mSharedPreferences.getString("text2", null);

                    String nameC2 = mSharedPreferences.getString("player2Name", null);
                    String handicapC2 = mSharedPreferences.getString("player2Handicap", null);
                    String textC2 = mSharedPreferences.getString("player2Email", null);

                    if (name2 != null && handicap2 != null && text2 != null) {
                        nameB1.setText(name2);
                        handicapB1.setText(handicap2);
                        addIConB1.setText(text2);
                    } else if (nameC2 != null && handicapC2 != null && textC2 != null) {
                        nameB1.setText(nameC2);
                        handicapB1.setText(handicapC2);
                        addIConB1.setText("REMOVE");
                    }
                    if (adminIMAGE2 != null && adminIMAGE2.length()>10) {

                        Picasso.with(this).load(adminIMAGE2).into(profilePicB1);
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "You have already selected that player", Toast.LENGTH_SHORT).show();
                }
            }else {
                String nameC2 = mSharedPreferences.getString("player2Name", null);
                String handicapC2 = mSharedPreferences.getString("player2Handicap", null);
                String textC2 = mSharedPreferences.getString("player2Email", null);

                if (nameC2 != null && handicapC2 != null && textC2 != null) {
                    nameB1.setText(nameC2);
                    handicapB1.setText(handicapC2);
                    addIConB1.setText("REMOVE");

                    SharedPreferences pref = this.getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("player2Team", "2");
                    editor.commit();
                }
            }

            user3 = mSharedPreferences.getString("user_id3",null);
            if (user3!=null) {

                if (!user3.equalsIgnoreCase(user1)&& !user3.equalsIgnoreCase(user2)) {
                    String name3 = mSharedPreferences.getString("name3", null);
                    String handicap3 = mSharedPreferences.getString("handicap3", null);
                    String adminIMAGE3 = mSharedPreferences.getString("image3", null);
                    String text3 = mSharedPreferences.getString("text3", null);

                    String nameC3 = mSharedPreferences.getString("player3Name", null);
                    String handicapC3 = mSharedPreferences.getString("player3Handicap", null);
                    String textC3 = mSharedPreferences.getString("player3Email", null);

                    if (name3 != null && handicap3 != null && text3 != null) {
                        nameB2.setText(name3);
                        handicapB2.setText(handicap3);
                        addIConB2.setText(text3);
                    } else if (nameC3 != null && handicapC3 != null && textC3 != null) {
                        nameB2.setText(nameC3);
                        handicapB2.setText(handicapC3);
                        addIConB2.setText("REMOVE");
                    }
                    if (adminIMAGE3 != null && adminIMAGE3.length()>10) {
                        Picasso.with(this).load(adminIMAGE3).into(profilePicB2);
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "You have already selected that player", Toast.LENGTH_SHORT).show();
                }
            }else {

                String nameC3 = mSharedPreferences.getString("player3Name", null);
                String handicapC3 = mSharedPreferences.getString("player3Handicap", null);
                String textC3 = mSharedPreferences.getString("player3Email", null);

                 if (nameC3 != null && handicapC3 != null && textC3 != null) {
                    nameB2.setText(nameC3);
                    handicapB2.setText(handicapC3);
                    addIConB2.setText("REMOVE");

                     SharedPreferences pref = this.getSharedPreferences("craete_event", Context.MODE_PRIVATE);
                     SharedPreferences.Editor editor = pref.edit();
                     editor.putString("player3Team", "2");
                     editor.commit();
                }
            }

        }



    }

}
