package com.dudaizhong.news.modules.zhihu.presenter.contract;

import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;

/**
 * Created by Markable on 2016/11/19.
 */

public interface SectionDetailContract {

    interface View extends BaseView {

    }

    abstract static class Presenter extends BasePresenter<ThemeDetailContract.View> {

    }
}
