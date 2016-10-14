package com.dudaizhong.news.modules.login;

import javax.inject.Inject;

/**
 * Created by Dudaizhong on 2016/9/30.
 */

public class LoginPresenter extends LoginContract.Presenter {

    @Inject
    public LoginPresenter() {
    }

    @Override
    protected void login(String userName, String password) {

        if (userName.toString().equals("dudaizhong") && password.toString().equals("123456")) {
            getView().onLoginSuccess();
        } else {
            getView().onLoginFaild();
        }
    }

    @Override
    protected void register() {

    }
}
