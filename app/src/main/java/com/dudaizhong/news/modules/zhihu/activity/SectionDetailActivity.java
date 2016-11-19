package com.dudaizhong.news.modules.zhihu.activity;

import android.os.Bundle;

import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.modules.zhihu.presenter.SectionDetailPresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.SectionDetailContract;

/**
 * Created by Markable on 2016/11/19.
 */

public class SectionDetailActivity extends BaseActivity<SectionDetailPresenter> implements SectionDetailContract.View {

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

    }
}
