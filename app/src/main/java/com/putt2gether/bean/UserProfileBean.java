package com.putt2gether.bean;

/**
 * Created by Ajay on 02/08/2016.
 *
 *
 * "user_id": 38,
 "user_name": "sachin+1@soms.in",
 "full_name": "Sachin Dh",
 "display_name": "Sachin Dh",
 "contact_no": "9911223300",
 "country_code": "+91",
 "handicap_value": 1,
 "address": "",
 "country": "india",
 "designation": "",
 "photo_url": "http://clients.vfactor.in/puttdemo/api_v2/profile/default.jpg",
 "thumb_url": "http://clients.vfactor.in/puttdemo/api_v2/profile/default.jpg"
 },
 "message": "User Details"

 */
public class UserProfileBean {
    private String user_id;
    private String user_name;
    private String full_name;
    private String display_name;
    private String contact_no;
    private String country_code;

    private String handicap_value;
    private String address;
    private String country;
    private String designation;
    private String photo_url;
    private String thumb_url;
    private String message;
    private String home_golf_course;

    public String getHome_golf_course() {
        return home_golf_course;
    }

    public void setHome_golf_course(String home_golf_course) {
        this.home_golf_course = home_golf_course;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getHandicap_value() {
        return handicap_value;
    }

    public void setHandicap_value(String handicap_value) {
        this.handicap_value = handicap_value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
