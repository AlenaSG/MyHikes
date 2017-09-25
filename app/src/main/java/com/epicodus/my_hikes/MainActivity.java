package com.epicodus.my_hikes;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   // public static final String TAG = MainActivity.class.getSimpleName();

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedCityReference;

    private ValueEventListener mSearchedCityReferenceListener;

    @Bind(R.id.findHikesButton) Button mFindHikesButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Bind(R.id.aboutButton) Button mAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        mSearchedCityReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_CITY);//pinpoint city node


            mSearchedCityReferenceListener = mSearchedCityReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot citySnapshot : dataSnapshot.getChildren()) {
                    String city = citySnapshot.getValue().toString();
                    Log.d("Cities updated", "city: " + city); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface amaticboldFont = Typeface.createFromAsset(getAssets(), "fonts/amaticbold.ttf");
        mAppNameTextView.setTypeface(amaticboldFont);


//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mFindHikesButton.setOnClickListener(this);

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
        if(v == mFindHikesButton) {
            String city = mLocationEditText.getText().toString();

            saveCityToFirebase(city);
//            if(!(city).equals("")) {
//                addToSharedPreferences(city);
//            }
            Intent intent = new Intent(MainActivity.this, HikesActivity.class);
            intent.putExtra("city", city);
            startActivity(intent);
        }
    }

    public void saveCityToFirebase(String city) {
        mSearchedCityReference.push().setValue(city);
    }

    @Override//for activity, not listener, not nested within addValue block
    protected void onDestroy() {
        super.onDestroy();
        mSearchedCityReference.removeEventListener(mSearchedCityReferenceListener);
    }

//    private void addToSharedPreferences(String city) {
//        mEditor.putString(Constants.PREFERENCES_CITY_KEY, city).apply();
//    }
}