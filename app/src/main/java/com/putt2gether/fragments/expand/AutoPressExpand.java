package com.putt2gether.fragments.expand;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.putt2gether.R;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.putt.addScore.AddScoreNew;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 23/02/2017.
 */
public class AutoPressExpand extends Fragment {



    ImageView finishdialogfrag;

    String eventID;
    LinearLayout r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18;

    TextView hole1,hole2,hole3,hole4,hole5,hole6,hole7,hole8,hole9,hole10,hole11,hole12,hole13,hole14,hole15,hole16,hole17,hole18;

    TextView f1_r1,f1_r2,f1_r3,f1_r4,f1_r5,f1_r6,f1_r7,f1_r8,f1_r9,f1_r10;
    TextView f2_r1,f2_r2,f2_r3,f2_r4,f2_r5,f2_r6,f2_r7,f2_r8,f2_r9,f2_r10;
    TextView f3_r1,f3_r2,f3_r3,f3_r4,f3_r5,f3_r6,f3_r7,f3_r8,f3_r9,f3_r10;
    TextView f4_r1,f4_r2,f4_r3,f4_r4,f4_r5,f4_r6,f4_r7,f4_r8,f4_r9,f4_r10;
    TextView f5_r1,f5_r2,f5_r3,f5_r4,f5_r5,f5_r6,f5_r7,f5_r8,f5_r9,f5_r10;
    TextView f6_r1,f6_r2,f6_r3,f6_r4,f6_r5,f6_r6,f6_r7,f6_r8,f6_r9,f6_r10;
    TextView f7_r1,f7_r2,f7_r3,f7_r4,f7_r5,f7_r6,f7_r7,f7_r8,f7_r9,f7_r10;
    TextView f8_r1,f8_r2,f8_r3,f8_r4,f8_r5,f8_r6,f8_r7,f8_r8,f8_r9,f8_r10;
    TextView f9_r1,f9_r2,f9_r3,f9_r4,f9_r5,f9_r6,f9_r7,f9_r8,f9_r9,f9_r10;
    TextView f10_r1,f10_r2,f10_r3,f10_r4,f10_r5,f10_r6,f10_r7,f10_r8,f10_r9,f10_r10;

    TextView b1_r1,b1_r2,b1_r3,b1_r4,b1_r5,b1_r6,b1_r7,b1_r8,b1_r9,b1_r10;
    TextView b2_r1,b2_r2,b2_r3,b2_r4,b2_r5,b2_r6,b2_r7,b2_r8,b2_r9,b2_r10;
    TextView b3_r1,b3_r2,b3_r3,b3_r4,b3_r5,b3_r6,b3_r7,b3_r8,b3_r9,b3_r10;
    TextView b4_r1,b4_r2,b4_r3,b4_r4,b4_r5,b4_r6,b4_r7,b4_r8,b4_r9,b4_r10;
    TextView b5_r1,b5_r2,b5_r3,b5_r4,b5_r5,b5_r6,b5_r7,b5_r8,b5_r9,b5_r10;
    TextView b6_r1,b6_r2,b6_r3,b6_r4,b6_r5,b6_r6,b6_r7,b6_r8,b6_r9,b6_r10;
    TextView b7_r1,b7_r2,b7_r3,b7_r4,b7_r5,b7_r6,b7_r7,b7_r8,b7_r9,b7_r10;
    TextView b8_r1,b8_r2,b8_r3,b8_r4,b8_r5,b8_r6,b8_r7,b8_r8,b8_r9,b8_r10;
    TextView b9_r1,b9_r2,b9_r3,b9_r4,b9_r5,b9_r6,b9_r7,b9_r8,b9_r9,b9_r10;
    TextView b10_r1,b10_r2,b10_r3,b10_r4,b10_r5,b10_r6,b10_r7,b10_r8,b10_r9,b10_r10;

    TextView f1_r11,f1_r12,f1_r13,f1_r14,f1_r15,f1_r16,f1_r17,f1_r18,f1_r19,f1_r20;
    TextView f2_r11,f2_r12,f2_r13,f2_r14,f2_r15,f2_r16,f2_r17,f2_r18,f2_r19,f2_r20;
    TextView f3_r11,f3_r12,f3_r13,f3_r14,f3_r15,f3_r16,f3_r17,f3_r18,f3_r19,f3_r20;
    TextView f4_r11,f4_r12,f4_r13,f4_r14,f4_r15,f4_r16,f4_r17,f4_r18,f4_r19,f4_r20;
    TextView f5_r11,f5_r12,f5_r13,f5_r14,f5_r15,f5_r16,f5_r17,f5_r18,f5_r19,f5_r20;
    TextView f6_r11,f6_r12,f6_r13,f6_r14,f6_r15,f6_r16,f6_r17,f6_r18,f6_r19,f6_r20;
    TextView f7_r11,f7_r12,f7_r13,f7_r14,f7_r15,f7_r16,f7_r17,f7_r18,f7_r19,f7_r20;
    TextView f8_r11,f8_r12,f8_r13,f8_r14,f8_r15,f8_r16,f8_r17,f8_r18,f8_r19,f8_r20;
    TextView f9_r11,f9_r12,f9_r13,f9_r14,f9_r15,f9_r16,f9_r17,f9_r18,f9_r19,f9_r20;
    TextView f10_r11,f10_r12,f10_r13,f10_r14,f10_r15,f10_r16,f10_r17,f10_r18,f10_r19,f10_r20;

    TextView b1_r11,b1_r12,b1_r13,b1_r14,b1_r15,b1_r16,b1_r17,b1_r18,b1_r19,b1_r20;
    TextView b2_r11,b2_r12,b2_r13,b2_r14,b2_r15,b2_r16,b2_r17,b2_r18,b2_r19,b2_r20;
    TextView b3_r11,b3_r12,b3_r13,b3_r14,b3_r15,b3_r16,b3_r17,b3_r18,b3_r19,b3_r20;
    TextView b4_r11,b4_r12,b4_r13,b4_r14,b4_r15,b4_r16,b4_r17,b4_r18,b4_r19,b4_r20;
    TextView b5_r11,b5_r12,b5_r13,b5_r14,b5_r15,b5_r16,b5_r17,b5_r18,b5_r19,b5_r20;
    TextView b6_r11,b6_r12,b6_r13,b6_r14,b6_r15,b6_r16,b6_r17,b6_r18,b6_r19,b6_r20;
    TextView b7_r11,b7_r12,b7_r13,b7_r14,b7_r15,b7_r16,b7_r17,b7_r18,b7_r19,b7_r20;
    TextView b8_r11,b8_r12,b8_r13,b8_r14,b8_r15,b8_r16,b8_r17,b8_r18,b8_r19,b8_r20;
    TextView b9_r11,b9_r12,b9_r13,b9_r14,b9_r15,b9_r16,b9_r17,b9_r18,b9_r19,b9_r20;
    TextView b10_r11,b10_r12,b10_r13,b10_r14,b10_r15,b10_r16,b10_r17,b10_r18,b10_r19,b10_r20;
    LinearLayout front1_hole1,front1_hole2,front1_hole3,front1_hole4,front1_hole5,front1_hole6,front1_hole7,front1_hole8,front1_hole9,front1_hole10,front1_hole11,front1_hole12,front1_hole13,front1_hole14,front1_hole15,front1_hole16,front1_hole17,front1_hole18;
    LinearLayout back1_hole1,back1_hole2,back1_hole3,back1_hole4,back1_hole5,back1_hole6,back1_hole7,back1_hole8,back1_hole9,back1_hole10,back1_hole11,back1_hole12,back1_hole13,back1_hole14,back1_hole15,back1_hole16,back1_hole17,back1_hole18;
    TextView and1,and2,and3,and4,and5,and6,and7,and8,and9,and10,and11,and12,and13,and14,and15,and16,and17,and18;






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addscoretabl, container, false);

        finishdialogfrag=(ImageView)view.findViewById(R.id.finishdialogfrag);

        front1_hole1 = (LinearLayout)view.findViewById(R.id.front_layout_r1);
        front1_hole2 = (LinearLayout)view.findViewById(R.id.front_layout_r2);
        front1_hole3 = (LinearLayout)view.findViewById(R.id.front_layout_r3);
        front1_hole4 = (LinearLayout)view.findViewById(R.id.front_layout_r4);
        front1_hole5 = (LinearLayout)view.findViewById(R.id.front_layout_r5);
        front1_hole6 = (LinearLayout)view.findViewById(R.id.front_layout_r6);
        front1_hole7 = (LinearLayout)view.findViewById(R.id.front_layout_r7);
        front1_hole8 = (LinearLayout)view.findViewById(R.id.front_layout_r8);
        front1_hole9 = (LinearLayout)view.findViewById(R.id.front_layout_r9);
        front1_hole10 = (LinearLayout)view.findViewById(R.id.front_layout_r10);
        front1_hole11 = (LinearLayout)view.findViewById(R.id.front_layout_r11);
        front1_hole12 = (LinearLayout)view.findViewById(R.id.front_layout_r12);
        front1_hole13 = (LinearLayout)view.findViewById(R.id.front_layout_r13);
        front1_hole14 = (LinearLayout)view.findViewById(R.id.front_layout_r14);
        front1_hole15 = (LinearLayout)view.findViewById(R.id.front_layout_r15);
        front1_hole16 = (LinearLayout)view.findViewById(R.id.front_layout_r16);
        front1_hole17 = (LinearLayout)view.findViewById(R.id.front_layout_r17);
        front1_hole18 = (LinearLayout)view.findViewById(R.id.front_layout_r18);

        back1_hole1 = (LinearLayout)view.findViewById(R.id.back_layout_r1);
        back1_hole2 = (LinearLayout)view.findViewById(R.id.back_layout_r2);
        back1_hole3 = (LinearLayout)view.findViewById(R.id.back_layout_r3);
        back1_hole4 = (LinearLayout)view.findViewById(R.id.back_layout_r4);
        back1_hole5 = (LinearLayout)view.findViewById(R.id.back_layout_r5);
        back1_hole6 = (LinearLayout)view.findViewById(R.id.back_layout_r6);
        back1_hole7 = (LinearLayout)view.findViewById(R.id.back_layout_r7);
        back1_hole8 = (LinearLayout)view.findViewById(R.id.back_layout_r8);
        back1_hole9 = (LinearLayout)view.findViewById(R.id.back_layout_r9);
        back1_hole10 = (LinearLayout)view.findViewById(R.id.back_layout_r10);
        back1_hole11 = (LinearLayout)view.findViewById(R.id.back_layout_r11);
        back1_hole12 = (LinearLayout)view.findViewById(R.id.back_layout_r12);
        back1_hole13 = (LinearLayout)view.findViewById(R.id.back_layout_r13);
        back1_hole14 = (LinearLayout)view.findViewById(R.id.back_layout_r14);
        back1_hole15 = (LinearLayout)view.findViewById(R.id.back_layout_r15);
        back1_hole16 = (LinearLayout)view.findViewById(R.id.back_layout_r16);
        back1_hole17 = (LinearLayout)view.findViewById(R.id.back_layout_r17);
        back1_hole18 = (LinearLayout)view.findViewById(R.id.back_layout_r18);

        and1 = (TextView)view.findViewById(R.id.andText_r1);
        and2 = (TextView)view.findViewById(R.id.andText_r2);
        and3 = (TextView)view.findViewById(R.id.andText_r3);
        and4 = (TextView)view.findViewById(R.id.andText_r4);
        and5 = (TextView)view.findViewById(R.id.andText_r5);
        and6 = (TextView)view.findViewById(R.id.andText_r6);
        and7 = (TextView)view.findViewById(R.id.andText_r7);
        and8 = (TextView)view.findViewById(R.id.andText_r8);
        and9 = (TextView)view.findViewById(R.id.andText_r9);
        and10 = (TextView)view.findViewById(R.id.andText_r10);
        and11 = (TextView)view.findViewById(R.id.andText_r11);
        and12 = (TextView)view.findViewById(R.id.andText_r12);
        and13 = (TextView)view.findViewById(R.id.andText_r13);
        and14 = (TextView)view.findViewById(R.id.andText_r14);
        and15 = (TextView)view.findViewById(R.id.andText_r15);
        and16 = (TextView)view.findViewById(R.id.andText_r16);
        and17 = (TextView)view.findViewById(R.id.andText_r17);
        and18 = (TextView)view.findViewById(R.id.andText_r18);



        hole1 = (TextView)view.findViewById(R.id.hole_auto_r1);
        hole2 = (TextView)view.findViewById(R.id.hole_auto_r2);
        hole3 = (TextView)view.findViewById(R.id.hole_auto_r3);
        hole4 = (TextView)view.findViewById(R.id.hole_auto_r4);
        hole5 = (TextView)view.findViewById(R.id.hole_auto_r5);
        hole6 = (TextView)view.findViewById(R.id.hole_auto_r6);
        hole7 = (TextView)view.findViewById(R.id.hole_auto_r7);
        hole8 = (TextView)view.findViewById(R.id.hole_auto_r8);
        hole9 = (TextView)view.findViewById(R.id.hole_auto_r9);
        hole10 = (TextView)view.findViewById(R.id.hole_auto_r10);
        hole11 = (TextView)view.findViewById(R.id.hole_auto_r11);
        hole12 = (TextView)view.findViewById(R.id.hole_auto_r12);
        hole13 = (TextView)view.findViewById(R.id.hole_auto_r13);
        hole14 = (TextView)view.findViewById(R.id.hole_auto_r14);
        hole15 = (TextView)view.findViewById(R.id.hole_auto_r15);
        hole16 = (TextView)view.findViewById(R.id.hole_auto_r16);
        hole17 = (TextView)view.findViewById(R.id.hole_auto_r17);
        hole18 = (TextView)view.findViewById(R.id.hole_auto_r18);

        r1 = (LinearLayout)view.findViewById(R.id.r1);
        r2 = (LinearLayout)view.findViewById(R.id.r2);
        r3 = (LinearLayout)view.findViewById(R.id.r3);
        r4 = (LinearLayout)view.findViewById(R.id.r4);
        r5 = (LinearLayout)view.findViewById(R.id.r5);
        r6 = (LinearLayout)view.findViewById(R.id.r6);
        r7 = (LinearLayout)view.findViewById(R.id.r7);
        r8 = (LinearLayout)view.findViewById(R.id.r8);
        r9 = (LinearLayout)view.findViewById(R.id.r9);
        r10 = (LinearLayout)view.findViewById(R.id.r10);
        r11 = (LinearLayout)view.findViewById(R.id.r11);
        r12 = (LinearLayout)view.findViewById(R.id.r12);
        r13 = (LinearLayout)view.findViewById(R.id.r13);
        r14 = (LinearLayout)view.findViewById(R.id.r14);
        r15 = (LinearLayout)view.findViewById(R.id.r15);
        r16 = (LinearLayout)view.findViewById(R.id.r16);
        r17 = (LinearLayout)view.findViewById(R.id.r17);
        r18 = (LinearLayout)view.findViewById(R.id.r18);



        f1_r1 = (TextView)view.findViewById(R.id.f1_r1);
        f1_r2 = (TextView)view.findViewById(R.id.f1_r2);
        f1_r3 = (TextView)view.findViewById(R.id.f1_r3);
        f1_r4 = (TextView)view.findViewById(R.id.f1_r4);
        f1_r5 = (TextView)view.findViewById(R.id.f1_r5);
        f1_r6 = (TextView)view.findViewById(R.id.f1_r6);
        f1_r7 = (TextView)view.findViewById(R.id.f1_r7);
        f1_r8 = (TextView)view.findViewById(R.id.f1_r8);
        f1_r9 = (TextView)view.findViewById(R.id.f1_r9);
        f1_r10 = (TextView)view.findViewById(R.id.f1_r10);
        b1_r1 = (TextView)view.findViewById(R.id.b1_r1);
        b1_r2 = (TextView)view.findViewById(R.id.b1_r2);
        b1_r3 = (TextView)view.findViewById(R.id.b1_r3);
        b1_r4 = (TextView)view.findViewById(R.id.b1_r4);
        b1_r5 = (TextView)view.findViewById(R.id.b1_r5);
        b1_r6 = (TextView)view.findViewById(R.id.b1_r6);
        b1_r7 = (TextView)view.findViewById(R.id.b1_r7);
        b1_r8 = (TextView)view.findViewById(R.id.b1_r8);
        b1_r9 = (TextView)view.findViewById(R.id.b1_r9);
        b1_r10 = (TextView)view.findViewById(R.id.b1_r10);

        f2_r1 = (TextView)view.findViewById(R.id.f2_r1);
        f2_r2 = (TextView)view.findViewById(R.id.f2_r2);
        f2_r3 = (TextView)view.findViewById(R.id.f2_r3);
        f2_r4 = (TextView)view.findViewById(R.id.f2_r4);
        f2_r5 = (TextView)view.findViewById(R.id.f2_r5);
        f2_r6 = (TextView)view.findViewById(R.id.f2_r6);
        f2_r7 = (TextView)view.findViewById(R.id.f2_r7);
        f2_r8 = (TextView)view.findViewById(R.id.f2_r8);
        f2_r9 = (TextView)view.findViewById(R.id.f2_r9);
        f2_r10 = (TextView)view.findViewById(R.id.f2_r10);
        b2_r1 = (TextView)view.findViewById(R.id.b2_r1);
        b2_r2 = (TextView)view.findViewById(R.id.b2_r2);
        b2_r3 = (TextView)view.findViewById(R.id.b2_r3);
        b2_r4 = (TextView)view.findViewById(R.id.b2_r4);
        b2_r5 = (TextView)view.findViewById(R.id.b2_r5);
        b2_r6 = (TextView)view.findViewById(R.id.b2_r6);
        b2_r7 = (TextView)view.findViewById(R.id.b2_r7);
        b2_r8 = (TextView)view.findViewById(R.id.b2_r8);
        b2_r9 = (TextView)view.findViewById(R.id.b2_r9);
        b2_r10 = (TextView)view.findViewById(R.id.b2_r10);

        f3_r1 = (TextView)view.findViewById(R.id.f3_r1);
        f3_r2 = (TextView)view.findViewById(R.id.f3_r2);
        f3_r3 = (TextView)view.findViewById(R.id.f3_r3);
        f3_r4 = (TextView)view.findViewById(R.id.f3_r4);
        f3_r5 = (TextView)view.findViewById(R.id.f3_r5);
        f3_r6 = (TextView)view.findViewById(R.id.f3_r6);
        f3_r7 = (TextView)view.findViewById(R.id.f3_r7);
        f3_r8 = (TextView)view.findViewById(R.id.f3_r8);
        f3_r9 = (TextView)view.findViewById(R.id.f3_r9);
        f3_r10 = (TextView)view.findViewById(R.id.f3_r10);
        b3_r1 = (TextView)view.findViewById(R.id.b3_r1);
        b3_r2 = (TextView)view.findViewById(R.id.b3_r2);
        b3_r3 = (TextView)view.findViewById(R.id.b3_r3);
        b3_r4 = (TextView)view.findViewById(R.id.b3_r4);
        b3_r5 = (TextView)view.findViewById(R.id.b3_r5);
        b3_r6 = (TextView)view.findViewById(R.id.b3_r6);
        b3_r7 = (TextView)view.findViewById(R.id.b3_r7);
        b3_r8 = (TextView)view.findViewById(R.id.b3_r8);
        b3_r9 = (TextView)view.findViewById(R.id.b3_r9);
        b3_r10 = (TextView)view.findViewById(R.id.b3_r10);

        f4_r1 = (TextView)view.findViewById(R.id.f4_r1);
        f4_r2 = (TextView)view.findViewById(R.id.f4_r2);
        f4_r3 = (TextView)view.findViewById(R.id.f4_r3);
        f4_r4 = (TextView)view.findViewById(R.id.f4_r4);
        f4_r5 = (TextView)view.findViewById(R.id.f4_r5);
        f4_r6 = (TextView)view.findViewById(R.id.f4_r6);
        f4_r7 = (TextView)view.findViewById(R.id.f4_r7);
        f4_r8 = (TextView)view.findViewById(R.id.f4_r8);
        f4_r9 = (TextView)view.findViewById(R.id.f4_r9);
        f4_r10 = (TextView)view.findViewById(R.id.f4_r10);
        b4_r1 = (TextView)view.findViewById(R.id.b4_r1);
        b4_r2 = (TextView)view.findViewById(R.id.b4_r2);
        b4_r3 = (TextView)view.findViewById(R.id.b4_r3);
        b4_r4 = (TextView)view.findViewById(R.id.b4_r4);
        b4_r5 = (TextView)view.findViewById(R.id.b4_r5);
        b4_r6 = (TextView)view.findViewById(R.id.b4_r6);
        b4_r7 = (TextView)view.findViewById(R.id.b4_r7);
        b4_r8 = (TextView)view.findViewById(R.id.b4_r8);
        b4_r9 = (TextView)view.findViewById(R.id.b4_r9);
        b4_r10 = (TextView)view.findViewById(R.id.b4_r10);

        f5_r1 = (TextView)view.findViewById(R.id.f5_r1);
        f5_r2 = (TextView)view.findViewById(R.id.f5_r2);
        f5_r3 = (TextView)view.findViewById(R.id.f5_r3);
        f5_r4 = (TextView)view.findViewById(R.id.f5_r4);
        f5_r5 = (TextView)view.findViewById(R.id.f5_r5);
        f5_r6 = (TextView)view.findViewById(R.id.f5_r6);
        f5_r7 = (TextView)view.findViewById(R.id.f5_r7);
        f5_r8 = (TextView)view.findViewById(R.id.f5_r8);
        f5_r9 = (TextView)view.findViewById(R.id.f5_r9);
        f5_r10 = (TextView)view.findViewById(R.id.f5_r10);
        b5_r1 = (TextView)view.findViewById(R.id.b5_r1);
        b5_r2 = (TextView)view.findViewById(R.id.b5_r2);
        b5_r3 = (TextView)view.findViewById(R.id.b5_r3);
        b5_r4 = (TextView)view.findViewById(R.id.b5_r4);
        b5_r5 = (TextView)view.findViewById(R.id.b5_r5);
        b5_r6 = (TextView)view.findViewById(R.id.b5_r6);
        b5_r7 = (TextView)view.findViewById(R.id.b5_r7);
        b5_r8 = (TextView)view.findViewById(R.id.b5_r8);
        b5_r9 = (TextView)view.findViewById(R.id.b5_r9);
        b5_r10 = (TextView)view.findViewById(R.id.b5_r10);

        f6_r1 = (TextView)view.findViewById(R.id.f6_r1);
        f6_r2 = (TextView)view.findViewById(R.id.f6_r2);
        f6_r3 = (TextView)view.findViewById(R.id.f6_r3);
        f6_r4 = (TextView)view.findViewById(R.id.f6_r4);
        f6_r5 = (TextView)view.findViewById(R.id.f6_r5);
        f6_r6 = (TextView)view.findViewById(R.id.f6_r6);
        f6_r7 = (TextView)view.findViewById(R.id.f6_r7);
        f6_r8 = (TextView)view.findViewById(R.id.f6_r8);
        f6_r9 = (TextView)view.findViewById(R.id.f6_r9);
        f6_r10 = (TextView)view.findViewById(R.id.f6_r10);
        b6_r1 = (TextView)view.findViewById(R.id.b6_r1);
        b6_r2 = (TextView)view.findViewById(R.id.b6_r2);
        b6_r3 = (TextView)view.findViewById(R.id.b6_r3);
        b6_r4 = (TextView)view.findViewById(R.id.b6_r4);
        b6_r5 = (TextView)view.findViewById(R.id.b6_r5);
        b6_r6 = (TextView)view.findViewById(R.id.b6_r6);
        b6_r7 = (TextView)view.findViewById(R.id.b6_r7);
        b6_r8 = (TextView)view.findViewById(R.id.b6_r8);
        b6_r9 = (TextView)view.findViewById(R.id.b6_r9);
        b6_r10 = (TextView)view.findViewById(R.id.b6_r10);

        f7_r1 = (TextView)view.findViewById(R.id.f7_r1);
        f7_r2 = (TextView)view.findViewById(R.id.f7_r2);
        f7_r3 = (TextView)view.findViewById(R.id.f7_r3);
        f7_r4 = (TextView)view.findViewById(R.id.f7_r4);
        f7_r5 = (TextView)view.findViewById(R.id.f7_r5);
        f7_r6 = (TextView)view.findViewById(R.id.f7_r6);
        f7_r7 = (TextView)view.findViewById(R.id.f7_r7);
        f7_r8 = (TextView)view.findViewById(R.id.f7_r8);
        f7_r9 = (TextView)view.findViewById(R.id.f7_r9);
        f7_r10 = (TextView)view.findViewById(R.id.f7_r10);
        b7_r1 = (TextView)view.findViewById(R.id.b7_r1);
        b7_r2 = (TextView)view.findViewById(R.id.b7_r2);
        b7_r3 = (TextView)view.findViewById(R.id.b7_r3);
        b7_r4 = (TextView)view.findViewById(R.id.b7_r4);
        b7_r5 = (TextView)view.findViewById(R.id.b7_r5);
        b7_r6 = (TextView)view.findViewById(R.id.b7_r6);
        b7_r7 = (TextView)view.findViewById(R.id.b7_r7);
        b7_r8 = (TextView)view.findViewById(R.id.b7_r8);
        b7_r9 = (TextView)view.findViewById(R.id.b7_r9);
        b7_r10 = (TextView)view.findViewById(R.id.b7_r10);

        f8_r1 = (TextView)view.findViewById(R.id.f8_r1);
        f8_r2 = (TextView)view.findViewById(R.id.f8_r2);
        f8_r3 = (TextView)view.findViewById(R.id.f8_r3);
        f8_r4 = (TextView)view.findViewById(R.id.f8_r4);
        f8_r5 = (TextView)view.findViewById(R.id.f8_r5);
        f8_r6 = (TextView)view.findViewById(R.id.f8_r6);
        f8_r7 = (TextView)view.findViewById(R.id.f8_r7);
        f8_r8 = (TextView)view.findViewById(R.id.f8_r8);
        f8_r9 = (TextView)view.findViewById(R.id.f8_r9);
        f8_r10 = (TextView)view.findViewById(R.id.f8_r10);
        b8_r1 = (TextView)view.findViewById(R.id.b8_r1);
        b8_r2 = (TextView)view.findViewById(R.id.b8_r2);
        b8_r3 = (TextView)view.findViewById(R.id.b8_r3);
        b8_r4 = (TextView)view.findViewById(R.id.b8_r4);
        b8_r5 = (TextView)view.findViewById(R.id.b8_r5);
        b8_r6 = (TextView)view.findViewById(R.id.b8_r6);
        b8_r7 = (TextView)view.findViewById(R.id.b8_r7);
        b8_r8 = (TextView)view.findViewById(R.id.b8_r8);
        b8_r9 = (TextView)view.findViewById(R.id.b8_r9);
        b8_r10 = (TextView)view.findViewById(R.id.b8_r10);

        f9_r1 = (TextView)view.findViewById(R.id.f9_r1);
        f9_r2 = (TextView)view.findViewById(R.id.f9_r2);
        f9_r3 = (TextView)view.findViewById(R.id.f9_r3);
        f9_r4 = (TextView)view.findViewById(R.id.f9_r4);
        f9_r5 = (TextView)view.findViewById(R.id.f9_r5);
        f9_r6 = (TextView)view.findViewById(R.id.f9_r6);
        f9_r7 = (TextView)view.findViewById(R.id.f9_r7);
        f9_r8 = (TextView)view.findViewById(R.id.f9_r8);
        f9_r9 = (TextView)view.findViewById(R.id.f9_r9);
        f9_r10 = (TextView)view.findViewById(R.id.f9_r10);
        b9_r1 = (TextView)view.findViewById(R.id.b9_r1);
        b9_r2 = (TextView)view.findViewById(R.id.b9_r2);
        b9_r3 = (TextView)view.findViewById(R.id.b9_r3);
        b9_r4 = (TextView)view.findViewById(R.id.b9_r4);
        b9_r5 = (TextView)view.findViewById(R.id.b9_r5);
        b9_r6 = (TextView)view.findViewById(R.id.b9_r6);
        b9_r7 = (TextView)view.findViewById(R.id.b9_r7);
        b9_r8 = (TextView)view.findViewById(R.id.b9_r8);
        b9_r9 = (TextView)view.findViewById(R.id.b9_r9);
        b9_r10 = (TextView)view.findViewById(R.id.b9_r10);

        f10_r1 = (TextView)view.findViewById(R.id.f10_r1);
        f10_r2 = (TextView)view.findViewById(R.id.f10_r2);
        f10_r3 = (TextView)view.findViewById(R.id.f10_r3);
        f10_r4 = (TextView)view.findViewById(R.id.f10_r4);
        f10_r5 = (TextView)view.findViewById(R.id.f10_r5);
        f10_r6 = (TextView)view.findViewById(R.id.f10_r6);
        f10_r7 = (TextView)view.findViewById(R.id.f10_r7);
        f10_r8 = (TextView)view.findViewById(R.id.f10_r8);
        f10_r9 = (TextView)view.findViewById(R.id.f10_r9);
        f10_r10 = (TextView)view.findViewById(R.id.f10_r10);
        b10_r1 = (TextView)view.findViewById(R.id.b10_r1);
        b10_r2 = (TextView)view.findViewById(R.id.b10_r2);
        b10_r3 = (TextView)view.findViewById(R.id.b10_r3);
        b10_r4 = (TextView)view.findViewById(R.id.b10_r4);
        b10_r5 = (TextView)view.findViewById(R.id.b10_r5);
        b10_r6 = (TextView)view.findViewById(R.id.b10_r6);
        b10_r7 = (TextView)view.findViewById(R.id.b10_r7);
        b10_r8 = (TextView)view.findViewById(R.id.b10_r8);
        b10_r9 = (TextView)view.findViewById(R.id.b10_r9);
        b10_r10 = (TextView)view.findViewById(R.id.b10_r10);

        f1_r11 = (TextView)view.findViewById(R.id.f1_r11);
        f1_r12 = (TextView)view.findViewById(R.id.f1_r12);
        f1_r13 = (TextView)view.findViewById(R.id.f1_r13);
        f1_r14 = (TextView)view.findViewById(R.id.f1_r14);
        f1_r15 = (TextView)view.findViewById(R.id.f1_r15);
        f1_r16 = (TextView)view.findViewById(R.id.f1_r16);
        f1_r17 = (TextView)view.findViewById(R.id.f1_r17);
        f1_r18 = (TextView)view.findViewById(R.id.f1_r18);


        b1_r11 = (TextView)view.findViewById(R.id.b1_r11);
        b1_r12 = (TextView)view.findViewById(R.id.b1_r12);
        b1_r13 = (TextView)view.findViewById(R.id.b1_r13);
        b1_r14 = (TextView)view.findViewById(R.id.b1_r14);
        b1_r15 = (TextView)view.findViewById(R.id.b1_r15);
        b1_r16 = (TextView)view.findViewById(R.id.b1_r16);
        b1_r17 = (TextView)view.findViewById(R.id.b1_r17);
        b1_r18 = (TextView)view.findViewById(R.id.b1_r18);


        f2_r11 = (TextView)view.findViewById(R.id.f2_r11);
        f2_r12 = (TextView)view.findViewById(R.id.f2_r12);
        f2_r13 = (TextView)view.findViewById(R.id.f2_r13);
        f2_r14 = (TextView)view.findViewById(R.id.f2_r14);
        f2_r15 = (TextView)view.findViewById(R.id.f2_r15);
        f2_r16 = (TextView)view.findViewById(R.id.f2_r16);
        f2_r17 = (TextView)view.findViewById(R.id.f2_r17);
        f2_r18 = (TextView)view.findViewById(R.id.f2_r18);

        b2_r11 = (TextView)view.findViewById(R.id.b2_r11);
        b2_r12 = (TextView)view.findViewById(R.id.b2_r12);
        b2_r13 = (TextView)view.findViewById(R.id.b2_r13);
        b2_r14 = (TextView)view.findViewById(R.id.b2_r14);
        b2_r15 = (TextView)view.findViewById(R.id.b2_r15);
        b2_r16 = (TextView)view.findViewById(R.id.b2_r16);
        b2_r17 = (TextView)view.findViewById(R.id.b2_r17);
        b2_r18 = (TextView)view.findViewById(R.id.b2_r18);


        f3_r11 = (TextView)view.findViewById(R.id.f3_r11);
        f3_r12 = (TextView)view.findViewById(R.id.f3_r12);
        f3_r13 = (TextView)view.findViewById(R.id.f3_r13);
        f3_r14 = (TextView)view.findViewById(R.id.f3_r14);
        f3_r15 = (TextView)view.findViewById(R.id.f3_r15);
        f3_r16 = (TextView)view.findViewById(R.id.f3_r16);
        f3_r17 = (TextView)view.findViewById(R.id.f3_r17);
        f3_r18 = (TextView)view.findViewById(R.id.f3_r18);

        b3_r11 = (TextView)view.findViewById(R.id.b3_r11);
        b3_r12 = (TextView)view.findViewById(R.id.b3_r12);
        b3_r13 = (TextView)view.findViewById(R.id.b3_r13);
        b3_r14 = (TextView)view.findViewById(R.id.b3_r14);
        b3_r15 = (TextView)view.findViewById(R.id.b3_r15);
        b3_r16 = (TextView)view.findViewById(R.id.b3_r16);
        b3_r17 = (TextView)view.findViewById(R.id.b3_r17);
        b3_r18 = (TextView)view.findViewById(R.id.b3_r18);


        f4_r11 = (TextView)view.findViewById(R.id.f4_r11);
        f4_r12 = (TextView)view.findViewById(R.id.f4_r12);
        f4_r13 = (TextView)view.findViewById(R.id.f4_r13);
        f4_r14 = (TextView)view.findViewById(R.id.f4_r14);
        f4_r15 = (TextView)view.findViewById(R.id.f4_r15);
        f4_r16 = (TextView)view.findViewById(R.id.f4_r16);
        f4_r17 = (TextView)view.findViewById(R.id.f4_r17);
        f4_r18 = (TextView)view.findViewById(R.id.f4_r18);

        b4_r11 = (TextView)view.findViewById(R.id.b4_r11);
        b4_r12 = (TextView)view.findViewById(R.id.b4_r12);
        b4_r13 = (TextView)view.findViewById(R.id.b4_r13);
        b4_r14 = (TextView)view.findViewById(R.id.b4_r14);
        b4_r15 = (TextView)view.findViewById(R.id.b4_r15);
        b4_r16 = (TextView)view.findViewById(R.id.b4_r16);
        b4_r17 = (TextView)view.findViewById(R.id.b4_r17);
        b4_r18 = (TextView)view.findViewById(R.id.b4_r18);


        f5_r11 = (TextView)view.findViewById(R.id.f5_r11);
        f5_r12 = (TextView)view.findViewById(R.id.f5_r12);
        f5_r13 = (TextView)view.findViewById(R.id.f5_r13);
        f5_r14 = (TextView)view.findViewById(R.id.f5_r14);
        f5_r15 = (TextView)view.findViewById(R.id.f5_r15);
        f5_r16 = (TextView)view.findViewById(R.id.f5_r16);
        f5_r17 = (TextView)view.findViewById(R.id.f5_r17);
        f5_r18 = (TextView)view.findViewById(R.id.f5_r18);

        b5_r11 = (TextView)view.findViewById(R.id.b5_r11);
        b5_r12 = (TextView)view.findViewById(R.id.b5_r12);
        b5_r13 = (TextView)view.findViewById(R.id.b5_r13);
        b5_r14 = (TextView)view.findViewById(R.id.b5_r14);
        b5_r15 = (TextView)view.findViewById(R.id.b5_r15);
        b5_r16 = (TextView)view.findViewById(R.id.b5_r16);
        b5_r17 = (TextView)view.findViewById(R.id.b5_r17);
        b5_r18 = (TextView)view.findViewById(R.id.b5_r18);

        f6_r11 = (TextView)view.findViewById(R.id.f6_r11);
        f6_r12 = (TextView)view.findViewById(R.id.f6_r12);
        f6_r13 = (TextView)view.findViewById(R.id.f6_r13);
        f6_r14 = (TextView)view.findViewById(R.id.f6_r14);
        f6_r15 = (TextView)view.findViewById(R.id.f6_r15);
        f6_r16 = (TextView)view.findViewById(R.id.f6_r16);
        f6_r17 = (TextView)view.findViewById(R.id.f6_r17);
        f6_r18 = (TextView)view.findViewById(R.id.f6_r18);

        b6_r11 = (TextView)view.findViewById(R.id.b6_r11);
        b6_r12 = (TextView)view.findViewById(R.id.b6_r12);
        b6_r13 = (TextView)view.findViewById(R.id.b6_r13);
        b6_r14 = (TextView)view.findViewById(R.id.b6_r14);
        b6_r15 = (TextView)view.findViewById(R.id.b6_r15);
        b6_r16 = (TextView)view.findViewById(R.id.b6_r16);
        b6_r17 = (TextView)view.findViewById(R.id.b6_r17);
        b6_r18 = (TextView)view.findViewById(R.id.b6_r18);

        f7_r11 = (TextView)view.findViewById(R.id.f7_r11);
        f7_r12 = (TextView)view.findViewById(R.id.f7_r12);
        f7_r13 = (TextView)view.findViewById(R.id.f7_r13);
        f7_r14 = (TextView)view.findViewById(R.id.f7_r14);
        f7_r15 = (TextView)view.findViewById(R.id.f7_r15);
        f7_r16 = (TextView)view.findViewById(R.id.f7_r16);
        f7_r17 = (TextView)view.findViewById(R.id.f7_r17);
        f7_r18 = (TextView)view.findViewById(R.id.f7_r18);

        b7_r11 = (TextView)view.findViewById(R.id.b7_r11);
        b7_r12 = (TextView)view.findViewById(R.id.b7_r12);
        b7_r13 = (TextView)view.findViewById(R.id.b7_r13);
        b7_r14 = (TextView)view.findViewById(R.id.b7_r14);
        b7_r15 = (TextView)view.findViewById(R.id.b7_r15);
        b7_r16 = (TextView)view.findViewById(R.id.b7_r16);
        b7_r17 = (TextView)view.findViewById(R.id.b7_r17);
        b7_r18 = (TextView)view.findViewById(R.id.b7_r18);

        f8_r11 = (TextView)view.findViewById(R.id.f8_r11);
        f8_r12 = (TextView)view.findViewById(R.id.f8_r12);
        f8_r13 = (TextView)view.findViewById(R.id.f8_r13);
        f8_r14 = (TextView)view.findViewById(R.id.f8_r14);
        f8_r15 = (TextView)view.findViewById(R.id.f8_r15);
        f8_r16 = (TextView)view.findViewById(R.id.f8_r16);
        f8_r17 = (TextView)view.findViewById(R.id.f8_r17);
        f8_r18 = (TextView)view.findViewById(R.id.f8_r18);

        b8_r11 = (TextView)view.findViewById(R.id.b8_r11);
        b8_r12 = (TextView)view.findViewById(R.id.b8_r12);
        b8_r13 = (TextView)view.findViewById(R.id.b8_r13);
        b8_r14 = (TextView)view.findViewById(R.id.b8_r14);
        b8_r15 = (TextView)view.findViewById(R.id.b8_r15);
        b8_r16 = (TextView)view.findViewById(R.id.b8_r16);
        b8_r17 = (TextView)view.findViewById(R.id.b8_r17);
        b8_r18 = (TextView)view.findViewById(R.id.b8_r18);

        f9_r11 = (TextView)view.findViewById(R.id.f9_r11);
        f9_r12 = (TextView)view.findViewById(R.id.f9_r12);
        f9_r13 = (TextView)view.findViewById(R.id.f9_r13);
        f9_r14 = (TextView)view.findViewById(R.id.f9_r14);
        f9_r15 = (TextView)view.findViewById(R.id.f9_r15);
        f9_r16 = (TextView)view.findViewById(R.id.f9_r16);
        f9_r17 = (TextView)view.findViewById(R.id.f9_r17);
        f9_r18 = (TextView)view.findViewById(R.id.f9_r18);

        b9_r11 = (TextView)view.findViewById(R.id.b9_r11);
        b9_r12 = (TextView)view.findViewById(R.id.b9_r12);
        b9_r13 = (TextView)view.findViewById(R.id.b9_r13);
        b9_r14 = (TextView)view.findViewById(R.id.b9_r14);
        b9_r15 = (TextView)view.findViewById(R.id.b9_r15);
        b9_r16 = (TextView)view.findViewById(R.id.b9_r16);
        b9_r17 = (TextView)view.findViewById(R.id.b9_r17);
        b9_r18 = (TextView)view.findViewById(R.id.b9_r18);


        f10_r11 = (TextView)view.findViewById(R.id.f10_r11);
        f10_r12 = (TextView)view.findViewById(R.id.f10_r12);
        f10_r13 = (TextView)view.findViewById(R.id.f10_r13);
        f10_r14 = (TextView)view.findViewById(R.id.f10_r14);
        f10_r15 = (TextView)view.findViewById(R.id.f10_r15);
        f10_r16 = (TextView)view.findViewById(R.id.f10_r16);
        f10_r17 = (TextView)view.findViewById(R.id.f10_r17);
        f10_r18 = (TextView)view.findViewById(R.id.f10_r18);

        b10_r11 = (TextView)view.findViewById(R.id.b10_r11);
        b10_r12 = (TextView)view.findViewById(R.id.b10_r12);
        b10_r13 = (TextView)view.findViewById(R.id.b10_r13);
        b10_r14 = (TextView)view.findViewById(R.id.b10_r14);
        b10_r15 = (TextView)view.findViewById(R.id.b10_r15);
        b10_r16 = (TextView)view.findViewById(R.id.b10_r16);
        b10_r17 = (TextView)view.findViewById(R.id.b10_r17);
        b10_r18 = (TextView)view.findViewById(R.id.b10_r18);



        finishdialogfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(AutoPressExpand.this).commit();
            }
        });


        eventID=((AddScoreNew) getActivity()).geteventID();

        getExpandAuto(eventID);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void getExpandAuto(String Event_Id) {
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");

        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();

            jsonObject.putOpt("event_id", Event_Id);
            jsonObject.putOpt("version", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.EXPAND_SCORE_VIEW, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                expandAutopressResponse(response);
                Log.e("AutoPress Expand", "auto press expand" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(getActivity(), "Error in " + "autopress expand URL = " + PUTTAPI.EXPAND_SCORE_VIEW);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }


    public void expandAutopressResponse(JSONObject response) {
        try {




            JSONObject output = response.getJSONObject("output");
            String status = output.getString("status");
            if (status.equalsIgnoreCase("1")) {

                JSONArray data = output.getJSONArray("data");
                for (int i = 0 ; i < data.length(); i++){

                    if (i==0) {

                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole1.setText(holeNumber);
                        r1.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0; j < frontArray.length(); j++) {
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j == 0) {
                                f1_r1.setText(score);
                                f1_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                f2_r1.setText(score);
                                f2_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                f3_r1.setText(score);
                                f3_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                f4_r1.setText(score);
                                f4_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                f5_r1.setText(score);
                                f5_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                f6_r1.setText(score);
                                f6_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                f7_r1.setText(score);
                                f7_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                f8_r1.setText(score);
                                f8_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                f9_r1.setText(score);
                                f9_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                f10_r1.setText(score);
                                f10_r1.setTextColor(Color.parseColor(color));
                            } else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0; j < backArray.length(); j++) {

                            and1.setVisibility(View.VISIBLE);
                            back1_hole1.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j == 0) {
                                b1_r1.setText(score);
                                b1_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 1) {
                                b2_r1.setText(score);
                                b2_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 2) {
                                b3_r1.setText(score);
                                b3_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 3) {
                                b4_r1.setText(score);
                                b4_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 4) {
                                b5_r1.setText(score);
                                b5_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 5) {
                                b6_r1.setText(score);
                                b6_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 6) {
                                b7_r1.setText(score);
                                b7_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 7) {
                                b8_r1.setText(score);
                                b8_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 8) {
                                b9_r1.setText(score);
                                b9_r1.setTextColor(Color.parseColor(color));
                            } else if (j == 9) {
                                b10_r1.setText(score);
                                b10_r1.setTextColor(Color.parseColor(color));
                            } else {

                            }


                        }
                    }else if (i==1){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole2.setText(holeNumber);
                        r2.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r2.setText(score);
                                f1_r2.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r2.setText(score);
                                f2_r2.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r2.setText(score);
                                f3_r2.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r2.setText(score);
                                f4_r2.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r2.setText(score);
                                f5_r2.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r2.setText(score);
                                f6_r2.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r2.setText(score);
                                f7_r2.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r2.setText(score);
                                f8_r2.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r2.setText(score);
                                f9_r2.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r2.setText(score);
                                f10_r2.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and2.setVisibility(View.VISIBLE);
                            back1_hole2.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r2.setText(score);
                                b1_r2.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r2.setText(score);
                                b2_r2.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r2.setText(score);
                                b3_r2.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r2.setText(score);
                                b4_r2.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r2.setText(score);
                                b5_r2.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r2.setText(score);
                                b6_r2.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r2.setText(score);
                                b7_r2.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r2.setText(score);
                                b8_r2.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r2.setText(score);
                                b9_r2.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r2.setText(score);
                                b10_r2.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==2){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole3.setText(holeNumber);
                        r3.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r3.setText(score);
                                f1_r3.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r3.setText(score);
                                f2_r3.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r3.setText(score);
                                f3_r3.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r3.setText(score);
                                f4_r3.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r3.setText(score);
                                f5_r3.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r3.setText(score);
                                f6_r3.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r3.setText(score);
                                f7_r3.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r3.setText(score);
                                f8_r3.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r3.setText(score);
                                f9_r3.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r3.setText(score);
                                f10_r3.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and3.setVisibility(View.VISIBLE);
                            back1_hole3.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r3.setText(score);
                                b1_r3.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r3.setText(score);
                                b2_r3.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r3.setText(score);
                                b3_r3.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r3.setText(score);
                                b4_r3.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r3.setText(score);
                                b5_r3.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r3.setText(score);
                                b6_r3.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r3.setText(score);
                                b7_r3.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r3.setText(score);
                                b8_r3.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r3.setText(score);
                                b9_r3.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r3.setText(score);
                                b10_r3.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==3){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole4.setText(holeNumber);
                        r4.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r4.setText(score);
                                f1_r4.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r4.setText(score);
                                f2_r4.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r4.setText(score);
                                f3_r4.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r4.setText(score);
                                f4_r4.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r4.setText(score);
                                f5_r4.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r4.setText(score);
                                f6_r4.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r4.setText(score);
                                f7_r4.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r4.setText(score);
                                f8_r4.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r4.setText(score);
                                f9_r4.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r4.setText(score);
                                f10_r4.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and4.setVisibility(View.VISIBLE);
                            back1_hole4.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r4.setText(score);
                                b1_r4.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r4.setText(score);
                                b2_r4.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r4.setText(score);
                                b3_r4.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r4.setText(score);
                                b4_r4.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r4.setText(score);
                                b5_r4.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r4.setText(score);
                                b6_r4.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r4.setText(score);
                                b7_r4.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r4.setText(score);
                                b8_r4.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r4.setText(score);
                                b9_r4.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r4.setText(score);
                                b10_r4.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==4){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole5.setText(holeNumber);
                        r5.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r5.setText(score);
                                f1_r5.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r5.setText(score);
                                f2_r5.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r5.setText(score);
                                f3_r5.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r5.setText(score);
                                f4_r5.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r5.setText(score);
                                f5_r5.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r5.setText(score);
                                f6_r5.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r5.setText(score);
                                f7_r5.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r5.setText(score);
                                f8_r5.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r5.setText(score);
                                f9_r5.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r5.setText(score);
                                f10_r5.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and5.setVisibility(View.VISIBLE);
                            back1_hole5.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r5.setText(score);
                                b1_r5.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r5.setText(score);
                                b2_r5.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r5.setText(score);
                                b3_r5.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r5.setText(score);
                                b4_r5.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r5.setText(score);
                                b5_r5.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r5.setText(score);
                                b6_r5.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r5.setText(score);
                                b7_r5.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r5.setText(score);
                                b8_r5.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r5.setText(score);
                                b9_r5.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r5.setText(score);
                                b10_r5.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==5){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole6.setText(holeNumber);
                        r6.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r6.setText(score);
                                f1_r6.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r6.setText(score);
                                f2_r6.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r6.setText(score);
                                f3_r6.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r6.setText(score);
                                f4_r6.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r6.setText(score);
                                f5_r6.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r6.setText(score);
                                f6_r6.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r6.setText(score);
                                f7_r6.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r6.setText(score);
                                f8_r6.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r6.setText(score);
                                f9_r6.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r6.setText(score);
                                f10_r6.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and6.setVisibility(View.VISIBLE);
                            back1_hole6.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r6.setText(score);
                                b1_r6.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r6.setText(score);
                                b2_r6.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r6.setText(score);
                                b3_r6.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r6.setText(score);
                                b4_r6.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r6.setText(score);
                                b5_r6.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r6.setText(score);
                                b6_r6.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r6.setText(score);
                                b7_r6.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r6.setText(score);
                                b8_r6.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r6.setText(score);
                                b9_r6.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r6.setText(score);
                                b10_r6.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==6){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole7.setText(holeNumber);
                        r7.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r7.setText(score);
                                f1_r7.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r7.setText(score);
                                f2_r7.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r7.setText(score);
                                f3_r7.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r7.setText(score);
                                f4_r7.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r7.setText(score);
                                f5_r7.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r7.setText(score);
                                f6_r7.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r7.setText(score);
                                f7_r7.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r7.setText(score);
                                f8_r7.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r7.setText(score);
                                f9_r7.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r7.setText(score);
                                f10_r7.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and7.setVisibility(View.VISIBLE);
                            back1_hole7.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r7.setText(score);
                                b1_r7.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r7.setText(score);
                                b2_r7.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r7.setText(score);
                                b3_r7.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r7.setText(score);
                                b4_r7.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r7.setText(score);
                                b5_r7.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r7.setText(score);
                                b6_r7.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r7.setText(score);
                                b7_r7.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r7.setText(score);
                                b8_r7.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r7.setText(score);
                                b9_r7.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r7.setText(score);
                                b10_r7.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==7){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole8.setText(holeNumber);
                        r8.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r8.setText(score);
                                f1_r8.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r8.setText(score);
                                f2_r8.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r8.setText(score);
                                f3_r8.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r8.setText(score);
                                f4_r8.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r8.setText(score);
                                f5_r8.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r8.setText(score);
                                f6_r8.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r8.setText(score);
                                f7_r8.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r8.setText(score);
                                f8_r8.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r8.setText(score);
                                f9_r8.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r8.setText(score);
                                f10_r8.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and8.setVisibility(View.VISIBLE);
                            back1_hole8.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r8.setText(score);
                                b1_r8.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r8.setText(score);
                                b2_r8.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r8.setText(score);
                                b3_r8.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r8.setText(score);
                                b4_r8.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r8.setText(score);
                                b5_r8.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r8.setText(score);
                                b6_r8.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r8.setText(score);
                                b7_r8.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r8.setText(score);
                                b8_r8.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r8.setText(score);
                                b9_r8.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r8.setText(score);
                                b10_r8.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==8){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole9.setText(holeNumber);
                        r9.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r9.setText(score);
                                f1_r9.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r9.setText(score);
                                f2_r9.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r9.setText(score);
                                f3_r9.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r9.setText(score);
                                f4_r9.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r9.setText(score);
                                f5_r9.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r9.setText(score);
                                f6_r9.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r9.setText(score);
                                f7_r9.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r9.setText(score);
                                f8_r9.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r9.setText(score);
                                f9_r9.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r9.setText(score);
                                f10_r9.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and9.setVisibility(View.VISIBLE);
                            back1_hole9.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r9.setText(score);
                                b1_r9.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r9.setText(score);
                                b2_r9.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r9.setText(score);
                                b3_r9.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r9.setText(score);
                                b4_r9.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r9.setText(score);
                                b5_r9.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r9.setText(score);
                                b6_r9.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r9.setText(score);
                                b7_r9.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r9.setText(score);
                                b8_r9.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r9.setText(score);
                                b9_r9.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r9.setText(score);
                                b10_r9.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==9){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole10.setText(holeNumber);
                        r10.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r10.setText(score);
                                f1_r10.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r10.setText(score);
                                f2_r10.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r10.setText(score);
                                f3_r10.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r10.setText(score);
                                f4_r10.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r10.setText(score);
                                f5_r10.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r10.setText(score);
                                f6_r10.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r10.setText(score);
                                f7_r10.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r10.setText(score);
                                f8_r10.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r10.setText(score);
                                f9_r10.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r10.setText(score);
                                f10_r10.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){


                            and10.setVisibility(View.VISIBLE);
                            back1_hole10.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r10.setText(score);
                                b1_r10.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r10.setText(score);
                                b2_r10.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r10.setText(score);
                                b3_r10.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r10.setText(score);
                                b4_r10.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r10.setText(score);
                                b5_r10.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r10.setText(score);
                                b6_r10.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r10.setText(score);
                                b7_r10.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r10.setText(score);
                                b8_r10.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r10.setText(score);
                                b9_r10.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r10.setText(score);
                                b10_r10.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==10){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole11.setText(holeNumber);
                        r11.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r11.setText(score);
                                f1_r11.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r11.setText(score);
                                f2_r11.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r11.setText(score);
                                f3_r11.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r11.setText(score);
                                f4_r11.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r11.setText(score);
                                f5_r11.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r11.setText(score);
                                f6_r11.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r11.setText(score);
                                f7_r11.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r11.setText(score);
                                f8_r11.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r11.setText(score);
                                f9_r11.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r11.setText(score);
                                f10_r11.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and11.setVisibility(View.VISIBLE);
                            back1_hole11.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r11.setText(score);
                                b1_r11.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r11.setText(score);
                                b2_r11.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r11.setText(score);
                                b3_r11.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r11.setText(score);
                                b4_r11.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r11.setText(score);
                                b5_r11.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r11.setText(score);
                                b6_r11.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r11.setText(score);
                                b7_r11.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r11.setText(score);
                                b8_r11.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r11.setText(score);
                                b9_r11.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r11.setText(score);
                                b10_r11.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==11){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole12.setText(holeNumber);
                        r12.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r12.setText(score);
                                f1_r12.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r12.setText(score);
                                f2_r12.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r12.setText(score);
                                f3_r12.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r12.setText(score);
                                f4_r12.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r12.setText(score);
                                f5_r12.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r12.setText(score);
                                f6_r12.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r12.setText(score);
                                f7_r12.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r12.setText(score);
                                f8_r12.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r12.setText(score);
                                f9_r12.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r12.setText(score);
                                f10_r12.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and12.setVisibility(View.VISIBLE);
                            back1_hole12.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r12.setText(score);
                                b1_r12.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r12.setText(score);
                                b2_r12.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r12.setText(score);
                                b3_r12.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r12.setText(score);
                                b4_r12.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r12.setText(score);
                                b5_r12.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r12.setText(score);
                                b6_r12.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r12.setText(score);
                                b7_r12.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r12.setText(score);
                                b8_r12.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r12.setText(score);
                                b9_r12.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r12.setText(score);
                                b10_r12.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==12){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole13.setText(holeNumber);
                        r13.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r13.setText(score);
                                f1_r13.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r13.setText(score);
                                f2_r13.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r13.setText(score);
                                f3_r13.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r13.setText(score);
                                f4_r13.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r13.setText(score);
                                f5_r13.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r13.setText(score);
                                f6_r13.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r13.setText(score);
                                f7_r13.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r13.setText(score);
                                f8_r13.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r13.setText(score);
                                f9_r13.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r13.setText(score);
                                f10_r13.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and13.setVisibility(View.VISIBLE);
                            back1_hole13.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r13.setText(score);
                                b1_r13.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r13.setText(score);
                                b2_r13.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r13.setText(score);
                                b3_r13.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r13.setText(score);
                                b4_r13.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r13.setText(score);
                                b5_r13.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r13.setText(score);
                                b6_r13.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r13.setText(score);
                                b7_r13.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r13.setText(score);
                                b8_r13.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r13.setText(score);
                                b9_r13.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r13.setText(score);
                                b10_r13.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==13){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole14.setText(holeNumber);
                        r14.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r14.setText(score);
                                f1_r14.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r14.setText(score);
                                f2_r14.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r14.setText(score);
                                f3_r14.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r14.setText(score);
                                f4_r14.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r14.setText(score);
                                f5_r14.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r14.setText(score);
                                f6_r14.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r14.setText(score);
                                f7_r14.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r14.setText(score);
                                f8_r14.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r14.setText(score);
                                f9_r14.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r14.setText(score);
                                f10_r14.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and14.setVisibility(View.VISIBLE);
                            back1_hole14.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r14.setText(score);
                                b1_r14.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r14.setText(score);
                                b2_r14.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r14.setText(score);
                                b3_r14.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r14.setText(score);
                                b4_r14.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r14.setText(score);
                                b5_r14.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r14.setText(score);
                                b6_r14.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r14.setText(score);
                                b7_r14.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r14.setText(score);
                                b8_r14.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r14.setText(score);
                                b9_r14.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r14.setText(score);
                                b10_r14.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==14){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole15.setText(holeNumber);
                        r15.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r15.setText(score);
                                f1_r15.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r15.setText(score);
                                f2_r15.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r15.setText(score);
                                f3_r15.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r15.setText(score);
                                f4_r15.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r15.setText(score);
                                f5_r15.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r15.setText(score);
                                f6_r15.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r15.setText(score);
                                f7_r15.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r15.setText(score);
                                f8_r15.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r15.setText(score);
                                f9_r15.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r15.setText(score);
                                f10_r15.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and15.setVisibility(View.VISIBLE);
                            back1_hole15.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r15.setText(score);
                                b1_r15.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r15.setText(score);
                                b2_r15.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r15.setText(score);
                                b3_r15.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r15.setText(score);
                                b4_r15.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r15.setText(score);
                                b5_r15.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r15.setText(score);
                                b6_r15.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r15.setText(score);
                                b7_r15.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r15.setText(score);
                                b8_r15.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r15.setText(score);
                                b9_r15.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r15.setText(score);
                                b10_r15.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==15){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole16.setText(holeNumber);
                        r16.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r16.setText(score);
                                f1_r16.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r16.setText(score);
                                f2_r16.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r16.setText(score);
                                f3_r16.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r16.setText(score);
                                f4_r16.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r16.setText(score);
                                f5_r16.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r16.setText(score);
                                f6_r16.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r16.setText(score);
                                f7_r16.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r16.setText(score);
                                f8_r16.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r16.setText(score);
                                f9_r16.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r16.setText(score);
                                f10_r16.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and16.setVisibility(View.VISIBLE);
                            back1_hole16.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r16.setText(score);
                                b1_r16.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r16.setText(score);
                                b2_r16.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r16.setText(score);
                                b3_r16.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r16.setText(score);
                                b4_r16.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r16.setText(score);
                                b5_r16.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r16.setText(score);
                                b6_r16.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r16.setText(score);
                                b7_r16.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r16.setText(score);
                                b8_r16.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r16.setText(score);
                                b9_r16.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r16.setText(score);
                                b10_r16.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==16){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole17.setText(holeNumber);
                        r17.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r17.setText(score);
                                f1_r17.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r17.setText(score);
                                f2_r17.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r17.setText(score);
                                f3_r17.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r17.setText(score);
                                f4_r17.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r17.setText(score);
                                f5_r17.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r17.setText(score);
                                f6_r17.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r17.setText(score);
                                f7_r17.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r17.setText(score);
                                f8_r17.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r17.setText(score);
                                f9_r17.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r17.setText(score);
                                f10_r17.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and17.setVisibility(View.VISIBLE);
                            back1_hole17.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r17.setText(score);
                                b1_r17.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r17.setText(score);
                                b2_r17.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r17.setText(score);
                                b3_r17.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r17.setText(score);
                                b4_r17.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r17.setText(score);
                                b5_r17.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r17.setText(score);
                                b6_r17.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r17.setText(score);
                                b7_r17.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r17.setText(score);
                                b8_r17.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r17.setText(score);
                                b9_r17.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r17.setText(score);
                                b10_r17.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }else if (i==17){
                        String holeNumber = data.getJSONObject(i).getString("hole_number");
                        hole18.setText(holeNumber);
                        r18.setVisibility(View.VISIBLE);

                        JSONArray frontArray = data.getJSONObject(i).getJSONArray("first_array");
                        for (int j = 0 ; j<frontArray.length();j++){
                            String color = frontArray.getJSONObject(j).getString("color");
                            String score = frontArray.getJSONObject(j).getString("score");
                            if (j==0){
                                f1_r18.setText(score);
                                f1_r18.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                f2_r18.setText(score);
                                f2_r18.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                f3_r18.setText(score);
                                f3_r18.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                f4_r18.setText(score);
                                f4_r18.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                f5_r18.setText(score);
                                f5_r18.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                f6_r18.setText(score);
                                f6_r18.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                f7_r18.setText(score);
                                f7_r18.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                f8_r18.setText(score);
                                f8_r18.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                f9_r18.setText(score);
                                f9_r18.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                f10_r18.setText(score);
                                f10_r18.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                        JSONArray backArray = data.getJSONObject(i).getJSONArray("second_array");
                        for (int j = 0 ; j<backArray.length();j++){

                            and18.setVisibility(View.VISIBLE);
                            back1_hole18.setVisibility(View.VISIBLE);

                            String color = backArray.getJSONObject(j).getString("color");
                            String score = backArray.getJSONObject(j).getString("score");

                            if (j==0){
                                b1_r18.setText(score);
                                b1_r18.setTextColor(Color.parseColor(color));
                            }else if (j==1){
                                b2_r18.setText(score);
                                b2_r18.setTextColor(Color.parseColor(color));
                            }else if (j==2){
                                b3_r18.setText(score);
                                b3_r18.setTextColor(Color.parseColor(color));
                            }else if (j==3){
                                b4_r18.setText(score);
                                b4_r18.setTextColor(Color.parseColor(color));
                            }else if (j==4){
                                b5_r18.setText(score);
                                b5_r18.setTextColor(Color.parseColor(color));
                            }else if (j==5){
                                b6_r18.setText(score);
                                b6_r18.setTextColor(Color.parseColor(color));
                            }else if (j==6){
                                b7_r18.setText(score);
                                b7_r18.setTextColor(Color.parseColor(color));
                            }else if (j==7){
                                b8_r18.setText(score);
                                b8_r18.setTextColor(Color.parseColor(color));
                            }else if (j==8){
                                b9_r18.setText(score);
                                b9_r18.setTextColor(Color.parseColor(color));
                            }else if (j==9){
                                b10_r18.setText(score);
                                b10_r18.setTextColor(Color.parseColor(color));
                            }else {

                            }
                        }
                    }


                }

            }else {

            }
        } catch (JSONException je)

        {
            je.printStackTrace();
        }
    }
}