package com.epicodus.my_hikes;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Hike {

    String mName;
    String mDirections;
//    private ArrayList<Activity> mActivities;
//    private double mLat;
//    private double mLon;

    public Hike() {

    }

    public Hike(String name, String directions)
    //ArrayList<Activity> activities, double lat, double lon
    {
        this.mName = name;
        this.mDirections = directions;
//        this.mActivities = activities;
//        this.mLat = lat;
//        this.mLon = lon;
    }

    public String getName() {

        return mName;
    }

    public String getDirections() {

        return mDirections;
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

}
