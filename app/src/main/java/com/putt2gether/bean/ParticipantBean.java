package com.putt2gether.bean;

/**
 * Created by Ajay on 25/08/2016.
 *
 * "userId": 42,
 "full_name": "Vishal",
 "user_name": "vishaltest@soms.in",
 "registered_mobile_number": "5522887799",
 "photo_url": "BASE_URLprofile/default.jpg",
 "invitation_status": "Pending",
 "thumb_url": "BASE_URLprofile/default.jpg",
 "add_player_type": "0",
 "handicap_value": 2

 */
public class ParticipantBean {
    private String userId;
    private String full_name;
    private String user_name;
    private String registered_mobile_number;
    private String invitation_status;
    private String thumb_url;

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    private String photo_url;

    private String add_player_type;
    private String handicap_value;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHandicap_value() {
        return handicap_value;
    }

    public void setHandicap_value(String handicap_value) {
        this.handicap_value = handicap_value;
    }

    public String getAdd_player_type() {
        return add_player_type;
    }

    public void setAdd_player_type(String add_player_type) {
        this.add_player_type = add_player_type;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getRegistered_mobile_number() {
        return registered_mobile_number;
    }

    public void setRegistered_mobile_number(String registered_mobile_number) {
        this.registered_mobile_number = registered_mobile_number;
    }

    public String getInvitation_status() {
        return invitation_status;
    }

    public void setInvitation_status(String invitation_status) {
        this.invitation_status = invitation_status;
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
}
