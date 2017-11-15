package com.putt2gether.putt.delegates;

/**
 * Created by Ajay on 28/11/2016.
 *
 * "scorer_count": 0,
 "player_id": 1,
 "scorere_id": 3,
 "event_id": 64,
 "player_name": "deepika@soms.in",
 "scorer_name": "vishalt@soms.in"

 */
public class DelegateBean {

    private String scorerCount;
    private String handicapValue;
    private String playerID;
    private String scorerID;
    private String eventID;
    private String playerName;
    private String scorerName;



    public String getHandicapValue() {
        return handicapValue;
    }

    public void setHandicapValue(String handicapValue) {
        this.handicapValue = handicapValue;
    }

    public String getScorerCount() {
        return scorerCount;
    }

    public void setScorerCount(String scorerCount) {
        this.scorerCount = scorerCount;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getScorerID() {
        return scorerID;
    }

    public void setScorerID(String scorerID) {
        this.scorerID = scorerID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getScorerName() {
        return scorerName;
    }

    public void setScorerName(String scorerName) {
        this.scorerName = scorerName;
    }
}
