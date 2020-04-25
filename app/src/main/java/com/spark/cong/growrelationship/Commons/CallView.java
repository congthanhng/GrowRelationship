package com.spark.cong.growrelationship.Commons;

import android.app.Application;

import androidx.fragment.app.FragmentManager;

public interface CallView {

    /**
     * call bottomSheet
     * @param fragmentManager
     * @param tag
     */
    void callBottomSheet(FragmentManager fragmentManager, String tag);

    /**
     * call bottomSheet with position of item
     * @param fragmentManager
     * @param tag
     * @param position
     */
    void callBottomSheet(FragmentManager fragmentManager, String tag , int position);

    /**
     * call bottomSheet with positon of item from a fragment
     * @param fragmentManager
     * @param tag
     * @param position
     * @param isFragment
     * @param listener
     */
    void callBottomSheet(FragmentManager fragmentManager, String tag, int position, boolean isFragment, ItemClickListener listener);

}
