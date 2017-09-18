package com.epicodus.my_hikes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public static final String TAG = HikesActivity.class.getSimpleName();

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Hike> mHikes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hikes);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");

        mLocationTextView.setText("Here are all the hikes near: " + city);

        getHikes(city);
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

                        String[] hikeNames = new String[mHikes.size()];
                        for (int i = 0; i < hikeNames.length; i++) {
                            hikeNames[i] = mHikes.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(HikesActivity.this,
                                android.R.layout.simple_list_item_1, hikeNames);
                        mListView.setAdapter(adapter);

                        for (Hike hike : mHikes) {
                            Log.d(TAG, "Name: " + hike.getName());
                        }
                    }
                });
            }
        });
    }
}


//        MyHikesArrayAdapter adapter = new MyHikesArrayAdapter(this, android.R.layout.simple_list_item_1, hikes, difficulties);
//        mListView.setAdapter(adapter);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String hike = ((TextView)view).getText().toString();
//                Toast.makeText(HikesActivity.this, hike, Toast.LENGTH_LONG).show();
//            }
//        });



//    }
//
//
//}
