package com.dudaizhong.news.modules.main;

import com.dudaizhong.news.base.BaseModel;
import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.base.BaseView;

/**
 * Created by Dudaizhong on 2016/9/16.
 */

public interface MainContract {


    interface View extends BaseView{

    }

    abstract static class Presenter extends BasePresenter<View> {

    }

}
