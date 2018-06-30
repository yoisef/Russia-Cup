package com.joe.android.russiacup.Newes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("Author")
    @Expose
    private String author;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}