package com.dudaizhong.news.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.dudaizhong.news.R;
import com.dudaizhong.news.app.App;
import com.dudaizhong.news.di.component.ActivityComponent;
import com.dudaizhong.news.di.component.DaggerActivityComponent;
import com.dudaizhong.news.di.module.ActivityModule;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/13.
 * mvp Activity基类,所有Activity都应继承自
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements BaseView {

    @Inject
    protected T mPresenter;
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initWindow();
        ButterKnife.bind(this);
        initInject();
        mActivity = this;
        if (mPresenter != null)
            mPresenter.attachView(this);

        initEventAndData(savedInstanceState);
    }

    protected void initWindow(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setNavigationBarTintColor(R.color.colorPrimary);
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    protected T getPresenter() {
        return mPresenter;
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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

    public <V> Observable.Transformer<V, V> bind() {
        return bindToLifecycle();
    }

    protected abstract void initInject();

    protected abstract int getLayoutId();

    protected abstract void initEventAndData(Bundle savedInstanceState);
}
