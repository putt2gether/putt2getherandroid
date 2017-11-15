package com.putt2gether.bean;

/**
 * Created by Ajay on 09/08/2016.
 *
 *
 * {
 "output": {
 "status": "1",
 "Event": {
 "event_id": "43",
 "message": "Your Event My deepika Event on 07/12/2016 for 2 participants has been created!",
 "data": {
 "event_id": 43,
 "admin_id": 3,
 "golf_course_name": "temp golf course",
 "event_name": "My deepika Event",
 "event_date": "2016-07-12",
 "event_start_time": "19:30:00",
 "total_hole_num": 18,
 "hole_start_from": 1,
 "format_name": "Gross Strokeplay",
 "stoke_name": "net strokeplay",
 "tee_name": {
 "Men": "",
 "MenColor": "",
 "Ladies": "",
 "LadiesColor": "",
 "Junior": "Blue",
 "JuniorColor": {
 "r": 0,
 "g": 0,
 "b": 153
 }
 },
 "game_status": 0,
 "admin_name": "Vishal Tripathi"
 }
 },
 "message": "Event List"
 }
 }

 */
public class EventPostBean {
    private String event_id;
    private String message;
    private String admin_id;
    private String golf_course_name;
    private String event_name;
    private String event_date;

    private String event_start_time;
    private String total_hole_num;
    private String hole_start_from;
    private String format_name;
    private String stoke_name;
    private String Men;
    private String MenColor;
    private String Ladies;
    private String LadiesColor;
    private String Junior;
    private String JuniorColor;
    private String game_status;
    private String admin_name;


    public String getMen() {
        return Men;
    }

    public void setMen(String men) {
        Men = men;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getGolf_course_name() {
        return golf_course_name;
    }

    public void setGolf_course_name(String golf_course_name) {
        this.golf_course_name = golf_course_name;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_start_time() {
        return event_start_time;
    }

    public void setEvent_start_time(String event_start_time) {
        this.event_start_time = event_start_time;
    }

    public String getTotal_hole_num() {
        return total_hole_num;
    }

    public void setTotal_hole_num(String total_hole_num) {
        this.total_hole_num = total_hole_num;
    }

    public String getHole_start_from() {
        return hole_start_from;
    }

    public void setHole_start_from(String hole_start_from) {
        this.hole_start_from = hole_start_from;
    }

    public String getFormat_name() {
        return format_name;
    }

    public void setFormat_name(String format_name) {
        this.format_name = format_name;
    }

    public String getStoke_name() {
        return stoke_name;
    }

    public void setStoke_name(String stoke_name) {
        this.stoke_name = stoke_name;
    }

    public String getMenColor() {
        return MenColor;
    }

    public void setMenColor(String menColor) {
        MenColor = menColor;
    }

    public String getLadies() {
        return Ladies;
    }

    public void setLadies(String ladies) {
        Ladies = ladies;
    }

    public String getLadiesColor() {
        return LadiesColor;
    }

    public void setLadiesColor(String ladiesColor) {
        LadiesColor = ladiesColor;
    }

    public String getJunior() {
        return Junior;
    }

    public void setJunior(String junior) {
        Junior = junior;
    }

    public String getJuniorColor() {
        return JuniorColor;
    }

    public void setJuniorColor(String juniorColor) {
        JuniorColor = juniorColor;
    }

    public String getGame_status() {
        return game_status;
    }

    public void setGame_status(String game_status) {
        this.game_status = game_status;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }


}
