package com.epicodus.my_hikes;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Hike {

    String name;
    String directions;
//    private ArrayList<Activity> mActivities;
//    private double mLat;
//    private double mLon;
    private String pushId;
    String index;

    public Hike() {

    }

    public Hike(String name, String directions)
    //ArrayList<Activity> activities, double lat, double lon
    {
        this.name = name;
        this.directions = directions;
//        this.mActivities = activities;
//        this.mLat = lat;
//        this.mLon = lon;
        this.index = "not_specified";
    }

    public String getName() {
        return name;
    }

    public String getDirections() {
        return directions;
    }


//    public ArrayList<Activity> getActivities() {
//        return mActivities;
//    }
//
//    public double getLat() {
//        return mLat;
//    }
//
//    public double getLon() {
//        return mLon;
//    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
