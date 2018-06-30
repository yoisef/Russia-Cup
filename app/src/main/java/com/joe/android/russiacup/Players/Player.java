package com.joe.android.russiacup.Players;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("jerseyNumber")
    @Expose
    private Integer jerseyNumber;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("nationality")
    @Expose
    private Object nationality;
    @SerializedName("contractUntil")
    @Expose
    private Object contractUntil;
    @SerializedName("marketValue")
    @Expose
    private Object marketValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Object getNationality() {
        return nationality;
    }

    public void setNationality(Object nationality) {
        this.nationality = nationality;
    }

    public Object getContractUntil() {
        return contractUntil;
    }

    public void setContractUntil(Object contractUntil) {
        this.contractUntil = contractUntil;
    }

    public Object getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Object marketValue) {
        this.marketValue = marketValue;
    }

}
