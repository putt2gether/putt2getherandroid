package com.putt2gether.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.putt2gether.bean.CountryBean;
import com.putt2gether.bean.DataBean;
import com.putt2gether.bean.FormatBean;
import com.putt2gether.bean.TeamIndividualBean;

/**
 * Created by Ajay on 22/06/2016.
 */
public class PuttParser {



    public DataBean getFormateResponseList(JSONObject response) {

        DataBean dataBean = new DataBean();
        ArrayList<FormatBean> eventTypeList = new ArrayList<FormatBean>();
        if (response != null){
            try{

                JSONObject jsonObject = response.getJSONObject("output");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        FormatBean typeBean = new FormatBean();
                        typeBean.setFormat_id(jsonArray.getJSONObject(i).getString("format_id"));
                        typeBean.setFormat_name(jsonArray.getJSONObject(i).getString("format_name"));
                        eventTypeList.add(typeBean);
                    }

            }catch (JSONException e){
                e.printStackTrace();
            }

            dataBean.setFormatList(eventTypeList);

        }

        return dataBean;
    }


    public DataBean getTeamIndividualResponseList(JSONObject response) {

        DataBean dataBean = new DataBean();
        ArrayList<TeamIndividualBean> teamEvent = new ArrayList<TeamIndividualBean>();
        if (response != null){
            try{

                JSONObject jsonObject = response.getJSONObject("output");
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    TeamIndividualBean typeBean = new TeamIndividualBean();
                    typeBean.setEvent_format_id(jsonArray.getJSONObject(i).getString("event_format_id"));
                    typeBean.setFormat_name(jsonArray.getJSONObject(i).getString("format_name"));
                    teamEvent.add(typeBean);
                }

            }catch (JSONException e){
                e.printStackTrace();
            }

            dataBean.setTeamIndividualList(teamEvent);

        }

        return dataBean;
    }

    public DataBean getCountryList(JSONObject response) {

        DataBean dataBean = new DataBean();
        ArrayList<CountryBean> countryTypeList = new ArrayList<CountryBean>();
        if (response != null){
            try{


                    JSONArray jsonArray = response.getJSONArray("CountryList");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        CountryBean typeBean = new CountryBean();
                        typeBean.setCountry_id(jsonArray.getJSONObject(i).getString("country_id"));
                        typeBean.setCountry_name(jsonArray.getJSONObject(i).getString("country_name"));
                        typeBean.setPhonecode(jsonArray.getJSONObject(i).getString("phonecode"));
                        countryTypeList.add(typeBean);
                    }


            }catch (JSONException e){
                e.printStackTrace();
            }

            dataBean.setCountryList(countryTypeList);

        }

        return dataBean;
    }

}
