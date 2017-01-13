package com.dudaizhong.news.base.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dudaizhong.news.R;

import retrofit2.http.Url;

/**
 * Created by Markable on 2016/11/28.
 */

public class ImageLoader {

    public static void load(Context context, String url, ImageView view) {
        Glide.with(context).load(url).placeholder(R.mipmap.ic_photo_place).crossFade().into(view);
    }

    public static void loadWithNoBg(Context context,String url,ImageView view){
        Glide.with(context).load(url).crossFade().into(view);
    }

    public static void clear(Context context) {
        Glide.get(context).clearMemory();
    }
}
