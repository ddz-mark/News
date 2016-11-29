package com.dudaizhong.news.modules.zhihu.presenter;

import android.content.Context;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuShortCommentData;
import com.dudaizhong.news.modules.zhihu.presenter.contract.CommentContract;
import com.orhanobut.logger.Logger;

import rx.Observer;
import rx.functions.Action0;

/**
 * Created by Markable on 2016/11/20.
 */

public class CommentPresenter extends CommentContract.Presenter {


    @Override
    public void getContent(final Context context, int id, int kind) {
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
                            try {
                                getView().showError();
                                RetrofitSingleton.disposeFailureInfo(e, context);
                            } catch (Exception e1) {
                                Logger.e(e1.getMessage());
                            }
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
                            try {
                                getView().showError();
                                RetrofitSingleton.disposeFailureInfo(e, context);
                            } catch (Exception e1) {
                                Logger.e(e1.getMessage());
                            }
                        }

                        @Override
                        public void onNext(ZhihuShortCommentData zhihuLongCommentData) {
                            getView().showShortComment(zhihuLongCommentData);
                        }
                    });
        }
    }
}
