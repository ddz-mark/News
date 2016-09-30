package com.dudaizhong.news.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by Dudaizhong on 2016/9/13.
 */

public abstract class BaseFragment<T extends BasePresenter> extends RxFragment implements BaseView {

    protected T mPresenter;
    protected View parentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (parentView == null)
            parentView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, parentView);
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);
        initEventAndData();
        return parentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
        mPresenter = null;
    }


    protected T getPresenter() {
        return mPresenter;
    }

    /**
     * 创建Presenter, 然后通过调用{@link #getPresenter()}来使用生成的Presenter
     *
     * @return Presenter
     */
    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
