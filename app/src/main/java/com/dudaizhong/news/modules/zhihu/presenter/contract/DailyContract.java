package com.dudaizhong.news.modules.zhihu.presenter.contract;

import android.content.Context;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;

/**
 * Created by Dudaizhong on 2016/9/24.
 */

public interface DailyContract {

    interface View extends BaseView {

        void showContent(ZhihuList zhihuList);

        void showLoading();

        void hideLoading();

        void showError();
    }

    abstract static class Presenter extends BasePresenter<View> {

        public abstract void getContent(Context context);

    }

}
