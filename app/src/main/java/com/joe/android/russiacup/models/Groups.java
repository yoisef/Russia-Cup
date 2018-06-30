package com.joe.android.russiacup.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Groups {

    @SerializedName("leagueCaption")
    @Expose
    private String leagueCaption;
    @SerializedName("matchday")
    @Expose
    private Integer matchday;
    @SerializedName("standings")
    @Expose
    private Standings standings;

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public Standings getStandings() {
        return standings;
    }

    public void setStandings(Standings standings) {
        this.standings = standings;
    }

}
