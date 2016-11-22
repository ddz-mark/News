package com.dudaizhong.news.modules.gank.presenter.contract;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.gank.domain.AIList;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuShortCommentData;
import com.dudaizhong.news.modules.zhihu.presenter.contract.CommentContract;

import java.util.ArrayList;

/**
 * Created by Markable on 2016/11/22.
 */

public interface AIContract {
    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showContent(ArrayList<AIList> aiList);

    }

    abstract static class Presenter extends BasePresenter<View> {

        public abstract void getContent(String type,int num,int page);

    }
}
