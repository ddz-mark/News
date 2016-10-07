package com.dudaizhong.news.modules.zhihu.presenter;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.ThemeList;
import com.dudaizhong.news.modules.zhihu.presenter.contract.ThemeContract;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public class ThemePresenter extends ThemeContract.Presenter {

    @Override
    public void getContent() {
        RetrofitSingleton.getInstance().getThemeList()
                .compose(this.<ThemeList>bindToLifeCycle())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        getView().showLoading();
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getView().hideLoading();
                    }
                })
                .subscribe(new Action1<ThemeList>() {
                    @Override
                    public void call(ThemeList themeList) {
                        getView().showContent(themeList);
                    }
                });


    }
}
