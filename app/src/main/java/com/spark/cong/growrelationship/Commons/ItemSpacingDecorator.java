package com.spark.cong.growrelationship.Commons;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Spacing between items of recycler grid view
 */
public class ItemSpacingDecorator extends RecyclerView.ItemDecoration {

    private int mSpacing;
    private int mSpanCount;

    public ItemSpacingDecorator(int mSpacing, int mSpanCount) {
        this.mSpacing = mSpacing;
        this.mSpanCount = mSpanCount;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final int position = parent.getChildLayoutPosition(view);
        Log.i("ItemDecorator", "getItemOffsets: "+ position);
        final int column = position % mSpanCount;
        final int parentWidth = parent.getWidth();
        outRect.left = mSpacing - column * mSpacing / mSpanCount;
        outRect.right = (column + 1) * mSpacing / mSpanCount;

        if (position < mSpanCount) {
            outRect.top = mSpacing;
        }
        outRect.bottom = mSpacing;
    }
}
