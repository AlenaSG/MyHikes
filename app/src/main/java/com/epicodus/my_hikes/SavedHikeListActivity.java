package com.epicodus.my_hikes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedHikeListActivity extends AppCompatActivity {
    private DatabaseReference mHikeReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hikes);
        ButterKnife.bind(this);

        mHikeReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HIKES);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Hike, FirebaseHikeViewHolder>
                (Hike.class, R.layout.hike_list_item, FirebaseHikeViewHolder.class,
                        mHikeReference) {

            @Override
            protected void populateViewHolder(FirebaseHikeViewHolder viewHolder,
                                              Hike model, int position) {
                viewHolder.bindHike(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}