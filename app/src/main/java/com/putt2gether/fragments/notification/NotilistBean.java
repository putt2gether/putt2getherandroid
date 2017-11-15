package com.putt2gether.fragments.notification;

/**
 * Created by Abc on 9/8/2016.
 */
public class NotilistBean {

    public String getNotieventid() {
        return notieventid;
    }

    public void setNotieventid(String notieventid) {
        this.notieventid = notieventid;
    }

    public String getNotiname() {
        return notiname;
    }

    public void setNotiname(String notiname) {
        this.notiname = notiname;
    }

    public String getNotitime() {
        return notitime;
    }

    public void setNotitime(String notitime) {
        this.notitime = notitime;
    }

    String notieventid;
    String notiname;
    String notitime;
    String notificationID;
    String pushType;
    String admin_id;

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getNotiread_status() {
        return notiread_status;
    }

    public void setNotiread_status(String notiread_status) {
        this.notiread_status = notiread_status;
    }

    String notiread_status;
}
