package com.epicodus.my_hikes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HikeDetailFragment extends Fragment {
    @Bind(R.id.saveHikeButton) Button mSaveHikeButton;
    @Bind(R.id.hikeNameTextView) TextView mHikeNameTextView;
    @Bind(R.id.directionsTextView) TextView mDirectionsTextView;

    private Hike mHike;

    public static HikeDetailFragment newInstance(Hike hike) {
       HikeDetailFragment hikeDetailFragment = new HikeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("hike", Parcels.wrap(hike));
       hikeDetailFragment.setArguments(args);
        return hikeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHike = Parcels.unwrap(getArguments().getParcelable("hike"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hike_detail, container, false);
        ButterKnife.bind(this, view);

        mHikeNameTextView.setText(mHike.getName());
        mDirectionsTextView.setText(mHike.getDirections());

        return view;
    }

}
