package com.putt2gether.bean;

/**
 * Created by Ajay on 21/06/2016.
 */
public class GolfCourseBean {
    private String golfCorseName;
    private String golfCourseAddress;
    private String golfCourseID;
    private String hasEvent;

    public String getHasEvent() {
        return hasEvent;
    }

    public void setHasEvent(String hasEvent) {
        this.hasEvent = hasEvent;
    }

    public String getGolfCourseID() {
        return golfCourseID;
    }

    public void setGolfCourseID(String golfCourseID) {
        this.golfCourseID = golfCourseID;
    }

    public String getGolfCorseName() {
        return golfCorseName;
    }

    public void setGolfCorseName(String golfCorseName) {
        this.golfCorseName = golfCorseName;
    }

    public String getGolfCourseAddress() {
        return golfCourseAddress;
    }

    public void setGolfCourseAddress(String golfCourseAddress) {
        this.golfCourseAddress = golfCourseAddress;
    }
}
