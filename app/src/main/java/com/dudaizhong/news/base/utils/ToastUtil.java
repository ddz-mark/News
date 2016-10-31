package com.dudaizhong.news.base.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;
import com.dudaizhong.news.app.App;

import javax.xml.datatype.Duration;

/**
 * Created by HugoXie on 16/5/20.
 *
 * Email: Hugo3641@gamil.com
 * GitHub: https://github.com/xcc3641
 * Info:
 */
public class ToastUtil {

    private static Toast toast;

    public static void showToast(Context context,
                                  String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showLongToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_LONG);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
