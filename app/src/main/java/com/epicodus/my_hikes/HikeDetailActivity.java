package com.epicodus.my_hikes;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HikeDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private HikePagerAdapter adapterViewPager;
    ArrayList<Hike> mHikes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_detail);
        ButterKnife.bind(this);

        mHikes = Parcels.unwrap(getIntent().getParcelableExtra("hikes"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new HikePagerAdapter(getSupportFragmentManager(), mHikes);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}