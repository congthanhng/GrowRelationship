package com.spark.cong.growrelationship.Commons;

import android.content.res.Resources;
import android.util.TypedValue;

public class Constant {
    public static final int SPAN_COUNT = 2;

    //spacing betwwen item grid was converted from dpi to pixel
    public static final int ITEM_SPACING = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10f, Resources.getSystem().getDisplayMetrics());

    public static final int REQUEST_CODE_PEOPLE = 123;
}
