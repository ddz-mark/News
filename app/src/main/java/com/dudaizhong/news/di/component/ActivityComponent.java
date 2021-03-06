package com.dudaizhong.news.di.component;

import android.app.Activity;

import com.dudaizhong.news.di.module.ActivityModule;
import com.dudaizhong.news.di.scopes.ActivityScope;
import com.dudaizhong.news.modules.gank.activity.MeiziActivity;
import com.dudaizhong.news.modules.launch.LaunchActivity;
import com.dudaizhong.news.modules.login.activity.LoginActivity;
import com.dudaizhong.news.modules.login.activity.RegisterActivity;
import com.dudaizhong.news.modules.main.activity.LikeActivity;
import com.dudaizhong.news.modules.zhihu.activity.SectionDetailActivity;
import com.dudaizhong.news.modules.zhihu.activity.ThemeDetailActivity;
import com.dudaizhong.news.modules.zhihu.activity.ZhihuDetailActivity;

import dagger.Component;
/**
 * Created by Dudaizhong on 2016/10/12.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(LaunchActivity launchActivity);

    void inject(LoginActivity loginActivity);

    void inject(ZhihuDetailActivity zhihuDetailActivity);

    void inject(ThemeDetailActivity themeDetailActivity);

    void inject(SectionDetailActivity sectionDetailActivity);

    void inject(MeiziActivity meiziActivity);

    void inject(LikeActivity likeActivity);

    void inject(RegisterActivity registerActivity);

}
