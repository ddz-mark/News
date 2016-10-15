package com.dudaizhong.news.modules.launch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.base.utils.SharedPreferencesUtil;
import com.dudaizhong.news.modules.main.MainActivity;

import rx.Observable;

/**
 * Created by Dudaizhong on 2016/10/15.
 */

public class SplashActivity extends AppCompatActivity{

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!SharedPreferencesUtil.getFirst()) {
            // 进入引导页
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, LaunchActivity.class));
                    finish();
                }
            });
        } else {
            // 进入主页
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            });

        }
    }

}
