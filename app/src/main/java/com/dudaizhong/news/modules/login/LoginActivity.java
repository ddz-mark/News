package com.dudaizhong.news.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.modules.main.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/30.
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

//    @Override
//    protected LoginPresenter createPresenter() {
//        return new LoginPresenter();
//    }

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
    public <V> Observable.Transformer<V, V> bind() {
        return bindToLifecycle();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(mActivity, "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(mActivity, MainActivity.class));
    }

    @Override
    public void onLoginFaild() {
        Toast.makeText(mActivity, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                mPresenter.login(name.getText().toString(),password.getText().toString());
                break;
            case R.id.register:
                mPresenter.register();
                break;
        }
    }
}
