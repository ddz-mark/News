package com.dudaizhong.news.modules.zhihu.presenter;

import com.dudaizhong.news.common.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.HotList;
import com.dudaizhong.news.modules.zhihu.presenter.contract.HotContract;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public class HotPresenter extends HotContract.Presenter {

    @Override
    public void getContent() {
        RetrofitSingleton.getInstance().getHotList()
                .compose(this.<HotList>bindToLifeCycle())
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
                .subscribe(new Action1<HotList>() {
                    @Override
                    public void call(HotList hotList) {
                        getView().showContent(hotList);
                    }
                });
    }
}
