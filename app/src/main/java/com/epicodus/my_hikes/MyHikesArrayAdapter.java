package com.epicodus.my_hikes;

import android.content.Context;
import android.widget.ArrayAdapter;


public class MyHikesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mHikes;
    private String[] mDifficulties;

    public MyHikesArrayAdapter(Context mContext, int resource, String[] mHikes, String[] mDifficulties) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mHikes = mHikes;
        this.mDifficulties = mDifficulties;
    }

    @Override
    public Object getItem(int position) {
        String hike = mHikes[position];
        String difficulty = mDifficulties[position];
        return String.format("%s \nThis hike is %s", hike, difficulty);
    }

    @Override
    public int getCount() {
        return mHikes.length;
    }
}
