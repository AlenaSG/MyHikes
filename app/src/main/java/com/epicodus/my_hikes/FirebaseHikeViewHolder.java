package com.epicodus.my_hikes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseHikeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseHikeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindHike(Hike hike) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.hikeNameTextView);
        TextView directionsTextView = (TextView) mView.findViewById(R.id.directionsTextView);

        nameTextView.setText(hike.getName());
        directionsTextView.setText(hike.getDirections());

    }

    @Override
    public void onClick(View view) {
        final ArrayList<Hike> hikes = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HIKES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    hikes.add(snapshot.getValue(Hike.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, HikeDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("hikes", Parcels.wrap(hikes));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}