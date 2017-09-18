package com.epicodus.my_hikes;

/**
 * Created by alenagolovina on 9/17/17.
 */

public class Activity {
    private String mDescription;
    private String mUrl;
    private double mLength;

    Activity(String description, String url, Double length) {
        description = mDescription;
        url = mUrl;
        length = mLength;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public double getLength() {
        return mLength;
    }
}
