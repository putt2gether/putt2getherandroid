package com.putt2gether.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

import com.putt2gether.R;

/**
 * Created by Abc on 9/2/2016.
 */
public class DayDecorator implements DayViewDecorator {


    private int color,color2;
    private HashSet<CalendarDay> dates;
    Context c1;

    public DayDecorator(int color, Collection<CalendarDay> dates,int color2,Context c) {
        this.color = color;
        this.color2=color2;
        this.c1=c;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(4, color));
       // view.setBackgroundDrawable(color2);
        view.addSpan(new ForegroundColorSpan(Color.WHITE));
        //view.addSpan(new BackgroundColorSpan(Color.BLUE));
        view.setBackgroundDrawable(ContextCompat.getDrawable(c1,R.drawable.date_bg));

    }
}