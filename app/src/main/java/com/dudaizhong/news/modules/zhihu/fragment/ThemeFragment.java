package com.dudaizhong.news.modules.zhihu.fragment;

import android.support.v4.app.Fragment;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.modules.main.MainContract;
import com.dudaizhong.news.modules.main.MainPresenter;

import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/18.
 */

public class ThemeFragment extends BaseFragment<MainPresenter> implements MainContract.View {
    @Override
    protected MainPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_hot;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public <V> Observable.Transformer<V, V> bind() {
        return bindToLifecycle();
    }


}
