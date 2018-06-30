package com.joe.android.russiacup;

import java.util.HashMap;

public class imgloading {

    private HashMap<String,String> imageUrl;

    public imgloading(String title,HashMap<String,String> imageUrl){


        this.imageUrl = imageUrl;
    }



    public HashMap<String, String> getImageUrl() {
        return imageUrl;
    }
}