package com.putt2gether.bean;

import java.util.ArrayList;

/**
 * Created by Ajay on 22/06/2016.
 */
public class DataBean {


    public ArrayList<FormatBean> formatList;
    public ArrayList<CountryBean> countryList;

    public ArrayList<TeamIndividualBean> teamIndividualList;

    public ArrayList<TeeBean> getTeeList() {
        return teeList;
    }

    public void setTeeList(ArrayList<TeeBean> teeList) {
        this.teeList = teeList;
    }

    public ArrayList<TeeBean> teeList;

    public ArrayList<TeamIndividualBean> getTeamIndividualList() {
        return teamIndividualList;
    }

    public void setTeamIndividualList(ArrayList<TeamIndividualBean> teamIndividualList) {
        this.teamIndividualList = teamIndividualList;
    }

    public ArrayList<CountryBean> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<CountryBean> countryList) {
        this.countryList = countryList;
    }

    public ArrayList<FormatBean> getFormatList() {
        return formatList;
    }

    public void setFormatList(ArrayList<FormatBean> formatList) {
        this.formatList = formatList;
    }





}