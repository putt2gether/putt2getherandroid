package com.putt2gether.fragments.mygroups;

/**
 * Created by Ajay on 23/11/2016.
 *
 * "display_name": "Deepika Test",
 "photo_url":"http://localhost/putt_final/api_v2/uploads/profile/noimage.png",
 "self_handicap": 6,
 "member_id": 1,
 "is_admin": "1"
 */
public class GroupDetailBean {

    private String display_name;
    private String photo_url;
    private String self_handicap;
    private String member_id;
    private String is_admin;

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getSelf_handicap() {
        return self_handicap;
    }

    public void setSelf_handicap(String self_handicap) {
        this.self_handicap = self_handicap;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }
}
