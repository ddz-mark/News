package com.dudaizhong.news.modules.main.presenter.contract;

import android.content.Context;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.main.domain.RealmLikeBean;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuShortCommentData;
import com.dudaizhong.news.modules.zhihu.presenter.contract.CommentContract;

import java.util.ArrayList;

/**
 * Created by Markable on 2016/11/26.
 */

public interface LikeContract {

    interface  View extends BaseView {

        void showContent(ArrayList<RealmLikeBean> data);

    }

    abstract static class Presenter extends BasePresenter<View> {

        public abstract void getContent(Context context);

    }
}
