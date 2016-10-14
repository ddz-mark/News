package com.dudaizhong.news.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.dudaizhong.news.common.api.RetrofitSingleton;
import com.dudaizhong.news.di.component.AppComponent;
import com.dudaizhong.news.di.component.DaggerAppComponent;
import com.dudaizhong.news.di.module.AppModule;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dudaizhong on 2016/9/13.
 * 继承自Application,封装
 */

public class App extends Application {

    public static String cacheDir = "";
    public static Context context;
//    private Set<Activity> activitySet;
    public static AppComponent appComponent;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();

        /*网络调试,在chrome的地址栏输入chrome://inspect
         *通过Elements标签查看界面的视图结构
         *通过Network标签观察网络请求
         *通过Resources标签查看本地数据，比如sqlite数据库，sharepreference等等。同时可以在这里执行sql语句
         *通过Console标签，在这里执行js语句，可以在APP上弹出一个Toast
         */
        Stetho.initializeWithDefaults(this);

        //初始化屏幕宽高
        getScreenSize();

        //初始化网络
        RetrofitSingleton.getInstance();

        //初始化日志
        Logger.init(getPackageName()).hideThreadInfo();

        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }

        /**
         * 初始化依赖加载器
         */
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

//    public void addActivity(Activity activity) {
//        if (activitySet == null) {
//            activitySet = new HashSet<>();
//        }
//        activitySet.add(activity);
//    }
//
//    public void removeActivity(Activity activity) {
//        if (null != activitySet) {
//            activitySet.remove(activity);
//        }
//    }
//
//    /**
//     * 杀掉所有Activity，并退出程序
//     */
//    public void removeAllActivity() {
//        if (null != activitySet) {
//            synchronized (activitySet) {
//                for (Activity activity : activitySet) {
//                    activity.finish();
//                }
//            }
//        }
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
//    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static Context getContext() {
        return context;
    }

}
