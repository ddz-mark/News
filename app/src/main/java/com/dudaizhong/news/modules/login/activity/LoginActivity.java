package com.dudaizhong.news.modules.login.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.base.utils.ToastUtil;
import com.dudaizhong.news.base.utils.rxUtils.RxBus;
import com.dudaizhong.news.modules.login.domain.event.RefreshEvent;
import com.dudaizhong.news.modules.login.presenter.contract.LoginContract;
import com.dudaizhong.news.modules.login.presenter.LoginPresenter;
import com.dudaizhong.news.modules.main.activity.MainActivity;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Dudaizhong on 2016/9/30.
 * Github:ddz-mark
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.register)
    Button register;

    ProgressDialog mProgressDialog;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {
        showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mProgressDialog.dismiss();
            }
        }).start();
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(mActivity, MainActivity.class));
        RxBus.getDefault().post(new RefreshEvent(name.getText().toString()));
        ToastUtil.showToast(LoginActivity.this, "登录成功");
    }

    @Override
    public void onLoginFaild() {
        ToastUtil.showToast(LoginActivity.this, "登录失败，重新登录");
    }

    @OnClick({R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                mPresenter.login(LoginActivity.this, name.getText().toString(), password.getText().toString());
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    public void showProgressDialog() {
        mProgressDialog = new ProgressDialog(LoginActivity.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("登录中...");
        mProgressDialog.setIcon(android.R.drawable.btn_star);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

}
