package com.dudaizhong.news.modules.login;

import com.dudaizhong.news.base.BaseModel;
import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.main.MainContract;

/**
 * Created by Dudaizhong on 2016/9/30.
 */

public interface LoginContract {


    interface View extends BaseView {

        void showLoading();

        void onLoginSuccess();

        void onLoginFaild();
    }

    abstract static class Presenter extends BasePresenter<View> {

        protected abstract void login(String userName,String password);

        protected abstract void register();
    }
}
