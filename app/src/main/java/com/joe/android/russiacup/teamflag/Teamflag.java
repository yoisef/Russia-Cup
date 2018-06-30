package com.joe.android.russiacup.teamflag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teamflag {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("shortName")
    @Expose
    private Object shortName;
    @SerializedName("squadMarketValue")
    @Expose
    private Object squadMarketValue;
    @SerializedName("crestUrl")
    @Expose
    private String crestUrl;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getShortName() {
        return shortName;
    }

    public void setShortName(Object shortName) {
        this.shortName = shortName;
    }

    public Object getSquadMarketValue() {
        return squadMarketValue;
    }

    public void setSquadMarketValue(Object squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

}