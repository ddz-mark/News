package com.dudaizhong.news.modules.gank.presenter.contract;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.gank.domain.AIList;

import java.util.ArrayList;

/**
 * Created by Markable on 2016/11/25.
 */

public interface MeiziContract {

    interface View extends BaseView {

        void showLoading();

        void hideLoading();

    }

    abstract static class Presenter extends BasePresenter<View> {

    }
}
