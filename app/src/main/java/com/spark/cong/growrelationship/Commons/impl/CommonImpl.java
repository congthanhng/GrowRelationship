package com.spark.cong.growrelationship.Commons.impl;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.spark.cong.growrelationship.Commons.CloseKeyboardAfterInput;

public class CommonImpl implements CloseKeyboardAfterInput {
    public static CommonImpl sInstance;

    public static CommonImpl getInstance(){
        if(sInstance == null){
            sInstance = new CommonImpl();
        }
        return sInstance;
    }

    //close keyboard after done input
    @Override
    public void closeKeyboard(View v,Context context){
        if(v != null){
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
        }
    }
}
