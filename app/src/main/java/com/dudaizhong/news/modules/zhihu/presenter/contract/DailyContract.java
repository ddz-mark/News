package com.dudaizhong.news.modules.zhihu.presenter.contract;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuListNews;

/**
 * Created by Dudaizhong on 2016/9/24.
 */

public interface DailyContract {

    interface View extends BaseView{

        void showContent(ZhihuListNews zhihuListNews);

    }

    abstract static class Presenter extends BasePresenter<View>{

        protected abstract void getContent();

        protected abstract void showLoading();

    }

}
