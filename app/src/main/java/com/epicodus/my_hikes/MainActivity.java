package com.epicodus.my_hikes;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.findHikesButton) Button mFindHikesButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FragmentManager fm = getFragmentManager();
        HikeDialog hikeDialog = new HikeDialog();
        hikeDialog.show(fm, "Sample Fragment");


        Typeface amaticboldFont = Typeface.createFromAsset(getAssets(), "fonts/amaticbold.ttf");
        mAppNameTextView.setTypeface(amaticboldFont);

        mFindHikesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindHikesButton) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, HikesActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
}