package com.epicodus.my_hikes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HikesActivity extends AppCompatActivity {
    //public static final String TAG = HikesActivity.class.getSimpleName();

//    private SharedPreferences mSharedPreferences;
//    private String mRecentCity;

    //@Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private HikeListAdapter mAdapter;

    public ArrayList<Hike> mHikes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hikes);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");

        //mLocationTextView.setText("Here are all the hikes near: " + city);

        getHikes(city);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentCity = mSharedPreferences.getString(Constants.PREFERENCES_CITY_KEY, null);
//        Log.d("Shared Pref City", mRecentCity);
//        if (mRecentCity != null) {
//            getHikes(mRecentCity);
//        }
    }



    private void getHikes(String city) {
        final HikesServiceJava hikesService = new HikesServiceJava();

        hikesService.findHikes(city, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mHikes = hikesService.processResults(response);

                HikesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new HikeListAdapter(getApplicationContext(), mHikes);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(HikesActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}

