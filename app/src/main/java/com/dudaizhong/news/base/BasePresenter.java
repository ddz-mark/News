package com.dudaizhong.news.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Dudaizhong on 2016/9/13.
 * presenter的基础类，控制订阅的生命周期
 */

public abstract class BasePresenter<V extends BaseView> {

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    protected V getView() {
        if (mViewRef.get() != null)
            return mViewRef.get();
        return null;
    }


    public boolean isAttachedView() {
        return mViewRef.get() == null ? false : true;
    }

    public void detachView() {
        if (isAttachedView()) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 此方法用于在presenter里取消Subscription,防止内存泄露
     * @param <V>
     * @return
     */
    protected <V> Observable.Transformer<V, V> bindToLifeCycle() {
        return getView().bind();
    }
}
