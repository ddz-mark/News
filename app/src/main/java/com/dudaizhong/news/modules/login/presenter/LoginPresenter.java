package com.dudaizhong.news.modules.login.presenter;

import android.content.Context;

import com.dudaizhong.news.common.db.RealmHelper;
import com.dudaizhong.news.modules.login.domain.User;
import com.dudaizhong.news.modules.login.presenter.contract.LoginContract;

import javax.inject.Inject;

/**
 * Created by Dudaizhong on 2016/9/30.
 */

public class LoginPresenter extends LoginContract.Presenter {

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(Context context, String userName, String password) {

        if (RealmHelper.getIntance(context).login(userName, password)) {
            getView().onLoginSuccess();
        } else {
            getView().onLoginFaild();
        }
    }

}
