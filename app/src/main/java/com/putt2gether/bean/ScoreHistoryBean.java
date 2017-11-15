package com.putt2gether.bean;

/**
 * Created by Abc on 8/24/2016.
 * <p>
 * "event_id": 369,
 * "event_name": "VISHAL TRI'S EVENT",
 * "golf_course_name": "Noida Golf Course",
 * "format_name": "Gross Strokeplay",
 * "total": 0,
 * "current_position": "0",
 * "no_of_player": 1,
 * "eagle": 0,
 * "gross_score": 0,
 * "no_of_birdies": 0,
 * "no_of_pars": 0
 */
public class ScoreHistoryBean {

    private String formatID;
    private String eventID;
    private String eventName;
    private String golfCourseName;
    private String formatName;
    private String total;
    private String currentPosion;
    private String noOfPlayer;

    private String eagle;
    private String grossScore;
    private String noOfBirdies;
    private String noOfPars;
    private String date;
    private String no_of_player_accepted;


    public String getFormatID() {
        return formatID;
    }

    public void setFormatID(String formatID) {
        this.formatID = formatID;
    }

    public String getNo_of_player_accepted() {
        return no_of_player_accepted;
    }

    public void setNo_of_player_accepted(String no_of_player_accepted) {
        this.no_of_player_accepted = no_of_player_accepted;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getGolfCourseName() {
        return golfCourseName;
    }

    public void setGolfCourseName(String golfCourseName) {
        this.golfCourseName = golfCourseName;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public String getCurrentPosion() {
        return currentPosion;
    }

    public void setCurrentPosion(String currentPosion) {
        this.currentPosion = currentPosion;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNoOfPlayer() {
        return noOfPlayer;
    }

    public void setNoOfPlayer(String noOfPlayer) {
        this.noOfPlayer = noOfPlayer;
    }

    public String getEagle() {
        return eagle;
    }

    public void setEagle(String eagle) {
        this.eagle = eagle;
    }

    public String getGrossScore() {
        return grossScore;
    }

    public void setGrossScore(String grossScore) {
        this.grossScore = grossScore;
    }

    public String getNoOfBirdies() {
        return noOfBirdies;
    }

    public void setNoOfBirdies(String noOfBirdies) {
        this.noOfBirdies = noOfBirdies;
    }

    public String getNoOfPars() {
        return noOfPars;
    }

    public void setNoOfPars(String noOfPars) {
        this.noOfPars = noOfPars;
    }
}
