package com.dudaizhong.news.modules.login.presenter;

import android.content.Context;

import com.dudaizhong.news.common.db.RealmHelper;
import com.dudaizhong.news.modules.login.domain.User;
import com.dudaizhong.news.modules.login.presenter.contract.RegisterContract;

import javax.inject.Inject;

/**
 * Created by Markable on 2016/11/26.
 */

public class RegisterPresenter extends RegisterContract.Presenter {

    @Inject
    public RegisterPresenter() {
    }

    @Override
    public void register(Context context, String name, String password) {
        if (name.equals("") || password.equals("")) {
            getView().registerFailed();
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            RealmHelper.getIntance(context).register(user);
            getView().registerSuccess();
        }
    }
}
