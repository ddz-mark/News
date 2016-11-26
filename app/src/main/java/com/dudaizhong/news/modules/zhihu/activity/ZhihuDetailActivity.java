package com.dudaizhong.news.modules.zhihu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.base.utils.HtmlUtil;
import com.dudaizhong.news.base.utils.ToastUtil;
import com.dudaizhong.news.modules.gank.activity.AIActivity;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuCommentData;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuShortCommentData;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetail;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetailZip;
import com.dudaizhong.news.modules.zhihu.presenter.ZhihuDetailPresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.ZhihuDetailContract;

import butterknife.Bind;
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
    @Bind(R.id.scroll_view)
    NestedScrollView mScrollView;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    private int id;
    private boolean isShow = true;
    ZhihuDetail zhihuDetail;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    public static Intent getZhihuDetailIntent(Context context, int id) {
        Intent intent = new Intent(context, ZhihuDetailActivity.class);
        intent.putExtra("id", id);
        return intent;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        Intent getId = getIntent();
        id = getId.getIntExtra("id", 0);
        initView();
        getPresenter().queryLike(ZhihuDetailActivity.this, id);
        getPresenter().getContent(id);
    }

    private void initView() {
        setToolBar(mToolbar, "");
        mWebView.getSettings().setJavaScriptEnabled(true);

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 0 && isShow) {  //下移隐藏
                    isShow = false;
                    mLlDetailBottom.animate().translationY(mLlDetailBottom.getHeight());
                } else if (scrollY - oldScrollY < 0 && !isShow) {    //上移出现
                    isShow = true;
                    mLlDetailBottom.animate().translationY(0);
                }
            }
        });
    }

    @OnClick({R.id.tv_detail_bottom_like, R.id.tv_detail_bottom_comment, R.id.tv_detail_bottom_share, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detail_bottom_like:
                ToastUtil.showToast(this, "对不起，目前暂不支持");
                break;
            case R.id.tv_detail_bottom_comment:
                getPresenter().mainToCommentActivity(this);
                break;
            case R.id.tv_detail_bottom_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, zhihuDetail.shareUrl);
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_app)));
                break;
            case R.id.fab:
                if (mFab.isSelected()) {
                    mFab.setSelected(false);
                    mPresenter.deleteLike(ZhihuDetailActivity.this);
                } else {
                    mFab.setSelected(true);
                    mPresenter.insertLike(ZhihuDetailActivity.this);
                }
                break;
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        if (null != mProgressBar)
            mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showContent(ZhihuDetailZip zhihuDetailZip) {

        zhihuDetail = zhihuDetailZip.mZhihuDetail;
        ZhihuCommentData zhihuCommentData = zhihuDetailZip.mZhihuCommentData;

        Glide.with(this)
                .load(zhihuDetail.image)
                .placeholder(R.mipmap.ic_launcher)
                .into(mBackdrop);
        mCollapsingToolbar.setTitle(zhihuDetail.title);
        String htmlData = HtmlUtil.createHtmlData(zhihuDetail.body, zhihuDetail.css, zhihuDetail.js);
        mWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                ToastUtil.showToast(ZhihuDetailActivity.this, "网络错误");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoading();
            }
        });

        mTvDetailBottomLike.setText(zhihuCommentData.popularity + "点赞");
        mTvDetailBottomComment.setText(zhihuCommentData.comments + "评论");
    }

    @Override
    public void showIsLike(boolean isLike) {
        mFab.setSelected(isLike);
    }

    //监听WebView内的返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
