package com.dudaizhong.news.modules.zhihu.activity;

import android.os.Bundle;

import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.modules.zhihu.presenter.ThemeDetailPresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.ThemeDetailContract;

/**
 * Created by Markable on 2016/11/19.
 */

public class ThemeDetailActivity extends BaseActivity<ThemeDetailPresenter> implements ThemeDetailContract.View {

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

    }
}
