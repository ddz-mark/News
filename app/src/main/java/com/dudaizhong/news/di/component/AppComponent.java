package com.dudaizhong.news.di.component;

import com.dudaizhong.news.app.App;
import com.dudaizhong.news.di.module.AppModule;
import com.dudaizhong.news.di.scopes.ContextLife;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dudaizhong on 2016/10/12.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    App getContext();

    //以下是注射目标，也就是说想要在哪个类里面使用依赖注解，必须在component里注射

}
