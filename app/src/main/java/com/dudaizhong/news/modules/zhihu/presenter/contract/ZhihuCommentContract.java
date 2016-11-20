package com.dudaizhong.news.modules.zhihu.presenter.contract;

import android.content.Context;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetailZip;

/**
 * Created by Markable on 2016/11/20.
 */

public interface ZhihuCommentContract {

    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showContent(ZhihuDetailZip zhihuDetailZip);

    }

    abstract static class Presenter extends BasePresenter<View> {

        public abstract void showLoading();

    }

}
