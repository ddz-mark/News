package com.dudaizhong.news.modules.zhihu.presenter.contract;

import android.content.Context;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.zhihu.domain.ThemeList;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public interface ThemeContract {

    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showContent(ThemeList themeList);

        void showError();
    }

    abstract static class Presenter extends BasePresenter<View> {

        public abstract void getContent(Context context);

    }

}
