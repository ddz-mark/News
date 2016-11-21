package com.dudaizhong.news.modules.zhihu.presenter.contract;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.zhihu.domain.ThemeDetail;

/**
 * Created by Markable on 2016/11/19.
 */

public interface ThemeDetailContract {

    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        void showContent(ThemeDetail themeDetail);
    }

    abstract static class Presenter extends BasePresenter<View> {

        public abstract void getContent(int id);

    }

}
