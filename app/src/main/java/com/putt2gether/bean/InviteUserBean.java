package com.putt2gether.bean;

/**
 * Created by Ajay on 04/08/2016.
 *
 * "is_accepted": "Pending",
 "event_id": 2,
 "event_name": "My Borthday Evet",
 "event_display_number": "0000000000",
 "start_date": "2016-09-30",
 "event_start_time": "09:00:00",
 "is_started": "Pending",
 "golf_course_name": "Delhi Golf Club",
 "golf_course_id": 1,
 "format_id": 2,
 "admin_id": 1,
 "admin": "Deepika",
 "creation_date": "2016-07-07 18:55:24",
 "is_submit_score": "0",
 "read_status": "0",
 "add_player_type": "0",
 "location": "New Delhi",
 "formate_name": "Gross Strokeplay"

 */
public class InviteUserBean  {

    private String is_accepted;
    private String event_id;
    private String event_name;
    private String event_display_number;
    private String start_date;
    private String event_start_time;
    private String is_started;
    private String golf_course_name;
    private String golf_course_id;
    private String format_id;
    private String is_individual;
    private String is_edit;
    private String banner_image;
    private String banner_href;

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getBanner_href() {
        return banner_href;
    }

    public void setBanner_href(String banner_href) {
        this.banner_href = banner_href;
    }

    public String getIs_edit() {
        return is_edit;
    }

    public void setIs_edit(String is_edit) {
        this.is_edit = is_edit;
    }

    public String getIs_individual() {
        return is_individual;
    }

    public void setIs_individual(String is_individual) {
        this.is_individual = is_individual;
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

    public String getEvent_display_number() {
        return event_display_number;
    }

    public void setEvent_display_number(String event_display_number) {
        this.event_display_number = event_display_number;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEvent_start_time() {
        return event_start_time;
    }

    public void setEvent_start_time(String event_start_time) {
        this.event_start_time = event_start_time;
    }

    public String getIs_started() {
        return is_started;
    }

    public void setIs_started(String is_started) {
        this.is_started = is_started;
    }

    public String getGolf_course_name() {
        return golf_course_name;
    }

    public void setGolf_course_name(String golf_course_name) {
        this.golf_course_name = golf_course_name;
    }

    public String getGolf_course_id() {
        return golf_course_id;
    }

    public void setGolf_course_id(String golf_course_id) {
        this.golf_course_id = golf_course_id;
    }

    public String getFormat_id() {
        return format_id;
    }

    public void setFormat_id(String format_id) {
        this.format_id = format_id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getIs_submit_score() {
        return is_submit_score;
    }

    public void setIs_submit_score(String is_submit_score) {
        this.is_submit_score = is_submit_score;
    }

    public String getRead_status() {
        return read_status;
    }

    public void setRead_status(String read_status) {
        this.read_status = read_status;
    }

    public String getAdd_player_type() {
        return add_player_type;
    }

    public void setAdd_player_type(String add_player_type) {
        this.add_player_type = add_player_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFormate_name() {
        return formate_name;
    }

    public void setFormate_name(String formate_name) {
        this.formate_name = formate_name;
    }

    private String admin_id;
    private String admin;
    private String creation_date;
    private String is_submit_score;
    private String read_status;
    private String add_player_type;
    private String location;
    private String formate_name;
}
