package com.dudaizhong.news.base;

import com.github.moduth.blockcanary.BlockCanaryContext;
import com.github.moduth.blockcanary.android.BuildConfig;

public class AppBlockCanaryContext extends BlockCanaryContext {

    // override to provide context like app qualifier, uid, network type, block threshold, log save path

    // this is default block threshold, you can set it by phone's performance
    //设置卡顿判断的阈值
    @Override
    public int getConfigBlockThreshold() {
        return 500;
    }

    // if set true, notification will be shown, else only write log file
    @Override
    public boolean isNeedDisplay() {
        return BuildConfig.DEBUG;
    }

    // path to save log file
    //设置log保存在sd卡的目录位置
    @Override
    public String getLogPath() {
        return "/blockcanary/performance";
    }
}