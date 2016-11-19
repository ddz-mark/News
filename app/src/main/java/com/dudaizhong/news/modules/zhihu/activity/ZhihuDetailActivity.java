package com.dudaizhong.news.modules.zhihu.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.modules.zhihu.presenter.ZhihuDetailPresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.ZhihuDetailContract;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Markable on 2016/11/19.
 */

public class ZhihuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements ZhihuDetailContract.View {

    @Bind(R.id.backdrop)
    ImageView mBackdrop;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppbar;
    @Bind(R.id.web_view)
    WebView mWebView;
    @Bind(R.id.tv_detail_bottom_like)
    TextView mTvDetailBottomLike;
    @Bind(R.id.tv_detail_bottom_comment)
    TextView mTvDetailBottomComment;
    @Bind(R.id.tv_detail_bottom_share)
    TextView mTvDetailBottomShare;
    @Bind(R.id.ll_detail_bottom)
    FrameLayout mLlDetailBottom;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.main_content)
    CoordinatorLayout mMainContent;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

    }


    @OnClick({R.id.tv_detail_bottom_like, R.id.tv_detail_bottom_comment, R.id.tv_detail_bottom_share, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detail_bottom_like:
                break;
            case R.id.tv_detail_bottom_comment:
                break;
            case R.id.tv_detail_bottom_share:
                break;
            case R.id.fab:
                break;
        }
    }
}
