package com.putt2gether.fragments.mygroups;

/**
 * Created by Ajay on 08/09/2016.
 *
 *  "group_id": 1,
 "group_name": "Group1",
 "profile_img": "",
 "admin_id": 1,
 "is_admin": "1"
 */

public class MyGroupBean {

    private String group_id;
    private String group_name;
    private String group_image;
    private String admin_id;
    private String is_admin;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_image() {
        return group_image;
    }

    public void setGroup_image(String group_image) {
        this.group_image = group_image;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }
}