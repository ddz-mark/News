package com.dudaizhong.news.di.module;

import com.dudaizhong.news.app.App;
import com.dudaizhong.news.di.scopes.ContextLife;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Dudaizhong on 2016/10/11.
 */
@Module
public class AppModule {

    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    public App provideApplication(){
        return application;
    }


}
