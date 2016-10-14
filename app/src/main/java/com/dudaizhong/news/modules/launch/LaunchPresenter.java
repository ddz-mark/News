package com.dudaizhong.news.modules.launch;

import javax.inject.Inject;

/**
 * Created by Dudaizhong on 2016/9/28.
 */

public class LaunchPresenter extends LaunchContract.Presenter {

    @Inject
    public LaunchPresenter() {
    }

    @Override
    protected void jumpToMain() {
        getView().jumpToMain();
    }
}
