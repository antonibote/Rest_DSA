package edu.upc.dsa.restproject;

import android.annotation.SuppressLint;
import android.view.View;

public interface RecyclerClickViewListener {
    public void recyclerViewListClicked(int position);

    @SuppressLint("NonConstantResourceId")
    void onClick(View view);
}
