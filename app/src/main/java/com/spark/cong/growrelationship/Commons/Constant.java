package com.spark.cong.growrelationship.Commons;

import android.content.res.Resources;
import android.util.TypedValue;

import com.spark.cong.growrelationship.R;

public class Constant {

    public static final int SPAN_COUNT = 2;

    //spacing betwwen item grid was converted from dpi to pixel
    public static final int ITEM_SPACING = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10f, Resources.getSystem().getDisplayMetrics());

    public static final int REQUEST_CODE_PEOPLE = 123;
    public static final int REQUEST_CODE_GROUP_TO_PEOPLE = 125;

    public static final String INTENT_MAIN_TO_PEOPLE = "intent_group";
    public static final String BUNDLE_MAIN_TO_PEOPLE = "bundle_group";

    public static final int[] tabSelected = {
            R.drawable.ic_friendship_selected,
            R.drawable.ic_person_black_24dp,
            R.drawable.ic_people_black_24dp
    };

    public static final int[] tabUnSelected = {
            R.drawable.icon_friendship,
            R.drawable.ic_person_outline_black_24dp,
            R.drawable.ic_people_outline_black_24dp
    };

    public static final int TAB_SIZE = 3;
}
