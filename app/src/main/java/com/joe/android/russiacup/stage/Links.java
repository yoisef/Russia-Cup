package com.joe.android.russiacup.stage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("team")
    @Expose
    private Team team;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
