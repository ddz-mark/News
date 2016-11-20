package com.dudaizhong.news.modules.zhihu.presenter;

import android.content.Context;
import android.content.Intent;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.activity.ZhihuCommentActivity;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuCommentData;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetail;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetailZip;
import com.dudaizhong.news.modules.zhihu.presenter.contract.ZhihuDetailContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Func2;

/**
 * Created by Markable on 2016/11/19.
 */

public class ZhihuDetailPresenter extends ZhihuDetailContract.Presenter {

    private ZhihuDetailZip mZhihuDetailZip;

    @Inject
    public ZhihuDetailPresenter() {
    }

    @Override
    public void showLoading() {
        getView().showLoading();
    }

    @Override
    public void getContent(int id) {
        Observable<ZhihuDetail> zhihuDetailObservable = RetrofitSingleton.getInstance().getZhihuDetail(id);
        Observable<ZhihuCommentData> zhihuCommentDataObservable = RetrofitSingleton.getInstance().getZhihuCommentInfo(id);
        Observable<ZhihuDetailZip> zhihuZipObservable = Observable.zip(zhihuDetailObservable, zhihuCommentDataObservable, new Func2<ZhihuDetail, ZhihuCommentData, ZhihuDetailZip>() {
            @Override
            public ZhihuDetailZip call(ZhihuDetail zhihuDetail, ZhihuCommentData data) {
                return new ZhihuDetailZip(zhihuDetail, data);
            }
        });

        zhihuZipObservable.compose(this.<ZhihuDetailZip>bindToLifeCycle())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getView().hideLoading();
                    }
                })
                .subscribe(new Observer<ZhihuDetailZip>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhihuDetailZip zip) {
                        mZhihuDetailZip = zip;
                        getView().showContent(zip);
                    }
                });

    }

    @Override
    public void insertLike() {

    }

    @Override
    public void deleteLike() {

    }

    @Override
    public void mainToCommentActivity(Context context) {
        if (null != mZhihuDetailZip)
            context.startActivity(ZhihuCommentActivity.getZhihuCommentIntent(context, mZhihuDetailZip.mZhihuCommentData));
    }
}
