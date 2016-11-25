package com.dudaizhong.news.modules.launch;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;

/**
 * Created by Dudaizhong on 2016/9/28.
 */

public interface LaunchContract {

    interface View extends BaseView {
        void jumpToMain();
    }

    abstract static class Presenter extends BasePresenter<View> {

        protected abstract void jumpToMain();
    }
}
