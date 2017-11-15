package com.putt2gether.bean;

/**
 * Created by Ajay on 08/07/2016.
 *
 * "golf_course_id": 1,
 "golf_course_name": "Delhi Golf Club",
 "latitude": "28.598445",
 "longitude": "77.235646",
 "city_id": 1,
 "city_name": "New Delhi",
 "event_count": 0,
 "has_event": 0

 */
public class SearchGolfCouseBean {

    private String golf_course_id;
    private String golf_course_name;
    private String latitude;
    private String longitude;
    private String city_id;
    private String city_name;
    private String event_count;
    private String has_event;

    public String getGolf_course_id() {
        return golf_course_id;
    }

    public void setGolf_course_id(String golf_course_id) {
        this.golf_course_id = golf_course_id;
    }

    public String getGolf_course_name() {
        return golf_course_name;
    }

    public void setGolf_course_name(String golf_course_name) {
        this.golf_course_name = golf_course_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getEvent_count() {
        return event_count;
    }

    public void setEvent_count(String event_count) {
        this.event_count = event_count;
    }

    public String getHas_event() {
        return has_event;
    }

    public void setHas_event(String has_event) {
        this.has_event = has_event;
    }
}
