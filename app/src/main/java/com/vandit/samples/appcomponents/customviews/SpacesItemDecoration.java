package com.vandit.samples.appcomponents.customviews;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by vandi on 1/16/2017.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    public final int mSpace;

    public SpacesItemDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.bottom = mSpace;
        outRect.right = mSpace;

        // Add top margin only for firtst item of views to avoid double space between items.
        if(parent.getChildAdapterPosition(view) == 0){
            outRect.top = mSpace;
        }
    }
}
