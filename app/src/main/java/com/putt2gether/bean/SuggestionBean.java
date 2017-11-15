package com.putt2gether.bean;

/**
 * Created by Ajay on 23/06/2016.
 *
 * "user_id": 37,
 "full_name": "",
 "registered_mobile_number": "8802929382",
 "last_modified_date": "",
 "country": "India",
 "self_handicap": 3,
 "played": 0,
 "handicap": null,
 "profile_image": "http://clients.vfactor.in/puttdemo/api_v2/profile/default.jpg",
 "thumb_image": "http://clients.vfactor.in/puttdemo/api_v2/profile/default.jpg"
 *
 *
 */
public class SuggestionBean {
    private String user_id;
    private String full_name;
    private String country;
    private boolean clicked;
    private boolean clickedRemove;
    private String isAdded;

    public String getIsAdded() {
        return isAdded;
    }

    public void setIsAdded(String isAdded) {
        this.isAdded = isAdded;
    }

    public boolean isClicked() {
        return clicked;
    }

    public boolean isClickedRemove() {
        return clickedRemove;
    }

    public void setClickedRemove(boolean clickedRemove) {
        this.clickedRemove = clickedRemove;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String registered_mobile_number;
    private String last_modified_date;
    private String self_handicap;
    private String played;
    private String handicap;
    private String profile_image;
    private String thumb_image;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRegistered_mobile_number() {
        return registered_mobile_number;
    }

    public void setRegistered_mobile_number(String registered_mobile_number) {
        this.registered_mobile_number = registered_mobile_number;
    }

    public String getLast_modified_date() {
        return last_modified_date;
    }

    public void setLast_modified_date(String last_modified_date) {
        this.last_modified_date = last_modified_date;
    }

    public String getSelf_handicap() {
        return self_handicap;
    }

    public void setSelf_handicap(String self_handicap) {
        this.self_handicap = self_handicap;
    }

    public String getPlayed() {
        return played;
    }

    public void setPlayed(String played) {
        this.played = played;
    }

    public String getHandicap() {
        return handicap;
    }

    public void setHandicap(String handicap) {
        this.handicap = handicap;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getThumb_image() {
        return thumb_image;
    }

    public void setThumb_image(String thumb_image) {
        this.thumb_image = thumb_image;
    }
}
