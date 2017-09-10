package com.epicodus.my_hikes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HikesActivity extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] hikes = new String[] {"RattleSnake Ledge", "Tenerife Falls",
            "Wallace Falls", "Lake Serene", "Bridal Veil Falls", "Franklin Falls",
            "Twin Falls", "Boulder River", "Murhut Falls", "Marymere Falls",
            "Carter Falls", "Myrtle Falls,", "Snoqualmie Falls",
            "Bagley Lakes Loop", "Mirror Lake"};
    private String[] difficulties = new String[] {"Moderate", "Easy", "Strenuous",
            "Easy", "Easy", "Strenuous", "Moderate", "Easy", "Easy",
            "Moderate", "Strenuous", "Strenuous", "Moderate", "Easy", "Moderate", "Moderate" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hikes);
        ButterKnife.bind(this);

        MyHikesArrayAdapter adapter = new MyHikesArrayAdapter(this, android.R.layout.simple_list_item_1, hikes, difficulties);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(HikesActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the hikes near: " + location);
    }
}
