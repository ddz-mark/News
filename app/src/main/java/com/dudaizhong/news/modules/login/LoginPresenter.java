package com.dudaizhong.news.modules.login;

/**
 * Created by Dudaizhong on 2016/9/30.
 */

public class LoginPresenter extends LoginContract.Presenter {

    @Override
    protected void login(String userName, String password) {

        if(userName.toString().equals("dudaizhong")&&password.toString().equals("123456")){
            getView().onLoginSuccess();
        }else {
            getView().onLoginFaild();
        }
    }

    @Override
    protected void register() {

    }
}
