package com.dudaizhong.news.modules.zhihu.presenter;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuShortCommentData;
import com.dudaizhong.news.modules.zhihu.presenter.contract.CommentContract;

import rx.Observer;
import rx.functions.Action0;

/**
 * Created by Markable on 2016/11/20.
 */

public class CommentPresenter extends CommentContract.Presenter {


    @Override
    public void getContent(int id, int kind) {
        if (kind == 0) {
            RetrofitSingleton.getInstance().getZhihuShortCommentInfo(id)
                    .compose(this.<ZhihuShortCommentData>bindToLifeCycle())
                    .doOnTerminate(new Action0() {
                        @Override
                        public void call() {
                            getView().hideLoading();
                        }
                    })
                    .subscribe(new Observer<ZhihuShortCommentData>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(ZhihuShortCommentData zhihuShortCommentData) {
                            getView().showShortComment(zhihuShortCommentData);
                        }
                    });
        } else if (kind == 1) {
            RetrofitSingleton.getInstance().getZhihuLongCommentInfo(id)
                    .compose(this.<ZhihuShortCommentData>bindToLifeCycle())
                    .doOnTerminate(new Action0() {
                        @Override
                        public void call() {
                            getView().hideLoading();
                        }
                    })
                    .subscribe(new Observer<ZhihuShortCommentData>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(ZhihuShortCommentData zhihuLongCommentData) {
                            getView().showShortComment(zhihuLongCommentData);
                        }
                    });
        }
    }
}
