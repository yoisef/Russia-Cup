package com.joe.android.russiacup.Newes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("news_link")
    @Expose
    private String newsLink;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("news_title")
    @Expose
    private String newsTitle;
    @SerializedName("image_link")
    @Expose
    private String imageLink;

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}