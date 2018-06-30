package com.joe.android.russiacup.matches;

public class Result
{
    private Integer goalsHomeTeam;

    public Integer getGoalsHomeTeam() { return this.goalsHomeTeam; }

    public void setGoalsHomeTeam(Integer goalsHomeTeam) { this.goalsHomeTeam = goalsHomeTeam; }

    private Integer goalsAwayTeam;

    public Integer getGoalsAwayTeam() { return this.goalsAwayTeam; }

    public void setGoalsAwayTeam(Integer goalsAwayTeam) { this.goalsAwayTeam = goalsAwayTeam; }

    private HalfTime halfTime;

    public HalfTime getHalfTime() { return this.halfTime; }

    public void setHalfTime(HalfTime halfTime) { this.halfTime = halfTime; }
}