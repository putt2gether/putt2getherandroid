package com.putt2gether.bean;

/**
 * Created by Ajay on 02/08/2016.
 *
 *
 * "eagle": 0,
 "birdie": 1,
 "par": 7,
 "bogey": 5,
 "double bogey": 5,
 "average": "45"
 */
public class PieDataBean {

    private String eagle;
    private String birdie;
    private String par;
    private String bogey;
    private String doublebogey;
    private String average;
    private String curent_position;

    public String getCurent_position() {
        return curent_position;
    }

    public void setCurent_position(String curent_position) {
        this.curent_position = curent_position;
    }

    public String getEagle() {
        return eagle;
    }

    public void setEagle(String eagle) {
        this.eagle = eagle;
    }

    public String getBogey() {
        return bogey;
    }

    public void setBogey(String bogey) {
        this.bogey = bogey;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getBirdie() {
        return birdie;
    }

    public void setBirdie(String birdie) {
        this.birdie = birdie;
    }

    public String getDoublebogey() {
        return doublebogey;
    }

    public void setDoublebogey(String doublebogey) {
        this.doublebogey = doublebogey;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
