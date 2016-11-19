package com.dudaizhong.news.modules.zhihu.presenter;

import android.content.Context;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuConst;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuData;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;
import com.dudaizhong.news.modules.zhihu.presenter.contract.DailyContract;

import java.io.Serializable;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by Dudaizhong on 2016/9/24.
 */

public class DailyPresenter extends DailyContract.Presenter {

//    private List<ZhihuList.StoriesBean> datas;
//    private Context context;
//
//    public DailyPresenter(List<ZhihuList.StoriesBean> datas, Context context) {
//        this.datas = datas;
//        this.context = context;
//    }

    @Override
    public void getContent() {

        RetrofitSingleton.getInstance().getZhihuListNews()
                .compose(this.<ZhihuList>bindToLifeCycle())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getView().hideLoading();
                    }
                })
                .subscribe(new Observer<ZhihuList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        RetrofitSingleton.disposeFailureInfo(e,context);
                    }

                    @Override
                    public void onNext(ZhihuList zhihuList) {
                        getView().showContent(zhihuList);
                    }
                });
    }


    @Override
    public void showLoading() {
        getView().showLoading();
    }

}
