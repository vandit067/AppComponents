package com.vandit.samples.appcomponents.util;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by vandi on 1/10/2017.
 */

public class AppUtils {

    public static boolean isTablet(Context mContext){
        return (mContext.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
