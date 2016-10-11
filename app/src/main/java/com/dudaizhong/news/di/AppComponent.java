package com.dudaizhong.news.di;

import com.dudaizhong.news.app.App;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dudaizhong on 2016/10/11.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    App getApplication();

    //以下是注射目标，也就是说想要在哪个类里面使用依赖注解，必须在component里注射

}
