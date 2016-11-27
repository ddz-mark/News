package com.dudaizhong.news.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dudaizhong.news.app.Constants;

/**
 * Created by Dudaizhong on 2016/10/15.
 */

public class SharedPreferencesUtil {

    public static final String USER_INFO = "user_info";
    public static final String FIRST = "first";
    public static final String DAY_NIGHT = "day_night";
    public static final String AUTO_CACHE = "auto_cache";
    public static final String CLEAR_CACHE = "clear_cache";
    public static final String SUGGEST = "suggest";

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
     *
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

    /**
     * 是否显示夜间模式
     *
     * @param isNight
     */
    public static void setDayNight(boolean isNight) {
        SharedPreferences.Editor editor = context.getSharedPreferences(DAY_NIGHT, Context.MODE_PRIVATE).edit();
        editor.putBoolean(DAY_NIGHT, isNight);
        editor.apply();
    }

    public static boolean getDayNight() {
        SharedPreferences preferences = context.getSharedPreferences(DAY_NIGHT, Context.MODE_PRIVATE);
        return preferences.getBoolean(DAY_NIGHT, false);
    }

    /**
     * 是否显示自动缓存
     *
     * @param isAcache
     */
    public static void setAutoCache(boolean isAcache) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AUTO_CACHE, Context.MODE_PRIVATE).edit();
        editor.putBoolean(AUTO_CACHE, isAcache);
        editor.apply();
    }

    public static boolean getAutoCache() {
        SharedPreferences preferences = context.getSharedPreferences(AUTO_CACHE, Context.MODE_PRIVATE);
        return preferences.getBoolean(AUTO_CACHE, false);
    }

}
