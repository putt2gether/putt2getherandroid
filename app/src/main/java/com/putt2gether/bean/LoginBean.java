package com.putt2gether.bean;

/**
 * Created by Ajay on 18/06/2016.
 */
public class LoginBean {
    private String user_id;
    private String user_name;
    private String full_name;
    private String display_name;
    private String Error;
    private String photoURL;
    private String message;
    private String self_handicap;

    public String getSelf_handicap() {
        return self_handicap;
    }

    public void setSelf_handicap(String self_handicap) {
        this.self_handicap = self_handicap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFormateId() {
        return formateId;
    }

    public void setFormateId(String formateId) {
        this.formateId = formateId;
    }

    public String getLatestEventId() {
        return latestEventId;
    }

    public void setLatestEventId(String latestEventId) {
        this.latestEventId = latestEventId;
    }

    private String formateId;
    private String latestEventId;

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
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

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}