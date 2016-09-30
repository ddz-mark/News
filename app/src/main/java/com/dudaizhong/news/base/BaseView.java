package com.dudaizhong.news.base;

import android.content.Context;

import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/13.
 * View的基础类
 */

public interface BaseView {

    /**
     * 加载时显示加载框
     */
    void showLoading();

    /**
     * 加载完成时隐藏加载框
     */
    void hideLoading();

    /**
     * 显示提示消息
     */
    void showToastMessage(String message);

    /**
     * 在p层没有此方法，需要在这里写上
     *
     * @param <V>
     * @return
     */
    <V> Observable.Transformer<V, V> bind();
}
