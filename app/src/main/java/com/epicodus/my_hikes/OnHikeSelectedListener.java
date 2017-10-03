package com.epicodus.my_hikes;

import java.util.ArrayList;

public interface OnHikeSelectedListener {
    public void onHikeSelected(Integer position, ArrayList<Hike> hikes);
}