package com.putt2gether.bean.LeaderboardBean;

/**
 * Created by Abc on 8/30/2016.
 */
public class Leader {

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getHolesplayed() {
        return holesplayed;
    }

    public void setHolesplayed(String holesplayed) {
        this.holesplayed = holesplayed;
    }

    public String getNet_gross_play() {
        return net_gross_play;
    }

    public void setNet_gross_play(String net_gross_play) {
        this.net_gross_play = net_gross_play;
    }

    String rank;
    String player;
    String holesplayed;
    String net_gross_play;

    public String getSubplayer() {
        return subplayer;
    }

    public void setSubplayer(String subplayer) {
        this.subplayer = subplayer;
    }

    String subplayer;
    String player_id;

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }
}
