package com.epicodus.my_hikes;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface amaticboldFont = Typeface.createFromAsset(getAssets(), "fonts/amaticbold.ttf");
        mAppNameTextView.setTypeface(amaticboldFont);


        mFindHikesButton.setOnClickListener(this);
        mSavedHikesButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };

    }//end of onCreate


    @Override
        public void onStart() {
            super.onStart();
            mAuth.addAuthStateListener(mAuthListener);
        }

        @Override
        public void onStop() {
            super.onStop();
            if (mAuthListener != null) {
                mAuth.removeAuthStateListener(mAuthListener);
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

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
        if(v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);
        }
    }

}

