package com.putt2gether.putt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.putt2gether.R;
import com.putt2gether.adapter.GolfCourseAdapter;
import com.putt2gether.adapter.SearchGolfCouseAdapter;
import com.putt2gether.bean.GolfCourseBean;
import com.putt2gether.bean.SearchGolfCouseBean;
import com.putt2gether.fragments.NearGolfCoures;
import com.putt2gether.fragments.SearchGolfCourseFragment;
import com.putt2gether.network.GPSTracker;
import com.putt2gether.utils.Constant;

/**
 * Created by Ajay on 21/06/2016.
 */
public class SelectGolfCourse extends AppCompatActivity {


    /** Declaration of UI **/
    private RecyclerView mListViewService;
    private EditText searchEdit;
    private EditText editBTN;
    List<GolfCourseBean> list1;
    List<SearchGolfCouseBean> listSearch;
    GolfCourseBean typeBean;
    SearchGolfCouseBean beanSearch;
    private GPSTracker gpsTracker;

    private String searchTEXT;
    private ImageView backBTN;

    private GolfCourseAdapter golfCorseAdapter;
    private SearchGolfCouseAdapter searchGolfCourseAdapter;
    Typeface roboto_Regular;
    private Constant constant;
    private RelativeLayout addGolfCourse;
    private String resultFor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_golf_course);
        backBTN = (ImageView)findViewById(R.id.back_selectgolf_course);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        constant = new Constant();

        displayView(0);

        //getGolfCourse();
        iniUI();
    }


    /*************************************
     * Here we use iniUI() Method
     **/
    public void iniUI(){

        addGolfCourse = (RelativeLayout)findViewById(R.id.addGolfCourse);
        addGolfCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddGolfCourseActivity.class);
                startActivity(intent);

            }
        });
        searchEdit = (EditText)findViewById(R.id.edit_search);
        editBTN = (EditText)findViewById(R.id.edit_btn);

        roboto_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);

        searchEdit.setTypeface(roboto_Regular);
        searchTEXT = searchEdit.getText().toString();

        editBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(1);

            }
        });

        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i("TAG,","Enter pressed");
                    displayView(1);
                }
                return false;
            }
        });

    }

    /*************************************
     * Here we use addListener() Method
     **/

    private void displayView(int position) {
        android.support.v4.app.Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new NearGolfCoures();
                //title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new SearchGolfCourseFragment();

                //title = getString(R.string.title_friends);
                break;

            default:
                break;
        }

        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            //getSupportActionBar().setTitle(title);
        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}