package com.epicodus.my_hikes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseHikeViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    View mView;
    Context mContext;
    public TextView mHikeNameTextView;


    public FirebaseHikeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindHike(Hike hike) {
        mHikeNameTextView = (TextView) mView.findViewById(R.id.hikeNameTextView);

        //TextView nameTextView = (TextView) mView.findViewById(R.id.hikeNameTextView);
        TextView directionsTextView = (TextView) mView.findViewById(R.id.directionsTextView);

        mHikeNameTextView.setText(hike.getName());
        directionsTextView.setText(hike.getDirections());

    }


    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }
}