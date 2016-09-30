package com.dudaizhong.news.modules.zhihu.presenter;

import android.util.Log;

import com.dudaizhong.news.common.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuListNews;
import com.dudaizhong.news.modules.zhihu.presenter.contract.DailyContract;

import rx.Observer;
import rx.functions.Action0;

/**
 * Created by Dudaizhong on 2016/9/24.
 */

public class DailyPresenter extends DailyContract.Presenter {

    @Override
    public void getContent() {
        RetrofitSingleton.getInstance().getZhihuListNews()
                .compose(this.<ZhihuListNews>bindToLifeCycle())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        getView().showLoading();
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getView().hideLoading();
                    }
                })
                .subscribe(new Observer<ZhihuListNews>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ZhihuListNews zhihuListNews) {
                        getView().showContent(zhihuListNews);
                    }
                });
    }


    @Override
    public void showLoading() {
        getView().showLoading();
    }

}
