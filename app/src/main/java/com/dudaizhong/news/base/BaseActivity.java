package com.dudaizhong.news.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by Dudaizhong on 2016/9/13.
 * mvp Activity基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxFragmentActivity implements BaseView {

    protected T mPresenter;
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = createPresenter();
        mActivity = this;
        if (mPresenter != null)
            mPresenter.attachView(this);
        initEventAndData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        mPresenter = null;
        ButterKnife.unbind(this);
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

    protected abstract void initEventAndData(Bundle savedInstanceState);
}
