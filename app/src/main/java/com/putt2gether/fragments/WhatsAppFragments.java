package com.putt2gether.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.putt2gether.R;

/**
 * Created by Ajay on 23/06/2016.
 */
public class WhatsAppFragments extends android.support.v4.app.Fragment {

    private ImageView shareBTN;
    private LinearLayout shareBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_whatsapp, container, false);

        shareBTN = (ImageView)rootView.findViewById(R.id.share_icon_whatsapp);
        shareBtn = (LinearLayout)rootView.findViewById(R.id.share_via_whatsapp);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getActivity().getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                final String user_name = pref.getString("createby",null);
                final String golfcoursename = pref.getString("golfcourseName",null);
                final String dateTime = pref.getString("dateTime",null);


                String str = "  Hi! I’m inviting you to a golf event "+ "'"+user_name+"'" +" at "+ "'"+golfcoursename+"'" +" using the putt2gether app.\n" +
                        "\n" +
                        "Follow these 3 STEPS to join the event.\n" +
                        "\n" +
                        "1: Download putt2gether https://itunes.apple.com/us/app/putt2gether-live-leaderboard/id1002496721?mt=8\n" +
                        "\n" +
                        "https://play.google.com/store/apps/details?id=com.putt2gether \n" +
                        "\n" +
                        "2: Go to 'Request to Participate' under Invites section\n" +
                        "\n" +
                        "3: Select "+ "'"+golfcoursename+"'"+", Select "+"'"+dateTime+"'"+", select "+"'"+user_name+"'"+ " and send the request to join the event.\n" +
                        "\n" +
                        "Thanks  ";


                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, str);
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    // ToastHelper.MakeShortText("Whatsapp have not been installed.");
                }

            }
        });


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


}
