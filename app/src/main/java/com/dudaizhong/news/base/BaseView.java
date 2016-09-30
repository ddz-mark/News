package com.dudaizhong.news.base;

import android.content.Context;

import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/13.
 * View的基础类
 */

public interface BaseView {

    /**
     * 在p层没有此方法，需要在这里写上
     *
     * @param <V>
     * @return
     */
    <V> Observable.Transformer<V, V> bind();
}
