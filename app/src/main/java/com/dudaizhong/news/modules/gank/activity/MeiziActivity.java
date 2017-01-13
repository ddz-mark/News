package com.dudaizhong.news.modules.gank.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.base.utils.ImageLoader;
import com.dudaizhong.news.base.utils.rxUtils.SimpleSubscriber;
import com.dudaizhong.news.modules.gank.presenter.MeiziPresenter;
import com.dudaizhong.news.modules.gank.presenter.contract.MeiziContract;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Markable on 2016/11/25.
 */

public class MeiziActivity extends BaseActivity<MeiziPresenter> implements MeiziContract.View {

    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;
    @Bind(R.id.imageView)
    PhotoView mImageView;
    private String url;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    public static Intent getMeiziActivityIntent(Context context, String url) {
        Intent intent = new Intent(context, MeiziActivity.class);
        intent.putExtra("url", url);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_meizi;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        showLoading();
        Observable.just(1)
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        showLoading();
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        hideLoading();
                    }
                })
                .subscribe(new SimpleSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        ImageLoader.load(MeiziActivity.this, url, mImageView);
                    }
                });

    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

}
