package com.dudaizhong.news.modules.zhihu.presenter;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.SectionList;
import com.dudaizhong.news.modules.zhihu.presenter.contract.SectionContract;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public class SectionPresenter extends SectionContract.Presenter {

    @Override
    public void getContent() {
        RetrofitSingleton.getInstance().getSectionList()
                .compose(this.<SectionList>bindToLifeCycle())
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
                .subscribe(new Action1<SectionList>() {
                    @Override
                    public void call(SectionList sectionList) {
                        getView().showContent(sectionList);
                    }
                });
    }
}
