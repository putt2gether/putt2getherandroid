package com.putt2gether.fragments.mygroups;

/**
 * Created by Ajay on 29/11/2016.
 */
public class AddmemberBean  {

    private String user_id;
    private String full_name;
    private String country;
    private boolean clicked;
    private boolean clickedRemove;

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
