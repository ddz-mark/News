package com.dudaizhong.news.modules.zhihu.presenter.contract;

import android.content.Context;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetail;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetailZip;

/**
 * Created by Markable on 2016/11/19.
 */

public interface ZhihuDetailContract {

    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showContent(ZhihuDetailZip zhihuDetailZip);

        void showIsLike(boolean isLike);

    }

    abstract static class Presenter extends BasePresenter<View> {

        public abstract void showLoading();

        public abstract void getContent(int id);

        public abstract void insertLike(Context context);

        public abstract void deleteLike(Context context);

        public abstract void queryLike(Context context,int id);

        public abstract void mainToCommentActivity(Context context);
    }

}
