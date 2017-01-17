package com.dudaizhong.news.modules.login.presenter;

import android.content.Context;

import com.dudaizhong.news.base.utils.rxUtils.RxHelper;
import com.dudaizhong.news.base.utils.rxUtils.SimpleSubscriber;
import com.dudaizhong.news.common.db.RealmHelper;
import com.dudaizhong.news.modules.login.domain.User;
import com.dudaizhong.news.modules.login.presenter.contract.LoginContract;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Dudaizhong on 2016/9/30.
 * Github:ddz-mark
 */

public class LoginPresenter extends LoginContract.Presenter {

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(Context context, String userName, String password) {
        getView().showLoading();
        Observable.just(true)
                .doOnNext(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(new SimpleSubscriber<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (RealmHelper.getIntance(context).login(userName, password)) {
                            getView().onLoginSuccess();
                        } else {
                            getView().onLoginFaild();
                        }
                    }
                });

    }

}
