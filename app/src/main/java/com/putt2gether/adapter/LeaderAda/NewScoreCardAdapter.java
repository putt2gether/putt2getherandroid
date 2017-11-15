package com.putt2gether.adapter.LeaderAda;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.bean.ColorBean;
import com.putt2gether.view.LatoTextView;

/**
 * Created by Ajay on 05/10/2016.
 */
public class NewScoreCardAdapter extends BaseAdapter {


    private ArrayList<String> listZero;
    private ArrayList<String> listfirst;

    private ArrayList<String> second;
    private ArrayList<String> third;
    private ArrayList<String> fourth;
    private ArrayList<String> counter;
    private ArrayList<ColorBean> colorArray ;
    private Activity context;
    int counterPosition = 0;
    private static LayoutInflater inflater=null;
    private LatoTextView firstrowscore, secondrowscore, thirdrowscore1, fourthrowscore,thirdrowscore2,thirdrowscore3,thirdrowscore4,thirdrowscore5,thirdrowscore6;

    public NewScoreCardAdapter(Activity mContext, ArrayList<String> zero, ArrayList<String> frst, ArrayList<String> secnd, ArrayList<String> thrd, ArrayList<String> frth,ArrayList<ColorBean> arrr,ArrayList<String> count) {
        this.context = mContext;
        this.listZero = zero;
        this.listfirst = frst;
        this.second=secnd;
        this.third=thrd;
        this.fourth=frth;
        this.colorArray=arrr;
        this.counter=count;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return second.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(convertView == null) {
            view = inflater.inflate(R.layout.score_tab_ada_layout, null);
        }

        firstrowscore = (LatoTextView)  view.findViewById(R.id.frstrowscore);
        secondrowscore = (LatoTextView) view.findViewById(R.id.secondrowscore);
        thirdrowscore1 = (LatoTextView) view.findViewById(R.id.thirdrowscore1);
        thirdrowscore2 = (LatoTextView) view.findViewById(R.id.thirdrowscore2);
        thirdrowscore3 = (LatoTextView) view.findViewById(R.id.thirdrowscore3);
        thirdrowscore4 = (LatoTextView) view.findViewById(R.id.thirdrowscore4);
        thirdrowscore5 = (LatoTextView) view.findViewById(R.id.thirdrowscore5);
        thirdrowscore6 = (LatoTextView) view.findViewById(R.id.thirdrowscore6);
        fourthrowscore = (LatoTextView) view.findViewById(R.id.fourthrowscore);


        firstrowscore.setText(listfirst.get(position));

        String strCount = counter.get(position);

        if (strCount.equalsIgnoreCase("1")){

            thirdrowscore1.setText(colorArray.get(counterPosition).getValue());
            String col = colorArray.get(counterPosition).getColor();
            if (col.equalsIgnoreCase("red")){
                thirdrowscore1.setTextColor(Color.RED);
            }else if (col.equalsIgnoreCase("black")){
                thirdrowscore1.setTextColor(Color.BLACK);
            }else {
                thirdrowscore1.setTextColor(Color.BLUE);
            }
            counterPosition = counterPosition+Integer.parseInt(strCount);

        }else if (strCount.equalsIgnoreCase("2")){

            thirdrowscore1.setText(colorArray.get(counterPosition).getValue());
            String col = colorArray.get(counterPosition).getColor();
            if (col.equalsIgnoreCase("red")){
                thirdrowscore1.setTextColor(Color.RED);
            }else if (col.equalsIgnoreCase("black")){
                thirdrowscore1.setTextColor(Color.BLACK);
            }else {
                thirdrowscore1.setTextColor(Color.BLUE);
            }

            thirdrowscore2.setText(colorArray.get(counterPosition+1).getValue());
            String col1 = colorArray.get(counterPosition+1).getColor();
            if (col1.equalsIgnoreCase("red")){
                thirdrowscore2.setTextColor(Color.RED);
            }else if (col1.equalsIgnoreCase("black")){
                thirdrowscore2.setTextColor(Color.BLACK);
            }else {
                thirdrowscore2.setTextColor(Color.BLUE);
            }
            counterPosition = counterPosition+Integer.parseInt(strCount);

        }else if (strCount.equalsIgnoreCase("3")){

            thirdrowscore1.setText(colorArray.get(counterPosition).getValue());
            String col = colorArray.get(counterPosition).getColor();
            if (col.equalsIgnoreCase("red")){
                thirdrowscore1.setTextColor(Color.RED);
            }else if (col.equalsIgnoreCase("black")){
                thirdrowscore1.setTextColor(Color.BLACK);
            }else {
                thirdrowscore1.setTextColor(Color.BLUE);
            }

            thirdrowscore2.setText(colorArray.get(counterPosition+1).getValue());
            String col1 = colorArray.get(counterPosition+1).getColor();
            if (col1.equalsIgnoreCase("red")){
                thirdrowscore2.setTextColor(Color.RED);
            }else if (col1.equalsIgnoreCase("black")){
                thirdrowscore2.setTextColor(Color.BLACK);
            }else {
                thirdrowscore2.setTextColor(Color.BLUE);
            }

            thirdrowscore3.setText(colorArray.get(counterPosition+2).getValue());
            String col2 = colorArray.get(counterPosition+2).getColor();
            if (col2.equalsIgnoreCase("red")){
                thirdrowscore3.setTextColor(Color.RED);
            }else if (col2.equalsIgnoreCase("black")){
                thirdrowscore3.setTextColor(Color.BLACK);
            }else {
                thirdrowscore3.setTextColor(Color.BLUE);
            }

            counterPosition = counterPosition+Integer.parseInt(strCount);
        }else if (strCount.equalsIgnoreCase("4")){

            thirdrowscore1.setText(colorArray.get(counterPosition).getValue());
            String col = colorArray.get(counterPosition).getColor();
            if (col.equalsIgnoreCase("red")){
                thirdrowscore1.setTextColor(Color.RED);
            }else if (col.equalsIgnoreCase("black")){
                thirdrowscore1.setTextColor(Color.BLACK);
            }else {
                thirdrowscore1.setTextColor(Color.BLUE);
            }

            thirdrowscore2.setText(colorArray.get(counterPosition+1).getValue());
            String col1 = colorArray.get(counterPosition+1).getColor();
            if (col1.equalsIgnoreCase("red")){
                thirdrowscore2.setTextColor(Color.RED);
            }else if (col1.equalsIgnoreCase("black")){
                thirdrowscore2.setTextColor(Color.BLACK);
            }else {
                thirdrowscore2.setTextColor(Color.BLUE);
            }

            thirdrowscore3.setText(colorArray.get(counterPosition+2).getValue());
            String col2 = colorArray.get(counterPosition+2).getColor();
            if (col2.equalsIgnoreCase("red")){
                thirdrowscore3.setTextColor(Color.RED);
            }else if (col2.equalsIgnoreCase("black")){
                thirdrowscore3.setTextColor(Color.BLACK);
            }else {
                thirdrowscore3.setTextColor(Color.BLUE);
            }

            thirdrowscore4.setText(colorArray.get(counterPosition+3).getValue());
            String col3 = colorArray.get(counterPosition+3).getColor();
            if (col3.equalsIgnoreCase("red")){
                thirdrowscore4.setTextColor(Color.RED);
            }else if (col3.equalsIgnoreCase("black")){
                thirdrowscore4.setTextColor(Color.BLACK);
            }else {
                thirdrowscore4.setTextColor(Color.BLUE);
            }

            counterPosition = counterPosition+Integer.parseInt(strCount);
        }else if (strCount.equalsIgnoreCase("5")){

            thirdrowscore1.setText(colorArray.get(counterPosition).getValue());
            String col = colorArray.get(counterPosition).getColor();
            if (col.equalsIgnoreCase("red")){
                thirdrowscore1.setTextColor(Color.RED);
            }else if (col.equalsIgnoreCase("black")){
                thirdrowscore1.setTextColor(Color.BLACK);
            }else {
                thirdrowscore1.setTextColor(Color.BLUE);
            }

            thirdrowscore2.setText(colorArray.get(counterPosition+1).getValue());
            String col1 = colorArray.get(counterPosition+1).getColor();
            if (col1.equalsIgnoreCase("red")){
                thirdrowscore2.setTextColor(Color.RED);
            }else if (col1.equalsIgnoreCase("black")){
                thirdrowscore2.setTextColor(Color.BLACK);
            }else {
                thirdrowscore2.setTextColor(Color.BLUE);
            }

            thirdrowscore3.setText(colorArray.get(counterPosition+2).getValue());
            String col2 = colorArray.get(counterPosition+2).getColor();
            if (col2.equalsIgnoreCase("red")){
                thirdrowscore3.setTextColor(Color.RED);
            }else if (col2.equalsIgnoreCase("black")){
                thirdrowscore3.setTextColor(Color.BLACK);
            }else {
                thirdrowscore3.setTextColor(Color.BLUE);
            }

            thirdrowscore4.setText(colorArray.get(counterPosition+3).getValue());
            String col3 = colorArray.get(counterPosition+3).getColor();
            if (col3.equalsIgnoreCase("red")){
                thirdrowscore4.setTextColor(Color.RED);
            }else if (col3.equalsIgnoreCase("black")){
                thirdrowscore4.setTextColor(Color.BLACK);
            }else {
                thirdrowscore4.setTextColor(Color.BLUE);
            }

            thirdrowscore5.setText(colorArray.get(counterPosition+4).getValue());
            String col4 = colorArray.get(counterPosition+4).getColor();
            if (col4.equalsIgnoreCase("red")){
                thirdrowscore5.setTextColor(Color.RED);
            }else if (col4.equalsIgnoreCase("black")){
                thirdrowscore5.setTextColor(Color.BLACK);
            }else {
                thirdrowscore5.setTextColor(Color.BLUE);
            }


            counterPosition = counterPosition+Integer.parseInt(strCount);
        }else if (strCount.equalsIgnoreCase("6")){

            thirdrowscore1.setText(colorArray.get(counterPosition).getValue());
            String col = colorArray.get(counterPosition).getColor();
            if (col.equalsIgnoreCase("red")){
                thirdrowscore1.setTextColor(Color.RED);
            }else if (col.equalsIgnoreCase("black")){
                thirdrowscore1.setTextColor(Color.BLACK);
            }else {
                thirdrowscore1.setTextColor(Color.BLUE);
            }

            thirdrowscore2.setText(colorArray.get(counterPosition+1).getValue());
            String col1 = colorArray.get(counterPosition+1).getColor();
            if (col1.equalsIgnoreCase("red")){
                thirdrowscore2.setTextColor(Color.RED);
            }else if (col1.equalsIgnoreCase("black")){
                thirdrowscore2.setTextColor(Color.BLACK);
            }else {
                thirdrowscore2.setTextColor(Color.BLUE);
            }

            thirdrowscore3.setText(colorArray.get(counterPosition+2).getValue());
            String col2 = colorArray.get(counterPosition+2).getColor();
            if (col2.equalsIgnoreCase("red")){
                thirdrowscore3.setTextColor(Color.RED);
            }else if (col2.equalsIgnoreCase("black")){
                thirdrowscore3.setTextColor(Color.BLACK);
            }else {
                thirdrowscore3.setTextColor(Color.BLUE);
            }

            thirdrowscore4.setText(colorArray.get(counterPosition+3).getValue());
            String col3 = colorArray.get(counterPosition+3).getColor();
            if (col3.equalsIgnoreCase("red")){
                thirdrowscore4.setTextColor(Color.RED);
            }else if (col3.equalsIgnoreCase("black")){
                thirdrowscore4.setTextColor(Color.BLACK);
            }else {
                thirdrowscore4.setTextColor(Color.BLUE);
            }

            thirdrowscore5.setText(colorArray.get(counterPosition+4).getValue());
            String col4 = colorArray.get(counterPosition+4).getColor();
            if (col4.equalsIgnoreCase("red")){
                thirdrowscore5.setTextColor(Color.RED);
            }else if (col4.equalsIgnoreCase("black")){
                thirdrowscore5.setTextColor(Color.BLACK);
            }else {
                thirdrowscore5.setTextColor(Color.BLUE);
            }
            thirdrowscore6.setText(colorArray.get(counterPosition+5).getValue());
            String col5 = colorArray.get(counterPosition+5).getColor();
            if (col5.equalsIgnoreCase("red")){
                thirdrowscore6.setTextColor(Color.RED);
            }else if (col5.equalsIgnoreCase("black")){
                thirdrowscore6.setTextColor(Color.BLACK);
            }else {
                thirdrowscore6.setTextColor(Color.BLUE);
            }


            counterPosition = counterPosition+Integer.parseInt(strCount);
        }

        String color = listZero.get(position);
        if (color.equalsIgnoreCase("red")){
            secondrowscore.setBackgroundResource(R.drawable.red_circle);
        }else if (color.equalsIgnoreCase("black")){
            secondrowscore.setBackgroundResource(R.drawable.black_circle);
        }else {
            secondrowscore.setBackgroundResource(R.drawable.circle_plus);
        }

        Log.v("aarraySize",colorArray.size()+"size");

        String cccoollrr = third.get(position);
        if (cccoollrr.equalsIgnoreCase("as")){
            secondrowscore.setText("AS");
        }else {
            secondrowscore.setText("W");
        }


        return view;

    }
}
