package com.dudaizhong.news.modules.zhihu.presenter.contract;

import android.content.Context;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.zhihu.domain.SectionDetail;

/**
 * Created by Markable on 2016/11/19.
 */

public interface SectionDetailContract {

    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        void showContent(SectionDetail sectionDetail);

        void showError();
    }

    abstract static class Presenter extends BasePresenter<View> {

        public abstract void getContent(Context context,int id);
    }
}
