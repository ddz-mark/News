package com.dudaizhong.news.modules.launch;

/**
 * Created by Dudaizhong on 2016/9/28.
 */

public class LaunchPresenter extends LaunchContract.Presenter {


    @Override
    protected void jumpToMain() {
        getView().jumpToMain();
    }
}
