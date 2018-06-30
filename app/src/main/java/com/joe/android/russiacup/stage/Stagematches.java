package com.joe.android.russiacup.stage;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stagematches {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("fixtures")
    @Expose
    private List<Fixture> fixtures = null;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

}