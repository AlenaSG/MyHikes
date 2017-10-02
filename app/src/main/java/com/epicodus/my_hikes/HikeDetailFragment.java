package com.epicodus.my_hikes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HikeDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.saveHikeButton) Button mSaveHikeButton;
    @Bind(R.id.hikeNameTextView) TextView mHikeNameTextView;
    @Bind(R.id.directionsTextView) TextView mDirectionsTextView;

    private Hike mHike;
    private ArrayList<Hike> mHikes;
    private int mPosition;

    public static HikeDetailFragment newInstance(ArrayList<Hike> hikes, Integer position) {
        HikeDetailFragment hikeDetailFragment = new HikeDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_HIKES, Parcels.wrap(hikes));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);


        hikeDetailFragment.setArguments(args);
        return hikeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHikes = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_HIKES));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mHike = mHikes.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hike_detail, container, false);
        ButterKnife.bind(this, view);

        mHikeNameTextView.setText(mHike.getName());
        mDirectionsTextView.setText(mHike.getDirections());

        mSaveHikeButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == mSaveHikeButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference hikeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_HIKES)
                    .child(uid);

            DatabaseReference pushRef = hikeRef.push();
            String pushId = pushRef.getKey();
            mHike.setPushId(pushId);
            pushRef.setValue(mHike);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
