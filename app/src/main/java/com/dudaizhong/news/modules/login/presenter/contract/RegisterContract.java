package com.dudaizhong.news.modules.login.presenter.contract;

import android.content.Context;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;

/**
 * Created by Markable on 2016/11/26.
 */

public interface RegisterContract {


    interface View extends BaseView {

        void registerSuccess();

        void registerFailed();
    }

    abstract static class Presenter extends BasePresenter<View> {

        public abstract void register(Context context, String name, String password);
    }
}
