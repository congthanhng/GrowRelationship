package com.spark.cong.growrelationship.Commons.impl;

import android.app.Application;
import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.spark.cong.growrelationship.Commons.CallView;
import com.spark.cong.growrelationship.View.Fragment.GroupBottomSheetFragment;

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
        GroupBottomSheetFragment groupBottomSheetFragment = new GroupBottomSheetFragment();
        groupBottomSheetFragment.show(fragmentManager,tag);
    }
}
