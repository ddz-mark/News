package com.dudaizhong.news.modules.gank;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.modules.main.MainContract;
import com.dudaizhong.news.modules.main.MainPresenter;

import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/16.
 */

public class GankFragment extends BaseFragment<MainPresenter> implements MainContract.View{

    @Override
    protected MainPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frament_gank;
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
        return null;
    }


}
