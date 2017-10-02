package com.epicodus.my_hikes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.my_hikes.Hike;
import com.epicodus.my_hikes.Hike;

import java.util.ArrayList;

public class HikePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Hike> mHikes;

    public HikePagerAdapter(FragmentManager fm, ArrayList<Hike> hikes) {
        super(fm);
        mHikes = hikes;
    }

    @Override
    public Fragment getItem(int position) {
        return HikeDetailFragment.newInstance(mHikes, position);
    }

    @Override
    public int getCount() {
        return mHikes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mHikes.get(position).getName();
    }
}