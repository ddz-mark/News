package com.dudaizhong.news.modules.zhihu.presenter;

import android.content.Context;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.HotList;
import com.dudaizhong.news.modules.zhihu.presenter.contract.HotContract;
import com.orhanobut.logger.Logger;

import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public class HotPresenter extends HotContract.Presenter {

    @Override
    public void getContent(final Context context) {
        RetrofitSingleton.getInstance().getHotList()
                .compose(this.<HotList>bindToLifeCycle())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getView().hideLoading();
                    }
                })
                .subscribe(new Observer<HotList>() {
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
                    public void onNext(HotList list) {
                        getView().showContent(list);
                    }
                });
    }
}
