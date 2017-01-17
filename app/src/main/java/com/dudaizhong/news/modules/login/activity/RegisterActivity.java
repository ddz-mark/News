package com.dudaizhong.news.modules.login.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.base.utils.ToastUtil;
import com.dudaizhong.news.modules.login.presenter.RegisterPresenter;
import com.dudaizhong.news.modules.login.presenter.contract.RegisterContract;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Markable on 2016/11/26.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    ProgressDialog mProgressDialog;

    @Bind(R.id.input_name)
    EditText mInputName;
    @Bind(R.id.input_password)
    EditText mInputPassword;
    @Bind(R.id.register)
    Button mRegister;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        initWindow();
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().register(RegisterActivity.this, mInputName.getText().toString(), mInputPassword.getText().toString());
            }
        });
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void registerSuccess() {
        ToastUtil.showToast(RegisterActivity.this, "注册成功");
        mProgressDialog.dismiss();
        startActivity(LoginActivity.getLoginAvtivityIntent(RegisterActivity.this, mInputName.getText().toString(), mInputPassword.getText().toString()));
    }

    @Override
    public void registerFailed() {
        mProgressDialog.dismiss();
        ToastUtil.showToast(RegisterActivity.this, "注册失败，请重新输入");
    }

    public void showProgressDialog() {
        mProgressDialog = new ProgressDialog(RegisterActivity.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("注册中...");
        mProgressDialog.setIcon(android.R.drawable.btn_star);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }


}
