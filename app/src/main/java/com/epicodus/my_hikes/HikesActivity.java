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

public class HikesActivity extends AppCompatActivity {
    private TextView mLocationTextView;
    private ListView mListView;
    private String[] hikes = new String[] {"RattleSnake Ledge", "Tenerife Falls",
            "Wallace Falls", "Lake Serene", "Bridal Veil Falls", "Franklin Falls",
            "Twin Falls", "Boulder River", "Murhut Falls", "Marymere Falls",
            "Carter Falls", "Myrtle Falls,", "Snoqualmie Falls",
            "Bagley Lakes Loop", "Mirror Lake"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hikes);

        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hikes);
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
