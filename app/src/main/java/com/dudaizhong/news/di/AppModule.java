package com.dudaizhong.news.di;

import com.dudaizhong.news.app.App;

import javax.inject.Inject;
import javax.inject.Singleton;

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
    public App provideApplication(){
        return application;
    }



}
