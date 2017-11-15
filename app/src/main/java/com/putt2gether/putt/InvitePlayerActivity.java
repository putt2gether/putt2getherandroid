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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import com.putt2gether.R;
import com.putt2gether.utils.Constant;

/**
 * Created by Ajay on 17/10/2016.
 */
public class InvitePlayerActivity  extends AppCompatActivity {
    private RelativeLayout nextBTN;
    private TextView addIConPlayer1,addIConPlayer2,addIConPlayer3;
    private ImageView backBTN;
    private ImageView profilePicA1,profilePicPlayer2,profilePicPlayer3,profilePicPlayer4;
    private TextView namePlayer1,namePlayer2,namePlayer3,namePlayer4;
    private TextView handicapPlayer1,handicapPlayer2,handicapPlayer3,handicapPlayer4;
    Typeface Lato_Bold;
    Typeface Lato_Regular;
    private SharedPreferences mSharedPreferences;
    private Constant constant;
    private String player1,player2,player3;
    private String player1Name,player2Name,player3Name,player4Name;
    private String player_number;
    private RelativeLayout layout1,layout2,layout3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);
        mSharedPreferences = getSharedPreferences("craete_event", Context.MODE_PRIVATE);

        player_number = mSharedPreferences.getString("player_nnno",null);

        layout3 = (RelativeLayout)findViewById(R.id.teamB_player2_layout);
        layout2 = (RelativeLayout)findViewById(R.id.teamB_player1_layout);
        layout1 = (RelativeLayout)findViewById(R.id.teamA_player2_layout);

        if (player_number.equalsIgnoreCase("2")){

            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
            layout1.setVisibility(View.VISIBLE);

        }else if (player_number.equalsIgnoreCase("3")){

            layout3.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);

        }else {
            layout3.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
        }

        constant = new Constant();
        Lato_Bold = Typeface.createFromAsset(getAssets(), constant.Lato_Bold);
        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);


        backBTN = (ImageView)findViewById(R.id.back_invite_players);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        namePlayer1 = (TextView)findViewById(R.id.invite_player1);
        profilePicA1 = (ImageView)findViewById(R.id.invite_player1_image);
        handicapPlayer1 = (TextView)findViewById(R.id.teamA_player1_age);


        namePlayer2 = (TextView)findViewById(R.id.invite_player2);
        profilePicPlayer2 = (ImageView)findViewById(R.id.invite_player2_image);
        handicapPlayer2 = (TextView)findViewById(R.id.teamA_player2_age);
        addIConPlayer1 = (TextView) findViewById(R.id.add_remove_btn_invite_player2);


        namePlayer3 = (TextView)findViewById(R.id.invite_player3);
        profilePicPlayer3 = (ImageView)findViewById(R.id.invite_player3_image);
        handicapPlayer3 = (TextView)findViewById(R.id.teamB_player1_age);
        addIConPlayer2 = (TextView)findViewById(R.id.add_remove_btn_invite_player3);

        namePlayer4 = (TextView)findViewById(R.id.invite_player4);
        profilePicPlayer4 = (ImageView)findViewById(R.id.invite_player4_image);
        handicapPlayer4 = (TextView)findViewById(R.id.teamB_player2_age);
        addIConPlayer3 = (TextView)findViewById(R.id.add_remove_btn_invite_player4);

        nextBTN = (RelativeLayout)findViewById(R.id.countinue_invite_player_btn);
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player1Name = namePlayer1.getText().toString();
                player2Name = namePlayer2.getText().toString();
                player3Name = namePlayer3.getText().toString();
                player4Name = namePlayer4.getText().toString();

                if (player1Name.equalsIgnoreCase("ADD PLAYER")&& player2Name.equalsIgnoreCase("ADD PLAYER")&& player3Name.equalsIgnoreCase("ADD PLAYER")){
                    Toast.makeText(getApplicationContext(),"PLEASE ADD ALL PLAYER",Toast.LENGTH_LONG).show();
                }else {

                    Intent intent = new Intent(getApplicationContext(), EventPreviewActivity.class);

                    intent.putExtra("invite_player1", player1);
                    intent.putExtra("invite_player2", player2);
                    intent.putExtra("invite_player3", player3);

                    startActivity(intent);
                }
            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String button1 = addIConPlayer1.getText().toString();
                if (button1.equalsIgnoreCase("add")){
                    if(mSharedPreferences==null)
                        return;
                    Intent intent = new Intent(getApplicationContext(),TeamTabLayout.class);
                    intent.putExtra("playerNo",player_number);
                    SharedPreferences.Editor editor1 = mSharedPreferences.edit();
                    editor1.putString("tag", "tag1");

                    editor1.putString("activity_type", "invite");
                    editor1.commit();
                    startActivity(intent);
                    finish();
                }else {

                    SharedPreferences.Editor editorRemove = mSharedPreferences.edit();
                    editorRemove.remove("name1");
                    editorRemove.remove("handicap1");
                    editorRemove.remove("image1");
                    editorRemove.remove("text1");

                    editorRemove.remove("player1Email");
                    editorRemove.remove("player1Name");
                    editorRemove.remove("player1Handicap");

                    editorRemove.commit();
                    Intent intent = new Intent(getApplicationContext(),InvitePlayerActivity.class);
                    intent.putExtra("playerNo",player_number);
                    startActivity(intent);
                    finish();

                }

            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button2 = addIConPlayer2.getText().toString();
                if (button2.equalsIgnoreCase("add")) {

                    if (mSharedPreferences == null)
                        return;
                    Intent intent = new Intent(getApplicationContext(), TeamTabLayout.class);
                    intent.putExtra("playerNo",player_number);

                    SharedPreferences.Editor editor1 = mSharedPreferences.edit();
                    editor1.putString("tag", "tag2");
                    editor1.putString("activity_type", "invite");
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

                    editorRemove.commit();
                    Intent intent = new Intent(getApplicationContext(),InvitePlayerActivity.class);
                    intent.putExtra("playerNo",player_number);
                    startActivity(intent);
                    finish();
                }
            }
        });


        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button3 = addIConPlayer3.getText().toString();
                if (button3.equalsIgnoreCase("add")) {

                    if (mSharedPreferences == null)
                        return;
                    Intent intent = new Intent(getApplicationContext(), TeamTabLayout.class);
                    intent.putExtra("playerNo",player_number);

                    SharedPreferences.Editor editor1 = mSharedPreferences.edit();
                    editor1.putString("tag", "tag3");
                    editor1.putString("activity_type", "invite");
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

                    editorRemove.commit();
                    Intent intent = new Intent(getApplicationContext(),InvitePlayerActivity.class);
                    intent.putExtra("playerNo",player_number);
                    startActivity(intent);
                    finish();
                }
            }
        });


        SharedPreferences mSharedPreferencesADMIN = getSharedPreferences("user_preference", Context.MODE_PRIVATE);

        final String adminNAME = mSharedPreferencesADMIN.getString("displayName",null);
        final String adminHANDICAP = mSharedPreferencesADMIN.getString("user_handicap",null);
        final String adminIMAGE = mSharedPreferencesADMIN.getString("user_image",null);
        namePlayer1.setText(adminNAME);
        handicapPlayer1.setText(adminHANDICAP);
        if (adminIMAGE.length()!=0){
            Picasso.with(this).load(adminIMAGE).into(profilePicA1);
        }



        final String tag = mSharedPreferences.getString("tag",null);
        if (tag !=null) {
            Log.v("ddd",tag+",,"+tag.length());

            player1 = mSharedPreferences.getString("user_id1",null);

            if (player1!=null) {

                if (!player1.equalsIgnoreCase(player2) && !player1.equalsIgnoreCase(player3)) {

                    String name1 = mSharedPreferences.getString("name1", null);
                    String handicap1 = mSharedPreferences.getString("handicap1", null);
                    String adminIMAGE1 = mSharedPreferences.getString("image1", null);
                    String text1 = mSharedPreferences.getString("text1", null);

                    String nameC1 = mSharedPreferences.getString("player1Name", null);
                    String handicapC1 = mSharedPreferences.getString("player1Handicap", null);
                    String textC1 = mSharedPreferences.getString("player1Email", null);


                    if (name1 != null && handicap1 != null && text1 != null) {
                        namePlayer2.setText(name1);
                        handicapPlayer2.setText(handicap1);
                        addIConPlayer1.setText(text1);
                    } else if (nameC1 != null && handicapC1 != null && textC1 != null) {
                        namePlayer2.setText(nameC1);
                        handicapPlayer2.setText(handicapC1);
                        addIConPlayer1.setText("REMOVE");
                    }
                    Log.v("image","image"+adminIMAGE1);

                    if (adminIMAGE1 != null && adminIMAGE1.length()>10) {
                        Log.v("image",""+adminIMAGE1);
                        Picasso.with(this).load(adminIMAGE1).into(profilePicPlayer2);
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "You have already selected that player", Toast.LENGTH_SHORT).show();
                }
            }else {
                String nameC1 = mSharedPreferences.getString("player1Name", null);
                String handicapC1 = mSharedPreferences.getString("player1Handicap", null);
                String textC1 = mSharedPreferences.getString("player1Email", null);

                if (nameC1 != null && handicapC1 != null && textC1 != null) {
                    namePlayer2.setText(nameC1);
                    handicapPlayer2.setText(handicapC1);
                    addIConPlayer1.setText("REMOVE");
                }
            }

            player2 = mSharedPreferences.getString("user_id2",null);

            if (player2!=null) {

                if (!player2.equalsIgnoreCase(player1)&&!player2.equalsIgnoreCase(player3)) {
                    String name2 = mSharedPreferences.getString("name2", null);
                    String handicap2 = mSharedPreferences.getString("handicap2", null);
                    String adminIMAGE2 = mSharedPreferences.getString("image2", null);
                    String text2 = mSharedPreferences.getString("text2", null);

                    String nameC2 = mSharedPreferences.getString("player2Name", null);
                    String handicapC2 = mSharedPreferences.getString("player2Handicap", null);
                    String textC2 = mSharedPreferences.getString("player2Email", null);

                    if (name2 != null && handicap2 != null && text2 != null) {
                        namePlayer3.setText(name2);
                        handicapPlayer3.setText(handicap2);
                        addIConPlayer2.setText(text2);
                    } else if (nameC2 != null && handicapC2 != null && textC2 != null) {
                        namePlayer3.setText(nameC2);
                        handicapPlayer3.setText(handicapC2);
                        addIConPlayer2.setText("REMOVE");
                    }
                    if (adminIMAGE2 != null && adminIMAGE2.length()>10) {
                        Log.v("adminIMAGE2", adminIMAGE2);
                           Picasso.with(this).load(adminIMAGE2).into(profilePicPlayer3);
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "You have already selected that player", Toast.LENGTH_SHORT).show();
                }

            }else {
                String nameC2 = mSharedPreferences.getString("player2Name", null);
                String handicapC2 = mSharedPreferences.getString("player2Handicap", null);
                String textC2 = mSharedPreferences.getString("player2Email", null);

               if (nameC2 != null && handicapC2 != null && textC2 != null) {
                    namePlayer3.setText(nameC2);
                    handicapPlayer3.setText(handicapC2);
                    addIConPlayer2.setText("REMOVE");
                }
            }




            player3 = mSharedPreferences.getString("user_id3",null);

            if (player3!=null) {
                if (!player3.equalsIgnoreCase(player1) && !player3.equalsIgnoreCase(player2)) {

                    String name3 = mSharedPreferences.getString("name3", null);
                    String handicap3 = mSharedPreferences.getString("handicap3", null);
                    String adminIMAGE3 = mSharedPreferences.getString("image3", null);
                    String text3 = mSharedPreferences.getString("text3", null);

                    String nameC3 = mSharedPreferences.getString("player3Name", null);
                    String handicapC3 = mSharedPreferences.getString("player3Handicap", null);
                    String textC3 = mSharedPreferences.getString("player3Email", null);

                    if (name3 != null && handicap3 != null && text3 != null) {
                        namePlayer4.setText(name3);
                        handicapPlayer4.setText(handicap3);
                        addIConPlayer3.setText(text3);
                    } else if (nameC3 != null && handicapC3 != null && textC3 != null) {
                        namePlayer4.setText(nameC3);
                        handicapPlayer4.setText(handicapC3);
                        addIConPlayer3.setText("REMOVE");
                    }
                    if (adminIMAGE3 != null && adminIMAGE3.length()>10) {

                        Picasso.with(this).load(adminIMAGE3).into(profilePicPlayer4);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "You have already selected that player", Toast.LENGTH_SHORT).show();
                }
            }else {

                String nameC3 = mSharedPreferences.getString("player3Name", null);
                String handicapC3 = mSharedPreferences.getString("player3Handicap", null);
                String textC3 = mSharedPreferences.getString("player3Email", null);

                if (nameC3 != null && handicapC3 != null && textC3 != null) {
                    namePlayer4.setText(nameC3);
                    handicapPlayer4.setText(handicapC3);
                    addIConPlayer3.setText("REMOVE");
                }
            }


        }



    }

}