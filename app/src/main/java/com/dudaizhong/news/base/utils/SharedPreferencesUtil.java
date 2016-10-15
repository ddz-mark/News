package com.dudaizhong.news.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dudaizhong.news.app.Constants;

/**
 * Created by Dudaizhong on 2016/10/15.
 */

public class SharedPreferencesUtil {


    private final static String USER_INFO = "user_info";
    private final static String FIRST = "first";

    private static Context context;

    public static void init(Context context) {
        SharedPreferencesUtil.context = context;
    }

    public static void setVersion(String version) {
        SharedPreferences.Editor editor = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.VERSION, version);
        editor.apply();
    }

    public static String getVersion() {
        SharedPreferences preferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return preferences.getString(Constants.VERSION, "");
    }

    /**
     * 检查是否加载引导页
     * @param first
     */
    public static void setFirst(boolean first) {
        SharedPreferences.Editor editor = context.getSharedPreferences(FIRST, Context.MODE_PRIVATE).edit();
        editor.putBoolean(Constants.FIRST, first);
        editor.apply();
    }

    public static Boolean getFirst() {
        SharedPreferences preferences = context.getSharedPreferences(FIRST, Context.MODE_PRIVATE);
        return preferences.getBoolean(Constants.FIRST, false);
    }
}
