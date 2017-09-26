package com.epicodus.my_hikes;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.savedHikesButton) Button mSavedHikesButton;
    @Bind(R.id.findHikesButton) Button mFindHikesButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Bind(R.id.aboutButton) Button mAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface amaticboldFont = Typeface.createFromAsset(getAssets(), "fonts/amaticbold.ttf");
        mAppNameTextView.setTypeface(amaticboldFont);


        mFindHikesButton.setOnClickListener(this);
        mSavedHikesButton.setOnClickListener(this);

        mAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

    }//end of onCreate

    private static boolean isCityNameValid(String city) {//form validation
        if (city == null) {
            return false;
        }
        if (city.isEmpty()) {
            return false;
        }
        if (city.matches("[ a-zA-Z]+")) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == mSavedHikesButton) {
            Intent intent = new Intent(MainActivity.this, SavedHikeListActivity.class);
            startActivity(intent);
        }
        if(v == mFindHikesButton) {

            Intent intent = new Intent(MainActivity.this, HikeListActivity.class);
            //intent.putExtra("city", city);
            startActivity(intent);
        }
    }

}