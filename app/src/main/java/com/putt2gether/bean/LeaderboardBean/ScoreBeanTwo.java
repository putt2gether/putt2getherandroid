package com.putt2gether.bean.LeaderboardBean;

import java.io.Serializable;

/**
 * Created by Abc on 9/22/2016.
 */
public class ScoreBeanTwo implements Serializable {

    public String getParticipant_ID() {
        return participant_ID;
    }

    public void setParticipant_ID(String participant_ID) {
        this.participant_ID = participant_ID;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getHandicap_Value() {
        return handicap_Value;
    }

    public void setHandicap_Value(String handicap_Value) {
        this.handicap_Value = handicap_Value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String participant_ID;
    String photo_url;
    String handicap_Value;
    String name;

    public String getSelf_delegate() {
        return self_delegate;
    }

    public void setSelf_delegate(String self_delegate) {
        this.self_delegate = self_delegate;
    }

    String self_delegate;

}
