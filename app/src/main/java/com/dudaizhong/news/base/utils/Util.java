package com.dudaizhong.news.base.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.dudaizhong.news.app.App;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;

/**
 * Created by Markable on 2016/11/21.
 */

public class Util {

    public static final String DEFAULT_FORMAT = "yyyy年MM月dd日";

    /**
     * 解决一些后台返回的空字段导致空指针.
     * <p>
     * Hugo
     */
    public static String safeText(String msg) {
        if (TextUtils.isEmpty(msg)) return "";
        return msg;
    }

    public static String safeText(int msg) {
        return safeText(msg + "");
    }

    public static String safeText(float msg) {
        return safeText(msg + "");
    }

    /**
     * 将服务器返回的时间戳转化为需要的日期格式
     *
     * @param format    需要的日期格式 如 "yyyy-MM-dd HH:mm:ss"
     * @param timestamp 时间戳
     */
    public static String formatDate(String format, Long timestamp) {

        try {
            SimpleDateFormat f = new SimpleDateFormat(format == null ? DEFAULT_FORMAT : format);
            return f.format(timestamp);
        } catch (Exception e) {
            Logger.e("formatDate?" + e.toString());
        }
        return "-1";
    }

    /**
     * 只关注是否联网
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 保存文字到剪贴板
     * @param context
     * @param text
     */
    public static void copyToClipBoard(Context context, String text) {
        ClipData clipData = ClipData.newPlainText("url", text);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
        ToastUtil.showToast(App.getAppContext(),"已复制到剪贴板");
    }
}
