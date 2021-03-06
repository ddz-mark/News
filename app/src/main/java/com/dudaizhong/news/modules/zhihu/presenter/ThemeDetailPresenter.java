package com.dudaizhong.news.modules.zhihu.presenter;

import android.content.Context;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.SectionDetail;
import com.dudaizhong.news.modules.zhihu.domain.ThemeDetail;
import com.dudaizhong.news.modules.zhihu.presenter.contract.ThemeDetailContract;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import rx.Observer;
import rx.functions.Action0;

/**
 * Created by Markable on 2016/11/19.
 */

public class ThemeDetailPresenter extends ThemeDetailContract.Presenter {

    @Inject
    public ThemeDetailPresenter() {
    }

    @Override
    public void getContent(final Context context, int id) {
        RetrofitSingleton.getInstance().getThemeDetail(id)
                .compose(this.<ThemeDetail>bindToLifeCycle())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getView().hideLoading();
                    }
                })
                .subscribe(new Observer<ThemeDetail>() {
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
                    public void onNext(ThemeDetail themeDetail) {
                        getView().showContent(themeDetail);
                    }
                });
    }
}
