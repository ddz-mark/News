package com.dudaizhong.news.modules.gank.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.base.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Markable on 2016/11/25.
 */

public class AIActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.web_view)
    WebView mWebView;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    private String type;
    private String url;

    @Override
    protected void initInject() {
    }

    public static Intent getAIActivityIntent(Context context, String type, String url) {
        Intent intent = new Intent(context, AIActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("url", url);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ai;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        url = intent.getStringExtra("url");
        setToolBar(mToolbar, type);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);// 使用当前WebView处理跳转
                return true;//true表示此事件在此处被处理，不需要再广播
            }

            @Override   //转向错误时的处理
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // TODO Auto-generated method stub
                ToastUtil.showToast(AIActivity.this, "网络错误");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (null != mProgressBar)
                    mProgressBar.setVisibility(View.GONE);
            }
        });

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
