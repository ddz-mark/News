package com.dudaizhong.news.modules.gank.presenter;

import android.content.Context;
import android.util.Log;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.gank.domain.AIList;
import com.dudaizhong.news.modules.gank.domain.GankHttpResponse;
import com.dudaizhong.news.modules.gank.presenter.contract.AIContract;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Markable on 2016/11/22.
 */

public class AIPresenter extends AIContract.Presenter{

    private Context mContext;

    public AIPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void getContent(String type,int num,int page) {
        RetrofitSingleton.getInstance().getGankList(type,num,page)
                .filter(new Func1<GankHttpResponse<List<AIList>>, Boolean>() {
                    @Override
                    public Boolean call(GankHttpResponse<List<AIList>> response) {
                        return !response.getError();
                    }
                })
                .compose(this.<GankHttpResponse<List<AIList>>>bindToLifeCycle())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getView().hideLoading();
                    }
                })
                .subscribe(new Observer<GankHttpResponse<List<AIList>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        RetrofitSingleton.disposeFailureInfo(e,mContext);
                    }

                    @Override
                    public void onNext(GankHttpResponse<List<AIList>> response) {
                        getView().showContent((ArrayList<AIList>) response.getResults());
                    }
                });
                
    }
}
