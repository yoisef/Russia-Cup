package com.joe.android.russiacup.matches;

import java.util.Date;

public class Fixture
{
    private Links2 _links;

    public Links2 getLinks() { return this._links; }

    public void setLinks(Links2 _links) { this._links = _links; }

    private Date date;

    public Date getDate() { return this.date; }

    public void setDate(Date date) { this.date = date; }

    private String status;

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }

    private int matchday;

    public int getMatchday() { return this.matchday; }

    public void setMatchday(int matchday) { this.matchday = matchday; }

    private String homeTeamName;

    public String getHomeTeamName() { return this.homeTeamName; }

    public void setHomeTeamName(String homeTeamName) { this.homeTeamName = homeTeamName; }

    private String awayTeamName;

    public String getAwayTeamName() { return this.awayTeamName; }

    public void setAwayTeamName(String awayTeamName) { this.awayTeamName = awayTeamName; }

    private Result result;

    public Result getResult() { return this.result; }

    public void setResult(Result result) { this.result = result; }

    /*private object odds;

    public object getOdds() { return this.odds; }

    public void setOdds(object odds) { this.odds = odds; }
    */
}