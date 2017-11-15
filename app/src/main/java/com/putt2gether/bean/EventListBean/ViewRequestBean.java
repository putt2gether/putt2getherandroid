package com.putt2gether.bean.EventListBean;

/**
 * Created by Ajay on 18/10/2016.
 *
 *  "is_accepted": "Pending",
 "event_id": 134,
 "event_name": "SIMRANGUJRAL'S EVENT",
 "user_id": 1,
 "user": "Deepika Test",
 "photo_url": "http://clients.vfactor.in/puttdemo/api_v2/uploads/profile/1472563281_dc5aaac2167c4b057427a451baa05c91.jpg",
 "is_started": "Closed",
 "thumb_url": "http://clients.vfactor.in/puttdemo/api_v2/uploads/profile/thumb/1472563281_dc5aaac2167c4b057427a451baa05c91.jpg"

 */
public class ViewRequestBean {

    String is_accepted;
    String event_id;
    String event_name;
    String user;
    String photo_url;
    String thumb_url;
    String handicap;
    String user_id;
    String is_started;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIs_started() {
        return is_started;
    }

    public void setIs_started(String is_started) {
        this.is_started = is_started;
    }

    public String getHandicap() {
        return handicap;
    }

    public void setHandicap(String handicap) {
        this.handicap = handicap;
    }

    public String getIs_accepted() {
        return is_accepted;
    }

    public void setIs_accepted(String is_accepted) {
        this.is_accepted = is_accepted;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
}
