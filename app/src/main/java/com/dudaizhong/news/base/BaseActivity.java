package com.dudaizhong.news.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.dudaizhong.news.app.App;
import com.dudaizhong.news.di.component.ActivityComponent;
import com.dudaizhong.news.di.component.DaggerActivityComponent;
import com.dudaizhong.news.di.module.ActivityModule;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Dudaizhong on 2016/9/13.
 * mvp Activity基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxFragmentActivity implements BaseView {

    @Inject
    protected T mPresenter;
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
//        mPresenter = createPresenter();
        initInject();
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

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }


//    protected abstract T createPresenter();

    protected abstract void initInject();

    protected abstract int getLayoutId();

    protected abstract void initEventAndData(Bundle savedInstanceState);
}
