package com.dudaizhong.news.modules.main.presenter;

import android.content.Context;

import com.dudaizhong.news.common.db.RealmHelper;
import com.dudaizhong.news.modules.main.domain.RealmLikeBean;
import com.dudaizhong.news.modules.main.presenter.contract.LikeContract;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Markable on 2016/11/26.
 */

public class LikePresenter extends LikeContract.Presenter {

    @Inject
    public LikePresenter() {
    }

    @Override
    public void getContent(Context context) {

        getView().showContent((ArrayList<RealmLikeBean>) RealmHelper.getIntance(context).getLikeList());
    }
}
