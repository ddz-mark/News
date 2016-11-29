package com.dudaizhong.news.modules.zhihu.presenter;

import android.content.Context;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;
import com.dudaizhong.news.modules.zhihu.presenter.contract.DailyContract;
import com.orhanobut.logger.Logger;

import rx.Observer;
import rx.functions.Action0;

/**
 * Created by Dudaizhong on 2016/9/24.
 */

public class DailyPresenter extends DailyContract.Presenter {

    @Override
    public void getContent(final Context context) {

        RetrofitSingleton.getInstance().getZhihuListNews()
                .compose(this.<ZhihuList>bindToLifeCycle())
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
                        try {
                            getView().showError();
                            RetrofitSingleton.disposeFailureInfo(e, context);
                        } catch (Exception e1) {
                            Logger.e(e1.getMessage());
                        }
                    }

                    @Override
                    public void onNext(ZhihuList zhihuList) {
                        getView().showContent(zhihuList);
                    }
                });
    }

}
