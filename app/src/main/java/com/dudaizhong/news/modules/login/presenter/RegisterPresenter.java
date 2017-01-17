package com.dudaizhong.news.modules.login.presenter;

import android.content.Context;

import com.dudaizhong.news.base.utils.rxUtils.RxHelper;
import com.dudaizhong.news.base.utils.rxUtils.SimpleSubscriber;
import com.dudaizhong.news.common.db.RealmHelper;
import com.dudaizhong.news.modules.login.domain.User;
import com.dudaizhong.news.modules.login.presenter.contract.RegisterContract;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Markable on 2016/11/26.
 */

public class RegisterPresenter extends RegisterContract.Presenter {

    @Inject
    public RegisterPresenter() {
    }

    @Override
    public void register(Context context, String name, String password) {
        getView().showLoading();
        Observable.just(true)
                .doOnNext(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(new SimpleSubscriber<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (name.equals("") || password.equals("")) {
                            getView().registerFailed();
                        } else {
                            User user = new User();
                            user.setName(name);
                            user.setPassword(password);
                            RealmHelper.getIntance(context).register(user);
                            getView().registerSuccess();
                        }

                    }
                });

    }
}
