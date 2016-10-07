package com.dudaizhong.news.modules.zhihu.presenter;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;
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
                .compose(this.<ZhihuList>bindToLifeCycle())
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
                .subscribe(new Observer<ZhihuList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ZhihuList zhihuList) {
                        getView().showContent(zhihuList);
                    }
                });
    }


    @Override
    public void showLoading() {
        getView().showLoading();
    }

}
