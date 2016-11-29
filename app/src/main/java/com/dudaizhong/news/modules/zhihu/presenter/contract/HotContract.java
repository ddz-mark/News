package com.dudaizhong.news.modules.zhihu.presenter.contract;

import android.content.Context;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.zhihu.domain.HotList;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public interface HotContract {

    interface  View extends BaseView {

        void showLoading();

        void hideLoading();

        void showContent(HotList hotList);

        void showError();
    }

    abstract static class Presenter extends BasePresenter<View> {

        public abstract void getContent(Context context);

    }
}
