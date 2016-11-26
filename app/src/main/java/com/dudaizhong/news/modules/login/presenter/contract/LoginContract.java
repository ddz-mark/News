package com.dudaizhong.news.modules.login.presenter.contract;

import android.content.Context;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;

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

        public abstract void login(Context context,String userName,String password);

    }
}
