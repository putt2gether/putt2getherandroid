package com.putt2gether.putt;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.putt2gether.R;
import com.putt2gether.fragments.CreateFragments;
import com.putt2gether.fragments.GroupsFragments;
import com.putt2gether.fragments.SuggetionsFragments;
import com.putt2gether.fragments.WhatsAppFragments;

/**
 * Created by Ajay on 01/08/2016.
 */
public class InviteTabPlusActivity extends AppCompatActivity {

    private RelativeLayout toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String title[] = {"SUGGESTIONS", "GROUPS", "+ ADD PLAYER","WHATS APP"};

    private TextView toolbarTitle;
    private ImageView venue_Filter;
    private ImageView back_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_tab_activity);


        venueToolbar();
        findViews();
    }

    private void venueToolbar() {
        toolbar = (RelativeLayout) findViewById(R.id.toolbar_tab_activity);

        toolbarTitle = (TextView) findViewById(R.id.tab_title_toolbar);

        toolbarTitle.setText("INVITE");



        back_Btn = (ImageView)findViewById(R.id.back_tab_invite);
        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void findViews()
    {

        tabLayout = (TabLayout)findViewById(R.id.invite_TabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewpager_invite);

        CreateTabFragmentAdapter adapter = new CreateTabFragmentAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(5);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (i == 0) {
                tab.setCustomView(getTabView(i, true));
            }
            else {
                tab.setCustomView(getTabView(i, false));

            }
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                TextView tabText = (TextView) view1.findViewById(R.id.tabtext);
                //toolbarTitle.setText(tabText.getText().toString());
                tabText.setTextColor(Color.WHITE);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view2 = tab.getCustomView();
                TextView t1 = (TextView) view2.findViewById(R.id.tabtext);
                t1.setTextColor(Color.WHITE);
            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                View v = tab.getCustomView();
                TextView t1 = (TextView) v.findViewById(R.id.tabtext);
                t1.setTextColor(Color.WHITE);


            }

        });
    }

    private View getTabView(int i, boolean b) {

        View v = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

        TextView tv = (TextView) v.findViewById(R.id.tabtext);

        tv.setText(title[i]);
        if (!b) {
            tv.setTextColor(Color.WHITE);
        } else if (b) {
            tv.setTextColor(Color.WHITE);

        }

        return v;
    }

    class CreateTabFragmentAdapter extends FragmentPagerAdapter {
        private Fragment fragment = null;

        public CreateTabFragmentAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                fragment = new SuggetionsFragments();
            } else if (position == 1) {
                fragment = new GroupsFragments();
            } else if (position == 2) {
                fragment = new CreateFragments();
            } else if (position == 3) {
                fragment = new WhatsAppFragments();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight()
                    || y < w.getTop() || y > w.getBottom()) ) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }

}