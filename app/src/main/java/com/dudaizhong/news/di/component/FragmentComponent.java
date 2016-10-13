package com.dudaizhong.news.di.component;

import android.app.Activity;

import com.dudaizhong.news.di.module.FragmentModule;
import com.dudaizhong.news.di.scopes.FragmentScope;

import dagger.Component;

/**
 * Created by Dudaizhong on 2016/10/13.
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();
}
