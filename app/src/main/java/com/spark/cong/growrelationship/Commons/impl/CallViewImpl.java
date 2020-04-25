package com.spark.cong.growrelationship.Commons.impl;

import androidx.fragment.app.FragmentManager;

import com.spark.cong.growrelationship.Commons.CallView;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.View.Fragment.BottomSheetFragment;

public class CallViewImpl implements CallView {

    private static CallViewImpl sInstance;

    private CallViewImpl(){}

    //Lazy Initialization Singleton pattern
    public static CallViewImpl getInstance(){
        if (sInstance == null){
            sInstance = new CallViewImpl();
        }
        return sInstance;
    }

    @Override
    public void callBottomSheet(FragmentManager fragmentManager, String tag) {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(fragmentManager,tag);
    }

    @Override
    public void callBottomSheet(FragmentManager fragmentManager, String tag, int position) {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(position);
        bottomSheetFragment.show(fragmentManager,tag);
    }

    @Override
    public void callBottomSheet(FragmentManager fragmentManager, String tag, int position, boolean isFragment, ItemClickListener listener) {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(position, true,listener );
        bottomSheetFragment.show(fragmentManager,tag);
    }
}
