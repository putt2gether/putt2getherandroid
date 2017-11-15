package com.putt2gether.putt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.putt2gether.R;
import com.putt2gether.bean.EventPostBean;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Constant;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 08/08/2016.
 */
public class AddGolfCourseActivity extends AppCompatActivity {

    private EditText golfCOurseName;
    private EditText golfCOurseCity;
    private EditText golfCOurseState;
    private EditText golfCOurseCountry;
    private EditText par1,par2,par3,par4,par5,par6,par7,par8,par9,par10,par12,par13,par14,par15,par16,par17,par18,par11;
    private EditText index1,index2,index3,index4,index5,index6,index7,index8,index9,index10,index11,index12,index13,index14,
            index15,index16,index17,index18;
    private RelativeLayout submitBTN;
    Typeface Lato_Regular;
    Constant constant;
    private CheckBox checkBox;
    private RelativeLayout holeLayout;
    String validTxt;
    private ArrayList<String> arrayList = new ArrayList<String>() ;
    private ArrayList<String> arrayListPar= new ArrayList<String>() ; ;
    private SharedPreferences mSharedPreferences;
    private String golfID;
    private int flag = 0;

    ScrollView scrollView;
    ImageView backBTN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_golf_course);
        constant = new Constant();
        scrollView = (ScrollView)findViewById(R.id.addgolfscroll);
        Lato_Regular = Typeface.createFromAsset(getAssets(), constant.Lato_Regular);

        backBTN = (ImageView)findViewById(R.id.back_submit_golf);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitBTN = (RelativeLayout)findViewById(R.id.submit_layout);
        checkBox = (CheckBox)findViewById(R.id.hole_checkbox);
        holeLayout = (RelativeLayout)findViewById(R.id.holeLayout);
        golfCOurseCity = (EditText)findViewById(R.id.add_golfCourse_city);
        golfCOurseName = (EditText)findViewById(R.id.add_golfCourse_name);
        golfCOurseState = (EditText)findViewById(R.id.add_golfCourse_state);
        golfCOurseCountry = (EditText)findViewById(R.id.add_golfCourse_country);

        golfCOurseCountry.setTypeface(Lato_Regular);
        golfCOurseState.setTypeface(Lato_Regular);
        golfCOurseCity.setTypeface(Lato_Regular);
        golfCOurseName.setTypeface(Lato_Regular);


        par1 =(EditText)findViewById(R.id.hole_par1);
        par2 =(EditText)findViewById(R.id.hole_par2);
        par3 =(EditText)findViewById(R.id.hole_par3);
        par4 =(EditText)findViewById(R.id.hole_par4);
        par5 =(EditText)findViewById(R.id.hole_par5);
        par6 =(EditText)findViewById(R.id.hole_par6);
        par7 =(EditText)findViewById(R.id.hole_par7);
        par8 =(EditText)findViewById(R.id.hole_par8);
        par9 =(EditText)findViewById(R.id.hole_par9);
        par10 =(EditText)findViewById(R.id.hole_par10);
        par11 =(EditText)findViewById(R.id.hole_par11);
        par12 =(EditText)findViewById(R.id.hole_par12);
        par13 =(EditText)findViewById(R.id.hole_par13);
        par14 =(EditText)findViewById(R.id.hole_par14);
        par15 =(EditText)findViewById(R.id.hole_par15);
        par16 =(EditText)findViewById(R.id.hole_par16);
        par17 =(EditText)findViewById(R.id.hole_par17);
        par18 =(EditText)findViewById(R.id.hole_par18);

        index1 = (EditText)findViewById(R.id.hole_index1);
        index2 = (EditText)findViewById(R.id.hole_index2);
        index3 = (EditText)findViewById(R.id.hole_index3);
        index4 = (EditText)findViewById(R.id.hole_index4);
        index5 = (EditText)findViewById(R.id.hole_index5);
        index6 = (EditText)findViewById(R.id.hole_index6);
        index7 = (EditText)findViewById(R.id.hole_index7);
        index8 = (EditText)findViewById(R.id.hole_index8);
        index9 = (EditText)findViewById(R.id.hole_index9);
        index10 = (EditText)findViewById(R.id.hole_index10);
        index11 = (EditText)findViewById(R.id.hole_index11);
        index12 = (EditText)findViewById(R.id.hole_index12);
        index13 = (EditText)findViewById(R.id.hole_index13);
        index14 = (EditText)findViewById(R.id.hole_index14);
        index15 = (EditText)findViewById(R.id.hole_index15);
        index16 = (EditText)findViewById(R.id.hole_index16);
        index17 = (EditText)findViewById(R.id.hole_index17);
        index18 = (EditText)findViewById(R.id.hole_index18);

        par1.setTypeface(Lato_Regular);
        par2.setTypeface(Lato_Regular);
        par1.setTypeface(Lato_Regular);
        par3.setTypeface(Lato_Regular);
        par4.setTypeface(Lato_Regular);
        par5.setTypeface(Lato_Regular);
        par6.setTypeface(Lato_Regular);
        par7.setTypeface(Lato_Regular);
        par8.setTypeface(Lato_Regular);
        par9.setTypeface(Lato_Regular);
        par10.setTypeface(Lato_Regular);
        par11.setTypeface(Lato_Regular);
        par12.setTypeface(Lato_Regular);
        par13.setTypeface(Lato_Regular);
        par14.setTypeface(Lato_Regular);
        par15.setTypeface(Lato_Regular);
        par16.setTypeface(Lato_Regular);
        par17.setTypeface(Lato_Regular);
        par18.setTypeface(Lato_Regular);

        index1.setTypeface(Lato_Regular);
        index2.setTypeface(Lato_Regular);
        index3.setTypeface(Lato_Regular);
        index4.setTypeface(Lato_Regular);
        index5.setTypeface(Lato_Regular);
        index6.setTypeface(Lato_Regular);
        index7.setTypeface(Lato_Regular);
        index8.setTypeface(Lato_Regular);
        index9.setTypeface(Lato_Regular);
        index10.setTypeface(Lato_Regular);
        index11.setTypeface(Lato_Regular);
        index12.setTypeface(Lato_Regular);
        index13.setTypeface(Lato_Regular);
        index14.setTypeface(Lato_Regular);
        index15.setTypeface(Lato_Regular);
        index16.setTypeface(Lato_Regular);
        index17.setTypeface(Lato_Regular);
        index18.setTypeface(Lato_Regular);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked()) {

                  //  scrollView.fullScroll(View.FOCUS_DOWN);
                    holeLayout.setVisibility(View.VISIBLE);
                    flag = 1;
                }
                else
                {
                    holeLayout.setVisibility(View.GONE);
                    flag = 0 ;
                }
            }
        });



            submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                arrayList = new ArrayList<String>();
                arrayListPar = new ArrayList<String>();


                String golfCourseNameText = golfCOurseName.getText().toString();
                String golfCourseCityText = golfCOurseCity.getText().toString();
                String golfCourseCountryText = golfCOurseCountry.getText().toString();
                String golfCourseStateText = golfCOurseState.getText().toString();

                int par2_int = 0, par10_int = 0, par3_int = 0, par4_int = 0, par5_int = 0, par6_int = 0, par7_int = 0, par8_int = 0, par9_int = 0,
                        par11_int = 0, par12_int = 0, par13_int = 0, par14_int = 0, par15_int = 0, par16_int = 0, par17_int = 0, par18_int = 0, par1_int = 0;
                int ind1 = 0, ind2 = 0, ind3 = 0, ind4 = 0, ind5 = 0, ind6 = 0, ind7 = 0, ind8 = 0, ind9 = 0, ind10 = 0, ind11 = 0, ind12 = 0, ind13 = 0,
                        ind14 = 0, ind15 = 0, ind16 = 0, ind17 = 0, ind18 = 0;

                String parText1 = par1.getText().toString();
                if (parText1.length() != 0) {
                    par1_int = Integer.parseInt(parText1);
                    arrayListPar.add(parText1);

                }

                String parText2 = par2.getText().toString();
                if (parText2.length() != 0) {
                    par2_int = Integer.parseInt(parText2);
                    arrayListPar.add(parText2);

                }
                String parText3 = par3.getText().toString();
                if (parText3.length() != 0) {
                    par3_int = Integer.parseInt(parText3);
                    arrayListPar.add(parText3);

                }
                String parText4 = par4.getText().toString();
                if (parText4.length() != 0) {
                    par4_int = Integer.parseInt(parText4);
                    arrayListPar.add(parText4);

                }
                String parText5 = par5.getText().toString();
                if (parText5.length() != 0) {
                    par5_int = Integer.parseInt(parText5);
                    arrayListPar.add(parText5);

                }
                String parText6 = par6.getText().toString();
                if (parText6.length() != 0) {
                    par6_int = Integer.parseInt(parText6);
                    arrayListPar.add(parText6);

                }
                String parText7 = par7.getText().toString();
                if (parText7.length() != 0) {
                    par7_int = Integer.parseInt(parText7);
                    arrayListPar.add(parText7);
                }
                String parText8 = par8.getText().toString();
                if (parText8.length() != 0) {
                    par8_int = Integer.parseInt(parText8);
                    arrayListPar.add(parText8);

                }
                String parText9 = par9.getText().toString();
                if (parText9.length() != 0) {
                    par9_int = Integer.parseInt(parText9);
                    arrayListPar.add(parText9);

                }
                String parText10 = par10.getText().toString();
                if (parText10.length() != 0) {
                    par10_int = Integer.parseInt(parText10);
                    arrayListPar.add(parText10);
                }
                String parText11 = par11.getText().toString();
                if (parText11.length() != 0) {
                    par11_int = Integer.parseInt(parText11);
                    arrayListPar.add(parText11);
                }
                String parText12 = par12.getText().toString();
                if (parText12.length() != 0) {
                    par12_int = Integer.parseInt(parText12);
                    arrayListPar.add(parText12);
                }
                String parText13 = par13.getText().toString();
                if (parText13.length() != 0) {
                    par13_int = Integer.parseInt(parText13);
                    arrayListPar.add(parText13);
                }
                String parText14 = par14.getText().toString();
                if (parText14.length() != 0) {
                    par14_int = Integer.parseInt(parText14);
                    arrayListPar.add(parText14);
                }
                String parText15 = par15.getText().toString();
                if (parText15.length() != 0) {
                    par15_int = Integer.parseInt(parText15);
                    arrayListPar.add(parText15);
                }
                String parText16 = par16.getText().toString();
                if (parText16.length() != 0) {
                    par16_int = Integer.parseInt(parText16);
                    arrayListPar.add(parText16);
                }
                String parText17 = par17.getText().toString();
                if (parText17.length() != 0) {
                    par17_int = Integer.parseInt(parText17);
                    arrayListPar.add(parText17);
                }
                String parText18 = par18.getText().toString();
                if (parText18.length() != 0) {
                    par18_int = Integer.parseInt(parText18);
                    arrayListPar.add(parText18);
                }

                String indexText1 = index1.getText().toString();
                if (indexText1.length() != 0) {
                    ind1 = Integer.parseInt(indexText1);
                    arrayList.add(indexText1);
                }

                String indexText2 = index2.getText().toString();
                if (indexText2.length() != 0) {
                    ind2 = Integer.parseInt(indexText2);
                    arrayList.add(indexText2);
                }
                String indexText3 = index3.getText().toString();
                if (indexText3.length() != 0) {
                    ind3 = Integer.parseInt(indexText3);
                    arrayList.add(indexText3);
                }
                String indexText4 = index4.getText().toString();
                if (indexText4.length() != 0) {
                    ind4 = Integer.parseInt(indexText4);
                    arrayList.add(indexText4);
                }
                String indexText5 = index5.getText().toString();
                if (indexText5.length() != 0) {
                    ind5 = Integer.parseInt(indexText5);
                    arrayList.add(indexText5);
                }
                String indexText6 = index6.getText().toString();
                if (indexText6.length() != 0) {
                    ind6 = Integer.parseInt(indexText6);
                    arrayList.add(indexText6);
                }
                String indexText7 = index7.getText().toString();
                if (indexText7.length() != 0) {
                    ind7 = Integer.parseInt(indexText7);
                    arrayList.add(indexText7);
                }
                String indexText8 = index8.getText().toString();
                if (indexText8.length() != 0) {

                    ind8 = Integer.parseInt(indexText8);
                    arrayList.add(indexText8);
                }
                String indexText9 = index9.getText().toString();
                if (indexText9.length() != 0) {
                    ind9 = Integer.parseInt(indexText9);
                    arrayList.add(indexText9);
                }
                String indexText10 = index10.getText().toString();
                if (indexText10.length() != 0) {
                    ind10 = Integer.parseInt(indexText10);
                    arrayList.add(indexText10);
                }
                String indexText11 = index11.getText().toString();
                if (indexText11.length() != 0) {
                    ind11 = Integer.parseInt(indexText11);
                    arrayList.add(indexText11);
                }
                String indexText12 = index12.getText().toString();
                if (indexText12.length() != 0) {
                    ind12 = Integer.parseInt(indexText12);
                    arrayList.add(indexText12);
                }
                String indexText13 = index13.getText().toString();
                if (indexText13.length() != 0) {
                    ind13 = Integer.parseInt(indexText13);
                    arrayList.add(indexText13);
                }
                String indexText14 = index14.getText().toString();
                if (indexText14.length() != 0) {
                    ind14 = Integer.parseInt(indexText14);
                    arrayList.add(indexText14);
                }
                String indexText15 = index15.getText().toString();
                if (indexText15.length() != 0) {
                    ind15 = Integer.parseInt(indexText15);
                    arrayList.add(indexText15);
                }
                String indexText16 = index16.getText().toString();
                if (indexText16.length() != 0) {
                    ind16 = Integer.parseInt(indexText16);
                    arrayList.add(indexText16);
                }
                String indexText17 = index17.getText().toString();
                if (indexText17.length() != 0) {
                    ind17 = Integer.parseInt(indexText17);
                    arrayList.add(indexText17);
                }
                String indexText18 = index18.getText().toString();
                if (indexText18.length() != 0) {
                    ind18 = Integer.parseInt(indexText18);
                    arrayList.add(indexText18);
                }




                if (golfCourseNameText.length() < 4) {
                    golfCOurseName.setError("Please enter the golf course name");
                } else if (golfCourseCityText.length() < 2) {
                    golfCOurseCity.setError("Please enter the city");
                } else if (golfCourseStateText.length() < 2) {
                    golfCOurseState.setError("Please enter the state");
                } else if (golfCourseCountryText.length() < 2) {
                    golfCOurseCountry.setError("Please enter the country");
                } else if (flag == 1){

                    if (par1_int < 3 || par1_int > 5) {

                        par1.setError("Please enter par value either 3,4 or 5");
                    } else if (ind1 < 1 || ind1 > 18) {
                        index1.setError("Please enter index value between 1 and 18");
                    } else if (par2_int < 3 || par2_int > 5) {
                        par2.setError("Please enter par value either 3,4 or 5");
                    } else if (ind2 < 1 || ind2 > 18) {
                        index2.setError("Please enter index value between 1 and 18");
                    } else if (par3_int < 3 || par3_int > 5) {
                        par3.setError("Please enter par value either 3,4 or 5");
                    } else if (ind3 < 1 || ind3 > 18) {
                        index3.setError("Please enter index value between 1 and 18");
                    } else if (par4_int < 3 || par4_int > 5) {
                        par4.setError("Please enter par value either 3,4 or 5");
                    } else if (ind4 < 1 || ind4 > 18) {
                        index4.setError("Please enter index value between 1 and 18");
                    } else if (par5_int < 3 || par5_int > 5) {
                        par5.setError("Please enter par value either 3,4 or 5");
                    } else if (ind5 < 1 || ind5 > 18) {
                        index5.setError("Please enter index value between 1 and 18");
                    } else if (par6_int < 3 || par6_int > 5) {
                        par6.setError("Please enter par value either 3,4 or 5");
                    } else if (ind6 < 1 || ind6 > 18) {
                        index6.setError("Please enter index value between 1 and 18");
                    } else if (par7_int < 3 || par7_int > 5) {
                        par7.setError("Please enter par value either 3,4 or 5");
                    } else if (ind7 < 1 || ind7 > 18) {
                        index7.setError("Please enter index value between 1 and 18");
                    } else if (par8_int < 3 || par8_int > 5) {
                        par8.setError("Please enter par value either 3,4 or 5");
                    } else if (ind8 < 1 || ind8 > 18) {
                        index8.setError("Please enter index value between 1 and 18");
                    } else if (par9_int < 3 || par9_int > 5) {
                        par9.setError("Please enter par value either 3,4 or 5");
                    } else if (ind9 < 1 || ind9 > 18) {
                        index9.setError("Please enter index value between 1 and 18");
                    } else if (par10_int < 3 || par10_int > 5) {
                        par10.setError("Please enter par value either 3,4 or 5");
                    } else if (ind10 < 1 || ind10 > 18) {
                        index10.setError("Please enter index value between 1 and 18");
                    } else if (par11_int < 3 && par11_int > 5) {
                        par11.setError("Please enter par value either 3,4 or 5");
                    } else if (ind11 < 1 || ind11 > 18) {
                        index11.setError("Please enter index value between 1 and 18");
                    } else if (par12_int < 3 || par12_int > 5) {
                        par12.setError("Please enter par value either 3,4 or 5");
                    } else if (ind12 < 1 || ind12 > 18) {
                        index12.setError("Please enter index value between 1 and 18");
                    } else if (par13_int < 3 || par13_int > 5) {
                        par13.setError("Please enter par value either 3,4 or 5");
                    } else if (ind13 < 1 || ind13 > 18) {
                        index13.setError("Please enter index value between 1 and 18");
                    } else if (par14_int < 3 || par14_int > 5) {
                        par14.setError("Please enter par value either 3,4 or 5");
                    } else if (ind14 < 1 || ind14 > 18) {
                        index14.setError("Please enter index value between 1 and 18");
                    } else if (par15_int < 3 || par15_int > 5) {
                        par15.setError("Please enter par value either 3,4 or 5");
                    } else if (ind15 < 1 || ind15 > 18) {
                        index15.setError("Please enter index value between 1 and 18");
                    } else if (par16_int < 3 || par16_int > 5) {
                        par16.setError("Please enter par value either 3,4 or 5");
                    } else if (ind16 < 1 || ind16 > 18) {
                        index16.setError("Please enter index value between 1 and 18");
                    } else if (par17_int < 3 || par17_int > 5) {
                        par17.setError("Please enter par value either 3,4 or 5");
                    } else if (ind17 < 1 || ind17 > 18) {
                        index17.setError("Please enter index value between 1 and 18");
                    } else if (par18_int < 3 || par18_int > 5) {
                        par18.setError("Please enter par value either 3,4 or 5");
                    } else if (ind18 < 1 || ind18 > 18) {
                        index18.setError("Please enter index value between 1 and 18");
                    }else {

                        Set<String> hs = new HashSet<>();
                        hs.addAll(arrayList);
                        arrayList.clear();
                        arrayList.addAll(hs);

                        if (arrayList.size()==18 && arrayList.size()==18){
                            addGolfCourseTask();
                        }else {
                            Toast.makeText(getApplicationContext(),"Please enter in all fields with unique index.",Toast.LENGTH_SHORT).show();
                        }
                        
                    }


                }
                else{

                    addGolfCourseTask();

                }

            }
        });

    }


    private void addGolfCourseTask() {

        SharedPreferences pref = this.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        final String user_ID = pref.getString("userId",null);

        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("created_by", user_ID);
            jsonObject.putOpt("version", "2");
            jsonObject.putOpt("name",golfCOurseName.getText().toString());
            jsonObject.putOpt("city", golfCOurseCity.getText().toString());
            jsonObject.putOpt("state", golfCOurseState.getText().toString());
            jsonObject.putOpt("country",golfCOurseCountry.getText().toString());
            jsonObject.putOpt("create_temporary","1");

            if (flag ==1) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.putOpt("par_1", par1.getText().toString());
                jsonObject1.putOpt("par_2", par2.getText().toString());
                jsonObject1.putOpt("par_3", par3.getText().toString());
                jsonObject1.putOpt("par_4", par4.getText().toString());
                jsonObject1.putOpt("par_5", par5.getText().toString());
                jsonObject1.putOpt("par_6", par6.getText().toString());
                jsonObject1.putOpt("par_7", par7.getText().toString());
                jsonObject1.putOpt("par_8", par8.getText().toString());
                jsonObject1.putOpt("par_9", par9.getText().toString());
                jsonObject1.putOpt("par_10", par10.getText().toString());
                jsonObject1.putOpt("par_11", par11.getText().toString());
                jsonObject1.putOpt("par_12", par12.getText().toString());
                jsonObject1.putOpt("par_13", par13.getText().toString());
                jsonObject1.putOpt("par_14", par14.getText().toString());
                jsonObject1.putOpt("par_15", par15.getText().toString());
                jsonObject1.putOpt("par_16", par16.getText().toString());
                jsonObject1.putOpt("par_17", par17.getText().toString());
                jsonObject1.putOpt("par_18", par18.getText().toString());
                jsonObject.putOpt("par_values", jsonObject1);

                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.putOpt("index_1", index1.getText().toString());
                jsonObject2.putOpt("index_2", index2.getText().toString());
                jsonObject2.putOpt("index_3", index3.getText().toString());
                jsonObject2.putOpt("index_4", index4.getText().toString());
                jsonObject2.putOpt("index_5", index5.getText().toString());
                jsonObject2.putOpt("index_6", index6.getText().toString());
                jsonObject2.putOpt("index_7", index7.getText().toString());
                jsonObject2.putOpt("index_8", index8.getText().toString());

                jsonObject2.putOpt("index_9", index9.getText().toString());
                jsonObject2.putOpt("index_10", index10.getText().toString());
                jsonObject2.putOpt("index_11", index11.getText().toString());
                jsonObject2.putOpt("index_12", index12.getText().toString());
                jsonObject2.putOpt("index_13", index13.getText().toString());
                jsonObject2.putOpt("index_14", index14.getText().toString());
                jsonObject2.putOpt("index_15", index15.getText().toString());
                jsonObject2.putOpt("index_16", index16.getText().toString());

                jsonObject2.putOpt("index_17", index17.getText().toString());
                jsonObject2.putOpt("index_18", index18.getText().toString());
                jsonObject.putOpt("index_values", jsonObject2);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("Post Event",jsonObject.toString());
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.ADD_GOLF_COURSE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                Log.e("Create Event", "OnResponse =" + response.toString());
                getResponseAddGolfCourse(response);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError && error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "error_network_timeout",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(getApplicationContext(), "ParseError", Toast.LENGTH_LONG).show();
                }
                pDialog.dismiss();

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e("Add golfCourse", "Url= " + PUTTAPI.ADD_GOLF_COURSE + " PostObject = " + jsonObject.toString());
        //requestQueue.add(jsonObjectRequest);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
    private void getResponseAddGolfCourse(JSONObject response) {
        mSharedPreferences = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        EventPostBean createBean = new EventPostBean();

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equals("1")){
                    golfID = jsonObject.getString("golfcourseid");
                    String msg = jsonObject.getString("message");

                    Intent intent = new Intent(getApplicationContext(),CreateEventActivity.class);
                    String b = getIntent().getStringExtra("golfNameTemp");
                    String golfTempID = getIntent().getStringExtra("golf_couse_id_temp");
                    intent.putExtra("golfNameTemp",golfCOurseName.getText().toString());
                    intent.putExtra("golf_couse_id_temp", golfID);
                    startActivity(intent);
                    finish();

                }else if (status.equals("2")){

                    Intent intent = new Intent(getApplicationContext(),CreateEventActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    String str = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




}
