package com.dudaizhong.news.modules.zhihu.presenter;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.HotList;
import com.dudaizhong.news.modules.zhihu.domain.SectionDetail;
import com.dudaizhong.news.modules.zhihu.presenter.contract.SectionDetailContract;

import javax.inject.Inject;

import rx.Observer;
import rx.functions.Action0;

/**
 * Created by Markable on 2016/11/19.
 */

public class SectionDetailPresenter extends SectionDetailContract.Presenter {

    @Inject
    public SectionDetailPresenter() {
    }

    @Override
    public void getContent(int id) {
        RetrofitSingleton.getInstance().getSetionDetail(id)
                .compose(this.<SectionDetail>bindToLifeCycle())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getView().hideLoading();
                    }
                })
                .subscribe(new Observer<SectionDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SectionDetail sectionDetail) {
                        getView().showContent(sectionDetail);
                    }
                });
    }
}
