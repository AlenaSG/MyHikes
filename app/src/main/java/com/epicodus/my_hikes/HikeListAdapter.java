package com.epicodus.my_hikes;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HikeListAdapter extends RecyclerView.Adapter<HikeListAdapter.HikeViewHolder> {
    private ArrayList<Hike> mHikes = new ArrayList<>();
    private Context mContext;

    public HikeListAdapter(Context context, ArrayList<Hike> hikes) {
        mContext = context;
        mHikes = hikes;
    }

    @Override
    public HikeListAdapter.HikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hike_list_item, parent, false);
        HikeViewHolder viewHolder = new HikeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HikeListAdapter.HikeViewHolder holder, int position) {
        holder.bindHike(mHikes.get(position));
    }

    @Override
    public int getItemCount() {
        return mHikes.size();
    }

    public class HikeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.hikeNameTextView)
        TextView mNameTextView;
        @Bind(R.id.directionsTextView) TextView mDirectionsTextView;
        private Context mContext;

        public HikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, HikeDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("hikes", Parcels.wrap(mHikes));
            mContext.startActivity(intent);
        }

        public void bindHike(Hike hike) {
            mNameTextView.setText(hike.getName());
            mDirectionsTextView.setText(hike.getDirections());
        }
    }
}