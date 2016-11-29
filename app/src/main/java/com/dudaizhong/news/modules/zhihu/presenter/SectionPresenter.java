package com.dudaizhong.news.modules.zhihu.presenter;

import android.content.Context;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.SectionList;
import com.dudaizhong.news.modules.zhihu.presenter.contract.SectionContract;
import com.orhanobut.logger.Logger;

import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public class SectionPresenter extends SectionContract.Presenter {

    @Override
    public void getContent(final Context context) {
        RetrofitSingleton.getInstance().getSectionList()
                .compose(this.<SectionList>bindToLifeCycle())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getView().hideLoading();
                    }
                })
                .subscribe(new Observer<SectionList>() {
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
                    public void onNext(SectionList list) {
                        getView().showContent(list);
                    }
                });
    }
}
